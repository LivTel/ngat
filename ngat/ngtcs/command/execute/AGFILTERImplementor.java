package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;


import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * This implementor will move the autoguider filter either in or out of the
 * autoguider beam.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class AGFILTERImplementor
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
    new String( "$Id: AGFILTERImplementor.java,v 1.3 2003-09-22 11:37:26 je Exp $" );

  /**
   * Timeout for this command, in milliseconds (60000).
   */
  public static final int TIMEOUT = 60000;

  /**
   * Number of Acknowledgements before a timeout (6).
   */
  public static final int MAX_TIMEOUTS = 6;

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
  public AGFILTERImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    boolean move = false;
    boolean deploy = ( (AGFILTER)command ).deploy();
    AGD_FilterPosition demand = null;

    try
    {
      TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
      AGD_FilterPosition pos = ag.getActualFilterPosition();

      // Do I need to init if pos = E_AGD_IR_UNSET ??

      if( deploy )
      {
	if( ( pos == AGD_FilterPosition.E_AGD_IR_UNSET )||
	    ( pos == AGD_FilterPosition.E_AGD_IR_RETRACT ) )
	{
	  demand = AGD_FilterPosition.E_AGD_IR_IN_LINE;
	  ag.setDemandFilterPosition( demand );
	  move = true;

	  logger.log( 3, logName, "Autoguider filter demand set to IN_LINE" );
	}
	else
	{
	  String msg = new String( "Autoguider filter already deployed" );
	  logger.log( 3, logName, msg );
	  commandDone.setReturnMessage( msg );
	}
      }
      else
      {
	if( ( pos == AGD_FilterPosition.E_AGD_IR_UNSET )||
	    ( pos == AGD_FilterPosition.E_AGD_IR_IN_LINE ) )
	{
	  demand = AGD_FilterPosition.E_AGD_IR_RETRACT;
	  ag.setDemandFilterPosition( demand );
	  move = true;

	  logger.log( 3, logName, "Autoguider filter demand set to RETRACT" );
	}
	else if( pos == AGD_FilterPosition.E_AGD_IR_RETRACT )
	{
	  String msg = new String( "Autoguider filter already retracted" );
	  logger.log( 3, logName, msg );
	  commandDone.setReturnMessage( msg );
	}
      }

      // if moving wait until stopped
      if( move == true )
      {
	do
	{
	  // check state every second
	  slept += 1000;
	  try
	  {
	    Thread.sleep( 1000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log( 1, logName, ie );
	  }
	}
	while( ( ag.get_AGD_State() == AGD_State.E_AGD_STATE_MOVING )&&
	       ( slept < TIMEOUT ) );
      }

      // if timeout or State != MOVING
      AGD_FilterPosition actual = ag.getActualFilterPosition();
      if( demand != actual )
      {
	String err = new String
	  ( "Autoguider position ["+actual.getName()+"] did not reach demand ["
	    +demand+"] before timeout ["+TIMEOUT+"ms] and AGD state = "+
	    ag.get_AGD_State().getName()+" : execution terminated" );
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
 *    $Date: 2003-09-22 11:37:26 $
 * $RCSfile: AGFILTERImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFILTERImplementor.java,v $
 *      $Id: AGFILTERImplementor.java,v 1.3 2003-09-22 11:37:26 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 11:32:28  je
 *     Added 'TIMEOUT' and 'slept' functionality from super-class.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
