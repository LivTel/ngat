package ngat.net;

import ngat.message.base.*;

public interface JMSClientProtocolResponseHandler {

    public void handleAck(ACK ack) throws Exception;

    public void handleDone(COMMAND_DONE done) throws Exception;

}
