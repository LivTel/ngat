package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;
import ngat.util.logging.*;

/** Encapsulates Observation update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public class LogInfo extends TelemetryInfo {   

    /** Category.*/
    String cat;

    /** Message.*/
    String message;

    LogRecord record;
    
    /** Create an empty LogInfo.*/
    public LogInfo(long time, String cat, String message) {
	super(time);
	this.cat     = cat;
	this.message = message;
    }

    public void setRecord(LogRecord record) {
	this.record = record;
    }

    /** Returns the category.*/
    public String getCat() { return cat; }

    /** Returns hte message.*/
    public String getMessage() { return message; }

    /** Readable representation of LogInfo.*/
    public String toString() {
	return "LogInfo: "+time+" : Cat="+cat+", Message="+message;
    }
    
}
