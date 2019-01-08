package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;
import java.util.*;

/** This class transports a query message to a single downstream transaction manager. This
 * message is used to request information about the current progress of a previously submitted
 * executable transaction.
 */
public class QueryTransactionMessage extends DistributedTransactionMessage {
    
    /** The unique ID of the executable - 
     * this must be unique over ALL transaction originators
     * and per originator instance. 
     */
    protected TransactionID id;

    /** Create a QueryTransaction.*/
    public QueryTransactionMessage() {
	super();
    }

    /** Create a QueryTransaction for executable with specified id.
     * @param id The transaction's unique ID.
     */
    public QueryTransactionMessage(TransactionID id) {
	this();
	this.id = id;
    }

    /** Returns the executable's unique ID.*/
    public TransactionID getId() { return id; }

    /** Return a readable description of this ExecutableTransaction.*/
    public String toString() {
	return "[QueryTransaction: id="+id+"]";
    }
 
} 
