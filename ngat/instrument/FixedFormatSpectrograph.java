package ngat.instrument;

import ngat.phase2.*;
import ngat.util.*;

import java.io.*;
import java.text.*;
/** Stores information about a FxiedFormatSpectrograph - its current configuration and
 * methods for calculating reconfiguration times etc.
 * SId$
 */
public class FixedFormatSpectrograph extends Instrument {
    
    /** Pixel size in arc secs.*/
    public static final double pixelSize = 0.1347;
    
    /** Default reconfiguration time (millis).*/
    protected final long DEFAULT_RECONFIG_TIME = 120000L;
    
    /** Default readout time. This actually depends on the binning, windowing
     * and other internal settings.*/
    protected final long DEFAULT_READOUT_TIME  = 20000L;

    /** Central wavelength (Angs).*/
    public static final double DEFAULT_CENTRAL_WAVELENGTH = 5000.0;

    /** Create an Instrument with the specified name/id.
     * @param name The name/id of this Instrument.*/
    public FixedFormatSpectrograph(String name) { 
 	super(name);	
	detectors = new Detector[getMaxDetectorCount()];
	instConfigName = null;
    }
   
    /** Configure the Instrument with the specified (instrument specific) ngat.phase2.Config.
     * @param ic The InstrumentConfig to apply.*/
    public void configure(InstrumentConfig ic) {
	FixedFormatSpecConfig spec = (FixedFormatSpecConfig)ic;	
	try {
	    setDetector(0, spec.getDetector(0));
	} catch (IllegalArgumentException e) {
	    System.err.println("FixedFormatSpec: "+name+
			       " (configure) Unexpected error setting detector from config: "+e);
	}
    }

     /** Load the Instrument initial configuration from a file layed out in the
     * java.util.Properties format. 
     * @param configFile The java.io.File containing the setup information for the Instrument
     * layed out in java.util.Properties format. The format will depend on the specific type
     * of Instrument e.g. for CCDs will have a list of filters etc.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void configure(File configFile) 
	throws IOException, IllegalArgumentException {

	ConfigurationProperties props = new ConfigurationProperties();
	
	// Load the Config info.
	InputStream in   = new FileInputStream(configFile);	
	props.load(in);
	
    }

    /** Configure the Instrument with default settings.*/
    public void loadConfig() {
	try {
	    FixedFormatSpecDetector detector = new FixedFormatSpecDetector();	    
	    setDetector(0, detector);
	} catch (IllegalArgumentException e) {
	    System.err.println("FixedFormatSpec: "+name+" (configure) Unexpected error setting default config: "+e);
	}
    }
    
    /** Sets the current InstrumentConfig from the properties contained in configFile.
     * @param configFile Config settings in java.util.Properties format.
     */
    public void loadConfig(File configFile) throws IOException, IllegalArgumentException {
	InputStream             in    = new FileInputStream(configFile);
	ConfigurationProperties props = new ConfigurationProperties();
	props.load(in);
	loadConfig(props);	
    }
    
    /** ConfigureInstrument from a ConfigurationProperties.
     * @param props The ConfigurationProperties to use.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void loadConfig(ConfigurationProperties props) throws IllegalArgumentException {
	instConfigName = props.getProperty("id", "UNTITLED");
	FixedFormatSpecDetector detector = new FixedFormatSpecDetector();	    
	setDetector(0, detector);
	setDetector(0, detector);
	detector.setXBin(props.getIntValue("x.bin", 1));
	detector.setYBin(props.getIntValue("y.bin", 1)); 
	detector.clearAllWindows();
    }

    /** Configure the Instrument Standards set from the specified File. Subclasses
     * must create the standards[] array and populate it.
     * @param file The configuration file.*/
    public void configureStandards(File file) throws IOException, IllegalArgumentException {
	InputStream             in    = new FileInputStream(file);
	ConfigurationProperties props = new ConfigurationProperties();
	props.load(in);
	configureStandards(props);
    }
    
    /** Configure Standards from a ConfigurationProperties.
     * @param props The ConfigurationProperties to use.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void configureStandards(ConfigurationProperties props)  throws IllegalArgumentException {

	FixedFormatSpecConfig   instConfig  = null;	
	FixedFormatSpecDetector detector    = new FixedFormatSpecDetector();
	detector.setXBin(1);
	detector.setYBin(1);
	
	int configCount = props.getIntValue("standard."+name+".config.count", -1);
	if (configCount == -1)
	    throw new IllegalArgumentException("Instrument ["+name+
					       "] standards: config.count: Not set or illegal.");
	
	standards = new FixedFormatSpecConfig[configCount];
	
	for (int i = 0; i < configCount; i++) {	 	 
	    instConfig  = new FixedFormatSpecConfig("Std-"+name+"-("+i+")");
	    instConfig.setDetector(0, detector);	    
	    standards[i] = instConfig;    
	}
    }
    
    /** Return the current configuration as an ngat.phase2.InstrumentConfig.
     * @param name A name to apply to the config.
     * @return The current configuration.*/
    public InstrumentConfig getConfig(String name) {
	FixedFormatSpecConfig config = new FixedFormatSpecConfig(name);	
	try {
	    config.setDetector(0, detectors[0]);
	} catch (IllegalArgumentException e) {
	    System.err.println("FixedFormatSpec: "+name+
			       " (getConfig) Unexpected error setting detector in config: "+e);
	}
	return config;
    }

    /** Returns true if the specified configuration is valid for this Instrument.
     * @param config The configuration to test against valid configs for the Instruement.
     */
    public boolean canBeConfigured(InstrumentConfig config) {
	if (! (config instanceof FixedFormatSpecConfig)) return false;
	FixedFormatSpecConfig cc = (FixedFormatSpecConfig)config;	
	Detector[] dets = cc.getDetectors();
	if (dets == null || (dets.length != 1)) return false;
	FixedFormatSpecDetector det = (FixedFormatSpecDetector)dets[0];
	if (det == null) return false;
	
	return true;
    }


    /** Returns the wavelength (nm) represented by the current configuration.
     */
    public double getCurrentWavelength() {
	return DEFAULT_CENTRAL_WAVELENGTH/10.0;
    }  


    /** Returns the time taken for reconfiguration to the specified
     * new configuration.
     * @param other The new configuration to go to.
     * @return The time required to move to this configuration from
     * the current configuration.*/
    public long getReconfigurationTime(InstrumentConfig other) {
	return DEFAULT_RECONFIG_TIME;
    }
   
    /** Calculates and returns the readout time for this Instrument in
     * its current configuration.*/
    public long calculateReadoutTime(InstrumentConfig config) {
	return DEFAULT_READOUT_TIME;
    }
    
    /** Returns the time taken to perform pre/post exposure calibration in
     * its current configuration.
     * @param exposeTime The time taken for the actual exposure.
     */
    public long getCalibrationTime(double exposeTime) {
	return 0L;
    }

    /** Returns the pixel size in (arcsec).*/
    public double getPixelSize() {
	return Math.toRadians(pixelSize*3600); 
    }

    /** Returns a reference to an exposure calculator.*/
    public ExposureCalculator getExposureCalculator() { 
	return null;
    }

}  

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/**
/** Revision 1.1  2001/02/23 18:46:58  snf
/** Initial revision
/** */
