package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * Interface describing the core functionality of all NGTCS-pluggable sub
 * systems.
 *
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public interface PluggableSubSystem
{
  /**
   * Gets the current State of the implementing PluggableSubSystem.
   */
  public SystemState getState() throws SystemException;

  /**
   * Initialise the object implementing this interface.
   */
  public void initialise( Telescope t ) throws InitialisationException;

  /**
   * Set the level of logging detail for this PluggableSubSystem.
   * @param i the new logging level
   */
  public void setLogLevel( int i );
}
/*
 *    $Date: 2013-07-04 10:54:41 $
 * $RCSfile: PluggableSubSystem.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/PluggableSubSystem.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
