// ConnectionParameters.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/ConnectionParameters.java,v 1.1 2008-03-06 10:47:39 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.df1.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * Class, an instance of which holds PLC connection details.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class ConnectionParameters
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: ConnectionParameters.java,v 1.1 2008-03-06 10:47:39 cjm Exp $");
	/**
	 * What sort of connection is to be used to talk to the PLC.
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_NONE
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_SERIAL
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_SOCKET
	 */
	protected int deviceId = Df1Library.INTERFACE_DEVICE_NONE;
	/**
	 * The connection name of the device.
	 * For serial devices the device to be used: i.e. /dev/ttyS0.
	 * For socket devices the IP address or hostname: i.e. supircamserialports or 192.168.1.40.
	 */
	protected String deviceName = null;
	/**
	 * The port number to use for the connection, for socket devices only.
	 */
	protected int portNumber = 0;
	
	/**
	 * Load in the connection parameters from the specified set of properties.
	 * @param properties The properties.
	 * @exception NullPointerException Thrown if a property was null.
	 * @exception Df1LibraryFormatException Thrown if the device Id was not a valid device.
	 * @exception NGATPropertyException Thrown if the port number was not an integer.
	 */
	public void loadConfig(NGATProperties properties) throws NullPointerException, 
								 Df1LibraryFormatException,
								 NGATPropertyException
	{
		String deviceIDString = null;

		// deviceId
		deviceIDString = properties.getProperty("lamp.connection.device");
		if(deviceIDString == null)
		{
			throw new NullPointerException(this.getClass().getName()+
			       ":loadConfig failed:Failed to find connection device in properties using key:"+
						       "lamp.connection.device");
		}
		deviceId = Df1Library.interfaceDeviceFromString(deviceIDString);
		// deviceName
		deviceName = properties.getProperty("lamp.connection.device_name");
		if(deviceName == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				  ":loadConfig failed:Failed to find connection device in properties using key:"+
						       "lamp.connection.device_name");
		}
		// portNumber
		portNumber = properties.getInt("lamp.connection.port_number");
	}
	
	/**
	 * Get the device ID - whether we are connecting to a serial or socket (Arcom ESS) device.
	 * @return An integer.
	 * @see #deviceId
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_NONE
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_SERIAL
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_SOCKET
	 */
	public int getDeviceID()
	{
		return deviceId;
	}
	
	/**
	 * Get the device name.
	 * @return A string.
	 * @see #deviceName
	 */
	public String getDeviceName()
	{
		return deviceName;
	}
	
	/**
	 * Get the port number.
	 * @return An integer.
	 * @see #portNumber
	 */
	public int getPortNumber()
	{
		return portNumber;
	}

	/**
	 * Open a connection (held within the Df1Library instance) using the specified connection parameters.
	 * Calls the Df1Library interfaceOpen method.
	 * @param df1 The Df1Library instance (PLC instance) to connect to.
	 * @exception Df1LibraryNativeException Thrown if the connection fails.
	 * @see #deviceId
	 * @see #deviceName
	 * @see #portNumber
	 * @see ngat.df1.Df1Library#interfaceOpen
	 */
	public void connectToController(Df1Library df1) throws Df1LibraryNativeException
	{
		df1.interfaceOpen(deviceId,deviceName,portNumber);
	}

	/**
	 * Close the previously opened connection.
	 * Calls the Df1Library interfaceClose method.
	 * @param df1 The Df1Library instance (PLC instance) to close.
	 * @exception Df1LibraryNativeException Thrown if the close fails.
	 * @see ngat.df1.Df1Library#interfaceClose
	 */
	public void closeConnection(Df1Library df1) throws Df1LibraryNativeException
	{
		df1.interfaceClose();
	}
}// end class ConnectionParameters
//
// $Log: not supported by cvs2svn $
//
