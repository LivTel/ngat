package ngat.phase2;
import ngat.phase2.nonpersist.*;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class EphemerisGroup extends Group implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -8848209037477185241L;
     
    // Variables.

     /**  date/time of the first observation period. */
     protected long start;

    /**  date/time of the last observation period. */
     protected long end;

     /**  length of the phase period. */
     protected long period;

     /**  specific phase to carry out observations. */
     protected float phase;

     /**  record of last successfully observed period number. */
     protected int lastPeriod;

    /** slop allowed in the phase fraction - i.e. phase +/- slop/2.*/
    protected float slopPhase;

     // Constructor.

     public EphemerisGroup() {this("untitled");}

     public EphemerisGroup(String name) {
          super(name);
     }

     // Accessors.

     /** Sets the  date/time of the first observation period .*/
     public void setStart(long in) {this.start = in;}

     /** Returns the  date/time of the first observation period. */
     public long getStart() { return start;}

  /** Sets the  date/time of the last observation period .*/
     public void setEnd(long in) {this.end = in;}

     /** Returns the  date/time of the last observation period. */
     public long getEnd() { return end;}    

     /** Sets the  length of the phase period .*/
     public void setPeriod(long in) {this.period = in;}

     /** Returns the  length of the phase period. */
     public long getPeriod() { return period;}

     /** Sets the  specific phase to carry out observations .*/
     public void setPhase(float in) {this.phase = in;}

     /** Returns the  specific phase to carry out observations. */
     public float getPhase() { return phase;}

     /** Sets the  record of last successfully observed period number .*/
     public void setLastPeriod(int in) {this.lastPeriod = in;}

     /** Returns the  record of last successfully observed period number. */
     public int getLastPeriod() { return lastPeriod;}
    
    /** Sets the  specific phase slopto carry out observations .*/
     public void setSlopPhase(float in) {this.slopPhase = in;}

     /** Returns the  specific phase slop to carry out observations. */
     public float getSlopPhase() { return slopPhase;}


    // Clone Constructor.
     public NPDBObject copy() { 
	 EphemerisGroup newGroup = new EphemerisGroup(name); 
	 newGroup.setPath(path);
	 newGroup.setDone(done);
	 newGroup.setExpiryDate(expiryDate);
	 newGroup.setDoneDate(doneDate);
	 newGroup.setMinimumLunar(minimumLunar);
	 newGroup.setMinimumSeeing(minimumSeeing);
	 Iterator it = listAllObservations();
	 while (it.hasNext()) {
	     Observation observation = (Observation)it.next();
	     Observation newObservation = (Observation)observation.copy();
	     newGroup.addObservation(newObservation);
	 }
	 newGroup.setStart(start);
	 newGroup.setPeriod(period);
	 newGroup.setPhase(phase);
	 newGroup.setSlopPhase(slopPhase);
	 newGroup.setLastPeriod(lastPeriod);
	 return newGroup;
     } // end (copy).

    /** Formatted Text Output as XML.*/
    public void writeXmlSpecial(PrintStream out, int level) {
	out.println(tab(level)+"<ephemeris>");
	out.println(tab(level+1)+"<startTime>"+sdf.format(new Date(start))+"</startTime>");	
	out.println(tab(level+1)+"<period>"+period+"</period>");	
	out.println(tab(level+1)+"<lastPeriod>"+lastPeriod+"</lastPeriod>");	
	out.println(tab(level+1)+"<phase>"+phase+"</phase>");
	out.println(tab(level+1)+"<phaseSlop>"+slopPhase+"</phaseSlop>");
	out.println(tab(level)+"</ephemeris>");	
    }
	

    /** Writes the (subclass-specific) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringSpecial(int protocolVersion, StringBuffer buffer) {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	buffer.append("\n   <!-- Ephemeris fields --> ");
	buffer.append("\n   <p>  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n     <tr> <td colspan = 4> <b> Ephemeris fields </b> </td> </tr>  ");
	buffer.append("\n     <tr>  ");
	buffer.append("\n       <td> Start ephemeris </td> <td bgcolor = white>"+ sdf.format(new Date(start))+ "</td>  ");
	buffer.append("\n       <td> End ephemeris    </td> <td bgcolor = white>"+ sdf.format(new Date(end))+ "</td>  ");
	buffer.append("\n     </tr>  ");
	buffer.append("\n     <tr>  ");

	int ts = (int)(period/1000);
        int h = ts/3600;
        int ms = ts - 3600*h;
        int m = ms/60;
        int s = ms - 60*m;    

	buffer.append("\n       <td> Period </td>           <td bgcolor = white>"+h+"H "+m+"M "+s+"S </td>  ");

	int ws = (int)((float)ts*phase);
	h = ws/3600;
        ms = ws - 3600*h;
        m = ms/60;
        s = ms - 60*m;    

	buffer.append("\n       <td> Phase </td>   <td bgcolor = white>"+ "+"+nf.format(phase)+
		      " ("+h+"H "+m+"M "+s+"S) </td>  ");
	buffer.append("\n     </tr>  "); 
	 
	int cp = (int)(( System.currentTimeMillis() - start ) / period );
	buffer.append("\n     <tr> ");
	buffer.append("\n       <td colspan = 2> Current period</td> <td colspan = 2 bgcolor = white>"+cp+"</td>");
	buffer.append("\n     </tr>");
	buffer.append("\n   </table>  ");
	
    }

    public String toString() {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	
	return "[EphemerisGroup: "+name+" : "+
	    ", Start-ephem="+sdf.format(new Date(start))+
	    ", End-ephem="+sdf.format(new Date(end))+
	    ", Period="+(period/1000.0)+" secs"+
	    ", Phase="+nf.format(phase)+
	    ", Slop=+/-"+nf.format(slopPhase)+
	    ", Last-Period="+sdf.format(new Date(lastPeriod))+	 
	    "]";
    }










} // end class def [EphemerisGroup].