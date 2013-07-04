package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Change the focus by a specified amount.
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.5 $
 */
public class DFOCUSImplementor extends CommandImplementor
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
    new String( "$Id: DFOCUSImplementor.java,v 1.5 2013-07-04 10:16:21 cjm Exp $" );

  /**
   * The timeout for the DFOCUS command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

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
  public DFOCUSImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * The input argument for the offset value is added to the current position
   * and checked to see if it is within operational range.  If so, the demand
   * is sent to the Secondary Mirror and the position polled periodically until
   * it is within tolerance of the demand, or the timeout has expired.
   * If the timeout expires, or the demand offset will move the mirror out of
   * operational range, a failed commandDone will be returned by this
   * implementor.
   */
  public void execute()
  {
    TTL_SecondaryMirror sm =
      (TTL_SecondaryMirror)TTL_SecondaryMirror.getInstance();

    DFOCUS dFocus = (DFOCUS)command;
    double offset = dFocus.getOffset();
    double demand = offset + sm.getFocusPosition();
    double tolerance = sm.getPositionTolerance();
    double posError = 99999.9, actual = 0.0;

    // check demand vs. actual position
    try
    {
      if( ( demand < sm.getMinimumDemandPosition() ) ||
	  ( demand > sm.getMaximumDemandPosition() ) )
      {
	commandDone.setErrorMessage
	  ( "Requested offset ["+offset+"] will drive the mirror out of "+
	    "operational range ["+sm.getMinimumDemandPosition()+"mm - "+
	    sm.getMaximumDemandPosition()+"mm]: execution terminated" );
	return;
      }

      // send demand
      sm.setDemandPosition( demand );

      do
      {
	sleep( 500 );
	actual = sm.getActualPosition();
	posError = Math.abs( actual - demand );
      }
      while( ( slept < TIMEOUT )&&( posError > tolerance ) );

      // update SM internal focus and offset position fields
      sm.setFocusPosition();
      sm.setFocusOffset( offset );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
      return;
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
 *    $Date: 2013-07-04 10:16:21 $
 * $RCSfile: DFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/DFOCUSImplementor.java,v $
 *      $Id: DFOCUSImplementor.java,v 1.5 2013-07-04 10:16:21 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/09/29 13:27:41  je
 *     Added execute documentation.
 *
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
