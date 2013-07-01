package ngat.net;

import ngat.message.base.*;

public interface JMSResponseHandler {

    /** Handle an exception during command execution.*/
    public void commandException(Exception e);

    /** Handle the command response.*/
    public void commandDone(COMMAND_DONE done);

}