// CCDLibrary.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibrary.java,v 0.13 2000-01-24 14:08:57 cjm Exp $
package ngat.ccd;

/**
 * This class supports an interface to the SDSU CCD Controller library, for controlling CCDs.
 * @author Chris Mottram
 * @version $Revision: 0.13 $
 */
public class CCDLibrary
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: CCDLibrary.java,v 0.13 2000-01-24 14:08:57 cjm Exp $");
// ccd_dsp.h
	/* These constants should be the same as those in ccd_dsp.h */
	/**
	 * Set Gain parameter, to set gain to 1.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_GAIN_ONE = 			0x1;
	/**
	 * Set Gain parameter, to set gain to 2.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_GAIN_TWO = 			0x2;
	/**
	 * Set Gain parameter, to set gain to 4.75.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_GAIN_FOUR = 			0x5;
	/**
	 * Set Gain parameter, to set gain to 9.5.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_GAIN_NINE = 			0xa;

	/* These constants should be the same as those in ccd_dsp.h */
	/**
	 * DSP exposure status number, showing that no exposure is underway at the present moment.
	 * @see #CCDDSPGetExposureStatus
	 */
	public final static int CCD_DSP_EXPOSURE_STATUS_NONE = 	0;
	/**
	 * DSP exposure status number, showing that an exposure is underway at the present moment.
	 * @see #CCDDSPGetExposureStatus
	 */
	public final static int CCD_DSP_EXPOSURE_STATUS_EXPOSE = 	1;
	/**
	 * DSP exposure status number, showing that a readout is underway at the present moment.
	 * @see #CCDDSPGetExposureStatus
	 */
	public final static int CCD_DSP_EXPOSURE_STATUS_READOUT = 	2;

	/* These constants should be the same as those in ccd_dsp.h */
	/**
	 * De-interlace type. This setting does no deinterlacing, as the CCD was read out from a single readout.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_DEINTERLACE_SINGLE = 			0;
	/**
	 * De-interlace type. This setting deinterlaces split parallel readout.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_DEINTERLACE_SPLIT_PARALLEL = 		1;
	/**
	 * De-interlace type. This setting deinterlaces split serial readout.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_DEINTERLACE_SPLIT_SERIAL = 		2;
	/**
	 * De-interlace type. This setting deinterlaces split quad readout.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_DSP_DEINTERLACE_SPLIT_QUAD = 		3;

// ccd_interface.h
	/* These constants should be the same as those in ccd_interface.h */
	/**
	 * Interface device number, showing that commands will currently be sent nowhere.
	 * @see #CCDInitialise
	 */
	public final static int CCD_INTERFACE_DEVICE_NONE = 		0;
	/**
	 * Interface device number, showing that commands will currently be sent to the text interface 
	 * to be printed out.
	 * @see #CCDInitialise
	 */
	public final static int CCD_INTERFACE_DEVICE_TEXT =		1;
	/**
	 * Interface device number, showing that commands will currently be sent to the PCI interface.
	 * @see #CCDInitialise
	 */
	public final static int CCD_INTERFACE_DEVICE_PCI = 		2;
// ccd_setup.h 
	/* These constants should be the same as those in ccd_setup.h */
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to Reset the SDSU controller.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_RESET_CONTROLLER = 		(1<<0);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to do a hardware test.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_HARDWARE_TEST = 		(1<<1);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to send an application to the timing board.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_TIMING_BOARD = 		(1<<2);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to send an application to the utility board.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_UTILITY_BOARD = 		(1<<3);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to turn the power on.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_POWER_ON = 			(1<<4);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to set a target CCD temperature.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_TARGET_CCD_TEMP = 		(1<<5);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to set the CCD gain/speed.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_GAIN = 			(1<<6);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to decide whether to set the idle status.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_IDLE = 			(1<<7);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to send the CCD dimensions/binning to the boards.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_DIMENSIONS = 		(1<<8);
	/**
	 * Setup flag passed to CCDSetupSetupCCD, to decide to set the deinterlace type.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_DEINTERLACE = 		(1<<9);
	/**
	 * Default setup flag passed to CCDSetupSetupCCD, which sets up all information.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_FLAG_ALL = (CCD_SETUP_FLAG_RESET_CONTROLLER|CCD_SETUP_FLAG_HARDWARE_TEST|
		CCD_SETUP_FLAG_TIMING_BOARD|CCD_SETUP_FLAG_UTILITY_BOARD|CCD_SETUP_FLAG_POWER_ON|
		CCD_SETUP_FLAG_TARGET_CCD_TEMP|
		CCD_SETUP_FLAG_GAIN|CCD_SETUP_FLAG_IDLE|CCD_SETUP_FLAG_DIMENSIONS|CCD_SETUP_FLAG_DEINTERLACE);

	/* These constants should be the same as those in ccd_setup.h */
	/**
	 * Setup Load Type passed to CCDSetupSetupCCD as a load_type parameter, to load DSP application code from 
	 * EEPROM.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_LOAD_APPLICATION = 		0;
	/**
	 * Setup flag passed to CCDSetupSetupCCD as a load_type parameter, to load DSP application code from a file.
	 * @see #CCDSetupSetupCCD
	 */
	public final static int CCD_SETUP_LOAD_FILENAME = 			1;

// ccd_text.h
	/* These constants should be the same as those in ccd_text.h */
	/**
	 * Level number passed to CCDTextSetPrintLevel, to print out commands only.
	 * @see #CCDTextSetPrintLevel
	 */
	public final static int CCD_TEXT_PRINT_LEVEL_COMMANDS = 		0;
	/**
	 * Level number passed to CCDTextSetPrintLevel, to print out commands replies as well.
	 * @see #CCDTextSetPrintLevel
	 */
	public final static int CCD_TEXT_PRINT_LEVEL_REPLIES = 			1;
	/**
	 * Level number passed to CCDTextSetPrintLevel, to print out parameter value information as well.
	 * @see #CCDTextSetPrintLevel
	 */
	public final static int CCD_TEXT_PRINT_LEVEL_VALUES = 			2;
	/**
	 * Level number passed to CCDTextSetPrintLevel, to print out everything.
	 * @see #CCDTextSetPrintLevel
	 */
	public final static int CCD_TEXT_PRINT_LEVEL_ALL = 			3;

// ccd_dsp.h
	/**
	 * Native wrapper to libccd routine thats returns whether an exposure is currently in progress.
	 */
	private native int CCD_DSP_Get_Exposure_Status();
	/**
	 * Native wrapper to return ccd_dsp's error number.
	 */
	private native int CCD_DSP_Get_Error_Number();

// ccd_exposure.h
	/**
	 * Native wrapper to libccd routine that does an exposure.
	 * @exception CCDLibraryNativeException This routine throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Exposure_Expose(boolean open_shutter,boolean readout_ccd,
		int msecs,String filename) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that takes a bias frame.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Exposure_Bias(String filename) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that does a readout of the CCD.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Exposure_Read_Out_CCD(String filename) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that aborts an exposure.
	 */
	private native void CCD_Exposure_Abort();
	/**
	 * Native wrapper to libccd routine that aborts the readout of an exposure.
	 */
	private native void CCD_Exposure_Abort_Readout();
	/**
	 * Native wrapper to return ccd_exposure's error number.
	 */
	private native int CCD_Exposure_Get_Error_Number();

// ccd_global.h
	/**
	 * Native wrapper to libccd routine that sets up the CCD library for use.
	 */
	private native void CCD_Global_Initialise(int interface_device);
	/**
	 * Native wrapper to libccd routine that prints error values.
	 */
	private native void CCD_Global_Error();
	/**
	 * Native wrapper to libccd routine that gets error values into a string.
	 */
	private native String CCD_Global_Error_String();

// ccd_interface.h
	/**
	 * Native wrapper to libccd routine that opens the selected interface device.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Interface_Open() throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that closes the selected interface device.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Interface_Close() throws CCDLibraryNativeException;

// ccd_setup.h
	/**
	 * Native wrapper to libccd routine that does the CCD setup.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Setup_Setup_CCD(int setup_flags,
		int timing_load_type,int timing_application_number,String timing_filename,
		int utility_load_type,int utility_application_number,String utility_filename,
		double target_temperature,int gain,boolean gain_speed,boolean idle,
		int ncols,int nrows,int nsbin,int npbin,int deinterlace_setting) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that aborts the CCD setup.
	 */
	private native void CCD_Setup_Abort();
	/**
	 * Native wrapper to libccd routine that gets the number of columns.
	 */
	private native int CCD_Setup_Get_NCols();
	/**
	 * Native wrapper to libccd routine that gets the number of Rows.
	 */
	private native int CCD_Setup_Get_NRows();
	/**
	 * Native wrapper to libccd routine that aborts the CCD setupgets whether a setup operation is in progress.
	 */
	private native boolean CCD_Setup_Get_Setup_In_Progress();
	/**
	 * Native wrapper to return ccd_setup's error number.
	 */
	private native int CCD_Setup_Get_Error_Number();

// ccd_temperature.h
	/**
	 * Native wrapper to libccd routine that gets the current temperature of the CCD.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Temperature_Get(CCDLibraryDouble temperature) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to libccd routine that sets the current temperature of the CCD.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	private native void CCD_Temperature_Set(double target_temperature) throws CCDLibraryNativeException;
	/**
	 * Native wrapper to return ccd_temperature's error number.
	 */
	private native int CCD_Temperature_Get_Error_Number();

// ccd_text.h
	/**
	 * Native wrapper to libccd routine that sets the amount of output from the text interface.
	 */
	private native void CCD_Text_Set_Print_Level(int level);

	/**
	 * Static code to load libccd, the shared C library that implements an interface to the
	 * SDSU CCD Controller.
	 */
	static
	{
		System.loadLibrary("ccd");
	}

// ccd_dsp.h
	/**
	 * Returns whether an exposure is currently in progress. The library keeps track of whether a call to
	 * <a href="#CCDExposureExpose">CCDExposureExpose</a> is in progress, and whether it is exposing or reading out
	 * @return Returns the exposure status, one of 
	 * 	<a href="#CCD_DSP_EXPOSURE_STATUS_NONE">CCD_DSP_EXPOSURE_STATUS_NONE</a>,
	 * 	<a href="#CCD_DSP_EXPOSURE_STATUS_EXPOSE">CCD_DSP_EXPOSURE_STATUS_EXPOSE</a> and 
	 *	<a href="#CCD_DSP_EXPOSURE_STATUS_READOUT">CCD_DSP_EXPOSURE_STATUS_READOUT</a>.
	 * @see #CCDExposureExpose
	 */
	public int CCDDSPGetExposureStatus()
	{
		return CCD_DSP_Get_Exposure_Status();
	}

	/**
	 * Returns the current error number from this module of the library. A zero means there is no error.
	 * @return Returns an error number.
	 */
	public int CCDDSPGetErrorNumber()
	{
		return CCD_DSP_Get_Error_Number();
	}

	/**
	 * Routine to parse a gain string and return a gain number suitable for input into
	 * <a href="#CCDSetupSetupCCD">CCDSetupSetupCCD</a>, or a DSP Set Gain (SGN) command. 
	 * @param s The string to parse.
	 * @return The gain number, one of:
	 * 	<ul>
	 * 	<li><a href="#CCD_DSP_GAIN_ONE">CCD_DSP_GAIN_ONE</a>
	 * 	<li><a href="#CCD_DSP_GAIN_TWO">CCD_DSP_GAIN_TWO</a>
	 * 	<li><a href="#CCD_DSP_GAIN_FOUR">CCD_DSP_GAIN_FOUR</a>
	 * 	<li><a href="#CCD_DSP_GAIN_NINE">CCD_DSP_GAIN_NINE</a>
	 * 	</ul>.
	 * @exception CCDLibraryFormatException If the string was not an accepted value an exception is thrown.
	 */
	public int CCDDSPGainFromString(String s) throws CCDLibraryFormatException
	{
		if(s.equals("CCD_DSP_GAIN_ONE"))
			return CCD_DSP_GAIN_ONE;
		if(s.equals("CCD_DSP_GAIN_TWO"))
			return CCD_DSP_GAIN_TWO;
		if(s.equals("CCD_DSP_GAIN_FOUR"))
			return CCD_DSP_GAIN_FOUR;
		if(s.equals("CCD_DSP_GAIN_NINE"))
			return CCD_DSP_GAIN_NINE;
		throw new CCDLibraryFormatException(this.getClass().getName(),"CCDDSPGainFromString",s);
	}

	/**
	 * Routine to parse a string containing a representation of a valid deinterlace type and to return
	 * the numeric value of that type, suitable for passing into <a href="#CCDSetupSetupCCD">CCDSetupSetupCCD</a>
	 * and other methods.
	 * @param s The string to parse.
	 * @return The deinterlace type number, one of:
	 * 	<ul>
	 * 	<li><a href="#CCD_DSP_DEINTERLACE_SINGLE">CCD_DSP_DEINTERLACE_SINGLE</a>
	 * 	<li><a href="#CCD_DSP_DEINTERLACE_SPLIT_PARALLEL">CCD_DSP_DEINTERLACE_SPLIT_PARALLEL</a>
	 * 	<li><a href="#CCD_DSP_DEINTERLACE_SPLIT_SERIAL">CCD_DSP_DEINTERLACE_SPLIT_SERIAL</a>
	 * 	<li><a href="#CCD_DSP_DEINTERLACE_SPLIT_QUAD">CCD_DSP_DEINTERLACE_SPLIT_QUAD</a>
	 * 	</ul>.
	 * @exception CCDLibraryFormatException If the string was not an accepted value an exception is thrown.
	 */
	public int CCDDSPDeinterlaceFromString(String s) throws CCDLibraryFormatException
	{
		if(s.equals("CCD_DSP_DEINTERLACE_SINGLE"))
			return CCD_DSP_DEINTERLACE_SINGLE;
		if(s.equals("CCD_DSP_DEINTERLACE_SPLIT_PARALLEL"))
			return CCD_DSP_DEINTERLACE_SPLIT_PARALLEL;
		if(s.equals("CCD_DSP_DEINTERLACE_SPLIT_SERIAL"))
			return CCD_DSP_DEINTERLACE_SPLIT_SERIAL;
		if(s.equals("CCD_DSP_DEINTERLACE_SPLIT_QUAD"))
			return CCD_DSP_DEINTERLACE_SPLIT_QUAD;
		throw new CCDLibraryFormatException(this.getClass().getName(),"CCDDSPDeinterlaceFromString",s);
	}

// ccd_exposure.h
	/**
	 * Routine to perform an exposure.
	 * @param open_shutter Determines whether the shutter should be opened to do the exposure. The shutter might
	 * 	be left closed to perform calibration images etc.
	 * @param readout_ccd Determines whether the CCD should be read out at the end of the exposure.
	 * @param msecs The number of milliseconds to expose the CCD.
	 * @param filename The filename to save the exposure into.
	 * @exception CCDLibraryNativeException This routine throws a CCDLibraryNativeException if 
	 * CCD_Exposure_Expose  failed.
	 */
	public void CCDExposureExpose(boolean open_shutter,boolean readout_ccd,int msecs,String filename) 
		throws CCDLibraryNativeException
	{
		CCD_Exposure_Expose(open_shutter,readout_ccd,msecs,filename);
	}

	/**
	 * Routine to take a bias frame and save the result to a file.
	 * A bias frame is taken by clearing the ccd array and then immediately reading it out to disk.
	 * This cannot be done with a call to CCDExposureExpose as this routine takes a <b>non-zero</b>
	 * exposure time.
	 * @param filename The filename to save the read out data into.
	 * @exception CCDLibraryNativeException This routine throws a CCDLibraryNativeException if it fails.
	 */
	public void CCDExposureBias(String filename) throws CCDLibraryNativeException
	{
		CCD_Exposure_Bias(filename);
	}

	/**
	 * Routine to readout data on the CCD to a file.
	 * @param filename The filename to save the read out data into.
	 * @exception CCDLibraryNativeException This routine throws a CCDLibraryNativeException if it fails.
	 */
	public void CCDExposureReadOutCCD(String filename) throws CCDLibraryNativeException
	{
		CCD_Exposure_Read_Out_CCD(filename);
	}

	/**
	 * Routine to abort an exposure that is underway. You can see if an exposure is in progress using 
	 * <a href="#CCDDSPGetExposureStatus">CCDDSPGetExposureStatus</a> which should return
	 * <a href="#CCD_DSP_EXPOSURE_STATUS_EXPOSE">CCD_DSP_EXPOSURE_STATUS_EXPOSE</a>. If the return value is 
	 * <a href="#CCD_DSP_EXPOSURE_STATUS_READOUT">CCD_DSP_EXPOSURE_STATUS_READOUT</a> then the CCD is reading out 
	 * and <a href="#CCDExposureAbortReadout">CCDExposureAbortReadout</a> should be called instead.
	 * @see #CCDDSPGetExposureStatus 
	 * @see #CCDExposureExpose 
	 * @see #CCDExposureAbortReadout
	 */
	public void CCDExposureAbort()
	{
		CCD_Exposure_Abort();
	}

	/**
	 * Routine to abort an exposure that is reading out. You can see if an exposure is reading out using 
	 * <a href="#CCDDSPGetExposureStatus">CCDDSPGetExposureStatus</a> which should return
	 * <a href="#CCD_DSP_EXPOSURE_STATUS_READOUT">CCD_DSP_EXPOSURE_STATUS_READOUT</a>. If the return value is 
	 * <a href="#CCD_DSP_EXPOSURE_STATUS_EXPOSE">CCD_DSP_EXPOSURE_STATUS_EXPOSE</a> then the CCD is exposing and
	 * <a href="#CCDExposureAbort">CCDExposureAbort</a> should be called instead.
	 * @see #CCDDSPGetExposureStatus 
	 * @see #CCDExposureExpose 
	 * @see #CCDExposureAbort
	 */
	public void CCDExposureAbortReadout()
	{
		CCD_Exposure_Abort_Readout();
	}

	/**
	 * Returns the current error number from this module of the library. A zero means there is no error.
	 * @return Returns an error number.
	 */
	public int CCDExposureGetErrorNumber()
	{
		return CCD_Exposure_Get_Error_Number();
	}

// ccd_global.h
	/**
	 * Routine that sets up all the parts of CCDLibrary  at the start of it's use. This routine should be
	 * called once at the start of each program. 
	 * @param interface_device The interface device to use to communicate with the SDSU CCD Controller.
	 * 	One of:
	 * <a href="#CCD_INTERFACE_DEVICE_NONE">CCD_INTERFACE_DEVICE_NONE</a>,
	 * <a href="#CCD_INTERFACE_DEVICE_TEXT">CCD_INTERFACE_DEVICE_TEXT</a>,
	 * <a href="#CCD_INTERFACE_DEVICE_PCI">CCD_INTERFACE_DEVICE_PCI</a>.
	 */
	public void CCDInitialise(int interface_device)
	{
		CCD_Global_Initialise(interface_device);
	}

	/**
	 * Error routine. Should be called whenever another library routine has failed. 
	 * Prints out to stderr any error messages outstanding in any of the modules that
	 * make up libccd.
	 * <b>Note</b> you cannot call both CCDError and CCDErrorString to print the error string and 
	 * get a string copy of it, only one of the error routines can be called after libccd has generated an error.
	 * A second call to one of these methods will generate a libccd 'Error not found' error!.
	 */
	public void CCDError()
	{
		CCD_Global_Error();
	}

	/**
	 * Error routine. Should be called whenever another library routine has failed. 
	 * Returns in a string any error messages outstanding in any of the modules that
	 * make up libccd.
	 * <b>Note</b> you cannot call both CCDError and CCDErrorString to print the error string and 
	 * get a string copy of it, only one of the error routines can be called after libccd has generated an error.
	 * A second call to one of these methods will generate a libccd 'Error not found' error!.
	 * @return Returns the error string generated by libccd.
	 */
	public String CCDErrorString()
	{
		return CCD_Global_Error_String();
	}

// ccd_interface.h
	/**
	 * Routine to open the interface selected with <a href="#CCDInitialise">CCDInitialise</a> 
	 * or CCD_Interface_Set_Device 
	 * (a libccd routine, not implemented in CCDLibrary at the moment). 
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if the device could
	 * 	not be opened.
	 * @see #CCDInitialise
	 * @see #CCDInterfaceClose
	 */
	public void CCDInterfaceOpen() throws CCDLibraryNativeException
	{
		CCD_Interface_Open();
	}

	/**
	 * Routine to close the interface selected with <a href="#CCDInitialise">CCDInitialise</a> 
	 * or CCD_Interface_Set_Device
	 * (a libccd routine, not implemented in CCDLibrary at the moment) and opened with 
	 * <a href="#CCDInterfaceOpen">CCDInterfaceOpen</a>.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if the device could
	 * 	not be closed.
	 * @see #CCDInitialise
	 * @see #CCDInterfaceOpen
	 */
	public void CCDInterfaceClose() throws CCDLibraryNativeException
	{
		CCD_Interface_Close();
	}

	/**
	 * Routine to get an interface device number from a string representation of the device. Used for
	 * getting a device number from a string in a properties file.
	 * @param s A string representing a device number, one of:
	 * 	<ul>
	 * 	<li>CCD_INTERFACE_DEVICE_NONE,
	 * 	<li>CCD_INTERFACE_DEVICE_TEXT,
	 * 	<li>CCD_INTERFACE_DEVICE_PCI.
	 * 	</ul>.
	 * @return An interface device number, one of:
	 * 	<ul>
	 * 	<li><a href="#CCD_INTERFACE_DEVICE_NONE">CCD_INTERFACE_DEVICE_NONE</a>,
	 * 	<li><a href="#CCD_INTERFACE_DEVICE_TEXT">CCD_INTERFACE_DEVICE_TEXT</a>,
	 * 	<li><a href="#CCD_INTERFACE_DEVICE_PCI">CCD_INTERFACE_DEVICE_PCI</a>.
	 * 	</ul>. 
	 * @exception CCDLibraryFormatException If the string was not an accepted value an exception is thrown.
	 */
	public int CCDInterfaceDeviceFromString(String s) throws CCDLibraryFormatException
	{
		if(s.equals("CCD_INTERFACE_DEVICE_NONE"))
			return CCD_INTERFACE_DEVICE_NONE;
		if(s.equals("CCD_INTERFACE_DEVICE_TEXT"))
			return CCD_INTERFACE_DEVICE_TEXT;
		if(s.equals("CCD_INTERFACE_DEVICE_PCI"))
			return CCD_INTERFACE_DEVICE_PCI;
		throw new CCDLibraryFormatException(this.getClass().getName(),"CCDInterfaceDeviceFromString",s);
	}

// ccd_setup.h
	/**
	 * This routine sets up the SDSU CCD Controller ready to do exposures.
	 * @param setup_flags Flags set to determine which parts of setup this call will do. Passing 
	 * 	<a href="#CCD_SETUP_FLAG_ALL">CCD_SETUP_FLAG_ALL</a> does all parts of setup, or bits can be ORed
	 * 	together from individual flags.
	 * @param timing_load_type Where to load the Timing application DSP code from. Acceptable values are
	 * 	<a href="#CCD_SETUP_LOAD_APPLICATION">CCD_SETUP_LOAD_APPLICATION</a> and
	 *	<a href="#CCD_SETUP_LOAD_FILENAME">CCD_SETUP_LOAD_FILENAME</a>.
	 * @param timing_application_number If timing_load_type is
	 *	<a href="#CCD_SETUP_LOAD_APPLICATION">CCD_SETUP_LOAD_APPLICATION</a> this specifies which 
	 * 	application to load.
	 * @param timing_filename If timing_load_type is
	 *	<a href="#CCD_SETUP_LOAD_FILENAME">CCD_SETUP_LOAD_FILENAME</a> this specifies which file to load from.
	 * @param utility_load_type Where to load the Utility application DSP code from. Acceptable values are
	 * 	<a href="#CCD_SETUP_LOAD_APPLICATION">CCD_SETUP_LOAD_APPLICATION</a> and
	 *	<a href="#CCD_SETUP_LOAD_FILENAME">CCD_SETUP_LOAD_FILENAME</a>.
	 * @param utility_application_number If utility_load_type is
	 *	<a href="#CCD_SETUP_LOAD_APPLICATION">CCD_SETUP_LOAD_APPLICATION</a> this specifies which 
	 * 	application to load.
	 * @param utility_filename If utility_load_type is
	 *	<a href="#CCD_SETUP_LOAD_FILENAME">CCD_SETUP_LOAD_FILENAME</a> this specifies which file to load from.
	 * @param target_temperature Specifies the target temperature the CCD is meant to run at. 
	 * @param gain Specifies the gain to use for the CCD video processors. Acceptable values are:
	 * 	<a href="#CCD_DSP_GAIN_ONE">CCD_DSP_GAIN_ONE</a>, 
	 * 	<a href="#CCD_DSP_GAIN_TWO">CCD_DSP_GAIN_TWO</a>,
	 * 	<a href="#CCD_DSP_GAIN_FOUR">CCD_DSP_GAIN_FOUR</a> and 
	 * 	<a href="#CCD_DSP_GAIN_NINE">CCD_DSP_GAIN_NINE</a>.
	 * @param gain_speed Set to true for fast integrator speed, false for slow integrator speed.
	 * @param idle If true puts CCD clocks in readout sequence, but not transferring any data, whenever a
	 * 	command is not executing.
	 * @param ncols The number of columns in the image.
	 * @param nrows The number of rows in the image.
	 * @param nsbin The amount of binning applied to pixels in columns.This parameter will change internally
	 *	ncols.
	 * @param npbin The amount of binning applied to pixels in rows.This parameter will change internally
	 *	nrows.
	 * @param deinterlace_setting The algorithm to use for deinterlacing the resulting data. The data needs to be
	 * 	deinterlaced if the CCD is read out from multiple readouts.One of:
	 * 	<a href="#CCD_DSP_DEINTERLACE_SINGLE">CCD_DSP_DEINTERLACE_SINGLE</a>,
	 * 	<a href="#CCD_DSP_DEINTERLACE_SPLIT_PARALLEL">CCD_DSP_DEINTERLACE_SPLIT_PARALLEL</a>,
	 * 	<a href="#CCD_DSP_DEINTERLACE_SPLIT_SERIAL">CCD_DSP_DEINTERLACE_SPLIT_SERIAL</a>,
	 * 	<a href="#CCD_DSP_DEINTERLACE_SPLIT_QUAD">CCD_DSP_DEINTERLACE_SPLIT_QUAD</a>.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if the setup failed.
	 */
	public void CCDSetupSetupCCD(int setup_flags,
		int timing_load_type,int timing_application_number,String timing_filename,
		int utility_load_type,int utility_application_number,String utility_filename,
		double target_temperature,int gain,boolean gain_speed,boolean idle,
		int ncols,int nrows,int nsbin,int npbin,int deinterlace_setting) throws CCDLibraryNativeException
	{
		CCD_Setup_Setup_CCD(setup_flags,timing_load_type,timing_application_number,timing_filename,
				utility_load_type,utility_application_number,utility_filename,
				target_temperature,gain,gain_speed,idle,
				ncols,nrows,nsbin,npbin,deinterlace_setting);
	}

	/**
	 * Routine to abort a setup that is underway.
	 * @see #CCDSetupSetupCCD
	 */
	public void CCDSetupAbort()
	{
		CCD_Setup_Abort();
	}

	/**
	 * Routine to get the number of columns on the CCD chip last passed into CCDSetupSetupCCD. This value
	 * is got from the stored setup data, rather than querying the camera directly.
	 * @return Returns an integer representing the number of columns.
	 * @see #CCDSetupSetupCCD
	 */
	public int CCDSetupGetNCols()
	{
		return CCD_Setup_Get_NCols();
	}

	/**
	 * Routine to get the number of rows on the CCD chip last passed into CCDSetupSetupCCD. This value
	 * is got from the stored setup data, rather than querying the camera directly.
	 * @return Returns an integer representing the number of rows.
	 * @see #CCDSetupSetupCCD
	 */
	public int CCDSetupGetNRows()
	{
		return CCD_Setup_Get_NRows();
	}

	/**
	 * Routine to detect whether a setup operation is underway.
	 * @return Returns true is a setup is in progress otherwise false.
	 * @see #CCDSetupSetupCCD
	 */
	public boolean CCDSetupGetSetupInProgress()
	{
		return (boolean)CCD_Setup_Get_Setup_In_Progress();
	}

	/**
	 * Returns the current error number from this module of the library. A zero means there is no error.
	 * @return Returns an error number.
	 */
	public int CCDSetupGetErrorNumber()
	{
		return CCD_Setup_Get_Error_Number();
	}

	/**
	 * Routine to parse a setup load type string and return a setup load type to pass into
	 * <a href="#CCDSetupSetupCCD">CCDSetupSetupCCD</a>. 
	 * @param s The string to parse.
	 * @return The load type, either <a href="#CCD_SETUP_LOAD_APPLICATION">CCD_SETUP_LOAD_APPLICATION</a> or
	 * 	<a href="#CCD_SETUP_LOAD_FILENAME">CCD_SETUP_LOAD_FILENAME</a>.
	 * @exception CCDLibraryFormatException If the string was not an accepted value an exception is thrown.
	 */
	public int CCDSetupLoadTypeFromString(String s) throws CCDLibraryFormatException
	{
		if(s.equals("CCD_SETUP_LOAD_APPLICATION"))
			return CCD_SETUP_LOAD_APPLICATION;
		if(s.equals("CCD_SETUP_LOAD_FILENAME"))
			return CCD_SETUP_LOAD_FILENAME;
		throw new CCDLibraryFormatException(this.getClass().getName(),"CCDSetupLoadTypeFromString",s);
	}

// ccd_temperature.h
	/**
	 * Routine to get the current CCD temperature.
	 * @param temperature A double wrapper in which the current temperature is returned.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 * @see CCDLibraryDouble
	 */
	public void CCDTemperatureGet(CCDLibraryDouble temperature) throws CCDLibraryNativeException
	{
		CCD_Temperature_Get(temperature);
	}

	/**
	 * Routine to set the temperature of the CCD.
	 * @param target_temperature The temperature in degrees centigrade required for the CCD.
	 * @exception CCDLibraryNativeException This method throws a CCDLibraryNativeException if it failed.
	 */
	public void CCDTemperatureSet(double target_temperature) throws CCDLibraryNativeException
	{
		CCD_Temperature_Set(target_temperature);
	}

	/**
	 * Returns the current error number from this module of the library. A zero means there is no error.
	 * @return Returns an error number.
	 */
	public int CCDTemperatureGetErrorNumber()
	{
		return CCD_Temperature_Get_Error_Number();
	}

// ccd_text.h
	/**
	 * Routine thats set the amount of information displayed when the text interface device
	 * is enabled.
	 * @param level An integer describing how much information is displayed. Can be one of:
	 * <a href="#CCD_TEXT_PRINT_LEVEL_COMMANDS">CCD_TEXT_PRINT_LEVEL_COMMANDS</a>,
	 * <a href="#CCD_TEXT_PRINT_LEVEL_REPLIES">CCD_TEXT_PRINT_LEVEL_REPLIES</a>,
	 * <a href="#CCD_TEXT_PRINT_LEVEL_VALUES">CCD_TEXT_PRINT_LEVEL_VALUES</a> and
	 * <a href="#CCD_TEXT_PRINT_LEVEL_ALL">CCD_TEXT_PRINT_LEVEL_ALL</a>.
	 */
	public void CCDTextSetPrintLevel(int level)
	{
		CCD_Text_Set_Print_Level(level);
	}

	/**
	 * Routine to parse a string version of a text print level and to return
	 * the numeric value of that level, suitable for passing into 
	 * <a href="#CCDTextSetPrintLevel">CCDTextSetPrintLevel</a>.
	 * @param s The string to parse.
	 * @return The printlevel number, one of:
	 * 	<ul>
	 * 	<a href="#CCD_TEXT_PRINT_LEVEL_COMMANDS">CCD_TEXT_PRINT_LEVEL_COMMANDS</a>,
	 * 	<a href="#CCD_TEXT_PRINT_LEVEL_REPLIES">CCD_TEXT_PRINT_LEVEL_REPLIES</a>,
	 * 	<a href="#CCD_TEXT_PRINT_LEVEL_VALUES">CCD_TEXT_PRINT_LEVEL_VALUES</a> and
	 * 	<a href="#CCD_TEXT_PRINT_LEVEL_ALL">CCD_TEXT_PRINT_LEVEL_ALL</a>.
	 * 	</ul>.
	 * @exception CCDLibraryFormatException If the string was not an accepted value an exception is thrown.
	 */
	public int CCDTextPrintLevelFromString(String s) throws CCDLibraryFormatException
	{
		if(s.equals("CCD_TEXT_PRINT_LEVEL_COMMANDS"))
			return CCD_TEXT_PRINT_LEVEL_COMMANDS;
		if(s.equals("CCD_TEXT_PRINT_LEVEL_REPLIES"))
			return CCD_TEXT_PRINT_LEVEL_REPLIES;
		if(s.equals("CCD_TEXT_PRINT_LEVEL_VALUES"))
			return CCD_TEXT_PRINT_LEVEL_VALUES;
		if(s.equals("CCD_TEXT_PRINT_LEVEL_ALL"))
			return CCD_TEXT_PRINT_LEVEL_ALL;

		throw new CCDLibraryFormatException(this.getClass().getName(),"CCDTextPrintLevelFromString",s);
	}
}
 
//
// $Log: not supported by cvs2svn $
// Revision 0.12  1999/09/20 14:40:08  cjm
// Changed due to libccd native routines throwing CCDLibraryNativeException when errors occur.
//
// Revision 0.11  1999/09/13 13:54:54  cjm
// Class is now public.
//
// Revision 0.10  1999/09/10 15:33:42  cjm
// Changed package to ngat.ccd.
//
// Revision 0.9  1999/09/09 10:13:07  cjm
// Added CCDExposureBias call to library.
//
// Revision 0.8  1999/09/08 10:52:40  cjm
// Trying to fix file permissions of these files.
//
// Revision 0.7  1999/07/09 12:17:09  dev
// JNI Routines to get the number of rows/columns the CCD was setup to
// use.
//
// Revision 0.6  1999/06/07 16:56:41  dev
// String to Number parse routines
//
// Revision 0.5  1999/05/28 09:54:18  dev
// "Name
//
// Revision 0.4  1999/03/25 14:02:01  dev
// Backup
//
// Revision 0.3  1999/03/08 12:20:40  dev
// Backup
//
// Revision 0.2  1999/02/23 11:08:00  dev
// backup/transfer to ltccd1.
//
// Revision 0.1  1999/01/21 15:44:00  dev
// initial revision
//
//