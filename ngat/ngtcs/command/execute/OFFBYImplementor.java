package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Offset the telescope from the reference position so that the image moves by
 * a specified distance in the sky or tangent plane.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.4 $
 */
public class OFFBYImplementor extends CommandImplementor
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
    new String( "$Id: OFFBYImplementor.java,v 1.4 2003-10-16 16:48:03 je Exp $" );

  /**
   * The timeout for the OFFBY command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

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
    double offsetX = o.getRa();
    double offsetY = o.getDec();

    if( om == OffsetMode.TIME )
    {
      tgt.setOffsetRA( offsetX );
      tgt.setOffsetDec( offsetY );
      commandDone.setSuccessful( true );
    }
    else if( om == OffsetMode.ARC )
    {
      VirtualTelescope vt = telescope.getActiveVirtualTelescope();
      XYZMatrix mntPos = telescope.getMount().getCurrentPosition();
      Timestamp = telescope.getTimer().getTime();
      Rotator rot = telescope.getRotator();
      double angle = ( ( rot == null ) ? 0.0 : rot.getPositionAngle() );
      PointingOrigin po1 = vt.calcPointingOrigin( time, tgt, mntPos, angle );
      // Y is in the -ve Alt/Dec direction.
      PointingOrigin po2 = new PointingOrigin
	( ( po1.getX() + offsetX ), ( po1.getY() - offsetY ) );
      /*
	ReportedTarget repTgt = vt.calcSomething( 
	XYZMatrix out = repTgt.getOutputRADec();
	double outRA = Math.atan2( out.getY(), out.getX() );
	double outDec = Math.asin( out.getZ() );
	double offRA = outRA - tgt.getRA();
	double offDec = outDec - tgt.getDec();
	tgt.setOffsetRA( offRA );
	tgt.setOffsetDec( offDec );
	commandDone.setSuccessful( true );
      */
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
 *    $Date: 2003-10-16 16:48:03 $
 * $RCSfile: OFFBYImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/OFFBYImplementor.java,v $
 *      $Id: OFFBYImplementor.java,v 1.4 2003-10-16 16:48:03 je Exp $
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
