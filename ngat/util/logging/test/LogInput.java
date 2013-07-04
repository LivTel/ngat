package ngat.util.logging.test;

import java.text.*;
import java.util.*;
import java.sql.*;

public class LogInput {

    public static void main(String args[]) {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	SimpleDateFormat odf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleTimeZone UTC = new SimpleTimeZone(0,"Z");
        sdf.setTimeZone(UTC);
	odf.setTimeZone(UTC);
	
	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    java.sql.Connection connection =
		DriverManager.getConnection("jdbc:mysql://localhost/logs?user=root&password=appletart99ax");
	    Statement statement = connection.createStatement();

	    for (int i = 0; i < 50; i++) {
		
		long time = System.currentTimeMillis();
		
		String sys = null;
		if (Math.random() > 0.5)
		    sys = "RCS";
		else
		    sys = "OSS";
		
		String cat = null;
                if (Math.random() > 0.5)
                    cat = "TMM";
                else
                    cat = "SCA";

		String log = null;
		if (Math.random() > 0.25)
		    log = "TASK";
		else if
		    (Math.random() > 0.5)
		    log = "COM";
		else if
		    (Math.random() > 0.75)
		    log = "OPS";
		else
		    log = "TMM";

		int level = (int)(Math.random()*4)+1;
		
		String update = "insert into record (scope, system,logger,level,time,message,cat) values ('LT', '"+sys+"', '"+log+"', "+level+", '"+
		    odf.format(new java.util.Date(time))+
		    "', 'Fast Autolog message "+i+" at "+time+"', '"+cat+"');";
		
		System.err.println("Exec update [ "+update+" ]");
		
		statement.executeUpdate(update);
	    
		try {Thread.sleep(100L);} catch (InterruptedException x) {}
		
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	    
    }

}
