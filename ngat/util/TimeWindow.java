package ngat.util;

/** Temporary class for some testing - probably delete after using.*/
public class TimeWindow {
    
    public static final TimeWindow EMPTY_WINDOW = new TimeWindow(0L,0L);

    public long start;

    public long end;

    public TimeWindow(long start, long end) {
	this.start = start;
	this.end = end;
    }

    /** Find the intersect with another window.*/
    public TimeWindow intersect(TimeWindow other) {
	long is = Math.max(start, other.start);
	long ie = Math.min(end, other.end);

	if (is > ie)
	    return EMPTY_WINDOW;

	return new TimeWindow(is,ie);

    }

}
