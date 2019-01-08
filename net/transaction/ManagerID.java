package ngat.net.transaction;

import java.io.*;

/** Provides a unique ID for a TransactionManager. The form of the ID can
 * be altered e.g. from long to String or whatever without changing any
 * existing code. By forming an Object we can use as a key to a hashtable
 * or map without the need for primitive wrappers etc.
 */
public class ManagerID implements Serializable {
    
    /** Tha actual ID value.*/
    protected long mid;

    /** Create a ManagerID with the specified value.
     * @param mid The long value to use.
     */
    public ManagerID(long mid) { 
	this.mid = mid;
    }

    /** Return the actual value of the ID.*/
    protected long getValue() { return mid; }

    /** Return a readable description.*/
    public String toString() { 
	return "[MgrID: "+mid+"]";
    }

    /** Return a readable description as a U/c hex string.*/ 
    public String toHexString() {
	return "[MgrID: "+Long.toHexString(mid).toUpperCase()+"]";
    }

    /** Return a string representation which can be used for filenames
     * and for indexing.
     */
    public String getIdString() {
	return Long.toHexString(mid).toUpperCase();
    }

    /** Overwritten to return true if both ManagerIDs have the same value.
     * @param other The ManagerID to compare with.
     */
    public boolean equals(Object other) {
	if (! (other instanceof ManagerID)) return false;
	return (((ManagerID)other).getValue() == mid);
    }

}
