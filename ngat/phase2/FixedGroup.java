package ngat.phase2;
import ngat.phase2.nonpersist.*;
import ngat.phase2.util.*;
import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class FixedGroup extends Group implements Serializable {

     // Variables.



     /**  the date/time at which this FixedGroup should be carried out. */
     protected long fixedTime;

     // Constructor.

     public FixedGroup() {this("untitled");}

     public FixedGroup(String name) {
          super(name);
     }

     // Accessors.



     /** Sets the  the date/time at which this FixedGroup should be carried out .*/
     public void setFixedTime(long in) { this.fixedTime = in;}


     /** Returns the  the date/time at which this FixedGroup should be carried out. */
     public long getFixedTime() { return fixedTime;}


     // Descendant Mutators.

     
     // NP -> P Translator.

     public FixedGroup(NPFixedGroup npFixedGroup) {
          super(npFixedGroup);
          Iterator it;
          fixedTime = npFixedGroup.getFixedTime();
          
          // Recursively call Daughter Translators.

     } // end (NP -> P Translator).
     
     // P -> NP Translator.

     public void stuff(NPFixedGroup npFixedGroup) {
          super.stuff(npFixedGroup);
          Iterator it;
          npFixedGroup.setFixedTime(getFixedTime());
     } // end (P -> NP Translator).
     
     // P -> NP Translator.

     public NPDBObject makeNP() {
          NPFixedGroup npFixedGroup = new NPFixedGroup();
          stuff(npFixedGroup);
          return npFixedGroup;
     } // end (makeNp).
     
     
     /**
      * FixedGroup scheduling algorithm. Handcoded, Insert in O2J generated FixedGroup.java source.
      */
     
     /** Determines whether this Group has been missed by the Scheduler. */
     public boolean overRun() { return (fixedTime < Scheduling.getCurrentTime());}
     
     public FixedGroup getNextFixedGroup() { 
         if (!isLocked() && !deleted() && !isDone() && !overRun())return this;
         return null;
     }
     
     public ScheduleDescriptor schedule() {
     
         // Return something, it won't actually be used for anything.
         return new ScheduleDescriptor(null, 0.0f, 0);
         
     }


} // end class def [FixedGroup].