package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Move autoguider probe mirror to a radial position.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class AGRADIALImplementor
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
    new String( "$Id: AGRADIALImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the AGRADIAL command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

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
  public AGRADIALImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * This execution sends the demand position, and waits until either the
   * position error of the autoguider position is within the config
   * file-specified tolerance, or the time elapsed exceeds the timeout for this
   * command.
   */
  public void execute()
  {
    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    double posError = 99999, actual = 0.0;
    double demand = ( (AGRADIAL)command ).getRadius();
    double tolerance = ag.getPositionTolerance();

    try
    {
      // send demand
      ag.setDemandPosition( demand );

      // get position
      actual = ag.getActualPosition();
      posError = Math.abs( demand - actual );

      // OK?
      if( posError < tolerance )
      {
	commandDone.setReturnMessage
	  ( "autoguider probe already in position" );
      }
      // wait until OK
      else
      {
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

	  actual = ag.getActualPosition();
	  posError = Math.abs( demand - actual );
	}
	while( ( posError > tolerance )&&( slept < TIMEOUT ) );
      }

      if( posError < tolerance )
      {
	commandDone.setSuccessful( true );
	return;
      }

      String err = new String
	( "Autoguider position ["+actual+"] did not fall within tolerance ["+
	  tolerance+"] of demand ["+demand+"] after "+slept+
	  " seconds : execution terminated" );
      logger.log( 1, logName, err );
      commandDone.setErrorMessage( err );
      return;
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
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: AGRADIALImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGRADIALImplementor.java,v $
 *      $Id: AGRADIALImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
