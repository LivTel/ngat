// NGATProperties.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/NGATProperties.java,v 0.10 2005-06-02 10:26:04 cjm Exp $
package ngat.util;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class extends the ngat.util.Properties class with useful load and type
 * conversion routines.
 *
 * For all methods taking a default value, the default value is returned if
 * parsing of the value returned on the key search fails.
 *
 * @version $Revision: 0.10 $
 * @author Jason Etherton,Chris Mottram
 */
public class NGATProperties extends Properties
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String
		("$Id: NGATProperties.java,v 0.10 2005-06-02 10:26:04 cjm Exp $");

	/**
	 * Method to load the properties.
	 * @param filename The filename of the property list.
	 * @exception FileNotFoundException Thrown if the file doesn't exist.
	 * @exception IOException Thrown if the load failed.
	 * @see #load( java.io.File )
	 */
	public void load( String filename )
		throws FileNotFoundException, IOException
	{
		File file = null;

		file = new File( filename );
		load( file );
	}    

	/**
	 * Method to load the properties.
	 * @param file A File object representing the filename of the property list.
	 * @exception FileNotFoundException Thrown if the file doesn't exist.
	 * @exception IOException Thrown if the load failed.
	 * @see java.util.Properties#load(java.io.InputStream)
	 */
	public void load( File file ) throws FileNotFoundException, IOException
	{
		FileInputStream fileInputStream = null;
	
		fileInputStream = new FileInputStream( file );
		load( fileInputStream );
		fileInputStream.close();
	}

	/**
	 * Method to save the properties.
	 * @param filename The filename of the property list.
	 * @param header The description to write at the top of the saved properties.
	 * @exception IOException Thrown if the save failed.
	 * @see java.util.Properties#store(java.io.OutputStream,java.lang.String)
	 * @see java.util.Properties#save(java.io.OutputStream,java.lang.String)
	 */
	public void save( String filename, String header ) throws IOException
	{
		File file = null;

		file = new File( filename );
		save( file, header );
	}    

	/**
	 * Method to save the properties.
	 * @param file A File object representing the filename of the property list.
	 * @param header The description to write at the top of the saved properties.
	 * @exception IOException Thrown if the save failed.
	 * @see java.util.Properties#store(java.io.OutputStream,java.lang.String)
	 * @see java.util.Properties#save(java.io.OutputStream,java.lang.String)
	 */
	public void save( File file, String header ) throws IOException
	{
		FileOutputStream fileOutputStream = null;
	
		fileOutputStream = new FileOutputStream( file );
		store( fileOutputStream, header );
		fileOutputStream.close();
	}

	/**
	 * Method to retrieve a <code>short</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>short</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid short.
	 */
	public short getShort( String key ) throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( Short.parseShort( value ) );
		}
		catch( NumberFormatException nfe )
		{
			throw new NGATPropertyException
				( nfe, key, getProperty( key ) );
		}
	}

	/**
	 * Method to retrieve a <code>short</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>short</code> value or the default value specified
	 */
	public double getShort( String key, short def )
	{
		try
		{
			return getShort( key );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def+"]" );
			return def;
		}
	}


	/**
	 * Method to retrieve a <code>int</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>int</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid int.
	 */
	public int getInt( String key ) throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( Integer.parseInt( value ) );
		}
		catch( NumberFormatException nfe )
		{
			throw new NGATPropertyException
				( nfe, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>int</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>int</code> value or the default value specified
	 */
	public int getInt( String key, int def )
	{
		try
		{
			return( getInt( key ) );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def+"]" );
			return def;
		}
	}


	/**
	 * Method to retrieve a <code>long</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>long</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid long.
	 */
	public long getLong( String key ) throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( Long.parseLong( value ) );
		}
		catch( NumberFormatException nfe )
		{
			throw new NGATPropertyException
				( nfe, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>long</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>long</code> value or the default value specified
	 */
	public long getLong( String key, long def )
	{
		try
		{
			return getLong( key );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def+"]" );
			return def;
		}
	}


	/**
	 * Method to retrieve a <code>double</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>double</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid double.
	 */
	public double getDouble( String key ) throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( Double.parseDouble( value ) );
		}
		catch( NumberFormatException nfe )
		{
			throw new NGATPropertyException
				( nfe, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>double</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>double</code> value or the default value specified
	 */
	public double getDouble( String key, double def )
	{
		try
		{
			return getDouble( key );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def+"]" );
			return def;
		}
	}


	/**
	 * Method to retrieve a <code>boolean</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>boolean</code> value
	 */
	public boolean getBoolean( String key )
	{
		return( ( Boolean.valueOf( getProperty( key ) ) ).booleanValue() );
	}


	/**
	 * Method to retrieve a <code>boolean</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>boolean</code> value or the default value specified
	 */
	public boolean getBoolean( String key, boolean def )
	{
		if( getProperty( key ) == null )
		{
			return def;
		}
		else if( getProperty( key ).length() < 4 )
		{
			return def;
		}
		else
		{
			return getBoolean( key );
		}
	}


	/**
	 * Method to retrieve a <code>InetAddress</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>InetAddress</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid address.
	 */
	public InetAddress getInetAddress( String key )
		throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( InetAddress.getByName( value ) );
		}
		catch( UnknownHostException uhe )
		{
			throw new NGATPropertyException
				( uhe, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>InetAddress</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>InetAddress</code> value or the default value specified
	 */
	public InetAddress getInetAddress( String key, InetAddress def )
	{
		try
		{
			return( getInetAddress( key ) );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def.toString()+"]" );
			return def;
		}
	}


	/**
	 * Method to retrieve a <code>Color</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>Color</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid colour.
	 */
	public Color getColor( String key ) throws NGATPropertyException
	{
		try
		{
			String colorString = getProperty( key );
			int i = colorString.indexOf( '=' );
			int j = colorString.indexOf( ',' );
			int red = Integer.parseInt
				( colorString.substring( i+1, j-1 ) );
			i = colorString.indexOf( '=', j );
			j = colorString.indexOf( ',', i );
			int green = Integer.parseInt
				( colorString.substring( i+1, j-1 ) );
			i = colorString.indexOf( '=', j );
			j = colorString.indexOf( ']', i );
			int blue = Integer.parseInt
				( colorString.substring( i+1, j-1 ) );
			return new Color( red, green, blue );
		}
		catch( NumberFormatException nfe )
		{
			throw new NGATPropertyException
				( nfe, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>Color</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>Color</code> value or the default value specified
	 */
	public Color getColor( String key, int r, int g, int b )
	{
		try
		{
			return getColor( key );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe,
				    " - using default [r="+r+",g="+g+",b="+b+"]" );
			return new Color( r, g, b );
		}
	}


	/**
	 * Method to retrieve a <code>Class</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>Class</code> value
	 * @exception NGATPropertyException Thrown if the value of the specified keyword is null, 
	 *            or is not a valid class.
	 */
	public Class getClass( String key )
		throws NGATPropertyException
	{
		String value = null;
		value = getProperty( key );
		if( value == null )
		{
			throw new NGATPropertyException
				( new NullPointerException(), key, "!MISSING!" );
		}

		try
		{
			return( Class.forName( value ) );
		}
		catch( ClassNotFoundException cfne )
		{
			throw new NGATPropertyException
				( cfne, key, getProperty( key ) );
		}
	}


	/**
	 * Method to retrieve a <code>Class</code> from the Properties.
	 * @param key the name of the property
	 * @return the <code>Class</code> value or the default value specified
	 */
	public Class getClass( String key, Class def )
	{
		try
		{
			return getClass( key );
		}
		catch( NGATPropertyException npe )
		{
			printError( npe, " - using default ["+def.toString()+"]" );
			return def;
		}
	}


	/**
	 * Print the specified exception to stderr, along with the key and
	 * returned value.
	 * @param e the Exception thrown
	 * @param a the key used to search the properties
	 * @param b the value returned from the search on <b><code>a</code></b>
	 */
	protected void printError( Exception e, String s )
	{
		System.err.println( e+s );
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.9  2002/10/23 10:20:33  je
// Finished with printError - no duplicate printing.
//
// Revision 0.8  2002/10/23 09:58:17  je
// Changed printError method.
//
// Revision 0.7  2002/09/25 12:53:31  je
// Sorted out documentation.
//
// Revision 0.6  2002/09/25 12:21:47  je
// Added getClass methods.
//
// Revision 0.5  2001/08/16 13:55:51  cjm
// Added save methods.
//
// Revision 0.4  2001/07/05 18:59:25  je
// NullPointerExceptions caught and fed into NGATPropertyException
//
// Revision 0.3  2001/06/20 13:33:57  cjm
// Fixed javadoc error.
//
// Revision 0.2  2001/03/05 20:04:15  cjm
// Added some load methods.
//
// Revision 0.1  2000/09/06 13:09:51  je
// initial revision.
//
//
