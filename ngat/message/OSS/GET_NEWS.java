package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_NEWS.<br>
 * Command: GET_NEWS<br>
 * Retrieves the current OSS system news.<br>
 * Module code: 701400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>fromIndex - The index of the earliest sequential news item required</dd>
 * <dd>&nbsp;values: index <= current news index</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>news - A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text></dd>
 * </dl>
 * <br>
 */
public class GET_NEWS extends TRANSACTION {

	// Variables.

	/** The The index of the earliest sequential news item required*/
	protected int fromIndex;

	/** Create a GET_NEWS with specified id.
	 * @param id The unique id of this GET_NEWS.
	 */
	public GET_NEWS(String id) { super(id); }

	/** Create a GET_NEWS with specified id and parameters.
	 * @param id The unique id of this GET_NEWS.
	 * @param fromIndex The The index of the earliest sequential news item required
	 */
	public GET_NEWS(
	         String id,
	         int fromIndex) {
	         super(id);
	           this.fromIndex = fromIndex;
	         }

	/** Set the The index of the earliest sequential news item required
	 * @param fromIndex The The index of the earliest sequential news item required
	 */
	public void setFromIndex(int fromIndex) { this.fromIndex = fromIndex; }

	/** Get the The index of the earliest sequential news item required
	 * @return The The index of the earliest sequential news item required
	 */
	public int getFromIndex() { return fromIndex; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", fromIndex="+fromIndex+"]";
	}
	// Hand generated code.

} // class def. [GET_NEWS].

