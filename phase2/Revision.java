package ngat.phase2;

import java.io.*;
import java.util.*;
import java.text.*;

/** Stores a Proposal's revision history .
 *    
 */
public class Revision implements Serializable {
	
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 8157565854678558848L;
    
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");

    /** Time of editing submission.*/
    protected long time;
    
    /** Version comment.*/
    protected String comment;
    
    /** UserID of the editor.*/
    protected String editor;
    
    /** Registration ID of the editor's editing tool.*/
    protected long editorRegId;

    public Revision() {}
    
    /** Create a Revision entry.*/
    public Revision(long time, String editor, long editorRegId, String comment) {
	this();
	this.time        = time;
	this.editor      = editor;
	this.editorRegId = editorRegId;
	this.comment     = comment;
    }
    
    /** Return the time of editing submission.*/
    public long getTime() { return time; }
    
    /** Return the userID of the editor.*/
    public String getEditor() { return editor; }
    
    /** Return the registration ID of the editor's editing tool.*/
    public long getEditorRegId() { return editorRegId; }
    
    /** Return the version comment.*/
    public String getComment() { return comment; }
    
    /** Return a readable String representation.*/
    public String toString() {
	return "[Revision: Date="+sdf.format(new Date(time))+
	    ", Editor="+editor+
	    ", RegID="+editorRegId+
	    ", Comment="+comment+"]";
    }
    
}
