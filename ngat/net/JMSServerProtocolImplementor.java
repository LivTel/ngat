package ngat.net;

import ngat.net.*;
import ngat.message.base.*;

public class JMSServerProtocolImplementor {
    
    public static final long INITIAL_TIMEOUT = 5000L;
    public static final long DELTA_TIMEOUT   = 5000L;

    IConnection connection;

    JMSServerProtocolRequestHandlerFactory factory;

    public JMSServerProtocolImplementor(IConnection connection, JMSServerProtocolRequestHandlerFactory factory) {
	this.connection = connection;
	this.factory    = factory;
    }
    
    public void implementProtocol() throws Exception {
	
	COMMAND command = (COMMAND)connection.receive();
	
	JMSServerProtocolRequestHandler handler = factory.createRequestHandler(command);

	long timeout = INITIAL_TIMEOUT;
	
	ACK iack = new ACK("jms-ia-0");
	iack.setTimeToComplete((int)(timeout + 2*DELTA_TIMEOUT));
	connection.send(iack);
	
	JMSExecutionMonitor monitor = new JMSExecutionMonitor();
	
	// This needs to be run in a seperate thread so monitor state can be updated in parallel
	try {
	    handler.handleRequest(command, monitor);
	} catch (Exception e) {
	    monitor.setCompleted(true);
	    COMMAND_DONE done = new COMMAND_DONE("test");
	    done.setSuccessful(false);
	    done.setErrorString(e.getMessage());
	    monitor.setReply(done);
	}
	
	// Keep asking the monitor how long to go,
	// wait this long and then some.
	// Eventually it will complete?
	// Maybe we need a master timeout from client?
	int ia = 0;
	while (! monitor.isCompleted()) {
	    timeout = monitor.getTimeToCompletion();

	    ACK rptack = new ACK("jms-ra-"+(++ia));
	    rptack.setTimeToComplete((int)(timeout + 2*DELTA_TIMEOUT));
	    connection.send(rptack);

	    try {
		monitor.waitUntil(timeout + DELTA_TIMEOUT);
	    } catch (InterruptedException ix) {}
	    
	}
		
	if (! monitor.isCompleted())
	    throw new Exception("Timed out after "+timeout+" waiting for handler to complete");
		
	COMMAND_DONE done = monitor.getReply();
	if (done == null)
	    throw new Exception("Null reply");
	
	connection.send(done);
	
    }
    
}
