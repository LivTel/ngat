package ngat.ngtcs.test;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.net.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class NGAT_NET_Communicator implements Communicator
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
	new String( "$Id: NGAT_NET_Communicator.java,v 1.1 2003-07-01 10:13:54 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The shutdown flag.
     */
    protected boolean shutdown = false;

    protected Logger logger;

    protected String logName;

    protected Telescope telescope;

    protected TCPServerExtension tcp;

    protected PrintWriter pw = new PrintWriter( System.err, true );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    public NGAT_NET_Communicator()
    {

    }


    /**
     *
     */
    public void initialise( Telescope t )
    {
	telescope = t;

	logger = telescope.getLogger( this.getClass() );
	logName = logger.getName();

	int timeout, port;

	NGATProperties np = new NGATProperties();

	try
	    {
		np.load
		    ( telescope.getName()+"-"+
		      StringUtilities.getLeaf( this.getClass().getName(), '.' )
		      +".cfg" );
	    }
	catch( Exception e )
	    {

	    }

	timeout = np.getInt( "timeout", 10000 );
	port = np.getInt( "port", 29574 );

	tcp = new TCPServerExtension( telescope.getName(), port, timeout );
	tcp.setErrorStream( pw );
	tcp.start();

	return;
    }


    /**
     * Handle the <code>Acknowledge</code> returned from
     * <code>telescope</code>.
     * @param ack the <code>Acknowledge</code> to handle
     */
    public void handleAcknowledge( Acknowledge ack )
    {
	return;
    }


    /**
     * Handle the <code>Done</code> returned from
     * <code>telescope</code>.
     * @param done the <code>Done</code> to handle
     */
    public void handleDone( CommandDone done )
    {
        return;
    }


    /**
     *
     */
    public boolean isActive()
    {
	return( !shutdown );
    }


    /**
     * Set the shutdown flag and close the TTL_CIL.
     */
    public void shutdown()
    {
	shutdown = true;
	tcp.close();
    }


    /**
     *
     */
    private class TCPServerExtension extends TCPServer
    {
	/**
	 *
	 */
	private TCPServerExtension( String name, int portNumber, int timeout )
	{
	    super( name, portNumber, timeout );

	    System.err.println( "TCPServer ["+name+"] listening on port "+
				portNumber+", with timeout = "+timeout );
	}


	/**
	 *
	 */
	public void startConnectionThread( Socket s )
	{
	    TestExecutionThread thread = new TestExecutionThread
		( s, telescope, NGAT_NET_Communicator.this );
	    thread.setErrorStream( pw );
	    thread.start();
	}
    }
}
/*
 * $Date: 2003-07-01 10:13:54 $
 * $RCSfile: NGAT_NET_Communicator.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/NGAT_NET_Communicator.java,v $
 * $Log: not supported by cvs2svn $
 */
