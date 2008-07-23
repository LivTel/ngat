package ngat.net;

import java.io.*;

/**
 * Temporary: Serial input stream, overrides standard ObjectInputStream to
 * collect extra information from the stream header.
 *
 * <br><br>
 * $Id: TestInput.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class TestInput extends ObjectInputStream {

    /** Create an instance of TestInput.*/
    public TestInput(InputStream in)  throws IOException, StreamCorruptedException {
	super(in);
    }

    /** Override to read stream ID and sequence number.
     * @exception StreamCorruptedException If the stream ID is wrong.*/
    protected void readStreamHeader() throws IOException, StreamCorruptedException {
	super.readStreamHeader();
	long seq = readLong();
	System.out.println("Read Stream Header: Seq: "+seq);
	long streamId = readLong();	
	System.out.println(".........Stream Id: "+streamId);
	if (streamId != TestOutput.STREAM_ID) 
	    throw new StreamCorruptedException("Error in header, stream id incorrect:");
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/28 10:48:08  snf
/** Initial revision
/** */
