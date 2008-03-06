// LTAGLampUnit.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LTAGLampUnit.java,v 1.1 2008-03-06 10:47:39 cjm Exp $
package ngat.lamp;

import java.io.*;
import java.lang.*;
import java.util.*;
import ngat.df1.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class supports an interface to the LT A&G lamp unit. This unit is a PLC controlled device
 * that supports 3 lamps (Tungsten,Neon and Xenon). They are controlled with a Micrologix 1100 PLC
 * (controlled via the ngat.df1 library).
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class LTAGLampUnit implements LampUnitInterface
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: LTAGLampUnit.java,v 1.1 2008-03-06 10:47:39 cjm Exp $");
	/**
	 * Basic unit log level.
	 */
	public final static int LOG_LEVEL_UNIT_BASIC = 1;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;
	/**
	 * The logger to log messages to.
	 */
	protected NGATProperties properties = null;
	/**
	 * The instance of the Df1Library which is used to control the lamp unit PLC.
	 */
	protected Df1Library df1 = null;
	/**
	 * List of lamps in the unit.
	 */
	protected List lampList = null;
	/**
	 * Details of how to connect to the PLC.
	 * @see ConnectionParameters
	 */
	protected ConnectionParameters connectionParameters  = null;
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
	 * Constructor. Initialise the logger, properties, df1, lampList objects.
	 * @exception Df1LibraryNativeException Thrown if the Df1Library constructor fails.
	 * @see #logger
	 * @see #properties
	 * @see #df1
	 * @see #lampList
	 */
	public LTAGLampUnit() throws Df1LibraryNativeException
	{
		super();
		logger = LogManager.getLogger(this);
		properties = new NGATProperties();
		df1 = new Df1Library();
		lampList = new Vector();
	}

	/**
	 * Load the configuration file that specified the connection properties to the LT Lamp unit PLC.
	 * @param filename The properties file containing the relevant connection data.
	 * @exception FileNotFoundException Thrown by properties.load.
	 * @exception IOException Thrown by properties.load.
	 * @exception NullPointerException Thrown if a property was null.
	 * @exception Df1LibraryFormatException Thrown if the device Id was not a valid device.
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
	 * @exception Df1LibraryFormatException Thrown if the device Id was not a valid device.
	 * @exception NGATPropertyException Thrown if the port number was not an integer.
	 * @see #properties
	 * @see #connectionParameters
	 * @see #lampList
	 * @see #highLightLevelFaultPLCAddress
	 * @see #lampOnFaultPLCAddress
	 * @see #lightLevelPLCAddress
	 * @see ngat.util.NGATProperties#load
	 */
	public void loadConfig(File file) throws FileNotFoundException, IOException, NullPointerException,
						 Df1LibraryFormatException, NGATPropertyException
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
		connectionParameters = new ConnectionParameters();
		connectionParameters.loadConfig(properties);
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
				lamp.setController(df1);
				lamp.setConnectionParameters(connectionParameters);
				lamp.loadConfig(properties);
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
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fail.
	 * @see #lampList
	 * @see #lampOnFaultTimer
	 * @see #lowlightLevel
	 * @see LTLamp#init
	 * @see PLCValueSetter#setValue
	 */
	public void init() throws Df1LibraryNativeException
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
		lampOnFaultTimer.setValue(df1,connectionParameters);
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":init:Configuring low light level.");
		lowlightLevel.setValue(df1,connectionParameters);
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
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #highLightLevelFaultPLCAddress
	 * @see #lampOnFaultPLCAddress
	 */
	public boolean isError() throws Df1LibraryNativeException
	{
		boolean highLightLevelFault,lampOnFault;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Started.");
		try
		{
			connectionParameters.connectToController(df1);
			highLightLevelFault = df1.getBoolean(highLightLevelFaultPLCAddress);
			lampOnFault = df1.getBoolean(lampOnFaultPLCAddress);
		}
		finally
		{
			connectionParameters.closeConnection(df1);
		}
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":isError:Returned highLightLevelFault:"+
			   highLightLevelFault+"|lampOnFault:"+lampOnFault+".");
		return highLightLevelFault|lampOnFault;
	}

	/**
	 * Return the actual light level in the A&G box.
	 * <ul>
	 * <li>Call the connectToController method of connectionParameters.
	 * <li>Call the getInteger method in the Df1Library instance, using the lightLevelPLCAddress PLC address.
	 * <li>Call the closeConnection method of connectionParameters.
	 * </ul>
	 * @return An integer, the actual light level in A/D counts.
	 * @exception Df1LibraryNativeException Thrown if comms to the device fails.
	 * @see #lightLevelPLCAddress
	 * @see #connectionParameters
	 * @see #df1
	 * @see ConnectionParameters#connectToController
	 * @see ConnectionParameters#closeConnection
	 * @see ngat.df1.Df1Library#getInteger
	 */
	public int getLightLevel() throws Df1LibraryNativeException
	{
		int retval;

		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":getLightLevel:Started.");
		try
		{
			connectionParameters.connectToController(df1);
			retval = df1.getInteger(lightLevelPLCAddress);
		}
		finally
		{
			connectionParameters.closeConnection(df1);
		}
		logger.log(LOG_LEVEL_UNIT_BASIC,this.getClass().getName()+":getLightLevel:Returned :"+retval+".");
		return retval;
	}

	/**
	 * Set the log level.Sets the logger, df1 library instance, and each lamps log level.
	 * @param level The log level.
	 * @see #logger
	 * @see #df1
	 * @see #lampList
	 * @see ngat.util.logging.Logger#setLogLevel
	 * @see ngat.df1.Df1Library#setLogFilterLevel
	 * @see ngat.lamp.LTLamp#setLogLevel
	 */
	public void setLogLevel(int level)
	{
		logger.setLogLevel(level);
		df1.setLogFilterLevel(level);
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
//
