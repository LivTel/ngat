package ngat.ngtcs.command;

import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * The response to the SHOW command, containing the information requested by
 * the SHOW command specified in the constructor.  For information requested
 * from this object that was not specified by the original SHOW command, or not
 * applicabel due to the Telescope's current mode of operation, the returned
 * values will be zero or null, depending upon the return type.  This
 * <b>MUST</b> be checked for in the control process that requested the data
 * with the SHOW command.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SHOWDone extends CommandDone
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
	new String( "$Id: SHOWDone.java,v 1.1 2003-07-01 10:12:39 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The object that hold all the Meteorological data used in the astrometry
     * transformations.
     */
    protected MeteorologicalData metData = null;

    /**
     * The object that hold all the International Earth Rotation Service data
     * used in the astrometry transformations.
     */
    protected IERSData iersData = null;

    /**
     * The PointingModel object being used on the Mount.
     */
    protected PointingModel pointingModel = null;

    /**
     * The FocalStation defining the current acticve VirtualTelescope.
     */
    protected FocalStation focalStation = null;

    /**
     * A list of Limit objects that will prevent tracking.
     */
    protected List limitList = null;

    /**
     * A list of the requested mechanism status.
     */
    protected List statusList = null;

    /**
     * The currently set target on the Telescope.
     */
    protected Target target = null;

    /**
     * A list of the requested systems states.
     */
    protected List stateList = null;

    /**
     * A parsed Time object filled with various representations of the
     * current TCS time.
     */
    protected Time time = null;

    /**
     * A String representing the version ID of the TCS.
     */
    protected String versionString = null;

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
     * Initialise the reply to the specified SHOW command containing the
     * <code><b>true</b></code> successful flag and requested data or a
     * <code><b>false</b></code> successful flag.
     * @param s the SHOW command to which this is a reply
     */
    public SHOWDone( SHOW s )
    {
        super( (Command)s );
    }


    /**
     * Method to set the returned MeteorologicalData object being used in the
     * astrometric transformations.
     * @param md the returned MeteorologicalData
     * @see #metData
     */
    public void setMeteorologicalData( MeteorologicalData md )
    {
        metData = md;
    }


    /**
     * Method to set the returned IERSData object being used in the
     * astrometric transformations.
     * @param id the returned IERSData
     * @see #iersData
     */
    public void setIERSData( IERSData id )
    {
        iersData = id;
    }


    /**
     * Method to set the returned PointingModel object being used by the
     * Mount. 
     * @param pm the returned PointingModel
     * @see #pointingModel
     */
    public void setPointingModel( PointingModel pm )
    {
	pointingModel = pm;
    }


    /**
     * Method to set the returned FocalStation object for the current active
     * VirtualTelescope.
     * @param fs the returned FocalStation
     * @see #focalStation
     */
    public void setFocalStation( FocalStation fs )
    {
	focalStation = fs;
    }


    /**
     * Method to set the returned Limits object.
     * <p>
     * <b>NOTE:</b><br>
     * This object will return <code>null</code> if the telescope state is
     * neither TRACKING or SLEWING.
     * @param l a list of Limit objects that will prevent tracking
     * @see #limitList
     */
    public void setLimits( List l )
    {
	limitList = l;
    }


    /**
     * Method to set the returned mechanism status for all mechanisms.
     * @param l a list of the requested status
     * @see #statusList
     */
    public void setStatus( List l )
    {
	statusList = l;
    }


    /**
     * Method to set the returned Target that is currently set on the
     * telescope.
     * @param t the returned Target
     * @see #target
     */
    public void setTarget( Target t )
    {
	target = t;
    }


    /**
     * Method to set the various States of the Telescope soft/hardware systems
     * and current mode of operation.
     * @param l a list of the requested states
     * @see #stateList
     */
    public void setState( List l )
    {
	stateList = l;
    }


    /**
     * Method to set the returned Time object.
     * @param t the current time, packed into an object 
     * @see #time
     */
    public void setTime( Time t )
    {
	time = t;
    }


    /**
     * Method to set the Version string
     * @param versionString
     * @see #versionString
     */
    public void setVersionString( String s )
    {
        versionString = s;
    }
}
/*
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: SHOWDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOWDone.java,v $
 *      $Id: SHOWDone.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */
