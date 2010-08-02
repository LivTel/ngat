// LTAGLampUnit.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LTAGLampUnit.java,v 1.8 2010-08-02 14:11:11 cjm Exp $
package ngat.lamp;

import java.io.*;
import java.lang.*;
import java.util.*;
import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class supports an interface to the LT A&G lamp unit. This unit is a PLC controlled device
 * that supports 3 lamps (Tungsten,Neon and Xenon). They are controlled with a Micrologix 1100 PLC
 * over Ethernet/IP (controlled via the ngat.eip library). 
 * @author Chris Mottram
 * @version $Revision: 1.8 $
 */
public class LTAGLampUnit implements LampUnitInterface
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: LTAGLampUnit.java,v 1.8 2010-08-02 14:11:11 cjm Exp $");
	/**
	 * Basic unit log level.
	 * @see ngat.util.logging.Logging#VERBOSITY_INTERMEDIATE
	 */
	public final static int LOG_LEVEL_UNIT_BASIC = Logging.VERBOSITY_INTERMEDIATE;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;
	/**
	 * The logger to log messages to.
	 */
	protected NGATProperties properties = null;
	/**
	 * List of lamps in the unit.
	 */
	protected List lampList = null;
	/**
	 * Details of how to connect to the PLC.
	 * @see PLCConnection
	 */
	protected PLCConnection connection  = null;
	/**
	 * A string containing the PLC address of the fault bit that is set when a high light level is
	 * detected within the A&G box when all lights are commanded off.
	 */
	protected String highLightLevelFaultPLCAddress = null;
	/**
	 * A string containing the PLC address of the fault bit that is set when the PLC has turned off a light
	 * that has been left on too long.
	 */
	protected String lampOnFaultPLCAddress = null;
	/**
	 * A string containing the PLC address of integer used to store the actual light level in the A&G box.
	 */
	protected String lightLevelPLCAddress = null;
	/**
	 * PLC Address of the bit that moves the calibration mirror in and out of the frodospec beam.
	 */
	protected String mirrorInOutPLCAddress = null;
	/**
	 * PLC Address of the status bit that is set when the calibration mirror is in the frodospec beam.
	 */
	protected String mirrorStatusInPLCAddress = null;
	/**
	 * PLC Address of the status bit that is set when the calibration mirror is out of the frodospec beam.
	 */
	protected String mirrorStatusOutPLCAddress = null;
	/**
	 * PLC Address of the status bit that is set when a mirror movement fault has occured.
	 */
	protected String mirrorStatusMoveFaultPLCAddress = null;
	/**
	 * PLC Address of the bit to set to reset PLC faults.
	 */
	protected String faultResetPLCAddress = null;
	/**
	 * PLC Address of the status bit that is set when an internal PLC fault has occured.
	 */
	protected String faultStatusPLCAddress = null;
	/**
	 * Lamp on fault timer preset in seconds.
	 */
	protected PLCValueSetter lampOnFaultTimer = null;
	/**
	 * Low light alarm threshold in A/D counts.
	 */
	protected PLCValueSetter lowlightLevel = null;
	/**
	 * Timeout, in milliseconds, to wait after commanding the calibration mirror to move
	 * before failing. This timeout is theoretically 10s (set in the PLC), however this is
	 * a secondary software timeout.
	 */
	protected int mirrorMoveTimeout = 20000;
	/**
	 * Copy of the log Level. Used to set log levels of new objects.
	 */
	protected int logLevel = 0;

	/**
	 * Constructor. Initialise the logger, properties, plc, lampList objects.
	 * @exception EIPNativeException Thrown if the Df1Library constructor fails.
	 * @see #logger
	 * @see #properties
	 * @see #lampList
	 */
	public LTAGLampUnit() throws EIPNativeException
	{
		super();
		logger = LogManager.getLogger(this);
		properties = new NGATProperties();
		lampList = new Vector();
	}

	/**
	 * Load the configuration file that specified the connection properties to the LT Lamp unit PLC.
	 * @param filename The properties file containing the relevant connection data.
	 * @exception FileNotFoundException Thrown by properties.load.
	 * @exception IOException Thrown by properties.load.
	 * @exception NullPointerException Thrown if a property was null.
	 * @exception EIPFormatException Thrown if the device Id was not a valid device.
	 * @exception NGATPropertyException Thrown by loadConfig.
	 * @see #loadConfig(java.io.File)
	 */
	public void loadConfig(String filename) throws FileNotFoundException, IOException, NGATPropertyException
	{
		loadConfig(new File(filename));
	}

	/**
	 * Load the configuration file that specified the connection properties to the LT Lamp unit PLC.
	 * @param file The properties file containing the relevant connection data.
	 * @exception FileNotFoundException Thrown by properties.load.
	 * @exception IOException Thrown by properties.load.
	 * @exception NullPointerException Thrown if a property was null.
	 * @exception EIPFormatException Thrown if the device Id was not a valid device.
	 * @exception NGATPropertyException Thrown if the port number was not an integer.
	 * @see #properties
	 * @see #connection
	 * @see #lampList
	 * @see #logLevel
	 * @see #highLightLevelFaultPLCAddress
	 * @see #lampOnFaultPLCAddress
	 * @see #lightLevelPLCAddress
	 * @see ngat.util.NGATProperties#load
	 * @see PLCConnection#loadConfig
	 * @see #mirrorInOutPLCAddress
	 * @see #mirrorStatusInPLCAddress
	 * @see #mirrorStatusOutPLCAddress
	 * @see #mirrorStatusMoveFaultPLCAddress
	 * @see #mirrorMoveTimeout
	 * @see #faultResetPLCAddress
	 * @see #faultStatusPLCAddress
	 */
	public void loadConfig(File file) throws FileNotFoundException, IOException, NullPointerException,
						 EIPFormatException, NGATPropertyException
	{
		int index;
		boolean done;
		String lampName = null;
		LTLamp lamp = null;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Started.");
		// load ngat properties
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Loading properties from:"+file);
		properties.load(file);
		// initialise connection data from properties
		connection = new PLCConnection();
		connection.loadConfig(properties);
		connection.setLogLevel(logLevel);
		// extract lamp data from list
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Finding lamps.");
		index = 0;
		done = false;
		while(done == false)
		{
			lampName = properties.getProperty("lamp.list."+index);
			if(lampName != null)
			{
				logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Loading lamp: "+
					   lampName+" at index "+index+".");
				lamp = new LTLamp();
				lamp.setName(lampName);
				lamp.setConnection(connection);
				lamp.loadConfig(properties);
				lamp.setLogLevel(logLevel);
				logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+
					   ":loadConfig:Addinging lamp: "+
					   lampName+" to list.");
				lampList.add(lamp);
				index++;
			}
			else
				done = true;
		}
		// lamp on fault timer preset
		lampOnFaultTimer = new PLCValueSetter();
		lampOnFaultTimer.loadConfig(properties,"lamp.light.on.fault.timer");
		// low light level alarm threshold
		lowlightLevel = new PLCValueSetter();
		lowlightLevel.loadConfig(properties,"lamp.low.light.level");
		// high light level status
		highLightLevelFaultPLCAddress = properties.getProperty("lamp.light.level.high.fault.plc_address");
		// lamp on fault timer set
		lampOnFaultPLCAddress = properties.getProperty("lamp.light.on.fault.set.plc_address");
		// actual light level status
		lightLevelPLCAddress = properties.getProperty("lamp.light.level.plc_address");
		// calibration mirror control bit
		mirrorInOutPLCAddress = properties.getProperty("lamp.mirror.inout.plc_address");
		// calibration mirror status bits
		mirrorStatusInPLCAddress = properties.getProperty("lamp.mirror.status.in.plc_address");
		mirrorStatusOutPLCAddress = properties.getProperty("lamp.mirror.status.out.plc_address");
		// calibration move fault status bit
		mirrorStatusMoveFaultPLCAddress = properties.getProperty("lamp.mirror.move_fault.plc_address");
		// mirror move software timeout in milliseconds
		mirrorMoveTimeout = properties.getInt("lamp.mirror.move.timeout");
		// fault reset 
		faultResetPLCAddress = properties.getProperty("lamp.plc.fault.reset.plc_address");
		// PLC fault status address
		faultStatusPLCAddress = properties.getProperty("lamp.plc.fault.plc_address");
		// and finish
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Finished.");
	}

	/**
	 * Initialise the unit.
	 * <ul>
	 * <li>Try to reset any faults in the PLC with a faultReset.
	 * <li>Try to move th calibration mirror out of the beam.
	 * <li>For each lamp in lampList, calls it's init method.
	 * <li>Set the lampOnFaultTimer, if configured to do so.
	 * <li>Set the lowlightLevel threshold, if configured to do so.
	 * </ul>
	 * @exception EIPNativeException Thrown if comms to the PLC fail.
	 * @exception Exception Thrown if the mirror fails to stow.
	 * @see #faultReset
	 * @see #stowMirror
	 * @see #lampList
	 * @see #lampOnFaultTimer
	 * @see #lowlightLevel
	 * @see #connection
	 * @see LTLamp#init
	 * @see PLCValueSetter#setValue
	 */
	public void init() throws Exception
	{
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Started.");
		// fault reset
		faultReset();
		// move calibration mirror out of beam
		stowMirror();
		// initialise each lamp
		for(int i = 0; i < lampList.size(); i++)
		{
			LTLamp lamp = (LTLamp)(lampList.get(i));
			logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Initialising lamp "+
				   lamp.getName()+".");
			lamp.init();
		}
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Configuring lamp on fault timer.");
		// configure presets, thresholds etc if configured to do so.
		lampOnFaultTimer.setValue(connection);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Configuring low light level.");
		lowlightLevel.setValue(connection);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Finished.");
	}

	/**
	 * Method to try to reset any PLC faults.
	 * @exception EIPNativeException Thrown if comms to the PLC fail.
	 * @see #faultResetPLCAddress
	 * @see #connection
	 * @see ngat.lamp.PLCConnection#setBoolean
	 */
	public void faultReset() throws EIPNativeException
	{
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":faultReset:Starting.");
		connection.setBoolean(faultResetPLCAddress,true);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":faultReset:Finished.");
	}

	/**
	 * Method to move mirror into the beam.
	 * <ul>
	 * <li>We set mirrorInOutPLCAddress PLC address to true, to tell the PLC to move the mirror inline
	 * <li>We save a timestamp for calculating a timeout.
	 * <li>We enter a loop:
	 *     <ul>
	 *     <li>We test whether the mirror is currenly in the beam by reading mirrorStatusInPLCAddress,
	 *         and exit the loop successfully if that is the case
	 *     <li>We test whether the move fault bit is set at mirrorStatusMoveFaultPLCAddress, and throw
	 *         an exception if that is the case.
	 *     <li>We test whether the PLC fault bit is set at faultStatusPLCAddress, and throw
	 *         an exception if that is the case.
	 *     <li>We test whether the loop has been running longer than mirrorMoveTimeout, and throw
	 *         an exception if that is the case.
	 *     </ul>
	 * </ul>
	 * @exception EIPNativeException Thrown if comms to the PLC fail.
	 * @exception Exception Thrown if the PLC fault status bit is set, or the PLC move fault status bit is set..
	 * @see #mirrorInOutPLCAddress
	 * @see #mirrorStatusInPLCAddress
	 * @see #mirrorStatusMoveFaultPLCAddress
	 * @see #mirrorMoveTimeout
	 * @see #faultStatusPLCAddress
	 * @see #connection
	 * @see ngat.lamp.PLCConnection#setBoolean
	 * @see ngat.lamp.PLCConnection#getBoolean
	 */
	public void moveMirrorInline() throws Exception
	{
		boolean done,inline,moveFault,plcFault;
		long startTime,currentTime,moveTime;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":moveMirrorInline:Starting.");
		// set demand
		connection.setBoolean(mirrorInOutPLCAddress,true);
		// store start time
		startTime = System.currentTimeMillis();
		// loop, waiting for in line status or movement/PLC fault.
		done = false;
		while(done == false)
		{
			logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+
				   ":moveMirrorInline:Getting current status.");
			// are we inline
			inline = connection.getBoolean(mirrorStatusInPLCAddress);
			if(inline)
			{
				logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+
					   ":moveMirrorInline:Mirror is now inline.");
				done = true;
			}
			// movement fault?
			moveFault = connection.getBoolean(mirrorStatusMoveFaultPLCAddress);
			if(moveFault)
				throw new Exception(this.getClass().getName()+"moveMirrorInline:Move fault bit set.");
			// PLC fault?
			plcFault = connection.getBoolean(faultStatusPLCAddress);
			if(plcFault)
				throw new Exception(this.getClass().getName()+"moveMirrorInline:PLC fault bit set.");
			// check for timeout
			currentTime = System.currentTimeMillis();
			moveTime = currentTime - startTime;
			if(((int)moveTime) > mirrorMoveTimeout)
			{
				throw new Exception(this.getClass().getName()+"stowMirror:Move timeout ("+moveTime+
						    " vs "+mirrorMoveTimeout+").");
			}
		}// end while
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":moveMirrorInline:Finished.");
	}

	/**
	 * Method to move mirror out of the beam.
	 * <ul>
	 * <li>We set mirrorInOutPLCAddress PLC address to false, to tell the PLC to move the mirror out of line.
	 * <li>We save a timestamp for calculating a timeout.
	 * <li>We enter a loop:
	 *     <ul>
	 *     <li>We test whether the mirror is currenly out of the beam by reading mirrorStatusOutPLCAddress,
	 *         and exit the loop successfully if that is the case.
	 *     <li>We test whether the move fault bit is set at mirrorStatusMoveFaultPLCAddress, and throw
	 *         an exception if that is the case.
	 *     <li>We test whether the PLC fault bit is set at faultStatusPLCAddress, and throw
	 *         an exception if that is the case.
	 *     <li>We test whether the loop has been running longer than mirrorMoveTimeout, and throw
	 *         an exception if that is the case.
	 *     </ul>
	 * </ul>
	 * @exception EIPNativeException Thrown if comms to the PLC fail.
	 * @exception Exception Thrown if the PLC fault status bit is set, or the PLC move fault status bit is set..
	 * @see #mirrorInOutPLCAddress
	 * @see #mirrorStatusOutPLCAddress
	 * @see #mirrorStatusMoveFaultPLCAddress
	 * @see #mirrorMoveTimeout
	 * @see #faultStatusPLCAddress
	 * @see #connection
	 * @see ngat.lamp.PLCConnection#setBoolean
	 * @see ngat.lamp.PLCConnection#getBoolean
	 */
	public void stowMirror() throws Exception
	{
		boolean done,outline,moveFault,plcFault;
		long startTime,currentTime,moveTime;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":stowMirror:Starting.");
		// set demand : false means mirror out of line
		connection.setBoolean(mirrorInOutPLCAddress,false);
		// store start time
		startTime = System.currentTimeMillis();
		// loop, waiting for out of line status or movement/PLC fault.
		done = false;
		while(done == false)
		{
			logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+
				   ":stowMirror:Getting current status.");
			// are we out of line
			outline = connection.getBoolean(mirrorStatusOutPLCAddress);
			if(outline)
			{
				logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+
					   ":stowMirror:Mirror is now out of line.");
				done = true;
			}
			// movement fault?
			moveFault = connection.getBoolean(mirrorStatusMoveFaultPLCAddress);
			if(moveFault)
				throw new Exception(this.getClass().getName()+"stowMirror:Move fault bit set.");
			// PLC fault?
			plcFault = connection.getBoolean(faultStatusPLCAddress);
			if(plcFault)
				throw new Exception(this.getClass().getName()+"stowMirror:PLC fault bit set.");
			// check for timeout
			currentTime = System.currentTimeMillis();
			moveTime = currentTime - startTime;
			if(((int)moveTime) > mirrorMoveTimeout)
			{
				throw new Exception(this.getClass().getName()+"stowMirror:Move timeout ("+moveTime+
						    " vs "+mirrorMoveTimeout+").");
			}
		}// end while
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":stowMirror:Finished.");
	}

	/**
	 * Turn the specified lamp on.
	 * @param lamp The name of the lamp.
	 * @exception Exception Thrown if we can't find the specified lamp, or comms to the device fails.
	 * @see #getLamp
	 * @see LampInterface#turnLampOn
	 */
	public void turnLampOn(String lamp) throws Exception
	{
		LampInterface li = null;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnLampOn:"+lamp+" Started.");
		li = getLamp(lamp);
		li.turnLampOn();
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnLampOn:"+lamp+" Finished.");
	}

	/**
	 * Turn the specified lamp off.
	 * @param lamp The name of the lamp.
	 * @exception Exception Thrown if we can't find the specified lamp, or comms to the device fails.
	 * @see #getLamp
	 * @see LampInterface#turnLampOff
	 */
	public void turnLampOff(String lamp) throws Exception
	{
		LampInterface li = null;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnLampOff:"+lamp+" Started.");
		li = getLamp(lamp);
		li.turnLampOff();
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnLampOff:"+lamp+" Finished.");
	}

	/**
	 * Method to turn on more than one lamp.
	 * @param lampListString A string containing a space separated list of valid lamp names.
	 * @exception Exception Thrown if we can't find one of the lamps, or comms to the device fails.
	 * @see #lampList
	 * @see LTLamp#turnLampOff
	 */
	public void turnLampsOn(String lampListString) throws Exception
	{
		LTLamp lamp = null;
		List onList = null;
		StringTokenizer st = null;
		String lampName = null;

		st = new StringTokenizer(lampListString," ");
		onList = new Vector();
		while(st.hasMoreTokens())
		{
			lampName = st.nextToken();
			lamp = (LTLamp)(getLamp(lampName));
			onList.add(lamp);
		}
		try
		{
			for(int i = 0;i < onList.size(); i++)
			{
				lamp = (LTLamp)(onList.get(i));
				lamp.turnLampOn();
			}
		}
		catch(Exception e)
		{
			for(int i = 0;i < onList.size(); i++)
			{
				lamp = (LTLamp)(onList.get(i));
				lamp.turnLampOff();
			}
			throw e;
		}
	}

	/**
	 * Turn off all the lamps in the list.
	 * @exception Exception Thrown if we can't find the specified lamp, or comms to the device fails.
	 * @see #lampList
	 * @see LTLamp#turnLampOff
	 */
	public void turnAllLampsOff() throws Exception
	{
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnAllLampsOff:Started.");
		for(int i = 0; i < lampList.size(); i++)
		{
			LTLamp lamp = (LTLamp)(lampList.get(i));
			lamp.turnLampOff();
		}
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":turnAllLampsOff:Finished.");
	}

	/**
	 * Return whether the speciied lamp is on (true) or off (false).
	 * @param lamp The name of the lamp.
	 * @return On (true) or off (false).
	 * @exception Exception Thrown if we can't find the specified lamp, or comms to the device fails.
	 * @see #getLamp
	 * @see LampInterface#isLampOn
	 */
	public boolean isLampOn(String lamp) throws Exception
	{
		LampInterface li = null;

		li = getLamp(lamp);
		return li.isLampOn();
	}

	/**
	 * Return whether the PLC fault status bit has been set.
	 * @return true if the PLC fault bit is set, false if it is not.
	 * @see #logger
	 * @see #connection
	 * @see #faultStatusPLCAddress
	 * @see ngat.lamp.PLCConnection#getBoolean
	 */
	public boolean getFaultStatus()  throws EIPNativeException
	{
		boolean plcFault;

		plcFault = connection.getBoolean(faultStatusPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":getFaultStatus:Returned plcFault:"+
			   plcFault+".");
		return plcFault;
	}

	/**
	 * Has an error flag been set on the PLC.
	 * <ul>
	 * <li>The method reads the <b>high light level fault</b>  
	 *     (high light level in A&G box when alllights should be off) using the highLightLevelFaultPLCAddress.
	 * <li>The method reads the <b>lamp on fault</b> (the light was switched off by the PLC because it has been 
	 *     left switched on for too long) using the lampOnFaultPLCAddress.
	 * <li>The method reads the <b>mirror move fault</b>  using the mirrorStatusMoveFaultPLCAddress.
	 * <li>The method reads the <b>plc fault status bit</b>  using the faultStatusPLCAddress.
	 * <li>If all the bits are false, false is returned, otherwise true is returned.
	 * </ul>
	 * @return true if an error has occured, false if it has not.
	 *         The return value is the logical OR of a <b>high light level fault</b> 
	 *         (high light level in A&G box when alllights should be off) and a 
	 *         <b>lamp on fault</b> (the light was switched off by the PLC because it has been left 
	 *         switched on for too long).
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #highLightLevelFaultPLCAddress
	 * @see #lampOnFaultPLCAddress
	 * @see #mirrorStatusMoveFaultPLCAddress
	 * @see #faultStatusPLCAddress
	 * @see ngat.lamp.PLCConnection#getBoolean
	 */
	public boolean isError() throws EIPNativeException
	{
		boolean highLightLevelFault,lampOnFault,moveFault,plcFault;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Started.");
		// high light level
		highLightLevelFault = connection.getBoolean(highLightLevelFaultPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned highLightLevelFault:"+
			   highLightLevelFault+".");
		// lamp on fault
		lampOnFault = connection.getBoolean(lampOnFaultPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned lampOnFault:"+
			   lampOnFault+".");
		// mirror movement fault
		moveFault = connection.getBoolean(mirrorStatusMoveFaultPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned moveFault:"+
			   moveFault+".");
		// general internal PLC fault
		plcFault = connection.getBoolean(faultStatusPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned plcFault:"+
			   plcFault+".");
		// aggregate faults
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned:"+
			   (highLightLevelFault|lampOnFault|moveFault|plcFault)+".");
		return highLightLevelFault|lampOnFault|moveFault|plcFault;
	}

	/**
	 * Return the actual light level in the A&G box.
	 * <ul>
	 * <li>Call the getInteger method in the connection instance, using the lightLevelPLCAddress PLC address.
	 * </ul>
	 * @return An integer, the actual light level in A/D counts.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #lightLevelPLCAddress
	 * @see #connection
	 * @see PLCConnection#getInteger
	 */
	public int getLightLevel() throws EIPNativeException
	{
		int retval;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":getLightLevel:Started.");
		retval = connection.getInteger(lightLevelPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":getLightLevel:Returned :"+retval+".");
		return retval;
	}

	/**
	 * Set the log level.Sets the logger, connection, and each lamps log level.
	 * @param level The log level.
	 * @see #logLevel
	 * @see #logger
	 * @see #connection
	 * @see #lampList
	 * @see ngat.util.logging.Logger#setLogLevel
	 * @see ngat.lamp.PLCConnection#setLogLevel
	 * @see ngat.lamp.LTLamp#setLogLevel
	 */
	public void setLogLevel(int level)
	{
		logLevel = level;
		logger.setLogLevel(level);
		if(connection != null)
			connection.setLogLevel(level);
		for(int i = 0; i < lampList.size(); i++)
		{
			LTLamp lamp = (LTLamp)(lampList.get(i));
			lamp.setLogLevel(level);
		}
	}

	// protected methods
	/**
	 * Find the specified lamp from it's name.
	 * @param lampName The name of the lamp.
	 * @return An instance of LampInterface (LTLamp).
	 * @exception IllegalArgumentException Thrown if a lamp of that name is not in the list.
	 * @see #lampList
	 * @see LampInterface#getName
	 */
	protected LampInterface getLamp(String lampName) throws IllegalArgumentException
	{
		LampInterface li = null;
		int i;
		boolean done;

		done = false;
		i = 0;
		while(done == false)
		{
			li = (LampInterface)(lampList.get(i));
			if(li.getName().equals(lampName))
				return li;
			i++;
			if(i == lampList.size())
				done = true;
		}
		throw new IllegalArgumentException(this.getClass().getName()+":getLamp:Illegal lamp name:"+lampName+
						   " not found in "+i+" lamps in list.");
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.7  2010/03/15 16:01:47  cjm
// Added getFaultStatus method.
//
// Revision 1.6  2010/03/15 14:41:37  cjm
// Added lamp mirror position config/control and fault status/reset bits.
//
// Revision 1.5  2009/02/05 14:33:29  cjm
// Changed logging levels to GLS verbosities.
//
// Revision 1.4  2008/10/14 13:30:14  cjm
// Fixed logging problems by adding internal logLevel variable.
// Now loadConfig / setLogLevel can be called in any order, and the logging
// should still be set correctly in the connection and lamp objects.
//
// Revision 1.3  2008/10/09 14:15:09  cjm
// Rewrite so ngat.lamp now does PLC comms using ngat.eip rather than
// via ngat.df1 / ngat.serial.arcomess.
//
// Revision 1.2  2008/10/03 09:20:00  cjm
// Changes relating to libdf1 using libarcom_ess handles.
//
// Revision 1.1  2008/03/06 10:47:39  cjm
// Initial revision
//
//
