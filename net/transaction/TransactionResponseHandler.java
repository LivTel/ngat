package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

/** Interface which must be implemented by any classes which wish to act as
 * recipients of data returned by executed transactions. There are 2 methods 
 * which need implementing.
 *
 * <dl>
 *  <dt>submissionAccepted(long) 
 *     <dd>is called by the local TM when it has accepted a transaction.
 *  <dt>response(long, TRANSACTION_DONE) 
 *     <dd>is called by the local TM when the response arrives back.
 * </dl>
 *
 * General notes:
 * <ol>
 *  <li>When submission is accepted the implementor will most likely want to
 *      set flags or the like to stop actions which could conflict with the
 *      transaction in progress - e.g. a GUI may need to disable some items
 *      to avoid a user clicking on them until the transaction is completed.
 *
 *  <li>On receipt the implementor will want to release locked resources which
 *      were locked on submission. e.g. A GUI could re-enable disabled items
 *      which were greyed out on submission.
 *
 *  <li>These last 2 imply some form of persistence will be required in order
 *      to maintain this state between instantiations of the source application.
 * 
 *  <li>It should be noted that the returned response may be from an upstream 
 *      transaction router other than the destination if the transaction was 
 *      not able to be delivered and a discard or timeout option was specified 
 *      on submission.
 * </ol>
 *
 */
public interface TransactionResponseHandler {

    /** This method should be overwritten to perform any work when the
     * TransactionManager has acknowledged receipt of a submitted transaction.
     * It is called when the local TM has made a persistent copy of the transaction
     * and has accepted responsibilty for delivery.
     * @param transId Unique ID of the transaction.
     */
    public void submissionAccepted(TransactionID transId);

    /** This method should be overwritten to handle the returned reponse
     * from an executed transaction. 
     * @param transId Unique ID of the transaction.
     * @param response the ngat.message.OSS,TRANSACTION_DONE received.
     */
    public void responseReceived(TransactionID transId, TRANSACTION_DONE response);

    /** This method should be overwritten to handle notification of
     * failure to deliver/execute a transaction.
     * @param transId Unique ID of the transaction.
     * @param message A message code indicating why the transaction failed.
     * @param ex      An (optional) Exception thrown by DTMS.
     */
    public void transactionFailed(TransactionID transId, int message, Exception ex);
    
}
