package ngat.util;


/**
 * 
 */

import java.io.*;
/**
 * @author snf
 *
 */
public interface IStatus extends Serializable {

	public StatusCategory getStatusCategory();
	
	public String getCategory();
	
}
