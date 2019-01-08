package ngat.net;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer implements Runnable {

    public static final int DEFAULT_PORT = 6666;

    InputStream in = null;

    OutputStream out = null;

    BufferedReader bin = null;

    PrintStream pout = null;

    ServerSocket server;

    Socket socket;

    String name;

    public EchoServer(String name) {
	this.name = name;
    }

    private void bind(int port) throws IOException{
	server = new ServerSocket(port);
	System.err.println("Bound to: "+port);
    }
    

    public void run() {
	
	try {
	    
	    socket = server.accept();
	    
	    System.err.println("Connection request from: "+socket.getInetAddress());
	    
	    in = socket.getInputStream();
	    bin = new BufferedReader(new InputStreamReader(in));
	    System.err.println("Input streams open");

	    out = socket.getOutputStream();
	    pout = new PrintStream(out);
	    System.err.println("Output streams open");
	    
	    String line = null;

	    while ((line = bin.readLine()) != null) {
		System.err.println("Received: ["+line+"]");	    
		pout.println("OK: "+name+" ["+line+"]");
	    }
	    
	    System.err.println("EOT");

	} catch (Exception ex) {
	    System.err.println("Error: "+ex);
	    return;
	}

    }

    public static void main(String args[]) {

	if (args.length < 2) {
	    System.err.println("Usage: EchoServer <name> <port>");
	    return;
	}

	String n = args[0];

	int p = DEFAULT_PORT;

	try {
	    p = Integer.parseInt(args[1]);
	} catch (Exception ex) {
	    System.err.println("Using default port: "+p);
	}
	
	EchoServer e = new EchoServer(n);
	
	try {
	    e.bind(p);
	} catch (Exception ex) {
	    System.err.println("Failed to bind to port: "+p+" : "+ex);
	    return;
	}
	
	(new Thread(e)).start();

    }

}
