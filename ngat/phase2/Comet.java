package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.util.*;
import java.io.*;


/** Encapsulates information about comets.*/
public class Comet extends SolarSystemSource implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 1272919295968610713L;
    
    // Variables.

    /** Epoch at which the elements are/were measured (mjd).*/
    protected double elementEpoch;
    
    /** Longitude of the ascending node (rads). */
    protected double longAscNode;

    /** Argument of perihelion (rads). */
    protected double argPeri;
    
    /** Periheion distance (au). */
    protected double periDist;
    
    /** Inclination of the orbit to ecliptic (rads). */
    protected double orbitalInc;
    
    /** Eccentricity of orbit ( =1, circle, <1, >1,  ). */
    protected double eccentricity;
    
    /** Cache RA locally. */
    private double cacheRA;
    
    /** Cache Dec locally. */
    private double cacheDec;
    
    /** The last time at which currently cached values can be relied on.*/
    protected long cacheExpiry;
    
    
    // Constructor.
    
    public Comet() {super();}
    
    public Comet(String name) {
	super(name);
    }
    
    // Accessors. 
    public double getElementEpoch() { return elementEpoch;}
    public double getLongAscNode() { return longAscNode;}
    public double getArgPeri() { return argPeri;}
    public double getPeriDist() { return periDist;}
    public double getOrbitalInc() { return orbitalInc;}
    public double getEccentricity() { return eccentricity;}
   
    // Mutators.
    public void setElementEpoch(double in) { elementEpoch = in;}
    public void setLongAscNode(double in) { longAscNode = in;}
    public void setArgPeri(double in) {  argPeri = in;}
    public void setPeriDist(double in) {  periDist = in;}
    public void setOrbitalInc(double in) {  orbitalInc = in;}
    public void setEccentricity(double in) { eccentricity = in;} 
    
    /** Returns the source's current position. This is calculated using
     * the orbital elements and obtained via ngat.astrometry.Astrometry*/
    public Position getPosition() { 	
	Position position = Astrometry.getPlanetPosition(this);
	return position;
    }

    /** Returns the current Non-sidereal tracking in RA.*/
    public double getNsTrackRA() { return 0.0;}
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public double getNsTrackDec() { return 0.0;}
    
    // NP -> P Translator.    
    public Comet(NPComet npComet) {
	super(npComet);
	elementEpoch = npComet.getElementEpoch();
	longAscNode  = npComet.getLongAscNode();
	argPeri      = npComet.getArgPeri();
	periDist     = npComet.getPeriDist();
	orbitalInc   = npComet.getOrbitalInc();
	eccentricity = npComet.getEccentricity();
    } // end (NP -> P Translator).
    
    // P -> NP Translator.   
    public void stuff(NPComet npComet) {
	super.stuff(npComet);
	npComet.setElementEpoch(elementEpoch);
	npComet.setLongAscNode(longAscNode);
	npComet.setArgPeri(argPeri);
	npComet.setPeriDist(periDist);
	npComet.setOrbitalInc(orbitalInc);
	npComet.setEccentricity(eccentricity);
    } // end (P -> NP Translator).
    
    // P -> NP Translator.  
    public NPDBObject makeNP() {
	NPComet npComet = new NPComet();
	stuff(npComet);
	return npComet;
    } // end (makeNp).


} // end class def [Comet].
