package ngat.net;

import ngat.util.*;
import java.io.InterruptedIOException;

/** A buffer for handing off objects between threads. This is
 * intended to be an efficient mechanism to replace Piped I/O Streams
 * when only single or small numbers of objects are to be
 * transferred between threads.
 * <br><br>
 * $Id$
 */
public class PriorityQueueBuffer {
    
    /** Indicates the top (highest) level queue (i.e. 0).*/
    public static final int MAX_PRIORITY = 0;

    /** Records the number of queue priority levels.*/
    protected int numLevels;
    
    /** Underlying storage buffer.*/
    protected LinkedQueue queue[];

    /** Counts the number of elements in all queues.*/
    protected volatile int count;

    /** Create a PriorityQueueBuffer with the specified no of levels.
     * @param numLevels The no of levels.*/
    public PriorityQueueBuffer(int numLevels) {
	this.numLevels = (numLevels > 0 ? numLevels : 1);
	queue = new LinkedQueue[numLevels];
	for (int i = 0; i < numLevels; i++) {
	    queue[i] = new LinkedQueue();
	}
	count = 0;
    }
    
    /** @return The capacity (number of items which can be inserted.*/
    public int getNumLevels() { return numLevels; }
    
    /** @return The size (number of elements) in the buffer.*/
    public synchronized int getSize(int level) { return queue[level].size(); }

    /** @return True if all queues are empty.*/
    public synchronized boolean isEmpty() {
	return (count == 0);
    }

    /** Add a single item to the buffer at the specified level.
     * @param obj The object to insert.*/
    public synchronized void add(int level, Object obj) throws InterruptedException {
	queue[level].enqueue(obj);
	count++;
	notifyAll();
    }

    /** Add a number of items to the buffer.
     * @param list The array of items to add.*/
    public synchronized void addEach(int level, Object[] list) throws InterruptedException {
	for (int i = 0; i < list.length; i++) {
	    add(level, list[i]);
	}
    }

    /** @return One object from the highest available buffer. It may return null if the
     * data in the highest buffer is not properly set.*/
    public synchronized Object remove() throws InterruptedException, QueueEmptyException {
	waitWhileEmpty();
	// Skip over empty buffers to find a non empty one.
	for (int i = 0; i < numLevels; i++) {
	    if (!queue[i].isEmpty()) {
		Object obj = queue[i].dequeue();
		count--;
		notifyAll();
		return obj;
	    }
	}
	return null;
    }
    
    /** Waits only for the specified period to read an Object
     * from the buffer.
     * @param timeout The period to block for (msecs).
     * @return One object from the head of the buffer.
     * Exception InterruptedIOException If the wait timed out.*/
    public synchronized Object remove(long timeout) 
	throws InterruptedException, InterruptedIOException, QueueEmptyException {
	waitWhileEmpty(timeout);
	// Skip over empty buffers to find a non empty one.
	for (int i = 0; i < numLevels; i++) {
	    if (!queue[i].isEmpty()) {
		Object obj = queue[i].dequeue();
		count--;
		notifyAll();
		return obj;
	    }
	}
	return null;
    }

    /** Causes a blocked thread to wait until the buffer is empty but
     * no longer than a specified time.
     * @param timeout The maximum period (msecs) to wait.*/
    public synchronized boolean waitUntilEmpty(long timeout) throws InterruptedException {
	if (timeout == 0l) {
	    waitUntilEmpty();
	    return true;
	}

	long endtime = System.currentTimeMillis() + timeout;
	long remaining = timeout;

	while ( !isEmpty() && (remaining > 0L) ) {
	    wait(remaining);
	    remaining = endtime - System.currentTimeMillis();
	}

	return isEmpty();
    }

    /** Causes a blocked thread to wait until the buffer is empty.*/
    public synchronized void waitUntilEmpty() throws InterruptedException { 
	while ( !isEmpty() ) {
	    wait();
	}
    }

    /** Causes a blocked thread to wait until the buffer is no longer empty.*/
    public synchronized void waitWhileEmpty() throws InterruptedException { 
	while ( isEmpty() ) {
	    wait();
	}
    }

    /** Causes a blocked thread to wait until the buffer is no longer empty
     * but only for specified period. If after this period the buffer is still
     * empty throws a*/
    public synchronized void waitWhileEmpty(long timeout) 
	throws InterruptedException, InterruptedIOException { 
	while ( isEmpty() ) {
	    wait(timeout);
	}
	if (isEmpty())
	    throw new InterruptedIOException("PriorityQueueBuffer timed out waiting:");
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2004/01/15 16:04:53  snf
/** uodated
/**
/** Revision 1.1  2002/02/12 18:52:28  cjm
/** Initial revision
/** */
