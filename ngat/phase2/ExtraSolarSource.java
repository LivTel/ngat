package ngat.phase2;

import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


public class ExtraSolarSource extends Source implements Serializable {

    // Variables
    
    /**  Proper motion in RA -(sec/year). */
    protected double pmRA;
    
    /**  Proper motion in Dec -(arcsec/year). */
    protected double pmDec;

    /**  Coordinate system for epoch measurment. */
    protected float equinox;
    
    /**The calendar designator for the coordinate system:- B = Besselian, J = Julian.*/
    protected char equinoxLetter;
    
    /**  Year and fraction of pm measurement. */
    protected float epoch;
    
    /** Parallax of the source. */
    protected double parallax;
    
    /** Non-sidereal tracking rate in RA. rad/sec*/
    protected double nsTrackRA;
    
    /** Non-sidereal tracking rate in declination. rad/sec*/
    protected double nsTrackDec;

  
     
    // Constructor.
    
    public ExtraSolarSource() {this("untitled");}
    
    public ExtraSolarSource(String name) {
	super(name);
    }

    // Accessors.



     /** Sets the  proper motion in RA -(sec/year) .*/
     public void setPmRA(double in) { this.pmRA = in;}

     /** Returns the  proper motion in RA -(sec/year). */
     public double getPmRA() { return pmRA;}

     /** Sets the  proper motion in Dec -(arcsec/year) .*/
     public void setPmDec(double in) { this.pmDec = in;}

     /** Returns the  proper motion in Dec -(arcsec/year). */
     public double getPmDec() { return pmDec;}

     /** Sets the  coordinate system for epoch measurment .*/
     public void setEquinox(float in) { this.equinox = in;}

     /** Returns the  coordinate system for epoch measurment. */
     public float getEquinox() { return equinox;}

    /** Sets the  calendar designator for the coordinate system.*/
    public void setEquinoxLetter(char in) { this.equinoxLetter = in;}
    
    /** Returns the calendar designator for the coordinate system.*/
    public char getEquinoxLetter() { return equinoxLetter;}

    /** Sets the Parallax of the source.*/
    public void setParallax(double in) { this.parallax = in;}

    /** Returns the Parallax of the source.*/
    public double getParallax() { return parallax;}

    /** Sets the Non-sidereal tracking rate in RA.*/
    public void setNsTrackRA(double in) { this.nsTrackRA = in;}

    /** Returns the Non-sidereal tracking rate in RA.*/
    public double getNsTrackRA() { return nsTrackRA;}

    /** Sets the  Non-sidereal tracking rate in declination.*/
    public void setNsTrackDec(double in) { this.nsTrackDec = in;}

    /** Returns the Non-sidereal tracking rate in declination.*/
    public double getNsTrackDec() { return nsTrackDec;}

    /** Sets the  year and fraction of RA/Dec  measurement .*/
    public void setEpoch(float in) { this.epoch = in;}
    
    /** Returns the  year and fraction of RA/Dec  measurement. */
    public float getEpoch() { return epoch;}
    
     // Descendant Mutators.
     
    // NP -> P Translator.
    public ExtraSolarSource(NPExtraSolarSource npExtraSolarSource) {
	super(npExtraSolarSource);
	Iterator it;
	pmRA = npExtraSolarSource.getPmRA();
	pmDec = npExtraSolarSource.getPmDec();
	equinox = npExtraSolarSource.getEquinox();
	equinoxLetter = npExtraSolarSource.getEquinoxLetter();
	epoch = npExtraSolarSource.getEpoch();
	parallax = npExtraSolarSource.getParallax();
	nsTrackRA = npExtraSolarSource.getNsTrackRA();
	nsTrackDec = npExtraSolarSource.getNsTrackDec();
	
	// Recursively call Daughter Translators.
	
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    public void stuff(NPExtraSolarSource npExtraSolarSource) {
	super.stuff(npExtraSolarSource);
	Iterator it;
	npExtraSolarSource.setPmRA(getPmRA());
	npExtraSolarSource.setPmDec(getPmDec());
	npExtraSolarSource.setEquinox(getEquinox());
	npExtraSolarSource.setEquinoxLetter(getEquinoxLetter());
	npExtraSolarSource.setEpoch(getEpoch());
	npExtraSolarSource.setParallax(getParallax());
	npExtraSolarSource.setNsTrackRA(getNsTrackRA());
	npExtraSolarSource.setNsTrackDec(getNsTrackDec());
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPExtraSolarSource npExtraSolarSource = new NPExtraSolarSource();
	stuff(npExtraSolarSource);
	return npExtraSolarSource;
    } // end (makeNp).
    

} // end class def [ExtraSolarSource].
