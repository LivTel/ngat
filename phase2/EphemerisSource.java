package ngat.phase2;

import java.io.*;
import java.util.*;
import java.text.*;

import ngat.astrometry.*;

public class EphemerisSource extends SolarSystemSource {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 5096760805793424329L;;
    
    Vector ephemeris;

    public EphemerisSource(String name) {
	super(name);
	ephemeris = new Vector();
    }

    public void addCoordinate(Coordinate c) {
	ephemeris.add(c);
    }

    public void addCoordinate(long time, double ra, double dec, double radot, double decdot) {
	ephemeris.add(new Coordinate(time,ra,dec,radot,decdot));
    }

    public Vector getEphemeris() {
	return ephemeris;
    }

    public void clearEphemeris() {
	ephemeris.clear();
    }

    public Position getPosition() {
	return Astrometry.getPlanetPosition(this);
    }

    public double getNsTrackRA() {
	// Astro.getTracking(this).radot;
	return 0.0;
    }
    public double getNsTrackDec() {
	// Astro.getTracking(this).decdot;
	return 0.0;
    }

    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("EphemerisSource: "+name+", Ephem=[");
	Iterator ie = ephemeris.iterator();
	while (ie.hasNext()) {
	    Coordinate coord = (Coordinate)ie.next();
	    Position pos = new Position(coord.getRA(), coord.getDec());
	    buffer.append(","+coord);
	}
	buffer.append("]");
	return buffer.toString();
    }

    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<ephemerisSource name = '"+name+"'>");
	Iterator ie = ephemeris.iterator();
	while (ie.hasNext()) {
	    Coordinate coord = (Coordinate)ie.next();
	    coord.writeXml(out, level+1);
	}
	out.println(tab(level)+"</ephemerisSource>");
    }
    
    public static class Coordinate implements Serializable {

	/** Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.*/
	private static final long serialVersionUID = -1256225309350558570L;;
	
	/** Epoch of this coordinate.*/
	private long time;

	/** RA (rads).*/
	private double ra;

	/** Declination (rads).*/
	private double dec;

	/** RA rate (rads/sec).*/
	private double radot;

	/** Declination rate (rads/sec).*/
	private double decdot;

	public Coordinate() {}

	public Coordinate(long time, double ra, double dec, double radot, double decdot) {
	    this();
	    this.time   = time;
	    this.ra     = ra;
	    this.dec    = dec;
	    this.radot  = radot;
	    this.decdot = decdot;
	}

	public long getTime()     { return time;}
	public double getRA()     { return ra;}
	public double getDec()    { return dec;}
	public double getRADot()  { return radot;}
	public double getDecDot() { return decdot;}

	public void setTime(long t) { this.time = t;}
	public void setRA(double ra) { this.ra = ra;}
	public void setDec(double dec) { this.dec = dec;}

	/** Readable string representation.*/
	public String toString() {
	    return sdf.format(new Date(time))+
		", RA= "+Position.toHMSString(ra)+
		", Dec= "+Position.toDMSString(dec)+
		", RA rate = "+240.0*Math.toDegrees(radot)+
		", Dec rate = "+3600.0*Math.toDegrees(decdot);
	}

	public void writeXml(PrintStream out, int level) {
	    NPDBObject np = new NPDBObject();
	    out.println(np.tab(level)+"<coordinate epoch='"+sdf.format(new Date(time))+"'>");
	    out.println(np.tab(level+1)+"<ra>"  +Position.toHMSString(ra)+"</ra>");
	    out.println(np.tab(level+1)+"<dec>" +Position.toDMSString(dec)+"</dec>");
	    out.println(np.tab(level)+"</coordinate>");
	}
	
    }

}
	
