package ngat.ngtcs.command;

import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * The response to the SHOW command, containing the information requested by
 * the SHOW command specified in the constructor.  For information requested
 * from this object that was not specified by the original SHOW command, or not
 * applicabel due to the Telescope's current mode of operation, the returned
 * values will be zero or null, depending upon the return type.  This
 * <b>MUST</b> be checked for in the control process that requested the data
 * with the SHOW command.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class SHOW_TIME_Done extends CommandDone
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
    new String( "$Id:" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  private Time time;

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
   * Initialise the reply to the specified SHOW command containing the
   * <code><b>true</b></code> successful flag and requested data or a
   * <code><b>false</b></code> successful flag.
   * @param s the SHOW command to which this is a reply
   */
  public SHOW_TIME_Done( SHOW s,
			 Time t )
  {
    super( s );

    time = t;
  }


  /**
   *
   */
  public Time getTime()
  {
    return( time );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_TIME_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_TIME_Done.java,v $
 *      $Id: SHOW_TIME_Done.java,v 1.1 2006-11-20 14:47:33 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
