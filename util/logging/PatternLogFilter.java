package ngat.util.logging;

import java.util.*;

/** A LogFilter which attempts to match a LogRecord against a set of
 * specifed LogFilterPatterns. A records is allowed if it matches any
 * of the inclusive patterns or fails to match any of the exclusive
 * patterns - i.e. the patterns are OR'ed.*/
public class PatternLogFilter implements LogFilter {

    protected Set patterns;

    public PatternLogFilter() {
	patterns = Collections.synchronizedSet(new HashSet());
    }
    
    public void addPattern(LogFilterPattern pattern) {
	patterns.add(pattern);
    }

    /** If any pattern is Inclusive and the record matches then the
     * record is loggable. Alternatively if any pattern is Exclusive
     * and the record doen't matche it then the record is loggable. 
     * Finally, having failed to match any patterns the record is 
     * not loggable.*/
    public boolean isLoggable(LogRecord record) {
	
	LogFilterPattern pattern = null;
	
	Iterator it = patterns.iterator();
	while (it.hasNext()) {
	    pattern = (LogFilterPattern)it.next();
	    if (pattern.isInclusive()) {
		if ( pattern.match(record) ) return true;
	    } else {
		if ( !pattern.match(record) ) return true;
	    }
	}
	return false;
    }

}
