package ngat.util;

/**
 * Used for recording statistical information on objects entered into the 
 * TransactionQueue, may be used for scheduling e.g. monitoring throughput
 * of low order queues to maintain time constraints on server response.
 *
 * $Id: TimeStamp.java,v 1.1 2000-11-21 16:54:10 snf Exp $
 *
 */
public class TimeStamp {

    /** The System time when the stamp was created. */
    protected long start;

    /** Create a TimeStamp with current System time...will be replaced with
     * a time sent by the RCS using CTRL_SetSystemTime_Req(long time) */
    public TimeStamp() {
	start = System.currentTimeMillis();
    }

    /** Get the time delay since this stamp was created (millis). 
     * ## will be modified to return 'OSS_TimeNow - start'. */
    public long getTime() { return (System.currentTimeMillis()-start);}

    /** Get the start time of this Timestamp. */
    public long getTimeStamp() { return start;}
}

/** $Log: not supported by cvs2svn $ */
