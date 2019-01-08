package ngat.instrument;

import java.util.*;
import java.io.*;

/** Stores information about the currently available filters.
 *SId$
 */
public class FilterDatabase {

    protected Map filters;

    public FilterDatabase(int capacity) {
	filters = Collections.synchronizedMap(new HashMap(capacity));
    }

    /** Load the database from  a file layed out in the
     * java.util.Properties format.
     * @param propsFile The java.io.File containing the properties.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void configureFilter(String physicalName, File propsFile) 
	throws IOException, IllegalArgumentException {
	
	InputStream in   = new FileInputStream(propsFile);
	Properties props = new Properties();
	props.load(in);
	Filter filter    = new Filter(physicalName);
	filter.configure(props);
	filters.put(filter.getName(), filter);
    }

    /** Lookup a filter with the specified (physical) name.*/
    public Filter lookupName(String physicalName) {
	return (Filter)filters.get(physicalName);
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/**
/** Revision 1.1  2001/02/23 18:46:58  snf
/** Initial revision
/** */
