package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Offset the telescope from the reference position so that the image moves by
 * a specified distance in the sky or tangent plane.
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class OFFBYImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the OFFBY command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                       */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public OFFBYImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * If the OffsetMode is TIME, the RA and Dec offsets are simply set in the
   * current target.  If the mode is ARC, the current pointing origin (X,Y in
   * the tangent plane) is calculated, the offsets applied to this, and the
   * corresponding RA and Dec that are observed are used to define the absolute
   * RA and Dec offsets that are a result of this tangent-plane offset are then
   * added to the current target.
   */
  public void execute()
  {
    OFFBY o = (OFFBY)command;
    OffsetMode om = o.getMode();
    Target tgt = telescope.getCurrentTarget();
    double offsetX = o.getRightAscensionOffset();
    double offsetY = o.getDeclinationOffset();

    if( om == OffsetMode.TIME )
    {
      tgt.setOffsetRA( offsetX );
      tgt.setOffsetDec( offsetY );
      commandDone.setSuccessful( true );
    }
    else if( om == OffsetMode.ARC )
    {
      double ra = tgt.getRA(), dec = tgt.getDec();
      double sinDec = Math.sin( dec );
      double cosDec = Math.cos( dec );
      double denom = cosDec - offsetX * sinDec;

      double newRA = Math.atan2( offsetX, denom ) + ra;
      double newDec =
	Math.atan2( sinDec + offsetY * cosDec,
		    Math.sqrt( offsetX * offsetX + offsetY * offsetY ) );

      if( newRA < 0.0 ) newRA += ( 2.0 * Math.PI );

      tgt.setOffsetRA( newRA - ra );
      tgt.setOffsetDec( newDec - dec );
      commandDone.setSuccessful( true );
    }
    else
    {
      logError
	( "Error : OffsetMode = "+om+" : execution terminated" );
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
 *    $Date: 2013-07-04 10:25:25 $
 * $RCSfile: OFFBYImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/OFFBYImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.5  2003/10/16 16:50:30  je
 *     Added documentation.
 *
 *     Revision 1.4  2003/10/16 16:48:03  je
 *     Implemented the execute method.
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
