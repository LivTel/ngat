package ngat.util;

import java.util.*;
import java.text.*;

/** Parses a Command list for options delimited by (usually) a '-' symbol.
 * and maps these to a java.util.Properties object.
 * Commands are split into 2 types:-
 * 
 * -<option> 
 *   If the option begins 'no' then the value of the key is set to "false"
 *   otherwise it is set to "true"
 *    e.g. -nohash (false value) or -optimize (true value)
 * -<option> <value> 
 *   The option value pair are stored in the properties.
 *    e.g.  -maxsize 200 (name is set to maxsize value is set to 200).
 *
 * A typical arglist might look like: -noresize -heap 200 -stackmin 30 -stackmax 80 -verbose
 *
 * $Id: CommandParser.java,v 1.5 2001-07-11 10:24:23 snf Exp $
 *
 */
public class CommandParser {

    /** Holds the list of properties.*/
    protected ConfigurationProperties map;

    /** Holds the current command delimiter character.*/
    protected String delim;

    /** Create a CommandParser using default - character.*/
    public CommandParser() {
	this("-");
    }

    /** Create a CommandParser.*/
    public CommandParser(String delim) {
	map = new ConfigurationProperties();
	this.delim = delim;
    }

    public void parse(String argList) throws ParseException { parse(argList, "enable", "disable"); }

    /** Parse the supplied arguments using yesVal as the option value for 'true' and 
     * noVal as the option value for false.
     *
     * 
     * parse("... -fixed -size 20 -nobuild ....", "enabled", "disabled")
     *
     * Option -fixed is stored with the value "enabled"
     * Option -build is stored with the value "disabled"
     *
     */
    public void parse(String argList, String yesVal, String noVal) throws ParseException {
	
	if (argList == null) throw new ParseException("No Args", 0);
	
	StringTokenizer st = new StringTokenizer(argList, delim);
	String token = null;
	String name = null;
	String value = null;
	int pos = 0;
	while (st.hasMoreTokens()) {
	    token = st.nextToken().trim(); // loose any trailing spaces.
	    //System.out.println("TOKEN: "+token);
	    pos = token.indexOf(" ");
	    //System.out.println("POS: "+pos);
	    if (pos != -1) {
		// type 1.
		name = token.substring(0, pos);
		value = token.substring(pos+1);
		map.setProperty(name,value);
	    } else {
		// type 2.
		if (token.length() > 2) {
		    if (token.substring(0,2).equals("no")) {
			name = token.substring(2);
			map.setProperty(name,noVal);
		    } else {
			name = token;
			map.setProperty(name, yesVal);
		    }
		} else {
		    map.setProperty(token, yesVal);
		}
	    }
	}
    }
    
    /** Parse the supplied arguments using the default values of "true" and "false".*/
    public void parse(String[] args) throws ParseException {
	StringBuffer buff = new StringBuffer();
	//System.out.println("ARRAY VERSION");
	if (args == null) return;
	if (args.length == 0) return;
	for (int i = 0; i < args.length; i++) {
	    //System.out.println("ARG: "+i+" : "+args[i]);
	    if (args[i].startsWith(delim)) {
		buff.append(args[i]);
	    } else {
		buff.append(" ").append(args[i]);
	    }
	}
	parse(buff.toString(), "true", "false");
    }

    /** Returns the properties parsed from the arglist in <option : value> pairs. */
    public ConfigurationProperties getMap() { return map;}

    /** Translates the map, replacing capitalized letters with dots
     * for use in Configuration files e.g.
     *
     * An option defined as: <i>-maxBufferSize</i> would be set to <i>max.buffer.size</i>
     *
     * */
    public ConfigurationProperties getTokenizedMap() {
	StringBuffer buff = new StringBuffer();
	Enumeration keys = map.propertyNames();
	ConfigurationProperties newMap = new ConfigurationProperties();
	String key = null;
	while (keys.hasMoreElements()) {
	    key = (String)keys.nextElement();
	    newMap.setProperty(key.replace('_','.'), map.getProperty(key));
	}
	return newMap;
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/02/23 18:49:14  snf
/** Added linger and awaken.
/**
/** Revision 1.3  2000/12/19 15:12:09  snf
/** Took out debugging.
/**
/** Revision 1.2  2000/11/21 17:24:58  snf
/** Changed package name to util.
/**
/** Revision 1.1  2000/11/21 16:37:10  snf
/** Initial revision
/** */
