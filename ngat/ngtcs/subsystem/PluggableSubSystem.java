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
public interface PluggableSubSystem
{
    /**
     * Initialise the object implementing this interface.
     */
    public void initialise( Telescope t ) throws InitialisationException;

    /**
     * Set the level of logging detail for this PluggableSubSystem.
     * @param i the new logging level
     */
    public void setLogLevel( int i );

    /**
     * Return an error message.  This will be called when an error is
     * encountered to describe the error.
     */
    public String getErrorMessage();

}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: PluggableSubSystem.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/PluggableSubSystem.java,v $
 *     $Log: not supported by cvs2svn $
 */
