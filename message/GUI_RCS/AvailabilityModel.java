package ngat.message.GUI_RCS;

import java.rmi.*;
import java.util.*;

public interface AvailabilityModel extends Remote {

    /** Returns true if the specified time (instant) is available for scheduled operations.
     * @param time when we want to check.
     */
    public boolean isAvailable(long time) throws RemoteException;

    /** Returns true if the specified time interval is fully available for scheduled operations.
     * @param start the start of the period of interest.
     * @param end the end of the period of interest.
     */
    public boolean isAvailable(long start, long end) throws RemoteException;
        
    /** Returns a list of times which are UNavailable for scheduled operations
     * between start and end - inclusive/overlapping.
     * @param start the start of the period of interest.
     * @param end the end of the period of interest.
     */
    public List getUnavailableTimes(long start, long end) throws RemoteException;


}
