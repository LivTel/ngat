package ngat.ngtcs.command;

/**
 * This command sets the humidity used in the refraction calculations in the
 * astrometry routines.
 * <p>
 * The humidity is given as a percentile from 0.0 to 1.0.
 *
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class HUMIDITY extends Command
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
	new String( "$Id: HUMIDITY.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The humidity to set as a percentage.
     */
    protected double humidity;

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The minimum humidity (0.0).
     */
    public static final double MIN_HUMIDITY = 0.0000;

    /**
     * The maximum humidity (1.0).
     */
    public static final double MAX_HUMIDITY = 1.0000;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Create the HUMIDITY command with the specified ID and humidity value.
     * <p>
     * <b>NOTE:</b>
     * <p>
     * The humidity must be between MIN_HUMIDTY and MAX_HUMIDITY inclusive
     * @see #MIN_HUMIDITY
     * @see #MAX_HUMIDITY
     * @param s the String ID of this command
     * @param d the humidity to set
     */
    public HUMIDITY( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( d, MIN_HUMIDITY, MAX_HUMIDITY ) )
	    throw new IllegalArgumentException
		( "Humidity "+d+" is outside the range "+MIN_HUMIDITY+" - "+
		  MAX_HUMIDITY );

	humidity = d;
    }


    /**
     * Return the humidity to be set.
     * @return humidity
     * @see #humidity
     */
    public double getHumidity()
    {
	return humidity;
    }
}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: HUMIDITY.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/HUMIDITY.java,v $
 *      $Id: HUMIDITY.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
