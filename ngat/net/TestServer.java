
import ngat.net.*;
import java.net.*;

public class TestServer {

    public static void main(String args[]) {

	try {

	    int port = Integer.parseInt(args[0]);
	    ServerSocket server = new ServerSocket(port);

	    while (true) {
		
		Socket socket = server.accept();

		if (socket != null) {		    
		    SocketConnection connection = new SocketConnection(socket);

		    JMSServerProtocolImplementor impl = 
			new JMSServerProtocolImplementor(connection, new TestRequestHandler());
		    
		    (new Thread(new Executor(impl))).start();
		    
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
	
    }

    public static class Executor implements Runnable {

	JMSServerProtocolImplementor impl;

	Executor(JMSServerProtocolImplementor impl) {
	    this.impl = impl;
	}

	public void run() {
	    try {
		impl.implementProtocol();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

}


