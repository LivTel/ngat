package ngat.ngtcs.command.execute;
 
import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * The GOTO command is implemented by setting all necessary states and
 * variables in the <code>execute</code> method.  The thread is then started
 * and calculates the XYZMatrix position vector in the Horizon (Alt-Az)
 * coordinate system for the current timestamp triggered by the Timer in
 * Telescope.  The XYZMatrix is right-handed (as per the mathematical norm) and
 * should be converted to a mount-specific form in the Mount sub-class.
 * <p>
 * The tracking position error is calculated in both the execute method and the
 * run method.
 * <p>
 * In the execute method the position error is used to determine success or
 * failure of the command. Success is achieved if the telescope position error
 * is less than the MAX_TRACKING_ERROR for 3 seconds, within 500 seconds (large
 * due to slew-time considerations). If the telescope position error fails to
 * fall below the MAX_TRACKING_ERROR for a period of 3 seconds, within 500
 * seconds of this command being executed, the telescope is stopped and a
 * success = false is returned.
 * <p>
 * In the run method the position error determines the state of the telescope,
 * i.e. SLEWING or TRACKING.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class GOTOImplementor extends CommandImplementor
  implements java.lang.Runnable
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: GOTOImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

  protected Target target;

  protected ReportedTarget reported;

  protected Timestamp timestamp;

  protected TimestampedPosition demand;

  protected TimestampedPosition oldTP, newTP;

  protected double newRotPos, oldRotPos;

  protected double wavelength;

  protected GOTO gotoCommand;

  protected Mount mount;

  protected XYZMatrix obsRADec, meanRADec;

  protected double obsRA, obsDec, meanRA, meanDec;

  protected VirtualTelescope activeVT;

  protected PointingModel pointing;

  protected List limits;

  protected Timer timer;

  protected Object positionLock = new Object();

  protected Equinox rotatorEquinox;

  protected double desiredPositionAngle;

  protected boolean equatorialRotation = false;

  protected AstrometryCalculator astroCalc;


  /**
   * The GOTOImplementor is instantiated. This sets the telescope state to
   * IDLE to stop any other GOTO commands.
   */
  public GOTOImplementor( ExecutionThread eT, Telescope ts, Command c )
  {
    super( eT, ts, c );
    telescope.setTelescopeState( TelescopeState.IDLE );
  }


  /**
   * The execute method. This methods executes the following
   * <ul>
   * <li> executes a STOP command</li>
   * <li> sets all relevent data (obtained from Telescope)</li>
   * <li> performs the rotator mode transformations</li>
   * <li> starts the GOTO thread</li>
   * <li> monitors the telescope position error for success or failure</li>
   * <li> retunrs with the appropriate success state</li>
   * </ul>
   */
  public void execute()
  {
    // stop previous gotos
    STOPImplementor stop = new STOPImplementor
      ( executionThread, telescope, 
	new STOP( command.getId() ) );
    stop.execute();

    // set relevant mechanisms
    timer       = telescope.getTimer();
    timestamp   = timer.trigger();
    mount       = telescope.getMount();
    pointing    = mount.getPointingModel();
    gotoCommand = (GOTO)command;
    target      = gotoCommand.getTarget();
    activeVT    = telescope.getActiveVirtualTelescope();
    astroCalc   = telescope.getAstrometryCalculator();
    wavelength  = activeVT.getWavelength();

    telescope.setCurrentTarget( target );
    target.setNonSiderealTrackingStart( timestamp );


    // convert rotatorMode into either SKY or MOUNT system.
    RotatorMode rotatorMode = telescope.getRotatorMode();
    double desiredPositionAngle = telescope.getPositionAngle();

    // set desired PA to be that of the current rotator angle at the
    // first demanded mount position of the target
    if( ( rotatorMode == RotatorMode.FLOATING_SKY_POSITION )||
	( rotatorMode == RotatorMode.FLOATING_VERTICAL_POSITION ) )
    {
      // send target down through VT to obtain Az,El
      XYZMatrix targetPos = astroCalc.generatePositionDemand
	( timestamp, target, wavelength );


      // apply pointing model stuff here


      desiredPositionAngle = activeVT.calcPositionAngle
	( timestamp, targetPos );
    }

    if( rotatorMode == RotatorMode.VERTICAL_POSITION )
      //      desiredPositionAngle -=
      //	activeVT.getInstrumentAlignmentAngle();

    // set old and new time position so that GOTO is not obtained
    // immediately and no null positions are given
    oldTP = new TimestampedPosition
      ( new XYZMatrix( 0.0, 0.0, 1.0 ), timestamp );
    newTP = new TimestampedPosition
      ( new XYZMatrix( 0.0, 1.0, 0.0 ),
	new Timestamp( 0, 0, CalendarType.GREGORIAN,
		       TimescaleType.UTC ) );


    System.err.println( "starting GOTO thread..." );
    Thread t = new Thread( this );
    t.setPriority( Thread.MAX_PRIORITY );
    t.start();

    telescope.setTelescopeState( TelescopeState.SLEWING );

    int nAck = 0;
    int gotoOK = 0;
    double mountPositionError = 0.0;

    // either timeout after 500 seconds or if thread is killed
    while( ( nAck < 50 ) && ( t.isAlive() ) )
    {
      gotoOK = 0;

      try
      {
	Thread.sleep( 10000 );
      }
      catch( InterruptedException ie )
      {
	logger.log( 1, logName, ie );
      }
      Acknowledge ack = new Acknowledge
	( command.getId()+"."+( nAck++ ), command );
      ack.setTimeToComplete( 15000 );

      executionThread.sendAcknowledge( (Acknowledge)ack );

      // check position error
      // if GOTO is OK for 3 seconds return success
      mountPositionError = calcMountPositionError();
      while( mountPositionError < telescope.MAX_TRACKING_ERROR )
      {
	gotoOK++;

	try
	{
	  Thread.sleep( 500 );
	}
	catch( InterruptedException ie )
	{
	  logger.log( 1, logName, ie );
	}

	mountPositionError = calcMountPositionError();

	if( gotoOK >= 6 )
	{
	  telescope.setTelescopeState
	    ( TelescopeState.TRACKING );
	  logger.log( 3, logName,
		      "telescope tracking" );
	  commandDone.setSuccessful( true );
	  return;
	}
      }
    }
    if( nAck >= 50 )
      returnMessage = "could not reach tracking tolerance";
    else
      returnMessage = "GOTO thread has mysteriously died";

    logger.log( 1, logName, returnMessage );
    stop.execute();
  }


  /**
   * The GOTO thread. This thread executes a while loop under conditional
   * testing of the telescope state. In the loop, mount position demands
   * are continously calculated and sent to the mount. The telescope
   * position error is also calculated to determine the telescope state
   * (SLEWING) or (TRACKING).
   */
  public void run()
  {
    double positionError, rotatorPos;
    XYZMatrix mountPos;
    TelescopeState telescopeState = TelescopeState.TRACKING;
    TimestampedPosition upperTP, lowerTP;
    XYZMatrix upperPos, lowerPos;




    TimestampedPosition observedTP;
    TimestampedPosition demandTP;
    XYZMatrix observedPos;
    XYZMatrix correctedPostureTarget;
    XYZMatrix upperCorrectedPostureTarget;
    XYZMatrix lowerCorrectedPostureTarget;
    double rotatorAngle;
    double instrumentAngle;
    double positionAngle;
    XYZMatrix mountDemand;
    double rotatorDemand;










    // used until `all threads have been stopped' is implemented
    try
    {
      Thread.sleep( 1000 );
    }
    catch( InterruptedException ie )
    {
      logger.log( 1, logName, ie );
      return;
    }
    // NEEDS replacing


    System.out.println( "entering tracking loop" );

    while( ( telescope.getSoftwareState() == SoftwareState.OKAY )&&
	   ( ( telescope.getTelescopeState() == TelescopeState.TRACKING )||
	     ( telescope.getTelescopeState() == TelescopeState.SLEWING ) ))
    {
      // calc VT output
      observedPos = activeVT.calcMountPosition
	( timestamp, target );
      rotatorAngle = activeVT.calcRotatorDemand
	( timestamp, target );

      // apply AN and AW from PointingModel
      correctedPostureTarget =
	pointing.applyAttitudeCorrection( observedPos );

      // apply pointing model corrections
      mountDemand = pointing.applyPositionCorrection
	( correctedPostureTarget );
      rotatorDemand = pointing.applyRotatorCorrection
	( rotatorAngle );
      demandTP = new TimestampedPosition( mountDemand,
					  timestamp );

      // update values for position error
      synchronized( positionLock )
      {
	oldTP = newTP;
	newTP = demandTP;
	oldRotPos  = newRotPos;
	newRotPos  = rotatorDemand;
      }

      logger.log( 4, logName,
		  "sending position demand : \n"+
		  demandTP.getPosition()+
		  "\n     and rotator demand : "+rotatorDemand+"\n"+
		  "\n           at timestamp : "+timestamp );

      // send demands
      mount.sendPositionDemand( demandTP );
      //      mount.sendRotatorDemand( rotatorDemand, timestamp );

      // monitor position error
      positionError = calcMountPositionError();
      if( positionError >= telescope.MAX_TRACKING_ERROR )
      {
	if( telescopeState == TelescopeState.TRACKING )
	{
	  telescope.setTelescopeState
	    ( TelescopeState.SLEWING );
	  telescopeState = TelescopeState.SLEWING;
	  logger.log
	    ( 1, logName, "position error exceeded" );
	  logger.log
	    ( 4, logName, "entered SLEWING state" );
	}
      }
      else
      {
	if( telescopeState == TelescopeState.SLEWING )
	  telescope.setTelescopeState
	    ( TelescopeState.TRACKING );
	{
	  telescopeState = TelescopeState.TRACKING;
	  logger.log( 4, logName,
		      "entered TRACKING state" );
	}
      }
      timestamp = timer.trigger();
    }

    System.out.println( "exited tracking loop" );

    target    = null;
    limits    = null;
    timestamp = null;
    demand    = null;
  }


  /**
   * This method calculates the position error for the current telescope
   * position compared to that demanded of the mount.
   * @return the position error in arcseconds
   */
  protected double calcMountPositionError()
  {
    TimestampedPosition oldDemand, newDemand, mountPosTime;
    double oldTime, newTime, mountTime;
    double x1, y1, z1, x2, y2, z2, xM, yM, zM;
    double oldRotPos, newRotPos, mountRotPos;
    double posError, rotError;
    double xIdeal, yIdeal, zIdeal, timeCoeff, posDiff;
    XYZMatrix oldPos, newPos, mountPos;

    synchronized( positionLock )
    {
      oldDemand = oldTP;
      newDemand = newTP;
    }

    oldTime = oldDemand.getTimestamp().getTime();
    newTime = newDemand.getTimestamp().getTime();

    oldPos = oldDemand.getPosition();
    newPos = newDemand.getPosition();

    mountPosTime = mount.getTimestampedPosition();
    mountTime = mountPosTime.getTimestamp().getTime();
    mountPos = mountPosTime.getPosition();

    x1 = oldPos.getX();
    y1 = oldPos.getY();
    z1 = oldPos.getZ();
    x2 = newPos.getX();
    y2 = newPos.getY();
    z2 = newPos.getZ();
    xM = mountPos.getX();
    yM = mountPos.getY();
    zM = mountPos.getZ();

    timeCoeff = ( mountTime - oldTime ) / ( newTime - oldTime );

    xIdeal = x1 + timeCoeff * ( x2 - x1 );
    yIdeal = y1 + timeCoeff * ( y2 - y1 );
    zIdeal = z1 + timeCoeff * ( z2 - z1 );

    posDiff = Math.sqrt( ( xIdeal - xM ) * ( xIdeal - xM ) +
			 ( yIdeal - yM ) * ( yIdeal - yM ) +
			 ( zIdeal - zM ) * ( zIdeal - zM ) );
			   
    posError = ( 2.0 * Math.asin( 0.5 * posDiff ) );

    // need to implement rotator position error
    rotError = 0.0;

    return( rotError > posError ? rotError : posError );
  }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: GOTOImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/GOTOImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/02 11:10:41  je
 *     Initial revision
 *
 */
