// NGATProperties.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/NGATProperties.java,v 0.1 2000-09-06 13:09:51 cjm Exp $
package ngat.util;

import java.lang.*;
import java.util.*;
import java.awt.*;
import java.net.*;

public class NGATProperties extends Properties
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: NGATProperties.java,v 0.1 2000-09-06 13:09:51 cjm Exp $");

    public short getShort( String key ) throws NGATPropertyException
    {
	try
	    {
		return( Short.parseShort( getProperty( key ) ) );
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
	try
	    {
		return( Integer.parseInt( getProperty( key ) ) );
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
	try
	    {
		return( Long.parseLong( getProperty( key ) ) );
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
	try
	    {
		return( Double.parseDouble( getProperty( key ) ) );
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
	try
	    {
		return( InetAddress.getByName( getProperty( key ) ) );
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
//
