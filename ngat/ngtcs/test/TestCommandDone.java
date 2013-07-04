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
public class TestCommandDone extends COMMAND_DONE
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
	new String( "$Id: TestCommandDone.java,v 1.2 2013-07-04 13:06:57 cjm Exp $" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The NGTCS <code>Command</code> for which this is a wrapper.
     */
    protected CommandDone commandDone = null;


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
     * @param done the <code>CommandDone</code> for which this is a wrapper
     */
    public TestCommandDone( CommandDone done, String id )
    {
	super( id );
	commandDone = done;
    }


    /**
     *
     */
    public CommandDone getCommandDone()
    {
	return commandDone;
    }
}
/*
 *    $Date: 2013-07-04 13:06:57 $
 * $RCSfile: TestCommandDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/TestCommandDone.java,v $
 *      $Id: TestCommandDone.java,v 1.2 2013-07-04 13:06:57 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
