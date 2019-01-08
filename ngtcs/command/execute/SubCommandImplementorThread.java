package ngat.ngtcs.command.execute;

import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class SubCommandImplementorThread implements Runnable
{
  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS FIELDS.  /*                                                       */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT FIELDS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * Boolean describing whether this thread has finished execution.
   */
  protected boolean finished = false;

  /**
   * Boolean describing whether this thread executed successfully.
   */
  protected boolean success = false;

  /**
   * Message to be returned from this Thread's execution.
   */
  protected String message = "";

  /**
   * Error message that occurred during execution.
   */
  protected String errorMessage = "";

  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS METHODS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/


  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT METHODS.  /*                                                     */
  /*  /*                                                                     */
  /*=========================================================================*/



  /**
   * The Thread.run() method.  This will be implemented by every sub-class to
   * enable threading, and consequently, simultaneous, multi-task commands.
   */
  public abstract void run();


  /**
   * Return <code>finished</code>.
   * @see #finished
   */
  public boolean isFinished()
  {
    return( finished );
  }


  /**
   * Return <code>success</code>.
   * @see #success
   */
  public boolean wasSuccessful()
  {
    return( success );
  }


  /**
   * Return the message from this Thread.#
   * @return message
   * @see #message
   */
  public String getReturnMessage()
  {
    return( message );
  }


  /**
   * Return the error message set during this thread's execution.
   * @return errorMessage
   * @see #errorMessage
   */
  public String getErrorMessage()
  {
    return( errorMessage );
  }
}
/*
 *    $Date: 2006-11-20 14:48:08 $
 * $RCSfile: SubCommandImplementorThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SubCommandImplementorThread.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 */
