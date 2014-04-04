package ngat.ngtcs.subsystem;

import ngat.ngtcs.subsystem.PluggableSubSystemCreator;
import ngat.ngtcs.subsystem.PluggableSubSystem;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
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
   * Method to return the only (Singleton) instance of Rotator, as a
   * <code>PluggableSubSystem</code>
   * @return the single Rotator instance
   */
  public PluggableSubSystem getInstance()
  {
    return( (PluggableSubSystem)( new Rotator() ) );
  }
}
/*
 *    $Date: 2013-07-04 10:54:44 $
 * $RCSfile: RotatorFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/RotatorFactory.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 */
