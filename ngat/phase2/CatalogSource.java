package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.util.*;
import java.io.*;

public class CatalogSource extends SolarSystemSource implements Serializable {
    
    /** Constant indicating source:- The SUN.*/
    public static final int SUN  = 0;
    
    /** Constant indicating source:- The .*/
    public static final int MOON = 1;
    
    /** Constant indicating source:- The planet MERCURY.*/
    public static final int MERCURY = 2;

    /** Constant indicating source:- The planet VENUS.*/
    public static final int VENUS = 3;
    
    /** Constant indicating source:- The planet MARS.*/
    public static final int MARS = 4;
    
    /** Constant indicating source:- The planet JUPITER.*/
    public static final int JUPITER = 5;
    
    /** Constant indicating source:- The planet SATURN.*/
    public static final int SATURN = 6;
    
    /** Constant indicating source:- The planet URANUS.*/
    public static final int URANUS = 7;
    
    /** Constant indicating source:- The planet NEPTUNE.*/
    public static final int NEPTUNE = 8;
    
    /** Constant indicating source:- The planet  PLUTO.*/
    public static final int PLUTO = 9;
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 6135911651599459717L;
    
    // Variables.
    
    /** Identity code from catalog.*/
    protected int catalogId;
    
    // Constructor.
    
    public CatalogSource() {super();}
    
    public CatalogSource(String name) {
	super(name);
    }
    
    public CatalogSource(String name, int catalogId) {
	super(name);
	this.catalogId = catalogId;
    }
    
    // Accessors.
    
    /** Set the catalog id of this source.*/
    public void setCatalogId(int in) { this.catalogId = in; }
    
    /** Returns the catalog id of this source.*/
    public int getCatalogId() { return catalogId; }
    
    /** Returns the catalog source's current position. This is calculated 
     * via an appropriate algorithm dependant on the type of source and
     * obtained via ngat.astrometry.Astrometry.*/
    public Position getPosition() {
 	Position position = null;
	switch (catalogId) {
	case SUN:
	    return Astrometry.getSolarPosition();
	case MOON:
	    return Astrometry.getLunarPosition();
	default:
	    return Astrometry.getPlanetPosition(this);
	}
    }
    
    /** Returns the current Non-sidereal tracking in RA.*/
    public double getNsTrackRA() { return 0.0;}
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public double getNsTrackDec() { return 0.0;}
    
    // NP -> P Translator.  
    public CatalogSource(NPCatalogSource npCatSource) {
	super(npCatSource);
	catalogId = npCatSource.getCatalogId();
    } // end (NP -> P Translator).
    
    // P -> NP Translator.   
    public void stuff(NPCatalogSource npCatSource) {
	super.stuff(npCatSource);
	npCatSource.setCatalogId(catalogId);
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    
    public NPDBObject makeNP() {
	NPCatalogSource npCatSource = new NPCatalogSource();
	stuff(npCatSource);
	return npCatSource;
    } // end (makeNp).

} // End class def. [CatalogSource].
