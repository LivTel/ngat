package ngat.util.logging;

import java.util.*;
import java.text.*;

public class CsvLogFormatter extends LogFormatter {

    final String DEFAULT_SEPERATOR = ",";

    String seperator = DEFAULT_SEPERATOR;

    public CsvLogFormatter() {
	super();
    } 

    public String format(LogRecord record) {
	String result = 
	    record.getSeqno()+seperator+
	    record.getLoggerName()+seperator+
	    record.getLevel()+seperator+
	    record.getCat()+seperator+
	    df.format(new Date(record.getTime()))+seperator+
	    record.getClazz()+seperator+
	    record.getSource()+seperator+
	    record.getMethod()+seperator+
	    record.getThread()+seperator+
	    record.getMessage();
	
	if (record.getException() != null) {
	    result += seperator+record.getException();
	}
	return result;
    }
    
    public String getHead() {
	return "CSV Logging START---";
    }
    
    public String getTail() {
	return "---END CSV Logging.";
    }
   
    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "csv"; }

    /** Set the seperator character(s).*/
    public void setSeperator(String seperator) {
	this.seperator = seperator;
    }

}
