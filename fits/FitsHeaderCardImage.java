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
// FitsHeaderCardImage.java 
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderCardImage.java,v 0.4 2006-05-16 17:42:22 cjm Exp $
package ngat.fits;

import java.io.Serializable;
import java.lang.*;
import java.util.*;

/**
 * This class holds data relating to one FITS header card image. A sequence of these card images make up a FITS
 * header. The FitsHeader class uses a list of instances of this class to represent a FITS header unit.
 * @author Chris Mottram
 * @version $Revision$
 * @see ngat.fits.FitsHeader
 */
public class FitsHeaderCardImage implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Serial version ID as instances of this class are serialized for transmission of FITS headers
	 * from the ISS to instruments.
	 */
	static final long serialVersionUID = -1411057302313729429L;
	/**
	 * Variable holding the keyword this card image represents.
	 */
	private String keyword = null;
	/**
	 * Variable holding the value this card image represents.
	 */
	private Object value = null;
	/**
	 * Variable holding an associated comment string this card image has attached to it.
	 */
	private String comment = null;
	/**
	 * Variable holding an associated units string this card image has attached to it.
	 */
	private String units = null;
	/**
	 * Variable holding an associated ordering value, used when sorting lists of card images for
	 * insertion into the FITS file in the correct order.
	 */
	private int orderNumber = 0;


	/**
	 * Default constructor.
	 */
	public FitsHeaderCardImage()
	{
		keyword = null;
		value = null;
		comment = null;
		units = null;
		orderNumber = 0;
	}

	/**
	 * Constructor to set all field when the object is constructed.
	 * @param k A valid FITS keyword string.
	 * @param v A valid FITS value. This can be of the
	 * 	following classes: String,Integer,Float,Double,Boolean,Date.
	 * @param c A valid FITS comment.
	 * @param u A valid FITS units string.
	 * @param o An order number.  This will determine the card image's position
	 * 	in the FITS header, when it is sorted.
	 */
	public FitsHeaderCardImage(String k,Object v,String c,String u,int o)
	{
		keyword = k;
		value = v;
		comment = c;
		units = u;
		orderNumber = o;
	}

	/**
	 * Method to set the keyword.
	 * @param k A valid FITS keyword string.
	 */
	public void setKeyword(String k)
	{
		keyword = k;
	}

	/**
	 * Method to get the keyword.
	 * @return A valid FITS keyword string.
	 */
	public String getKeyword()
	{
		return keyword;
	}

	/**
	 * Method to set the value.
	 * @param v A valid FITS value. This can be of the
	 * 	following classes: String,Integer,Float,Double,Boolean,Date.
	 */
	public void setValue(Object v)
	{
		value = v;
	}

	/**
	 * Method to get the value.
	 * @return A valid FITS value. This can be of the
	 * 	following classes: String,Integer,Float,Double,Boolean,Date.
	 */
	public Object getValue()
	{
		return value;
	}

	/**
	 * Method to set the comment string.
	 * @param c A valid FITS comment.
	 */
	public void setComment(String c)
	{
		comment = c;
	}

	/**
	 * Method to get the comment string.
	 * @return A valid FITS comment.
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Method to set the units string.
	 * @param u A valid FITS units string.
	 */
	public void setUnits(String u)
	{
		units = u;
	}

	/**
	 * Method to get the units string.
	 * @return A valid FITS units string.
	 */
	public String getUnits()
	{
		return units;
	}

	/**
	 * Method to set the order number for this card image. This will determine the card image's position
	 * in the FITS header, when it is sorted.
	 * @param o An order number.
	 */
	public void setOrderNumber(int o)
	{
		orderNumber = o;
	}

	/**
	 * Method to get the order number for this card image. This will determine the card image's position
	 * in the FITS header, when it is sorted.
	 * @return An order number.
	 */
	public int getOrderNumber()
	{
		return orderNumber;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * The equals method says the two objects are equals, if the `other' object 
	 * is of class FitsHeaderCardImage, and it's <b>keyword</b> field is equal to
	 * this objects <b>keyword</b> field. The values can be different. This is done so
	 * we can use List.indexOf to determine whether a keyword/value pair we are adding to
	 * the exist already exists in the list (equal keyword), so that we can replace that element 
	 * with the new value for the specified keyword.
	 * @param obj An object to compare to the current instance.
	 * @return The method returns true if the two objects are equals, false if they are not.
	 */
	public boolean equals(Object obj)
	{
		FitsHeaderCardImage other = null;

		if(obj instanceof FitsHeaderCardImage)
		{
			other = (FitsHeaderCardImage)obj;
			return keyword.equals(other.keyword);
		}
		else
			return false;
	}

	/**
	 * Print a debug message with the data held in this class in it.
	 * @return The generated string.
	 */
	public String toString()
	{
		return new String(getClass().getName()+
			"["+keyword+":"+value+":"+comment+":"+units+":"+orderNumber+"]");
	}

	/**
	 * Create a copy of this card image. This has the same data values as the original, but a new object
	 * reference. The inner immutable sub-object references are the same, if the sub-object is mutable
	 * (value can be an instance of Date which it mutable) a new sub-object is created with the same value.
	 * @return A new copy of the original object, with the same values, and with mutable sub-objects copied.
	 * @see #keyword
	 * @see #value
	 * @see #comment
	 * @see #units
	 * @see #orderNumber
	 */
	public FitsHeaderCardImage copy()
	{
		FitsHeaderCardImage clone = null;
		Object newValue = null;
		Date oldDate = null;
		Date newDate = null;

		// keyword, comment and units are all strings - therefore immutable
		// value can be a String/Integer/Float/Double/Boolean/Date. These are all immutable except Date.
		// Therefore we must copy the value (if it is an instance of Date) to prevent the original and
		// copy being tied together my a common reference to a mutable object.
		if(value instanceof Date)
		{
			oldDate = (Date)value;
			newDate = new Date(oldDate.getTime());
			newValue = newDate;
		}
		else
			newValue = value;
		clone = new FitsHeaderCardImage(keyword,newValue,comment,units,orderNumber);
		return clone;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.3  2006/03/29 11:16:30  cjm
// Comment fix.
//
// Revision 0.2  2001/07/11 10:29:00  cjm
// Added Serializable support for FitsHeaderCardImage, so
// that GET_FITS can supply a list of card images.
//
// Revision 0.1  2000/05/30 14:37:28  cjm
// initial revision.
//
//
