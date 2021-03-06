package ngat.util.logging;

/** Abstract base class for all Handlers of LogRecords generated by Loggers.
 * The general contract for a handler is as follows:-
 * <dl>
 * <dt> Constructor: 
 * <dd> Open any output streams, sockets etc. Write the formatter's header. 
 * <dt> Override method publish(): 
 * <dd> Write a formatted record via formatter.format(record).
 * <dt> Override method close(): 
 * <dd> Write the formatter's tail. Close output streams, sockets etc.
 * </dl>
 */
public abstract class LogHandler implements Logging {

    /** The formatter to use for output.*/
    protected LogFormatter formatter;

    /** A LogFilter attached to this Logger.*/  
    protected LogFilter filter;
    
    /** The current level of logging for this Logger.*/
    protected int logLevel;

    /** An ID to distinguish this handler from others.*/
    protected String name;

    /** Create a generic LogHandler using the specified LogFormatter.
     * The initial logLevel is set to 1.
     * @param formatter The non-null formatter to use.*/
    public LogHandler(LogFormatter formatter) {
	this.formatter = formatter;
	logLevel = OFF; 
	name = ""+hashCode();
    }

    /** Override to write a formatted LogRecord to the output stream.*/
    public abstract void publish(LogRecord record);

    /** @return True if no filter is specified otherwise whatever the
     * filter lets through.*/
    protected boolean isLoggable(LogRecord record) {
	if (logLevel != ALL)
	    if (record.getLevel() > logLevel) return false;
	if (filter == null) return true;
	return filter.isLoggable(record);
    }

    /** Override to close / terminate this handler and any associated
     * output streams, sockets etc.*/
    public abstract void close();

    /** Set the Filter to be used by this Handler.
     * @param filter The filter to apply.*/
    public void setFilter(LogFilter filter) { this.filter = filter; }
    
    /** @return The filter in use or null if none set.*/  
    public LogFilter getFilter() { return filter; }
    
    /** Set the level of logging for this handler. LogRecords with level
     * greater than this will be rejected.*/  
    public void setLogLevel(int logLevel) { this.logLevel = logLevel; }
    
    /** @return The level of logging for this handler.*/
    public int getLogLevel() { return logLevel; }
    
    /** Set the name/ID for this handler.
     * @param name The name to use.
     */
    public void setName(String name) { this.name = name; }

    /** @return The name/ID for this handler.*/
    public String getName() { return name; }

}
