package ngat.ngtcs.command;

import ngat.ngtcs.PointingModelCoefficients;

/**
 *
 * 
 * @author $Author$
 * @version $Revision$
 */
public class CALIBRATEDone extends CommandDone
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
   * The coefficient set that resulted from the CALIBRATE command.
   */
  protected PointingModelCoefficients pmc;

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
   * Initialises this CommandDone to have the same ID as the instigating
   * command specified.
   * @param c the command for which this is the DONE message.
   */
  public CALIBRATEDone( Command c )
  {
    super( c );
  }


  /**
   *
   */
  public void setPointingModelCoefficients( PointingModelCoefficients pmc )
  {
    this.pmc = pmc;
  }


  /**
   *
   */
  public PointingModelCoefficients getPointingModelCoefficients()
  {
    return( pmc );
  }
}
/*
 *    $Date: 2013-07-04 10:06:29 $
 * $RCSfile: CALIBRATEDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/CALIBRATEDone.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 *
 */
