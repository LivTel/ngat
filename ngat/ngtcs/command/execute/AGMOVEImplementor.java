package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Move autoguider probe mirror to a radial position, rotator to amount
 * position angle and set autoguider pixel on which to guide.
 * 
 * 
 * @author $Author: je $
 * @version $Revision: 1.1 $
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
  public static final String RevisionString =
    new String( "$Id: AGMOVEImplementor.java,v 1.1 2003-09-22 13:24:36 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Timeout for the AGMOVE command.
   */
  public final int TIMEOUT = 500000;

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
  public AGMOVEImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    AGMOVE a = (AGMOVE)command;
    double radius = a.getRadius();
    double angle = a.getRotatorAngle();
    double x = a.getXPixel();
    double y = a.getYPixel();

    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    TTL_Rotator rot = TTL_Rotator.getInstance();
    double actual, posError, tolerance;

    try
    {
      ag.setDemandPosition( radius );
      rot.setDemandPosition( angle );
      ag.guideOnPixel( x, y );

      // Rotator position tolerance
      tolerance = rot.getPositionTolerance();
      do
      {
	slept += 5000;
	try
	{
	  Thread.sleep( 5000 );
	}
	catch( InterruptedException ie )
	{
	  logger.log( 1, logName, ie.toString() );
	}
	actual = rot.getActualPosition();
	posError = Math.abs( actual - angle );
      }
      while( ( ag.get_AGD_State() == AGD_State.E_AGD_STATE_MOVING )&&
	     ( posError > tolerance )&&( slept < TIMEOUT ) );

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

      actual = ag.getActualPosition();
      posError = Math.abs( actual - radius );
      tolerance = ag.getPositionTolerance();
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

      commandDone.setSuccessful( true );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
    }
  }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: AGMOVEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGMOVEImplementor.java,v $
 *      $Id: AGMOVEImplementor.java,v 1.1 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
