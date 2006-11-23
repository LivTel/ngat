package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


/**
 * The entry point for the Phase II database. All objects in the
 * database tree hang off this (singleton) object.
 * <br>
 * $Id: Root.java,v 1.3 2006-11-23 10:43:05 snf Exp $
 *
 */
public class Root extends DBObject implements Serializable {
 
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -1018898926424988916L;
    
	// Variables.

    /** The list of TAGs associated with this database. */
    protected SerializableStorableSortedDictionary tags;
    
    // Constructor.
    
    /** Create a database Root with the name 'untitled'. */
    public Root() {this("untitled");}
    
    /** Create a database Root with the specified (database) name. */
    public Root(String name) {
	super(name);
	tags = new SerializableStorableSortedDictionary();
    }

    // Accessors.
    // There are no additional accessors.

    // Descendant Mutators.
    
    // Tag Methods.

    /** Add the specified TAG to the database Root.*/
    public void addTag(Tag tag) {
	this.storableUpdate();
	tags.put(tag.getName(), tag);
	tag.setPath(getPath() + "/" + getName());
    }
    
    /** Remove the specified TAG from the database Root.*/
    public void deleteTag(Tag tag) {
	this.storableUpdate();
	tags.remove(tag.getName());
    }

    /** Remove ALL TAGs from the database Root.*/
    public void removeAllTags() {
	this.storableUpdate();
	tags.clear();
    }
    
    /** Return an Iterator over the list of TAGs.*/
    public Iterator listAllTags() {
	this.storableFetch();
	return tags.values().iterator();
    }
    
    /** Locate the record of the TAG with the given name or null if not found.*/
    public Tag findTag(String name) {
	this.storableFetch();
	if (tags.containsKey(name)) return ((Tag)tags.get(name));
	return null;
    }

    /** Return a reference to the list of TAGs.*/
    public SerializableStorableSortedDictionary getTags() { 
	this.storableFetch();
	return tags;
    }
  
    // Self Lock Method.
    
    /** Lock this database Root) and recursively all descendants with the supplied key,*/
    public void lock(int key) {
	this.storableUpdate();
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).

    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this Root?.*/
    public void updateChildren() {
	this.storableUpdate();
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
	this.storableFetch();
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
	this.storableUpdate();
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
	this.storableUpdate();
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
	this.storableUpdate();
	lock = 0;
	Iterator it  = listAllTags();
	while (it.hasNext()) {
	    Tag tag = (Tag)it.next();
	    tag.forceUnLock();
	}
    } // end (forceUnLock).

   
} // end class def [Root].

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/11/29 12:42:02  snf
/** Entry point to PhaseII database.
/**
/** Revision 1.1  2000/11/23 11:38:47  snf
/** Initial revision
/** */
