package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.util.*;
import java.io.*;

public class CatalogSource extends SolarSystemSource implements Serializable {
    
    public static final int SUN  = 0;

    public static final int MOON = 1;
    
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
	    return new Position(0.0, 0.0);
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
