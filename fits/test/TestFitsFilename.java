// TestFitsFilename.java
// $Id$
package ngat.fits.test;

import java.lang.*;
import java.util.*;
import java.text.*;

import ngat.fits.*;

/**
 * Simple test class for FitsFilename. This sets up an instance of FitsFilename, runs the initialise() method, and then can simulate generating FITS filenames
 * for a multrun.
 * Example invocation:
 * <code>
 * java ngat.fits.test.TestFitsFilename -directory /home/cjm/tmp/20241204/ -instrument_code k -exposure_code e -exposure_count 20
 * </code>
 * @author Chris Mottram
 * @version $Id$
 */
public class TestFitsFilename
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * FitsFilename object instance to test.
	 */
	private FitsFilename fitsFilename = null;
	/**
	 * The directory to scan for FITS filename.
	 */
	private String directoryName = ".";
	/**
	 * The instrument code to use.
	 */
	private String instrumentCode = "c";
	/**
	 * The exposure code to use.
	 */
	private String exposureCode = "e";
	/**
	 * How many exposures to generate new filenames for (in a single multrun).
	 */
	private int exposureCount = 0;
	
	/**
	 * Test program constructor. Create FitsFilename reference.
	 * @see #fitsFilename
	 */
	public TestFitsFilename()
	{
		super();
		fitsFilename = new FitsFilename();
	}

	/**
	 * Parse the command line arguments
	 * @param args An array of strings to parse.
	 * @see #directoryName
	 * @see #exposureCode
	 * @see #help
	 * @see #instrumentCode
	 * @see #exposureCount
	 */
	public void parseArguments(String args[])
	{
		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-d")||args[i].equals("-directory"))
			{
				if((i+1)< args.length)
				{
					directoryName = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-directory should  have a string argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-e")||args[i].equals("-exposure_code"))
			{
				if((i+1)< args.length)
				{
					exposureCode = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-exposure_code should  have a character argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-c")||args[i].equals("-exposure_count"))
			{
				if((i+1) < args.length)
				{
					exposureCount = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-exposure_count should have an integer argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-h")||args[i].equals("-help"))
			{
				help();
				System.exit(0);
			}
			else if(args[i].equals("-i")||args[i].equals("-instrument_code"))
			{
				if((i+1)< args.length)
				{
					instrumentCode = args[i+1];
					i++;
				}
				else
				{
					System.err.println("-instrument_code should have a single character argument.");
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
	 * Do some work with the fitsFilename object instance.
	 * <ul>
	 * <li>We setup the fitsFilename object directory (setDirectory) to the command line parameter directoryName.
	 * <li>We setup the fitsFilename object instrument code (setInstrumentCode) to the command line parameter instrumentCode.
	 * <li>We initialise the fitsFilename object. This will read the directory for FITS images and set the multrun number to the highest multrun filename
	 *     found in the directory.
	 * <li>We print out some information about what multrun the fitsFilename object has been initialised to.
	 * <li>if exposureCount is greater than 0, we simulate doing a multrun as follows:
	 *     <ul>
	 *     <li>We increment the fitsFilename object multrun number (nextMultRunNumber).
	 *     <li>We loop over exposureCount images doing the following:
	 *         <ul>
	 *         <li>We increment the fitsFilename object run number (nextRunNumber).
	 *         <li>We set the exposure code to specify what kind of image we are acquiring/saving.
	 *         <li>We generate a new FITS image filename and print it out, by invoking fitsFilename's getFilename method.
	 *         </ul>
	 *     </ul>
	 * <li>We print out some information about what multrun the fitsFilename object is now at.
	 * </ul>
	 * @exception Exception Thrown by FitsFilename.initialise(), fitsFilename.setInstrumentCode.
	 * @see #fitsFilename
	 * @see #directoryName
	 * @see #exposureCount
	 * @see #exposureCode
	 * @see #instrumentCode
	 * @see ngat.fits.FitsFilename#setDirectory
	 * @see ngat.fits.FitsFilename#setExposureCode
	 * @see ngat.fits.FitsFilename#setInstrumentCode
	 * @see ngat.fits.FitsFilename#initialise
	 * @see ngat.fits.FitsFilename#getMultRunNumber
	 * @see ngat.fits.FitsFilename#getRunNumber
	 * @see ngat.fits.FitsFilename#getFilename
	 */
	public void run() throws Exception
	{
		String filename = null;
		
		System.out.println(this.getClass().getName()+":run:Setting FITS filename directory to:"+directoryName);
		fitsFilename.setDirectory(directoryName);
		System.out.println(this.getClass().getName()+":run:Setting FITS instrument code to:"+instrumentCode);
		fitsFilename.setInstrumentCode(instrumentCode);
		System.out.println(this.getClass().getName()+":run:Initialising FITS Filename object.");
		fitsFilename.initialise();
		System.out.println(this.getClass().getName()+":run:Current (last) Multrun number:"+
				   fitsFilename.getMultRunNumber());
		System.out.println(this.getClass().getName()+":run:Current (last) Run number:"+
				   fitsFilename.getRunNumber());
		if(exposureCount > 0)
		{
			System.out.println(this.getClass().getName()+":run:Simulate a multrun with "+
					   exposureCount+" frames.");
			// simulate a new multrun
			fitsFilename.nextMultRunNumber();
			for(int i = 0; i < exposureCount;i++)
			{
				fitsFilename.nextRunNumber();
				fitsFilename.setExposureCode(exposureCode);
				filename = fitsFilename.getFilename();
				System.out.println(this.getClass().getName()+":run:New FITS image filename:"+filename);
			}
		}
		System.out.println(this.getClass().getName()+":run:Current (last) Multrun number:"+
				   fitsFilename.getMultRunNumber());
		System.out.println(this.getClass().getName()+":run:Current (last) Run number:"+
				   fitsFilename.getRunNumber());
	}

	/**
	 * Help message routine.
	 */
	private void help()
	{
		System.out.println(this.getClass().getName()+" Help:");
		System.out.println("Options are:");
		System.out.println("\t-help");
		System.out.println("\t-c|-exposure_count <count> - Simulate generating new FITS filenames for a multrun with <count> number of frames.");
		System.out.println("\t-[d]irectory <name> - Set the directory containing FITS filenames.");
		System.out.println("\t-[e]xposure_code <letter> - Set the exposure code of the newly generated filename.");
		System.out.println("\t-[i]nstrument_code <letter> - Set the instrument code of the instrument.");
	}
	
	/**
	 * Main entry point for test program.
	 * @param args Command line arguments.
	 */
	public static void main(String args[])
	{
		TestFitsFilename testFitsFilename = null;

		System.out.println("TestFitsFilename:started.");
		testFitsFilename = new TestFitsFilename();
		System.out.println("TestFitsFilename:parsing arguments.");
		testFitsFilename.parseArguments(args);
		try
		{
			System.out.println("TestFitsFilename:running tests.");
			testFitsFilename.run();
		}
		catch(Exception e)
		{
			System.err.println("TestFitsFilename:main:run failed with error:"+e);
			e.printStackTrace(System.err);
			System.exit(1);
		}
		System.out.println("TestFitsFilename:finished.");
		System.exit(0);
	}
}
