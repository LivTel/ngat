package ngat.net;

import java.io.*;

/**
 * Temporary: Serial output stream, overrides standard ObjectOutputStream to
 * add information to the stream header.
 *
 * <br><br>
 * $Id$
 */
public class CryptoOutputStream extends ObjectOutputStream {

    /** Sequence number incremented each time a stream instance is created.*/
    static long sequence = 0;

    /** Unique ID for this type of stream.*/
    static final long STREAM_ID = 0xBEEFFACE;
    
    /** Create an instance of CryptoOutputStream for the client end of a connection.*/
    public CryptoOutputStream(OutputStream out) 
	throws IOException {
	super(out);
	sequence++;
    }

    /** Override to add sequence number and stream ID.*/
    protected void writeStreamHeader() throws IOException{
	super.writeStreamHeader();
	writeLong(sequence);
	writeLong(STREAM_ID);
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/02/19 11:07:23  snf
/** Initial revision
/** */
