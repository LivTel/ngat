package ngat.message.GUI_RCS;

import java.io.*;

/** Encapsulates telemetry update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public abstract class TelemetryInfo implements Serializable {

    /** Timestamp.*/
    long time;

    public TelemetryInfo(long time) {
	this.time = time;
    }

}
