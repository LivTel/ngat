// CCDLibraryDouble.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibraryDouble.java,v 0.3 1999-09-08 10:52:40 cjm Exp $
/**
 * This class is a simple double data type object wrapper, allowing us to return a double value from a 
 * method..
 * @author Chris Mottram
 * @version $Revision: 0.3 $
 */
class CCDLibraryDouble
{
	/**
	 * The variable used to hold the double value.
	 */
	private double value;
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: CCDLibraryDouble.java,v 0.3 1999-09-08 10:52:40 cjm Exp $");

	/**
	 * This method sets the value of the double.
	 * @param value The value the double is set to.
	 */
	public void setValue(double value)
	{
		this.value = value;
	}

	/**
	 * This method gets the value of the double.
	 * @return The current value of the double.
	 */
	public double getValue()
	{
		return value;
	}
}
 
//
// $Log: not supported by cvs2svn $
// Revision 0.2  1999/02/23 11:08:00  dev
// backup/transfer to ltccd1.
//
// Revision 0.1  1999/01/21 15:45:18  dev
// initial revision
//
//