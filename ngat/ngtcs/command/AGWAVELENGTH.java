package ngat.ngtcs.command;

/**
 * This command sets the wavelength used for guiding by the autoguider.  If the
 * wavelength value is outside the range <code><b>MIN_WAVELENGTH</b></code> -
 * <code><b>MAX_WAVELENGTH</b></code> an IllegalArgumentException will be
 * thrown.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGWAVELENGTH extends ngat.ngtcs.command.Command
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
	new String( "$Id: AGWAVELENGTH.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /**
     * Minimum wavelength in microns.
     */
    public static final double MIN_WAVELENGTH = 0.300;

    /**
     * Maximum wavelength in microns.
     */
    public static final double MAX_WAVELENGTH = 35.000;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The wavelength of light to be used by the autoguider, set to the default
     * of 550nm.
     */
    protected double wavelength = 5.5E-05;

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
     *
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
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: AGWAVELENGTH.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGWAVELENGTH.java,v $
 *      $Id: AGWAVELENGTH.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
