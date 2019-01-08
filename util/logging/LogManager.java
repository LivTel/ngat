package ngat.util.logging;

import java.io.*;
import java.util.*;
import javax.swing.event.*;

/** Global Logging manager. Holds configuration information for
 * all Loggers and can retrieve references to any named Loggers.
 */
public class LogManager implements Logging {

    protected static Logger anonymousLogger;

    protected static LogHandler globalConsoleLogHandler;

    protected static Map registry;

    protected static Map handlers;

    protected static Set globalHandlers;

    protected static int globalLogLevel;

    protected static EventListenerList listenerList;

    protected static LogManagerEvent logManagerEvent;
    
    static {
	registry = Collections.synchronizedMap(new HashMap());
	handlers = Collections.synchronizedMap(new HashMap());
	globalHandlers = Collections.synchronizedSet(new HashSet());
	listenerList = new EventListenerList();
	anonymousLogger = new Logger("ANON");
	registry.put("ANON", anonymousLogger);
	globalConsoleLogHandler = new ConsoleLogHandler(new SimpleLogFormatter());
	globalLogLevel = ALL;
	globalConsoleLogHandler.setLogLevel(ALL);
	globalHandlers.add(globalConsoleLogHandler);
    }

    /** Create a default Logger using SimpleLogFormatter and writing
     * output to a file in the user's home directory if possible
     * otherwise to the console via System.err .*/
    public static Logger createDefaultLogger(String name) {
	Logger logger = new Logger(name);
	LogFormatter formatter = new SimpleLogFormatter();
	LogHandler handler = null;
	try {
	    File file = new File(System.getProperty("user.home"), name+".log");
	    handler = new FileLogHandler(file.getPath(), formatter);
	} catch (FileNotFoundException e) {
	    handler = new ConsoleLogHandler(formatter);
	}
	logger.addHandler(handler);
	return logger;
    }
    
    /** @return The anonymous (un-named) Logger with default settings.*/
    public static Logger getAnonymousLogger() {
	return anonymousLogger;
    }

    /** @return The anonymous (un-named) Logger with default settings.*/
    public static Logger getLogger() {
	return anonymousLogger;
    }

    /** If a Logger with the specified name exists (is registered) this is
     * returned, otherwise a Logger with this name is created 
     * (with default settings), registered and returned. If the name is null
     * or empty, the Anonymous Logger ("ANON") is returned.
     * @param name The Logger's name.
     * @return An existing or new Logger.
     */
    public static Logger getLogger(String name) {
	if (name == null || name.equals("")) return anonymousLogger;
	if (registry.containsKey(name)) return (Logger)registry.get(name);
	Logger logger = new Logger(name);
	registry.put(name, logger);
	fireLoggerAdded(logger);
	return logger;
    }   
    
    /** If a Logger with the name of the specified Class exists (is registered)
     * this is returned, otherwise a Logger with this name is created 
     * (with default settings), registered and returned.
     * @param clazz The Class whose fully qualified name is used for
     * the Logger's name.
     * @return An existing or new Logger.
     */
    public static Logger getLogger(Class clazz) {
	return getLogger(clazz.getName());
    }
    
    /** If a Logger with the name of the specified object's fully qualified Class 
     * exists (is registered) this is returned, otherwise a Logger with this name 
     * is created (with default settings), registered and returned.
     * @param source The object whose fully qualified class name is used for
     * the Logger's name.
     * @return An existing or new Logger.
     */
    public static Logger getLogger(Object source) {
	return getLogger(source.getClass());
    }

    /** Returns an Iterator over the Loggers managed by the LogManager.
     * @return An Iterator over the managed Loggers.*/
    public static Iterator listLoggers() { 
	return registry.values().iterator();
    }

    /** Register a LogHandler. It can then be retrieved by getHandler(String).
     * @param handler The handler to register.
     * @param name The <i>application-unique</i> name/ID for this handler.
     */
    public static void registerHandler( String name, LogHandler handler) {
	handlers.put(name, handler);
    }
	
    /** Register a LogHandler using its own ID.
     * @param handler The handler to register.
     */
    public static void registerHandler(LogHandler handler) {
	registerHandler(handler.getName(), handler);
    }

    /** Returns the LogHandler identified with the specified ID. If no
     * handler with this name has been registered, returns null.
     * @param name The <i>application-unique</i> name/ID of the handler to
     * locate and return.
     */
    public static LogHandler getHandler(String name) {
	if (handlers.containsKey(name))
	    return (LogHandler)handlers.get(name);
	return null;
    }

    /** Add a global handler.
     * @param handler The new handler to add to the global set.*/
    public static void addGlobalHandler(LogHandler handler) {
	globalHandlers.add(handler);
    }

    /** Remove a global handler.
     * @param handler The handler to remove from the global set.*/
    public static void removeGlobalHandler(LogHandler handler) {
	globalHandlers.remove(handler);
    }

    /** Remove ALL global handlers.*/
    public static void removeAllGlobalHandlers() {
	globalHandlers.clear();
    }

    /** @return An iterator over the set of global handlers.*/
    public static Iterator getGlobalhandlers() {
	return globalHandlers.iterator();
    }

    /** Set the global log level - used by all global handlers.
     * @param level The level to apply to all global handlers.*/
    public static void setGlobalLogLevel(int level) {
	globalLogLevel = level;
    }

    /** @return The global log level - used by global handlers.*/
    public static int getGlobalLogLevel() { return globalLogLevel; }



    /** Configure all Loggers from a config file. For now this just sets the
     * logLevels at startup - others will be added.
     * @param Properties The set of config Properties, previously loaded from a file.
     */
    public static void configureLoggers(Properties config) {
	Iterator it = registry.values().iterator();
	while (it.hasNext()) {	 
	    Logger logger = (Logger)it.next(); 	 
	    logger.configure(config);	   
	}
    }


    public static void addLogManagerListener(LogManagerListener l) {
	listenerList.add(LogManagerListener.class, l);
    }
    
    public static void removeLogManagerListener(LogManagerListener l) {
	listenerList.remove(LogManagerListener.class, l);
    }
    
    
    // Notify all listeners that have registered interest for
    // notification on this event type.  The event instance 
    // is lazily created using the parameters passed into 
    // the fire method.
    
    protected static void fireLoggerAdded(Logger logger) {
	// Guaranteed to return a non-null array
	Object[] listeners = listenerList.getListenerList();
	// Process the listeners last to first, notifying
	// those that are interested in this event
	for (int i = listeners.length - 2; i >= 0; i -= 2) {
	    if (listeners[i] == LogManagerListener.class) {
		// Lazily create the event:
		if (logManagerEvent == null)
		    logManagerEvent = new LogManagerEvent(LogManager.class);
		System.err.println("LM: lev. do setlogger: "+logger);
		logManagerEvent.setLogger(logger);
		System.err.println("LM: lev. done setlogger: "+logger);
		((LogManagerListener)listeners[i+1]).loggerAdded(logManagerEvent);
	    }              
	}
    }

    protected static void fireLoggerChanged(Logger logger) {
	// Guaranteed to return a non-null array
	Object[] listeners = listenerList.getListenerList();
	// Process the listeners last to first, notifying
	// those that are interested in this event
	for (int i = listeners.length - 2; i >= 0; i -= 2) {
	    if (listeners[i] == LogManagerListener.class) {
		// Lazily create the event:
		if (logManagerEvent == null) 
		    logManagerEvent = new LogManagerEvent(LogManager.class);
		logManagerEvent.setLogger(logger);
		((LogManagerListener)listeners[i+1]).loggerChanged(logManagerEvent);
	    }              
	}
    }


}

