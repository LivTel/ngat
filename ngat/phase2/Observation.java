package ngat.phase2;
import ngat.phase2.nonpersist.*;
import ngat.phase2.util.*;
import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class Observation extends DBObject implements Serializable {
    
    // Variables.

    private transient boolean logging;

    /**  number of millis for the exposure. */
    protected float exposeTime;

    /**  indicator of whether the exposure time should be corrected for conditions. */
    protected boolean conditionalExposure;
    
    /**  number of (MULTRUN) exposures to take. */
    protected int numRuns;
    
    /**  filepath (or URL) containing the data for this Observation. */
    protected String dataLocation;
    
    /**  indicator of whether this obs has an associated  mosaic array. */
    protected boolean mosaicArray;
    
    /**  indicator of whether this obs has an associated  mosaic cross. */
    protected boolean mosaicCross;
    
    /**  gap between mosaic RA offsets (rads). */
    protected double mosaicRAOffset;
    
    /**  gap between mosaic Dec offsets (rads). */
    protected double mosaicDecOffset;
    
    /**  number of RA offsets in array/cross. */
    protected int mosaicRACells;
    
    /**  number of Dec offsets in array/cross. */
    protected int mosaicDecCells;

    /**  distance from meridian at which the source may be observed (rads). */
    protected double meridianLimit;
    
    /** determines how twilight time is to be used by this observation. */
    protected int twilightUsageMode;

    /**  reference to Telescope Configuration used. */
    protected TelescopeConfig telescopeConfig;
    
    /**  reference to Instrument Configuration used. */
    protected InstrumentConfig instrumentConfig;
    
    /**  reference to Pipeline reduction Configuration used. */
    protected PipelineConfig pipelineConfig;
    
    /**  reference to the observed Source object. */
    protected Source source;
    
     // Constructor.
    public Observation() {this("untitled");}
    
    public Observation(String name) {
	super(name);
    }
    
    // Accessors.   
    
    /** Sets the  number of millis for the exposure .*/
    public void setExposeTime(float in) { this.exposeTime = in;}
    
    /** Returns the  number of millis for the exposure. */
    public float getExposeTime() { return exposeTime;}
    
    /** Sets the  indicator of whether the exposure time should be corrected for conditions .*/
    public void setConditionalExposure(boolean in) { this.conditionalExposure = in;}
    
    /** True if  indicator of whether the exposure time should be corrected for conditions. */
    public boolean isConditionalExposure() { return conditionalExposure;}
    
    /** Sets the  number of (MULTRUN) exposures to take .*/
    public void setNumRuns(int in) { this.numRuns = in;}

    /** Returns the  number of (MULTRUN) exposures to take. */
    public int getNumRuns() { return numRuns;}
    
    /** Sets the  filepath (or URL) containing the data for this Observation .*/
    public void setDataLocation(String in) { this.dataLocation = in;}
    
    /** Returns the  filepath (or URL) containing the data for this Observation. */
    public String getDataLocation() { return dataLocation;}
    
    /** Sets the  indicator of whether this obs has an associated  mosaic array .*/
    public void setMosaicArray(boolean in) { this.mosaicArray = in;}
    
    /** True if  indicator of whether this obs has an associated  mosaic array. */
    public boolean isMosaicArray() { return mosaicArray;}
    
    /** Sets the  indicator of whether this obs has an associated  mosaic cross .*/
    public void setMosaicCross(boolean in) { this.mosaicCross = in;}
    
    /** True if  indicator of whether this obs has an associated  mosaic cross. */
    public boolean isMosaicCross() { return mosaicCross;}
    
    /** Sets the  gap between mosaic RA offsets (rads) .*/
    public void setMosaicRAOffset(double in) { this.mosaicRAOffset = in;}
    
    /** Returns the  gap between mosaic RA offsets (rads). */
    public double getMosaicRAOffset() { return mosaicRAOffset;}
    
    /** Sets the  gap between mosaic Dec offsets (rads) .*/
    public void setMosaicDecOffset(double in) { this.mosaicDecOffset = in;}
    
    /** Returns the  gap between mosaic Dec offsets (rads). */
    public double getMosaicDecOffset() { return mosaicDecOffset;}
    
    /** Sets the  number of RA offsets in array/cross .*/
    public void setMosaicRACells(int in) { this.mosaicRACells = in;}
    
    /** Returns the  number of RA offsets in array/cross. */
    public int getMosaicRACells() { return mosaicRACells;}
    
    /** Sets the  number of Dec offsets in array/cross .*/
    public void setMosaicDecCells(int in) { this.mosaicDecCells = in;}
    
    /** Returns the  number of Dec offsets in array/cross. */
    public int getMosaicDecCells() { return mosaicDecCells;}
    
    /** Sets the  distance from meridian at which the source may be observed (rads) .*/
    public void setMeridianLimit(double in) { this.meridianLimit = in;}
    
    /** Returns the  distance from meridian at which the source may be observed (rads). */
    public double getMeridianLimit() { return meridianLimit;}

    /** Stes the mode for twilight usage.*/
    public void setTwilightUsageMode(int mode) { twilightUsageMode = mode;}

    /** Returns the  mode for twilight usage.*/
    public int getTwilightUsageMode() { return  twilightUsageMode;}

    /** Sets the  reference to Telescope Configuration used .*/
    public void setTelescopeConfig(TelescopeConfig in) { this.telescopeConfig = in;}
    
    /** Returns the  reference to Telescope Configuration used. */
    public TelescopeConfig getTelescopeConfig() { return telescopeConfig;}
    
    /** Sets the  reference to Instrument Configuration used .*/
    public void setInstrumentConfig(InstrumentConfig in) { this.instrumentConfig = in;}
    
    /** Returns the  reference to Instrument Configuration used. */
    public InstrumentConfig getInstrumentConfig() { return instrumentConfig;}
    
    /** Sets the  reference to Pipeline reduction Configuration used .*/
    public void setPipelineConfig(PipelineConfig in) { this.pipelineConfig = in;}
    
    /** Returns the  reference to Pipeline reduction Configuration used. */
    public PipelineConfig getPipelineConfig() { return pipelineConfig;}
    
    /** Sets the  reference to the observed Source object .*/
    public void setSource(Source in) { this.source = in;}
    
    /** Returns the  reference to the observed Source object. */
    public Source getSource() { return source;}
    
    // Descendant Mutators.
    
    // NP -> P Translator.
    public Observation(NPObservation npObservation) {
	super(npObservation);
	Iterator it;
	exposeTime = npObservation.getExposeTime();
	conditionalExposure = npObservation.isConditionalExposure();
	numRuns = npObservation.getNumRuns();
	dataLocation = npObservation.getDataLocation();
	mosaicArray = npObservation.isMosaicArray();
	mosaicCross = npObservation.isMosaicCross();
	mosaicRAOffset = npObservation.getMosaicRAOffset();
	mosaicDecOffset = npObservation.getMosaicDecOffset();
	mosaicRACells = npObservation.getMosaicRACells();
	mosaicDecCells = npObservation.getMosaicDecCells();
	meridianLimit = npObservation.getMeridianLimit();
	twilightUsageMode = npObservation.getTwilightUsageMode();
	
    } // end (NP -> P Translator).
     
    // P -> NP Translator.   
    public void stuff(NPObservation npObservation) {
	super.stuff(npObservation);
	Iterator it;
	npObservation.setExposeTime(getExposeTime());
	npObservation.setConditionalExposure(isConditionalExposure());
	npObservation.setNumRuns(getNumRuns());
	npObservation.setDataLocation(getDataLocation());
	npObservation.setMosaicArray(isMosaicArray());
	npObservation.setMosaicCross(isMosaicCross());
	npObservation.setMosaicRAOffset(getMosaicRAOffset());
	npObservation.setMosaicDecOffset(getMosaicDecOffset());
	npObservation.setMosaicRACells(getMosaicRACells());
	npObservation.setMosaicDecCells(getMosaicDecCells());
	npObservation.setMeridianLimit(getMeridianLimit());	
	npObservation.setTwilightUsageMode(getTwilightUsageMode());
    } // end (P -> NP Translator).
    
    // P -> NP Translator.   
    public NPDBObject makeNP() {
	NPObservation npObservation = new NPObservation();
	stuff(npObservation);
	return npObservation;
    } // end (makeNp).     
    
    /**
     * Observation schedule scoring methods. Handcoded. Insert in O2J generated Source.java file.
     */    
    public float getScore(StringBuffer buffer, double execTime, boolean dolog) {
	
	// Implement Observation Level efficiency weighting functions.
	// ----------------------------------------------------------
	float score = 0.0f;
	Position target = getSource().getPosition();
	double low = 0.34906585; // 20 degs
	
	logging = dolog; // ###TEMP

	log(buffer,"Observation: "+getName());
	log(buffer,"Source: RA: "+Position.toHMSString(target.getRA())+" dec: "+Position.toDMSString(target.getDec()));
	log(buffer,"       alt: "+Math.toDegrees(target.getAltitude())+" az: "+Math.toDegrees(target.getAzimuth()));
	log(buffer,"        HA: "+Position.toHMSString(target.getHA())+" transits at: "+Math.toDegrees(target.getTransitHeight()));
	if (target.isRisen(low)) log(buffer,"star is ABOVE dome limit.");
	if (target.isSet(low)) log(buffer,"star is BELOW dome limit.");
	if (target.isRising()) log(buffer,"star is RISING.");
	if (target.isSetting()) log(buffer,"star is SETTING.");
	
	if (!target.neverRises(low)) {
	    log(buffer,"star rises: "+Position.toHMSString(target.getRiseTime()));
	} else {
	    log(buffer,"star NEVER RISES.");
	}
	
	if (!target.neverSets(low)) {
	    log(buffer,"star  sets: "+Position.toHMSString(target.getSetTime()));
	} else {
	    log(buffer,"star NEVER SETS.");
	}

	log(buffer,"Twilight usage: "+twilightUsageMode);
	
	// Check sunrise/set
	Position sun = Scheduling.getSolarPosition();
	
	log(buffer,"Sun:   RA: "+Position.toHMSString(sun.getRA())+
	    " dec: "+Position.toDMSString(sun.getDec()));
	log(buffer,"       alt: "+Math.toDegrees(sun.getAltitude())+
	    "  az: "+Math.toDegrees(sun.getAzimuth()));
	log(buffer,"        HA: "+Position.toHMSString(sun.getHA())+
	    " transits at: "+Math.toDegrees(sun.getTransitHeight()));
	if (sun.isRisen()) {
	    log(buffer,"sun is ABOVE Horizon.");
	    log(buffer,"Time left till sunset: "+Position.toHMSString(sun.getUpTimeMillis()/13750987.08));
	    log(buffer,"End of evening twilight: "+Position.toHMSString(sun.getUpTimeMillis(-Math.toRadians(18.0))/13750987.08));
	}
	
	if (sun.isSet()) {
	    log(buffer,"sun is BELOW Horizon.");
	    if (sun.isRisen(-Math.toRadians(18.0))) {
		if (sun.isSetting()) {
		    log(buffer,"EVENING TWILIGHT TIME"); 
		    log(buffer,"Time till end of evening twilight: "+Position.toHMSString(sun.getUpTimeMillis(-Math.toRadians(18.0))/13750987.08));
		} else if
		    (sun.isRising()) {
		    log(buffer,"MORNING TWILIGHT TIME");
		    log(buffer,"Time till end of morning twilight: "+Position.toHMSString(sun.getDownTimeMillis(-Math.toRadians(18.0))/13750987.08));
		} 
	    }
	    log(buffer,"Time left till sunrise: "+Position.toHMSString(sun.getDownTimeMillis()/13750987.08));
	   
	}
	if (sun.isRising()) log(buffer,"sun is RISING.");
	if (sun.isSetting()) log(buffer,"sun is SETTING.");
	

	// 1. Height function.         
	WeightingParameters heightParams = Scheduling.getHeightFnParams(); 
	
	double ht = target.getAltitude();
	
	score += heightParams.evaluate(ht);
	log(buffer,"..Scores:  HEIGHT: "+heightParams.evaluate(ht));
	// OR Scheduling.calculateHeightFn(ht);
	
	// 2. Transit Height function.        
	WeightingParameters transitParams = Scheduling.getTransitFnParams(); 
	
	double th = target.getAltitude()/target.getTransitHeight();
	
	score += transitParams.evaluate(th);
	log(buffer,"..Scores: TRANSIT: "+transitParams.evaluate(th));
	// OR Scheduling.calculateTransitFn(th);
	
	// 3. Lunar angular distance.
	
	// 4. Meridian limits.
	
	// 5. Twilight. I,R,Z band and PUST.
	log(buffer,"..Scores: TOTAL: "+score);
	
	return score;
    }
    
    
    public boolean getAllow(StringBuffer buffer, double execTime, boolean dolog) {
	logging = dolog; // ###TEMP

	// Implement Veto functions.	
	boolean allow = true;
	Position target = getSource().getPosition();

	// 1. Height veto function.   
	WeightingParameters heightParams = Scheduling.getHeightFnParams();          
	double ht = target.getAltitude();
	
	allow &= heightParams.inRange(ht);
	log(buffer,"..Allow:   HEIGHT: "+heightParams.inRange(ht))
;
	// 2. Transit veto function.  (do we need this!)   
	WeightingParameters transitParams = Scheduling.getTransitFnParams();          
	double th = target.getAltitude()/target.getTransitHeight();
	 
	allow &= transitParams.inRange(ht);
	log(buffer,"..Allow:  TRANSIT: "+transitParams.inRange(th));
	
	// 3. Lunar angular distance veto function.
	// ##For now this is a sharp cutoff at 2.5 degrees.
	
	//allow &= lunarParams.inRange(lunarDistance);
	
	Position moon = Scheduling.getLunarPosition();
	
	log(buffer,"Moon:   RA: "+Position.toHMSString(moon.getRA())+
	    " dec: "+Position.toDMSString(moon.getDec()));
	log(buffer,"       alt: "+Math.toDegrees(moon.getAltitude())+
	    "  az: "+Math.toDegrees(moon.getAzimuth()));
	log(buffer,"        HA: "+Position.toHMSString(moon.getHA())+
	    " transits at: "+Math.toDegrees(moon.getTransitHeight()));
	if (moon.isRisen()) {
	    log(buffer,"moon is ABOVE Horizon.");
	    log(buffer,"Time left till DARK: "+(moon.getUpTimeMillis()/1000.0)+"secs");
	}

	if (moon.isSet()) {
	    log(buffer,"moon is BELOW Horizon.");
	    log(buffer,"Time left till BRIGHT: "+(moon.getDownTimeMillis()/1000.0)+"secs");
	}
	if (moon.isRising()) log(buffer,"moon is RISING.");
	if (moon.isSetting()) log(buffer,"moon is SETTING.");
	
	double lunarAngularDistance = target.getAngularDistance(moon);
	log(buffer, "lunar angle distance: "+Position.toDMSString(lunarAngularDistance));
	if (lunarAngularDistance < 0.0436) {
	    log(buffer,"..Allow: LUNAR DIST: false");
	    allow = false;
	}
	
	// 4. Meridian veto.
	long meridianDistance = (long)(target.getHA()*43200000.0/Math.PI);// in msecs
	log(buffer,"Meridian distance: "+meridianDistance+" msecs");
	if (meridianDistance > 43200000) meridianDistance = 86400000 - meridianDistance;
	if (meridianLimit > 1 &&  meridianDistance > meridianLimit) allow = false;
	log(buffer,"..Allow: MERIDIAN: "+(!( meridianLimit > 1 &&  meridianDistance > meridianLimit)));
	
	
	
	// 5. 
	
	// 6. Visible over full executionTime veto.
	// source must be above horizon for the full exectime.
	log(buffer,"Time left above dome limits: "+(target.getUpTimeMillis()/1000.0)+"secs");
	log(buffer,"Time needed for execution:   "+(execTime/1000.0)+"secs");
	if (target.getUpTimeMillis() < execTime) allow = false;
	log(buffer,"..Allow:  HORIZON: "+(target.getUpTimeMillis() > execTime));
	return allow;
    }


    private void log(StringBuffer buffer, String text) { 
	if (logging)
	    if (buffer != null) 
		buffer.append("\n"+text);
	    else
		System.out.println(text);
    }    


} // end class def [Observation].
