package ngat.ngtcs.command;

/**
 * This command moves the autoguider probe mirror to the radius specified in
 * the constructor.  The range of movement is from
 * <code><b>MIN_RADIUS</b></code> (0.00mm) to <code><b>MIN_RADIUS</b></code>
 * (110.00mm).  If the radius is outside this position an
 * IllegalArgumentException will be thrown.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class AGRADIAL extends ngat.ngtcs.command.Command
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
	new String( "$Id: AGRADIAL.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /**
     * Minimum radius of the autoguider probe mirror.
     */
    public static final double MIN_RADIUS = 0.000;

    /**
     * Maximum radius of the autoguider probe mirror.
     */
    public static final double MAX_RADIUS = 110.000;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Radius of the autoguider probe mirror.
     */
    protected double radius = 0.000;

    /**
     * boolean Describing whether the autoguider probe mirror should be at the
     * autoguider field centre.
     */
    protected boolean centre = false;

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
     * This constructor creates an AGRADIAL command with the ID specified by
     * <b>s</b> and a radius specified by <b>d</b>.
     * @param s the String ID of this command
     * @param d the radius of the autoguider probe mirror
     */
    public AGRADIAL( String s, double d )
	throws IllegalArgumentException
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange( d, MIN_RADIUS, MAX_RADIUS ) )
	    throw new IllegalArgumentException
		( "radius "+d+" is not in the range "+MIN_RADIUS+" - "+
		  MAX_RADIUS );

	radius = d;
	centre = false;
    }


    /**
     * This constructor creates an AGRADIAL command with the specified ID.  The
     * position of the probe mirror will be the autoguider field centre.
     * @param s the String ID of this command
     */
    public AGRADIAL( String s )
    {
	super( s );
	centre = true;
    }


    /**
     * Return the radius to set the autoguider probe mirror to, in millimetres.
     * @return radius
     * @see #radius
     */
    public double getRadius()
    {
	return radius;
    }


    /**
     * Return a boolean describing whether the autoguider probe mirror should
     * be centred.
     * @return centre
     * @see #centre
     */
    public boolean isCentre()
    {
	return centre;
    }
}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: AGRADIAL.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGRADIAL.java,v $
 *      $Id: AGRADIAL.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
