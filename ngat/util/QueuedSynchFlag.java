package ngat.util;

import java.util.Vector;

/**
 * Implements a synchronization lock which allows a single reader and multiple writer
 * and observer Threads to queue for access to an Object. The reader is always put to
 * the head of the queue, writers and observers are placed in arrival order. A Thread
 * which wishes to access an Object locked with this Mutex must call getSynchFlag() to
 * acquire the lock, then freeSynchFlag() to release it, preferrably in a finally{}
 * clause to ensure it is always released e.g.
 * <pre>
 *    try {
 *        lock.getSynchFlag();
 *        // do something with the object.
 *    } catch (VariousExceptions) { 
 *        // Recovery actions.
 *    } finally {
 *        lock.freeSynchFlag();
 *    }
 * </pre>
 *
 * $Id$
 *
 */
public class QueuedSynchFlag extends SynchFlag {

    /** Indicates that a Thread is a Reader.*/
    public static final int READER = 1;
 
    /** Indicates that a Thread is a Writer.*/
    public static final int WRITER = 2;
  
    /** Indicates that a Thread is a just Observing.*/
    public static final int OBSERVER = 3;

    /** Holds the queue of Threads. */
    protected Vector waiters;

    /** True if a Reader Thread is currently attached to the waiters queue. */
    protected boolean serverIsAttached;

    /** Construct an instance of QueuedSynchFlag with no Threads waiting. 
     * @param size The initial size of the waiters Thread queue. */
    public QueuedSynchFlag(int size) {
	super();
	waiters = new Vector(size);
       	serverIsAttached = false;

    }

    /** Join the queue of Threads waiting to acquire the Mutex lock.
     * A Thread can acquire the Mutex multiple times e.g. from inside
     * cascaded or recursive method calls. These locks will be released
     * correctly.
     * @param type The Thread can be READER, WRITER or OBSERVER. */
    public synchronized void getSynchFlag(int type) {

	Thread me = Thread.currentThread();
	
	if (me == flag) { // already got lock..
	    count++;
	    return;
	}

	if (type == READER) {
	    waiters.insertElementAt(me, 0); // put the reader first.
	    serverIsAttached = true;
	} else {
	    if (type == WRITER || type == OBSERVER){
		waiters.addElement(me); // put writers in arrival order.
	    }
	}

	// This code is executed when notifyAll() is called
	// to allow waiters to check whether they are at
	// the head of the queue.

	while ((Thread)waiters.elementAt(0) != me){

	    try {
		wait(); // The calling thread [me] waits..
	    } catch (Exception e) {}
	}

	// Entered only when notifyAll() has been called by the current owner.
	// and [me] has reached head of queue..

	flag = me; // Actually make [me] the flag owner..
	count = 0; // Set the count (for nested calls)..
    }

    /** Release the Mutex lock and leave the queue of waiters, or release
     * one level of locking if multiple locks held.
     * @param type The Thread can be READER, WRITER or OBSERVER. */
    public synchronized void freeSynchFlag(int type) {
	
	if (Thread.currentThread() != flag) 
	    throw new IllegalArgumentException ("SynchFlag not held");

	// Thread is owner and at last lock level.

	if (count == 0) {	
	    if (type == READER) {
		waiters.removeElementAt(0);
		serverIsAttached = false;
	    } else {
		if (serverIsAttached) {
		    waiters.removeElementAt(1);
		} else {
		    waiters.removeElementAt(0);
		}
	    }
	    notifyAll();
	    flag = null;
	} else count--; // still the owner, release one level of lock.
    }

    public synchronized boolean tryGetSynchFlag() {
	if (waiters.size() != 0 && flag != Thread.currentThread()) 
	    return false;
	getSynchFlag();
	return true;
    }

}

/** $Log: not supported by cvs2svn $ */
