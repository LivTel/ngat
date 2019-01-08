package ngat.net;

import java.io.*;

/**
 * Temporary: Serial output stream, overrides standard ObjectOutputStream to
 * add information to the stream header.
 *
 * <br><br>
 * $Id$
 */
public class TestOutput extends ObjectOutputStream {

    /** Sequence number incremented each time a stream instance is created.*/
    static long sequence = 0;

    /** Unique ID for this type of stream.*/
    static final long STREAM_ID = 5656;

    /** Create an instance of TestOutput.*/
    public TestOutput(OutputStream out) throws IOException {
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
/** Revision 1.1  2000/11/28 10:47:57  snf
/** Initial revision
/** */
