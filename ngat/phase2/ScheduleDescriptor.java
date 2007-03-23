package ngat.phase2;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Holds data passed up through the database tree when the RCS makes a SCHEDULE transaction 
 * This object holds the next schedulable group if any available and other relevant data.
 * Some of the data is packed in by the OSS after running schedule.
 *
 * $Id: ScheduleDescriptor.java,v 1.4 2007-03-23 20:56:42 snf Exp $
 *
 */
public class ScheduleDescriptor implements Serializable {
 
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
    private static SimpleTimeZone   UTC = new SimpleTimeZone(0, "UTC");

    static {
	sdf.setTimeZone(UTC);
    }

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 8646091077658908442L;
    
    /** The highest scoring (best) moveable Group or next Fixed Group in the database, 
     * satisfying the current constraints. */
    protected Group group;
    
    /** The score attached to the best Group. */
    protected double score;
    
    /** True if this Schedule is allowed (i.e. not vetoed).*/
    protected boolean allowed;
    
    /** The calculated (i.e. estimated) execution time of this Group. */
    protected long execTime;
    
    /** The nominal execution time for this Group. This is the time used
     * when updating the Tag and Proposal used fractions.*/
    protected  long nominalTime;
    
    /** A time constraint on this Group forced by some condition about to occur
     * e.g. moonrise, start of evening twilight. This indicates to the RCS the
     * latest time by which the group MUST be completed.*/
    protected long latestTime;
    
    /** Stores the total number of schedulable groups during the current schedule period
     * which are avaiable.*/
    protected int availableGroupCount;

    /** Stores the reason for this schedule failing (if allowed is False).*/
    protected String failureReason;

    // temporary metrics.
    protected Map metrics;


    /** Create a ScheduleDescriptor using the supplied Group, score, time.
     * The descriptor has its allow flag set to false initially.
     * @param group The highest scoring Group found so far.
     * @param score The score attained by the group.
     * @param execTime The expected execution time for this group.
     */
    public ScheduleDescriptor(Group group, double score, long execTime) {
	this.group = group;
	this.score = score;
	this.execTime = execTime;
	this.allowed = false;
	this.latestTime = -1;
	this.nominalTime = -1;
	this.availableGroupCount = 0;
	metrics = new HashMap();
    }
    
    /** Get reference to the best Group. May be null if no Groups satisfy current constraints. */
    public Group   getGroup() { return group;}
 
    /** Set reference to best Group. */
    public void    setGroup(Group group) 
    { this.group = group;}
    
    /** Get the value of the best Group's score. May be zero if no Groups satisfy current constraints. */
    public double  getScore() { return score;}

    /** Set the score of the best Group. This does not set any fields in the Group itself. */
    public void    setScore(double score) 
    { this.score = score;}

    /** Get the status of this Group - true if the Group is NOT vetoed for any reason.*/
    public boolean getAllowed() { return allowed; }

    /** Set the status of this Group to true if the Group is NOT vetoed for any reason.*/
    public void    setAllowed(boolean allowed) 
    { this.allowed = allowed; }

    /** Get the estimated execution time of the best Group. */
    public long    getExecTime() { return execTime;}
    
    /** Set the execution time for the best Group. */
    public void    setExecTime(long execTime) 
    { this.execTime = execTime;}

    /** Get the latest time by which this Group must be completed. */
    public long    getLatestTime() { return latestTime;}
   
    /** Set the latest time by which this Group must be completed. */
    public void    setLatestTime(long latestTime) 
    { this.latestTime = latestTime;}
    
    /** Get the nominal time for this Group. */
    public long    getNominalTime() { return nominalTime;}
    
    /** Set the nominal time for this Group. */
    public void    setNominalTime(long nominalTime) 
    { this.nominalTime = nominalTime;}

    /** Get the count of available Groups.*/
    public int     getAvailableGroupCount() { return availableGroupCount; }
    
    /** Set available Group count.*/
    public void    setAvailableGroupCount(int availableGroupCount) 
    { this.availableGroupCount = availableGroupCount;}

    /** Set the reason for this schedule failing (if allowed is False).*/
    public void setFailureReason(String failureReason) 
    { this.failureReason = failureReason;}

    /** Get the reason for this schedule failing (if allowed is False).*/
    public String getFailureReason() { return failureReason; }

    /** Add a metric.*/
    public void addMetric(String key, double value) {
	metrics.put(key, new Double(value));
    }

    /** Get the value of a metric.*/
    public double getMetric(String key) {
	if (metrics.containsKey(key))
	    return ((Double)metrics.get(key)).doubleValue();
	return 0.0;
    }

    /** Clears out all metric info.*/
    public void clearMetrics() {
	metrics.clear();
    }

    /** Lists metric keys.*/
    public Iterator listMetricKeys() {
	return metrics.keySet().iterator();
    }

    /** Returns a readable string representation.*/
    public String toString() {
	StringBuffer buffer = new StringBuffer("Metrics");
	buffer.append("Group="+(group != null ? group.getFullPath() : "null"));
	buffer.append(","+ (allowed ? "Enabled" : "Vetoed: "+failureReason));
	buffer.append(", Score = "+score); 
	buffer.append(", Exec="+nominalTime);
	buffer.append(", Latest="+sdf.format(new Date(latestTime)));
	buffer.append(", Components=[");
	Iterator is = metrics.keySet().iterator();
	while (is.hasNext()) {
	    String key = (String)is.next();
	    double d   = getMetric(key);
	    buffer.append(key+"="+d);
	    if (is.hasNext())
		buffer.append(", ");
	}
	buffer.append("]");
	return buffer.toString();
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2006/11/23 10:43:05  snf
/** test
/**
/** Revision 1.2  2000/11/23 11:41:55  snf
/** Added doc comments.
/**
/** Revision 1.1  2000/09/20 16:48:12  snf
/** Initial revision
/** */







