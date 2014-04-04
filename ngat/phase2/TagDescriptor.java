package ngat.phase2;

import ngat.phase2.nonpersist.*;
import ngat.util.logging.*;

import java.io.*;

/** Contains selected fields of a Tag, suitable for informing remote clients of the
 * details of a Tag without revealing any substructure. Used By ADM_ListTags
 * to store the basic details of a Tag. 
 * $Id$.*/
public class TagDescriptor implements Serializable {
  
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -130123947390872636L;

    public static final int BRIGHT = 0;
    
    public static final int DARK   = 1;
    
    public static final int EXCELLENT = 0;
    
    public static final int AVERAGE   = 1;
    
    public static final int POOR      = 2;
    
    protected String path;
    protected String name;    
    protected int lock;
   
    protected float[][] achievedTime;
    
    protected float[][] targetFraction;

    float totalAchievedTime;

    float totalAllocatedTime;


    /** Make an empty descriptor. */
    public TagDescriptor(String name) {
	this.name = name;
	achievedTime   = new float[2][3];
	targetFraction = new float[2][3];
    }

    /** Make a descriptor for the supplied Tag. */
    public TagDescriptor(Tag tag) {
	achievedTime   = new float[2][3];
	targetFraction = new float[2][3];
	this.path = tag.getPath();
	this.name = tag.getName();
	this.lock = tag.getLock();
	
	this.achievedTime[BRIGHT][EXCELLENT] = tag.getAchievedTime(BRIGHT, EXCELLENT);
	this.achievedTime[BRIGHT][AVERAGE]   = tag.getAchievedTime(BRIGHT, AVERAGE);
	this.achievedTime[BRIGHT][POOR]      = tag.getAchievedTime(BRIGHT, POOR);
	this.achievedTime[DARK]  [EXCELLENT] = tag.getAchievedTime(DARK,   EXCELLENT);
	this.achievedTime[DARK]  [AVERAGE]   = tag.getAchievedTime(DARK,   AVERAGE);
	this.achievedTime[DARK]  [POOR]      = tag.getAchievedTime(DARK,   POOR);
	
	this.totalAchievedTime = tag.getTotalAchievedTime();
	
	this.targetFraction[BRIGHT][EXCELLENT] = tag.getTargetFraction(BRIGHT, EXCELLENT);
	this.targetFraction[BRIGHT][AVERAGE]   = tag.getTargetFraction(BRIGHT, AVERAGE);
	this.targetFraction[BRIGHT][POOR]      = tag.getTargetFraction(BRIGHT, POOR);
	this.targetFraction[DARK]  [EXCELLENT] = tag.getTargetFraction(DARK,   EXCELLENT);
	this.targetFraction[DARK]  [AVERAGE]   = tag.getTargetFraction(DARK,   AVERAGE);
	this.targetFraction[DARK]  [POOR]      = tag.getTargetFraction(DARK,   POOR);
    
	this.totalAllocatedTime = tag.getTotalAllocatedTime();
	
    }
    
    /** Return a Tag from this descriptor. */
    public NPTag createTag() {
	NPTag tag = new NPTag(name);
	tag.setPath(path);
	tag.setLock(lock);	

	tag.setAchievedTime(BRIGHT, EXCELLENT, achievedTime[BRIGHT][EXCELLENT]);
	tag.setAchievedTime(BRIGHT, AVERAGE,   achievedTime[BRIGHT][AVERAGE]);
	tag.setAchievedTime(BRIGHT, POOR,      achievedTime[BRIGHT][POOR]);
	tag.setAchievedTime(DARK,   EXCELLENT, achievedTime[DARK]  [EXCELLENT]);
	tag.setAchievedTime(DARK,   EXCELLENT, achievedTime[DARK]  [AVERAGE]);
	tag.setAchievedTime(DARK,   EXCELLENT, achievedTime[DARK]  [POOR]);
	
	tag.setTotalAchievedTime(totalAchievedTime);

	tag.setTargetFraction(BRIGHT, EXCELLENT, targetFraction[BRIGHT][EXCELLENT]);
	tag.setTargetFraction(BRIGHT, AVERAGE,   targetFraction[BRIGHT][AVERAGE]);
	tag.setTargetFraction(BRIGHT, POOR,      targetFraction[BRIGHT][POOR]);
	tag.setTargetFraction(DARK,   EXCELLENT, targetFraction[DARK]  [EXCELLENT]);
	tag.setTargetFraction(DARK,   AVERAGE,   targetFraction[DARK]  [AVERAGE]);
	tag.setTargetFraction(DARK,   POOR,      targetFraction[DARK]  [POOR]);
	
	tag.setTotalAllocatedTime(totalAllocatedTime);
	
	return tag;
    }
    
    public String  getPath() { return path;} 
    public void    setPath(String path) { this.path = path;}
    
    public String  getName() { return name;}  
    public void    setName(String name) { this.name =  name;}
    
    public int     getLock() { return lock; }
    public void    setLock(int lock) { this.lock = lock;}
    
    /** Sets the time fraction actually achieved under BrightSky/ExcellentSeeing conditions .*/
    public void setAchievedTime(int lunar, int seeing, float in) { this.achievedTime[lunar][seeing] = in;}    
    
    /** Returns the time fraction actually achieved under BrightSky/ExcellentSeeing conditions. */
    public float getAchievedTime(int lunar, int seeing) { return achievedTime[lunar][seeing];}          
    
    public float   getTotalAchievedTime() { return totalAchievedTime;}
    public void    setTotalAchievedTime(float totalAchievedTime)  { this.totalAchievedTime = totalAchievedTime;}
    
    /** Sets the time fraction allocated to BrightSky/ExcellentSeeing conditions .*/
    public void setTargetFraction(int lunar, int seeing, float in) { this.targetFraction[lunar][seeing] = in;}
    
    /** Returns the time fraction allocated to BrightSky/ExcellentSeeing conditions. */
    public float getTargetFraction(int lunar, int seeing) { return targetFraction[lunar][seeing]; }        
    
    public float   getTotalAllocatedTime() { return totalAllocatedTime;}
    public void    setTotalAllocatedTime(float totalAllocatedTime)  { this.totalAllocatedTime = totalAllocatedTime;}
    
    public boolean isLocked() { return (lock != 0);}
    
    /** Formatted Text Output to specified Logger.
     * @param logName The name of the logger to use.*/
    public void write(String logName) {
	Logger logger = LogManager.getLogger(logName);
	logger.log(1, "path: ["+path+"]"+
		   "\nname: ["+name+"]"+
		   "\nlock: ["+lock+"]"+
		   "\nachievedTime[BRIGHT][EXCELLENT]: ["+achievedTime[BRIGHT][EXCELLENT]+"]"+
		   "\nachievedTime[BRIGHT][AVERAGE]: ["+achievedTime[BRIGHT][AVERAGE]+"]"+
		   "\nachievedTime[BRIGHT][POOR]: ["+achievedTime[BRIGHT][POOR]+"]"+
		   "\nachievedTime[DARK][EXCELLENT]: ["+achievedTime[DARK][EXCELLENT]+"]"+
		   "\nachievedTime[DARK][AVERAGE]: ["+achievedTime[DARK][AVERAGE]+"]"+
		   "\nachievedTime[DARK][POOR]: ["+achievedTime[DARK][POOR]+"]"+
		   "\ntotalAchievedTime: ["+totalAchievedTime+"]"+
		   "\ntargetFraction[BRIGHT][EXCELLENT]: ["+targetFraction[BRIGHT][EXCELLENT]+"]"+
		   "\ntargetFraction[BRIGHT][AVERAGE]: ["+targetFraction[BRIGHT][AVERAGE]+"]"+
		   "\ntargetFraction[BRIGHT][POOR]: ["+targetFraction[BRIGHT][POOR]+"]"+
		   "\ntargetFraction[DARK][EXCELLENT]: ["+targetFraction[DARK][EXCELLENT]+"]"+
		   "\ntargetFraction[DARK][AVERAGE]: ["+targetFraction[DARK][AVERAGE]+"]"+
		   "\ntargetFraction[DARK][POOR]: ["+targetFraction[DARK][POOR]+"]"+
		   "\ntotalAllocatedTime: ["+totalAllocatedTime+"]");
    } // end (write).
    
} // Class Def. [TagDescriptor].

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:46:07  snf
/** modified write.
/**
/** Revision 1.1  2000/11/29 12:48:37  snf
/** Initial revision
/**. */
