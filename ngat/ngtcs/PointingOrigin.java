package ngat.ngtcs;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class PointingOrigin
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: PointingOrigin.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

    /**
     * The X coordinate of this PointingOrigin.
     */
    private double x;

    /**
     * The Y coordinate of this PointingOrigin.
     */
    private double y;

    /**
     * The X coodinate offset from this PointingOrigin.
     */
    private double dx;

    /**
     * The Y coodinate offset from this PointingOrigin.
     */
    private double dy;


    /**
     * Empty constructor.
     */
    public PointingOrigin()
    {

    }


    /**
     * Defining constructor.
     */
    public PointingOrigin( double _x, double _y )
    {
	x = _x;
	y = _y;
    }


    /**
     * Set the X coordinate of this PointinOrigin.
     * @param _x the new x coordinate of this PointingOrigin
     * @see #x
     */
    public void setX( double _x )
    {
	x = _x;
    }


    /**
     * Return the X coordinate of this PointinOrigin.
     * @return x coordinate of this PointingOrigin
     * @see #x
     */
    public double getX()
    {
	return x;
    }


    /**
     * Set the Y coordinate of this PointinOrigin.
     * @param _y the new x coordinate of this PointingOrigin
     * @see #y
     */
    public void setY( double _y )
    {
	y = _y;
    }


    /**
     * Return the Y coordinate of this PointinOrigin.
     * @return x coordinate of this PointingOrigin
     * @see #y
     */
    public double getY()
    {
	return y;
    }


    /**
     * Return the String description of this PointingOrigin as ( x, y ).
     */
    public String toString()
    {
	return "( "+x+", "+y+" )";
    }
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: PointingOrigin.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/PointingOrigin.java,v $
 *     $Log: not supported by cvs2svn $
 */
