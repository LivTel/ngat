package ngat.net.filetransfer;

public class FileServer extends ControlThread {

    /** The actual ServerSocket.*/
    ServerSocket server;

    /** Server belongs to a node.*/    
    Node node;

    /** A List of current connections.*/
    protected List connections;

    /** Create a FileServer on a port for a given Node.*/
    public FileServer(Node node, int port) throws IOException {
	super("FileServer:"+port, true);
	this.node = node;
	serverSocket = new ServerSocket(port);
	connections  = new Vector();
    }

    public void initialise() {
	
    }

    public void mainTask() {
	Socket socket = serverSocket.accept();
	ConnectionHandler handler = new ConnectionHandler(socket);
	handler.start();
    }

    public void shutdown() {

    }

    private synchronized void addConnection(ConnectionInfo info) {
	connections.add(info);
    }

    private synchronized void removeConnection(ConnectionInfo info) {
	if (connections.contains(info)) {
	    connections.remove(info);
	}
    }


}
