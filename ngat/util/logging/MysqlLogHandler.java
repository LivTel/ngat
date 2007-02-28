package ngat.util.logging;

import java.util.*;
import java.text.*;
import java.sql.*;

public class MysqlLogHandler extends LogHandler {

    SimpleDateFormat odf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleTimeZone UTC = new SimpleTimeZone(0,"Z");

    static final String update = "insert into record (scope, system,logger,level,time,message,cat) "+
	"values (?,?,?,?,?,?,?)";

    static final long POLL = 1000L;
    
    java.sql.Connection connection;

    PreparedStatement statement;

    String scope;

    String sys;

    String url;

    public MysqlLogHandler(String scope, 
			   String sys, 
			   String host, 
			   String db, 
			   String user, 
			   String pass) throws Exception { 
	super(null);
	// truncate these now, saves time later
	this.scope = truncate(scope.toUpperCase().trim(), 5);
	this.sys   = truncate(sys.toUpperCase().trim(), 4);
	
	odf.setTimeZone(UTC);

	Class.forName("com.mysql.jdbc.Driver").newInstance();
	
	url = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pass;
	
	connection = DriverManager.getConnection(url);
	statement  = connection.prepareStatement(update);
    
    }

    public void publish(LogRecord record) {
	
	try {	

	    //System.err.println("MysqlHandler: [1] Preparing update fields...");

	    statement.setString(1, scope);
	    statement.setString(2, sys);
	    statement.setString(3, truncate(record.getLoggerName().toUpperCase().trim(),10));
	    statement.setInt(4, record.getLevel());
	    statement.setTimestamp(5, new Timestamp(record.getTime()));
	    statement.setString(6, truncate(record.getMessage().trim(), 255));
	    statement.setString(7, truncate(record.getCat().toUpperCase().trim(),10));
	    
	    //System.err.println("MysqlHandler: [2] Execute statement...["+statement+"]");

	    int rows =  statement.executeUpdate();
	    
	    //System.err.println("MysqlHandler: [3] Insert returned rowcount "+rows);
	    
	} catch (Exception e) {
	    System.err.println("MysqlHandler: Insert error: "+e);
	}
    }
    
    
    public void close() {
	try {
	    statement.close();
	    connection.close();
	} catch (Exception e) {
	  e.printStackTrace();
	}
    }

    /** Returns a truncated version of string.*/
    private String truncate(String input, int size) {
	String out = input.substring(0, Math.min(input.length(), size));
	System.err.println("truncate("+input+", "+size+") -> ["+out+"]");
	return out;
    }

}
