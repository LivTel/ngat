// FitsHeaderException.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderException.java,v 0.1 2000-05-30 14:37:28 cjm Exp $
package ngat.fits;

/**
 * This class extends java.lang.Exception. Objects of this class are thrown when the list of keyword/value pairs used
 * to write FITS headers to disc contains keyword/argument pairs that are unexpected..
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class FitsHeaderException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: FitsHeaderException.java,v 0.1 2000-05-30 14:37:28 cjm Exp $");

	/**
	 * Constructor for the exception.
	 * @param keywordString The keyword causing problems.
	 * @param message The error message to display for this keyword..
	 */
	public FitsHeaderException(String keywordString,String message)
	{
		super("FitsHeaderException:"+keywordString+": gave error '"+message+"'.");
	}

	/**
	 * Constructor for the exception.
	 * @param s The error message to display.
	 */
	public FitsHeaderException(String s)
	{
		super("FitsHeaderException:"+s);
	}
}

//
// $Log: not supported by cvs2svn $
//
