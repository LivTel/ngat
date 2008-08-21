package ngat.message.OSS;

import ngat.message.base.*;
import ngat.net.*;

import java.util.*;
import java.io.*;

/**
 * Returns results of a TRANSACTION request.
 */
public class TRANSACTION_DONE extends COMMAND_DONE implements Serializable {
 
    /** Holds information about the originating client/server. */
    ClientDescriptor clientDesc;
    
    /** Carries authentication info for the Client/Server to identify itself. */
    protected Crypto crypto; 
   
    /** Optionally records any Exception which caused failure of the TRANSACTION.*/
    protected Exception exception;
  
    /** Create a TRANSACTION_DONE with the specified id.
     * @param id The name/id of this TRANSACTION_DONE.*/
    public TRANSACTION_DONE(String id) {
	super(id);
    }
   
    /** Create a TRANSACTION_DONE with the specified parameters.*/
    public TRANSACTION_DONE(String id, ClientDescriptor clientDesc, Crypto crypto) {
	this(id);
	this.clientDesc = clientDesc;
	this.crypto = crypto;	
	setSuccessful(true);
	setErrorNum(0);
	setErrorString(null);
    }

    /** Create a TRANSACTION_DONE with the specified parameters.*/
    public TRANSACTION_DONE(String id, ClientDescriptor clientDesc, Crypto crypto, Exception exception) {
	this(id);
	this.clientDesc = clientDesc;
	this.crypto = crypto;	
	setSuccessful(true);
	setErrorNum(0);
	setErrorString(null);
	this.exception = exception;
    }

    /** Sets the client descriptor information.*/
    public void setClientDescriptor(ClientDescriptor clientDesc) { this.clientDesc = clientDesc; }
    
    /** Returns a reference to the data carried by the PDU. */
    public ClientDescriptor getClientDescriptor() { return clientDesc;}
    
    /** Sets the authentication information.*/
    public void setCrypto(Crypto crypto) { this.crypto = crypto; }

    /** Returns the Client's authentication information. */
    public Crypto getCrypto() { return crypto;}

    /** Returns the server's identity. */
    public String getServerId() { return clientDesc.getId();}

    /** Sets an optional Exception which caused the TRANSACTION to fail.*/
    public void setException(Exception exception) { this.exception = exception; }    

    /** Returns an optional Exception which caused the TRANSACTION to fail.*/
    public Exception getException() { return exception; }
    
    /** Returns a readable description.*/
    public String toString() { return "TRANSACTION_DONE:"+getId()+" : "+
				   (getSuccessful() ? " OK" : 
				    " FAILED, error="+getErrorNum()+
				    ", message="+getErrorString()+
				    ", Exception="+getException()); }
        
}


