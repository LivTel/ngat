package ngat.util;

import ngat.util.logging.*;

/**
 * Thread model which can be stopped safely by itself or by other (parent) threads.
 * The thread is designed for one-off or repetitive activity conforming to the behavior
 * defined in the run() template method:-
 * 
 * initialise() 
 * loop(control)
 *  { mainTask() }
 * repeat
 * shutdown().
 */

public abstract class ControlThread extends Thread {

    /** Control variable, used by Thread to determine if it should die. */
    protected volatile boolean canRun;

    /** Object to lock on when testing/setting the control variable. */
    protected Object runLock;

    /** Object to lock on when testing/setting the paused state variable.*/
    protected Object pauseLock;
  
    /** Indicates whether the Thread is paused.*/
    protected volatile boolean paused;

    /** Lock to wait on for pause.*/
    BooleanLock pLock;

    /** Linger timeout, set by linger(long). If 0 , wait forever.*/
    long lingerTimeout;

    /** Determines whether the Thread should run its mainTask() method once only.*/
    protected boolean permanence;

    /** Constant. Thread runs mainTask() once only. */
    public static final boolean TRANSIENT = false;

    /** Constant. Thread can run mainTask() method repeatedly until terminate()
     * is called by another Thread (or this one in its overridden mainTask() method). */
    public static final boolean PERMANENT = true;

    /** Class Logger.*/
    protected Logger logger;

    // Constructors.

    /** Construct a ControlThread which may be TRANSIENT or PERMANENT. */
    public ControlThread(String name, boolean permanent) {
	super(name);
	this.permanence = permanent;
	canRun = false;
	paused = false;
	pLock = new BooleanLock();
	runLock   = new Object();
	pauseLock = new Object();
	logger = LogManager.getLogger(this);
    }

    /** Construct a named TRANSIENT ControlThread. */
    public ControlThread(String name) {
	this(name, TRANSIENT);
    }

    /** Construct a TRANSIENT ControlThread with unspecified name. */
    public ControlThread() {
	this("ControlThread", TRANSIENT);
    }

    // The run() method is inherited but cannot be overridden.
    // Subclasses should override the 3 methods contained in it.

    /** This method cannot be overridden. Calls the following methods in
     * sequence:-
     * <ul>
     *  <li> <b>initialise()</b>.
     *  <li> while (not terminated)
     *  <ul>
     *   <li> if paused then wait till resumed.
     *   <li>  <b>mainTask()</b>.
     *   <li> if TRANSIENT then terminate().
     *  </ul>
     *  <li> repeat
     *  <li>  <b>shutdown(</b>). (even if main methods fail.)
     * </ul>
     * Each of these methods should be overridden to carry out the specific
     * operations required of a subclass.
     */
    public final void run() {
	setRunnable();
	try {
	    initialise();
	    while (canRun()) {
		// Wait for linger period (could be forever !).
		if (isPaused()) {
		    try {
			pLock.waitUntilFalse(lingerTimeout);
		    } catch (InterruptedException e) {
		    }
		}
		mainTask();		
		if (isTransient()) terminate();
	    }
	} finally {
	    shutdown();
	}
    }

    // These methods should be overridden by subclasses to perform the Thread's
    // *initialisation, *mainTask and *shutdown procedures.

    /** Setting up of sockets, I/O streams and any initialisation should be performed here. */
    protected abstract void initialise();

    /** This method should force a return if a terminal error occurs during execution. */
    protected abstract void mainTask();

    /** Close down sockets, I/O streams and inform connected child/parent threads. */
    protected abstract void shutdown();


    // Control methods.

    /** Tell the Thread to die. Other Threads may call this. */
    public final void terminate() { 
	synchronized(runLock) {
	    canRun = false;
	}
    }

    /** Make the Thread capable of running mainTask() at least once. */
    protected final void setRunnable() { 
	synchronized(runLock) {
	    canRun = true;
	}
    }

    /** Test to see if the Thread should die. Call this frequently in mainTask(). */
    protected final boolean canRun() {
	synchronized(runLock) {
	    return canRun;
	}
    }

    /** Tell the Thread to go into the paused state at the end of a mainTask loop.*/
    public final void linger() {
	synchronized(pauseLock) {
	    lingerTimeout = 0;
	    paused = true;
	    pLock.setValue(true);
	}
    }

    /** Tell the Thread to go into the paused state at the end of a mainTask loop.*/
    public final void linger(long timeout) {
	synchronized(pauseLock) {
	    lingerTimeout = timeout;
	    paused = true;
	    pLock.setValue(true);
	}
    }

    /** Tell the Thread to resume after a pause.*/
    public final void awaken() {
	synchronized(pauseLock) {
	    paused = false;
	    pLock.setValue(false);
	}
    }

    /** Returns the paused state of the Thread.
     * @return True if this Thread is currently paused.*/
    public boolean isPaused() { 
	synchronized(pauseLock) {
	    return paused;
	}
    }
    
    /** Check if this Thread is capable of running its mainTask() repeatedly. */
    protected final boolean isPermanent() { return permanence;}

    /** Check if this Thread runs its mainTask() once only. */
    protected final boolean isTransient() { return !permanence;}

}






