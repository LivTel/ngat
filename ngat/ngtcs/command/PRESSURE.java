package ngat.ngtcs.command;

/**
 * This command sets the pressure used in the refraction calculations in the
 * astrometry routines.  The pressure is set in millibars, and this command
 * will throw an IllegalArgumentException if the pressure argument is outside
 * the range <code><b>MIN_PRESSURE</b></code> (0.0 mB) to 
 * <code><b>MAX_PRESSURE</b></code> (1030.0 mB)
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class PRESSURE extends Command
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
	new String( "$Id: PRESSURE.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /**
     * The minimum allowable value for the pressure (0.00)
     */
    public static final double MIN_PRESSURE = 0.00;

    /**
     * The maximum allowable value for the pressure (1030.00)
     */
    public static final double MAX_PRESSURE = 1030.00;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The pressure in millibars.
     */
    protected double pressure;

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
     * Create a PRESSURE command with the specified ID and pressure to set
     * in millibars.
     * @param s the String ID of this command
     * @param d the pressure to set in millibars
     */
    public PRESSURE( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( d, MIN_PRESSURE, MAX_PRESSURE ) )
	    throw new IllegalArgumentException
		( "Pressure "+d+" is outside the range "+MIN_PRESSURE+" - "+
		  MAX_PRESSURE );

	pressure = d;
    }


    /**
     * Return the pressure to set, in millibars.
     * @return pressure
     * @see #pressure
     */
    public double getPressure()
    {
	return pressure;
    }

}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: PRESSURE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/PRESSURE.java,v $
 *      $Id: PRESSURE.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */


