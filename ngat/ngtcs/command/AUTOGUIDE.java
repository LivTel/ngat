package ngat.ngtcs.command;

import ngat.ngtcs.common.*;

/**
 * Set the <code>AutoguideMode</code> and relevant parameters to autoguide.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class AUTOGUIDE extends ngat.ngtcs.command.Command
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: AUTOGUIDE.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /**
     * The brightest magnitude of star to guide on.
     */
    public static double BRIGHTEST_MAGNITUDE = 0.0;

    /**
     * The fainttest magnitude of star to guide on.
     */
    public static double FAINTEST_MAGNITUDE = 20.0;

    /**
     * The rank of the brightest star to guide on.
     */
    public static int BRIGHTEST_RANK = 0;

    /**
     * The rank of the faintest star to guide on.
     */
    public static int FAINTEST_RANK = 10;

    /**
     * The lowest pixel coordinate to guide on.
     */
    public static double LOWEST_PIXEL = 0.0;

    /**
     * The highest pixel coordinate to guide on.
     */
    public static double HIGHEST_PIXEL = 1023.0;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Description of whether this command is to start autoguiding (as opposed
     * to stopping it).
     */
    protected boolean autoguide = false;

    /**
     * Mode used for guiding.  The default is BRIGHTEST.
     */
    protected AutoguideMode autoguideMode = AutoguideMode.BRIGHTEST;

    /**
     * Minimum magnitude of the guide star in milliMagnitudes
     */
    protected double faintestMagnitude = 10000;

    /**
     * Maximum magnitude of the guide star in milliMagnitudes
     */
    protected double brightestMagnitude = 0;

    /**
     * Rank in brightness of the guide star.
     */
    protected int brightnessRank = 0;

    /**
     * X pixel of star to guide on.
     */
    protected double xPixel = 0.0;

    /**
     * Y pixel of star to guide on.
     */
    protected double yPixel = 0.0;

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
     * This constructor will autoguide using the
     * <code><b>PluggabelSubSystem</b></code>-implementing autoguider
     * referenced by the specified name, using the default mode of autoguiding
     * (BRIGHTEST).
     * @param s the String ID of this command
     */
    public AUTOGUIDE( String s )
	throws IllegalArgumentException
    {
	super( s );
    }


    /**
     *
     *
     * @param s the String ID of this command
     */
    public AUTOGUIDE( String s, 
		      boolean on,
		      AutoguideMode newAutoguideMode,
		      double newBrightestMagnitude,
		      double newFaintestMagnitude,
		      int newBrightnessRank,
		      double newXPixel,
		      double newYPixel )
	throws IllegalArgumentException
    {
	super( s );
	autoguide = on;

	if( newAutoguideMode == null )
	    throw new IllegalArgumentException
		( "AutoguideMode argument = null" );
	autoguideMode = newAutoguideMode;

	if( ngat.ngtcs.common.Util.outOfRange
	    ( newBrightestMagnitude,
	      BRIGHTEST_MAGNITUDE, FAINTEST_MAGNITUDE ) )
	    throw new IllegalArgumentException
		( "brightest magnitude "+newBrightestMagnitude+" is outside "+
		  "the range "+FAINTEST_MAGNITUDE+" - "+BRIGHTEST_MAGNITUDE );
	brightestMagnitude = newBrightestMagnitude;

	if( ngat.ngtcs.common.Util.outOfRange
	    ( newFaintestMagnitude,
	      BRIGHTEST_MAGNITUDE, FAINTEST_MAGNITUDE ) )
	    throw new IllegalArgumentException
		( "faintest magnitude "+newFaintestMagnitude+" is outside "+
		  "the range "+FAINTEST_MAGNITUDE+" - "+BRIGHTEST_MAGNITUDE );
	faintestMagnitude = newFaintestMagnitude;

	if( faintestMagnitude <= brightestMagnitude )
	    throw new IllegalArgumentException
		( "faintest magnitude ("+faintestMagnitude+") is brighter "+
		  "than the brightest magnitude ("+brightestMagnitude+")" );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( newBrightnessRank, BRIGHTEST_RANK, FAINTEST_RANK ) )
	    throw new IllegalArgumentException
		( "brightness rank "+newBrightnessRank+
		  " is outside the range "+
		  FAINTEST_RANK+" - "+BRIGHTEST_RANK );
	brightnessRank = newBrightnessRank;

	if( ngat.ngtcs.common.Util.outOfRange
	    ( newXPixel, LOWEST_PIXEL, HIGHEST_PIXEL ) )
	    throw new IllegalArgumentException
		( "X pixel "+newXPixel+" is outside the range "+LOWEST_PIXEL+
		  " - "+HIGHEST_PIXEL);
	xPixel = newXPixel;

	if( ngat.ngtcs.common.Util.outOfRange
	    ( newYPixel, LOWEST_PIXEL, HIGHEST_PIXEL ) )
	    throw new IllegalArgumentException
		( "Y pixel "+newYPixel+" is outside the range "+LOWEST_PIXEL+
		  " - "+HIGHEST_PIXEL);
	yPixel = newYPixel;
    }


    /**
     *
     */
    public AutoguideMode getAutoguideMode()
    {
	return autoguideMode;
    }


    /**
     *
     */
    public double getBrightestMagnitude()
    {
	return brightestMagnitude;
    }


    /**
     *
     */
    public double getFaintestMagnitude()
    {
	return faintestMagnitude;
    }


    /**
     *
     */
    public int getBrightnessRank()
    {
	return brightnessRank;
    }


    /**
     *
     */
    public double getXPixel()
    {
	return xPixel;
    }


    /**
     *
     */
    public double getYPixel()
    {
	return yPixel;
    }


    /**
     *
     */
    public String getHelp()
    {
	return( "AUTOGUIDE diddly diddly" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "" );
    }
}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: AUTOGUIDE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AUTOGUIDE.java,v $
 *      $Id: AUTOGUIDE.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
