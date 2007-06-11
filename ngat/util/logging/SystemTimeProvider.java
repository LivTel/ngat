package ngat.util.logging;

/** TimeProvider which returns system time.*/
public class SystemTimeProvider implements TimeProvider {

    /** Returns the system time.*/
    public long getTime() { return System.currentTimeMillis(); }

    /** Returns an empty string.*/
    public String getPrefix() { return "";}

}
