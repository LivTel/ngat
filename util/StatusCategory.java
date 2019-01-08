package ngat.util;

/** Implementors should contain hooks to suitable status repositories.*/
public interface StatusCategory {

    /** Implementors should return status identified by the supplied key or throw 
     * an IllegalArgumentException if no such status exists. This method is
     * intended for descriptive (String) status variables.
     */
    public String getStatusEntryId(String key) throws IllegalArgumentException;
     
    /** Implementors should return status identified by the supplied key or throw 
     * an IllegalArgumentException if no such status exists. This method is
     * intended for continuous status variables.
     */
    public int getStatusEntryInt(String key) throws IllegalArgumentException;
     
    /** Implementors should return status identified by the supplied key or throw 
     * an IllegalArgumentException if no such status exists. This method is
     * intended for discrete status variables.
     */
    public double getStatusEntryDouble(String key) throws IllegalArgumentException;

    /** Implementors should return status identified by the supplied key or throw 
     * an IllegalArgumentException if no such status exists. No type conversion 
     * should be attempted.
     */
    public String getStatusEntryRaw(String key) throws IllegalArgumentException;
       
    /** Implementors should return the timestamp of the latest readings.*/
    public long getTimeStamp();


}
