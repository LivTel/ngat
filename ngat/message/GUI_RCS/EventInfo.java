package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates Event update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public class EventInfo extends TelemetryInfo {

    protected String topic;

    public EventInfo(long time) {
	super(time);
    }

    public void setTopic(String topic) { this.topic = topic; }
    
    public String getTopic() { return topic; }

    public String toString() {
	return "EventInfo: "+time+" : Topic="+topic;
    }
    
}
