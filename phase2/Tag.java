package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Represents a Time Allocation Group in the Phase II database.
 * <br>
 * $Id$
 *
 */

public class Tag extends DBObject implements Serializable {
 
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 5525911300950747505L;
    
	// Variables.
    public static final int BRIGHT = 0;

    public static final int DARK   = 1;

    public static final int EXCELLENT = 2;

    public static final int AVERAGE   = 1;
    
    public static final int POOR      = 0;

  
    protected float[][] achievedTime;
    
    protected float[][] targetFraction;
    
    /**  Total time used by this TAG (hours). */
    protected float totalAchievedTime;
       
    /**  Total time allocated to this TAG (hours). */
    protected float totalAllocatedTime;
    
    protected SerializableStorableSortedDictionary users;
    
    // Constructor.
    
    public Tag() {this("untitled");}
    
    public Tag(String name) {
	super(name);
	users = new SerializableStorableSortedDictionary();
	achievedTime   = new float[2][3];
	targetFraction = new float[2][3];
    }
    
    // Accessors.
    
    

    /** Sets the time fraction actually achieved under BrightSky/ExcellentSeeing conditions .*/
    public void setAchievedTime(int lunar, int seeing, float in) { this.storableUpdate();this.achievedTime[lunar][seeing] = in;}    
    
    /** Returns the time fraction actually achieved under BrightSky/ExcellentSeeing conditions. */
    public float getAchievedTime(int lunar, int seeing) {  this.storableFetch();return achievedTime[lunar][seeing];}
          
    /** Sets the total time allocated to this TAG (hours) .*/
    public void setTotalAchievedTime(float in) {  this.storableUpdate();this.totalAchievedTime = in;}
        
    /** Returns the total time allocated to this TAG (hours). */
    public float getTotalAchievedTime() {  this.storableFetch();return totalAchievedTime;}
    
    
    /** Sets the time fraction allocated to BrightSky/ExcellentSeeing conditions .*/
    public void setTargetFraction(int lunar, int seeing, float in) {  this.storableUpdate();this.targetFraction[lunar][seeing] = in;}
    
    /** Returns the time fraction allocated to BrightSky/ExcellentSeeing conditions. */
    public float getTargetFraction(int lunar, int seeing) {  this.storableFetch();return targetFraction[lunar][seeing];}    
    
    /** Sets the total time allocated to this TAG (hours) .*/
    public void setTotalAllocatedTime(float in) {  this.storableUpdate();this.totalAllocatedTime = in;}    
    
    /** Returns the total time allocated to this TAG (hours). */
    public float getTotalAllocatedTime() {  this.storableFetch();return totalAllocatedTime;}
    
    
    // User Methods.
    /** Add a User to this TAG. */
    public void addUser(User user) { this.storableUpdate();
	users.put(user.getName(), user);
	user.setPath(getPath() + "/" + getName());
    }
    
    /** Remove the specified User from this TAG.*/
    public void deleteUser(User user) { this.storableUpdate();
	users.remove(user.getName());
    }
    
    /** Remove ALL Users from this TAG.*/
    public void removeAllUsers() { this.storableUpdate();
	users.clear();
    }
    
    /** Return an Iterator over the list of Users for this TAG.*/
    public Iterator listAllUsers() { this.storableFetch();
	return users.values().iterator();
    }
    
    /** Return a reference to the named User if the User is in this TAG.
     * Returns null if the user is not found.*/
    public User findUser(String name) { this.storableFetch();
	if (users.containsKey(name)) return ((User)users.get(name));
	return null;
    }
    
    /** Return a reference to the list of Users in this TAG.*/
    public SerializableStorableSortedDictionary getUsers() {  this.storableFetch();return users;}
       
    // Self Lock Method.
    /** Lock this Tag and recursively all descendants with the supplied key,*/
    public void lock(int key) { 
	this.storableUpdate();
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).

    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this Tag?.*/
    public void updateChildren() { 
	this.storableUpdate();
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    user.setPath(path+"/"+name);
	    user.updateChildren();
	}
    } // end (updateChildren).
    
    // Child Lock-Check Method.
    /** Return true if this Tag and ALL descendants are unlocked.*/
    public boolean canLock() { 
	this.storableFetch();
	if (isLocked()) return false;
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    if (!user.canLock()) return false;
	}
	return true;
    } // end (canLock).
    
    // Child Locking Method.
    /** Lock all descendants using the supplied key.*/
    public void lockChildren(int key) { 
	this.storableUpdate();
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    user.lockChildren(key);
	}
	setLock(key);
    } // end (lockChildren).
    
    // Child Un-Locking Method.
    /** Unlock this (Tag) and all descendants using the supplied key if possible.
     * Returns silently if it  fails. Use canLock() to see if the unlock succeeded.*/
    public void unLockChildren(int key) {
	this.storableUpdate();
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    user.unLockChildren(key);
	}
	unLock(key);
    } // end (unLockChildren).
    
    // Force Un-Locking Method.
    /** Forcibly unlock this (Tag) and all descendants whatever their current key.*/
    public void forceUnLock() {
	this.storableUpdate();
	lock = 0;
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    user.forceUnLock();
	}
    } // end (forceUnLock).
    
    /** Returns a descriptor for this Tag's internal fields.*/
    public TagDescriptor getDescriptor() { 
	this.storableFetch();
	TagDescriptor descriptor = new TagDescriptor(name);
	descriptor.setPath(path);
	descriptor.setLock(lock);	
	
	descriptor.setAchievedTime(BRIGHT, EXCELLENT, achievedTime[BRIGHT] [EXCELLENT]);
	descriptor.setAchievedTime(BRIGHT, AVERAGE,   achievedTime[BRIGHT] [AVERAGE]);
	descriptor.setAchievedTime(BRIGHT, POOR,      achievedTime[BRIGHT] [POOR]);
	descriptor.setAchievedTime(DARK, EXCELLENT,   achievedTime[DARK] [EXCELLENT]);
	descriptor.setAchievedTime(DARK, AVERAGE,     achievedTime[DARK] [AVERAGE]);
	descriptor.setAchievedTime(DARK, POOR,        achievedTime[DARK] [POOR]);
	
	descriptor.setTotalAchievedTime(totalAchievedTime);
	
	descriptor.setTargetFraction(BRIGHT, EXCELLENT, targetFraction[BRIGHT] [EXCELLENT]);
	descriptor.setTargetFraction(BRIGHT, AVERAGE,   targetFraction[BRIGHT] [AVERAGE]);
	descriptor.setTargetFraction(BRIGHT, POOR,      targetFraction[BRIGHT] [POOR]);
	descriptor.setTargetFraction(DARK, EXCELLENT,   targetFraction[DARK] [EXCELLENT]);
	descriptor.setTargetFraction(DARK, AVERAGE,     targetFraction[DARK] [AVERAGE]);
	descriptor.setTargetFraction(DARK, POOR,        targetFraction[DARK] [POOR]);

	descriptor.setTotalAllocatedTime(totalAllocatedTime);
	return descriptor;
    }

    public String toString() {
	return "[Tag: "+name+" : "+
	    ", Achieved={BX="+achievedTime[BRIGHT][ EXCELLENT]+
	    ", BA="+achievedTime[BRIGHT][ AVERAGE]+
	    ", BP="+achievedTime[BRIGHT][ POOR]+
	    ", DX="+achievedTime[DARK][ EXCELLENT]+
	    ", DA="+achievedTime[DARK][ AVERAGE]+
	    ", DP="+achievedTime[DARK][ POOR]+"}"+
	    " Target={BX="+targetFraction[BRIGHT][ EXCELLENT]+
	    ", BA="+targetFraction[BRIGHT][ AVERAGE]+
	    ", BP="+targetFraction[BRIGHT][ POOR]+
	    ", DX="+targetFraction[DARK][ EXCELLENT]+
	    ", DA="+targetFraction[DARK][ AVERAGE]+
	    ", DP="+targetFraction[DARK][ POOR]+"}"+
	    " Total-alloc="+totalAllocatedTime+
	    ", Total-achieved="+totalAchievedTime+
	    "]";
    }

    /** Include any Tag specific schedule weighting with this method.
     *
     * @param sched The schedule item so far.
     * @return The modified schedule.*/
    public ScheduleDescriptor applyScheduling(ScheduleDescriptor sched) {	
	// Insert code here to apply this.object's schedule coefft.
	// will make use of attribute: [ schedCoeff ] and possibly
	// various ServerContext and TelescopeEnvironment variables.	
	Group group  = sched.getGroup();
	double score = sched.getScore();

	double fairnessFraction = achievedTime [group.getMinimumLunar()] [group.getMinimumSeeing()] /
	    (targetFraction [group.getMinimumLunar()] [group.getMinimumSeeing()]
	     * totalAllocatedTime);
	// The scale factor (4.0) determines over how long the fairnesses will
	// require to even out between Tags.
	double score1 = 1.0 / ( 1.0 + 4.0 * fairnessFraction );
	//logger.log(1,"Tag fairness fraction: "+fairnessFraction);
	//logger.log(1,"..Scores: "+score1);
	score *= score1;
	score *= schedCoeff;
	sched.setScore(score);

	return sched;	
	
    } // end (applyScheduling).    

} // end class def [Tag].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 14:05:28  snf
/** Initial revision
/** */
