package ngat.ngtcs.command;

/**
 * This command will return data and/or status information, depending upon the 
 * specified subject.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SHOW extends Command
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
	new String( "$Id: SHOW.java,v 1.1 2003-07-01 10:12:39 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Type of information to show.
     * @see ngat.ngtcs.command.ShowType
     */
    protected ShowType showType;

    /**
     * String name of the sub-system where sub-system-specific data is
     * requested.
     */
    protected String systemName = null;

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
     * Create a SHOW command with the specified ID and type of data to show.
     * @param s the String ID of this command
     * @param st the type of information to show
     */
    public SHOW( String s, ShowType st )
    {
	super( s );
	showType = st;
    }


    /**
     * Create a SHOW command with the specified ID and type of data to show
     * about the specified sub-system.
     * @param s1 the String ID of this command
     * @param st the type of information to show
     * @param s2 the String name of the specific sub-system
     */
    public SHOW( String s1, ShowType st, String s2 )
    {
	super( s1 );
	showType = st;
	systemName = s2;
    }


    /**
     * Return the type of information to show.
     * @return showType
     * @see #showType
     */
    public ShowType getType()
    {
	return showType;
    }


    /**
     * Return the name of the specific sub-system about which the data is
     * required.
     * @return systemName
     * @see #systemName
     */
    public String getSystemName()
    {
	return systemName;
    }
}
/*
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: SHOW.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW.java,v $
 *      $Id: SHOW.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */
