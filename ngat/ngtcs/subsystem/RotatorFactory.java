package ngat.ngtcs.subsystem;

import ngat.ngtcs.subsystem.PluggableSubSystemCreator;
import ngat.ngtcs.subsystem.PluggableSubSystem;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class RotatorFactory
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
  public static final String RevisionString =
    new String( "$Id: RotatorFactory.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

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
   * Method to return the only (Singleton) instance of Rotator, as a
   * <code>PluggableSubSystem</code>
   * @return the single Rotator instance
   */
  public PluggableSubSystem getInstance()
  {
    return( (PluggableSubSystem)( Rotator.getInstance() ) );
  }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: RotatorFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/RotatorFactory.java,v $
 *      $Id: RotatorFactory.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
