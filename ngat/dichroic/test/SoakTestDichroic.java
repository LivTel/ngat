// SoakTestDichroic.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/test/SoakTestDichroic.java,v 1.2 2013-03-27 16:32:08 cjm Exp $
package ngat.dichroic.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import ngat.dichroic.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class tests the IO:O dichroic.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class SoakTestDichroic
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: SoakTestDichroic.java,v 1.2 2013-03-27 16:32:08 cjm Exp $");
	/**
	 * The dichroic instance.
	 */
	protected Dichroic dichroic = null;
	/**
	 * A string holding the IP address or hostname of the Arduino used to control the dichroic.
	 */
	protected String address = null;
	/**
	 * The integer holding the port number of the server on the Arduino used to control the dichroic.
	 */
	protected int portNumber = 23;
	/**
	 * A time specified in milliseconds to sleep between each move operation.
	 */
	protected int moveSleepTime = 1000;
	/**
	 * Boolean specifying whether to quit the thread if an error occurs whilst moving.
	 */
	protected boolean moveQuitOnError = false;
	/**
	 * A time specified in milliseconds to sleep between each getPosition operation.
	 */
	protected int getPositionSleepTime = 1000;
	/**
	 * Boolean specifying whether to quit the thread if an error occurs whilst querying position.
	 */
	protected boolean getPositionQuitOnError = false;
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
	 * Logger log level.
	 * @see ngat.util.logging.Logging#VERBOSITY_VERY_VERBOSE
	 */
	protected int logFilterLevel = Logging.VERBOSITY_VERY_VERBOSE;

	/**
	 * Constructor.
	 */
	public SoakTestDichroic()
	{
		super();
	}

	/**
	 * init method.
	 * <ul>
	 * <li>Construct new instance of Dichroic.
	 * <li>We set the address and port number arguments to dichroic.
	 * </ul>
	 * @exception UnknownHostException Thrown if the address is not known.
	 * @see #dichroic
	 * @see #address
	 * @see #portNumber
	 * @see ngat.dichroic.Dichroic#setAddress
	 * @see ngat.dichroic.Dichroic#setPortNumber
	 */
	public void init() throws UnknownHostException
	{
		dichroic = new Dichroic();
		dichroic.setAddress(address);
		dichroic.setPortNumber(portNumber);
	}

	/**
	 * Run method. We start an instance of SoakTestMoveThread and SoakTestGetPositionThread
	 * @see SoakTestMoveThread
	 * @see SoakTestGetPositionThread
	 * @see #dichroic
	 * @see #moveSleepTime
	 * @see #moveQuitOnError
	 * @see #getPositionSleepTime
	 * @see #getPositionQuitOnError
	 */
	public void run()
	{
		SoakTestMoveThread stmt = null;
		SoakTestGetPositionThread stgpt = null;

		// initialise threads
		stmt = new SoakTestMoveThread();
		stmt.setLogger(logger);
		stmt.setDichroic(dichroic);
		stmt.setSleepTime(moveSleepTime);
		stmt.setQuitOnError(moveQuitOnError);
		stgpt = new SoakTestGetPositionThread();
		stgpt.setLogger(logger);
		stgpt.setDichroic(dichroic);
		stgpt.setSleepTime(getPositionSleepTime);
		stgpt.setQuitOnError(getPositionQuitOnError);
		// start threads
		stmt.start();
		stgpt.start();
		// wait for threads to terminate
		try
		{
			stmt.join();
			stgpt.join();
		}
		catch(Exception e)
		{
			logger.log(Logging.VERBOSITY_INTERMEDIATE,"SoakTestDichroic:run:join failed:",e);
			e.printStackTrace();
		}
	}

	/**
	 * Method to initialise the logger.
	 * @see #logger
	 * @see #logFilter
	 * @see #logFilterLevel
	 */
	protected void initLoggers()
	{
		LogHandler handler = null;
		BogstanLogFormatter blf = null;
		String loggerList[] = {"ngat.dichroic.test.SoakTestDichroic","ngat.dichroic.Dichroic",
				       "ngat.net.TelnetConnection"};

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
			logger.setLogLevel(logFilterLevel);
			logger.setFilter(logFilter);
		}
		// reset logger instance variable to the test program's logger.
		logger = LogManager.getLogger("ngat.dichroic.test.SoakTestDichroic");
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @exception DichroicException Thrown if parsing the dirchroic position fails.
	 * @see #address
	 * @see #help
	 * @see #logFilterLevel
	 * @see #moveSleepTime
	 * @see #moveQuitOnError
	 * @see #getPositionSleepTime
	 * @see #getPositionQuitOnError
	 */
	private void parseArgs(String[] args) throws DichroicException 
	{
		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-address")||args[i].equals("-a"))
			{
				if((i+1) < args.length)
				{
					address = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-address should have an address argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-get_position_sleep_time")||args[i].equals("-gpst"))
			{
				if((i+1) < args.length)
				{
					getPositionSleepTime = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-move_sleep_time should have a sleep time argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-get_position_quit_on_error")||args[i].equals("-gpqoe"))
			{
				if((i+1) < args.length)
				{
					if(args[i+1].equals("true"))
						getPositionQuitOnError = true;
					else if(args[i+1].equals("false"))
						getPositionQuitOnError = false;
					else
					{
						System.err.println("-get_position_quit_on_error should have a true|false argument.");
						System.exit(1);
					}
					i++;
				}
				else
				{
					System.err.println("-get_position_quit_on_error should have a true|false argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-h")||args[i].equals("-help"))
			{
				help();
				System.exit(0);
			}
			else if(args[i].equals("-log_level")||args[i].equals("-log"))
			{
				if((i+1) < args.length)
				{
					logFilterLevel = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-log_level should have an integer argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-move_sleep_time")||args[i].equals("-mst"))
			{
				if((i+1) < args.length)
				{
					moveSleepTime = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-move_sleep_time should have a sleep time argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-move_quit_on_error")||args[i].equals("-mqoe"))
			{
				if((i+1) < args.length)
				{
					if(args[i+1].equals("true"))
						moveQuitOnError = true;
					else if(args[i+1].equals("false"))
						moveQuitOnError = false;
					else
					{
						System.err.println("-move_quit_on_error should have a true|false argument.");
						System.exit(1);
					}
					i++;
				}
				else
				{
					System.err.println("-move_quit_on_error should have a true|false argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-port_number")||args[i].equals("-p"))
			{
				if((i+1) < args.length)
				{
					portNumber = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-port_number should have an integer argument.");
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
		System.out.println("\t-a[ddress] <hostname|IP>");
		System.out.println("\t-log[_level] <n>");
		System.out.println("\t-get_position_sleep_time|-gpst <msecs>");
		System.out.println("\t-get_position_quit_on_error|-gpqoe");
		System.out.println("\t-move_sleep_time|-mst <msecs>");
		System.out.println("\t-move_quit_on_error|-mqoe");
		System.out.println("\t-p[ort_number] <n>");
		System.out.println("");
	}

	/**
	 * Main program.
	 * <ul>
	 * </ul>
	 * @see #parseArgs
	 * @see #initLoggers
	 * @see #init
	 * @see #run
	 */
	public static void main(String[] args)
	{
		SoakTestDichroic std = new SoakTestDichroic();

		try
		{
			std.parseArgs(args);
			std.initLoggers();
			if(std.address == null)
			{
				System.err.println("SoakTestDichroic:main:No address specified.");
				std.help();
				System.exit(1);
			}
			std.init();
			std.run();
		}
		catch(Exception e)
		{
			System.err.println("SoakTestDichroic failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Inner class to call Dichroic's move command in a run loop as a thread, with user-specified 
	 * sleeps between each call.
	 */
	protected class SoakTestMoveThread extends  SoakTestThread
	{
		Random random = null;

		/**
		 * Constructor. Initialise random.
		 * @see #random
		 */
		public SoakTestMoveThread()
		{
			super();
			random = new Random();
		}

		/**
		 * Move the dichroic.
		 * @exception DichroicException Thrown if an error occurs.
		 * @exception DichroicMoveException Thrown if an error occurs whilst moving the dichroic.
		 * @exception IOException  Thrown if an error occurs whilst communicating with the Arduino.
		 * @see #logger
		 * @see #dichroic
		 * @see ngat.dichroic.Dichroic#move
		 * @see ngat.dichroic.Dichroic#stringFromPosition
		 */
		public void doCommand() throws DichroicException, DichroicMoveException, IOException
		{
			int movePosition;

			movePosition = random.nextInt(3);// produce values 012
			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+
				   ":doCommand:About to move to position:"+movePosition);
			dichroic.move(movePosition);	
			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":doCommand:move done.");
		}
	}

	/**
	 * Inner class to call Dichroic's getPosition command in a run loop as a thread, with user-specified 
	 * sleeps between each call.
	 */
	protected class SoakTestGetPositionThread extends  SoakTestThread
	{
		public SoakTestGetPositionThread()
		{
			super();
		}

		/**
		 * Get the current position of the dichroic.
		 * @exception DichroicException Thrown if an error occurs.
		 * @exception DichroicMoveException Thrown if an error occurs whilst moving the dichroic.
		 * @exception IOException  Thrown if an error occurs whilst communicating with the Arduino.
		 * @see #logger
		 * @see #dichroic
		 * @see ngat.dichroic.Dichroic#getPosition
		 * @see ngat.dichroic.Dichroic#stringFromPosition
		 */
		public void doCommand() throws DichroicException, IOException
		{
			int position;

			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+
				   ":doCommand:About to get position.");
			position = dichroic.getPosition();
			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+
				   ":doCommand:position is:"+position);
		}
	}

	protected class SoakTestThread extends Thread
	{
		Logger logger = null;
		protected Dichroic dichroic = null;
		protected int sleepTime  = 1000;
		protected boolean quitOnError = false;
		protected int successCount;
		protected int failureCount;

		public SoakTestThread()
		{
			super();
		}

		public void setLogger(Logger l)
		{
			logger = l;
		}

		public void setDichroic(Dichroic d)
		{
			dichroic = d;
		}

		public void setSleepTime(int t)
		{
			sleepTime = t;
		}
		public void setQuitOnError(boolean b)
		{
			quitOnError = b;
		}

		public void run()
		{
			boolean quit;

			quit = false;
			successCount = 0;
			failureCount = 0;
			while(quit == false)
			{
				try
				{
					doCommand();
					successCount++;
				}
				catch(Exception e)
				{
					logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+
						   "run:doCommand failed:",e);
					e.printStackTrace();
					failureCount++;
					if(quitOnError)
					{
						quit = true;
						System.exit(1);
					}
				}
				try
				{
					Thread.sleep(sleepTime);
				}
				catch (Exception e)
				{
					logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+
						   "run:sleep failed:",e);
					if(quitOnError)
						quit = true;
				}
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":run:success = "+
					   successCount+ " , failures = "+failureCount);
			}// end while
		}

		/**
		 * Superclass doCommand. Does nothing.
		 * @exception Exception Allows sub-classes to throw exceptions.
		 */
		public void doCommand() throws Exception
		{
		}
	}
}


//
// $Log: not supported by cvs2svn $
// Revision 1.1  2011/10/18 17:30:46  cjm
// Initial revision
//
//
