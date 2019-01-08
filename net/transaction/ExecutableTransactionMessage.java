package ngat.net.transaction;

import ngat.message.OSS.*;
import ngat.message.base.*;

import java.io.*;
import java.util.*;

/** This class transports information to perform an executable transaction 
 * at a downstream TransactionManager location. The ExecutableTransaction 
 * contains identification, prioritization and control options for downstream
 * TMs in addition to an ngat.message.OSS.TRANSACTION.
 */
public class ExecutableTransactionMessage extends DistributedTransactionMessage {
    
    /** A unique ID for this executable - this must be unique over ALL 
     * tranaction originators and per originator instance. 
     */
    protected TransactionID id;
    
    /** The ngat.message.OSS.TRANSACTION to carry.*/
    protected TRANSACTION transaction;

    /** The destination TM's ID. This is the TM which will execute the 
     * encapsulated TRANSACTION.
     */
    protected ManagerID destination;

    /** A sequential list of TM's to route the message through. The map keys 
     * are of type java.lang.Integer and reflect the routing sequence. 
     * i.e. lower number keys indicate earlier nodes in the route.
     * If empty the transaction is sent direct to the destination.
     * The entries are of class ngat.net.transaction.ManagerID.
     */
    protected SortedMap routingTable;

    /** The origin time of this message.*/
    protected long originTime;

    /** Stores various message options.*/
    protected Map options;

    /** Holds information about the originating client/server. */
    ClientDescriptor clientDesc;
  
    /** Stores the number of routers added to route using method routeVia() so far.*/
    protected transient int routerCount = 0;

    // START OF OPTIONS.

    /** The timeout option.*/
    protected int timeoutOption; 

    /** Transaction's Time to live (millis).*/
    protected long timeToLive;

    // END OF OPTIONS.

    /** Create an ExecutableTransaction with specified id.
     * @param id The transaction's unique ID.
     */
    public ExecutableTransactionMessage(TransactionID id) {
	super();
	this.id = id;
	routingTable = new TreeMap();
	options = new TreeMap();
    }
    
    /** Create an ExecutableTransaction with specified id and TRANSACTION.
     * @param id The transaction's unique ID.
     * @param transaction The ngat.message.OSS.TRANSACTION to carry.
     */
    public ExecutableTransactionMessage(TransactionID id, TRANSACTION transaction) {
	this(id);
	this.transaction = transaction;
    }
    
    /** Returns the executable's unique ID.*/
    public TransactionID getId() { return id; }

    /** Set the ngat.message.OSS.TRANSACTION to carry.
     * @param transaction The ngat.message.OSS.TRANSACTION to carry.
     */ 
    public void setTransaction(TRANSACTION transaction) { this.transaction = transaction; }
    
    /** Return The ngat.message.OSS.TRANSACTION to carry.*/
    public TRANSACTION getTransaction() { return transaction; } 
    
    /** Set the destination router ID.
     * @param destination The destination router ID.
     */
    public void setDestination(ManagerID destination) { this.destination = destination; }

    /** Return the destination router ID.*/
    public ManagerID getDestination() { return destination; }

    /** Set the origin time of this executable.
     * @param time The origin time of this executable.
     */
    public void setOriginTime(long time) { this.originTime = time; }

    /** Return the origin time of this executable.*/
    public long getOriginTime() { return originTime; }

    /** Sets transaction options for this executable.
     * @param options A set of options for this ExecutableTransactionMessage.
     */
    public void setOptions(Map options) { this.options = options; }

    /** Add the specified option to the set of options.
     * @param option A code (@see TransactionOptions) indicating a message control option.
     */
    public void addOption(int optionCode, int value) { 
	options.put(new Integer(optionCode), new Integer(value)); 
    }
    
    /** Return true if the specified option is set for this message.
     * @param option A code (@see TransactionOptions) indicating a message control option.
     */
    public int getOption(int optionCode) { 
	if (! options.containsKey((new Integer(optionCode))))
	    return -1;
	return ((Integer)options.get(new Integer(optionCode))).intValue(); 
    }

    // START OF OPTIONS

    /** Set the timeout option.
     * @param option The option value (@see TransactionOptions) to set.
     */
    public void setTimeoutOption(int option) {
	timeoutOption = option;
    }

    /** Set the timeout value.
     * @param ttl The value of the timeout (millis).
     */
    public void setTimeToLive(long ttl) {
	timeToLive = ttl;
    }
 

    // END OF OPTIONS

    /** Add a router ID to the routing table. Router IDs must be entered in order.
     * @param mid The id of the router.
     */
    public void routeVia(ManagerID mid) {	
	Integer in = new Integer(++routerCount);
	routingTable.put(in, mid);
	System.err.println("Ex: Debug: Added routing info: "+in+" = "+mid);
    }

    /** Return a set containing the ordered routing IDs. Note that an
     * Iterator over this set will yield java.util.Map.Entry ...
     */
    public Set getRoute() { 
	return routingTable.entrySet();
    }

    /** Return the Nth entry in the routing table or null if the
     * specified entry does not exist.
     * @param n The entry to extract.
     */
    public ManagerID getRoutingEntry(int n) {
	Integer in = new Integer(n);
	if (routingTable.containsKey(in))
	    return (ManagerID)routingTable.get(in);
	return null;
    }

    /** Return the next routing table entry after the specified one or
     * null if it does not exist. If The entry is the last in the table 
     * then returns the destination's ID.
     * @param mid The ManagerID of the entry after which to return an ID.
     */
    public ManagerID getRoutingEntryAfter(ManagerID mid) {
	// The entry is not in the RT. 
	System.err.println("Ex: Debug: Looking for next ID after: "+mid);
	//if (! routingTable.containsValue(mid)) {
	//  System.err.println("Ex: Debug: No such entry in RT");
	//  return null;
	//}
	ManagerID oid   = null; // other ID for search.
	Integer   indx  = null; // route index counter.
	Iterator  route = routingTable.keySet().iterator();
	while (route.hasNext()) {
	    indx = (Integer)route.next();
	    System.err.println("Ex: Debug: At index: "+indx);
	    if (indx != null) {
		oid = (ManagerID)routingTable.get(indx);
		System.err.println("Ex: Debug: Found: "+oid);
		if (oid != null && oid.equals((Object)mid)) {
		    System.err.println("Ex: Debug: Matched specified ID");
		    // Return the next in route.
		    if (route.hasNext()) {
			Integer sindx = (Integer)route.next();
			ManagerID nid = (ManagerID)routingTable.get(sindx);
			System.err.println("Ex: Debug: Returning: "+nid); 
			return nid;
			// Return destination.
		    } else {
			System.err.println("Ex: Debug: Returning: Destination: "+destination);
			return destination;
		    }
		}
	    }
	}
	// Not found.
	System.err.println("Ex: Debug: Nothing found");
	return null;
    }
    
    /** Set the client descriptor information.*/
    public void setClientDescriptor(ClientDescriptor clientDesc) { this.clientDesc = clientDesc; }
    
    /** Get a reference to the data carried by the PDU. */
    public ClientDescriptor getClientDescriptor() { return clientDesc;}
    
    /** Get the client's ident. */
    public String getClientId() { return clientDesc.getId();}
    
    /** Get the 'type' of the client. */
    public int getClientType() { return clientDesc.getType();}
    
    /** Get the client's priority. */
    public int getClientPriority() { return clientDesc.getPriority();}

    /** Get the client's RegID.*/
    public long getClientRegId() { return clientDesc.getRegId();}

    /** Return a readable description of this ExecutableTransaction.*/
    public String toString() {
	return "[ExecutableTransaction: id="+id+
	    ", Destination="+destination+
	    ", Routing="+routingTable+
	    ", Transaction="+transaction+
	    ", Client="+clientDesc+"]";
    }
    
} 
