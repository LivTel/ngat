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
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class AGFOCUSImplementor extends CommandImplementor
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
    new String( "$Id: AGFOCUSImplementor.java,v 1.3 2003-09-22 14:39:45 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


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
   *
   */
  public AGFOCUSImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
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

      if( posError < tolerance )
      {
	commandDone.setReturnMessage( "focus already in position" );
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
}
/*
 *    $Date: 2003-09-22 14:39:45 $
 * $RCSfile: AGFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGFOCUSImplementor.java,v $
 *      $Id: AGFOCUSImplementor.java,v 1.3 2003-09-22 14:39:45 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
