// BooleanRead.java
// $Header: /space/home/eng/cjm/cvs/ngat/df1/test/BooleanRead.java,v 1.2 2008-06-24 15:45:21 cjm Exp $
package ngat.df1.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import ngat.df1.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class tests the Df1 library, by reading a boolean value from a PLC.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class BooleanRead
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: BooleanRead.java,v 1.2 2008-06-24 15:45:21 cjm Exp $");
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
	 * The logger.
	 */
	protected Logger logger = null;
	/**
	 * The filter used to filter messages sent to the logger.
	 * @see #logger
	 */
	protected BitFieldLogFilter logFilter = null;
	/**
	 * The Df1Library log level.
	 */
	protected int logLevel = Df1Library.LOG_BIT_SERIAL|Df1Library.LOG_BIT_SOCKET|
		Df1Library.LOG_BIT_DF1|Df1Library.LOG_BIT_DF1_READ_WRITE;
	/**
	 * The PLC address of the boolean to read. i.e. B3:0/5 , T4:0.dn.
	 */
	protected String plcAddress = null;

	/**
	 * Run method.
	 * <ul>
	 * <li>Creates a Df1Library instance.
	 * <li>Calls setLogFilterLevel with the logLevel.
	 * <li>Calls interfaceOpen with deviceId, deviceName, portNumber to connect to the PLC.
	 * <li>Calls getBoolean with plcAddress to get the boolean value held at the specified address on the PLC.
	 * <li>Finally, calls interfaceClose to close the connection to the PLC.
	 * </ul>
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #logLevel
	 * @see ngat.df1.Df1Library
	 * @see ngat.df1.Df1Library#setLogFilterLevel
	 * @see ngat.df1.Df1Library#interfaceOpen
	 * @see ngat.df1.Df1Library#interfaceClose
	 * @see ngat.df1.Df1Library#getBoolean
	 */
	public void run() throws Df1LibraryNativeException
	{
		Df1Library df1 = null;
		boolean value;

		df1 = new Df1Library();
		df1.setLogFilterLevel(logLevel);
		df1.interfaceOpen(deviceId,deviceName,portNumber);
		try
		{
			value = df1.getBoolean(plcAddress);
			System.out.println(this.getClass().getName()+": Address: "+plcAddress+" has value: "+value);
		}
		finally
		{
			df1.interfaceClose();
		}
	}

	/**
	 * Method to initialise the logger.
	 * @see #logger
	 * @see #logFilter
	 */
	protected void initLoggers()
	{
		LogHandler handler = null;
		BogstanLogFormatter blf = null;
		String loggerList[] = {"ngat.df1.Df1Library"};

		// setup log formatter
		blf = new BogstanLogFormatter();
		blf.setDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.SSS z"));
		// setup log handler
		handler = new ConsoleLogHandler(blf);
		handler.setLogLevel(Logging.ALL);
		// setup log filter
		logFilter = new BitFieldLogFilter(Logging.ALL);
		// Apply handler and filter to each logger in the list
		for(int i=0;i < loggerList.length;i++)
		{
			System.out.println(this.getClass().getName()+":initLoggers:Setting up logger:"+loggerList[i]);
			logger = LogManager.getLogger(loggerList[i]);
			logger.addHandler(handler);
			logger.setLogLevel(Logging.ALL);
			logger.setFilter(logFilter);
		}
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #logLevel
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
			else if(args[i].equals("-log_level")||args[i].equals("-l"))
			{
				if((i+1)< args.length)
				{
					logLevel = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-log_level should have an numeric argument.");
					System.exit(1);
				}
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
		System.out.println("\t-log_level <number>");
	}

	/**
	 * Main method - entry point for the test program.
	 * Example run: 
	 * <pre>java ngat.df1.test.BooleanRead -socket frodospec1serialports 3040 -plc_address B3:0/5</pre>
	 * <ul>
	 * <li>Calls parseArgs to parse the command line arguments.
	 * <li>Calls initLoggers to setup logging.
	 * <li>Calls run method to get the boolean value from the PLC.
	 * </ul>
	 * @param args Command line arguments.
	 * @see #deviceName
	 * @see #deviceId
	 * @see #portNumber
	 * @see #plcAddress
	 * @see #parseArgs
	 * @see #initLoggers
	 * @see #run
	 */
	public static void main(String[] args)
	{
		BooleanRead br = new BooleanRead();

		br.parseArgs(args);
		br.initLoggers();
		try
		{
			br.run();
		}
		catch(Exception e)
		{
			System.err.println("BooleanRead run failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2008/03/06 10:46:47  cjm
// Initial revision
//
//
