package ngat.ngtcs.test;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.message.base.*;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

/**
 * This class is a wrapper for the ngat.net.TCPServerConnectionThread to 
 * implement the JMS protocol for NGTCS command communication.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TestExecutionThread extends TCPServerConnectionThread
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
	new String( "$Id: TestExecutionThread.java,v 1.1 2003-07-01 10:13:54 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The NGTCS ExecutionThread for which this is a wrapper.
     */
    protected ExecutionThread executor;

    /**
     * The Telescope on which commands will be executed.
     */
    protected Telescope telescope;

    /**
     * The Communicator used to talk to the NGTCS.
     */
    protected Communicator communicator;

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
     * Create a TestExecutionThread wityh the specified Socket for the
     * super-class.  This thread will command the specified Telescope by using
     * the specified Communicator.
     * @param s the Socket over which to communicate
     * @param t the Telescope on which to execute commands
     * @param c the COmmunicator used to talk to the NGTCS
     */
    public TestExecutionThread( Socket s, Telescope t, Communicator c )
    {
	super( s );
	telescope = t;
	communicator = c;
    }


    /**
     * Over-ride the super-class method to produce the
     * <code><b>ngat.net.ACK</b></code> object form the NGTCS ExecutionThread
     * as per the ngat.net protocol.
     * @return a generated <code><b>ngat.net.ACK</b></code>
     */
    protected ACK calculateAcknowledgeTime()
    {
	Command c = ( (TestCommand)command ).getCommand();

        executor = new ExecutionThread( telescope, communicator, c );
	try
	    {
		executor.instantiateImplementor();
	    }
	catch( Exception e )
	    {
		processError( "couldn't instantiate implementor:", e );
		return null;
	    }

	ACK ack = new ACK( command.getId() );
	Acknowledge a = executor.generateAcknowledge();
	ack.setTimeToComplete( a.getTimeToComplete() );

	return( ack );
    }


    /**
     * Over-ride the method in the super-class to call the NGTCS
     * ExecutionThread <code><b>execute</b></code> method.
     */
    public void processCommand()
    {
	CommandDone cd = executor.processCommand();
	done = new TestCommandDone( cd, command.getId() );
    }
}
/*
 *    $Date: 2003-07-01 10:13:54 $
 * $RCSfile: TestExecutionThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/TestExecutionThread.java,v $
 *      $Id: TestExecutionThread.java,v 1.1 2003-07-01 10:13:54 je Exp $
 *     $Log: not supported by cvs2svn $
 */
