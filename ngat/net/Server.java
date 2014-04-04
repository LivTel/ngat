package ngat.net;

/** Classes which wish to act as servers should implement this 
 * interface and supply implementations of the 2 methods.
 * <br><br>
 * $Id$
 */
public interface Server {

    public void listen();

    public ServerConnectionThread createServerConnectionThread(ProtocolImpl impl);

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
