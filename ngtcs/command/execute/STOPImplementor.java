package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Stops the named mechanism.
 * The STOP command is implemented by setting the telescope state to IDLE.
 * This is because all telescope operation is (or <i>should</i> be) performed
 * under conditional testing of this state.
 *
 * @author $Author$ 
 * @version $Revision$
 */
public class STOPImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the STOP command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;


  /**
   * Create the STOPImplementor.
   */
  public STOPImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   * Set the telescope state to IDLE.
   */
  public void execute()
  {
    telescope.setOperationalMode( OperationalMode.IDLE );
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
 *    $Date: 2013-07-04 10:29:20 $
 * $RCSfile: STOPImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/STOPImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
