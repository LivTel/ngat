package ngat.net.datalogger;

/** Interface whcih must be implemented by any classes which are to act
 * as handlers for DataLogging. A DataLoggerUpdateListener may chose to 
 * ignore particualr classes of data if set up to do so.
 * E.g. it is expected that clients could register for specific classes
 * of data rather than receive all data updates.
 */
public interface DataLoggerUpdateListener {

    /** Concrete classes implement a handler for a received data item.*/ 
    public void dataUpdate(Object data);

}
