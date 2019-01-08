package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;
import java.util.*;

/** This class transports a response message to an upstream transaction manager. This
 * message is used to supply information about the current progress of a previously submitted
 * executable transaction and may contain a TRANSACTION_DONE response.
 */
public class ResponseTransactionMessage 
    extends DistributedTransactionMessage 
    implements TransactionOptions {
    
    /** The unique ID of the executable - 
     * this must be unique over ALL tranaction originators
     * and per originator instance. 
     */
    protected TransactionID id;

    /** The status of the executable transaction at this TM.*/
    protected int status;

    /** The completion response TRANSACTION_DONE from the destination TM.*/
    protected TRANSACTION_DONE response;

    /** Create a ResponseTransaction with no ID.*/
    public ResponseTransactionMessage() {
	super();
    }

    /** Create a ResponseTransaction for executable with specified ID.
     * @param id The transaction's unique ID.
     */
    public ResponseTransactionMessage(TransactionID id) {
	this();
	this.id = id;
    }
    
    /** Sets the status of the executable transaction at the current TM.
     * @param status The status of the executable being queried.
     */
    public void setStatus(int status) { this.status = status; }

    /** Return the status of the executable transaction at the current TM.*/
    public int getStatus() { return status ; }
    
    /** Sets the completion response TRANSACTION_DONE from the destination.
     * @param response An ngat.message.OSS.TRANSACTION_DONE containing the
     *                 completion response of the executed transaction.
     */
    public void setResponse(TRANSACTION_DONE response) { this.response = response; }

    /** Return the completion response TRANSACTION_DONE from the destination.*/
    public TRANSACTION_DONE getResponse() { return response; }

    /** Return a readable description of this ExecutableTransaction.*/
    public String toString() {
	return "[ResponseTransaction: id="+id+
	    ", Returned status: "+statusString(status)+", Response: "+response+"]";
    }
 
    /** Converts status codes into equal length readable text.*/
    public String statusString(int status) {
	switch (status) {
	case UNKNOWN_STATUS:
	    return "UNKNOWN  ";
	case RECVD_STATUS:
	    return "RECVD    ";
	case FWD_STATUS:
	    return "FWD      ";
	case DONE_STATUS:
	    return "DONE     ";
	case CAN_RECVD_STATUS:
	    return "CAN_RECVD";
	case CAN_STATUS :
	    return "CAN      ";
	case DEL_RECVD_STATUS:
	    return "DEL_RECVD";
	case DEL_STATUS:
	    return "DEL      ";
	default:
	    return "UNKNOWN  ";		
	}
    }

} 
