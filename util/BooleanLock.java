package ngat.util;


/** Encapsulates  a boolean to allow Threads to signal changes
 * of state and allow one thread to block waiting for the other to
 * signal it.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/BooleanLock.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public class BooleanLock {
    
    /** The lock variable. This is the value whose changes are waited on
     * by any threads using the BooleanLock facility.*/
    private boolean value;
    
    /** Create a BooleanLock with the specified initial state.
     * @param initValue The initial state of the lock variable.
     */
    public BooleanLock(boolean initValue) {
	value = initValue;
    }
    
    /** Create a BooleanLock with the initial state set to false.*/
    public BooleanLock() {
	this(false);
    }
    
    /** Sets the value of the lock variable and notifies any threads waiting
     * on a state change.
     * @param newValue The new value of the lock variable.
     */
    public synchronized void setValue(boolean newValue) {
	if (value != newValue) {
	    value = newValue;
	    notifyAll();
	}
    }
    
    /** The calling thread waits until it has exclusive access to the lock
     * and the lock value is false. Calls setValue() to notify any other threads
     * waiting on the state change.
     * @param timeout The maximum period to wait for (ms). 
     * @return True if we reached the specified state, False if we just timedout.
     */
    public synchronized boolean waitToSetTrue(long timeout) 
	throws InterruptedException {
	boolean success = waitUntilFalse(timeout);
	if (success)
	    setValue(true);
	return success;
    }

    /** The calling thread waits until it has exclusive access to the lock
     * and the lock value is true. Calls setValue() to notify any other threads
     * waiting on the state change.
     * @param timeout The maximum period to wait for (ms). 
     * @return True if we reached the specified state, False if we just timedout.
      */
    public synchronized boolean waitToSetFalse(long timeout)
	throws InterruptedException {
	boolean success = waitUntilTrue(timeout);
	if (success)
	    setValue(false);
	return success;
    }

    /** Returns the state of the lock variable.
     * @return True if the lock variable is set true.
     */
    public synchronized boolean isTrue() {
	return value;
    }

    /** Returns the state of the lock variable.
     * @return True if the lock variable is NOT set true.
     */
    public synchronized boolean isFalse() {
	return ! value;
    }
    
    /** Wait until the lock state is true but only until timeout.
     * @param timeout The maximum period to wait for (ms).
     * @return True if we reached the specified state, False if we just timedout.
     */
    public synchronized boolean waitUntilTrue(long timeout)
	throws InterruptedException {
	return waitUntilStateIs(true, timeout);
    }

    /** Wait until the lock state is false but only until timeout.
     * @param timeout The maximum period to wait for (ms).
     * @return True if we reached the specified state, False if we just timedout.
     */
    public synchronized boolean waitUntilFalse(long timeout)
	throws InterruptedException {
	return waitUntilStateIs(false, timeout);
    }
    
    /** Wait until the specified state is reached but only until timeout.
     * @param state The state we want to achieve.
     * @param timeout The maximum period to wait for (ms). If set to 0 (zero)
     * the blocked Thread waits forever or until interrupted.
     * @return True if we reached the specified state, False if we just timedout.
     */
    public synchronized boolean waitUntilStateIs(boolean state, long timeout) 
	throws InterruptedException {
	// timeout = 0 -> wait forever.
	if (timeout == 0L) {
	    while (value != state) {
		wait();
	    }
	    return true;
	}

	// Set the time left to wait.
	long endTime = System.currentTimeMillis() + timeout;
	long remaining = timeout;

	while ( (value != state) && (remaining > 0L) ) {
	    wait(remaining);
	    // Woke up so check if there is time left.
	    remaining = endTime - System.currentTimeMillis();
	}
	// We may just have timed-out calc return value.
	return (value == state);
    }

}

/** $Log: not supported by cvs2svn $ */
