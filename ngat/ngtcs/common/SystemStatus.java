package ngat.ngtcs.common;

import java.util.Hashtable;

/**
 * Abstract super-class of all NGTCS-pluggable Systems Status.
 *
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public abstract class SystemStatus extends State
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
    new String( "$Id: SystemStatus.java,v 1.1 2013-07-04 10:46:43 cjm Exp $" );

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
   * Create a SystemState.
   */
  public SystemStatus()
  {
  }
}
/*
 *    $Date: 2013-07-04 10:46:43 $
 * $RCSfile: SystemStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SystemStatus.java,v $
 *      $Id: SystemStatus.java,v 1.1 2013-07-04 10:46:43 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
