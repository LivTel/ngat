package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


public class ExtraSolarSource extends Source implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -4942161864226138846L;
    
    // Variables

    /** RA (rads).*/
    protected double RA;

    /** Dec (rads).*/
    protected double dec;
   
    /**  Proper motion in RA -(sec/year). */
    protected double pmRA;
    
    /**  Proper motion in Dec -(arcsec/year). */
    protected double pmDec;
   
    /** Parallax of the source.*/
    protected double parallax;
    
    /** Radial velocity. */
    protected double radialVelocity;
     
    // Constructor.
    
    public ExtraSolarSource() {this("untitled");}
    
    public ExtraSolarSource(String name) {
	super(name);
    }

    // Accessors.

    /** Set the source RA.*/
    public void setRA(double in) { this.RA = in; }
    
    /** Returns the source RA.*/
    public double getRA() { return RA; }

    /** Set the source dec.*/
    public void setDec(double in) { this.dec = in; }
    
    /** Return the source dec.*/
    public double getDec() {  return dec; }
    
    /** Sets the  proper motion in RA -(sec/year) .*/
    public void setPmRA(double in) { this.pmRA = in;}
    
    /** Returns the  proper motion in RA -(sec/year). */
    public double getPmRA() {  return pmRA;}
    
    /** Sets the  proper motion in Dec -(arcsec/year) .*/
    public void setPmDec(double in) { this.pmDec = in;}
    
    /** Returns the  proper motion in Dec -(arcsec/year). */
    public double getPmDec() {  return pmDec;}   
   
    /** Sets the Parallax of the source.*/
    public void setParallax(double in) { this.parallax = in;}
    
    /** Returns the Parallax of the source.*/
    public double getParallax() {  return parallax;}
    
    /** Set the source radial velocity.*/
    public void setRadialVelocity(double in) { this.radialVelocity = in; }

    /** Returns the source radial velocity.*/
    public double getRadialVelocity() {  return radialVelocity; }

    /** Returns the source's current position.*/
    public Position getPosition() {  return new Position(RA, dec); }

    // Clone Constructor.    
    public NPDBObject copy() {
	try {
	    return (ExtraSolarSource)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    public String toString() {
	return "ExtraSolar: "+name+
	    " : RA "+Position.toHMSString(RA)+
	    ", Dec "+Position.toDMSString(dec)+
	    ", pmRA "+pmRA+
	    ", pmDec "+pmDec+
	    ", px "+parallax+
	    ", RV "+radialVelocity;
	
	// ExtraSolar: SA3 : RA 12h 23m 12.2, Dec 243 34 43.4, Pm RA 2.3, Pm Dec 0.2, px 0.02, RV 23.3
	
    }
    
    /** Basic Formatted Text Output as XML.*/
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<extraSolarSource name = '"+name+"'>");
	out.println(tab(level+1)+"<ra>"            +Position.toHMSString(RA)+"</ra>");
	out.println(tab(level+1)+"<dec>"           +Position.toDMSString(dec)+"</dec>");
	out.println(tab(level+1)+"<pmRA>"          +pmRA+"</pmRA>");
	out.println(tab(level+1)+"<pmDec>"         +pmDec+"</pmDec>");
	out.println(tab(level+1)+"<parallax>"      +parallax+"</parallax>");
	out.println(tab(level+1)+"<radialVelocity>"+radialVelocity+"</radialVelocity>");
	out.println(tab(level)+"</extraSolarSource>");
    } // end (write).
     
} // end class def [ExtraSolarSource].
