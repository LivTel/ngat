package ngat.ngtcs.command;

/**
 * This command updates the pointing model by either performing a pointing
 * test, or by reverting to the 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class CALIBRATE extends ngat.ngtcs.command.Command
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
	new String( "$Id: CALIBRATE.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The CalibrateMode set by this command.
     */
    protected CalibrateMode calibrateMode;

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
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: CALIBRATE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/CALIBRATE.java,v $
 *      $Id: CALIBRATE.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
