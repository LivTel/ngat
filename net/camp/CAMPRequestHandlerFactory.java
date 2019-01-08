package ngat.net.camp;

import ngat.net.*;
import ngat.message.base.*;

public interface CAMPRequestHandlerFactory {

    public CAMPRequestHandler createHandler(IConnection connection, COMMAND command);

}
