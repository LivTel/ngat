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
// FitsHeader.java
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeader.java,v 0.6 2009-10-06 15:47:08 cjm Exp $
package ngat.fits;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * This class holds FITS header information for a FITS file, and routines using JNI to save the
 * header card images to a file, ready for concatenating the data.
 * @author Chris Mottram
 * @version $Revision: 0.6 $
 * @see ngat.fits.FitsHeaderCardImage
 */
public class FitsHeader
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FitsHeader.java,v 0.6 2009-10-06 15:47:08 cjm Exp $");
	/**
	 * The fits header contains keywords with values associated with them. A List (Vector) is used
	 * to store these. Each element of the vector contains an instance of FitsHeaderCardImage,
	 * which stores information about one card image.
	 * @see ngat.fits.FitsHeaderCardImage
	 */
	private List cardImageList = null;
	/**
	 * Our instance of FitsHeaderCardImageOrderNumberComparator. This is a comparator that
	 * compares FitsHeaderCardImage instances by the orderNumber. It is used to
	 * order the cardImageList by orderNumbers of the card images in it, so that
	 * FITS header keywords are written in the right order.
	 * @see ngat.fits.FitsHeaderCardImageOrderNumberComparator
	 */
	private FitsHeaderCardImageOrderNumberComparator cardImageCompareByOrderNumber = null;

	/**
	 * Static code to load libngatfits, the shared C library that implements an interface to the
	 * CFITSIO FITS file routines.
	 */
	static
	{
		System.loadLibrary("ngatfits");
	}

	/**
	 * Native wrapper to a libccsfits routine to return the error number.
	 * @return The error number.
	 */
	private native int Fits_Header_Get_Error_Number();
	/**
	 * Native wrapper to a libccsfits routine to return the error string.
	 * @return A String of the error.
	 */
	private native String Fits_Header_Get_Error_String();
	/**
	 * Native wrapper to a libccsfits routine to open the filename.
	 * @param filename The filename to open.
	 * @return Returns true if the operation succeeds, false if it fails.
	 */
	private native boolean Fits_Header_Open(String filename);
	/**
	 * Native wrapper to a libccsfits routine to update a keywords value that is of type String.
	 * @param keyword The FITS keyword.
	 * @param value The value for this keyword.
	 */
	private native boolean Fits_Header_Update_Keyword_String(String keyword,String value,
		String comment,String units);
	/**
	 * Native wrapper to a libccsfits routine to update a keywords value that is of type Integer.
	 * @param keyword The FITS keyword.
	 * @param value The value for this keyword.
	 */
	private native boolean Fits_Header_Update_Keyword_Integer(String keyword,Integer value,
		String comment,String units);
	/**
	 * Native wrapper to a libccsfits routine to update a keywords value that is of type Float.
	 * @param keyword The FITS keyword.
	 * @param value The value for this keyword.
	 */
	private native boolean Fits_Header_Update_Keyword_Float(String keyword,Float value,
		String comment,String units);
	/**
	 * Native wrapper to a libccsfits routine to update a keywords value that is of type Double.
	 * @param keyword The FITS keyword.
	 * @param value The value for this keyword.
	 */
	private native boolean Fits_Header_Update_Keyword_Double(String keyword,Double value,
		String comment,String units);
	/**
	 * Native wrapper to a libccsfits routine to update a keywords value that is of type Boolean.
	 * @param keyword The FITS keyword.
	 * @param value The value for this keyword.
	 */
	private native boolean Fits_Header_Update_Keyword_Boolean(String keyword,Boolean value,
		String comment,String units);
	/**
	 * Native wrapper to a libccsfits routine to close the file.
	 * @return Returns true if the operation succeeds, false if it fails.
	 */
	private native boolean Fits_Header_Close();

	/**
	 * Constructor for FitsHeader. Constructs an empty cardImageList, and initialises the
	 * cardImageCompareByOrderNumber comparator.
	 * @see #cardImageList
	 * @see #cardImageCompareByOrderNumber
	 */
	public FitsHeader()
	{
		cardImageList = new Vector();
		cardImageCompareByOrderNumber = new FitsHeaderCardImageOrderNumberComparator();
	}

	/**
	 * This routine adds a card image to the FITS card image list.
	 * If the keyword already has a  value it will be overwritten with it's new values.
	 * @param keyword The String keyword - a valid fits keyword. This cannot be null.
	 * @param value An object representing a value of some type. This cannot be null.
	 * @param comment A small comment string. This can be null (no comment).
	 * @param units A small units string. This can be null (no units).
	 * @param orderNumber A number used to determine where in the list of card images this
	 * 	one should be placed. The list is sorted on this number before it is written to the FITS file.
	 *	If you don't care about the location, specify a large number (e.g. 100). Specify zero to four
	 * 	for non-mandatory keyword will cause CFITSIO to fail writing the FITS file.
	 */
	public void add(String keyword,Object value,String comment,String units,int orderNumber)
	{
		FitsHeaderCardImage cardImage = null;
		int index;

	// construct card image
		cardImage = new FitsHeaderCardImage(keyword,value,comment,units,orderNumber);
	// does a card image with this keyword already exist?
		index = cardImageList.indexOf(cardImage);
		if(index > -1)
			cardImageList.set(index,cardImage);
		else
			cardImageList.add(cardImage);
	}

	/**
	 * This routine adds a card image to the FITS card image list.
	 * If the keyword already has an associated card image it will be overwritten with it's new values.
	 * @param cardImage The card image to add to the list.
	 */
	public void add(FitsHeaderCardImage cardImage)
	{
		int index;

	// does a card image with this keyword already exist?
		index = cardImageList.indexOf(cardImage);
		if(index > -1)
			cardImageList.set(index,cardImage);
		else
			cardImageList.add(cardImage);
	}

	/**
	 * Method to return the current card image for the specified keyword.
	 * @param keyword The keyword string to get the card image for.
	 * @return The card image with the specified keyword is returned, or null if the
	 * 	card image with that keyword cannot be found.
	 */
	public FitsHeaderCardImage get(String keyword)
	{
		FitsHeaderCardImage cardImage = null;
		int index;

	// construct card image with keyword set to test against
		cardImage = new FitsHeaderCardImage(keyword,null,null,null,1);
	// search for the index in the list, of a card image with the same keyword.
		index = cardImageList.indexOf(cardImage);
		if(index > -1)
			cardImage = (FitsHeaderCardImage)(cardImageList.get(index));
		return cardImage;
	}

	/**
	 * Function to return the Keyword Value from a header (I TODD).
	 * @param keyword The keyword string to get the card image for.
	 * @return The keyword value as an object, or null if the keyword is not found.
	 */
	 public Object getKeywordValue(String keyword)
	 {
	   	FitsHeaderCardImage cardImage = null;
		int index;

	// construct card image with keyword set to test against
		cardImage = new FitsHeaderCardImage(keyword,null,null,null,1);
	// search for the index in the list, of a card image with the same keyword.
		index = cardImageList.indexOf(cardImage);
		if(index > -1)
		{
			cardImage = (FitsHeaderCardImage)(cardImageList.get(index));
			return(cardImage.getValue());
		}
		else
			return null;
	 }

	/**
	 * This routine will add the list of keyword-value pairs to the fits keyword-value list.
	 * Any keyword that already have values will be overwritten with their new values.
	 * @param list The list of keyword-value pairs to add to the list held by this object.
	 * 	This list should contain only instances of ngat.fits.FitsHeaderCardImage.
	 * @param orderNumberOffset A number, which is added to each orderNumber in the list.
	 * 	This allows each subsystem to start it's orderNumber's at one, and this
	 * 	routine will offset them by a sensible amount whilst merging lists of FitsHeaderCardImages.
	 * @see ngat.fits.FitsHeaderCardImage
	 */
	public void addKeywordValueList(Vector list,int orderNumberOffset)
	{
		FitsHeaderCardImage cardImage = null;
		int orderNumber,index;

		for (int i=0; i < list.size(); i++)
		{
			cardImage = (FitsHeaderCardImage)(list.get(i));
		// add an offset to the orderNumber
			orderNumber = cardImage.getOrderNumber();
			cardImage.setOrderNumber(orderNumber+orderNumberOffset);
		// does a card image with this keyword already exist?
			index = cardImageList.indexOf(cardImage);
			if(index > -1)
				cardImageList.set(index,cardImage);
			else
				cardImageList.add(cardImage);
		}// for on keys in hashtable
	}

	/**
	 * This routine clears out all the keyword-value combinations to be stored in the header.
	 */
	public void clearKeywordValueList()
	{
		cardImageList.clear();
	}

	/**
	 * Routine to write out the keyword-value combinations into a new file in the FITS format.
	 * @param filename The filename to write the FITS header to.
	 * @exception FitsHeaderException Thrown if the file cannot be opened.
	 */
	public void writeFitsHeader(String filename) throws FitsHeaderException
	{
		FitsHeaderCardImage cardImage = null;
		Object keyword = null;
		Object value = null;
		String keywordString = null;
		Enumeration e = null;
		boolean retval = false;
		int i;

	// open the fits file.
		retval =  Fits_Header_Open(filename);
		if(retval == false)
		{
			throw new FitsHeaderException(new String("writeFitsHeader:Open Error("+
				Fits_Header_Get_Error_Number()+"):"+Fits_Header_Get_Error_String()));
		}
		try
		{
		// sort the List into orderNumber order.
			Collections.sort(cardImageList,cardImageCompareByOrderNumber);
		// write the keywords into the FITS file
			for(i=0;i<cardImageList.size();i++)
			{
				cardImage = (FitsHeaderCardImage)(cardImageList.get(i));
				writeFitsField(cardImage);
			}
		// CFITSIO writes the END keyword so we don't have to.
			//writeFitsField("END",null);
		}// end try
		finally
		{
		// close the fits file.
		// Note we don't check the return value here which could return false.
		// This is in case another call in the try statement has failed and already thown an exception.
		// If we throw another exception here we will 
		// lose the contents on the one with the original error in it.
			Fits_Header_Close();
		}
	}

	/**
	 * This routine writes one keyword and it's value to a file.
	 * @param cardImage An instance of FitsHeaderCardImage representing the keyword/value to write to the
	 * 	FITS file.
	 * @exception FitsHeaderException This is thrown if the value is not of a recognized class type,
	 * 	or if the Java Native Interface routines give an error.
	 */
	private void writeFitsField(FitsHeaderCardImage cardImage) throws FitsHeaderException
	{
		String keyword = null;
		Object value = null;
		String comment = null;
		String units = null;
		boolean retval = false;

		keyword = cardImage.getKeyword();
		value = cardImage.getValue();
		comment = cardImage.getComment();
		units = cardImage.getUnits();
	// If the keyword or value is null we get a NULLPointer Exception thrown, which
	// is not very descriptive. Catch this and make something more descriptive.
		if((keyword == null)||(value == null))
		{
			throw new FitsHeaderException("writeFitsField:keyword/value null:keyword:'"+keyword+
				"':value:'"+value+"'.");
		}
		if(value instanceof String)
			retval = Fits_Header_Update_Keyword_String(keyword,(String)value,comment,units);
		else if(value instanceof Integer)
			retval = Fits_Header_Update_Keyword_Integer(keyword,(Integer)value,comment,units);
		else if(value instanceof Float)
			retval = Fits_Header_Update_Keyword_Float(keyword,(Float)value,comment,units);
		else if(value instanceof Double)
			retval = Fits_Header_Update_Keyword_Double(keyword,(Double)value,comment,units);
		else if(value instanceof Boolean)
			retval = Fits_Header_Update_Keyword_Boolean(keyword,(Boolean)value,comment,units);
		else if(value instanceof Date)
			retval = Fits_Header_Update_Keyword_String(keyword,DateFitsFieldToString((Date)value),
				comment,units);
		else
		{
			throw new FitsHeaderException(keyword,new String("writeFitsField:value of unknown type '"+
				value.getClass().getName()+"'."));
		}
		if(retval == false)
		{
			throw new FitsHeaderException(keyword,new String("writeFitsField:Update Error("+
				Fits_Header_Get_Error_Number()+"):"+Fits_Header_Get_Error_String()));
		}
	}

	/**
	 * This routine takes a Date, and formats a string to the correct FITS format for that date and returns it.
	 * The format should be 'CCYY-MM-DDThh:mm:ss[.sss...]'.
	 * @param date The date to return a string for.
	 * @return Returns a String version of the date in the correct new FITS format.
	 */
// diddly use keyword to determine format of string
// DATE-OBS format 'CCYY-MM-DDThh:mm:ss[.sss...]'
// DATE 'CCYY-MM-DD'
// UTSTART 'HH:MM:SS.s'
// others?
	private String DateFitsFieldToString(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		NumberFormat numberFormat = NumberFormat.getInstance();

		numberFormat.setMinimumIntegerDigits(2);
		calendar.setTime(date);
		return new String(calendar.get(Calendar.YEAR)+"-"+
			numberFormat.format(calendar.get(Calendar.MONTH)+1)+"-"+
			numberFormat.format(calendar.get(Calendar.DAY_OF_MONTH))+"T"+
			numberFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+
			numberFormat.format(calendar.get(Calendar.MINUTE))+":"+
			numberFormat.format(calendar.get(Calendar.SECOND))+"."+
			calendar.get(Calendar.MILLISECOND));
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.5  2006/05/16 17:42:25  cjm
// gnuify: Added GNU General Public License.
//
// Revision 0.4  2006/03/29 11:16:31  cjm
// Comment fix.
//
// Revision 0.3  2001/07/11 10:29:00  cjm
// Changes so that addKeywordValueList takes a Vector.
//
// Revision 0.2  2000/06/14 10:50:36  cjm
// Added keyword/value null reference test in writeFitsField.
//
// Revision 0.1  2000/05/30 14:37:28  cjm
// initial revision.
//
//
