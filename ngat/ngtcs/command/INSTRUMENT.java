package ngat.ngtcs.command;

/**
 * This command will initialise telescope sub-systems (namely the focus
 * mechanism, rotator and fold-mirror) ready for an exposure on the specified
 * instrument port.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class INSTRUMENT extends ngat.ngtcs.command.Command
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
	new String( "$Id: INSTRUMENT.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The String name of the instrument port to initialise.
     */
    protected String instPortName;

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
     * Create an INSTRUMENT command with the specified ID, to change instrument
     * port to that which has the name specified by <b>s2</b>.
     * @param s1 the String ID of this command
     * @param s2 the name of the instrument port to initialise
     */
    public INSTRUMENT( String s1, String s2 )
    {
	super( s1 );
	instPortName = s2;
    }


    /**
     * Return the name of the instrument port to be initialised.
     * @return instPortName
     * @see #instPortName
     */
    public String getPortName()
    {
	return instPortName;
    }
}
/*
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: INSTRUMENT.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/INSTRUMENT.java,v $
 *      $Id: INSTRUMENT.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
