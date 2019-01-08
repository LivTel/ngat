/*   
    Copyright 2006, Astrophysics Research Institute, Liverpool John Moores University.

    This file is part of NGAT.

    NGAT is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    NGAT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NGAT; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
// FitsFlipException.java 
// $Header$
package ngat.fits;

/**
 * This class extends java.lang.Exception. Objects of this class are thrown when the flipping of FITS images fails.
 * @author Chris Mottram
 * @version $Revision: 721 $
 */
public class FitsFlipException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: FitsFlipException.java 721 2014-04-04 08:19:17Z cjm $");

	/**
	 * Constructor for the exception.
	 * @param keywordString The keyword causing problems.
	 * @param message The error message to display for this keyword.
	 */
	public FitsFlipException(String keywordString,String message)
	{
		super("FitsFlipException:"+keywordString+": gave error '"+message+"'.");
	}

	/**
	 * Constructor for the exception. This is for cases when another exception is thrown
	 * internally and caught. This constructor causes the passed in exception to be displayed
	 * along with the specified keyword and message.
	 * @param keywordString The keyword causing problems.
	 * @param message The error message to display for this keyword.
	 * @param e The exception thrown that caused this exception to occur.
	 */
	public FitsFlipException(String keywordString,String message,Exception e)
	{
		super("FitsFlipException:"+keywordString+":"+message+":"+e);
	}

	/**
	 * Constructor for the exception.
	 * @param s The error message to display.
	 */
	public FitsFlipException(String s)
	{
		super("FitsFlipException:"+s);
	}
}
