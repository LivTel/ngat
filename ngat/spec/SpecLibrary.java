// SpecLibrary.java -*- mode: Fundamental;-*-
// libspec Java wrapper.
// $Header: /space/home/eng/cjm/cvs/ngat/spec/SpecLibrary.java,v 0.1 2000-10-16 17:37:13 cjm Exp $
package ngat.spec;

/**
 * This class holds the JNI interface to the general spectrograph access routines provided by libspec.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class SpecLibrary
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SpecLibrary.java,v 0.1 2000-10-16 17:37:13 cjm Exp $");
// general constants
	/**
	 * Bit definition to pass into open to tell the routine to open communication with the IO card hardware. 
	 * @see #open
	 */
	public final static int SPEC_OPEN_DAIO_BIT			= (1<<0);
	/**
	 * Bit definition to pass into open to tell the routine to open communication with the 
	 * Apogee CCD camera hardware. 
	 * @see #open
	 */
	public final static int SPEC_OPEN_APOGEE_BIT			= (1<<1);
	/**
	 * Bit definition to pass into open to tell the routine to open communication with all the hardware. 
	 * @see #SPEC_OPEN_DAIO_BIT
	 * @see #SPEC_OPEN_APOGEE_BIT
	 * @see #open
	 */
	public final static int SPEC_OPEN_ALL_BIT			= (SPEC_OPEN_DAIO_BIT|SPEC_OPEN_APOGEE_BIT);
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Open(int open_bits) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Close routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Close() throws SPECNativeException;
// arc lamp native methods
	/**
	 * Native wrapper to libspec SPEC_Arc_Lamp_Set_Lamp routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Arc_Lamp_Set_Lamp(int lamp_number) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Arc_Lamp_Set routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Arc_Lamp_Set(boolean on) throws SPECNativeException;
// filter native methods
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Position routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Position(int position,int ad_count) 
		throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Auto_Configure_Position routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Auto_Configure_Position() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position_AD_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position_AD_Count(int position) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Set_Position routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Set_Position(int position) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Wavelength_Range routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Wavelength_Range(double min_wavelength,
		double max_wavelength) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_AD_Range routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_AD_Range(int min_ad_count,int max_ad_count) 
		throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Auto_Configure_AD_Range routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Auto_Configure_AD_Range() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Wavelength_Range routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native SpecDoubleRange SPEC_Filter_Get_Wavelength_Range() throws SPECNativeException; 
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Range routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native SpecIntRange SPEC_Filter_Get_AD_Range() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Set_Wavelength routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Set_Wavelength(double wavelength) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Wavelength routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native double SPEC_Filter_Get_Wavelength() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_AD_Count_Error routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_AD_Count_Error(int ad_count) throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Switch_AD_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Switch_AD_Count(int ad_count)  throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Configure_Position_Timeout_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Configure_Position_Timeout_Count(int timeout_number) 
		throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Count_Error routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_AD_Count_Error() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Switch_AD_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Switch_AD_Count() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_Position_Timeout_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_Position_Timeout_Count() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Get_AD_Count routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native int SPEC_Filter_Get_AD_Count() throws SPECNativeException;
	/**
	 * Native wrapper to libspec SPEC_Filter_Abort routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_Filter_Abort() throws SPECNativeException;
// daio specific native methods
	/**
	 * Native wrapper to libspec SPEC_DAIO_Select_Device routine.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 */
	private static native void SPEC_DAIO_Select_Device(int device) throws SPECNativeException;

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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Open
	 * @see #SPEC_OPEN_ALL_BIT
	 */
	public static void open(int openBits) throws SPECNativeException
	{
		SPEC_Open(openBits);
	}

	/**
	 * Method to close the opened connections to the hardware.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Close
	 */
	public static void close() throws SPECNativeException
	{
		SPEC_Close();
	}
// arc lamp
	/**
	 * Routine to determine which lamp to switch on and off. 
	 * @param lampNumber Which lamp to switch on and off. 
	 * 	There are two lamps, the value passed in here should be either one or zero. 
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Arc_Lamp_Set_Lamp
	 */
	public static void arcLampSetLamp(int lampNumber) throws SPECNativeException
	{
		SPEC_Arc_Lamp_Set_Lamp(lampNumber);
	}

	/**
	 * Routine to switch the lamp on or off. The lamp should be selected with arcLampSetLamp
	 * @param on This parameter should be set to TRUE to switch the lamp on, 
	 * 	and FALSE to switch the lamp off. 
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Arc_Lamp_Set
	 */
	public static void arcLampSet(boolean on) throws SPECNativeException
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Position
	 */
	public static void filterConfigurePosition(int position,int adCount) throws SPECNativeException
	{
		SPEC_Filter_Configure_Position(position,adCount); 
	}

	/**
	 * Routine to automatically determine the filter slide A/D count positions.
	 * This is done by driving the filter slide between the two limits, noting at what position A/D counts
	 * the filter indexer is set.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Auto_Configure_Position
	 */
	public static void filterAutoConfigurePosition() throws SPECNativeException
	{
		SPEC_Filter_Auto_Configure_Position();
	}

	/**
	 * Routine to get the A/D count for the specified filter slide position. 
	 * @param position Which filter slide position to get the A/D count for. 
	 * 	An integer between 0 and the number of filters minus one. 
	 * @return An A/D count is returned, which when returned from the position potentiometer, 
	 * 	indicates the filter slide has achieved the required position.
 	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position_AD_Count
	 */
	public static int filterGetPositionADCount(int position) throws SPECNativeException
	{
		return SPEC_Filter_Get_Position_AD_Count(position);
	}

	/**
	 * Routine to move the filter slide until it has achieved the required position. 
	 * This routine is only used for the MES, where there are set positions to be set.
	 * The Nu-View should use filterSetWavelength.
 	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @param position The position number, between zero and the number of filters minus one, 
	 * 	that the slide should be moved to. 
	 * @see #SPEC_Filter_Set_Position
	 * @see #filterSetWavelength
	 */
	public static void filterSetPosition(int position) throws SPECNativeException
	{
		SPEC_Filter_Set_Position(position);
	}

	/**
	 * This routine gets the current position the filter slide is at. 
	 * The filter indexer is checked to ensure the filter slide is in position, if it in between positions -1
	 * is returned.
	 * @return This routine returns the filter position between 0 and the number of position minus one,
	 * 	or -1 if the filter slide is between positions.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position
	 */
	public static int filterGetPosition() throws SPECNativeException
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Wavelength_Range
	 */
	public static void filterConfigureWavelengthRange(double minWavelength,
		double maxWavelength) throws SPECNativeException
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_AD_Range
	 */
	public static void filterConfigureADRange(int minADCount,int maxADCount) throws SPECNativeException
	{
		SPEC_Filter_Configure_AD_Range(minADCount,maxADCount);
	}

	/**
	 * Try to automaticaly configure the A/D range a continuous variable filter slide operates over. 
	 * This is done by driving the filter slide into each limit in turn, 
	 * and reading the A/D count at these locations. 
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Auto_Configure_AD_Range
	 */
	public static void filterAutoConfigureADRange() throws SPECNativeException
	{
		SPEC_Filter_Auto_Configure_AD_Range();
	}

	/**
	 * Routine to get current settings of the minimum and maximum wavelength's of a 
	 * continuously variable filter slide at the limits. 
	 * The variables this routine gets are only used for continuously varaible filter slides (Nu-View). 
	 * @return An allocated instance of SpecDoubleRange is returned, with the minimum and maximum wavelength's
	 * 	set.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Wavelength_Range
	 */
	public static SpecDoubleRange filterGetWavelengthRange() throws SPECNativeException
	{
		return SPEC_Filter_Get_Wavelength_Range();
	}

	/**
	 * Routine to get the minimum A/D count of a filter slide at the minimum limit, 
	 * and the maximum A/D count of the filter slide at the maximum limit. 
	 * The variables this routine gets are only used for continuously varaible filter slides (Nu-View). 
	 * @return An allocated instance of SpecIntRange is returned, with the A/D count range contained
	 * 	within it.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Range
	 */
	public static SpecIntRange filterGetADRange() throws SPECNativeException
	{
		return SPEC_Filter_Get_AD_Range();
	}

	/**
	 * Routine to move the filter slide until it has achieved the required wavelength. 
	 * This routine is only used for the Nu-View, where there is a continous filter slide with no positions. 
	 * The MES should use filterSetPosition. 
	 * @param wavelength The wavelength to drive to.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Set_Wavelength
	 * @see #filterSetPosition
	 */
	public static void filterSetWavelength(double wavelength) throws SPECNativeException
	{
		SPEC_Filter_Set_Wavelength(wavelength);
	}

	/**
	 * Routine to get the current wavelength a continuously variable filter slide is currently at. 
	 * This routine should only be used with continuously variable filter slides (Nu-View). 
	 * @return The current wavelength the filter slide is driven to.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Wavelength
	 */
	public static double filterGetWavelength() throws SPECNativeException
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_AD_Count_Error
	 */
	public static void filterConfigureADCountError(int adCount) throws SPECNativeException
	{
		SPEC_Filter_Configure_AD_Count_Error(adCount);
	}

	/**
	 * The biggest A/D count on an analogue input that means a switch is off. 
	 * An A/D count less than this, i.e. near to zero means the switch has been tripped.
	 * This value is used for both the limit switches and index switches. 
	 * It should not be too big, otherwise switches will appear to have been tripped when they are not set.
	 * @param adCount An A/D count, below which a switch is meant to be tripped.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Switch_AD_Count
	 */
	public static void filterConfigureSwitchADCount(int adCount) throws SPECNativeException
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
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Configure_Position_Timeout_Count
	 */
	public static void filterConfigurePositionTimeoutCount(int timeoutNumber) throws SPECNativeException
	{
		SPEC_Filter_Configure_Position_Timeout_Count(timeoutNumber);
	}

	/**
	 * Routine to get the current value of the A/D Count Error configuration, 
	 * which determines how close the A/D count has to be to a target A/D count to be at that target position. 
	 * This allows us some potential slop in positions to allows for digitisation times etc.
	 * @return The current value of A/D Count Error.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Count_Error
	 */
	public static int filterGetADCountError() throws SPECNativeException
	{
		return SPEC_Filter_Get_AD_Count_Error();
	}

	/**
	 * Routine to get the current switch A/D count configuration.
	 * A returned A/D count lower than this value means the relevant switch has been tripped. 
	 * @return The current value of Switch A/D count.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Switch_AD_Count
	 */
	public static int filterGetSwitchADCount() throws SPECNativeException
	{
		return SPEC_Filter_Get_Switch_AD_Count();
	}

	/**
	 * Routine to get the current value of the Position Timeout Number. 
	 * When driving the filter to a location, if the position A/D count is returned as the same
	 * number this many times, it is assumed the motor has failed or the position pot is slipping, 
	 * and an error occurs. 
	 * @return The current configuration of the Position Timeout Number.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_Position_Timeout_Count
	 */
	public static int filterGetPositionTimeoutCount() throws SPECNativeException
	{
		return SPEC_Filter_Get_Position_Timeout_Count();
	}

	/**
	 * Simple routine to get the current A/D count of the potentiometer monitoring the filter slide position. 
	 * @return The A/D count of the potentiometer monitoring the filter slide position.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Get_AD_Count
	 */
	public static int filterGetADCount() throws SPECNativeException
	{
		return SPEC_Filter_Get_AD_Count();
	}

	/**
	 * This routine sets the filter abort, which stops any filter slide movement operation currently underway.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_Filter_Abort
	 */
	public static void filterAbort() throws SPECNativeException
	{
		SPEC_Filter_Abort();
	}

//Digital Analogue IO card selection
	/**
	 * Routine to select which hardware IO card to use for the arc-lamp and filter communication.
	 * Should be called before open method.
	 * @param deviceNumber A number specifying which IO device to talk to. One of:
	 * 	DAIO_INTERFACE_DEVICE_NONE, DAIO_INTERFACE_DEVICE_TEXT, DAIO_INTERFACE_DEVICE_DAS08JR,
	 * 	DAIO_INTERFACE_DEVICE_PC104_DAS08.
	 * @exception SPECNativeException Thrown if the underlying C routine failed.
	 * @see #SPEC_DAIO_Select_Device
	 * @see #DAIO_INTERFACE_DEVICE_NONE
	 * @see #DAIO_INTERFACE_DEVICE_TEXT
	 * @see #DAIO_INTERFACE_DEVICE_DAS08JR
	 * @see #DAIO_INTERFACE_DEVICE_PC104_DAS08
	 * @see #open
	 */
	public static void selectIODevice(int deviceNumber) throws SPECNativeException
	{
		SPEC_DAIO_Select_Device(deviceNumber);
	}

}
//
// $Log: not supported by cvs2svn $
//
