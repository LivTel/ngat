package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.spt.*;

/**
 * Prepare the telescope for observing or request the telescope stand-down at
 * the end of an observing session.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class OPERATIONALImplementor extends CommandImplementor
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
    new String( "$Id: OPERATIONALImplementor.java,v 1.4 2013-07-04 10:25:34 cjm Exp $" );

  /**
   * The timeout for the OPERATIONAL command (600 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 600000;

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
  public OPERATIONALImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * Request control from the SPT, then wait until the requested state change
   * happens and return a success, or the TIMEOUT is exceeded and return a
   * failure.
   */
  public void execute()
  {
    OPERATIONAL o = (OPERATIONAL)command;
    SPT spt = SPT.getInstance();
    boolean success = false;
    TTL_SystemState state = null;

    try
    {
      if( o.isOn() )
	spt.requestControl();
      else
	spt.releaseControl();
    }
    catch( TTL_SystemException tse )
    {
      logError( tse.toString() );
      return;
    }

    do
    {
      sleep( 10000 );
      state = (TTL_SystemState)( telescope.getTelescopeState() );
      if( o.isOn() )
      {
	if( ( state == TTL_SystemState.SYS_WARN_STATE )||
	    ( state == TTL_SystemState.SYS_OKAY_STATE ) )
	  success = true;
      }
      else
      {
	if( state == TTL_SystemState.SYS_STANDBY_STATE )
	  success = true;
      }
    }
    while( ( success == false )&&( slept < TIMEOUT ) );

    if( slept > TIMEOUT )
    {
      logError( "Telescope state ["+state.getName()+"] is not as requested "+
		"after "+slept+"ms : execution terminated" );
    }

    commandDone.setSuccessful( success );
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
 *    $Date: 2013-07-04 10:25:34 $
 * $RCSfile: OPERATIONALImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/OPERATIONALImplementor.java,v $
 *      $Id: OPERATIONALImplementor.java,v 1.4 2013-07-04 10:25:34 cjm Exp $
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
