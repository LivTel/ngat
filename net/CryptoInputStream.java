package ngat.net;

import java.io.*;

/**
 * Temporary: Serial input stream, overrides standard ObjectInputStream to
 * collect extra information from the stream header.
 *
 * <br><br>
 * $Id$
 */
public class CryptoInputStream extends ObjectInputStream {

    /** Create an instance of TestInput.*/
    public CryptoInputStream(InputStream in)  
	throws IOException, StreamCorruptedException {
	super(in);
    }
    
    /** Override to read stream ID and sequence number.
     * @exception StreamCorruptedException If the stream ID is wrong.*/
    protected void readStreamHeader() 
	throws IOException, StreamCorruptedException {
	super.readStreamHeader(); 
	long seq = readLong();
	long streamId = readLong();	
	if (streamId != CryptoOutputStream.STREAM_ID) 
	    throw new StreamCorruptedException("Error in header, stream id incorrect:");
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**  Revision 1.1  2001/02/19 10:17:03  snf
/** Initial revision
/** */
