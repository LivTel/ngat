package ngat.phase2;

import ngat.phase2.nonpersist.*;
import java.util.*;
import java.io.*;

/** 
 * A top level object from which all database objects are derived. Contains
 * generic variables and methods.
 * $Id: DBObject.java,v 1.2 2001-02-23 18:45:20 snf Exp $
 */
public class DBObject implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 3059223912664057529L;
    
    /** The unique (at a given subtree level) name/ident for this object. */
    protected String name;

    /** The unique path expression for this object. A string made up from
     * the names of all its ancestors concatenated in order.
     * <br>
     * <pre>
     * e.g. Root/Tag1/User3 for a particular user's Proposals.
     * <pre>
     */
    protected String path;

    /** A PRBS generated key needed to unlock this object. It is upto the
     * client application to ensure the key it generates is <i>sufficiently random</i>.*/
    protected int lock;

    /** A generic scheduling coefficient for the object. These are used in
     * different ways by different objects, if at all. */
    protected float schedCoeff;

    /** Denotes whether the object is marked for deletion at a later stage. */
    protected boolean deleted;

    /** Construct a generic object with the name 'untitled'. */
    public DBObject() { this("untitled");}

    /** Construct a generic object with specified name. */
    public DBObject(String name) {
	this.name = name;
	this.path = "";
	this.schedCoeff = 1.0f;
	this.lock = 0;
	this.deleted = false;
    }

    /** Construct a DBObject from the supplied NonPersistent DBObject. */
    public DBObject(NPDBObject npo) {
	name = npo.getName();
	path = npo.getPath();
	schedCoeff = npo.getSchedCoeff();
	lock = npo.getLock();
	deleted = false;
    }

    
    /** Translate the DBObject into a NonPersistent mirror object.
     * This method just pushes relevant stuff into an existing NPDBObject. */
    public void stuff(NPDBObject npo) {
	npo.setName(name);
	npo.setPath(path);
	npo.setSchedCoeff(schedCoeff);
	npo.setLock(lock);
    }

    /** Translate to NP, creating a new NPDBObject. */
    public NPDBObject makeNP() {
	NPDBObject npo = new NPDBObject();
	stuff(npo);
	return npo;
    }

    // Database locking methods.

    /** Set the Lock for this object. 
     * @return false if alreadylocked with a different key. */
    public boolean setLock(int key) { 
	boolean set = true;   
	if (isLocked() && key != getLock()) set = false;
	this.lock = key;
	return set;
    }
    
    /** Unlock this object using the specified key.
     * @return false if locked with a different key. */
    public boolean unLock(int key) {
	boolean freed = true;   
	if (isLocked() && key != getLock()) freed = false;
	this.lock = 0;
	return freed;
    }

    /** Determine whether the object is locked. */
    public boolean isLocked() { return (lock != 0);}

    /** Get the key for this object's lock.*/
    public int getLock() { return lock;}

    // Generic Methods.

    /** Get the object's identity. */
    public String getName() { return name;}
    
    /** Set the Object's name (used by stuffers). */
    public void setName(String name) { this.name = name;}

    public void setId(String name) { this.name = name;}

    public String getId() { return name;}

    /** Set the object's path - when placing in a tree. */
    public void setPath(String path) {this.path = path;}
    
    /** Get the object's path expression - ie. its tree location. */
    public String getPath() { return path;}

    /** Get the full path anme for this Object. */
    public String getFullPath() { return path+"/"+name;}

    /** Get the object's schedule coefficient. */
    public float getSchedCoeff() { return schedCoeff;}

    /** Set the object's schedule coefficient. */
    public void setSchedCoeff(float schedCoeff) {this.schedCoeff = schedCoeff;}

    /** Determine whether the object is marked for deletion. */
    public boolean deleted() { return deleted;}

    /** Mark the object for deletion - the time of deletion is not known
     * in advance, a future implementation may allow a period of grace to be specified.*/
    public void setDeleted(boolean deleted) { this.deleted = deleted;}

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 11:38:08  snf
/** Initial revision
/**
/** Revision 1.1  2000/05/25 10:14:41  snf
/** Initial revision
/** */


