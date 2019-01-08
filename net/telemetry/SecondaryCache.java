/**
 * 
 */
package ngat.net.telemetry;

import java.util.List;

/**
 * @author eng
 *
 */
public interface SecondaryCache {

	
	/** Store the supplied status in the secondary cache.
	 * @param status
	 * @throws Exception
	 */
	public void storeStatus(StatusCategory status) throws Exception;
	
	/** Retrieve a block of statii from the secondary cache.
	 * @param t1 Start time of block.
	 * @param t2 End time of block.
	 * @return The block of statii.
	 * @throws Exception
	 */
	public List<StatusCategory> retrieveStatus(long t1, long t2) throws Exception;
}
