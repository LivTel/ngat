package ngat.ngtcs.command;

/**
 * Generic DONE message used to notify Command requests upon execution
 * completion.  The general values such as the success state of the command
 * executiuon and a brief return message are held in this class.
 * <br>
 * For commands that return specific data (e.g. specific mechanism status
 * requests), this class should be sub-classed.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class CommandDone extends ngat.message.base.COMMAND_DONE
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
    new String( "$Id: CommandDone.java,v 1.3 2013-07-04 10:06:37 cjm Exp $" );

  /**
   * Total number of dones sent.
   */
  protected static long totalDoneNumber = 0;


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The number of this CommandDone.
   */
  protected long doneNumber;

  /**
   * The return message from the CommandImplementor that executed the
   * command to which this is the completion message.
   */
  protected String returnMessage = "";

  /**
   * The command to which this is the completion message.
   */
  protected Command command;

  /**
   * The success state of the comman execution.
   */
  protected boolean successful = false;

  /**
   * An error number which can be set to specific error codes.
   */
  protected int errorNum = 0;

  /**
   * An error string detailing the reason for <code>success = false</code>.
   */
  protected String errorMsg = "";


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the total number of <code>CommandDone</code>s.
   */
  public static long getTotalDoneNumber()
  {
    return totalDoneNumber;
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Initialises this CommandDone to have the same ID as the instigating
   * command specified.
   * @param c the command for which this is the DONE message.
   */
  public CommandDone( Command c )
  {
    super( c.getId() );
    command = c;
    doneNumber = ++totalDoneNumber;
  }


  /**
   * Return the Command for which this is the completion onject.
   * @return command
   * @see #command
   */
  public Command getCommand()
  {
    return command;
  }

  /**
   * Sets the success state (<code>successful</code>) of the command
   * execution.  This method is called from the CommandImplementor executing
   * the Command specified in the constructor.
   * @param b the success state of execution
   * @see #successful
   */
  public void setSuccessful( boolean b )
  {
    successful = b;
  }


  /**
   * Return the success state of execution of the Command specified in the
   * constructor.
   * @return successful
   * @see #successful
   */
  public boolean getSuccessful()
  {
    return successful;
  }


  /**
   * Set the error code describing why the command execution failed.
   * @param i the error code to set
   * @see #errorNum
   */
  public void setErrorNum( int i )
  {
    errorNum = i;
  }


  /**
   * Return the error code explaining why the command execution was
   * unsuccessful.
   * @return errorNum
   * @see #errorNum
   */
  public int getErrorNum()
  {
    return errorNum;
  }


  /**
   * Set the error message describing why the command execution failed.
   * @param s the error message to set
   * @see #errorMsg
   */
  public void setErrorMessage( String s )
  {
    errorMsg = s;
  }


  /**
   * Return the error message explaining why the command execution failed.
   * @return errorMsg
   * @see #errorMsg
   */
  public String getErrorMessage()
  {
    return errorMsg;
  }


  /**
   * Set the message returned by the CommandImplementor.
   * @param s the message to set
   * @see #returnMessage
   */
  public void setReturnMessage( String s )
  {
    returnMessage = s;
  }


  /**
   * Return the message from the CommandImplementor.
   * @return returnMessage
   * @see #returnMessage
   */
  public String getReturnMessage()
  {
    return returnMessage;
  }


  /**
   * Return the number of this <code>CommandDone</code>.
   */
  public long getDoneNumber()
  {
    return doneNumber;
  }
}
/*
 *    $Date: 2013-07-04 10:06:37 $
 * $RCSfile: CommandDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/CommandDone.java,v $
 *      $Id: CommandDone.java,v 1.3 2013-07-04 10:06:37 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
