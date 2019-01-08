// SpecIntRange.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/spec/SpecIntRange.java,v 0.1 2000-10-16 17:37:13 cjm Exp $
package ngat.spec;

/**
 * This class allows various methods in SpecLibrary to return a int range, i.e. two int
 * values min and max.
 * @author Chris Mottram
 * @version $Revision$
 * @see SpecLibrary
 * @see #min
 * @see #max
 */
public class SpecIntRange
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The variable used to hold the minimum int value.
	 */
	protected int min;
	/**
	 * The variable used to hold the maximum int value.
	 */
	protected int max;

	/**
	 * Default Constructor.
	 */
	public SpecIntRange()
	{
		super();
	}

	/**
	 * Constructor. Used to set minimum and maximum values at creation.
	 * @param small A int for the minimum value.
	 * @param large A int for the maximum value.
	 */
	public SpecIntRange(int small,int large)
	{
		super();
		min = small;
		max = large;
	}

	/**
	 * This method sets the value of the minimum int value.
	 * @param value The value the int is set to.
	 */
	public void setMinimum(int value)
	{
		this.min = value;
	}

	/**
	 * This method sets the value of the maximum int value.
	 * @param value The value the int is set to.
	 */
	public void setMaximum(int value)
	{
		this.max = value;
	}

	/**
	 * This method gets the minimum int value.
	 * @return The current value of the int.
	 */
	public int getMinimum()
	{
		return min;
	}

	/**
	 * This method gets the maximum int value.
	 * @return The current value of the int.
	 */
	public int getMaximum()
	{
		return max;
	}
}
 
//
// $Log: not supported by cvs2svn $
//
