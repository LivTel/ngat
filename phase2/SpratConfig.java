// SpratConfig.java
// $HeadURL$
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * SPRAT Spectrograph. This is a low resolution high throughput slit spectrograph, with mechanisms to move the
 * slit and grism out of the way for self acquisition. It therefore has the following mechanisms:
 * <ul>
 * <li><b>Slit</b> To move the slit in and out of the beam.
 * <li><b>Grism</b> To move the grism (dispersing element) in and out of the beam.
 * <li><b>Mirror</b> To move the mirror in and out of the beam. The mirror is used for calibration images 
 *                   i.e. to ensure arc and tungsten lamp light falls on the CCD.
 * <li><b>Grism Rotation</b> To change the rotation angle of the grism :- 
 *        this is adjustable as it effects throughput significantly and the optimum position is wavelength dependant.
 * </ul>
 * The mirror is only used for ARC and LAMP flats and so it not controllable using the SpecConfig.
 * The normal mode of operation is to move the slit and grism out of the beam for imaging operations (acquisition),
 * and move the slit and grism into the beam for taking science data (spectra). However there may be cases where
 * we want to take test (operations) data with the PhaseII is some intermediate state, hence these options are included
 * in the possible configurations.
 */
public class SpratConfig extends SpecConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	static final long serialVersionUID = 5373766310465511420L;
	public static final int maxDetectorCount = 1;
	/**
	 * Position state - in this case, the mechanism is IN the beam.
	 */
	public final static int POSITION_IN = 1;
	/**
	 * Position state - in this case, the mechanism is OUT of the beam.
	 */
	public final static int POSITION_OUT = 2;

	// Variables.
	/**
	 * Slit position :- whether the slit is IN or OUT of the beam.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	protected int slitPosition;
	/**
	 * Grism position :- whether the grism is IN or OUT of the beam.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	protected int grismPosition;
	/**
	 * Grism rotation position :- there are currently two configured positions :- either 0 or 1.
	 */
	protected int grismRotation;

	// Constructor.
    
	public SpratConfig()
	{
		this("untitled");
	}
    
	public SpratConfig(String name)
	{
		super(name);
		detectors = new SpratDetector[maxDetectorCount];
		detectors[0] = new SpratDetector();
	}
    
	// Accessors.
    
	/** 
	 * Sets the slit position.
	 * @param p The slit position, one of POSITION_IN, POSITION_OUT.
	 * @see #slitPosition
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public void setSlitPosition(int p) 
	{ 
		this.slitPosition = p;
	}
    
	/** 
	 * Returns the slit position.
	 * @return The slit position requested in this config, legal configs have one of POSITION_IN, POSITION_OUT.
	 * @see #slitPosition
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public int getSlitPosition()
	{
		return slitPosition;
	}
    
	/** 
	 * Sets the grism position.
	 * @param p The grism position, one of POSITION_IN, POSITION_OUT.
	 * @see #grismPosition
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public void setGrismPosition(int p) 
	{ 
		this.grismPosition = p;
	}
    
	/** 
	 * Returns the grism position.
	 * @return The grism position requested in this config, legal configs have one of POSITION_IN, POSITION_OUT.
	 * @see #grismPosition
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public int getGrismPosition()
	{
		return grismPosition;
	}
    
	/** 
	 * Sets the grism rotation.
	 * @param r The grism rotation, one of: 0|1.
	 * @see #grismRotation
	 */
	public void setGrismRotation(int r) 
	{ 
		this.grismRotation = r;
	}
    
	/** 
	 * Returns the grism rotation position.
	 * @return The grism rotation position requested in this config, legal configs have one of: 0|1.
	 * @see #grismRotation
	 */
	public int getGrismRotation()
	{
		return grismRotation;
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
		System.err.println("Checking SpratConfig with another one: "+
				   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
	
		SpratConfig cother = (SpratConfig)other;

		if(slitPosition != cother.getSlitPosition())
			return false;
		if(grismPosition != cother.getGrismPosition())
			return false;
		if(grismRotation != cother.getGrismRotation())
			return false;
		
		if(! detectors[0].equals(cother.getDetector(0)))
			return false;
		return true;
	}

	/** 
	 * Clone Constructor.
	 */
	public NPDBObject copy() 
	{
		try
		{
			return (SpratConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).
    
	public String toString() 
	{ 
		return new String("SpratConfig: "+name+
				  " : Slit Position "+slitPositionToString()+
				  ", Grism Position "+grismPositionToString()+
				  ", Grism Rotation "+grismRotation+
				  ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]");
		// diddly windowing
	}

	/**
	 * Print a string version of the config's slit position.
	 * @return A string, one of "in" or "out".
	 * @exception IllegalArgumentException Thrown if the slit position is not a known position number.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 * @see #slitPosition
	 * @see #positionToString
	 */
	public String slitPositionToString() throws IllegalArgumentException
	{
		return positionToString(slitPosition);
	}

	/**
	 * Parse a string into a suitable slit position number, one of POSITION_IN or POSITION_OUT.
	 * @param positionString A String, should be one of "in" or "out" to be successful.
	 * @exception IllegalArgumentException Thrown if positionString is not a suitable string.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 * @see #slitPosition
	 * @see #parsePosition
	 */
	public void parseSlitPosition(String positionString) throws IllegalArgumentException
	{
		slitPosition = parsePosition(positionString);
	}

	/**
	 * Print a string version of the config's grism position.
	 * @return A string, one of "in" or "out".
	 * @exception IllegalArgumentException Thrown if the grism position is not a known position number.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 * @see #grismPosition
	 * @see #positionToString
	 */
	public String grismPositionToString() throws IllegalArgumentException
	{
		return positionToString(grismPosition);
	}

	/**
	 * Parse a string into a suitable grism position number, one of POSITION_IN or POSITION_OUT.
	 * @param positionString A String, should be one of "in" or "out" to be successful.
	 * @exception IllegalArgumentException Thrown if positionString is not a suitable string.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 * @see #grismPosition
	 * @see #parsePosition
	 */
	public void parseGrismPosition(String positionString) throws IllegalArgumentException
	{
		grismPosition = parsePosition(positionString);
	}

	/**
	 * Print a string version of a position.
	 * @return A string, one of "in" or "out".
	 * @exception IllegalArgumentException Thrown if the position is not a known position number.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public static String positionToString(int position) throws IllegalArgumentException
	{
		if(position == POSITION_IN)
			return "in";
		else if(position == POSITION_OUT)
			return "out";
		else
			throw new IllegalArgumentException("ngat.phase2.SpecConfig:positionToString:"+
							   "Illegal position number:"+position);

	}

	/**
	 * Parse a string into a suitable position number.
	 * @param positionString A String, should be one of "in" or "out" to be successful.
	 * @return The position number corresponding to the positionString, one of POSITION_IN or POSITION_OUT
	 * @exception IllegalArgumentException Thrown if positionString is not a suitable string.
	 * @see #POSITION_IN
	 * @see #POSITION_OUT
	 */
	public static int parsePosition(String positionString) throws IllegalArgumentException
	{
		int position;

		if(positionString.equals("in"))
			position = POSITION_IN;
		else if(positionString.equals("out"))
			position = POSITION_OUT;
		else
			throw new IllegalArgumentException("ngat.phase2.SpecConfig:parsePosition:"+
							   "Illegal position string:"+positionString);
		return position;
	}

} // end class def [SpratConfig].
