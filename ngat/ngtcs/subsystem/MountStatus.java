package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class MountStatus extends Status
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
	new String( "$Id: MountStatus.java,v 1.1 2003-07-01 10:13:46 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Mount for which this is the Status.
     */
    private String mount = null;

    /**
     * Current MountState.
     */
    private MountState mountState = null;

    /**
     * Current position of this Mount.
     */
    private XYZMatrix currentPos = null;

    /**
     * The Rotator object used on the specified Mount.
     */
    private double rotatorAngle = 0.0;

    /**
     * Timestamp of the `current' positions.
     */
    private Timestamp timestamp = null;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Constructor.
     */
    public MountStatus( SoftwareState ss, MountState ms, Timestamp t, 
			XYZMatrix pos )
    {
	super( ss );
	mountState = ms;
	currentPos = pos;
	timestamp = t;
    }


    /**
     * Return the current position of the Mount as an XYZMatrix.
     * @return currentPos
     */
    public XYZMatrix getPosition()
    {
	return currentPos;
    }


    /**
     * Return the current rotator angle.
     * @return rotatorAngle
     */
    public double getRotatorAngle()
    {
	return rotatorAngle;
    }


    /**
     * Return the Timestamp
     * @return timestamp
     */
    public Timestamp getTimestamp()
    {
	return timestamp;
    }
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: MountStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/MountStatus.java,v $
 *     $Log: not supported by cvs2svn $
 */
