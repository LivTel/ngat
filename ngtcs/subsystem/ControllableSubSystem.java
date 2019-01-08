package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * This interface is to be implemented by every mechanism that the NGTCS is to
 * control.
 *
 * @author $Author$ 
 * @version $Revision$
 */
public interface ControllableSubSystem extends PluggableSubSystem
{
  /**
   * Stop this mechanism performing whatever action(s) it is currently doing.
   */
  public void stop() throws SystemException;

  /**
   * The mechanism implementing this interface into a safe state in 
   * preparation for a shutdown or power cut.
   */
  public void makeSafe() throws SystemException;

  /**
   * Shutdown this mechanism.
   */
  public void shutdown() throws SystemException;

  /**
   * Describes whether a Shutdown is in progress.
   */
  public boolean shutdownInProgress();
}
/*
 *    $Date: 2013-07-04 10:53:16 $
 * $RCSfile: ControllableSubSystem.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ControllableSubSystem.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
