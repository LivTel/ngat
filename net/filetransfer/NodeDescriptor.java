package ngat.net.filetransfer;

public class NodeDescriptor {

    /** Node ID.*/
    public String id;

    /** Node host.*/
    public String host;

    /** Node port.*/
    public int port;

    public NodeDescriptor() {}

    public NodeDescriptor(String id, String host, int port) {
	this();
	this.id = id;
	this.host = host;
	this.port = port;
    }

    /** Returns a readable description of the node.*/
    public String toString() { return "Node: "+id+" @ "+host+":"+port; }

}
