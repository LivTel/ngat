package ngat.util;

import java.util.*;
import java.io.*;
import java.text.*;

/** Extension of java.util.Properties to allow configuration settings
 * to be obtained via some useful methods..
 * 
 * $Id: ConfigurationCache.java,v 1.2 2000-11-21 17:26:44 snf Exp $
 *
 */
public class ConfigurationCache extends ConfigurationProperties {

    protected Properties essential;

    public ConfigurationCache() {
	super();
	essential = new Properties();
    }
    
    public ConfigurationCache(Properties defaults) {
	super(defaults);
	essential = new Properties();
    }
    
    
    /** Must be overriden to allow a cache variable to be set using the supplied string
     * as the property value associated with key. Should throw an exception if either
     * the key does not exist (in essential properties) or the value would be out of range. 
     * The contents of the essential properties hashtable are used as a switch to decide
     * which cache variable to set.*/
    public void update(String key, String value) throws IllegalArgumentException {};

    /** Load all the essential cache properties and set cache variables. */
    public void loadEssential(Properties defaults) throws IllegalArgumentException {
	// Check all the keys are there. loop thro props and see if defs has each of them
	String key = null;
	Enumeration e = essential.keys();
	while (e.hasMoreElements()) {
	    key = (String)e.nextElement();
	    if (!defaults.containsKey(key)) throw new IllegalArgumentException("Missing properties: "+key);
	}
	// Now go through the props and set the cache vars individually.
	e = essential.keys();
	while (e.hasMoreElements()) {
	    key = (String)e.nextElement();
	    setProperty(key, defaults.getProperty(key));
	    update(key, defaults.getProperty(key));
	}
    }
    
    /** Load the cache properties from a file and set cache variables. */
    public void loadEssential(File propertyFile) throws IllegalArgumentException {
	Properties defaults = new Properties();
	try {
	    defaults.load(new FileInputStream(propertyFile));
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error loading essential properties from ["+
					       propertyFile.getPath()+"] "+e);
	}
	try {
	    defaults.store(System.out, "Properties");
	} catch (IOException e) {}  
	// Ok the defaults are loaded, now try updating them. 
	// This will crash as expected if any are missing or invalid.
	loadEssential(defaults);
    }
    
    /** Merges the supplied cache properties with the existing ones and sets cache variables.*/
    public void mergeProperties(Properties extras) throws IllegalArgumentException {
	String key = null;
	Enumeration e = extras.keys();
	while (e.hasMoreElements()) {
	    key = (String)e.nextElement();
	    setProperty(key, extras.getProperty(key));
	    update(key, extras.getProperty(key));
	}
    }


    /** Merges the properties from a file and sets cache variables.*/
    public void mergeProperties(File propertyFile) throws IllegalArgumentException {
	Properties extras = new Properties();
	try {
	    extras.load(new FileInputStream(propertyFile));
	} catch (IOException e) {
	    throw new IllegalArgumentException("Error loading merge properties from ["+
					       propertyFile.getPath()+"] "+e);
	}
	mergeProperties(extras);
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/21 16:12:37  snf
/** Initial revision
/** */
