package ngat.ngtcs.test;

import ngat.net.*;
import ngat.message.base.*;
import ngat.ngtcs.command.*;

/**
 * Basic <b><code>ngat.message</code></b> wrapper 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class TestCommand extends COMMAND
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
	new String( "$Id: TestCommand.java,v 1.1 2003-07-01 10:13:54 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The NGTCS <code>Command</code> for which this is a wrapper.
     */
    protected Command command = null;


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
     * Constructor.
     * <br>
     * @param newCommand the NGTCS <code>Command</code> for which this is a wrapper
     */
    public TestCommand( Command newCommand, String newID )
    {
	super( newID );
	command = newCommand;
    }


    /**
     *
     */
    public Command getCommand()
    {
	return command;
    }
}
/*
 *    $Date: 2003-07-01 10:13:54 $
 * $RCSfile: TestCommand.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/TestCommand.java,v $
 *      $Id: TestCommand.java,v 1.1 2003-07-01 10:13:54 je Exp $
 *     $Log: not supported by cvs2svn $
 */
