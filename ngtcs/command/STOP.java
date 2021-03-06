package ngat.ngtcs.command;

/**
 * This command will stop the telescope and place it into an IDLE state.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class STOP extends Command
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
   * Create a STOP command with the specified ID.
   * @param s the String ID of this command
   */
  public STOP( String s )
  {
    super( s );
  }


  /**
   * Return a decription of how to use this command.
   */
  public String getHelp()
  {
    return( "STOP <telescope|mechanism-name>" );
  }


  /**
   * Returns the arguments of this Command as a String.
   * @return the argument String
   */
  protected String getArgString()
  {
    return( "\thas no arguments" );
  }
}
/*
 *    $Date: 2013-07-04 10:08:27 $
 * $RCSfile: STOP.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/STOP.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:01:31  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
