package ngat.ngtcs.subsystem.ags;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGG_GuidePacket
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
    new String( "$Id: AGG_GuidePacket.java,v 1.1 2003-09-19 16:08:38 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * The X pixel of this guide packet
   */
  protected double xPixel = 0.0;

  /**
   * The Y pixel of this guide packet
   */
  protected double yPixel = 0.0;

  /**
   * The error returned from the AGG.
   */
  protected AGG_GuidePacketError error = null;

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
  public AGG_GuidePacket( int i, double x, double y )
  {
    xPixel = x;
    yPixel = y;
  }

  /**
   *
   */
  public AGG_GuidePacket( AGG_GuidePacketError e )
  {
    error = e;
  }

  /**
   * Get the X pixel coordinate.
   * @return xPixel
   * @see #xPixel
   */
  public double getXPixel()
  {
    return xPixel;
  }

  /**
   * Get the Y pixel coordinate.
   * @return yPixel
   * @see #yPixel
   */
  public double getYPixel()
  {
    return yPixel;
  }

  /**
   * Get the error returned from the AGG
   * @return error
   * @see #error
   */
  public AGG_GuidePacketError getError()
  {
    return error;
  }
}
/*
 *    $Date: 2003-09-19 16:08:38 $
 * $RCSfile: AGG_GuidePacket.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_GuidePacket.java,v $
 *      $Id: AGG_GuidePacket.java,v 1.1 2003-09-19 16:08:38 je Exp $
 *     $Log: not supported by cvs2svn $
 */
