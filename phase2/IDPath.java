package ngat.phase2;

import java.io.*;
import java.util.*;

/** An IDPath is used to identify a Phase2 object. 
 * It is generally associated with lower level objects such as Groups.
 */
public class IDPath implements Serializable {

    private String groupId;

    private String tagId;

    private String programId;

    private String userId;

    private String proposalId;

    public IDPath(String groupId) {
	this.groupId = groupId;
    }

    public void setTagId(String id) {this.tagId = id;}

    public String getTagId() { return tagId; }
    

    public void setProgramId(String id) {this.programId = id;}

    public String getProgramId() { return programId; }
    
    
    public void setUserId(String id) {this.userId = id;}

    public String getUserId() { return userId; }
    

    public void setProposalId(String id) {this.proposalId = id;}

    public String getProposalId() { return proposalId; }
    

    public void setGroupId(String id) {this.groupId = id;}

    public String getGroupId() { return groupId; }


    public String toString() {
	return "["+tagId+"]/["+userId+"]/["+programId+"]/["+proposalId+"]/["+groupId+"]";

	// e.g. JMU/jim.smith/PRG5B/JL05B17/CassA-2days

    }

    /** Check for equality. Returns true only if all fields match.*/
    public boolean equals(Object other) {
	
	if (! (other instanceof IDPath))
	    return false;

	IDPath oid = (IDPath)other;

	if (tagId == null && oid.getTagId() != null)
	    return false;
	if (tagId != null && oid.getTagId() == null)
	    return false;
	if (tagId != null && oid.getTagId() != null &&
	    !oid.getTagId().equals(tagId))
	    return false;
	
	if (userId == null && oid.getUserId() != null)
	    return false;
	if (userId != null && oid.getUserId() == null)
	    return false;
	if (userId != null && oid.getUserId() != null &&
	    !oid.getUserId().equals(userId))
	    return false;

	if (programId == null && oid.getProgramId() != null)
	    return false;
	if (programId != null && oid.getProgramId() == null)
	    return false;
	if (programId != null && oid.getProgramId() != null &&
	    !oid.getProgramId().equals(programId))
	    return false;

	if (proposalId == null && oid.getProposalId() != null)
	    return false;
	if (proposalId != null && oid.getProposalId() == null)
	    return false;
	if (proposalId != null && oid.getProposalId() != null &&
	    !oid.getProposalId().equals(proposalId))
	    return false;


	if (groupId == null && oid.getGroupId() != null)
	    return false;
	if (groupId != null && oid.getGroupId() == null)
	    return false;
	if (groupId != null && oid.getGroupId() != null &&
	    !oid.getGroupId().equals(groupId))
	    return false;

	return true;

    }

    public int hashCode() {
	return toString().hashCode();
    }



}

