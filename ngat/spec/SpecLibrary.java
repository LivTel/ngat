// SpecLibrary.java -*- mode: Fundamental;-*-
// libspec Java wrapper.
// $Header: /space/home/eng/cjm/cvs/ngat/spec/SpecLibrary.java,v 0.5 2001-02-15 15:34:21 cjm Exp $
package ngat.spec;

/**
 * This class holds the JNI interface to the general spectrograph access routines provided by libspec.
 * @author Chris Mottram
 * @version $Revision: 0.5 $
 */
public class SpecLibrary
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SpecLibrary.java,v 0.5 2001-02-15 15:34:21 cjm Exp $");
// general constants
	/**
	 * Bit definition to pass into open to tell the routine to open communication with the IO card hardware. 
	 * This value should be kept in line with the C hash definition of the same name in spec.h.
	 * @see #open
	 */
	public final static int SPEC_OPEN_DAIO_BIT			= (1<<0);
	/**
	 * Bit definition to pass into open to tell the routine to open communication with the 
	 * Apogee CCD camera hardware. 
	 * This value should be kept in line with the C hash definition of the same name in spec.h.
	 * @see #open
	 */
	public final static int SPEC_OPEN_APOGEE_BIT			= (1<<1);
	/**
	 * Bit definition to pass into open to tell the routine to open communication with all the hardware. 
	 * This value should be kept in line with the C hash definition of the same name in spec.h.
	 * @see #SPEC_OPEN_DAIO_BIT
	 * @see #SPEC_OPEN_APOGEE_BIT
	 * @see #open
	 */
	public final static int SPEC_OPEN_ALL_BIT			= (SPEC_OPEN_DAIO_BIT|SPEC_OPEN_APOGEE_BIT);
// filter constants
	/**
	 * Number of position a filter slide with positions has. Position arguments can take
	 * an integer between zero and one less than this number.
	 * This value should be kept the same as that in libspec, spec_filter.h.
	 * @see #filterConfigurePosition
	 * @see #filterGetPositionADCount
	 * @see #filterSetPosition
	 * @see #filterGetPosition
	 */
	public final static int SPEC_FILTER_COUNT			= 4;
// camera exposure constants
	/**
	 * Value returned from cameraExposeGetStatus, specifying the status of a camera exposure.
	 * SPEC_EXPOSURE_STATUS_NONE means the library is not currently performing an exposure. 
	 * This value should be kept in line with the C enum SPEC_EXPOSURE_STATUS in spec_exposure.h.
	 * @see #cameraExposeGetStatus
	 */
	public final static int SPEC_EXPOSURE_STATUS_NONE		= 0;
	/**
	 * Value returned from cameraExposeGetStatus, specifying the status of a camera exposure.
	 * SPEC_EXPOSURE_STATUS_WAIT_START means the library is waiting for the correct time to 
	 * start the exposure to occur. 
	 * This value should be kept in line with the C enum SPEC_EXPOSURE_STATUS in spec_exposure.h.
	 * @see #cameraExposeGetStatus
	 */
	public final static int SPEC_EXPOSURE_STATUS_WAIT_START		= 1;
	/**
	 * Value returned from cameraExposeGetStatus, specifying the status of a camera exposure.
	 * SPEC_EXPOSURE_STATUS_EXPOSE means the library is currently performing an exposure. 
	 * This value should be kept in line with the C enum SPEC_EXPOSURE_STATUS in spec_exposure.h.
	 * @see #cameraExposeGetStatus
	 */
	public final static int SPEC_EXPOSURE_STATUS_EXPOSE		= 2;
	/**
	 * Value returned from cameraExposeGetStatus, specifying the status of a camera exposure.
	 * SPEC_EXPOSURE_STATUS_READOUT means the library is currently reading out data from the ccd. 
	 * This value should be kept in line with the C enum SPEC_EXPOSURE_STATUS in spec_exposure.h.
	 * @see #cameraExposeGetStatus
	 */
	public final static int SPEC_EXPOSURE_STATUS_READOUT		= 3;
	/**
	 * Value returned from cameraExposeGetStatus, specifying the status of a camera exposure.
	 * SPEC_EXPOSURE_STATUS_SAVE means the library is currently saving the data to disc.
	 * This value should be kept in line with the C enum SPEC_EXPOSURE_STATUS in spec_exposure.h.
	 * @see #cameraExposeGetStatus
	 */
	public final static int SPEC_EXPOSURE_STATUS_SAVE		= 4;
// daio constants
	/**
	 * Enumerated constant, used to select which Digital Analogue IO card to
	 * use when communicating with the filter/arc-lamp mechanisms on the spectrograph.
	 * These constants must have the same values as those defined in daio_interface.h, 
	 * enum DAIO_INTERFACE_DEVICE.
	 * @see #selectIODevice
	 */
	public final static int DAIO_INTERFACE_DEVICE_NONE 		= 0;
	public final static int DAIO_INTERFACE_DEVICE_TEXT 		= 1;
	public final static int DAIO_INTERFACE_DEVICE_DAS08JR 		= 2;
	public final static int DAIO_INTERFACE_DEVICE_PC104_DAS08 	= 3;

// general native methods
	/**
	 * Native wrapper to libspec SPEC_Open routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Open(int open_bits) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Close routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Close() throws SpecNativeException;
// arc lamp native methods
	/**
	 * Native wrapper to libspec SPEC_Arc_Lamp_Set_Lamp routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Arc_Lamp_Set_Lamp(int lamp_number) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Arc_Lamp_Set routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Arc_Lamp_Set(boolean on) throws SpecNativeException;
// filter native methods
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Position routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Position(int position,int ad_count) 
		throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Auto_Configure_Position routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Auto_Configure_Position() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position_AD_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position_AD_Count(int position) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Set_Position routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Set_Position(int position) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Wavelength_Range routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Wavelength_Range(double min_wavelength,
		double max_wavelength) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_AD_Range routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_AD_Range(int min_ad_count,int max_ad_count) 
		throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Auto_Configure_AD_Range routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Auto_Configure_AD_Range() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Wavelength_Range routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native SpecDoubleRange SPEC_Filter_Get_Wavelength_Range() throws SpecNativeException; 
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Range routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native SpecIntRange SPEC_Filter_Get_AD_Range() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Set_Wavelength routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Set_Wavelength(double wavelength) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Wavelength routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native double SPEC_Filter_Get_Wavelength() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_AD_Count_Error routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_AD_Count_Error(int ad_count) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Switch_AD_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Switch_AD_Count(int ad_count)  throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Position_Timeout_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Position_Timeout_Count(int timeout_number) 
		throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Count_Error routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_AD_Count_Error() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Switch_AD_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Switch_AD_Count() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position_Timeout_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position_Timeout_Count() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Count routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_AD_Count() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Abort routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Abort() throws SpecNativeException;
// camera setup
	/**
	 * Native wrapper to libspec SPEC_Setup_Startup routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Setup_Startup(boolean gain,boolean cable_length)  throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Setup_Shutdown routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Setup_Shutdown() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Setup_Dimensions routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Setup_Dimensions(int ncols,int nrows,int hbin,int vbin)  
		throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Setup_Abort routine.
	 */
	private static native void SPEC_Setup_Abort();
	/**
	 * Native wrapper to libspec SPEC_Setup_Get_NCols routine.
	 */
	private static native int SPEC_Setup_Get_NCols();
	/**
	 * Native wrapper to libspec SPEC_Setup_Get_NRows routine.
	 */
	private static native int SPEC_Setup_Get_NRows();
	/**
	 * Native wrapper to libspec SPEC_Setup_Get_HBin routine.
	 */
	private static native int SPEC_Setup_Get_HBin();
	/**
	 * Native wrapper to libspec SPEC_Setup_Get_VBin routine.
	 */
	private static native int SPEC_Setup_Get_VBin();
// camera exposure
	/**
	 * Native wrapper to libspec SPEC_Exposure_Expose routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Exposure_Expose(boolean open_shutter,
		long start_time,int exposure_time,String filename) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Exposure_Bias routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Exposure_Bias(String filename) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Exposure_Abort routine.
	 */
	private static native void SPEC_Exposure_Abort();
	/**
	 * Native wrapper to libspec SPEC_Exposure_Get_Status routine.
	 */
	private static native int SPEC_Exposure_Get_Status();
	/**
	 * Native wrapper to libspec SPEC_Exposure_Get_Length routine.
	 */
	private static native int SPEC_Exposure_Get_Length();
	/**
	 * Native wrapper to libspec SPEC_Exposure_Get_Start_Time routine.
	 */
	private static native long SPEC_Exposure_Get_Start_Time();
	/**
	 * Native wrapper to libspec SPEC_Exposure_Set_Start_Offset_Time routine.
	 */
	private static native void SPEC_Exposure_Set_Start_Offset_Time(int time);
	/**
	 * Native wrapper to libspec SPEC_Exposure_Get_Start_Offset_Time routine.
	 */
	private static native int SPEC_Exposure_Get_Start_Offset_Time();
// camera temperature control
	/**
	 * Native wrapper to libspec SPEC_Temperature_Ambient routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Temperature_Ambient() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Temperature_Set routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Temperature_Set(double target_temperature) throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Temperature_Off routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Temperature_Off() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Temperature_Get routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native double SPEC_Temperature_Get() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Temperature_Regulate routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Temperature_Regulate() throws SpecNativeException;
	/**
	 * Native wrapper to libspec SPEC_Temperature_Get_Status routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native String SPEC_Temperature_Get_Status() throws SpecNativeException;
// daio specific native methods
	/**
	 * Native wrapper to libspec SPEC_DAIO_Select_Device routine.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_DAIO_Select_Device(int device) throws SpecNativeException;

// static code block
	/**
	 * Static code to load <b>libspec</b>, the shared C library that implements an interface to the
	 * SpecLibrary hardware.
	 */
	static
	{
		System.loadLibrary("spec");
	}

// general methods
	/**
	 * Method to open a connection to the hardware.
	 * @param openBits A bitfield, specifying which parts of the Spectrograph hardware to open.
	 * 	SPEC_OPEN_ALL_BIT opens all the hardware (CCD camera and IO card).
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Open
	 * @see #SPEC_OPEN_ALL_BIT
	 */
	public static void open(int openBits) throws SpecNativeException
	{
		SPEC_Open(openBits);
	}

	/**
	 * Method to close the opened connections to the hardware.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Close
	 */
	public static void close() throws SpecNativeException
	{
		SPEC_Close();
	}
// arc lamp
	/**
	 * Routine to determine which lamp to switch on and off. 
	 * @param lampNumber Which lamp to switch on and off. 
	 * 	There are two lamps, the value passed in here should be either one or zero. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Arc_Lamp_Set_Lamp
	 */
	public static void arcLampSetLamp(int lampNumber) throws SpecNativeException
	{
		SPEC_Arc_Lamp_Set_Lamp(lampNumber);
	}

	/**
	 * Routine to switch the lamp on or off. The lamp should be selected with arcLampSetLamp
	 * @param on This parameter should be set to TRUE to switch the lamp on, 
	 * 	and FALSE to switch the lamp off. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Arc_Lamp_Set
	 */
	public static void arcLampSet(boolean on) throws SpecNativeException
	{
		SPEC_Arc_Lamp_Set(on);
	}

// filter
	/**
	 * 
	 * Routine to change the position along the slide this module thinks is the correct position to stop. 
	 * Each filter position is denoted as being selected when a specific A/D count (between min and max) 
	 * is returned from the potentiometer analogue inputs. 
	 * @param position Which filter slide position to change. 
	 * 	An integer between 0 and the number of filters minus one. 
	 * @param ad_count The A/D count, which when returned from the analogue inputs, 
	 * 	indicates the filter slide has achieved the required position. 
	 * 	An int in the range 0..(1<<Analogue_Bit_Resolution).
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Position
	 */
	public static void filterConfigurePosition(int position,int adCount) throws SpecNativeException
	{
		SPEC_Filter_Configure_Position(position,adCount); 
	}

	/**
	 * Routine to automatically determine the filter slide A/D count positions.
	 * This is done by driving the filter slide between the two limits, noting at what position A/D counts
	 * the filter indexer is set.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Auto_Configure_Position
	 */
	public static void filterAutoConfigurePosition() throws SpecNativeException
	{
		SPEC_Filter_Auto_Configure_Position();
	}

	/**
	 * Routine to get the A/D count for the specified filter slide position. 
	 * @param position Which filter slide position to get the A/D count for. 
	 * 	An integer between 0 and the number of filters minus one. 
	 * @return An A/D count is returned, which when returned from the position potentiometer, 
	 * 	indicates the filter slide has achieved the required position.
 	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position_AD_Count
	 */
	public static int filterGetPositionADCount(int position) throws SpecNativeException
	{
		return SPEC_Filter_Get_Position_AD_Count(position);
	}

	/**
	 * Routine to move the filter slide until it has achieved the required position. 
	 * This routine is only used for the MES, where there are set positions to be set.
	 * The Nu-View should use filterSetWavelength.
 	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @param position The position number, between zero and the number of filters minus one, 
	 * 	that the slide should be moved to. 
	 * @see #SPEC_Filter_Set_Position
	 * @see #filterSetWavelength
	 */
	public static void filterSetPosition(int position) throws SpecNativeException
	{
		SPEC_Filter_Set_Position(position);
	}

	/**
	 * This routine gets the current position the filter slide is at. 
	 * The filter indexer is checked to ensure the filter slide is in position, if it in between positions -1
	 * is returned.
	 * @return This routine returns the filter position between 0 and the number of position minus one,
	 * 	or -1 if the filter slide is between positions.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position
	 */
	public static int filterGetPosition() throws SpecNativeException
	{
		return SPEC_Filter_Get_Position();
	}

	/**
	 * Routine to specify the minimum wavelength of a continuously variable filter slide at the minimum limit, 
	 * and the maximum wavelength of the filter slide at the maximum limit. 
	 * The variables this routine sets are only used for continuously varaible filter slides (Nu-View), 
	 * not position filter slides (MES). 
	 * @param minWavelength A double representing the minimum wavelength, in Angstroms. 
	 * @param maxWavelength A double representing the maximum wavelength, in Angstroms. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Wavelength_Range
	 */
	public static void filterConfigureWavelengthRange(double minWavelength,
		double maxWavelength) throws SpecNativeException
	{
		SPEC_Filter_Configure_Wavelength_Range(minWavelength,maxWavelength);
	}

	/**
	 * Routine to specify the minimum A/D count of a filter slide at the minimum limit, 
	 * and the maximum A/D count of the filter slide at the maximum limit. 
	 * The variables this routine sets are only used for continuously varaible filter slides (Nu-View), 
	 * not position filter slides (MES). 
	 * @param minADCount An integer representing the minimum A/D count. 
	 * @param maxADCount An integer representing the maximum A/D count. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_AD_Range
	 */
	public static void filterConfigureADRange(int minADCount,int maxADCount) throws SpecNativeException
	{
		SPEC_Filter_Configure_AD_Range(minADCount,maxADCount);
	}

	/**
	 * Try to automaticaly configure the A/D range a continuous variable filter slide operates over. 
	 * This is done by driving the filter slide into each limit in turn, 
	 * and reading the A/D count at these locations. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Auto_Configure_AD_Range
	 */
	public static void filterAutoConfigureADRange() throws SpecNativeException
	{
		SPEC_Filter_Auto_Configure_AD_Range();
	}

	/**
	 * Routine to get current settings of the minimum and maximum wavelength's of a 
	 * continuously variable filter slide at the limits. 
	 * The variables this routine gets are only used for continuously varaible filter slides (Nu-View). 
	 * @return An allocated instance of SpecDoubleRange is returned, with the minimum and maximum wavelength's
	 * 	set.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Wavelength_Range
	 */
	public static SpecDoubleRange filterGetWavelengthRange() throws SpecNativeException
	{
		return SPEC_Filter_Get_Wavelength_Range();
	}

	/**
	 * Routine to get the minimum A/D count of a filter slide at the minimum limit, 
	 * and the maximum A/D count of the filter slide at the maximum limit. 
	 * The variables this routine gets are only used for continuously varaible filter slides (Nu-View). 
	 * @return An allocated instance of SpecIntRange is returned, with the A/D count range contained
	 * 	within it.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Range
	 */
	public static SpecIntRange filterGetADRange() throws SpecNativeException
	{
		return SPEC_Filter_Get_AD_Range();
	}

	/**
	 * Routine to move the filter slide until it has achieved the required wavelength. 
	 * This routine is only used for the Nu-View, where there is a continous filter slide with no positions. 
	 * The MES should use filterSetPosition. 
	 * @param wavelength The wavelength to drive to.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Set_Wavelength
	 * @see #filterSetPosition
	 */
	public static void filterSetWavelength(double wavelength) throws SpecNativeException
	{
		SPEC_Filter_Set_Wavelength(wavelength);
	}

	/**
	 * Routine to get the current wavelength a continuously variable filter slide is currently at. 
	 * This routine should only be used with continuously variable filter slides (Nu-View). 
	 * @return The current wavelength the filter slide is driven to.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Wavelength
	 */
	public static double filterGetWavelength() throws SpecNativeException
	{
		return SPEC_Filter_Get_Wavelength();
	}

	/**
	 * This routine sets the A/D Count Error. 
	 * This is the amount of error allowed in the filter A/D position count, 
	 * when trying to decide whether the filter slide is at the correct location. 
	 * Due to analogue voltage digitisation effects, time taken to digitise 
	 * (15/25 usec for the cards we are using), and software rounding errors, 
	 * it may be impossible to get the precise A/D count requested. 
	 * <b>Please choose this value very carefully</b>. 
	 * Too small a value and the filter positioning will keep failing because the filter is driven past the 
	 * range of positions before the value is digitised. 
	 * Too large a value and filter positioning becomes innaccurate. 
	 * @param adCount The A/D Count Error.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_AD_Count_Error
	 */
	public static void filterConfigureADCountError(int adCount) throws SpecNativeException
	{
		SPEC_Filter_Configure_AD_Count_Error(adCount);
	}

	/**
	 * The biggest A/D count on an analogue input that means a switch is off. 
	 * An A/D count less than this, i.e. near to zero means the switch has been tripped.
	 * This value is used for both the limit switches and index switches. 
	 * It should not be too big, otherwise switches will appear to have been tripped when they are not set.
	 * @param adCount An A/D count, below which a switch is meant to be tripped.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Switch_AD_Count
	 */
	public static void filterConfigureSwitchADCount(int adCount) throws SpecNativeException
	{
		SPEC_Filter_Configure_Switch_AD_Count(adCount);
	}

	/**
	 * After switching on the motor to move the slide, 
	 * we enter a loop monitoring the slide position until the requied A/D count is reached. 
	 * The motor may fail or the potentiometer may slip. 
	 * We can detect this by timing out if the voltage keeps returning the same value. 
	 * This number is the number of times through the loop that the A/D count is the same value, 
	 * before we time out. 
	 * @param timeoutNumber The number of times round a movement loop returning the same position A/D count is OK.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Position_Timeout_Count
	 */
	public static void filterConfigurePositionTimeoutCount(int timeoutNumber) throws SpecNativeException
	{
		SPEC_Filter_Configure_Position_Timeout_Count(timeoutNumber);
	}

	/**
	 * Routine to get the current value of the A/D Count Error configuration, 
	 * which determines how close the A/D count has to be to a target A/D count to be at that target position. 
	 * This allows us some potential slop in positions to allows for digitisation times etc.
	 * @return The current value of A/D Count Error.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Count_Error
	 */
	public static int filterGetADCountError() throws SpecNativeException
	{
		return SPEC_Filter_Get_AD_Count_Error();
	}

	/**
	 * Routine to get the current switch A/D count configuration.
	 * A returned A/D count lower than this value means the relevant switch has been tripped. 
	 * @return The current value of Switch A/D count.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Switch_AD_Count
	 */
	public static int filterGetSwitchADCount() throws SpecNativeException
	{
		return SPEC_Filter_Get_Switch_AD_Count();
	}

	/**
	 * Routine to get the current value of the Position Timeout Number. 
	 * When driving the filter to a location, if the position A/D count is returned as the same
	 * number this many times, it is assumed the motor has failed or the position pot is slipping, 
	 * and an error occurs. 
	 * @return The current configuration of the Position Timeout Number.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position_Timeout_Count
	 */
	public static int filterGetPositionTimeoutCount() throws SpecNativeException
	{
		return SPEC_Filter_Get_Position_Timeout_Count();
	}

	/**
	 * Simple routine to get the current A/D count of the potentiometer monitoring the filter slide position. 
	 * @return The A/D count of the potentiometer monitoring the filter slide position.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Count
	 */
	public static int filterGetADCount() throws SpecNativeException
	{
		return SPEC_Filter_Get_AD_Count();
	}

	/**
	 * This routine sets the filter abort, which stops any filter slide movement operation currently underway.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Abort
	 */
	public static void filterAbort() throws SpecNativeException
	{
		SPEC_Filter_Abort();
	}

// camera setup
	/**
	 * This routine starts the spectrograph camera setup.
	 * @param gain The gain setting the camera is to use. This should be TRUE for high gain,
	 * 	FALSE for low gain. This value is only important for dual gain cameras.
	 * @param cableLength The cable length. This turns the cable length bit on or off,
	 * 	and should be set to TRUE or FALSE.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Setup_Startup
	 */
	public static void cameraSetupStartup(boolean gain,boolean cableLength) throws SpecNativeException
	{
		SPEC_Setup_Startup(gain,cableLength);
	}

	/**
	 * This routine shuts down the spectrograph camera.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Setup_Shutdown
	 */
	public static void cameraSetupShutdown() throws SpecNativeException
	{
		SPEC_Setup_Shutdown();
	}

	/**
	 * This method sets the apogee camera dimensions.
	 * @param ncols Total number of image columns.
	 * @param nrows Total number of image rows.
	 * @param hbin The horizontal binning factor.
	 * @param vbin The vertical binning factor.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Setup_Dimensions
	 */
	public static void cameraSetupDimensions(int ncols,int nrows,int hbin, int vbin) throws SpecNativeException
	{
 		SPEC_Setup_Dimensions(ncols,nrows,hbin,vbin);
	}

	/**
	 * Apogee camera setup abort routine.
	 * @see #SPEC_Setup_Abort
	 */
	public static void cameraSetupAbort()
	{
		SPEC_Setup_Abort();
	}

	/**
	 * Method to get the number of columns currently configured for the Apogee camera.
	 * @return The currently configured number of columns.
	 * @see #SPEC_Setup_Get_NCols
	 */
	public static int cameraSetupGetNumberCols()
	{
		return SPEC_Setup_Get_NCols();
	}

	/**
	 * Method to get the number of rows currently configured for the Apogee camera.
	 * @return The currently configured number of rows.
	 * @see #SPEC_Setup_Get_NRows
	 */
	public static int cameraSetupGetNumberRows()
	{
		return SPEC_Setup_Get_NRows();
	}

	/**
	 * Method to get the horizontal(column) binning factor currently configured for the Apogee camera.
	 * @return The currently configured horizontal binning factor.
	 * @see #SPEC_Setup_Get_HBin
	 */
	public static int cameraSetupGetHorizontalBinning()
	{
		return SPEC_Setup_Get_HBin();
	}

	/**
	 * Method to get the vertical(row) binning factor currently configured for the Apogee camera.
	 * @return The currently configured vertical binning factor.
	 * @see #SPEC_Setup_Get_VBin
	 */
	public static int cameraSetupGetVerticalBinning()
	{
		return SPEC_Setup_Get_VBin();
	}

// camera exposure
	/**
	 * This method takes an exposure with the camera.
	 * @param openShutter Whether to open the shutter or not (perform a dark frame). Should be set to
	 * 	true to open the shutter and false to not open the shutter.
	 * @param startTime The start time, in milliseconds since the epoch (1st January 1970) to start the exposure.
	 * 	Passing the value -1 will start the exposure as soon as possible.
	 * @param exposureTime The length of time to open the shutter for in milliseconds.
	 * @param filename The filename to save the exposure into. The FITS headers should have previously been saved
	 * 	in this file.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Exposure_Expose
	 */
	public static void cameraExpose(boolean openShutter,
		long startTime,int exposureTime,String filename) throws SpecNativeException
	{
		SPEC_Exposure_Expose(openShutter,startTime,exposureTime,filename);
	}

	/**
	 * This method takes an exposure with the camera. It is an overloaded method, it calls through
	 * to SPEC_Exposure_Expose with openShutter true (open shutter),
	 * and startTime -1 (start at any time).
	 * @param exposureTime The length of time to open the shutter for in milliseconds.
	 * @param filename The filename to save the exposure into. The FITS headers should have previously been saved
	 * 	in this file.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Exposure_Expose
	 */
	public static void cameraExpose(int exposureTime,String filename) throws SpecNativeException
	{
		SPEC_Exposure_Expose(true,-1,exposureTime,filename);
	}

	/**
	 * This method takes an bias frame with the camera.
	 * @param filename The filename to save the exposure into. The FITS headers should have previously been saved
	 * 	in this file.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Exposure_Bias
	 */
	public static void cameraBias(String filename) throws SpecNativeException
	{
		SPEC_Exposure_Bias(filename);
	}

	/**
	 * This method trys to abort an exposure or bias frame that is underway.
	 * @see #SPEC_Exposure_Abort
	 */
	public static void cameraExposeAbort()
	{
		SPEC_Exposure_Abort();
	}

	/**
	 * This method gets the current status of an exposure. 
	 * @return The status is returned.
	 * @see #SPEC_Exposure_Get_Status
	 * @see #SPEC_Exposure_Get_Status
 	 * @see #SPEC_EXPOSURE_STATUS_NONE
 	 * @see #SPEC_EXPOSURE_STATUS_WAIT_START
 	 * @see #SPEC_EXPOSURE_STATUS_EXPOSE
 	 * @see #SPEC_EXPOSURE_STATUS_READOUT
 	 * @see #SPEC_EXPOSURE_STATUS_SAVE
	 */
	public static int cameraExposeGetStatus()
	{
		return SPEC_Exposure_Get_Status();
	}

	/**
	 * This method returns the last length set for an exposure.
	 * @return The exposure length, in milliseconds.
	 * @see #SPEC_Exposure_Get_Length
	 */
	public static int cameraExposeGetLength()
	{
		return SPEC_Exposure_Get_Length();
	}

	/**
	 * Method to get number of milliseconds since the EPOCH to the exposure start time.
	 * @return The start time, in milliseconds since the EPOCH (January 1st, 1970).
	 * @see #SPEC_Exposure_Get_Start_Time
	 */
	public static long cameraExposeGetStartTime()
	{
		return SPEC_Exposure_Get_Start_Time();
	}

	/**
	 * Method to set the start offset time for an exposure. 
	 * This time is the amount of time before an exposure is required to start to send the command to open
	 * the shutter, to allow for transmission delays etc. 
	 * @param time The new start offset time, in milliseconds.
	 * @see #SPEC_Exposure_Set_Start_Offset_Time
	 */
	public static void cameraExposeSetStartOffsetTime(int time)
	{
		SPEC_Exposure_Set_Start_Offset_Time(time);
	}

	/**
	 * Routine to get the start offset time for an exposure.
	 * @return The current value of the start offset time, in milliseconds.
	 * @see #SPEC_Exposure_Get_Start_Offset_Time
	 */
	public static int cameraExposeGetStartOffsetTime()
	{
		return SPEC_Exposure_Get_Start_Offset_Time();
	}

// camera temperature control
	/**
	 * This method tells the temperature module to return to ambient temperature.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Ambient
	 */
	public static void cameraTemperatureAmbient() throws SpecNativeException
	{
		SPEC_Temperature_Ambient();
	}

	/**
	 * This method sets the temperature to attain.
	 * @param targetTemperature The temperature to attain, in degrees C. 
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Set
	 */
	public static void cameraTemperatureSet(double targetTemperature) throws SpecNativeException
	{
		SPEC_Temperature_Set(targetTemperature);
	}

	/**
	 * This method switches off temperature control.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Off
	 */
	public static void cameraTemperatureOff() throws SpecNativeException
	{
		SPEC_Temperature_Off();
	}

	/**
	 * This method gets the current temperature of the camera.
	 * @return The current temperature in degrees Centigrade.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Get
	 */
	public static double cameraTemperatureGet() throws SpecNativeException
	{
		return SPEC_Temperature_Get();
	}

	/**
	 * This method tells the temperature control system to control itself towards the desired temperature.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Regulate
	 */
	public static void cameraTemperatureRegulate() throws SpecNativeException
	{
		SPEC_Temperature_Regulate();
	}

	/**
	 * This method gets the status of the temperature control system.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Temperature_Get_Status
	 */
	public static String cameraTemperatureGetStatus() throws SpecNativeException
	{
		return SPEC_Temperature_Get_Status();
	}

//Digital Analogue IO card selection
	/**
	 * Routine to select which hardware IO card to use for the arc-lamp and filter communication.
	 * Should be called before open method.
	 * @param deviceNumber A number specifying which IO device to talk to. One of:
	 * 	DAIO_INTERFACE_DEVICE_NONE, DAIO_INTERFACE_DEVICE_TEXT, DAIO_INTERFACE_DEVICE_DAS08JR,
	 * 	DAIO_INTERFACE_DEVICE_PC104_DAS08.
	 * @exception SpecNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_DAIO_Select_Device
	 * @see #DAIO_INTERFACE_DEVICE_NONE
	 * @see #DAIO_INTERFACE_DEVICE_TEXT
	 * @see #DAIO_INTERFACE_DEVICE_DAS08JR
	 * @see #DAIO_INTERFACE_DEVICE_PC104_DAS08
	 * @see #open
	 */
	public static void selectIODevice(int deviceNumber) throws SpecNativeException
	{
		SPEC_DAIO_Select_Device(deviceNumber);
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 0.4  2000/11/20 16:40:09  cjm
// Added cameraExposeGetLength and cameraExposeGetStartTime.
//
// Revision 0.3  2000/10/23 09:23:54  cjm
// Added SPEC_FILTER_COUNT constant.
//
// Revision 0.2  2000/10/19 13:48:14  cjm
// initial revision.
//
// Revision 0.1  2000/10/16 17:37:13  cjm
// initial revision.
//
//
