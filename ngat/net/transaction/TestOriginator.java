package ngat.net.transaction;

import ngat.util.logging.*;
import ngat.message.OSS.*;
import ngat.phase2.*;

/** Creates a TransactionManager.Originator.*/
public class TestOriginator implements TransactionResponseHandler {

    static final String R0_NAME = "R0-GECKO";
    static final String R1_NAME = "R1-SIDEWINDER";
    static final String R2_NAME = "LT_PROXY"; 
    static final String R3_NAME = "LT_OSS_TMX";

    static final long R0_LONG = 657555L;
    static final long R1_LONG = 686868L; 
    static final long R2_LONG = 555L;
    static final long R3_LONG = 888L;
    
    static final String R1_HOST = "ltccd1";
    static final String R2_HOST = "150.204.240.8";
    static final String R3_HOST = "192.168.1.30";
    
    static final int R1_PORT = 7810;
    static final int R2_PORT = 7910;
    static final int R3_PORT = 7910;

    static final ManagerID R0_ID = new ManagerID(R0_LONG);
    static final ManagerID R1_ID = new ManagerID(R1_LONG);
    static final ManagerID R2_ID = new ManagerID(R2_LONG);
    static final ManagerID R3_ID = new ManagerID(R3_LONG);

    int doneCt = 0;
    int failCt = 0;
    
    public static void main(String args[]) {
	
	LogHandler console = new ConsoleLogHandler(new BogstanLogFormatter());
	console.setLogLevel(Logging.ALL);

	// DTMS logging.
	Logger logger = LogManager.getLogger("DTMS");
	logger.setLogLevel(Logging.ALL);

	logger.addHandler(console);

	// DTP logging.
	Logger dtp =  LogManager.getLogger("DTP");
	dtp.setLogLevel(Logging.ALL);
	dtp.addHandler(console);

	new TestOriginator();
    }

    public TestOriginator() {
	

	// Create the Originator.	
	TransactionManager.Originator tmo = TransactionManager.createOriginatorInstance(R0_NAME, R0_ID);

	// Setup routing info.
	tmo.addDestination(R1_NAME, R1_ID);
	tmo.addDestination(R2_NAME, R2_ID);
	tmo.addDestination(R3_NAME, R3_ID);

	tmo.addConnectionInfo(R1_ID, R1_HOST, R1_PORT);
	tmo.addConnectionInfo(R2_ID, R2_HOST, R2_PORT);
	tmo.addConnectionInfo(R3_ID, R3_HOST, R3_PORT);

	tmo.start();

	// Create an executable.


	for (int i = 0; i < 100; i++) {

	    try { Thread.sleep(5000L+50000L*(long)Math.random()); } catch (InterruptedException ix) {}

	    // Generate a TRANSACTION.
	    ADD_GROUP addGroup = new ADD_GROUP("add-group-"+i);
	    addGroup.setGroup(new Group("gr-"+i));
	    addGroup.setProposalPath(new Path("/LT_Phase2_A2/JMU/abc-user/JL03B232"));
	    
	    // get some IDs. These 2 together should be unique over all TMs and all time.
	    TransactionID addGroupId = tmo.getNextTransactionId();
	    System.err.println("Test: Got TransactionID from tmo: "+addGroupId);
	    
	    
	    // Make an Executable
	    ExecutableTransactionMessage exec = new ExecutableTransactionMessage(addGroupId, addGroup);
	    
	    // Setup dest and routing.
	    //exec.routeVia(tmo.getDestination(R1_NAME));
	    exec.routeVia(tmo.getDestination(R2_NAME));
	    exec.setDestination(tmo.getDestination(R3_NAME));

	    // About half will be timeout capable
	    if (Math.random() > 0.8) {
		// OLD FORMAT
		exec.addOption(TransactionOptions.TIMEOUT_OPTION, TransactionOptions.DISCARD_ON_TIMEOUT);
		exec.addOption(TransactionOptions.TIME_TO_LIVE, (int)(20000L + 60000L*(long)Math.random()));
		
		// NEW FORMAT
		//exec.setTimeoutOption(TransactionOptions.DISCARD_ON_TIMEOUT);
		//exec.setTimeToLive((int)(20000L + 60000L*(long)Math.random()));
		
	    }

	    // This trans will go:  (here) -> R1-SIDEWINDER(router) -> R2-VIPER(router) -> R3-IGUANA(dest)
	    
	    // Some options to control its progress.
	    
	    // Now Submit - we are the response handler also.
	    try {
		tmo.submitTransaction(exec, this);		
	    } catch (SubmissionException sx) {
		System.err.println("Failed to submit to: "+tmo+" : "+sx);
	    }
	}
    }


    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     */
    public void submissionAccepted(TransactionID transId) {
	System.err.println("***DTRH::SubmissionReceived:: "+transId);
    }

    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     * @param response the ngat.message.OSS,TRANSACTION_DONE received.
     */
    public void responseReceived(TransactionID transId, TRANSACTION_DONE response) {
	doneCt++;
	System.err.println("***DTRH::ResponseReceived:: ["+failCt+","+doneCt+"] "+transId+" : "+response);
    }

    /** This method does nothing.
     * @param transId Unique ID of the transaction.
     * @param message A message code indicating why the transaction failed.
     * @param ex      An optional Exception which caused the failure.
     */
    public void transactionFailed(TransactionID transId, int message, Exception ex) {
	failCt++;
	System.err.println("***DTRH::TransactionFailed:: ["+failCt+","+doneCt+"] "+transId+" : "+message+" : "+ex);
    }
    
    
}
