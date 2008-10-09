// PLCConnection.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/PLCConnection.java,v 1.1 2008-10-09 14:15:09 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * Class, an instance of which holds PLC connection details.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class PLCConnection
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: PLCConnection.java,v 1.1 2008-10-09 14:15:09 cjm Exp $");
	/**
	 * Basic connection log level.
	 */
	public final static int LOG_LEVEL_CONNECTION_BASIC = 1;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;
	/**
	 * The hostname of the PLC: i.e. supircamserialports or 192.168.1.40.
	 */
	protected String hostname = null;
	/**
	 * The backplane containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int backplane = 1;
	/**
	 * The slot containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int slot = 0;
	/**
	 * Which type of device to try to connect to.
	 * @see ngat.eip.EIPPLC#PLC_TYPE_MICROLOGIX1100
	 */
	protected int plcType = EIPPLC.PLC_TYPE_MICROLOGIX1100;
	/**
	 * The PLC communication object.
	 */
	protected EIPPLC plc = null;
	/**
	 * How long the conenction can be open and idle (in milliseconds) before it is closed.
	 */
	protected long idleTime = 120000L;
	/**
	 * The EIP handle (connection) to the PLC. Only non-null when the conenction is open.
	 */
	protected EIPHandle handle = null;
	/**
	 * The thread handling when the connection has been idle for long enough to close it.
	 */
	protected ConnectionIdleThread connectionIdleThread = null;


	/**
	 * Constructor. Initialise logger and plc.
	 * @see #logger
	 * @see #plc
	 */
	public PLCConnection()
	{
		super();
		logger = LogManager.getLogger(this);
		plc = new EIPPLC();
	}

	/**
	 * Load in the connection parameters from the specified set of properties.
	 * @param properties The properties.
	 * @exception NullPointerException Thrown if a property was null.
	 * @exception EIPFormatException Thrown if the plc type was not valid.
	 * @exception NGATPropertyException Thrown if the port number was not an integer.
	 * @see #hostname
	 * @see #plcType
	 * @see #backplane
	 * @see #slot
	 * @see #idleTime
	 */
	public void loadConfig(NGATProperties properties) throws NullPointerException, 
								 EIPFormatException,
								 NGATPropertyException
	{
		String plcTypeString = null;

		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":loadConfig:Started.");
		// hostname
		hostname = properties.getProperty("lamp.connection.hostname");
		if(hostname == null)
		{
			throw new NullPointerException(this.getClass().getName()+
			       ":loadConfig failed:Failed to find connection hostname in properties using key:"+
						       "lamp.connection.hostname");
		}
		// plcType
		plcTypeString = properties.getProperty("lamp.connection.plc_type");
		if(plcTypeString == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				  ":loadConfig failed:Failed to find PLC type in properties using key:"+
						       "lamp.connection.plc_type");
		}
		plcType = EIPPLC.plcTypeFromString(plcTypeString);
		// backplane
		backplane = properties.getInt("lamp.connection.backplane");
		// slot
	        slot = properties.getInt("lamp.connection.slot");
		// idle time
		idleTime = properties.getLong("lamp.connection.idle.time");
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":loadConfig:"+hostname+":Finished.");
	}
	
	/**
	 * Get the device hostname.
	 * @return A string.
	 * @see #hostname
	 */
	public String getHostname()
	{
		return hostname;
	}
	
	/**
	 * Get the backplane.
	 * @return An integer.
	 * @see #backplane
	 */
	public int getBackplane()
	{
		return backplane;
	}

	/**
	 * Get the slot.
	 * @return An integer.
	 * @see #slot
	 */
	public int getSlot()
	{
		return slot;
	}

	/**
	 * Open a connection (held within the plc instance) using the specified connection parameters.
	 * Calls the PLC createHandle method, followed by the open method.
	 * @exception EIPNativeException Thrown if the connection fails.
	 * @see #plc
	 * @see #hostname
	 * @see #backplane
	 * @see #slot
	 * @see #plcType
	 * @see #handle
	 * @see ngat.eip.EIPPLC#createHandle
	 * @see ngat.eip.EIPPLC#open
	 */
	public synchronized void openConnection() throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":openConnection:"+hostname+":Started.");
		if(handle == null)
		{
			handle = plc.createHandle(hostname,backplane,slot,plcType);
			plc.open(handle);
			connectionIdleThread = new ConnectionIdleThread();
			connectionIdleThread.start();
		}
		else
			connectionIdleThread.setBusy();	
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":openConnection:"+hostname+":Finished.");
	}

	/**
	 * Close the previously opened connection.
	 * Calls the EIPPLC close method, followed by the destroyHandle method.
	 * @exception EIPNativeException Thrown if the close fails.
	 * @see ngat.eip.EIPPLC#destroyHandle
	 */
	public synchronized void closeConnection() throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":closeConnection:"+hostname+":Started.");
		if(handle != null)
		{
			plc.close(handle);
			plc.destroyHandle(handle);
			handle = null;
		}
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":closeConnection:"+hostname+":Finished.");
	}

	/**
	 * Wrapper for PLC get boolean method.
	 * @param plcAddress The address to retrieve the boolean value from.
	 * @return The boolean value.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.	 
	 * @see #plc
	 * @see #handle
	 * @see ngat.eip.EIPPLC#getBoolean
	 */
	public synchronized boolean getBoolean(String plcAddress) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":getBoolean:"+hostname+":Started.");
		if(handle == null)
			openConnection();
		connectionIdleThread.setBusy();	
		return plc.getBoolean(handle,plcAddress);
	}

	/**
	 * Wrapper for PLC get integer method.
	 * @param plcAddress The address to retrieve the value from.
	 * @return The integer value.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.	 
	 * @see #plc
	 * @see #handle
	 * @see ngat.eip.EIPPLC#getInteger
	 */
	public synchronized int getInteger(String plcAddress) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":getInteger:"+hostname+":Started.");
		if(handle == null)
			openConnection();
		connectionIdleThread.setBusy();	
		return plc.getInteger(handle,plcAddress);
	}

	/**
	 * Wrapper for PLC set integer method.
	 * @param plcAddress The address to write the value to.
	 * @param value The value to write to the PLC.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.	 
	 * @see #plc
	 * @see #handle
	 * @see ngat.eip.EIPPLC#setInteger
	 */
	public synchronized void setInteger(String plcAddress,int value) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":setInteger:"+hostname+":Started.");
		if(handle == null)
			openConnection();
		connectionIdleThread.setBusy();	
		plc.setInteger(handle,plcAddress,value);
	}

	/**
	 * Wrapper for PLC set boolean method.
	 * @param plcAddress The address to write the value to.
	 * @param value The value to write to the PLC.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.	 
	 * @see #plc
	 * @see #handle
	 * @see ngat.eip.EIPPLC#setBoolean
	 */
	public synchronized void setBoolean(String plcAddress,boolean value) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":setBoolean:"+hostname+":Started.");
		if(handle == null)
			openConnection();
		connectionIdleThread.setBusy();	
		plc.setBoolean(handle,plcAddress,value);
	}

	/**
	 * Set the log level. Sets the logger and plc library instance log level.
	 * @param level The log level.
	 * @see #logger
	 * @see #plc
	 * @see ngat.util.logging.Logger#setLogLevel
	 * @see ngat.eip.EIPPLC#setLogFilterLevel
	 */
	public void setLogLevel(int level)
	{
		logger.setLogLevel(level);
		plc.setLogFilterLevel(level);
	}

	/**
	 * Inner class.
	 * Thread started when the handle is created/opened.
	 */
	public class ConnectionIdleThread extends Thread
	{
		/**
		 * Boolean keeping track of wether the connection is idle or not.
		 */
		protected boolean isIdle = false;

		/**
		 * Thread run method.
		 * <ul>
		 * <li>A loop is entered. 
		 * <li>isIdle is set to true. 
		 * <li>The thread then sleeps for idleTime (from the parent). 
		 * <li>The loop is exited if the isIdle is still true at the
		 *     end of the sleep (has not been reset by another thread calling setBusy).
		 * <li>When the loop is exited the connection is closed.
		 * </ul>
		 * @see #isIdle
		 * @see #closeConnection
		 */
		public void run()
		{
			boolean done = false;

			logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+":Started.");
			while(done == false)
			{
				logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+
					   ":Sleeping.");
				isIdle = true;
				try
				{
					Thread.sleep(idleTime);
				}
				catch(Exception e)
				{
				}
				logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+
					   ":Checking whether connection is idle:"+isIdle+".");
				done = (isIdle == true);
			}
			logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+
				   ":Closing connection.");
			try
			{
				closeConnection();
			}
			catch(Exception e)
			{
				logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+
					   ":Close connection failed.",e);
			}
			logger.log(LOG_LEVEL_CONNECTION_BASIC,this.getClass().getName()+":run:"+hostname+":Finished.");
		}

		/**
		 * Method called to set isIdle to false, to stop the thread terminating.
		 * @see #isIdle
		 */
		public void setBusy()
		{
			isIdle = false;	
		}
	}
}// end class PLCConnection
//
// $Log: not supported by cvs2svn $
//

