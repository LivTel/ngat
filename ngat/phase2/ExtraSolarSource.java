package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


public class ExtraSolarSource extends Source implements Serializable {

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
    public void setDec(double in) { this.dec = dec; }
    
    /** Return the source dec.*/
    public double getDec() { return dec; }
    
    /** Sets the  proper motion in RA -(sec/year) .*/
    public void setPmRA(double in) { this.pmRA = in;}
    
    /** Returns the  proper motion in RA -(sec/year). */
    public double getPmRA() { return pmRA;}
    
    /** Sets the  proper motion in Dec -(arcsec/year) .*/
    public void setPmDec(double in) { this.pmDec = in;}
    
    /** Returns the  proper motion in Dec -(arcsec/year). */
    public double getPmDec() { return pmDec;}   
   
    /** Sets the Parallax of the source.*/
    public void setParallax(double in) { this.parallax = in;}
    
    /** Returns the Parallax of the source.*/
    public double getParallax() { return parallax;}
    
    /** Set the source radial velocity.*/
    public void setRadialVelocity(double in) { this.radialVelocity = in; }

    /** Returns the source radial velocity.*/
    public double getRadialVelocity() { return radialVelocity; }

    /** Returns the source's current position.*/
    public Position getPosition() { return new Position(RA, dec); }

    // Descendant Mutators.
     
    // NP -> P Translator.
    public ExtraSolarSource(NPExtraSolarSource npExtraSolarSource) {
	super(npExtraSolarSource);
	RA = npExtraSolarSource.getRA();
	dec = npExtraSolarSource.getDec();
	pmRA = npExtraSolarSource.getPmRA();
	pmDec = npExtraSolarSource.getPmDec();
	parallax = npExtraSolarSource.getParallax();
	radialVelocity =  npExtraSolarSource.getRadialVelocity();
	// Recursively call Daughter Translators.
	
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    public void stuff(NPExtraSolarSource npExtraSolarSource) {
	super.stuff(npExtraSolarSource);
	npExtraSolarSource.setRA(getRA());
	npExtraSolarSource.setDec(getDec());
	npExtraSolarSource.setPmRA(getPmRA());
	npExtraSolarSource.setPmDec(getPmDec());
	npExtraSolarSource.setParallax(getParallax());
	npExtraSolarSource.setRadialVelocity(getRadialVelocity());
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPExtraSolarSource npExtraSolarSource = new NPExtraSolarSource();
	stuff(npExtraSolarSource);
	return npExtraSolarSource;
    } // end (makeNp).
    

} // end class def [ExtraSolarSource].
