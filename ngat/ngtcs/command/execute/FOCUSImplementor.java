package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class FOCUSImplementor extends CommandImplementor
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
    new String( "$Id: FOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
  public FOCUSImplementor
    ( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    TTL_SecondaryMirror sm =
      (TTL_SecondaryMirror)TTL_SecondaryMirror.getInstance();

    FOCUS focus = (FOCUS)command;

    if( focus.isDefaultPosition() )
    {

    }
    else
    {
      try
      {
	double actual, demand, posError, tolerance;
	demand = focus.getPosition();
	posError = 0.0;
	tolerance = sm.getPositionTolerance();


	if( ( demand < sm.getMinimumDemandPosition() ) ||
	    ( demand > sm.getMaximumDemandPosition() ) )
	{
	  commandDone.setErrorMessage
	    ( "Requested position [ "+demand+"mm ] will drive the mirror out "+
	      "of operational range [ "+sm.getMinimumDemandPosition()+"mm - "+
	      sm.getMaximumDemandPosition()+"mm ]: execution terminated" );
	  return;
	}

	// check state is READY or MOVING
	SMF_State smf_State = sm.get_SMF_State();
	if( ( smf_State != SMF_State.E_SMF_STATE_READY ) ||
	    ( smf_State != SMF_State.E_SMF_STATE_MOVING ) )
	{
	  commandDone.setErrorMessage
	    ( "Secondary Mirror state ["+smf_State.getName()+
	      "] is not READY or MOVING : execution terminated" );
	  return;
	}

	// send position demand
	sm.setDemandPosition( demand );

	// check state is STOPPED
	int nAck = 0;
	posError = 999.0; //larger than possible until first real reading

	// ??
	// HOW TO HANDLE A MOVEMENT *THROUGH* THE TOLERANCE RANGE SO THAT
	// EXECUTION DOES NOT RETURN SUCCESS PREMATURELY BECAUSE WE CANNOT USE
	// E_STATE_MOVING AS IT CAN APPLY TO TEMPERATURE COMPENSATION
	// ??
	//
	// TEMP-COMP ONLY MOMENTARY - USE ANYWAY??
	//
	while( ( nAck < 30 ) && ( posError > tolerance ) )
	{
	  actual = sm.getActualPosition();
	  demand = sm.getDemandPosition();
	  posError = Math.abs( actual - demand );

	  // sleep 1/30th of Timeout				      
	  try
	  {
	    Thread.sleep( 10000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log( 1, logName, ie );
	  }
	}

	actual = sm.getActualPosition();
	demand = sm.getDemandPosition();
	posError = Math.abs( actual - demand );
	if( posError > tolerance )
	{
	  logger.log
	    ( 1, logName, "The Secondary Mirror focus has stopped at "+
	      actual+"mm which is more than the acceptable position error [ "+
	      tolerance+"mm ] away from the demand [ "+demand+
	      "mm ] : execution terminated" );
	  return;
	}

	sm.setFocusPosition( actual );
      }
      catch( TTL_SystemException se )
      {
	logger.log( 1, logName, se );
	commandDone.setErrorMessage( se.toString() );
	return;
      }
    }
    commandDone.setSuccessful( true );
  }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: FOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/FOCUSImplementor.java,v $
 *      $Id: FOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
