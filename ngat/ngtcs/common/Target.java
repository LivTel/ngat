package ngat.ngtcs.common;

/**
 * This class is a subset of the Telescope Data used in the astrometric
 * transformations performed by the NGTCS.
 *
 * Some method overloading has been used to re-direct references to make access
 * from the Java Native Interface easier.
 * 
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public class Target implements java.lang.Cloneable, java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
        new String( "$Id: Target.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Name of this Target.
     */
    protected String name = "target";

    /**
     * Clone number of this Target.
     */
    protected int cloneNumber = 0;

    /**
     * Right Ascension in radians.
     */
    protected double ra = 0.0;

    /**
     * Declination in radians.
     */
    protected double dec = 0.0;

    /**
     * Proper Motion in Right Ascension in radians per year.
     */
    protected double properMotionRA = 0.0;

    /**
     * Proper Motion in Declination in radians per year.
     */
    protected double properMotionDec = 0.0;

    /**
     * Parallax in Arcseconds.
     */
    protected double parallax = 0.0;

    /**
     * Radial Velocity in kms<sup>-1</sup>, positive towards observer.
     */
    protected double radialVelocity = 0.0;

    /**
     * Epoch of Mean Equator and Equinox.
     */
    protected Equinox equinox = Equinox.J2000;

    /**
     * Epoch of Proper Motion.
     */
    protected Epoch epoch = new Epoch( 2000.0 );

    /**
     * Offset in Right Ascension in radians.
     */
    protected double offsetRA = 0.0;

    /**
     * Offset in Declination in radians.
     */
    protected double offsetDec = 0.0;

    /**
     * Non sidereal tracking rate in Right Ascension, in radians per day.
     */
    protected double nonSiderealTrackingRateRA = 0.0;

    /**
     * Non sidereal tracking rate in Declination, in radians per day.
     */
    protected double nonSiderealTrackingRateDec = 0.0;

    /**
     * Timestamp of when Non-sidereal tracking is to start.
     */
    protected Timestamp nonSiderealTrackingStart = null;

    /**
     * Minimum acceptable value for Proper Motion (-0.01).
     */
    protected final double MIN_PROPER_MOTION = -0.01;

    /**
     * Maximum acceptable value for Proper Motion (0.01).
     */
    protected final double MAX_PROPER_MOTION = 0.01;

    /**
     * Maximum acceptable value for Parallax (10.0).
     */
    protected final double MAX_PARALLAX = 10.0;

    /**
     * Minimum acceptable value for Radial Velocity (-100000.0).
     */
    protected final double MIN_RADIAL_VELOCITY = -100000.0;

    /**
     * Maximum acceptable value for Radial Velocity (10000.0).
     */
    protected final double MAX_RADIAL_VELOCITY = 10000.0;

    /**
     * Minimum Offset in Right Ascension, in arcseconds.
     */
    protected final double MIN_OFFSET_RA = -300.0;

    /**
     * Maximum Offset in Right Ascension, in arcseconds.
     */
    protected final double MAX_OFFSET_RA = 300.0;

    /**
     * Minimum Offset in Declination, in arcseconds.
     */
    protected final double MIN_OFFSET_DEC = -300.0;

    /**
     * Maximum Offset in Declination, in arcseconds.
     */
    protected final double MAX_OFFSET_DEC = 300.0;

    /**
     * Minimum wavelength in metres.
     */
    protected final double MIN_LAMBDA = 0.0;

    /**
     * Maximum wavelength in metres.
     */
    protected final double MAX_LAMBDA = 100.0;

    /**
     * Minimum non-sidereal tracking rate in radians per day.
     */
    protected final double MIN_NS_TRACK = -10.0;

    /**
     * Maximum non-sidereal tracking rate in radians per day.
     */
    protected final double MAX_NS_TRACK = 10.0;

    /**
     *
     */
    public Target()
    {

    }

    /**
     *
     */
    public Target( String targetName )
    {
	name = targetName;
    }


    /**
     * This method sets name.
     * @param name
     * @see#name
     */
    public void setName( String newName ) throws IllegalArgumentException
    {
	if( newName == null )
	    {
		throw new IllegalArgumentException
		    ( "null pointer given for name" );
	    }
    }


    /**
     * This method returns name
     * @return name
     * @see#name
     */
    public String getName()
    {
	return name;
    }


    /**
     * This method sets ra.
     * @param newRA
     * @see #ra
     */
    public void setRA( double newRA ) throws IllegalArgumentException
    {
	if( Util.outOfRange( newRA, 0.0, 2.0*Math.PI ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newRA+"] out of range for Right Ascension : "+
		      "0.0 .. "+(2.0*Math.PI) );
	    }
        ra = newRA;
    }


    /**
     * This method returns ra.
     * @return ra
     * @see #ra
     */
    public double getRA()
    {
        return ra;
    }


    /**
     * This method sets dec.
     * @param newDec
     * @see #dec
     */
    public void setDec( double newDec ) throws IllegalArgumentException
    {
	if( Util.outOfRange( newDec, -Math.PI/2.0, Math.PI/2.0 ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newDec+"] out of range for Declination : "+
		      (-Math.PI/2.0)+" .. "+(Math.PI/2.0) );
	    }
        dec = newDec;
    }


    /**
     * This method returns dec.
     * @return dec
     * @see #dec
     */
    public double getDec()
    {
        return dec;
    }


    /**
     * This method sets properMotionRA.
     * @param newProperMotionRA
     * @see #properMotionRA
     */
    public void setProperMotionRA( double newProperMotionRA ) 
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newProperMotionRA, MIN_PROPER_MOTION, 
			     MAX_PROPER_MOTION ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newProperMotionRA+
		      "] out of range for proper motion in Right "+
		      "Ascension : "+MIN_PROPER_MOTION+" .. "+
		      MAX_PROPER_MOTION);
	    }
        properMotionRA = newProperMotionRA;
    }


    /**
     * This method returns properMotionRA.
     * @return properMotionRA
     * @see #properMotionRA
     */
    public double getProperMotionRA()
    {
        return properMotionRA;
    }


    /**
     * This method sets properMotionDec.
     * @param newProperMotionDec
     * @see #properMotionDec
     */
    public void setProperMotionDec( double newProperMotionDec ) 
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newProperMotionDec, MIN_PROPER_MOTION, 
			     MAX_PROPER_MOTION ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newProperMotionDec+
		      "] out of range for proper motion in "+
		      "Declination : "+MIN_PROPER_MOTION+" .. "+
		      MAX_PROPER_MOTION);
	    }
        properMotionDec = newProperMotionDec;
    }


    /**
     * This method returns properMotionDec.
     * @return properMotionDec
     * @see #properMotionDec
     */
    public double getProperMotionDec()
    {
        return properMotionDec;
    }


    /**
     * This method sets parallax.
     * @param newParallax
     * @see #parallax
     */
    public void setParallax( double newParallax ) 
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newParallax, 0.0, MAX_PARALLAX ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newParallax+"] out of range for parallax : "+
		      "0.0 .. "+MAX_PARALLAX );
	    }
        parallax = newParallax;
    }


    /**
     * This method returns parallax.
     * @return parallax
     * @see #parallax
     */
    public double getParallax()
    {
        return parallax;
    }


    /**
     * This method sets radialVelocity.
     * @param newRadialVelocity
     * @see #radialVelocity
     */
    public void setRadialVelocity( double newRadialVelocity )
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newRadialVelocity, MIN_RADIAL_VELOCITY, 
			     MAX_RADIAL_VELOCITY ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newRadialVelocity+"] out of range for radial "+
		      "velocity : "+MIN_RADIAL_VELOCITY+" .. "+
		      MAX_RADIAL_VELOCITY );
	    }
        radialVelocity = newRadialVelocity;
    }


    /**
     * This method returns radialVelocity.
     * @return radialVelocity
     * @see #radialVelocity
     */
    public double getRadialVelocity()
    {
        return radialVelocity;
    }


    /**
     * This method sets equinox.
     * @param newEquinox
     * @see #equinox
     */
    public void setEquinox( Equinox newEquinox ) 
	throws IllegalArgumentException
    {
	if( newEquinox == null )
	    {
		throw new IllegalArgumentException
		    ( "null pointer given for Equinox" );
	    }
        equinox = newEquinox;
    }


    /**
     * This method returns equinox.
     * @return equinox
     * @see #equinox
     */
    public Equinox getEquinox()
    {
        return equinox;
    }


    /**
     * This method sets epoch.
     * @param epoch
     * @see #epoch
     */
    public void setEpoch( Epoch newEpoch ) throws IllegalArgumentException
    {
	if( newEpoch == null )
	    {
		throw new IllegalArgumentException
		    ( "null pointer given for Epoch" );
	    }
	epoch = newEpoch;
    }


    /**
     * This method returns the epoch.
     * @return epoch
     * @see #epoch
     */
    public Epoch getEpoch()
    {
	return epoch;
    }


    /**
     * This method sets OffsetRA.
     * @param newOffsetRA
     * @see #offsetRA
     */
    public void setOffsetRA( double newOffsetRA )
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newOffsetRA, MIN_OFFSET_RA, MAX_OFFSET_RA ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newOffsetRA+"] out of range for offset "+
		      "in right ascension : "+MIN_OFFSET_RA+" .. "+
		      MAX_OFFSET_RA );
	    }
        offsetRA = newOffsetRA;
    }


    /**
     * This method returns OffsetRA.
     * @return OffsetRA
     * @see #offsetRA
     */
    public double getOffsetRA()
    {
        return offsetRA;
    }


    /**
     * This method sets OffsetDec.
     * @param newOffsetDec
     * @see #offsetDec
     */
    public void setOffsetDec( double newOffsetDec )
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newOffsetDec, MIN_OFFSET_DEC, MAX_OFFSET_DEC ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newOffsetDec+"] out of range for offset "+
		      "in declination : "+MIN_OFFSET_DEC+" .. "+
		      MAX_OFFSET_DEC );
	    }
        offsetDec = newOffsetDec;
    }


    /**
     * This method returns OffsetDec.
     * @return OffsetDec
     * @see #offsetDec
     */
    public double getOffsetDec()
    {
        return offsetDec;
    }


    /**
     * This method sets the non-sidereal tracking rate in Right Ascension.
     * @param newNSTrackRA
     * @see #nonSiderealTrackingRateRA
     */
    public void setNonSiderealTrackingRateRA( double newNSTrackRA )
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newNSTrackRA, MIN_NS_TRACK, MAX_NS_TRACK ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newNSTrackRA+"] out of range for non-sidereal"+
		      "tracking rate in right ascension : "+MIN_NS_TRACK+
		      " .. "+MAX_NS_TRACK );
	    }
	nonSiderealTrackingRateRA = newNSTrackRA;
    }


    /**
     * This method returns the non-sidereal tracking rate in Right Ascension.
     * @return nonSiderealTrackingRateRA
     * @see #nonSiderealTrackingRateRA
     */
    public double getNonSiderealTrackingRateRA()
    {
	return nonSiderealTrackingRateRA;
    }


    /**
     * This method sets the non-sidereal tracking rate in Declination.
     * @param newNSTrackDec
     * @see #nonSiderealTrackingRateDec
     */
    public void setNonSiderealTrackingRateDec( double newNSTrackDec )
	throws IllegalArgumentException
    {
	if( Util.outOfRange( newNSTrackDec, MIN_NS_TRACK, MAX_NS_TRACK ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newNSTrackDec+
		      "] out of range for non-sidereal"+
		      "tracking rate in right ascension : "+
		      MIN_NS_TRACK+" .. "+MAX_NS_TRACK );
	    }
	nonSiderealTrackingRateDec = newNSTrackDec;
    }


    /**
     * This method returns the non-sidereal tracking rate in Declination.
     * @return nonSiderealTrackingRateDec
     * @see #nonSiderealTrackingRateDec
     */
    public double getNonSiderealTrackingRateDec()
    {
	return nonSiderealTrackingRateDec;
    }


    /**
     * Set the nonsidereal tracking Timestamp.
     * @param nsTrackStart
     * @see #nonSiderealTrackingStart
     */
    public void setNonSiderealTrackingStart( Timestamp nsTrackStart )
    {
	nonSiderealTrackingStart = nsTrackStart;
    }


    /**
     * Get the nonsidereal tracking Timestamp
     * @return nonSiderealTrackingStart
     * @see #nonSiderealTrackingStart
     */
    public Timestamp getNonSiderealTrackingStart()
    {
	return nonSiderealTrackingStart;
    }


    /**
     *
     *
     */
    public String toString()
    {
	return( "\t             name : "+name+"\n"+
	        "\t               RA : "+
		Convert.radiansTo24HMSString( ra )+"\n"+
		"\t              Dec : "+
		Convert.radiansTo180DMSString( dec )+"\n"+
		"\t Proper Motion RA : "+Convert.radiansTo24HMSString
		( properMotionRA )+"\n"+
		"\tProper Motion Dec : "+Convert.radiansTo180DMSString
		( properMotionDec )+"\n"+
		"\t  Radial Velocity : "+radialVelocity+"\n"+
		"\t         Parallax : "+parallax+"\n"+
		"\t          Equinox : "+equinox.getName()+"\n"+
		"\t            Epoch : "+epoch );

    }


    /**
     *
     */
    public Object clone()
    {
	cloneNumber++;
	Target t = new Target( name+".clone"+cloneNumber );
	t.setRA( ra );
	t.setDec( dec );
	t.setProperMotionRA( properMotionRA );
	t.setProperMotionDec( properMotionDec );
	t.setParallax( parallax );
	t.setRadialVelocity( radialVelocity );
	t.setEquinox( equinox );
	t.setEpoch( epoch );
	t.setOffsetRA( offsetRA );
	t.setOffsetDec( offsetDec );
	t.setNonSiderealTrackingRateRA( nonSiderealTrackingRateRA );
	t.setNonSiderealTrackingRateDec( nonSiderealTrackingRateDec );
	t.setNonSiderealTrackingStart( nonSiderealTrackingStart );
	return( (Object)t );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Target.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Target.java,v $
 *     $Log: not supported by cvs2svn $
 */
