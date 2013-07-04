package ngat.ngtcs.command;

/**
 * Offset the focus of the telescope by the amount specified.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class DFOCUS extends ngat.ngtcs.command.FOCUS
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
	new String( "$Id: DFOCUS.java,v 1.3 2013-07-04 10:06:44 cjm Exp $" );

    /**
     * Minimum offset (-30.00) to the current focus position in millimetres.
     */
    public static final double MIN_OFFSET = -30.00;

    /**
     * Maximum offset (+30.00) to the current focus position in millimetres.
     */
    public static final double MAX_OFFSET = +30.00;

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The offset to the focus position in millimetres.
     */
    protected double offset;

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
     * Create a DFOCUS command with the ID and focus offset (mm) specified.
     * @param s the String ID of this command
     * @param d the amopunt of offset to the current focus in millimetres
     */
    public DFOCUS( String s, double d ) throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange( d, MIN_OFFSET, MAX_OFFSET ) )
	    throw new IllegalArgumentException
		( "Focus offset "+d+" is outside the range "+MIN_OFFSET+" - "+
		  MAX_OFFSET );

	offset = d;
    }


    /**
     * Return the focus offset specified by this command
     * @return offset
     * @see #offset
     */
    public double getOffset()
    {
	return offset;
    }
}
/*
 *    $Date: 2013-07-04 10:06:44 $
 * $RCSfile: DFOCUS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/DFOCUS.java,v $
 *      $Id: DFOCUS.java,v 1.3 2013-07-04 10:06:44 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
