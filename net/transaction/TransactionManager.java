package ngat.net.transaction;

import ngat.net.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.security.*;
import javax.net.*;
import javax.net.ssl.*;
//import com.sun.net.ssl.*;
import javax.security.cert.*;
//import com.sun.net.ssl.*;

/** Base and factory class for all TransactionManagers. 
 * Specific subclasses are created using the static createXXXInstance() methods of this class.
 */
public abstract class TransactionManager implements TransactionOptions, Logging {

    /** ClassID for logging.*/
    public static final String CLASS = "TransactionManager";

    /** Default OpsThread polling interval.*/
    public static final long DEFAULT_OPS_POLLING_INTERVAL = 1000L;

    /** Default GcThread cadence interval.*/
    public static final long DEFAULT_GC_CADENCE_INTERVAL = 60000L;

    /** The name of this TransactionManager.This can only be setup by constructor.*/
    protected String name;

    /** The ID of this TransactionManager. This can only be setup by constructor.*/
    protected ManagerID myId;

    /** Stores the set of Transactions currently being managed. They are referenced
     * by unique transaction ID.
     */
    protected volatile Map transactionStore;
  
    /** Thread which handles forwarding of messages to downstream managers.*/
    protected OperationThread operationThread;

    /** Thread which handles transaction messages received from upstream managers.*/
    protected ServerThread serverThread;

    /** Thread which handles clearance of the transaction messages from the store.*/
    protected GarbageCollectorThread gcThread;

    /** File containing client's KeyStore.*/
    protected File clientKeyFile;
    
    /** File containing client's TrustStore.*/
    protected File clientTrustFile;
    
    /** File containing server's KeyStore.*/
    protected File serverKeyFile;
    
    /** File containing server's TrustStore.*/
    protected File serverTrustFile;

    /** Password for client's KeyStore.*/
    protected String clientKeyPass;
    
    /** Password for client's TrustStore.*/
    protected String clientTrustPass;
    
    /** Password for server's KeyStore .*/
    protected String serverKeyPass;
    
    /** Password for server's TrustStore.*/
    protected String serverTrustPass;
    
    /** Persistence data directory.*/
    protected File workDir;

    /** True if incoming connections from an upstream client must be secure.*/
    protected boolean upstreamSecure;

    /** True if outgoing connections to downstream server must be secure.*/
    protected boolean downstreamSecure;
    
    /** Stores the set of TransactionManagers known to this TM i.e. those which can be reached
     * from it. These are referenced by name and yield the (long) tmID of the TM.
     * This is part-1 of the router table.
     */
    protected Map destinations;

    /** Stores a mapping between TM IDs and their connection details as ConnectionInfos.
     * This is part-2 of the router table.
     */
    protected Map connections;
    
    /** TransactionManager logging.*/
    protected Logger dtmsLog;

    /** Synch lock.... depreciated from 19-nov-2003.*/
    protected Object lock = new Object();

    /** Underlying TS map to synchronize on.*/
    protected Map transactionStoreLock;


    /** Create a TransactionManager with specified name and ID.
     * @param name The name (alias) of this TM.
     * @param myId The ID of this TransactionManager.    
     */
    private TransactionManager(String name, ManagerID myId) {
	this.name = name;
	this.myId = myId;
	transactionStoreLock = new HashMap();
	transactionStore = Collections.synchronizedMap(transactionStoreLock);
	destinations     = Collections.synchronizedMap(new HashMap());
	connections      = Collections.synchronizedMap(new HashMap());
	
	dtmsLog = LogManager.getLogger("DTMS");	
	
	dtmsLog.log(1, CLASS, name, "init",
		    "Initializing TransactionManager: "+name+" : "+myId);
	
    }

    /** Create a TransactionManager source class.
     * @param name The name (alias) of this TM.
     * @param myId The ID of this TransactionManager.    
     */
    public static TransactionManager.Originator createOriginatorInstance(String name, ManagerID myId) {
	return new Originator(name, myId);
    }
    
    /** Create a TransactionManager router class.
     * @param name The name (alias) of this TM.
     * @param myId The ID of this TransactionManager.  
     * @param port Port to bind server to.
     */
    public static TransactionManager.Router createRouterInstance(String name, ManagerID myId, int port) {
	return new Router(name, myId, port);
    }
    
    /** Create a TransactionManager destination class
     * @param name The name (alias) of this TM.
     * @param myId The ID of this TransactionManager.   
     * @param port Port to bind server to.
     */
    public static TransactionManager.Executor createExecutorInstance(String name, ManagerID myId, int port) {
	return new Executor(name, myId, port);    
    }
    
    /** Return the unique ID of this TransactionManager.*/
    public ManagerID getManagerId() { return myId; }

    /** Generates a unique transaction ID over all time. This method must keep a
     * persistent record of the ID it is using. 
     * ### TEMP returns (MgrID : current system time millis) ###
     */
    public TransactionID getNextTransactionId() { 
	return new TransactionID(myId, new MessageID(System.currentTimeMillis())); 
    }
    
    /** Sets the file to use as KeyStore when acting as client.
     * @param keyFile The KeyStore.
     */
    public void setClientKeyFile(File keyFile) {
	this.clientKeyFile = keyFile;
    }
    
    /** Sets the file to use as TrustStore when acting as client.
     * @param keyFile The TrustStore.
     */
    public void setClientTrustFile(File trustFile) {
	this.clientTrustFile = trustFile;
    }
    
    /** Sets the file to use as KeyStore when acting as server.
     * @param keyFile The KeyStore.
     */
    public void setServerKeyFile(File keyFile) {
	this.serverKeyFile = keyFile;
    }
    
    /** Sets the file to use as TrustStore when acting as server.
     * @param keyFile The TrustStore.
     */
    public void setServerTrustFile(File trustFile) {
	this.serverTrustFile = trustFile;
    }
    
    /** Retrieve the list of KeyManagers. (Pass = geronimo)!*/
    protected KeyManager [] getKeyManagers(File keyFile, String pass) throws Exception {
	String alg = KeyManagerFactory.getDefaultAlgorithm();
	
	KeyManagerFactory kmf = KeyManagerFactory.getInstance(alg);		

	// Uses "server.private" or equivalant.
	FileInputStream fis = new FileInputStream(keyFile);
	KeyStore ks = KeyStore.getInstance("jks");
	ks.load(fis, pass.toCharArray());
	
	fis.close();
	kmf.init(ks, pass.toCharArray());
	
	KeyManager [] kms = kmf.getKeyManagers();
	
	return kms;
    }
	
    /** Retrieve the list of trustManagers. (Pass = public)!*/
    protected TrustManager [] getTrustManagers(File trustFile, String pass) throws Exception {
	String alg = TrustManagerFactory.getDefaultAlgorithm();
	
	TrustManagerFactory tmf = TrustManagerFactory.getInstance(alg);
	
	// Uses "client.public" or equivalant.
	FileInputStream fis = new FileInputStream(trustFile);
	KeyStore ks = KeyStore.getInstance("jks");
	ks.load(fis, pass.toCharArray());
	
	fis.close();
	tmf.init(ks);
	
	TrustManager [] tms = tmf.getTrustManagers();
	
	return tms;
    }
    
    /** Set up an alias for the TM identified by id.
     * @param name The readable name (alias)of the TM
     * @param mid  The unique ID of the TM.
     */ 
    public void addDestination(String name, ManagerID mid) {
	destinations.put(name, mid);
    }
    
    /** Return the ID of the TM identified (aliased) by name.If the destination
     * is not known returns null.
     * @param name The readable name (alias)of the TM.
     */
    public ManagerID getDestination(String name) {
	//System.err.println("TMO: debug: Looking for name: "+name);
	if (destinations.containsKey(name)) {
	    ManagerID mid = (ManagerID)destinations.get(name);
	    // System.err.println("TMO: debug: Found: "+mid);
	    return (ManagerID)destinations.get(name);
	}
	//System.err.println("TMO: debug: Not found");
	return null;
    }

    /** Setup a TM's connection details.
     * @param mid  The unique ID of the TM.
     * @param host The host's address (name or IP quad).
     * @param port Host's port number.
     */
    public void addConnectionInfo(ManagerID mid, String host, int port) {
	connections.put(mid.toString(), new ConnectionInfo(host, port));
    }
    
    /** Return the host and port of an IDd TM destination. If the TM is
     * not known to the routing table, returns null.
     *  @param mid The unique ID of the TM.
     * @return A ConnectionInfo containing the host and port for the conenction.
     */
    public ConnectionInfo getConnectionInfo(ManagerID mid) {
	//System.err.println("TMO: debug: Looking for connection: "+mid);
	if (! connections.containsKey(mid.toString())) return null;
	ConnectionInfo connect = (ConnectionInfo)connections.get(mid.toString());
	//System.err.println("TMO: debug: Found Connection: "+connect);
	return connect;
    }

    /** Load routing-table from file.
     * @param file The file to load routing-table entries from.
     */
    public void loadRouting(File file) throws IOException, IllegalArgumentException {
	ConfigurationProperties props = new ConfigurationProperties();
	props.load(new FileInputStream(file));
	loadRouting(props);
    }

    /** Load routing-table from ConfigurationProperties.
     * @param params The routing details.
     */
    public void loadRouting(ConfigurationProperties params) throws IllegalArgumentException {
	// router.x = FRED
	// FRED.ID   = 123
	// FRED.host =localhost
	// FRED.port = 5555
	//

	String rname = null;
	String host  = null;
	long   sid   = -1L;
	int    port  = -1;

	ManagerID mid = null;
	
	String item = null;
	Enumeration e = params.propertyNames();
	while (e.hasMoreElements()) {
	    item = (String)e.nextElement();
	    if (item.startsWith("router.")) {
		rname = params.getProperty(item);
		try {
		    sid  = params.getLongValue(rname+".ID");
		} catch (ParseException px) {
		    throw new IllegalArgumentException("Parsing routing-table for router: "+rname+
						       " Illegal value for ID: "+sid);
		}
		host = params.getProperty(rname+".host");
		try {
		    port = params.getIntValue(rname+".port");
		} catch (ParseException px) {
		    throw new IllegalArgumentException("Parsing routing-table for router: "+rname+
						       " ("+sid+")"+
						       " on host: "+host+
						       " Illegal value for port: "+port);
		}
		mid = new ManagerID(sid);
		addDestination(rname, mid);
		addConnectionInfo(mid, host, port);
		dtmsLog.log(1, CLASS, name, "loadRouting",
			    "Added routing entry: [Router="+rname+
			    ", ID="+mid+
			    ", Link="+getConnectionInfo(mid)+"]"); 
	    }
	}	
    }

    /** Load transaction-store from file.
     * @param file The directory where all the transaction (and handler) records are serialized.
     */
    public void loadStore(File file) throws IOException, IllegalArgumentException {
	// do nothing yet.
    }
    
    /** Configure from file.
     * @param file The file to load configuration settings from.
     */
    public void configure(File file) throws IOException, IllegalArgumentException {
	ConfigurationProperties props = new ConfigurationProperties();
	props.load(new FileInputStream(file));
	configure(props);
    }

    /** Configure from ConfigurationProperties.
     * @param params The configuration details.
     */
    public void configure(ConfigurationProperties params) throws IllegalArgumentException {
	// See if we need secure connections either way.

	String fileName = null;
	
	// As server
	upstreamSecure = params.getBooleanValue("upstream.secure", false);
	
	if (upstreamSecure) {
	    
	    fileName = params.getProperty("server.trust.file");
	    if (fileName == null)
		throw new IllegalArgumentException("No value supplied for: [server.trust.file].");
	 
	    serverTrustFile = new File(fileName);
	    if (! serverTrustFile.exists())
		throw new IllegalArgumentException("Server trust file does not exist.");

	    fileName = params.getProperty("server.key.file");
	    if (fileName == null)
		throw new IllegalArgumentException("No value supplied for: [server.key.file].");
	    
	    serverKeyFile = new File(fileName);
	    if (! serverKeyFile.exists())
		throw new IllegalArgumentException("Server key file does not exist.");

	    serverTrustPass = params.getProperty("server.trust.pass");
	    if (serverTrustPass == null)
		throw new IllegalArgumentException("No value supplied for: [server.trust.pass].");

	    serverKeyPass = params.getProperty("server.key.pass");
	    if (serverKeyPass == null)	 
		throw new IllegalArgumentException("No value supplied for: [server.key.pass].");
	    
	    System.err.println("TM:Configure::OnExit:: STF="+serverTrustFile+" STP="+serverTrustPass+
			       " SKF="+serverKeyFile+" SKP="+serverKeyPass);
	}

	// As client
	downstreamSecure = params.getBooleanValue("downstream.secure", false);

	if (downstreamSecure) {

	    fileName = params.getProperty("client.trust.file");
	    if (fileName == null)
		throw new IllegalArgumentException("No value supplied for: [client.trust.file].");
	 
	    clientTrustFile = new File(fileName);
	    if (! clientTrustFile.exists())
		throw new IllegalArgumentException("Client trust file does not exist.");

	    fileName = params.getProperty("client.key.file");
	    if (fileName == null)
		throw new IllegalArgumentException("No value supplied for: [client.key.file].");
	    
	    clientKeyFile = new File(fileName);
	    if (! clientKeyFile.exists())
		throw new IllegalArgumentException("Client key file does not exist.");

	    clientTrustPass = params.getProperty("client.trust.pass");
	    if (clientTrustPass == null)
		throw new IllegalArgumentException("No value supplied for: [client.trust.pass].");

	    clientKeyPass = params.getProperty("client.key.pass");
	    if (clientKeyPass == null)
		throw new IllegalArgumentException("No value supplied for: [client.key.pass].");

	    System.err.println("TM:Configure::OnExit:: CTF="+clientTrustFile+" CTP="+clientTrustPass+
			       " CKF="+clientKeyFile+" CKP="+clientKeyPass);


	}

    }

    /** Start any executing threads.*/
    public abstract void start();

    /** Stop any executing threads safely.*/
    public abstract void stop();

    /** Local TM procedure to call on receipt of the DONE (completion) response.
     * @param record The TransactionRecord of the transaction which has completed.
     */
    public abstract void processDone(TransactionRecord record);

    /** Local TM procedure to call on receipt of the DEL_RECVD (delete received)  
     *  response.
     * @param record The TransactionRecord of the transaction which has had a
     *               deletion request received.
     */
    public abstract void processDelRecvd(TransactionRecord record);
	
    /** Local TM procedure to call on receipt of a DTMS control message.
     * @param record The TransactionRecord of the transaction which has had a
     *               control message.
     * @param message The control message.
     */
    public abstract void processFailed(TransactionRecord record, int message);

    /** Local TM procedure to call to validate a transaction's ClientDescriptor.
      * @param clientDescriptor Transaction's ClientDescriptor.
      */
    public abstract boolean checkValidClient(ClientDescriptor clientDescriptor);
    
	
    //--------------------------------------------------------------------------
    // Originator.
    //--------------------------------------------------------------------------

    /** Class of TransactionManager which acts as source of transactions.*/
    public static class Originator extends TransactionManager {
	
	/** Stores the set of TransactionResponseHandlers for those transactions
	 * currently being managed. They are referenced by unique transaction ID.
	 */
	Map handlers;

	/** Create a TransactionManager source class.
	 * @param name The name (alias) of this TM.
	 * @param myId The ID of this TransactionManager.    
	 */
	private Originator(String name, ManagerID myId) {
	    super(name, myId);
	    operationThread = new OperationThread(name+":Ops");
	    handlers = new HashMap();
	}
	
	/** Start the Originator.*/
	public void start() {
	    dtmsLog.log(1, CLASS, name, "start", "Starting Originator: "+name+" : "+myId);
	    operationThread.start();
	}

	/** Stop the Originator.*/
	public void stop() {
	    dtmsLog.log(1, CLASS, name, "stop", "Stopping Originator: "+name+" : "+myId);
	    operationThread.terminate();
	}

	/** Adds a new transaction request to the transactionStore and attempts to manage it.
	 * Both the executable and the handler must be non-null.
	 * @param  executable The ExecutableTransactionMessage containing the request 
	 *                    and its control and routing information.
	 * @param  handler    An object which inplements the TransactionResponseHandler interface
	 *                    which will handle the response when received.
	 * @throws SubmissionException If anything untoward occurs during submission or
	 * either parameter is null. 
	 */
	public void submitTransaction(ExecutableTransactionMessage executable, 
				      TransactionResponseHandler   handler) 
				      throws SubmissionException {
	    dtmsLog.log(1, CLASS, name, "submit",
			"Transaction submitted: "+executable);
	    
	    if (executable == null)
		throw new SubmissionException("Null executable");

	    if (handler == null)
		throw new SubmissionException("Null handler");

	    TransactionRecord record = new  TransactionRecord(executable);
	    record.setStatus(RECVD_STATUS);
	    record.setArrivalTime(System.currentTimeMillis());

	    // Next router is just the first item in its RT - we are not in it!
	    ManagerID nextRouter = executable.getRoutingEntry(1);
	    
	    // No first router - maybe its the destination.
	    if (nextRouter == null) {
		nextRouter = executable.getDestination();
		if (nextRouter == null) {
		    dtmsLog.log(1, CLASS, name, "submit",
				"No route specified: ");
		    throw new SubmissionException("No route was specified");		
		}
	    }

	    record.setNextRouter(nextRouter);

	    synchronized(transactionStoreLock) {
		//System.err.println("Lock grabbed by: "+Thread.currentThread());
		// Store by its TransID.
		transactionStore.put(executable.getId().toString(), record);
		//saveTransactionRecord(record);
		handlers.put(executable.getId().toString(), handler);
		//saveHandler(handler);  

		handler.submissionAccepted(executable.getId());
	    
		//System.err.println("Lock released by: "+Thread.currentThread());		
	    }

	  
	} // (submitTransaction)

	/** Local TM procedure to call on receipt of the DONE (completion) response.
	 * @param record The TransactionRecord of the transaction which has completed.
	 */
	public void processDone(TransactionRecord record) {
	    record.setStatus(DEL_RECVD_STATUS);

	    ExecutableTransactionMessage exec = record.getExecutable();
	    if (exec == null) return;
		
	    TransactionID transId = exec.getId();
	    if (transId == null) return;

	    TRANSACTION_DONE response = record.getResponse();	    

	    TransactionResponseHandler handler = (TransactionResponseHandler)handlers.get(transId.toString());
	    handler.responseReceived(transId, response);
	}

	/** Local TM procedure to call on receipt of the DEL_RECVD (delete received)  
	 *  response.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               deletion request received.
	 */
	public void processDelRecvd(TransactionRecord record) {
	    record.setStatus(DEL_RECVD_STATUS);
	}

	/** Local TM procedure to call on receipt of a DTMS control message.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               control message.
	 * @param message The control message.
	 */
	public void processFailed(TransactionRecord record, int message) {
	    // Mark for deletion.
	    
	    // What if it has not been forwarded yet ? OT will not remove this until
	    // downstream routers have signalled they have received DEL request.

	    if (record.getStatus() == RECVD_STATUS)
		record.setStatus(DEL_STATUS);
	    else
		record.setStatus(DEL_RECVD_STATUS);
	    
	    ExecutableTransactionMessage exec = record.getExecutable();
	    if (exec == null) return;
		
	    TransactionID transId = exec.getId();
	    if (transId == null) return;

	    TRANSACTION_DONE response = record.getResponse();

	    TransactionResponseHandler handler = (TransactionResponseHandler)handlers.get(transId.toString());
	    handler.transactionFailed(transId, message, null);
	}

	/** Local TM procedure to call to validate a transaction's ClientDescriptor.
	 * @param clientDescriptor Transaction's ClientDescriptor.
	 */
	public boolean checkValidClient(ClientDescriptor clientDescriptor) { return true;}
	
	/** Returns a description of this TM.*/
	public String toString() { 
	    return "[TM:Originator: "+name+", id="+myId+"]";
	}

    } // [Originator]
    
    //--------------------------------------------------------------------------
    // Router.
    //--------------------------------------------------------------------------

    /** Class of TransactionManager which acts as transaction router.*/
    public static class Router extends TransactionManager {

	/** Create a TransactionManager router class.
	 * @param name The name (alias) of this TM.
	 * @param myId The ID of this TransactionManager.  
	 * @param port Port to bind server to.
	 */
	private Router(String name, ManagerID myId, int port) {
	    super(name, myId); 
	    serverThread    = new ServerThread(name+":Server", port);
	    operationThread = new OperationThread(name+":Ops");	   
	}

	/** Configure from file.
	 * @param file The file to load configuration settings from.
	 */
	public void configure(File file) throws IOException, IllegalArgumentException {	   
	    super.configure(file);
	}
	
	/** Configure from ConfigurationProperties.
	 * @param params The configuration details.
	 */
	public void configure(ConfigurationProperties params) throws IllegalArgumentException {
	    // do nothing yet.
	    super.configure(params);
	    long poll = params.getLongValue("ops.polling.interval", DEFAULT_OPS_POLLING_INTERVAL);
	    operationThread.pollingInterval = poll;
	}
	
	/** Start the Router.*/
	public void start() { 
	    dtmsLog.log(1, CLASS, name, "start", "Starting Router: "+name+" : "+myId);
	    serverThread.start();
	    try {Thread.sleep(50000L);} catch (InterruptedException ix) {
		//System.err.println("*****************Interuppted while waiting on ST to ini/start");
	    }
	    operationThread.start();
	}
	
	/** Stop the Router.*/
	public void stop() {
	    dtmsLog.log(1, CLASS, name, "stop", "Stopping Router: "+name+" : "+myId);
	    operationThread.terminate();
	    serverThread.terminate();
	}

	/** Returns a description of this TM.*/
	public String toString() { 
	    return "[TM:Router: "+name+", id="+myId+"]";
	}

	/** Local TM procedure to call on receipt of the DONE (completion) response.
	 * @param record The TransactionRecord of the transaction which has completed.
	 */
	public void processDone(TransactionRecord record) {}
	
	/** Local TM procedure to call on receipt of the DEL_RECVD (delete received)  
	 *  response.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               deletion request received.
	 */
	public void processDelRecvd(TransactionRecord record) {}
	
	/** Local TM procedure to call on receipt of a DTMS control message.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               control message.
	 * @param message The control message.
	 */
	public void processFailed(TransactionRecord record, int message) {}
 
	/** Local TM procedure to call to validate a transaction's ClientDescriptor.
	 * @param clientDescriptor Transaction's ClientDescriptor.
	 */
	public boolean checkValidClient(ClientDescriptor clientDescriptor) { return true; }
    

    } // [Router]

    //--------------------------------------------------------------------------
    // Executor.
    //--------------------------------------------------------------------------
    
    /** Class of TransactionManager which executes transactions.*/
    public static class Executor extends TransactionManager {

	// Collects done and delete requested TRs
	// GarbageThread garbageThread;

	/** Create a TransactionManager destination class
	 * @param name The name (alias) of this TM.
	 * @param myId The ID of this TransactionManager.   
	 * @param port Port to bind server to.
	 */
	private Executor(String name, ManagerID myId, int port) {
	    super(name, myId);
	    serverThread = new ServerThread(name+":Server", port);	   
	    gcThread     = new GarbageCollectorThread(name+":GC");	  
	}

	/** Configure from file.
	 * @param file The file to load configuration settings from.
	 */
	public void configure(File file) throws IOException, IllegalArgumentException {	   
	    super.configure(file);
	}
	
	/** Configure from ConfigurationProperties.
	 * @param params The configuration details.
	 */
	public void configure(ConfigurationProperties params) throws IllegalArgumentException {
	    // do nothing yet.
	    super.configure(params);
	    long cadence = params.getLongValue("gc.cadence.interval", DEFAULT_GC_CADENCE_INTERVAL);
	    gcThread.cadenceInterval = cadence;
	}
	
	/** Start the Executor.*/
	public void start() { 
	    dtmsLog.log(1, CLASS, name, "start", "Starting Executor: "+name+" : "+myId);
	    serverThread.start();
	    gcThread.start();
	} // (start)
	
	/** Stop the Executor.*/
	public void stop() {
	    dtmsLog.log(1, CLASS, name, "stop", "Stopping Executor: "+name+" : "+myId);
	    serverThread.terminate();
	    // gc.terminate();
	}
	
	/** Return the ID of the next available (unexecuted) transaction.
	 * If no transaction is available returns null.
	 */
	//public TransactionID nextTransactionId() {
	public String nextTransactionId() {
	    TransactionRecord record = null;
	    TransactionID     tid    = null;	    
	    int  status = 0;

	    synchronized(transactionStoreLock) {
		Iterator trans = transactionStore.keySet().iterator();
		while (trans.hasNext()) {
		    //tid     = (TransactionID)trans.next();
		    //record  = (TransactionRecord)transactionStore.get(tid);
		    
		    String tids = (String)trans.next();
		    record  = (TransactionRecord)transactionStore.get(tids);
		    
		    status  = record.getStatus();
		    
		    if (status == RECVD_STATUS)
			return tids;
		}
	    }

	    return null;
	} // (nextTransactionId)

	/** Return the transaction with the specified Id. If no transaction
	 * with the specified ID exists returns null.
	 * @param tid The ID of the transaction to obtain.
	 */
	//public TRANSACTION getTransaction(TransactionID tid) {
	public TRANSACTION getTransaction(String tids) {
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord record = (TransactionRecord)transactionStore.get(tids);
		    if (record != null) {
			ExecutableTransactionMessage exec = record.getExecutable();
			if (exec != null)
			    return exec.getTransaction();
		    }
		}
	    }
	    return null;
	} // (getTransaction)

	/** Return ClientDescriptor (authorization) for the transaction with the specified Id.
	 * If no transaction with the specified ID exists returns null.
	 * @param tid The ID of the transaction to obtain.
	 */
	//public ClientDescriptor getClientDescriptor(TransactionID tid) {
	public ClientDescriptor getClientDescriptor(String tids) {
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord record = (TransactionRecord)transactionStore.get(tids);
		    if (record != null) {
			ExecutableTransactionMessage exec = record.getExecutable();
			if (exec != null)
			    return exec.getClientDescriptor();
		    }
		}
	    }
	    return null;
	} // (getClientDescriptor)

	/** Allows a local (same JVM as TX) client to place executables
	 * directly onto the TransactionQueue.
	 * @param executable The ExecutableTransactionMessage to submit to queue.
	 * @throws SubmissionException If either the executable or its ID is null.
	 */
	public void submitLocalTransaction(ExecutableTransactionMessage executable) 
	    throws SubmissionException {

	    if (executable == null)
		throw new SubmissionException("Null executable");

	    TransactionID transId = executable.getId();		

	    dtmsLog.log(1, CLASS, name, "submitLocal",
			"Transaction submitted locally: "+executable);

	    synchronized(transactionStoreLock) {
		TransactionRecord record = new TransactionRecord(executable);
		record.setStatus(RECVD_STATUS);
		record.setArrivalTime(System.currentTimeMillis());
		    
		// Important to force TX to execute rather than forward.
		record.setNextRouter(null);
		
		transactionStore.put(transId.toString(), record);
		//saveTransactionRecord(record);
	    }
	}
		
	/** Allows a local (same JVM as TX) client to query the progress
	 * of an executable previously placed on the transactionQueue.
	 * @param tids The ID of the transaction to query.
	 * @return The status of the executable.
	 */
	public int getLocalStatus(String tids) {  	   
	    int status = UNKNOWN_STATUS;
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord rec = (TransactionRecord)transactionStore.get(tids);
		    if (rec != null) 
			return rec.getStatus();
		}		
	    }	
	    dtmsLog.log(1, CLASS, name, "getLocalStatus",
			"Query on ID: "+tids+", Status="+status);
	    return status;    
	}
	
	/** Allows a local (same JVM as TX) client to retrieve the completed response
	 * for a previously submitted and confirmed DONE executable.
	 * @param tids The ID of the transaction to query.
	 * @return The results of the executable.
	 */
	public TRANSACTION_DONE getLocalResponse(String tids) {
	    TRANSACTION_DONE done = null;
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord rec = (TransactionRecord)transactionStore.get(tids);
		    if (rec != null) {
			if (rec.getStatus() == DONE_STATUS) 	   
			    done = rec.getResponse();
		    }
		}
	    }  
	    dtmsLog.log(1, CLASS, name, "getLocalResponse",
			"Response requested on ID: "+tids+", Response="+done); 
	    return done;
	}

	/** Allows a local (same JVM as TX) client to mark the specified transaction.
	 * for garbage collection.
	 * @param tid  The ID of the transaction to notify of completion.
	 */
	//public void deleteLocalTransaction(TransactionID tid, TRANSACTION_DONE done) {
	public void deleteLocalTransaction(String tids) { 
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord rec = (TransactionRecord)transactionStore.get(tids);
		    if (rec != null) {
			rec.setStatus(DEL_RECVD_STATUS);	   
			dtmsLog.log(1, CLASS, name, "deleteLocalTransaction",
				    "Finished marking for deletion Record: "+rec);
		    } else {
			dtmsLog.log(1, CLASS, name, "deleteLocalTransaction",
				    "Error: No such Record: "+tids);
		    }
		}
	    }
	} // (deleteLocalTransaction)
	
	/** Set the status of the specified transaction so it will be deleted.
	 * @param tid  The ID of the transaction to notify of completion.	
	 */
	//public void doneTransaction(TransactionID tid, TRANSACTION_DONE done) {
	public void doneTransaction(String tids, TRANSACTION_DONE done) { 
	    synchronized(transactionStoreLock) {
		if (transactionStore.containsKey(tids)) {
		    TransactionRecord rec = (TransactionRecord)transactionStore.get(tids);
		    if (rec != null) {
			rec.setStatus(DONE_STATUS);	   
			rec.setResponse(done);
			dtmsLog.log(1, CLASS, name, "doneTransaction",
				    "Finished setting DONE status for Record: "+rec);
		    } else {
			dtmsLog.log(1, CLASS, name, "doneTransaction",
				    "Error: No such Record: "+tids);
		    }
		}
	    }
	} // (doneTransaction)

	
	/** Returns a description of this TM.*/
	public String toString() { 
	    return "[TM:Executor: "+name+", id="+myId+"]";
	}

	/** Local TM procedure to call on receipt of the DONE (completion) response.
	 * @param record The TransactionRecord of the transaction which has completed.
	 */
	public void processDone(TransactionRecord record) {}
	
	/** Local TM procedure to call on receipt of the DEL_RECVD (delete received)  
	 *  response.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               deletion request received.
	 */
	public void processDelRecvd(TransactionRecord record) {}

	/** Local TM procedure to call on receipt of a DTMS control message.
	 * @param record The TransactionRecord of the transaction which has had a
	 *               control message.
	 * @param message The control message.
	 */
	public void processFailed(TransactionRecord record, int message) {}
	
	/** Local TM procedure to call to validate a transaction's ClientDescriptor.
	 * @param clientDescriptor Transaction's ClientDescriptor.
	 */
	public boolean checkValidClient(ClientDescriptor clientDescriptor) { return true; }
    
    } // [Executor]

    /** Class to handle receipt of transaction messages from upstream managers and
     * to respond to progress queries from upstream TMs.
     */
    public class ServerThread extends ControlThread {

	String CLASS = TransactionManager.this.CLASS+".SvrThread";

	/** The ServerSocket to handle connections.*/
	ServerSocket serverSocket;

	/** Port to bind to.*/
	int port;

	/** Create a ServerThread.
	 * @param name An ID for this thread.
	 * @param port Port to bind to.
	 */
	ServerThread(String name, int port) {
	    super(name, true);
	    this.port = port;
	}

	/** Initialize SSL ServerSocket with certs.*/
	public void initialise() {
	    dtmsLog.log(1, CLASS, getName(), "initialise",
			"Starting ServerThread: "+getName()+" on port: "+port);

	    if (upstreamSecure) {
		try {
		    KeyManager   [] kms = getKeyManagers(serverKeyFile,     serverKeyPass);
		    TrustManager [] tms = getTrustManagers(serverTrustFile, serverTrustPass);
		    
		    SSLContext context = SSLContext.getInstance("TLS");
		    dtmsLog.log(1, CLASS, getName(), "initialise", "Got Server SSL Context");
		    
		    context.init(kms, tms, null);
		    dtmsLog.log(1, CLASS, getName(), "initialise", "Server SSL Context initialized");
		    
		    SSLServerSocketFactory sf = context.getServerSocketFactory();
		    dtmsLog.log(1, CLASS, getName(), "initialise", "Got a Server SSLSocketFactory");
		    
		    serverSocket = sf.createServerSocket(port);
		    
		    //set this when client auth is required.	    
		    ((SSLServerSocket)serverSocket).setNeedClientAuth(true);
		    
		    String [] suites = ((SSLServerSocket)serverSocket).getSupportedCipherSuites();     	    
		    ((SSLServerSocket)serverSocket).setEnabledCipherSuites(suites);

		} catch (Exception e) {	
		    //System.err.println("ServerThread::FATAL:Failed to initialize: "+e);
		    e.printStackTrace();
		    System.exit(1);
		}
	    } else {
		try {
		    serverSocket = new ServerSocket(port);
		} catch (Exception e) {	
		    //System.err.println("ServerThread::FATAL:Failed to initialize: "+e);
		    e.printStackTrace();
		    System.exit(1);
		}
	    }

	} // (initialise)


	/** Runnable method. Waits for connections from upstream TMs and handles
	 * the request messages.
	 *
	 */
	public void mainTask() {
	   
	    // Listen for Client connections. Timeout regularly to check for termination signal.
	    while (canRun() && !isInterrupted()) {  
		try {
		    Socket clientSocket = serverSocket.accept();
		    //clientSocket.setSoLinger(true, 200);
		    dtmsLog.log(INFO, 1, CLASS, getName(), "mainTask",
			       "Client attached from: " + clientSocket.getInetAddress()+
			       " : " + clientSocket.getPort());

		    if (clientSocket != null) {
			dtmsLog.log(INFO, 2, CLASS, getName(), "mainTask",
				   "Creating Connection Thread for Client at: ["+
				   clientSocket.getInetAddress() + "] on local port: " + 
				   clientSocket.getLocalPort());
			
			// Name for this thread. e.g. GECKO:SERVER-(150.24.242.17):56403
			String thid = getName()+"-("+clientSocket.getInetAddress()+"):"+clientSocket.getLocalPort();
								
			// Fork off a thread to handle it
			try {
			    ServerConnectionThread connectionThread = 
				new ServerConnectionThread(thid,
							   clientSocket);
			    dtmsLog.log(INFO, 2, CLASS, getName(), "mainTask", 
				       "Starting Connection Thread for client connection: ");
			    connectionThread.start();
			} catch (IOException iox) {	
			    dtmsLog.log(ERROR, 1, CLASS, getName(), "mainTask", 
				       "Unable to generate Connection Thread for client: "+iox);
			} 
		    }

		} catch (InterruptedIOException iie1) {
		    // Socket timed-out so try again
		} catch (IOException iox) {
		    if (canRun()) 
			dtmsLog.log(ERROR, 1, CLASS, getName(), "mainTask", 
				   "Error connecting to client:", null, iox);		   
		}		
	    }
	    
	} // (mainTask)

	public void shutdown() {}
	
    } // [ServerThread]

    /** Thread to invoke a DTP server session.*/
    public class ServerConnectionThread extends ControlThread {
	
	/** The server end of the connection.*/
	IConnection connection;

	/** The request handler.*/
	DTPServer server;

	/** Client socket.*/
	Socket socket;

	/** Create a ServerConnectionThread to handle a request from
	 * an upstream TM.
	 * @param name The name of this instance.
	 * @param socket The incoming TCP client-socket.
	 */
	ServerConnectionThread(String name, 
			       Socket socket) throws IOException {
	    super(name);
	    this.socket = socket;
	   
	    connection = new SocketConnection(socket);	 
	    dtmsLog.log(INFO, 2, CLASS, getName(), "ConnectionThread",
			"Created socket connection: "+connection);
	} 

	/** Create a DTPServer to handle the request.*/
	public void initialise() {	   
	    server = new ConnectionRequestHandler();	    
	} 
	
	/** Invoke the protocol. Server will get the request to handle.*/
	public void mainTask() {
	    DistributedTransactionProtocolServerImpl protocol = 
		new DistributedTransactionProtocolServerImpl(server, 
							     connection);
	     dtmsLog.log(INFO, 2, CLASS, getName(), "main",
			 "Implementing protocol");
	     protocol.implement();	    

	}

	public void shutdown() {}
    
    } // [ServerConnectionThread]

    /** Handles the request(s) from a DTP session.*/
    public class ConnectionRequestHandler implements DTPServer {

	/** Handles the request and generates a suitable response.*/
	public ResponseTransactionMessage handleRequest(DistributedTransactionMessage request) {
	   
	    
	    // Received EXEC. Store it and reply RECVD_STATUS.
	    if (request instanceof ExecutableTransactionMessage) {
		
		ExecutableTransactionMessage executable = (ExecutableTransactionMessage)request;
				 
		TransactionID transId = executable.getId();		

		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Transaction received: "+executable);
		synchronized(transactionStoreLock) {
		    TransactionRecord record = new TransactionRecord(executable);
		    record.setStatus(RECVD_STATUS);
		    record.setArrivalTime(System.currentTimeMillis());
		    
		    // Next router is just the next item in its RT after ourself!
		    
		    ManagerID nextRouter = executable.getRoutingEntryAfter(myId);
		    if (nextRouter == null) {
			if (myId.equals(executable.getDestination()))
			    dtmsLog.log(1, CLASS, name, "handleRequest",
					"This one is for ME");
			else
			    dtmsLog.log(1, CLASS, name, "handleRequest",
					"No next hop ID specified");		   
		    }
		    
		    record.setNextRouter(nextRouter);
		    
		    transactionStore.put(executable.getId().toString(), record);
		    //saveTransactionRecord(record);
		}
		
		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Stored executable: with key: "+executable.getId()+
			    " StoreSize="+transactionStore.size());
		ResponseTransactionMessage reply = new ResponseTransactionMessage(transId); 
		reply.setStatus(RECVD_STATUS);
		return reply;
	    }

	    // Received QRY. Reply with Trans status.
	    if (request instanceof QueryTransactionMessage) {
		QueryTransactionMessage query = (QueryTransactionMessage)request;

		TransactionID transId = query.getId();		

		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Transaction received: "+query);

		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Searching store, StoreSize="+transactionStore.size()+" for Key: "+transId);
		  

		
		synchronized(transactionStoreLock) {
		    TransactionRecord record = (TransactionRecord)transactionStore.get(transId.toString());
		    
		    dtmsLog.log(1, CLASS, name, "handleRequest",
				"Found stored record: "+record);

		    int status = UNKNOWN_STATUS;	  
		    if (record != null) {
			status = record.getStatus();
			dtmsLog.log(1, CLASS, name, "handleRequest",
				    "Query: Setting response status to: "+status);			
		    } else {
			dtmsLog.log(1, CLASS, name, "handleRequest",
				    "Query: No record found");
		    }

		    ResponseTransactionMessage reply = new ResponseTransactionMessage(transId); 
		    reply.setStatus(status);
		    if (status == DONE_STATUS) {
			reply.setResponse(record.getResponse());
			dtmsLog.log(1, CLASS, name, "handleRequest",
				    "Query: Setting response to: "+reply);
		    }
		    return reply;
		}

	    }
	    
	    // Received DEL. Reply with Trans status.
	    if (request instanceof DeleteTransactionMessage) {
		DeleteTransactionMessage delete = (DeleteTransactionMessage)request;

		TransactionID transId = delete.getId();		

		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Transaction received: "+delete);

		dtmsLog.log(1, CLASS, name, "handleRequest",
			    "Searching store, StoreSize="+transactionStore.size()+" for Key: "+transId);
		
		synchronized(transactionStoreLock) {
		    
		    TransactionRecord record = (TransactionRecord)transactionStore.get(transId.toString());
		    
		    dtmsLog.log(1, CLASS, name, "handleRequest",
				"Found stored record: "+record);
		    
		    // If we have the done and get a delete we go to the DEL_REC state
		    // where we will await (Ops) for a DEL_REC from downstream		
		    if (record != null) {     
			//if (record.getStatus() == DONE_STATUS) {		  
			    record.setStatus(DEL_RECVD_STATUS);			
			    //}  
		    }
		
		    // ###### NEED TO SORT LOGIC HERE if not found then dont access the null record ######
 
		    ResponseTransactionMessage reply = new ResponseTransactionMessage(transId);		
		    if (record != null) { 
			reply.setStatus(record.getStatus());	
		    } else {
			reply.setStatus(UNKNOWN_STATUS);
		    }
		    return reply;
		}
	    }

	    // Default reply with no ID.	    
	    ResponseTransactionMessage reply = new ResponseTransactionMessage(); 
	    reply.setStatus(UNKNOWN_STATUS);
	    return reply;
	}
    } 
    
    
    /** Class to handle forwarding of transaction messages to downstream managers and
     * to monitor the status of these transactions at downstream TMs.
     */
    public class OperationThread extends ControlThread {

	String CLASS = TransactionManager.this.CLASS+".OpsThread";


	// ### These are temp will be configurable or obtained from ET.
	long execTimeout   = 60000L;
	long queryTimeout  = 60000L;
	long deleteTimeout = 60000L;

	long pollingInterval = DEFAULT_OPS_POLLING_INTERVAL;
	
	SSLSocketFactory sf;

	/** Create an OperationThread.
	 * @param name An ID for this thread.
	 */
	OperationThread(String name) {
	    super(name, true);	  
	}

	/** Initialize SSL ServerSocket with certs. We can use the methods:
	 *
	 * setClientKeyStore(File ksf), setClientTrustSTore(File tsf) etc to set these
	 * prior to starting, and they will have been set anyway by the intital
	 * configure(File configfile) call.
	 *
	 * We may want to be able to do this from supplied info later on a per message
	 * basis. 
	 *
	 */
	public void initialise() {
	  
	    dtmsLog.log(1, CLASS, getName(), "initialise",
			"Starting OpsThread: "+name);

	    dtmsLog.log(1, CLASS, getName(), "initialise",
			"Client Keyfile: "+clientKeyFile+" .. "+clientKeyPass+
			"Client TrustFile: "+clientTrustFile+" .. "+clientTrustPass);
	    	 
	    if (downstreamSecure) {
		try {
		    
		    KeyManager   [] kms = getKeyManagers(clientKeyFile,     clientKeyPass);
		    TrustManager [] tms = getTrustManagers(clientTrustFile, clientTrustPass);
		    
		    SSLContext context = SSLContext.getInstance("TLS");
		    dtmsLog.log(1, CLASS, getName(), "initialise", "Got OpsClient SSL Context");
		    
		    context.init(kms, tms, null);
		    dtmsLog.log(1, CLASS, getName(), "initialise", "OpsClient SSL Context initialized");
		    
		    sf = context.getSocketFactory();
		    //SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
		    dtmsLog.log(1, CLASS, getName(), "initialise", "Got an OpsClient SSLSocketFactory");
		    
		} catch (Exception e) {	
		    //System.err.println("OpsThread::FATAL:Failed to initialize: "+e);
		    e.printStackTrace();
		    System.exit(1);
		}
	    }

	}
	
	/** Runnable method. Inspects the TransactionStore at polling interval and 
	 * carries out appropriate actions depending on the state of the transaction.
	 *
	 */
	public void mainTask() {

	    // this is really a configurable polling interval and much more complex.
	    try { sleep(pollingInterval); } catch (InterruptedException ix) {}
	    
	    // Check each transaction record.
	    TransactionRecord            record     = null;
	    ExecutableTransactionMessage executable = null;
	    ResponseTransactionMessage   reply      = null;
	    TransactionID                transId    = null; 

	    ClientDescriptor clientDescriptor = null;

	    long lastOpTime    = 0L;
	    int  status        = 0;
	    long nextRouter    = 0L;

	   

	    dtmsLog.log(3, CLASS, getName(), "main", "Checking TransactionStore");
	    
	    synchronized(transactionStoreLock) {
		//System.err.println("Lock grabbed by: "+Thread.currentThread());
		Iterator records = transactionStore.keySet().iterator();
		while (records.hasNext()) {
		    //transId = (TransactionID)records.next();
		    
		    //record  = (TransactionRecord)transactionStore.get(transId);
		    
		    String tids = (String)records.next();
		    record  = (TransactionRecord)transactionStore.get(tids);
		
		    
		    // Extract data.
		    executable    = record.getExecutable();	
		    status        = record.getStatus();
		    lastOpTime    = record.getLastOperationTime();	
		    
		    dtmsLog.log(2, CLASS, name, "main", "Found record: "+record);
		    
		    // First we check any control options here.

		    // #### CALL checkOptions(record); ####

		    // Only the TIMEOUT is implemented for now.

		    int time = (int)(System.currentTimeMillis() - record.getArrivalTime());
		    if (executable.getOption(TIMEOUT_OPTION) == DISCARD_ON_TIMEOUT &&
			time > executable.getOption(TIME_TO_LIVE) &&
			status != DONE_STATUS &&
			status != DEL_RECVD_STATUS) {
			processFailed(record, TIMED_OUT);				
		    }

		    // Extract the client descriptor and perform any validation before sending.
		    clientDescriptor = executable.getClientDescriptor();

		    if (! checkValidClient(clientDescriptor)) {
			processFailed(record, INVALID_CLIENT);
		    }
		    
		    // This may have been altered by checkOptions()
		    status = record.getStatus();
		    
		   
		    switch (status) {
			
		    case UNKNOWN_STATUS:
			// do nothing.
			break;
		    case RECVD_STATUS:  
			// try to forward it to downstream TM. 
			// - send to remote TM via connection protocol, get response, update TR
			try {
			    reply = send_forward(record);
			    if (reply != null) {
				// Do something depending on the reply status (and response)
				// e.g. if reply status is RECVD then we can set this to FWD
				// as the next router is managing it.
		    
				if (reply.getStatus() == RECVD_STATUS) {
				    record.setStatus(FWD_STATUS);
				}
			    }
			    
			} catch (IOException iox) {
			    dtmsLog.log(1, CLASS, name, "main", 
					"Failed to send forwarding request for executable: "+iox);			
			}
			break;
		    case FWD_STATUS:
			// Query downstream TM about it.
			try {
			    reply = send_query(record); 
			    dtmsLog.log(2, CLASS, name, "main", 
					"Received Query reply: "+reply);
			    if (reply != null) {
				// Do something depending on the reply status (and response)
				// e.g. if reply status is RECVD then we can set this to FWD
				// as the next router is managing it.
				
				//System.err.println("***Ops::Query:Reply status: "+reply.getStatus());


				if (reply.getStatus() == DONE_STATUS) {
				    record.setStatus(DONE_STATUS);
				    record.setResponse(reply.getResponse());
				    // Call a local procedure -intended for Source to mark for deletion.
				    processDone(record);
				}

			    }
			    
			} catch (IOException iox) {
			    dtmsLog.log(1, CLASS, name, "main",  
					"Failed to send query request for executable: "+iox);
			}
			break;
		    case DONE_STATUS:
			
			break;
		    case CAN_RECVD_STATUS:
			// ignore for now.
			break;
		    case CAN_STATUS:
			// ignore for now.
			break;
		    case DEL_RECVD_STATUS:
			// Tell downstream TM to delete.
			try {
			    reply = send_delete(record);
			    if (reply != null) { 
				if (reply.getStatus() == DEL_RECVD_STATUS) {
				    //System.err.println("****\n*****\n*****\n*****\n*****\n*****Removing record: "+record);
				//transactionStore.remove(tids);
				    records.remove();
				    //System.err.println("****\n*****\n*****Store size: "+transactionStore.size());
				}			
			    }    
			} catch (IOException iox) {
			    dtmsLog.log(1, CLASS, name, "main",  
					"Failed to send delete request for executable: "+iox);
			}
			// 
			break;
		    case DEL_STATUS:
			// The record should be deleted immedialtely.
			records.remove();
			break;
		    default:
			
			break;
		    }
		}
		//System.err.println("Lock released by: "+Thread.currentThread());
	    }
	} // (mainTask)

	public void shutdown() {}

	/** Send an executable transaction onward to a downtream TM.
	 * @param record TransactionRecord holding details of the executable.
	 * @return The response received from the downstream TM (or null).
	 * @throws IOException If anything untoward occurs during transmission.
	 */
	public ResponseTransactionMessage send_forward(TransactionRecord record) 
	    throws IOException {
	   
	    ExecutableTransactionMessage exec = record.getExecutable();

	    dtmsLog.log(2, CLASS, getName(), "forward",
			"Forwarding executable: "+ exec.getId());

	    ManagerID router = record.getNextRouter();
	    return send(exec, router, execTimeout);

	} // (forward)
	
	/** Send an query message onward to a downtream TM.
	 * @param record TransactionRecord holding details of the executable.
	 * @return The response received from the downstream TM (or null).
	 * @throws IOException If anything untoward occurs during transmission.
	 */
	public ResponseTransactionMessage send_query(TransactionRecord record) 
	    throws IOException {

	    ExecutableTransactionMessage exec = record.getExecutable();
	   
	    TransactionID tid = exec.getId();

	    QueryTransactionMessage query = new QueryTransactionMessage(tid);

	    ManagerID router = record.getNextRouter();
	    return send(query, router, queryTimeout);

	} // (query)
	
	/** Send an executable transaction onward to a downtream TM.
	 * @param record TransactionRecord holding details of the executable.
	 * @return The response received from the downstream TM (or null).
	 * @throws IOException If anything untoward occurs during transmission.
	 */
	public ResponseTransactionMessage send_delete(TransactionRecord record) 
	    throws IOException {

	    ExecutableTransactionMessage exec = record.getExecutable();
	   
	    TransactionID tid = exec.getId();
	    
	    DeleteTransactionMessage del = new  DeleteTransactionMessage(tid);

	    ManagerID router = record.getNextRouter();
	    return send(del, router, deleteTimeout);

	} // (delete)

	/** Send the message to a TM and get the response. Uses JMSMA_ProtocolClientImpl
	 * to send the request and receive the reply.
	 * @param message A DistributedTransactionMessage to send.
	 * @param target  The ID of the router to send to.
	 * @param timeout Socket conneciton timeout (millis).
	 * @return  The response received from the downstream TM (or null).
	 * @throws IOException If anything untoward occurs during transmission.
	 */
	public ResponseTransactionMessage send(DistributedTransactionMessage message, 
					       ManagerID                     target,
					       long                          timeout)
	    throws IOException {

	    ConnectionResponseHandler handler = new ConnectionResponseHandler();

	    ConnectionInfo info = getConnectionInfo(target);

	    // No details of the target TM are known to us.
	    if (info == null) 
		throw new IOException("Cannot locate target connection details: "+target);
	    
	     dtmsLog.log(1, CLASS, getName(), "send",
			 "Using downstream router: "+info);
	     

	     IConnection connection = null;
	     if (downstreamSecure) {	
		 connection = new SocketConnection(info.host, info.port, sf);
	     } else {
		 connection = new SocketConnection(info.host, info.port);
	     }

	    DistributedTransactionProtocolClientImpl protocol =
		new DistributedTransactionProtocolClientImpl(handler, 
							     connection,
							     message,
							     timeout);

	    // This is a blocking wait so make sure the timeout is not silly !
	    protocol.implement();	
	    
	    if (handler.hResponse instanceof ResponseTransactionMessage) {
		//System.err.println("**Response received: "+handler.hResponse);
		return ((ResponseTransactionMessage)handler.hResponse);
	    } else {
		//System.err.println("**NO Valid Response received - got: "+handler.hResponse);
		return null; 
	    }
	    
	} // (send)

	/** Handles the response(s) from a DTP session.*/
	class ConnectionResponseHandler implements DTPClient {
	    
	    /** The response message received from a DTP session.*/
	    public ResponseTransactionMessage hResponse = null;
	    
	    /** Saves an error code (why?).*/
	    public int hErr;
	    
	    /** Overwritten to handle any i/o errors.
	     * @param e An exception which was thrown by the DTP implementor.
	     */
	    public void failed(Exception e) {	   	  
		System.err.println("DTP Session failed: "+e);
		hErr = 1;
	    }
	    
	    /** Overwritten to handle the response message received from a DTP session.
	     * @param response The ResponseTransactionMessage received.
	     */
	    public void handleResponse(ResponseTransactionMessage response) {	   
		//System.err.println("DTP Received response: "+response);
		hResponse = response;
	    } 
	    	
	} // [ConnectionHandler]
	
    } // [OperationThread]


    /** Class to handle deletion of completed transactions from store.
     */
    public class GarbageCollectorThread extends ControlThread {

	String CLASS = TransactionManager.this.CLASS+".GcThread";

	long cadenceInterval = DEFAULT_GC_CADENCE_INTERVAL;

	/** Create a GcThread.
	 * @param name An ID for this thread.
	 */
	GarbageCollectorThread(String name) {
	    super(name, true);	  
	}

	/** Initialize.
	 * 
	 */
	public void initialise() {
	    dtmsLog.log(1, CLASS, getName(), "initialise",
			"Starting GcThread: "+name);}
	
	/** Runnable method. Inspects the TransactionStore at cadence interval and 
	 * removes deleted transactions.
	 *
	 */
	public void mainTask() {

	    // this is really a configurable polling interval and much more complex.
	    try { sleep(cadenceInterval); } catch (InterruptedException ix) {}

	    // Check each transaction record.
	    TransactionRecord            record     = null;
	    ExecutableTransactionMessage executable = null;
	    ResponseTransactionMessage   reply      = null;
	    TransactionID                transId    = null;
	    int  status        = 0;

	    dtmsLog.log(1, CLASS, getName(), "main", "Checking TransactionStore");
	    
	    synchronized(transactionStoreLock) {
		//System.err.println("Lock grabbed by: "+Thread.currentThread());
		Iterator records = transactionStore.keySet().iterator();
		while (records.hasNext()) {
		    //transId = (TransactionID)records.next();
		    
		    //record  = (TransactionRecord)transactionStore.get(transId);
		    
		    String tids = (String)records.next();
		    record  = (TransactionRecord)transactionStore.get(tids);
				    
		    // Extract data.
		    executable    = record.getExecutable();	
		    status        = record.getStatus();

		    if (status == DEL_RECVD_STATUS) {
			dtmsLog.log(1, CLASS, getName(), "main", 
				    "Deleting record: "+ tids);
			records.remove();
		    }

		}
		//System.err.println("Lock released by: "+Thread.currentThread());
	    }
	}

	public void shutdown() {}

	
    }



    /** Launcher. Creates an appropriate class of TM and starts if required.*/
    public static void main(String[] args) {

	CommandParser parser = new CommandParser();

	try {
	    parser.parse(args);
	} catch (ParseException px) {
	    System.err.println("Error parse command args: "+px);
	    System.exit(1);
	}

	ConfigurationProperties params = parser.getMap();

	// Setup logging.
	LogHandler console = new ConsoleLogHandler(new BogstanLogFormatter());
	console.setLogLevel(3);

	int dtmsl = params.getIntValue("dtms", 0);
	Logger dtms = LogManager.getLogger("DTMS");
	dtms.setLogLevel(dtmsl);	
	dtms.addHandler(console);

	int dtpl = params.getIntValue("dtp", 0);
	Logger dtp = LogManager.getLogger("DTP");
	dtp.setLogLevel(dtpl);
	dtp.addHandler(console);
	
	TransactionManager manager = null;

	// Name.
	String name = params.getProperty("name");
	if (name == null || name.equals("")) {
	     System.err.println("Error: No name specified for TM");
	     System.exit(1);
	}

	// ID.
	long sid = params.getLongValue("id", -1L);
	if (sid == -1L) {
	    System.err.println("Error: No ID specified for TM");
	    System.exit(1);
	}

	ManagerID mid = new ManagerID(sid);

	if (params.getProperty("originator") != null) 
	    manager = createOriginatorInstance(name, mid);
	else {
	    // Port.
	    int port = params.getIntValue("port", -1);
	    if (port == -1) {
		System.err.println("Error: No port specified for TM");
		System.exit(1);
	    }
	    if (params.getProperty("router") != null) 
		manager = createRouterInstance(name, mid, port);
	    else if
		(params.getProperty("executor") != null) 
		manager = createExecutorInstance(name, mid, port);
	    else {
		System.err.println("Error: No class specified for TM (O, R or X)");
		System.exit(1);
	    }		
	}

       	// Store.
	String store = params.getProperty("store");
	if (store == null || name.equals("")) {
	    System.err.println("Error: No transaction-store specified for TM");
	    System.exit(1);
	}

	try {
	    manager.loadStore(new File(store));
	} catch (Exception ex) {
	    System.err.println("Error loading transaction-store: "+ex);
	    System.exit(1);
	}

	// Routing.
	String routing = params.getProperty("routing");
	if (routing == null || name.equals("")) {
	    System.err.println("Error: No routing-table specified for TM");
	    System.exit(1);
	}

	try {
	    manager.loadRouting(new File(routing));
	} catch (Exception ex) {
	    System.err.println("Error loading routing-table: "+ex);
	    System.exit(1);
	}

	// Config. (optional).
	String config = params.getProperty("config");
	if (config != null) {
	    try {
		manager.configure(new File(config));
	    } catch (Exception ex) {
		System.err.println("Error during configuration: "+ex);
		System.exit(1);
	    }
	}

	// Gui-start. 
	if (params.getProperty("gui") != null)
	    // GUI:    threads are started via GUI buttons.
	    manager.showGui();
	// (new TGui(manager)).display();
	else	    
	    // No-GUI: threads start automatically.
	    try {
		manager.start();
	    } catch (Exception e) {
		e.printStackTrace();
		return;
	    }
    }

    /** Display a GUI to control/monitor this TM.*/
    protected void showGui() {
	System.err.println("This is where I should really display the GUI !");
    }

    
    
} // [TransactionManager]
 

    
