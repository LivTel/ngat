package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 *
 *
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public interface ControllableSubSystem extends PluggableSubSystem
{
    /**
     * Gets the current State of the implementing PluggableSubSystem.
     * @return mechanismState
     */
    public SubSystemState getState();

    /**
     * Return the status of this mechanism.
     * @return the status of this mechanism
     */
    public Status getStatus();

    /**
     * Return a descriptor of whether the mechanism implementing this interface
     * is currently active.
     */
    public boolean isActive();

    /**
     * The mechanism implementing this interface into a safe state in 
     * preparation for a shutdown or power cut.
     * @return boolean describing the success state of this method call.
     */
    public boolean makeSafe();

    /**
     * Shutdown this mechanism.
     * @return boolean describing the success state of this method call.
     */
    public boolean shutdown();

    /**
     * Describes whether a Shutdown is in progress.
     * @return boolean
     */
    public boolean shutdownInProgress();
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: ControllableSubSystem.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ControllableSubSystem.java,v $
 *      $Id: ControllableSubSystem.java,v 1.1 2003-07-01 10:13:46 je Exp $
 *     $Log: not supported by cvs2svn $
 */
