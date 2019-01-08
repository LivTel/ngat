// QuadraticFitPoint.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/QuadraticFitPoint.java,v 0.1 2000-08-18 17:24:43 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * This class holds an x/y position of a data value, that we are trying to obtain a fit for.
 * @author Chris Mottram
 * @version $Revision$
 */
public class QuadraticFitPoint
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * X data point value.
	 */
	private double x = 0.0;
	/**
	 * Y data point value.
	 */
	private double y = 0.0;

	/**
	 * Constructor.
	 */
	public QuadraticFitPoint()
	{
		x = 0.0;
		y = 0.0;
	}

	/**
	 * Constructor.
	 * @param xp X data point value.
	 * @param yp Y data point value.
	 */
	public QuadraticFitPoint(double xp,double yp)
	{
		x = xp;
		y = yp;
	}

	/**
	 * Constructor. The parameters are converted from floats to doubles internally.
	 * @param xp X data point value.
	 * @param yp Y data point value.
	 */
	public QuadraticFitPoint(float xp,float yp)
	{
		x = (double)xp;
		y = (double)yp;
	}

	/**
	 * Method to set the value of x.
	 * @param xp The value to set x to.
	 */
	public void setX(double xp)
	{
		x = xp;
	}

	/**
	 * Method to set the value of x. The parameter is converted to double internally.
	 * @param xp The value to set x to.
	 */
	public void setX(float xp)
	{
		x = (float)xp;
	}

	/**
	 * Method to return the value of x for this data point.
	 * @return The value of x.
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Method to set the value of y.
	 * @param yp The value to set y to.
	 */
	public void setY(double yp)
	{
		y = yp;
	}

	/**
	 * Method to set the value of y. The parameter is converted to double internally.
	 * @param yp The value to set y to.
	 */
	public void setY(float yp)
	{
		y = (float)yp;
	}

	/**
	 * Method to return the value of y for this data point.
	 * @return The value of y.
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Method overriding Object's toString method. This prints this class's x and y values,
	 */
	public String toString()
	{
		return new String(this.getClass().getName()+":x = "+x+":y = "+y);
	}

	/**
	 * Method to get the slope between this point and the other point. This is done by:
	 * slope = y/x
	 * @param other The other point to use in computing the slope.
	 * @return The gradient slope of the point.
	 * @exception IllegalArgumentException Thrown if the two points x values are the same, i.e. the 
	 * 	gradient is infinite.
	 */
	double getGradient(QuadraticFitPoint other)
	{
		double dx,dy;

		dx = other.getX()-x;
		dy = other.getY()-y;
	// If the other point is before this one in x, reverse the delta values.
		if(dx < 0.0)
		{
			dx = -dx;
			dy = -dy;
		}
		if(dx == 0.0) // This is not a very good idea?
		{
			throw new IllegalArgumentException(this.getClass().getName()+
				":getSlope:X values are the same:this:"+toString()+":other:"+other.toString());
		}
		return dy/dx;
	}

	/**
	 * Method to get the rate of change of gradient between three points, this,point2,point3.
	 * The three points must be in ascending numerical order in terms of their x values e.g.
	 * this.x < point2.x < point3.x.
	 * This is done by getting the gradient/slope/first derivative between the this and point2 and
	 * point2 and point3, and then subtracting the second value from the first, and dividing by the
	 * average x distance between the points (as the gradient's were calculated with one step size
	 * between them, in effect).
	 * @param point2 The second point.
	 * @param point3 The third point.
	 * @return The rate of change of gradient.
	 * @exception IllegalArgumentException Thrown if the three points are not in ascending numerical order 
	 * 	in terms of their x values.
	 */
	double getRateOfChangeOfGradient(QuadraticFitPoint point2,QuadraticFitPoint point3)
	{
		double dx1,dx2;
		double g1,g2,retval;

		if(point2.getX() < this.getX())
		{
			throw new IllegalArgumentException(this.getClass().getName()+
				":getRateOfChangeOfGradient:Arguments not in ascending X order(this/point2):this:"+
				this.toString()+":point2:"+point2.toString());
		}
		if(point3.getX() < point2.getX())
		{
			throw new IllegalArgumentException(this.getClass().getName()+
				":getRateOfChangeOfGradient:Arguments not in ascending X order(point2/point3):point2:"+
				point2.toString()+":point2:"+point3.toString());
		}
		dx1 = point2.getX() - this.getX();
		dx2 = point3.getX() - point2.getX();
		g1 = this.getGradient(point2);
		g2 = point2.getGradient(point3);
		retval = (g2 - g1)/((dx1+dx2)/2.0);
		return retval;
	}	
}
//
// $Log: not supported by cvs2svn $
//

