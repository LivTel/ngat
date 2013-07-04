package ngat.ngtcs.test;

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * Simple GUI to experiment with (x,y), (xi,eta), (RA,Dec) and (alt,az).
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class VTTests implements ActionListener
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
    new String( "$Id: VTTests.java,v 1.2 2013-07-04 13:07:08 cjm Exp $" );

  /**
   * String formatter for the Azimuth.
   */
  protected static DecimalFormat azdf =
    new DecimalFormat( " 000.000;-000.000" );

  /**
   * String formatter for the Altitude.
   */
  protected static DecimalFormat eldf =
    new DecimalFormat( " 00.000;-#0.000" );

  /**
   * String formatter for the pointing origin.
   */
  protected static DecimalFormat podf =
    new DecimalFormat( " 00.0000;-00.0000" );

  /**
   * Use the time now?
   */
  protected static boolean now = false;

  /**
   * Longitude of the observation site.
   */
  protected static double longitude = 0.0;

  /**
   * Latitude of the observation site.
   */
  protected static double latitude = 0.0;

  /**
   * Altitude of the observation site.
   */
  protected static double altitude = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Input panel for Right Ascension.
   */
  protected JRAInputPanel raip = new JRAInputPanel();

  /**
   * Input panel for Declination.
   */
  protected JDecInputPanel dip = new JDecInputPanel();

  /**
   * Input panel for the x position.
   */
  protected JTextField xIn = new JTextField( "0.000000" );

  /**
   * Input panel for the y position.
   */
  protected JTextField yIn = new JTextField( "0.000000" );

  /**
   * Input panel for the rotator position.
   */
  protected JTextField paIn = new JTextField( "000.00" );

  /**
   * Input panel for Xi.
   */
  protected JTextField xiIn = new JTextField( "00.000" );

  /**
   * Input panel for Eta.
   */
  protected JTextField etaIn = new JTextField( "00.000" );

  /**
   * Input panel for the Azimuth.
   */
  protected JTextField azIn = new JTextField( "000.0000" );

  /**
   * Input panel for the Altitude.
   */
  protected JTextField elIn = new JTextField( " 00.0000" );

  /**
   * Input panel for wavelength.
   */
  protected JTextField lambdaIn = new JTextField( "  550.00" );

  /**
   * Input panel for the time in seconds since 01/01/1970
   */
  protected JTextField tIn = new JTextField( 10 );

  /**
   * VirtualTelescope used to perform transformations.
   */
  protected VirtualTelescope vt;

  /**
   * Focal station for the VirtualTelescope.
   */
  protected FocalStation focalStation;

  /**
   * PointingModel used on the VirtualTelescope.
   */
  protected PointingModel pointingModel = new AltAzPointingModel();

  /**
   * Universal error message
   */
  protected String errorMessage;

  protected JFrame frame;
  protected Container frameCon;
  protected GridBagLayout gbl = new GridBagLayout();
  protected GridBagConstraints gbc = new GridBagConstraints();


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  public static void main( String[] args )
  {
    try
    {
      for( int i = 0; i < args.length; )
      {
	if( args[ i ].equals( "-lat" ) )
	  latitude = Double.parseDouble( args[ ++i ] );

	if( args[ i ].equals( "-lon" ) )
	  longitude = Double.parseDouble( args[ ++i ] );

	if( args[ i ].equals( "-alt" ) )
	  altitude = Double.parseDouble( args[ ++i ] );

	if( args[ i ].equals( "-now" ) )
	  now = true;

	i++;
      }
    }
    catch( Exception e )
    {
      System.err.println( e );
      System.exit( 1 );
    }

    JFrame f = new JFrame( "VT Test GUI" );

    f.addWindowListener(
			new WindowAdapter()
			{
			  public void windowClosing( WindowEvent e )
			  {
			    System.err.println( "closing..." );
			    System.exit(0);
			  }
			}
			);
    f.setSize( 800, 600 );
    new VTTests( f );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create the GUI.
   */
  public VTTests( JFrame f )
  {
    JLabel label;
    String degrees = "\u00B0  ";

    frame = f;
    frameCon = frame.getContentPane();
    frameCon.setLayout( gbl );



    gbc.weightx = 1.0;
    gbc.gridwidth = GridBagConstraints.REMAINDER;

    // RA input
    gbl.setConstraints( raip, gbc );
    frameCon.add( raip );


    // Dec input
    gbl.setConstraints( dip, gbc );
    frameCon.add( dip );


    // x,y input
    gbc.gridwidth = 1;
    addLabel( " X :" );
    gbl.setConstraints( xIn, gbc );
    frameCon.add( xIn );
    addLabel( "mm  " );
    addLabel( " Y :" );
    gbl.setConstraints( yIn, gbc );
    frameCon.add( yIn );
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    addLabel( "mm  " );


    // Az,El input
    gbc.gridwidth = 1;
    addLabel( " Az :" );
    gbl.setConstraints( azIn, gbc );
    frameCon.add( azIn );
    addLabel( degrees );
    addLabel( " El :" );
    gbl.setConstraints( elIn, gbc );
    frameCon.add( elIn );
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    addLabel( degrees );


    // Rotator position input
    gbc.gridwidth = 2;
    addLabel( "Rotator angle :" );
    gbl.setConstraints( paIn, gbc );
    frameCon.add( paIn );
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    addLabel( degrees );


    // Time input
    gbc.gridwidth = 2;
    addLabel( "Timestamp :" );
    gbl.setConstraints( tIn, gbc );
    frameCon.add( tIn );
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    addLabel( "s" );


    // Wavelength input
    gbc.gridwidth = 2;
    addLabel( "Wavelength :" );
    gbl.setConstraints( lambdaIn, gbc );
    frameCon.add( lambdaIn );
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    addLabel( "nm" );


    JButton b1 = new JButton( "Get RA,Dec" );
    b1.setActionCommand( "getRaDec" );
    b1.addActionListener( this );
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbl.setConstraints( b1, gbc );
    frameCon.add( b1 );



    JButton b2 = new JButton( "Get Az,El" );
    b2.setActionCommand( "getAzEl" );
    b2.addActionListener( this );
    gbl.setConstraints( b2, gbc );
    frameCon.add( b2 );



    JButton b3 = new JButton( "Get X,Y" );
    b3.setActionCommand( "getXY" );
    b3.addActionListener( this );
    gbl.setConstraints( b3, gbc );
    frameCon.add( b3 );






    // use VT that has a pointing origin @ (10, 10)mm from rotator axis,
    // and therefore a scale of 1:1
    AstrometryCalculator ac = new JNIAstrometryCalculator();
    ac.setIERSData( new IERSData() );

    MeteorologicalData md = new MeteorologicalData();
    md.setPressure( 0.0 );
    ac.setMeteorologicalData( md );
	
    ac.setSiteData( new SiteData( longitude, latitude, altitude ) );

    focalStation = new FocalStation( "test focal station", 123, 0.0,
				     new PointingOrigin( 0, 0 ),
				     0.0, 1.0, "mm" );

    vt = new VirtualTelescope
      ( focalStation, ac, 1.0, pointingModel );






    frame.setSize( 300, 200 );
    frame.pack();
    frame.setVisible( true );
  }


  /**
   * Add a label with the specified String.
   */
  protected void addLabel( String s )
  {
    JLabel l = new JLabel( s );
    gbl.setConstraints( l, gbc );
    frameCon.add( l );
  }


  /**
   * Perform the button actions.
   */
  public void actionPerformed( ActionEvent ae )
  {
    //	System.err.println( "\n" );
    String aec = ae.getActionCommand();
    //	System.err.println( "executing "+aec+"...\n\n" );

    //	System.err.println( "\n\nThread = "+Thread.currentThread() );

    try
    {
      Timestamp ts = getTimestamp();
      double rotPA = getRotatorAngle();
      double wl = getWavelength();

	/*===============================================================*/
	/*                                                               */
	/* Calculate the RA, Dec                                         */
	/*                                                               */
	/*===============================================================*/
	if( aec.equals( "getRaDec" ) )
	{
	  PointingOrigin po = getPointingOrigin();
	  double az = getAz();
	  double el = getEl();

	  XYZMatrix m1 = new XYZMatrix
	  ( ( Math.cos( az ) * Math.cos( el ) ),
	    -( Math.sin( az ) * Math.cos( el ) ),
	    Math.sin( el ) );

	  ReportedTarget t = vt.calcObservedTarget
	  ( ts, m1, po, rotPA, wl );

	  XYZMatrix m2 = t.getSystemRADec();

	  HoursMinutesSeconds hms =
	  HoursMinutesSeconds.radiansToHMS
	  ( Math.atan2( m2.getY(), m2.getX() ), true );
	  DegreesMinutesSeconds dms =
	  DegreesMinutesSeconds.radiansToDMS
	  ( Math.asin( m2.getZ() ), false );

	  //System.err.println( "Ra, Dec = "+hms+" "+dms );

	  raip.setText( hms );
	  dip.setText( dms );
	}
      /*===============================================================*/
      /*                                                               */
      /* Caluclate the Az, El                                          */
      /*                                                               */
      /*===============================================================*/
	else if( aec.equals( "getAzEl" ) )
	{
	  Target t = new Target();
	  t.setRA( getRA() );
	  t.setDec( getDec() );

	  PointingOrigin po = getPointingOrigin();

	  XYZMatrix m = vt.calcMountPosition( ts, t, po, rotPA, wl );

	  double d = Math.toDegrees( Math.atan2( -m.getY(), m.getX() ) );
	  while( d < 0.0 ) d += 360.0;
	  azIn.setText( azdf.format( d ) );
	  elIn.setText( eldf.format
			( Math.toDegrees( Math.asin( m.getZ() ) ) ) );

	  //System.err.println( "Az, El = "+
	  //		    Convert.xyzMatrixToAltAz( m ) );
	}
      /*===============================================================*/
      /*                                                               */
      /* Calculate the X, Y                                            */
      /*                                                               */
      /*===============================================================*/
	else if( aec.equals( "getXY" ) )
	{
	  Target t = new Target();
	  t.setRA( getRA() );
	  t.setDec( getDec() );

	  double az = getAz();
	  double el = getEl();

	  XYZMatrix m = new XYZMatrix
	    ( ( Math.cos( az ) * Math.cos( el ) ),
	      -( Math.sin( az ) * Math.cos( el ) ),
	      Math.sin( el ) );

	  PointingOrigin po = vt.calcPointingOrigin
	    ( ts, t, m, rotPA );

	  xIn.setText( podf.format( po.getX() ) );
	  yIn.setText( podf.format( po.getY() ) );

	  //System.err.println( "X, Y = "+po );
	}
    }
    catch( Exception e )
    {
      new JExceptionMessage
	( new Exception( "Error parsing "+errorMessage+" : "+e ),
	  frame );
      return;
    }
  }


  /**
   * Read the timestamp field and return the value as a Timestamp.
   */
  protected Timestamp getTimestamp()
    throws NumberFormatException
  {
    errorMessage = new String( "Timestamp" );
    int s;
    if( now )
    {
      s = (int)( ( (double)System.currentTimeMillis() ) / 1000.0 );
      tIn.setText( ""+s );
    }
    else
    {
      s = Integer.parseInt( tIn.getText() );
    }

    Timestamp t = new Timestamp
      ( s, 0, CalendarType.GREGORIAN, TimescaleType.UTC );

    //System.err.println( "Timestamp = "+t );
    return t;
  }


  /**
   * Read the RA from the JRAInputPanel and return as a double in radians.
   */
  protected double getRA()
  {
    errorMessage = new String( "Right Ascension" );
    double d = raip.getValue();
    //System.err.println
    //  ( " RA = "+HoursMinutesSeconds.radiansToHMS( d ) );
    return d;
  }


  /**
   * Read the Dec from the JDecInputPanel and return as a double in radians.
   */
  protected double getDec()
  {
    errorMessage = new String( "Declination" );
    double d = dip.getValue();
    //System.err.println
    //  ( "Dec = "+DegreesMinutesSeconds.radiansToDMS( d ) );
    return d;
  }


  /**
   * Read the X and Y fields and return the vcalues as a PointingOrigin.
   */
  protected PointingOrigin getPointingOrigin()
    throws NumberFormatException
  {
    errorMessage = new String( "Pointing Origin" );
    PointingOrigin po = new PointingOrigin
      ( ( Double.parseDouble( xIn.getText() ) / 1000.0 ),
	( Double.parseDouble( yIn.getText() ) / 1000.0 ) );
    //System.err.println( "PointingOrigin = "+po );
    return po;
  }


  /**
   * Read the rotator angle and return as radians.
   */
  protected double getRotatorAngle()
    throws NumberFormatException
  {
    errorMessage = new String( "Rotator Angle" );
    double d = Double.parseDouble( paIn.getText() );
    //System.err.println( "Rotator Angle = "+d );
    return Math.toRadians( d );
  }


  /**
   * Read the Azimuth angle and return as radians.
   */
  protected double getAz()
    throws NumberFormatException
  {
    errorMessage = new String( "Azimuth" );
    double d = Double.parseDouble( azIn.getText() );
    //System.err.println( " Azimuth = "+d );
    return Math.toRadians( d );
  }

  /**
   * Read the Altitude angle and return as radians.
   */
  protected double getEl()
    throws NumberFormatException
  {
    errorMessage = new String( "Elevation" );
    double d = Double.parseDouble( elIn.getText() );
    //System.err.println( "Altitude = "+d );
    return Math.toRadians( d );
  }

  /**
   * Read the Wavelength and return.
   */
  protected double getWavelength()
  {
    errorMessage = new String( "Wavelength" );
    double d = Double.parseDouble( lambdaIn.getText() );
    //System.err.println( "Wavelength = "+d );
    return d;
  }
}
/*
 *    $Date: 2013-07-04 13:07:08 $
 * $RCSfile: VTTests.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/VTTests.java,v $
 *      $Id: VTTests.java,v 1.2 2013-07-04 13:07:08 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
