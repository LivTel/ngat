package ngat.ngtcs.subsystem;
 
import java.util.*;

import ngat.util.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class Mount extends BasicMechanism
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Mount.java,v 1.1 2003-07-01 10:13:46 je Exp $" );


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    private MountState mountState = null;

    /**
     * Previous timestamp of previous mount position.
     */
    protected Timestamp oldMountTimestamp = 
	new Timestamp( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );

    /**
     * Previous position of Mount.
     */
    protected XYZMatrix oldMountPosition = new XYZMatrix();

    /**
     * Current timestamp.
     */
    protected Timestamp currentMountTimestamp =
	new Timestamp( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );

    /**
     * Current position of Mount.
     */
    protected XYZMatrix currentMountPosition = new XYZMatrix();

    /**
     * Timer for this Mount's Telescope.
     */
    protected Timer timer;

    /**
     * Pointing Model for this Mount.
     */
    protected PointingModel pointingModel;

    /**
     * AstrometryCalculator used by the Telescope for which this is the Mount.
     */
    protected AstrometryCalculator astroCalc;

    /**
     * Lower limit of Telescope operation in radians.
     */
    protected final double LOWER_LIMIT = Math.toRadians( 20.0 );

    /**
     * Upper limit of Telescope operation in radians (blind spot).
     */
    protected final double UPPER_LIMIT = Math.toRadians( 88.0 );

    /**
     * Flag to represent enter state of Limit.
     */
    protected final boolean enter = false;

    /**
     * Flag to represent exit state of Limit.
     */
    protected final boolean exit = true;


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Constructor for Mount objects.
     */
    public Mount()
    {
	super();
    }


    /**
     * Initialise the Mount for operation.
     */
    public void initialise( Telescope t ) throws InitialisationException
    {
	super.initialise( t );

	getProperties();
	String pmName = np.getProperty( "pointingModel.class" );
	if( pmName == null )
	    {
		InitialisationException ie = new InitialisationException
		    ( "Mount : No specified pointingModel class" );
		logger.log( 1, logName, ie );
		throw ie;
	    }

	try
	    {
		pointingModel = (PointingModel)
		    ( Class.forName( pmName ).newInstance() );
		pointingModel.initialise( telescope );
	    }
	catch( Exception e )
	    {
		InitialisationException ie = 
		    new InitialisationException( "Mount : "+e );
		logger.log( 1, logName, ie );
		throw ie;
	    }
	logger.log( 1, logName, "Pointing Model [ "+pmName+" ] initialised" );

	//Make hardware connection to MCS etc...
    }


    /**
     * Activate the Mount as per the PowerController interface.
     */
    public void activate()
    {
	super.activate();
	// do stuff
    }


    /**
     * De-activate the Mount as per the PowerController interface.
     */
    public void deActivate()
    {
	// do hardware stuff...
	super.deActivate();
    }


    /**
     * Make the Mount safe in preparation for a shutdown or power cut.
     */
    public boolean makeSafe()
    {
	// do hardware stuff - remembering state...
	return super.makeSafe();
    }


    /**
     * Return the status of this Mount as per the Monitor interface.
     * @return status of this Mount
     */
    public Status getStatus()
    {
	return( Status )( new MountStatus( softwareState, 
					   mountState,
					   currentMountTimestamp, 
					   currentMountPosition ) );
    }


    /**
     * Send the specified position demand to the Mount Control System.
     * @param timePos the Timestamped position demand
     */
    public void sendPositionDemand( TimestampedPosition currentTP )
    {
	oldMountTimestamp     = currentMountTimestamp;
	oldMountPosition      = currentMountPosition;
	currentMountTimestamp = currentTP.getTimestamp(); 
	currentMountPosition  = currentTP.getPosition();
    }


    /**
     * Retrieve the current position of the Mount from the Mount Control 
     * System.
     * @return the latest Timestamped position from the Mount Control System
     */
    public TimestampedPosition getTimestampedPosition()
    {
	return new TimestampedPosition
	    ( currentMountPosition, currentMountTimestamp );
    }


    /**
     * Returns the current position
     * @return the current position
     */
    public XYZMatrix getCurrentPosition()
    {
	return currentMountPosition;
    }


    /**
     *
     */
    public XYZMatrix predictPosition( Timestamp tStamp )
    {
	// if current timestamp older than X
	// get update for pos rot and T

	double oldTime = oldMountTimestamp.getTime();

	double x1 = oldMountPosition.getX();
	double y1 = oldMountPosition.getY();
	double z1 = oldMountPosition.getZ();
	double xDiff = currentMountPosition.getX() - x1;
	double yDiff = currentMountPosition.getY() - y1;
	double zDiff = currentMountPosition.getZ() - z1;

	double timeDiff = currentMountTimestamp.getTime() - oldTime;
	double timeCoeff = ( tStamp.getTime() - oldTime ) / timeDiff;

	XYZMatrix predicted = new XYZMatrix
	    ( ( x1 + xDiff * timeCoeff ),
	      ( y1 + yDiff * timeCoeff ),
	      ( z1 + zDiff * timeCoeff ) );

	predicted.normalise();

	return predicted;
    }


    /**
     *
     *
     */
    public abstract List calculateLimits( SiteData siteData, Target target );
    /*
    {
	// RUBBISH !!!

	Vector limits = new Vector();

	limits.add( calcAltLimit( siteData, target, LOWER_LIMIT, enter ) );
	limits.add( calcAltLimit( siteData, target, LOWER_LIMIT, exit  ) );
	limits.add( calcAltLimit( siteData, target, UPPER_LIMIT, enter ) );
	limits.add( calcAltLimit( siteData, target, UPPER_LIMIT, exit  ) );

	return limits;
    }
    */

    /**
     * 
     */
    /*
    protected Limit calcAltLimit( SiteData siteData, Target target, 
				  double altitudeLimit, boolean state )
    {		       
	Timestamp utcLimit, utcGuess;
	double    lst, lstNow, lstDiff, lstGuess, timeDiff;
	double    acosLST, cosZD, x, y, z;
	XYZMatrix appPos;
	double    lat    = siteData.getLatitude();
	double    cosLat = Math.cos( lat );
	double    sinLat = Math.sin( lat );

	utcLimit = timer.getTime();
	cosZD = Math.cos( LOWER_LIMIT );
	do
	    {
		utcGuess = utcLimit;

		appPos = astroCalc.calcApparentPosition( utcGuess, target );

		x = appPos.getX();
		y = appPos.getY();
		z = appPos.getZ();

		acosLST = Math.sqrt( ( (cosZD - sinLat*z)*(cosZD - sinLat*z) -
				       (y*y*cosLat*cosLat ) ) /
				     ( x*x*cosLat*cosLat - 
				       y*y*cosLat*cosLat ) );

		if( ( acosLST > 1.0 )||( acosLST < -1.0 ) )
		    {		      
			return null;
		    }

		lst = Math.acos( acosLST );

		//calculate UTC from LST
		lstGuess = astroCalc.getLST( utcGuess );

		if( state )
		    {
			lstDiff = lst - lstGuess;
		    }
		else
		    {
			lstDiff = lstGuess - lst;
		    }

		timeDiff = ( lstDiff * astroCalc.RADS2SECS * 
			     astroCalc.SIDEREAL2ATOMIC );

		utcLimit = new Timestamp( utcGuess.getSeconds() - 
					  (int)timeDiff, 0,
					  CalendarType.GREGORIAN,
					  TimescaleType.UTC );

	    }
	while ( utcLimit.getSeconds() != utcGuess.getSeconds() );

	return new Limit( new Timestamp ( utcLimit.getSeconds(), 0,
					  CalendarType.GREGORIAN,
					  TimescaleType.UTC ), state );
    }
    */


    /**
     * Return the PointingModel object used with this Mount.
     * @return the PointingModel for this mount.
     */
    public PointingModel getPointingModel()
    {
	return pointingModel;
    }


    /**
     * Return the type of Mount that this is.
     * @return one of the pre-defined MountTypes
     */
    public abstract MountType getType();

}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: Mount.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Mount.java,v $
 *     $Log: not supported by cvs2svn $
 */
