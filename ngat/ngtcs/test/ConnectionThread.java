package ngat.ngtcs.test;

import ngat.util.*;

/**
 * Base class for implementations of a client-server Protocol
 * over a generic IConnection. This class is used to provide an
 * execution thread for a ProtocolImpl acting as client.
 * <br>
 */
public class ConnectionThread extends ControlThread
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
	new String( "$Id: ConnectionThread.java,v 1.1 2003-07-01 10:13:54 je Exp $" );


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


  
    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    public ConnectionThread()
    {

    }
    

    /**
     * Carry out any initialization.
     */
    public void initialise()
    {
    }


    /**
     * The mainTask() method is made final to force subclasses to follow the
     * specified execution template. Calls implement() on the attached
     * ProtocolImpl. The implementor <b>must</b> block to stop this thread
     * from returning from its run method and dieing prematurely.
     */
    public final void mainTask()
    {
	//implementor.implement();
    }
    

    /**
     * Force the implementor to give up any live connections
     * and release this thread from a blocked I/O state.
     */
    public void abort()
    {
	System.err.println( "CCT: called abort()" );
	//implementor.cancel();
    }


    /**
     * Carry out any shutdown procedures.
     */
    public void shutdown()
    {
    }
}
/*
 *    $Date: 2003-07-01 10:13:54 $
 * $RCSfile: ConnectionThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/ConnectionThread.java,v $
 *      $Id: ConnectionThread.java,v 1.1 2003-07-01 10:13:54 je Exp $
 *     $Log: not supported by cvs2svn $
 */
