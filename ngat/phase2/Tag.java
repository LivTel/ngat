package ngat.phase2;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Represents a Time Allocation Group in the Phase II database.
 * <br>
 * $Id: Tag.java,v 1.1 2000-11-23 14:05:28 snf Exp $
 *
 */

public class Tag extends DBObject implements Serializable {

     // Variables.

    /**  Time fraction actually acheived under BrightSky/ExcellentSeeing conditions. */
    protected float acheivedFraction_BX;
    
    
    /**  Time fraction actually acheived under BrightSky/AverageSeeing conditions. */
    protected float acheivedFraction_BA;
    
    
    /**  Time fraction actually acheived under BrightSky/PoorSeeing conditions. */
    protected float acheivedFraction_BP;
    
    
    /**  Time fraction actually acheived under DarkSky/ExcellentSeeing conditions. */
    protected float acheivedFraction_DX;
    
    
    /**  Time fraction actually acheived under DarkSky/AverageSeeing conditions. */
    protected float acheivedFraction_DA;
    
    
    /**  Time fraction actually acheived under DarkSky/PoorSeeing conditions. */
    protected float acheivedFraction_DP;
    
    
    /**  Total time allocated to this TAG (hours). */
    protected float totalAcheivedTime;
    
    
    /**   Time fraction allocated to BrightSky/ExcellentSeeing conditions. */
    protected float targetFraction_BX;
    
    
    /**   Time fraction allocated to BrightSky/AverageSeeing conditions. */
    protected float targetFraction_BA;
    
    
    /**   Time fraction allocated to BrightSky/PoorSeeing conditions. */
    protected float targetFraction_BP;
    
    
    /**   Time fraction allocated to DarkSky/ExcellentSeeing conditions. */
    protected float targetFraction_DX;
    
    
    /**   Time fraction allocated to DarkSky/AverageSeeing conditions. */
    protected float targetFraction_DA;
    
    
    /**   Time fraction allocated to DarkSky/PoorSeeing conditions. */
    protected float targetFraction_DP;
    
    
    /**  Total time allocated to this TAG (hours). */
    protected float totalAllocatedTime;
    protected OSHashMap users;
    
    // Constructor.
    
    public Tag() {this("untitled");}
    
    public Tag(String name) {
	super(name);
	users = new OSHashMap();
    }
    
    // Accessors.
    
    

    /** Sets the time fraction actually acheived under BrightSky/ExcellentSeeing conditions .*/
    public void setAcheivedFraction_BX(float in) { this.acheivedFraction_BX = in;}
    
    
    /** Returns the time fraction actually acheived under BrightSky/ExcellentSeeing conditions. */
    public float getAcheivedFraction_BX() { return acheivedFraction_BX;}
    
    
    /** Sets the time fraction actually acheived under BrightSky/AverageSeeing conditions .*/
    public void setAcheivedFraction_BA(float in) { this.acheivedFraction_BA = in;}
    
    
    /** Returns the time fraction actually acheived under BrightSky/AverageSeeing conditions. */
    public float getAcheivedFraction_BA() { return acheivedFraction_BA;}
    
    
    /** Sets the time fraction actually acheived under BrightSky/PoorSeeing conditions .*/
    public void setAcheivedFraction_BP(float in) { this.acheivedFraction_BP = in;}
    
    
    /** Returns the time fraction actually acheived under BrightSky/PoorSeeing conditions. */
    public float getAcheivedFraction_BP() { return acheivedFraction_BP;}
    
    
    /** Sets the time fraction actually acheived under DarkSky/ExcellentSeeing conditions .*/
    public void setAcheivedFraction_DX(float in) { this.acheivedFraction_DX = in;}
    
    
    /** Returns the time fraction actually acheived under DarkSky/ExcellentSeeing conditions. */
    public float getAcheivedFraction_DX() { return acheivedFraction_DX;}
    
    
    /** Sets the time fraction actually acheived under DarkSky/AverageSeeing conditions .*/
    public void setAcheivedFraction_DA(float in) { this.acheivedFraction_DA = in;}
    
    
    /** Returns the time fraction actually acheived under DarkSky/AverageSeeing conditions. */
    public float getAcheivedFraction_DA() { return acheivedFraction_DA;}
    
    
    /** Sets the time fraction actually acheived under DarkSky/PoorSeeing conditions .*/
    public void setAcheivedFraction_DP(float in) { this.acheivedFraction_DP = in;}
    
    
    /** Returns the time fraction actually acheived under DarkSky/PoorSeeing conditions. */
    public float getAcheivedFraction_DP() { return acheivedFraction_DP;}
    
    
    /** Sets the total time allocated to this TAG (hours) .*/
    public void setTotalAcheivedTime(float in) { this.totalAcheivedTime = in;}
    
    
    /** Returns the total time allocated to this TAG (hours). */
    public float getTotalAcheivedTime() { return totalAcheivedTime;}
    
    
    /** Sets the time fraction allocated to BrightSky/ExcellentSeeing conditions .*/
    public void setTargetFraction_BX(float in) { this.targetFraction_BX = in;}
    
    
    /** Returns the time fraction allocated to BrightSky/ExcellentSeeing conditions. */
    public float getTargetFraction_BX() { return targetFraction_BX;}
    
    
    /** Sets the time fraction allocated to BrightSky/AverageSeeing conditions .*/
    public void setTargetFraction_BA(float in) { this.targetFraction_BA = in;}
    
    
    /** Returns the time fraction allocated to BrightSky/AverageSeeing conditions. */
    public float getTargetFraction_BA() { return targetFraction_BA;}
    
    
    /** Sets the time fraction allocated to BrightSky/PoorSeeing conditions .*/
    public void setTargetFraction_BP(float in) { this.targetFraction_BP = in;}
    

    /** Returns the time fraction allocated to BrightSky/PoorSeeing conditions. */
    public float getTargetFraction_BP() { return targetFraction_BP;}
    
    
    /** Sets the time fraction allocated to DarkSky/ExcellentSeeing conditions .*/
    public void setTargetFraction_DX(float in) { this.targetFraction_DX = in;}
    
    
    /** Returns the time fraction allocated to DarkSky/ExcellentSeeing conditions. */
    public float getTargetFraction_DX() { return targetFraction_DX;}
    
    
    /** Sets the time fraction allocated to DarkSky/AverageSeeing conditions .*/
    public void setTargetFraction_DA(float in) { this.targetFraction_DA = in;}
    

    /** Returns the time fraction allocated to DarkSky/AverageSeeing conditions. */
    public float getTargetFraction_DA() { return targetFraction_DA;}
    
    
    /** Sets the time fraction allocated to DarkSky/PoorSeeing conditions .*/
    public void setTargetFraction_DP(float in) { this.targetFraction_DP = in;}
    
    
    /** Returns the time fraction allocated to DarkSky/PoorSeeing conditions. */
    public float getTargetFraction_DP() { return targetFraction_DP;}
    
    
    /** Sets the total time allocated to this TAG (hours) .*/
    public void setTotalAllocatedTime(float in) { this.totalAllocatedTime = in;}
    
    
    /** Returns the total time allocated to this TAG (hours). */
    public float getTotalAllocatedTime() { return totalAllocatedTime;}
    
    
    // User Methods.
    /** Add a User to this TAG. */
    public void addUser(User user) {
	users.put(user.getName(), user);
	user.setPath(getPath() + "/" + getName());
    }
    
    /** Remove the specified User from this TAG.*/
    public void deleteUser(User user) {
	users.remove(user.getName());
    }
    
    /** Remove ALL Users from this TAG.*/
    public void removeAllUsers() {
	users.clear();
    }
    
    /** Return an Iterator over the list of Users for this TAG.*/
    public Iterator listAllUsers() {
	return users.values().iterator();
    }
    
    /** Return a reference to the named User if the User is in this TAG.
     * Returns null if the user is not found.*/
    public User findUser(String name) {
	if (users.containsKey(name)) return ((User)users.get(name));
	return null;
    }
    
    /** Return a reference to the list of Users in this TAG.*/
    public OSHashMap getUsers() { return users;}
    
    // NP -> P Translator.
    /** Create a Tag from an NPTag.*/
    public Tag(NPTag npTag) {
	super(npTag);
	Iterator it;
	acheivedFraction_BX = npTag.getAcheivedFraction_BX();
	acheivedFraction_BA = npTag.getAcheivedFraction_BA();
	acheivedFraction_BP = npTag.getAcheivedFraction_BP();
	acheivedFraction_DX = npTag.getAcheivedFraction_DX();
	acheivedFraction_DA = npTag.getAcheivedFraction_DA();
	acheivedFraction_DP = npTag.getAcheivedFraction_DP();
	totalAcheivedTime = npTag.getTotalAcheivedTime();
	targetFraction_BX = npTag.getTargetFraction_BX();
	targetFraction_BA = npTag.getTargetFraction_BA();
	targetFraction_BP = npTag.getTargetFraction_BP();
	targetFraction_DX = npTag.getTargetFraction_DX();
	targetFraction_DA = npTag.getTargetFraction_DA();
	targetFraction_DP = npTag.getTargetFraction_DP();
	totalAllocatedTime = npTag.getTotalAllocatedTime();
	
	// Recursively call Daughter Translators.
	
	users = new OSHashMap();
	it = npTag.listAllNPUsers();
	while (it.hasNext()) {
	    try {
		NPUser npUser = (NPUser)it.next();
		Class npClazz = npUser.getClass();
		String npName = npClazz.getName();
		int k = npName.indexOf("nonpersist.NP");
		String pName = npName.substring(0,k).concat(npName.substring(k+13));
		Class pClazz = Class.forName(pName);
		Constructor pCon = pClazz.getConstructor(new Class[]{npClazz});
		User user = (User)pCon.newInstance(new Object[]{npUser});
		addUser(user);
	    } catch (ClassNotFoundException re1){
		System.out.println("Translation Error: "+re1);
	    } catch (NoSuchMethodException re2) {
		System.out.println("Translation Error: "+re2);
	    } catch (InvocationTargetException re3) {
		System.out.println("Translation Error: "+re3);
	    } catch (IllegalAccessException re4) {
		System.out.println("Translation Error: "+re4);
	    } catch (InstantiationException re5) {
		System.out.println("Translation Error: "+re5);
	    }
	}
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    /** When translating to NPTag, stuff all the internal fields and descendants.*/
    public void stuff(NPTag npTag) {
	super.stuff(npTag);
	Iterator it;
	npTag.setAcheivedFraction_BX(getAcheivedFraction_BX());
	npTag.setAcheivedFraction_BA(getAcheivedFraction_BA());
	npTag.setAcheivedFraction_BP(getAcheivedFraction_BP());
	npTag.setAcheivedFraction_DX(getAcheivedFraction_DX());
	npTag.setAcheivedFraction_DA(getAcheivedFraction_DA());
	npTag.setAcheivedFraction_DP(getAcheivedFraction_DP());
	npTag.setTotalAcheivedTime(getTotalAcheivedTime());
	npTag.setTargetFraction_BX(getTargetFraction_BX());
	npTag.setTargetFraction_BA(getTargetFraction_BA());
	npTag.setTargetFraction_BP(getTargetFraction_BP());
	npTag.setTargetFraction_DX(getTargetFraction_DX());
	npTag.setTargetFraction_DA(getTargetFraction_DA());
	npTag.setTargetFraction_DP(getTargetFraction_DP());
	npTag.setTotalAllocatedTime(getTotalAllocatedTime());
	it = listAllUsers();
	while (it.hasNext()) {
	    npTag.addNPUser((NPUser)(((User)it.next()).makeNP()));
	}
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    /** Make this Tag into an NPTag.*/
    public NPDBObject makeNP() {
	NPTag npTag = new NPTag();
	stuff(npTag);
	return npTag;
    } // end (makeNp).
    
    // Self Lock Method.
    /** Lock this Tag and recursively all descendants with the supplied key,*/
    public void lock(int key) {
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).

    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this Tag?.*/
    public void updateChildren() {
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
	lock = 0;
	Iterator it  = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    user.forceUnLock();
	}
    } // end (forceUnLock).
    
    /** Returns a descriptor for this Tag's internal fields.*/
    public TagDescriptor getDescriptor() {
	TagDescriptor descriptor = new TagDescriptor(name);
	descriptor.setPath(path);
	descriptor.setLock(lock);	
	
	descriptor.setAcheivedFraction_BX(acheivedFraction_BX);
	descriptor.setAcheivedFraction_BA(acheivedFraction_BA);
	descriptor.setAcheivedFraction_BP(acheivedFraction_BP);
	descriptor.setAcheivedFraction_DX(acheivedFraction_DX);
	descriptor.setAcheivedFraction_DA(acheivedFraction_DA);
	descriptor.setAcheivedFraction_DP(acheivedFraction_DP);
	
	descriptor.setTotalAcheivedTime(totalAcheivedTime);
	
	descriptor.setTargetFraction_BX(targetFraction_BX);
	descriptor.setTargetFraction_BA(targetFraction_BA);
	descriptor.setTargetFraction_BP(targetFraction_BP);
	descriptor.setTargetFraction_DX(targetFraction_DX);
	descriptor.setTargetFraction_DA(targetFraction_DA);
	descriptor.setTargetFraction_DP(targetFraction_DP);

	descriptor.setTotalAllocatedTime(totalAllocatedTime);
	return descriptor;
    }


    
    // FixedGroup Method.
    /** Return the next fixed group available ## DEPRECATED.*/
    public FixedGroup getNextFixedGroup() {
	FixedGroup earliest = new FixedGroup("NULL");
	// Set earliest Fixed to about Y29,688 AD !
	earliest.setFixedTime(999999999999999L);
	Iterator it = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    if (!user.isLocked() && !user.deleted()) {
		FixedGroup fg = user.getNextFixedGroup();
		if (fg != null) {
		    if (fg.getFixedTime() < earliest.getFixedTime()) {
			earliest = fg;
		    }
		}
	    }
	}
	return earliest;
    } // end (getNextFixedGroup).
    
    // Scheduling Method.
    /** Invoke the scheduling function on this Tag. Calls recursively 
     * through descendants until all objects which contribute to scheduling 
     * descisions have been visited.*/
    public ScheduleDescriptor schedule() {
	ScheduleDescriptor best = new ScheduleDescriptor(null,0.0f,0);
	Iterator it = listAllUsers();
	while (it.hasNext()) {
	    User user = (User)it.next();
	    if (!user.isLocked() && !user.deleted()) {
		ScheduleDescriptor sd = user.schedule();
		if (sd.getGroup() != null) {
		    if (sd.getScore() > best.getScore()) {
			best.setGroup(sd.getGroup());
			best.setScore(sd.getScore());
			best.setExecTime(sd.getExecTime());
		    }
		}
	    }
	}
	return applyScheduling(best);
    } // end (schedule).
    
    // Scheduling Algorithm.
    /** Include any Tag specific schedule weighting with this method.
     * Currently this does nothing.
     * @param best The 'best' schedule so far.
     * @return The modified schedule.*/
    public ScheduleDescriptor applyScheduling(ScheduleDescriptor best) {	
	// Insert code here to apply this.object's schedule coefft.
	// will make use of attribute: [ schedCoeff ] and possibly
	// various ServerContext and TelescopeEnvironment variables.	
	best.setScore(best.getScore()*schedCoeff);
	return best;			
    } // end (applyScheduling).    

} // end class def [Tag].

/** $Log: not supported by cvs2svn $ */
