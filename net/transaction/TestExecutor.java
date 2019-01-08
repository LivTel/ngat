package ngat.net.transaction;

import ngat.util.logging.*;
import ngat.message.OSS.*;
import ngat.phase2.*;

/** Creates a TransactionManager.Executor.*/
public class TestExecutor {

    static final String R3_NAME = "R3-IGUANA";

    static final long R3_LONG = 985757L;
    
    static final String R3_HOST = "ltccd1";
   
    static final int R3_PORT = 5830;

    static final ManagerID R3_ID = new ManagerID(R3_LONG);

    TransactionManager.Executor tmx;

    public TestExecutor() {
	// Create the Originator.	
	tmx = TransactionManager.createExecutorInstance(R3_NAME, R3_ID, R3_PORT);

	tmx.start();

	// Start a Thread to query the tranaction store.
	QueryThread qt = new QueryThread(tmx);
	qt.start();

    }

    public static void main(String args[]) {
	
	Logger logger = LogManager.getLogger("DTMS");

	logger.setLogLevel(Logging.ALL);

	LogHandler console = new ConsoleLogHandler(new BogstanLogFormatter());
	console.setLogLevel(Logging.ALL);

	logger.addHandler(console);

	new TestExecutor();

    }

    class QueryThread extends Thread {

	TransactionManager.Executor tmx;

	QueryThread(TransactionManager.Executor tmx) {
	    super("QueryThread");
	    this.tmx = tmx;
	}

	public void run() {

	    int count = 0;
	    while (true) {
		
		try {sleep(2500L);} catch (InterruptedException ix) {}
		
		String tids = tmx.nextTransactionId();

		if (tids != null) {
		    count++;
		    TRANSACTION trans = tmx.getTransaction(tids);

		    System.err.println("********Retrieved for execution: ["+count+"] "+trans);
		    
		    try {sleep(3000L);} catch (InterruptedException ix) {}

		    TRANSACTION_DONE done = new TRANSACTION_DONE("TR:"+count);
		    done.setSuccessful(false);
		    done.setErrorNum(666);
		    done.setErrorString("Just a test not a real transaction");
		    
		    System.err.println("********Done Transaction: ["+count+"] "+trans);
		    
		    tmx.doneTransaction(tids, done);
		    
		}


	    }

	}

    }


}
