package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Move fold mirror to a predetermined position.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.4 $
 */
public class MOVE_FOLDImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: MOVE_FOLDImplementor.java,v 1.4 2003-10-16 15:30:36 je Exp $" );

  /**
   * The timeout for the MOVE_FOLD command (120 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 120000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public MOVE_FOLDImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    MOVE_FOLD mf = (MOVE_FOLD)command;
    int portNumber = mf.getPosition();
    TTL_FoldMirror fm = TTL_FoldMirror.getInstance();
    SFD_State sfdState;
    SFP_State sfpState;
    SFD_DeployState deployment;
    SFP_Port port;

    try
    {
      if( portNumber == 0 )
      {
	port = null; //what should this be?
	deployment = SFD_DeployState.E_SFD_POS_STOW;
	fm.retract();
      }
      else
      {
	port = SFP_Port.getReference( portNumber );
	deployment = SFD_DeployState.E_SFD_POS_DEPLOY;
	fm.deploy();
	fm.setPortDemand( port );
      }

      do
      {
	sleep( 10000 );
	sfdState = fm.get_SFD_State();
	sfpState = fm.get_SFP_State();
      }
      while( ( ( sfdState != SFD_State.E_SFD_STATE_READY )||
	       ( sfpState != SFP_State.E_SFP_STATE_READY ) )&&
	     ( slept < TIMEOUT ) );

      if( slept > TIMEOUT )
      {
	logError
	  ( "Fold Mirror has not acheived desired state after "+TIMEOUT+
	    "ms [deploy state = "+sfdState.getName()+", position state = "+
	    sfpState.getName()+"] : execution terminated" );
	return;
      }
      else if( fm.getActualPort() != port )
      {
	logError
	  ( "Command has finished but actual port ["+
	    fm.getActualPort().getName()+"] is not the desired port ["+
	    port.getName()+"] : execution terminated" );
	return;
      }
      else if( fm.getDeployState() != deployment )
      {
	logError
	  ( "Command has finished but actual deployment ["+
	    fm.getDeployState().getName()+"] is not the desired deployment ["+
	    deployment.getName()+"] : execution terminated" );
	return;
      }
    }
    catch( TTL_SystemException tse )
    {
      logError( tse.toString() );
    }

    commandDone.setSuccessful( true );
    return;
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
 *    $Date: 2003-10-16 15:30:36 $
 * $RCSfile: MOVE_FOLDImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/MOVE_FOLDImplementor.java,v $
 *      $Id: MOVE_FOLDImplementor.java,v 1.4 2003-10-16 15:30:36 je Exp $
 *     $Log: not supported by cvs2svn $
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
