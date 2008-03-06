// LTLamp.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LTLamp.java,v 1.1 2008-03-06 10:47:39 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.df1.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class holds information about an individual lamp as part of a lamp unit.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class LTLamp implements LampInterface
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: LTLamp.java,v 1.1 2008-03-06 10:47:39 cjm Exp $");
	/**
	 * Basic lamp log level.
	 */
	public final static int LOG_LEVEL_LAMP_BASIC = 1;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;
	/**
	 * Name of the lamp.
	 */
	protected String name = null;
	/**
	 * The PLC address of the bit used to turn the lamp on and off.
	 */
	protected String onOffPLCAddress = null;
	/**
	 * The threshold value, set during unit initialisation.
	 */
	protected PLCValueSetter threshold = null;
	/**
	 * The PLC address of the bit in the PLC that is set when the light level in the A&G box
	 * coresponds to this light being on.
	 */
	protected String lightLevelOnPLCAddress = null;
	/**
	 * How to connect to the controller.
	 */
	protected ConnectionParameters connectionParameters = null;
	/**
	 * The controller object, an instance of Df1Library.
	 */
	protected Df1Library df1 = null;

	/**
	 * Constructor. Initialise the logger.
	 * @see #logger
	 */
	public LTLamp()
	{
		super();
		logger = LogManager.getLogger(this);
	}

	/**
	 * Set the lamps name.
	 * @param s The name
	 * @see #name
	 */
	public void setName(String s)
	{
		name = s;
	}

	/**
	 * Get the lamps name.
	 * @return The lamps name.
	 * @see #name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the controller of this lamp.
	 * @param o The Df1Library instance corresponding to the PLC that controls this lamp.
	 * @see #df1
	 * @see ngat.df1.Df1Library
	 */
	public void setController(Df1Library o)
	{
		df1 = o;
	}

	/**
	 * Set the connection parameters (how to connect to the PLC).
	 * @param cp The connection parameters.
	 * @see #connectionParameters
	 * @see ConnectionParameters
	 */
	public void setConnectionParameters(ConnectionParameters cp)
	{
		connectionParameters = cp;
	}

	/**
	 * Load the lamp's internal data from the specified properties.
	 * @param properties The properties to load from.
	 * @exception NullPointerException Thrown if a property key cannot be found.
	 * @exception NGATPropertyException Thrown if an integer property value cannot be parsed.
	 * @see #onOffPLCAddress
	 * @see #threshold
	 * @see #lightLevelOnPLCAddress
	 * @see PLCValueSetter#loadCOnfig
	 */
	public void loadConfig(NGATProperties properties) throws NullPointerException, NGATPropertyException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":loadConfig:Started.");
		// onOffPLCAddress
		onOffPLCAddress = properties.getProperty("lamp."+name+".onoff.plc_address");
		if(onOffPLCAddress == null)
		{
			throw new NullPointerException(this.getClass().getName()+
			    ":loadConfig failed:Failed to find On/Off PLC Address in properties using key:"+
						       "lamp."+name+".onoff.plc_address");
		}
		// threshold 
		threshold = new PLCValueSetter();
		threshold.loadConfig(properties,"lamp."+name+".threshold");
		// lightLevelOnPLCAddress
		lightLevelOnPLCAddress = properties.getProperty("lamp."+name+".light.level.on.plc_address");
		if(lightLevelOnPLCAddress == null)
		{
			throw new NullPointerException(this.getClass().getName()+
			    ":loadConfig failed:Failed to find Light Level On PLC Address in properties using key:"+
						       "lamp."+name+".light.level.on.plc_address");
		}
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":loadConfig:Finished.");
	}

	/**
	 * Initialise the threshold and turn the lamp off.
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #df1
	 * @see #connectionParameters
	 * @see #threshold
	 * @see #turnLampOff
	 */
	public void init() throws Df1LibraryNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":init:Started.");
		threshold.setValue(df1,connectionParameters);
		turnLampOff();
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":init:Finished.");
	}

	/**
	 * Turn the lamp on.
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #df1
	 * @see #onOffPLCAddress
	 * @see #turnLampOff
	 * @see #connectToController
	 * @see #closeConnection
	 * @see ngat.df1.Df1Library#setBoolean
	 */
	public void turnLampOn() throws Df1LibraryNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":turnLampOn:"+name+":Started.");
		try
		{
			connectToController();
			df1.setBoolean(onOffPLCAddress,true);
		}
		finally
		{
			closeConnection();
		}
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":turnLampOn:"+name+":Finished.");
	}

	/**
	 * Turn the lamp off.
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #df1
	 * @see #onOffPLCAddress
	 * @see #turnLampOn
	 * @see #connectToController
	 * @see #closeConnection
	 * @see ngat.df1.Df1Library#setBoolean
	 */
	public void turnLampOff() throws Df1LibraryNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":turnLampOff:"+name+":Started.");
		try
		{
			connectToController();
			df1.setBoolean(onOffPLCAddress,false);
		}
		finally
		{
			closeConnection();
		}
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":turnLampOff:"+name+":Finished.");
	}

	/**
	 * Return whether the lamp is on not.
	 * @return true if the lamp is on, false if it is not (according to the PLC, which uses the light level
	 *         in the A&G box).
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #df1
	 * @see #lightLevelOnPLCAddress
	 * @see #turnLampOn
	 * @see #connectToController
	 * @see #closeConnection
	 * @see ngat.df1.Df1Library#getBoolean
	 */
	public boolean isLampOn() throws Df1LibraryNativeException
	{
		boolean retval;

		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":isLampOn:"+name+":Started.");
		try
		{
			connectToController();
			retval = df1.getBoolean(lightLevelOnPLCAddress);
		}
		finally
		{
			closeConnection();
		}
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":isLampOn:"+name+":Finished.");
		return retval;
	}
	/**
	 * Set the log level.Sets the logger log level.
	 * @param level The log level.
	 * @see #logger
	 * @see ngat.util.logging.Logger#setLogLevel
	 */
	public void setLogLevel(int level)
	{
		logger.setLogLevel(level);
	}

	// protected methods
	/**
	 * Connect to the controller (PLC).
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #connectionParameters
	 * @see #df1
	 * @see ConnectionParameters#connectToController
	 */
	protected void connectToController() throws Df1LibraryNativeException
	{
		connectionParameters.connectToController(df1);
	}

	/**
	 * Close the connection to the controller (PLC).
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC fails.
	 * @see #connectionParameters
	 * @see #df1
	 * @see ConnectionParameters#closeConnection
	 */
	protected void closeConnection() throws Df1LibraryNativeException
	{
		connectionParameters.closeConnection(df1);
	}
}
//
// $Log: not supported by cvs2svn $
//
