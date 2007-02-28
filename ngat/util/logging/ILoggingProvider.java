package ngat.util.logging;

import java.rmi.*;
import java.util.*;

public interface ILoggingProvider extends Remote {

    /** Returns a list of logger names available in the target JVM.*/
    public List listLoggers() throws RemoteException;

    /** Returns the current log-level of the specified logger.
     * @param logName The name of the logger.
     */
    public int getLogLevel(String logName)  throws RemoteException;
    
    /** Register an instance of LogListener to receive log messages.
     * @param logName The name of the logger to register against.
     * @param listener An instance of LogListener to receive the log messages.
     */
    public void addLogListener(String logName, LogListener listener) throws RemoteException;

    /** Remove an instance of LogListener from the registered list.
    * @param logName The name of the logger to de-register from.
    * @param listener An instance of LogListener currently receiving the log messages.
    */
    public void removeLogListener(String logName, LogListener listener) throws RemoteException;

}

