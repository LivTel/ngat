package ngat.util.logging.test;

import ngat.util.logging.*;
import java.text.*;
import java.util.*;
import java.sql.*;

public class LoggerTest {

    public static void main(String args[]) {

	try {
	    
	    MysqlLogHandler handler = new MysqlLogHandler(args[0], args[1], 
							  "localhost", 
							  "logs", 
							  "root",
							  "appletart99ax");
	    handler.setLogLevel(5);
	    
	    Logger loggera = LogManager.getLogger("AAA");
	    loggera.setLogLevel(6);
	    loggera.addHandler(handler);

	    Logger loggerb = LogManager.getLogger("BBB");
            loggerb.setLogLevel(6);
            loggerb.addHandler(handler);

	    int count = 0;

	    for (int i = 0; i < 100; i++) {
		
		long time = System.currentTimeMillis();

		// pick a logger 
		Logger uselog = null;
		if (Math.random() > 0.5) 
		    uselog = loggera;
		else
		    uselog = loggerb;

		// pcik a level
		int level = (int)(Math.random()*4)+1;
		
		//log(int level, String clazz, String source, String method, String message)
		System.err.println("Sending message: "+i);
		uselog.log(level, "TEST", "Test", "test()", "Fast logger test message "+i);
		
		try {Thread.sleep(200L);} catch (InterruptedException x) {}
		
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	    
    }

}
