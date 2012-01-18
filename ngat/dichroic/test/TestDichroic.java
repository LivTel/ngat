// TestDichroic.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/test/TestDichroic.java,v 1.2 2012-01-18 15:14:40 cjm Exp $
package ngat.dichroic.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;

import ngat.dichroic.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class tests the IO:O dichroic.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class TestDichroic
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TestDichroic.java,v 1.2 2012-01-18 15:14:40 cjm Exp $");
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
	 * Boolean, if true try to move the dichroic.
	 */
	protected boolean doMove = false;
	/**
	 * If we are moving the dichroic, the position to move to.
	 */
	protected int movePosition = -1;
	/**
	 * Boolean, if true try to get the dichroic position.
	 */
	protected boolean doGetPosition = false;
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
	public TestDichroic()
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
	 * Run method.
	 * @exception DichroicException Thrown if an error occurs.
	 * @exception DichroicMoveException Thrown if an error occurs whilst moving the dichroic.
	 * @exception IOException  Thrown if an error occurs whilst communicating with the Arduino.
	 * @see #doMove
	 * @see #movePosition
	 * @see #dichroic
	 * @see #doGetPosition
	 * @see ngat.dichroic.Dichroic#move
	 * @see ngat.dichroic.Dichroic#getPosition
	 * @see ngat.dichroic.Dichroic#stringFromPosition
	 */
	public void run() throws DichroicException, DichroicMoveException, IOException
	{
		int position;

		if(doMove)
		{
			System.out.println(this.getClass().getName()+":run:About to move to position:"+movePosition);
			dichroic.move(movePosition);	
			System.out.println(this.getClass().getName()+":run:move done.");
		}
		if(doGetPosition)
		{
			System.out.println(this.getClass().getName()+":run:About to get position.");
			position = dichroic.getPosition();
			System.out.println(this.getClass().getName()+":run:position is:"+position);
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
		String loggerList[] = {"ngat.dichroic.test.TestDichroic","ngat.dichroic.Dichroic",
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
		logger = LogManager.getLogger("ngat.dichroic.test.TestDichroic");
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @exception DichroicException Thrown if parsing the dirchroic position fails.
	 * @see #address
	 * @see #doGetPosition
	 * @see #doMove
	 * @see #help
	 * @see #logFilterLevel
	 * @see #movePosition
	 * @see ngat.dichroic.Dichroic#positionFromString
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
			else if(args[i].equals("-get_position")||args[i].equals("-g"))
			{
				doGetPosition = true;
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
			else if(args[i].equals("-move")||args[i].equals("-m"))
			{
				if((i+1) < args.length)
				{
					movePosition = Integer.parseInt(args[i+1]);
					doMove = true;
					i++;
				}
				else
				{
					System.err.println("-move should have a position argument.");
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
		System.out.println("\t-g[et_position]");
		System.out.println("\t-log[_level] <n>");
		System.out.println("\t-m[ove] <0|1|2>");
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
		TestDichroic td = new TestDichroic();

		try
		{
			td.parseArgs(args);
			td.initLoggers();
			if(td.address == null)
			{
				System.err.println("TestDichroic:main:No address specified.");
				td.help();
				System.exit(1);
			}
			if((td.doMove == false)&&(td.doGetPosition == false))
			{
				System.err.println("TestDichroic:main:Neither move nor get position selected.");
				td.help();
				System.exit(1);
			}
			td.init();
			td.run();
		}
		catch(Exception e)
		{
			System.err.println("TestDichroic failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2011/10/12 10:15:55  cjm
// Initial revision
//
//
