package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Change the focus by a specified amount.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
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
  public static final String rcsid =
    new String( "$Id: DFOCUSImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the DFOCUS command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

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
    ( Telescope t, Command c )
  {
    super( t, c );
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
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: DFOCUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/DFOCUSImplementor.java,v $
 *      $Id: DFOCUSImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
