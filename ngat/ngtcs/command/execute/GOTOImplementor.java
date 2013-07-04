package ngat.ngtcs.command.execute;
 
import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Move the telescope to a new source and track it.
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
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class GOTOImplementor extends CommandImplementor
  implements java.lang.Runnable
{
  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS FIELDS.  /*                                                       */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: GOTOImplementor.java,v 1.4 2013-07-04 10:25:03 cjm Exp $" );

  /**
   * The timeout for the GOTO command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT FIELDS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * The target to GOTO.
   */
  protected Target target;

  /**
   * The reported target of the current position.
   */
  protected ReportedTarget reported;

  /**
   * The timestamp for the position demands.
   */
  protected Timestamp timestamp;

  /**
   * The Timestamped position demand.
   */
  protected TimestampedPosition demand;

  /**
   * The old and new timestamped position demands.
   */
  protected TimestampedPosition oldTP, newTP;

  /**
   * The old and new rotator demands.
   */
  protected double newRotPos, oldRotPos;

  /**
   * The currently used wavelength of observation.
   */
  protected double wavelength;

  /**
   * The GOTO command that invoked this implementor.
   */
  protected GOTO gotoCommand;

  /**
   * The mount to send the position demands to.
   */
  protected Mount mount;

  /**
   * The Observed RA,Dec as an XYZMatrix.
   */
  protected XYZMatrix obsRADec;

  /**
   * The catalogue Mean RA,Dec as an XYZMatrix.
   */
  protected XYZMatrix meanRADec;

  /**
   * The Observed RA, in radians.
   */
  protected double obsRA;

  /**
   * The Observed RA, in radians.
   */
  protected double obsDec;

  /**
   * The catalogue Mean RA, in radians.
   */
  protected double meanRA;

  /**
   * The catalogue Mean RA, in radians.
   */
  protected double meanDec;

  /**
   * The currently-active VirtualTelescope, used to define the pointing in this
   * GOTO command execution.
   */
  protected VirtualTelescope activeVT;

  /**
   * The PointingModel used in this GOTO command execution.
   */
  protected PointingModel pointing;

  /**
   * A list of the tracking limits that will impact upon this GOTO command
   * execution.
   */
  protected List limits;

  /**
   * The timer mechanism used to trigger the timestamped positon-demands.
   */
  protected Timer timer;

  /**
   * Object used to protect the access synchronisation of the current position.
   */
  protected Object positionLock = new Object();

  /**
   * The Equinox used in the rotator transformations.
   */
  protected Equinox rotatorEquinox;

  /**
   * The desired rotator position angle.
   */
  protected double desiredPositionAngle;

  /**
   * A boolean describing whether equatorial rotation is to be used.
   */
  protected boolean equatorialRotation = false;

  /**
   * A boolean flag used to stop this GOT command execution.
   */
  protected boolean stopTracking = false;

  /**
   * The AstrometryCalculator used for the transformations.
   */
  protected AstrometryCalculator astroCalc;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS METHODS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT METHODS.  /*                                                     */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * The GOTOImplementor is instantiated. This sets the telescope state to
   * IDLE to stop any other GOTO commands.
   */
  public GOTOImplementor( Telescope ts, Command c )
  {
    super( ts, c );
    telescope.setOperationalMode( OperationalMode.IDLE );
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
      ( telescope, new STOP( command.getId()+"[STOP]" ) );
    stop.execute();

    // set relevant mechanisms
    timer       = telescope.getTimer();
    timestamp   = timer.trigger();
    mount       = telescope.getMount();
    Rotator rotator     = telescope.getRotator();
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


      //
      desiredPositionAngle = activeVT.calcPositionAngle
	( timestamp, targetPos );
    }

    if( rotatorMode == RotatorMode.VERTICAL_POSITION )
    {
      //      desiredPositionAngle -=
      //	activeVT.getInstrumentAlignmentAngle();
    }

    // set old and new time position so that GOTO is not obtained
    // immediately and no null positions are given
    oldTP = new TimestampedPosition
      ( new XYZMatrix( 0.0, 0.0, 1.0 ), timestamp );
    newTP = new TimestampedPosition
      ( new XYZMatrix( 0.0, 1.0, 0.0 ),
	new Timestamp( 0, 0, CalendarType.GREGORIAN,
		       TimescaleType.UTC ) );


    logger.log( 1, logName, "Starting GOTO thread." );
    Thread t = new Thread( this );
    t.setPriority( Thread.MAX_PRIORITY );
    t.start();

    telescope.setOperationalMode( OperationalMode.SLEWING );

    int gotoOK = 0;
    double mountPositionError = 0.0;

    // either timeout after 500 seconds or exit if thread is killed
    while( ( slept < TIMEOUT ) && ( t.isAlive() ) )
    {
      gotoOK = 0;
      sleep( 1000 );

      // check position error
      // if GOTO is OK for 3 seconds return success
      mountPositionError = calcMountPositionError();
      while( mountPositionError < telescope.MAX_TRACKING_ERROR )
      {
	gotoOK++;
	sleep( 500 );
	mountPositionError = calcMountPositionError();

	if( gotoOK >= 6 )
	{
	  telescope.setOperationalMode( OperationalMode.TRACKING );
	  String msg = new String( "Telescope tracking" );
	  logger.log( 3, logName, msg );
	  commandDone.setReturnMessage( msg );
	  commandDone.setSuccessful( true );
	  return;
	}
      }
    }

    String err = null;
    OperationalMode tState = telescope.getOperationalMode();

    if( tState == OperationalMode.ERROR )
    {
      err = new String( "Telescope state is ERROR" );
    }
    else if( tState == OperationalMode.IDLE )
    {
      err = new String
	( "Telescope state has been set to IDLE (STOP command)" );
    }
    else if( slept > TIMEOUT )
    {
      err = new String( "Could not reach tracking tolerance within "+
			TIMEOUT+"ms : execution terminated " );
      stopTracking = true;
    }
    else if( ! t.isAlive() )
    {
      err = new String( "GOTO thread has mysteriously died" );
      stopTracking = true;
    }

    logError( err );
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
    OperationalMode telescopeState = OperationalMode.TRACKING;
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


    logger.log( 2, logName, "Entering tracking loop" );

    while( ( telescope.getSoftwareState() == SoftwareState.OKAY )&&
	   ( ( telescope.getOperationalMode() == OperationalMode.TRACKING )||
	     ( telescope.getOperationalMode() == OperationalMode.SLEWING ) )&&
	   ( stopTracking == false ) )
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

      logger.log( 3, logName,
		  "\nsending position demand : \n"+
		  demandTP.getPosition()+
		  "\n     and rotator demand : "+rotatorDemand+"\n"+
		  "\n          for timestamp : "+timestamp );

      try
      {
	// send demands
	mount.sendPositionDemand( demandTP );
	//      mount.sendRotatorDemand( rotatorDemand, timestamp );

	// monitor position error
	positionError = calcMountPositionError();
      }
      catch( SystemException se )
      {
	logError( "Failed sending position demand : "+se.toString()+
		  "\nterminatinf execution" );
	telescope.setOperationalMode( OperationalMode.ERROR );
	mount.stop();
	rotator.stop();
	
	target    = null;
	limits    = null;
	timestamp = null;
	demand    = null;
	return;
      }

      if( positionError >= telescope.MAX_TRACKING_ERROR )
      {
	if( telescopeState == OperationalMode.TRACKING )
	{
	  telescope.setOperationalMode
	    ( OperationalMode.SLEWING );
	  telescopeState = OperationalMode.SLEWING;
	  logger.log( 1, logName, "position error exceeded" );
	  logger.log( 2, logName, "entered SLEWING state" );
	}
      }
      else
      {
	if( telescopeState == OperationalMode.SLEWING )
	{
	  telescope.setOperationalMode( OperationalMode.TRACKING );
	  telescopeState = OperationalMode.TRACKING;
	  logger.log( 2, logName, "entered TRACKING state" );
	}
      }
      timestamp = timer.trigger();
    }

    logger.log( 2, logName, "Exited tracking loop" );

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
    throws SystemException
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

    mountPosTime = mount.getCurrentPosition();
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
 *    $Date: 2013-07-04 10:25:03 $
 * $RCSfile: GOTOImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/GOTOImplementor.java,v $
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
 *     Revision 1.1  2003/07/02 11:10:41  je
 *     Initial revision
 *
 */
