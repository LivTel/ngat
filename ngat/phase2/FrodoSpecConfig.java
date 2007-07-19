// FrodoSpecConfig.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/FrodoSpecConfig.java,v 1.2 2007-07-19 18:38:40 cjm Exp $
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * FrodoSpec Spectrograph. This is a dual beam fibre fed spectrograph. It has rwo resolution options
 * (per arm). An instance of this class is designed to configure one arm only.
 */
public class FrodoSpecConfig extends SpecConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FrodoSpecConfig.java,v 1.2 2007-07-19 18:38:40 cjm Exp $"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = 719699051007274564L;

	public static final int maxDetectorCount = 1;
	/**
	 * Arm constant denoting no arm.
	 * #arm
	 */
	public static final int NO_ARM = 0;
	/**
	 * Arm constant denoting red arm.
	 * #arm
	 */
	public static final int RED_ARM = 1;
	/**
	 * Arm constant denoting blue arm.
	 * #arm
	 */
	public static final int BLUE_ARM = 2;
	/**
	 * Grating position constant denoting unknown resolution.
	 * #resolution
	 */
	public static final int RESOLUTION_UNKNOWN = 0;
	/**
	 * Grating position constant denoting Low resolution.
	 * #resolution
	 */
	public static final int RESOLUTION_LOW = 1;
	/**
	 * Grating position constant denoting High resolution.
	 * #resolution
	 */
	public static final int RESOLUTION_HIGH = 2;

	// Variables.
	/**
	 * Which arm this configis for, the red arm or the blue arm.
	 * @see #RED_ARM
	 * @see #BLUE_ARM
	 */
	protected int arm;
	/** 
	 * Resolution. 
	 */
	protected int resolution;
    
	// Constructor.
    
	public FrodoSpecConfig()
	{
		this("untitled");
	}
    
	public FrodoSpecConfig(String name)
	{
		super(name);
		detectors = new FrodoSpecDetector[maxDetectorCount];
		detectors[0] = new FrodoSpecDetector();
	}
    
	// Accessors.
    
	/** 
	 * Sets the arm.
	 * @param a The arm, one of RED_ARM, BLUE_ARM.
	 * @see #arm
	 * @see #RED_ARM
	 * @see #BLUE_ARM
	 */
	public void setArm(int a) 
	{ 
		this.arm = a; 
	}
    
	/** 
	 * Returns the arm.
	 * @see #arm
	 */
	public int getArm()
	{
		return arm;
	}
    
	/** 
	 * Sets the resolution.
	 * @param a The resolution, one of RESOLUTION_LOW, RESOLUTION_HIGH.
	 * @see #resolution
	 * @see #RESOLUTION_LOW
	 * @see #RESOLUTION_HIGH
	 */
	public void setResolution(int r) 
	{ 
		this.resolution = r; 
	}
    
	/** 
	 * Returns the resolution.
	 * @see #resolution
	 */
	public int getResolution()
	{
		return resolution;
	}
    
	public int getMaxDetectorCount()
	{
		return maxDetectorCount; 
	}

	/** 
	 * Compares with another InstConfig to see if they are effectively the same.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
		System.err.println("Checking FrodoSpecConfig with another one: "+
				   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
	
		FrodoSpecConfig cother = (FrodoSpecConfig)other;

		if (arm != cother.getArm())
			return false;
		if (resolution != cother.getResolution())
			return false;
	
		if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
			return false;
		if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
			return false;
		// Remember to look at CalBef and CalAft.
		return true;
	}

	/** 
	 * Clone Constructor.
	 */
	public NPDBObject copy() 
	{
		try
		{
			return (FrodoSpecConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).
    
	public String toString() { return "FrodoSpecConfig: "+name+
					   " : Arm "+armToString()+", Resolution "+resolutionToString()+
					   ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
	}

	/**
	 * Print a string version of the arm.
	 * @return A string, one of "unknown","red","blue".
	 * @exception IllegalArgumentException Thrown if arm is not a known arm number.
	 * @see #arm
	 * @see #NO_ARM
	 * @see #RED_ARM
	 * @see #BLUE_ARM
	 */
	public String armToString() throws IllegalArgumentException
	{
		if(arm == NO_ARM)
			return "unknown";
		else if(arm == RED_ARM)
			return "red";
		else if(arm == BLUE_ARM)
			return "blue";
		else
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":armToString:Illegal arm number:"+arm);
	}

	/**
	 * Parse a string into a suitable arm number.
	 * @param armString a String, should be one of "unknown","red","blue" to be successful.
	 * @exception IllegalArgumentException Thrown if armString is not a suitable string.
	 * @see #arm
	 * @see #NO_ARM
	 * @see #RED_ARM
	 * @see #BLUE_ARM
	 */
	public void parseArm(String armString) throws IllegalArgumentException
	{
		if(armString.equals("unknown"))
			arm = NO_ARM;
		else if(armString.equals("red"))
			arm = RED_ARM;
		else if(armString.equals("blue"))
			arm = BLUE_ARM;
		else
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":parseArm:Illegal arm string:"+armString);
}

	/**
	 * Print a string version of the resolution.
	 * @return A string, one of "RESOLUTION_UNKNOWN","RESOLUTION_LOW","RESOLUTION_HIGH".
	 * @exception IllegalArgumentException Thrown if resolution is not a known resolution number.
	 * @see #resolution
	 * @see #RESOLUTION_UNKNOWN
	 * @see #RESOLUTION_LOW
	 * @see #RESOLUTION_HIGH
	 */
	public String resolutionToString() throws IllegalArgumentException
	{
		if(resolution == RESOLUTION_UNKNOWN)
			return "RESOLUTION_UNKNOWN";
		else if(resolution == RESOLUTION_LOW)
			return "RESOLUTION_LOW";
		else if(resolution == RESOLUTION_HIGH)
			return "RESOLUTION_HIGH";
		else
			throw new IllegalArgumentException(this.getClass().getName()+
							  ":resolutionToString:Illegal resolution number:"+resolution);
	}

	/**
	 * Parse a string into a suitable resolution number.
	 * @param resolutionString A String, should be one of "RESOLUTION_UNKNOWN","RESOLUTION_LOW",
	 *                 "RESOLUTION_HIGH" to be successful.
	 * @exception IllegalArgumentException Thrown if resolutionString is not a suitable string.
	 * @see #resolution
	 * @see #RESOLUTION_UNKNOWN
	 * @see #RESOLUTION_LOW
	 * @see #RESOLUTION_HIGH
	 */
	public void parseResolution(String resolutionString) throws IllegalArgumentException
	{
		if(resolutionString.equals("RESOLUTION_UNKNOWN"))
			resolution = RESOLUTION_UNKNOWN;
		else if(resolutionString.equals("RESOLUTION_LOW"))
			resolution = RESOLUTION_LOW;
		else if(resolutionString.equals("RESOLUTION_HIGH"))
			resolution = RESOLUTION_HIGH;
		else
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":parseResolution:Illegal resolution string:"+
							   resolutionString);
	}
} // end class def [FrodoSpecConfig].

//
// $Log: not supported by cvs2svn $
// Revision 1.1  2007/05/22 09:07:31  cjm
// Initial revision
//
//
