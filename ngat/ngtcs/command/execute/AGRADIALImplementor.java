package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * Move autoguider probe mirror to a radial position.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class AGRADIALImplementor
  extends CommandImplementor
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
    new String( "$Id: AGRADIALImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

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
  public AGRADIALImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    AGRADIAL a = (AGRADIAL)command;
    int nAck = 0;
    double radius = 0.0;

    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();

    try
    {
      int demand;

      if( a.isCentre() )
      {
	//PointingOrigin po = 
	//    telescope.getActiveVirtualTelescope().
	//    getFocalStation().getPointingOrigin();
	//
	//double radius = Math.sqrt
	//    ( ( po.getX() * po.getX() ) +
	//      ( po.getY() * po.getY() ) );
	radius = 123.4;

	demand = (int)( Math.rint( radius * 1000.0 ) );
      }
      else
      {
	demand = (int)( Math.rint( a.getRadius() * 1000.0 ) );
      }

      if( ag.getActualPosition() == demand )
      {
	commandDone.setReturnMessage
	  ( "autoguider probe already in position" );
      }
      else
      {
	ag.setDemandPosition( demand );

	// sleep to allow command routing??

	while( ( ag.getActualPosition() != demand )&&
	       ( nAck < 11 ) )
	{
	  Acknowledge ack = new Acknowledge
	    ( command.getId()+"."+( nAck++ ),
	      command );
	  ack.setTimeToComplete( 6000 );
	  executionThread.sendAcknowledge( ack );

	  try
	  {
	    Thread.sleep( 5000 );
	  }
	  catch( InterruptedException ie )
	  {
	    logger.log
	      ( 2, logName, ie.toString() );
	  }
	}
      }
      commandDone.setSuccessful( true );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
    }
  }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: AGRADIALImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGRADIALImplementor.java,v $
 *      $Id: AGRADIALImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
