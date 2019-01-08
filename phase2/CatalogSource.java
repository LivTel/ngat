package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.util.*;
import java.io.*;

public class CatalogSource extends SolarSystemSource implements Serializable {
    
    /** Constant indicating source:- The SUN.*/
    public static final int SUN  = 0;
    
    /** Constant indicating source:- The MOON.*/
    public static final int MOON = 3;
    
    /** Constant indicating source:- The planet MERCURY.*/
    public static final int MERCURY = 1;

    /** Constant indicating source:- The planet VENUS.*/
    public static final int VENUS = 2;
    
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
    public void setCatalogId(int in) { 
	
	this.catalogId = in; 
    }
    
    /** Returns the catalog id of this source.*/
    public int getCatalogId() { 
	
	return catalogId;
    }
    
    /** Returns the catalog source's current position. This is calculated 
     * via an appropriate algorithm dependant on the type of source and
     * obtained via ngat.astrometry.Astrometry.*/
    public ngat.astrometry.Position getPosition() {
	
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
    public double getNsTrackRA() { 
	
	switch (catalogId) {
	case SUN:
	    return Astrometry.getSolarTracking().getNsTrackRA();
	case MOON:
	    return Astrometry.getLunarTracking().getNsTrackRA();
	default:
	    return 0.0;
	}	
    }
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public double getNsTrackDec() { 
	
	switch (catalogId) {
	case SUN:
	    return Astrometry.getSolarTracking().getNsTrackDec();
	case MOON:
	    return Astrometry.getLunarTracking().getNsTrackDec();
	default:
	    return 0.0;
	}
    }
    
    // Clone Constructor.   
    public NPDBObject copy() {
	try {
	    return (CatalogSource)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<catalogSource name = '"+name+"'>");
	out.println(tab(level+1)+"<catalogId>"+catalogId+"</catalogId>");
	out.println(tab(level)+"</catalogSource>");
    } // end (write).
    
    /** Returns readable description. ##TBD##*/
    public String toString() {
	return "Cat.Source: "+name+
	    " : Catid"+catalogId;
    }
  
} // End class def. [CatalogSource].
