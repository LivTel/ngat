package ngat.net;

import java.io.*;

/** Thrown when a problem occurs during an SSLFileTransfer session.*/
public class FileTransferException extends IOException {

    /** A code to indicate what occurred - 
     * These are enumerated in SSLFileTransfer and are currently all negative codes.
     */
    protected int errorCode;

    /** Create a FileTransferException with specified code and message.*/
    public FileTransferException(String message, int errorCode) {
	super(message);
	this.errorCode = errorCode;
    }

    /** Create a FileTransferException with specified code.*/
    public FileTransferException(int errorCode) {
	super();
	this.errorCode = errorCode;
    }

    /** Return the error code.*/
    public int getErrorCode() { return errorCode; }

    /** Returns a readable message.*/
    public String toString() { return super.toString()+" , Code: "+errorCode; }
    
}
