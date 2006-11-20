package ngat.net.transaction;

/** This interface should be implemented by any classes which wish 
 * to be notified of changes to a TransactionStore. i.e. the addition,
 * removal or changes of status of records in the store.
 */
public interface TransactionStoreChangeListener {

    /** Implementing classes should override to handle notification
     * that a TransactionRecord has been added to the store.
     * @param record The TransactionRecord added to the store.
     */
    public void recordAdded(TransactionRecord record);

    /** Implementing classes should override to handle notification
     * that a TransactionRecord's state has changed.
     * @param record The TransactionRecord whose state has changed.
     */
    public void recordStateChanged(TransactionRecord record);

    /** Implementing classes should override to handle notification
     * that a TransactionRecord has been removed from the store.
     * @param record The ID of the TransactionRecord removed from the store.
     */
    public void recordRemoved(TransactionID tid);

}
