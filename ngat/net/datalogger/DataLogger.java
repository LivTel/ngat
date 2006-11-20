package ngat.net.datalogger;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.util.*;

/** A DataLogger receives data updates via an incoming UDP stream.
 * Handlers can be registered to deal with the message objects. 
 * Each handler can register for a specific class of object - this
 * should be the base class for any subclasses it should handle.
 */
public class DataLogger {
    
    /** Data handlers.*/
    protected Vector listeners;

    /** Despatches update events to handlers.*/
    protected DataDespatchThread despatcher;

    /** Blocking cache mechanism.*/
    protected BlockingQueue cache;

    /** Create a DataLogger.*/
    public DataLogger() {

	listeners  = new Vector();

	cache      = new BlockingQueue();

	despatcher = new DataDespatchThread(this);

    }

    /** Start the receiver and despatcher threads.*/
    public void start() {

	despatcher.start();

    }

    /** Stop the receiver and despatcher threads.*/
    public void stop() {

	despatcher.terminate();
	   
    }

    /** Add an update listener.
     * @param listener The update listener.
     */
    public void addUpdateListener(DataLoggerUpdateListener listener) {

	listeners.add(listener);
	 
    }

    /** Returns an Iterator over the list of listeners.*/
    public Iterator listeners() {
	
	return listeners.iterator();
	
    }
  
    /** Places an object at the tail of the queue.*/
    public void push(Object data) throws InterruptedException {
	
	cache.push(data);
	
    }
    
    /** Returns without removal, the data at the head of the queue.*/
    public Object look() throws InterruptedException {
	
	return cache.peek();
	
    }
    
    /** Returns and removes the data at the head of the queue.*/
    public Object get() throws InterruptedException {
	
	return cache.remove();

    }
    


}
