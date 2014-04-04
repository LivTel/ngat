package ngat.astrometry;

import java.io.*;
import java.util.*;
import java.text.*;
/**
 * Stores the non-sidereal tracking for a target.
 * <br>
 * $Id$    
 *
 */
public class Tracking implements Serializable {

    /** The non-sidereal tracking in Right Ascension of the target in rads/sec. (0 <= RA <= PI) */
    protected double nsTrackRA;
    
    /** The  non-sidereal tracking in Declination of the target in rads/sec. (-PI <= dec <= PI) */
    protected double nsTrackDec;
    
    public Tracking(double nsTrackRA, double nsTrackDec) {
	this.nsTrackRA  = nsTrackRA;
	this.nsTrackDec = nsTrackDec;
    }

    static NumberFormat nf;

    static {
	nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(8);
        nf.setMinimumFractionDigits(8);
    }

    public double getNsTrackRA() { return nsTrackRA; }

    public double getNsTrackDec() { return nsTrackDec; }

    /** Format the Tracking rates in sec/sec (RA) and arcsec/sec (Dec).
     * @return A formatted String representation of tracking rates.*/
    public String toString() { 
	return toSecPerHour(nsTrackRA) + " sec/hour : " + toArcSecPerHour(nsTrackDec)+ " arcsec/hour";
    }
    
    /** Translate a tracking rate into sec/sec.
     * @param Tracking rate (RA/dec) in rad/sec.
     * @return The rate in sec-time/sec - (usually RA).
     */
    public static String toSecPerSec(double rate) { return nf.format(rate*180*240/Math.PI); }
 
    /** Translate a tracking rate into arcsec/sec.
     * @param Tracking rate (RA/dec) in rad/sec.
     * @return The rate in arcsec/sec (Ra or Dec).
     */
    public static String toArcSecPerSec(double rate) { return nf.format(rate*180*3600/Math.PI); }

    /** Translate a tracking rate into sec/sec.
     * @param Tracking rate (RA/dec) in rad/sec.
     * @return The rate in sec-time/sec - (usually RA).
     */
    public static String toSecPerHour(double rate) { return nf.format(rate*3600*180*240/Math.PI); }
 
    /** Translate a tracking rate into arcsec/sec.
     * @param Tracking rate (RA/dec) in rad/sec.
     * @return The rate in arcsec/sec (Ra or Dec).
     */
    public static String toArcSecPerHour(double rate) { return nf.format(rate*3600*180*3600/Math.PI); }

}
