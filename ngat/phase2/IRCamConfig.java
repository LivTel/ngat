package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class IRCamConfig extends InstrumentConfig implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -1885321170923597100L;
     
    /** Default (blank) filter.*/
    public static final String BLANK = "blank";

    public static final int maxDetectorCount = 1;

    // Variables.

    /** The single filterwheel. */
    protected String filterWheel;
       
    // Constructor.
    
    public IRCamConfig() {this("untitled");}
    
    public IRCamConfig(String name) {
	super(name);
	detectors = new IRCamDetector[1];
	detectors[0] = new IRCamDetector();
    }

    // Accessors.
    
    /** Sets the filter name.*/
    public void setFilterWheel(String in) {this.filterWheel = in;}   
    
    /** Returns the filter name. */
    public String getFilterWheel() {return filterWheel;}
           
    public int getMaxDetectorCount() {return maxDetectorCount; }

    // Clone Constructor.
    public NPDBObject copy() {
	try {
	    return (IRCamConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
      
    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	//	System.err.println("Checking IRC with another one: "+
	//	   this.toString()+" with "+other.toString());
	if (! super.sameAs(other))
	    return false;
	// Ok we know they are the same class now..
	IRCamConfig cother = (IRCamConfig)other;	
	if (! filterWheel.equals(cother.getFilterWheel()))
	    return false;	
	if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
	    return false;
	if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
	    return false;
	// Remember to look at CalBef and CalAft and windows..
	return true;
    }
    
    /** Create a Default CCDConfig.*/
    public static IRCamConfig getDefault() {
	IRCamConfig DEFAULT = new IRCamConfig("DEFAULT");
	DEFAULT.setDescription("Default IRCamConfig");
	DEFAULT.setFilterWheel(BLANK);	
	IRCamDetector detector = new IRCamDetector();
	detector.setXBin(1);
	detector.setYBin(1);
	detector.clearAllWindows();
	DEFAULT.setDetector(0, detector); 	
	return DEFAULT;
    }
    
    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<irCamConfig name = '"+name+"'>");
	out.println(tab(level+1)+"<filterWheel>"+filterWheel+"</filterWheel>");
	detectors[0].writeXml(out,level+1);
	out.println(tab(level)+"</irCamConfig>");
    } // end (write).
    
    public String toString() { return "IRCamConfig: "+instrumentName+"/"+
				   name+
				   " : Filter "+filterWheel+				 
				   ", Bin: ["+detectors[0].getXBin()+" x "+detectors[0].getYBin()+"]";
    }




} // end class def [IRCamConfig].
