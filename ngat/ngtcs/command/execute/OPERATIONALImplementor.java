package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
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
	new String( "$Id: OPERATIONALImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: OPERATIONALImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/OPERATIONALImplementor.java,v $
 *      $Id: OPERATIONALImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
