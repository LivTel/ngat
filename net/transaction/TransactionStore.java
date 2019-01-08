package ngat.net.transaction;

import java.io.*;
import java.util.*;

/** This class provides the mechanism for implementing persistence for the
 * TransactionRecords and associated handlers. The following files could be
 * generated for a TM.O with MgrID [55BF20]:-
 *
 * <pre>
 *   List:   55BF20.db0
 *    Rec:    55BF20-272727.dbr
 *     Hdlr:   55BF20-272727.dbh
 *       .
 *       .
 *    Rec:    55BF20-5345343.dbr
 *     Hdlr:   55BF20-5345343.dbh
 * </pre>
 *
 * In the case of a Router or Executor instance there would be NO handler records.
 *
 * Note: Currently we store to file using the TransactionID.getIdString() method
 *       to obtain a filename but we obtain a map keyusing the TransactionID.toString() 
 *       method. These will change at a future time when problems with hashCode() 
 *       and equals() are resolved wrt TransactionID class.
 *
 */
public class TransactionStore implements Serializable {

    /** Listens for any changes to the content of this Store.*/
    protected TransactionStoreChangeListener tscl;

    /** The Map of TransactionRecords stored against TransactionID.*/
    protected Map transactionMap;

    /** The Map of TransactionResponseHandlers stored against TransactionID.*/
    protected Map handlerMap;

    /** A list of current TransactionIDs. */
    protected List current;

    /** Directory where serialized files live.*/
    protected File store;

    /** The file to write/read  current-list from.*/
    protected File listFile;

    /** The ManagerID of the TM this store is for.*/
    protected ManagerID managerId;

    /** Create a TransactionStore.
     * @param managerId ID of the Manger which this store is for.
     */
    public TransactionStore(ManagerID managerId)  throws IOException {
	this.managerId = managerId;
	current         = Collections.synchronizedList(new Vector());
	transactionMap  = Collections.synchronizedMap(new HashMap());
	handlerMap      = Collections.synchronizedMap(new HashMap());
    }

    /** Sets the optional ChangeListener for this store.
     * @param tscl A TransactionStoreChangeListener.
     */
    public void setTransactionStoreChangeListener(TransactionStoreChangeListener tscl) {
	this.tscl = tscl;
    }
    
    /** Initialize the TransactionStore using the specified storage directory.
     * The list-file will be: <store>/<mgrId>.db0
     * This method loads the current-list and the transaction mappings. To load
     * the handler map in addition call loadHandlers(void) strictly after load(store)
     * If load(store) has not been pre-called loadHandlers(void) will fail because 
     * store directory has not been defined yet.
     * @param store The directory containing the map.
     * @throws IOException if anything goes wrong opening or reading the listFile.
     */
    public void load(File store) throws IOException, ClassNotFoundException {
	this.store = store;
	
	// Check for and create listFile if not in existance already.
	listFile = new File(store, managerId.getIdString()+".db0");

	// e.g. 54AB600FF.db0

	if (! listFile.exists()) {
	    // First time - new list-file.
	    listFile.createNewFile();
	} else {
	    
	    // Load list-file.
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream(listFile));	  
	    current = (Vector)in.readObject();	   
	    in.close();

	    if (current != null) {

		// Check for and load records.
		TransactionID     tid    = null;
		TransactionRecord record = null;

		Iterator recs = current.iterator();
		while (recs.hasNext()) {
		    tid = (TransactionID)recs.next();		
		    record = loadRecord(tid);
		    transactionMap.put(tid.toString(), record);
		}
		
	    }
	}
    }

    /** Loads the handlers from the store directory previously defined by
     * a call to load(store).  
     * @throws IOException if anything goes wrong openeing or reading the handler serial files.
     */
    public void loadHandlers() throws IOException, ClassNotFoundException  {

	if (current != null) {
	    
	    // Check for and load handlers
	    TransactionID              tid     = null;
	    TransactionResponseHandler handler = null;

	    Iterator recs = current.iterator();		
	    while (recs.hasNext()) {
		tid = (TransactionID)recs.next();		
		handler = loadHandler(tid);
		handlerMap.put(tid.toString(), handler);
	    }
	}
    }

    /** Loads the TransactionRecords and their handlers from the store directory.
     * This method is provided to ensure that all relevant data can be loaded during
     * a single synch block rather than via 2 seperate calls. 
     * @param store The directory containing the map.
     * @throws IOException if anything goes wrong opening or reading the listFile.
     */
    public void loadAll(File store)  throws IOException, ClassNotFoundException  {
	load(store);
	loadHandlers();
    }

    /** Load a  TransactionRecord with specified ID.
     * @param tid The ID of the record. 
     * @throws IOException if anything goes wrong openeing or reading the listFile.
     */
    protected TransactionRecord loadRecord(TransactionID tid) throws IOException, ClassNotFoundException {
	File recFile = new File(store, tid.getIdString()+".dbr");
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(recFile));
	TransactionRecord record = (TransactionRecord)in.readObject();
	in.close();
	return record;
    }
    
    /** Load a  TransactionResponseHandler for specified ID.
     * @param tid The ID of the record which this handler handles. 
     * @throws IOException if anything goes wrong openeing or reading the listFile.
     */
    protected TransactionResponseHandler loadHandler(TransactionID tid) throws IOException, ClassNotFoundException {
	File recFile = new File(store, tid.getIdString()+".dbh");
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(recFile));
	TransactionResponseHandler handler = (TransactionResponseHandler)in.readObject();
	in.close();
	return handler;
    }

    /** Save the entire store excluding handlers. These should not need to be stored
     * at any time other than when they are created and stored individually using
     * the saveHandler(handler) method.
     */
    public void store() throws IOException {
	
	// Save the list-file.
	saveList();
	
	// Check for and save records. 
	TransactionID tid = null;
	TransactionRecord record = null;
	Iterator recs = current.iterator();
	while (recs.hasNext()) {
	    tid = (TransactionID)recs.next();	
	    record = (TransactionRecord)transactionMap.get(tid.toString());
	    saveRecord(record);
	}
    }

    /** Save the list-file.
     */
    protected void saveList() throws IOException {
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(listFile));
	out.flush();
	out.writeObject(current);
	out.flush();
	out.close();	
    }

    /** Save a  TransactionRecord.
     * @param record The TransactionRecord to save.
     * @throws IOException if anything goes wrong opening or reading the listFile.
     */
    protected void saveRecord(TransactionRecord record) throws IOException {

	TransactionID tid = record.getExecutable().getId();

	File recFile = new File(store, tid.getIdString()+".dbr");
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(recFile));
	out.flush();
	out.writeObject(record);
	out.flush();
	out.close();	
    }

    /** Save a  TransactionResponseHandler.
     * @param tid     The ID of the record for which the handler is.
     * @param handler The TransactionResponseHandler to save.
     * @throws IOException if anything goes wrong openeing or reading the listFile.
     */
    protected void saveHandler(TransactionID tid, TransactionResponseHandler handler) throws IOException {
	File recFile = new File(store, tid.getIdString()+".dbh");
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(recFile));
	out.flush();
	out.writeObject(handler);
	out.flush();
	out.close();	
    }


    /** Place a TransactionRecord in the store (and save it).
     * @param tid The ID of the record. 
     * @param record The TransactionRecord to save.
     */
    public void putRecord(TransactionID tid, TransactionRecord record) throws IOException {
	transactionMap.put(tid.toString(), record);	
	saveRecord(record);	
	if (tscl != null)
	    tscl.recordAdded(record);
    }

    /** Place a TransactionResponseHandler in the store (and save it).
     * @param tid The ID of the record for which it is a handler. 
     * @param record The TransactionResponseHandler to save.
     */
    public void putHandler(TransactionID tid, TransactionResponseHandler handler) throws IOException {
	handlerMap.put(tid.toString(), handler);	
	saveHandler(tid, handler);	
    }
    

    /** Return a TransactionRecord of the specified ID.
     * @param tid The ID of the record. 
     */
    public TransactionRecord getRecord(TransactionID tid) {
	return (TransactionRecord)transactionMap.get(tid.toString());
    }
    
    /** Return a TransactionResponseHandler of the specified ID.
     * @param tid The ID of the record. 
     */
    public TransactionResponseHandler getHandler(TransactionID tid) {
	return (TransactionResponseHandler)handlerMap.get(tid.toString());
    }

    /** Save an updated TransactionRecord. 
     * Just calls saveRecord() if the record exists.
     * @param tid The ID of the record. 
     */
    public void updateRecord(TransactionID tid) throws IOException {
	TransactionRecord record = getRecord(tid);
	if (record != null) {
	    saveRecord(record);
	    if (tscl != null)
		tscl.recordStateChanged(record);
	}
    }

    /** Returns true if the specified TransactionID is a key to a record.*/
    public boolean containsRecord(TransactionID tid) {
	return transactionMap.containsKey(tid.toString());
    }
    
    /** Returns the size of the Transaction map.*/
    public int size() { return transactionMap.size(); }

}
