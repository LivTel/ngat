package ngat.ngtcs.command;

/**
 * This command sets the autoguider to be used for all susequent autoguider
 * commands.  The CommandImplementor for this command will initialise the
 * specified <code><b>PluggableSubSystem</code></b>-implementing autoguider.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGSELECT extends ngat.ngtcs.command.Command
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
	new String( "$Id: AGSELECT.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The String name of the PluggableSubSystem to use for autoguiding.
     */
    protected String agName;

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
     * This constructor creates an AGSELECT command with the ID and name
     * specified by <b>s1</b> and <b>s2</b> respectively.
     * @param s1 the String ID of this command
     * @param s2 the String name of the autoguider to select
     */
    public AGSELECT( String s1, String s2 )
    {
	super( s1 );
	agName = s2;
    }

    /**
     * Return the name of the autoguider to select.
     * @return agName
     * @see #agName
     */
    public String getName()
    {
	return agName;
    }
}
/*
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: AGSELECT.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGSELECT.java,v $
 *      $Id: AGSELECT.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
