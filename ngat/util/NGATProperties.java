// NGATProperties.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/NGATProperties.java,v 0.5 2001-08-16 13:55:51 cjm Exp $
package ngat.util;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class extends the ngat.util.Properties class with useful load and type
 * conversion routines.
 * @version $Revision: 0.5 $
 * @author Jason Etherton,Chris Mottram
 */
public class NGATProperties extends Properties
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: NGATProperties.java,v 0.5 2001-08-16 13:55:51 cjm Exp $");

	/**
	 * Method to load the properties.
	 * @param filename The filename of the property list.
	 * @exception FileNotFoundException Thrown if the file doesn't exist.
	 * @exception IOException Thrown if the load failed.
	 * @see #load(java.io.File)
	 */
	public void load(String filename) throws FileNotFoundException, IOException
	{
		File file = null;

		file = new File(filename);
		load(file);
	}    

	/**
	 * Method to load the properties.
	 * @param file A File object representing the filename of the property list.
	 * @exception FileNotFoundException Thrown if the file doesn't exist.
	 * @exception IOException Thrown if the load failed.
	 * @see java.util.Properties#load(java.io.InputStream)
	 */
	public void load(File file) throws FileNotFoundException, IOException
	{
		FileInputStream fileInputStream = null;

		fileInputStream = new FileInputStream(file);
		load(fileInputStream);
		fileInputStream.close();
	}

	/**
	 * Method to save the properties.
	 * @param filename The filename of the property list.
	 * @param header The description to write at the top of the saved properties.
	 * @exception IOException Thrown if the save failed.
	 * @see #save(java.io.File)
	 */
	public void save(String filename,String header) throws IOException
	{
		File file = null;

		file = new File(filename);
		save(file,header);
	}    

	/**
	 * Method to save the properties.
	 * @param file A File object representing the filename of the property list.
	 * @param header The description to write at the top of the saved properties.
	 * @exception IOException Thrown if the save failed.
	 * @see java.util.Properties#store(java.io.InputStream)
	 */
	public void save(File file,String header) throws IOException
	{
		FileOutputStream fileOutputStream = null;

		fileOutputStream = new FileOutputStream(file);
		store(fileOutputStream,header);
		fileOutputStream.close();
	}

    public short getShort( String key ) throws NGATPropertyException
    {
	String value = null;
	value = getProperty( key );
	if( value == null )
	    {
		throw new NGATPropertyException
		    ( new NullPointerException(), key, "missing" );
	    }

	try
	    {
		return( Short.parseShort( value ) );
	    }
	catch( NumberFormatException nfe )
	    {
		throw new NGATPropertyException( nfe, key, getProperty( key ) );
	    }
    }

    public double getShort( String key, short def )
    {
	try
	    {
		return getShort( key );
	    }
	catch( NGATPropertyException npe )
	    {
		printError( npe, key, new Short( def ).toString() );
		return def;
	    }
    }

    public int getInt( String key ) throws NGATPropertyException
    {
	String value = null;
	value = getProperty( key );
	if( value == null )
	    {
		throw new NGATPropertyException
		    ( new NullPointerException(), key, "missing" );
	    }

	try
	    {
		return( Integer.parseInt( value ) );
	    }
	catch( NumberFormatException nfe )
	    {
		throw new NGATPropertyException( nfe, key, getProperty( key ) );
	    }
    }

    public int getInt( String key, int def )
    {
	try
	    {
		return( getInt( key ) );
	    }
	catch( NGATPropertyException npe )
	    {
		printError( npe, key, new Integer( def ).toString() );
		return def;
	    }
    }

    public long getLong( String key ) throws NGATPropertyException
    {
	String value = null;
	value = getProperty( key );
	if( value == null )
	    {
		throw new NGATPropertyException
		    ( new NullPointerException(), key, "missing" );
	    }

	try
	    {
		return( Long.parseLong( value ) );
	    }
	catch( NumberFormatException nfe )
	    {
		throw new NGATPropertyException( nfe, key, getProperty( key ) );
	    }
    }

    public long getLong( String key, long def )
    {
	try
	    {
		return getLong( key );
	    }
	catch( NGATPropertyException npe )
	    {
		printError( npe, key, new Long( def ).toString() );
		return def;
	    }
    }

    public double getDouble( String key ) throws NGATPropertyException
    {
	String value = null;
	value = getProperty( key );
	if( value == null )
	    {
		throw new NGATPropertyException
		    ( new NullPointerException(), key, "missing" );
	    }

	try
	    {
		return( Double.parseDouble( value ) );
	    }
	catch( NumberFormatException nfe )
	    {
		throw new NGATPropertyException( nfe, key, getProperty( key ) );
	    }
    }

    public double getDouble( String key, double def )
    {
	try
	    {
		return getDouble( key );
	    }
	catch( NGATPropertyException npe )
	    {
		printError( npe, key, new Double( def ).toString() );
		return def;
	    }
    }

    public boolean getBoolean( String key )
    {
	return( ( Boolean.valueOf( getProperty( key ) ) ).booleanValue() );
    }

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

    public InetAddress getInetAddress( String key ) throws NGATPropertyException
    {
	String value = null;
	value = getProperty( key );
	if( value == null )
	    {
		throw new NGATPropertyException
		    ( new NullPointerException(), key, "missing" );
	    }

	try
	    {
		return( InetAddress.getByName( value ) );
	    }
	catch( UnknownHostException uhe )
	    {
		throw new NGATPropertyException( uhe, key, getProperty( key ) );
	    }
    }

    public InetAddress getInetAddress( String key, InetAddress def )
    {
	try
	    {
		return( getInetAddress( key ) );
	    }
	catch( NGATPropertyException npe )
	    {
		printError( npe, key, def.toString() );
		return def;
	    }
    }

    public Color getColor( String key ) throws NGATPropertyException
    {
	try
	    {
		String colorString = getProperty( key );
		int i = colorString.indexOf( '=' );
		int j = colorString.indexOf( ',' );
		int red = Integer.parseInt( colorString.substring( i+1, j-1 ) );
		i = colorString.indexOf( '=', j );
		j = colorString.indexOf( ',', i );
		int green = Integer.parseInt( colorString.substring( i+1, j-1 ) );
		i = colorString.indexOf( '=', j );
		j = colorString.indexOf( ']', i );
		int blue = Integer.parseInt( colorString.substring( i+1, j-1 ) );
		return new Color( red, green, blue );
	    }
	catch( NumberFormatException nfe )
	    {
		throw new NGATPropertyException( nfe, key, getProperty( key ) );
	    }
    }

    public Color getColor( String key, int r, int g, int b )
    {
	try
	    {
		return getColor( key );
	    }
	catch( NGATPropertyException npe )
	    {
		String colorString = new String ( "[r="+r+",g="+g+",b="+b+"]" );
		printError( npe, key, colorString );
		return new Color( r, g, b );
	    }
    }

    protected void printError( Exception e, String a, String b )
    {
	System.err.println( "NGATProperty : "+e+" on key ("+a+") value - "+b );
    }
}
//
// $Log: not supported by cvs2svn $
// Revision 0.4  2001/07/05 18:59:25  je
// NullPointerExceptions caught and fed into NGATPropertyException
//
// Revision 0.3  2001/06/20 13:33:57  cjm
// Fixed javadoc error.
//
// Revision 0.2  2001/03/05 20:04:15  cjm
// Added some load methods.
//
// Revision 0.1  2000/09/06 13:09:51  cjm
// initial revision.
//
//
