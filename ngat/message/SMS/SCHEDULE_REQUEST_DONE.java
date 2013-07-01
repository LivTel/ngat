package ngat.message.SMS;

import ngat.message.base.*;
import ngat.phase2.*;
import ngat.sms.*;

public class SCHEDULE_REQUEST_DONE extends COMMAND_DONE {
    	
    /** An internal group.*/
    private GroupItem group;
    
    /** The proposal to which this GroupItem belongs.*/
    // private IProposal proposal;
    
    /** The program to which this GroupItem belongs.*/
    // private IProgram program;
    
    /** The TAG to which this GroupItem belongs.*/
    // private ITag tag;
    
    /** The user to which this GroupItem belongs.*/
    // private IUser user;
    
    /** Groups observation sequence.*/
    //private ISequenceComponent sequence;
	
    /** The ID of the execution history item belonging to this group execution instance 
     * - may be changed to a full IHistoryItem in the fullness of time or a HistSynopsis but needs an ID for matching with exec.
     */
    //private long historyId;
	

    public SCHEDULE_REQUEST_DONE(String name) {
	 super(name);
    }


     /**
	 * @return the group.
	 */
	public GroupItem getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(GroupItem group) {
		this.group = group;
	}




//     /**
// 	 * @return the proposal to which this GroupItem belongs.
// 	 */
// 	public IProposal getProposal() {
// 		return proposal;
// 	}

// 	/**
// 	 * @param proposal the proposal to set
// 	 */
// 	public void setProposal(IProposal proposal) {
// 		this.proposal = proposal;
// 	}

// 	/**
// 	 * @return the program to which this GroupItem belongs.
// 	 */
// 	public IProgram getProgram() {
// 		return program;
// 	}

// 	/**
// 	 * @param program the program to set
// 	 */
// 	public void setProgram(IProgram program) {
// 		this.program = program;
// 	}

// 	/**
// 	 * @return the tag to which this GroupItem belongs.
// 	 */
// 	public ITag getTag() {
// 		return tag;
// 	}

// 	/**
// 	 * @param tag the tag to set
// 	 */
// 	public void setTag(ITag tag) {
// 		this.tag = tag;
// 	}

// 	/**
// 	 * @return the user to which this GroupItem belongs.
// 	 */
// 	public IUser getUser() {
// 		return user;
// 	}

// 	/**
// 	 * @param user the user to set
// 	 */
// 	public void setUser(IUser user) {
// 		this.user = user;
// 	}

// 	/**
// 	 * @return the sequence
// 	 */
// 	public ISequenceComponent getSequence() {
// 		return sequence;
// 	}

// 	/**
// 	 * @param sequence the sequence to set
// 	 */
// 	public void setSequence(ISequenceComponent sequence) {
// 		this.sequence = sequence;
// 	}

	
	
// 	/**
// 	 * @return the hId
// 	 */
// 	public long getHistoryId() {
// 		return historyId;
// 	}

// 	/**
// 	 * @param id the hId to set
// 	 */
// 	public void setHistoryId(long id) {
// 		historyId = id;
//}

public String toString() {
    return "SCHEDULE_REQUEST_DONE: "+
	"("+(getSuccessful() ? "SUCCESS":"FAILURE ["+getErrorNum()+"] "+getErrorString())+")"+
	", Group="+group;
}


}