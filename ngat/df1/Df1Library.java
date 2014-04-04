// Df1Library.java
// $Header: /space/home/eng/cjm/cvs/ngat/df1/Df1Library.java,v 1.3 2013-06-28 10:39:57 cjm Exp $
package ngat.df1;

import java.lang.*;
import ngat.util.logging.*;
import ngat.serial.arcomess.*;

/**
 * This class supports an interface to a Micrologix 1100 PLC, using thr DF1 protocol either over a 
 * serial link, or over a socket conenction via an Arcom ESS.It supports an Arcom
 * ESS interface (via an instance of the ngat.serial.arcomess.ArcomESS class), which
 * allows communication with the motion controller's serial interface either directly or via
 * a Arcom ESS (Ethernet Serial Server).
 * @author Chris Mottram
 * @version $Revision$
 */
public class Df1Library
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
// df1_general.h
	/* These constants should be the same as those in df1_general.h */
	/**
	 * Logging filter bit.
	 * @see #setLogFilterLevel
	 */
	public final static int LOG_BIT_DF1          = (1<<18);
	/**
	 * Logging filter bit.
	 * @see #setLogFilterLevel
	 */
	public final static int LOG_BIT_DF1_READ_WRITE = (1<<19);

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
	/**
	 * Native method that allows the JNI layer to store in a handle map, this reference, the asssociated
	 * ArcomESS reference and it's associated native Arcom ESS interface handle.
	 * @param handle The handle to initialise.
	 */
	private native void initialiseHandle(ArcomESS handle);
	/**
	 * Native method that allows the JNI layer to release this instance from the handle map.
	 */
	private native void finaliseHandle();
// df1_general.h
	/**
	 * Native wrapper to libdf1 routine that changes the log Filter Level.
	 */
	private native void Df1_Set_Log_Filter_Level(int level);
// df1_read_write.h
	/**
	 * Native wrapper to libdf1 routine that writes a boolean value to the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native void Df1_Write_Boolean(String plcAddress,boolean value) throws Df1LibraryNativeException;
	/**
	 * Native wrapper to libdf1 routine that reads a boolean value from the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native boolean Df1_Read_Boolean(String plcAddress) throws Df1LibraryNativeException;
	/**
	 * Native wrapper to libdf1 routine that writes an integer value to the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native void Df1_Write_Integer(String plcAddress,short value) throws Df1LibraryNativeException;
	/**
	 * Native wrapper to libdf1 routine that reads an integer value from the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native short Df1_Read_Integer(String plcAddress) throws Df1LibraryNativeException;
	/**
	 * Native wrapper to libdf1 routine that writes a float value to the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native void Df1_Write_Float(String plcAddress,float value) throws Df1LibraryNativeException;
	/**
	 * Native wrapper to libdf1 routine that reads a float value from the PLC.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if it failed.
	 */
	private native float Df1_Read_Float(String plcAddress) throws Df1LibraryNativeException;

// per instance variables
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

// static code block
	/**
	 * Static code to load libdf1, the shared C library that implements an interface to the
	 * PLC.
	 */
	static
	{
		System.loadLibrary("df1");
	}

// constructor
	/**
	 * Constructor. Constructs the logger, and sets the C layers reference to it.
	 * Calls initialiseHandle to associate in the handle map the ArcomESS instance
	 * with this new instance of Df1Library, and the associated ArcomESS interface handle.
	 * @param handle An instance of ArcomESS describing the connection interface to the Micrologix controller.
	 * @exception Df1LibraryNativeException Thrown if the handle creation / mapping fails.
	 * @see #logger
	 * @see #initialiseHandle
	 * @see #initialiseLoggerReference
	 */
	public Df1Library(ArcomESS handle) throws Df1LibraryNativeException
	{
		super();
		logger = LogManager.getLogger(this);
		initialiseLoggerReference(logger);
		initialiseHandle(handle);
	}

	/**
	 * Finalize method for this class, delete JNI global references.
	 * Calls finaliseHandle to remove the Df1 Library handle from the handle map for this
	 * instance of the Df1Library class.
	 * @exception Df1LibraryNativeException Thrown if the handle destruction fails.
	 * @see #finaliseLoggerReference
	 * @see #finaliseHandle
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		finaliseHandle();
		finaliseLoggerReference();
	}

// df1_general.h
	/**
	 * Routine that changes the libdf1 logging filter level.
	 * @param level The logging filter level.
	 * @see #Df1_Set_Log_Filter_Level
	 * @see #LOG_BIT_SERIAL
	 * @see #LOG_BIT_SOCKET
	 * @see #LOG_BIT_DF1
	 * @see #LOG_BIT_DF1_READ_WRITE
	 */
	public void setLogFilterLevel(int level)
	{
		Df1_Set_Log_Filter_Level(level);
	}

	/**
	 * Print some information out on this classes logger.
	 */
	public void logDebug()
	{
		LogHandler handlerList[];
		LogHandler handler = null;

		System.out.println("Logger = "+logger);
		System.out.println("Logger log level is: "+logger.getLogLevel());
		System.out.println("Logger log filter is: "+logger.getFilter());
		handlerList = logger.getHandlers();
		for(int i = 0; i < handlerList.length; i++)
		{
			handler = handlerList[i];
			System.out.println("Logger handler "+i+" is:"+handler+" and called "+handler.getName());
			System.out.println("Logger handler "+i+" has filter:"+handler.getFilter());
			System.out.println("Logger handler "+i+" has log level:"+handler.getLogLevel());
		}
	}

// df1_read_write.h
	/**
	 * Method to write a boolean value to a PLC address.
	 * @param plcAddress The memory location to write the boolean to, in the form: B3:0/5 , T4:0.dn. 
	 * @param value The boolean value (true or false).
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the write failed.
	 * @see #Df1_Write_Boolean
	 */
	public void setBoolean(String plcAddress,boolean value) throws Df1LibraryNativeException
	{
		Df1_Write_Boolean(plcAddress,value);
	}

	/**
	 * Method to read a boolean value from a PLC address.
	 * @param plcAddress The memory location to read the boolean from, in the form: B3:0/5 , T4:0.dn.
	 * @return The boolean value at that PLC address (true or false).
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the read failed.
	 * @see #Df1_Read_Boolean
	 */
	public boolean getBoolean(String plcAddress)  throws Df1LibraryNativeException
	{
		return Df1_Read_Boolean(plcAddress);
	}

	/**
	 * Method to write an integer value to a PLC address.
	 * @param plcAddress The memory location to write the integer to, in the form: N7:1 , T4:0.PRE. 
	 * @param value The integer value. This should be a positive short.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the write failed.
	 * @see #Df1_Write_Integer
	 */
	public void setInteger(String plcAddress,short value) throws Df1LibraryNativeException
	{
		Df1_Write_Integer(plcAddress,value);
	}

	/**
	 * Method to read an integer value from a PLC address.
	 * @param plcAddress The memory location to read the integer from, in the form: N7:1 , T4:0.PRE.
	 * @return The integer value at that PLC address.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the read failed.
	 * @see #Df1_Read_Integer
	 */
	public short getInteger(String plcAddress)  throws Df1LibraryNativeException
	{
		return Df1_Read_Integer(plcAddress);
	}

	/**
	 * Method to write a float value to a PLC address.
	 * @param plcAddress The memory location to write the float to, in the form: F8:5 , F8:2. 
	 * @param value The float value.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the write failed.
	 * @see #Df1_Write_Float
	 */
	public void setFloat(String plcAddress,float value) throws Df1LibraryNativeException
	{
		Df1_Write_Float(plcAddress,value);
	}

	/**
	 * Method to read a float value from a PLC address.
	 * @param plcAddress The memory location to read the integer from, in the form: F8:5 , F8:2.
	 * @return The float value at that PLC address.
	 * @exception Df1LibraryNativeException This method throws a Df1LibraryNativeException if the read failed.
	 * @see #Df1_Read_Float
	 */
	public float getFloat(String plcAddress)  throws Df1LibraryNativeException
	{
		return Df1_Read_Float(plcAddress);
	}
}
 
//
// $Log: not supported by cvs2svn $
// Revision 1.2  2008/06/24 15:17:12  cjm
// Added logDebug method for log debugging.
//
// Revision 1.1  2008/03/06 10:46:40  cjm
// Initial revision
//
//
