package ngat.net;

import ngat.message.base.*;

public interface JMSServerProtocolRequestHandler {

    /** Implementors should handle the supplied command and report progress to
     * the supplied JMSExecutionMonitor.
     * @param command The command to process.
     * @param monitor A JMSExecutionMonitor to report progress.
     */
    public void handleRequest(COMMAND command, JMSExecutionMonitor monitor) throws Exception;

}

