package ngat.net.transaction;

import java.io.*;

/** Stores details of an operation on an ExecutableTransactionMessage.*/
public class TransactionOperation implements Serializable {

    /** Time of the operation.*/
    protected long operationTime;

    /** Code indicating the type of operation performed.*/
    protected int operation;

    /** Code indicating the returned status from the operation.*/
    protected int reply;

    /** Create a TransactionOperation.*/
    public TransactionOperation() {}
    
    /** Set the time of the operation.
     * @param time The time the operation was performed.
     */
    public void setOperationTime(long time) { this.operationTime = time; }

    /** Return the time the operation was performed.*/
    public long getOperationTime() { return operationTime; }

    /** Set a code to indicate the type of operation.
     * @param op Code indicating the type of operation performed.
     */
    public void setOperation(int op) { this.operation = op; }

    /** Return the code indicating the type of operation performed.*/
    public int getOperation() { return operation ; }

    /** Set a code to indicate the returned status from the operation.
     * @param reply Code indicating the returned status from the operation.
     */
    public void setReply(int reply) { this.reply = reply; }

    /** Return the code indicating the returned status from the operation.*/
    public int getReply() { return reply; }

}

