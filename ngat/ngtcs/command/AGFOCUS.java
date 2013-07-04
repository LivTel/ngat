package ngat.ngtcs.command;

/**
 * This command sets the autoguide focus position to that specified in the
 * constructor.  The allowable range is from
 * <code><b>MIN_FOCUS_POSITION</b></code> (0.00mm) to
 * <code><b>MAX_FOCUS_POSITION</b></code>.  (25.00mm) If the argument is
 * outside this range an IllegalArgumentException will be thrown.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class AGFOCUS extends ngat.ngtcs.command.Command
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
	new String( "$Id: AGFOCUS.java,v 1.3 2013-07-04 10:06:10 cjm Exp $" );

    /**
     * Maximum position (25.00) along the autoguider focus in millimetres.
     */
    public static final double MAX_FOCUS_POSITION = 25.000;

    /**
     * Minimum position (0.00) along the autoguider focus in millimetres.
     */
    public static final double MIN_FOCUS_POSITION = 0.000;

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * Position along the autoguider focus in millimetres.
     */
    protected double position = 0;

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
     * This constructor will throw an IllegalArgumentException if the
     * specified position argument is outside the range described by
     * <code><b>MIN_FOCUS_POSITION</b></code> and
     * <code><b>MAX_FOCUS_POSITION</b></code>.
     * @param s the String ID of this command
     * @param d the focus position for the autoguider (in millimetres)
     */
    public AGFOCUS( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange( d, MIN_FOCUS_POSITION,
						 MAX_FOCUS_POSITION ) )
	    throw new IllegalArgumentException
		( "position "+d+" is outside range "+MIN_FOCUS_POSITION+" - "+
		  MAX_FOCUS_POSITION );

	position = d;
    }


    /**
     * Return the focus position in microns.
     * @return position
     * @see #position
     */
    public double getPosition()
    {
	return position;
    }
}
/*
 *    $Date: 2013-07-04 10:06:10 $
 * $RCSfile: AGFOCUS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGFOCUS.java,v $
 *      $Id: AGFOCUS.java,v 1.3 2013-07-04 10:06:10 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
