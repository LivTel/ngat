package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;

/** Basic implementation of the TransactionResponseHandler interface.
 * This implementation does nothing.
 */
public class DefaultTransactionResponseHandler implements TransactionResponseHandler, Serializable {

    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     */
    public void submissionAccepted(TransactionID transId) {
	System.err.println("SubmissionReceived:: "+transId);
    }

    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     * @param response the ngat.message.OSS,TRANSACTION_DONE received.
     */
    public void responseReceived(TransactionID transId, TRANSACTION_DONE response) {
	System.err.println("***DTRH::ResponseReceived:: "+transId+" : "+response);
    }

    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     * @param message A message code indicating why the transaction failed. 
     * @param ex      An (optional) Exception thrown by DTMS.
     */
    public void transactionFailed(TransactionID transId, int message, Exception ex) {
	System.err.println("***DTRH::TransactionFailed:: "+transId+" : "+message);
    }
    
}
