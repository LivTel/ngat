package ngat.ngtcs.command;

/**
 * This command will move the fold mirror to the specified position (0-4).
 * An IllegalArgument Exception is thrown if the position specified is outside
 * the lagal values of MIN_POSITION (0) and MAX_POSITION (4).
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class MOVE_FOLD extends ngat.ngtcs.command.Command
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
	new String( "$Id: MOVE_FOLD.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The position to move the fold mirror to.
     * <p>
     * The possible values for the fold mirror are:
     * <ul>
     * <li>0 - stowed</li>
     * <li>1 - port 1</li>
     * <li>2 - port 2</li>
     * <li>3 - port 3</li>
     * <li>4 - port 4</li>
     * </ul>
     */
    protected int position;

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Minimum value for the mirror position.
     */
    public static final int MIN_POSITION = 0;

    /**
     * Maximum value for the mirror position.
     */
    public static final int MAX_POSITION = 4;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Creat a MOVE_FOLD command with the specified ID and position for the
     * fold mirror.
     * @param s the String ID of this command
     * @param i the position to move the fold mirror
     */
    public MOVE_FOLD( String s, int i )
    {
	super( s );

	if( ngat.ngtcs.common.Util.outOfRange
	    ( i, MIN_POSITION, MAX_POSITION ) )
	    throw new IllegalArgumentException
		( "Position "+i+" is outside the range "+MIN_POSITION+" - "+
		  MAX_POSITION );

	position = i;
    }


    /**
     * Return the position value of where the fold mirror is to be moved.
     * @return position
     * @see #position
     */
    public int getPosition()
    {
	return position;
    }
}
/*
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: MOVE_FOLD.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/MOVE_FOLD.java,v $
 *      $Id: MOVE_FOLD.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
