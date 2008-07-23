package ngat.net;

import java.io.InterruptedIOException;

/** A buffer for handing off objects between threads. This is
 * intended to be an efficient mechanism to replace Piped I/O Streams
 * when only single or small numbers of objects are to be
 * transferred between threads.
 * <br><br>
 * $Id: SlotBuffer.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class SlotBuffer {

    /** Records the head position of the queue.*/
    protected int head;

    /** Records the tail position of the queue.*/
    protected int tail;
    
    /** Records the size (number of elements) currently in the queue.*/
    protected int size;
    
    /** The capacity of the queue.*/
    protected int capacity;
    
    /** Underlying storage buffer.*/
    protected Object queue[];

    /** Create a SlotBuffer with the specified capacity.
     * @param capcaity The size of the underlying buffer.*/
    public SlotBuffer(int capacity) {
	this.capacity = (capacity > 0 ? capacity : 1);
	queue = new Object[capacity];
	head = 0;
	tail = 0;
	size = 0;
    }

    /** @return The capacity (number of items which can be inserted.*/
    public int getCapacity() { return capacity; }

    /** @return The size (number of elements) in the buffer.*/
    public synchronized int getSize() { return size; }

    /** @return True if the buffer is full.*/
    public synchronized boolean isFull() { 
	return (size == capacity);
    }

    /** @return True if the buffer is empty.*/
    public synchronized boolean isEmpty() {
	return (size == 0);
    }

    /** Add a single item to the buffer but  only hang around so long.
     * @param obj The object to insert.
     * @param timeout How long to hang on for nonfull buffer.
     */
    public synchronized void add(Object obj, long timeout) 
	throws InterruptedException, InterruptedIOException {
	waitWhileFull(timeout);
	queue[head] = obj;
	head = (head + 1) % capacity;
	size++;
	
	notifyAll();
    }

    /** Add a single item to the buffer.
     * @param obj The object to insert.*/
    public synchronized void add(Object obj) throws InterruptedException {
	waitWhileFull();
	queue[head] = obj;
	head = (head + 1) % capacity;
	size++;

	notifyAll();
    }

    /** Add a number of items to the buffer.
     * @param list The array of items to add.*/
    public synchronized void addEach(Object[] list) throws InterruptedException {
	for (int i = 0; i < list.length; i++) {
	    add(list[i]);
	}
    }

    /** @return One object from the head of the buffer.*/
    public synchronized Object remove() throws InterruptedException {
	waitWhileEmpty();
	Object obj = queue[tail];
	queue[tail] = null;
	tail = (tail + 1) % capacity;
	size--;
	
	notifyAll();

	return obj;
    }
    
    /** Waits only for the specified period to read an Object
     * from the buffer.
     * @param timeout The period to block for (msecs).
     * @return One object from the head of the buffer.
     * Exception InterruptedIOException If the wait timed out.*/
    public synchronized Object remove(long timeout) 
	throws InterruptedException, InterruptedIOException {
	waitWhileEmpty(timeout);
	Object obj = queue[tail];
	queue[tail] = null;
	tail = (tail + 1) % capacity;
	size--;
	
	notifyAll();

	return obj;
    }

    /** @return All the items in the buffer.*/
    public synchronized Object[] removeAll() throws InterruptedException {
	Object[] list = new Object[size];
	
	for (int i = 0; i < list.length; i++) {
	    list[i] = remove();
	}

	return list;
    }

    /** @return Waits until at least one item is inserted and removes it.*/
    public synchronized Object[] removeAtLeastOne() throws InterruptedException {
	waitWhileEmpty();
	return removeAll();
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
	    throw new InterruptedIOException("Slotbuffer timed out waiting:");
    }




    /** Causes a blocked thread to wait until the buffer is full.*/
    public synchronized void waitUntilFull() throws InterruptedException { 
	while ( ! isFull() ) {
	    wait();
	}
    }

    /** Causes a blocked thread to wait until the buffer is no longer full.*/
    public synchronized void waitWhileFull() throws InterruptedException { 
	while ( isFull() ) {
	    wait();
	}
    }

    /** Causes a blocked thread to wait until the buffer is no longer full. 
     * but only for specified period. If after this period the buffer is still
     * full throws an  InterruptedException*/
    public synchronized void waitWhileFull(long timeout) throws InterruptedException, InterruptedIOException { 
	while ( isFull() ) {
	    wait(timeout);
	}
	if (isFull())
	    throw new InterruptedIOException("Slotbuffer timed out after "+timeout+" still full while waiting :");
    }
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/12/20 09:44:40  snf
/** Added timed read.
/**
/** Revision 1.1  2000/12/11 16:59:13  snf
/** Initial revision
/** */
