package ngat.phase2;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


/**
 * The entry point for the Phase II database. All objects in the
 * database tree hang off this (singleton) object.
 * <br>
 * $Id: Root.java,v 1.2 2000-11-29 12:42:02 snf Exp $
 *
 */
public class Root extends DBObject implements Serializable {

     // Variables.

    /** The list of TAGs associated with this database. */
    protected OSHashMap tags;
    
    // Constructor.
    
    /** Create a database Root with the name 'untitled'. */
    public Root() {this("untitled");}
    
    /** Create a database Root with the specified (database) name. */
    public Root(String name) {
	super(name);
	tags = new OSHashMap();
    }

    // Accessors.
    // There are no additional accessors.

    // Descendant Mutators.
    
    // Tag Methods.

    /** Add the specified TAG to the database Root.*/
    public void addTag(Tag tag) {
	tags.put(tag.getName(), tag);
	tag.setPath(getPath() + "/" + getName());
    }
    
    /** Remove the specified TAG from the database Root.*/
    public void deleteTag(Tag tag) {
	tags.remove(tag.getName());
    }

    /** Remove ALL TAGs from the database Root.*/
    public void removeAllTags() {
	tags.clear();
    }
    
    /** Return an Iterator over the list of TAGs.*/
    public Iterator listAllTags() {
	return tags.values().iterator();
    }
    
    /** Locate the record of the TAG with the given name or null if not found.*/
    public Tag findTag(String name) {
	if (tags.containsKey(name)) return ((Tag)tags.get(name));
	return null;
    }

    /** Return a reference to the list of TAGs.*/
    public OSHashMap getTags() { return tags;}
    
    // NP -> P Translator.
    
    /** Translate a NonPersistent NPRoot into a Root including all descendants.*/
    public Root(NPRoot npRoot) {
	super(npRoot);
	Iterator it;
	
	// Recursively call Daughter Translators.
	
	tags = new OSHashMap();
	it = npRoot.listAllNPTags();
	while (it.hasNext()) {
	    try {
		NPTag npTag = (NPTag)it.next();
		Class npClazz = npTag.getClass();
		String npName = npClazz.getName();
		int k = npName.indexOf("nonpersist.NP");
		String pName = npName.substring(0,k).concat(npName.substring(k+13));
		Class pClazz = Class.forName(pName);
		Constructor pCon = pClazz.getConstructor(new Class[]{npClazz});
		Tag tag = (Tag)pCon.newInstance(new Object[]{npTag});
		addTag(tag);
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
    /** When translating to NPRoot, stuff all the internal fields and descendants.*/
    public void stuff(NPRoot npRoot) {
	super.stuff(npRoot);
	Iterator it;
	it = listAllTags();
	while (it.hasNext()) {
	    npRoot.addNPTag((NPTag)(((Tag)it.next()).makeNP()));
	}
    } // end (P -> NP Translator).
    
    // P -> NP Translator.   
    /** Make this Root into an NPRoot.*/
    public NPDBObject makeNP() {
	NPRoot npRoot = new NPRoot();
	stuff(npRoot);
	return npRoot;
    } // end (makeNp).
    
    // Self Lock Method.
    
    /** Lock this database Root) and recursively all descendants with the supplied key,*/
    public void lock(int key) {
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).

    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this Root?.*/
    public void updateChildren() {
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    tag.setPath(path+"/"+name);
	    tag.updateChildren();
	}
    } // end (updateChildren).
    
    // Child Lock-Check Method.
    /** Return true if this Root and ALL descendants are unlocked.*/
    public boolean canLock() {
	if (isLocked()) return false;
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    if (!tag.canLock()) return false;
	}
	return true;
    } // end (canLock).
    
    // Child Locking Method.
    /** Lock all descendants using the supplied key.*/
    public void lockChildren(int key) {
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    tag.lockChildren(key);
	}
	setLock(key);
    } // end (lockChildren).

    // Child Un-Locking Method.
    /** Unlock this (Root) and all descendants using the supplied key if possible.
     * Returns silently if it  fails. Use canLock() to see if the unlock succeeded.*/
    public void unLockChildren(int key) {
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    tag.unLockChildren(key);
	}
	unLock(key);
    } // end (unLockChildren).
    
    // Force Un-Locking Method.
    /** Forcibly unlock this (Root) and all descendants whatever their current key.*/
    public void forceUnLock() {
	lock = 0;
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    tag.forceUnLock();
	}
    } // end (forceUnLock).

    // FixedGroup Method.
    /** Return the next fixed group available ## DEPRECATED.*/
    public FixedGroup getNextFixedGroup() {
	FixedGroup earliest = new FixedGroup("NULL");
	// Set earliest Fixed to about Y29,688 AD !
	earliest.setFixedTime(999999999999999L);
	Iterator it = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    if (!tag.isLocked() && !tag.deleted()) {
		FixedGroup fg = tag.getNextFixedGroup();
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
    /** Invoke the scheduling function on this database. Calls recursively 
     * through descendants until all objects which contribute to scheduling 
     * descisions have been visited.*/
    public ScheduleDescriptor schedule() {
	ScheduleDescriptor best = new ScheduleDescriptor(null,0.0f,0);
	Iterator it = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    if (!tag.isLocked() && !tag.deleted()) {
		ScheduleDescriptor sd = tag.schedule();
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
    /** Include any Root specific schedule weighting with this method.
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
    
} // end class def [Root].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 11:38:47  snf
/** Initial revision
/** */
