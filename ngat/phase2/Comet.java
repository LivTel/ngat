package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
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
    
    // Constructor.
    
    public Comet() {super();}
    
    public Comet(String name) {
	super(name);
    }
    
    // Accessors. 
    public double getElementEpoch() { 
	
	return elementEpoch;
    }
    public double getLongAscNode() {
	 
	return longAscNode;
    }
    public double getArgPeri() { 
	
	return argPeri;
    }
    public double getPeriDist() {
	 
	return periDist;
    }
    public double getOrbitalInc() {
	 
	return orbitalInc;
    }
    public double getEccentricity() {
	 
	return eccentricity;
    }
    
    // Mutators.
    public void setElementEpoch(double in) {
	
	elementEpoch = in;
    }
    public void setLongAscNode(double in) { 
	
	longAscNode = in;
    }
    public void setArgPeri(double in) {  
	
	argPeri = in;
    }
    public void setPeriDist(double in) {  
	
	periDist = in;
    }
    public void setOrbitalInc(double in) {  
	
	orbitalInc = in;
    }
    public void setEccentricity(double in) { 
	
	eccentricity = in;
    } 
    
    /** Returns the source's current position. This is calculated using
     * the orbital elements and obtained via ngat.astrometry.Astrometry*/
    public Position getPosition() {
	 	
	Position position = Astrometry.getPlanetPosition(this);
	return position;
    }
    
    /** Returns the current Non-sidereal tracking in RA.*/
    public double getNsTrackRA() { 
	
	return 0.0;
    }
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public double getNsTrackDec() {
	
	return 0.0;
    }
    
    // Clone Constructor.
    public NPDBObject copy() {
	try {
	    return (Comet)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    /** Returns readable description. ##TBD##*/
    public String toString() {
	return "Comet: "+name+
	    " : Epoch "+elementEpoch+" ..ns tracking is not currently available";
    }

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<comet name = '"+name+"'>");
	out.println(tab(level+1)+"<elementEpoch>"+elementEpoch+"</elementEpoch>");
	out.println(tab(level+1)+"<longAscNode>" +Position.toDegrees(longAscNode,3)+"</longAscNode>");
	out.println(tab(level+1)+"<argPeri>"     +Position.toDegrees(argPeri,3)+"</argPeri>");
	out.println(tab(level+1)+"<periDist>"    +periDist+"</periDist>");
	out.println(tab(level+1)+"<orbitalInc>"  +Position.toDegrees(orbitalInc,3)+"</orbitalInc>");
	out.println(tab(level+1)+"<eccentricity>"+eccentricity+"</eccentricity>");	
	out.println(tab(level)+"</comet>");
    } // end (write).
   
} // end class def [Comet].

