package ngat.net.transaction;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.OSS.*;
import ngat.phase2.*;

import java.io.*;
import java.util.*;
import java.text.*;

/** Creates and starts a TransactionManager.Router.*/
public class TestRouter {

    /** The Router instance.*/
    TransactionManager.Router tmr;

    /** Configure from file layed out java.util.Properties format.
     * @param file The file containing the properties.
     * @throws IOException if there is a problem reading the file.
     * @throws IllegalArgumentException if any properties are not valid.
     */
    public void configure(File file) 
	throws IOException, IllegalArgumentException {
	ConfigurationProperties config = new ConfigurationProperties();
	FileInputStream fin = new FileInputStream(file);
	config.load(fin);
	configure(config);
    }
    
    /** Configure from supplied properties.
     * @param config The configuration properties.
     * @throws IllegalArgumentException if any properties are not valid.
     */
    public void configure(ConfigurationProperties config) 
	throws IllegalArgumentException {

	String    routerName = null;
	long      routerLong = 0L;
	String    routerHost = null;
	int       routerPort = 0;
	ManagerID routerId   = null;

	String prop = null;

	Enumeration params = config.propertyNames();
	while (params.hasMoreElements()) {
	    prop = (String)params.nextElement();
	    if (prop.startsWith("router") &&
		prop.endsWith("alias")) {
		try {
		    routerName = config.getProperty(prop);
		    routerLong = config.getLongValue(routerName+".longID");
		    routerHost = config.getProperty(routerName+".host");
		    routerPort = config.getIntValue(routerName+".port");
		    routerId   = new ManagerID(routerLong);
		    tmr.addDestination(routerName, routerId);
		    tmr.addConnectionInfo(routerId, routerHost, routerPort);
		} catch (ParseException px) {
		    throw new IllegalArgumentException("Parsing prop: "+prop);
		}
	    }
	}
    }
    
    /** Prints usage instructions to stderr.*/
    public static void usage() {
	System.err.println("Usage: java ngat.net.transaction.TestRouter <options> "+
			   "\n\n"+
			   "\n -name  <name> : The name of this Router."+
			   "\n"+
			   "\n -ident <id>   : A long ident."+ 
			   "\n"+
			   "\n -port  <port> : Port for server to listen on.");
    }

    /** Create a TestRouter.*/
    public TestRouter(String name, long lid, int port) {	
	tmr = TransactionManager.createRouterInstance(name, new ManagerID(lid), port);
    }
    
    /** Starts the Router.*/
    public void start() { tmr.start(); }

    /** Creates a TestRouter.*/
    public static void main(String args[]) {
	
	// Logging.
	Logger logger = LogManager.getLogger("DTMS");

	logger.setLogLevel(Logging.ALL);

	LogHandler console = new ConsoleLogHandler(new BogstanLogFormatter());
	console.setLogLevel(Logging.ALL);

	logger.addHandler(console);

	Logger dtp = LogManager.getLogger("DTP");
	dtp.setLogLevel(Logging.ALL);
	dtp.addHandler(console);

	CommandParser parser = new CommandParser();
	try {
	    parser.parse(args);
	} catch (ParseException px) {
	    System.err.println("Failed to parse command args: "+px);
	    usage();
	    return;
	}
	
	TestRouter test = null;
	
	ConfigurationProperties params = parser.getMap();
	
	try {
	    String name = params.getProperty("name");
	    long   lid  = params.getLongValue("ident");
	    int    port = params.getIntValue("port");
	
	    String configFileName = params.getProperty("config");

	    // Create Tester and configure.
	    test = new TestRouter(name, lid, port);
	    test.configure(new File(configFileName));
	} catch (IOException iox) {
	    System.err.println("Failed to read config file: "+iox);
	    return;
	} catch (IllegalArgumentException iax) {
	    System.err.println("Failed to parse config args: "+iax);
	    return;
	} catch (ParseException px) {
	    System.err.println("Failed to parse command args: "+px);
	    usage();
	    return;
	}

	test.start();
	
    }

}


