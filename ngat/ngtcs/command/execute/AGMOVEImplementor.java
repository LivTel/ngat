package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;
import ngat.ngtcs.subsystem.ags.*;

/**
 * Move autoguider probe mirror to a radial position, rotator to a
 * mount-position angle and set autoguider pixel on which to guide.
 * 
 * @author $Author: je $
 * @version $Revision: 1.5 $
 */
public class AGMOVEImplementor
  extends CommandImplementor
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: AGMOVEImplementor.java,v 1.5 2003-09-29 11:55:56 je Exp $" );

  /**
   * The timeout for the AGMOVE command (500 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 500000;

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  public AGMOVEImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * First the autoguider position demand and the rotator angle demand are
   * sent.  After both mechanisms have fallen within tolerance of the demanded
   * positions the GuideOnPixel command is sent to the autoguider.  The
   * autoguider state is then monitored every second to check for the
   * <code>AGS_State.E_AGS_ON_PIXEL</code> state before returning a success.
   * Failure to acheive any of these requirements before the timeout expires
   * results in a failure being returned.
   */
  public void execute()
  {
    AGMOVE a = (AGMOVE)command;
    double radius = a.getPosition();
    double angle = a.getRotatorAngle();
    double x = a.getXPixel();
    double y = a.getYPixel();

    logger.log( 3, logName, "Sending autoguider to "+radius+
		"mm, guiding on pixel ("+x+", "+y+
		"), with mount position angle "+angle+" degrees." );

    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    TTL_Rotator rot = (TTL_Rotator)TTL_Rotator.getInstance();
    double actual, posError, tolerance;

    try
    {
      ag.setDemandPosition( radius );
      rot.setDemandPosition( angle );

      // Rotator position tolerance
      tolerance = rot.getPositionTolerance();

      int sleep = 5000;
      do
      {
	try
	{
	  Thread.sleep( sleep );
	  slept += sleep;
	}
	catch( InterruptedException ie )
	{
	  logger.log( 1, logName, ie.toString() );
	}
	actual = rot.getActualPosition();
	posError = Math.abs( actual - angle );
      }
      while( ( ( ag.get_AGD_State() == AGD_State.E_AGD_STATE_MOVING )||
	       ( posError > tolerance ) )&&( slept < TIMEOUT ) );

      // check rotator position
      if( posError > tolerance )
      {
	String err = new String
	  ( "Rotator position ["+actual+"] did not fall within tolerance ["+
	    tolerance+"] of demand ["+angle+"] within "+TIMEOUT+
	    " seconds : execution terminated" );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
	return;
      }

      // check autoguider position
      actual = ag.getActualPosition();
      posError = Math.abs( actual - radius );
      tolerance = ag.getFocusPositionTolerance();
      if( posError > tolerance )
      {
	String err = new String
	  ( "Autoguider position ["+actual+"] did not fall within tolerance ["+
	    tolerance+"] of demand ["+radius+"] within "+TIMEOUT+
	    " seconds : execution terminated" );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
	return;
      }

      // send GuideOnPixel command
      ag.guideOnPixel( x, y );
      AGS_State state, desired = AGS_State.E_AGS_ON_PIXEL;

      // wait for AGS state change and see if it's the desired one
      // every second
      sleep = 1000;
      do
      {
	try
	{
	  Thread.sleep( sleep );
	  slept += sleep;
	}
	catch( InterruptedException ie )
	{
	  logger.log( 1, logName, ie.toString() );
	}
	state = ag.get_AGS_State();
      }
      while( ( state == AGS_State.E_AGS_WORKING )&&( slept < TIMEOUT ) );

      // if the desired state is not acheived, return a failure
      if( state != desired )
      {
	String s = ( "after "+slept+"ms AGS has acheived "+state.getName()+
		     " state, desired state is "+desired.getName() );
	commandDone.setErrorMessage( s );
	logger.log( 1, logName, s );
	return;
      }

      commandDone.setSuccessful( true );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
    }
  }


  /**
   * Return the default timeout for this command execution.
   * @return TIMEOUT
   * @see #TIMEOUT
   */
  public int calcAcknowledgeTime()
  {
    return( TIMEOUT );
  }
}
/*
 *    $Date: 2003-09-29 11:55:56 $
 * $RCSfile: AGMOVEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGMOVEImplementor.java,v $
 *      $Id: AGMOVEImplementor.java,v 1.5 2003-09-29 11:55:56 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/09/29 11:37:17  je
 *     Added execute documentation.
 *
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/23 14:16:58  je
 *     Added documentation.
 *
 *     Revision 1.1  2003/09/22 13:24:36  je
 *     Initial revision
 *
 *
 */
