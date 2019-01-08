package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;
import java.util.*;

/** This class transports a delete message to a downstream transaction manager. This
 * message is used to request the TM to delete its local copy of a specified, 
 * previously executed transaction.
 */
public class DeleteTransactionMessage extends DistributedTransactionMessage {
    
    /** The unique ID of the executable - 
     * this must be unique over ALL tranaction originators
     * and per originator instance. 
     */
    protected TransactionID id;

    /** Create a DeleteTransaction.*/
    public DeleteTransactionMessage() {
	super();
    }

    /** Create a DeleteTransaction for executable with specified id.
     * @param id The transaction's unique ID.
     */
    public DeleteTransactionMessage(TransactionID id) {
	this();
	this.id = id;
    }
    
    /** Returns the executable's unique ID.*/
    public TransactionID getId() { return id; }

    /** Return a readable description of this ExecutableTransaction.*/
    public String toString() {
	return "[DeleteTransaction: id="+id+"]";
    }
    
} 
