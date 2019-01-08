package ngat.instrument;

import ngat.phase2.*;
import ngat.util.*;

import java.io.*;

/** Holds information about an Instrument.
 * SId$
 */
public abstract class Instrument {

    /** Constant indicating instrument has status OFFLINE.*/
    public final static int OFFLINE = 0;
    
    /** Constant indicating instrument has status ONLINE.*/
    public final static int ONLINE = 1;
    // TODO these  should replace above
    /** Constant indicating instrument has status OFFLINE.*/
    public final static int NETWORK_STATUS_OFFLINE = 0;

    /** Constant indicating instrument has status ONLINE.*/
    public final static int NETWORK_STATUS_ONLINE = 1;

    /** Constant indicating instrument is operational -> use at will.*/
    public final static int OPERATIONAL_STATUS_OKAY = 1;

    /** Constant indicating instrument is impaired -> use with caution.*/
    public final static int OPERATIONAL_STATUS_WARN = 2;

    /** Constant indicating instrument is stuffed -> forget it.*/
    public final static int OPERATIONAL_STATUS_FAIL = 3;

    /** Constant indicating instrument is not available -> you decide.*/
    public final static int OPERATIONAL_STATUS_UNAVAILABLE = 4;

    /** The name/id of this Instrument.*/
    protected String name;

    /** The name.id of the current InstrumentConfig in use.*/
    protected String instConfigName;

    /** Holds references to the Instruemnt's detectors.*/
    protected Detector[] detectors;

    /** Holds the set of Standard Observation InstrumentConfigs for this Instrument.
     * @ deprecated Not used since 2007.
     */
    protected InstrumentConfig[] standards;

    /** Determines the instrument's network  status e.g. ON/OFFline.*/
    protected int status;

    /**  Determines the instrument's operational status.*/
    protected int operationalStatus;

    /** Determines if the instrument is capable of providing SkyModel data.*/
    protected boolean skyModelProvider;

    /** Focal plane offset X (mm).*/
    protected double apertureOffsetX;

    /** Focal plane offset Y (mm).*/
     protected double apertureOffsetY;

    /** Rotator offset for sky PA to mount angle translation (rads).*/
    protected double rotatorOffset;

    /** True if the instrument requires acquisition.*/
    protected boolean requiresAcquisition;

    /** Name of the instrument used for acquisition.*/
    protected String acquisitionInstrumentName;

    /** Acquisition offset X (pixels).*/
    protected double acquisitionOffsetX;

    /** Acquisition offset Y (pixels).*/
    protected double acquisitionOffsetY;

    /** Alignment correction for rotator Sky PA (rads).*/
    protected double rotatorAlignmentCorrection;

    /** Create an Instrument with the specified name/id.
     * @param name The anme/id of this Instrument.*/
    public Instrument(String name) { 
	this.name = name;	
	detectors = new Detector[getMaxDetectorCount()];
	instConfigName = null;
    }

    /** Set the specified Detector to that supplied.
     * @param i The number of the detector to set must be >=0 and < getMaxDetectorCount().
     * @param detector The detector to set at that location.
     * @exception IllegalArgumentException If the index is < 0 or > maxDetectorCount.
     */
    public void setDetector(int index, Detector detector) throws IllegalArgumentException {
	if (index < 0 || index >= getMaxDetectorCount()) 
	    throw new IllegalArgumentException("Instrument: "+name+" Detector number: "+index+
					       " out of range [0,"+(getMaxDetectorCount()-1));
	detectors[index] = detector;
    }
    
    /** Subclasses should override to return the correct value. 
     * @return The maximum number of detectors for this Instrument.*/
    public int getMaxDetectorCount() { return 1;}
    
    /** Returns the name of this Instrument.
     * @return The name of the Instrument.*/
    public String getName() { return name; }

    /** Returns the status of this instrument.
     * @return The status of this instrument.*/
    public int getStatus() { return status; }

    /** Sets the status of this instrument.
     * @param status The status of this Instrument.
     */
    public void setStatus(int status) { this.status = status; }

    /** Returns the impairment state of the instrument.*/
    public int getOperationalStatus() { return operationalStatus; }
    
    /** Sets the imapirment state.*/
    public void setOperationalStatus(int operationalStatus) { this.operationalStatus = operationalStatus; }

    /** Returns whether the instrument is a SkyModelProvider.*/
    public boolean isSkyModelProvider() { return skyModelProvider; }

    /** Sets whether this instrument is a SkyModelProvider.*/
    public void setSkyModelProvider(boolean skyModelProvider) {this.skyModelProvider = skyModelProvider; }

    /** Returns Focal plane offset X (mm).*/
    public double getApertureOffsetX() { return apertureOffsetX; }

    /** Sets the Focal plane offset X (mm).*/
    public void setApertureOffsetX(double apertureOffsetX) { this.apertureOffsetX = apertureOffsetX; }

    /** Returns Focal plane offset Y (mm).*/
    public double getApertureOffsetY() { return apertureOffsetY; }

    /** Sets the Focal plane offset Y (mm).*/
    public void setApertureOffsetY(double apertureOffsetY) { this.apertureOffsetY = apertureOffsetY; }

    /** Returns rotator offset (rads).*/
    public double getRotatorOffset() { return rotatorOffset; }

    /** Sets the rotator offset (rads).*/
    public void setRotatorOffset(double rotatorOffset) { this.rotatorOffset = rotatorOffset; }

    /** Return true if the instrument requires acquisition.*/
    public boolean getRequiresAcquisition() {return requiresAcquisition; }
    
    /** Set whether the instrument requires acquisition.*/
    public void setRequiresAcquisition(boolean requiresAcquisition) { this.requiresAcquisition = requiresAcquisition; } 

    /** Returns the name of the instrument used for acquisition.*/
    public String getAcquisitionInstrumentName() { return acquisitionInstrumentName; }

    /** Sets the name of the instrumnt used for acquisition.*/
    public void setAcquisitionInstrumentName(String acquisitionInstrumentName) { this.acquisitionInstrumentName = acquisitionInstrumentName; }

    /** Returns acquisition offset X (pixels).*/
    public double getAcquisitionOffsetX() { return acquisitionOffsetX; }

    /** Sets the acquisition offset X (pixels).*/
    public void setAcquisitionOffsetX(double acquisitionOffsetX) { this.acquisitionOffsetX = acquisitionOffsetX; }
    
    /** Returns acquisition offset Y (pixels).*/
    public double getAcquisitionOffsetY() { return acquisitionOffsetY; }

    /** Sets the acquisition offset Y (pixels).*/
    public void setAcquisitionOffsetY(double acquisitionOffsetY) { this.acquisitionOffsetY = acquisitionOffsetY; }

    /** Set the rotator alignment correction (rads).*/
    public void setRotatorAlignmentCorrection(double rotatorAlignmentCorrection) {
	this.rotatorAlignmentCorrection = rotatorAlignmentCorrection; }

    /** Returns the rotator alignment correction (rads).*/
    public double getRotatorAlignmentCorrection() { return rotatorAlignmentCorrection;}

    /** Returns the wavelength (nm) represented by the current configuration.
     * Defaults to 0.0.
     * Subclasses should override appropriately.*/
    public abstract double getCurrentWavelength();
    
    /** Configure the Instrument with the specified (instrument specific) ngat.phase2.Config.
     * @param ic The InstrumentConfig to apply.*/
    public abstract void configure(InstrumentConfig ic);

     /** Load the Instrument initial configuration from a file layed out in the
     * java.util.Properties format. 
     * @param configFile The java.io.File containing the setup information for the Instrument
     * layed out in java.util.Properties format. The format will depend on the specific type
     * of Instrument e.g. for CCDs will have a list of filters etc.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public abstract void configure(File configFile) 
	throws IOException, IllegalArgumentException;

    /** Configure the Instrument with default settings.*/
    public abstract void loadConfig();
    
    /** Sets the current InstrumentConfig from the properties contained in configFile.
     * @param configFile Config settings in java.util.Properties format.
     */
    public  abstract void loadConfig(File configFile) throws IOException, IllegalArgumentException;
    
    /** ConfigureInstrument from a ConfigurationProperties.
     * @param props The ConfigurationProperties to use.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public  abstract void loadConfig(ConfigurationProperties props) throws IllegalArgumentException;

    /** Returns true if the specified configuration is valid for this Instrument.
     * @param config The configuration to test against valid configs for the Instruement.
     */
    public abstract boolean canBeConfigured(InstrumentConfig config);
    
    /** Return the current configuration as an ngat.phase2.InstrumentConfig.
     * @param name A name to apply to the config.
     * @return The current configuration.*/
    public abstract InstrumentConfig getConfig(String name);
    
    /** Return the current configuration as an ngat.phase2.InstrumentConfig.
     * The current InstConfig name is used.
     * @return The current configuration.*/
    public InstrumentConfig getConfig() {
	return getConfig(instConfigName);
    }
    
    /** Returns the time taken for re-configuration to the specified
     * new configuration.
     * @param other The new configuration to go to.
     * @return The time required to move to this configuration from
     * the current configuration.*/
    public abstract long getReconfigurationTime(InstrumentConfig other);
   
    /** Calculates and returns the readout time for this Instrument for
     * the specified configuration including binning.
     * @param config The configuration for which to work out readout-time.
     * @return The time required for readout under config.
     */
    public abstract long calculateReadoutTime(InstrumentConfig config);
    
    /** Returns the time taken to perform pre/post exposure calibration in
     * its current configuration.
     * @param exposeTime The time taken for the actual exposure.
     */
    public abstract long getCalibrationTime(double exposeTime);

    /** Returns the pixel size in (arcsec).*/
    public abstract double getPixelSize();

    /** Returns a reference to an exposure calculator.*/
    public abstract ExposureCalculator getExposureCalculator();

    /** Readable description of network status.*/
    public static String toNetworkStatusString(int netStatus) {
	switch (netStatus) {
	case NETWORK_STATUS_ONLINE:
	    return "ONLINE";
	case NETWORK_STATUS_OFFLINE:
	    return "OFFLINE";
	default:
	    return "UNKNOWN";
	}
    }

    /** Readable description of operational status.*/
    public static String toOperationalStatusString(int opStatus) {
	switch (opStatus) {
	case OPERATIONAL_STATUS_OKAY:
	    return "OKAY";
	case OPERATIONAL_STATUS_WARN:
	    return "WARN";
	case OPERATIONAL_STATUS_FAIL:
	    return "FAIL";
	case OPERATIONAL_STATUS_UNAVAILABLE:
	    return "UNAVAILABLE";
	default:
	    return "UNKNOWN";
	}
    }

}  

/** $Log: not supported by cvs2svn $
/** Revision 1.13  2008/04/10 07:19:12  snf
/** added rotator alignment correction.
/**
/** Revision 1.12  2008/04/09 10:58:25  snf
/** removed calib soas to move over to ICM v1 implementation
/**
/** Revision 1.11  2008/01/11 08:59:05  snf
/** changed calcReadout to use supplied config
/**
/** Revision 1.10  2007/09/07 09:11:58  snf
/** calib typo
/**
/** Revision 1.9  2007/09/07 09:03:10  snf
/** added calibratio config and accessors.
/**
/** Revision 1.8  2007/08/30 09:47:29  snf
/** typo
/**
/** Revision 1.7  2007/08/30 09:46:48  snf
/** added acq inst name methjods
/**
/** Revision 1.6  2007/08/30 09:45:13  snf
/** added acquisition stuff
/**
/** Revision 1.5  2007/04/12 11:05:57  snf
/** added rotator offsets
/**
/** Revision 1.4  2007/03/20 13:47:07  snf
/** changed protection on aperture methods
/**
/** Revision 1.3  2007/03/20 13:37:11  snf
/** added aperture offsets
/**
/** Revision 1.2  2007/03/07 10:36:12  snf
/** Added skyModelProvider methods.
/**
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/**
/** Revision 1.1  2001/02/23 18:46:58  snf
/** Initial revision
/** */
