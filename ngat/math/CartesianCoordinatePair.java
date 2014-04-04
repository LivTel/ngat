package ngat.math;


/** Stores a pair of Cartesian coordinates.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/math/CartesianCoordinatePair.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public class CartesianCoordinatePair {

    /** X - Coordinate.*/
    public double x;

    /** Y - Coordinate.*/
    public double y;

    public CartesianCoordinatePair() {
	x = 0.0;
	y = 0.0;
    }

    public CartesianCoordinatePair(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public CartesianCoordinatePair(float x, float y) {
	this.x = (double)x;
	this.y = (double)y;
    }
    
    public CartesianCoordinatePair(int x, int y) {
	this.x = (double)x;
	this.y = (double)y;
    }

}

/** $Log: not supported by cvs2svn $ */
