package ngat.net;

import ngat.message.base.*;

public interface JMSServerProtocolRequestHandlerFactory {

    public JMSServerProtocolRequestHandler createRequestHandler(COMMAND command);

}
