package ngat.util.logging;

/** Abstract base class for all Handlers of ExtendedLogRecords generated by Loggers.
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
public interface ExtendedLogHandler extends Logging {

    /** Override to write a formatted LogRecord to the output stream.*/
    public abstract void publish(ExtendedLogRecord record);

    /** Override to return true if the supplied record is allowed.*/
    public boolean isLoggable(ExtendedLogRecord record);

}