package ngat.util;


/** 
 * Implements a shared storage area for Server to place responses to client transactions.
 * The Store will be extended to allow reader/writer to block when full/empty conditions occur.
 * For now r/w threads will have to poll while read()/write() throw full/empty exceptions.
 * (A simpler implementation could just synch the 2 access methods).
 * ## This is likely to be replaced by an implementation using wait/notify but needs some work
 * ## to allow waiting Threads to be still interruptable by their controller.
 *
 * $Id: SharedStore.java,v 1.2 2000-12-11 14:38:16 snf Exp $
 *
 */
public class SharedStore {

    /** The storage location. (A single Object). */
    Object buffer;
    /** Determines whether the store is occupied. */
    boolean status;
    /** Mutex lock to allow Threads to wait for access. */
    SynchFlag lock;

    static final boolean EMPTY = false;
    static final boolean FULL = true;

    /** Constructs a new, initially empty SharedStore. */
    public SharedStore() {
	buffer = new Object();
	lock = new SynchFlag();
        status = EMPTY; // nothing in to start with.
	
    }

    /** Writes an Object into the store.
     * @param data The Object (subclass) written.
     * @exception StoreFullException When the store is occupied. */
    public void write(Object data) throws StoreFullException {
	try {
	    lock.getSynchFlag();
	    if (status == EMPTY) {
		buffer = data;
		status = FULL;
	    } else throw new StoreFullException("Write: - Buffer is full");
	} finally {
	    lock.freeSynchFlag();
	}
    }

    /** Reads an Object from the store.
     * @return The contents of the store.
     * @exception StoreEmptyException When there is nothing in the store. */
    public Object read() throws StoreEmptyException {
	try {
	    lock.getSynchFlag();
	    if (status == FULL) {
		status = EMPTY;
		return buffer;
	    } else throw new StoreEmptyException("Read: - Buffer is empty");
	} finally {
	    lock.freeSynchFlag();
	}
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/21 17:08:04  snf
/** Initial revision
/** */
