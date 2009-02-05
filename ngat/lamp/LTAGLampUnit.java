// LTAGLampUnit.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LTAGLampUnit.java,v 1.5 2009-02-05 14:33:29 cjm Exp $
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
 * @version $Revision: 1.5 $
 */
public class LTAGLampUnit implements LampUnitInterface
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: LTAGLampUnit.java,v 1.5 2009-02-05 14:33:29 cjm Exp $");
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
	 * Lamp on fault timer preset in seconds.
	 */
	protected PLCValueSetter lampOnFaultTimer = null;
	/**
	 * Low light alarm threshold in A/D counts.
	 */
	protected PLCValueSetter lowlightLevel = null;
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
		// and finish
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":loadConfig:Finished.");
	}

	/**
	 * Initialise the unit.
	 * <ul>
	 * <li>For each lamp in lampList, calls it's init method.
	 * <li>Set the lampOnFaultTimer, if configured to do so.
	 * <li>Set the lowlightLevel threshold, if configured to do so.
	 * </ul>
	 * @exception EIPNativeException Thrown if comms to the PLC fail.
	 * @see #lampList
	 * @see #lampOnFaultTimer
	 * @see #lowlightLevel
	 * @see #connection
	 * @see LTLamp#init
	 * @see PLCValueSetter#setValue
	 */
	public void init() throws EIPNativeException
	{
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Started.");
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
	 * Has an error flag been set on the PLC.
	 * @return true if an error has occured, false if it has not.
	 *         The return value is the logical OR of a <b>high light level fault</b> 
	 *         (high light level in A&G box when alllights should be off) and a 
	 *         <b>lamp on fault</b> (the light was switched off by the PLC because it has been left 
	 *         switched on for too long).
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #highLightLevelFaultPLCAddress
	 * @see #lampOnFaultPLCAddress
	 * @see PLCConnection#getBoolean
	 */
	public boolean isError() throws EIPNativeException
	{
		boolean highLightLevelFault,lampOnFault;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Started.");
		highLightLevelFault = connection.getBoolean(highLightLevelFaultPLCAddress);
		lampOnFault = connection.getBoolean(lampOnFaultPLCAddress);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned highLightLevelFault:"+
			   highLightLevelFault+"|lampOnFault:"+lampOnFault+".");
		return highLightLevelFault|lampOnFault;
	}

	/**
	 * Return the actual light level in the A&G box.
	 * <ul>
	 * <li>Call the getInteger method in the Df1Library instance, using the lightLevelPLCAddress PLC address.
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
