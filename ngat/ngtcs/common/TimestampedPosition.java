package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class TimestampedPosition implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: TimestampedPosition.java,v 1.2 2013-07-04 10:47:30 cjm Exp $" );

    /**
     * Positional vector of the mount.
     */
    protected XYZMatrix positionVector;

    /**
     * Timestamp object of this PositionDemand
     */
    protected Timestamp timestamp;


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
 *    $Date: 2013-07-04 10:47:30 $
 * $RCSfile: TimestampedPosition.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TimestampedPosition.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
