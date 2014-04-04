// SpecDoubleRange.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/spec/SpecDoubleRange.java,v 0.1 2000-10-16 17:37:13 cjm Exp $
package ngat.spec;

/**
 * This class allows various methods in SpecLibrary to return a double range, i.e. two double
 * values min and max.
 * @author Chris Mottram
 * @version $Revision$
 * @see SpecLibrary
 * @see #min
 * @see #max
 */
public class SpecDoubleRange
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The variable used to hold the minimum double value.
	 */
	protected double min;
	/**
	 * The variable used to hold the maximum double value.
	 */
	protected double max;

	/**
	 * Default Constructor.
	 */
	public SpecDoubleRange()
	{
		super();
	}

	/**
	 * Constructor. Used to set minimum and maximum values at creation.
	 * @param small A double for the minimum value.
	 * @param large A double for the maximum value.
	 */
	public SpecDoubleRange(double small,double large)
	{
		super();
		min = small;
		max = large;
	}

	/**
	 * This method sets the value of the minimum double value.
	 * @param value The value the double is set to.
	 */
	public void setMinimum(double value)
	{
		this.min = value;
	}

	/**
	 * This method sets the value of the maximum double value.
	 * @param value The value the double is set to.
	 */
	public void setMaximum(double value)
	{
		this.max = value;
	}

	/**
	 * This method gets the minimum double value.
	 * @return The current value of the double.
	 */
	public double getMinimum()
	{
		return min;
	}

	/**
	 * This method gets the maximum double value.
	 * @return The current value of the double.
	 */
	public double getMaximum()
	{
		return max;
	}
}
 
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2000/10/16 17:35:11  cjm
// Initial revision
//
//
