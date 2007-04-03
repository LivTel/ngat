package ngat.phase2;

import ngat.util.*;
import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.io.*;

/**  
 * A Group contains details of a set of Observations to perform. This is
 * the minumum schedulable entity as regards robotic operation. The schedule()
 * method is used to calculate a score for the Group and performs any vetoing
 * with respect to current observing environment and predefined constraints.
 * The folowing fields are used in scoring:- ##TBD ##
 * <br><br>
 * $Id: Group.java,v 1.7 2007-04-03 07:00:59 snf Exp $
 */
public class Group extends NPDBObject implements Serializable {
   
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 2728957598013116145L;

    /** Default minimum allowable lunar distance for observation source. */
    public static final double DEFAULT_MINIMUM_LUNAR_DISTANCE = Math.toRadians(2.5);

    /** Indicates that time between Sunset and bright twilight can be used.*/
    public static final int TWILIGHT_USAGE_DONTCARE = -1;

    /** Indicates that Twilight time can only be used.*/
    public static final int TWILIGHT_USAGE_ALWAYS   = 0;

    /** Indicates that Twilight time may be used.*/
    public static final int TWILIGHT_USAGE_OPTIONAL = 1;
    
    /** Indicates that Twilight time should NOT be used.*/
    public static final int TWILIGHT_USAGE_NEVER    = 2;

    // Alternative Sky bright/twilight settings, USE THESE FROM NOW ON//
    public static final int SKY_ANY             = -1;
    public static final int SKY_BRIGHT_TWILIGHT = 0;
    public static final int SKY_DARK_TWILIGHT   = 1;
    public static final int SKY_NIGHT           = 2;
    


    /** Nominal value of expected source-acquisition/slew time.*/
    public static final long DEFAULT_NOMINAL_ACQUIRE_TIME       = 60000L;

    /** Nominal value of expected instrument and telescope-configuration times.*/
    public static final long DEFAULT_NOMINAL_CONFIGURATION_TIME = 5000L;
    
    /** Nominal value of expected source-offset time.*/
    public static final long DEFAULT_NOMINAL_OFFSET_TIME        = 5000L;
    
    /** Nominal value of expected instrument-readout time.*/
    public static final long DEFAULT_NOMINAL_READOUT_TIME       = 20000L;

    /** Maximal value of expected instrument and telescope-configuration times.*/
    public static final long DEFAULT_MAXIMAL_CONFIGURATION_TIME = 60000L;

    /** Maximal value of expected source-acquisition time.*/
    public static final long DEFAULT_MAXIMAL_ACQUIRE_TIME       = 10000L;
    
    // Actuals

    /** Nominal value of expected source-acquisition/slew time.*/
    public static long NOMINAL_ACQUIRE_TIME       = DEFAULT_NOMINAL_ACQUIRE_TIME;

    /** Nominal value of expected instrument and telescope-configuration times.*/
    public static long NOMINAL_CONFIGURATION_TIME = DEFAULT_NOMINAL_CONFIGURATION_TIME;
    
    /** Nominal value of expected source-offset time.*/
    public static long NOMINAL_OFFSET_TIME        = DEFAULT_NOMINAL_OFFSET_TIME;
    
    /** Nominal value of expected instrument-readout time.*/
    public static long NOMINAL_READOUT_TIME       = DEFAULT_NOMINAL_READOUT_TIME;

    /** Maximal value of expected instrument and telescope-configuration times.*/
    public static long MAXIMAL_CONFIGURATION_TIME = DEFAULT_MAXIMAL_CONFIGURATION_TIME;

    /** Maximal value of expected source-acquisition time.*/
    public static long MAXIMAL_ACQUIRE_TIME       = DEFAULT_MAXIMAL_ACQUIRE_TIME;

    // These next ones are really inst stuff...or telescope stuff.

    /** Maximal value of expected instrument-readout time.*/
    public static  long MAXIMAL_READOUT_TIME       = 20000L;

    /** Maximal value of expected data-pipeline-processing time.*/
    public static  long MAXIMAL_DPRT_TIME          = 60000L;
    
    /** Maximal value of expected source-offset time.*/
    public static  long MAXIMAL_OFFSET_TIME        = 45000L;
        
    /** Maximal value of expected slew. */
    public static  long MAXIMAL_SLEW_TIME          = 180000L;
 
    /** Time taken to fully UNWRAP. */
    public static  long WRAP_TIME                  = 360000L;
    
    /** Constant: Indicates lunar BRIGHT conditions.*/
    public static final int BRIGHT    = 0;
    
    /** Constant: Indicates lunar DARK conditions.*/
    public static final int DARK      = 1;
    
    /** Constant: Indicates minimum required seeing EXCELLENT.*/
    public static final int EXCELLENT = 2;

    /** Constant: Indicates minimum required seeing AVERAGE.*/
    public static final int AVERAGE   = 1;
    
    /** Constant: Indicates minimum required seeing POOR .*/ 
    public static final int POOR      = 0;

    /** Constant: Indicates minimum required seeing CRAP .*/ 
    public static final int CRAP      = -1;

    
    // Variables.
    
    /**  Indicator to whether this Group has been successfully scheduled. */
    protected boolean done;
    
    /**  Date at which this Group MUST have been completed to avoid being discarded. */
    protected long expiryDate;
    
    /**  Date this Group was scheduled (if Done). */
    protected long doneDate;
    
    /** Identify poorest (lunar) conditions under which this Group can be scheduled. */
    protected int minimumLunar;

    /** Identify poorest (seeing) conditions under which this Group can be scheduled. */
    protected int minimumSeeing;

    /** Maximum distance from meridian at which the source may be observed (rads). */
    protected double meridianLimit;
    
    /** Determines how twilight time is to be used by this observation. */
    protected int twilightUsageMode;

    /** Minimum distance from moon at which the source may be observed (rads). */
    protected double minimumLunarDistance;

    /** True if this Group must be done only in photometric conditions.*/
    protected boolean photometric;

    /** Determines whether the PI should be notified as-soon-as the Group has been done.*/
    protected boolean notifyWhenDone;

    /** PI allocated priority. (0=MAX, larger values are lower priority).
     * This is only used for competition between groups within a proposal and is
     * NOT propagated to higher scheduling layers.*/
    protected int priority;

    /** Stores details of Group's execution history.*/
    protected List history;

    /** Holds the set of Observations for this Group.*/
    protected HashMap observations;

    protected Vector sequence;

    /** Store the Group's expected slew time between sources.*/
    protected double slewTime;

    /** Stores the Group's maximal calculated execution time.*/
    protected double maximalExecutionTime = 0.0;
    
    /** Stores the Group's nominal (per schedule weighting) execution time. */
    protected double nominalExecutionTime = 0.0;

    protected long startingDate;

    /** Returns a String representing the specified seeing conditions.*/
    public static String toSeeingString(int seeing) {
	switch (seeing) {
	case POOR:
	    return "POOR";
	case AVERAGE:
	    return "AVERAGE";
	case EXCELLENT:
	    return "EXCELLENT";
	case CRAP:
	    return "ATROCIOUS";
	default:
	    return "UNKNOWN";
	}
    }

    /** Returns a String representing the specified lunar conditions.*/
    public static String toLunarString(int lunar) {
	switch (lunar) {
	case BRIGHT:
	    return "BRIGHT";
	case DARK:
	    return "DARK";
	   default:
	    return "UNKNOWN";
	}
    } 

    /** Returns a String representing the specified twilight usage.*/
    public static String toTwilightString(int twilight) {
	switch (twilight) {
	case SKY_ANY:
	    return "ANY";
	case SKY_BRIGHT_TWILIGHT:
	    return "DARK_TWILIGHT";
	case SKY_DARK_TWILIGHT:
	    return "BRIGHT_TWILIGHT";
	case SKY_NIGHT:
	    return "NIGHT"
	default:
	    return "UNKNOWN";
	}
    } 
    
    /** Constructor. */  
    public Group() {this("untitled");}
    
    /** Constructor. */  
    public Group(String name) {
	super(name);
	observations = new HashMap();
	history = new Vector();
	sequence = new Vector();
    }
    
    // Accessors.
    
    /** Sets the  indicator to whether this Group has been successfully scheduled .*/
    public void setDone(boolean in) { this.done = in;}
    
    /** True if  indicator to whether this Group has been successfully scheduled. */
    public boolean isDone() { return done;}
    
    /** Sets the  date at which this Group MUST have been completed to avoid being discarded .*/
    public void setExpiryDate(long in) {  this.expiryDate = in;}
    
    /** Returns the  date at which this Group MUST have been completed to avoid being discarded. */
    public long getExpiryDate() { return expiryDate;}
    
    /** Sets the  date this Group was scheduled (if Done) .*/
    public void setDoneDate(long in) {  this.doneDate = in;}
    
    /** Returns the  date this Group was scheduled (if Done). */
    public long getDoneDate() { return doneDate;}


    /** Sets the  date this Group was entered.*/
    public void setStartingDate(long in) {  this.startingDate = in;}
    
    /** Returns the  date this Group was entered. */
    public long getStartingDate() { return startingDate;}


    /** Sets whether the PI should be notified on completion.*/
    public void setNotifyWhenDone(boolean in) { this.notifyWhenDone = in; }

    /** Returns True if the PI should be notified on completion.*/
    public boolean getNotifyWhenDone() { return notifyWhenDone; }

    /** Adds a detail to the Group's execution history. 
     * @param date The date/time the event occurred.
     * @param done True if the Group was successfully executed.
     * @param details Some details.
     */
    public void updateHistory(long date, boolean done, String details) {
	History histEvent = new History(date, done, details);
	history.add(histEvent);
    }

    /** Adds a detail to the Group's execution history. 
     * @param date     The date/time the event occurred.
     * @param done     True if the Group was successfully executed.
     * @param timeUsed The time taken (msecs) whether successful or failure
     * @param code     A code to indicate the reason for aborting.
     * @param details  Some details. 
     */
    public void updateHistory(long date, boolean done, int timeUsed, String code, String details) {
	History histEvent = new History(date, done, timeUsed, code,  details);
	history.add(histEvent);
    }

    /** Clears out the history list.*/
    public void clearHistory() {
	history.clear();
    }
    
    /** Returns the Group's execution history.*/
    public List getHistory() { return history; }
    
    /** Set to identify poorest (lunar) conditions under which this Group can be scheduled .*/
    public void setMinimumLunar(int in) {  this.minimumLunar = in;}
    
    /** Returns the poorest (lunar) conditions under which this Group can be scheduled. */
    public int getMinimumLunar() { return minimumLunar;}
    
    /** Set to identify poorest (seeing) conditions under which this Group can be scheduled .*/
    public void setMinimumSeeing(int in) {  this.minimumSeeing = in;}
    
    /** Returns the poorest (seeing) conditions under which this Group can be scheduled. */
    public int getMinimumSeeing() { return minimumSeeing;}

    /** Sets the  distance from meridian at which the source may be observed (rads) .*/
    public void setMeridianLimit(double in) {  this.meridianLimit = in;}
    
    /** Returns the  distance from meridian at which the source may be observed (rads). */
    public double getMeridianLimit() { return meridianLimit;}
    
    /** Sets the minimum distance from moon at which the source may be observed (rads). */
    public void setMinimumLunarDistance(double in) {this.minimumLunarDistance = in;}

    /** Returns the minimum distance from moon at which the source may be observed (rads). */
    public double getMinimumLunarDistance() { return minimumLunarDistance;}

    /** Sets the mode for twilight usage.*/
    public void setTwilightUsageMode(int mode) {  twilightUsageMode = mode;}

    /** Returns the  mode for twilight usage.*/
    public int getTwilightUsageMode() { return  twilightUsageMode;}

    /** Sets whether this Group must be done in photometric conditions.*/
    public void setPhotometric(boolean photom) { this.photometric = photom; }

    /** Returns True if  this Group must be done in photometric conditions.*/
    public boolean getPhotometric() { return photometric; }

    /** Sets the PI allocated scientific priority.*/
    public void setPriority(int priority) { this.priority = priority;}

    /** Returns the PI allocated scientific priority.*/
    public int getPriority() { return  priority;}

    /** Sets the Group's expected slew time between sources.*/
    public void setSlewTime(double in) {  slewTime = in;}

    /** Returns the Group's expected slew time between sources.*/
    public double getSlewTime() { return slewTime;}

    /** Stores the Group's maximal calculated execution time.*/
    public void setMaximalExecutionTime(double in) {  maximalExecutionTime = in;}

    /** Returns the Group's maximal calculated execution time.*/
    public double getMaximalExecutionTime() { return maximalExecutionTime;}
    
    /** Stores the Group's nominal (per schedule weighting) execution time. */
    public void setNominalExecutionTime(double in) {  nominalExecutionTime = in;}
    
    /** Returns the Group's nominal (per schedule weighting) execution time. */
    public double getNominalExecutionTime() { return nominalExecutionTime;}
    
    /** Configure various group params.
     * @param file The config file.
     * @throws IOException if any file errors.
     * @throws IllegalArgumentException if any dodgy config.
     */
    public static void configure(File file) throws IOException, IllegalArgumentException {
	ConfigurationProperties config = new ConfigurationProperties();
	config.load(new FileInputStream(file));
	configure(config);
    }
    
    /** Configure various Group params.
     * @param config A set of config options.
     * @throws IllegalArgumentException if any dodgy config.
     */
    public static void configure(ConfigurationProperties config) throws IllegalArgumentException {
	NOMINAL_ACQUIRE_TIME       = config.getLongValue("nominal.acquire.time", 
							 DEFAULT_NOMINAL_ACQUIRE_TIME);	
	NOMINAL_CONFIGURATION_TIME = config.getLongValue("nominal.configuration.time", 
							 DEFAULT_NOMINAL_CONFIGURATION_TIME); 			 
	NOMINAL_OFFSET_TIME        = config.getLongValue("nominal.offset.time", 
							 DEFAULT_NOMINAL_OFFSET_TIME);    
	NOMINAL_READOUT_TIME       = config.getLongValue("nominal.readout.time", 
							 DEFAULT_NOMINAL_READOUT_TIME);	
	MAXIMAL_CONFIGURATION_TIME = config.getLongValue("maximal.configuration.time", 
							 DEFAULT_MAXIMAL_CONFIGURATION_TIME);	
	MAXIMAL_ACQUIRE_TIME       = config.getLongValue("maximal.acquire.time", 
							 DEFAULT_MAXIMAL_ACQUIRE_TIME);
    }




    // Observation Methods.
    
    public void addObservation(Observation observation) {
	
	observations.put(observation.getName(), observation);
	observation.setPath(getPath() + "/" + getName());
    }
    
    public void deleteObservation(Observation observation) { 
	
	observations.remove(observation.getName());
    }
    
    public void removeAllObservations() { 
	
	observations.clear();
    }
    
    public Iterator listAllObservations() {
	
	return observations.values().iterator();
    }
    
    public Observation findObservation(String name) {
	
	if (observations.containsKey(name)) return ((Observation)observations.get(name));
	return null;
    }
    
    public HashMap getObservations() { return observations;}
      
    /** Formatted Text Output as XML.*/
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<group name = '"+name+"'>");
	out.println(tab(level+1)+"<expiryDate>"+sdf.format(new Date(expiryDate))+"</expiryDate>");

	// Constraints
	out.println(tab(level+1)+"<constraints>");

	String strSeeing = toSeeingString(minimumSeeing);	
	out.println(tab(level+2)+"<seeing>"+strSeeing+"</seeing>");	

	String strLunar = toLunarString(minimumLunar); 
	out.println(tab(level+2)+"<lunar>" +strLunar+"</lunar>");

	out.println(tab(level+1)+"</constraints>");

	writeXmlSpecial(out, level+1);

	// Observations.
	Iterator it;
	it = listAllObservations();
	while (it.hasNext()) {
	    ((Observation)it.next()).writeXml(out,level+1);
	}

	// History.
	out.println(tab(level+1)+"<history>");
	Iterator ih = history.iterator();
	while (ih.hasNext()) {
	    History hist = (History)ih.next();
	    hist.writeXml(out,level+2);	   
	}
	out.println(tab(level+1)+"</history>");

	// Sequence.
      
	if (sequence != null) {  
	    out.println(tab(level+1)+"<sequence>");
	    Iterator is = sequence.iterator();
	    int kk = 1;
	    while (is.hasNext()) {
		String ss = (String)is.next();
		out.println(tab(level+2)+"<item pos = '"+kk+"' value = '"+ss+"' />");
		kk++;
	    }
	    out.println(tab(level+1)+"</sequence>");
	} else {
	    out.println(tab(level+1)+"<sequence>EMPTY</sequence>");
	}

	out.println(tab(level)+"</group>");

    } // end (write).

    public void setSequence(Vector v) { this.sequence = v;}

    public Vector getSequence() { return sequence; }

    
    /** Formatted Text Output as XML.*/
    public void writeXmlSpecial(PrintStream out, int level) {
	// Nothing this is the Generic Group.
    }
   
    // Child lock / update  methods. Handcoded to override O2J generated Group.java source.          
    public void lock(int key) { 	
     	setLock(key);
    }
    
    public void updateChildren() { 	
	Iterator it  = listAllObservations();
	while (it.hasNext()) {
	    Observation obo = (Observation)it.next();
	    obo.setPath(path+"/"+name);
	}
    }
    
    // Obo's cant be locked they are subatomic.     
    public boolean canLock() {	
	if (isLocked()) return false;
	return true;
    }
    
    public void lockChildren(int key) { 	
	setLock(key);
    }
    
    public void unLockChildren(int key) {	
	lock = 0;
    }
    
    public void forceUnLock() {	
     	lock = 0;
    }	
    
    // Clone Constructor.    
    public NPDBObject copy() {
	try {
	    return (Group)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).


    /** Writes the descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     */
    public String toHtmlString(int protocolVersion) {

	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	
	StringBuffer buffer = new StringBuffer();
	
	toHtmlStringGenericHeader(protocolVersion, buffer);
	toHtmlStringSpecial(protocolVersion, buffer);
	toHtmlStringHistory(protocolVersion, buffer);
	toHtmlStringGenericTail(protocolVersion, buffer);
	
	return buffer.toString();
    }

    /** Writes the (Generic header) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringGenericHeader(int protocolVersion, StringBuffer buffer) {
	buffer.append("<html> ");
	buffer.append("\n   <body> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Group ID --> ");
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	String type = this.getClass().getName();
	type = type.substring(type.lastIndexOf("."));
	buffer.append("\n     <tr> <td><font size = +2> <b>"+type+"</b> </font></td>   ");
	buffer.append("\n          <td bgcolor = white>  <font size = +2>"+name+"</font></td> </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Internal priority --> ");
	buffer.append("\n  <p>");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	buffer.append("\n     <tr> <td>Priority</td>   ");
	buffer.append("\n          <td bgcolor = white>"+priority+"</td> </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");

	buffer.append("\n   <!-- Ownership --> ");
	
	Path pp = new Path(path);
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>   ");
	buffer.append("\n     <tr> <td colspan = 8> <b>Ownership</b> </td> </tr> ");
	buffer.append("\n     <tr> ");
	buffer.append("\n       <td> DB       </td> <td bgcolor = white>"+pp.getRootByName()+    "</td> ");
	buffer.append("\n       <td> TAG      </td> <td bgcolor = white>"+pp.getTagByName()+     "</td> ");
	buffer.append("\n       <td> PI       </td> <td bgcolor = white>"+pp.getUserByName()+    " </td>  ");
	buffer.append("\n       <td> Proposal </td> <td bgcolor = white>"+pp.getProposalByName()+" </td>  ");	
	buffer.append("\n     </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");

	buffer.append("\n   <!-- Status flags --> ");
	buffer.append("\n   <p>  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n    <tr> <td colspan = 2> <b> Status flags </b> </td> </tr>  ");
	buffer.append("\n    <tr>  ");

	if (isLocked()) 
	    buffer.append("\n     <td bgcolor = white> <b><font color = red> Locked </font></b></td> ");
	else
	    buffer.append("\n     <td bgcolor = white> <b><font color = limegreen> Unlocked </font></b></td> ");
		
	buffer.append("\n    <td bgcolor = white>"+(notifyWhenDone ? "Notify-when-done" : "")+"</td>  ");
	buffer.append("\n    </tr>  "); 
	buffer.append("\n   </table>  ");
	
	buffer.append("<!-- Constraints -->");	    
	buffer.append("\n    <p>  ");
	buffer.append("\n    <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n    <tr> <td colspan = 4> <b> Constraints </b> </td> </tr>  ");

	String lunar  = toLunarString(minimumLunar);
	String seeing = toSeeingString(minimumSeeing);
	
	buffer.append("\n   <tr>  ");	
	buffer.append("\n    <td> Minimum Lunar </td> <td bgcolor = white>"+ lunar+"</td>  ");
	buffer.append("\n    <td> Minimum Seeing</td> <td bgcolor = white>"+ seeing+"</td>  ");
	buffer.append("\n    </tr> ");
	
	String meridian = (meridianLimit < 0.0 ? "NO LIMIT" :  nf.format(Math.toDegrees(meridianLimit)*4.0)+" mins." );
	
	buffer.append("\n   <tr> ");
	buffer.append("\n    <td> Twilight      </td> <td bgcolor = white>"+toTwilightString(twilightUsageMode)+"</td>  ");
	buffer.append("\n    <td> Meridian      </td> <td bgcolor = white>"+meridian+"</td>  ");
	buffer.append("\n    </tr> ");

	String photom = null;
	if (photometric)
	    photom = "REQUIRED";
	else
	    photom = "NOT REQUIRED";

	String distance = "&gt;&nbsp;"+nf.format(Math.toDegrees(minimumLunarDistance))+"&#176";
	
	buffer.append("\n   <tr> ");
	buffer.append("\n    <td> Lunar Distance</td> <td bgcolor = white>"+distance+"</td>  ");
	buffer.append("\n    <td> Photometric   </td> <td bgcolor = white>"+ photom+ "</td>  ");
	buffer.append("\n    </tr> ");


	buffer.append("\n    <tr> ");	
	buffer.append("\n     <td colspan = 2> Expires </td> <td bgcolor = white colspan = 2>"+
		      sdf.format(new Date(expiryDate)) +"</td>  ");
	buffer.append("\n    </tr>  ");
	buffer.append("\n   </table>  ");
	
    }
    
    /** Writes the (subclass-specific) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringSpecial(int protocolVersion, StringBuffer buffer) {
	// In base class does nothing.
    }
    
    /** Writes the (History) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringHistory(int protocolVersion, StringBuffer buffer) {
	
	buffer.append("\n<!-- History -->");
	buffer.append("\n  <p> ");
	buffer.append("\n  <table border = 0 bgcolor = #ADD8E6>   ");
	buffer.append("\n    <tr> <td colspan = 4> <b> History </b> </td> </tr> ");
	buffer.append("\n    <tr> ");
	buffer.append("\n     <td> <b> Execution Date </b> </td> <td> <b> Completed </b> </td> <td> <b> Details </b> </td>");
	buffer.append("\n    </tr>");

	Iterator hist = history.iterator();
	while (hist.hasNext()) {
	    History history = (History)hist.next();
	    buffer.append("\n    <tr>  ");
	    buffer.append("\n      <td bgcolor = white>"+sdf.format(new Date(history.getDate()))+"</td> ");
	    buffer.append("\n      <td bgcolor = white>"+(history.getDone() ? "YES" : "NO")+"</td> ");
	    int ts = (int)((double)history.getTimeUsed()/1000.0);	   
	    int m = ts/60;
	    int s = ts - 60*m;
	    if (history.getDone()) 
		buffer.append("\n      <td bgcolor = white> Completed in "+m+"M "+s+"S, "+
			      (history.getDetails() != null ? history.getDetails() : "")+"</td>");
	    else
		buffer.append("\n      <td bgcolor = white> Failed after "+m+"M "+s+"S due to <u><font color = blue>"+
			      (history.getCode() != null ? history.getCode() : "NOT_KNOWN")+"</font></u> </td>");
	    buffer.append("\n    </tr> ");	    
	}
	
	buffer.append("\n     </table> ");	
    }
    
    /** Writes the (Generic tail) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringGenericTail(int protocolVersion, StringBuffer buffer) {
	buffer.append("\n  ");
	buffer.append("\n </body> ");
	buffer.append("\n </html> ");
	buffer.append("\n  ");
    }

    /** Returns a readable description of this Group.*/
    public String toString() { 
	return "[Group: "+name+
	    ", Lock="+lock+
	    ", Done="+done+
	    ", NotifyDone="+notifyWhenDone+
	    ", MinLunar="+toLunarString(minimumLunar)+
	    ", MinSeeing="+toSeeingString(minimumSeeing)+
	    ", Use-Twilight="+toTwilightString(twilightUsageMode)+
	    ", Meridian="+(meridianLimit < 0.0 ? "NO LIMIT" :  nf.format(Math.toDegrees(meridianLimit)*4.0)+" mins." )+
	    ", Requires-Photom="+photometric+
	    ", Entered/starts="+sdf.format(new Date(startingDate))+
	    ", Expires="+sdf.format(new Date(expiryDate))+
	    ", Sequence="+sequence+
	    "]";
    }

    /** Stores a Group history event.
     *
     * The detail message is supplementary to the code and timeUsed
     * so these should not generally be incorporated into it.
     *
     *
     */
    public class History implements Serializable {

	/** Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.*/
	private static final long serialVersionUID = -4546547850755473521L;

	/**True if the Group was successfully executed.*/
	protected boolean done;
	
	/** The date/time the event occurred.*/
	protected long date;
	
	/** Some details ().*/
	protected String details;

	/** Time taken (secs) whether successful or failure.*/
	protected int timeUsed;

	/** A code to indicate the reason for aborting.*/
	protected String code;

	/** Create a History event.*/
	public History(long date, boolean done, String details) {
	    this.date    = date;
	    this.done    = done;
	    this.details = details;
	}

	/** Create a History event.*/
	public History(long date, boolean done, int timeUsed, String code, String details) {
	    this.date     = date;
	    this.done     = done;
	    this.timeUsed = timeUsed;
	    this.code     = code;
	    this.details  = details;
	}
	
	/** Returns True if the Group was successfully executed.*/
	public boolean getDone() { return done;}
	
	/** Returns the date/time the event occurred.*/
	public long getDate() { return date; }
	
	/** Returns some details.*/
	public String getDetails() { return details;}
	
	/** Set the time taken (secs) whether successful or failure.*/
	public void setTimeUsed(int timeUsed) {
	    this.timeUsed = timeUsed;
	}
	
	/** Returns the time taken (secs) whether successful or failure.*/
	public int getTimeUsed() { return timeUsed; }
	
	/** Set a code to indicate the reason for aborting.*/
	public void setCode(String code) {
	    this.code = code;
	}
	
	/** Returns a code to indicate the reason for aborting.*/
	public String getCode() { return code; }

	/** Formatted Text Output as XML.*/
	public void writeXml(PrintStream out, int level) {

	    int ts = (int)((double)timeUsed/1000.0);	   
	    int m = ts/60;
	    int s = ts - 60*m;
	    String message = "";
			
	    if (done) 
		message = "Completed in "+m+"M "+s+"S, "+
		    (details != null ? details : "No extra information.");
	    else
		message = "Failed after "+m+"M "+s+"S due to "+
		    (code != null ? code : "NOT_KNOWN");
	    
	    out.println(tab(level)+"<historyEvent>");
	    out.println(tab(level+1)+"<date>"+sdf.format(new Date(date))+"</date>");
	    out.println(tab(level+1)+"<done>"+done+"</done>");   
	    out.println(tab(level+1)+"<detail>"+message+"</detail>");
	    out.println(tab(level)+"</historyEvent>");

	}

	public String toString() {
	    return "[HistoryEvent: Execution date="+sdf.format(new Date(date))+
		", Done="+done+
		", Time used="+timeUsed+" secs"+
		", Code="+code+
		", Details="+details+"]";
	}

    }
    

} // end class def [Group].



