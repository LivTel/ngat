package ngat.net.transaction;

import java.io.*;

/** Provides a unique ID for a transaction based on the unique
 * TransactionManagerID and a persistent per-transaction ID.
 */
public class TransactionID implements Serializable{

    /** ID of the manager which originated a transaction.*/
    protected ManagerID managerId;

    /** Per-transaction message unique ID.*/
    protected MessageID messageId;

    /** Create a  TransactionID with specified parameters.
     * @param managerId ID of the manager which originated a transaction.
     * @param messageId Per-transaction message unique ID.
     */
    public TransactionID(ManagerID managerId, MessageID messageId) {
	this.managerId = managerId;
	this.messageId = messageId;
    }

    /** Return the ID of the manager which originated a transaction.*/
    public ManagerID getManagerId() { return managerId; }
    
    /** Return the per-transaction message unique ID.*/
    public MessageID getMessageId() { return messageId; }

    /** Returns a readable description of this TransactionID.*/
    public String toString() {
	return "[TransID: "+managerId+":"+messageId+"]";
    }

    /** Returns a readable description of this TransactionID as a u/c hex string.*/
    public String toHexString() {
	return "[TransID: "+
	    managerId.toHexString()+":"+
	    messageId.toHexString()+"]";
    }

    /** Return a string representation which can be used for filenames
     * and for indexing.
     */
    public String getIdString() {
	return managerId.getIdString()+"-"+messageId.getIdString();
    }
    
    /** Overwritten to return true if both TransactionIDs have the same value.
     * i.e. Both Message and ManagerID agree.
     * @param other The TransactionID to compare with.
     **/
    public boolean equals(Object other) {
	if (! (other instanceof TransactionID)) return false;
	return (((TransactionID)other).getMessageId().equals(messageId) &&
		((TransactionID)other).getManagerId().equals(managerId));
    }

    public int hashCode() { return ((int)managerId.getValue() + (int)messageId.getValue()); }

}
