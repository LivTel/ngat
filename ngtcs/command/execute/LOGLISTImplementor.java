package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 *
 * Returns a list of all the <code>Logger</code>s used on the telescope.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class LOGLISTImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the LOGLIST command (5 seconds), in milliseconds
   */
  public static final int TIMEOUT = 5000;


  /**
   * List of PluggableSubSystems on Telescope.
   */
  private List logList = null;


  public LOGLISTImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   *
   */
  public void execute()
  {
    String list = new String( "Loggers on this telescope :\n" );
    logList = telescope.getLoggerList();
    for( int i = 0; i < logList.size(); i++ )
    {
      list = new String( list+logList.get( i )+"\n" );
    }
    commandDone.setReturnMessage( list );
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
   *    $Date: 2013-07-04 10:16:42 $
   * $RCSfile: LOGLISTImplementor.java,v $
   *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/LOGLISTImplementor.java,v $
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
