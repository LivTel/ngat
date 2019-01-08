package ngat.net.transaction;

/** Stores details of a connection.*/
public class ConnectionInfo {
    
    /** The connection host.*/
    public String host;
    
    /** The connection port.*/
    public int port;
    
    /** Create a ConnectionInfo.
     * @param host The connection host.
     * @param port The connection port.
     */
    ConnectionInfo(String host, int port) {
	this.host = host;
	this.port = port;
    }
    
    /** Returns a description of this Connection.*/
    public String toString() { 
	return "[Connection: Host="+host+", Port="+port+"]";
    }
	
} // [ConnectionInfo]
    
