package ngat.util;

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

    /** Determines whether the Thread should run its main method once only.*/
    protected boolean permanent;

    /** Const. Thread runs once only. */
    public static final boolean TRANSIENT = false;

    /** Const. Thread can run mainTask method repeatedly. */
    public static final boolean PERMANENT = true;


    // Constructors.

    /** Construct a ControlThread which may be TRANSIENT or PERMANENT. */
    public ControlThread(String name, boolean permanent) {
	super(name);
	this.permanent = permanent;
	canRun = false;
	runLock = new Object();
    }

    /** Construct a named TRANSIENT ControlThread. */
    public ControlThread(String name) {
	this(name, TRANSIENT);
    }

    /** Construct a TRANSIENT ControlThread with unspecified name. */
    public ControlThread() {
	super();
	this.permanent = TRANSIENT;
	canRun = false;
	runLock = new Object();
    }

    // The run() method is inherited but cannot be overridden.
    // Subclasses should override the 3 methods contained in it.

    /** This method cannot be overridden. Calls initialise(), mainTask() and always
     * calls shutdown() even if main methods fail. */
    public final void run() {
	setRunnable();
	try {
	    initialise();
	    while (canRun()) {
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

    // Utility Methods.
    
    /** Check if this Thread is capable of running its mainTask() repeatedly. */
    protected final boolean isPermanent() { return permanent;}

    /** Check if this Thread runs its mainTask() once only. */
    protected final boolean isTransient() { return !permanent;}

}






