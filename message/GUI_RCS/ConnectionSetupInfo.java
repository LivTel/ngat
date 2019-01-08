package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates the data required to setup a return connection.
 */
public class ConnectionSetupInfo extends TelemetryInfo {

    public static final int UDP  = 1;

    public static final int CAMP = 2;

    public static final int JMS  = 3;

    public int type;

    public String host;

    public int port;

    /** Create a ConnectionSetupInfo.*/
    public ConnectionSetupInfo(long time, int type, String host, int port) {
	super(time);
	this.type = type;
	this.host = host;
	this.port = port;
    }
    
    public static String toTypeString(int type) {
	switch (type) {
	case UDP:
	    return "UDP";
	case CAMP:
	    return "CAMP";
	case JMS:
	    return "JMS";
	default:
	    return "UNKNOWN:"+type;
	}
    }

    public String toString() {
	return "ConnectionSetupInfo: "+time+", Host="+host+", port="+port+", Protocol="+toTypeString(type);
    }
    
}
