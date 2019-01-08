package ngat.message.OSS;

import ngat.message.base.*;
import ngat.net.*;

import java.io.*;

/**
 * The superclass of all Client requests for access to the Phase II database.
 * Clients include: RCS, Users (PEST / OCR), Dprt, and JMU Admin. All client requests are
 * instantiated as subclasses of this class and carryout their specific behavior
 * via the overridden exec() method in their TransactionImpl subclass.
 */
public class TRANSACTION extends COMMAND implements Serializable {
 
    /** Base for error codes.*/
    public static final int ERROR_CODE_BASE = 700000;


    // There are loads of rubbish errors here which dont exist anymore.

    /** Indicates an unknown or unspecified error has occurred.*/
    public static final int UNSPECIFIED_ERROR              = ERROR_CODE_BASE + 0;

    /** Indicates that the server is temporarily paused and will not accept connections.*/
    public static final int SERVER_TEMPORARILY_PAUSED      = ERROR_CODE_BASE + 1;

    /** Indicates that the transaction/command was unknown.*/
    public static final int CLIENT_REQUEST_UNKNOWN_CLASS   = ERROR_CODE_BASE + 2;

    /** Indicates that an i/o error occurred during serialization of the command or its response.*/
    public static final int CLIENT_REQUEST_IO_ERROR        = ERROR_CODE_BASE + 3;

    /** Indicates that the specified Path was not found in the database.*/
    public static final int PATH_RESOLUTION_ERROR          = ERROR_CODE_BASE + 9;

    /** Indicates that the attempt to update a Group's done field failed.*/
    public static final int GROUP_UPDATE_ERROR             = ERROR_CODE_BASE + 11; 

    /** Indicates that the data could not be retrieved from the database.*/
    public static final int DATABASE_READ_ERROR            = ERROR_CODE_BASE + 12;

    /** Indicates that the server is about to shutdown imminently.*/
    public static final int SERVER_SHUTDOWN                = ERROR_CODE_BASE + 13;

    /** Indicates that the password supplied was invalid.*/
    public static final int CLIENT_AUTHORIZATION_FAILURE   = ERROR_CODE_BASE + 14;

    /** Indicates that some error occurred during the execution of a transaction.*/
    public static final int TRANSACTION_FAILURE            = ERROR_CODE_BASE + 15;

    /** Indicates that the key supplied for unlocking a database object was incorrect.*/
    public static final int ILLEGAL_DATABASE_ACCESS        = ERROR_CODE_BASE + 16;

    /** Indicates that an attempt was made to unlock a database object which was not actually locked.*/
    public static final int DATABASE_OBJECT_NOT_LOCKED     = ERROR_CODE_BASE + 17;

    /** Indicates that the database object could not be locked because it was already locked.*/
    public static final int DATABASE_LOCK_FAILURE          = ERROR_CODE_BASE + 18;

    /** Indicates that the server refused a connection.*/
    public static final int CONNECTION_REFUSED             = ERROR_CODE_BASE + 19;

    /** Indicates that the user is not known to the system.*/
    public static final int INVALID_USER                   = ERROR_CODE_BASE + 20;

    /** Indicates that the user is not authorized to submit the specified transaction class.*/
    public static final int TRANSACTION_AUTHORIZATION_ERROR= ERROR_CODE_BASE + 21;

    /** Indicates that an internal error occurred while reading the password table.*/
    public static final int PASSWORD_TABLE_ERROR           = ERROR_CODE_BASE + 22;

    /** Indicates that some error occurred while trying to connect to the server.*/
    public static final int CONNECTION_IO_ERROR            = ERROR_CODE_BASE + 23;

    /** Indicates a timeout on a TCP socket connection.*/
    public static final int SOCKET_TIMEOUT                 = ERROR_CODE_BASE + 24;

    /** Indicates that the TCP connection has failed.*/
    public static final int BROKEN_CONNECTION              = ERROR_CODE_BASE + 25;

    /** Indicates that the no acknowledgement was received from the server.*/
    public static final int ACK_NOT_RECEIVED               = ERROR_CODE_BASE + 26;

    /** Indicates a java serialization error occurred.*/
    public static final int SERIALIZATION_ERROR            = ERROR_CODE_BASE + 27;

    /** Indicates that no response was recieved from the server.*/
    public static final int DATA_NOT_RECEIVED              = ERROR_CODE_BASE + 28;

    /** Indicates that an error occurred while modifying a database record.*/
    public static final int DATABASE_UPDATE_ERROR          = ERROR_CODE_BASE + 29;

    /** The default message.*/
    public static final String DEFAULT_MESSAGE = "no extra information available";

    /** NOT USED.*/
    Crypto crypto;

    /** The Client Descriptor set by client app.*/
    ClientDescriptor clientDesc;

    /** Transaction execution priority.*/
    int transactionPriority;

    /** Create a TRANSACTION with id made up from the TransIdent for
     * this type of TRANSACTION with '-?' appended.*/
    public TRANSACTION() {
	this("untitled");
    }  
    
    /** Create a TRANSACTION with the specified id.
     * @param id The name/id of this TRANSACTION.*/
    public TRANSACTION(String id) {
	super(id);
	transactionPriority = 3;
    }
    
    /** Create a TRANSACTION with the specified id.
     * The extra client parameters are to be trashed soon.
     * @param id The name/id of this TRANSACTION.
     * @param clientDesc Client information.
     * @param crypt Authentication information.*/
    public TRANSACTION(String id, ClientDescriptor clientDesc, Crypto crypto) {
	super(id);
    }

    public void setTransactionPriority(int tp) {
	this.transactionPriority = tp;
    }

    public int getTransactionPriority() {
	return transactionPriority;
    }

    public void setCrypto(Crypto crypto) {
	this.crypto = crypto;
    }

    public void setClientDescriptor(ClientDescriptor clientDesc) {
	this.clientDesc = clientDesc;
    }

    public ClientDescriptor getClientDescriptor() { return clientDesc; }
    
    /** Returns an identity String - this is used for Authentication.
     * It is made up from the last part of the class's fully qualified 
     * name - e.g. ngat.message.OSS.SOMETRANS becomes 'SOMETRANS' .*/
    public String getTransIdent() {
	String name = this.getClass().getName();
	int l = name.lastIndexOf(".");
	return name.substring(l+1);
    }
   
}


