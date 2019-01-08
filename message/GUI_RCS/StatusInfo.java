package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.util.*;

/** Encapsulates a StatusCategory .
 */
public class StatusInfo extends TelemetryInfo {

    /** Describes this status category - typically it will be the name
     * of the category as used when obtaining the provider.
     */
    String cat;

    /** The actual status object which can be interrogated to extract
     * any individual data items.
     */
    StatusCategory status;

    /** Create an empty SpyInfo.*/
    public StatusInfo(long time, String cat, StatusCategory status) {
	super(time);
	this.cat = cat;
	this.status = status;
    }

    public StatusCategory getStatus() {
	return status;
    }


    public String getCat() { 
	return cat;
    }

}
