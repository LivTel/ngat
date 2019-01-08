package ngat.phase2;

import java.io.*;
import java.util.*;
/** Contains selected fields of a Proposal, suitable for informing remote users of the
 * details of a Proposal without revealing any substructure. Used By USR_ListProposals
 * to store the basic details of a Proposal. 
 * $Id$.*/
public class ProposalDescriptor implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 84560745016572214L;
    
    public static final int BRIGHT = 0;
    
    public static final int DARK   = 1;

    public static final int EXCELLENT = 0;
    
    public static final int AVERAGE   = 1;
    
    public static final int POOR      = 2;


    protected String path;

    protected String name;
    
    protected int lock;
    
    protected String lastEditor;
    
    protected long lastLocked;

    protected long lastUnLocked;

    protected long lastRegId;
    
    protected float allocatedTime;

    protected float usedTime;

    /** fraction of (total) time available for Fixed observations.*/
    protected double allocatedFixedFraction;
    
    /** actual time used for Fixed observations.*/
    protected long usedFixedTime;

    /** Indicates whether a free twilight time allocation is allowed - i.e. any
     * Observations performed in twilight time are not charged for if set.
     */
    protected boolean freeTwilight;

    protected int instrumentsAllowed;

    protected Set xinstrumentsAllowed;

    protected long activationDate;

    protected long expiryDate;

    /** TAG allocated scientific priority. (0=MAX, larger values are lower priority).
     * This field will almost certainly change in future to allow for more detailed
     * priority information to be stored for scheduling performance evaluation.
     */
    protected int priority;

    protected String pustAbstract;
    
    protected String scienceAbstract;

    protected boolean exported;
    
    /** Indicates, when True that this  Proposal has been locked by ADMIN.*/
    protected boolean admin;

    /** Indicates, when True that this Proposal is for test purposes only 
     * and will never be scheduled.
     */
    protected boolean test;

    /**  fraction of time used so far during lunar/seeing conditions. */
    protected float[][] timeUsed;
    
    /**  fraction of time allocated to  lunar/seeing conditions. */
    protected float[][] allocatedFraction;

    protected List revisions;

    public ProposalDescriptor() {
	allocatedFraction = new float[2][3];
	timeUsed          = new float[2][3];
	xinstrumentsAllowed = new HashSet();
	revisions = new Vector(); 
	//	System.err.println("PD::Creating revisions: "+revisions);
    }

    /** Make an empty ProposalDescriptor. */
    public ProposalDescriptor(String name) {
	this();
	this.name = name;
    }
    
    /** Make a descriptor for the supplied Proposal. */
    public ProposalDescriptor(Proposal proposal) {
	this();
	this.path = proposal.getPath();
	this.name = proposal.getName();

	this.admin        = proposal.isAdmin();
	this.lock         = proposal.getLock();
	this.lastEditor   = proposal.getLastEditor();
	this.lastLocked   = proposal.getLastLocked();
	this.lastUnLocked = proposal.getLastUnLocked();

	this.allocatedTime          = proposal.getAllocatedTime();
	this.usedTime               = proposal.getUsedTime();
	this.allocatedFixedFraction = proposal.getAllocatedFixedFraction();
	this.usedFixedTime          = proposal.getUsedFixedTime();

	this.freeTwilight           = proposal.getFreeTwilight();

	this.instrumentsAllowed = proposal.getInstrumentsAllowed();

	// Add the list of instruments.
	//this.xinstrumentsAllowed = new HashSet();
	Iterator it = proposal.listXInstrumentsAllowed();
	while (it.hasNext()) {
	    xinstrumentsAllowed.add((String)it.next());
	}
	
	//revisions = new Vector();
	//System.err.println("PD::Created Revisions is: "+revisions);
	it = proposal.listRevisions();	

	while (it.hasNext()) {
	    Revision rev  = (Revision)it.next();
	    addRevisionInfo(rev);
	    //System.err.println("PD::Adding a revision: "+rev);
	}
	
	this.pustAbstract    = proposal.getPustAbstract();
	this.scienceAbstract = proposal.getScienceAbstract();
	this.activationDate  = proposal.getActivationDate();
	this.expiryDate      = proposal.getExpiryDate();
	this.exported        = proposal.isExported();
	this.lastRegId       = proposal.getLastRegId();

	//allocatedFraction = new float[2][3];
	//timeUsed          = new float[2][3];

	allocatedFraction[BRIGHT][EXCELLENT] = proposal.getAllocatedFraction(BRIGHT, EXCELLENT);
	allocatedFraction[BRIGHT][AVERAGE]   = proposal.getAllocatedFraction(BRIGHT, AVERAGE);
	allocatedFraction[BRIGHT][POOR]      = proposal.getAllocatedFraction(BRIGHT, POOR);
	allocatedFraction[DARK][EXCELLENT]   = proposal.getAllocatedFraction(DARK,   EXCELLENT);
	allocatedFraction[DARK][AVERAGE]     = proposal.getAllocatedFraction(DARK,   AVERAGE);
	allocatedFraction[DARK][POOR]        = proposal.getAllocatedFraction(DARK,   POOR);
    }

    /** Return a Proposal Stub from this descriptor. */
    public Proposal createProposal() {
	Proposal proposal = new Proposal(name);
	proposal.setPath(path);
	proposal.setLock(lock);
	proposal.setLastEditor(lastEditor);
	proposal.setLastLocked(lastLocked);
	proposal.setLastUnLocked(lastUnLocked);
	proposal.setAdmin(admin);

	proposal.setAllocatedTime(allocatedTime);
	proposal.setUsedTime(usedTime);
	proposal.setAllocatedFixedFraction(allocatedFixedFraction);
	proposal.setUsedFixedTime(usedFixedTime);
	proposal.setFreeTwilight(freeTwilight);

	proposal.setInstrumentsAllowed(instrumentsAllowed);

	Iterator it = listXInstrumentsAllowed();
	while (it.hasNext()) {
	    proposal.setXInstrumentsAllowed((String)it.next(), true);
	}

	proposal.setPustAbstract(pustAbstract);
	proposal.setScienceAbstract(scienceAbstract);
	proposal.setActivationDate(activationDate);
	proposal.setExpiryDate(expiryDate);

	proposal.setExported(exported);
	proposal.setLastRegId(lastRegId);

	proposal.setAllocatedFraction(BRIGHT, EXCELLENT, allocatedFraction[BRIGHT][EXCELLENT]);
	proposal.setAllocatedFraction(BRIGHT, AVERAGE,   allocatedFraction[BRIGHT][AVERAGE]);
	proposal.setAllocatedFraction(BRIGHT, POOR,      allocatedFraction[BRIGHT][POOR]);
	proposal.setAllocatedFraction(DARK,   EXCELLENT, allocatedFraction[DARK][EXCELLENT]);
	proposal.setAllocatedFraction(DARK,   AVERAGE,   allocatedFraction[DARK][AVERAGE]);
	proposal.setAllocatedFraction(DARK,   POOR,      allocatedFraction[DARK][POOR]);

	//System.err.println("PD:createProp::The Revisions is:" + revisions);
	it = revisions.iterator();
	while (it.hasNext()) {
	    proposal.addRevisionInfo((Revision)it.next());
	}

	return proposal;
    }

    public String  getPath() { return path;} 
    public void    setPath(String path) { this.path = path;}
    
    public String  getName() { return name;}
    public void    setName(String name) { this.name = name;}
    
    public String  getLastEditor() { return lastEditor;}
    public void    setLastEditor(String lastEditor) { this.lastEditor = lastEditor;}
    
    public String  getPustAbstract() { return pustAbstract;}
    public void    setPustAbstract(String pustAbstract) { this.pustAbstract = pustAbstract;}
    
    public String  getScienceAbstract() { return scienceAbstract;}
    public void    setScienceAbstract(String scienceAbstract) { this.scienceAbstract = scienceAbstract;}
    
    public long    getLastLocked() { return lastLocked; }
    public void    setLastLocked(long lastLocked) { this.lastLocked = lastLocked;}
    
    public long    getLastUnLocked() { return lastUnLocked;} 
    public void    setLastUnLocked(long lastUnLocked) { this.lastUnLocked = lastUnLocked;}
    
    public long    getLastRegId() { return lastRegId;}
    public void    setLastRegId(long lastRegID) { this.lastRegId = lastRegId;}
    
    public int     getLock() { return lock; }
    public void    setLock(int lock) { this.lock = lock;
    System.out.println("PDesc.set()ting lock to: "+lock+" my lock: "+this.lock);}
    
    public float   getAllocatedTime() { return allocatedTime;}
    public void    setAllocatedTime(float allocatedTime) { this.allocatedTime = allocatedTime;}
    
    public float   getUsedTime() { return usedTime;}
    public void    setUsedTime(float usedTime) { this.usedTime = usedTime;}
    
    public int     getInstrumentsAllowed() { return instrumentsAllowed;}
    public void    setInstrumentsAllowed(int instrumentsAllowed) { this.instrumentsAllowed = instrumentsAllowed;}

     /** Set whether an instrument can be used. There is generally no reason
      * to call this method with the false parameter - just dont call it with
      * the true parameter. Potentially it could be used to revoke permission
      * on an instrument. This method is generally only called when the proposal
      * stub is setup by admin.
      * @param instId The ID of the instrument (telescope-specific).
      * @param allow True if the instrument is allowed (in this proposal.
      */
    public void setXInstrumentsAllowed(String instId, boolean allow) {
	if (allow)
	    xinstrumentsAllowed.add(instId);
	else 
	    xinstrumentsAllowed.remove(instId);
    }
    
    /** Returns true to indicate that an instrument can be used in this proposal. 
     * If the instrument is not recognized returns false.
     * @param instId The ID of the instrument (telescope-specific).
     */
    public boolean getXInstrumentsAllowed(String instId) {  
	return  xinstrumentsAllowed.contains(instId);
    }

    /** Returns an Iterator over the list of allowed instruments by name.*/
    public Iterator listXInstrumentsAllowed() {
	return xinstrumentsAllowed.iterator();
    }

    public long    getActivationDate() { return activationDate;}
    public void    setActivationDate(long activationDate) { this.activationDate = activationDate;}
    
    public long    getExpiryDate() {  return expiryDate;}
    public void    setExpiryDate(long expiryDate) { this.expiryDate = expiryDate; }

    public boolean isLocked() { return (lock != 0);}
    public boolean isExported() { return exported;}
    public void    setExported(boolean exported) { this.exported = exported;}
    
    /** Sets the flag to indicate that the Proposal is locked by admin.*/
    public void setAdmin(boolean in) { this.admin = in; }

    /** Returns True if the flag to indicate that the Proposal is locked by admin is set.*/
    public boolean isAdmin() { return admin; }

    /** Sets the flag to indicate that the Proposal is for test purposes only.*/
    public void setTest(boolean in) { this.test = in; }

    /** Returns True if the flag to indicate that the Proposal is for test purposes only is set.*/
    public boolean isTest() { return test; }
    
    /** Sets the  fraction of time allocated to BrightSky/ExcellentSeeing conditions .*/
    public void setAllocatedFraction(int lunar, int seeing, float in) { allocatedFraction[lunar][seeing] = in;}
    
    /** Returns the  fraction of time allocated to BrightSky/ExcellentSeeing conditions. */
    public float getAllocatedFraction(int lunar, int seeing) { return allocatedFraction[lunar][seeing];}
   
    /** Sets the fraction of (total) time available for Fixed observations.*/
    public void setAllocatedFixedFraction(double allocatedFixedFraction) {
	this.allocatedFixedFraction = allocatedFixedFraction;
    }
    
    /** Returns the fraction of (total) time available for Fixed observations.*/
    public double getAllocatedFixedFraction() {
	return allocatedFixedFraction;
    }
    
    /** Sets the actual time used for Fixed observations.*/
    public void setUsedFixedTime(long usedFixedTime) {
	this.usedFixedTime = usedFixedTime;
    }
    
    /** Returns the actual time used for Fixed observations.*/
    public long getUsedFixedTime() {
	return usedFixedTime;
    }

    /** Sets indicator of whether twilight time is charged for.*/
    public void setFreeTwilight(boolean freeTwilight) {
	this.freeTwilight = freeTwilight;
    }

    /** Returns true if the proposal has free twilight time.*/
    public boolean getFreeTwilight() {  return freeTwilight;}

    /** Add a revision entry.*/
    public void addRevisionInfo(Revision revision) {

	//System.err.println("PD:AddREvInfo:: adding "+revision);

	Revision rev = new Revision(revision.getTime(), 
				    revision.getEditor(), 
				    revision.getEditorRegId(), 
				    revision.getComment());
	
	revisions.add(rev);
    }

    public String toString() {
	return "ProposalDescriptor: "+name+", Revisions="+revisions;
    }

} // Class Def. [ProposalDescriptor].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/29 12:48:37  snf
/** Initial revision
/**. */
