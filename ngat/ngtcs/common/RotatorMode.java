package ngat.ngtcs.common;

/**
 * Define the type-safe enumerations of the Operation Modes of 
 * Rotator Tracking.
 *
 * The operational modes available are:
 *
 * <ul>
 * <li> RotatorMode.SKY_POSITION
 * <li> RotatorMode.FLOATING_SKY_POSITION
 * <li> RotatorMode.MOUNT_POSITION
 * <li> RotatorMode.VERTICAL_POSITION
 * <li> RotatorMode.FLOATING_VERTICAL_POSITION
 * </ul>
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class RotatorMode extends ngat.util.TypeSafeEnumeration
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
	new String( "$Id: RotatorMode.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * The name of this mode of operation.
     */
    protected String name;

    /**
     * The integer representation of this mode of operation.
     */
    protected int modeInt;

    /**
     * The tracking angle used in the relevent mode of operation, in radians.
     */
    protected double trackingAngle = 0.0;

    /**
     * The Equinox of the tracking frame used to track the rotator.
     */
    protected Equinox trackingEquinox = Equinox.J2000;

    /**
     * Type-safe enumerations.
     * =======================
     *
     * These are the only instances of this class that can be used.
     */
    public final static RotatorMode SKY_POSITION = 
	new RotatorMode( "SKY_POSITION", 4001 );

    public final static RotatorMode MOUNT_POSITION = 
	new RotatorMode( "MOUNT_POSITION", 4002 );

    public final static RotatorMode FLOATING_SKY_POSITION = 
	new RotatorMode( "FLOATING_SKY_POSITION", 4003 );

    public final static RotatorMode VERTICAL_POSITION = 
	new RotatorMode( "VERTICAL_POSITION", 4004 );

    public final static RotatorMode FLOATING_VERTICAL_POSITION = 
	new RotatorMode( "FLOATING_VERTICAL_POSITION", 4005 );

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
     * Constructor for RotatorModes, protected so that the only modes 
     * available are declared in this class.
     * @param name the name of this mode of operation
     * @param modeInt the integer representation for this mode of operation
     */
    protected RotatorMode( String s, int i )
    {
	super( s, i );
    }


    /**
     * Sets the tracking angle used in this operational mode.  The specified 
     * angle should be in radians, in the range: 0 - 2*PI.
     * @param newTrackingAngle
     * @see #trackingAngle
     */
    public void setTrackingAngle( double newTrackingAngle )
    {
	trackingAngle = newTrackingAngle;
    }


    /**
     * Return the current tracking angle being used in this operational mode.
     * @return trackingAngle
     * @see #trackingAngle
     */
    public double getTrackingAngle()
    {
	return trackingAngle;
    }


    /**
     * Set the tracking frame to be used while tracking the rotator.
     * @param newTrackingEquinox
     */
    public void setTrackingFrame( Equinox newTrackingEquinox )
    {
	trackingEquinox = newTrackingEquinox;
    }


    /**
     * Get the tracking frame used to track the rotator.
     * @return trackingEquinox
     */
    public Equinox getTrackingFrame()
    {
	return trackingEquinox;
    }


    /**
     * Reset the tracking angle and tracking equinox to the defaults
     */
    public void resetValues()
    {
	trackingAngle = 0.0;
	trackingEquinox = Equinox.J2000;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: RotatorMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/RotatorMode.java,v $
 *      $Id: RotatorMode.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
