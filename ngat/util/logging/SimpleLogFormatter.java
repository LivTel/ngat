package ngat.util.logging;

import java.util.*;
import java.text.*;

/** This LogFormatter creates a message from the published LogRecord 
 * with the following format:-<br>
 * ------------------------------------------------------------------------------------------<br>
 * <i>logger-name</i> Log: <i>log-no</i>  Level: <i>level</i> Cat: <i>category</i> at: <i>time</i><br>
 *      Source: <i>class</i> : <i>source-id</i> : (<i>method</i>) Called by: <i>thread</i><br>
 * ------------------------------------------------------------------------------------------<br>
 * <i>one or more lines of message</i><br>
 * ------------------------------------------------------------------------------------------<br>
 *
 * <br><br>
 * Example.
 *
 * <br><br>
 * --------------------------------------------------------------<br>
 * TRACE Log: 362  Level: 1 Cat: ERROR at: 11:29:56.27 GMT <br>
 * <ul>      Source: DBServer : DB_SERVER : (-) Called by: DB_SERVER_THREAD#01</ul><br>
 * --------------------------------------------------------------<br>
 * Client (Someuser) connected from 150.221.223.15 (port 5050)<br>
 *  - Authetication failed<br>
 *  - Code 214 (Not authorized for operation: KILL_SERVER).
 * --------------------------------------------------------------<br>
 *
 * <br><br>
 * $Id$
 */
public class SimpleLogFormatter extends LogFormatter {

    public SimpleLogFormatter() {
	 super();
    }

    public String format(LogRecord record) {
	StringBuffer buffer = new StringBuffer();
	buffer.append("--------------------------------------------------------------"+
		      "\n**"+record.getLoggerName()+" Log: "+record.getSeqno()+" "+
		      " Level: "+record.getLevel()+" Cat: "+record.getCat()+
		      " at: "+df.format(new Date(record.getTime()))+
		      " \n\tSource: "+
		      record.getClazz()+" : "+
		      record.getSource()+" : "+"("+
		      record.getMethod()+")"+" Called by: "+
		      record.getThread()+
		      "\n--------------------------------------------------------------\n"+
		      record.getMessage()+
		      "\n--------------------------------------------------------------");
	
	Exception exception = record.getException();
	if (exception != null) {
	    buffer.append("\nException: "+exception.getClass().getName()+
			  "\n"+exception+
			  "\n--------------------------------------------------------------");
	}
	
	Object[] params = record.getParams();
	if (params != null) {
	    for (int i = 0; i < params.length; i++) {
		buffer.append("\nParam #"+i+" "+params[i]);
	    }
	    buffer.append("\n--------------------------------------------------------------");
	}
	return buffer.toString();
    }
    
    public String getHead() {
	return "";
    }
    
    public String getTail() {
	return "";
    }
  
    /** Return the file name extension for the formatter.*/
    public String getExtensionName() { return "txt"; }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/11/13 13:20:33  snf
/** SDF is now protected.
/**
/** Revision 1.3  2001/03/30 15:03:15  cjm
/** Changing getHead and getTail so that we don't get anonymous logger starts when starting a process containing
/** logging.
/**
/** Revision 1.2  2001/03/30 15:02:52  cjm
/** *** empty log message ***
/***/
