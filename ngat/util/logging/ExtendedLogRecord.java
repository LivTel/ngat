package ngat.util.logging;

import java.util.*;
import java.io.*;


/** Extended Log record based on Common Base Event (CBE) logging but with
 * additional localization and classification information.
 */
public class ExtendedLogRecord implements Serializable {

    /** Serial version UID.*/
    public static final long serialVersionUID = 5963418736665803428L;

    /** The time the log event was generated (ms-1970).*/
    private long time;

    /** The system which generated the log message.*/
    private String system;

    /** The sub-system/module which generated the log message.*/
    private String subSystem;

    /** The class/type of the component which generated the log message.*/
    private String srcCompClass;

     /** The ID of the component which generated the log message.*/
    private String srcCompId;

    /** Block/method/procedure/section of the component code where the event was generated.*/
    private String block;

    /** Severity (in terms of effect on the system) of the event.
     */
    private int severity;

    /** Level/priority/granularity of the message.*/
    private int level;

    /** The theme or type of information the event is conveying e.g. operations, tracing.*/
    private String theme;

    /** Category of event - may be a keyword used to find specific types of information.*/
    private String category;
    
    /** A message explaining the event.*/
    private String message;

    /** Name of the thread calling the logget.*/
    private String thread;

    /** Mapping from keyword to value for various relevant environment (context) variables.*/
    private Map context;

    /** Create an ExtendedLogRecord.*/
    public ExtendedLogRecord() {}


    public void setTime(long t) {this.time = t;}
    public void setSystem(String s) {this.system = s;}
    public void setSubSystem(String s) {this.subSystem = s;}
    public void setSrcCompClass(String c) {this.srcCompClass = c;}
    public void setSrcCompId(String id) {this.srcCompId = id;}
    public void setBlock(String b) {this.block = b;}
    public void setSeverity(int s) {this.severity = s;}
    public void setLevel(int l) {this.level = l;}
    public void setTheme(String t) {this.theme = t;}
    public void setCategory(String c) {this.category = c;}
    public void setMessage(String m) {this.message = m;}
    public void setThread(String t) {this.thread = t;}
    public void setContext(Map c) {this.context = c;}


    /** Convenience method, add a context entry. If the context
     * is not already in existance it is created.
     */
    public void addContextEntry(String key, String value) {
	if (context == null)
	    context = new HashMap();
	context.put(key,value);
    }
    
    
    public long getTime() {return time;}
    public String getSystem() {return system;}
    public String getSubSystem() {return subSystem;}
    public String getSrcCompClass() {return srcCompClass;}
    public String getSrcCompId() {return srcCompId;}
    public String getBlock() {return block;}
    public int getSeverity() {return severity;}
    public int getLevel() {return level;}
    public String getTheme() {return theme;}
    public String getCategory() {return category;}
    public String getMessage() {return message;}
    public String getThread() {return thread;}
    public Map getContext() {return context;}

    public String toString() {
	return (new Date(time)).toGMTString()+" "+system+"."+subSystem+"."+srcCompClass+":"+srcCompId+"::"+block+
	    " ("+thread+") "+
	    toSeverity(severity)+" ("+level+") "+"["+
	    (category != null ? category: "")+
	    (theme    != null ? theme: "")+"] "+
	    (context != null ? "{"+context.size()+"}" : "{}")+" "+message;
	// eg.
	//    2008-01-03 T 15:22:33 RCS.TMM.ExposureTask:ETA_01B_MR5::preInit (main) ERROR(3) [Info,Trace] Unable to connect to RATCAM
	//                          Exception: java.net.ConnectException No route to host.

    }

    public String toSeverity(int severity) {
	switch (severity) {
	case Logging.SEVERITY_FATAL:
	    return "FATAL";
	case Logging.SEVERITY_CRITICAL:
	    return "CRITICAL";
	case Logging.SEVERITY_ERROR:
	    return "ERROR";
	case Logging.SEVERITY_WARNING:
	    return "WARNING";
	case Logging.SEVERITY_INFORMATION:
	    return "INFORMATION";
	default:
	    return "UNKNOWN";
	}
    }

}
