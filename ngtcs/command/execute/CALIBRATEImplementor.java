package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * Update a subset of the pointing coefficients in the pointing model.
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class CALIBRATEImplementor
  extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the CALIBRATE command (1800 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 1800000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                        */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  protected boolean calibrating = false;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                       */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public CALIBRATEImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    CALIBRATE c = (CALIBRATE)command;
    CalibrateMode cm = c.getMode();
    commandDone = new CALIBRATEDone( c );
    PointingModelCoefficients pmc = null;

    if( cm == CalibrateMode.DEFAULT )
    {
      try
      {
	telescope.getMount().getPointingModel().initialise( telescope );
      }
      catch( InitialisationException ie )
      {
	String err = new String
	  ( "Failed to reset pointing model : "+ie.toString() );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
	return;
      }
    }
    else if( cm == CalibrateMode.NEW )
    {
      pmc = null;

      calibrating = true;
      //perform some calibration


      commandDone.setReturnMessage( "RMS of calibration is:" );
      ( (CALIBRATEDone)commandDone ).setPointingModelCoefficients( pmc );

      calibrating = false;
    }
    else if( cm == CalibrateMode.LAST )
    {
      telescope.getMount().getPointingModel().revertValues();
    }

    commandDone.setSuccessful( true );
  }


  /**
   * Return the default timeout for this command execution.
   * @return TIMEOUT
   * @see #TIMEOUT
   */
  public int calcAcknowledgeTime()
  {
    return( TIMEOUT );
  }
}
/*
 *    $Date: 2013-07-04 10:16:16 $
 * $RCSfile: CALIBRATEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/CALIBRATEImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
