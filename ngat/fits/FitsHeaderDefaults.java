// FitsHeaderDefaults.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsHeaderDefaults.java,v 0.2 2000-07-13 09:10:24 cjm Exp $
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
 * @author Chris Mottram
 * @version $Revision: 0.2 $
 */
public class FitsHeaderDefaults
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FitsHeaderDefaults.java,v 0.2 2000-07-13 09:10:24 cjm Exp $");
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
	 */
	public void load() throws FileNotFoundException,IOException
	{
		load(DEFAULT_PROPERTY_FILE_NAME);
	}

	/**
	 * The load method for the class. This loads the property file from disc, using the specified
	 * filename. Any old properties are first cleared.
	 * @see #properties
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
	 * keyword has no default value (a lot don't).
	 * @param keyword The FITS keyword string.
	 * @return An object is returned of a class specified by the relevant value type property, with a value
	 *	specified by the relevant value property.
	 * @exception FitsHeaderException Thrown if the value type or value defaults are not found.
	 * @exception ClassNotFoundException Thrown if the value type string is not a fully qualified valid class
	 * 	name.
	 * @exception NoSuchMethodException Thrown as a result of calling Class.getDeclaredConstructor, if the
	 * 	requested constructor (of Class value type string with 1 string argument) does not exist.
	 * @exception SecurityException Thrown as a result of calling Class.getDeclaredConstructor, if 
	 * 	we are not allowed access to the required information.
	 * @exception InstantiationException Thrown as a result of calling Constructor.newInstance, if the
	 *	new object could not be instansiated (it's abstract).
	 * @exception IllegalAccessException Thrown as a result of calling Constructor.newInstance, if the
	 *	constructor is inaccessible.
	 * @exception IllegalArgumentException Thrown as a result of calling Constructor.newInstance, if
	 * 	the string argument (the default value string) is illegal.
	 * @exception InvocationTargetException Thrown as a result of calling Constructor.newInstance, the
	 * 	constructor throws an exception.
	 * @see #VALUE_TYPE_PROPERTY_PREFIX
	 * @see #VALUE_PROPERTY_PREFIX
	 */
	public Object getValue(String keyword) throws FitsHeaderException, ClassNotFoundException,
		NoSuchMethodException, SecurityException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
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
	// get class from value type string
		valueClass = Class.forName(valueTypeString);
	// get constructor with one string paramater
		constructorParameterTypeList[0] = Class.forName("java.lang.String");
		constructor = valueClass.getDeclaredConstructor(constructorParameterTypeList);
	// construct value object
		constructorParameterList[0] = valueString;
		value = constructor.newInstance(constructorParameterList);
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
	 * @exception FitsHeaderException Thrown if the value type or value defaults are not found.
	 * @exception NumberFormatException Thrown if the value is not a valid integer.
	 * @see #VALUE_TYPE_PROPERTY_PREFIX
	 * @see #VALUE_PROPERTY_PREFIX
	 */
	public int getValueInteger(String keyword) throws FitsHeaderException, NumberFormatException
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
		value = Integer.parseInt(valueString);
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
	 * @exception NumberFormatException If the properties value string is not a valid integer, this
	 * 	exception will be thrown when the Integer.parseInt routine is called.
	 * @see #ORDER_NUMBER_PROPERTY_PREFIX
	 */
	public int getOrderNumber(String keyword) throws NumberFormatException
	{
		String orderNumberString = null;
		int orderNumber;

		orderNumberString = properties.getProperty(ORDER_NUMBER_PROPERTY_PREFIX+keyword);
		orderNumber = Integer.parseInt(orderNumberString);
		return orderNumber;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  2000/05/30 14:37:28  cjm
// initial revision.
//
//
