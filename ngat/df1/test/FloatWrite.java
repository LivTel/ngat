// FloatWrite.java
// $Header: /space/home/eng/cjm/cvs/ngat/df1/test/FloatWrite.java,v 1.1 2008-03-06 10:46:47 cjm Exp $
package ngat.df1.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

import ngat.df1.*;

/**
 * This class tests the Frodospec Df1 library, by writing a float value to a PLC.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class FloatWrite
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FloatWrite.java,v 1.1 2008-03-06 10:46:47 cjm Exp $");
	/**
	 * Which type of device to try to connect to.
	 * @see ngat.df1.Df1Library#INTERFACE_DEVICE_NONE
	 */
	protected int deviceId = Df1Library.INTERFACE_DEVICE_NONE;
	/**
	 * Name of the device to connect to.
	 * If deviceId is INTERFACE_DEVICE_SERIAL, the serial device i.e. /dev/ttyS0.
	 * If deviceId is INTERFACE_DEVICE_SOCKET, the socket hostname/IP address, 
	 * i.e. 150.204.240.115 / frodospecserialports.
	 */
	protected String deviceName = null;
	/**
	 * The port number of the device to connect to. Only used when deviceId is INTERFACE_DEVICE_SOCKET.
	 */
	protected int portNumber = 0;
	/**
	 * The PLC address of the float to write. i.e. F8:4.
	 */
	protected String plcAddress = null;
	/**
	 * The float value to write to the PLC.
	 */
	protected float value;

	/**
	 * Run method.
	 * <ul>
	 * <li>Creates a Df1Library instance.
	 * <li>Calls interfaceOpen with deviceId, deviceName, portNumber to connect to the PLC.
	 * <li>Calls setFloat with plcAddress, value to set the float value held at the specified address 
	 *     on the PLC.
	 * <li>Finally, calls interfaceClose to close the connection to the PLC.
	 * </ul>
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #value
	 * @see ngat.df1.Df1Library
	 * @see ngat.df1.Df1Library#interfaceOpen
	 * @see ngat.df1.Df1Library#interfaceClose
	 * @see ngat.df1.Df1Library#setFloat
	 */
	public void run() throws Df1LibraryNativeException
	{
		Df1Library df1 = null;

		df1 = new Df1Library();
		df1.interfaceOpen(deviceId,deviceName,portNumber);
		try
		{
			System.out.println(this.getClass().getName()+": Setting Address: "+plcAddress+
					   " to have value: "+value);
			df1.setFloat(plcAddress,(short)value);
			System.out.println(this.getClass().getName()+": Set Address: "+plcAddress+
					   " to have value: "+value);
		}
		finally
		{
			df1.interfaceClose();
		}
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #value
	 * @see #help
	 */
	private void parseArgs(String[] args)
	{
		for(int i = 0; i < args.length;i++)
		{

			if(args[i].equals("-h")||args[i].equals("-help"))
			{
				help();
				System.exit(0);
			}
			else if(args[i].equals("-plc_address")||args[i].equals("-a"))
			{
				if((i+1)< args.length)
				{
					plcAddress = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-plc_address should  have an address argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-serial")||args[i].equals("-ser"))
			{
				if((i+1)< args.length)
				{
					deviceName = args[i+1];
					deviceId = Df1Library.INTERFACE_DEVICE_SERIAL;
					i++;
				}
				else
				{
					System.err.println("-serial should  have a device argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-socket")||args[i].equals("-sock"))
			{
				if((i+2)< args.length)
				{
					deviceName = args[i+1];
					portNumber = Integer.parseInt(args[i+2]);
					deviceId = Df1Library.INTERFACE_DEVICE_SOCKET;
					i += 2;
				}
				else
				{
					System.err.println("-serial should have 2 arguments : <device> <port number>.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-value")||args[i].equals("-v"))
			{
				if((i+1)< args.length)
				{
					value = Float.parseFloat(args[i+2]);
					i++;
				}
				else
				{
					System.err.println("-value should have an argument: <true|false>.");
					System.exit(1);
				}
			}
			else
			{
				System.out.println(this.getClass().getName()+":Option not supported:"+args[i]);
				System.exit(1);
			}
		}
	}

	/**
	 * Help message routine.
	 */
	private void help()
	{
		System.out.println(this.getClass().getName()+" Help:");
		System.out.println("Options are:");
		System.out.println("\t-help");
		System.out.println("\t-serial <device name>");
		System.out.println("\t-socket <hostname> <port number>");
		System.out.println("\t-plc_address <string>");
		System.out.println("\t-value <number>");
	}

	/**
	 * Main method - entry point for the test program.
	 * Example run: 
	 * <pre>java ngat.df1.test.FloatWrite -socket frodospec1serialports 3040 -plc_address F8:1 -value 1.2</pre>
	 * <ul>
	 * <li>Calls parseArgs to parse the command line arguments.
	 * <li>Calls run method to set the float value to the PLC.
	 * </ul>
	 * @param args Command line arguments.
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #parseArgs
	 * @see #run
	 */
	public static void main(String[] args)
	{
		FloatWrite fw = new FloatWrite();

		fw.parseArgs(args);
		try
		{
			fw.run();
		}
		catch(Exception e)
		{
			System.err.println("FloatWrite run failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
//
// $Log: not supported by cvs2svn $
//
