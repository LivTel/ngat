package ngat.util;

import java.util.*;
import java.io.*;
import java.text.*;

/** Extension of java.util.Properties to allow configuration settings
 * to be obtained via some useful methods..
 * 
 * $Id: ConfigurationProperties.java,v 1.1 2000-11-21 16:11:43 snf Exp $
 *
 */
public class ConfigurationProperties extends Properties {

    /** Create a ConfigurationProperties.*/
    public ConfigurationProperties() {
	super();
    }

    /** Create a ConfigurationProperties with specified defaults.*/
    public ConfigurationProperties(Properties defaults) {
	super(defaults);
    }
    
    /** Returns the int value of a property for specified key. */
    public int getIntValue(String key) throws ParseException {
	try {
	    return Integer.parseInt(getProperty(key));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString(),0);
	}
    }
    
    /** Returns the int value of a property for specified key or the supplied default. */
    public int getIntValue(String key, int def) {
	try {
	    return Integer.parseInt(getProperty(key));
	} catch (NumberFormatException e) {
	    return def;
	}
    }
    
    /** Returns the float value of a property for specified key. */ 
    public float getFloatValue(String key) throws ParseException {
	try {
	    return  Float.parseFloat(getProperty(key));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString(),0);
	}
    }

    /** Returns the float value of a property for specified key or the supplied default. */
    public float getFloatValue(String key, float def) {
	try {
	    return Float.parseFloat(getProperty(key));
	} catch (NumberFormatException e) {
	    return def;
	}
    }

    /** Returns the double value of a property for specified key.*/
    public double getDoubleValue(String key) throws ParseException {
	try {
	    return  Double.parseDouble(getProperty(key));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString(),0);
	}
    }

    /** Returns the double value of a property for specified key or the supplied default. */
    public double getDoubleValue(String key, double def) {
	try {
	    return Double.parseDouble(getProperty(key));
	} catch (NumberFormatException e) {
	    return def;
	}
    }

    /** Returns the long value of a property for specified key.*/
    public long getLongValue(String key) throws ParseException {
	try {
	    return (long)Integer.parseInt(getProperty(key));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString(),0);
	}
    }

    /** Returns the long value of a property for specified key or the supplied default. */
    public long getLongValue(String key, long def) {
	try {
	    return (long)Integer.parseInt(getProperty(key));
	} catch (NumberFormatException e) {
	    return def;
	}
    }

    /** Returns the boolean value of a property for specified key.*/
    public boolean getBooleanValue(String key) {
	return Boolean.valueOf(getProperty(key)).booleanValue();}
    
    /** Returns a Date from a string version of a long.*/
    public Date getDateValue(String key) throws ParseException {
	try {
	    return new Date(Long.parseLong(getProperty(key)));
	} catch (NumberFormatException e) {
	    throw new ParseException(e.toString(),0);
	}
    }

    /** Returns a Date from a string version of a long or the supplied default.*/
    public Date getDateValue(String key, Date def) {
	try {
	    return new Date(Long.parseLong(getProperty(key)));
	} catch (NumberFormatException e) {
	    return def;
	}
    }

}

/** $Log: not supported by cvs2svn $ */
