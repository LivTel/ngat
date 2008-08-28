package ngat.util.logging;

import java.io.*;

public class ConsoleLogHandler extends LogHandler {

    /** Create a ConsoleLogHandler using the specified formatter.*/
    public ConsoleLogHandler(LogFormatter formatter) {
	super(formatter);
	System.err.println(formatter.getHead());
    }

    /** Publish a LogRecord to System.err .*/
    public void publish(LogRecord record) {
	System.err.println(formatter.format(record));
    }

    /** Override to write a formatted LogRecord to the output stream.*/
    public abstract void publish(ExtendedLogRecord elr) {
	System.err.println(formatter.format(record));
    }

    /** Write the tail.*/
    public void close() {
	System.err.println(formatter.getTail());
    }

}
