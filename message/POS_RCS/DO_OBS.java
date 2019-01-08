package ngat.message.POS_RCS;

import ngat.phase2.*;

public class DO_OBS extends POS_TO_RCS {

    protected Observation observation;

    public DO_OBS(String id) {
	super(id);
    }

    public void setObservation(Observation obs) {this.observation = observation; }

    public Observation getObservation() { return observation; }
    
}

