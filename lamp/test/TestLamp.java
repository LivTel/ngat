// TestLamp.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/test/TestLamp.java,v 1.4 2010-03-16 15:22:06 cjm Exp $
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
 * This class tests the LTAGLampUnit.
 * @author Chris Mottram
 * @version $Revision$
 */
public class TestLamp
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
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
	 * Boolean, if true call the lamp units init method at startup.
	 */
	protected boolean doInit = false;
	/**
	 * Boolean, if true try to switch a lamp on for a bit.
	 */
	protected boolean doOn = false;
	/**
	 * The lamp to switch on, hopefully on off "Neon", "Tungsten" or "Xenon".
	 */
	protected String onLamp = null;
	/**
	 * Boolean, if true try to switch off the lamp after you have used it.
	 */
	protected boolean doOff = false;
	/**
	 * Boolean, if true move the mirror in the beam (before turning a lamp on).
	 */
	protected boolean doMirrorInline = false;
	/**
	 * Boolean, if true move the mirror out of the beam (after turning a lamp off).
	 */
	protected boolean doMirrorStow = false;
	/**
	 * How long to turn the lamp on (and monitor it), however unless doOff it set the lamp will be left on.
	 */
	protected int onTime = 1;
	/**
	 * Boolean, if true monitor light levels whilst the lamp is on.
	 */
	protected boolean doLightLevel = false;
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
	public TestLamp()
	{
		super();
	}

	/**
	 * init method.
	 * <ul>
	 * <li>Construct new instance of LTAGLampUnit.
	 * <li>Call setLogLevel with logFilterLevel as the parameter.
	 * <li>Call loadConfig with configFilename as the parameter.
	 * <li>Call the lamp unit's init method, if doInit is specified.
	 * </ul>
	 * @exception EIPNativeException Thrown by the lamp unit constructor and init methods.
	 * @exception FileNotFoundException Thrown by the lamp unit's loadConfig method.
	 * @exception IOException Thrown by the lamp unit's loadConfig method.
	 * @exception NGATPropertyException Thrown by the lamp unit's loadConfig method.
	 * @exception Exception Thrown by the lamp unit's init method.
	 * @see #lampUnit
	 * @see #configFilename
	 * @see #doInit
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
		if(doInit)
			lampUnit.init();
		else
			System.out.println("The lamp unit has not been initialised.");
	}

	/**
	 * run method.
	 * If doMirrorInline:
	 * <ul>
	 * <li>Call the lampUnit moveMirrorInline method.
	 * </ul>
	 * If doOn:
	 * <ul>
	 * <li>Call the lampUnit turnLampOn method.
	 * <li>Enter a loop sleeping for 1 second until onTime has been slept for. If doLightLevel,
	 *     calls lampUnit's getLightLevel and isLampOn methods and report the result.
	 * </ul>
	 * If doOff:
	 * <ul>
	 * <li>Call the lampUnit's turnLampOff method.
	 * <li>Call the lampUnit's isLampOn method and report the result.
	 * </ul>
	 * If doMirrorStow:
	 * <ul>
	 * <li>Call the lampUnit stowMirror method.
	 * </ul>
	 * @exception EIPNativeException Thrown by the lamp unit's unit/getLightLevel method.
	 * @exception Exception Thrown by the lamp unit's turnLampOn,isLampOn,turnLampOff method.
	 * @see #lampUnit
	 * @see #onLamp
	 * @see #onTime
	 * @see #doLightLevel
	 * @see #doOff
	 * @see #doMirrorInline
	 * @see #doMirrorStow
	 * @see ngat.lamp.LTAGLampUnit#turnLampOn
	 * @see ngat.lamp.LTAGLampUnit#turnLampOff
	 * @see ngat.lamp.LTAGLampUnit#getLightLevel
	 * @see ngat.lamp.LTAGLampUnit#isLampOn
	 * @see ngat.lamp.LTAGLampUnit#moveMirrorInline
	 * @see ngat.lamp.LTAGLampUnit#stowMirror
	 */
	public void run() throws Exception, EIPNativeException
	{
		int index,lightLevel;

		if(doMirrorInline)
			lampUnit.moveMirrorInline();
		if(doOn)
		{
			lampUnit.turnLampOn(onLamp);
			index = 0;
			while(index < onTime)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ie)
				{
				}
				if(doLightLevel)
				{
					lightLevel = lampUnit.getLightLevel();
					System.out.println("The light level is:"+lightLevel);
					System.out.println("Lamp "+onLamp+" is on = "+lampUnit.isLampOn(onLamp));
				}
				index++;
			}
		}
		if(doOff)
		{
			lampUnit.turnLampOff(onLamp);
			System.out.println("Lamp "+onLamp+" is on = "+lampUnit.isLampOn(onLamp));
		}
		if(doMirrorStow)
			lampUnit.stowMirror();
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
	 * @see #doOn
	 * @see #doOff
	 * @see #onLamp
	 * @see #onTime
	 * @see #doInit
	 * @see #doLightLevel
	 * @see #doMirrorInline
	 * @see #doMirrorStow
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
			else if(args[i].equals("-init")||args[i].equals("-i"))
			{
				doInit = true;
			}
			else if(args[i].equals("-lightlevel")||args[i].equals("-l"))
			{
				doLightLevel = true;
			}
			else if(args[i].equals("-mirror_inline")||args[i].equals("-mi"))
			{
				doMirrorInline = true;
			}
			else if(args[i].equals("-mirror_stow")||args[i].equals("-ms"))
			{
				doMirrorStow = true;
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
			else if(args[i].equals("-on")||args[i].equals("-o"))
			{
				if((i+2)< args.length)
				{
					doOn = true;
					onLamp = args[i+1];
					onTime = Integer.parseInt(args[i+2]);
					i+=2;
				}
				else
				{
					System.err.println("-on should have a lamp and on time argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-off"))
			{
				doOff = true;
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
		System.out.println("\t-on <Neon|Xenon|Tungsten> <secs>");
		System.out.println("\t-off");
		System.out.println("\t-mirror_inline");
		System.out.println("\t-mirror_stow");
		System.out.println("\t-i[nit]");
		System.out.println("\t-l[ightlevel]");
		System.out.println("\t-log[_level] <n>");
		System.out.println("");
		System.out.println("-config_filename specifies the lamp.properties used to configure the device.");
		System.out.println("-init calls the lamp unit's init method, which switches all lamps off and can set some thresholds/presets.");
		System.out.println("-on turns the specified lamp on for a number of seconds.");
		System.out.println("-off turns off the lamp turned on by -on. Otherwise the lamp stays on.");
		System.out.println("-mirror_inline moves the calibration mirror into the beam.");
		System.out.println("-mirror_stow moves the calibration mirror out of the beam.");
		System.out.println("-lightlevel monitors the internal A&G light level whilst the lamp is on.");
	}

	/**
	 * Main program.
	 * <ul>
	 * <li>Create a TestLamp instance.
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
		TestLamp tl = new TestLamp();

		tl.parseArgs(args);
		tl.initLoggers();
		try
		{
			tl.init();
			tl.run();
		}
		catch(Exception e)
		{
			System.err.println("TestLamp failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
