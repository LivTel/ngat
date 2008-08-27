package ngat.util.logging;

import java.util.*;
import java.io.*;

/** A Logger is the class which is used to carry out logging from any 
 * classes across an application or utilities. A Logger has a LogLevel setting
 * which is used to make a cheap comparison test and possibly reject a message.
 * A filter can be attached to a Logger to make a more refined selection of
 * messages to despatch to all of the handlers registered against it. 
 * When a logging call is made by a class (via an embedded call to any of 
 * the Logger.log() methods the following sequence of actions are performed.<br>
 *  <ul>
 * <li>The logLevel for this Logger is checked against the level set
 * in the log call (as the level parameter). If this is greater than the
 * Logger's LogLevel the call returns immediately - this is a cheap test
 * and prevents a call to the more expensive filtering.
 * <li> If the above test passes, the more expensive filter test is
 * performed to test against the more complex conditions.
 * <li> If the useGlobalHandlers condition is set then the record is
 * tested against the enablement conditions of the set of global handlers
 * which may be passed the record to publish
 * <li> The record is then tested against the enablement conditions of
 * any registered handlers for this Logger which may be passed the record 
 * to publish.
 * </ul>
 */  
public class Logger implements Logging {

    /** Sequence number for this logger.*/
    public int seqno;
    
    /** Name/id of this Logger. This may be either a name chosen to be used
     * over the various classes of an application i.e. a general name such as
     * <b>TRACE</b> or <b>ADMIN</b> or may be the name of a class such as
     * <b>com.abc.Thing</b> or <b>SpecialUtility</b> The latter form is 
     * preferred for general utility classes which may be used across multiple
     * applications so that the application can at least guess the id of the
     * utility's Logger if it has one.*/  
    protected String name;
    
    /** Holds the set of LogHandlers for this Logger.*/
    protected Set handlers;

    /** A LogFilter attached to this Logger.*/  
    protected LogFilter filter;

    /** The current level of logging for this Logger.*/
    protected int logLevel;
    
    /** Determines whether this Logger should use the GlobalHandlers.*/
    protected boolean useGlobalHandlers;

    /** Provides the timestanp for the log records.*/
    protected TimeProvider timeProvider;

    protected String channelID;

    /** Create a Logger with the specified name. You should <b>never</b> call
     * this constructor directly - <b>always</b> use the LogManager factory
     * method LogManager.getLogger(String name) as this will check to see if
     * the Logger has already been instantiated and either return it or create
     * a new one and register it. - An unregistered Logger cannot be retrieved
     * from any other class. The Logger is initialized with LogLevel OFF and
     * globalHandlers disabled.*/  
    protected Logger(String name) {
	this.name = name;
	handlers = Collections.synchronizedSet(new HashSet());
	logLevel = OFF;
	useGlobalHandlers = false;
	seqno = 0;
	timeProvider = new SystemTimeProvider();
    }
    
    /** Conveniance method to create a named Logger and register with the LogManager.
     * @param name The name / id of the Logger.*/
    public static Logger getLogger(String name) {
	return LogManager.getLogger(name);
    }

    /** Add the specifed handler to the set of handlers which
     * are notified when this Logger creates a LogRecord.
     * @param handler The handler to add.
     */  
    public void addHandler(LogHandler handler) {
	handlers.add(handler);
    }
    
    /** Remove a handler from the set of handlers.
     * @param handler The handler to remove.
     */  
    public void removeHandler(LogHandler handler) {
	if (handlers.contains(handler))
	    handlers.remove(handler);
    }

    /**
     * Get the handlers associated with this logger.
     * The resulting array includes all Handlers set with addHandler, but excludes global
     * Handlers.
     * @return An array of all registered Handlers.
     */
    public LogHandler[] getHandlers()
    {
	Object objectList[] = null;
	LogHandler handlerList[] = null;
	Object o = null;
	LogHandler handler = null;

	objectList = (handlers.toArray());
	handlerList = new LogHandler[objectList.length];
	for(int i = 0;i < objectList.length;i++)
	{
	    o = objectList[i];
	    handler = (LogHandler)(o);
	    handlerList[i] = handler;
	}
	return handlerList;
    }

    /** Set the filter for this Logger.
     * @param filter The filter to apply to this Logger.*/  
    public void setFilter(LogFilter filter) { this.filter = filter; }
    
    /** @return The filter applied to records from this Logger.*/  
    public LogFilter getFilter() { return filter; }
    
    /** Set the LogLevel for this Logger. All LogRecords with level
     * greater than this will be rejected immediately by this Logger.
     * @param logLevel The level above which records will be rejected.
     */  
    public void setLogLevel(int logLevel) { this.logLevel = logLevel; }
    
    /** @return The logLevel set for this Logger.*/  
    public int getLogLevel() { return logLevel; }
    
    /** Set to determine whether this Logger should use the GlobalHandlers.*/
    public void setUseGlobalHandlers(boolean globalHandlers) { this.useGlobalHandlers = globalHandlers; }
 
    /** Determines whether this Logger should use the GlobalHandlers.*/
    public boolean getUseGlobalHandlers() { return useGlobalHandlers; }

    /** Sets the TimeProvider.*/
    public void setTimeProvider(TimeProvider tp) { this.timeProvider = tp; }

    /** @return The name/id of this Logger.*/  
    public String getName() {return name; }
    
    public void setChannelID(String channelID) { this.channelID = channelID;}

    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message. 
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public void log(int level, String message) {
	log("", level, "-", "-", "-", message, null, null);
    }

    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param exception An Exception to log.  
     */   
    public void log(int level, Exception e) {
	log("", level, "-", "-", "-", "", null, e);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param params An array of parameters to log.
     */   
    public void log(int level, Object[] params) {
	log("", level, "-", "-", "-", "", params, null);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param exception An Exception to log.  
     */    
    public void log(int level, String message, Exception e) {
	log("", level, "-", "-", "-", message, null, e);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public void log(String cat, int level, String message) {
	log(cat, level, "-", "-", "-", message, null, null);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param exception An Exception to log.  
     */   
    public void log(String cat, int level, String message, Exception e) {
	log(cat, level, "-", "-", "-", message, null, e);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public void log(int level, String clazz, String message) {
	log("", level, clazz, "-", "-", message, null, null);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */     
    public void log(String cat, int level, String clazz, String message) {
	log(cat, level, clazz, "-", "-", message, null, null);
    }

    /** */   /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param source The id of the object which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     **/     
    public void log(int level, String clazz, String source, String message) {
	log("", level, clazz, source, "-", message, null, null);
    }

    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param source The id of the object which originated this message.
     * @param method The method which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param params An array of parameters to log.
     * @param exception An Exception to log.  
     */      
    public void log(String cat, int level, String clazz, String source, String message) {
	log(cat, level, clazz, source, "-", message, null, null);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param source The id of the object which originated this message.
     * @param method The method which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public void log(int level, String clazz, String source, String method, String message) {
	log("", level, clazz, source, method, message, null, null);
    }
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param source The id of the object which originated this message.
     * @param method The method which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public void log(String cat, int level, String clazz, String source, String method, String message) {
	log(cat, level, clazz, source, method, message, null, null);
    }    
    
    /** Generate a LogRecord with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param clazz The class which originated this message.
     * @param source The id of the object which originated this message.
     * @param method The method which originated this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param params An array of parameters to log.
     * @param exception An Exception to log.  
     */   
    public void log(String cat, int level, String clazz, String source, 
		    String method, String message, Object[] params, Exception exception) {	
	// Cheap test.
	if (logLevel != ALL)
	    if (level > logLevel) return;
	seqno++;
	
	LogRecord record = new LogRecord(level, message);
	record.setChannelID(channelID);
	record.setCat(cat);
	record.setLoggerName(name);
	record.setClazz(clazz);
	record.setSource(source);
	record.setMethod(method);
	record.setThread(Thread.currentThread().getName());
	record.setTime(timeProvider.getTime());
	record.setSeqno(seqno); 
	record.setParams(params);
	record.setException(exception);
	
	// More expensive test.
	if (!isLoggable(record)) return;
	
	LogHandler handler = null;
	
	Iterator it = null;
	// Iterate over global handlers.
	if (useGlobalHandlers) {
	    if (LogManager.getGlobalLogLevel() == ALL ||
		level <= LogManager.getGlobalLogLevel()) {
		it = LogManager.getGlobalhandlers();
		while (it.hasNext()) {
		    handler = (LogHandler)it.next();
		    // The handlers may have filters also.
		    if (handler.isLoggable(record))
			handler.publish(record);
		}
	    }
	}
	
	// Iterate over local handlers.
	it = handlers.iterator();
	while (it.hasNext()) {
	    handler = (LogHandler)it.next();
	    // The handlers may have filters also.
	    if (handler.isLoggable(record))
		handler.publish(record);
	}
    }
    
    /** Dump an Exception stack trace to this logger.
     * @param level The level of this message.
     * @param exception An Exception to dump to the log.  
     */
    public void dumpStack(int level, Exception exception) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintWriter w = new PrintWriter(baos);
	exception.printStackTrace(w);
	w.close();
	w = null;
	log(EXCEPTION, level, baos.toString());
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message. 
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */    
    public static void slog(int level, String message) {
	LogManager.getLogger().log("", level, "-", "-", "-", message, null, null);
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param exception An Exception to log.  
     */   
    public static void slog(int level, Exception e) {
	LogManager.getLogger().log("", level, "-", "-", "-", "", null, e);
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param params An array of parameters to log.
     */   
    public static void slog(int level, Object[] params) {
	LogManager.getLogger().log("", level, "-", "-", "-", "", params, null);
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param exception An Exception to log.  
     */    
    public static void slog(int level, String message, Exception e) {
	LogManager.getLogger().log("", level, "-", "-", "-", message, null, e);
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     */   
    public static void slog(String cat, int level, String message) {
	LogManager.getLogger().log(cat, level, "-", "-", "-", message, null, null);
    }
    
    /** Generate a LogRecord for the default Logger with the supplied parameters and
     * hand off to any handlers to format and publish.
     * @param cat The category ident for this message.
     * @param level The level of this message.
     * @param message The actual message. May contain formatting information.    
     * to be parsed by a LogFormatter.
     * @param exception An Exception to log.  
     */  
    public static void slog(String cat, int level, String message, Exception e) {
	LogManager.getLogger().log(cat, level, "-", "-", "-", message, null, e);
    }
    
    
    /** @return True if no filter is specified otherwise whatever the
     * filter lets through.*/
    protected boolean isLoggable(LogRecord record) {
	if (filter == null) return true;
	return filter.isLoggable(record);
    }
    
    /** Close all the Logger's LogHandlers.*/ 
    public void close() {
	LogHandler handler = null;
	
	Iterator it = handlers.iterator();
	while (it.hasNext()) {
	    handler = (LogHandler)it.next();
	    handler.close();
	}
    }

    /** Configure the logger from a config file. For now this just sets the
     * logLevel at startup - others will be added.
     * @param Properties The set of config Properties, previously loaded from a file.
     */
    public void configure(Properties config) {
	try {
	    logLevel = Integer.parseInt(config.getProperty("logger."+name+".level", "0"));	   
	} catch (NumberFormatException e) {
	    System.err.println("Logger: "+name+" Error parsing logLevel: "+e);
	}
    }
    
}



