package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TimestampedPosition implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: TimestampedPosition.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Positional vector of the mount.
     */
    private XYZMatrix positionVector;

    /**
     * Timestamp object of this PositionDemand
     */
    private Timestamp timestamp;


    /**
     *
     */
    public TimestampedPosition( XYZMatrix newMountVector, 
				Timestamp newTimestamp )
    {
	positionVector = newMountVector;
	timestamp = newTimestamp;
    }


    /**
     * Return the Timestamp for this object.
     * @return this object's timestamp
     */
    public Timestamp getTimestamp()
    {
	return timestamp;
    }


    /**
     * Return this object's position vector.
     * @return this object's position
     */
    public XYZMatrix getPosition()
    {
	return positionVector;
    }


    /**
     * Return the MountVector for this object.
     * @return this object's positionVector
     */
    public XYZMatrix getPositionVector()
    {
	return positionVector;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: TimestampedPosition.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TimestampedPosition.java,v $
 *     $Log: not supported by cvs2svn $
 */
