package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;


import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
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
    new String( "$Id: AGFILTERImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected AGD_State state;

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
	  ag.setDemandedFilterPosition
	    ( AGD_FilterPosition.E_AGD_IR_IN_LINE );
	  move = true;
	}
	else
	{
	  commandDone.setReturnMessage
	    ( "filter already deployed" );
	}
      }
      else
      {
	if( ( pos == AGD_FilterPosition.E_AGD_IR_UNSET )||
	    ( pos == AGD_FilterPosition.E_AGD_IR_IN_LINE ) )
	{
	  ag.setDemandedFilterPosition
	    ( AGD_FilterPosition.E_AGD_IR_RETRACT );
	  move = true;
	}
	else if( pos == AGD_FilterPosition.E_AGD_IR_RETRACT )
	{
	  commandDone.setReturnMessage
	    ( "filter already retracted" );
	}
      }

      if( move == true )
      {
	do
	{

	  // sleep and ACK ??

	}
	while( ag.get_AGD_State() ==
	       AGD_State.E_AGD_STATE_MOVING );
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
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: AGFILTERImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFILTERImplementor.java,v $
 *      $Id: AGFILTERImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
