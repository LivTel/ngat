package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;
import ngat.phase2.util.*;
import ngat.util.logging.*;

import com.odi.*;
import com.odi.util.*;

import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.io.*;

/**  
 * A Group contains details of a set of Observations to perform. This is
 * the minumum schedulable entity as regards robotic operation. The schedule()
 * method is used to calculate a score for the Group and performs any vetoing
 * with respect to current observing environment and predefined constraints.
 * The folowing fields are used in scoring:- ##TBD ##
 * <br><br>
 * $Id: Group.java,v 1.3 2001-02-23 18:45:20 snf Exp $
 */
public class Group extends DBObject implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 2728957598013116145L;
     
    // Variables.
    
    /**  Indicator to whether this Group has been successfully scheduled. */
    protected boolean done;
    
    /**  Date at which this Group MUST have been completed to avoid being discarded. */
    protected long expiryDate;
    
    /**  Date this Group was scheduled (if Done). */
    protected long doneDate;
    
    /**  Bitmask to identify poorest conditions under which this Group can be scheduled. */
    protected int minimumConditions;

    /** Holds the set of Observations for this Group.*/
    protected OSHashMap observations;

    // Constructor.
    
    public Group() {this("untitled");}
    
    public Group(String name) {
	super(name);
	observations = new OSHashMap();
    }
    
    // Accessors.
    
    /** Sets the  indicator to whether this Group has been successfully scheduled .*/
    public void setDone(boolean in) { this.done = in;}
    
    /** True if  indicator to whether this Group has been successfully scheduled. */
    public boolean isDone() { return done;}
    
    /** Sets the  date at which this Group MUST have been completed to avoid being discarded .*/
    public void setExpiryDate(long in) { this.expiryDate = in;}
    
    /** Returns the  date at which this Group MUST have been completed to avoid being discarded. */
    public long getExpiryDate() { return expiryDate;}
    
    /** Sets the  date this Group was scheduled (if Done) .*/
    public void setDoneDate(long in) { this.doneDate = in;}
    
    /** Returns the  date this Group was scheduled (if Done). */
    public long getDoneDate() { return doneDate;}
    
    /** Sets the  bitmask to identify poorest conditions under which this Group can be scheduled .*/
    public void setMinimumConditions(int in) { this.minimumConditions = in;}
    
    /** Returns the  bitmask to identify poorest conditions under which this Group can be scheduled. */
    public int getMinimumConditions() { return minimumConditions;}
    
    // Descendant Mutators.
    
    // Observation Methods.
    
    public void addObservation(Observation observation) {
	observations.put(observation.getName(), observation);
	observation.setPath(getPath() + "/" + getName());
    }
    
    public void deleteObservation(Observation observation) {
	observations.remove(observation.getName());
    }
    
    public void removeAllObservations() {
	observations.clear();
    }
    
    public Iterator listAllObservations() {
	return observations.values().iterator();
    }
    
    public Observation findObservation(String name) {
	if (observations.containsKey(name)) return ((Observation)observations.get(name));
	return null;
    }
    
    public OSHashMap getObservations() { return observations;}
    
    // NP -> P Translator.
    public Group(NPGroup npGroup) throws InvocationTargetException {
	super(npGroup);
	Iterator it;
	done = npGroup.isDone();
	expiryDate = npGroup.getExpiryDate();
	doneDate = npGroup.getDoneDate();
	minimumConditions = npGroup.getMinimumConditions();
	
	// Recursively call Daughter Translators.
	observations = new OSHashMap();
	it = npGroup.listAllNPObservations();
	while (it.hasNext()) {
	    String npName = null;
	    try {
		NPObservation npObservation = (NPObservation)it.next();
		Class npClazz = npObservation.getClass();
		npName = npClazz.getName();
		int k = npName.indexOf("nonpersist.NP");
		String pName = npName.substring(0,k).concat(npName.substring(k+13));
		Class pClazz = Class.forName(pName);
		Constructor pCon = pClazz.getConstructor(new Class[]{npClazz});
		Observation observation = (Observation)pCon.newInstance(new Object[]{npObservation});
		addObservation(observation);
	    } catch (Exception re1){
		throw new InvocationTargetException(e,"Translating Group ["+name+
						    "] observation ["+npName+"] from NP to P version");
	    } 
	}
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    public void stuff(NPGroup npGroup) {
	super.stuff(npGroup);
	Iterator it;
	npGroup.setDone(isDone());
	npGroup.setExpiryDate(getExpiryDate());
	npGroup.setDoneDate(getDoneDate());
	npGroup.setMinimumConditions(getMinimumConditions());
	it = listAllObservations();
	while (it.hasNext()) {
	    npGroup.addNPObservation((NPObservation)(((Observation)it.next()).makeNP()));
	}
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPGroup npGroup = new NPGroup();
	stuff(npGroup);
	return npGroup;
    } // end (makeNp).
    
    
    // Child lock / update  methods. Handcoded to override O2J generated Group.java source.          
    public void lock(int key) {
     	setLock(key);
    }
    
    public void updateChildren() {
	Iterator it  = listAllObservations();
	while (it.hasNext()) {
	    Observation obo = (Observation)it.next();
	    obo.setPath(path+"/"+name);
	}
    }
    
    // Obo's cant be locked they are subatomic.     
    public boolean canLock() {
	if (isLocked()) return false;
	return true;
    }
    
    public void lockChildren(int key) {
	setLock(key);
    }
    
    public void unLockChildren(int key) {
	unLock(key);
    }
    
    public void forceUnLock() {
     	lock = 0;
    }	
    
    /**
     * Group's getNextFixedGroup method null?.
     */
    public FixedGroup getNextFixedGroup() {
	return null;
    }
    
    /**
     * Group's scheduling algorithm  - handcoded. Insert in the O2J generated Group.java source.
     */
    public ScheduleDescriptor schedule(Proposal proposal) {
     	Logger logger = LogManager.getLogger("SCHEDULE");

	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss - dd/MM/yyyy");
	
	double time = 0.0;
	float score = 0.0f;
	
	int mosaic = 0;
	int multruns = 1;
	
	double multrunTime = 0.0;
	double mosaicTime  = 0.0;
	double exposeTime = 0.0;

	/** Time required to reposition between mosaics (needs calibaration). ## CONFIG. */
	double mosaicRepositionTime = 2000.0;
	
	/** Time required for a readout from the chip (needs calibration). ## CONFIG. */
	double multrunReadoutTime = 20000.0;
	
	double totalExposeTime = 0.0;
	double totalExecutionTime = 0.0;

	// Slew variables.
	double slew = 0.0;
	double totalSlewTime = 0.0;
	double initSlewTime  = 0.0;
	double finalSlewTime = 0.0;

	// Inst. Reconfig variables.
	double initInstrumentReconfigTime = 0.0;
	double totalInstrumentReconfigTime = 0.0;
	double finalInstrumentReconfigTime = 0.0;

	// Tele Reconfig variables.
	double initTelescopeReconfigTime = 0.0;
	double totalTelescopeReconfigTime = 0.0;
	double finalTelescopeReconfigTime = 0.0;

	// Pipeline Reconfig variables.
	double initPipelineReconfigTime = 0.0;
	double totalPipelineReconfigTime = 0.0;
	double finalPipelineReconfigTime = 0.0;
 
	boolean allow = true; 

	int moves = 0;
	
	// Current settings.
	Position         currentTelescopePosition = TelescopeStatus.getTelescopePosition();
	InstrumentConfig currentInstrumentConfig  = TelescopeStatus.getCurrentInstrumentConfig();
	TelescopeConfig  currentTelescopeConfig   = TelescopeStatus.getCurrentTelescopeConfig();
	PipelineConfig   currentPipelineConfig    = TelescopeStatus.getCurrentPipelineConfig();

	// Next settings.
	Position         nextTelescopePosition  = null;
	InstrumentConfig nextInstrumentConfig   = null;
	TelescopeConfig  nextTelescopeConfig    = null;
	PipelineConfig   nextPipelineConfig     = null;
	
	logger.log(1,"Group: "+getFullPath());
	logger.log(1,"****************");
	// Iterate over all observations to calculate total expected exec time.
	logger.log(1,"Checking observations for exec time:");
	logger.log(1,"Current Time: "+sdf.format(new Date()));
	// XXXXXlogger.log(1,"Current LST:  "+Position.toHMSString(Astrometry.computeLST(XXXlongitudeXXX)));
	Iterator it = listAllObservations();    
	while (it.hasNext()) {	     
	    moves++;
	    
	    Observation observation = (Observation)it.next();
	    logger.log(1,"Observation: "+observation.getName());
	    Source source = observation.getSource();
	    if (source == null) return new ScheduleDescriptor(null, 0.0f, 0); // Can't be done.
	    logger.log(1,"source: "+source.getName());

	    // Get the obo's Position and configuration.
	    nextTelescopePosition = source.getPosition();
	    logger.log(1,"Posn: "+source.getPosition().toString());
	    
	    nextInstrumentConfig  = observation.getInstrumentConfig();
	    logger.log(1,"I/c: "+observation.getInstrumentConfig().getName());

	    nextTelescopeConfig   = observation.getTelescopeConfig();
	    logger.log(1,"T/c: "+ observation.getTelescopeConfig().getName());

	    nextPipelineConfig    = observation.getPipelineConfig();
	    logger.log(1,"P/c: "+ observation.getPipelineConfig().getName());
	    // Add up all the contributions to Obo's time.
	    
	    // First Obo, get slew and reconfig times from current position/configs to first obo's.
	    if (moves == 1) { 
//              initSlewTime               = currentTelescopePosition.getSlewTime(nextTelescopePosition);
//  		initInstrumentReconfigTime = currentInstrumentConfig.getReconfigurationTime(nextInstrumentConfig);
//  		initTelescopeReconfigTime  = currentTelescopeConfig.getReconfigurationTime(nextTelescopeConfig);
//  		initPipelineReconfigTime   = currentPipelineConfig.getReconfigurationTime(nextPipelineConfig);
		initSlewTime               = 1000.0;
		initInstrumentReconfigTime = 1001.0;
		initTelescopeReconfigTime  = 1002.0;
		initPipelineReconfigTime   = 1003.0;
	    }
	    
	    // Not first, add contrib to internal slew and reconfiguration times.
	    if (moves != 1) { 
		totalSlewTime               += currentTelescopePosition.getSlewTime(nextTelescopePosition);
		totalInstrumentReconfigTime += currentInstrumentConfig.getReconfigurationTime(nextInstrumentConfig);
		totalTelescopeReconfigTime  += currentTelescopeConfig.getReconfigurationTime(nextTelescopeConfig);
		totalPipelineReconfigTime   += currentPipelineConfig.getReconfigurationTime(nextPipelineConfig);
	    }
	    
	    // Mosaics.
	    mosaic = 1;
	    if (observation.isMosaicCross()) {
		mosaic = 5;
	    } else if
		(observation.isMosaicArray()) {
		mosaic = observation.getMosaicRACells()*observation.getMosaicDecCells();
	    }
	    logger.log(1,"Mosaics: "+mosaic);
	    
	    // Multruns.
	    multruns = observation.getNumRuns();
	    logger.log(1,"Multruns: "+multruns);
	    
	    
	    // Exposure Time contributions.
	    // multrunReadoutTime = k.sigma(Wxi.Wyi/N^2)/(bx.by) ##TEMP
	    multrunTime = mosaic*multruns*multrunReadoutTime;
	    mosaicTime  = mosaic*mosaicRepositionTime;
	    exposeTime  = mosaic*multruns*observation.getExposeTime();
	    
	    logger.log(1,"Readout time: "+multrunTime/1000.0);
	    logger.log(1,"Mosaic moves: "+mosaicTime/1000.0);
	    logger.log(1,"Expose  time: "+exposeTime/1000.0);

	    totalExposeTime += multrunTime + mosaicTime + exposeTime;
	    logger.log(1,"Total for obs: "+totalExposeTime/1000.0);

	    // Reposition and reconfigure the Telescope between Obs.
	    currentTelescopePosition = nextTelescopePosition;
	    currentInstrumentConfig  = nextInstrumentConfig;
	    currentTelescopeConfig   = nextTelescopeConfig;
	    currentPipelineConfig    = nextPipelineConfig;
	    
	}
    
	logger.log(1,"Done checking observations for exec time:");

	// Add on time to allow slewing and reconfiguration to next FixedGroup.
	// if the time constraint is such. ### these just return a fixed amount for now.
	Position nfgPosn = Scheduling.getNextFGroupPosition();
	finalSlewTime               = currentTelescopePosition.getSlewTime(nfgPosn);
	finalInstrumentReconfigTime = currentInstrumentConfig.getReconfigurationTime(null);
	finalTelescopeReconfigTime  = currentTelescopeConfig.getReconfigurationTime(null);
	finalPipelineReconfigTime   = currentPipelineConfig.getReconfigurationTime(null);
	logger.log(1,"Total reposition and reconfiguration times:");
	logger.log(1,"Slew  reposn time: "+totalSlewTime/1000.0);
	logger.log(1,"I/c reconfig time: "+totalInstrumentReconfigTime/1000.0);
	logger.log(1,"T/c reconfig time: "+totalTelescopeReconfigTime/1000.0);
	logger.log(1,"P/c reconfig time: "+totalPipelineReconfigTime/1000.0);
	    
	// OR ignore if its a SUN or MOON constraint
	// OR work out a probability based estimate if we have a sliding
	// time constraint.
	
	totalExecutionTime = totalExposeTime+
	    totalSlewTime+
	    totalInstrumentReconfigTime+
	    totalTelescopeReconfigTime+
	    totalPipelineReconfigTime;

	logger.log(1,"Total exec time: "+totalExecutionTime/1000.0);

	// Iterate over all observations.
	it = listAllObservations();
	logger.log(1,"Scoring observations:");
	logger.log(1,"--------------------");
	int numObs = 0;
	while (it.hasNext()) {	 
	    numObs++;
	    Observation observation = (Observation)it.next();   
	    // obs needs total exectime to find out if the source will remain
	    // above the dome limits for the full period ..
	    logger.log(1,"Scoring observation...");
	    score = score + observation.getScore(totalExecutionTime);
	    
	    logger.log(1,"Vetoing observation...");
	    // AND the Observation veto functions. ## WATCH THIS !!
	    allow = allow & (observation.getAllow(totalExecutionTime));
	} 
	score /= numObs;
	logger.log(1,"After all obo scores ALLOW: "+allow);
	logger.log(1,"After all obo scores SCORE: "+score);

	// Apply Group's contribution to scoring.
	// -------------------------------------
	
	// 1. Slew time function. (only use the internal slewing time between sources).	 
	WeightingParameters slewParams = Scheduling.getSlewFnParams();	
	score += slewParams.evaluate(initSlewTime + totalSlewTime);
	logger.log(1,"..Scores:  SLEW: "+slewParams.evaluate(initSlewTime + totalSlewTime));
	
	// 2. Lateness function.         
	WeightingParameters latenessParams = Scheduling.getLatenessFnParams();	
	score += latenessParams.evaluate(expiryDate - Scheduling.getNow());
	logger.log(1,"lateness: "+((expiryDate - Scheduling.getNow())/1000.0));
	logger.log(1,"..Scores:  LATENESS: "+latenessParams.evaluate(expiryDate - Scheduling.getNow()));
	
	// 3. Reconfig function.
	//  WeightingParameters reconfigParams = Scheduling.getReconfigFnParams();
	//  	score += reconfigParams.evaluate(totalInstrumentReconfigTime+
	//  					 totalTelescopeReconfigTime+
	//  					 totalPipelineReconfigTime+
	//  					 initInstrumentReconfigTime+
	//  					 initTelescopeReconfigTime+
	//  					 initPipelineReconfigTime);
	
	// Apply generic schedule-coefft.
	score = score*schedCoeff; // ## For now. 
	logger.log(1,"..Scores:  PRIORITY: "+ schedCoeff);
	
	// Do any Group level vetoing.
	// --------------------------
	
	// 1. FixedGroup time veto (OR other time-constraint).
	// ### Uses NOW from SIMULATION ie NOT REAL TIME ###     
	allow = allow & (totalExecutionTime + Scheduling.getNow() < Scheduling.getNextFGroupTime());
	logger.log(1,"..Allow:   EXEC: "+ (totalExecutionTime + Scheduling.getNow() < Scheduling.getNextFGroupTime()));
	
	// 2. Excessive slew time veto.      
	allow = allow & (slewParams.inRange(slew));
	logger.log(1,"..Allow:   SLEW: "+(slewParams.inRange(slew)));
	
	// 3. Expiry date exceeded veto.    
	allow = allow & (expiryDate > Scheduling.getNow());
	logger.log(1,"..Allow: EXPIRY: "+(expiryDate > Scheduling.getNow()));

	// 4. Already Done veto.      
	allow = allow & (!isDone());
	logger.log(1,"..Allow:   DONE: "+!isDone());
 
	// 5. if (group needs GOOD seeing and moon will rise before group is done -> veto)
	if ((minimumConditions & 0x0008) == 0x0008) {
	    logger.log(1, "Group requires DARK CONDITIONS");
	    Position moon = Astrometry.getLunarPosition();
	    if (moon.isRisen()) {
		logger.log(1,"Moon is up  -> sky is BRIGHT -> VETO"+
			   "..Allow   MOON: false");
		allow = false;
	    }
	    
	    if (moon.isSet()) {
		logger.log(1,"Moon is Below Horizon. - sky is DARK");
		logger.log(1,"Time left till moonrise: "+(moon.getDownTimeMillis()/1000.0)+"secs");
		if (moon.getDownTimeMillis() < totalExecutionTime) {
		    logger.log(1, "Moon will rise before group has finished"+
			       "..Allow   MOON: false");
		    allow = false;
		}
	    }
	}

	// 6. if group uses min-conditions X and will take time T and time-left under conditons X
	//    in Proposal < T -> veto.
	
	logger.log(1,"*Finally  ALLOW: "+allow);
	logger.log(1,"*Finally  SCORE: "+score);
	logger.log(1,"-------------------");
	if (allow) { 
	    return new ScheduleDescriptor(this, score, (long)time); // Nominate this Group.
	}
	
	return new ScheduleDescriptor(null, 0.0f, 0); // Can't be done.
	
    }
    
} // end class def [Group].

