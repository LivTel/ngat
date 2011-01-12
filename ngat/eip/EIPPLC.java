// EIPPLC.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/EIPPLC.java,v 1.6 2011-01-12 14:15:38 cjm Exp $
package ngat.eip;

import java.lang.*;
import ngat.util.logging.*;

/**
 * This class provides a native wrapper to libtuxeip. This uses CIP/EIP over ethernet to talk to various
 * PLCs (for instance Micrologix 1100 and Micrologix 1200). Each instance of this class represents (a connection
 * to) one of these PLCs, with methods for reading and writing integers, floats and booleans (bits).
 * @author Chris Mottram
 * @version $Revision: 1.6 $
 */
public class EIPPLC
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: EIPPLC.java,v 1.6 2011-01-12 14:15:38 cjm Exp $");
// eip_session.h
	/* These constants should be the same as those in eip_session.h */
	/**
	 * PLC Type : None defined.
	 * @see #open
	 */
	public final static int PLC_TYPE_NONE              = 0;
	/**
	 * PLC Type : Micrologix 1100. Also works for the Micrologix 1200.
	 * @see #open
	 */
	public final static int PLC_TYPE_MICROLOGIX1100    = 1;

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
// eip_general.h
	/**
	 * Native wrapper to libeip routine that changes the log Filter Level.
	 */
	private native void EIP_Set_Log_Filter_Level(int level);
// eip_read.h
	/**
	 * Native wrapper to libeip routine that reads a boolean value from the PLC.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native boolean EIP_Read_Boolean(String clazz,String source,EIPHandle handle,String plcAddress) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that reads a float value from the PLC.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native float EIP_Read_Float(String clazz,String source,EIPHandle handle,String plcAddress) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that reads an integer value from the PLC.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native int EIP_Read_Integer(String clazz,String source,EIPHandle handle,String plcAddress) 
		throws EIPNativeException;
// eip_session.h
	/**
	 * Native wrapper to libeip routine that creates a session handle.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Session_Handle_Create(String clazz,String source,EIPHandle handle) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that opens a session handle.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Session_Handle_Open(String clazz,String source,String hostname,int backplane,int slot,
						    int plc_type,EIPHandle handle) throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that closes a session handle.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Session_Handle_Close(String clazz,String source,EIPHandle handle) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that destroys a session handle.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Session_Handle_Destroy(String clazz,String source,EIPHandle handle) 
		throws EIPNativeException;
// eip_write.h
	/**
	 * Native wrapper to libeip routine that writes a boolean value from the PLC.
	 * NB This method is synchronized on the EIPPLC instance. This stops two threads trying to write
	 * boolean values at the same time - the underlying C implementation is a bodge (and hence not
	 * atomic) as libtuxeip can only write words rather than bits.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private synchronized native void EIP_Write_Boolean(String clazz,String source,EIPHandle handle,
							   String plcAddress,boolean value) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that writes a float value from the PLC.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Write_Float(String clazz,String source,EIPHandle handle,String plcAddress,float value) 
		throws EIPNativeException;
	/**
	 * Native wrapper to libeip routine that writes an integer value from the PLC.
	 * @param clazz A string representing the class that generated log messages originiting
	 *        from this method call. 
	 * @param source A string representing the source that generated log messages originiting
	 *        from this method call. 
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 */
	private native void EIP_Write_Integer(String clazz,String source,EIPHandle handle,String plcAddress,int value) 
		throws EIPNativeException;

// per instance variables
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

// static code block
	/**
	 * Static code to load libeip, the shared C library that implements an interface to the PLC.
	 */
	static
	{
		System.loadLibrary("eip");
	}

// constructor
	/**
	 * Constructor. Constructs the logger, and sets the C layers reference to it.
	 * @see #logger
	 * @see #initialiseLoggerReference
	 */
	public EIPPLC()
	{
		super();
		logger = LogManager.getLogger(this);
		initialiseLoggerReference(logger);
	}

	/**
	 * Finalize method for this class, delete JNI global references.
	 * @see #finaliseLoggerReference
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		finaliseLoggerReference();
	}

	/**
	 * Create a handle to a specific PLC. EIP_Session_Handle_Create is called to create
	 * an equivalent C (JNI) layer EIP_Handle_T and map it against the returned Java handle instance.
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param hostname The hostname/ip address of the PLC.
	 * @param backplane Which backplane the PLC is on: usually 1.
	 * @param slot  Which slot the PLC is on: usually 0.
	 * @param plcType The type of PLC: i.e. PLC_TYPE_MICROLOGIX1100.
	 * @return A new EIPHandle handle instance.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Create
	 * @see #PLC_TYPE_MICROLOGIX1100
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setClose
	 */
	public EIPHandle createHandle(String clazz,String source,String hostname,int backplane,int slot,int plcType) 
		throws EIPNativeException
	{
		EIPHandle handle = null;

		handle = new EIPHandle();
		handle.setHostname(hostname);
		handle.setBackplane(backplane);
		handle.setSlot(slot);
		handle.setPLCType(plcType);
		// initialise handle to not being open
		handle.setClose();
		EIP_Session_Handle_Create(clazz,source,handle);
		return handle;
	}

	/**
	 * Create a handle to a specific PLC. EIP_Session_Handle_Create is called to create
	 * an equivalent C (JNI) layer EIP_Handle_T and map it against the returned Java handle instance.
	 * @param hostname The hostname/ip address of the PLC.
	 * @param backplane Which backplane the PLC is on: usually 1.
	 * @param slot  Which slot the PLC is on: usually 0.
	 * @param plcType The type of PLC: i.e. PLC_TYPE_MICROLOGIX1100.
	 * @return A new EIPHandle handle instance.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Create
	 * @see #PLC_TYPE_MICROLOGIX1100
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setClose
	 */
	public EIPHandle createHandle(String hostname,int backplane,int slot,int plcType) throws EIPNativeException
	{
		return createHandle(this.getClass().getName(),null,hostname,backplane,slot,plcType);
	}

	/**
	 * Destroy a handle to a specific PLC. The call to EIP_Session_Handle_Destroy is 
	 * synchronised on the handle instance.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Destroy
	 */
	public void destroyHandle(String clazz,String source,EIPHandle handle) throws EIPNativeException
	{
		synchronized(handle)
		{
			EIP_Session_Handle_Destroy(clazz,source,handle);
		}
	}

	/**
	 * Destroy a handle to a specific PLC. The call to EIP_Session_Handle_Destroy is 
	 * synchronised on the handle instance.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Destroy
	 */
	public void destroyHandle(EIPHandle handle) throws EIPNativeException
	{
		destroyHandle(this.getClass().getName(),null,handle);
	}

	/**
	 * Get the value of a boolean from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Boolean is called to read the boolean value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: N7:1/0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Boolean
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public boolean getBoolean(String clazz,String source,EIPHandle handle,String plcAddress) 
		throws EIPNativeException
	{
		boolean retval;
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				retval =  EIP_Read_Boolean(clazz,source,handle,plcAddress);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
		return retval;
	}

	/**
	 * Get the value of a boolean from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Boolean is called to read the boolean value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: N7:1/0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Boolean
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public boolean getBoolean(EIPHandle handle,String plcAddress) throws EIPNativeException
	{
		return getBoolean(this.getClass().getName(),null,handle,plcAddress);
	}

	/**
	 * Get the value of a float from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Float is called to read the float value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: F8:0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Float
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public float getFloat(String clazz,String source,EIPHandle handle,String plcAddress) throws EIPNativeException
	{
		float retval;
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				retval =  EIP_Read_Float(clazz,source,handle,plcAddress);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
		return retval;
	}

	/**
	 * Get the value of a float from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Float is called to read the float value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: F8:0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Float
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public float getFloat(EIPHandle handle,String plcAddress) throws EIPNativeException
	{
		return getFloat(this.getClass().getName(),null,handle,plcAddress);
	}

	/**
	 * Get the value of an integer from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Integer is called to read the integer value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: N7:0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Integer
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public int getInteger(String clazz,String source,EIPHandle handle,String plcAddress) throws EIPNativeException
	{
		int retval;
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				retval = EIP_Read_Integer(clazz,source,handle,plcAddress);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
		return retval;
	}

	/**
	 * Get the value of an integer from the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Read_Integer is called to read the integer value from the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to read, of the form: N7:0.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Read_Integer
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public int getInteger(EIPHandle handle,String plcAddress) throws EIPNativeException
	{
		return getInteger(this.getClass().getName(),null,handle,plcAddress);
	}

	/**
	 * Routine to get an interface device number from a string representation of the device. Used for
	 * getting a device number from a string in a properties file.
	 * @param s A string representing a device number, one of:
	 * 	<ul>
	 * 	<li>NONE,
	 * 	<li>MICROLOGIX1100.
	 * 	</ul>.
	 * @return An interface device number, one of:
	 * 	<ul>
	 * 	<li>PLC_TYPE_NONE,
	 * 	<li>PLC_TYPE_MICROLOGIX1100.
	 * 	</ul>. 
	 * @exception ArcomESSFormatException If the string was not an accepted value an exception is thrown.
	 * @see #PLC_TYPE_NONE
	 * @see #PLC_TYPE_MICROLOGIX1100
	 */
	public static int plcTypeFromString(String s) throws EIPFormatException
	{
		if(s.equals("NONE"))
			return PLC_TYPE_NONE;
		if(s.equals("MICROLOGIX1100"))
			return PLC_TYPE_MICROLOGIX1100;
		throw new EIPFormatException("ngat.eip.EIPPLC","plcTypeFromString",s);
	}

	/**
	 * Set the value of a boolean in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Boolean is called to set the boolean (bit) value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: N7:1/0.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Boolean
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setBoolean(String clazz,String source,EIPHandle handle,String plcAddress,boolean value) 
		throws EIPNativeException
	{
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				EIP_Write_Boolean(clazz,source,handle,plcAddress,value);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
	}

	/**
	 * Set the value of a boolean in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Boolean is called to set the boolean (bit) value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: N7:1/0.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Boolean
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setBoolean(EIPHandle handle,String plcAddress,boolean value) throws EIPNativeException
	{
		setBoolean(this.getClass().getName(),null,handle,plcAddress,value);
	}

	/**
	 * Set the value of a float in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Float is called to set the float value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: F8:1.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Float
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setFloat(String clazz,String source,EIPHandle handle,String plcAddress,float value) 
		throws EIPNativeException
	{
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				EIP_Write_Float(clazz,source,handle,plcAddress,value);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
	}

	/**
	 * Set the value of a float in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Float is called to set the float value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: F8:1.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Float
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setFloat(EIPHandle handle,String plcAddress,float value) throws EIPNativeException
	{
		setFloat(this.getClass().getName(),null,handle,plcAddress,value);
	}

	/**
	 * Set the value of an integer in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Integer is called to set the integer value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: N7:1.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Integer
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setInteger(String clazz,String source,EIPHandle handle,String plcAddress,int value) 
		throws EIPNativeException
	{
		boolean doClose = false;

		synchronized(handle)
		{
			if(handle.isOpen() == false)
			{
				open(clazz,source,handle);
				doClose = true;
			}
			try
			{
				EIP_Write_Integer(clazz,source,handle,plcAddress,value);
			}
			finally
			{
				if(doClose)
					close(clazz,source,handle);
			}
		}
	}

	/**
	 * Set the value of an integer in the PLC.
	 * <ul>
	 * <li>Synchronise on the handle instance.
	 * <li>The the handle is <b>not</b> already open, a socket connection to the PLC is opened, 
	 *     and a conected session registered using the <b>open</b> method.
	 * <li>EIP_Write_Integer is called to set the integer value in the PLC.
	 * <li><i>finally</i>, if the handle was <b>open</b>ed at the start of the method call,
	 *     the session is unregistered and the socket connection is closed using <b>close</b>.
	 * </ul>
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @param plcAddress The PLC memory address to write, of the form: N7:1.
	 * @param value The new value to store in the PLC at the specified address.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #open
	 * @see #close
	 * @see #EIP_Write_Integer
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#isOpen
	 */
	public void setInteger(EIPHandle handle,String plcAddress,int value) throws EIPNativeException
	{
		setInteger(this.getClass().getName(),null,handle,plcAddress,value);
	}

	/**
	 * Routine that changes the libeip logging filter level.
	 * @param level The logging filter level.
	 * @see #EIP_Set_Log_Filter_Level
	 */
	public void setLogFilterLevel(int level)
	{
		EIP_Set_Log_Filter_Level(level);
	}

	/**
	 * Open a socket connection to a PLC (and register a connected session).
	 * The Java handle instance is updated to be <i>open</i> using <b>setOpen</b>.
	 * Normally this is called internally to each get/set Integer/Float/Boolean call.
	 * However, this can cause the EIP_Session_Handle_Open call to fail
	 * when a lot of open/closes are done close together (I think due to CLOSE_WAIT on the underlying
	 * TCP/IP socket). So this open method can be called explicity externally to EIPPLC so we can hold a session
	 * open whilst performing an operation that requires multiple PLC manipultations.
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Open
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setOpen
	 */
	public void open(String clazz,String source,EIPHandle handle) throws EIPNativeException
	{
		synchronized(handle)
		{
			EIP_Session_Handle_Open(clazz,source,handle.getHostname(),handle.getBackplane(),
						handle.getSlot(),handle.getPLCType(),handle);
			handle.setOpen();
		}
	}

	/**
	 * Open a socket connection to a PLC (and register a connected session).
	 * The Java handle instance is updated to be <i>open</i> using <b>setOpen</b>.
	 * Normally this is called internally to each get/set Integer/Float/Boolean call.
	 * However, this can cause the EIP_Session_Handle_Open call to fail
	 * when a lot of open/closes are done close together (I think due to CLOSE_WAIT on the underlying
	 * TCP/IP socket). So this open method can be called explicity externally to EIPPLC so we can hold a session
	 * open whilst performing an operation that requires multiple PLC manipultations.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Open
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setOpen
	 */
	public void open(EIPHandle handle) throws EIPNativeException
	{
		open(this.getClass().getName(),null,handle);
	}

	/**
	 * Close a socket connection to a PLC.
	 * The Java handle instance is updated to be <i>closed</i> using <b>setClose</b>.
	 * Normally this is called internally to each get/set Integer/Float/Boolean call.
	 * However, this can cause the EIP_Session_Handle_Open call to fail
	 * when a lot of open/closes are done close together (I think due to CLOSE_WAIT on the underlying
	 * TCP/IP socket). So this open method can be called explicity externally to EIPPLC so we can hold a session
	 * open whilst performing an operation that requires multiple PLC manipultations.
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Open
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setClose
	 */
	public void close(String clazz,String source,EIPHandle handle) throws EIPNativeException
	{
		synchronized(handle)
		{
			EIP_Session_Handle_Close(clazz,source,handle);
			handle.setClose();
		}
	}

	/**
	 * Close a socket connection to a PLC.
	 * The Java handle instance is updated to be <i>closed</i> using <b>setClose</b>.
	 * Normally this is called internally to each get/set Integer/Float/Boolean call.
	 * However, this can cause the EIP_Session_Handle_Open call to fail
	 * when a lot of open/closes are done close together (I think due to CLOSE_WAIT on the underlying
	 * TCP/IP socket). So this open method can be called explicity externally to EIPPLC so we can hold a session
	 * open whilst performing an operation that requires multiple PLC manipultations.
	 * @param handle The EIPHandle instance containing the connection information to the PLC.
	 * @exception EIPNativeException This method throws a EIPNativeException if it failed.
	 * @see #EIP_Session_Handle_Open
	 * @see ngat.eip.EIPHandle
	 * @see ngat.eip.EIPHandle#setClose
	 */
	public void close(EIPHandle handle) throws EIPNativeException
	{
		close(this.getClass().getName(),null,handle);
	}
};
/*
** $Log: not supported by cvs2svn $
** Revision 1.5  2009/02/05 11:33:39  cjm
** Swapped Bitwise for Absolute logging levels.
**
** Revision 1.4  2008/11/20 10:46:58  cjm
** Added handle synchronisation on open and close.
**
** Revision 1.3  2008/10/23 13:13:24  cjm
** Fixed LOG_BIT_ADDRESS value.
**
** Revision 1.2  2008/10/22 13:53:08  cjm
** More documentation explaining open/close call.
**
** Revision 1.1  2008/10/09 14:14:21  cjm
** Initial revision
**
*/
