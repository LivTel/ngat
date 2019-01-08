// $Header: /space/home/eng/cjm/cvs/ngat/math/XYZMatrix.java,v 1.1 2000-11-10 13:48:04 je Exp $
package ngat.math;

/**
 * A Cartesian vector of<pre><font size = +2>[ x</font><b>i</b>
<font size = +2>  y</font><b>j</b>
<font size = +2>  z</font><b>k</b><font size = +2> ]</font></pre>
*/
public class XYZMatrix extends Matrix
{
    /**
     * Revision Control System id string, showing the version of the Class.
     */
    public static final String RCSID = new String( "$Id$" );


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
	setElement( 1, 1, x );
	setElement( 2, 1, y );
	setElement( 3, 1, z );
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
     * Calculator method.  Calculates and returns the modulus of this XYZ Matrix.
     * @return modulus of this matrix
     */
    public double calcModulus()
    {
	return( Math.sqrt( getX()*getX() + getY()*getY() + getZ()*getZ() ) );
    }


    /**
     * Calculator method.  Returns a normalized matrix of this matrix
     * @return a normalized matrix
     */
    public XYZMatrix normalize()
    {
	double r = calcModulus();
	return new XYZMatrix( getX()/r, getY()/r, getZ/r );
    }
}

