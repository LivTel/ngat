package ngat.util.logging;

/** This default implementation of LogFilter allows any 
 *and all records through.*/
public class DefaultLogFilter implements LogFilter {

    public boolean isLoggable(LogRecord record) {
	return true;
    }

}
