package ngat.ngtcs.command;

import ngat.ngtcs.common.RotatorMode;

/**
 * This command sets the mode and position angle to be used by the rotator
 * while tracking.  This command will throw an IllegalArgumentException if the
 * position angle is outside the valid ranges, which depend upon the mode used.
 * <p>
 * For SKY position angle tracking the range is
 * <code><b>MIN_SKY_POSITION_ANGLE</b></code> (0.0) to
 * <code><b>MAX_SKY_POSITION_ANGLE</b></code> (360.0).
 * For MOUNT position angle tracking the range is
 * <code><b>MIN_MOUNT_POSITION_ANGLE</b></code> (-240.0) to
 * <code><b>MAX_MOUNT_POSITION_ANGLE</b></code> (240.0).
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class ROTATOR extends ngat.ngtcs.command.Command
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
	new String( "$Id: ROTATOR.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /**
     * Minimum value for the rotator position angle in sky mode (0.0), in
     * degrees
     */
    public static final double MIN_SKY_POSITION_ANGLE = 0.00;

    /**
     * Maximum value for the rotator position angle in sky mode (360.0), in
     * degrees
     */
    public static final double MAX_SKY_POSITION_ANGLE = 360.00;

    /**
     * Minimum value for the rotator position angle in mount mode (-240.0), in
     * degrees
     */
    public static final double MIN_MOUNT_POSITION_ANGLE = -240.0;

    /**
     * Maximum value for the rotator position angle in mount mode (240.0), in
     * degrees
     */
    public static final double MAX_MOUNT_POSITION_ANGLE = 240.0;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The tracking mode to use on the rotator.
     */
    protected RotatorMode rotatorMode;

    /**
     * the tracking position angle to use on the rotator.
     */
    protected double positionAngle;

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
     * This constructor creates a ROTATOR command with the specified ID, the
     * RotatorMode to be used while tracking, and the position angle at which
     * to track.  The position angle must be between the ranges specified by
     * the RotatorMode given.
     * @param s the String ID of this command
     * @param r the RotatorMode to be used while tracking
     * @param d the position angle to track at
     * @see ngat.ngtcs.common.RotatorMode
     * @see #MIN_SKY_POSITION_ANGLE
     * @see #MAX_SKY_POSITION_ANGLE
     * @see #MIN_MOUNT_POSITION_ANGLE
     * @see #MAX_MOUNT_POSITION_ANGLE
     */
    public ROTATOR( String s, RotatorMode r, double d )
	throws IllegalArgumentException
    {
	super( s );
	rotatorMode = r;

	if( rotatorMode == RotatorMode.SKY_POSITION )
	    {
		if( ngat.ngtcs.common.Util.outOfRange
		    ( d, MIN_SKY_POSITION_ANGLE, MAX_SKY_POSITION_ANGLE ) )
		    throw new IllegalArgumentException
			( "Position Angle "+d+" is outside the range "+
			  MIN_SKY_POSITION_ANGLE+" - "+
			  MAX_SKY_POSITION_ANGLE );
	    }
	else if( rotatorMode == RotatorMode.MOUNT_POSITION )
	    {
		if( ngat.ngtcs.common.Util.outOfRange
		    ( d, MIN_MOUNT_POSITION_ANGLE, MAX_MOUNT_POSITION_ANGLE ) )
		    throw new IllegalArgumentException
			( "Position Angle "+d+" is outside the range "+
			  MIN_MOUNT_POSITION_ANGLE+" - "+
			  MAX_MOUNT_POSITION_ANGLE );
	    }

	positionAngle = d;
    }


    /**
     * Return the RotatorMode used to track.
     * @return rotatorMode
     * @see #rotatorMode
     */
    public RotatorMode getRotatorMode()
    {
	return rotatorMode;
    }


    /**
     * Return the position angle at which to track, in degrees.
     * @return positionAngle
     * @see #positionAngle
     */
    public double getPositionAngle()
    {
	return positionAngle;
    }
}
/*
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: ROTATOR.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/ROTATOR.java,v $
 *      $Id: ROTATOR.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
