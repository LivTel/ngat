package ngat.util;


/**
 * Generic container for status results.
 */

import java.io.*;

/**
 * @author snf
 *
 */
public interface IStatus extends Serializable {

    /** @return the status represented by this IStatus implementation instance.*/
	public StatusCategory getStatusCategory();
	
    /** @return the name of the status category identifier.*/
	public String getCategory();
	
}
