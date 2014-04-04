package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Set autoguider filter position.
 * This implementor will move the autoguider filter either in or out of the
 * autoguider beam.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGFILTERImplementor
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
   * The timeout for the AGFILTER command (60 seconds), in milliseconds.
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
  public AGFILTERImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * If deploy flag is set and filter is NOT deployed, then deploy OR vice
   * versa.  If the demand has already been acheived, then return immediately
   * with success = true.  If a movement is required, a periodic sleep is
   * performed to return whether the movement has succeeded or not, unless the
   * timeout period is exceeded, in which case a success = false is returned.
   */
  public void execute()
  {
    boolean move = false;
    boolean deploy = ( (AGFILTER)command ).deploy();
    AGD_FilterPosition demand = null;

    try
    {
      TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
      AGD_FilterPosition pos = ag.getActualFilterPosition();

      // Do I need to init if pos = E_AGD_IR_UNSET ??

      if( deploy )
      {
	if( ( pos == AGD_FilterPosition.E_AGD_IR_UNSET )||
	    ( pos == AGD_FilterPosition.E_AGD_IR_RETRACT ) )
	{
	  demand = AGD_FilterPosition.E_AGD_IR_IN_LINE;
	  ag.setDemandFilterPosition( demand );
	  move = true;
	  logger.log( 3, logName, "Autoguider filter demand set to IN_LINE" );
	}
	else
	{
	  String msg = new String( "Autoguider filter already deployed" );
	  logger.log( 3, logName, msg );
	  commandDone.setReturnMessage( msg );
	}
      }
      else
      {
	if( ( pos == AGD_FilterPosition.E_AGD_IR_UNSET )||
	    ( pos == AGD_FilterPosition.E_AGD_IR_IN_LINE ) )
	{
	  demand = AGD_FilterPosition.E_AGD_IR_RETRACT;
	  ag.setDemandFilterPosition( demand );
	  move = true;
	  logger.log( 3, logName, "Autoguider filter demand set to RETRACT" );
	}
	else if( pos == AGD_FilterPosition.E_AGD_IR_RETRACT )
	{
	  String msg = new String( "Autoguider filter already retracted" );
	  logger.log( 3, logName, msg );
	  commandDone.setReturnMessage( msg );
	}
      }

      // if moving wait until stopped
      if( move == true )
      {
	do
	{
	  // check state every second
	  slept += 1000;
	  try
	  {
	    Thread.sleep( 1000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log( 1, logName, ie );
	  }
	}
	while( ( ag.get_AGD_State() == AGD_State.E_AGD_STATE_MOVING )&&
	       ( slept < TIMEOUT ) );
      }

      // if timeout or State != MOVING
      AGD_FilterPosition actual = ag.getActualFilterPosition();
      if( demand != actual )
      {
	String err = new String
	  ( "Autoguider position ["+actual.getName()+"] did not reach demand ["
	    +demand+"] before timeout ["+TIMEOUT+"ms] and AGD state = "+
	    ag.get_AGD_State().getName()+" : execution terminated" );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
	return;
      }

      commandDone.setSuccessful( true );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
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
 *    $Date: 2013-07-04 10:15:30 $
 * $RCSfile: AGFILTERImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFILTERImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.6  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.5  2003/09/22 14:21:36  je
 *     Removed MAX_TIMEOUTS field.
 *
 *     Revision 1.4  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.3  2003/09/22 11:37:26  je
 *     Changed timeout message.
 *
 *     Revision 1.2  2003/09/22 11:32:28  je
 *     Added 'TIMEOUT' and 'slept' functionality from super-class.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
