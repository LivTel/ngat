package ngat.util.logging;

/** Implementation of TimeProvider with which time can be modified.*/
public class DefaultMutableTimeProvider implements TimeProvider {

    /** Records time.*/
    long time;

    /** Create a DefaultMutableTimeProvider initialized to start time.*/
    public DefaultMutableTimeProvider(long start) {
	time = start;
    }

    /** Set the time.*/
    public void setTime(long t) { this.time = t;}

    /** Returms the last set time.*/
    public long getTime() { return time; }

}
