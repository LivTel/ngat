package ngat.ngtcs.command;

import java.util.*;

/**
 * The Acknowledge message is returned from the NGTCS to the specified
 * <code><b>Communicator</b></code> object being used on the Telescope system.
 * An Acknowledge is returned after command receipt with a timeout.  Further
 * Acknowledges may be sent to increase this timeout.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class Acknowledge extends ngat.message.base.ACK
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
    new String( "$Id: Acknowledge.java,v 1.3 2013-07-04 10:05:28 cjm Exp $" );

  /**
   * Total number of acknowledges sent.
   */
  protected static long totalAcknowledgeNumber = 0;


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * This acknowledges number.
   */
  protected long acknowledgeNumber = 0;

  /**
   * Time to complete Command, in seconds.
   */
  protected int timeToComplete;

  /**
   * Command for which this is an Acknowledge for.
   */
  protected Command command;


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the total number of acknowledge sent.
   * @return <code>totalAcknowledgeNumber</code>
   * @see #totalAcknowledgeNumber
   */
  public static long getTotalAcknowledgeNumber()
  {
    return totalAcknowledgeNumber;
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Constructor.
   * <br>
   * Sets the ID of this Acknowledge, and increments the acknowledge counters
   * accordingly.
   * @param s the ID string of this acknowledge.
   * @param c the <code>Command</code> for which this is the <code>Ack</code>
   */
  public Acknowledge( String s, Command c )
  {
    super( s );
    command = c;
    acknowledgeNumber = ++totalAcknowledgeNumber;

  }


  /**
   * Set the completion time for the <code>Command</code> specified in the
   * constructor.
   */
  public void setTimeToComplete( int i )
  {
    timeToComplete = i;
  }


  /**
   * Get the completion time for the <code>Command</code> specified in the
   * constructor.
   */
  public int getTimeToComplete()
  {
    return timeToComplete;
  }


  /**
   * Return the number of this acknowledge.
   * @return <code>acknowledgeNumber</code>
   * @see #acknowledgeNumber
   */
  public long getAcknowledgeNumber()
  {
    return acknowledgeNumber;
  }


  /**
   * Return the <code>Command</code> for which this is an Acknowledgement.
   * @return command
   * @see #command
   */
  public Command getCommand()
  {
    return command;
  }
}
/*
 *    $Date: 2013-07-04 10:05:28 $
 * $RCSfile: Acknowledge.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/Acknowledge.java,v $
 *      $Id: Acknowledge.java,v 1.3 2013-07-04 10:05:28 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
