package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Returns a list of all <code>PluggableSubSystem</code> implementing objects
 * used on the telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 *
 */
public class SUBSYSTEMLISTImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: SUBSYSTEMLISTImplementor.java,v 1.3 2003-09-23 11:45:16 je Exp $" );

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
   * Create a SUBSYSTEMLISTImplementor.  This method only calls
   * <code>super</code>.
   * @see CommandImplementor
   */
  public SUBSYSTEMLISTImplementor
    ( ExecutionThread eT, Telescope ts, Command c )
  {
    super( eT, ts, c );
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
      String mech = subSysList.get( i );
      logger.log( 3, logName, "Listing mechanisms : "+mech );
      list = new String( list+mech+"\n" );
    }
    commandDone.setReturnMessage( list );
    commandDone.setSuccessful( true );
  }
}
/*
 *    $Date: 2003-09-23 11:45:16 $
 * $RCSfile: SUBSYSTEMLISTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SUBSYSTEMLISTImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
