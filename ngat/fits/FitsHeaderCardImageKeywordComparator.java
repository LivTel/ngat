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
// FitsHeaderCardImageKeywordComparator.java
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderCardImageKeywordComparator.java,v 1.1 2009-11-20 11:15:16 cjm Exp $
package ngat.fits;

import java.lang.*;
import java.util.*;

/**
 * This is a comparator that compares FitsHeaderCardImage instances by keyword. It is used to
 * order lists of card images by keyword, to enable searching lists of FitsHeaderCardImages by keyword.
 * Note: this comparator imposes orderings that are inconsistent with equals.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 * @see ngat.fits.FitsHeader
 */
public class FitsHeaderCardImageKeywordComparator implements Comparator
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FitsHeaderCardImageKeywordComparator.java,v 1.1 2009-11-20 11:15:16 cjm Exp $");

	/**
	 * Compare the two objects (which should be of class FitsHeaderCardImage, by their keywords. 
	 * Returns a negative integer, zero, or a positive integer as the first argument is 
	 * less than, equal to, or greater than the second.
	 * A ClassCastException (a RuntimeException) may be thrown if one of the arguments to compare is
	 * not an instance of FitsHeaderCardImage.
	 * @param o1 The first object to compare. This should be an instance of FitsHeaderCardImage,
	 * 	otherwise a ClassCastException is thrown.
	 * @param o2 The second object to compare. This should be an instance of FitsHeaderCardImage,
	 * 	otherwise a ClassCastException is thrown.
	 * @return An integer, the result of ci1.getKeyword().compareTo(ci2.getKeyword()).
	 */
	public int compare(Object o1, Object o2)
	{
		FitsHeaderCardImage ci1 = null;
		FitsHeaderCardImage ci2 = null;

		ci1 = (FitsHeaderCardImage)o1;
		ci2 = (FitsHeaderCardImage)o2;
		return ci1.getKeyword().compareTo(ci2.getKeyword());
	}
}
//
// $Log: not supported by cvs2svn $
//
