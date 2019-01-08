package ngat.util.logging;

/** Provides time information.*/
public interface TimeProvider {

    /** Implementors should provide the time when called.*/
    public long getTime();

    /** Implementors should return a prefix string.*/
    public String getPrefix();

}
