package ngat.util.logging;

import java.util.*;

/** A LogHandler which appends the log record to a java.util.List.*/
public class ListAppenderLogHandler extends LogHandler implements ExtendedLogHandler {
    
    /** the list which holds the log messages.*/
    private List list;
    
    public ListAppenderLogHandler(List list) {
	super(null);
	this.list = list;
    }
    
    public void publish(LogRecord record) {
	if (list != null)
	    list.add(record);
    }
    
    public void publish(ExtendedLogRecord record) {
	if (list != null)
	    list.add(record);
    }
    
    public boolean isLoggable(ExtendedLogRecord record) {	
	return (logLevel == Logging.ALL || record.getLevel() <= logLevel);
    }
    
    public void close() { }
    
}