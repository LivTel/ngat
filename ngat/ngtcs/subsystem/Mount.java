package ngat.ngtcs.subsystem;
 
import java.util.*;

import ngat.util.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public abstract class Mount extends BasicMechanism
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: Mount.java,v 1.2 2013-07-04 10:54:21 cjm Exp $" );


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Previous timestamp of previous mount position.
   */
  protected TimestampedPosition oldTP = new TimestampedPosition
    ( new XYZMatrix(), new Timestamp() );

  /**
   * Current timestaped position of Mount.
   */
  protected TimestampedPosition currentTP = new TimestampedPosition
    ( new XYZMatrix(), new Timestamp() );

  /**
   * new timestaped position demand of Mount.
   */
  protected TimestampedPosition demandTP = new TimestampedPosition
    ( new XYZMatrix(), new Timestamp() );

  /**
   * Timer for this Mount's Telescope.
   */
  protected Timer timer;

  /**
   * Pointing Model for this Mount.
   */
  protected PointingModel pointingModel;

  /**
   * AstrometryCalculator used by the Telescope for which this is the Mount.
   */
  protected AstrometryCalculator astroCalc;

  /**
   * Lower limit of Telescope operation in radians.
   */
  protected double LOWER_LIMIT = Math.toRadians( 20.0 );

  /**
   * Upper limit of Telescope operation in radians (blind spot).
   */
  protected double UPPER_LIMIT = Math.toRadians( 88.0 );

  /**
   * Flag to represent enter state of Limit.
   */
  protected final boolean enter = false;

  /**
   * Flag to represent exit state of Limit.
   */
  protected final boolean exit = true;

  /**
   * Object used to protect the access synchronisation of the current position.
   */
  protected Object positionLock = new Object();

  /**
   * Length of the first in-first out list.
   */
  private final int FIFO_SIZE = 100;

  /**
   * Next index to update in the position FIFO.
   */
  private int positionIndex = 0;

  /**
   * Next index to update in the demand FIFO.
   */
  private int demandIndex = 0;

  /**
   * First in-first out list of timestamped positions of this Mount.
   */
  private Vector positionFIFO = new Vector( FIFO_SIZE );

  /**
   * First in-first out list of timestamped demands of this Mount.
   */
  private Vector demandFIFO = new Vector( FIFO_SIZE );

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Constructor for Mount objects.
   */
  public Mount()
  {
    super();
  }


  /**
   * Initialise the Mount for operation.
   */
  protected void _initialise( Telescope t ) throws InitialisationException
  {
    getProperties();
    String pmName = np.getProperty( "pointingModel.class" );
    if( pmName == null )
    {
      InitialisationException ie = new InitialisationException
	( "Mount : No specified pointingModel class" );
      logger.log( 1, logName, ie );
      throw ie;
    }

    try
    {
      pointingModel = (PointingModel)
	( Class.forName( pmName ).newInstance() );
      pointingModel.initialise( telescope );
    }
    catch( Exception e )
    {
      InitialisationException ie = 
	new InitialisationException( "Mount : "+e );
      logger.log( 1, logName, ie );
      throw ie;
    }
    logger.log( 1, logName, "Pointing Model ["+pmName+"] initialised" );
  }


  /**
   * Return the PointingModel object used with this Mount.
   * @return the PointingModel for this mount.
   */
  public PointingModel getPointingModel()
  {
    return pointingModel;
  }


  /**
   * Send the specified position demand to the Mount Control System.
   * @param timePos the Timestamped position demand
   */
  public final void sendPositionDemand( TimestampedPosition newTP )
    throws SystemException
  {
    track( newTP );
    demandFIFO.set( demandIndex, newTP );
    demandIndex++;
    demandIndex %= FIFO_SIZE;
  }


  /**
   * Retrieve the current position of the Mount from the Mount Control 
   * System.
   * @return the latest Timestamped position from the Mount Control System
   */
  public final TimestampedPosition getCurrentPosition()
    throws SystemException
  {
    TimestampedPosition tp = getLatestTimestampedPosition();
    positionFIFO.set( positionIndex, tp );
    positionIndex++;
    positionIndex %= FIFO_SIZE;
    return( tp );
  }


  /**
   * 
   */
  public final TimestampedPosition getTimestampedDemand( Timestamp t )
    throws SystemException
  {
    return( calcTimestampedPosition( t, demandIndex, demandFIFO ) );
  }


  /**
   *
   */
  public final TimestampedPosition getTimestampedPosition( Timestamp t )
    throws SystemException
  {
    return( calcTimestampedPosition( t, positionIndex, positionFIFO ) );
  }


  /**
   *
   */
  protected abstract void track( TimestampedPosition tp )
    throws SystemException;


  /**
   *
   */
  protected abstract void move( XYZMatrix m )
    throws SystemException;


  /**
   *
   */
  protected abstract TimestampedPosition getLatestTimestampedPosition()    
    throws SystemException;


  /**
   * Return the type of Mount that this is.
   * @return one of the pre-defined MountTypes
   */
  public abstract MountType getType();


  /**
   *
   *
   */
  public abstract List calculateLimits( Target target );


  /**
   * This method calculates the position error for the current mount
   * position compared to the demanded.
   * @return the position error in arcseconds
   */
  public final double calcPositionError()
    throws SystemException
  {
    TimestampedPosition curTP = getLatestTimestampedPosition();
    TimestampedPosition demTP = getTimestampedDemand( curTP.getTimestamp() );

    // implies NO demands are within 0.5 seconds of now
    if( demTP == null )
      return( 0.0 );

    XYZMatrix curPos = curTP.getPosition();
    XYZMatrix demPos = demTP.getPosition();

    double x1 = curPos.getX();
    double y1 = curPos.getY();
    double z1 = curPos.getZ();
    double x2 = demPos.getX();
    double y2 = demPos.getY();
    double z2 = demPos.getZ();

    double posDiff = Math.sqrt( ( x2 - x1 ) * ( x2 - x1 ) +
				( y2 - y1 ) * ( y2 - y1 ) +
				( z2 - z1 ) * ( z2 - z1 ) );

    double posError = ( 2.0 * Math.asin( 0.5 * posDiff ) );

    return( posError * 180.0 / Math.PI );
  }


  /**
   *
   */
  protected final TimestampedPosition calcTimestampedPosition( Timestamp t,
							       int i,
							       Vector v )
  {
    int n;
    TimestampedPosition tpLower, tpUpper;
    Timestamp t1;
    double timestamp = t.getTime();

    for( n = 1; n <= FIFO_SIZE; n++ )
    {
      // get last position
      tpLower = (TimestampedPosition)v.get
	( ( i - n + FIFO_SIZE ) % FIFO_SIZE );
      if( tpLower != null )
      {
	t1 = tpLower.getTimestamp();
	// see if position is older than t
	if( t1.getTime() < timestamp )
	{
	  // see if only just older
	  //USE GET METHOD instead of 0.5s
	  if( ( timestamp - t1.getTime() ) < 0.50000000 )
	  {
	    // get next newest position
	    tpUpper = (TimestampedPosition)v.get
	      ( ( i - n + FIFO_SIZE + 1 ) % FIFO_SIZE );
	    if( tpUpper != null )
	    {
	      t1 = tpUpper.getTimestamp();
	      // see if position is newer
	      if( t1.getTime() > timestamp )
	      {
		// see if only just newer
		if( ( t1.getTime() - timestamp ) < 0.50000000 )
		{
		  // interpolate results and return
		  return( calcInterpolatedPosition( tpLower, tpUpper, t ) );
		}
	      }
	    }
	  }
	}
      }
    }
    return( null );
  }


  /**
   * This method will calculate a TimestampedPosition, using a linear
   * interpolation beteen tp1 and tp2, solved at the point defined by t.
   * @param tp1 the start Timestamp time and XYZMatrix position
   * @param tp2 the end Timestamp time and XYZMatrix position
   * @param t the timestamp for the desired result
   */
  protected final TimestampedPosition calcInterpolatedPosition
    ( TimestampedPosition tp1, TimestampedPosition tp2, Timestamp t )
  {
    XYZMatrix m1 = tp1.getPosition();
    XYZMatrix m2 = tp2.getPosition();

    double x1 = m1.getX();
    double y1 = m1.getY();
    double z1 = m1.getZ();
    double x2 = m2.getX();
    double y2 = m2.getY();
    double z2 = m2.getZ();

    double xDiff = x2 - x1;
    double yDiff = y2 - y1;
    double zDiff = z2 - z1;

    double t1 = tp1.getTimestamp().getTime();
    double t2 = tp2.getTimestamp().getTime();

    double timeCoeff = ( t.getTime() - t1 ) / ( t2 - t1 );

    XYZMatrix predicted = new XYZMatrix
      ( ( x1 + xDiff * timeCoeff ),
	( y1 + yDiff * timeCoeff ),
	( z1 + zDiff * timeCoeff ) );

    predicted.normalise();

    return( new TimestampedPosition( predicted, t ) );
  }
}
/*
 *    $Date: 2013-07-04 10:54:21 $
 * $RCSfile: Mount.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Mount.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
