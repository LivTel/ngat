// MOPTOPPolarimeterConfig.java
// $Id$
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Instrument configuration for the MOPTOP Polarimeter. This consists of:
 * <ul>
 * <li>A Starlight Express 5 position filter wheel containing filters.
 * <li>A PI rotator containing a half-wave plate, we can spin this "slow" or "fast".
 * <li>Two "Zyla 4.2 PLUS" cameras (with Fairchild CMOS detectors), which can be binned squarely from bin 1 to 4.
 * </ul>
 * @version $Revision$
 * @author cjm
 * @see InstrumentConfig
 */
public class MOPTOPPolarimeterConfig extends InstrumentConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */    
	//private static final long serialVersionUID = ;
	/**
	 * Rotor speed denoting an unknown rotor speed.
	 */
	public final static int ROTOR_SPEED_UNKNOWN = 0;
	/**
	 * Rotor speed denoting a slow rotor speed.
	 */
	public final static int ROTOR_SPEED_SLOW = 1;
	/**
	 * Rotor speed denoting a fast rotor speed.
	 */
	public final static int ROTOR_SPEED_FAST = 2;
	
	// Variables.
	/**
	 * Number of filter wheels: 1.
	 */
        public static final int maxWheelCount = 1;
	/**
	 * Maximum number of detectors.
	 */
        public static final int maxDetectorCount = 2;
	/** 
	 * Actual number of filter wheels: 1
	 */
	protected int wheelCount = maxWheelCount;
	/** 
	 * Name of the selected filter in the filter wheel. 
	 */
	protected String filterName;
	/**
	 * The speed to move the PI rotator.
	 * @see #ROTOR_SPEED_SLOW
	 * @see #ROTOR_SPEED_FAST
	 */
	protected int rotorSpeed = ROTOR_SPEED_SLOW;
	
	// Constructor.
	/**
	 * Default constructor.
	 */
	public MOPTOPPolarimeterConfig()
	{
		this("untitled");
	}
    
	/**
	 * Constructor. Constructs the detector objects in the detector list.
	 * @see MOPTOPPolarimeterDetector
	 * @see #maxDetectorCount
	 * @see #wheelCount
	 * @see #maxWheelCount
	 */
	public MOPTOPPolarimeterConfig(String name)
	{
		super(name);
		detectors = new MOPTOPPolarimeterDetector[maxDetectorCount];
		for(int i = 0; i < maxDetectorCount; i++)
			detectors[i] = new MOPTOPPolarimeterDetector();
		wheelCount = maxWheelCount;
	}
    
	// Accessors.
	/** 
	 * Sets the String identity of the filter.
	 * @param in The identity string of the filter to set.
	 */
	public void setFilterName(String in) 
	{
		this.filterName = in;
	}
    
	/** 
	 * Get the String identity of the filter in the filter wheel.
	 * @return The String identity of the current filter in the filter wheel.
	 */
	public String getFilterName()
	{
		return this.filterName;
	}

	/** 
	 * Sets the rotor speed.
	 * @param rs The rotor speed, one of ROTOR_SPEED_SLOW, ROTOR_SPEED_FAST.
	 * @see #rotorSpeed
	 * @see #ROTOR_SPEED_SLOW
	 * @see #ROTOR_SPEED_FAST
	 */
	public void setRotorSpeed(int rs)
	{ 
		this.rotorSpeed = rs;
	}
    
	/** 
	 * Returns the rotor speed.
	 * @return The srotor speed requested in this config, legal configs have one of:
	 *         ROTOR_SPEED_SLOW, ROTOR_SPEED_FAST.
	 * @see #rotorSpeed
	 * @see #ROTOR_SPEED_SLOW
	 * @see #ROTOR_SPEED_FAST
	 */
	public int getRotorSpeed()
	{
		return rotorSpeed;
	}
    
	/**
	 * Return the number of detectors.
	 * @return An integer, the number of detectors
	 * @see #maxDetectorCount
	 */
	public int getMaxDetectorCount()
	{	
		return maxDetectorCount; 
	}

	/** 
	 * Compares this instance of MOPTOPPolarimeterConfig with another InstConfig to see if they are the same.
	 * @param other The comparison InstrumentConfig.
	 * @return The method returns trus if the instrument configurations are the same, and false otherwise.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
		if (! super.sameAs(other))
			return false;
		MOPTOPPolarimeterConfig cother = (MOPTOPPolarimeterConfig)other;
		// filter wheel
		if(filterName.equals(cother.getFilterName()) == false)
			return false;		
		// rotor speed
		if(rotorSpeed != cother.getRotorSpeed())
			return false;
		// detectors
		if(maxDetectorCount != cother.getMaxDetectorCount())
			return false;
		for(int i = 0; i < maxDetectorCount; i++)
		{
			if (detectors[i].getXBin() != cother.getDetector(i).getXBin())
				return false;
			if (detectors[i].getYBin() != cother.getDetector(i).getYBin())
				return false;
		}
		return true;
	}
	
	// Clone Constructor.
	public NPDBObject copy()
	{
		try
		{
			return (MOPTOPPolarimeterConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).

	
	// Formatted Text Output.
	public void writeXml(PrintStream out, int level)
	{
		out.println(tab(level)+"<MOPTOPPolarimeterConfig name = '"+name+"'>");
		out.println(tab(level+1)+"<filterName>"+filterName+"</filterName>");
		out.println(tab(level+1)+"<rotorSpeed>"+rotorSpeedToString()+"</rotorSpeed>");
		for(int i = 0; i < maxDetectorCount; i++)
		{
			detectors[i].writeXml(out,level+1);
		}
		out.println(tab(level)+"</MOPTOPPolarimeterConfig>");
	} // end (write).
    

	public String toString()
	{
		StringBuffer sb = null;
		sb = new StringBuffer();
		sb.append("MOPTOPPolarimeterConfig: ");
		sb.append(instrumentName);
		sb.append(" name = "+name);
		sb.append(" filter = "+filterName);
		sb.append(" rotorspeed = "+rotorSpeedToString());
		for(int i = 0; i < maxDetectorCount; i++)
		{
			sb.append("Detector "+i);
			sb.append(" xbin = "+detectors[i].getXBin());
			sb.append(" ybin = "+detectors[i].getYBin());
		}
		return sb.toString();
	}

	/**
	 * Print a string version of the config's rotor speed.
	 * @return A string, one of "slow" or "fast".
	 * @exception IllegalArgumentException Thrown if the rotor speed is not a legal number.
	 * @see #ROTOR_SPEED_SLOW
	 * @see #ROTOR_SPEED_FAST
	 * @see #rotorSpeed
	 */
	public String rotorSpeedToString() throws IllegalArgumentException
	{
		if(rotorSpeed == ROTOR_SPEED_SLOW)
			return "slow";
		else if(rotorSpeed == ROTOR_SPEED_FAST)
			return "fast";
		else
			throw new IllegalArgumentException("ngat.phase2.MOPTOPPolarimeterConfig:rotorSpeedToString:"+
							   "Illegal number:"+rotorSpeed);
	}

	/**
	 * Parse a string into a suitable rotot speed, one of ROTOR_SPEED_SLOW or ROTOR_SPEED_FAST.
	 * @param rotorSpeedString A String, should be one of "slow" or "fast" to be successful.
	 * @exception IllegalArgumentException Thrown if rotorSpeedString is not a suitable string.
	 * @see #ROTOR_SPEED_SLOW
	 * @see #ROTOR_SPEED_FAST
	 * @see #rotorSpeed
	 */
	public void parseRotorSpeed(String rotorSpeedString) throws IllegalArgumentException
	{
		if(rotorSpeedString.equals("slow"))
			rotorSpeed = ROTOR_SPEED_SLOW;
		else if(rotorSpeedString.equals("fast"))
			rotorSpeed = ROTOR_SPEED_FAST;
		else
			throw new IllegalArgumentException("ngat.phase2.MOPTOPPolarimeterConfig:parseRotorSpeed:"+
							   "Illegal width string:"+rotorSpeedString);
	}

} 
