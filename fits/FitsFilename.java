/*   
    Copyright 2006, Astrophysics Research Institute, Liverpool John Moores University.

    This file is part of NGAT.

    NGAT is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    NGAT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NGAT; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
// FitsFilename.java
// $Header: /space/home/eng/cjm/cvs/ngat/fits/FitsFilename.java,v 1.11 2011-11-01 10:06:43 cjm Exp $
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
 * @version $Revision$
 */
public class FitsFilename
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	// instrument codes
	/**
	 * Instrument code constant - FrodoSpec blue arm.
	 */
	public final static char INSTRUMENT_CODE_FRODOSPEC_BLUE 	= 'b';
	/**
	 * Instrument code constant - RATCAM/Dillcam N/S.
	 */
	public final static char INSTRUMENT_CODE_CCD_CAMERA 		= 'c';
	/**
	 * Instrument code constant - Dillcam North CCD Camera (originally/currently 'c').
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_DILLCAM_NORTH 		= 'd';
	/**
	 * Instrument code constant - Dillcam South CCD Camera (originally/currently 'c').
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_DILLCAM_SOUTH 		= 'e';
	/**
	 * Instrument code constant - FTSpec (North).
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_FTSPEC_NORTH 	        = 'f';
	/**
	 * Instrument code constant - RingoIII Red.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_RINGOIII_RED 	        = 'd';
	/**
	 * Instrument code constant - RingoIII Blue.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_RINGOIII_BLUE 		= 'e';
	/**
	 * Instrument code constant - RingoIII Green.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_RINGOIII_GREEN 	= 'f';
	/**
	 * Instrument code constant - FTSpec (South).
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_FTSPEC_SOUTH 	        = 'g';
	/**
	 * Instrument code constant - IO:O.
	 */
	public final static char INSTRUMENT_CODE_O        	        = 'h';
	/**
	 * Instrument code constant - IO:I.
	 */
	public final static char INSTRUMENT_CODE_I        	        = 'i';
	/**
	 * Instrument code constant - LIRIC.
	 */
	public final static char INSTRUMENT_CODE_LIRIC                  = 'j';
	/**
	 * Instrument code constant - LOTUS.
	 */
	public final static char INSTRUMENT_CODE_LOTUS        	        = 'l';
	/**
	 * Instrument code constant - NuView.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_NUVIEW 		= 'n';
	/**
	 * Instrument code constant - Ringo Star / Grope Polarimeter.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_RINGO_STAR 	        = 'o';
	/**
	 * Instrument code constant - Ringo2 Polarimeter.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_RINGO2 	        = 'p';
	/**
	 * Instrument code constant - RISE.
	 */
        public final static char INSTRUMENT_CODE_RISE 	                = 'q';
	/**
	 * Instrument code constant - FrodoSpec red arm.
	 */
	public final static char INSTRUMENT_CODE_FRODOSPEC_RED 	        = 'r';
	/**
	 * Instrument code constant - SupIRCam.
	 * This code is now deprecated.
	 */
	public final static char INSTRUMENT_CODE_SUPIRCAM        	= 's';
	/**
	 * Instrument code constant - Sprat.
	 */
	public final static char INSTRUMENT_CODE_SPRAT        	        = 'v';
	/**
	 * Instrument code constant - Moptop Andor camera 1.
	 */
	public final static char INSTRUMENT_CODE_MOPTOP_1               = '1';
	/**
	 * Instrument code constant - Moptop Andor camera 2.
	 */
	public final static char INSTRUMENT_CODE_MOPTOP_2               = '2';
	/**
	 * Instrument code constant - Moptop PCO camera 1.
	 */
	public final static char INSTRUMENT_CODE_MOPTOP_3               = '3';
	/**
	 * Instrument code constant - Moptop PCO camera 2.
	 */
	public final static char INSTRUMENT_CODE_MOPTOP_4               = '4';

	// exposure codes 
	/**
	 * Exposure code constant - for an arc.
	 */
	public final static char EXPOSURE_CODE_ARC 			= 'a';
	/**
	 * Exposure code constant - for a bias frame.
	 */
	public final static char EXPOSURE_CODE_BIAS 			= 'b';
	/**
	 * Exposure code constant - for a dark frame.
	 */
	public final static char EXPOSURE_CODE_DARK 			= 'd';
	/**
	 * Exposure code constant - for an exposure.
	 */
	public final static char EXPOSURE_CODE_EXPOSURE 		= 'e';
	/**
	 * Exposure code constant - for a sky flat.
	 */
	public final static char EXPOSURE_CODE_SKY_FLAT 		= 'f';
	/**
	 * Exposure code constant - for an acquisition image.
	 */
	public final static char EXPOSURE_CODE_ACQUIRE 		        = 'q';
	/**
	 * Exposure code constant - for an standard.
	 */
	public final static char EXPOSURE_CODE_STANDARD 		= 's';
	/**
	 * Exposure code constant - for a lamp flat.
	 */
	public final static char EXPOSURE_CODE_LAMP_FLAT 		= 'w';
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
	 * Internal constant. The firsat element of a FitsFilename tokenized on '_.' will
	 * be the instrument code.
	 */
	private final static int INSTRUMENT_TOKEN_NUMBER 		= 1;
	/**
	 * Internal constant. The second element of a FitsFilename tokenized on '_.' will
	 * be the exposure code.
	 */
	private final static int EXPOSE_CODE_TOKEN_NUMBER 		= 2;
	/**
	 * Internal constant. The third element of a FitsFilename tokenized on '_.' will
	 * be the date in the format yyyymmdd.
	 */
	private final static int DATE_TOKEN_NUMBER 		        = 3;
	/**
	 * Internal constant. The fourth element of a FitsFilename tokenized on '_.' will
	 * be the MULTRUN number.
	 */
	private final static int MULTRUN_TOKEN_NUMBER 			= 4;
	/**
	 * Internal constant. The fifth element of a FitsFilename tokenized on '_.' will
	 * be the RUN number within a multrun.
	 */
	private final static int RUN_TOKEN_NUMBER 			= 5;
	/**
	 * Internal constant. The sixth element of a FitsFilename tokenized on '_.' will
	 * be the Window number.
	 */
	private final static int WINDOW_TOKEN_NUMBER 			= 6;
	/**
	 * Internal constant. The seventh element of a FitsFilename tokenized on '_.' will
	 * be the pipeline processing.
	 */
	private final static int PIPELINE_PROCESSING_TOKEN_NUMBER      	= 7;
	/**
	 * Internal constant. The eighth element of a FitsFilename tokenized on '_.' will
	 * be the file extension.
	 */
	private final static int FILE_EXTENSION_TOKEN_NUMBER 		= 8;
	/**
	 * Directory string to be pre-pended to the filename..
	 */
	private String directory = new String("./");
	/**
	 * Instrument code describing the instrument the exposure was taken with.
	 * @see #INSTRUMENT_CODE_CCD_CAMERA
	 */
	private char instrumentCode = INSTRUMENT_CODE_CCD_CAMERA;
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
	 * String representation of the date.
	 */
	private String dateString = null;
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
	 * Boolean, if true, indicates filename is a temporary one of the form: telFocus<n>[_1].fits
	 */
	private boolean isTelfocus = false;
	/**
	 * Boolean, if true, indicates filename is a temporary one of the form: twilight_calibrate[_1].fits
	 */
	private boolean isTwilightCalibrate = false;

	/**
	 * Constructor for FitsFilename. Sets up default values for fields. Also sets numberFormat to print
	 * out 2 digit integers (for month/date string creation). 
	 * @see #getDateString
	 */
	public FitsFilename()
	{
		directory = new String("./");
		instrumentCode = INSTRUMENT_CODE_CCD_CAMERA;
		exposureCode = EXPOSURE_CODE_EXPOSURE;
		calendar = Calendar.getInstance();
		numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumIntegerDigits(2);
		date = new Date();
		dateString = getDateString();
		multRunNumber = 0;
		runNumber = 0;
		windowNumber = 1;
		pipelineProcessing = PIPELINE_PROCESSING_FLAG_NONE;
		fileExtension = new String("fits");
		isTelfocus = false;
		isTwilightCalibrate = false;
	}

	/**
	 * Copy constructor for a FitsFilename. The new FitsFilename instance has the same values for it's data fields 
	 * as the passed in instance. Mostly these are copied where the values are non-mutable, 
	 * date has a new instance created as it is mutable. calendar and numberFormat are new instances, setup in the
	 * same way as the default constructor.
	 * @param f The FitsFilename instance to copy settings from.
	 * @see #directory
	 * @see #instrumentCode
	 * @see #exposureCode
	 * @see #calendar
	 * @see #numberFormat
	 * @see #date
	 * @see #dateString
	 * @see #multRunNumber
	 * @see #runNumber
	 * @see #windowNumber
	 * @see #pipelineProcessing
	 * @see #fileExtension
	 * @see #isTelfocus
	 * @see #isTwilightCalibrate
	 */
	public FitsFilename(FitsFilename f)
	{
		super();
		directory = f.directory;
		instrumentCode = f.instrumentCode;
		exposureCode = f.exposureCode;
		calendar = Calendar.getInstance();
		numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumIntegerDigits(2);
		date = new Date(f.date.getTime());
		// Note dateString must be the same as the copied version, not regenerated from getDateString().
		// Otherwise this will go wrong when MULTRUNs are run over midday.
		dateString = f.dateString;
		multRunNumber = f.multRunNumber;
		runNumber = f.runNumber;
		windowNumber = f.windowNumber;
		pipelineProcessing = f.pipelineProcessing;
		fileExtension = f.fileExtension;
		isTelfocus = f.isTelfocus;
		isTwilightCalibrate = f.isTwilightCalibrate;
	}

	/**
	 * This method should be called after the object has been constructed and the directory path 
	 * and instrument code have been set.
	 * It initialises the multRunNumber to be the last one used on this night (zero if none exist), for when the
	 * ICS has been rebooted during one nights work. This means when nextMultRunNumber is called a filename
	 * will be generated that does not already exist. The run number is reset to zero, the window number to one.
	 * @exception Exception Thrown if the specified directory cannot be found, or an error occurs retrieving
	 * 	a directory listing.
	 * @see #directory
	 * @see FitsFilename.SubstringFilenameFilter
	 * @see #multRunNumber
	 * @see #runNumber
	 * @see #windowNumber
	 */
	public void initialise() throws Exception
	{
		StringTokenizer stringTokenizer = null;
		String directoryList[] = null;
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
		windowNumber = 1;
	}

	/**
	 * This method should be called after the object has been constructed and the directory path has been set.
	 * This method parses an already existing filename string into it's component parts and sets the fields
	 * in this object accordingly.
	 * @exception Exception Thrown if the specified directory cannot be found, or an error occurs
	 * 	during the parsing.
	 * @see #multRunNumber
	 * @see #runNumber
	 * @see #windowNumber
	 * @see #isTelfocus
	 * @see #isTwilightCalibrate
	 */
	public void parse(String filename) throws Exception
	{
		StringTokenizer stringTokenizer = null;
		File file = null;
		String s = null;
		int tokenNumber,directoryIndex,eIndex;

		// handle directory.
		directoryIndex = filename.lastIndexOf(System.getProperty("file.separator"));
		if(directoryIndex < 0)
		{
			setDirectory("");
		}
		else
		{
			setDirectory(filename.substring(0,directoryIndex+1));
			filename = filename.substring(directoryIndex+1,filename.length());
		}
		// check telFocus, twilight_calibrate filenames
		if(filename.startsWith("telFocus"))
		{
			isTelfocus = true;
			eIndex = filename.indexOf('.');
			if((filename.indexOf('_') > -1) && (filename.indexOf('_') < eIndex))
			{
				eIndex = filename.indexOf('_');
				setPipelineProcessing(PIPELINE_PROCESSING_FLAG_REAL_TIME);
			}
			else
				setPipelineProcessing(PIPELINE_PROCESSING_FLAG_NONE);
			// run number
			s = filename.substring("telFocus".length(),eIndex);
			try
			{
				runNumber = Integer.parseInt(s);
			}
			catch(NumberFormatException e)
			{
				throw new Exception(this.getClass().getName()+
						    ":parse: Failed to parse run number "+s+
						    " in filename "+filename+" : "+e);
			}
			instrumentCode = INSTRUMENT_CODE_CCD_CAMERA;
			exposureCode = EXPOSURE_CODE_EXPOSURE;
			dateString = getDateString();
			return; // finished parsing
		}
		else
			isTelfocus = false;
		if(filename.startsWith("twilight_calibrate"))
		{
			isTwilightCalibrate = true;
			eIndex = filename.indexOf('.');
			if((filename.lastIndexOf('_') > -1) && (filename.lastIndexOf('_') < eIndex) &&
			   (filename.lastIndexOf('_') != filename.indexOf('_')))
			{
				eIndex = filename.lastIndexOf('_');
				setPipelineProcessing(PIPELINE_PROCESSING_FLAG_REAL_TIME);
			}
			else
				setPipelineProcessing(PIPELINE_PROCESSING_FLAG_NONE);
			// run number
			runNumber = 1;
			instrumentCode = INSTRUMENT_CODE_CCD_CAMERA;
			exposureCode = EXPOSURE_CODE_SKY_FLAT;
			dateString = getDateString();
			return; // finished parsing
		}
		else
			isTwilightCalibrate = false;
		// tokenize each filename by '_.' and look through tokens.
       		stringTokenizer = new StringTokenizer(filename,"_.");
	       	tokenNumber = 0;
		while(stringTokenizer.hasMoreTokens())
		{
		       	String tokenString = stringTokenizer.nextToken();
			tokenNumber++; // Note therefore tokens start at ONE.

			switch(tokenNumber)
			{
				case INSTRUMENT_TOKEN_NUMBER:
					setInstrumentCode(tokenString);
					break;
				case EXPOSE_CODE_TOKEN_NUMBER:
					setExposureCode(tokenString);
					break;
				case DATE_TOKEN_NUMBER:
					dateString = tokenString;
					break;
				case MULTRUN_TOKEN_NUMBER:
					try
					{
						multRunNumber = Integer.parseInt(tokenString);
					}
					catch(NumberFormatException e)
					{
						throw new Exception(this.getClass().getName()+
							       ":parse: Failed to parse multrun number "+tokenString+
								    " in filename "+filename+" : "+e);
					}
					break;
				case RUN_TOKEN_NUMBER:
					try
					{
						runNumber = Integer.parseInt(tokenString);
					}
					catch(NumberFormatException e)
					{
						throw new Exception(this.getClass().getName()+
							       ":parse: Failed to parse run number "+tokenString+
								    " in filename "+filename+" : "+e);
					}
					break;
				case WINDOW_TOKEN_NUMBER:
					try
					{
						windowNumber = Integer.parseInt(tokenString);
					}
					catch(NumberFormatException e)
					{
						throw new Exception(this.getClass().getName()+
							       ":parse: Failed to parse window number "+tokenString+
								    " in filename "+filename+" : "+e);
					}
					break;
				case PIPELINE_PROCESSING_TOKEN_NUMBER:
					setPipelineProcessing(tokenString);
					break;
				case FILE_EXTENSION_TOKEN_NUMBER:
					setFileExtension(tokenString);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Set routine for the instrument code.
	 * @param code The code to set to.
	 * @see #instrumentCode
	 * @exception Exception Thrown if code is NULL, is not of length 1 or is not a valid instrument code.
	 * @see #INSTRUMENT_CODE_FRODOSPEC_BLUE
	 * @see #INSTRUMENT_CODE_CCD_CAMERA
	 * @see #INSTRUMENT_CODE_DILLCAM_NORTH
	 * @see #INSTRUMENT_CODE_DILLCAM_SOUTH
	 * @see #INSTRUMENT_CODE_FTSPEC_NORTH
	 * @see #INSTRUMENT_CODE_FTSPEC_SOUTH
	 * @see #INSTRUMENT_CODE_RINGOIII_RED
	 * @see #INSTRUMENT_CODE_RINGOIII_BLUE
	 * @see #INSTRUMENT_CODE_RINGOIII_GREEN
	 * @see #INSTRUMENT_CODE_LOTUS
	 * @see #INSTRUMENT_CODE_NUVIEW
	 * @see #INSTRUMENT_CODE_RINGO_STAR
	 * @see #INSTRUMENT_CODE_RINGO2
	 * @see #INSTRUMENT_CODE_FRODOSPEC_RED
	 * @see #INSTRUMENT_CODE_SUPIRCAM
	 * @see #INSTRUMENT_CODE_RISE
	 * @see #INSTRUMENT_CODE_I
	 * @see #INSTRUMENT_CODE_O
	 * @see #INSTRUMENT_CODE_SPRAT
	 * @see #INSTRUMENT_CODE_MOPTOP_1
	 * @see #INSTRUMENT_CODE_MOPTOP_2
	 * @see #INSTRUMENT_CODE_MOPTOP_3
	 * @see #INSTRUMENT_CODE_MOPTOP_4
	 * @see #INSTRUMENT_CODE_LIRIC
	 */
	public void setInstrumentCode(String code) throws Exception
	{
		char ch;

		if(code == null)
		{
			throw new Exception(this.getClass().getName()+":setInstrumentCode:Code was NULL.");
		}
		if(code.length() != 1)
		{
			throw new Exception(this.getClass().getName()+
					    ":setInstrumentCode:Illegal instrument code length"+
					    code+" with length "+code.length()+".");
		}
		ch = code.charAt(0);
		if((ch != INSTRUMENT_CODE_FRODOSPEC_BLUE) && (ch != INSTRUMENT_CODE_CCD_CAMERA) && 
		   (ch != INSTRUMENT_CODE_DILLCAM_NORTH) && (ch != INSTRUMENT_CODE_DILLCAM_SOUTH) && 
		   (ch != INSTRUMENT_CODE_FTSPEC_NORTH) && (ch != INSTRUMENT_CODE_FTSPEC_SOUTH) &&
		   (ch != INSTRUMENT_CODE_NUVIEW) && 
		   (ch != INSTRUMENT_CODE_RINGO_STAR) && (ch != INSTRUMENT_CODE_RINGO2) && 
		   (ch != INSTRUMENT_CODE_RINGOIII_RED) &&(ch != INSTRUMENT_CODE_RINGOIII_BLUE) &&
		   (ch != INSTRUMENT_CODE_RINGOIII_GREEN) && (ch != INSTRUMENT_CODE_RISE) && 
		   (ch != INSTRUMENT_CODE_FRODOSPEC_RED) && (ch != INSTRUMENT_CODE_SUPIRCAM) &&
		   (ch != INSTRUMENT_CODE_I) && (ch != INSTRUMENT_CODE_O)&& (ch != INSTRUMENT_CODE_SPRAT) && 
		   (ch != INSTRUMENT_CODE_LOTUS) && (ch != INSTRUMENT_CODE_LIRIC) &&
		   (ch != INSTRUMENT_CODE_MOPTOP_1) && (ch != INSTRUMENT_CODE_MOPTOP_2) &&
		   (ch != INSTRUMENT_CODE_MOPTOP_3) && (ch != INSTRUMENT_CODE_MOPTOP_4))
		{
			throw new Exception(this.getClass().getName()+":setInstrumentCode:Illegal instrument code "+
					    ch+".");
		}
		instrumentCode = ch;
	}

	/**
	 * Set routine for the directory. If the directory is not terminated with a file separator,
	 * one is added.
	 * @param d The directory string.
	 * @see #directory
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
	 * Set routine for the exposure code.
	 * @param codeString The code to set to.
	 * @see #exposureCode
	 * @exception Exception Thrown if codeString is NULL, is not of length 1 or is not a valid exposure code.
	 * @see #EXPOSURE_CODE_ARC
	 * @see #EXPOSURE_CODE_BIAS
	 * @see #EXPOSURE_CODE_DARK
	 * @see #EXPOSURE_CODE_EXPOSURE
	 * @see #EXPOSURE_CODE_ACQUIRE
	 * @see #EXPOSURE_CODE_SKY_FLAT
	 * @see #EXPOSURE_CODE_STANDARD
	 * @see #EXPOSURE_CODE_LAMP_FLAT
	 */
	public void setExposureCode(String codeString) throws Exception
	{
		char code;

		if(codeString == null)
		{
			throw new Exception(this.getClass().getName()+":setExposureCode:Code was NULL.");
		}
		if(codeString.length() != 1)
		{
			throw new Exception(this.getClass().getName()+
					    ":setExposureCode:Illegal instrument code length"+
					    codeString+" with length "+codeString.length()+".");
		}
		code = codeString.charAt(0);
		if ((code != EXPOSURE_CODE_ARC) && (code != EXPOSURE_CODE_BIAS) && (code != EXPOSURE_CODE_DARK) && 
		    (code != EXPOSURE_CODE_EXPOSURE) && (code != EXPOSURE_CODE_SKY_FLAT) && 
		    (code != EXPOSURE_CODE_ACQUIRE) && (code != EXPOSURE_CODE_STANDARD) && 
		    (code != EXPOSURE_CODE_LAMP_FLAT))
		{
			throw new Exception(this.getClass().getName()+":setExposureCode:Illegal exposure code "+
					    code+".");
		}
		exposureCode = code;
	}

	/**
	 * Set routine for the exposure code.
	 * @param code The code to set to.
	 * @exception Exception Thrown if code is not a valid exposure code.
	 * @see #exposureCode
	 * @see #EXPOSURE_CODE_ARC
	 * @see #EXPOSURE_CODE_BIAS
	 * @see #EXPOSURE_CODE_DARK
	 * @see #EXPOSURE_CODE_EXPOSURE
	 * @see #EXPOSURE_CODE_ACQUIRE
	 * @see #EXPOSURE_CODE_SKY_FLAT
	 * @see #EXPOSURE_CODE_STANDARD
	 * @see #EXPOSURE_CODE_LAMP_FLAT
	 */
	public void setExposureCode(char code) throws Exception
	{
		if ((code != EXPOSURE_CODE_ARC) && (code != EXPOSURE_CODE_BIAS) && (code != EXPOSURE_CODE_DARK) && 
		    (code != EXPOSURE_CODE_EXPOSURE) && (code != EXPOSURE_CODE_SKY_FLAT) && 
		    (code != EXPOSURE_CODE_ACQUIRE) && (code != EXPOSURE_CODE_STANDARD) && 
		    (code != EXPOSURE_CODE_LAMP_FLAT))
		{
			throw new Exception(this.getClass().getName()+":setExposureCode:Illegal exposure code "+
					    code+".");
		}
		exposureCode = code;
	}

	/**
	 * Setup the next multi run. Should be called at the start of a multirun to increment the
	 * multrun number and reset the run number within a multrun. Also resets the window number to 1.
	 * And resets dateString.
	 * @see #multRunNumber
	 * @see #runNumber
	 * @see #windowNumber
	 * @see #dateString
	 * @see #getDateString
	 */
	public void nextMultRunNumber()
	{
		String newDateString = null;

		newDateString = getDateString();
		// assume dateString can never be null at this point, 
		// according to constructor/initialise this is true.
		// has the night date changed since last multrun/initialise?
		if(dateString.equals(newDateString) == false)
		{
			// new night date rollover detected - reset to multrun 1 of night
			multRunNumber = 0;
			runNumber = 0;
			windowNumber = 1;
		}
		dateString = newDateString;
		multRunNumber++;
		runNumber = 0;
		windowNumber = 1;
	}

	/**
	 * Setup the next run. Should be called just before each frames' first getFilename to increment the
	 * run number. Note the window number is reset to 1.
	 * And resets dateString.
	 * @see #getFilename
	 * @see #runNumber
	 * @see #windowNumber
	 * @see #dateString
	 * @see #getDateString
	 */
	public void nextRunNumber()
	{
		runNumber++;
		windowNumber = 1;
		dateString = getDateString();
	}

	/**
	 * Set routine for the pipeline processing string.
	 * @param s The string to set pipeline processing to.
	 * @exception Exception Thrown if string s is not a valid pipeline processing flag.
	 * @see #PIPELINE_PROCESSING_FLAG_NONE
	 * @see #PIPELINE_PROCESSING_FLAG_REAL_TIME
	 * @see #PIPELINE_PROCESSING_FLAG_OFF_LINE
	 */
	public void setPipelineProcessing(String s) throws Exception
	{
		if(s == null)
		{
			throw new Exception(this.getClass().getName()+":setPipelineProcessing:Flag was NULL. ");
		}
		if((s.equals(PIPELINE_PROCESSING_FLAG_NONE) == false) && 
		   (s.equals(PIPELINE_PROCESSING_FLAG_REAL_TIME) == false) && 
		   (s.equals(PIPELINE_PROCESSING_FLAG_OFF_LINE) == false))
		{
			throw new Exception(this.getClass().getName()+":setPipelineProcessing:Illegal pipeline flag "+
					    s+".");
		}
		pipelineProcessing = new String(s);
	}

	/**
	 * Set routine for the file extension string.
	 * @param s The string to set the file extension to, withour the '.'.
	 * @see #fileExtension
	 */
	public void setFileExtension(String s)
	{
		if(s != null)
			fileExtension = new String(s);
	}

	/**
	 * Set routine for the window number.
	 * @param n The window number. Should be between 1 and 4.
	 * @see #windowNumber
	 */
	public void setWindowNumber(int n)
	{
		windowNumber = n;
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
	 * @see #directory
	 * @see #instrumentCode
	 * @see #exposureCode
	 * @see #dateString
	 * @see #multRunNumber
	 * @see #runNumber
	 * @see #windowNumber
	 * @see #pipelineProcessing
	 * @see #fileExtension
	 * @see #isTelfocus
	 * @see #isTwilightCalibrate
	 */
	public String getFilename()
	{
		String resultString = null;

		if(isTelfocus)
		{
			resultString = new String(directory+"telFocus"+runNumber);
			if(pipelineProcessing.equals(PIPELINE_PROCESSING_FLAG_NONE) == false)
				resultString = new String(resultString+"_"+pipelineProcessing);
			resultString = new String(resultString+"."+fileExtension);
		}
		else if(isTwilightCalibrate)
		{
			resultString = new String(directory+"twilight_calibrate");
			if(pipelineProcessing.equals(PIPELINE_PROCESSING_FLAG_NONE) == false)
				resultString = new String(resultString+"_"+pipelineProcessing);
			resultString = new String(resultString+"."+fileExtension);
		}
		else
		{
			resultString = new String(directory+instrumentCode+"_"+exposureCode+"_"+dateString+"_"+
						  multRunNumber+"_"+runNumber+"_"+windowNumber+"_"+
						  pipelineProcessing+"."+fileExtension);
		}
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
// Revision 1.10  2009/12/18 12:01:35  cjm
// Added INSTRUMENT_CODE_RINGO2 and EXPOSURE_CODE_ACQUIRE.
//
// Revision 1.9  2009/02/23 15:17:18  cjm
// Added INSTRUMENT_CODE_RISE.
//
// Revision 1.8  2006/05/16 17:42:21  cjm
// gnuify: Added GNU General Public License.
//
// Revision 1.7  2006/03/29 11:25:40  cjm
// Modified nextMultRunNumber so that it detects when the new dateString is not the same as
// the current dateString. It then resets the multrun number (etc) and assumes a date rollover has
// taken place.
//
// Revision 1.6  2005/11/29 11:00:51  cjm
// Updated instrument codes.
//
// Revision 1.5  2005/04/13 14:13:29  cjm
// Added returns in parse for telFocus and twilight_calibrate parsing.
//
// Revision 1.4  2005/04/13 13:07:56  cjm
// Attempted to fix bug in setPipelineProcessing flag test.
//
// Revision 1.3  2005/03/31 13:16:20  cjm
// Added INSTRUMENT_CODE constants.
// Added _TOKEN_NUMBER constants for parsing.
// instrumentCode now a char.
// dateString generation changed - internal string now used.
// Specialist checks for telFocus and twilight_calibrate filenames.
// Added filename parsing method.
// setInstrumentCode now throws exception is argument is illegal.
// setExposureCode now throws exception is argument is illegal.
// setPipelineProcessing now throws exception is argument is illegal.
// getFilename now uses previously set dateSTring internal variable.
// getFilename now checks isTelfocus and isTwilightCalibrate variables and returns different filenames.
// All this added for support for filename locking.
//
// Revision 1.2  2003/06/06 16:52:15  cjm
// Windowing implementation.
//
// Revision 1.1  2001/06/21 11:06:27  cjm
// Initial revision
//
//
