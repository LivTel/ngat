package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class MajorPlanet extends SolarSyatemSource implements Serializable {

    // Variables.
    
    /** Epoch at which the elements are/were measured (mjd).*/
    protected double elementEpoch;
    
    /** Longitude of the ascending node (rads). */
    protected double longAscNode;
    
    /** Inclination of the orbit to ecliptic (rads). */
    protected double orbitalInc;
    
    /** Eccentricity of orbit ( =1, circle, <1, >1,  ). */
    protected double eccentricity;
    
    /** Mean distance (orbital radius), (au). */
    protected double meanDistance;
    
    /** Daily rate of motion in orbit (rad/day). */
    protected double dailyMotion;
    
    /** Longitude of perihelion (rads). */
    protected double longPeri;
    
    /** Mean longitude (rads). */
    protected double meanLongitude;
    
    /** Cache RA locally. */
    private double cacheRA;
    
    /** Cache Dec locally. */
    private double cacheDec;
    
    /** The last time at which currently cached values can be relied on.*/
    protected long cacheExpiry;
    
    // Constructor.
    
    public MajorPlanet() {super();}
    
    public MajorPlanet(String name) {
	super(name);
    }
    
    // Accessors.
    public double getElementEpoch() { return elementEpoch;}
    public double getLongAscNode() { return longAscNode;}
    
    public double getOrbitalInc() { return orbitalInc;}
    public double getEccentricity() { return eccentricity;}
   
    public double getMeanDistance() { return meanDistance;}
    public double getDailyMotion() { return dailyMotion;}
    public double getLongPeri() { return longPeri;}
    public double getMeanLongitude() { return meanLongitude;}
    
    // Mutators
    public void setElementEpoch(double in) { elementEpoch = in;}
    public void setLongAscNode(double in) { longAscNode = in;}
    
    public void setOrbitalInc(double in) {  orbitalInc = in;}
    public void setEccentricity(double in) { eccentricity = in;}
    
    public void setMeanDistance(double in) {  meanDistance = in;}
    public void setDailyMotion(double in) {  dailyMotion = in;}
    public void setLongPeri(double in) {  longPeri = in;}
    public void setMeanLongitude(double in) {  meanLongitude = in;}

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
    public MajorPlanet(NPMajorPlanet npMajorPlanet) {
	super(npMajorPlanet);
	elementEpoch = npMajorPlanet.getElementEpoch();
	longAscNode = npMajorPlanet.getLongAscNode();    
	orbitalInc = npMajorPlanet.getOrbitalInc();
	eccentricity =npMajorPlanet.getEccentricity();     
	meanDistance = npMajorPlanet.getMeanDistance();
	dailyMotion = npMajorPlanet.getDailyMotion();
	longPeri = npMajorPlanet.getLongPeri();
	meanLongitude = npMajorPlanet.getMeanLongitude();
    } // end (NP -> P Translator).
     

    // P -> NP Translator.
    public void stuff(NPMajorPlanet npMajorPlanet) {
	super.stuff(npMajorPlanet);
	npMajorPlanet.setElementEpoch(elementEpoch);
	npMajorPlanet.setLongAscNode(longAscNode);    
	npMajorPlanet.setOrbitalInc(orbitalInc);
	npMajorPlanet.setEccentricity(eccentricity);  
	npMajorPlanet.setMeanDistance(meanDistance);
	npMajorPlanet.setDailyMotion(dailyMotion);
	npMajorPlanet.setLongPeri(longPeri);
	npMajorPlanet.setMeanLongitude(meanLongitude);
    } // end (P -> NP Translator).
    
    
    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPMajorPlanet npMajorPlanet = new NPMajorPlanet();
	stuff(npMajorPlanet);
	return npMajorPlanet;
    } // end (makeNp).
        
} // end class def [MajorPlanet].
