package ngat.util.logging;

/** TimeProvider which returns system time.*/
public class SystemTimeProvider implements TimeProvider {

    /** Retruns the system time.*/
    public long getTime() { return System.currentTimeMillis(); }

}
