package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Prepare the telescope for observing or request the telescopestand-down at
 * the end of an observing session.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class OPERATIONALImplementor extends CommandImplementor
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
	new String( "$Id: OPERATIONALImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

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
  public OPERATIONALImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    return;
  }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: OPERATIONALImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/OPERATIONALImplementor.java,v $
 *      $Id: OPERATIONALImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */