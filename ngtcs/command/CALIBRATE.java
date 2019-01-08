package ngat.ngtcs.command;

/**
 * This command updates the pointing model by either performing a pointing
 * test, or by reverting to the 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class CALIBRATE extends ngat.ngtcs.command.Command
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
     * The CalibrateMode set by this command.
     */
    protected CalibrateMode calibrateMode;

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
     * This onstructor sets the ID of this command and the mode used to
     * calibrate the pointing.
     * @param s the String ID of this command
     * @param cm the CalibrateMode of this command
     */
    public CALIBRATE( String s, CalibrateMode cm )
    {
	super( s );
	calibrateMode = cm;
    }


    /**
     * Return the CalibrateMode of this command.
     * @return calibrateMode
     * @see #calibrateMode
     */
    public CalibrateMode getMode()
    {
	return calibrateMode;
    }
}
/*
 *    $Date: 2013-07-04 10:06:31 $
 * $RCSfile: CALIBRATE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/CALIBRATE.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
