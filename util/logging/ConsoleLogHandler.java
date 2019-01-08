package ngat.util.logging;

import java.io.*;

public class ConsoleLogHandler extends LogHandler implements ExtendedLogHandler {

    /** Create a ConsoleLogHandler using the specified formatter.*/
    public ConsoleLogHandler(LogFormatter formatter) {
	super(formatter);
	System.err.println(formatter.getHead());
    }

    /** Publish a LogRecord to System.err .*/
    public void publish(LogRecord record) {
	System.err.println(formatter.format(record));
    }

    /** Write a formatted ELR to the output stream.*/
    public void publish(ExtendedLogRecord record) {
	System.err.println(record.toString());
    }

    public boolean isLoggable(ExtendedLogRecord record) {
	return (record.getLevel() <= logLevel);
    }

    /** Write the tail.*/
    public void close() {
	System.err.println(formatter.getTail());
    }

}
