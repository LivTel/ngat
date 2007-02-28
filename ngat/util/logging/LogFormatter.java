package ngat.util.logging;

import java.text.*;
import java.util.*;

/** Interface to be implemented by any class which is required to format Log messages.*/
public abstract class LogFormatter {

    /** Standard date format ISO 8601.*/
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS z");
    
    public static SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");
    
    /** The Date format in use.*/
    protected SimpleDateFormat df;

    public LogFormatter() {
	df = sdf;
	df.setTimeZone(UTC);
    }

 
    /** Format a LogRecord.*/
    public abstract String format(LogRecord record);
    
    /** Return the head sequence for the formatter.*/
    public abstract String getHead();

    /** Return the Tail sequence for the formatter.*/
    public abstract String getTail();

    /** Return the file name extension for the formatter.*/
    public abstract String getExtensionName();

    /** Set the DateFormat to use for time formats.
     * @param sdf The DateFormat to use - typically java.text.SimpleDateFormat.*/
    public void setDateFormat(SimpleDateFormat df) { this.df = sdf; }

}
