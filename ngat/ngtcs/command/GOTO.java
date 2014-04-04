package ngat.ngtcs.command;

import ngat.ngtcs.common.*;

/**
 * This command will point the telescope at the specified Target and track it.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class GOTO extends ngat.ngtcs.command.Command
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

  /**
   * Target this TRACK Command is to use.
   */
  protected Target target;

  /**
   * Non-sidereal timestamp for non-sidereal tracking of this target.
   */
  protected Timestamp timestamp;

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
   * This constructor sets all variables in this command.
   * @param s the String ID of this command
   * @param t the Target to track
   * @param ts the Timestamp from when non-sidereal tracking must start
   */
  public GOTO( String s, Target t, Timestamp ts )
  {
    super( s );
    target = t;
    timestamp = ts;
  }


  /**
   * This constructor creates a TRACK command with the specified Target..
   * @param s the String ID of this command
   * @param t the Target to track
   */
  public GOTO( String s, Target t )
  {
    super( s );
    target = t;
  }


  /**
   * Return the Target to be tracked.
   * @return target
   * @see #target
   */
  public Target getTarget()
  {
    return target;
  }


  /**
   * Return the Timestamp from when non-sidereal tracking must start.
   * @return timestamp
   * @see #timestamp
   */
  public Timestamp getTimestamp()
  {
    return timestamp;
  }


  /**
   * Return a decription of how to use this command.
   */
  public String getHelp()
  {
    return( "GOTO target" );
  }


  /**
   * Returns the arguments of this Command as a String.
   * @return the argument String
   */
  protected String getArgString()
  {
    return( target.toString() );
  }
}
/*
 *    $Date: 2013-07-04 10:06:50 $
 * $RCSfile: GOTO.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/GOTO.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
