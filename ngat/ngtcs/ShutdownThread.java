package ngat.ngtcs;

import ngat.net.*;

/**
 * Thread used to sleep for the specified seconds, then exit with the specified
 * exit value.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class ShutdownThread extends Thread
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
	new String( "$Id: ShutdownThread.java,v 1.1 2003-07-01 10:11:30 je Exp $" );


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    private int sleepMillis = 60000;

    private int exitValue = 0;

    private TCPServer server = null;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    public ShutdownThread( TCPServer ser, int sleep, int exit )
    {
	sleepMillis = sleep;
	exitValue = exit;
	server = ser;
    }

    public void run()
    {
	if( server != null )
	    {
		server.close();

		while( server.isAlive() )
		    {
			try
			    {
				Thread.sleep( 1000 );
			    }
			catch( InterruptedException ie )
			    {
				System.err.println
				    ( ie );
			    }
		    }
	    }

	try
	    {
		Thread.sleep( sleepMillis );
	    }
	catch( InterruptedException ie )
	    {
		System.err.println( "ShutdownThread interrupted" );
	    }
	System.err.println( "\nExiting..." );
	System.exit( exitValue );
    }
}
/*
 * $Date: 2003-07-01 10:11:30 $
 * $RCSfile: ShutdownThread.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/ShutdownThread.java,v $
 * $Log: not supported by cvs2svn $
 */
