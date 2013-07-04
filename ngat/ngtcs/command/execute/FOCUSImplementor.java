package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Drive the focus to a specified setting.
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.5 $
 */
public class FOCUSImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: FOCUSImplementor.java,v 1.5 2013-07-04 10:23:34 cjm Exp $" );

  /**
   * The timeout for the FOCUS command (900 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 900000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                       */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public FOCUSImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * First define the demand to send to the secondary mirror (default or
   * user-input focus), then send the demand and periodically poll the position
   * until it is within tolerance of the demand.  An out of range demand or
   * expired timeout will result in a failure.
   */
  public void execute()
  {
    TTL_SecondaryMirror sm = TTL_SecondaryMirror.getInstance();
    FOCUS focus = (FOCUS)command;
    double actual = 0.0;
    double demand = focus.getPosition();
    double posError = 999999.9;
    double tolerance = sm.getPositionTolerance();

    try
    {
      if( focus.isDefaultPosition() )
      {
	demand = telescope.getActiveVirtualTelescope().
	  getFocalStation().getFocus();
	sm.setDemandPosition( demand );

	do
	{
	  sleep( 10000 );

	  actual = sm.getActualPosition();
	  demand = sm.getDemandPosition();
	  posError = Math.abs( actual - demand );
	}
	while( ( slept < TIMEOUT )&&( posError > tolerance ) );

      }
      else
      {
	if( ( demand < sm.getMinimumDemandPosition() ) ||
	    ( demand > sm.getMaximumDemandPosition() ) )
	{
	  logError
	    ( "Requested position ["+demand+"mm ] will drive the mirror out "+
	      "of operational range ["+sm.getMinimumDemandPosition()+"mm - "+
	      sm.getMaximumDemandPosition()+"mm ]: execution terminated" );
	  return;
	}

	// check state is READY or MOVING
	SMF_State smf_State = sm.get_SMF_State();
	if( ( smf_State != SMF_State.E_SMF_STATE_READY ) ||
	    ( smf_State != SMF_State.E_SMF_STATE_MOVING ) )
	{
	  logError
	    ( "Secondary Mirror state ["+smf_State.getName()+
	      "] is not able to accept commands : execution terminated" );
	  return;
	}

	// send position demand
	sm.setDemandPosition( demand );

	// and wait...
	do
	{
	  sleep( 10000 );

	  actual = sm.getActualPosition();
	  demand = sm.getDemandPosition();
	  posError = Math.abs( actual - demand );
	}
	while( ( slept < TIMEOUT )&&( posError > tolerance ) );

	if( posError > tolerance )
	{
	  logError
	    ( "The Secondary Mirror focus has stopped at "+actual+
	      "mm which is more than the acceptable position error ["+
	      tolerance+"mm ] away from the demand ["+demand+
	      "mm ] : execution terminated" );
	  return;
	}

	// update SM focus and offset internal fields
	sm.setFocusPosition();
	sm.setFocusOffset( 0.0 );
      }
    }
    catch( TTL_SystemException se )
    {
      logError( se.toString() );
      return;
    }
    commandDone.setSuccessful( true );
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
 *    $Date: 2013-07-04 10:23:34 $
 * $RCSfile: FOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/FOCUSImplementor.java,v $
 *      $Id: FOCUSImplementor.java,v 1.5 2013-07-04 10:23:34 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/09/29 14:16:44  je
 *     Implemented execute and added documentation.
 *
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
