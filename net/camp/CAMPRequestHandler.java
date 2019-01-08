package ngat.net.camp;

import ngat.net.*;
import ngat.message.base.*;

public interface CAMPRequestHandler {

    public void handleRequest();

    public long getHandlingTime();

    public void dispose();

}
