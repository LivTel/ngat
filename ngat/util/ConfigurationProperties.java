package ngat.util;

import java.util.*;
import java.io.*;
import java.text.*;

/** Extension of java.util.Properties to allow configuration settings
 * to be obtained via some useful methods..
 * 
 * $Id: ConfigurationProperties.java,v 1.5 2007-02-01 08:13:58 snf Exp $
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
    
    /** Returns the property - trimmed.*/
    public String getProperty(String key) { 
	String value = super.getProperty(key);
	if (value == null)
	    return null;
	return value.trim();
    }

    /** Returns the property - trimmed.*/
    public String getProperty(String key, String def) { 
	String value = super.getProperty(key);
	if (value == null)
	    return def;
	return value.trim();
    }

    /** Returns the int value of a property for specified key. */
    public int getIntValue(String key) throws ParseException {
	try {
	    return Integer.parseInt(getProperty(key));
	} catch (Exception e) {
	    throw new ParseException("Parsing key: "+key+":"+e.toString(),0);
	}
    }
    
    /** Returns the int value of a property for specified key or the supplied default. */
    public int getIntValue(String key, int def) {
	try {
	    return Integer.parseInt(getProperty(key));
	} catch (Exception e) {
	    return def;
	}
    }
    
    /** Returns the float value of a property for specified key. */ 
    public float getFloatValue(String key) throws ParseException {
	try {
	    return  Float.parseFloat(getProperty(key));
	} catch (Exception e) {
	    throw new ParseException("Parsing key: "+key+":"+e.toString(),0);
	}
    }

    /** Returns the float value of a property for specified key or the supplied default. */
    public float getFloatValue(String key, float def) {
	try {
	    return Float.parseFloat(getProperty(key));
	} catch (Exception e) {
	    return def;
	}
    }

    /** Returns the double value of a property for specified key.*/
    public double getDoubleValue(String key) throws ParseException {
	try {
	    return  Double.parseDouble(getProperty(key));
	} catch (Exception e) {
	    throw new ParseException("Parsing key: "+key+":"+e.toString(),0);
	}
    }

    /** Returns the double value of a property for specified key or the supplied default. */
    public double getDoubleValue(String key, double def) {
	try {
	    return Double.parseDouble(getProperty(key));
	} catch (Exception e) {
	    return def;
	}
    }

    /** Returns the long value of a property for specified key.*/
    public long getLongValue(String key) throws ParseException {
	try {
	    return (long)Integer.parseInt(getProperty(key));
	} catch (Exception e) {
	    throw new ParseException("Parsing key: "+key+":"+e.toString(),0);
	}
    }

    /** Returns the long value of a property for specified key or the supplied default. */
    public long getLongValue(String key, long def) {
	try {
	    return (long)Integer.parseInt(getProperty(key));
	} catch (Exception e) {
	    return def;
	}
    }

    /** Returns the boolean value of a property for specified key.*/
    public boolean getBooleanValue(String key, boolean def) {
	if (getProperty(key) == null) return def;
	return Boolean.valueOf(getProperty(key)).booleanValue();
    }

    /** Returns the boolean value of a property for specified key.*/
    public boolean getBooleanValue(String key) {
	return getBooleanValue(key, false);
    }
    
    /** Returns a Date from a string version of a long.*/
    public Date getDateValue(String key) throws ParseException {
	try {
	    return new Date(Long.parseLong(getProperty(key)));
	} catch (Exception e) {
	    throw new ParseException("Parsing key: "+key+":"+e.toString(),0);
	}
    }

    /** Returns a Date from a string version of a long or the supplied default.*/
    public Date getDateValue(String key, Date def) {
	try {
	    return new Date(Long.parseLong(getProperty(key)));
	} catch (Exception e) {
	    return def;
	}
    }

    /** Returns an existing File from its path name in a property key.*/
    public File getFile(String key) throws FileNotFoundException {
	if (key == null || getProperty(key) == null)
	    throw new FileNotFoundException("Cannot locate file for key ("+key+")");
	File file = new File(getProperty(key));
	if (!file.exists())
	    throw new FileNotFoundException("No such file: "+file.getPath());
	return file;
    }

    /** Returns a File from its path name in a property key or the supplied default.*/
     public File getFile(String key, String def) throws FileNotFoundException {
	 try {
	     return getFile(key);
	 } catch (FileNotFoundException e) {
	     File file = new File(def);
	     if (!file.exists())
		 throw new FileNotFoundException("No such file: "+file.getPath());
	     return file;
	 }
     }
    
    /** Returns a File from its path name in a property key or the supplied default.*/
    public File getFile(String key, File file) throws FileNotFoundException {
	try {
	    return getFile(key);
	} catch (FileNotFoundException e) {
	    if (!file.exists())
		throw new FileNotFoundException("No such file: "+file.getPath());
	    return file;
	}
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2004/01/15 16:01:57  snf
/** Removed debugging code.
/**
/** Revision 1.3  2002/09/25 10:56:17  cjm
/** rcs bug fix.
/**
/** Revision 1.2  2001/07/11 10:24:23  snf
/** backup.
/**
/** Revision 1.1  2000/11/21 16:11:43  snf
/** Initial revision
/** */
