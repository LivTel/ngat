// PlcReadWriteTest.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/test/PlcReadWriteTest.java,v 1.2 2009-02-05 11:33:57 cjm Exp $
package ngat.eip.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class tests the ngat.eip library, by reading/writing an integer value to/from a PLC a large number of times.
 * @author Chris Mottram
 * @version $Revision$
 */
public class PlcReadWriteTest
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Which type of device to try to connect to.
	 * @see ngat.eip.EIPPLC#PLC_TYPE_MICROLOGIX1100
	 */
	protected int plcType = EIPPLC.PLC_TYPE_MICROLOGIX1100;
	/**
	 * The hostname of the device to connect to.
	 * i.e. 150.204.240.115 / frodospecserialports.
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
	 * The logger.
	 */
	protected Logger logger = null;
	/**
	 * The log level.
	 */
	protected int logLevel = Logging.VERBOSITY_VERY_VERBOSE;
	/**
	 * The PLC address of the integer to write. i.e. N7:1.
	 */
	protected String plcAddress = null;
	/**
	 * The number of times to read/write to the specified PLC address.
	 */
	protected int count = 0;
	/**
	 * The amount of time to sleep for inbetween calls, in milliseconds.
	 */
	protected int sleepTime = 0;

	/**
	 * Run method.
	 * <ul>
	 * <li>Creates an EIPPLC instance.
	 * <li>Calls setLogFilterLevel with the logLevel.
	 * <li>Calls interfaceOpen with deviceId, deviceName, portNumber to connect to the EIPPLC 
	 *     (and therefore the PLC).
	 * <li>Calls setLogFilterLevel with the logLevel.
	 * <li>Calls setInteger with plcAddress to set the value held at the specified address on the PLC.
	 * <li>Finally, calls interfaceClose to close the connection to the EIPPLC.
	 * </ul>
	 * @exception EIPNativeException Thrown if an error occurs initialising,opening or 
	 *            closing the EIPPLC interface.
	 * @see #hostname
	 * @see #plcType
	 * @see #backplane
	 * @see #slot
	 * @see #plcAddress
	 * @see #logLevel
	 * @see #count
	 * @see #sleepTime
	 * @see ngat.eip.EIPPLC
	 * @see ngat.eip.EIPPLC#setLogFilterLevel
	 * @see ngat.eip.EIPPLC#createHandle
	 * @see ngat.eip.EIPPLC#destroyHandle
	 * @see ngat.eip.EIPPLC#setInteger
	 * @see ngat.eip.EIPHandle
	 */
	public void run() throws EIPNativeException, InterruptedException
	{
		EIPPLC plc = null;
		EIPHandle handle = null;
		int value = 0;

		logger.log(1,this.getClass().getName()+":run:Creating PLC instance.");
		plc = new EIPPLC();
		plc.setLogFilterLevel(logLevel);
		logger.log(1,this.getClass().getName()+":run:Creating PLC Handle instance to hostname:"+hostname+".");
		handle = plc.createHandle(hostname,backplane,slot,plcType);
		try
		{
			logger.log(1,this.getClass().getName()+":run:Initialising integer to 0.");
			plc.setInteger(handle,plcAddress,0);
			for(int i = 0; i < count; i++)
			{
				logger.log(1,this.getClass().getName()+":run:Reading PLC at hostname:"+
					   hostname+" Address: "+plcAddress+".");
				value = plc.getInteger(handle,plcAddress);
				logger.log(1,this.getClass().getName()+":run:PLC at hostname:"+
					   hostname+" Address: "+plcAddress+" contains value "+value+".");
				value++;
				logger.log(1,this.getClass().getName()+":run:PLC at hostname:"+
					   hostname+" Address: "+plcAddress+" writing value "+value+".");
				plc.setInteger(handle,plcAddress,value);
				logger.log(1,this.getClass().getName()+":run:PLC at hostname:"+
					   hostname+" Address: "+plcAddress+" set to value: "+value);
				if(sleepTime > 0)
				{
					logger.log(1,this.getClass().getName()+":run:Sleeping for: "+
						   sleepTime+" milliseconds.");
					Thread.sleep(sleepTime);
				}
			}
		}
		finally
		{
			logger.log(1,this.getClass().getName()+":run:Destroying handle.");
			plc.destroyHandle(handle);
		}
	}

	/**
	 * Method to initialise the logger.
	 * @see #logger
	 */
	protected void initLoggers()
	{
		LogHandler handler = null;
		BogstanLogFormatter blf = null;
		String loggerList[] = {"ngat.eip.EIPPLC"};

		// setup log formatter
		blf = new BogstanLogFormatter();
		blf.setDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.SSS z"));
		// setup log handler
		handler = new ConsoleLogHandler(blf);
		handler.setLogLevel(Logging.ALL);
		// Apply handler and filter to each logger in the list
		for(int i=0;i < loggerList.length;i++)
		{
			System.out.println(this.getClass().getName()+":initLoggers:Setting up logger:"+loggerList[i]);
			logger = LogManager.getLogger(loggerList[i]);
			logger.addHandler(handler);
			logger.setLogLevel(Logging.ALL);
		}
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @see #backplane
	 * @see #hostname
	 * @see #plcType
	 * @see #plcAddress
	 * @see #help
	 * @see #logLevel
	 * @see #slot
	 * @see #count
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
			else if(args[i].equals("-backplane")||args[i].equals("-b"))
			{
				if((i+1)< args.length)
				{
					backplane = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-backplane should have an numeric argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-hostname")||args[i].equals("-ip"))
			{
				if((i+1)< args.length)
				{
					hostname = args[i+1];
					i += 1;
				}
				else
				{
					System.err.println("-hostname should have 1 arguments : A valid hostname or IP address.");
					System.exit(1);
				}
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
			else if(args[i].equals("-plc_type")||args[i].equals("-pt"))
			{
				if((i+1)< args.length)
				{
					plcType = EIPPLC.plcTypeFromString(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-plc_type should  have an argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-sleep_time")||args[i].equals("-st"))
			{
				if((i+1)< args.length)
				{
					sleepTime = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-backplane should have an numeric argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-slot")||args[i].equals("-s"))
			{
				if((i+1)< args.length)
				{
					slot = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-slot should have an numeric argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-count")||args[i].equals("-c"))
			{
				if((i+1)< args.length)
				{
					count = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-count should have an numeric argument.");
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
		System.out.println("\t-backplane|-b <number>");
		System.out.println("\t-hostname|-ip <hostname>");
		System.out.println("\t-log_level|-l <number>");
		System.out.println("\t-plc_address|-a <address>");
		System.out.println("\t-plc_type|-pt <MICROLOGIX1100>");
		System.out.println("\t-slot|-s <number>");
		System.out.println("\t-count|-c <number>");
		System.out.println("\t-sleep_time|-st <milliseconds>");
		System.out.println("\t<address> is of the form N7:1.");
	}

	/**
	 * Main method - entry point for the test program.
	 * Example run: 
	 * <pre>java ngat.eip.test.PlcReadWriteTest -ip 150.204.240.114 -plc_address N7:1 -backplane 1 -slot 0 -count 100</pre>
	 * <ul>
	 * <li>Calls parseArgs to parse the command line arguments.
	 * <li>Calls initLoggers to setup logging.
	 * <li>Calls run method to get the value from the PLC.
	 * </ul>
	 * @param args Command line arguments.
	 * @see #parseArgs
	 * @see #initLoggers
	 * @see #run
	 */
	public static void main(String[] args)
	{
		PlcReadWriteTest ir = new PlcReadWriteTest();

		ir.parseArgs(args);
		ir.initLoggers();
		try
		{
			ir.run();
		}
		catch(Exception e)
		{
			System.err.println("PlcReadWriteTest run failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2008/10/09 14:14:27  cjm
// Initial revision
//
//
