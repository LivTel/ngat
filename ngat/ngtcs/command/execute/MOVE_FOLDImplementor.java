package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Move fold mirror to a predetermined position.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class MOVE_FOLDImplementor extends CommandImplementor
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: MOVE_FOLDImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the MOVE_FOLD command (120 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 120000;

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
  public MOVE_FOLDImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
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
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: MOVE_FOLDImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/MOVE_FOLDImplementor.java,v $
 *      $Id: MOVE_FOLDImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
