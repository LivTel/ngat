package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Represents a user (observer) in the Phase II database.
 * <br>
 * $Id$
 *
 */
public class User extends DBObject implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -5949186439364520929L;
    
	// Variables.
    
    /**  The user's email address for contact. */
    protected String email;

    /** Telephone number.*/
    protected String telephone;

    /** Fax number.*/
    protected String fax;

    /** Postal/ZIP code.*/
    protected String postalCode;
       
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
    
    /**  The user's first name(s). */
    protected String fname;

    /** Stores the user's password.*/
    protected String password;
    
    /** List of Proposals for which this user is Principle Investigator (PI).*/
    protected SerializableStorableSortedDictionary proposals;
    
    /** List of Proposals for which this user is Co-Investigator (CoI).*/
    protected SerializableStorableSortedDictionary coIProposals;
    
    // Constructor.
    
    /** Create a user with name 'untitled'.*/
    public User() {this("untitled");}
    
    /** Creat a user with the specified name.*/
    public User(String name) {
	super(name);
	proposals    = new SerializableStorableSortedDictionary();
	coIProposals = new SerializableStorableSortedDictionary();
    }
    
    // Accessors.

    /** Sets the user's email address for contact .*/
    public void setEmail(String in) {this.storableUpdate(); this.email = in;}
    
    /** Returns the user's email address for contact. */
    public String getEmail() { this.storableFetch();return email;}

    /** Sets the Telephone number.*/
    public void setTelephone(String in) { this.storableUpdate();  this.telephone = in;};

    /** Returns the Telephone number.*/
    public String getTelephone() { this.storableFetch();return telephone;}
    
    /** Sets the Fax number.*/
    public void setFax(String in) { this.storableUpdate();  this.fax = in;};
    
    /** Returns the Fax number.*/
    public String getFax() { this.storableFetch();return fax;}
    
    /** Sets the Postal/ZIP code.*/
    public void setPostalCode(String in){ this.storableUpdate();  this.postalCode = in;};
    
    /** Returns the Postal/ZIP code.*/
    public String getPostalCode() { this.storableFetch();return postalCode;}

    /** Sets the organization, department, intitute to which user belongs .*/
    public void setOrganization(String in) {this.storableUpdate();  this.organization = in;}
    
    /** Returns the organization, department, intitute to which user belongs. */
    public String getOrganization() { this.storableFetch();return organization;}
    
    /** Sets the address field line 1 .*/
    public void setAddress1(String in) { this.storableUpdate(); this.address1 = in;}

    /** Returns the address field line 1. */
    public String getAddress1() { this.storableFetch();return address1;}
    
    /** Sets the address field line 2 .*/
    public void setAddress2(String in) { this.storableUpdate(); this.address2 = in;} 
    
    /** Returns the address field line 2. */
    public String getAddress2() { this.storableFetch();return address2;}
    
    /** Sets the address field line 3 .*/
    public void setAddress3(String in) {this.storableUpdate();  this.address3 = in;}
    
    /** Returns the address field line 3. */
    public String getAddress3() { this.storableFetch();return address3;}
    
    /** Sets the user's Country .*/
    public void setCountry(String in) {this.storableUpdate();  this.country = in;}
    
    /** Returns the user's Country. */
    public String getCountry() { this.storableFetch();return country;}
        
    /** Sets the user's surname .*/
    public void setSname(String in) { this.storableUpdate(); this.sname = in;}
    
    /** Returns the user's surname. */
    public String getSname() { this.storableFetch();return sname;}
    
    /** Sets the user's first name .*/
    public void setFname(String in) { this.storableUpdate(); this.fname = in;}
    
    /** Returns the user's first name. */
    public String getFname() { this.storableFetch();return fname;}

    /** Sets the user's password.*/
    public void setPassword(String in) { this.storableUpdate(); this.password = in;}

    /** Returns the user's pasword.*/
    public String getPassword() {this.storableFetch();return password; }


    // Descendant Mutators.

    // Proposal Methods.
    /** Add a Proposal for which this user is PI.*/
    public void addProposal(String proposalName) {
	this.storableUpdate(); 
	proposals.put(proposalName, proposalName);	
    }
    
    /** Remove the specified proposal from the user's PI list.*/
    public void deleteProposal(String proposalName) {
	this.storableUpdate(); 
	proposals.remove(proposalName);
    }
    
    /** Remove ALL proposals from the user's PI list.*/
    public void removeAllProposals() {
	this.storableUpdate(); 
	proposals.clear();
    }
    
    /** Return an Iterator over the user's PI list.*/
    public Iterator listAllProposals() {
	this.storableFetch();
	return proposals.iterator();
    }
    
    /** Locate a named proposal in the user's PI list or null if not found.*/
    public String findProposal(String name) {
	this.storableFetch();
	if (proposals.containsKey(name)) {
	    return name;
	}
	return null;
    }
    
    /** Return a reference to the list of proposals for which this user is PI.*/
    public SerializableStorableSortedDictionary getProposals() {
	this.storableFetch(); 
	return proposals;
    }
    
    // CoIProposal Methods.
    /** Add a Proposal for which this user is CoI.
     * Use the full-path here not just name.
     */
    public void addCoIProposal(String coIProposalName) {
	this.storableUpdate(); 
	coIProposals.put(coIProposalName, coIProposalName);
	System.err.println("USER::AddedCoIProposal "+coIProposalName);
    }
    
    /** Remove the specified proposal from the user's CoI list.*/
    public void deleteCoIProposal(String coIProposalName) {
	this.storableUpdate(); 
	coIProposals.remove(coIProposalName);
    }
    
    /** Remove ALL proposals from the user's CoI list.*/
    public void removeAllCoIProposals() {
	this.storableUpdate(); 
	coIProposals.clear();
    }
    
    /** Return an Iterator over the user's CoI list.*/
    public Iterator listAllCoIProposals() {
	this.storableFetch();
	return coIProposals.iterator();
    }
    
    /** Locate a named proposal in the user's CoI list or null if not found.*/
    public String findCoIProposal(String name) {
	this.storableFetch();
	if (coIProposals.containsKey(name)) 
	    return name;
	return null;
    }
    
    /** Return a reference to the list of proposals for which this user is CoI.*/
    public SerializableStorableSortedDictionary getCoIProposals() {this.storableFetch(); return coIProposals;}
    
   
    // Self Lock Method.
    /** Lock this User and recursively all descendants with the supplied key,*/
    public void lock(int key) {
	this.storableUpdate();
	if (canLock()) {
	    lockChildren(key);
	}
    } // end (lock).
    
    // Child Update Method.
    /** Reset paths for descendants to reflect movement of this User.*/
    public void updateChildren() {
    } // end (updateChildren).
    
    // Child Lock-Check Method.
    /** Return true if this User and ALL descendants are unlocked.*/
    public boolean canLock() {
	this.storableFetch();
	if (isLocked()) return false;
	
	return true;
    } // end (canLock).
    
    // Child Locking Method.
    /** Lock all descendants using the supplied key.*/
    public void lockChildren(int key) {
	this.storableUpdate(); 
	
	setLock(key);
    } // end (lockChildren).
    
    // Child Un-Locking Method.
    /** Unlock this (User) and all descendants using the supplied key if possible.
     * Returns silently if it  fails. Use canLock() to see if the unlock succeeded.*/
    public void unLockChildren(int key) {
	this.storableUpdate(); 
	
	unLock(key);
    } // end (unLockChildren).
    
    // Force Un-Locking Method.
    /** Forcibly unlock this (User) and all descendants whatever their current key.*/
    public void forceUnLock() {
	this.storableUpdate(); 
	lock = 0;
	
    } // end (forceUnLock).
    
    /** Returns a descriptor for this User's internal fields.*/
    public UserDescriptor getDescriptor() {
	this.storableFetch();
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
	descriptor.setTelephone(telephone);
	descriptor.setFax(fax);
	descriptor.setPostalCode(postalCode);
	descriptor.setOrganization(organization);

	return descriptor;
    }
    
   
    public String toString() {
	this.storableFetch();
	return "[User: "+path+" : "+
	    ", Name="+name+
	    ", Lock="+lock+   
	    ", Email="+email+
	    ", Tel="+telephone+
	    ", Fax="+fax+
	    ", PostCode="+postalCode+
	    ", Organization="+organization+
	    ", Address="+address1+", "+address2+", "+address3+
	    ", Country="+country+
	    ", Uname="+fname+" "+sname+"]";
    }
    
} // end class def [User].

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/02/23 18:45:20  snf
/** added serialversionUID.
/**
/** Revision 1.2  2000/11/23 18:44:56  snf
/** Debug.
/**
/** Revision 1.1  2000/11/23 14:21:49  snf
/** Initial revision
/** */
