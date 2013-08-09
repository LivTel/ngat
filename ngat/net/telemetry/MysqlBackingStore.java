/**
 * 
 */
package ngat.net.telemetry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @author eng
 *
 */
public abstract class MysqlBackingStore implements SecondaryCache {

	/**
	 * @param mysqlUrl
	 */
	public MysqlBackingStore(String mysqlUrl) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.err.println("Connect using: "+mysqlUrl);
		Connection connection = DriverManager.getConnection(mysqlUrl);
		prepareStatements(connection);
	}

	/** Subclasses should create their store and query statements here.*/
	protected abstract void prepareStatements(Connection connection) throws Exception;

	public abstract void storeStatus(StatusCategory status) throws Exception;

	/* (non-Javadoc)
	 * @see ngat.rcs.telemetry.SecondaryCache#retrieveStatus(long, long)
	 */
	public abstract List<StatusCategory> retrieveStatus(long t1, long t2) throws Exception;

	
	
}
