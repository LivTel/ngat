package ngat.util.logging;

import java.util.*;

/** A Pattern against which LogRecords can be matched by a LogFilter.
 */
public class LogFilterPattern {
    
    protected Set classes;
    
    protected Set sources;

    protected Set methods;

    protected Set cats;

    protected boolean inclusive;

    /** Create a LogFilterPattern.
     * @param inclusive True if the pattern is inclusive i.e.
     * records must match it.*/
    public  LogFilterPattern(boolean inclusive) {
	classes = Collections.synchronizedSet(new HashSet());
	sources = Collections.synchronizedSet(new HashSet());
	methods = Collections.synchronizedSet(new HashSet());
	cats    = Collections.synchronizedSet(new HashSet());
	this.inclusive = inclusive;
    }

    /** Add a class name to the set of classes to match.
     * @param clazz The name of the class to add.*/
    public void addClass(String clazz) {
	classes.add(clazz);
    }

    /** Add a source id to the set of sources to match.
     * @param source The id of the source to add.*/
    public void addSource(String source) {
	sources.add(source);
    }

    /** Add a method name to the set of methods to match.
     * @param method The name of the method to add.*/
    public void addMethod(String method) {
	methods.add(method);
    }

    /** Add a category to the set of categories to match.
     * @param cat The category to add.*/
    public void addCat(String cat) {
	cats.add(cat);
    }

    /** Test the supplied record against the various sets and
     * decide if it constitutes a match.
     * @param record The LogRecord to match.
     * @return True if the record matches the pattern.*/
    public boolean match(LogRecord record) {
	if ( !classes.isEmpty() && ! classes.contains(record.getClazz())) return false;
	if ( !sources.isEmpty() && ! sources.contains(record.getSource())) return false;
	if ( !methods.isEmpty() && ! methods.contains(record.getMethod())) return false;
	if ( !cats.isEmpty()    && ! cats.contains(record.getCat())) return false;
	return true;
    }
    
    /** @return True if this pattern is inclusive.*/ 
    public boolean isInclusive() { return inclusive; }
    
}


