package ngat.ngtcs.common;

/**
 * A Cartesian vector of<pre><font size = +2>[ x</font><b>i</b>
 * <font size = +2>  y</font><b>j</b>
 * <font size = +2>  z</font><b>k</b><font size = +2> ]</font></pre>
 *
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public class XYZMatrix extends Matrix
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
        new String( "$Id: XYZMatrix.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Calls super constructor.  Defines a generic 3 x 1 matrix.
     */
    public XYZMatrix()
    {
	super( 3, 1 );
    }


    /**
     * Instantiates and sets a matrix.
     * @param x the <b>x</b> element
     * @param y the <b>y</b> element
     * @param z the <b>z</b> element
     */
    public XYZMatrix( double x, double y, double z )
    {
	super( 3, 1 );
	setXYZ( x, y, z );
    }


    /**
     * Mutator method.  Sets the x element.
     * @param  the x element
     */
    public void setX( double x )
    {
	setElement( 1, 1, x );
    }


    /**
     * Accessor method.  Returns the x element of the vector.
     * @return the x element
     */
    public double getX()
    {
	return getElement( 1, 1 );
    }


    /**
     * Mutator method.  Sets the y element.
     * @param  the y element
     */
    public void setY( double y )
    {
	setElement( 2, 1, y );
    }


    /**
     * Accessor method.  Returns the y element of the vector.
     * @return the y element
     */
    public double getY()
    {
	return this.getElement( 2, 1 );
    }


    /**
     * Mutator method.  Sets the z element.
     * @param  the z element
     */
    public void setZ( double z )
    {
	setElement( 3, 1, z );
    }


    /**
     * Accessor method.  Returns the z element of the vector.
     * @return the z element
     */
    public double getZ()
    {
	return this.getElement( 3, 1 );
    }


    /**
     * Mutator method.  Sets all 3 x,y and z elements.
     * @param x the X element
     * @param y the Y element
     * @param z the Z element
     */
    public void setXYZ( double x, double y, double z )
    {
	setElement( 1, 1, x );
	setElement( 2, 1, y );
	setElement( 3, 1, z );
    }


    /**
     * Return a new XYZMatrix object expressed as a the opposite-handed matrix,
     * i.e if applied to a matrix <b>[ x, y, z ]</b>, this method will return a
     * matrix <b>[ x, -y, z ].
     * <p>
     * For example, this method can be used to switch between -HA,Dec and
     * HA,Dec, in either direction.
     * @return a matrix of opposite handedness to <b>this</b> one
     */
    public XYZMatrix swapHandedness()
    {
	return new XYZMatrix( getX(), -getY(), getZ() );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: XYZMatrix.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/XYZMatrix.java,v $
 *      $Id: XYZMatrix.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
