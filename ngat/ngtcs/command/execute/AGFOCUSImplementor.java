package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGFOCUSImplementor extends CommandImplementor
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
    new String( "$Id: AGFOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
  public AGFOCUSImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    AGFOCUS a = (AGFOCUS)command;
    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    double posError, actual, demand, tolerance;
    int nAck = 0;

    try
    {
      actual = ag.getActualFocusPosition();
      demand = a.getPosition();
      tolerance = ag.getFocusPositionTolerance();

      if( Math.abs( demand - actual ) < tolerance )
      {
	commandDone.setReturnMessage( "focus already in position" );
      }
      else
      {
	ag.setDemandFocusPosition( demand );
	boolean completed = false;

	while( ( nAck < 13 )&&( !completed ) )
	{
	  Acknowledge ack = new Acknowledge
	    ( command.getId()+"."+( nAck++ ), command );
	  ack.setTimeToComplete( 5500 );
	  executionThread.sendAcknowledge( ack );

	  try
	  {
	    Thread.sleep( 5000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log( 1, logName, ie.toString() );
	  }

	  actual = ag.getActualFocusPosition();
	  posError = demand - actual;
	  if( Math.abs( posError ) < tolerance )
	    completed = true;
	}

	actual = ag.getActualFocusPosition();
	posError = demand - actual;
	if( Math.abs( posError ) > tolerance )
	{
	  String err = new String
	    ( "Autoguider focus has not achieved the "+
	      "demanded position ["+demand+"mm] after 60 seconds; "+
	      "position acheived = "+actual+"mm : execution terminated" );
	  logger.log( 1, logName, err );
	  commandDone.setErrorMessage( err );
	  return;
	}
      }
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
      return;
    }

    commandDone.setSuccessful( true );
  }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: AGFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFOCUSImplementor.java,v $
 *      $Id: AGFOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
