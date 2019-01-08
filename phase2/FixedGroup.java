package ngat.phase2;

import ngat.phase2.nonpersist.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class FixedGroup extends Group implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 2835412734902740563L;
     
    // Variables.

     /**  the date/time at which this FixedGroup should be carried out. */
     protected long fixedTime;

    /** The size of a window in which the group can start - equal either side of fixed time.*/
    protected long window;

     // Constructor.

     public FixedGroup() {this("untitled");}

     public FixedGroup(String name) {
          super(name);
     }

     // Accessors.



     /** Sets the  the date/time at which this FixedGroup should be carried out .*/
     public void setFixedTime(long in) {  this.fixedTime = in;}


     /** Returns the  the date/time at which this FixedGroup should be carried out. */
     public long getFixedTime() { return fixedTime;}

    public void setWindow(long w) { this.window = w; }

    public long getWindow() { return window; }

    // Clone Constructor.
    public NPDBObject copy() {
	try {
	    return (FixedGroup)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    /** Formatted Text Output as XML.*/
    public void writeXmlSpecial(PrintStream out, int level) {

	out.println(tab(level)+"<fixed time = '"+sdf.format(new Date(fixedTime))+"'/>");

    } 

    /** Writes the (subclass-specific) descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     * @param buffer The already created buffer to append to.
     */
    public void toHtmlStringSpecial(int protocolVersion, StringBuffer buffer) {
	
	buffer.append("\n   <!-- Fixed fields --> ");
	buffer.append("\n   <p>  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n    <tr> <td colspan = 2> <b> Fixed fields </b> </td> </tr>  ");
	buffer.append("\n    <tr>  ");
	buffer.append("\n      <td> Fixed-time </td> <td bgcolor = white>"+sdf.format(new Date(fixedTime))+"</td>  ");
	buffer.append("\n    </tr>  ");
	buffer.append("\n    <tr>  ");
	buffer.append("\n      <td> Window </td> <td bgcolor = white>"+(window/1000)+"</td>  ");
	buffer.append("\n    </tr>  ");
	
	buffer.append("\n   </table>  ");
	
    }
    

    public String toString() { 
	return "[FixedGroup: "+name+" : Fixed-time="+sdf.format(new Date(fixedTime))+", Window="+(window/1000)+"s]";
    }

} // end class def [FixedGroup].
