package ngat.ngtcs.subsystem;

import ngat.ngtcs.subsystem.PluggableSubSystemCreator;
import ngat.ngtcs.subsystem.PluggableSubSystem;

/**
 * 
 * 
 * @author $Author: cjm $
 * @version $Revision: 1.1 $
 */
public class TTL_SecondaryMirrorFactory
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
    new String( "$Id: TTL_SecondaryMirrorFactory.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

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
   * Method to return the only (Singleton) instance of TTL_SecondaryMirror, as a
   * <code>PluggableSubSystem</code>
   * @return the single TTL_SecondaryMirror instance
   */
  public PluggableSubSystem getInstance()
  {
    return( (PluggableSubSystem)( TTL_SecondaryMirror.getInstance() ) );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_SecondaryMirrorFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SecondaryMirrorFactory.java,v $
 *      $Id: TTL_SecondaryMirrorFactory.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
