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
// FitsHeaderDefaults.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderDefaults.java,v 0.4 2006-05-16 17:42:23 cjm Exp $
package ngat.fits;

import java.io.*;
import java.lang.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * This class holds default data relating to the list of FITS header card images that makes up one FITS file. 
 * The defaults are read from a Java property file. They contain information on the following FITS card image
 * attributes:
 * <ul>
 * <li>Types of values for keywords - <b>ngat.fits.value.type</b>.
 * <li>Default values for keywords with constant values (over the execution of a program) - <b>ngat.fits.value</b>.
 * <li>Default comments for keywords - <b>ngat.fits.comment</b>.
 * <li>Default units for keywords - <b>ngat.fits.units</b>.
 * <li>Default orderNumber for keywords. This determines where the keywords are placed in
 *     the FITS header, which is important for mandatory keywords. - <b>ngat.fits.order_number</b>.
 * </ul>
 * A list of keywords can also optionally be specified, using:<b>ngat.fits.keyword.&lt;number&gt;</b>.
 * @author Chris Mottram
 * @version $Revision$
 */
public class FitsHeaderDefaults
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is an Arc.
	 */
	public final static String OBSTYPE_VALUE_ARC		= "ARC";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is an Bias.
	 */
	public final static String OBSTYPE_VALUE_BIAS		= "BIAS";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is an Dark.
	 */
	public final static String OBSTYPE_VALUE_DARK		= "DARK";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is an Exposure.
	 */
	public final static String OBSTYPE_VALUE_EXPOSURE	= "EXPOSE";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is a Standard exposure.
	 */
	public final static String OBSTYPE_VALUE_STANDARD	= "STANDARD";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is a Lamp Flat.
	 */
	public final static String OBSTYPE_VALUE_LAMP_FLAT	= "LAMP-FLAT";
	/**
	 * Default value to put in the FITS header, against the OBSTYPE keyword, when the
	 * image is a Sky Flat.
	 */
	public final static String OBSTYPE_VALUE_SKY_FLAT	= "SKY-FLAT";

	/**
	 * Default filename containing FITS default properties.
	 */
	private final static String DEFAULT_PROPERTY_FILE_NAME = "./fits.properties";
	/**
	 * Prefix of a property key that returns a keyword for a numeric list of keywords.
	 */
	private final static String KEYWORD_PROPERTY_PREFIX = "ngat.fits.keyword.";
	/**
	 * Prefix of a property key that returns the Java type of the default value associated with a
	 * specified key.
	 */
	private final static String VALUE_TYPE_PROPERTY_PREFIX = "ngat.fits.value.type.";
	/**
	 * Prefix of a property key that returns the default value associated with a
	 * specified key.
	 */
	private final static String VALUE_PROPERTY_PREFIX = "ngat.fits.value.";
	/**
	 * Prefix of a property key that returns the default comment associated with a
	 * specified key.
	 */
	private final static String COMMENT_PROPERTY_PREFIX = "ngat.fits.comment.";
	/**
	 * Prefix of a property key that returns the default units associated with a
	 * specified key.
	 */
	private final static String UNITS_PROPERTY_PREFIX = "ngat.fits.units.";
	/**
	 * Prefix of a property key that returns the default order number associated with a
	 * specified key.
	 */
	private final static String ORDER_NUMBER_PROPERTY_PREFIX = "ngat.fits.order_number.";

	/**
	 * A list of properties held in the properties file.
	 */
	private Properties properties = null;

	/**
	 * Default constructor. Initialises properties.
	 * @see #properties
	 */
	public FitsHeaderDefaults()
	{
		properties = new Properties();
	}

	/**
	 * This loads the property file from disc, using the default filename. 
	 * @see #properties
	 * @see #load(java.lang.String)
	 * @see #DEFAULT_PROPERTY_FILE_NAME
	 * @exception FileNotFoundException Thrown if DEFAULT_PROPERTY_FILE_NAME does not exist.
	 * @exception IOException Throwen if there is a problem loading the property file.
	 */
	public void load() throws FileNotFoundException,IOException
	{
		load(DEFAULT_PROPERTY_FILE_NAME);
	}

	/**
	 * The load method for the class. This loads the property file from disc, using the specified
	 * filename. Any old properties are first cleared.
	 * @param filename A string representing the properties filename to load.
	 * @see #properties
	 * @exception FileNotFoundException Thrown if the filename does not exist.
	 * @exception IOException Throwen if there is a problem loading the property file.
	 */
	public void load(String filename) throws FileNotFoundException,IOException
	{
		FileInputStream fileInputStream = null;

		fileInputStream = new FileInputStream(filename);
		properties.clear();
		properties.load(fileInputStream);
		fileInputStream.close();
	}

	/**
	 * Method to get the default value for a specified FITS keyword. The VALUE_TYPE_PROPERTY_PREFIX
	 * is used to determine which class of object to return. The VALUE_PROPERTY_PREFIX is used to
	 * get the string to pass into the constructor. Note this method will throw an exception if the
	 * keyword has no default value.
	 * @param keyword The FITS keyword string.
	 * @return An object is returned of a class specified by the relevant value type property, with a value
	 *	specified by the relevant value property.
	 * @exception FitsHeaderException Thrown if the value type or value defaults are not found, of if
	 * 	an error occurs during the creation of the specified value object instance.
	 * @see #VALUE_TYPE_PROPERTY_PREFIX
	 * @see #VALUE_PROPERTY_PREFIX
	 */
	public Object getValue(String keyword) throws FitsHeaderException
	{
		String valueTypeString = null;
		String valueString = null;
		Class valueClass = null;
		Object value = null;
		Constructor constructor = null;
		Class constructorParameterTypeList[] = new Class[1];
		Object constructorParameterList[] = new Object[1];

	// get value type string
		valueTypeString = properties.getProperty(VALUE_TYPE_PROPERTY_PREFIX+keyword);
		if(valueTypeString == null)
			throw new FitsHeaderException(keyword,"Default value type not found.");
		if(valueTypeString.equals(""))
			throw new FitsHeaderException(keyword,"Default value type is empty.");
	// get value string
		valueString = properties.getProperty(VALUE_PROPERTY_PREFIX+keyword);
		if(valueString == null)
			throw new FitsHeaderException(keyword,"Default value not found.");
		if(valueString.equals(""))
			throw new FitsHeaderException(keyword,"Default value is empty.");
		try
		{
		// get class from value type string
			valueClass = Class.forName(valueTypeString);
		// get constructor with one string paramater
			constructorParameterTypeList[0] = Class.forName("java.lang.String");
			constructor = valueClass.getDeclaredConstructor(constructorParameterTypeList);
		// construct value object
			constructorParameterList[0] = valueString;
			value = constructor.newInstance(constructorParameterList);
		}
		//ClassNotFoundException Thrown if the value type string is not a fully qualified valid class name.
		//NoSuchMethodException Thrown as a result of calling Class.getDeclaredConstructor, if the
	 	// requested constructor (of Class value type string with 1 string argument) does not exist.
		//SecurityException Thrown as a result of calling Class.getDeclaredConstructor, if 
		// we are not allowed access to the required information.
		//InstantiationException Thrown as a result of calling Constructor.newInstance, if the
		// new object could not be instansiated (it's abstract).
		//IllegalAccessException Thrown as a result of calling Constructor.newInstance, if the
		// constructor is inaccessible.
		//IllegalArgumentException Thrown as a result of calling Constructor.newInstance, if
		// the string argument (the default value string) is illegal.
		//InvocationTargetException Thrown as a result of calling Constructor.newInstance, the
		// constructor throws an exception.
		catch(Exception e)
		{
			throw new FitsHeaderException(keyword,"Creating FITS value '"+valueString+"' of type '"+
				valueTypeString+"' failed.",e);
		}
		// return value
		return value;
 	}

	/**
	 * Method to get the default integer value for a specified FITS keyword. The VALUE_TYPE_PROPERTY_PREFIX
	 * is used to determine the default value is of the right type. 
	 * The VALUE_PROPERTY_PREFIX is used to get the string to pass into the parseInt routine
	 * Note this method will throw an exception if the keyword has no default value (a lot don't).
	 * @param keyword The FITS keyword string.
	 * @return The default integer value for this keyword.
	 * @exception FitsHeaderException Thrown if the value type or value defaults are not found, or 
	 * 	if the value is not a valid integer.
	 * @see #VALUE_TYPE_PROPERTY_PREFIX
	 * @see #VALUE_PROPERTY_PREFIX
	 */
	public int getValueInteger(String keyword) throws FitsHeaderException
	{
		String valueTypeString = null;
		String valueString = null;
		int value = 0;

	// get value type string
		valueTypeString = properties.getProperty(VALUE_TYPE_PROPERTY_PREFIX+keyword);
		if(valueTypeString == null)
			throw new FitsHeaderException(keyword,"Default value type not found.");
		if(valueTypeString.equals("java.lang.Integer") == false)
			throw new FitsHeaderException(keyword,"Default value type is not an integer.");
	// get value string
		valueString = properties.getProperty(VALUE_PROPERTY_PREFIX+keyword);
		if(valueString == null)
			throw new FitsHeaderException(keyword,"Default value not found.");
		if(valueString.equals(""))
			throw new FitsHeaderException(keyword,"Default value is empty.");
	// parse value string
		try
		{
			value = Integer.parseInt(valueString);
		}
		catch(NumberFormatException e)
		{
			throw new FitsHeaderException(keyword,"Parsing Integer Value '"+valueString+"' failed.",e);
		}
	// return value
		return value;
 	}

	/**
	 * Method to get the default double value for a specified FITS keyword. The VALUE_TYPE_PROPERTY_PREFIX
	 * is used to determine the default value is of the right type. 
	 * The VALUE_PROPERTY_PREFIX is used to get the string to pass into the parseDouble routine
	 * Note this method will throw an exception if the keyword has no default value (a lot don't).
	 * @param keyword The FITS keyword string.
	 * @return The default double value for this keyword.
	 * @exception FitsHeaderException Thrown if the value type or value defaults are not found, or 
	 * 	if the value is not a valid double.
	 * @see #VALUE_TYPE_PROPERTY_PREFIX
	 * @see #VALUE_PROPERTY_PREFIX
	 */
	public double getValueDouble(String keyword) throws FitsHeaderException
	{
		String valueTypeString = null;
		String valueString = null;
		double value = 0;

	// get value type string
		valueTypeString = properties.getProperty(VALUE_TYPE_PROPERTY_PREFIX+keyword);
		if(valueTypeString == null)
			throw new FitsHeaderException(keyword,"Default value type not found.");
		if(valueTypeString.equals("java.lang.Double") == false)
			throw new FitsHeaderException(keyword,"Default value type is not a double.");
	// get value string
		valueString = properties.getProperty(VALUE_PROPERTY_PREFIX+keyword);
		if(valueString == null)
			throw new FitsHeaderException(keyword,"Default value not found.");
		if(valueString.equals(""))
			throw new FitsHeaderException(keyword,"Default value is empty.");
	// parse value string
		try
		{
			value = Double.parseDouble(valueString);
		}
		catch(NumberFormatException e)
		{
			throw new FitsHeaderException(keyword,"Parsing Double Value '"+valueString+"' failed.",e);
		}
	// return value
		return value;
 	}

	/**
	 * Method to get the default comment for a FITS keyword. The COMMENT_PROPERTY_PREFIX is prepended to
	 * the keyword, and the resulting property queried to get the default comment.
	 * @param keyword The FITS keyword.
	 * @return The routine returns the default comment string. If one is not set it returns null.
	 * @see #COMMENT_PROPERTY_PREFIX
	 */
	public String getComment(String keyword)
	{
		String commentString = null;

		commentString = properties.getProperty(COMMENT_PROPERTY_PREFIX+keyword);
		return commentString;
	}

	/**
	 * Method to get the default units string for a FITS keyword. The UNITS_PROPERTY_PREFIX is prepended to
	 * the keyword, and the resulting property queried to get the default unit string.
	 * @param keyword The FITS keyword.
	 * @return The routine returns the default units string. If one is not set it returns null.
	 * @see #UNITS_PROPERTY_PREFIX
	 */
	public String getUnits(String keyword)
	{
		String unitsString = null;

		unitsString = properties.getProperty(UNITS_PROPERTY_PREFIX+keyword);
		return unitsString;
	}

	/**
	 * Method to get the default order number for a FITS keyword. The ORDER_NUMBER_PROPERTY_PREFIX is prepended to
	 * the keyword, and the resulting property queried to get the default order number.
	 * @param keyword The FITS keyword.
	 * @return The routine returns the default order number. If one is not set a NumberFormatException is
	 * 	thrown.
	 * @exception FitsHeaderException If the properties order number string is null/blank/not a valid integer, 
	 * 	this exception will be thrown (possibly as a result of a NumberFormatException occuring when
	 * 	the Integer.parseInt routine is called).
	 * @see #ORDER_NUMBER_PROPERTY_PREFIX
	 */
	public int getOrderNumber(String keyword) throws FitsHeaderException
	{
		String orderNumberString = null;
		int orderNumber = -1;

		orderNumberString = properties.getProperty(ORDER_NUMBER_PROPERTY_PREFIX+keyword);
		if(orderNumberString == null)
			throw new FitsHeaderException(keyword,"Order Number is null.");
		if(orderNumberString.equals(""))
			throw new FitsHeaderException(keyword,"Order Number is empty.");
		try
		{
			orderNumber = Integer.parseInt(orderNumberString);
		}
		catch(NumberFormatException e)
		{
			throw new FitsHeaderException(keyword,"Parsing Order Number '"+
				orderNumberString+"' failed.",e);
		}
		return orderNumber;
	}

	/**
	 * Method to get the FITS keyword string for an index in a list in the properties file. 
	 * The KEYWORD_PROPERTY_PREFIX is prepended to
	 * the index, and the resulting property queried to get the FITS keyword string.
	 * @param index An integer, used as an index for this element in a list of keywords, specified
	 * 	in the properties file.
	 * @return The routine returns the keyword at this index in the list of keywords. 
	 * 	If one is not set it returns null.
	 * @see #KEYWORD_PROPERTY_PREFIX
	 */
	public String getKeyword(int index)
	{
		String keywordString = null;

		keywordString = properties.getProperty(KEYWORD_PROPERTY_PREFIX+index);
		return keywordString;
	}

	/**
	 * Convenience routine that creates and returns a FitsHeaderCardImage instance, with it's fields
	 * filled in with defaults held in this class.
	 * @param keyword A FITS header keyword to create the card image for.
	 * @return An instance of FitsHeaderCardImage is returned, with it's fields
	 * 	filled in with defaults held in this class.
	 * @exception FitsHeaderException Thrown if getValue/getOrderNumber fails.
	 * @see ngat.fits.FitsHeaderCardImage
	 * @see #getValue
	 * @see #getComment
	 * @see #getUnits
	 * @see #getOrderNumber
	 */
	public FitsHeaderCardImage getCardImage(String keyword) throws FitsHeaderException
	{
		FitsHeaderCardImage cardImage = null;

		cardImage = new FitsHeaderCardImage(keyword,
			getValue(keyword),getComment(keyword),getUnits(keyword),getOrderNumber(keyword));
		return cardImage;
	}

	/**
	 * Convenience routine to create a Vector (List) containing an instance of every FitsHeaderCardImage
	 * specified with the <b>ngat.fits.keyword.&lt;number&gt;</b> properties.
	 * This is donr by creating the list, looping through the <b>ngat.fits.keyword.&lt;number&gt;</b> properties
	 * Using getKeyword (until it returns null), and adding each keyword found using getCardImage.
	 * @return A Vector is returned, each element containing a FitsHeaderCardImage instance
	 * 	constructed using data in the properties file loaded by this class instance.
	 * @exception FitsHeaderException Thrown if getCardImage fails.
	 * @see ngat.fits.FitsHeaderCardImage
	 * @see #getKeyword
	 * @see #getCardImage
	 */
	public Vector getCardImageList() throws FitsHeaderException
	{
		FitsHeaderCardImage cardImage = null;
		Vector fitsHeaderList = null;
		String keyword = null;
		boolean done;
		int index;

		fitsHeaderList = new Vector();
		done = false;
		index = 0;
		while(done == false)
		{
			keyword = getKeyword(index);
			if(keyword != null)
			{
				cardImage = getCardImage(keyword);
				fitsHeaderList.add(cardImage);
				index++;
			}
			else
				done = true;
		}// end while done == false
		return fitsHeaderList;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.3  2001/07/11 10:29:00  cjm
// Standard OBSTYPE added.
// getCardImage added. getCardImageList added.
// getKeyword, getValueDouble added.
// getValue changed to throw a common FitsHeaderException rather than
// lots of different types of exception.
//
// Revision 0.2  2000/07/13 09:10:24  cjm
// Added getValueInteger method.
//
// Revision 0.1  2000/05/30 14:37:28  cjm
// initial revision.
//
//
