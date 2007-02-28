package ngat.util.logging;

import ngat.util.*;

import java.util.*;
import java.text.*;

/** This '<i>Bog-Standard</i>' LogFormatter just prints out the time, source and message.
 */
public class BogstanLogFormatter extends LogFormatter {

    public BogstanLogFormatter() {
	super();
    }

    public String format(LogRecord record) {	
	return record.getLoggerName()+" :: "+df.format(new Date(record.getTime()))+" : "+
	    record.getClazz()+" : "+record.getSource()+" : "+record.getMessage();
    }
    
    public String getHead() {
	return "Logging START--->";
    }
    
    public String getTail() {
	return "<---END Logging.";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "txt"; }

}
