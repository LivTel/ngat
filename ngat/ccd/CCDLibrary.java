// CCDLibrary.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibrary.java,v 0.1 1999-01-21 15:44:00 dev Exp $
class CCDLibrary
{
	public final static String RCSID = new String("$Id: CCDLibrary.java,v 0.1 1999-01-21 15:44:00 dev Exp $");
// ccd_interface.h
	public final static int INTERFACE_DEVICE_NONE = 	0;
	public final static int INTERFACE_DEVICE_TEXT =		1;
	public final static int INTERFACE_DEVICE_SBUS = 	2;
	public final static int INTERFACE_DEVICE_PCI = 		3;
// ccd_setup.h 
	public final static int SETUP_FLAG_RESET_TIMING = 	(1<<0);
	public final static int SETUP_FLAG_HARDWARE_TEST = 	(1<<1);
	public final static int SETUP_FLAG_TIMING_BOARD = 	(1<<2);
	public final static int SETUP_FLAG_UTILITY_BOARD = 	(1<<3);
	public final static int SETUP_FLAG_POWER_ON = 		(1<<4);
	public final static int SETUP_FLAG_TARGET_CCD_TEMP = 	(1<<5);
	public final static int SETUP_FLAG_GAIN = 		(1<<6);
	public final static int SETUP_FLAG_IDLE = 		(1<<7);
	public final static int SETUP_FLAG_DIMENSIONS = 	(1<<8);
	public final static int SETUP_FLAG_DEINTERLACE = 	(1<<9);
	public final static int SETUP_FLAG_ALL = (SETUP_FLAG_RESET_TIMING|SETUP_FLAG_HARDWARE_TEST|
		SETUP_FLAG_TIMING_BOARD|SETUP_FLAG_UTILITY_BOARD|SETUP_FLAG_POWER_ON|SETUP_FLAG_TARGET_CCD_TEMP|
		SETUP_FLAG_GAIN|SETUP_FLAG_IDLE|SETUP_FLAG_DIMENSIONS|SETUP_FLAG_DEINTERLACE);
	public final static int SETUP_LOAD_APPLICATION = 	0;
	public final static int SETUP_LOAD_FILENAME = 		1;
	public final static int SETUP_GAIN_ONE = 		1;
	public final static int SETUP_GAIN_TWO = 		2;
	public final static int SETUP_GAIN_FOUR = 		4;
	public final static int SETUP_GAIN_NINE = 		9;
// ccd_text.h
	public final static int TEXT_PRINT_LEVEL_COMMANDS = 	0;
	public final static int TEXT_PRINT_LEVEL_REPLIES = 	1;
	public final static int TEXT_PRINT_LEVEL_HEADERS = 	2;
	public final static int TEXT_PRINT_LEVEL_BUFFERS = 	5;
	public final static int TEXT_PRINT_LEVEL_ALL = 		9;

// ccd_exposure.h
	private native boolean CCD_Exposure_Expose(boolean open_shutter,boolean readout_ccd,int ncols,int nrows,
		int msecs,int algorithm,byte[] data);
	private native void CCD_Exposure_Abort();
	private native void CCD_Exposure_Abort_Readout();
// ccd_global.h
	private native void CCD_Setup(int interface_device);
	private native void CCD_Error();
// ccd_interface.h
	private native boolean CCD_Interface_Open();
	private native boolean CCD_Interface_Close();
// ccd_setup.h
	private native boolean CCD_Setup_Setup(int setup_flags,
		int timing_load_type,int timing_application_number,String timing_filename,
		int utility_load_type,int utility_application_number,String utility_filename,
		double target_temperature,int gain,int gain_speed,boolean idle,
		int nrows,int ncols,int nsbin,int npbin,int deinterlace_setting);
	private native void CCD_Setup_Abort();
// ccd_temperature.h
	private native boolean CCD_Temperature_Get(CCDLibraryDouble temperature);
	private native boolean CCD_Temperature_Set(double target_temperature);
// ccd_text.h
	private native void CCD_Text_Set_Print_Level(int level);

	static
	{
		System.loadLibrary("ccd");
	}

// ccd_exposure.h
	public byte []CCDExposureExpose(boolean open_shutter,boolean readout_ccd,int ncols,int nrows,
		int msecs,int algorithm)
	{
		byte[] data = null;
		boolean retval = false;

		data = new byte[ncols*nrows*2];
		retval = CCD_Exposure_Expose(open_shutter,readout_ccd,ncols,nrows,
			msecs,algorithm,data);
		if(retval)
			return data;
		else
			return null;
	}

	public void CCDExposureAbort()
	{
		CCD_Exposure_Abort();
	}

	public void CCD_ExposureAbortReadout()
	{
		CCD_Exposure_Abort_Readout();
	}

// ccd_global.h
	public void CCDSetup(int interface_device)
	{
		CCD_Setup(interface_device);
	}

	public void CCDError()
	{
		CCD_Error();
	}

// ccd_interface.h
	public boolean CCDInterfaceOpen()
	{
		return (boolean)CCD_Interface_Open();
	}

	public boolean CCDInterfaceClose()
	{
		return (boolean)CCD_Interface_Close();
	}

// ccd_setup.h
	public boolean CCDSetupSetup(int setup_flags,
		int timing_load_type,int timing_application_number,String timing_filename,
		int utility_load_type,int utility_application_number,String utility_filename,
		double target_temperature,int gain,int gain_speed,boolean idle,
		int nrows,int ncols,int nsbin,int npbin,int deinterlace_setting)
	{
		return (boolean)CCD_Setup_Setup(setup_flags,
				timing_load_type,timing_application_number,timing_filename,
				utility_load_type,utility_application_number,utility_filename,
				target_temperature,gain,gain_speed,idle,
				nrows,ncols,nsbin,npbin,deinterlace_setting);
	}

	public void CCDSetupAbort()
	{
		CCD_Setup_Abort();
	}

// ccd_temperature.h
	public boolean CCDTemperatureGet(CCDLibraryDouble temperature)
	{
		return (boolean)CCD_Temperature_Get(temperature);
	}

	public boolean CCDTemperatureSet(double target_temperature)
	{
		return (boolean)CCD_Temperature_Set(target_temperature);
	}

// ccd_text.h
	public void CCDTextSetPrintLevel(int level)
	{
		CCD_Text_Set_Print_Level(level);
	}

}

//
// $Log: not supported by cvs2svn $
//