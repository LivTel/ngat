package ngat.util.logging;

import java.util.*;
import java.text.*;

public class HtmlLogFormatter extends LogFormatter {

    final String NBSP = "&nbsp;";

    public  HtmlLogFormatter() {
	super();
    }
    

    public String format(LogRecord record) {
	String result = 
	    "<hr>** Log:"+NBSP+record.getSeqno()+NBSP+record.getLoggerName()+NBSP+
	    "Level:"+NBSP+record.getLevel()+NBSP+" Cat:"+NBSP+record.getCat()+
	    "at:"+NBSP+df.format(new Date(record.getTime()))+
	    "<ul><li>Source:"+NBSP+
	    record.getClazz()+NBSP+":"+NBSP+
	    record.getSource()+NBSP+":"+NBSP+"("+
	    record.getMethod()+")"+NBSP+"Called by:"+NBSP+
	    record.getThread()+
	    "</ul><hr>"+record.getMessage()+"<hr>";
	
	if (record.getException() != null) {
	    result += "<ul><li>Exception:"+NBSP+
		record.getException()+
		"</ul><hr><br>";
	}
	return result;
    }
    
    public String getHead() {
	return "<html>\n<head>\n<title>Logging info.</title>\n</head>\n<body bgcolor = #FFFFFF >\n<h2>Logging</h2><hr>";
    }
    
    public String getTail() {
	return "</head>\n</html>";
    }

    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "html"; }

}
