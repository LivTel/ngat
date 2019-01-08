package ngat.ngtcs.command;

/**
 * This command will park the telescope in the preset park position specified.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class PARK extends ngat.ngtcs.command.Command
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

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The ParkPosition to be used for parking the telescope.
     */
    protected ParkPosition position;

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
     * Create a PARK command with the specified ID and position to park the
     * telescope in.
     * @param s the String ID of this command
     * @param p the preset ParkPosition to use
     * @see ParkPosition
     */
    public PARK( String s, ParkPosition p )
    {
	super( s );
	position = p;
    }


    /**
     * Return the preset ParkPosition to be used for parking the telescope.
     * @return position
     * @see #position
     */
    public ParkPosition getPosition()
    {
	return position;
    }
}
/*
 *    $Date: 2013-07-04 10:07:12 $
 * $RCSfile: PARK.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/PARK.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
