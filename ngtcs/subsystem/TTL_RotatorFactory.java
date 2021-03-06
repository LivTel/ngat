package ngat.ngtcs.subsystem;

import ngat.ngtcs.subsystem.PluggableSubSystemCreator;
import ngat.ngtcs.subsystem.PluggableSubSystem;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_RotatorFactory
  implements PluggableSubSystemCreator
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Method to return the only (Singleton) instance of TTL_Rotator, as a
   * <code>PluggableSubSystem</code>
   * @return the single TTL_Rotator instance
   */
  public PluggableSubSystem getInstance()
  {
    return( (PluggableSubSystem)( TTL_Rotator.getInstance() ) );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_RotatorFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_RotatorFactory.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
