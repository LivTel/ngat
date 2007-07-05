package ngat.phase2;

import java.io.*;

public class GenericCCDConfig extends InstrumentConfig implements Serializable 
{
    public static final int maxDetectorCount = 1;
    
    public static final int maxWheelCount = 10;
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 759627829525815554L;
    
    /** Default clear filter.*/
    public static final String CLEAR = "clear";

    /** Identity of filter in each wheel. */
    protected String filterName[];
    
    /** Number of wheels in the filter wheel. */
    protected int wheelCount = 0;

    /** Default constructor. */
    public GenericCCDConfig() 
    {
    	this("untitled");
    }
    
    /** Make a CCD-Config, Adds 1 CCD-Detector only.*/
    public GenericCCDConfig(String name) 
    {
		super(name);
		detectors = new CCDDetector[maxDetectorCount];
		detectors[0] = new CCDDetector();
		filterName = new String[maxWheelCount];
    }
    
    /**
     * Sets the number of filter wheels in the current configuration.
     * @param numWheels The number of filter wheels in the current configuration.
     * @throws IllegalArgumentException if the number of wheels is out of range.
     */
    public void setWheelCount(int numWheels) throws IllegalArgumentException
    {
    	if (numWheels < 0 || numWheels > maxWheelCount)
    		throw new IllegalArgumentException("CCD: " + name + " Wheel count: " + numWheels
    				+ " out of range [0, " + (maxWheelCount) + "]");
    	wheelCount = numWheels;
    }

    /** 
     * Sets the String identity of the filter in the specified wheel.
     * @param wheel The wheel to set the filter for.
     * @param in The identity string of the filter to set.
     */
    public void setFilterName(int wheel, String in) 
    {
    	if (wheel < 0 || wheel >= wheelCount)
    		throw new IllegalArgumentException("CCD: " + name + " Wheel index " + wheel
    				+ " out of range [0, " + (wheelCount-1) + "]");
    	this.filterName[wheel] = in;
    }
    
    /** 
     * Get the String identity of the filter in the specified wheel.
     * @param wheel The wheel to get the filter identity of.
     * @return The String identity of the current filter in the specified wheel.
     * @throws IllegalArgumentException If the wheel number is out of range.
     */
    public String getFilterName(int wheel) throws IllegalArgumentException
    {
    	if (wheel < 0 || wheel >= wheelCount)
    		throw new IllegalArgumentException("CCD: " + name + " Wheel index " + wheel
    				+ " out of range [0, " + (wheelCount-1) + "]");
    	return this.filterName[wheel];
    }
    
    /**
     * Get the detector count for the current configuration.
     * @return The detector count for the current configuration.
     */
    public int getMaxDetectorCount() 
    {
    	return maxDetectorCount; 
    }
    
    /**
     * Get the number of filter wheels in the current configuration.
     * @return The number of filter wheels supported by this configuration.
     */
    public int getWheelCount()
    {
    	return wheelCount;
    }
        
    /**
     * Compares with another InstConfig to see if they are effectively the same.
     * @param other An InstrumentConfig to compare the context object with.
     * @return true if the config objects are equivalent, false if not.
     */
    public boolean sameAs(InstrumentConfig other) 
    {
		System.err.println("Checking CCDC with another one: "+
				   this.toString()+" with "+other.toString());
		if (!super.sameAs(other))
		    return false;
		// Ok we know they are the same class now..
		GenericCCDConfig cother = (GenericCCDConfig)other;

		if (wheelCount != cother.getWheelCount())
			return false;
		for (int wheel = 0; wheel < wheelCount; wheel++)
		{
			if (!filterName[wheel].equals(cother.getFilterName(wheel)))
			    return false;
		}
		if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
		    return false;
		if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
		    return false;
		// Remember to look at CalBef and CalAft and windows..
		return true;
    }
    
    /** 
     * Create a Default GenericCCDConfig.
     * @return Reference to a new GenericCCDConfig.
     */
    public static GenericCCDConfig getDefault() 
    {
		GenericCCDConfig DEFAULT = new GenericCCDConfig("DEFAULT");
		DEFAULT.setDescription("Default GenericCCDConfig");
		DEFAULT.setWheelCount(2);
		DEFAULT.setFilterName(0, CLEAR);
		DEFAULT.setFilterName(1, CLEAR);
		CCDDetector detector = new CCDDetector();
		detector.setXBin(1);
		detector.setYBin(1);
		detector.clearAllWindows();
		DEFAULT.setDetector(0, detector); 	
		return DEFAULT;
    }
    
    /**
     * Clone the object.
     * @return Reference to the clone, or null if cloning is not supported.
     */
    public NPDBObject copy() 
    {
		try 
		{
		    return (GenericCCDConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
    }

    /**
     * Write formatted text output XML.
     * @param out The PrintStream to write to.
     * @param level The tab level to use for this output.
     */
    public void writeXml(PrintStream out, int level) 
    {
		out.println(tab(level)+"<ccdConfig name = '"+name+"'>");
		for (int wheel = 0; wheel < wheelCount; wheel++)
			out.println(tab(level+1)+"<filterName"+wheel+">"+filterName[wheel]+"</filterName"+wheel+">");
		detectors[0].writeXml(out,level+1);
		out.println(tab(level)+"</ccdConfig>");
    }

    /**
     * Get a String description of this object.
     * @return A String description of this object.
     */
    public String toString() 
    { 
    	String desc = "GenericCCDConfig: "+name;
    	for (int wheel = 0; wheel < wheelCount; wheel++)
    		desc += ", Wheel["+wheel+"]: "+filterName[wheel];
		desc += ", Bin: ["+detectors[0].getXBin()+" x "+detectors[0].getYBin()+"]";
    	return desc;
    }
}
