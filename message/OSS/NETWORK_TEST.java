package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: NETWORK_TEST.<br>
 * Command: NETWORK_TEST<br>
 * Requests the OSS to return a number of blocks of data.<br>
 * Currently this is broken<br>
 * Module code: 702000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>blocks - null</dd>
 * <dd>&nbsp;values: Number of blocks of data to return</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class NETWORK_TEST extends TRANSACTION {

	// Variables.

	/** The null*/
	protected int blocks;

	/** Create a NETWORK_TEST with specified id.
	 * @param id The unique id of this NETWORK_TEST.
	 */
	public NETWORK_TEST(String id) { super(id); }

	/** Create a NETWORK_TEST with specified id and parameters.
	 * @param id The unique id of this NETWORK_TEST.
	 * @param blocks The null
	 */
	public NETWORK_TEST(
	         String id,
	         int blocks) {
	         super(id);
	           this.blocks = blocks;
	         }

	/** Set the null
	 * @param blocks The null
	 */
	public void setBlocks(int blocks) { this.blocks = blocks; }

	/** Get the null
	 * @return The null
	 */
	public int getBlocks() { return blocks; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", blocks="+blocks+"]";
	}
	// Hand generated code.

} // class def. [NETWORK_TEST].

