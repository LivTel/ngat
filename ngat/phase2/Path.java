package ngat.phase2;

import java.util.*;
import java.io.*;

/** Converts a string description of a database object's position in the hierarchy
 * into a series of objects in the descendancy path..*/

public class Path implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 578809207634447571L;
    
	/** Holds the Root object in the descendancy path. */
    protected String p_Root;

    /** Holds the Tag ancestor in the descendancy path. */
    protected String p_Tag;

    /** Holds the User ancestor in the descendancy path. */
    protected String p_User;

    /** Holds the Proposal ancestor in the descendancy path. */
    protected String p_Proposal;

    /** Holds the Group ancestor in the descendancy path. */
    protected String p_Group;

    /** Holds the Observation ancestor in the descendancy path. */
    protected String p_Observation;

    /** Holds the input string, used for toString(). */
    protected String pathExpr;

    /** make a Path from the expression. */
    public Path(String pathExpr) {

	this.pathExpr = pathExpr;
	StringTokenizer st = new StringTokenizer(pathExpr, "/");
	int count = st.countTokens();
	p_Root = null;
	p_Tag = null;
	p_User = null;
	p_Proposal = null;
	p_Group = null;
	p_Observation = null;
	if (count > 0)p_Root = st.nextToken();
	if (count > 1)p_Tag = st.nextToken();
	if (count > 2)p_User = st.nextToken();
	if (count > 3)p_Proposal = st.nextToken();
	if (count > 4)p_Group = st.nextToken();
	if (count > 5)p_Observation = st.nextToken();
    }

    /** Make a Path from a relative pathexpression and leaf. */
    public Path(String path, String name) { this(path+"/"+name);}

    /** Get the id of the Root ancestor. */
    public String getRootByName() { return p_Root;}
    /** Get the id of the Tag ancestor. */
    public String getTagByName() { return p_Tag;}
    /** Get the id of the User ancestor. */
    public String getUserByName() { return p_User;}
    /** Get the id of the Proposal ancestor. */
    public String getProposalByName() { return p_Proposal;}
    /** Get the id of the Group ancestor. */
    public String getGroupByName() { return p_Group;}
    /** Get the id of the Observation ancestor. */
    public String getObservationByName() { return p_Observation;}

    public String getRootPathByName()     { return  "/"+p_Root; }
    public String getTagPathByName()      { return  "/"+p_Root+"/"+p_Tag; }
    public String getUserPathByName()     { return  "/"+p_Root+"/"+p_Tag+"/"+p_User; }
    public String getProposalPathByName() { return  "/"+p_Root+"/"+p_Tag+"/"+p_User+"/"+p_Proposal; }


    /** Return a String representation of the Path. */
    public String toString() { return pathExpr;}

    /** A convenience method to return a Path. */
    public static Path getPathFor(String pathExpr, String leaf) {
	return new Path(pathExpr+"/"+leaf);
    }

}





