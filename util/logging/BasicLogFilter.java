package ngat.util.logging;

import java.util.*;

/** This basic implementation of LogFilter allows through
 * all records which are not specifically excluded by reference
 * to tables of excluded catagories.*/
public class BasicLogFilter implements LogFilter {

    /** The set of classes which are excluded by this filter.*/
    Set classX;

    /** The set of method names which are excluded by this filter.*/
    Set methodX;
    
    /** The set of source ids which are excluded by this filter.*/
    Set sourceX;
    
    /** The set of loggers which are excluded by this filter.*/
    Set loggerX;

    /** Create a BasicLogFilter with empty exclusion sets.*/
    public BasicLogFilter(){
	classX  = Collections.synchronizedSet(new HashSet());
	methodX = Collections.synchronizedSet(new HashSet());
	sourceX = Collections.synchronizedSet(new HashSet());
	loggerX = Collections.synchronizedSet(new HashSet());
    }

    /** Add the supplied class to the list of those excluded
     * by this filter.
     * @param clazz The name of the class to exclude.
     */
    public void excludeClass(String clazz) { classX.add(clazz); }

    /** If the supplied class is currently excluded by this filter then
     * stop excluding it now.
     * @param clazz The name of the class to stop excluding.
     */
    public void includeClass(String clazz) { classX.remove(clazz); }

    /** Add the supplied method to the list of those excluded
     * by this filter.
     * @param method The name of the method to exclude.
     */
    public void excludeMethod(String method) { methodX.add(method); }

    /** If the supplied method is currently excluded by this filter then
     * stop excluding it now.
     * @param method The name of the method to stop excluding.
     */
    public void includeMethod(String method) { methodX.remove(method); }
    
    /** Add the supplied source to the list of those excluded
     * by this filter.
     * @param source The name of the source to exclude.*/
    public void excludeSource(String source) { sourceX.add(source); }

    /** If the supplied method is currently excluded by this filter then
     * stop excluding it now.
     * @param source The name of the source to stop excluding.
     */
    public void includeSource(String source) { sourceX.remove(source); }
    
    /** Add the supplied logger to the list of those excluded
     * by this filter.
     * @param logger The name of the logger to exclude.
     */
    public void excludeLogger(String logger) { loggerX.add(logger); }
    
    /** If the supplied logger is currently excluded by this filter then
     * stop excluding it now.
     * @param logger The name of the logger to stop excluding.
     */
    public void includeLogger(String logger) { loggerX.remove(logger); }

    /** */
    public boolean isLoggable(LogRecord record) {
	if (classX.contains(record.getClazz())) return false;
	if (methodX.contains(record.getMethod())) return false;
	if (sourceX.contains(record.getSource())) return false;
	if (loggerX.contains(record.getLoggerName())) return false;
	return true;
    }
    
}
