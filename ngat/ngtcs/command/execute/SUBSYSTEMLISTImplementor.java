package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Returns a list of all <code>PluggableSubSystem</code> implementing objects
 * used on the telescope.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.6 $
 *
 */
public class SUBSYSTEMLISTImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS FIELDS.  /*                                                       */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: SUBSYSTEMLISTImplementor.java,v 1.6 2013-07-04 10:29:22 cjm Exp $" );

  /**
   * The timeout for the SUBSYSTEMLIST command (3 seconds), in milliseconds
   */
  public static final int TIMEOUT = 3000;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT FIELDS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS METHODS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT METHODS.  /*                                                     */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * Create a SUBSYSTEMLISTImplementor.  This method only calls
   * <code>super</code>.
   * @see CommandImplementor
   */
  public SUBSYSTEMLISTImplementor
    ( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   * This method creates a String and adds the name of every subsystem held in
   * the main Telescope object.
   */
  public void execute()
  {
    List subSysList = null;
    String list = "Mechansims on this telescope :\n";

    subSysList = telescope.getPluggableSubSystemList();

    for( int i = 0; i < subSysList.size(); i++ )
    {
      String mech = (String)subSysList.get( i );
      logger.log( 3, logName, "Listing mechanisms : "+mech );
      list = new String( list+mech+"\n" );
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
 *    $Date: 2013-07-04 10:29:22 $
 * $RCSfile: SUBSYSTEMLISTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SUBSYSTEMLISTImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.5  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.4  2003/09/23 12:26:50  je
 *     Forgot to cast Hashtable.get() from Object to String
 *
 *     Revision 1.3  2003/09/23 11:45:16  je
 *     Replaced System.err reporting with logger.log
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
