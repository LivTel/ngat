package ngat.ngtcs;

import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public interface Communicator
{
    /**
     * Initialise the NGTCS_Communicator; set up sockets, connections etc.
     * @param t the Telescope for which this is the Communicator.
     */
    public void initialise( Telescope t ) throws InitialisationException;

    /**
     * Process the <code>Acknowledge</code> returned from
     * <code>telescope</code>.
     * @param ack the <code>Acknowledge</code> to process
     */
    public void handleAcknowledge( Acknowledge ack );

    /**
     * Process the <code>Done</code> returned from
     * <code>telescope</code>. Any errors should be set in the
     * <code>CommandDone</code> object, along with the relevent error codes.
     * @param ack the <code>Done</code> to process
     */
    public void handleDone( CommandDone done );

    /**
     * Shutdown this Communicator.
     * This method should close any sockets/pipes etc that is performing IO
     * operations, in preparation for a power failure or application close.
     */
    public void shutdown();

    /**
     * Returns a boolean describing whether the <code>shutdown</code> method
     * has been called.
     */
    public boolean isActive();
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: Communicator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/Communicator.java,v $
 *      $Id: Communicator.java,v 1.1 2003-07-01 10:11:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
