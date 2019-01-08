package ngat.phase2;

import ngat.phase2.nonpersist.*;
import java.io.*;

/** Holds a reference to a generic DBObject and its associated DB lock. */
public class DBObjectDescriptor implements Serializable {

    /** Reference to the contained DBObject. */
    protected DBObject object;
    
    /** The key which the DBObject has been locked with. */
    protected int key;

    /** Null constructor. */
    public DBObjectDescriptor() {}

    /** Make a DBObjectDescriptor using specified Object and key. */
    public DBObjectDescriptor(DBObject object, int key) {
	this.object = object;
	this.key = key;
    }

    /** Get the reference to the contained DBObject. */
    public DBObject getObject() { return object;}

    /** Get the reference to the object's key. */
    public int getKey() { return key;}

}
