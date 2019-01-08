package ngat.ngtcs.common;

import java.util.Hashtable;

/**
 * Abstract super-class of all NGTCS-pluggable Systems States.
 *
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class SystemState extends State
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
   * Create a SystemState.
   */
  public SystemState()
  {
  }
}
/*
 *    $Date: 2013-07-04 10:46:40 $
 * $RCSfile: SystemState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SystemState.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
