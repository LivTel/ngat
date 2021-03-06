package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Open/close the enclosure.
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class ENCLOSUREImplementor extends CommandImplementor
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
    new String( "$Id$" );

  /**
   * The timeout for the ENCLOSURE command (600 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 600000;

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
  public ENCLOSUREImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * Close, open or move to a specified angle, the Enclosre, 1 shutter at a
   * time.
   */
  public void execute()
  {
    ENCLOSURE  encCmd = (ENCLOSURE)command;
    double      angle = encCmd.getAngle();
    TTL_Enclosure enc = TTL_Enclosure.getInstance();

    try
    {
      // deal with shutter 1
      if( encCmd.includesShutter1() )
	if( encCmd.getClose() )
	  enc.closeShutter1();
	else if( encCmd.getOpen() )
	  enc.openShutter1();
	else
	  enc.moveShutter1( angle );

      // now 2
      if( encCmd.includesShutter2() )
	if( encCmd.getClose() )
	  enc.closeShutter2();
	else if( encCmd.getOpen() )
	  enc.openShutter2();
	else
	  enc.moveShutter2( angle );
	
    }
    catch( TTL_SystemException se )
    {
      logError( "Enclosure command failed : "+se.toString() );
    }
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
 *    $Date: 2013-07-04 10:16:23 $
 * $RCSfile: ENCLOSUREImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/ENCLOSUREImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.5  2003/10/02 16:57:58  je
 *     Added 'import ngat.ngtcs.subsystem.*;'
 *
 *     Revision 1.4  2003/09/29 14:03:05  je
 *     Added execute documentation.
 *
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
