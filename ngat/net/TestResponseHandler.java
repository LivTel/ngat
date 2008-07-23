package ngat.net;

import ngat.message.base.*;

public class TestResponseHandler implements JMSClientProtocolResponseHandler {

    public void handleAck(ACK ack) throws Exception {
	System.err.println("TEST: received ack: "+ack);
    }

    public void handleDone(COMMAND_DONE done) throws Exception {
	System.err.println("TEST: received reply: "+done);
    }

}
