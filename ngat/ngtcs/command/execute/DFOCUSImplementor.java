package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class DFOCUSImplementor extends CommandImplementor
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
    new String( "$Id: DFOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
  public DFOCUSImplementor
    ( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    TTL_SecondaryMirror sm =
      (TTL_SecondaryMirror)TTL_SecondaryMirror.getInstance();

    DFOCUS dFocus = (DFOCUS)command;
    double offset = dFocus.getOffset();
    double demand = offset + sm.getFocusPosition();

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


      //sm.setDemandedPosition( offset + home );

      // check state is READY
      //while( sm.getState() == SMF_State.E_SMF_STATE_MOVING )
      // check state is STOPPED
      // check |(position-demand)| < tolerance
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
      return;
    }

    commandDone.setSuccessful( true );
  }

}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: DFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/DFOCUSImplementor.java,v $
 *      $Id: DFOCUSImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
