package ngat.ngtcs.command;

/**
 * This command will offset the current telescope target by the values
 * specified.  The value range checking will throw an IllegalArgumentException,
 * and the values at which it does so depend upon the OffsetMode specified.
 * <p>
 * In <code><b>OffsetMode.ARC<b></code> the minimum and maximum offsets are
 * &plusmn;<code>MAX_RA_OFFSET_ARC</code> (3600.00) in Right Ascension, and
 * &plusmn;<code>MAX_DEC_OFFSET_ARC</code> (3600.00) in Decliantion.
 * <br>
 * In <code><b>OffsetMode.TIME<b></code> the minimum and maximum offsets are
 * &plusmn;<code>MAX_RA_OFFSET_TIME</code> (240.00) in Right Ascension, and
 * &plusmn;<code>MAX_DEC_OFFSET_TIME</code> (3600.00) in Decliantion.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class OFFBY extends ngat.ngtcs.command.Command
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
	new String( "$Id: OFFBY.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Mode to offset in, defaulted to <code>OffsetMode.ARC</code>.
     */
    protected OffsetMode offsetMode = OffsetMode.ARC;

    /**
     * Offset in Right Ascension.
     */
    protected double ra;

    /**
     * Offset in Declination.
     */
    protected double dec;

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Minimum Right Ascension offset, in seconds (240.00).
     */
    public static final double MAX_RA_OFFSET_TIME = 240.000;

    /**
     * Maximum Declination offset, in arcseconds (3600.00).
     */
    public static final double MAX_DEC_OFFSET_TIME = 3600.000;

    /**
     * Maximum Right Ascension offset, in arcseconds (3600.00).
     */
    public static final double MAX_RA_OFFSET_ARC = 3600.000;

    /**
     * Maximum Declination offset, in arcseconds (3600.00).
     */
    public static final double MAX_DEC_OFFSET_ARC = 3600.000;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Create an OFFBY command with the specified ID, using the specified
     * OffsetMode and the two values for offsetting in Right Ascension and
     * Declination.
     * @param s the String ID of this command
     * @param o the OffsetMode to use for offsetting
     * @param d1 the offset value for Right Ascension
     * @param d2 the offset value for Declination
     */
    public OFFBY( String s, OffsetMode o, double d1, double d2 )
	throws IllegalArgumentException
    {
	super( s );

	offsetMode = o;
	if( offsetMode == OffsetMode.TIME )
	    if( ngat.ngtcs.common.Util.outOfRange
		( d1, MAX_RA_OFFSET_TIME, -MAX_RA_OFFSET_TIME ) )
		throw new IllegalArgumentException
		    ( "Offset "+d1+" is outside the range "+
		      MAX_RA_OFFSET_TIME+" - "+(-MAX_RA_OFFSET_TIME )+
		      " for OffsetMode "+offsetMode.getName() ) ;
	    else if( ngat.ngtcs.common.Util.outOfRange
		     ( d2, MAX_DEC_OFFSET_TIME, -MAX_DEC_OFFSET_TIME ) )
		throw new IllegalArgumentException
		    ( "Offset "+d2+" is outside the range "+
		      MAX_DEC_OFFSET_TIME+" - "+(-MAX_DEC_OFFSET_TIME )+
		      " for OffsetMode "+offsetMode.getName() ) ;
	else if( offsetMode == OffsetMode.ARC )
	    if( ngat.ngtcs.common.Util.outOfRange
		( d1, MAX_RA_OFFSET_ARC, -MAX_RA_OFFSET_ARC ) )
		throw new IllegalArgumentException
		    ( "Offset "+d1+" is outside the range "+
		      MAX_RA_OFFSET_ARC+" - "+(-MAX_RA_OFFSET_ARC )+
		      " for OffsetMode "+offsetMode.getName() ) ;
	    else if( ngat.ngtcs.common.Util.outOfRange
		     ( d2, MAX_DEC_OFFSET_ARC, -MAX_DEC_OFFSET_ARC ) )
		throw new IllegalArgumentException
		    ( "Offset "+d2+" is outside the range "+
		      MAX_DEC_OFFSET_ARC+" - "+(-MAX_DEC_OFFSET_ARC )+
		      " for OffsetMode "+offsetMode.getName() ) ;

	ra = d1;
	dec = d2;
    }

    /**
     * Return the OffsetMode to be used by this command.
     * @return offsetMode
     * @see #offsetMode
     */
    public OffsetMode getMode()
    {
	return offsetMode;
    }


    /**
     * Return the offset in Right Ascension.
     * <p>
     * <b>NOTE:</b> The return value will be in seconds for an offset mode of
     * <code><b>OffsetMode.TIME</b></code>, and in arcseconds for
     * <code><b>OffsetMode.ARC</b></code>.
     * @return ra
     * @see #ra
     */
    public double getRightAscensionOffset()
    {
	return ra;
    }


    /**
     * Return the offset in Declination, in arcseconds.
     * @return dec
     * @see #dec
     */
    public double getDeclinationOffset()
    {
	return dec;
    }
}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: OFFBY.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/OFFBY.java,v $
 *      $Id: OFFBY.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
