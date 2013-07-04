package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class ReportedTarget extends Target
    implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
        new String( "$Id: ReportedTarget.java,v 1.2 2013-07-04 10:38:20 cjm Exp $" );

    /**
     *
     */
    protected XYZMatrix observed;

    /**
     * The unrefracted `Observed' position as an XYZMatrix in Horizon
     * coordinates.
     */
    protected XYZMatrix unrefracted;

    /**
     *
     */
    protected XYZMatrix observedRADec;

    /**
     *
     */
    protected XYZMatrix topocentric;

    /**
     *
     */
    protected XYZMatrix apparentHADec;

    /**
     *
     */
    protected XYZMatrix apparentRADec;

    /**
     *
     */
    protected XYZMatrix systemRADec;

    /**
     *
     */
    protected XYZMatrix outputRADec;

    /**
     *
     */
    protected Equinox outputEquinox;

    /**
     *
     */
    protected double airmass;

    /**
     * Timestamp of this ReportedTarget
     */
    protected Timestamp timestamp;

    /**
     *
     */
    protected double lstRadians;

    /**
     *
     */
    protected double mjd;

    /**
     *
     */
    protected double julianEpoch;


    /**
     * Empty constructor.
     */
    public ReportedTarget()
    {

    }


    /**
     * Constructor for ReportedTargets.
     * 
     */
    public ReportedTarget( XYZMatrix newObserved, 
			   XYZMatrix newObservedRADec, 
			   XYZMatrix newUnrefracted,
			   XYZMatrix newTopocentric, 
			   XYZMatrix newApparentHADec, 
			   XYZMatrix newApparentRADec, 
			   XYZMatrix newSystemRADec, 
			   XYZMatrix newOutputRADec, 
			   Timestamp newTimestamp, 
			   Equinox newOutputEquinox,
			   double newAirmass, double newLST, 
			   double newMJD, double newJulianEpoch )
    {
	this.observed            = newObserved;
	this.observedRADec       = newObservedRADec;
	this.unrefracted         = newUnrefracted;
	this.topocentric         = newTopocentric;
	this.apparentHADec       = newApparentHADec;
	this.apparentRADec       = newApparentRADec;
	this.systemRADec         = newSystemRADec;
	this.outputRADec         = newOutputRADec;
	this.timestamp           = newTimestamp;
	this.outputEquinox       = newOutputEquinox;
	this.airmass             = newAirmass;
	this.lstRadians          = newLST;
	this.mjd                 = newMJD;
	this.julianEpoch         = newJulianEpoch;
    }


    /**
     * This method sets observed.
     * @param newObserved
     * @see #observed
     */
    public void setObserved( XYZMatrix newObserved )
    {
        observed = newObserved;
    }


    /**
     * This method returns observed.
     * @return observed
     * @see #observed
     */
    public XYZMatrix getObserved()
    {
        return observed;
    }


    /**
     * This method returns the unrefracted Observed position.
     * @return unrefracted
     * @see #unrefracted
     */
    public XYZMatrix getUnrefracted()
    {
	return unrefracted;
    }


    /**
     * This method sets observedRADec.
     * @param newObservedRADec
     * @see #observedRADec
     */
    public void setObservedRADec( XYZMatrix newObservedRADec )
    {
        observedRADec = newObservedRADec;
    }


    /**
     * This method sets observedRADec.
     * Overloaded for the JNI functions.
     * @param x the x direction-cosine
     * @param y the y direction-cosine
     * @param z the z direction-cosine 
     * @see #apparentRADec
     */
    public void setObservedRADec( double x, double y, double z )
    {
        observedRADec = new XYZMatrix( x, y, z );
    }


    /**
     * This method returns observedRADec.
     * @return observedRADec
     * @see #observedRADec
     */
    public XYZMatrix getObservedRADec()
    {
        return observedRADec;
    }
    

    /**
     * This method sets topocentric.
     * @param newTopocentric
     * @see #topocentric
     */
    public void setTopocentric( XYZMatrix newTopocentric )
    {
        topocentric = newTopocentric;
    }


    /**
     * This method sets topocentric.
     * Overloaded for the JNI functions.
     * @param x the x direction-cosine
     * @param y the y direction-cosine
     * @param z the z direction-cosine
     * @see #topocentric
     */
    public void setTopocentric( double x, double y, double z )
    {
        topocentric = new XYZMatrix( x, y, z );
    }


    /**
     * This method returns topocentric.
     * @return topocentric
     * @see #topocentric
     */
    public XYZMatrix getTopocentric()
    {
        return topocentric;
    }


    /**
     * This method sets apparentHADec.
     * @param newApparentHADec
     * @see #apparentHADec
     */
    public void setApparentHADec( XYZMatrix newApparentHADec )
    {
        apparentHADec = newApparentHADec;
    }


    /**
     * This method sets apparentHADec.
     * Overloaded for the JNI functions.
     * @param x the x direction-cosine
     * @param y the y direction-cosine
     * @param z the z direction-cosine
     * @see #apparentHADec
     */
    public void setApparentHADec( double x, double y, double z )
    {
        apparentHADec = new XYZMatrix( x, y, z );
    }


    /**
     * This method returns apparentHADec.
     * @return apparentHADec
     * @see #apparentHADec
     */
    public XYZMatrix getApparentHADec()
    {
        return apparentHADec;
    }


    /**
     * This method sets apparentRADec.
     * @param newApparentRADec
     * @see #apparentRADec
     */
    public void setApparentRADec( XYZMatrix newApparentRADec )
    {
        apparentRADec = newApparentRADec;
    }


    /**
     * This method sets apparentRADec.
     * Overloaded for the JNI functions.
     * @param x the x direction-cosine
     * @param y the y direction-cosine
     * @param z the z direction-cosine 
     * @see #apparentRADec
     */
    public void setApparentRADec( double x, double y, double z )
    {
        apparentRADec = new XYZMatrix( x, y, z );
    }


    /**
     * This method returns apparentRADec.
     * @return apparentRADec
     * @see #apparentRADec
     */
    public XYZMatrix getApparentRADec()
    {
        return apparentRADec;
    }


    /**
     * This method sets systemRADec.
     * @param newSystemRADec
     * @see #systemRADec
     */
    public void setSystemRADec( XYZMatrix newSystemRADec )
    {
        systemRADec = newSystemRADec;
    }


    /**
     * This method sets systemRADec.
     * Overloaded for the JNI functions.
     * @param x the x direction-cosine
     * @param y the y direction-cosine
     * @param z the z direction-cosine
     * @see #systemRADec
     */
    public void setSystemRADec( double x, double y, double z )
    {
        systemRADec = new XYZMatrix( x, y, z );;
    }


    /**
     * This method returns systemRADec.
     * @return systemRADec
     * @see #systemRADec
     */
    public XYZMatrix getSystemRADec()
    {
        return systemRADec;
    }


    /**
     * This method sets outputRADec.
     * @param newOutputRADec
     * @see #outputRADec
     */
    public void setOutputRADec( XYZMatrix newOutputRADec )
    {
        outputRADec = newOutputRADec;
    }


    /**
     * This method sets outputRADec.
     * @see #outputRADec
     */
    public void setOutputRADec( double x, double y, double z )
    {
        outputRADec = new XYZMatrix( x, y, z );
    }


    /**
     * This method returns outputRADec.
     * @return outputRADec
     * @see #outputRADec
     */
    public XYZMatrix getOutputRADec()
    {
        return outputRADec;
    }


    /**
     * This method sets airmass.
     * @param newAirmass
     * @see #airmass
     */
    public void setAirmass( double newAirmass )
    {
        airmass = newAirmass;
    }


    /**
     * This method returns airmass.
     * @return airmass
     * @see #airmass
     */
    public double getAirmass()
    {
        return airmass;
    }


    /**
     * This method sets utc.
     * @param newTimestamp
     * @see #timestamp
     */
    public void setTimestamp( Timestamp newTimestamp )
    {
        timestamp = newTimestamp;
    }


    /**
     * This method returns utc.
     * @return timestamp
     * @see #timestamp
     */
    public Timestamp getTimestamp()
    {
        return timestamp;
    }


    /**
     * Set outputEquinox.
     * @param newOutputEquinox
     * @see #outputEquinox
     */
    public void setOutputEquinox( Equinox newOutputEquinox )
    {
	outputEquinox = newOutputEquinox;
    }


    /**
     * Get outputEquinox.
     * @return outputEquinox
     * @see #outputEquinox
     */
    public Equinox getOutputEquinox()
    {
	return outputEquinox;
    }


    /**
     * This method sets lstRadians.
     * @param newLSTRadians
     * @see #lstRadians
     */
    public void setLSTRadians( double newLSTRadians )
    {
        lstRadians = newLSTRadians;
    }


    /**
     * This method returns lstRadians.
     * @return lstRadians
     * @see #lstRadians
     */
    public double getLSTRadians()
    {
        return lstRadians;
    }


    /**
     * This method sets mjd.
     * @param newMjd
     * @see #mjd
     */
    public void setMJD( double newMJD )
    {
        mjd = newMJD;
    }


    /**
     * This method returns mjd.
     * @return mjd
     * @see #mjd
     */
    public double getMJD()
    {
        return mjd;
    }


    /**
     * This ReportedTarget object described as a String
     * @return the String describing this ReportedTarget object
     */
    public String toString()
    {
	return( "   Observed position : "+
		Convert.xyzMatrixToAltAz( observed )+"\n"+
		"             Airmass : "+
		airmass+"\n"+
		"     Observed RA,Dec : "+
		Convert.xyzMatrixToRADec( observedRADec )+"\n"+
		"Unrefracted position : "+
		Convert.xyzMatrixToAltAz( unrefracted )+"\n"+
		"  Topocentric HA,Dec : "+
		Convert.xyzMatrixToHADec( topocentric )+"\n"+
		"     Apparent HA,Dec : "+
		Convert.xyzMatrixToHADec( apparentHADec )+"\n"+
		"     Apparent RA,Dec : "+
		Convert.xyzMatrixToRADec( apparentRADec )+"\n"+
		"        J2000 RA,Dec : "+
		Convert.xyzMatrixToRADec( systemRADec )+"\n"+
		"      Output Equinox : "+
		outputEquinox.getName()+"\n"+
		"  Output Coordinates : "+
		Convert.xyzMatrixToRADec( outputRADec )+"\n"+
		"           Timestamp : "+
		Convert.formatTime( timestamp.getSeconds() )+"\n"+
		"                 LST : "+
		Convert.radiansTo24HMSString( lstRadians )+"\n"+
		"                 MJD : "+
		mjd+"\n"+
		"        Julian Epoch : "+
		julianEpoch );
    }
}
/*
 *    $Date: 2013-07-04 10:38:20 $
 * $RCSfile: ReportedTarget.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/ReportedTarget.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
*/
