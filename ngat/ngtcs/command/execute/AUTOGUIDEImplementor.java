package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.ags.*;

/**
 * Lock or unlock the autoguider loop.
 * This command implements the autoguiding functionality.
 * <p>
 * Autoguiding is performed by monitoring the position of a chosen object
 * (Guide Star) upon a CCD.  If the position of the guide star moves, then a
 * pointing model correction is applied to re-position the telescope so to
 * re-align the guide star with it's original CCD-frame coordinates.
 * <p>
 * The autoguiding procedure is implemented in a seperate thread after the
 * initial guide star coordinates have been calculated.  When autoguiding is
 * stopped the pointing model corrections are removed.
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class AUTOGUIDEImplementor extends CommandImplementor
  implements Runnable
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
    new String( "$Id: AUTOGUIDEImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected TTL_Autoguider ttlAG = null;

  /**
   *
   */
  protected Target guideStar;

  /**
   *
   */
  protected boolean stopGuiding = true;

  /**
   *
   */
  protected AltAzPointingModelCoefficients pmc;

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
  public AUTOGUIDEImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   *
   */
  public void execute()
  {
    // obtain object references
    ttlAG = (TTL_Autoguider)TTL_Autoguider.getInstance();
    AUTOGUIDE a = (AUTOGUIDE)command;
    AGS_State desired = null;
    pmc = new AltAzPointingModelCoefficients();
    telescope.getMount().getPointingModel().addCoefficients( pmc );

    // start of command execution
    Timestamp start = telescope.getTimer().getTime();

    try
    {
      AutoguideMode mode = a.getAutoguideMode();

      // Guide on the Brightest guide star
      if( mode == AutoguideMode.BRIGHTEST )
      {
	ttlAG.guideOnBrightest();
	desired = AGS_State.E_AGS_ON_BRIGHTEST;
      }

      // Gide on a guide star between magnitudes i1 and i2
      else if( mode == AutoguideMode.RANGE )
      {
	int i1 = (int)
	  ( Math.rint
	    ( a.getFaintestMagnitude() ) * 1000.0 );

	int i2 = (int)
	  ( Math.rint
	    ( a.getBrightestMagnitude() ) * 1000.0 );

	ttlAG.guideOnRange( i1, i2 );
	desired = AGS_State.E_AGS_ON_RANGE;
      }

      // Guide on teh Nth brightest star
      else if( mode == AutoguideMode.RANK )
      {
	ttlAG.guideOnRank( a.getBrightnessRank() );
	desired = AGS_State.E_AGS_ON_RANK;
      }

      // Guide on the star at pixel coords X,Y
      else if( mode == AutoguideMode.PIXEL )
      {
	int i1 = (int)
	  ( Math.rint( a.getXPixel() * 1000.0 ) );

	int i2 = (int)
	  ( Math.rint( a.getYPixel() * 1000.0 ) );

	ttlAG.guideOnPixel( i1, i2 );
	desired = AGS_State.E_AGS_ON_PIXEL;
      }


      // wait for AGS state change and see if it's the desired one
      // after 200 seconds
      int nAck = 0;
      while( ( ttlAG.get_AGS_State() == AGS_State.E_AGS_WORKING )&&
	     ( nAck < 40 ) )
      {
	Acknowledge ack = new Acknowledge
	  ( command.getId()+"."+( nAck++ ), command );
	ack.setTimeToComplete( 6500 );
	executionThread.sendAcknowledge( ack );

	try
	{
	  Thread.sleep( 5000 );
	}
	catch( InterruptedException ie )
	{
	  logger.log( 2, logName, ie.toString() );
	}
      }

      AGS_State actual = ttlAG.get_AGS_State();
      if( actual != desired )
      {
	String s =
	  ( "after "+(nAck * 5)+" seconds AGS has acheived "+
	    actual.getName()+" state, desired state is "+
	    desired.getName() );
	commandDone.setErrorMessage( s );
	logger.log( 1, logName, s );
	return;
      }

      Acknowledge ack = new Acknowledge
	( command.getId()+"."+( nAck++ ), command );
      ack.setTimeToComplete( 6500 );
      executionThread.sendAcknowledge( ack );

      // get x,y of guide star depending on mode and check for age of
      // returned centroids - centroids MUST have been placed SINCE this
      // cmd impl was started.
      TTL_AutoguiderCentroid centroid;
      do
      {
	try
	{
	  Thread.sleep( 500 );
	}
	catch( InterruptedException ie )
	{
	  logger.log( 2, logName, ie.toString() );
	}

	centroid = ttlAG.getCentroidData();
      }
      while( centroid.getTimestamp().getSeconds() < start.getSeconds() );

      guideStar = ttlAG.getGuideTarget( centroid );

    }
    catch( TTL_SystemException se )
    {

    }
    
    
    stopGuiding = false;
    new Thread( this ).start();

    commandDone.setSuccessful( true );
  }


  /**
   *
   */
  public void run()
  {
    PointingOrigin po;
    AGG_GuidePacket gp;
    XYZMatrix desired, actual;
    double x, y;

    while( ( telescope.getSoftwareState() != SoftwareState.ERROR )&&
	   ( stopGuiding != true ) )
    {
      try
      {
	po = ttlAG.getGuideTargetXiEta();

	pmc.setIA( pmc.getIA() + po.getX() );
	pmc.setIE( pmc.getIE() + po.getY() );
      }
      catch( TTL_SystemException se )
      {
	logger.log( 1, logName, se );
	stopGuiding = true;
      }
    }

    // as now out of guide loop remove coefficients from PointingModel
    telescope.getMount().getPointingModel().removeCoefficients( pmc );
  }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: AUTOGUIDEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AUTOGUIDEImplementor.java,v $
 *      $Id: AUTOGUIDEImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
