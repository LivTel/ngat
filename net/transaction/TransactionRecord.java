package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;


import java.io.*;
import java.util.*;

/** Stores the details of a Transaction persistently at a TransactionManager.
 */
public class TransactionRecord implements Serializable {

   
    /** The ExecutableTransaction which the destination TM will execute.*/
    protected ExecutableTransactionMessage executable;

    /** A list of operations performed on the transaction.*/
    protected List operations;

    /** Current status of the transaction.*/
    protected volatile int status;

    /** Records the arrival time of the ExecutableTransaction.*/
    protected long arrivalTime;

    /** Records the time the last operation was performed in respect of this ExecutableTransaction.*/
    protected long lastOperationTime;
    
    /** ID of the next router on the executable's route. This has to be worked out by the TM's
     * ServerThread when the executable arrives by inspecting the routing table.
     */
    protected ManagerID nextRouter;

    /** The response to the executable received from the destination TM.*/
    protected TRANSACTION_DONE response;

    /** Create a  TransactionRecord - used in de-serialization.*/
    public TransactionRecord() {this(null);}
    
    /** Create a TransactionRecord for an ExecutableTransaction.
     * @param executable The ExecutableTransaction to record.
     */
    public TransactionRecord(ExecutableTransactionMessage executable) {
	this.executable = executable;
	operations = new Vector();
    }
    
    /** Sets the ExecutableTransaction.
     * @param executable The ExecutableTransaction to record.
     */
    public void setExecutable(ExecutableTransactionMessage executable) {
	this.executable = executable;
    }

    /** Return the ExecutableTransaction recorded.*/
    public ExecutableTransactionMessage getExecutable() { return executable; }

    /** Add an operation to the list of operations performed with the executable.
     * @param operation A TransactionOperation which has been performed.
     */
    public void addOperation(TransactionOperation op) {
	operations.add(op);
    }
	
    /** Retun a java.util.Iterator over the list of operations performed.*/
    public Iterator listOperations() { return operations.iterator(); }

    /** Sets the current status of the ExecutableTransaction.
     * @param status The status of the ExecutableTransaction.
     */
    public void setStatus(int status) {this.status = status; }

    /** Return the current status oof the executable.*/
    public int getStatus() {return status; }

    /** Set the arrival time of the ExecutableTransaction.
     * @param arrivalTime The time of arrival.
     */
    public void setArrivalTime(long time) { this.arrivalTime = time; }

    /** Return the arrival time of the ExecutableTransaction.*/
    public long getArrivalTime() { return arrivalTime; }

    /** Set the time of last operation transmission. This is the last time 
     * a request was sent to the downstream router (if any).
     * @param time The time of the last downstream operation transmission.
     */
    public void setLastOperationTime(long time) { this.lastOperationTime = time; }

    /** Return the time of last operation transmission. This is the last time 
     * a request was sent to the downstream router (if any).
     */
    public long getLastOperationTime() { return lastOperationTime; }

    /** Set the ID of the next router in sequence (after the current one).
     * @param id The unique ID of the router.
     */
    public void setNextRouter(ManagerID id) { nextRouter = id; }

    /** Return the ID of the next router in sequence (after the current one).*/
    public ManagerID getNextRouter() { return nextRouter; }

    /** Set the completion response.
     * @param reponse The compeltion response.
     */
    public void setResponse(TRANSACTION_DONE response) { this.response = response; }

    /** Return the completion response.*/
    public TRANSACTION_DONE getResponse() { return response; }

    /** Return a readable string representing this record.*/
    public String toString() {
	return "[TransactionRecord: "+executable+
	    ", Status="+status+"("+TransactionUtilities.getStateCodeString(status)+")"+
	    ", Arrived="+arrivalTime+
	    ", NextRouter="+nextRouter+"]";
    }
    
    /** Return a hashcode for this record.*/
    public int hashCode() { return executable.getId().hashCode(); }


}












