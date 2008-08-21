package ngat.oss.scheduling;

public class ScheduleItem {

    /** The Group this item describes.*/
    private Group group;
    
    /** The Group's objective score.*/
    private double score;

    /** Earliest time this Group can be started.*/
    private long earliestStartTime;

    /** Latest time this Group can be completed by.*/
    private long latestCompletionTime;

    /** Nominal time this Group is expected to take to execute.*/
    private long nominalExecutionTime;

    /** Create an empty ScheduleItem.*/
    public ScheduleItem() {}

    /** Create a ScheduleItem with given parameters.
     * @param group The Group this item describes.
     * @param score The Group's objective score.
     * @param est   Earliest time this Group can be started.
     * @param lct   Latest time this Group can be completed by.
     * @param net   Nominal time this Group is expected to take to execute.
     */
    public ScheduleItem(Group group, double score, long est, long lct, long net) {
	this.group = group;
	this.score = score;
	this.earliestStartTime    = est;
	this.latestCompletionTime = lct;
	this.nominalExecutionTime = net;
    }

    /** Set the Group.*/
    public void setGroup(Group group) { this.group = group; }

    /** Return the Group.*/
    public Group getGroup() { return group;}

    /** Set the score.*/
    public void setScore(double score) { this.score = score; }

    /** Return the score.*/
    public double getScore() { return score; }

    /** Set the earliest time this Group can be started.*/
    public void setEarliestStartTime(long t) { this.earliestStartTime = t; }

    /** Return the earliest time this Group can be started.*/
    public long getEarliestStartTime() { return earliestStartTime; }

    /** Set the latest time this Group can be completed by.*/
    public void setLatestCompletionTime(long t) { this.latestCompletionTime = t; }

    /** Return the latest time this Group can be completed by.*/
    public long getLatestCompletionTime() { return latestCompletionTime; }

    /** Set the nominal time this Group is expected to take to execute.*/
    public void setNominalExecutionTime(long t) { this.nominalExecutionTime = t; }

    /** Return the nominal time this Group is expected to take to execute.*/
    public long getNominalExecutionTime() { return nominalExecutionTime; }

    /** Returns a readable description of this ScheduleItem.*/
    public String toString() {
	return "ScheduleItem:"+group.getFullPath()+
	    ",score="+score+
	    ",est="+earliestStartTime+
	    ",lct="+latestCompletionTime+
	    ",net="+nominalExecutionTime;
    }

}
