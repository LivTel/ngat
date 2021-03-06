package ngat.ngtcs.subsystem;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.ags.*;
import ngat.ngtcs.subsystem.amn.*;
import ngat.ngtcs.subsystem.sdb.*;

/**
 * This class represents the TTL Autoguider System.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_Autoguider extends BasicMechanism
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
    new String( "$Id$" );

  /**
   * The single instance of this class.
   */
  protected static TTL_Autoguider instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL AGS subsystem used by this autoguider.
   */
  protected AGS ags = null;

  /**
   * The TTL AGG subsystem used by this autoguider.
   */
  protected AGG agg = null;

  /**
   * The TTL AMN subsystem used by this autoguider.
   */
  protected AMN amn = null;

  /**
   * The TTL SDB subsystem used by this autoguider.
   */
  protected SDB sdb = null;

  /**
   * VirtualTelescope object used to perform the astrometry in the autoguider
   * correction.
   */
  protected VirtualTelescope vt;

  /**
   * The maximum acceptable position error that will return a successfully
   * acheived demand of the autoguider position.  This value is read from the
   * config file.
   */
  protected double positionTolerance = 0.0;

  /**
   * The maximum acceptable position error that will return a successfully
   * acheived demand of the autoguider focus.  This value is read from the
   * config file.
   */
  protected double focusPositionTolerance = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the ONLY instance of this TTL_Autoguider class.
   * <br>
   * If this class is previously uninstantiated a new object will be created,
   * otherwise the previous instantiation is returned.
   * @return the ONLY instance of this class.
   */
  public static PluggableSubSystem getInstance()
  {
    if( instance == null )
      instance = new TTL_Autoguider();

    return instance;
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The non-public Constructor required by singleton classes.
   */
  protected TTL_Autoguider()
  {
    ags = AGS.getInstance();
    agg = AGG.getInstance();
    amn = AMN.getInstance();
    sdb = SDB.getInstance();
  }


  /**
   *
   */
  protected void _initialise( Telescope t )
    throws InitialisationException
  {
    String name = logName, units = null;
    double x = 0.0, y = 0.0, scale = 0.0, iaa = 0.0, focus = 0.0;
    double focalLength = 0.0;
    int portNumber = 0;

    // get properties file
    getProperties();

    try
    {
      x = np.getDouble( "centreFOV.X" );
      logger.log( 3, logName, "centreFOV.X = "+x );

      y = np.getDouble( "centreFOV.Y" );
      logger.log( 3, logName, "centreFOV.Y = "+y );

      focus = np.getDouble( "focusPosition" );
      logger.log( 3, logName, "focusPosition = "+focus );

      scale = np.getDouble( "unitScale" );
      logger.log( 3, logName, "unitScale = "+scale );

      iaa = np.getDouble( "detectorAngle" );
      logger.log( 3, logName, "detectorAngle = "+iaa );

      units = np.getProperty( "units" );
      logger.log( 3, logName, "units = "+units );

      portNumber = np.getInt( "portNumber" );
      logger.log( 3, logName, "portNumber = "+portNumber );

      positionTolerance = np.getDouble( "positionTolerance" );
      logger.log( 3, logName, "positionTolerance = "+positionTolerance );

      focusPositionTolerance = np.getDouble( "focusPositionTolerance" );
      logger.log
	( 3, logName, "focusPositionTolerance= "+focusPositionTolerance );

      focalLength = np.getDouble( "focalLength" );
      logger.log( 3, logName, "focalLength = "+focalLength );
    }
    catch( NGATPropertyException npe )
    {
      String err = new String( "Cannot initialise "+logName+" : "+npe );
      logger.log( 1, logName, err );
      throw new InitialisationException( err );
    }

    // define FocalStation
    if( units == null )
      throw new InitialisationException
	( "Need \"units\" field in configuring the "+
	  "autoguider VirtualTelescope" );

    FocalStation fs = new FocalStation
      ( name, portNumber, focus, new PointingOrigin( x, y ),
	iaa, scale, units );;

    // create new Virtual Telescope to look after the transformations
    vt = new VirtualTelescope( telescope, fs );

    initialised = true;
  }


  /**
   *
   */
  public State getState()
  {
    AGS_State agsState = get_AGS_State();
    AGS_Status agsStatus = get_AGS_Status();
    AGG_State aggState = get_AGG_State();
    AGG_Status aggStatus = get_AGG_Status();
    AGD_State agdState = get_AGD_State();
    AGD_Status agdStatus = get_AGD_Status();
    AGF_State agfState = get_AGF_State();
    AGF_Status agfStatus = get_AGF_Status();

    return( (State)( new TTL_AutoguiderState
		     () ) );
  }


  /**
   *
   */
  protected void _shutdown() throws SystemException
  {
    return;
  }


  /**
   *
   */
  public void makeSafe() throws SystemException
  {
    return;
  }


  /**
   * Set the observing wavelength for the guide system.
   * @param d the new wavelength in metres.
   */
  public void setWavelength( double d )
  {
    vt.setWavelength( d );
  }


  /**
   * Change the position of the guide target.
   * @param po the new PointingOrigin of the target
   */
  public void setPointingOrigin( PointingOrigin po )
  {
    vt.setPointingOrigin( po );
  }


  /**
   * Given the PointingOrigin and Mount position for the specified time,
   * calculate and return the coordinates of the guide Target.
   */
  public Target getGuideTarget( TTL_AutoguiderCentroid centroid )
    throws TTL_SystemException
  {
    // need to do something with focalStation, gp (X,Y) and
    // pointingOrigin for focalStation
    //
    // NEED to change the prediction and get routines so the correct IAA is
    // added/subtracted etc.
    //
    // ALSO - 1 (better) level of abstraction.
    //
    //double r = vt.predictPositionAngle( t );
    //XYZMatrix m  = vt.predictPosition( t );

    //return( (Target)( vt.calcTargetCoordinates( t, m, r ) ) );

    return( new Target() );
  }


  /**
   *
   */
  public PointingOrigin getGuideTargetXiEta()
    throws TTL_SystemException
  {
    AGG_GuidePacket gp = agg.getGuidePacket();

    //use autoguider pixel scale and telescope focal length to get
    //Xi,Eta in radians, and return.

    return( new PointingOrigin( 0.0, 0.0 ) );
  }


  /**
   * This method calls slightly lower level functions
   * (<code>sdb.retrieveValue</code>) to obtain both centroid values, and the
   * Timestamp for the <b>NEWEST</b> value.
   */
  public TTL_AutoguiderCentroid getCentroidData()
    throws TTL_SystemException
  {
    double x, y;
    Timestamp t;
    SDB_DataType demands[] = new SDB_DataType[ 2 ];
    TTL_DataValue vals[] = new TTL_DataValue[ 2 ];

    demands[ 0 ] = new SDB_DataType
      ( (TTL_DataType)AGS_DataType.D_AGS_CENTROIDX, TTL_CIL_Node.E_CIL_AGS );
    demands[ 1 ] = new SDB_DataType
      ( (TTL_DataType)AGS_DataType.D_AGS_CENTROIDY, TTL_CIL_Node.E_CIL_AGS );

    vals = sdb.retrieveMultipleValues( demands );

    x = (double)( vals[ 0 ].getValue() / 1000.0 );
    t = vals[ 0 ].getTimestamp();
    y = (double)( vals[ 1 ].getValue() / 1000.0 );

    if( vals[ 1 ].getTimestamp().getTime() > t.getTime() )
      t = vals[ 1 ].getTimestamp();

    return new TTL_AutoguiderCentroid( x, y, t );
  }


  /**
   * Return the maximum acceptable position error that will return a
   * successfully acheived demand of the autoguider focus.
   * @return focusPositionTolerance
   * @see #focusPositionTolerance
   */
  public double getFocusPositionTolerance()
  {
    return focusPositionTolerance;
  }


  /**
   * Return the maximum acceptable position error that will return a
   * successfully acheived demand of the autoguider position.
   * @return positionTolerance
   * @see #positionTolerance
   */
  public double getPositionTolerance()
  {
    return positionTolerance;
  }

  /*=========================================================================*/

  /*=========================================================================*/
  /*                                                                         */
  /* AGS commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Request the operational state of the AGS.
   * @return the operational state as an AGS_State object
   */
  public AGS_State get_AGS_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGS_DataType.D_AGS_AGSTATE,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (AGS_State)( AGS_State.getReference( val.getValue() ) ) );
  }

  /**
   * Command the AGS to start guiding on the brightest object in the
   * autoguider frame.
   */
  public void guideOnBrightest()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_AUTOGUIDE_ON_BRIGHTEST, 0, 0 );
  }


  /**
   * Command the AGS to start guiding on the brightest object between the
   * specified magnitudes in the autoguider frame.
   * @param min the minimum magnitude of the guide target
   * @param max the maximum magnitude of the guide target
   */
  public void guideOnRange( double min, double max )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_AUTOGUIDE_ON_RANGE,
		     ( (int)( min * 1000.0 ) ),
		     ( (int)( max * 1000.0 ) ) );
  }


  /**
   * Command the AGS to start guiding on the
   * <b><font size=+1>n<sup>th</sup></font></b> brightest object in the
   * autoguider frame.
   * @param n the rank of brightness of object to guide on
   */
  public void guideOnRank( int n )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_AUTOGUIDE_ON_RANK, n, 0 );
  }


  /**
   * Command the AGS to start guiding on the object located at the specified
   * pixel coordinates.
   * @param x the X pixel coordinate
   * @param y the Y pixel coordinate
   */
  public void guideOnPixel( double x, double y )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_AUTOGUIDE_ON_PIXEL,
		     ( (int)( x * 1000.0 ) ),
		     ( (int)( y * 1000.0 ) ) );
  }

  /**
   * Command the AGS to stop guiding.
   */
  public void stopGuiding()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_AUTOGUIDE_OFF, 0, 0 );
  }

  /**
   * Set the exposure time of the autoguider CCD.
   * @param t the new exposure time in diddlyseconds.
   */
  public void setExposureTime( int t )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_CONF_EXP_TIME, t, 0 );
  }

  /**
   *
   */
  public void setFrameRate( int i )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_CONF_FRAME_RATE, i, 0 );
  }

  /**
   *
   */
  public void setFrameAverage( int i )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_CONF_FRAME_AVERAGE, i, 0 );
  }

  /**
   *
   */
  public void configureCalibration( int i1, int i2 )
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_CONF_CALIB, i1, i2 );
  }

  /**
   * Prepare the AGS for guiding. diddly.
   */
  public void startSession()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_START_SESSION, 0, 0 );
  }


  /**
   * 
   */
  public void endSession()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_END_SESSION, 0, 0 );
  }


  /**
   *
   */
  public void startLogging()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_LOGGING,
		     AGG_Keyword.E_AGG_ON.getInt(), 0 );
  }


  /**
   *
   */
  public void stopLogging()
    throws TTL_SystemException
  {
    ags.sendCommand( AGS_Service.E_AGS_LOGGING,
		     AGG_Keyword.E_AGG_OFF.getInt(), 0 );
  }


  /*=========================================================================*/

  /*=========================================================================*/
  /*                                                                         */
  /* AGS data in the SDB                                                     */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the X pixel coordinate of the guiding centroid currently stored
   * in the SDB.
   * @return the X pixel coordinate
   */
  public double getCentroidXPixel()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGS_DataType.D_AGS_CENTROIDX,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (double)( val.getValue() ) / 1000.0 );
  }


  /**
   * Return the Y pixel coordinate of the guiding centroid currently stored
   * in the SDB.
   * @return the Y pixel coordinate
   */
  public double getCentroidYPixel()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGS_DataType.D_AGS_CENTROIDY,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (double)( val.getValue() ) / 1000.0 );
  }


  /**
   * Return the Full-Width Half-Maximum of the guiding centroid currently
   * stored in the SDB, in pixels.
   * @return the guide object FWHM in pixels
   */
  public double getCentroidFWHM()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGS_DataType.D_AGS_FWHM,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (double)( val.getValue() ) / 1000.0 );
  }


  /**
   * Return the magnitude of the guiding centroid currently stored in the
   * SDB.
   * @return guide object magnitude
   */
  public double getCentroidMagnitude()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGS_DataType.D_AGS_GUIDEMAG,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (double)( val.getValue() ) / 1000.0 );
  }


  //========================================================================

  /*=========================================================================*/
  /*                                                                         */
  /* AMN commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  // Autoguider Deployment
  // =====================

  /**
   * Request the operational state of the AGD.
   * @return the operational state as an AGD_State object
   */
  public AGD_State get_AGD_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGD_DataType.D_AGD_STATE,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (AGD_State)( AGD_State.getReference( val.getValue() ) ) );
  }


  /**
   * Set the autoguider position demand to that specified.
   * @param i the position demand in millimetres
   */
  public void setDemandPosition( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( AGD_DataType.D_AGD_DEMAND,
			 (int)( d * 1000.0 ) );
    amn.setValue( val );
  }


  /**
   * Return the demanded autguider position in millimetres.
   * @return the demanded position
   */
  public double getDemandPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGD_DataType.D_AGD_DEMAND,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (double)( val.getValue() ) / 1000.0 );
  }


  /**
   * Return the actual autoguider position in millimetres.
   * @return the actual position
   */
  public double getActualPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
    ( new SDB_DataType( AGD_DataType.D_AGD_ACTUAL,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( ( (double)val.getValue() ) / 1000.0 );
  }


  // Autoguider Filter Deployment
  // ============================

  /**
   * Command the AMN to deploy the autoguider filter.
   */
  public void deployFilter()
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( AGD_DataType.D_AGD_FILTER_DEMAND,
	AGD_FilterPosition.E_AGD_IR_IN_LINE.getInt() );
    amn.setValue( val );
  }


  /**
   * Command the AMN to retract the autoguider filter.
   */
  public void retractFilter()
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( AGD_DataType.D_AGD_FILTER_DEMAND,
	AGD_FilterPosition.E_AGD_IR_RETRACT.getInt() );
    amn.setValue( val );
  }


  /**
   * Return the demanded filter position as an AGD_FilterPosition object.
   * @return the demanded position
   */
  public AGD_FilterPosition getDemandFilterPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGD_DataType.D_AGD_FILTER_DEMAND,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (AGD_FilterPosition)( AGD_FilterPosition.getReference
				  ( val.getValue() ) ) );
  }


  /**
   * Return the actual filter position as an AGD_FilterPosition object.
   * @return the actual position
   */
  public AGD_FilterPosition getActualFilterPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGD_DataType.D_AGD_FILTER_ACTUAL,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (AGD_FilterPosition)( AGD_FilterPosition.getReference
				  ( val.getValue() ) ) );
  }


  /**
   * Set the demanded filter position to the specified AGD_FilterPosition
   * object.
   * @param p the new filter position demand
   */
  public void setDemandFilterPosition( AGD_FilterPosition p )
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( AGD_DataType.D_AGD_FILTER_DEMAND, p.getInt() );
    amn.setValue( val );
  }


  // Autoguider Focus

  /**
   * Request the operational state of the AGF.
   * @return the operational state as an AGF_State object
   */
  public AGF_State get_AGF_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGF_DataType.D_AGF_STATE,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( (AGF_State)( AGF_State.getReference( val.getValue() ) ) );
  }


  /**
   * Return the actual focus position in millimetres.
   * @return the actual position
   */
  public double getActualFocusPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGF_DataType.D_AGF_ACTUAL,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( ( (double)val.getValue() ) / 1000.0 );
  }


  /**
   * Set the demanded focus position in millimetres
   * @param d the new focus position demand
   */
  public void setDemandFocusPosition( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( AGF_DataType.D_AGF_DEMAND, (int)( d * 1000.0 ) );
    amn.setValue( val );
  }


  /**
   * Return the demanded focus position in millimetres
   * @param d the new focus position demand
   */
  public double getDemandFocusPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AGF_DataType.D_AGF_DEMAND,
			  TTL_CIL_Node.E_CIL_AGS ) );
    return( ( (double)val.getValue() ) / 1000.0 );
  }
}
/*
 *    $Date: 2013-07-04 10:55:18 $
 * $RCSfile: TTL_Autoguider.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Autoguider.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/24 13:07:51  je
 *     Added positionTolerance and focusPositionTolerance.
 *
 *     Revision 1.2  2003/09/22 11:29:02  je
 *     Changed '...DemandedPosition' for '...DemandPosition'
 *
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 */
