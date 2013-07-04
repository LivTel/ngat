package ngat.ngtcs.common;

import java.util.Hashtable;

/**
 * Abstract super-class of all NGTCS-pluggable systems states and status.
 *
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public abstract class State
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
    new String( "$Id: State.java,v 1.3 2013-07-04 10:46:08 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Hashtable of Objects describing the mechanism-specific state.
   */
  protected Hashtable stateHash = new Hashtable();

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
   * Create a State.
   */
  public State()
  {
  }


  /**
   * Return the state Hashtable.
   * @return stateHash
   * @see #stateHash
   */
  public Hashtable getHashtable()
  {
    return( stateHash );
  }
}
/*
 *    $Date: 2013-07-04 10:46:08 $
 * $RCSfile: State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/State.java,v $
 *      $Id: State.java,v 1.3 2013-07-04 10:46:08 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
