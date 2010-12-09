// TestCalibrationMirror.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/test/TestCalibrationMirror.java,v 1.1 2010-12-09 14:46:16 cjm Exp $
package ngat.lamp.test;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import ngat.eip.*;
import ngat.lamp.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class tests moving inline and stowing the calibration mirror used via Frodospec and the LTAGLampUnit,#
 * when taking ARCS and Tungsten lampflats.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TestCalibrationMirror
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TestCalibrationMirror.java,v 1.1 2010-12-09 14:46:16 cjm Exp $");
	/**
	 * The filename of a properties file containing the lamp unit configuration.
	 */
	protected String configFilename = null;
	/**
	 * An instance of LTAGLampUnit, which controls the LT A&G lamps.
	 * @see ngat.lamp.LTAGLampUnit
	 */
	protected LTAGLampUnit lampUnit = null;
	/**
	 * A boolean, if true stop after a movement failure has occured, false otherwise.
	 */
	protected boolean stopOnFail = false;
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
	public TestCalibrationMirror()
	{
		super();
	}

	/**
	 * init method.
	 * <ul>
	 * <li>Construct new instance of LTAGLampUnit.
	 * <li>Call setLogLevel with logFilterLevel as the parameter.
	 * <li>Call loadConfig with configFilename as the parameter.
	 * <li>Call the lamp unit's init method.
	 * </ul>
	 * @exception EIPNativeException Thrown by the lamp unit constructor and init methods.
	 * @exception FileNotFoundException Thrown by the lamp unit's loadConfig method.
	 * @exception IOException Thrown by the lamp unit's loadConfig method.
	 * @exception NGATPropertyException Thrown by the lamp unit's loadConfig method.
	 * @exception Exception Thrown by the lamp unit's init method.
	 * @see #lampUnit
	 * @see #configFilename
	 * @see #logFilterLevel
	 * @see ngat.lamp.LTAGLampUnit#loadConfig
	 * @see ngat.lamp.LTAGLampUnit#setLogLevel
	 * @see ngat.lamp.LTAGLampUnit#init
	 */
	public void init() throws EIPNativeException,FileNotFoundException, IOException, NGATPropertyException, 
				  Exception
	{
		lampUnit = new LTAGLampUnit();
		lampUnit.loadConfig(configFilename);
		lampUnit.setLogLevel(logFilterLevel);
		lampUnit.init();
	}

	/**
	 * run method.
	 * @exception EIPNativeException Thrown by the lamp unit's unit/getLightLevel method.
	 * @exception Exception Thrown by the lamp unit's turnLampOn,isLampOn,turnLampOff method.
	 * @see #lampUnit
	 * @see #stopOnFail
	 * @see ngat.lamp.LTAGLampUnit#moveMirrorInline
	 * @see ngat.lamp.LTAGLampUnit#stowMirror
	 */
	public void run() throws Exception, EIPNativeException
	{
		int movementCount;
		int stowCount;
		int stowFailureCount;
		int inlineCount;
		int inlineFailureCount;
		boolean done;

		movementCount = 0;
		stowCount = 0;
		inlineCount = 0;
		inlineFailureCount = 0;
		stowFailureCount = 0;
		done = false;
		while(done == false)
		{
			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),"Moving mirror inline.");
			try
			{
				lampUnit.moveMirrorInline();
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
					   "Mirror moved inline.");
				inlineCount++;
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
				      "Mirror moved "+movementCount+" times and moved inline "+inlineCount+" times.");
			}
			catch(Exception e)
			{
				inlineFailureCount++;
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
					   "FAILED to move mirror inline.");
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
				      "Mirror moved "+movementCount+" times and moved inline "+inlineCount+
					   " times, failed to move inline "+inlineFailureCount+" times.");
				if(stopOnFail)
					System.exit(1);
			}
			movementCount++;
			logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),"Stowing mirror.");
			try
			{
				lampUnit.stowMirror();
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),"Mirror stowed.");
				stowCount++;
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
				      "Mirror moved "+movementCount+" times and stowed "+stowCount+" times.");
			}
			catch(Exception e)
			{
				stowFailureCount++;
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
					   "FAILED to stow mirror.");
				logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName(),
				      "Mirror moved "+movementCount+" times and stowed "+stowCount+
					   " times, failed to stow "+stowFailureCount+" times.");
				if(stopOnFail)
					System.exit(1);
			}
			movementCount++;
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
		String loggerList[] = {"ngat.lamp.LTAGLampUnit","ngat.lamp.LTLamp","ngat.lamp.PLCConnection",
				       "ngat.eip.EIPPLC"};

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
	 * @see #configFilename
	 * @see #help
	 * @see #logFilterLevel
	 */
	private void parseArgs(String[] args)
	{
		for(int i = 0; i < args.length;i++)
		{

			if(args[i].equals("-config_filename")||args[i].equals("-co"))
			{
				if((i+1)< args.length)
				{
					configFilename = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-config_filename should  have a filename argument.");
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
			else if(args[i].equals("-s")||args[i].equals("-stop_on_fail"))
			{
				stopOnFail = true;
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
		System.out.println("\t-co[nfig_filename] <filename>");
		System.out.println("\t-log[_level] <n>");
		System.out.println("\t-s[top_on_fail]]");
		System.out.println("");
		System.out.println("-config_filename specifies the lamp.properties used to configure the device.");
		System.out.println("-log_level can be used to set the log level from very terse (1) to very verbose (5).");
		System.out.println("-stop_on_fail causes the program to stop after a movement failure is detected.");
	}

	/**
	 * Main program.
	 * <ul>
	 * <li>Create a TestCalibrationMirror instance.
	 * <li>Call parseArgs.
	 * <li>Call initLoggers.
	 * <li>Call init.
	 * <li>Call run.
	 * </ul>
	 * @see #parseArgs
	 * @see #initLoggers
	 * @see #init
	 * @see #run
	 */
	public static void main(String[] args)
	{
		TestCalibrationMirror tl = new TestCalibrationMirror();

		tl.parseArgs(args);
		tl.initLoggers();
		try
		{
			tl.init();
			tl.run();
		}
		catch(Exception e)
		{
			System.err.println("TestCalibrationMirror failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}
}
/*
** $Log: not supported by cvs2svn $
*/
