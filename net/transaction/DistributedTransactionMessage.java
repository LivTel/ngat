package ngat.net.transaction;

import java.io.*;

/** This class represents the base class for all distributed transaction managment (DTM) messages.
 * Subclasses will carry information to perform transactions and managment information to
 * control, modify or monitor progress of previously sent transactions.
 */
public class DistributedTransactionMessage implements Serializable {
    
    /** Create a DistributedTransactionMessage.
     * @param A unique ID for this transaction.
     */
    public DistributedTransactionMessage() { }
    
}
