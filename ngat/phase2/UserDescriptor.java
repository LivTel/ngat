package ngat.phase2;

import ngat.phase2.nonpersist.*;
import ngat.util.logging.*;

import java.io.*;

/** Contains selected fields of a User, suitable for informing
 * remote clients of the details of a User without revealing
 * any substructure.
 * Used By ADM_ListUsers to store the basic details of a User. 
 * $Id$.*/
public class UserDescriptor implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -588538019979248313L;

    protected String path;
    protected String name;    
   
    protected int lock;   

    protected String email;

    /** Telephone number.*/
    protected String telephone;

    /** Fax number.*/
    protected String fax;

    /** Postal/ZIP code.*/
    protected String postalCode;
       
    /**  organization, department, intitute to which user belongs. */
    protected String organization;
    
    protected String address1;
    protected String address2;
    protected String address3;
    protected String country;
    protected String sname;
    protected String fname;

    /** Make an empty descriptor. */
    public UserDescriptor() {}

    /** Make an empty descriptor. */
    public UserDescriptor(String name) {
	this();
	this.name = name;
    }

    /** Make a descriptor for the supplied User. */
    public UserDescriptor(User user) {
	this.path = user.getPath();
	this.name = user.getName();
	this.lock = user.getLock();
	this.email = user.getEmail();
	this.telephone=user.getTelephone();
	this.fax=user.getFax();
	this.postalCode=user.getPostalCode();
	this.organization = user.getOrganization();
	this.address1 = user.getAddress1();
	this.address2 = user.getAddress2();
	this.address3 = user.getAddress3();
	this.country = user.getCountry();
	this.sname = user.getSname();
	this.fname = user.getFname();
    }
    
    /** Return a User from this descriptor. */
    public NPUser createUser() {
	NPUser user = new NPUser(name);
	user.setPath(path);
	user.setLock(lock);
	user.setEmail(email);
	user.setFax(fax);
	user.setTelephone(telephone);
	user.setPostalCode(postalCode);
	user.setOrganization(organization);
	user.setAddress1(address1);
	user.setAddress2(address2);
	user.setAddress3(address3);
	user.setCountry(country);
	user.setSname(sname);
	user.setFname(fname);
	return user;
    }

    public String  getPath() { return path;} 
    public void    setPath(String path) { this.path = path;}

    public String  getName() { return name;}  
    public void    setName(String name) { this.name = name;}
    
    public int     getLock() { return lock;}
    public void    setLock(int lock) { this.lock = lock;}

    public String  getEmail() { return email;}
    public void    setEmail(String email) { this.email = email ;}
 
    /** Sets the Telephone number.*/
    public void setTelephone(String in) {  this.telephone = in;};

    /** Returns the Telephone number.*/
    public String getTelephone() { return telephone;}
    
    /** Sets the Fax number.*/
    public void setFax(String in) { this.fax = in;};
    
    /** Returns the Fax number.*/
    public String getFax() { return fax;}
    
    /** Sets the Postal/ZIP code.*/
    public void setPostalCode(String in){   this.postalCode = in;};
    
    /** Returns the Postal/ZIP code.*/
    public String getPostalCode() { return postalCode;}

    /** Sets the  organization, department, intitute to which user belongs .*/
    public void setOrganization(String in) { this.organization = in;}
    
    /** Returns the  organization, department, intitute to which user belongs. */
    public String getOrganization() { return organization;}
    
    public String  getAddress1() { return address1;}
    public void    setAddress1(String address1) { this.address1 = address1;}

    public String  getAddress2() { return address2;}
    public void    setAddress2(String address2) { this.address2 = address2;}

    public String  getAddress3() { return address3;}
    public void    setAddress3(String address3) { this.address3 = address3;}

    public String  getCountry() { return country;}
    public void    setCountry(String country) { this.country = country;}

    public String  getSname() { return sname;}
    public void    setSname(String sname) { this.sname = sname;}

    public String  getFname() { return fname;}
    public void    setFname(String fname) { this.fname = fname;}


    public boolean isLocked() { return (lock != 0);}

    /** Formatted Text Output to specified Logger.
     * @param logName The name of the logger to use.*/
    public void write(String logName) {
	Logger logger = LogManager.getLogger(logName);
	logger.log(1, "Path="+path+
		   ", Name="+name+
		   ", Lock="+lock+   
		   ", Email="+email+ 
		   ", Tel="+telephone+
		   ", Fax="+fax+
		   ", PostCode="+postalCode+
		   ", Organization="+organization+
		   ", Address="+address1+", "+address2+", "+address3+
		   ", Country="+country+
		   ", Uname="+sname+" "+fname+"]");       
    } // end (write).
    
    public String toString() {
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
	    ", Uname="+sname+" "+fname+"]";
    }

    
} // Class Def. [UserDescriptor].

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:46:07  snf
/** modified write.
/**
/** Revision 1.1  2000/11/29 12:48:37  snf
/** Initial revision
/**. */


