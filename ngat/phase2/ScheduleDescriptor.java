package ngat.phase2;


/**
 * Holds data passed up through the database tree when the RCS makes a SCHEDULE transaction 
 * This object holds the next schedulable group if any available and other relevant data.
 * Some of the data is packed in by the OSS after running schedule.
 *
 * $Id: ScheduleDescriptor.java,v 1.2 2000-11-23 11:41:55 snf Exp $
 *
 */
public class ScheduleDescriptor {

    /** The highest scoring (best) moveable Group or next Fixed Group in the database, 
     * satisfying the current constraints. */
    Group group;
    
    /** The score attached to the best Group. */
    float score;

    /** The calculated (i.e. estimated) execution time of this Group. */
    long execTime;

    /** The nominal execution time for this Group. */
    long nominalTime;
    
    /** A time constraint on this Group forced by some condition about to occur
     * e.g. moonrise, start of evening twilight. */
    long latestTime;
    
    /** Create a ScheduleDescriptor using the supplied Group, score, time.
     * @param group The highest scoring Group found so far.
     * @param score the score attanied by the group.
     * @param execTime The expected execution time for this group.
     */
    public ScheduleDescriptor(Group group, float score, long execTime) {
	this.group = group;
	this.score = score;
	this.execTime = execTime;
	this.latestTime = -1;
	this.nominalTime = -1;
    }
    
    /** Get reference to the best Group. May be null if no Groups satisfy current constraints. */
    public Group getGroup() { return group;}
 
    /** Set reference to best Group. */
    public void setGroup(Group group) { this.group = group;}

    /** Get the value of the best Group's score. May be zero if no Groups satisfy current constraints. */
    public float getScore() { return score;}
    /** Set the score of the best Group. This does not set any fields in the Group itself. */
    public void setScore(float score) { this.score = score;}

    /** Get the estimated execution time of the best Group. */
    public long getExecTime() { return execTime;}
    
    /** Set the execution time for the best Group. */
    public void setExecTime(long execTime) { this.execTime = execTime;}

    /** Get the latest time by which this Group must be completed. */
    public long getLatestTime() { return latestTime;}
   
    /** Set the latest time by which this Group must be completed. */
    public void setLatestTime(long latestTime) { this.latestTime = latestTime;}
    
    /** Get the nominal time for this Group. */
    public long getNominalTime() { return latestTime;}
    
    /** Set the nominal time for this Group. */
    public void setNominalTime(long latestTime) { this.latestTime = latestTime;}

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/09/20 16:48:12  snf
/** Initial revision
/** */







