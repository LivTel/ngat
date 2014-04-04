package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class IERSData extends Data
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String revisionString =
	new String( "$Id$" );

    /**
     * Current differernce between Universal Time (UT1) and Coordinated
     * Universal Time (UTC) in seconds.
     */
    protected double UT1_UTC = 0.0;

    /**
     * Current differernce between Universal Time (UT1) and Terrestrial
     * Time (TT) in seconds.
     */
    protected double TT_UT1 = 0.0;

    /**
     * Current difference between Coordinated Universal Time (UTC) and 
     * International Atomic Time (TAI).  This value is read from a config 
     * script and should be updated according to leap-seconds.
     */
    protected long TAI_UTC = 0;

    /**
     * Current position of the diurnal rotation axis in arcseconds along the
     * zero dergees longitude meridian.
     */
    protected double polarMotionX = 0.0;

    /**
     * Current position of the diurnal rotation axis in arcseconds along the
     * 270 dergees longitude meridian.
     */
    protected double polarMotionY = 0.0;

    /**
     * Current number of leapseconds ADDED since 01/01/1970
     */
    protected int leapseconds = 0;

    /**
     * Maximum acceptable polar motion input (10).
     */
    protected final double MAX_POLAR_MOTION = 10.0;

    /**
     * Minimum acceptable polar motion input (10).
     */
    protected final double MIN_POLAR_MOTION = -10.0;


    /**
     * Empty constructor
     */
    public IERSData()
    {

    }


    /**
     * newPolarMotionX and newPolarMotionY are input in arcseconds, but
     * converted and stored in radians.
     */
    public IERSData( double newUT1_UTC, double newTT_UT1,
		     long newTAI_UTC, double newPolarMotionX,
		     double newPolarMotionY ) throws IllegalArgumentException
    {
	setUT1_UTC( newUT1_UTC );
	setTT_UT1( newTT_UT1 );
	setTAI_UTC( newTAI_UTC );
	setPolarMotionX( newPolarMotionX );
	setPolarMotionY( newPolarMotionY );
    }

    /**
     * Set a new value for the UT1-UTC time difference.
     * @param newUT1_UTC the new time difference
     */
    public void setUT1_UTC( double newUT1_UTC )
    throws IllegalArgumentException
    {
	if( !isValid( newUT1_UTC, -1.0, 1.0 ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newUT1_UTC+"] out of range for UT1-UTC : "+
		      "-1.0 .. 1.0" );
	    }
	UT1_UTC = newUT1_UTC;
    }

    /**
     * Retrieve the current UT1 - UTC value in seconds.
     * @return UT1_UTC
     * @see #UT1_UTC
     */
    public double getUT1_UTC()
    {
	return UT1_UTC;
    }

    /**
     * Set a new value for the TT-UT1 time difference.
     * @param newTT_UT1 the new time difference
     * @see #TT_UT1
     */
    public void setTT_UT1( double newTT_UT1 )
    {
	/*
	if( !isValid( newTT_UT1, -10.0, 10.0 )
	{
	    throw new IllegalArgumentException
		( "value ["+newTT_UT1+"] out of range for TT-UT1 : "+
		  "-10.0 .. 10.0" );
	}
	*/
	TT_UT1 = newTT_UT1;
    }

    /**
     * Retrieve the current TT-UT1 value in seconds.
     * @return TT_UT1
     * @see #TT_UT1
     */
    public double getTT_UT1()
    {
	return TT_UT1;
    }

    /**
     * Set a new value for the TAI-UTC time difference.
     * @param newTAI_UTC the new time difference
     * @see #TAI_UTC
     */
    public void setTAI_UTC( long newTAI_UTC )
    {
	/*
	if( !isValid( newTAI_UTC, -10.0, 10.0 )
	{
	    throw new IllegalArgumentException
		( "value ["+newTAI_UTC+"] out of range for TAI-UTC : "+
		  "-10.0 .. 10.0" );
	}
	*/
	TAI_UTC = newTAI_UTC;
    }

    /**
     * Retrieve the current value for the TAI-UTC time difference.
     * @return TAI_UTC
     * @see #TAI_UTC
     */
    public long getTAI_UTC()
    {
	return TAI_UTC;
    }

    /**
     * Set a new value for the position of the diurnal rotation axis X 
     * component, in arcseconds.
     * @param newPolarMotionX the new position of the rotation axis
     * @see #polarMotionX
     */
    public void setPolarMotionX( double newPolarMotionX )
    throws IllegalArgumentException
    {
	if( !isValid( newPolarMotionX, MIN_POLAR_MOTION, MAX_POLAR_MOTION ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newPolarMotionX+
		      "] out of range for polar motion (Y) : "+
		      MIN_POLAR_MOTION+" .. "+MAX_POLAR_MOTION );
	    }
	polarMotionX = newPolarMotionX;
    }


    /**
     * Retrieve the current position of the diurnal rotation axis X component,
     * in arcseconds.
     * @return polarMotionX
     * @see #polarMotionX
     */
    public double getPolarMotionX()
    {
	return( polarMotionX );
    }


    /**
     * Retrieve the current position of the diurnal rotation axis X component,
     * in radians.
     * @return polarMotionX
     * @see #polarMotionX
     */
    public double getPolarMotionXRadians()
    {
	return( Math.toRadians( polarMotionX / 3600.0 ) );
    }


    /**
     * Set a new value for the position of the diurnal rotation axis Y 
     * component, in arcseconds.
     * @param newPolarMotionY the new position of the rotation axis
     * @see #polarMotionY
     */
    public void setPolarMotionY( double newPolarMotionY )
    throws IllegalArgumentException
    {
	if( !isValid( newPolarMotionY, MIN_POLAR_MOTION, MAX_POLAR_MOTION ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newPolarMotionY+
		      "] out of range for polar motion (Y) : "+
		      MIN_POLAR_MOTION+" .. "+MAX_POLAR_MOTION );
	    }
	polarMotionY = newPolarMotionY;
    }


    /**
     * Retrieve the current position of the diurnal rotation axis Y component,
     * in arcseconds
     * @return polarMotionY
     * @see #polarMotionY
     */
    public double getPolarMotionY()
    {
	return( polarMotionY );
    }


    /**
     * Retrieve the current position of the diurnal rotation axis Y component,
     * in radians
     * @return polarMotionY
     * @see #polarMotionY
     */
    public double getPolarMotionYRadians()
    {
	return( Math.toRadians( polarMotionY / 3600.0 ) );
    }


    /**
     * Set the value for current number leapseconds ADDED since 01/01/1970
     * @param newLeapseconds
     * @see #leapseconds
     */
    public void setLeapseconds( int newLeapseconds )
    {
	/*
	if( !isValid( newLeapseconds, -100.0, 100.0 )
	{
	    throw new IllegalArgumentException
		( "value ["+newLeapseconds+"] out of range for Leapseconds : "+
		  "-100.0 .. 100.0" );
	}
	*/

	leapseconds = newLeapseconds;
    }

    /**
     * Retrieve the current number of leapseconds ADDED since 01/01/1970
     * @return leapseconds
     * @see #leapseconds
     */
    public int getLeapseconds()
    {
	return leapseconds;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: IERSData.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/IERSData.java,v $
 *     $Log: not supported by cvs2svn $
 */
