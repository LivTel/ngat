package ngat.ngtcs;

/**
 * The FocalStation class defines the focal station geometry and focus offset
 * for a specific instrument focal plane.
 * <p>
 * The basic focal station coordinates are measured in millimetres, along the
 * axes (X,-Y), as viewed from behind the telescope. This view is also how the
 * coordinates would appear on-sky. The origin of the axes lie <i>exactly</i>
 * on the telescope pointing origin (usually the rotator axis). The X axis
 * increases to the right while the -Y axis decreases (i.e. gets <i>more</i>
 * negative) upwards, when the rotation of the focal plane is at 0 (zero)
 * degrees.  Zero degrees rotation implies that the -Y axis points North and
 * the X axis points East.
 * <p>
 * Superimposed upon the X,-Y plane is the instrument plane. This plane is
 * parallel to the X,-Y plane though it may be rotated through some arbitrary
 * angle. The instrument plane may also have different units of measurement,
 * most likely pixels or some equivalent. This plane will be positionally
 * defined by having its origin at a specified X,-Y ( @see #instOrigXYCoords )
 * and aligned at a specified angle ( @see #iaa ). The conversion from
 * instrument-specific coordinates to millimetres is performed using the
 * scale factor ( @see #scale ) of millimetres per instrument units.
 * <p>
 * <b>Note</b> a Mirror object is to be added to include pin-cushion/barrel
 * distortions.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class FocalStation
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * Name of this FocalStation.
   */
  protected String name;

  /**
   * Port number of this FocalStation.  0 (zero) Refers to the straight through
   * port position (main Cassgerain for the LT).
   */
  protected int portNumber = 0;

  /**
   * The focus offset for this Focal Station, in millimetres.
   */
  protected double focus = 0.0;

  /**
   * Origin for this FocalStation's instrument origin. i.e. This pair of
   * coordinates defines the position, in millimetres, of the instruments
   * (0, 0) coordinate, in the XY scale.
   */
  protected PointingOrigin instOrigXYCoords = new PointingOrigin( 0.0, 0.0 );

  /**
   * Desired instrument coordinates for the object being observed.
   */
  protected PointingOrigin instCoords = new PointingOrigin( 0.0, 0.0 );

  /**
   * Name of the units used for this FocalStation.
   */
  protected String unitName;

  /**
   * Scale of this FocalStation's units per mm.
   */
  protected double scale;

  /**
   * Instrument Alignment Angle, measured anticlockwise in radians, from the -Y
   * axis.
   */
  protected double iaa;

  /**
   * Sin( iaa ).
   */
  protected double sinIAA;

  /**
   * Cos( iaa ).
   */
  protected double cosIAA;


  /**
   * Constructor for FocalStations.
   */
  public FocalStation( String _name, int _portNumber, double _focus,
		       PointingOrigin _pointingOrigin, double _IAA,
		       double _scale, String _unitName )
  {
    name	     = _name;
    portNumber       = _portNumber;
    focus            = _focus;
    instOrigXYCoords = _pointingOrigin;
    scale	     = _scale;
    iaa		     = _IAA;
    sinIAA	     = Math.sin( iaa );
    cosIAA	     = Math.cos( iaa );
    unitName	     = _unitName;
  }


  /**
   * Return the name of this FocalStation.
   * @return the String name of this FocalStation
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the port number of this focal station.
   * @return portNumber
   * @seer #portNumber
   */
  public int getPortNumber()
  {
    return portNumber;
  }


  /**
   * Return the default focus for this Focal Station.
   * @return focus
   * @see #focus
   */
  public double getFocus()
  {
    return focus;
  }


  /**
   * Set the instrument origin for this focal station.
   * <br>
   * <b><u>Note:</u></b> This should ONLY be used when the actual focal
   * station is moving (e.g. an Autoguider re-positioning), NOT to align the
   * telescope position vector with an astronomical object.
   * @param po the new Instrument Origin
   * @see #instOrigXYCoords
   */
  public void setInstOriginInXY( PointingOrigin po )
  {
    instOrigXYCoords = po;
  }


  /**
   * Return the PointingOrigin object describing the origin of the 
   * instrument in this FocalStation.
   * @return the PointingOrigin of the centre of this FocalStation
   * @see #instOrigXYCoords
   */
  public PointingOrigin getInstOriginInXY()
  {
    return instOrigXYCoords;
  }


  /**
   * Set the Instrument Alignment Angle.  This is the anti-clockwise angle of
   * the instrument-specific desired direction ( i.e. the Y-axis of an
   * imaging CCD chip, or a spectrograph slit ) and the rotator -Y axis.
   * @param newIAA the new InstrumentAlignmentAngle, in radians
   * @see #iaa
   */
  public void setInstrumentAlignmentAngle( double newIAA )
  {
    iaa = newIAA;
  }


  /**
   * Get the Instrument Alignment Angle.
   * @return iaa, the Instrument Alignment Angle in radians
   * @see #iaa
   */
  public double getInstrumentAlignmentAngle()
  {
    return iaa;
  }


  /**
   * Set the instrument coordinates that define where the observed target is
   * desired in the Instrument plane.
   * @param newTargetOrigin the new centre of this FocalStation
   */
  public void setTargetInstCoords( PointingOrigin newTargetOrigin)
  {
    instCoords = newTargetOrigin;
  }


  /**
   * Return the Pointing Origin object describing the centre of this 
   * Focal Station.
   * @return the PointingOrigin of the centre of this FocalStation
   */
  public PointingOrigin getTargetInstCoords()
  {
    return instCoords;
  }


  /**
   * Calculate the position of the pointing origin in millimetres on the
   * X-Y plane.
   *
   */
  public PointingOrigin getTargetXY()
  {
    double targetX = ( instOrigXYCoords.getX() + 
		       instCoords.getX() * scale * cosIAA +
		       instCoords.getY() * scale * sinIAA );
    double targetY = ( instOrigXYCoords.getY() + 
		       instCoords.getY() * scale * cosIAA +
		       instCoords.getX() * scale * sinIAA );

    return new PointingOrigin( targetX, targetY );
  }
}
/*
 *    $Date: 2013-07-02 14:40:07 $
 * $RCSfile: FocalStation.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/FocalStation.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
