package ngat.astrometry;

import java.io.*;

/** Represents a position on the Celestial Sphere as Altitude and azimuth.
 * The position may be fixed (as in a current telescope pointing coordinates)
 * or may be moving (as in an instantaneous star position on the Celestial sphere).
 * This class represents an instantaneous snapshot of an instance of 
 * ngat.astrometry.Position.
 * <br><br>
 * $Id: SkyCoordinate.java,v 1.1 2013-06-28 10:11:49 cjm Exp $
 */
public class SkyCoordinate implements Serializable{

    /** The altitude of this SkyCoordinate (rads +/- pi).*/
    protected double altitude;

    /** The azimuth of this SkyCoordinate (rads [0, 2*pi]).*/
    protected double azimuth;

    /** The time at whcih this SkyCoordinate is valid.*/
    protected long validTime;

    public SkyCoordinate(double azimuth, double altitude) {
	this.azimuth   = azimuth;
	this.altitude  = altitude;
	this.validTime = System.currentTimeMillis();
    }
    
    public SkyCoordinate(double azimuth, double altitude, long validTime) {
	this.azimuth   = azimuth;
	this.altitude  = altitude;
	this.validTime = validTime;
    }

    protected double getAltitude() { return altitude; }

    protected void   setAltitude(double altitude) { this.altitude = altitude; }

    protected double getAzimuth() { return azimuth; }

    protected void   setAzimuth( double azimuth) { this.azimuth = azimuth; }
    
    protected long   getValidTime() { return validTime; }
    
    protected void   setValidTime(long validTime) { this.validTime = validTime; }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/12/18 12:16:09  snf
/** Initial revision
/**
/** Revision 1.1  2006/11/20 14:37:45  cjm
/** Initial revision
/** */
