package ngat.ngtcs.command;

import ngat.ngtcs.PointingModelCoefficients;

/**
 *
 * 
 * @author $Author: je $
 * @version $Revision: 1.1 $
 */
public class CALIBRATEDone extends CommandDone
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: CALIBRATEDone.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * The coefficient set that resulted from the CALIBRATE command.
   */
  protected PointingModelCoefficients pmc;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: CALIBRATEDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/CALIBRATEDone.java,v $
 *      $Id: CALIBRATEDone.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
