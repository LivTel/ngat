// TwoPlcTest.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/test/TwoPlcTest.java,v 1.1 2008-10-09 14:14:27 cjm Exp $
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
 * This class tests the using the ngat.eip library to communicate with two
 * PLCs in different threads at the same time.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TwoPlcTest
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TwoPlcTest.java,v 1.1 2008-10-09 14:14:27 cjm Exp $");
	/**
	 * Number of Plcs to communicate with.
	 */
	public final static int PLC_COUNT = 2;
	/**
	 * Which type of device to try to connect to.
	 * @see ngat.eip.EIPPLC#PLC_TYPE_MICROLOGIX1100
	 */
	protected int plcType[] = {EIPPLC.PLC_TYPE_MICROLOGIX1100,EIPPLC.PLC_TYPE_MICROLOGIX1100};
	/**
	 * The hostname of the device to connect to.
	 * i.e. 150.204.240.115 / frodospecserialports.
	 */
	protected String hostname[] = {null,null};
	/**
	 * The backplane containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int backplane[] = {1,1};
	/**
	 * The slot containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int slot[] = {0,0};
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
	 * The log level.
	 * @see ngat.eip.EIPPLC#LOG_BIT_SESSION
	 * @see ngat.eip.EIPPLC#LOG_BIT_READ
	 * @see ngat.eip.EIPPLC#LOG_BIT_WRITE
	 * @see ngat.eip.EIPPLC#LOG_BIT_ADDRESS
	 */
	protected int logLevel = EIPPLC.LOG_BIT_SESSION|EIPPLC.LOG_BIT_READ|
		EIPPLC.LOG_BIT_WRITE|EIPPLC.LOG_BIT_ADDRESS;
	/**
	 * The PLC address of the integer to write. i.e. N7:1.
	 */
	protected String plcAddress[] = {"N7:1","N7:2"};
	/**
	 * The value to write to the specified PLC address.
	 */
	protected int value[] = {0,0};
	/**
	 * The number of times round the read/write test loop.
	 */
	protected int count = 100;
	/**
	 * The amount of time to sleep after each read/write instance (in milliseconds).
	 */
	protected int sleepTime = 0;
	/**
	 * Test threads.
	 */
	protected TestThread threadList[] = {null,null};

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
	 * @see ngat.eip.EIPHandle
	 */
	public void run() throws EIPNativeException, InterruptedException
	{
		EIPPLC plc[] = {null,null};
		EIPHandle handle[] = {null,null};

		logger.log(1,this.getClass().getName()+":run:Initialising PLCs/Handles.");
		for(int i = 0; i < PLC_COUNT; i++)
		{
			logger.log(1,this.getClass().getName()+":run:Initialising PLC:"+i);
			plc[i] = new EIPPLC();
			plc[i].setLogFilterLevel(logLevel);
			logger.log(1,this.getClass().getName()+":run:Creating Handle "+i+" to hostname:"+hostname[i]);
			handle[i] = plc[i].createHandle(hostname[i],backplane[i],slot[i],plcType[i]);
		}
		// Initialise a read/write test thread for each plc
		for(int i = 0; i < PLC_COUNT; i++)
		{
			logger.log(1,this.getClass().getName()+":run:Initialising TestThread:"+i);
			threadList[i] = new TestThread();
			threadList[i].setHostname(hostname[i]);
			threadList[i].setEIPPLC(plc[i]);
			threadList[i].setHandle(handle[i]);
			threadList[i].setPLCAddress(plcAddress[i]);
			threadList[i].setCount(count);
			threadList[i].setSleepTime(sleepTime);
		}
		// Start a read/write test thread for each plc
		for(int i = 0; i < PLC_COUNT; i++)
		{
			logger.log(1,this.getClass().getName()+":run:Starting TestThread:"+i);
			threadList[i].start();
		}
		// wait for completion of thread
		for(int i = 0; i < PLC_COUNT; i++)
		{
			logger.log(1,this.getClass().getName()+":run:Waiting for Test Thread:"+i);
			threadList[i].join();
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
		String loggerList[] = {"ngat.eip.EIPPLC"};

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
	 * @see #hostname
	 * @see #help
	 * @see #logLevel
	 * @see #sleepTime
	 * @see #count
	 */
	private void parseArgs(String[] args)
	{
		int ivalue;

		for(int i = 0; i < args.length;i++)
		{

			if(args[i].equals("-h")||args[i].equals("-help"))
			{
				help();
				System.exit(0);
			}
			else if(args[i].equals("-hostname")||args[i].equals("-ip"))
			{
				if((i+2)< args.length)
				{
					ivalue = Integer.parseInt(args[i+1]);
					hostname[ivalue] = args[i+2];
					i += 2;
				}
				else
				{
					System.err.println("-hostname <index> <address>.");
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
			else if(args[i].equals("-sleep_time")||args[i].equals("-st"))
			{
				if((i+1)< args.length)
				{
					sleepTime = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-sleep_time should have an numeric "+
							   "argument(milliseconds).");
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
		System.out.println("\t-hostname|-ip <plcnumber> <hostname>");
		System.out.println("\t-log_level|-l <number>");
		System.out.println("\t-count|-c <number>");
		System.out.println("\tThe count is how many times to read/write a value.");
	}

	/**
	 * Main method - entry point for the test program.
	 * Example run: 
	 * <pre>java ngat.eip.test.TwoPlcTest -ip 0 150.204.240.114 -ip 1 150.204.240.115</pre>
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
		TwoPlcTest t = new TwoPlcTest();

		t.parseArgs(args);
		t.initLoggers();
		try
		{
			t.run();
		}
		catch(Exception e)
		{
			System.err.println("TwoPlcTest run failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public class TestThread extends Thread
	{
		protected String hostname = null;
		protected EIPPLC plc = null;
		protected EIPHandle handle = null;
		protected String plcAddress = null;
		protected int count;
		protected int sleepTime;

		public void setHostname(String s)
		{
			hostname = s;
		}

		public void setEIPPLC(EIPPLC p)
		{
			plc = p;
		}

		public void setHandle(EIPHandle h)
		{
			handle = h;
		}

		public void setPLCAddress(String s)
		{
			plcAddress = s;
		}

		public void setCount(int i)
		{
			count = i;
		}

		public void setSleepTime(int i)
		{
			sleepTime = i;
		}

		/**
		 * TestThread run method
		 * @see ngat.eip.EIPPLC#destroyHandle
		 * @see ngat.eip.EIPPLC#setInteger
		 */
		public void run()
		{
			int currentValue = 0;
			try
			{
				logger.log(1,this.getClass().getName()+":PLC:"+
					   hostname+" Address:"+plcAddress+": Counting up to "+count+".");
				for(int i = 0; i < count; i++ )
				{
					logger.log(1,this.getClass().getName()+": Reading value from PLC:"+
							   hostname+" Address:"+plcAddress+".");
					currentValue = plc.getInteger(handle,plcAddress);
					logger.log(1,this.getClass().getName()+": Current value from PLC:"+
							   hostname+" Address:"+plcAddress+" is "+currentValue+".");
					currentValue++;
					logger.log(1,this.getClass().getName()+": Writing value "+
							   currentValue+" to PLC:"+hostname+" Address:"+
							   plcAddress+".");
					plc.setInteger(handle,plcAddress,currentValue);
					logger.log(1,this.getClass().getName()+":PLC:"+hostname+" Address: "+
							   plcAddress+" set to value: "+currentValue);
					if(sleepTime > 0)
					{
						logger.log(1,this.getClass().getName()+":run:Sleeping for: "+
							   sleepTime+" milliseconds.");
						Thread.sleep(sleepTime);
					}
				}
				logger.log(1,this.getClass().getName()+":PLC:"+
					   hostname+" Address:"+plcAddress+": Finished Counting up to "+count+".");
			}
			catch(Exception e)
			{
				System.err.println("TestThread run failed for PLC:"+hostname+" Address: "+
						   plcAddress+" value: "+currentValue+":"+e);
				e.printStackTrace();
			}
			finally
			{
				try
				{
					plc.destroyHandle(handle);
				}
				catch(Exception e)
				{
					System.err.println("TestThread run failed (destroyHandle) for PLC:"+
							   hostname+" Address: "+
							   plcAddress+" value: "+currentValue+":"+e);
					e.printStackTrace();
				}
			}
		}
	}
}
//
// $Log: not supported by cvs2svn $
//
