// TestSpectrographCamera.java
// $Header: /space/home/eng/cjm/cvs/ngat/spec/test/TestSpectrographCamera.java,v 1.2 2004-07-29 14:47:54 cjm Exp $
package ngat.spec.test;

import java.lang.*;
import java.text.*;
import ngat.util.logging.*;
import ngat.spec.*;

/**
 * Simple test of ngat.spec library.
 * Run as follows:
 * <pre>
 * java ngat.spec.test.TestSpectrographCamera -l 34560 -f test.fits -e 10000
 * </pre>
 * @author Chris Mottram
 * @version $Revision$
 */
public class TestSpectrographCamera
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
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
	 * Spectrograph library log level.
	 * Initial level 34560, bits 8,9,10,15 set.
	 * See spec.h for possible values.
	 */
	protected int logFilterLevel = 34560;
	/**
	 * The exposure length, in milliseconds.
	 */
	protected int exposureLength = 10000;
	/**
	 * The FITS filename to save the exposure into.
	 */
	protected String fitsFilename = new String("test.fits");

	/**
	 * Method to parse arguments.
	 * @param args The argument list.
	 * @see #help
	 * @see #exposureLength
	 * @see #fitsFilename
	 * @see #logFilterLevel
	 */
	protected void parseArguments(String args[]) throws Exception
	{
		double w2mc,m2pc;

		for(int i = 0;i < args.length; i++)
		{
			if(args[i].equals("-exposure_length")||args[i].equals("-e"))
			{
				if((i+1) < args.length)
				{
					exposureLength = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					throw new IllegalArgumentException(this.getClass().getName()+
							      ":parseArguments:No exposureLength argument.");
				}
			}
			else if(args[i].equals("-filename")||args[i].equals("-f"))
			{
				if((i+1) < args.length)
				{
					fitsFilename = args[i+1];
					i++;
				}
				else
				{
					throw new IllegalArgumentException(this.getClass().getName()+
							      ":parseArguments:No FITS filename argument.");
				}
			}
			else if(args[i].equals("-help")||args[i].equals("-h"))
			{
				help();
				System.exit(1);
			}
			else if(args[i].equals("-log_level")||args[i].equals("-l"))
			{
				if((i+1) < args.length)
				{
					logFilterLevel = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					throw new IllegalArgumentException(this.getClass().getName()+
						   ":parseArguments:Cannot parse log level:"+args[i+1]);
				}
			}
			else
			{
				throw new IllegalArgumentException(this.getClass().getName()+
								   ":parseArguments:argument not recognised:"+args[i]);
			}

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

		logger = LogManager.getLogger("ngat.spec.SpecLibrary");
		// setup log formatter
		blf = new BogstanLogFormatter();
		blf.setDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.SSS z"));
		// setup log handler
		handler = new ConsoleLogHandler(blf);
		handler.setLogLevel(Logging.ALL);
		logger.addHandler(handler);
		logger.setLogLevel(Logging.ALL);
		logFilter = new BitFieldLogFilter(Logging.ALL);
		logger.setFilter(logFilter);

	}

	/**
	 * Help method.
	 */
	protected void help()
	{
		System.out.println("Filter Wheel Test Move Help:");
		System.out.println("java ngat.spec.test.TestSpectrographCamera [-help]");
		System.out.println("\t[-l[og_level] <level>]");
		System.out.println("\t[-f[ilename] <fits filename>]");
		System.out.println("\t[-e[xposure_length <ms>]");
		System.out.println("\t-log_level specifies the logging. See scs_filter.h for details.");
		System.out.println("\t-filename specifies a FITS file to save the image into.");
		System.out.println("\t-exposure_length specifies the number os milliseconds to excpose the CCD for.");
	}

	/**
	 * Run method. 
	 * <ul>
	 * <li>Opens spectrograph library.
	 * <li>Calls camera setup startup.
	 * <li>Calls camera setup dimensions.
	 * <li>Calls camera expose.
	 * <li>Closes spectrograph library.
	 * </ul>
	 * @see ngat.spec.SpecLibrary#open
	 * @see ngat.spec.SpecLibrary#close
	 * @see ngat.spec.SpecLibrary#cameraSetupStartup
	 * @see ngat.spec.SpecLibrary#cameraSetupDimensions
	 * @see ngat.spec.SpecLibrary#cameraExpose
	 */
	public void run()
	{
		int ncols,nrows,hbin,vbin,startx,starty;

		try
		{
			SpecLibrary.open(SpecLibrary.SPEC_OPEN_APOGEE_BIT);

			SpecLibrary.cameraSetupStartup(false,false);
			startx = SpecLibrary.cameraSetupGetStartColumn();
			starty = SpecLibrary.cameraSetupGetStartRow();
			ncols = SpecLibrary.cameraSetupGetNumberCols();
			nrows = SpecLibrary.cameraSetupGetNumberRows();
			hbin = SpecLibrary.cameraSetupGetHorizontalBinning();
			vbin = SpecLibrary.cameraSetupGetVerticalBinning();
			SpecLibrary.cameraSetupDimensions(startx,starty,ncols,nrows,hbin,vbin);

			//SpecLibrary.cameraTemperatureSet(-10.0);
			SpecLibrary.cameraExpose(exposureLength,fitsFilename);

			SpecLibrary.close();
		}
		catch(SpecNativeException e)
		{
			System.err.println(this.getClass().getName()+":failed:"+e);
			return;
		}
	}

	/**
	 * Main program. Creates an instance of this class, parses arguments, initialise loggers,
	 * set log level, and calls it's run method.
	 * @see #parseArguments
	 * @see #initLoggers
	 * @see ngat.spec.SpecLibrary#setLogLevel
	 * @see #logFilterLevel
	 * @see #run
	 */
	public static void main(String args[])
	{
		TestSpectrographCamera tsc = new TestSpectrographCamera();
		try
		{
			tsc.parseArguments(args);
		}
		catch(Exception e)
		{
			System.err.println("Test Spectrograph failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
		tsc.initLoggers();
		SpecLibrary.setLogLevel(tsc.logFilterLevel);
		tsc.run();
		System.exit(0);
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2004/07/29 14:43:39  cjm
// Initial revision
//
//
