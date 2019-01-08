package ngat.util.logging;

import java.io.*;
import java.util.*;

/** LogHandler which logs data into a StringBuffer.
 * <br><br>
 * $Id$
 */
public class StringBufferLogHandler extends LogHandler {

    /** The buffer to hold the data.*/
    protected StringBuffer buffer;
    
    /** Create a StringBufferLogHandler using the specified JTextArea
     * and formatter.*/
    public StringBufferLogHandler(StringBuffer buffer, LogFormatter formatter) {
	super(formatter);
	this.buffer = buffer;
	buffer.append(formatter.getHead());
    }
    
    /** Publish a LogRecord to the TextArea.*/
    public void publish(LogRecord record) {
	buffer.append(formatter.format(record));
    }
    
    /** Write the tail.*/
    public void close() {
	buffer.append(formatter.getTail());
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/07/11 10:27:38  snf
/** backup.
/** */
