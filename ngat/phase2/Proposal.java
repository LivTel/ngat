package ngat.phase2;

import ngat.phase2.nonpersist.*;
import ngat.util.*;

import java.lang.reflect.*;
import java.util.*;
import java.text.*;
import java.io.*;



/** Stores details of an observing proposal which has passed the Phase2 approval process.
 * 
 *
 *
 * Condition blocks are allocated as follows:-
 * <ul>
 *  <li> s - refers to seeing state (0=POOR,   1=GOOD/AV, 2=EXCELLENT).
 *  <li> l - refers to lunar state  (0=BRIGHT, 1=DARK).
 * </ul>
 *
 *<dl>
 * <dt> allocatedFraction[s, l] 
 *   <dd> is the fraction of allocatedTime for conditions (s, l).
 * <dt> timeUsed[s, l]
 *   <dd> is the fraction of total  usedTime under conditions (s, l).
 *   <ul>
 *     <li>usedTime should always be less than allocatedTime and 
 *     <li> tU[s,l] should always be less than aF[s,l]*aT
 *   </ul> 
 * <dt> allocatedFixedFraction 
 *   <dd> is the fraction of total allocatedTime usable for FixedGroups.
 * <dt> usedFixedTime 
 *   <dt>is the total time used so far for FixedGroups. 
 *        This must be less than aT*aFF.
 *</dl>
 *
 */

public class Proposal extends NPDBObject implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -7049581884132556442L;

    public static final int BRIGHT = 0;

    public static final int DARK   = 1;

    public static final int EXCELLENT = 2;

    public static final int AVERAGE   = 1;
    
    public static final int POOR      = 0;

    // Variables.
    
    /**  Time used so far during lunar/seeing conditions. */
    protected float[][] timeUsed;
    
    /**  Fraction of time allocated to  lunar/seeing conditions. */
    protected float[][] allocatedFraction;
    
    /**  Total time allocated to this Proposal ### long. */
    protected float allocatedTime;
    
    /**  Total time used so far by this Proposal ### long. */
    protected float usedTime;
    
    /** Fraction of (total) time available for Fixed observations.*/
    protected double allocatedFixedFraction;

    /** Actual time used for Fixed observations.*/
    protected long usedFixedTime;

    /** Indicates whether a free twilight time allocation is allowed - i.e. any
     * Observations performed in twilight time are not charged for if set.
     */
    protected boolean freeTwilight;

    /**  bitfield to indicate which instruments can be used. */
    protected int instrumentsAllowed;

    /** Stores the allowed list of instruments by ID as Strings.*/
    protected Set xinstrumentsAllowed;

    /**  Date after which this Proposal can be scheduled. */
    protected long activationDate;
    
    /**  Date after which the Proposal cannot be scheduled. */
    protected long expiryDate;
    
    /** TAG allocated scientific priority. (0=MAX, larger values are lower priority).
     * This field will almost certainly change in future to allow for more detailed
     * priority information to be stored for scheduling performance evaluation.
     */
    protected int priority;

    /** PUST abstract text. */
    protected String pustAbstract;
    
    /** Science abstract text. */
    protected String scienceAbstract;
    
    /** Records date/time of last (user) update (ie. unlock). */
    protected long lastUnLocked;
    
    /** Records date/time of last (user) lock on this Proposal. */
    protected long lastLocked;

    /** Records the uid of the last user to edit this Proposal. */
    protected String lastEditor;

    /** Records the registration Id of the last user/tool instance to edit this Proposal. */
    protected long lastRegId;

    /** Indicates whether this Proposal has been exported (for further editing by another user). */
    protected boolean exported;

    /** Indicates, when True that this  Proposal has been locked by ADMIN.*/
    protected boolean admin;

    /** Indicates, when True that this Proposal is for test purposes only 
     * and will never be scheduled.
     */
    protected boolean test;

    protected HashMap groups;
    protected HashMap telescopeConfigs;
    protected HashMap instrumentConfigs;
    protected HashMap pipelineConfigs;
    protected HashMap sources;

    protected List revisions;

    // Constructor.
    
    public Proposal() {this("untitled");}
    
    public Proposal(String name) {
	super(name);
	groups            = new HashMap();
	telescopeConfigs  = new HashMap();
	instrumentConfigs = new HashMap();
	pipelineConfigs   = new HashMap();
	sources           = new HashMap();
	allocatedFraction = new float[2][3];
	timeUsed          = new float[2][3];
	xinstrumentsAllowed = new HashSet();
	revisions = new Vector();
    }
    
    // Accessors.
    
    /** Sets the  fraction of time used so far during lunar/Seeing conditions.
     * @param lunar  The lunar conditions.
     * @param seeing The seeing conditions.
     * @param in     The fraction.
     */
    public void setTimeUsed(int lunar, int seeing, float in) { 	
	timeUsed[lunar][seeing] = in;
    }
    
    /** Returns the  fraction of time used so far during lunar/Seeing conditions.
     * @param lunar  The lunar conditions.
     * @param seeing The seeing conditions. 
     */
    public float getTimeUsed(int lunar, int seeing) { 	
	return timeUsed[lunar][seeing];
    }
    
   
    /** Sets the  fraction of time allocated to BrightSky/ExcellentSeeing conditions.
     * @param lunar  The lunar conditions.
     * @param seeing The seeing conditions.
     * @param in     The fraction.
     */
    public void setAllocatedFraction(int lunar, int seeing, float in) { 	
	allocatedFraction[lunar][seeing] = in;
    }
    
    /** Returns the  fraction of time allocated to BrightSky/ExcellentSeeing conditions.
     * @param lunar  The lunar conditions.
     * @param seeing The seeing conditions. 
     */
    public float getAllocatedFraction(int lunar, int seeing) { 	
	return allocatedFraction[lunar][seeing];
    }
   
    /** Sets the  total time allocated to this Proposal ### long .*/
    public void setAllocatedTime(float in) { this.allocatedTime = in;}
    
    /** Returns the  total time allocated to this Proposal ### long. */
    public float getAllocatedTime() {  return allocatedTime;}
    
    /** Sets the  total time used so far by this Proposal ### long .*/
    public void setUsedTime(float in) { this.usedTime = in;}

    /** Returns the  total time used so far by this Proposal ### long. */
    public float getUsedTime() {  return usedTime;}

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



    /** Sets the  bitfield to indicate which instruments can be used .*/
    public void setInstrumentsAllowed(int in) { this.instrumentsAllowed = in;}
    
    /** Returns the  bitfield to indicate which instruments can be used. */
    public int getInstrumentsAllowed() {  return instrumentsAllowed;}
    
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

    /** Sets the  date after which this Proposal can be scheduled .*/
    public void setActivationDate(long in) { this.activationDate = in;}

    /** Returns the  date after which this Proposal can be scheduled. */
    public long getActivationDate() {  return activationDate;}
    
    /** Sets the  date after which the Proposal cannot be scheduled .*/
    public void setExpiryDate(long in) { this.expiryDate = in;}
    
    /** Returns the  date after which the Proposal cannot be scheduled. */
    public long getExpiryDate() {  return expiryDate;}
    
    /** Sets the TAG allocated scientific priority.*/
    public void setPriority(int priority) { this.priority = priority;}

    /** Returns the TAG allocated scientific priority.*/
    public int getPriority() { return  priority;}

    /** Sets the no comment .*/
    public void setPustAbstract(String in) { this.pustAbstract = in;}
    
    /** Returns the no comment. */
    public String getPustAbstract() {  return pustAbstract;}

    /** Sets the no comment .*/
    public void setScienceAbstract(String in) { this.scienceAbstract = in;}
    
    /** Returns the no comment. */
    public String getScienceAbstract() {  return scienceAbstract;}
    
    /** Sets the  records date/time of last (user) update (ie. unlock) .*/
    public void setLastUnLocked(long in) { this.lastUnLocked = in;}
    
    /** Returns the  records date/time of last (user) update (ie. unlock). */
    public long getLastUnLocked() {  return lastUnLocked;}
    
    /** Sets the  records date/time of last (user) lock on this Proposal .*/
    public void setLastLocked(long in) { this.lastLocked = in;}
    
    /** Returns the  records date/time of last (user) lock on this Proposal. */
    public long getLastLocked() {  return lastLocked;}
    
    /** Sets the  records the uid of the last user to edit this Proposal .*/
    public void setLastEditor(String in) { this.lastEditor = in;}

    /** Returns the  records the uid of the last user to edit this Proposal. */
    public String getLastEditor() {  return lastEditor;}

    /** Sets the reg Id of the last user to edit this Proposal. */
    public void setLastRegId(long in) { this.lastRegId = in;}
    
    /** Returns the reg Id of the last user to edit this Proposal. */
    public long getLastRegId() {  return lastRegId;}
    
    /** Sets the flag to indicate that the Proposal is exported.*/
    public void setExported(boolean in) { this.exported = in;}

    /** Returns True if the flag to indicate that the Proposal is exported is set. */
    public boolean isExported() {  return exported;}

    /** Sets the flag to indicate that the Proposal is locked by admin.*/
    public void setAdmin(boolean in) { this.admin = in; }

    /** Returns True if the flag to indicate that the Proposal is locked by admin is set.*/
    public boolean isAdmin() { return admin; }

    /** Sets the flag to indicate that the Proposal is for test purposes only.*/
    public void setTest(boolean in) { this.test = in; }

    /** Returns True if the flag to indicate that the Proposal is for test purposes only is set.*/
    public boolean isTest() { return test; }
    
    // Group Methods.
    public void addGroup(Group group) {
	
	groups.put(group.getName(), group);
	group.setPath(getPath() + "/" + getName());
    }
    
    public void deleteGroup(Group group) {
	
	groups.remove(group.getName());
    }

    public void removeAllGroups() {
	
	groups.clear();
    }
    
    public Iterator listAllGroups() { 
	
	return groups.values().iterator();
    }
    
    public Group findGroup(String name) { 
	
	if (groups.containsKey(name)) return ((Group)groups.get(name));
	return null;
    }

     public HashMap getGroups() {  
	 
	 return groups;
     }

     // TelescopeConfig Methods.
     public void addTelescopeConfig(TelescopeConfig telescopeConfig) {
	 
          telescopeConfigs.put(telescopeConfig.getName(), telescopeConfig);
     }

     public void deleteTelescopeConfig(TelescopeConfig telescopeConfig) {
	 
          telescopeConfigs.remove(telescopeConfig.getName());
     }

     public void removeAllTelescopeConfigs() {
	 
          telescopeConfigs.clear();
     }

     public Iterator listAllTelescopeConfigs() { 
	 
          return telescopeConfigs.values().iterator();
     }

     public TelescopeConfig findTelescopeConfig(String name) { 
	 
          if (telescopeConfigs.containsKey(name)) return ((TelescopeConfig)telescopeConfigs.get(name));
          return null;
     }

     public HashMap getTelescopeConfigs() {  
	 
	 return telescopeConfigs;
     }

     // InstrumentConfig Methods.
     public void addInstrumentConfig(InstrumentConfig instrumentConfig) {
	 
	 instrumentConfigs.put(instrumentConfig.getName(), instrumentConfig);
     }

     public void deleteInstrumentConfig(InstrumentConfig instrumentConfig) {
	 
	 instrumentConfigs.remove(instrumentConfig.getName());
     }

     public void removeAllInstrumentConfigs() {
	 
	 instrumentConfigs.clear();
     }

     public Iterator listAllInstrumentConfigs() {
	 
	 return instrumentConfigs.values().iterator();
     }

     public InstrumentConfig findInstrumentConfig(String name) { 
	 
	 if (instrumentConfigs.containsKey(name)) return ((InstrumentConfig)instrumentConfigs.get(name));
	 return null;
     }

     public HashMap getInstrumentConfigs() { 
	 
	 return instrumentConfigs;
     }

     // PipelineConfig Methods.
     public void addPipelineConfig(PipelineConfig pipelineConfig) {
	 
	 pipelineConfigs.put(pipelineConfig.getName(), pipelineConfig);
     }

     public void deletePipelineConfig(PipelineConfig pipelineConfig) {
	 
	 pipelineConfigs.remove(pipelineConfig.getName());
     }
    
    public void removeAllPipelineConfigs() {
	
	pipelineConfigs.clear();
    }    
    
    public Iterator listAllPipelineConfigs() { 
	
	return pipelineConfigs.values().iterator();
    }
     
    public PipelineConfig findPipelineConfig(String name) {
	
	if (pipelineConfigs.containsKey(name)) return ((PipelineConfig)pipelineConfigs.get(name));
	return null;
    }
     
    public HashMap getPipelineConfigs() {  
	
	return pipelineConfigs;
    }
     
     // Source Methods. 
     public void addSource(Source source) {
	 
	 sources.put(source.getName(), source);
     }

     public void deleteSource(Source source) {
	 
	 sources.remove(source.getName());
     }

     public void removeAllSources() {
	 
	 sources.clear();
     }

     public Iterator listAllSources() {
	 
	 return sources.values().iterator();
     }

     public Source findSource(String name) { 
	 
	 if (sources.containsKey(name)) return ((Source)sources.get(name));
	 return null;
     }

     public HashMap getSources() {  
	 
	 return sources;
     }
     
    
 
    /** Add a revision entry.*/
    public void addRevisionInfo(Revision revision) {
	if (revisions == null)
	    revisions = new Vector();
	revisions.add(revision);
    }
    
    /** Add a revision entry.*/
    public void addRevisionInfo(long time, String editor, long editorRegId, String comment) {
	addRevisionInfo(new Revision(time, editor, editorRegId, comment));
    }
    
    /** Returns the latest revision.*/
    public Revision getLatestRevision() { 
	
	long lt = 0L;
	Revision irev = null;
	Iterator it = revisions.iterator();
	while (it.hasNext()) {
	    Revision rev = (Revision)it.next();
	    if (rev.getTime() > lt) {
		lt = rev.getTime();
		irev = rev;
	    }
	}

	return irev;
    }

    /** Returns the revision history.*/
    public List getRevisions() { return revisions; }

    /** Returns an iterator over the revisions list.*/
    public Iterator listRevisions() { 
	if (revisions == null)
	    revisions = new Vector();
	return revisions.iterator();
    }
    
    /** Clear the revision list.*/
    public void clearRevisions() {
	if (revisions == null)
	    revisions = new Vector();
	revisions.clear();
    }

     // Lock Proposal and subtree.
     public void lock(int key) {	 
	 setLock(key);
	 lockChildren(key);
     } // end (lock).

     // Child Update Method.
     public void updateChildren() {
	 
	 Iterator it  = listAllGroups();
	 while (it.hasNext()) {
	     Group group = (Group)it.next();
	     group.setPath(path+"/"+name);
	     group.updateChildren();
	 }
     } // end (updateChildren).

     // Child Lock-Check Method.
     public boolean canLock() { 
	 
	 if (isLocked()) return false;
	 Iterator it  = listAllGroups();
	 while (it.hasNext()) {
	     Group group = (Group)it.next();
	     if (!group.canLock()) return false;
	 }
	 return true;
     } // end (canLock).

     // Subtree Locking Method.
     public void lockChildren(int key) {	 
	 setLock(key);
	 Iterator it  = listAllGroups();
	 while (it.hasNext()) {
	     Group group = (Group)it.next();
	     group.lockChildren(key);
	 }	
     } // end (lockChildren).
    
     // Subtree Un-Locking Method.
     public void unLockChildren(int key) {	 
	 unLock(key);
	 Iterator it  = listAllGroups();
	 while (it.hasNext()) {
	     Group group = (Group)it.next();
	     group.unLockChildren(key);
	 }
     } // end (unLockChildren).

     // Force Un-Locking Method.
     public void forceUnLock() {	 
	 lock = 0;
	 Iterator it  = listAllGroups();
	 while (it.hasNext()) {
	     Group group = (Group)it.next();
	     group.forceUnLock();
	 }
     } // end (forceUnLock).

    /** Convenience method calls ProposalDescriptor with this Proposal as constructor parameter.
     * Returns a ProposalDescriptor for this Proposal.
     */
    public ProposalDescriptor getDescriptor() { 	    	
	return new ProposalDescriptor(this);
    }

    /**Formatted Text Output as XML.*/
    public void writeXml(PrintStream out, int level) { 
	out.println(tab(level)+"<proposal name = '"+name+"'>");
	
	out.println(tab(level+1)+"<allocatedFraction lunar = 'BRIGHT' seeing = 'EXCELLENT' value = '"+
		    allocatedFraction[BRIGHT][EXCELLENT]+"'/>");
	out.println(tab(level+1)+"<allocatedFraction lunar = 'BRIGHT' seeing = 'AVERAGE' value = '"+
		    allocatedFraction[BRIGHT][AVERAGE]+"'/>");
	out.println(tab(level+1)+"<allocatedFraction lunar = 'BRIGHT' seeing = 'POOR' value = '"+
		    allocatedFraction[BRIGHT][POOR]+"'/>");

	out.println(tab(level+1)+"<allocatedFraction lunar = 'DARK' seeing = 'EXCELLENT' value = '"+
		    allocatedFraction[DARK][EXCELLENT]+"'/>");
	out.println(tab(level+1)+"<allocatedFraction lunar = 'DARK' seeing = 'AVERAGE' value = '"+
		    allocatedFraction[DARK][AVERAGE]+"'/>");
	out.println(tab(level+1)+"<allocatedFraction lunar = 'DARK' seeing = 'POOR' value = '"+
		    allocatedFraction[DARK][POOR]+"'/>");

	out.println(tab(level+1)+"<usedTime lunar = 'BRIGHT' seeing = 'EXCELLENT' value = '"+
		    timeUsed[BRIGHT][EXCELLENT]+"'/>");
	out.println(tab(level+1)+"<usedTime lunar = 'BRIGHT' seeing = 'AVERAGE' value = '"+
		    timeUsed[BRIGHT][AVERAGE]+"'/>");
	out.println(tab(level+1)+"<usedTime lunar = 'BRIGHT' seeing = 'POOR' value = '"+
		    timeUsed[BRIGHT][POOR]+"'/>");

	out.println(tab(level+1)+"<usedTime lunar = 'DARK' seeing = 'EXCELLENT' value = '"+
		    timeUsed[DARK][EXCELLENT]+"'/>");
	out.println(tab(level+1)+"<usedTime lunar = 'DARK' seeing = 'AVERAGE' value = '"+
		    timeUsed[DARK][AVERAGE]+"'/>");
	out.println(tab(level+1)+"<usedTime lunar = 'DARK' seeing = 'POOR' value = '"+
		    timeUsed[DARK][POOR]+"'/>");
       
	out.println(tab(level+1)+"<priority>"+priority+"</priority>");
	out.println(tab(level+1)+"<allocatedTime>"+allocatedTime+"</allocatedTime>");
	out.println(tab(level+1)+"<usedTime>"+usedTime+"</usedTime>");
	out.println(tab(level+1)+"<allocatedFixedFraction>"+allocatedFixedFraction+"</allocatedFixedFraction>");
	out.println(tab(level+1)+"<usedFixedTime>"+usedFixedTime+"</usedFixedTime>");
	out.println(tab(level+1)+"<instrumentsAllowed>"+instrumentsAllowed+"</instrumentsAllowed>");
	out.println(tab(level+1)+"<activationDate>"+sdf.format(new Date(activationDate))+"</activationDate>");
	out.println(tab(level+1)+"<expiryDate>"+sdf.format(new Date(expiryDate))+"</expiryDate>");
	out.println(tab(level+1)+"<pustAbstract>"+pustAbstract+"</pustAbstract>");
	out.println(tab(level+1)+"<scienceAbstract>"+scienceAbstract+"</scienceAbstract>");
	out.println(tab(level+1)+"<lastUnLocked>"+sdf.format(new Date(lastUnLocked))+"</lastUnLocked>");
	out.println(tab(level+1)+"<lastLocked>"+sdf.format(new Date(lastLocked))+"</lastLocked>");
	out.println(tab(level+1)+"<lastEditor>"+lastEditor+"</lastEditor>");
	out.println(tab(level+1)+"<lastRegId>"+lastRegId+"</lastRegId>");
	out.println(tab(level+1)+"<exported>"+exported+"</exported>");
	Iterator it;
	it = listAllGroups();
	while (it.hasNext()) {
	    ((Group)it.next()).writeXml(out,level+1);
	}
	it = listAllTelescopeConfigs();
	while (it.hasNext()) {
	    ((TelescopeConfig)it.next()).writeXml(out,level+1);
	}
	it = listAllInstrumentConfigs();
	while (it.hasNext()) {
	    ((InstrumentConfig)it.next()).writeXml(out,level+1);
	}
	it = listAllPipelineConfigs();
	while (it.hasNext()) {
	    ((PipelineConfig)it.next()).writeXml(out,level+1);
	}
	it = listAllSources();
	while (it.hasNext()) {
	    ((Source)it.next()).writeXml(out,level+1);
	}
	out.println(tab(level)+"</proposal>");
    } // end (write).
     
    /** Writes the descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     */
    public String toHtmlString(int protocolVersion) {
	StringBuffer buffer = new StringBuffer();
	
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	
	buffer.append("<html> ");
	buffer.append("\n   <body> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Proposal ID --> ");
	buffer.append("\n  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	buffer.append("\n     <tr> <td><font size = +2> <b>Proposal</b> </font></td>   ");
	buffer.append("\n          <td bgcolor = white>  <font size = +2>"+name+"</font></td> </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");

	buffer.append("\n   <!-- Scientific priority --> ");
	buffer.append("\n  <p>");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	buffer.append("\n     <tr> <td>Scientific Priority</td>   ");
	buffer.append("\n          <td bgcolor = white>"+priority+"</td></tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");

	
	buffer.append("\n   <!-- Ownership --> ");
	
	Path pp = new Path(path);
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>   ");
	buffer.append("\n     <tr> <td colspan = 6> <b>Ownership</b> </td> </tr> ");
	buffer.append("\n     <tr> ");
	buffer.append("\n       <td> DB </td> <td bgcolor = white>"+pp.getRootByName()+"</td> ");
	buffer.append("\n       <td> TAG</td> <td bgcolor = white>"+pp.getTagByName()+"</td> ");
	buffer.append("\n       <td> PI </td> <td bgcolor = white>"+pp.getUserByName()+" </td>  ");
	buffer.append("\n     </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Time allocations --> ");
	
	double taDX = allocatedFraction[DARK][EXCELLENT] * allocatedTime;
	double tuDX = timeUsed[DARK][EXCELLENT] / taDX;	
	
	double taDA = allocatedFraction[DARK][AVERAGE] * allocatedTime;
	double tuDA = timeUsed[DARK][AVERAGE] / taDA;
	
	double taDP = allocatedFraction[DARK][POOR] * allocatedTime;
	double tuDP = timeUsed[DARK][POOR] / taDP;
	
	double taBX = allocatedFraction[BRIGHT][EXCELLENT] * allocatedTime;
	double tuBX = timeUsed[BRIGHT][EXCELLENT] / taBX;	
	
	double taBA = allocatedFraction[BRIGHT][AVERAGE] * allocatedTime;
	double tuBA = timeUsed[BRIGHT][AVERAGE] / taBA;
	
	double taBP = allocatedFraction[BRIGHT][POOR] * allocatedTime;
	double tuBP = timeUsed[BRIGHT][POOR] / taBP;
	
	buffer.append("\n  <p> ");
	buffer.append("\n  <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n     <tr> <td colspan = 3 > <b>Time allocations</b> </td> </tr> ");
	buffer.append("\n     <tr> ");
	buffer.append("\n       <td>       ");
	buffer.append("\n         <table border = 0 bgcolor = #ADD8E6> ");
	buffer.append("\n 	  <tr><td> Seeing </td> <td> Poor </td> <td> Average </td> <td> Excellent </td> </tr> ");
	buffer.append("\n 	  <tr><td> Dark   </td> <td bgcolor = white>"+nf.format(taDP)+" ("+nf.format(100.0*tuDP)+"%)</td>  ");
	buffer.append("\n 	      <td bgcolor = white>"+nf.format(taDA)+" ("+nf.format(100.0*tuDA)+"%)</td>  ");
	buffer.append("\n 	      <td bgcolor = white>"+nf.format(taDX)+" ("+nf.format(100.0*tuDX)+"%)</td> </tr> ");
	buffer.append("\n 	  <tr><td> Bright </td> <td bgcolor = white>"+nf.format(taBP)+" ("+nf.format(100.0*tuBP)+"%)</td>  ");
	buffer.append("\n 	      <td bgcolor = white>"+nf.format(taBA)+" ("+nf.format(100.0*tuBA)+"%)</td>  ");
	buffer.append("\n 	      <td bgcolor = white>"+nf.format(taBX)+" ("+nf.format(100.0*tuBX)+"%)</td> </tr> ");
	buffer.append("\n 	</table> ");
	buffer.append("\n       </td> ");
	buffer.append("\n  ");
	buffer.append("\n       <td>       ");
	buffer.append("\n         <table border = 0  bgcolor = #ADD8E6> ");
	buffer.append("\n 	  <tr> <td> Allocation </td> <td bgcolor = white>"+nf.format(allocatedTime)+" hrs </td> </tr> ");
	buffer.append("\n 	  <tr> <td> Used       </td> <td bgcolor = white>"+nf.format(usedTime)+" hrs </td> </tr> ");
	buffer.append("\n 	</table> ");
	buffer.append("\n       </td> ");
	buffer.append("\n        ");
	buffer.append("\n       <td>       ");
	buffer.append("\n         <table border = 0  bgcolor = #ADD8E6> ");
	buffer.append("\n 	  <tr> <td> Fixed fraction </td>  ");
	buffer.append("\n 	       <td bgcolor = white>"+nf.format(allocatedFixedFraction*100)+"% </td> </tr> ");
	buffer.append("\n 	  <tr> <td> Used fixed time</td>  ");
	buffer.append("\n 	       <td bgcolor = white>"+nf.format(usedFixedTime)+" hrs </td> </tr> ");
	buffer.append("\n 	</table> ");
	buffer.append("\n       </td> ");
	buffer.append("\n     </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Activation --> ");
	
	long days = (expiryDate - activationDate) / 86400000L;
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6> ");
	buffer.append("\n     <tr> <td colspan = 6> <b>Activation</b> </td> </tr> ");
	buffer.append("\n     <tr> <td> Activation </td> <td bgcolor = white> "+sdf.format(new Date(activationDate))+" </td>  ");
	buffer.append("\n          <td> Expiry     </td> <td bgcolor = white> "+sdf.format(new Date(expiryDate))+" </td>  ");
	buffer.append("\n 	 <td> Period     </td> <td bgcolor = white> "+days+" days   </td> </tr> ");
	buffer.append("\n   </table>   ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Locking -->  ");
	
	String message = "";
	if (lock == 0) 
	    message = "<font color = green> unlocked </font>";
	else
	    message = "<font color = red> locked with key "+lock+"</font>";

	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6> ");
	buffer.append("\n     <tr> <td colspan = 2> <b>Lock status </b> </td> <td colspan = 2>"+message+"</td></tr> ");
	buffer.append("\n     <tr> <td> Locked              </td>   ");
	buffer.append("\n          <td bgcolor = white> "+sdf.format(new Date(lastLocked))+"  </td>   ");
	buffer.append("\n 	 <td> Unlocked            </td>   ");
	buffer.append("\n 	 <td bgcolor = white> "+sdf.format(new Date(lastUnLocked))+"  </td> </tr> ");

	if (lock == 0)
	    buffer.append("\n     <tr> <td> Last editor   </td>   ");
	else
	    buffer.append("\n     <tr> <td> Current editor   </td>   ");
	
	if (admin)
	    buffer.append("\n          <td bgcolor = yellow> <font color = red>"+lastEditor+"</font></td>   ");
	else
	    buffer.append("\n          <td bgcolor = white> "+lastEditor+"      </td>   ");


	buffer.append("\n 	 <td> Using RegID </td>   ");
	buffer.append("\n 	 <td bgcolor = white> "+Long.toHexString(lastRegId).toUpperCase()+" </td> </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Science -->  ");
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6 > ");
	buffer.append("\n     <tr> <td> <b>Science abstract</b> </td> </tr> ");
	buffer.append("\n     <tr> <td bgcolor = white>"+scienceAbstract+"</td></tr> ");
	buffer.append("\n   </table>    ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- PUST --> ");
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6 > ");
	buffer.append("\n     <tr> <td> <b>PUST abstract</b> </td> </tr> ");
	buffer.append("\n     <tr> <td bgcolor = white>"+pustAbstract+"</td></tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");

	buffer.append("\n  ");

	buffer.append("\n<!-- Revision History -->");

	buffer.append("\n  <p> ");
	buffer.append("\n  <table border = 0 bgcolor = #ADD8E6>   ");
	buffer.append("\n    <tr> <td colspan = 4> <b> Revision History </b> </td> </tr> ");
	buffer.append("\n    <tr> ");
	buffer.append("\n     <td> <b> Revision Date </b> </td> <td> <b> Editor </b> </td> <td> <b> RegID </b> </td><td> <b> Details </b> </td>");
	buffer.append("\n    </tr>");

	System.err.println("Revisions: "+revisions);
	if (revisions != null) {
	    Iterator revs = revisions.iterator();
	    while (revs.hasNext()) {
		Revision revision = (Revision)revs.next();
		buffer.append("\n    <tr>  ");
		buffer.append("\n      <td bgcolor = white>"+sdf.format(new Date(revision.getTime()))+"</td> ");
		buffer.append("\n      <td bgcolor = white>"+revision.getEditor()+"</td> ");	   
		buffer.append("\n      <td bgcolor = white>"+revision.getEditorRegId()+"</td>");
		buffer.append("\n      <td bgcolor = white>"+revision.getComment()+"</td>");
		buffer.append("\n    </tr> ");	    
	    }
	}

	buffer.append("\n     </table> ");	
	
	buffer.append("\n </body> ");
	buffer.append("\n </html> ");
	return buffer.toString();
    }  
    
    //      /** Scheduling enhancement Algorithm.*/
//      public ScheduleDescriptor applyScheduling(ScheduleDescriptor sched) {	
//  	// Insert code here to apply this.object's schedule coefft.
//  	// will make use of attribute: [ schedCoeff ] and possibly
//  	// various ServerContext and TelescopeEnvironment variables.
//  	Group group  = sched.getGroup();
//  	double score = sched.getScore();
//  	//Logger logger = LogManager.getLogger("SCHEDULE");	
	
//  	double timeFraction =  timeUsed [group.getMinimumLunar()] [group.getMinimumSeeing()] /
//  	    (allocatedFraction [group.getMinimumLunar()] [group.getMinimumSeeing()] 
//  	     * allocatedTime);
//  	// The scale factor (5.0) determines how fast the fn drops off.
//  	double score1 = 1.0 / ( 1.0 + 5.0 * timeFraction * timeFraction);
//  	//logger.log(1, "Proposal", name, "appSched", "Sched group: "+group.getName()+
//  	//  "** Proposal-Group. "+group.getFullPath()+
//  	//  "\nMinLunar: "+group.getMinimumLunar()+" MinSeeing: "+group.getMinimumSeeing()+
//  	//  "\nTimeUsed: "+timeUsed [group.getMinimumLunar()] [group.getMinimumSeeing()]+
//  	//  " AllocFrac: "+allocatedFraction [group.getMinimumLunar()] [group.getMinimumSeeing()]+
//  	//   "\nTime allocation fraction: "+timeFraction+"..Scores: "+score1);
//  	score += score1;
//  	score *= schedCoeff;
//  	sched.setScore(score);

//  	return sched;	
//      } // end (applyScheduling).
    

   


} // end class def [Proposal].
