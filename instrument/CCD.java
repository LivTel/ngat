package ngat.instrument;

import ngat.phase2.*;
import ngat.phase2.nonpersist.*;
import ngat.util.*;
import ngat.util.logging.*;

import java.text.*;
import java.util.*;
import java.io.*;

/** Stores information about a CCD camera - its current configuration and
 * methods for calculating reconfiguration times etc.
 * SId$
 */
public class CCD extends Instrument implements Logging {

    /** Pixel size in arc secs.*/
    public static final double pixelSize = 0.1347;

    /** Stores the filter at each location 
	in the lower wheel.*/
    Filter[] lowerFilterWheel;
    
    /** Stores the filter at each location 
	in the upper wheel.*/
    Filter[] upperFilterWheel;
    
    /** The currently selected filter in the lower wheel.*/
    Filter lowerFilter;
    
    /** The currently selected filter in the upper wheel.*/
    Filter upperFilter;
    
    /** Stores the currently selected index in the Lower wheel.*/
    int lowerFilterIndex;
    
    /** Stores the currently selected index in the Upper wheel.*/
    int upperFilterIndex;
  
    /** Logging.*/
    Logger infoLog;
    
    /** The number of filters in each wheel.*/
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

    /** Binning multiplication factor in calculation of readout time.*/
    protected final double READOUT_TIME_MULT_FACTOR = 10.667 ;

    /** Fixed-overhead factor in calculation of readout time.*/
    protected final double READOUT_TIME_FIXED_OVERHEAD = 9.33;

    
    /** Create a CCD camera with the specified name.
     * @param name The name of the CCD camera.*/
    public CCD(String name) {
	super(name);
	lowerFilterWheel = new Filter[maxFilterCount];
	upperFilterWheel = new Filter[maxFilterCount];
	lowerFilter = null;
	upperFilter = null;
	lowerFilterIndex = 0;
	upperFilterIndex = 0;
	infoLog           = LogManager.getLogger(INFO);
    }
    
    

    /** Set the currently selected filter in the lower wheel by type.
     * @param type The type of the currently selected filter in the lower wheel.*/
    public void   setLowerFilter(String type)  throws IllegalArgumentException { 
	int index   = getIndexForLowerFilter(type);
	if (index < 0)
	    throw new IllegalArgumentException("CCD: "+name+" Lower filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	lowerFilterIndex = index;
	lowerFilter = getLowerFilterWheel(index);
    }
    
    /** Returns the currently selected filter in the lower wheel.
     * @return The currently selected filter in the lower wheel.*/
    public Filter getLowerFilter() { return lowerFilter; }   

    /** Returns the index of the currently selected Lower Filter.*/
    public int getLowerFilterIndex() { return lowerFilterIndex; }
    
    /** Set the Filter at index in the lower wheel to the specified filter.
     * @param index The index (location) in the filterwheel: 0 to maxFilterCount-1.
     * @param name The physical name of the filter.*/
    public void   setLowerFilterWheel(int index, Filter filter) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("CCD: "+name+" Lower filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	
	lowerFilterWheel[index] = filter;
	infoLog.log(INFO, 2, "CCD", name, "setLowerFilterWheel", 
		    "Type: "+filter.getType()+" Name: "+filter.getName());
    }
    
    /** Return the Filter at location index in the lower wheel.
     * @param index The index (location) in the filterwheel: 0 to maxFilterCount-1.
     * @return The Filter at that position.*/
    public Filter getLowerFilterWheel(int index) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("CCD: "+name+" Lower filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	return lowerFilterWheel[index];
    }
    
    /** Returns true if the lower filter wheel contains named filter.
     * @param filterName The physical name of the Filter.
     */
    public boolean lowerFilterWheelHasFilter(String filterName) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (filterName.equals(lowerFilterWheel[i].getName()))
		return true; 
	}
	return false;
    }
    
    /** Returns true if the lower filter wheel contains named filter type.
     * @param filterType The logical name (type) of the Filter.
     */
    public boolean lowerFilterWheelHasFilterType(String filterType) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (filterType.equals(lowerFilterWheel[i].getType()))
		return true; 
	}
	return false;
    }
    
    /** Returns the index in the lower wheel of the specified type of filter.*/
    public int getIndexForLowerFilter(String type) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (type.equals(lowerFilterWheel[i].getType()))
		return i;
	}
	return -1;
    }

    /** Set the type of the currently selected filter in the upperer wheel.
     * @param upperFilter The type of the currently selected filter in the upper wheel.*/
    public void   setUpperFilter(String type) throws IllegalArgumentException  {
	int index   = getIndexForUpperFilter(type);
	if (index < 0)
	    throw new IllegalArgumentException("CCD: "+name+" Upper filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	upperFilterIndex = index;
	upperFilter = getUpperFilterWheel(index);
    }
    
    /** Returns the currently selected filter in the upper wheel.
     * @return The currently selected filter in the upper wheel.*/
    public Filter getUpperFilter() { return upperFilter; }

    /** Returns the index of the currently selected Upper Filter.*/
    public int getUpperFilterIndex() { return upperFilterIndex; }
    
    /** Set the Filter at index in the upper wheel to the specified physical name.
     * @param index The index (location) in the filterwheel: 0 to maxFilterCount-1.
     * @param name The physical name of the filter.*/
    public void    setUpperFilterWheel(int index, Filter filter) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("CCD: "+name+" Upper filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	
	upperFilterWheel[index] = filter;
	infoLog.log(INFO, 2, "CCD", name, "setUpperFilterWheel", 
		    "Type: "+filter.getType()+" Name: "+filter.getName());
    }

    /** Return the Filter at location index in the upper wheel.
     * @param index The index (location) in the filterwheel: 0 - maxFilterCount.
     * @return The Filter at that position.*/
    public Filter  getUpperFilterWheel(int index) throws IllegalArgumentException {
	if (index < 0 || index >= maxFilterCount)
	    throw new IllegalArgumentException("CCD: "+name+" Upper filter number: "+index+
					       " out of range [0,"+(getMaxFilterCount()-1));
	return upperFilterWheel[index];
    }

    /** Returns true if the upper filter wheel contains  named filter.
     * @param filterName The physical name of the Filter.
     */
    public boolean upperFilterWheelHasFilter(String filterName) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (filterName.equals(upperFilterWheel[i].getName()))
		return true; 
	}
	return false;
    } 

    /** Returns true if the upper filter wheel contains  named filter type.
     * @param name The logical name (type) of the Filter.
     */
    public boolean upperFilterWheelHasFilterType(String filterType) {
	for (int i = 0; i < maxFilterCount; i++) {
	    //System.err.println("Testing filtertype: "+filterType+
	    //	       " against upper-"+i+" type is: "+upperFilterWheel[i].getType());
	    if (filterType.equals(upperFilterWheel[i].getType())) {
		//System.err.println("Returning TRUE");
		return true; 	    	   
	    }
	} 
	System.err.println("Returning FALSE");
	return false;
    }

    /** Returns the index in the upper wheel of the specified type of filter.*/
    public int getIndexForUpperFilter(String type) {
	for (int i = 0; i < maxFilterCount; i++) {
	    if (type.equals(upperFilterWheel[i].getType()))
		return i;
	}
	return -1;
    }
    
    /** @return The maximum number of detectors for this Instrument.*/
    public int getMaxFilterCount() { return maxFilterCount;}

    /** Load the CCD camera initial configuration from a file layed out in the
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
	    throw new IllegalArgumentException("CCD: "+name+
					       " (configure) Error parsing config: Filter Wheel Set: "+fx);
	}

	// Get the Filter database file.
	File dbPropsFile = null;
	try {
	    dbPropsFile = props.getFile("filter.database.file");
	} catch (FileNotFoundException fx) {
	    throw new IllegalArgumentException("CCD: "+name+
					       " (configure) Error parsing config: Filter Database: "+fx);
	}
	
	configure(dbPropsFile, filterFile);

    }
    
    /** Load the CCD camera initial configuration from a file layed out in the
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
	
//  	// Semester limits. FORGET THESE THEY ARE SUPERFLUOUS
//  	Date start = null;
//  	Date end   = null;
//  	try {
//  	    start = sdf.parse((String)filterProps.getProperty("semester.start"));
//  	    end   = sdf.parse((String)filterProps.getProperty("semester.end"));
//  	} catch (ParseException pe) {
//  	    throw new IllegalArgumentException("CCD: "+name+
//  					       " (configure) Error parsing semester limits: "+pe);
//  	}
	
//  	long now = System.currentTimeMillis();
//  	if (start.getTime() > now || end.getTime() < now) {
//  	     throw new IllegalArgumentException("CCD: "+name+
//  						" (configure) Illegal semester limits: from: "+
//  						sdf.format(start)+" to: "+sdf.format(end));
//  	}
//  	infoLog.log(INFO, 2, "CCD", name, 
//  		   "Loaded semester limits: "+sdf.format(start)+" to "+sdf.format(end));
	
	// Wheels.
	int wheelCount = 0;
	try {
	    wheelCount = Integer.parseInt((String)filterProps.getProperty("filterwheel.count",""+2));
	} catch (NumberFormatException ne) {
	    throw new IllegalArgumentException("CCD: "+name+
					       " (configure) Error parsing filterwheel.count : "+ne);
	}
	infoLog.log(INFO, 2, "CCD", name, 
		   "Loaded wheel count: "+wheelCount);
	Filter filter = null;
	for (int wheel = 0; wheel < wheelCount; wheel++) {
	    int filterCount = 0;
	   
	    try {
		filterCount = Integer.parseInt((String)filterProps.getProperty("filterwheel."+wheel+".count",""+0));
	    } catch (NumberFormatException ne) {
		throw new IllegalArgumentException("CCD: "+name+
						   " (configure) Error parsing filter."+wheel+".count : "+ne);
	    }
	    infoLog.log(INFO, 2, "CCD", name, 
			"Loaded filter wheel "+wheel+" filter count: "+filterCount);

	    maxFilterCount = filterCount;
	    
	    for (int filterNo = 0; filterNo < filterCount; filterNo++) {
		String type = (String)filterProps.getProperty("filterwheel."+wheel+"."+filterNo+".type");
		
		if (type == null)
		    throw new IllegalArgumentException("CCD: "+name+
						       " (configure) Filterwheel: "+wheel+
						       " location: "+filterNo+" type not defined.");
		infoLog.log(INFO, 2, "CCD", name, 
			   "Wheel: "+wheel+" location: "+filterNo+" Loaded type: "+type);
		
		String id = (String)filterProps.getProperty("filterwheel."+type+".id");		
		
		if (id == null)
		    throw new IllegalArgumentException("CCD: "+name+
						       " (configure) Filterwheel: "+wheel+
						       " location: "+filterNo+" id not defined. ");	    
		infoLog.log(INFO, 2, "CCD", name, 
			   "Wheel: "+wheel+" location: "+filterNo+" Loaded id: "+id);
		
		// Setup this filter in the database using the filter's Physical name.
		filter = makeFilter(type, id, dbFile);
		infoLog.log(INFO, 2, "CCD", name, 
			    "Configured CCD for filter: "+filter);
		
		// Set the lower or upper filter to specified physical name.
		if (wheel == 0)
		    setLowerFilterWheel(filterNo, filter );
		
		if (wheel == 1)
		    setUpperFilterWheel(filterNo, filter);
		
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
	
	CCDConfig   instConfig  = null;	
	CCDDetector detector    = new CCDDetector();
	detector.setXBin(1);
	detector.setYBin(1);
	
	int configCount = props.getIntValue("standard."+name+".config.count", -1);
	if (configCount == -1)
	    throw new IllegalArgumentException("Instrument ["+name+
					       "] standards: config.count: Not set or illegal.");
	
	standards = new CCDConfig[configCount];
	
	for (int i = 0; i < configCount; i++) {	 	   
	    instConfig  = new CCDConfig("Std-"+name+"-("+i+")");
	    instConfig.setDetector(0, detector);
	    String upper = props.getProperty("standard."+name+".config."+i+".upper.filter");
	    if (upper == null)
		throw new IllegalArgumentException("Standards: "+instConfig.getName()+
						   " Upper filter Not set or illegal.");
	    instConfig.setUpperFilterWheel(upper);
	    String lower = props.getProperty("standard."+name+".config."+i+".lower.filter");
	    if (lower == null)
		throw new IllegalArgumentException("Standards: "+instConfig.getName()+
						   " Lower filter Not set or illegal.");
	    instConfig.setLowerFilterWheel(lower);	  
	    
	    standards[i] = instConfig;
	}
    }
    
    /** Configure the Instrument with default settings.*/
    public void loadConfig() {
	try {
	    Filter upper = getUpperFilterWheel(0);
	    infoLog.log(INFO, 2, "CCD", name, "config(V)", "Upper filter:" + upper);
	    setUpperFilter(upper.getType());
	    Filter lower = getLowerFilterWheel(0);
	    infoLog.log(INFO, 2, "CCD", name, "config(V)", "Lower filter:" + lower);
	    setLowerFilter(lower.getType());
	    CCDDetector detector = new CCDDetector();	    
	    setDetector(0, detector);
	} catch (IllegalArgumentException e) {
	    System.err.println("CCD: "+name+" (configure) Unexpected error setting default config: "+e);
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
	setLowerFilter(props.getProperty("lower.filter", "clear"));
	setUpperFilter(props.getProperty("upper.filter", "clear"));
	CCDDetector detector = new CCDDetector();	    
	setDetector(0, detector);
	detector.setXBin(props.getIntValue("x.bin", 1));
	detector.setYBin(props.getIntValue("y.bin", 1)); 
	detector.clearAllWindows();
    }
    
    /** Configure the Instrument with the specified (instrument specific) ngat.phase2.Config.
     * @param ic The InstrumentConfig to apply.*/
    public void configure(InstrumentConfig ic) {
	CCDConfig ccdc = (CCDConfig)ic;
	setLowerFilter(ccdc.getLowerFilterWheel());
	setUpperFilter(ccdc.getUpperFilterWheel());
	try {
	    setDetector(0, ccdc.getDetector(0));
	} catch (IllegalArgumentException e) {
	    System.err.println("CCD: "+name+" (configure) Unexpected error setting detector from config: "+e);
	}
    }
    
    /** Return the current configuration as an ngat.phase2.InstrumentConfig.
     * @param name A name to apply to the config.
     * @return The current configuration.*/
    public InstrumentConfig getConfig(String name) {
	CCDConfig config = new CCDConfig(name);
	config.setLowerFilterWheel(lowerFilter.getType());
	config.setUpperFilterWheel(upperFilter.getType());
	try {
	    config.setDetector(0, detectors[0]);
	} catch (IllegalArgumentException e) {
	    System.err.println("CCD: "+name+" (getConfig) Unexpected error setting detector in config: "+e);
	}
	return config;
    }

    /** Returns true if the specified configuration is valid for this Instrument.
     * @param config The configuration to test against valid configs for the Instruement.
     */
    public boolean canBeConfigured(InstrumentConfig config) {
	if (! (config instanceof CCDConfig)) return false;
	CCDConfig cc = (CCDConfig)config;
	String lfilter = cc.getLowerFilterWheel();
	String ufilter = cc.getUpperFilterWheel();	
	// Check both filters.
	if (! lowerFilterWheelHasFilterType(lfilter)) return false;
	if (! upperFilterWheelHasFilterType(ufilter)) return false;

	Detector[] dets = cc.getDetectors();
	if (dets == null || (dets.length != 1)) return false;
	CCDDetector det = (CCDDetector)dets[0];
	if (det == null) return false;
	
	return true;
    }
    
    /** Returns the wavelength (nm) represented by the current configuration.
     * If both filters are either Clear or Neutral then we just assume 500nm !
     */
    public double getCurrentWavelength() { 
	if (upperFilter.isClear()) {
	    if ((! lowerFilter.isClear()) && (! lowerFilter.isNeutral())) 
		return lowerFilter.getCentralWavelength()/10.0;
	    else
		return 500.0;
	} else { 	   
	    if ((! upperFilter.isClear()) && (! upperFilter.isNeutral())) 
		return upperFilter.getCentralWavelength()/10.0;
	    else
		return 500.0;
	}
    }

    /** Returns the time taken for reconfiguration to the specified
     * new configuration.## Just returns DEFAULT_RECONFIG_TIME.
     * @param other The new configuration to go to.
     * @return The time required to move to this configuration from
     * the current configuration.*/
    public long getReconfigurationTime(InstrumentConfig other) {
	return DEFAULT_RECONFIG_TIME;
    }
    
    /** Calculates and returns the readout time for this CCD in
     * its current configuration. ## Just returns DEFAULT_READOUT_TIME.*/
    public long calculateReadoutTime(InstrumentConfig config) {
	//return DEFAULT_READOUT_TIME;
	
	int binxy = 1;
	
	if (config != null) {
	    Detector det = config.getDetector(0);
	    if (det != null) {
		binxy = det.getXBin()*det.getYBin(); 
	    }
	}
	
	return (long)(1000.0*(READOUT_TIME_MULT_FACTOR/(double)binxy +READOUT_TIME_FIXED_OVERHEAD));

    }
    
    /** Returns the time taken to perform pre/post exposure calibration in
     * its current configuration.
     * @param exposeTime The time taken for the actual exposure.
     */
    public long getCalibrationTime(double exposeTime) {
	return 0L;
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
	Filter filter    = new Filter(name);
	filter.setType(type);	
	filter.configure(props);	
	return filter;
    }

    /** Returns a reference to an exposure calculator.*/
    public ExposureCalculator getExposureCalculator() { 
	return new CCDExposureCalculator();
    }

    /** Internal ExposureCalculator.*/
    protected class CCDExposureCalculator implements ExposureCalculator {

	/** Returns the exposure time (millis) for the CCD as currently configured for the specifed 
	 * Source magnitude and SNR. 	
	 * @param sourceMagnitude The magnitude of the star for which the exposure is to be determined.
	 * @param snr             The snr for this exposure.
	 * @param skyMagnitude    Magnitude of the sky (mag/arcsec^2).
	 * @param seeing          The current seeing (arcsec).
	 * @return The exposure time in millis.
	 */
	public double calculateExposureTime(double sourceMagnitude, double snr, double skyMagnitude, double seeing) {
	    // Fake result.
	    //return (1000.0)*Math.pow(2.0, sourceMagnitude-15.0)*(Math.log(snr)+5.0);
	    
	    // Actual Result.
	    double telarea   = 31415;
	    double readnoise = 3.0;
	    double telqe     = 0.70;

	    Filter filter = null;

	    System.err.println("EXP:Starting exposure calculation:");
	    // Work out which is the non-clear filter. Note that the 'clear' filter may be an ND so
	    // need to take into account the extinction (mags)..
	    double extinction = 1.0;
	    
	    // Either filter is NOT ND and NOT clear use it.
	    // Note: We just assume the lower if upper not usable.
	    if ((! upperFilter.isClear()) && (! upperFilter.isNeutral()))
		filter = upperFilter;
	    else
		filter = lowerFilter;
	   
	    // Either one is neutral then set its extinction.
	    if (upperFilter.isNeutral())
		extinction = upperFilter.getExtinction();
	    else if
		(lowerFilter.isNeutral())
		extinction = lowerFilter.getExtinction();
	    else 
		extinction = 1.0;

	    System.err.println("EXP:Use Filter: "+filter);
	    System.err.println("EXP:Overall Extinction: "+extinction);
	   
	    double ccdqe    = filter.getQCCD();
	    double zp       = filter.getZeroCalibration();
	    double filwidth = filter.getBandwidth();
	    double filwave  = filter.getCentralWavelength();
	    double filqe    = filter.getQFilter();
	 
	    double starflux        = zp * Math.pow(10.0,-0.4*sourceMagnitude);
	    double flux            = starflux * telarea * telqe * ccdqe * filqe * filwidth / extinction;
	    double energyperphoton = 19.8e-9/filwave;
	    double starphotons     = flux / energyperphoton; // units: photons per 
	    
	    System.out.println("EXP:starphotons="+starphotons);
	    
	    double areaofdisk = (seeing * 1.5) * (seeing * 1.5);
	    
	    double pixelarea = pixelSize * pixelSize;
	    
	    double numberofpixels = areaofdisk/pixelarea;
	    
	    System.out.println("EXP:numberofpixels="+numberofpixels);
	    
	    double skyflux = zp * Math.pow(10.0,-0.4*skyMagnitude) * pixelarea;
	    flux = skyflux * telarea * telqe * ccdqe * filqe * filwidth / extinction;
	    energyperphoton = 19.8e-9/filwave;
	    double skyphotons = flux / energyperphoton;
	    
	    System.out.println("EXP:skyphotons/pixel="+skyphotons);
	    
	    skyphotons = skyphotons * numberofpixels;
	    
	    double temp = snr * snr * starphotons + snr*snr*skyphotons;
	    
	    double root = Math.sqrt(temp*temp + 
				    4 * starphotons * starphotons * readnoise *
				    readnoise*numberofpixels*snr*snr);
	    
	    double texp = (snr * snr * starphotons + snr*snr*skyphotons + root) /
		(2.0 * starphotons * starphotons);	
	    
	    int iexp = (int)(texp + 1);
	    System.err.println("EXP:Calculated exposure: "+iexp+" secs.");

	    return iexp*1000.0;

	}  

	/** Calculates the exposure time (millis) for the CCD  
	 * configured with the supplied InstrumentConfig.
	 * @param config          The InstrumentConfig to use.
	 * @param sourceMagnitude The magnitude of the star for which the exposure is to be determined.
	 * @param snr             The snr for this exposure.
	 * @param skyMagnitude    Magnitude of the sky (mags.arcsec<sup>-2</sup>).
	 * @param seeing          The current seeing (arcsec).
	 * @return                The exposure time in millis.
	 */
	public double calculateExposureTime(InstrumentConfig config,
					    double           sourceMagnitude,
					    double           snr, 
					    double           skyMagnitude, 
					    double           seeing) {
	    
	    // Save the current config, re-configure with new config, 
	    // calc time, reset config to current.
	    CCDConfig current = (CCDConfig)getConfig("");
	    configure(config);
	    double time = calculateExposureTime(sourceMagnitude, snr, skyMagnitude, seeing);
	    configure(current);
	    return time;
	}
	
    }

    
	
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:38:11  cjm
/** Initial revision
/**
/** Revision 1.1  2001/02/23 18:46:58  snf
/** Initial revision
/** */
