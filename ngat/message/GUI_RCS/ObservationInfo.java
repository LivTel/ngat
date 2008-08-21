package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates Observation update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public class ObservationInfo extends TelemetryInfo {

    /** The observation being performed.*/
    protected Observation observation;

    /** Program ID.*/
    protected String programId;

    /** True if the observation is a standard.*/
    protected boolean standard;

    /** True if the observation is for a fixed time.*/
    protected boolean fixed;

    /** True if the observation requires DP(RT) reduction.*/
    protected boolean reduce;
    
    /** Create an empty ObservationInfo.*/
    public ObservationInfo(long time) {
	super(time);
    }

    /** Set the Observation.*/
    public void setObservation(Observation observation) { this.observation = observation; }

    /** Return the Observation.*/
    public Observation getObservation() { return observation; }

    /** Set the Program ID.*/
    public void setProgramId(String programId) { this.programId = programId; }

    /** Return the Program ID.*/
    public String getProgramId() { return programId; }

    /** Set True if the observation is a standard.*/
    public void setStandard(boolean standard) { this.standard = standard; }

    /** Return True if the observation is a standard.*/
    public boolean isStandard() { return standard; }
 
    /** Set True if the observation is for a fixed time.*/
    public void setFixed(boolean fixed) { this.fixed = fixed; }

    /** Return Trueif the observation is for a fixed time.*/
    public boolean isFixed() { return fixed; }

    /** Set True if the observation requires DP(RT) reduction.*/
    public void setReduce(boolean reduce) { this.reduce = reduce; }

    /** Return True if the observation requires DP(RT) reduction.*/
    public boolean isReduce() { return reduce; }

    public String toString() {
	return "ObservationInfo: "+time+" : Program="+programId+
	    ", Standard="+standard+
	    ", Fixed="+fixed+
	    ", Observation="+observation;
    }

}
