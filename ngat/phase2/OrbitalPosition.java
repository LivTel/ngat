package ngat.phase2;

import ngat.phase2.nonpersist.*;
import java.io.*;

/** Stores the location on the sky, of an object (planet, comet) specified by a set of
 * orbital elements. The current position is either calculated using these
 * elements at this time or retrieved from either an internal or external cache.
 * 
 * $Id: OrbitalPosition.java,v 1.1 2000-11-29 12:42:02 snf Exp $
 *
 */
public class OrbitalPosition extends Position implements Serializable {
    
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
    
    /** Mean anomaly at epoch (rads). */
    protected double meanAnomaly;
    
    /** Mean distance (orbital radius), (au). */
    protected double meanDistance;

    /** Daily rate of motion in orbit (rad/day). */
    protected double dailyMotion;
    
    /** Longitude of perihelion (rads). */
    protected double longPeri;
    
    /** Mean longitude (rads). */
    protected double meanLongitude;

    /** Orbital period (days). */
    protected double period;
    
    /** Cache RA locally. */
    private double cacheRA;
    
    /** Cache Dec locally. */
    private double cacheDec;
    
    /** The last time at which currently cached values can be relied on.*/
    protected long cacheExpiry;
    
    
    public OrbitalPosition() {
	super();
	cacheRA  = 0.0;
	cacheDec = 0.0;
	cacheExpiry = 0L;
    }
    
    public OrbitalPosition(NPOrbitalPosition npPosition) {
	this.argPeri       = npPosition.getArgPeri();
	this.longAscNode   = npPosition.getLongAscNode();
	this.orbitalInc    = npPosition.getOrbitalInc();
	this.dailyMotion   = npPosition.getDailyMotion();
	this.eccentricity  = npPosition.getEccentricity();
	this.elementEpoch  = npPosition.getElementEpoch();
	this.meanDistance  = npPosition.getMeanDistance();
	this.meanAnomaly   = npPosition.getMeanAnomaly();
	this.longPeri      = npPosition.getLongPeri();
	this.period        = npPosition.getPeriod();
	this.periDist      = npPosition.getPeriDist();
	this.meanLongitude = npPosition.getMeanLongitude();	  
	cacheExpiry = 0L;
    }
    
    
    public NPPosition makeNP() {
	NPOrbitalPosition  npPosition = new NPOrbitalPosition();
	npPosition.setArgPeri(argPeri);
	npPosition.setLongAscNode(longAscNode);
	npPosition.setOrbitalInc(orbitalInc);
	npPosition.setDailyMotion(dailyMotion);
	npPosition.setEccentricity(eccentricity);
	npPosition.setElementEpoch (elementEpoch);
	npPosition.setMeanDistance(meanDistance);
	npPosition.setMeanAnomaly(meanAnomaly);
	npPosition.setLongPeri(longPeri);
	npPosition.setPeriod(period);
	npPosition.setPeriDist(periDist);
	npPosition.setMeanLongitude(meanLongitude);	
	return npPosition;
    }

    public double getRA() {
	if (now() > cacheExpiry) {
	    calculatePosition();
	}
	return cacheRA;
    }
    
    public double getDec() {
	if (now() > cacheExpiry) {
	    calculatePosition();
	}
	return cacheDec;
    }
    
    /** Calculates the RA of this Position using orbital elements at this time.
     * Uses slaPlante.*/
    public void calculatePosition() {
	// cacheExpiry = now()+.... depends on rate of change of posn and desired accuracy
	cacheRA  = 0.0;
	cacheDec = 0.0;
    } //#### TEMP ####
    

    public double getElementEpoch() { return elementEpoch;}
    public double getLongAscNode() { return longAscNode;}
    public double getArgPeri() { return argPeri;}
    public double getPeriDist() { return periDist;}
    public double getOrbitalInc() { return orbitalInc;}
    public double getEccentricity() { return eccentricity;}
    public double getMeanAnomaly() { return meanAnomaly;}
    public double getMeanDistance() { return meanDistance;}
    public double getDailyMotion() { return dailyMotion;}
    public double getLongPeri() { return longPeri;}
    public double getMeanLongitude() { return meanLongitude;}
    public double getPeriod() { return period;}
    
    public void setElementEpoch(double in) { elementEpoch = in;}
    public void setLongAscNode(double in) { longAscNode = in;}
    public void setArgPeri(double in) {  argPeri = in;}
    public void setPeriDist(double in) {  periDist = in;}
    public void setOrbitalInc(double in) {  orbitalInc = in;}
    public void setEccentricity(double in) { eccentricity = in;}
    public void setMeanAnomaly(double in) {  meanAnomaly = in;}
    public void setMeanDistance(double in) {  meanDistance = in;}
    public void setDailyMotion(double in) {  dailyMotion = in;}
    public void setLongPeri(double in) {  longPeri = in;}
    public void setMeanLongitude(double in) {  meanLongitude = in;}
    public void setPeriod(double in) {  period = in;}
    
}



