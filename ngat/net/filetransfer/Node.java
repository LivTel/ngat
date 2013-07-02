package ngat.net.filetransfer;

public class Node {

    /** List of servers this node manages.*/
    protected List servers;

    /** Map of nodes this node can see by ID.*/
    protected Map nodes;
    
    /** This node's ID.*/
    protected String id;

    /** Create a Node.*/
    public Node(String id) {
	this.id = id;
	servers = new Vector();
	nodes   = new HashMap();
    }
    
    public void loadRoutingTable(File file) throws Exception {
	ConfigurationProperties routing = new ConfigurationProperties();
	routing.load(new FileInputStream(file));
	loadRoutingTable(routing);
    }

    public void loadRoutingTable(ConfigurationProperties routing) throws Exception {

	Enumeration e = routing.propertyNames();
	while (e.hasMoreElements()) {
	    String el = e.nextElement();
	    if (el.startsWith("node")) {
		String nid = routing.getProperty(el);
		String host = routing.getProperty(nid+".host");
		int port = routing.getIntValue(nid+".port");
		NodeDescriptor nd = new NodeDescriptor(nid, host, port);
		nodes.put(nid, nd);
	    }
	}
    }

}
