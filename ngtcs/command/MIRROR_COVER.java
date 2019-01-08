package ngat.ngtcs.command;

/**
 * This command will open or close the mirror cover.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class MIRROR_COVER extends ngat.ngtcs.command.Command
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
     * Description of whether the mirror cover should be open.
     */
    protected boolean open;

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
     * Create a MIRROR_COVER command with the specified ID, stating whether the
     * mirror cover should be OPEN.
     * @param s the StringID of this command
     * @param b whether the mirror cover should be open
     */
    public MIRROR_COVER( String s, boolean b )
    {
	super( s );
	open = b;
    }


    /**
     * Return whether the mirror cover is to be open or not.
     * @return open
     * @see #open
     */
    public boolean open()
    {
	return open;
    }
}
/*
 *    $Date: 2013-07-04 10:07:01 $
 * $RCSfile: MIRROR_COVER.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/MIRROR_COVER.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
