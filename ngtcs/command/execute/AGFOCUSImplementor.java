package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Set autoguider focus position.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGFOCUSImplementor extends CommandImplementor
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
   * The timeout for the AGFOCUS command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


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
  public AGFOCUSImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * First, the focus position demand is sent to the autoguider.
   * If the position error of the demand is within the tolerance then this
   * method will return successful immediately.
   * If the autoguider focus position is required to move then periodic
   * monitoring of the actual position will take place until either the
   * position falls within tolerance of the demand (returning a success), or
   * the timeout is exceeded (returning a failure).
   */
  public void execute()
  {
    AGFOCUS a = (AGFOCUS)command;
    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    double posError, actual, demand, tolerance;
    int nAck = 0;

    try
    {
      actual = ag.getActualFocusPosition();
      demand = a.getPosition();
      tolerance = ag.getFocusPositionTolerance();
      posError = Math.abs( demand - actual );
      ag.setDemandFocusPosition( actual );

      if( posError < tolerance )
      {
	commandDone.setReturnMessage( "focus already in position" );
	commandDone.setSuccessful( true );
	return;
      }
      else
      {
	ag.setDemandFocusPosition( demand );
	boolean completed = false;

	while( slept < TIMEOUT )
	{
	  slept += 5000;
	  try
	  {
	    Thread.sleep( 5000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log( 1, logName, ie.toString() );
	  }

	  actual = ag.getActualFocusPosition();
	  posError = Math.abs( demand - actual );
	  if( posError < tolerance )
	  {
	    commandDone.setSuccessful( true );
	    return;
	  }
	}

	String err = new String
	  ( "Autoguider focus position ["+actual+"mm] has not achieved the "+
	    "demanded position ["+demand+"mm] after 60 seconds "+
	    ": execution terminated" );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
      }
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
      return;
    }
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
 *    $Date: 2013-07-04 10:15:40 $
 * $RCSfile: AGFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFOCUSImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.6  2003/09/29 11:53:18  je
 *     corrected a method name call.
 *
 *     Revision 1.5  2003/09/29 11:06:03  je
 *     Added <code>execute</code> documentation.
 *
 *     Revision 1.4  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.3  2003/09/22 14:39:45  je
 *     Added TIMEOUT and slept functionality.
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
