package ngat.util.logging;

import ngat.util.*;

import java.util.*;
import java.text.*;

public class PlainLogFormatter extends LogFormatter {

    static final int[] COLUMNS = new int[] { 10, 10, 8, 12, 20, 25, 20, 10, 20, 40, 40 };

    static String[] TEXT = new String[11];


    public  PlainLogFormatter() {
	super();
    }

    public String format(LogRecord record) {
      
	TEXT[0] = "**"+record.getLoggerName(); // 10
	TEXT[1] = " Log: "+record.getSeqno();  // 10
	TEXT[2] = " Level: "+record.getLevel();// 8
	TEXT[3] = " Cat: "+record.getCat();    // 12
	TEXT[4] = " at: "+df.format(new Date(record.getTime())); // 20
	TEXT[5] = " Source: "+record.getClazz(); // 25
	TEXT[6] = " : "+record.getSource();      // 20
	TEXT[7] = " : "+"("+record.getMethod()+")"; // 10
	TEXT[8] = " Called by: "+record.getThread(); // 20
	TEXT[9] = "/"+record.getMessage();               // 40
	
	Exception exception = record.getException();
	if (exception != null) {
	    TEXT[10] = "Exception: "+exception; // 40
	} else {
	    TEXT[10] = "";
	}

	String result = null;

	try {
	    result = StringUtilities.tabulate(TEXT, COLUMNS);
	} catch (IllegalArgumentException e) {
	    return "Message could not be formatted";
	}

	return result;
    }
    
    public String getHead() {
	return "Logging START---";
    }
    
    public String getTail() {
	return "---END Logging.";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "txt"; }

}
