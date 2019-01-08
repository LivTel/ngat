package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_NEWS_DONE.<br>
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
public class GET_NEWS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text>*/
	protected List news;

	/** Create a GET_NEWS_DONE with specified id.
	 * @param id The unique id of this GET_NEWS_DONE.
	 */
	public GET_NEWS_DONE (String id) { super(id); }

	/** Set the A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text>
	 * @param news The A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text>.
	 */
	public void setNews(List news) { this.news = news; }

	/** Get the A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text>
	 * @return The A java.util.List of news items as Strings in form: Index: <index>\n Date: <date>\n Text: <text>
	 */
	public List getNews() { return news; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", news="+news+"]";
	}
	// Hand generated code.

} // class def. [GET_NEWS_DONE].

