
import ngat.net.*;
import java.net.*;
import ngat.message.base.*;

public class TestClient {
    
    public static void main(String args[]) {
	
	try {
	    
	    String host = args[0];
	    int    port = Integer.parseInt(args[1]);
	    
	    COMMAND command = new COMMAND("testing123...");
	    
	    JMSClientProtocolImplementor impl = new
		JMSClientProtocolImplementor();

	    impl.implementProtocol(new TestResponseHandler(),
				   new SocketConnection(host,port),
				   command);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    return;
	}
	
    }

}
