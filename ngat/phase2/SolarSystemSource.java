package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.util.*;
import java.io.*;

/** Encapsulates information about Solar system objects.*/
public abstract class SolarSystemSource extends Source implements Serializable {

    // Variables

    // Constructor. 
    public SolarSystemSource() {this("untitled");}
    
    public SolarSystemSource(String name) {
	super(name);
    }
    
    // Accessors.

    /** Returns the current Non-sidereal tracking in RA.*/
    public abstract double getNsTrackRA();
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public abstract double getNsTrackDec();

    // NP -> P Translator.   
    public SolarSystemSource(NPSolarSystemSource npSource) {
	super(npSource);
    } // end (NP -> P Translator).
    
    // P -> NP Translator.   
    public void stuff(NPSolarSystemSource npSource) {
	super.stuff(npSource);
    } // end (P -> NP Translator).
    
    // P -> NP Translator.  
    public NPDBObject makeNP() {
	NPSolarSystemSource npSource = new NPSolarSystemSource();
	stuff(npSource);
	return npSource;
    } // end (makeNp).
    

} // end class def [SolarSystemSource].
