package ngat.ngtcs.test;

import ngat.net.*;
import ngat.message.base.*;
import ngat.ngtcs.command.*;

/**
 * Basic <b><code>ngat.message</code></b> wrapper 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 *
 */
public class TestCommand extends Command
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
	new String( "$Id: TestCommand.java,v 1.2 2013-07-04 13:07:01 cjm Exp $" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The NGTCS <code>Command</code> for which this is a wrapper.
     */
    protected Command command = null;


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
 *    $Date: 2013-07-04 13:07:01 $
 * $RCSfile: TestCommand.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/TestCommand.java,v $
 *      $Id: TestCommand.java,v 1.2 2013-07-04 13:07:01 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
