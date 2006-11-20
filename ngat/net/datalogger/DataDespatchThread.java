package ngat.net.datalogger;

import java.util.*;

import ngat.util.*;

public class DataDespatchThread extends ControlThread {
    
    /** The DataLogger to service.*/
    protected DataLogger logger;

    /** Create a DataDespatchThread to service the specified DataLogger.
     * @param logger The DataLogger to service.
     */
    public DataDespatchThread(DataLogger logger) {
	super("Despatcher", true);
	this.logger = logger;
    }

    /** */
    protected void initialise() {}
    
    /** Called repeatedly to check for new data in cache. */
    protected void mainTask() {

	try {
	    Object data = logger.get();
	    //System.err.println("Despatcher:: Got data: "+data);

	    Iterator listeners = logger.listeners();
	    while (listeners.hasNext()) {
		
		DataLoggerUpdateListener dl = (DataLoggerUpdateListener)listeners.next();
		
		dl.dataUpdate(data);

	    }

	} catch (InterruptedException ix) {}

    }
    
    /** Close down. */
    protected void shutdown() {}

}
