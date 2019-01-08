package ngat.message.OSS;

import ngat.message.base.*;
import ngat.net.*;

import java.util.*;
import java.io.*;

/**
 * Used by Server to indicate to client that their TransactionRequest is
 * being processed . A TransactionRequest_DONE will follow
 * after a time estimate contained in the inherited timeToComplete field.
 */
public class TRANSACTION_ACK extends ACK implements Serializable {

    /** Holds information about the originating client/server. */
    ClientDescriptor clientDesc;
    
    /** Carries authentication info for the Client/Server to identify itself. */
    protected Crypto crypto; 

    /** Stores a return message - may be need by GUI clients to see progress.*/
    protected String message;

    /** Create a TRANSACTION_ACK.*/
    public TRANSACTION_ACK(String id, ClientDescriptor clientDesc, Crypto crypto, String message) {
	this(id, clientDesc, crypto);
	this.message = message;
    }

    /** Create a TRANSACTION_ACK.*/
    public TRANSACTION_ACK(String id, ClientDescriptor clientDesc, Crypto crypto) {
	super(id);
	this.clientDesc = clientDesc;
	this.crypto = crypto;
    }
    
    /** Get a reference to the originator's details.*/
    public ClientDescriptor getClientDescriptor() { return clientDesc;}
    
    /** Get the originator's authentication information. */
    public Crypto getCrypto() { return crypto;}
    
    /** Get the server's identity. */
    public String getServerId() { return clientDesc.getId();}

    /** Set the message text.*/
    public void setMessage(String message) { this.message = message; }
    
    /** Return the message.*/
    public String getMessage() { return message; }
    
}


