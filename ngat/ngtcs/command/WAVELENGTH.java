package ngat.ngtcs.command;

/**
 * This command sets the wavelength used in the refraction calculations in the
 * astrometry routines.  The wavelength is set in metres, and this command
 * will throw an IllegalArgumentException if the argument is outside
 * the range <code><b>MIN_WAVELENGTH</b></code> (250 nm) to 
 * <code><b>MAX_WAVELENGTH</b></code> (1.0 m)
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class WAVELENGTH extends Command
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
	new String( "$Id: WAVELENGTH.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /**
     * The minimum allowable value for the wavelength in metres (2.5E-07)
     */
    public static final double MIN_WAVELENGTH = 2.50E-07;

    /**
     * The maximum allowable value for the wavelength (1.0)
     */
    public static final double MAX_WAVELENGTH = 1.00;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The wavelength in metres.
     */
    protected double wavelength;

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
     * Create a WAVELENGTH command with the specified ID and wavelength to set
     * in metres.
     * @param s the String ID of this command
     * @param d the wavelength to set in metres
     */
    public WAVELENGTH( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( d, MIN_WAVELENGTH, MAX_WAVELENGTH ) )
	    throw new IllegalArgumentException
		( "Wavelength "+d+" is outside the range "+MIN_WAVELENGTH+
		  " - "+MAX_WAVELENGTH );

	wavelength = d;
    }


    /**
     * Return the wavelength, in metres.
     * @return wavelength
     * @see #wavelength
     */
    public double getWavelength()
    {
	return wavelength;
    }

}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: WAVELENGTH.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/WAVELENGTH.java,v $
 *      $Id: WAVELENGTH.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
