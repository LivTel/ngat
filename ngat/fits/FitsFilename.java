// FitsFilename.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsFilename.java,v 1.1 2001-06-21 11:06:27 cjm Exp $
package ngat.fits;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * This class holds FITS filename generation information for an Instrument Control Subsystem.
 * It allows an ICS to generate FITS filenames in accordance with the Liverpool Telescope
 * Fits Keyword Specification. Usage is as follows:
 * <ul>
 * <li>Construct an instance of the class: <b>ff = new FitsFilename();</b>
 * <li>Set the instrument code:<b>ff.setInstrumentCode("c");</b>
 * <li>Set the directory path which holds the filenames:<b>ff.setDirectory("/space/home/dev/tmp/");</b>
 * <li>Initilaise multrun and run numbers to the last file in the directory:<b>ff.initialise();</b>
 * <li>Every time a new multrun is started, call:<b>ff.nextMultRunNumber();</b>
 * <li>Set the relevant exposure code:<b>ff.setExposureCode(FitsFilename.EXPOSURE_CODE_STANDARD);</b>
 * <li>For each frame in the run, call:<b>ff.nextRunNumber();</b>
 * <li>To get the filename for this frame, call:<b>ff.getFilename();</b>
 * </ul>
 * Note more calls are needed to get individual window filenames.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class FitsFilename
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FitsFilename.java,v 1.1 2001-06-21 11:06:27 cjm Exp $");
	/**
	 * Exposure code constant - for an exposure.
	 */
	public final static char EXPOSURE_CODE_EXPOSURE 		= 'e';
	/**
	 * Exposure code constant - for an standard.
	 */
	public final static char EXPOSURE_CODE_STANDARD 		= 's';
	/**
	 * Exposure code constant - for a bias frame.
	 */
	public final static char EXPOSURE_CODE_BIAS 			= 'b';
	/**
	 * Exposure code constant - for a sky flat.
	 */
	public final static char EXPOSURE_CODE_SKY_FLAT 		= 'f';
	/**
	 * Exposure code constant - for a lamp flat.
	 */
	public final static char EXPOSURE_CODE_LAMP_FLAT 		= 'w';
	/**
	 * Exposure code constant - for an arc.
	 */
	public final static char EXPOSURE_CODE_ARC 			= 'a';
	/**
	 * Exposure code constant - for a dark frame.
	 */
	public final static char EXPOSURE_CODE_DARK 			= 'd';
	/**
	 *  Pipeline processing flag - when the integration is first written to disc it unprocessed.
	 */
	public final static String PIPELINE_PROCESSING_FLAG_NONE 	= "0";
	/**
	 *  Pipeline processing flag - After real-time processing.
	 */
	public final static String PIPELINE_PROCESSING_FLAG_REAL_TIME 	= "1";
	/**
	 *  Pipeline processing flag - After off-line processing.
	 */
	public final static String PIPELINE_PROCESSING_FLAG_OFF_LINE 	= "2";
	/**
	 * Internal constant. The fourth element of a FitsFilename tokenized on '_.' will
	 * be the MULTRUN number.
	 */
	private final static int MULTRUN_TOKEN_NUMBER 			= 4;

	/**
	 * Directory string to be pre-pended to the filename..
	 */
	private String directory = new String("./");
	/**
	 * Instrument code describing the instrument the exposure was taken with.
	 */
	private String instrumentCode = null;
	/**
	 * Exposure code saying what type of exposure was taken.
	 */
	private char exposureCode = EXPOSURE_CODE_EXPOSURE;
	/**
	 * Calendar object to process dates.
	 */
	private Calendar calendar = null;
	/**
	 * Number format object to process dates.
	 */
	private NumberFormat numberFormat = null;
	/**
	 * Todays date.
	 */
	private Date date = null;
	/**
	 * Multrun number - start at 1 each night and is incremented for each SET of exposures (1..n).
	 * It is initialsed to zero, bacause nextMultRunNumber should be called before the first getFilename.
	 */
	private int multRunNumber = 0;
	/**
	 * Run number - the exposure within each multi-run, starts at 1 for each SET of exposures.
	 */
	private int runNumber = 0;
	/**
	 * Window number - which window in the readout this is, starts at 1.
	 */
	private int windowNumber = 1;
	/**
	 * Pipeline processing flag.
	 */
	private String pipelineProcessing = PIPELINE_PROCESSING_FLAG_NONE;
	/**
	 * The extension to concatenate onto the end of the generated filename.
	 * Currently defaults to 'fits'.
	 */
	private String fileExtension = new String("fits");

	/**
	 * Constructor for FitsFilename. Sets up default values for fields. Also sets numberFormat to print
	 * out 2 digit integers (for month/date string creation). 
	 */
	public FitsFilename()
	{
		directory = new String("./");
		instrumentCode = null;
		exposureCode = EXPOSURE_CODE_EXPOSURE;
		calendar = Calendar.getInstance();
		numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumIntegerDigits(2);
		date = new Date();
		multRunNumber = 0;
		runNumber = 0;
		windowNumber = 1;
		pipelineProcessing = PIPELINE_PROCESSING_FLAG_NONE;
		fileExtension = new String("fits");
	}

	/**
	 * Set routine for the instrument code.
	 * @param code The code to set to.
	 */
	public void setInstrumentCode(String code)
	{
		if(code != null)
			instrumentCode = new String(code);
	}

	/**
	 * Set routine for the directory. If the directory is not terminated with a file separator,
	 * one is added.
	 * @param d The directory string.
	 */
	public void setDirectory(String d)
	{
		if(d != null)
		{
			directory = new String(d);
			// ensure directory ends with a separator
			if(directory.endsWith(System.getProperty("file.separator")) == false)
				directory = directory.concat(System.getProperty("file.separator"));
		}
	}

	/**
	 * This method should be called after the object has been constructed and the directory path 
	 * and instrument code have been set.
	 * It initialises the multRunNumber to be the last one used on this night (zero if none exist), for when the
	 * ICS has been rebooted during one nights work. This means when nextMultRunNumber is called a filename
	 * will be generated that does not already exist.
	 * @exception Exception Thrown if the specified directory cannot be found, or an error occurs retrieving
	 * 	a directory listing.
	 * @see #directory
	 * @see FitsFilename.SubstringFilenameFilter
	 */
	public void initialise() throws Exception
	{
		StringTokenizer stringTokenizer = null;
		String directoryList[] = null;
		String dateString = null;
		File file = null;
		int tokenNumber,testMultRunNumber;

	// reset mulRunNumber/runNumber
		multRunNumber = 0;
		runNumber = 0;
		windowNumber = 1;
	// get tonights date string.
		dateString = getDateString();
	// get directory list.
		file = new File(directory);
		directoryList = file.list(new SubstringFilenameFilter(dateString));
		if(directoryList == null)
		{
			throw new Exception(this.getClass().getName()+":initialise:directory listing of:"+
				directory+": failed.");
		}
	// look for largest multrun number in list.
		for(int i = 0; i < directoryList.length; i++)
		{
		// tokenize each filename by '_.' and look through tokens.
			stringTokenizer = new StringTokenizer(directoryList[i],"_.");
			tokenNumber = 0;
			while(stringTokenizer.hasMoreTokens())
			{
				String tokenString = stringTokenizer.nextToken();
				tokenNumber++;
			// fourth token is multrun number
				if(tokenNumber == MULTRUN_TOKEN_NUMBER)
				{
					try
					{
						testMultRunNumber = Integer.parseInt(tokenString);
					}
					catch(NumberFormatException e)
					{
						testMultRunNumber = 0;
					}
				// Set multrun number to last number used on this night,
				// so that the next multrun number is the first we can use.
					if(testMultRunNumber > multRunNumber)
						multRunNumber = testMultRunNumber;
				}
			}
		}
	// Reset runNumber to zero.
		runNumber = 0;
	}

	/**
	 * Set routine for the exposure code.
	 * @param code The code to set to.
	 */
	public void setExposureCode(char code)
	{
		exposureCode = code;
	}

	/**
	 * Setup the next multi run. Should be called at the start of a multirun to increment the
	 * multirun number and reset the run number within a multirun.
	 */
	public void nextMultRunNumber()
	{
		multRunNumber++;
		runNumber = 0;
	}

	/**
	 * Setup the next run. Should be called just before each frames' first getFilename to increment the
	 * run number.
	 * @see #getFilename
	 */
	public void nextRunNumber()
	{
		runNumber++;
	}

	/**
	 * Set routine for the pipeline processing string.
	 * @param s The string to set pipeline processing to.
	 */
	public void setPipelineProcessing(String s)
	{
		if(s != null)
			pipelineProcessing = new String(s);
	}

	/**
	 * Set routine for the file extension string.
	 * @param s The string to set the file extension to, withour the '.'.
	 */
	public void setFileExtension(String s)
	{
		if(s != null)
			fileExtension = new String(s);
	}

	/**
	 * Routine to get the filename to put the next exposure into. The routine assumes the system
	 * clock is correct to get the right date. If a new multi run is about to start, it assumes 
	 * nextMultRunNumber has been called to increment the multrun. If this filename is for different
	 * frame to the last one, it assumes nextRunNumber has been called to increment the run (frame)
	 * number.
	 * @return The filename to put the next exposure into.
	 * @see #nextMultRunNumber
	 * @see #nextRunNumber
	 */
	public String getFilename()
	{
		String resultString = null;
		String dateString = null;

		dateString = getDateString();
		resultString = new String(directory+instrumentCode+"_"+exposureCode+"_"+dateString+"_"+multRunNumber+
			"_"+runNumber+"_"+windowNumber+"_"+pipelineProcessing+"."+fileExtension);
		return resultString;
	}

	/**
	 * Method to get the current value of the multrun number.
	 * @return The multrun number.
	 * @see #multRunNumber
	 */
	public int getMultRunNumber()
	{
		return multRunNumber;
	}

	/**
	 * Method to get the current value of the run number within the multrun i.e. which exposure 
	 * in the multrun is currently being processed. 
	 * @return The run number, from one. 
	 */
	public int getRunNumber()
	{
		return runNumber;
	}

	/**
	 * Internal method to return the date string part of a filename, of the form
	 * CCYYMMDD. The date and calendar object references will be updated to the current system time.
	 * The date string returned is the date <b>AT THE START OF THE NIGHT</b>, i.e. if the hours are less than
	 * twelve the previous date is returned.
	 * @return a String representing the date to go into a filename.
	 * @see #date
	 * @see #calendar
	 * @see #numberFormat
	 */
	private String getDateString()
	{
		String dayOfMonthString = null;
		String monthString = null;
		String dateString = null;

		date = new Date();// get the current date.
		calendar.setTime(date);// set the calendars current date to NOW.
		// The date string needs to be the date AT THE START OF THE NIGHT.
		// If the hour of day is less than midday, than the date at the start of the night
		// is the day before the current day so roll back the date by one day.
		if(calendar.get(Calendar.HOUR_OF_DAY) < 12)
		{
		// use add here, not roll. roll(-1) on the first day of the month does not change 
		// the month, add(-1) does
			calendar.add(Calendar.DAY_OF_MONTH,-1);// roll day of month down
		}
		dayOfMonthString = numberFormat.format(calendar.get(Calendar.DAY_OF_MONTH));
		monthString = numberFormat.format(calendar.get(Calendar.MONTH)+1);// months start at zero.
		dateString = new String(calendar.get(Calendar.YEAR)+monthString+dayOfMonthString);
		return dateString;
	}

	/**
	 * Inner class definition for a class that implements a FilenameFilter. This filter, when used on
	 * a directory, returns all filenames that have the constructors string in the filename
	 * (<b>NOT</b> directory name).
	 */
	private class SubstringFilenameFilter implements FilenameFilter
	{
		/**
	 	 * Private string containing the substring that accepted filenames will have in them. 
	 	 */
		private String subString = null;

		/**
		 * Constructor. 
		 * @param s The substring (of the filename) to test for acceptance.
		 */
		public SubstringFilenameFilter(String s)
		{
			super();
			subString = s;
		}

		/**
		 * Method that implements the FilenameFilter interface. 
		 * @param dir The directory containing the file we are testing.
		 * @param name The name of the file we are testing.
		 * @return Returns true if subString is a sub-string of name, false otherwise.
	 	 */
		public boolean accept(File dir,String name)
		{
			return (name.indexOf(subString) > -1);
		}
	}
}
//
// $Log: not supported by cvs2svn $
//
