package ngat.ngtcs.command;
/**
 * Top-level communication object, sub-classed for Command, Acknowledge and
 * CommandDone objects.  This top-level class contains field and methods
 * common across all NGTCS-Communicator communications.
 * <br>
 * @see ngat.ngtcs.command.Command
 * @see ngat.ngtcs.command.Acknowledge
 * @see ngat.ngtcs.command.CommandDone
 * @see ngat.ngtcs.Communicator
 *
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public abstract class Message extends ngat.message.base.MESSAGE
  implements java.io.Serializable
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
    new String( "$Id: Message.java,v 1.3 2013-07-04 10:06:59 cjm Exp $" );

  /**
   * Total number of messages sent.
   */
  protected static long totalMessageNumber = 0;


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * ID of this Message.
   */
  protected String id;

  /**
   * This messages number.
   */
  protected long messageNumber;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the total number of message sent.
   * @return <code>totalMessageNumber</code>
   * @see #totalMessageNumber
   */
  public static long getTotalMessageNumber()
  {
    return totalMessageNumber;
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Constructor.
   * <br>
   * Sets the ID of this Message, and increments the message counters
   * accordingly.
   * @param s the ID string of this message.
   */
  public Message( String s )
  {
    super( s );
    messageNumber = ( ++totalMessageNumber );
  }


  /**
   * Return the ID of this message.
   * @return <code>id</code>
   * @see #id
   */
  public String getId()
  {
    return id;
  }


  /**
   * Return the number of this message.
   * @return <code>messageNumber</code>
   * @see #messageNumber
   */
  public long getMessageNumber()
  {
    return messageNumber;
  }

}
/*
 *    $Date: 2013-07-04 10:06:59 $
 * $RCSfile: Message.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/Message.java,v $
 *      $Id: Message.java,v 1.3 2013-07-04 10:06:59 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
