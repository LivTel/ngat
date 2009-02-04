// ArcomESS.java
// $Header: /space/home/eng/cjm/cvs/ngat/serial/arcomess/ArcomESS.java,v 1.1 2009-02-04 11:26:08 cjm Exp $
package ngat.serial.arcomess;

import java.lang.*;
import ngat.util.logging.*;

/**
 * This class supports an interface to a serial device, either over a 
 * serial link, or over a socket conenction via an Arcom ESS.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class ArcomESS
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: ArcomESS.java,v 1.1 2009-02-04 11:26:08 cjm Exp $");
// arcom_ess_interface.h
	/* These constants should be the same as those in arcom_ess_interface.h */
	/**
	 * Interface device number, showing that commands will currently be sent nowhere.
	 * @see #interfaceOpen
	 */
	public final static int INTERFACE_DEVICE_NONE = 		0;
	/**
	 * Interface device number, showing that commands will currently be sent over a serial connection.
	 * @see #interfaceOpen
	 */
	public final static int INTERFACE_DEVICE_SERIAL = 		1;
	/**
	 * Interface device number, showing that commands will currently be sent over a socket (Arcom ESS) connection.
	 * @see #interfaceOpen
	 */
	public final static int INTERFACE_DEVICE_SOCKET = 		2;
//internal C layer initialisation
	/**
	 * Native method that allows the JNI layer to store a reference to this Class's logger.
	 * @param logger The logger for this class.
	 */
	private native void initialiseLoggerReference(Logger logger);
	/**
	 * Native method that allows the JNI layer to release the global reference to this Class's logger.
	 */
	private native void finaliseLoggerReference();
// arcom_ess_general.h
	/**
	 * Native wrapper to libarcom_ess routine that changes the log Filter Level.
	 */
	private native void Arcom_ESS_Set_Log_Filter_Level(int level);
// arcom_ess_interface.h
	/**
	 * Native wrapper to libarcom_ess routine that create a Arcom ESS handle and associates it with
	 * this instance of the ArcomESS class.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Interface_Handle_Create() throws ArcomESSNativeException;
	/**
	 * Native wrapper to libarcom_ess routine that destroys the Arcom ESS handle associated with
	 * this instance of the ArcomESS class.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Interface_Handle_Destroy() throws ArcomESSNativeException;
	/**
	 * Native wrapper to libarcom_ess routine that opens the selected interface device.
	 * @param interfaceDevice The interface device to use to communicate with the PLC.
	 * 	One of: INTERFACE_DEVICE_NONE, INTERFACE_DEVICE_SERIAL, INTERFACE_DEVICE_SOCKET.
	 * @param deviceName The name of the device. For devices of type SERIAL, this will be something like
	 *        "/dev/ttyS0". For devices of type SOCKET, this should be a valid IP address or hostname 
	 *        e.g. 150.204.240.115 or frodospec1serialports.
	 * @param portNumber The port number of the SOCKET device - not used for serial devices.
	 * @see #INTERFACE_DEVICE_NONE
	 * @see #INTERFACE_DEVICE_TEXT
	 * @see #INTERFACE_DEVICE_PCI
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Interface_Open(int interfaceDevice,String deviceName,int portNumber)
		throws ArcomESSNativeException;
	/**
	 * Native wrapper to libarcom_ess routine that closes the selected interface device.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Interface_Close() throws ArcomESSNativeException;
	/**
	 * Native wrapper to libarcom_ess routine that reads from the serial device.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native String Arcom_ESS_Interface_Read() throws ArcomESSNativeException;
	/**
	 * Native wrapper to libarcom_ess routine that writes to the serial device.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Interface_Write(String s) throws ArcomESSNativeException;
// arcom_ess_serial.h
	/**
	 * Native wrapper to libarcom_ess routine that sets the baud rate when communicating with the serial device
	 * directly (rather than via the Arcom ESS).
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if it failed.
	 */
	private native void Arcom_ESS_Serial_Baud_Rate_Set(int br) throws ArcomESSNativeException;

// per instance variables
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

// static code block
	/**
	 * Static code to load libarcom_ess, the shared C library that implements an interface to the
	 * serial device via an Arcom ESS.
	 */
	static
	{
		System.loadLibrary("arcom_ess");
	}

// constructor
	/**
	 * Constructor. Constructs the logger, and sets the C layers reference to it.
	 * Calls Arcom_ESS_Interface_Handle_Create to create an handle and associate in the handle map
	 * with this new instance of ArcomESS.
	 * @exception ArcomESSNativeException Thrown if the handle creation / mapping fails.
	 * @see #logger
	 * @see #initialiseLoggerReference
	 * @see #Arcom_ESS_Interface_Handle_Create
	 */
	public ArcomESS() throws ArcomESSNativeException
	{
		super();
		logger = LogManager.getLogger(this);
		initialiseLoggerReference(logger);
		Arcom_ESS_Interface_Handle_Create();
	}

	/**
	 * Finalize method for this class, delete JNI global references.
	 * Calls Arcom_ESS_Interface_Handle_Destroy to remove the handle from the handle map for this
	 * instance of the ArcomESS class.
	 * @exception ArcomESSNativeException Thrown if the handle destruction fails.
	 * @see #finaliseLoggerReference
	 * @see #Arcom_ESS_Interface_Handle_Destroy
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		Arcom_ESS_Interface_Handle_Destroy();
		finaliseLoggerReference();
	}

// arcom_ess_general.h
	/**
	 * Routine that changes the libarcom_ess logging filter level.
	 * @param level The logging filter level.
	 * @see #Arcom_ESS_Set_Log_Filter_Level
	 */
	public void setLogFilterLevel(int level)
	{
		Arcom_ESS_Set_Log_Filter_Level(level);
	}

// arcom_ess_interface.h
	/**
	 * Routine to open the interface. 
	 * @param interfaceDevice The interface device to use to communicate with the serial device.
	 * 	One of: INTERFACE_DEVICE_NONE, INTERFACE_DEVICE_SERIAL, INTERFACE_DEVICE_SOCKET.
	 * @param deviceName The name of the device. For devices of type SERIAL, this will be something like
	 *        "/dev/ttyS0". For devices of type SOCKET, this should be a valid IP adddress or hostname 
	 *        (e.g. 150.204.240.115 ot frodospec1serialports).
	 * @param portNumber The port number of the SOCKET device - not used for serial devices.
	 * @exception ArcomESSNativeException This method throws an ArcomESSNativeException if the device could
	 * 	not be opened.
	 * @see #INTERFACE_DEVICE_NONE
	 * @see #INTERFACE_DEVICE_SERIAL
	 * @see #INTERFACE_DEVICE_SOCKET
	 * @see #interfaceClose
	 * @see #Df1_Interface_Open
	 */
	public void interfaceOpen(int interfaceDevice,String deviceName,int portNumber) 
		throws ArcomESSNativeException
	{
		Arcom_ESS_Interface_Open(interfaceDevice,deviceName,portNumber);
	}

	/**
	 * Routine to close the interface opened with <b>interfaceOpen</b>.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if the device could
	 * 	not be closed.
	 * @see #interfaceOpen
	 * @see #Arcom_ESS_Interface_Close
	 */
	public void interfaceClose() throws ArcomESSNativeException
	{
		Arcom_ESS_Interface_Close();
	}

	/**
	 * Routine to read a string from the opened interface.
	 * @return A string read from the serial device.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if the read failed.
	 * @see #interfaceWrite
	 * @see #Arcom_ESS_Interface_Read
	 */
	public String interfaceRead() throws ArcomESSNativeException
	{
		return Arcom_ESS_Interface_Read();
	}

	/**
	 * Routine to write a string to the opened interface.
	 * @param s A string to write to  the serial device.
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if the write failed.
	 * @see #interfaceWrite
	 * @see #Arcom_ESS_Interface_Read
	 */
	public void interfaceWrite(String s) throws ArcomESSNativeException
	{
		Arcom_ESS_Interface_Write(s);
	}

	/**
	 * Routine to get an interface device number from a string representation of the device. Used for
	 * getting a device number from a string in a properties file.
	 * @param s A string representing a device number, one of:
	 * 	<ul>
	 * 	<li>INTERFACE_DEVICE_NONE,
	 * 	<li>INTERFACE_DEVICE_SERIAL,
	 * 	<li>INTERFACE_DEVICE_SOCKET.
	 * 	</ul>.
	 * @return An interface device number, one of:
	 * 	<ul>
	 * 	<li>INTERFACE_DEVICE_NONE,
	 * 	<li>INTERFACE_DEVICE_SERIAL,
	 * 	<li>INTERFACE_DEVICE_SOCKET.
	 * 	</ul>. 
	 * @exception ArcomESSFormatException If the string was not an accepted value an exception is thrown.
	 * @see #INTERFACE_DEVICE_NONE
	 * @see #INTERFACE_DEVICE_SERIAL
	 * @see #INTERFACE_DEVICE_SOCKET
	 */
	public static int interfaceDeviceFromString(String s) throws ArcomESSFormatException
	{
		if(s.equals("INTERFACE_DEVICE_NONE"))
			return INTERFACE_DEVICE_NONE;
		if(s.equals("INTERFACE_DEVICE_SERIAL"))
			return INTERFACE_DEVICE_SERIAL;
		if(s.equals("INTERFACE_DEVICE_SOCKET"))
			return INTERFACE_DEVICE_SOCKET;
		throw new ArcomESSFormatException("ngat.serial.arcomess.ArcomESS","interfaceDeviceFromString",s);
	}

// arcom_ess_serial.h
	/**
	 * Routine to set the baud rate. This baud rate is only used for directly connected devices
	 * (INTERFACE_DEVICE_SERIAL) if using the Arcom ESS the baud rate has to be configured on the ESS itself.
	 * @param br A baud rate. Notmally 19200 or 9600 (it has to be one of a set number to suceed).
	 * @exception ArcomESSNativeException This method throws a ArcomESSNativeException if the routine failed.
	 * @see #Arcom_ESS_Serial_Baud_Rate_Set
	 */
	public void setBaudRate(int br) throws ArcomESSNativeException
	{
		Arcom_ESS_Serial_Baud_Rate_Set(br);
	}

}
 
//
// $Log: not supported by cvs2svn $
//
