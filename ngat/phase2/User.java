package ngat.phase2;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Represents a user (observer) in the Phase II database.
 * <br>
 * $Id: User.java,v 1.2 2000-11-23 18:44:56 snf Exp $
 *
 */
public class User extends DBObject implements Serializable {
    
    // Variables.
    
    /**  The user's email address for contact. */
    protected String email;
       
    /**  Organization, department, intitute to which user belongs. */
    protected String organization;
    
    /**  Address field line 1. */
    protected String address1;
    
    /**  Address field line 2. */
    protected String address2;
    
    /**  Address field line 3. */
    protected String address3;
    
    /**  User's Country. */
    protected String country;
    
    /**  The user's surname. */
    protected String sname;
    
    /**  The user's first name. */
    protected String fname;
    
    /** List of Proposals for which this user is Principle Investigator (PI).*/
    protected OSHashMap proposals;
    
    /** List of Proposals for which this user is Co-Investigator (CoI).*/
    protected OSHashMap coIProposals;
    
    // Constructor.
    
    /** Create a user with name 'untitled'.*/
    public User() {this("untitled");}
    
    /** Creat a user with the specified name.*/
    public User(String name) {
	super(name);
	proposals = new OSHashMap();
	coIProposals = new OSHashMap();
    }
    
    // Accessors.

    /** Sets the user's email address for contact .*/
    public void setEmail(String in) { this.email = in;}
    
    /** Returns the user's email address for contact. */
    public String getEmail() { return email;}

    /** Sets the organization, department, intitute to which user belongs .*/
    public void setOrganization(String in) { this.organization = in;}
    
    /** Returns the organization, department, intitute to which user belongs. */
    public String getOrganization() { return organization;}
    
    /** Sets the address field line 1 .*/
    public void setAddress1(String in) { this.address1 = in;}

    /** Returns the address field line 1. */
    public String getAddress1() { return address1;}
    
    /** Sets the address field line 2 .*/
    public void setAddress2(String in) { this.address2 = in;} 
    
    /** Returns the address field line 2. */
    public String getAddress2() { return address2;}
    
    /** Sets the address field line 3 .*/
    public void setAddress3(String in) { this.address3 = in;}
    
    /** Returns the address field line 3. */
    public String getAddress3() { return address3;}
    
    /** Sets the user's Country .*/
    public void setCountry(String in) { this.country = in;}
    
    /** Returns the user's Country. */
    public String getCountry() { return country;}
        
    /** Sets the user's surname .*/
    public void setSname(String in) { this.sname = in;}
    
    /** Returns the user's surname. */
    public String getSname() { return sname;}
    
    /** Sets the user's first name .*/
    public void setFname(String in) { this.fname = in;}
    
    /** Returns the user's first name. */
    public String getFname() { return fname;}
    
    // Descendant Mutators.

    // Proposal Methods.
    /** Add a Proposal for which this user is PI.*/
    public void addProposal(Proposal proposal) {
	proposals.put(proposal.getName(), proposal);
	proposal.setPath(getPath() + "/" + getName());
    }
    
    /** Remove the specified proposal from the user's PI list.*/
    public void deleteProposal(Proposal proposal) {
	proposals.remove(proposal.getName());
    }
    
    /** Remove ALL proposals from the user's PI list.*/
    public void removeAllProposals() {
	proposals.clear();
    }
    
    /** Return an Iterator over the user's PI list.*/
    public Iterator listAllProposals() {
	return proposals.values().iterator();
    }
    
    /** Locate a named proposal in the user's PI list or null if not found.*/
    public Proposal findProposal(String name) {
	if (proposals.containsKey(name)) return ((Proposal)proposals.get(name));
	return null;
    }
    
    /** Return a reference to the list of proposals for which this user is PI.*/
    public OSHashMap getProposals() { return proposals;}
    
    // CoIProposal Methods.
    /** Add a Proposal for which this user is CoI.*/
    public void addCoIProposal(Proposal coIProposal) {
	coIProposals.put(coIProposal.getName(), coIProposal);
    }
    
    /** Remove the specified proposal from the user's CoI list.*/
    public void deleteCoIProposal(Proposal coIProposal) {
	coIProposals.remove(coIProposal.getName());
    }
    
    /** Remove ALL proposals from the user's CoI list.*/
    public void removeAllCoIProposals() {
	coIProposals.clear();
    }
    
    /** Return an Iterator over the user's CoI list.*/
    public Iterator listAllCoIProposals() {
	return coIProposals.values().iterator();
    }
    
    /** Locate a named proposal in the user's CoI list or null if not found.*/
    public Proposal findCoIProposal(String name) {
	if (coIProposals.containsKey(name)) return ((Proposal)coIProposals.get(name));
	return null;
    }
    
    /** Return a reference to the list of proposals for which this user is CoI.*/
    public OSHashMap getCoIProposals() { return coIProposals;}
    
    // NP -> P Translator.
    /** Create a User from an NPUser.*/
    public User(NPUser npUser) {
	super(npUser);
	Iterator it;
	email = npUser.getEmail();
	organization = npUser.getOrganization();
	address1 = npUser.getAddress1();
	address2 = npUser.getAddress2();
	address3 = npUser.getAddress3();
	country = npUser.getCountry();
	sname = npUser.getSname();
	fname = npUser.getFname();
	
	// Recursively call Daughter Translators.
	
	proposals = new OSHashMap();
	it = npUser.listAllNPProposals();
	while (it.hasNext()) {
	    try {
		NPProposal npProposal = (NPProposal)it.next();
		Class npClazz = npProposal.getClass();
		String npName = npClazz.getName();
		int k = npName.indexOf("nonpersist.NP");
		String pName = npName.substring(0,k).concat(npName.substring(k+13));
		Class pClazz = Class.forName(pName);
		Constructor pCon = pClazz.getConstructor(new Class[]{npClazz});
		Proposal proposal = (Proposal)pCon.newInstance(new Object[]{npProposal});
		addProposal(proposal);
	    } catch (ClassNotFoundException re1){
		System.out.println("Translation Error: "+re1);
	    } catch (NoSuchMethodException re2) {
		System.out.println("Translation Error: "+re2);
	    } catch (InvocationTargetException re3) {
		System.out.println("Translation Error: "+re3);
	    } catch (IllegalAccessException re4) {
		System.out.println("Translation Error: "+re4);
	    } catch (InstantiationException re5) {
		System.out.println("Translation Error: "+re5);
	    }
	}
	coIProposals = new OSHashMap();
	it = npUser.listAllNPCoIProposals();
	while (it.hasNext()) {
	    try {
		NPProposal npCoIProposal = (NPProposal)it.next();
		Class npClazz = npCoIProposal.getClass();
		String npName = npClazz.getName();
		int k = npName.indexOf("nonpersist.NP");
		String pName = npName.substring(0,k).concat(npName.substring(k+13));
		Class pClazz = Class.forName(pName);
		Constructor pCon = pClazz.getConstructor(new Class[]{npClazz});
		Proposal coIProposal = (Proposal)pCon.newInstance(new Object[]{npCoIProposal});
		addCoIProposal(coIProposal);
	    } catch (ClassNotFoundException re1){
		System.out.println("Translation Error: "+re1);
	    } catch (NoSuchMethodException re2) {
		System.out.println("Translation Error: "+re2);
	    } catch (InvocationTargetException re3) {
		System.out.println("Translation Error: "+re3);
	    } catch (IllegalAccessException re4) {
		System.out.println("Translation Error: "+re4);
	    } catch (InstantiationException re5) {
		System.out.println("Translation Error: "+re5);
	    }
	}
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    /** When translating to NPUser, stuff all the internal fields and descendants.*/
    public void stuff(NPUser npUser) {
	super.stuff(npUser);
	Iterator it;
	npUser.setEmail(getEmail());
	npUser.setOrganization(getOrganization());
	npUser.setAddress1(getAddress1());
	npUser.setAddress2(getAddress2());
	npUser.setAddress3(getAddress3());
	npUser.setCountry(getCountry());
	npUser.setSname(getSname());
	npUser.setFname(getFname());
	it = listAllProposals();
	while (it.hasNext()) {
	    npUser.addNPProposal((NPProposal)(((Proposal)it.next()).makeNP()));
	}
	it = listAllCoIProposals();
	while (it.hasNext()) {
	    npUser.addNPCoIProposal((NPProposal)(((Proposal)it.next()).makeNP()));
	}
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    /** Make this User into an NPUser.*/
    public NPDBObject makeNP() {
	NPUser npUser = new NPUser();
	stuff(npUser);
	return npUser;
    } // end (makeNp).

    // Self Lock Method.
    /** Lock this User and recursively all descendants with the supplied key,*/
    public void lock(int key) {
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).
    
    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this User.*/
    public void updateChildren() {
	Iterator it  = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    proposal.setPath(path+"/"+name);
	    proposal.updateChildren();
	}
    } // end (updateChildren).
    
    // Child Lock-Check Method.
    /** Return true if this User and ALL descendants are unlocked.*/
    public boolean canLock() {
	if (isLocked()) return false;
	Iterator it  = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    if (!proposal.canLock()) return false;
	}
	return true;
    } // end (canLock).
    
    // Child Locking Method.
    /** Lock all descendants using the supplied key.*/
    public void lockChildren(int key) {
	Iterator it  = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    proposal.lockChildren(key);
	}
	setLock(key);
    } // end (lockChildren).
    
    // Child Un-Locking Method.
    /** Unlock this (User) and all descendants using the supplied key if possible.
     * Returns silently if it  fails. Use canLock() to see if the unlock succeeded.*/
    public void unLockChildren(int key) {
	Iterator it  = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    proposal.unLockChildren(key);
	}
	unLock(key);
    } // end (unLockChildren).
    
    // Force Un-Locking Method.
    /** Forcibly unlock this (User) and all descendants whatever their current key.*/
    public void forceUnLock() {
	lock = 0;
	Iterator it  = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    proposal.forceUnLock();
	}
    } // end (forceUnLock).
    
    /** Returns a descriptor for this User's internal fields.*/
    public UserDescriptor getDescriptor() {
	UserDescriptor descriptor = new UserDescriptor(name);
	descriptor.setPath(path);
	descriptor.setLock(lock);
	descriptor.setEmail(email);
	descriptor.setAddress1(address1);
	descriptor.setAddress2(address2);
	descriptor.setAddress3(address3);
	descriptor.setCountry(country);
	descriptor.setSname(sname);
	descriptor.setFname(fname);
	return descriptor;
    }
    
    // FixedGroup Method.
    /** Return the next fixed group available ## DEPRECATED.*/
    public FixedGroup getNextFixedGroup() {
	FixedGroup earliest = new FixedGroup("NULL");
	// Set earliest Fixed to about Y29,688 AD !
	earliest.setFixedTime(999999999999999L);
	Iterator it = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    if (!proposal.isLocked() && !proposal.deleted()) {
		FixedGroup fg = proposal.getNextFixedGroup();
		if (fg != null) {
		    if (fg.getFixedTime() < earliest.getFixedTime()) {
			earliest = fg;
		    }
		}
	    }
	}
	return earliest;
    } // end (getNextFixedGroup).
    
    // Scheduling Method.
    /** Invoke the scheduling function on this Tag. Calls recursively 
     * through descendants until all objects which contribute to scheduling 
     * descisions have been visited.*/
    public ScheduleDescriptor schedule() {
	ScheduleDescriptor best = new ScheduleDescriptor(null,0.0f,0);
	Iterator it = listAllProposals();
	while (it.hasNext()) {
	    Proposal proposal = (Proposal)it.next();
	    if (!proposal.isLocked() && !proposal.deleted()) {
		ScheduleDescriptor sd = proposal.schedule();
		if (sd.getGroup() != null) {
		    if (sd.getScore() > best.getScore()) {
			best.setGroup(sd.getGroup());
			best.setScore(sd.getScore());
			best.setExecTime(sd.getExecTime());
		    }
		}
	    }
	}
	return applyScheduling(best);
    } // end (schedule).
    
    // Scheduling Algorithm.
    /** Include any User specific schedule weighting with this method.
     * Currently this does nothing.
     * @param best The 'best' schedule so far.
     * @return The modified schedule.*/
    public ScheduleDescriptor applyScheduling(ScheduleDescriptor best) {		
	// Insert code here to apply this.object's schedule coefft.
	// will make use of attribute: [ schedCoeff ] and possibly
	// various ServerContext and TelescopeEnvironment variables.	
	best.setScore(best.getScore()*schedCoeff);
	return best;	
    } // end (applyScheduling).
      
} // end class def [User].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 14:21:49  snf
/** Initial revision
/** */
