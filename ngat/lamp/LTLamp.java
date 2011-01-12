// LTLamp.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LTLamp.java,v 1.5 2011-01-12 14:16:33 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class holds information about an individual lamp as part of a lamp unit.
 * @author Chris Mottram
 * @version $Revision: 1.5 $
 */
public class LTLamp implements LampInterface
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: LTLamp.java,v 1.5 2011-01-12 14:16:33 cjm Exp $");
	/**
	 * Basic lamp log level.
	 * @see ngat.util.logging.Logging#VERBOSITY_INTERMEDIATE
	 */
	public final static int LOG_LEVEL_LAMP_BASIC = Logging.VERBOSITY_INTERMEDIATE;
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
	protected PLCConnection connection = null;

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
	 * Set the PLC connection of this lamp.
	 * @param o The PLCConnection instance corresponding to the PLC.
	 * @see #connection
	 * @see PLCConnection
	 */
	public void setConnection(PLCConnection o)
	{
		connection = o;
	}

	/**
	 * Load the lamp's internal data from the specified properties.
	 * @param properties The properties to load from.
	 * @exception NullPointerException Thrown if a property key cannot be found.
	 * @exception NGATPropertyException Thrown if an integer property value cannot be parsed.
	 * @see #onOffPLCAddress
	 * @see #threshold
	 * @see #lightLevelOnPLCAddress
	 * @see PLCValueSetter#loadConfig
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
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #threshold
	 * @see #turnLampOff
	 */
	public void init() throws EIPNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":init:Started.");
		threshold.setValue(connection);
		turnLampOff();
		logger.log(LOG_LEVEL_LAMP_BASIC,this.getClass().getName()+":init:Finished.");
	}

	/**
	 * Turn the lamp on.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #turnLampOn(String,String)
	 */
	public void turnLampOn() throws EIPNativeException
	{
		turnLampOn(this.getClass().getName(),null);
	}

	/**
	 * Turn the lamp on.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #onOffPLCAddress
	 * @see #turnLampOff
	 * @see PLCConnection#setBoolean
	 */
	public void turnLampOn(String clazz,String source) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":turnLampOn:"+name+
			   ":Started.");
		connection.setBoolean(clazz,source,onOffPLCAddress,true);
		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":turnLampOn:"+name+
			   ":Finished.");
	}

	/**
	 * Turn the lamp off.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #turnLampOff(String,String)
	 */
	public void turnLampOff() throws EIPNativeException
	{
		turnLampOff(this.getClass().getName(),null);
	}

	/**
	 * Turn the lamp off.
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #onOffPLCAddress
	 * @see #turnLampOn
	 * @see PLCConnection#setBoolean
	 */
	public void turnLampOff(String clazz,String source) throws EIPNativeException
	{
		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":turnLampOff:"+name+
			   ":Started.");
		connection.setBoolean(clazz,source,onOffPLCAddress,false);
		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":turnLampOff:"+name+
			   ":Finished.");
	}

	/**
	 * Return whether the lamp is on not.
	 * @return true if the lamp is on, false if it is not (according to the PLC, which uses the light level
	 *         in the A&G box).
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #isLampOn(String,String)
	 */
	public boolean isLampOn() throws EIPNativeException
	{
		return isLampOn(this.getClass().getName(),null);
	}

	/**
	 * Return whether the lamp is on not.
	 * @return true if the lamp is on, false if it is not (according to the PLC, which uses the light level
	 *         in the A&G box).
	 * @exception EIPNativeException Thrown if comms to the PLC fails.
	 * @see #connection
	 * @see #lightLevelOnPLCAddress
	 * @see #turnLampOn
	 * @see PLCConnection#getBoolean
	 */
	public boolean isLampOn(String clazz,String source) throws EIPNativeException
	{
		boolean retval;

		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":isLampOn:"+name+":Started.");
		retval = connection.getBoolean(clazz,source,lightLevelOnPLCAddress);
		logger.log(LOG_LEVEL_LAMP_BASIC,clazz,source,this.getClass().getName()+":isLampOn:"+name+":Finished.");
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
}
//
// $Log: not supported by cvs2svn $
// Revision 1.4  2009/02/05 14:33:32  cjm
// Changed logging levels to GLS verbosities.
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
