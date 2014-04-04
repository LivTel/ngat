package ngat.ngtcs.command;

/**
 * This command sets the temperature to be used in the refraction calculation
 * used in the astrometry routines.  The temperature <b>MUST</b> be in Kelvin
 * and inside the range <code><b>MIN_TEMPERATURE</b></code> (170.00) to
 * <code><b>MAX_TEMPERATURE</b></code> (320.00).
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TEMPERATURE extends Command
{
    /*=========================================================================*/
    /*                                                                         */
    /* CLASS FIELDS.                                                           */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     * Minimum temperature, 170K (-100&deg;C).
     */
    public static final double MIN_TEMPERATURE = 170.00;

    /**
     * Maximum temperature, 320K (50&deg;C).
     */
    public static final double MAX_TEMPERATURE = 320.00;

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * Temperature to set, in Kelvin.
     */
    protected double temperature;

    /*=========================================================================*/
    /*                                                                         */
    /* CLASS METHODS.                                                          */
    /*                                                                         */
    /*=========================================================================*/


    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT METHODS.                                                         */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * Create a TEMPERATURE command with the specified ID and temperature (in
     * Kelvin).  An IllegalArgumentException will be thrown if the temperature
     * argument is outside the range <code><b>MIN_TEMPERATURE</b></code> to
     * <code><b>MAX_TEMPERATURE</b></code>.
     * @param s the String ID of this command
     * @param d the temperature to set, in Kelvin
     */
    public TEMPERATURE( String s, double d )
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( d, MIN_TEMPERATURE, MAX_TEMPERATURE ) )
	    throw new IllegalArgumentException
		( "Temperature "+d+" is outside range "+MIN_TEMPERATURE+" - "+
		  MAX_TEMPERATURE );

	temperature = d;
    }


    /**
     * Return the temperature to be set, in Kelvin.
     * @return temperature
     * @see #temperature
     */
    public double getKelvin()
    {
	return temperature;
    }
}
/*
 *    $Date: 2013-07-04 10:08:28 $
 * $RCSfile: TEMPERATURE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/TEMPERATURE.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
