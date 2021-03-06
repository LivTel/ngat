package ngat.ngtcs.test;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

import ngat.net.*;
import ngat.message.base.*;

import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

import ngat.ngtcs.command.*;

/**
 * Simple GUI to test the NGTCS commands and execution.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class JTestGUI extends JApplet implements ActionListener
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   *
   */
  protected final int commandPort = 29574;

  /**
   *
   */
  protected final String commandAddressString = "150.204.240.96";

  /**
   *
   */
  protected InetAddress commandAddress = null;

  /**
   *
   */
  protected JRAInputPanel raip;

  /**
   *
   */
  protected JDecInputPanel dip;

  /**
   *
   */
  protected JTextField tpointFile;

  /**
   *
   */
  protected JTextField pressure;

  /**
   *
   */
  protected JTextField mechanism;

  /**
   *
   */
  protected JTextField rotPAText;

  /**
   *
   */
  protected JTextField logName;

  /**
   *
   */
  protected JTextField logLevel;

  /**
   *
   */
  protected JTextField timeSeconds;

  /**
   *
   */
  protected JTextField timeNanoseconds;

  /**
   *
   */
  protected JTextField timeIncrement;

  /**
   *
   */
  protected JTextField agWavelength;

  /**
   *
   */
  protected JComboBox positionAngleCB;

  /**
   *
   */
  protected Vector positionAngleObjs = new Vector();

  /**
   *
   */
  protected JComboBox timeCB;

  /**
   *
   */
  protected JComboBox equinoxCB;

  /**
   *
   */
  protected Vector equinoxObjs = new Vector();

  /**
   * The autoguidder IN selected Radio Button.
   */
  protected JRadioButton agFiltInB;

  /**
   *
   */
  protected AzPanel azPanel;

  /**
   *
   */
  protected AltPanel altPanel;

  /**
   *
   */
  protected AzPanel rotPanel;

  /**
   *
   */
  protected Socket socket = null; 

  /**
   *
   */
  protected JFrame frame;

  /**
   *
   */
  protected String cmdPath = "dummy";

  /**
   *
   */
  protected boolean running = false;

  /**
   *
   */
  public JTestGUI( JFrame f )
  {
    frame = f;

    JPanel p1, p2, p3;
    Checkbox cb;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.ipadx = 10;
    gbc.ipady = 5;

    Container cp = getContentPane();
    cp.setLayout( new GridLayout( 2, 1 ) );


    p1 = new JPanel();
    p1.setLayout( new GridLayout( 10, 1 ) );


    p2 = new JPanel();
    raip = new JRAInputPanel( frame );
    p2.add( raip );
    dip = new JDecInputPanel( frame );
    p2.add( dip );
    Vector equinoxList = new Vector();
    equinoxList.add( "B1950" );
    equinoxList.add( "J2000" );
    equinoxList.add( "Apparent" );
    equinoxObjs.add( Equinox.B1950 );
    equinoxObjs.add( Equinox.J2000 );
    equinoxObjs.add( Equinox.APPARENT );
    equinoxCB = new JComboBox( equinoxList );
    equinoxCB.setEditable( false );
    p2.add( equinoxCB );
    p1.add( p2 );


    p2 = new JPanel();
    p2.setLayout( new GridLayout( 1, 7 ) );
    JButton trackB = new JButton( "Track" );
    trackB.setActionCommand( "track" );
    trackB.addActionListener( this );
    p2.add( trackB );
    JButton stopB = new JButton( "Stop" );
    stopB.setActionCommand( "stop" );
    stopB.addActionListener( this );
    p2.add( stopB );
    JButton testB = new JButton( "Test" );
    testB.setActionCommand( "test" );
    testB.addActionListener( this );
    p2.add( testB );
    JButton statusB = new JButton( "Get Status" );
    statusB.setActionCommand( "get status" );
    statusB.addActionListener( this );
    p2.add( statusB );
    JButton stateB = new JButton( "Get State" );
    stateB.setActionCommand( "get state" );
    stateB.addActionListener( this );
    p2.add( stateB );
    JButton shutB = new JButton( "Shutdown" );
    shutB.setActionCommand( "shutdown" );
    shutB.addActionListener( this );
    p2.add( shutB );
    JButton autoguideB = new JButton( "Autoguide" );
    autoguideB.setActionCommand( "autoguide" );
    autoguideB.addActionListener( this );
    p2.add( autoguideB );
    /*
      gbc.weightx = 1;
      gbc.gridwidth = GridBagConstraints.REMAINDER;
      gbl.setConstraints( p2, gbc );
    */
    p1.add( p2 );
    /*
      gbc.weightx = 0;
      gbc.gridwidth = 1;
      gbc.fill = GridBagConstraints.NONE;
    */


    p2 = new JPanel();
    p2.setLayout( new GridBagLayout() );
    p2.add( new JLabel( "Rotator Mode :" ) );
    Vector v = new Vector();
    v.add( "Sky PA" );
    v.add( "Mount PA" );
    v.add( "Vertical" );
    v.add( "Floating" );
    v.add( "Floating Vertical" );
    positionAngleObjs.add( RotatorMode.SKY_POSITION );
    positionAngleObjs.add( RotatorMode.MOUNT_POSITION );
    positionAngleObjs.add( RotatorMode.VERTICAL_POSITION );
    positionAngleObjs.add( RotatorMode.FLOATING_SKY_POSITION );
    positionAngleObjs.add( RotatorMode.FLOATING_VERTICAL_POSITION );
    positionAngleCB = new JComboBox( v );
    positionAngleCB.setEditable( false );
    p2.add( positionAngleCB );
    JLabel l = new JLabel( "Rotator Position Angle :" );
    p2.add( l );
    rotPAText = new JTextField( " ddd.ddd " );
    p2.add( rotPAText );
    JButton setPAB = new JButton( "Set PA" );
    setPAB.setActionCommand( "setpa" );
    setPAB.addActionListener( this );
    p2.add( setPAB );
    /*
      gbc.weightx = 1;
      gbc.gridwidth = GridBagConstraints.REMAINDER;
      gbl.setConstraints( p, gbc );
    */
    p1.add( p2 );


    p2 = new JPanel();
    p2.add( new JLabel( "Seconds :" ) );
    timeSeconds = new JTextField( 11 );
    p2.add( timeSeconds );
    // Commented out - thought unecessary to have nanosecond precision in
    // setting the time!!
    /*
      p2.add( new JLabel( "Nanoseconds :" ) );
      timeNanoseconds = new JTextField( 10 );
      p2.add( timeNanoseconds );
    */
    p2.add( new JLabel( "Increment (sec) :" ) );
    timeIncrement = new JTextField( 6 );
    p2.add( timeIncrement );
    v = new Vector();
    v.add( "Static" );
    v.add( "Stepped" );
    timeCB = new JComboBox( v );
    timeCB.setEditable( false );
    p2.add( timeCB );
    JButton timeB = new JButton( "Set Time" );
    timeB.setActionCommand( "time" );
    timeB.addActionListener( this );
    p2.add( timeB );
    p1.add( p2 );


    p2 = new JPanel();
    //p.setLayout( new GridLayout( 2, 2 ) );
    //p1 = new JPanel();
    p2.add( new JLabel( "TPOINT input file :" ) );
    tpointFile = new JTextField( " tpoint input filename " );
    p2.add( tpointFile );
    JButton tpointB = new JButton( "TPOINT" );
    tpointB.setActionCommand( "tpoint" );
    tpointB.addActionListener( this );
    p2.add( tpointB );
    p1.add( p2 );


    p2 = new JPanel();
    p2.add( new JLabel( "Pressure (mB) :" ) );
    pressure = new JTextField( " 1024.0 " );
    p2.add( pressure );
    JButton pressureB = new JButton( "Set Pressure" );
    pressureB.setActionCommand( "set pressure" );
    pressureB.addActionListener( this );
    p2.add( pressureB );
    p1.add( p2 );


    p2 = new JPanel();
    p2.add( new JLabel( "Mechanism to reset :" ) );
    mechanism = new JTextField( " mechanism " );
    p2.add( mechanism );
    JButton resetB = new JButton( "Reset" );
    resetB.setActionCommand( "reset" );
    resetB.addActionListener( this );
    p2.add( resetB );
    JButton mechListB = new JButton( "Mech list" );
    mechListB.setActionCommand( "mech list" );
    mechListB.addActionListener( this );
    p2.add( mechListB );
    p1.add( p2 );

	    
    p2 = new JPanel();
    JButton logListB = new JButton( "Log list" );
    logListB.setActionCommand( "log list" );
    logListB.addActionListener( this );
    p2.add( logListB );
    p2.add( new JLabel( "Log name :" ) );
    logName = new JTextField( " log name " );
    p2.add( logName );
    p2.add( new JLabel( "Log level :" ) );
    logLevel = new JTextField( " 3 " );
    p2.add( logLevel );
    JButton logB = new JButton( "Log" );
    logB.setActionCommand( "log" );
    logB.addActionListener( this );
    p2.add( logB );
    p1.add( p2 );


    p2 = new JPanel();
    p2.add( new JLabel( "Autoguider wavelength :" ) );
    agWavelength = new JTextField( "  550" );
    p2.add( agWavelength );
    p2.add( new JLabel( "nm" ) );
    JButton agWavelengthB = new JButton( "AG Wavelength" );
    agWavelengthB.setActionCommand( "agWavelength" );
    agWavelengthB.addActionListener( this );
    p2.add( agWavelengthB );
    p1.add( p2 );


    p2 = new JPanel();
    p2.add( new JLabel( "Autoguider Filter :" ) );
    ButtonGroup agFiltBGrp = new ButtonGroup();
    agFiltInB = new JRadioButton( "IN", false );
    agFiltBGrp.add( agFiltInB );
    p2.add( agFiltInB );
    JRadioButton agFiltOutB = new JRadioButton( "OUT", true );
    agFiltBGrp.add( agFiltOutB );
    p2.add( agFiltOutB );
    JButton agFiltB = new JButton( "Select" );
    agFiltB.setActionCommand( "agFilt" );
    agFiltB.addActionListener( this );
    p2.add( agFiltB );
    p1.add( p2 );


    cp.add( p1 );

    /*
      gbc.fill = GridBagConstraints.BOTH;
      gbl.setConstraints( p, gbc );
      add( p );
      gbc.fill = GridBagConstraints.NONE;
    */


    p1 = new JPanel();
    //p1.setLayout( new GridLayout( 1, 3 ) );
    azPanel = new AzPanel();
    p1.add( azPanel );
    altPanel = new AltPanel();
    p1.add( altPanel );
    rotPanel = new AzPanel();
    p1.add( rotPanel );

    cp.add( p1 );


    /*
      gbc.weightx = 1;
      gbc.gridwidth = GridBagConstraints.REMAINDER;
      gbl.setConstraints( p, gbc );
    */


    try
    {
      commandAddress = InetAddress.getByName
	( commandAddressString );;
    }
    catch( Exception e )
    {
      System.err.println( e );
      System.exit( 1 );
    }
  }

  /**
   *
   */
  public void actionPerformed( ActionEvent ae )
  {
    if( ae.getActionCommand().equals( "stop" ) )
    {
      STOP stop = new STOP( cmdPath );
      sendCommand( stop );
    }

    if( ae.getActionCommand().equals( "setpa" ) )
    {
      try
      {
	double pa = Double.parseDouble
	  ( rotPAText.getText().trim() );
	RotatorMode rm = null;

	int i = positionAngleCB.getSelectedIndex();

	if( i == -1 )
	{
	  new JExceptionMessage
	    ( new Exception( "Select a Rotator Mode" ),
	      frame );
	  return;
	}

	rm = (RotatorMode)( positionAngleObjs.get( i ) );

	ROTATOR r = new ROTATOR( cmdPath, rm,
				 Math.toRadians( pa ) );

	sendCommand( r );
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
      }
    }

    if( ae.getActionCommand().equals( "track" ) )
    {
      try
      {
	Target t = getInputTarget();
	if( t == null )
	  return;

	GOTO go = new GOTO( cmdPath, t );

	sendCommand( go );
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
	return;
      }
    }

    if( ae.getActionCommand().equals( "test" ) )
    {
      try
      {
	Target t = getInputTarget();
	if( t == null )
	  return;

	TEST test = new TEST( cmdPath, t );

	sendCommand( test );
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
	return;
      }
    }

    if( ae.getActionCommand().equals( "tpoint" ) )
    {

      String s = tpointFile.getText().trim();
      if( s == null )
      {
	new JExceptionMessage( new NullPointerException
			       ( "null pointer - enter a path into the TPOINT file "+
				 "field" ), frame );
	return;
      }
      TPOINT tpoint = new TPOINT( cmdPath, s );
      sendCommand( tpoint );
    }

    if( ae.getActionCommand().equals( "set pressure" ) )
    {
      String str = pressure.getText().trim();
      double p;
      if( str == null )
      {
	new JExceptionMessage( new NullPointerException
			       ( "null pointer - enter a pressure" ), 
			       frame );
	return;
      }
      p = Double.parseDouble( str );
      System.err.println( "pressure read as "+p );
      sendCommand( new PRESSURE( cmdPath, p ) );
    }

    if( ae.getActionCommand().equals( "reset" ) )
    {
      String resetStr = mechanism.getText().trim();
      RESET reset = new RESET( cmdPath, resetStr );
      sendCommand( reset );
    }

    if( ae.getActionCommand().equals( "get status" ) )
    {
      if( running )
      {
	running = false;
      }
      else
      {
	running = true;
	new Thread( new StatusThread() ).start();
      }
    }

    if( ae.getActionCommand().equals( "get state" ) )
    {
      STATE s = new STATE( cmdPath );
      sendCommand( s );
    }

    /*
      if( ae.getActionCommand().equals( "autoguide" ) )
      {
      AUTOGUIDE autoguide = new AUTOGUIDE( cmdPath,
      "MSIAutoguider1" );
      sendCommand( autoguide );
      }
    */

    if( ae.getActionCommand().equals( "mech list" ) )
    {
      SUBSYSTEMLIST ml = new SUBSYSTEMLIST( cmdPath );
      sendCommand( ml );
    }

    if( ae.getActionCommand().equals( "log list" ) )
    {
      LOGLIST ll = new LOGLIST( cmdPath );
      sendCommand( ll );
    }

    if( ae.getActionCommand().equals( "log" ) )
    {
      String logNameStr = logName.getText().trim();
      int logLevelInt = 3;
      try
      {
	logLevelInt = Integer.parseInt
	  ( logLevel.getText().trim() );
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
	return;
      }
      LOG l = new LOG( cmdPath, logNameStr, logLevelInt );
      sendCommand( l );
    }

    if( ae.getActionCommand().equals( "time" ) )
    {
      long seconds = 0;
      long nanoseconds = 0;
      double timestep = 0.0;
      boolean staticTime = false;

      try
      {
	seconds = Integer.parseInt
	  ( timeSeconds.getText().trim() );
	timestep = Double.parseDouble
	  ( timeIncrement.getText().trim() );

	int i = timeCB.getSelectedIndex();

	if( i == -1 )
	{
	  new JExceptionMessage
	    ( new Exception( "Select a Time Mode" ),
	      frame );
	  return;
	}

	if( i == 0 )
	  staticTime = true;
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
	return;
      }

      TIME time = new TIME
	( cmdPath, staticTime, seconds, nanoseconds,
	  (int)( timestep * 1000000000.0 ) );
      sendCommand( time );
    }

    if( ae.getActionCommand().equals( "agWavelength" ) )
    {
      double d;
      try
      {
	d = Double.parseDouble( agWavelength.getText().trim() ) * 1.0E-09;
      }
      catch( Exception e )
      {
	new JExceptionMessage( e, frame );
	return;
      }
      AGWAVELENGTH agW = new AGWAVELENGTH( cmdPath, d );
      sendCommand( agW );
    }

    if( ae.getActionCommand().equals( "agFilt" ) )
    {
      boolean deploy = false;
      if( agFiltInB.isSelected() )
	deploy = true;
      AGFILTER agF = new AGFILTER( cmdPath, deploy );
      sendCommand( agF );
    }

    if( ae.getActionCommand().equals( "shutdown" ) )
    {
      running = false;
      SHUTDOWN shutdown = new SHUTDOWN( cmdPath, "dummy" );
      sendCommand( shutdown );
    }
  }


  /**
   *
   */
  public void sendCommand( Command command )
  {
    System.out.println( "sending "+command.getClass().getName()+" command" );

    TCPClientConnectionThreadMA cct = new TCPClientConnectionThreadMA
      ( commandAddress, commandPort, command );
    cct.setErrorStream( new PrintWriter( System.err, true ) );
    new Thread( new DoneThread( cct, command ) ).start();
    cct.start();    
  }


  /**
   *
   */
  protected class DoneThread implements Runnable
  {
    private TCPClientConnectionThreadMA cConThread;

    private Command command;

    private CommandDone cmdDone;

    public DoneThread( TCPClientConnectionThreadMA cct, Command cmd )
    {
      cConThread = cct;
      command = cmd;
    }

    public void run()
    {
      int sleep = 500, slept = 0, totalSlept = 0, timeout = 999999;
      Acknowledge ack = null, lastAck = null;
      boolean success = false;
      String msg;

      do
      {
	try
	{
	  Thread.sleep( sleep );
	  slept += sleep;
	}
	catch( InterruptedException ie )
	{
	  System.err.println( ie );
	}

	ack = (Acknowledge)( cConThread.getAcknowledge() );
	cmdDone = (CommandDone)( cConThread.getDone() );

	if( ( ack != null )&&( ack != lastAck ) )
	{
	  timeout = ack.getTimeToComplete();
	  String toStr = ""+timeout;
	  if( timeout == Integer.MAX_VALUE )
	    toStr = new String( "NOT APPLICABLE" );
	  totalSlept += slept;
	  slept = 0;
	  System.err.println( "       Timeout : "+timeout+"\n"+
			      "Slept this Ack : "+slept+"\n"+
			      "   Total Slept : "+totalSlept );
	  lastAck = ack;
	}
      }
      while( ( cmdDone == null )&&( slept < timeout ) );

      if( cmdDone != null )
      {
	System.err.println( "\nCommandDone received" );

	success = cmdDone.getSuccessful();
	System.out.println( "Command ["+cmdDone.getCommand()+
			    "] returned : SUCCESS = "+success );

	if( success == true )
	  msg = cmdDone.getReturnMessage();
	else
	  msg = cmdDone.getErrorMessage();

	System.out.println( "Command returned : "+msg );
      }
      else
      {
	totalSlept += slept;
	System.err.println( "Command ["+command+"] has timed out after "+
			    totalSlept+"ms" );
      }
    }
  }


  /**
   *
   */
  protected class StatusThread implements Runnable
  {
    public void run()
    {
      while( running )
      {
	getStatus();
	try
	{
	  Thread.sleep( 5000 );
	}
	catch( InterruptedException ie )
	{
	  System.err.println( ie );
	}
      }
    }
  }


  /**
   *
   *
   */
  public void getStatus()
  {
    TCPClientConnectionThreadMA cct = new TCPClientConnectionThreadMA
      ( commandAddress, commandPort, new STATUS( cmdPath ) );
    cct.start();

    while( cct.getDone() == null )
    {
      try
      {
	Thread.sleep( 1000 );
      }
      catch( InterruptedException ie )
      {
	System.err.println( ie ); 
      }
    }

    STATUSDone sd  = (STATUSDone)( cct.getDone() );
    MountStatus ms = sd.getTelescopeStatus().getMountStatus();
    XYZMatrix mat  = ms.getPosition();
    double az      = Math.toDegrees( Math.atan2(-(mat.getY()),mat.getX()));
    double alt     = Math.toDegrees( Math.asin( mat.getZ() ) );
    double rot     = Math.toDegrees( ms.getRotatorAngle() );
    System.err.println( "az = "+az );
    azPanel.update( az );
    System.err.println( "alt = "+alt );
    altPanel.update( alt );
    System.err.println( "rot = "+rot );
    rotPanel.update( rot );
  }


  /**
   *
   *
   */
  protected Target getInputTarget()
  {
    Target t = new Target();
    Equinox equinox = null;
    double ra = 0.0, dec = 0.0;
    int i = equinoxCB.getSelectedIndex();

    if( i == -1 )
    {
      new JExceptionMessage
	( new Exception( "Select an Equinox" ), frame );
      return null;
    }

    equinox = (Equinox)( equinoxObjs.get( i ) );

    ra = raip.getValue();
    dec = dip.getValue();

    t.setRA( ra );
    t.setDec( dec );
    t.setEquinox( equinox );

    return t;
  }


  public static void main( String[] args )
  {
    JFrame f = new JFrame( "Test GUI" );
    JTestGUI tg = new JTestGUI( f );

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
    f.getContentPane().add( tg );
    f.setVisible( true );
  }
}
/*
 *    $Date: 2013-07-04 13:02:29 $
 * $RCSfile: JTestGUI.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/JTestGUI.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 18:16:45  je
 *     Sorted out TestExecutionThread stuff.
 *
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
