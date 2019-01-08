package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import java.io.*;

/** Class to represent a Group of observations which can be repeated upto a specified
 * number of times with a specified minimum gap between executions.
 */
public class RepeatableGroup extends Group implements Serializable {
  
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.
     */
    private static final long serialVersionUID = 8711731594113455658L;
    
    // Variables.

    /** Date/time observation periods can start. */
    protected long startDate;
    
    /** Date/time observation period should end. */
    protected long endDate;
    
    /** Minimum Interval between observations. (millis)*/
    protected long minimumInterval;

    /** Maximum number of times this group can be repeated.*/
    protected int maximumRepeats;

    /** Records the last time this Group was scheduled for execution.*/
    protected long lastScheduled;

    /** Records the last time this Group was successfully completed.*/
    protected long lastCompleted;
    
    /** Counts number of times group was scheduled.*/
    protected int countScheduled;

    /** Counts number of times group was completed.*/
    protected int countCompleted;

     // Constructor.

     public RepeatableGroup() {this("untitled");}

     public RepeatableGroup(String name) {
          super(name);
     }

     // Accessors.

    /** Sets the  date/time observation periods should start .*/
    public void setStartDate(long in) {  this.startDate = in;}
    
    /** Returns the  date/time observation periods should start. */
    public long getStartDate() {  return startDate;}
    
    /** Sets the  date/time of last observation period .*/
    public void setEndDate(long in) { this.endDate = in;}
    
    /** Returns the  date/time of last observation period. */
    public long getEndDate() {  return endDate;}
    
    /** Sets the minimum Interval between observations .*/
    public void setMinimumInterval(long in) { this.minimumInterval = in;}
    
    /** Returns the minimum Interval between observations. */
    public long getMinimumInterval() {  return minimumInterval;}
 
    /** Sets the maximum number of times this group can be repeated .*/
    public void setMaximumRepeats(int in) { this.maximumRepeats = in;}
    
    /** Returns the maximum number of times this group can be repeated. */
    public int getMaximumRepeats() {  return maximumRepeats;}
            
    /** Sets the last time this Group was scheduled for execution.*/
    public void setLastScheduled(long in) { this.lastScheduled = in;}
    
    /** Returns the last time this Group was successfully completed.*/
    public long getLastScheduled() { return lastScheduled;}

    /** Sets the last time this Group was successfully completed.*/
    public void setLastCompleted(long in) { this.lastCompleted = in;}
    
    /** Returns last time this Group was successfully completed.*/
    public long getLastCompleted() { return lastCompleted;}
    
    /** Increments the number of times group was scheduled.*/
    public void incCountScheduled() { countScheduled++;}

     /** Returns number of times group was completed.*/
    public int getCountScheduled() { return countScheduled;}
    
    /** Increments the number of times group was completed.*/
    public void incCountCompleted() { countCompleted++;}

    /** Returns number of times group was completed.*/
    public int getCountCompleted() { return countCompleted;}
    

    /** Formatted Text Output as XML.*/
    public void writeXmlSpecial(PrintStream out, int level) {
	out.println(tab(level)+"<repeatable>");
	out.println(tab(level+1)+"<startDate>"+sdf.format(new Date(startDate))+"</startDate>");
	out.println(tab(level+1)+"<endDate>"+sdf.format(new Date(endDate))+"</endDate>");
	out.println(tab(level+1)+"<minimumInterval>"+minimumInterval+"</minimumInterval>");	
	out.println(tab(level+1)+"<maximumRepeats>"+maximumRepeats+"</maximumRepeats>");
	out.println(tab(level+1)+"<lastScheduled>"+sdf.format(new Date(lastScheduled))+"</lastScheduled>");
	out.println(tab(level+1)+"<lastCompleted>"+sdf.format(new Date(lastCompleted))+"</lastCompleted>");
	out.println(tab(level+1)+"<countScheduled>"+countScheduled+"</countScheduled>");
	out.println(tab(level+1)+"<countCompleted>"+countCompleted+"</countCompleted>");
	out.println(tab(level)+"</repeatable>");
    }
   
    public NPDBObject copy() {
	try {
	    return (RepeatableGroup)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
     
    /** Writes the (subclass-specific) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringSpecial(int protocolVersion, StringBuffer buffer) {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	buffer.append("\n   <!-- Repeatable fields --> ");
	buffer.append("\n   <p>  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n     <tr> <td colspan = 4> <b> Repeatable fields </b> </td> </tr>  ");
	buffer.append("\n     <tr>  ");
	buffer.append("\n       <td> Start observing </td> <td bgcolor = white>"+ sdf.format(new Date(startDate))+ "</td>  ");
	buffer.append("\n       <td> Stop observing    </td> <td bgcolor = white>"+ sdf.format(new Date(endDate))+ "</td>  ");
	buffer.append("\n     </tr>  ");

	buffer.append("\n     <tr>  ");

	int ts = (int)(minimumInterval/1000);
        int h = ts/3600;
        int ms = ts - 3600*h;
        int m = ms/60;
        int s = ms - 60*m;    

	buffer.append("\n       <td> Minimum interval </td>           <td bgcolor = white>"+h+"H "+m+"M "+s+"S </td>  ");
	buffer.append("\n       <td> Maximum repeats </td>           <td bgcolor = white>"+maximumRepeats+"</td>"); 
	buffer.append("\n     </tr>");

	buffer.append("\n     </tr>  ");
	buffer.append("\n       <td> Scheduled </td> <td bgcolor = white> "+countScheduled+" times</td>");
	buffer.append("\n       <td> Completed </td> <td bgcolor = white> "+countCompleted+" times</td>");
	buffer.append("\n     </tr>");

	buffer.append("\n   </table>  ");
	
    }

    public String toString() {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	
	return "[RepeatableGroup: "+name+" : "+
	    ", Monitor-start="+sdf.format(new Date(startDate))+
	    ", Monitor-end="+sdf.format(new Date(endDate))+
	    ", Minimum-interval="+(minimumInterval/1000.0)+" secs"+
	    ", Maximum-repeats="+maximumRepeats+
	    ", Last-sched="+sdf.format(new Date(lastScheduled))+
	    ", Last-done="+sdf.format(new Date(lastCompleted))+
	    "]";
    }
    
    
} // end class def [RepeatableGroup].



