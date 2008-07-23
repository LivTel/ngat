package ngat.net;

import ngat.message.base.*;

public class TestRequestHandler implements JMSServerProtocolRequestHandler {

    /** Implementors should handle the supplied command and report progress to
     * the supplied JMSExecutionMonitor.
     * @param command The command to process.
     * @param monitor A JMSExecutionMonitor to report progress.
     */
    public void handleRequest(COMMAND command, JMSExecutionMonitor monitor) throws Exception {

	try {Thread.sleep(25000L);} catch (InterruptedException e) {}

	COMMAND_DONE done = new COMMAND_DONE("a");
	
	boolean ok = (Math.random() > 0.2);
	if (ok) {
	    done.setSuccessful(true);
	} else {
	    done.setSuccessful(false);
	    done.setErrorString("An error occurred because it just did - so there !");
	    done.setErrorNum(666);
	}

	monitor.setReply(done);
	
    }
    
}
