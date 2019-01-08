package ngat.util.logging;

import java.text.*;
import java.util.*;


public class SyslogFormatter extends LogFormatter {

    /** Format a LogRecord.*/
    public String format(LogRecord record) {
	return record.getLoggerName()+" ["+record.getSeqno()+"] :: "+
	    record.getClazz()+" : "+record.getMessage();
    }
    
    /** Return the head sequence for the formatter.*/
    public String getHead() { return ""; }

    /** Return the Tail sequence for the formatter.*/
    public String getTail() { return ""; }
    
    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "sys"; }

}
