package ngat.instrument;

import ngat.phase2.*;

import java.io.*;
import java.util.*;

/** This class represents the properties of an optical filter attached to an instrument.
 *SId$
 */
public class Filter {

    /** The physical name of the Filter - as per Filter database.*/
    protected String name;

    /** The type of this filter i.e. its logical name.
     * Note: That Clear and Neutral density filters are identified by being of types:
     * <dl>   
     *  <dt>neutralXXX 
     *  <dd> For Neutral density filters, plus an extinction value (mags).
     *  <dt>clearXXX 
     *  <dd> For Clear filters.
     * </dl>
     **/
    protected String type;

    /** A textual description of the filter.*/
    protected String description;

    /** Centre wavelength (angstrom).*/
    protected double centralWavelength;

    /** Filter bandwidth (angstrom).*/
    protected double bandwidth;

    /** Integrated/averaged sky brightness across wavelength passband with moon.*/
    protected double skyBright;

    /** Integrated/averaged sky brightness across wavelength bandpasss with no moon.*/
    protected double skyDark;

    /** Calibration zero point.*/
    protected double zeroCalibration;
    
    /** Efficiency of filter averaged across bandwidth.*/
    protected double qFilter;
    
    /** Efficiency of CCD averaged across bandwidth. (Note: This should probably be an
     * instrument parameter !.)*/
    protected double qCCD;

    /** Optical thickness of the filter (mm).*/
    protected double opticalThickness;

    /** Physical thickness of the filter (mm).*/
    protected double physicalThickness;

    /** Extinction factor for neutral density filters.*/
    protected double extinction;

    /** Create a Filter with the specified (physical) name.
     * @param name The physical name of the Filter as per Filter database.*/
    public Filter(String name) { 
	this.name = name;
    }
    
    /** Configure the Filter from a java.util.Properties.
     * @param props The java.util.Properties containing the Filter description.
     * @exception IllegalArgumentException If any of the properties cannot be
     * parsed properly or does not exist.*/
    public void configure(Properties props) throws IllegalArgumentException {
	String propstring = "filter."+name+".description";
	description = (String)props.get(propstring); 
	if (description == null)  
	    throw new IllegalArgumentException("Filter configuration parameter: ("+propstring+"): ");

	System.err.println("Filter:: Configuring id: "+name+" Desc="+description);
	try {
	    propstring = "filter."+name+".center";
	    centralWavelength = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".width";
	    bandwidth         = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".skybright";
	    skyBright         = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".skydark";
	    skyDark           = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".zero";
	    zeroCalibration   = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".qfilter";
	    qFilter           = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".qccd";
	    qCCD              = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".optical_thickness";
	    opticalThickness  = Double.parseDouble((String)props.get(propstring));
	    propstring = "filter."+name+".physical_thickness";
	    physicalThickness = Double.parseDouble((String)props.get(propstring));
	    if (isNeutral()) {
		propstring = "filter."+name+".extinction";
		extinction        = Double.parseDouble((String)props.get(propstring));
	    }
	} catch (NumberFormatException nfe) {
	    throw new IllegalArgumentException("Filter configuration parameter: ("+propstring+") : "+nfe);
	} catch (NullPointerException npe) {
	    throw new IllegalArgumentException("Filter configuration parameter: ("+propstring+") : "+npe);
	}	
    }

    /** Returns a String description of the Filter.
     * @return A String description of the Filter.
     */
    public String toString() {
	return 
	    "Filter:         "+name+
	    "\nType:           "+type+(isClear() ? "(CLEAR)" : isNeutral() ? "(NEUTRAL-X1/"+extinction+")" : "")+
	    "\nDescription:    "+description+
	    "\nCentral wave:   "+centralWavelength+" angstrom."+
	    "\nBandwidth:      "+bandwidth+" angstrom."+
	    "\nSky (bright):   "+skyBright+
	    "\nSky (dark):     "+skyDark+
	    "\nCalibration:    "+zeroCalibration+
	    "\nQ (filter):     "+qFilter+
	    "\nQ (detector):   "+qCCD+
	    "\nOptical thick:  "+opticalThickness+" mm."+
	    "\nPhysical thick: "+physicalThickness+" mm.";
    }
    
    public String getName() { return name; }
    
    public void setName(String name) { this.name = name; } 
    
    public String getType() { return type; }
    
    public void setType(String type) { this.type = type; } 
    
    public String getDescription() { return description; }
    
    public void setDescription(String description) { this.description = description; }
    
    public double getCentralWavelength() { return centralWavelength; }
    
    public void setCentralWavelength(double centralWavelength) { this.centralWavelength = centralWavelength; } 
    
    public double getBandwidth() { return bandwidth; }
    
    public void setBandwidth(double bandwidth) { this.bandwidth = bandwidth; }
    
    public double getSkyBright() { return skyBright ; }

    public void setSkyBright(double skyBright) { this.skyBright = skyBright; } 
    
    public double getSkyDark() { return skyDark; }
    
    public void setSkyDark(double skyDark) { this.skyDark = skyDark; }
    
    public double getZeroCalibration() { return zeroCalibration; }
    
    public void setZeroCalibration(double zeroCalibration) { this.zeroCalibration = zeroCalibration; } 
    
    public double getQFilter() { return qFilter; }
    
    public void setQFilter(double qFilter) { this.qFilter = qFilter; }
    
    public double getQCCD() { return qCCD; }
    
    public void setQCCD(double qCCD) { this.qCCD = qCCD; }
    
    public double getOpticalThickness() { return opticalThickness; }
    
    public void setOpticalThickness(double opticalThickness) { this.opticalThickness = opticalThickness; }
    
    public double getPhysicalThickness() { return physicalThickness; }
    
    public void setPhysicalThickness(double physicalThickness) { this.physicalThickness = physicalThickness; }

    public double getExtinction() { return extinction; }

    public void setExtinction(double extinction) { this.extinction = extinction; }

    public boolean isClear() {
	return type.equalsIgnoreCase("clear");
    }

    public boolean isNeutral() {
	return type.toLowerCase().startsWith("neutral");
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/**
/** Revision 1.1  2001/02/23 18:46:58  snf
/** Initial revision
/** */
