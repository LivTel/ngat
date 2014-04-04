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
// FitsHeaderCardImageOrderNumberComparator.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderCardImageOrderNumberComparator.java,v 0.2 2006-05-16 17:42:23 cjm Exp $
package ngat.fits;

import java.lang.*;
import java.util.*;

/**
 * This is a comparator that compares FitsHeaderCardImage instances by the orderNumber. It is used to
 * order lists of card images by orderNumbers, so that
 * FITS header keywords are written in the right order.
 * Note: this comparator imposes orderings that are inconsistent with equals.
 * @author Chris Mottram
 * @version $Revision$
 * @see ngat.fits.FitsHeader
 */
public class FitsHeaderCardImageOrderNumberComparator implements Comparator
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Compare the two objects (which should be of class FitsHeaderCardImage, by their orderNumber. 
	 * Returns a negative integer, zero, or a positive integer as the first argument is 
	 * less than, equal to, or greater than the second.
	 * A ClassCastException (a RuntimeException) may be thrown if one of the arguments to compare is
	 * not an instance of FitsHeaderCardImage.
	 * @param o1 The first object to compare. This should be an instance of FitsHeaderCardImage,
	 * 	otherwise a ClassCastException is thrown.
	 * @param o2 The second object to compare. This should be an instance of FitsHeaderCardImage,
	 * 	otherwise a ClassCastException is thrown.
	 * @return An integer, the result of subtracting o2.getOrderNumber() from o1.getOrderNumber().
	 */
	public int compare(Object o1, Object o2)
	{
		FitsHeaderCardImage ci1 = null;
		FitsHeaderCardImage ci2 = null;

		ci1 = (FitsHeaderCardImage)o1;
		ci2 = (FitsHeaderCardImage)o2;
		return ci1.getOrderNumber() - ci2.getOrderNumber();
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  2000/05/30 14:37:28  cjm
// initial revision.
//
//
