package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class CALIBRATEImplementor
  extends CommandImplementor
  implements Runnable
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
    new String( "$Id: CALIBRATEImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

  /**
   * Length of time (milliseconds) to sleep between sending back Acknowledges.
   */
  public static final int SLEEP_MILLIS = 10000;

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected boolean calibrating = false;

  /**
   *
   */
  protected int nAck = 0;

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
  public CALIBRATEImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    CALIBRATE c = (CALIBRATE)command;
    CalibrateMode cm = c.getMode();
    commandDone = new CALIBRATEDone( c );

    if( cm == CalibrateMode.DEFAULT )
    {
      try
      {
	telescope.getMount().getPointingModel().initialise( telescope );
      }
      catch( InitialisationException ie )
      {
	logger.log( 1, logName, ie );
	return;
      }
    }
    else if( cm == CalibrateMode.NEW )
    {
      PointingModelCoefficients pmc = null;

      calibrating = true;

      // start a thread to send back Acknowledges until command is completed
      Thread t = new Thread( this );
      t.setPriority( Thread.MIN_PRIORITY );
      t.start();

      //perform some calibration


      commandDone.setReturnMessage( "RMS of calibration is:" );
      ( (CALIBRATEDone)commandDone ).setPointingModelCoefficients( pmc );

      calibrating = false;
    }
    else if( cm == CalibrateMode.LAST )
    {
      telescope.getMount().getPointingModel().revertValues();
    }

    commandDone.setSuccessful( true );
  }


  /**
   *
   */
  public void run()
  {
    Acknowledge ack = new Acknowledge
      ( command.getId()+"."+( nAck++ ), command );
    ack.setTimeToComplete( SLEEP_MILLIS + 1000 );

    while( calibrating )
    {
      try
      {
	Thread.sleep( SLEEP_MILLIS );

	if( calibrating )
	  executionThread.sendAcknowledge( ack );
      }
      catch( InterruptedException ie )
      {
	logger.log( 1, logName, ie );
      }
    }
  }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: CALIBRATEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/CALIBRATEImplementor.java,v $
 *      $Id: CALIBRATEImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
