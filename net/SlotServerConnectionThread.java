package ngat.net;

/** Executive for running ProtocolImplementors using a SlotBuffer
 * as the connection paradigm.
 * <br><br>
 * $Id$
 */
public class SlotServerConnectionThread extends ServerConnectionThread {

    /** Reference to the <i>server</i> which created this connection.*/
    protected SlotServer server;

    /** Creates the server's end of the communication pipes.*/
    public SlotServerConnectionThread(SlotServer server, ProtocolImpl implementor) {
	super(implementor);
	this.server = server;
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/12/12 10:49:12  snf
/** Updated to use SlotBuffer.
/** */
