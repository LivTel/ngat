package ngat.util.logging;

import ngat.util.*;

import java.util.*;
import java.text.*;

/** This '<i>Basic</i>' LogFormatter just prints out the time, source and message.
 */
public class BasicLogFormatter extends LogFormatter {

    /** Default point where log messages are broken over a line.
     * Any messages under this length are written to same line as time.
     * Any messages over this length are started on a new line.
     */
    public static final int DEFAULT_BREAK_LENGTH = 50;

    /** Point where messages are broken over a line.*/
    protected int breakLength;

    /** Create a BasicLogFormatter with default break length.*/
    public BasicLogFormatter() {
	this(DEFAULT_BREAK_LENGTH);
    }

    /** Create a BasicLogFormatter with specified break length.*/
    public BasicLogFormatter(int breakLength) {
	 super();
	 this.breakLength = breakLength;
    }

    public String format(LogRecord record) {
	int len = (record.getMessage() != null ? record.getMessage().length() : 0);
	if (len <= breakLength)
	    return df.format(new Date(record.getTime()))+" : "+record.getMessage();
	return df.format(new Date(record.getTime()))+"\n"+record.getMessage();
    }
    
    public String getHead() {
	return "Basic-Logging START--->";
    }
    
    public String getTail() {
	return "<---END Basic-Logging.";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "txt"; }

}
