package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.spt.*;

/**
 * Open/Close mirror cover.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.4 $
 */
public class MIRROR_COVERImplementor extends CommandImplementor
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
    new String( "$Id: MIRROR_COVERImplementor.java,v 1.4 2003-10-16 15:31:25 je Exp $" );

  /**
   * The timeout for the MIRROR_COVER command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

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
  public MIRROR_COVERImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * Send the relevent command (open/close) and set the desired state
   * accordingly.  Then wait until either the state is acheived, or the timeout
   * is exceeded, returning a successful or failed CommandDone respectively.
   */
  public void execute()
  {
    TTL_PrimaryMirror pm = TTL_PrimaryMirror.getInstance();
    MIRROR_COVER mc = (MIRROR_COVER)command;
    SPT_State desired = null, actual;

    // set desired mirror cover state and send relevent command
    try
    {
      if( mc.open() )
      {
	desired = SPT_State.E_SPT_DMD_STS_OPN;
	pm.openCover();
      }
      else
      {
	desired = SPT_State.E_SPT_DMD_STS_CLS;
	pm.closeCover();
      }

      // wait until timeout or succeed
      do
      {
	sleep( 10000 );
	actual = pm.getCoverState();
      }
      while( ( actual != desired )&&( slept < TIMEOUT ) );

      // check if timeout OK
      if( slept < TIMEOUT )
      {
	commandDone.setSuccessful( true );
	return;
      }

      // timed out
      commandDone.setErrorMessage
	( "Primary Mirror cover did not reach desired state ["+
	  desired.getName()+"] after "+TIMEOUT+"ms : acheived state = "+
	  actual.getName()+"; execution terminated" );
    }
    catch( TTL_SystemException tse )
    {
      logError( tse.toString() );
    }

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
 *    $Date: 2003-10-16 15:31:25 $
 * $RCSfile: MIRROR_COVERImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/MIRROR_COVERImplementor.java,v $
 *      $Id: MIRROR_COVERImplementor.java,v 1.4 2003-10-16 15:31:25 je Exp $
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
