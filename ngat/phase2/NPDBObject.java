package ngat.phase2;

import java.util.*;
import java.text.*;
import java.io.*;

/** 
 * A top level object from which all database objects are derived. Contains
 * generic variables and methods.
 * <br><br>
 * $Id: NPDBObject.java,v 1.1 2007-08-07 12:02:27 snf Exp $.
 */
public class NPDBObject implements Serializable, Cloneable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -360506483491750566L;
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
    
    static NumberFormat nf = NumberFormat.getInstance();

    /** The unique (at a given subtree level) name/ident for this object. */
    protected String name;

    /** The unique path expression for this object. */
    protected String path;

    /** A PRBS generated key needed to unlock this object. */
    protected int lock;

    /** A generic scheduling coefficient for the object. These are used in
     * different ways by different objects, if at all. */
    protected float schedCoeff;
    
    /** A unique object ID.*/
    protected long oid;
    
    /** Construct a generic object. */
    public NPDBObject() { this("untitled");}

    /** Construct a generic object with specified name. */
    public NPDBObject(String name) {
	this.name = name;
	this.path = "";
	this.schedCoeff = 1.0f;
	this.lock = 0;
    }

     /** Construct a NPDBObject from the supplied NonPersistent DBObject. */
    public NPDBObject(NPDBObject npo) {
	name = npo.getName();
	path = npo.getPath();
	schedCoeff = npo.getSchedCoeff();
	lock = npo.getLock();
    }

    // Database locking methods.
    
    /** Set the Lock for this object. Always sets this key.
     * @return false if locked with a different key. */
    public boolean setLock(int key) { 
	boolean set = true;   
	if (isLocked() && key != getLock()) set = false;
	this.lock = key;
	return set;
    }
    
    /** Unlock this object using the specified key. Always unlocks.
     * @return false if locked with a different key. */
    public boolean unLock(int key) {
	boolean freed = true;   
	if (isLocked() && key != getLock()) freed = false;
	this.lock = 0;
	return freed;
    }
    

    /** Determine whether the object is locked. */
    public boolean isLocked() { return (lock != 0);}

    /** Get the key for this object's lock.. this will be made protected.*/
    public int getLock() { return lock;}

    // Generic Methods.

    /** Get the object's identity. */
    public String getName() { return name;}

    /** Set the object's identity. */
    public void setName(String name) { this.name = name;}
    
    /** Get the identity for this object.*/
    public String getId() { return name;}
    
    /** Set the object's identity. */
    public void setId(String name) { this.name = name;}    

    /** Get the object's path expression - ie. its tree location. */
    public String getPath() { return path;}

    /** Set the object's path - when placing in a tree. */
    public void setPath(String path) {this.path = path;}
    
    /** Get the object's full pathname. */
    public String getFullPath() { return path+"/"+name; }

    /** Get the object's schedule coefficient. */
    public float getSchedCoeff() { return schedCoeff;}

    /** Set the object's schedule coefficient. */
    public void setSchedCoeff(float schedCoeff) {this.schedCoeff = schedCoeff;}


    /** Translate the NPDBObject into a NonPersistent mirror object.
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

    /** Write formatted output to a printstream.*/
    public void write(PrintStream out, int level) {
	out.println(tab(level)+"<"+this.getClass().getName()+">");
	out.println(tab(level)+"Name: ["+name+"]");
	out.println(tab(level)+"Path: ["+path+"]");
	out.println(tab(level)+"Lock: ["+lock+"]");
    }
    
    /** Write formatted output to a printstream as XML.*/
    public void writeXml(PrintStream out, int level) {
	String ss = this.getClass().getName();
	if (ss.lastIndexOf(".") != -1) 
	    ss = ss.substring(ss.lastIndexOf(".")+1);
	out.println(tab(level)+"<"+ss+">");
	out.println(tab(level+1)+"<name>"+name+"</name>");
	out.println(tab(level+1)+"<path>"+path+"</path>");
	out.println(tab(level+1)+"<lock>"+lock+"</lock>");
    }
    
    public void write(StringBuffer buffer, int level) {
	buffer.append(tab(level)+"name="+name);
    }

    public String tab(int level) {
	StringBuffer buff = new StringBuffer("");
	for (int i = 0; i < level; i++) {
	    buff.append("   ");
	}
	return buff.toString();
    }

	/**
	 * @return Returns the oid.
	 */
	public long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(long oid) {
		this.oid = oid;
	}

	
}


/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:46:07  snf
/** modified write.
/**
/** Revision 1.1  2000/11/29 12:48:37  snf
/** Initial revision
/** */

