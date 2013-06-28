package ngat.instrument;

import ngat.phase2.*;
import ngat.phase2.nonpersist.*;
import ngat.util.*;
import ngat.util.logging.*;

import java.text.*;
import java.util.*;
import java.io.*;

/** Stores information about a IRCam camera - its current configuration and
 * methods for calculating reconfiguration times etc.
 * SId$
 */
public class IRCam extends Instrument implements Logging {

    /** Pixel size in arc secs.*/
    public static final double pixelSize = 0.1347;

    /** Stores the filter at each location 
	in the wheel.*/
    Filter[] filterWheel;
  
    /** The currently selected filter in the wheel.*/
    Filter filter;
   
    /** Stores the currently selected index in the wheel.*/
    int filterIndex;

    /** Logging.*/
    Logger infoLog;
    
    /** The number of filters in the wheel.*/
    public int maxFilterCount = 7;
    
    /** Default reconfiguration time (millis). This is needed because of a
     * problem  with the wheel mechanism resulting in frequent resets as 
     * the detente mechanism is missed or overshot due to slack in the 
     * drive belt. This will hopefully be replaced with a more accurate set
     * of parameters to allow realistic estimates of the time required.*/
    protected final long DEFAULT_RECONFIG_TIME = 60000L;
    
    /** Default readout time. This actually depends on the binning, windowing
     * and other internal settings.*/
    protected final long DEFAULT_READOUT_TIME  = 20000L;
    
    /** Create a IRCam camera with the specified name.
     * @param name The name of the IRCam camera.*/
    public IRCam(String name) {
	super(name);
	filterWheel = new Filter[maxFilterCount];	
	filter = null;
	filterIndex = 0;
	infoLog           = LogManager.getLogger(INFO);
    }
    
    

    /** Set the currently selected filter in the lower wheel by type.
     * @param type The type of the currently selected filter in the lower wheel.*/
    public void   setFilter(String type)  throws IllegalArgumentException { 
	int index   = getIndexForfilter(type);
	if (index < 0)
	    throw new IllegalArgumentException("IRCam: "+name+" Filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	filterIndex = index;
	filter = getFilterWheel(index);
    }
    
    /** Returns the currently selected filter in the lower wheel.
     * @return The currently selected filter in the lower wheel.*/
    public Filter getFilter() { return filter; }   

    /** Returns the index of the currently selected Lower Filter.*/
    public int getFilterIndex() { return filterIndex; }
    
    /** Set the Filter at index in the lower wheel to the specified filter.
     * @param index The index (location) in the filterwheel: 0 to maxFilterCount-1.
     * @param name The physical name of the filter.*/
    public void   setFilterWheel(int index, Filter filter) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("IRCam: "+name+" Lower filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	
	filterWheel[index] = filter;
	infoLog.log(INFO, 2, "IRCam", name, "setFilterWheel", 
		    "Type: "+filter.getType()+" Name: "+filter.getName());
    }
    
    /** Return the Filter at location index in the lower wheel.
     * @param index The index (location) in the filterwheel: 0 to maxFilterCount-1.
     * @return The Filter at that position.*/
    public Filter getFilterWheel(int index) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("IRCam: "+name+" Lower filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	return filterWheel[index];
    }
    
    /** Returns true if the lower filter wheel contains named filter.
     * @param filterName The physical name of the Filter.
     */
    public boolean filterWheelHasFilter(String filterName) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (filterName.equals(filterWheel[i].getName()))
		return true; 
	}
	return false;
    }
    
    /** Returns true if the lower filter wheel contains named filter type.
     * @param filterType The logical name (type) of the Filter.
     */
    public boolean filterWheelHasFilterType(String filterType) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (filterType.equals(filterWheel[i].getType()))
		return true; 
	}
	return false;
    }
    
    /** Returns the index in the lower wheel of the specified type of filter.*/
    public int getIndexForfilter(String type) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (type.equals(filterWheel[i].getType()))
		return i;
	}
	return -1;
    }

    
    /** @return The maximum number of detectors for this Instrument.*/
    public int getMaxFilterCount() { return maxFilterCount;}

    /** Load the IRCam camera initial configuration from a file layed out in the
     * java.util.Properties format. As a side effect, the FilterDatabase is also
     * setup with the filters specified in the named property files.
     * @param configFile The java.io.File containing Filter parameters etc
     * layed out in java.util.Properties format.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void configure(File configFile) throws IOException, IllegalArgumentException {
	
	ConfigurationProperties props = new ConfigurationProperties();
	
	// Load the Config info.
	InputStream in   = new FileInputStream(configFile);	
	props.load(in);
	
	// Get the Filter Wheel config file.
	File filterFile = null;
	try {
	    filterFile = props.getFile("filter.config.file");
	} catch (FileNotFoundException fx) {
	    throw new IllegalArgumentException("IRCam: "+name+
					       " (configure) Error parsing config: Filter Wheel Set: "+fx);
	}

	// Get the Filter database file.
	File dbPropsFile = null;
	try {
	    dbPropsFile = props.getFile("filter.database.file");
	} catch (FileNotFoundException fx) {
	    throw new IllegalArgumentException("IRCam: "+name+
					       " (configure) Error parsing config: Filter Database: "+fx);
	}
	
	configure(dbPropsFile, filterFile);

    }
    
    /** Load the IRCam camera initial configuration from a file layed out in the
     * java.util.Properties format. As a side effect, the FilterDatabase is also
     * setup with the filters specified in the named property files.
     * @param dbFile Filter Database file layed out in java.util.Properties format.
     * @param filterFile The java.io.File containing Filter parameters etc
     * layed out in java.util.Properties format.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public void configure(File dbFile, File filterFile) throws IOException, IllegalArgumentException {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	ConfigurationProperties filterProps = new ConfigurationProperties();
	
	// Load the Filter Config info.
	InputStream in   = new FileInputStream(filterFile);	
	filterProps.load(in);
	
//  	// Semester limits.
//  	Date start = null;
//  	Date end   = null;
//  	try {
//  	    start = sdf.parse((String)filterProps.getProperty("semester.start"));
//  	    end   = sdf.parse((String)filterProps.getProperty("semester.end"));
//  	} catch (ParseException pe) {
//  	    throw new IllegalArgumentException("IRCam: "+name+
//  					       " (configure) Error parsing semester limits: "+pe);
//  	}
	
//  	long now = System.currentTimeMillis();
//  	if (start.getTime() > now || end.getTime() < now) {
//  	     throw new IllegalArgumentException("IRCam: "+name+
//  						" (configure) Illegal semester limits: from: "+
//  						sdf.format(start)+" to: "+sdf.format(end));
//  	}
//  	infoLog.log(INFO, 2, "IRCam", name, 
//  		   "Loaded semester limits: "+sdf.format(start)+" to "+sdf.format(end));
	
	// Wheels.
	int wheelCount = 0;
	try {
	    wheelCount = Integer.parseInt((String)filterProps.getProperty("filterwheel.count",""+2));
	} catch (NumberFormatException ne) {
	    throw new IllegalArgumentException("IRCam: "+name+
					       " (configure) Error parsing filterwheel.count : "+ne);
	}
	infoLog.log(INFO, 2, "IRCam", name, 
		   "Loaded wheel count: "+wheelCount);
	Filter filter = null;
	for (int wheel = 0; wheel < wheelCount; wheel++) {
	    int filterCount = 0;
	   
	    try {
		filterCount = Integer.parseInt((String)filterProps.getProperty("filterwheel."+wheel+".count",""+0));
	    } catch (NumberFormatException ne) {
		throw new IllegalArgumentException("IRCam: "+name+
						   " (configure) Error parsing filter."+wheel+".count : "+ne);
	    }
	    infoLog.log(INFO, 2, "IRCam", name, 
			"Loaded filter wheel "+wheel+" filter count: "+filterCount);

	    maxFilterCount = filterCount;
	    
	    for (int filterNo = 0; filterNo < filterCount; filterNo++) {
		String type = (String)filterProps.getProperty("filterwheel."+wheel+"."+filterNo+".type");
		
		if (type == null)
		    throw new IllegalArgumentException("IRCam: "+name+
						       " (configure) Filterwheel: "+wheel+
						       " location: "+filterNo+" type not defined.");
		infoLog.log(INFO, 2, "IRCam", name, 
			   "Wheel: "+wheel+" location: "+filterNo+" Loaded type: "+type);
		
		String id = (String)filterProps.getProperty("filterwheel."+type+".id");		
		
		if (id == null)
		    throw new IllegalArgumentException("IRCam: "+name+
						       " (configure) Filterwheel: "+wheel+
						       " location: "+filterNo+" id not defined. ");	    
		infoLog.log(INFO, 2, "IRCam", name, 
			   "Wheel: "+wheel+" location: "+filterNo+" Loaded id: "+id);
		
		// Setup this filter in the database using the filter's Physical name.

		System.err.println("IRCam::Makefilter from: "+dbFile.getPath()+", for filter type: "+type+" id: "+id);
		
		filter = makeFilter(type, id, dbFile);
		infoLog.log(INFO, 2, "IRCam", name, 
			    "Configured IRCam for filter: "+filter);
		
		// Set the filter to specified physical name.		
		setFilterWheel(filterNo, filter );
		
	    }
	}	
    }
    
    /** Configure the Instrument Standards set from the specified File.
     * The properties should include the following info:-<br>
     * <dl>
     * <dt>standard.inst-name.config.count
     * <dd>The number of configs to set up.
     * <dt>standard.inst-name.config.<i>count</i>.upper.filter  
     * <dd>The name/type of the upper filter.
     * <dt>standard.inst-name.config.<i>count</i>.lower.filter   
     * <dd>The name/type of the lower filter.
     * </dl>
     * @param file The configuration file.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it. 
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.
     */
    public void configureStandards(File file) throws IOException, IllegalArgumentException{
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
	
	IRCamConfig   instConfig  = null;
	
	IRCamDetector detector    = new IRCamDetector();
	detector.setXBin(1);
	detector.setYBin(1);
	
	int configCount = props.getIntValue("standard."+name+".config.count", -1);
	if (configCount == -1)
	    throw new IllegalArgumentException("Instrument ["+name+
					       "] standards: config.count: Not set or illegal.");
	
	standards = new IRCamConfig[configCount];
	
	for (int i = 0; i < configCount; i++) {	 	 
	    instConfig  = new IRCamConfig("Std-"+name+"-("+i+")");
	    instConfig.setDetector(0, detector);
	  
	    String usefilter = props.getProperty("standard."+name+".config."+i+".filter");
	    if (usefilter == null)
		throw new IllegalArgumentException("Standards: "+instConfig.getName()+
						   " Filter Not set or illegal.");
	    instConfig.setFilterWheel(usefilter);
	    
	    standards[i] = instConfig;
	}

    }
    
    /** Configure the Instrument with default settings.*/
    public void loadConfig() {
	try {	 
	    Filter usefilter = getFilterWheel(0);
	    infoLog.log(INFO, 2, "IRCam", name, "config(V)", "Filter:" + usefilter);
	    setFilter(usefilter.getType());
	    IRCamDetector detector = new IRCamDetector();	    
	    setDetector(0, detector);
	} catch (IllegalArgumentException e) {
	    System.err.println("IRCam: "+name+" (configure) Unexpected error setting default config: "+e);
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
	setFilter(props.getProperty("filter", IRCamConfig.BLANK));	
	IRCamDetector detector = new IRCamDetector();	    
	setDetector(0, detector);
	detector.setXBin(props.getIntValue("x.bin", 1));
	detector.setYBin(props.getIntValue("y.bin", 1)); 
	detector.clearAllWindows();
    }
    
    /** Configure the Instrument with the specified (instrument specific) ngat.phase2.Config.
     * @param ic The InstrumentConfig to apply.*/
    public void configure(InstrumentConfig ic) {
	IRCamConfig irc = (IRCamConfig)ic;
	setFilter(irc.getFilterWheel());	
	try {
	    setDetector(0, irc.getDetector(0));
	} catch (IllegalArgumentException e) {
	    System.err.println("IRCam: "+name+" (configure) Unexpected error setting detector from config: "+e);
	}
    }
    
    /** Return the current configuration as an ngat.phase2.InstrumentConfig.
     * @param name A name to apply to the config.
     * @return The current configuration.*/
    public InstrumentConfig getConfig(String name) {
	IRCamConfig config = new IRCamConfig(name);
	config.setFilterWheel(filter.getType());	
	try {
	    config.setDetector(0, detectors[0]);
	} catch (IllegalArgumentException e) {
	    System.err.println("IRCam: "+name+" (getConfig) Unexpected error setting detector in config: "+e);
	}
	return config;
    }
    
    /** Returns true if the specified configuration is valid for this Instrument.
     * @param config The configuration to test against valid configs for the Instruement.
     */
    public boolean canBeConfigured(InstrumentConfig config) {
	if (! (config instanceof IRCamConfig)) return false;
	IRCamConfig cc = (IRCamConfig)config;
	String usefilter = cc.getFilterWheel();
	
	// Check both filters.
	if (! filterWheelHasFilterType(usefilter)) return false;
	
	Detector[] dets = cc.getDetectors();
	if (dets == null || (dets.length != 1)) return false;
	IRCamDetector det = (IRCamDetector)dets[0];
	if (det == null) return false;
	
	return true;
    }
    
    /** Returns the wavelength (nm) represented by the current configuration.
     */
    public double getCurrentWavelength() {
	return filter.getCentralWavelength()/10.0;
    }
    
    /** Returns the time taken for reconfiguration to the specified
     * new configuration.## Just returns DEFAULT_RECONFIG_TIME.
     * @param other The new configuration to go to.
     * @return The time required to move to this configuration from
     * the current configuration.*/
    public long getReconfigurationTime(InstrumentConfig other) {
	return DEFAULT_RECONFIG_TIME;
    }
    
    /** Calculates and returns the readout time for this IRCam in
     * its current configuration. ## Just returns DEFAULT_READOUT_TIME.*/
    public long calculateReadoutTime(InstrumentConfig config) {
	return DEFAULT_READOUT_TIME;
    }

    /** Returns the time taken to perform pre/post exposure calibration in
     * its current configuration.
     * @param exposeTime The time taken for the actual exposure.
     */
    public long getCalibrationTime(double exposeTime) {
	//return 2*exposeTime;
	// really we need the current IC and if its calBef/calAft flags are set.
	return (long)exposeTime;
    }

    public double getPixelSize() { return Math.toRadians(pixelSize*3600); }
    
    /** Load the filter config from a file layed out in the
     * java.util.Properties format.
     * @param propsFile The java.io.File containing the properties.
     * @exception IOException If the file cannot be found or any other IO
     * problems occur while reading it.
     * @exception IllegalArgumentException If an number parsing error occurs
     * or an essential property is missing from the file.*/
    public Filter makeFilter(String type, String name, File propsFile) 
	throws IOException, IllegalArgumentException {
	
	InputStream in   = new FileInputStream(propsFile);
	Properties props = new Properties();
	props.load(in);
	System.err.println("IRCam::Loaded filter DB: "+propsFile.getPath());

	Filter filter    = new Filter(name);
	filter.setType(type);	
	filter.configure(props);

	return filter;
    }
   
    /** Returns a reference to an exposure calculator.*/
    public ExposureCalculator getExposureCalculator() { 
	return null;
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/** */
