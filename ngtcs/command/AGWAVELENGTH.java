package ngat.ngtcs.command;

/**
 * This command sets the wavelength used for guiding by the autoguider.  If the
 * wavelength value is outside the range <code><b>MIN_WAVELENGTH</b></code> -
 * <code><b>MAX_WAVELENGTH</b></code> an IllegalArgumentException will be
 * thrown.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGWAVELENGTH extends ngat.ngtcs.command.Command
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
     * Minimum wavelength in metres.
     */
    public static final double MIN_WAVELENGTH = 3.000E-07;

    /**
     * Maximum wavelength in metres.
     */
    public static final double MAX_WAVELENGTH = 3.500E-05;

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The wavelength of light to be used by the autoguider (in micrometres),
     * set to the default of 550nm.
     */
    protected double wavelength = 5.5E-07;

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
     * Create an AGWAVELENGTH command with the specified command path and the
     * new autoguiding wavelength to set, in metres
     * @param s the command path (command ID)
     * @param d the new autoguiding wavelength, in metres
     */
    public AGWAVELENGTH( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange( d, MIN_WAVELENGTH,
					       MAX_WAVELENGTH ) )
	    throw new IllegalArgumentException
		( "Wavelength "+d+" is out of range "+MIN_WAVELENGTH+" - "+
		  MAX_WAVELENGTH );

	wavelength = d / 10E+06;
    }


    /**
     * Return the wavelength.
     * @return wavelength
     * @see #wavelength
     */
    public double getWavelength()
    {
	return wavelength;
    }
}
/*
 *    $Date: 2013-07-04 10:06:22 $
 * $RCSfile: AGWAVELENGTH.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGWAVELENGTH.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.2  2003/09/24 12:21:55  je
 *     Added documentation and changed internal units to metres.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
