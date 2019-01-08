package ngat.util.logging;

import java.rmi.*;

public interface LogListener extends Remote {

    public void loggingRecord(LogRecord record) throws RemoteException;

}
