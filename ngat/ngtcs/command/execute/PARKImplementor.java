package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Park telescope.
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class PARKImplementor extends CommandImplementor
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
    new String( "$Id: PARKImplementor.java,v 1.4 2013-07-04 10:19:35 cjm Exp $" );

  /**
   * The timeout for the PARK command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

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
   *
   */
  public PARKImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    TTL_Mount mount = (TTL_Mount)( telescope.getMount() );
    try
    {
      mount.park();

      do
      {
	sleep( 10000 );
      }
      while( ( slept < TIMEOUT )&&( mount.isParked() != true ) );
    }
    catch( TTL_SystemException tse )
    {

    }

    if( slept > TIMEOUT )
    {
      
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
 *    $Date: 2013-07-04 10:19:35 $
 * $RCSfile: PARKImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/PARKImplementor.java,v $
 *      $Id: PARKImplementor.java,v 1.4 2013-07-04 10:19:35 cjm Exp $
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
