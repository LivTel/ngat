package ngat.ngtcs.subsystem;

/**
 * Interface to be implemented by any factory class that will supply references
 * of PluggableSubSystem objects.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public interface PluggableSubSystemCreator
{
    /**
     * Return an/the instance of the PluggableSubSystem for which this is the
     * Factory
     */
    public PluggableSubSystem getInstance()
	throws InstantiationException;
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: PluggableSubSystemCreator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/PluggableSubSystemCreator.java,v $
 *      $Id: PluggableSubSystemCreator.java,v 1.1 2003-07-01 10:13:46 je Exp $
 *     $Log: not supported by cvs2svn $
 */
