package ngat.ngtcs;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * The Top-level Telescope class.
 *
 * This class is the top-level class for a Telescope Control System.
 *
 * It has all the references pertaining to each of its sub-systems (Mount,
 * Timer and PluggableSubSystems).  It is these references that this class will
 * provide for the CommandImplementor classes.
 *
 * @author $Author: je $
 * @version $Revision: 1.1 $
 */
public class Telescope
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
	new String( "$Id: Telescope.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Logger for this Telescope.
     */
    protected Logger logger = null;

    /**
     * Name of the Logger on this Telescope.
     */
    protected String logName = null;

    /**
     * The name of this Telescope.
     */
    protected String name;

    /**
     * List of the loggers in this NGTCS.
     */
    protected List loggers = new Vector();

    /**
     * LogHandler for ALL NGTCS logs.
     */
    protected List logHandlerList;

    /**
     * Logging Level for the NGTCS logs, set to the default of 3.
     */
    protected int logLevel = 3;

    /**
     * List of all Loggers used in this Telescope.
     */
    protected List loggerList = null;

    /**
     * Hashtable of all Loggers used in this Telescope.
     */
    protected Hashtable loggerHash = null;

    /**
     * Counts of all loggers with the same names.
     */
    protected Hashtable loggerNameCounts = null;

    /**
     * The current state of the Telescope, set to the default of SAFE
     */
    protected volatile TelescopeState telescopeState = TelescopeState.SAFE;

    /**
     * The current state of the Telescope, set to the default of SAFE
     */
    protected volatile SoftwareState softwareState = SoftwareState.SAFE;

    /**
     * The current Status of the Telescope.
     */
    protected TelescopeStatus status;

    /**
     * The Communicator used to listen for incoming Commands.
     */
    protected Communicator communicator = null;

    /**
     * List of subsystems which are part of this telescope.
     */
    protected List subsystemList = null;

    /**
     * Hashtable of the PluggableSubSystems on this telescope.  The key-value
     * pair consists of: the PluggableSubSystems unique name
     * (name+instance-number) : the reference to that PluggableSubSystem.
     */
    protected Hashtable subsystemHash = null;

    /**
     * Hashtable of the counts of PluggableSubSystems with the same name.
     */
    protected Hashtable subsysNameCounts = null;

    /**
     * List of ControllableSubSystems which are part of this telescope.
     */
    protected List conSubsystemList = null;

    /**
     * Hashtable of the ControllableSubSystems on this telescope.
     * The key-value pair consists of: the ControllableSubSystems unique name
     * (name+instance-number) : the reference to that ControllableSubSystem.
     */
    protected Hashtable conSubsystemHash = null;

    /**
     * Hashtable of the counts of Ports with the same name.
     */
    protected Hashtable portNameCounts = null;

    /**
     * List of the FocalStations on this Telescope.
     */
    protected List focalStationList = null;

    /**
     * List of the VirtualTelescopes on this Telescope.
     */
    protected List virtualTelescopeList = null;

    /**
     * FocalStation:VirtualTelescope key:value Hashtable.
     */
    protected Hashtable virtualTelescopeHash = null;

    /**
     * Currently active VirtualTelescope.
     */
    protected VirtualTelescope activeVT;

    /**
     * Timer mechanism.
     */
    protected ngat.ngtcs.subsystem.Timer timer;

    /**
     * Mount mechanism.
     */
    protected Mount mount;

    /**
     * AstrometryCalculator for all astrometric transformations.
     */
    protected AstrometryCalculator astroCalc;

    /**
     * Current Target selected.
     */
    protected Target currentTarget;

    /**
     * Observing wavelength, in metres, set to the default of 550nm.
     */
    protected double wavelength = 5.5E-07;

    /**
     * Focal lenght of this Telescope, in metres, set to the default of 1.0m.
     */
    protected double focalLength = 1.0;

    /**
     * Data object for the Location of this Telescope.
     */
    protected SiteData siteData;

    /**
     * Data object for the Meteorological conditions at this Telescope.
     */
    protected MeteorologicalData metData;

    /**
     * Data object for the time/position data from the IERS bulletins.
     */
    protected IERSData iersData;

    /**
     * Shutdown lock object.
     */
    protected Object shutdownLock = new Object();

    /**
     * Active VirtualTelescope lock object.
     */
    protected Object activeVTLock = new Object();

    /**
     * `Shutdown-in-progress' boolean defining the shutdown state of this
     * Telescope.
     */
    protected boolean shutdownInProgress = false;

    /**
     * Maximum TRACKing position error allowable (in radians).     */
    public final double MAX_TRACKING_ERROR;
    protected double mte;



    /**
     * Private class used to keep count of various sub-systems that may have
     * the same name, or for more than one of the same sub-system.
     */
    protected class Counter
    {
	/**
	 * The current count.
	 */
	protected int count;
    

	/**
	 * Initialse the count.
	 */
        public Counter()
	{
            count = 0;
        }
    

	/**
	 * Add 1 to the count.
	 */
        public void increment()
	{
            count++;
        }
    

	/**
	 * Retrieve the current count.
	 * @return count
	 * @see #count
	 */
        public int getCount()
	{
            return count;
        }
    

	/**
	 * Return the count as a String
	 * @return <code>Integer.toString( count );</code>
	 * @see #count
	 */
        public String toString()
	{
            return Integer.toString( count );
        }
    }

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    public static void main( String args[] )
    {
	if( args.length != 1 )
	    {
		System.err.println( "Need name of Telescope for configuring" );
		System.exit( 1 );
	    }
	try
	    {
		Telescope t = new Telescope( args[ 0 ] );
	    }
	catch( InitialisationException nie )
	    {
		System.err.println( "Cannot initialise Telescope [ "+args[ 0 ]+
				    " ] : "+nie );
		System.exit( 1 );
	    }
    }


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Constructor for Telescope objects.  Creates a Telescope object with
     * the name specified in newName and initialises the Telescope.
     *
     * The name of this Telescope will be used for all configuration,
     * intialisation and logging.  Failure to give a name for which there are
     * the relevent config files will result in an InitialisationException
     * @see Telescope#initialise
     * @param newName the name of this Telescope.
     */
    public Telescope( String newName ) throws InitialisationException
    {
	if( newName == null )
	    {
		throw new NullPointerException
		    ( "Telescope null String specified for newName" );
	    }
	name = newName;
	initialise();
	MAX_TRACKING_ERROR = mte;
    }


    /**
     * Read the various configuration files for the specified telescope.
     *
     * The following procedures are performed:
     * <ul><li>
     * The properties file is loaded
     * </li><li>
     * The logLevel is read from the configuration file and logging is
     * implemented (currently the only LogHandler writes to the file
     * <code>"NGTCS-"+name+"-dd-MM-yyyy"</code> where <code>name</code> is the
     * name specified in the Telescope constructor.
     * </li><li>
     * The Mount Class for this Telescope is read from the configuration file,
     * instantiated and initialised
     * </li><li>
     * The AstrometryCalculator Class for this Telescope is read from the
     * configuration file, instantiated and initialised
     * </li><li>
     * The Timer Class for this Telescope is read from the configuration file,
     * instantiated and initialised
     * </li><li>
     * Telescope-specific data is read from the configuration file and set
     * </li><li>
     * Instrument port descriptions are read from the configuration file and
     * Virtual Telescopes are instantiated for every port.
     * <b>The port descriptions MUST begin with <code>port1</code> and be
     * numerically sequential</b>
     * </li><li>
     * Auxilliary mechanims are read in from the subsystems file,
     * instantiated and initialised.
     * </li></li>
     * The TelescopeServer is started to listen for commands.
     * </li></ul>
     */
    public void initialise() throws InitialisationException
    {

	// Needs sorting so that if subsys/whatever are NOT null they are shut
	// down  properly first!!
	communicator = null;
	loggerList = null;
	loggerHash = null;
	logHandlerList = null;
	loggerNameCounts = null;
	subsystemList = null;
	subsystemHash = null;
	subsysNameCounts = null;
	conSubsystemList = null;
	conSubsystemHash = null;
	portNameCounts = null;
	focalStationList = null;
	virtualTelescopeList = null;
	virtualTelescopeHash = null;
	loggerList = new Vector();
	loggerHash = new Hashtable();
	logHandlerList = new Vector();
	loggerNameCounts = new Hashtable();
	subsystemList = new Vector();
	subsystemHash = new Hashtable();
	subsysNameCounts = new Hashtable();
	conSubsystemList = new Vector();
	conSubsystemHash = new Hashtable();
	portNameCounts = new Hashtable();
	focalStationList = new Vector();
	virtualTelescopeList = new Vector();
	virtualTelescopeHash = new Hashtable();

	softwareState = SoftwareState.INITIALISING;

	double longitude = 0.0, latitude = 0.0, altitude = 0.0;
	double frequency = 1.0, focalLength = 1.0;

	Class clsArr[] = new Class[ 0 ];
	java.lang.reflect.Method refMethod;
	String subsysClassName;
	/*********************************************************************/
	/* Configure logging                                                 */
	/*********************************************************************/
	LoggingInitialiser li = new LoggingInitialiser( name );
	li.initialise();

	logHandlerList = li.getHandlers();

	/*********************************************************************/
	/* Load the Properties file for Telescope [name]                     */
	/*********************************************************************/
	NGATProperties np = new NGATProperties();
	String configFilename = name+".cfg";
	try
	    {
		np.load( configFilename );
	    }
	catch( Exception e )
	    {
		softwareState = SoftwareState.ERROR;

		InitialisationException ie = new InitialisationException
		    ( "Telescope [ "+name+
		      " ] cannot load NGATProperties file [ "+configFilename+
		      "] because...\n"+e );
		System.err.println( ie.toString() );
		throw ie;
	    }

	try
	    {
		logLevel = np.getInt( "logLevel" );
	    }
	catch( NGATPropertyException npe )
	    {
		System.err.println( "Telescope [ "+name+
				    " ] cannot read logLevel from file [ "+
				    configFilename+" ] because:\n"+npe+
				    "\n\n" );
	    }
	System.err.println( "Using log level "+logLevel );

	logger = getLogger( this.getClass() );
	logName = logger.getName();
	logger.setLogLevel( logLevel );
	logger.log( 1, logName, "Initialising..." );

	/*********************************************************************/
	// Read the name of the Mount class
	/*********************************************************************/
	String mountClassName = np.getProperty( "mountClass" );
	mount = (ngat.ngtcs.subsystem.Mount)
	    instantiateByName( mountClassName );
	mount.initialise( this );
	logger.log( 1, logName, "Mount [ "+mountClassName+" ] initialised" );
	/*********************************************************************/
	// Set the maximum tracking error
	/*********************************************************************/
	try
	    {
		double mteArcsecs = np.getDouble( "maxTrackError" );
		mte = Math.toRadians( mteArcsecs / 3600.0 );
	    }
	catch( NGATPropertyException npe )
	    {
		logger.log( 1, logName, npe );
		InitialisationException ie = new InitialisationException
		    ( npe );
		throw ie;
	    }
	/*********************************************************************/
	// Read the name of the Timer class
	/*********************************************************************/
	String timerClassName = np.getProperty( "timerClass" );
	timer = (ngat.ngtcs.subsystem.Timer)
	    instantiateByName( timerClassName );
	timer.initialise( this );
	logger.log( 1, logName, "Timer [ "+timerClassName+" ] initialised" );
	/*********************************************************************/
	// Read the name of the AstrometryCalculator class
	/*********************************************************************/
	String astroCalcClassName = np.getProperty( "astrometryClass" );
	astroCalc = (ngat.ngtcs.AstrometryCalculator)
	    instantiateByName( astroCalcClassName );
	logger.log( 1, logName, "AstrometryCalculator [ "+astroCalcClassName+
		    " ] initialised" );
	/*********************************************************************/
 	// Read the Telescope attributes
	/*********************************************************************/
	try
	    {
		longitude   = np.getDouble( "longitude" );
		latitude    = np.getDouble( "latitude" );
		altitude    = np.getDouble( "altitude" );
		frequency   = np.getDouble( "frequency" );
		focalLength = np.getDouble( "focalLength" );
	    }
	catch( NGATPropertyException npe )
	    {
		softwareState = SoftwareState.ERROR;
		InitialisationException ie =
		    new InitialisationException( npe );
		logger.log( 1, logName, ie );
		throw ie;	
	    }

	/*********************************************************************/
	// Instantiate SiteData
	/*********************************************************************/
	SiteData siteData = null;
	try
	    {
		siteData = new SiteData( longitude, latitude, altitude );
	    }
	catch( IllegalArgumentException iae )
	    {
		softwareState = SoftwareState.ERROR;
		InitialisationException ie = new InitialisationException
		    ( "cannot create SiteData : "+iae );
		logger.log( 1, logName, ie );
		throw ie;	
	    }

	logger.log( 1, logName, "SiteData initialised" );

	/*********************************************************************/
	// Used here solely for testing
	/*********************************************************************/
	// TTL comparison tests have:
	// temp = 5 degs C
	// pressure = 1 bar
	// humidity = 0.7
	MeteorologicalData metData = new MeteorologicalData
	    ( 278.15, 1024.0, 0.7 );
	/**
	 * TTL comparisons
	 */
	IERSData iersData = new IERSData( 0.0, 0.0, 0, 0.2231, 0.4654 );
	/*
	 * IERSData iersData = new IERSData( 0.0, 0.0, 0, 0.0, 0.0 );
	 */
	astroCalc.setSiteData( siteData );
	astroCalc.setMeteorologicalData( metData );
	astroCalc.setIERSData( iersData );

	/*********************************************************************/
	// Read instrument port configurations and instantiate VTs
	/*********************************************************************/
	int nPorts = 0;
	boolean morePorts = true;
	String portDesc = null;

	while( morePorts )
	    {
		nPorts++;
		portDesc = null;

		/* Read port description string */
		portDesc = np.getProperty( "port"+nPorts );

		if( portDesc == null )
		    {
			logger.log
			    ( 1, logName, 
			      "null String returned from port"+nPorts+
			      " descriptor in config file [ "+configFilename+
			      " ]" );
			morePorts = false;
		    }
		else
		    {
			StringTokenizer st =
			    new StringTokenizer( portDesc, "/" );
			String n = null, u = null;
			double x = 0.0, y = 0.0, f = 0.0;
			double s = 0.0, a = 0.0;
			boolean goodDesc = false;

			try
			    {
				/* Name of VT */
				n = st.nextToken();
				/* X offset of FOV */
				x = Double.parseDouble( st.nextToken() );
				/* Y offset of FOV */
				y = Double.parseDouble( st.nextToken() );
				/* Inst Alignment Angle */
				a = Double.parseDouble( st.nextToken() );
				/* Focus offset */
				f = Double.parseDouble( st.nextToken() );
				/* Arcseconds/Unit */
				s = Double.parseDouble( st.nextToken() );
				/* Unit name */
				u = st.nextToken();

				goodDesc = true;
			    }
			catch( NoSuchElementException nsee )
			    {
				logger.log( 1, logName,
					    "incomplete port descriptor on "+
					    "port ["+nPorts+"] : "+nsee );
			    }
			catch( NumberFormatException nfe )
			    {
				logger.log( 1, logName, 
					    "cannot parse instrument "
					    +"port ["+nPorts+
					    " ] descriptor [ "+
					    portDesc+" ] : "+nfe );
			    }
		
			/* Use hashtable to prevent same name being 
			 * used twice. */
			if( goodDesc )
			    {
				if( ! portNameCounts.containsKey( n ) )
				    {
					portNameCounts.put( n, new Counter() );
				    }

				Counter c = (Counter)
				    ( portNameCounts.get( n ) );
				c.increment();

				String fsName = n+c.toString();

				/* Instantiate the FocalStation */
				FocalStation fs = new FocalStation
				    ( fsName, new PointingOrigin( x, y  ),
				      f, a, s, u );
				focalStationList.add( fs );
				/* Instantiate the VirtualTelescope */
				VirtualTelescope vt = 
				    new VirtualTelescope( this, fs, astroCalc,
							  focalLength );
				virtualTelescopeList.add( fsName );
				virtualTelescopeHash.put( fsName, vt );

				logger.log( 1, logName,
					    "VirtualTelescope [ "+fsName+
					    " ] configured" );
			    }
		    }
	    }

	if( virtualTelescopeList.size() == 0 )
	    {
		softwareState = SoftwareState.ERROR;
		InitialisationException ie = new InitialisationException
		    ( "No Instrument Ports specified" );
		logger.log( 1, logName, ie );
		throw ie;
	    }

	activeVT = (VirtualTelescope)
	  ( virtualTelescopeHash.get
	    ( (String)( virtualTelescopeList.get( 0 ) ) ) );

	/*********************************************************************/
	/* Instantiate and initialise PluggableSubSystems                    */
	/*                                                                   */
	/* PluggableSubSystem class names are read in from file.  Each class */
	/* is then iinstantiated and initialised.  If this succeeds the      */
	/* subsystem is placed in hashtables and a list for future reference.*/
	/*********************************************************************/
	FileReader fr = null;
	try
	    {
		fr = new FileReader( name+"-subsystems.cfg" );
	    }
	catch( FileNotFoundException fnfe )
	    {
		logger.log
		    ( 1, logName,
		      "cannot find optional subsystems config file : "+fnfe );
		fr = null;
	    }

	PluggableSubSystem subsys;
	if( fr != null )
	{
	    BufferedReader br = new BufferedReader( fr );
	    try
		{
		    /* Read Class name of PluggableSubSystem */
		    subsysClassName = br.readLine();

		    while( subsysClassName != null )
			{
			    subsys = null;

			    /* Instantiate PluggableSubSystem */
			    subsys = (PluggableSubSystem)
				instantiateByName( subsysClassName );

			    /* Use hashtable to prevent same name being 
			     * used twice. */
			    String shortSubsysName =
				StringUtilities.getLeaf
				( subsysClassName, '.' );

			    if( ! subsysNameCounts.containsKey
				( shortSubsysName ) )
				{
				    subsysNameCounts.put( shortSubsysName,
							  new Counter() );
				}

			    Counter c = (Counter)
				( subsysNameCounts.get( shortSubsysName ) );
			    c.increment();

			    String subsysName = shortSubsysName+c.
				toString();

			    /* Initialise PluggableSubSystem */
			    subsys.initialise( this );

			    subsystemHash.put( subsysName, subsys );
			    subsystemList.add( subsysName );

			    if( subsys instanceof ControllableSubSystem )
				{
				    conSubsystemHash.put
					( subsysName, subsys );
				    conSubsystemList.add( subsysName );
				}

			    subsysClassName = br.readLine();
			}
		    //		catch( IOException ioe )
		}
	    catch( Exception e )
		{
		    InitialisationException ie =
			new InitialisationException
			    ( "cannot initialise subsystems : "+e );
		    logger.log( 1, logName, ie );
		    throw ie;
		}
	}
	logger.log( 1, logName, "subsystems initialised" );

	/*********************************************************************/
	// Instantiate and initialise the Command Communicator class.
	/*********************************************************************/
	Class commClazz;
	try
	{
	    commClazz = np.getClass( "commClass" );
	    communicator = ( Communicator )( commClazz.newInstance() );
	    communicator.initialise( this );
	}
	catch( Exception e )
	{
	    softwareState = SoftwareState.ERROR;
	    InitialisationException ie = new InitialisationException
		( "cannot create Communicator : "+e );
	    logger.log( 1, logName, ie );
	    throw ie;
	}
	logger.log( 1, logName, "Communicator [ "+commClazz.getName()+
		    " ] initialised" );

	/*********************************************************************/
	softwareState = SoftwareState.OKAY;
	telescopeState = TelescopeState.IDLE;

	logger.log( 1, logName, "Telescope ["+name+"] initialised" );
	System.out.println( "ready" );
    }


    /**
     * Get the NGTCS-specific logger.
     * The String name passed in is of the class being initialised.  To remove
     *   name headers the last '.' token of the String is used.  A log
     * is initialised with this name (or this name appended with the number
     * of instance) by the LogManager.  All logs will be placed in the NGTCS
     * log file regardless of name.  Configurability can be added at a later
     * date to specify alternate logging procedures for any logs.
     * Currently, all logging is recorded to a file only
     * @see Telescope#initialise
     * @return the logger for the specified class
     */
    public Logger getLogger( Class clazz )
    {
	Logger logga = null;
	String loggerName = null;
	int logNameNumber = 0;

	String shortLogName = StringUtilities.getLeaf( clazz.getName(), '.' );

	// use counters to describe subsystem names, apart from Timer and Mount
	if( ( PluggableSubSystem.class.isAssignableFrom( clazz ) )&&
	    ( !( ( Mount.class.isAssignableFrom( clazz ) )||
		 ( ngat.ngtcs.subsystem.Timer.class.isAssignableFrom
		   ( clazz ) ) ) ) )
	    {
		if( ! loggerNameCounts.containsKey( shortLogName ) )
		    {
			loggerNameCounts.put( shortLogName,
					      new Counter() );
		    }

		Counter c = (Counter)( loggerNameCounts.get( shortLogName ) );
		c.increment();

		loggerName = shortLogName+c.toString();
	    }
	else
	    {
		if( loggerHash.containsKey( shortLogName ) )
		    {
			return ( (Logger)( loggerHash.get( shortLogName ) ) );
		    }
		loggerName = shortLogName;
	    }

	logga = LogManager.getLogger( loggerName );
	for( int i = 0; i < logHandlerList.size(); i++ )
	    {
		logga.addHandler( (LogHandler)( logHandlerList.get( i ) ) );
	    }
	logga.setLogLevel( logLevel );
	loggerList.add( loggerName );
	loggerHash.put( loggerName, logga );
	return logga;
    }


    /**
     *
     */
    public Object instantiateByName( String s )
	throws InitialisationException
    {
	try
	    {
		Class facClazz = Class.forName( s+"Factory" );
		PluggableSubSystemFactory fac =
		    (PluggableSubSystemFactory)facClazz.newInstance();

		return fac.getInstance();
	    }
	catch( Exception e )
	    {	    
		throw new InitialisationException
		    ( "couldn't instantiate "+s+" : "+e );
	    }
    }


    /**
     * Set the current selected Target to that specified.
     * @param newCurrentTarget
     */
    public void setCurrentTarget( Target newCurrentTarget )
    {
	currentTarget = newCurrentTarget;
    }


    /**
     * Get the current selected Target.
     * @return currentTarget
     */
    public Target getCurrentTarget()
    {
	return currentTarget;
    }


    /**
     * Set the wavelength of light to be observed.
     * @param d the wavelength, in metres.
     */
    public void setObservingWavelength( WAVELENGTH w )
	throws IllegalArgumentException
    {
	wavelength = w.getWavelength();

	for( int i = 0; i < virtualTelescopeList.size(); i++ )
	    {
		String s = (String)( virtualTelescopeList.get( i ) );
		( (VirtualTelescope)( virtualTelescopeHash.get( s ) ) ).
		    setWavelength( wavelength );
	    }
    }


    /**
     * Return the observing wavelength, in metres.
     * @return wavelength
     * @see #wavelength
     */
    public double getObservingWavelength()
    {
	return wavelength;
    }


    /**
     * Set the RotatorMode used for tracking on this Telescope.
     * @param newRotatorMode the RotatorMode to be used
     */
    public void setRotatorMode( RotatorMode newRotatorMode )
    {
	astroCalc.setRotatorMode( newRotatorMode );
    }


    /**
     * Get the RotatorMode used for tracking on this Telescope.
     * @return the RotatorMode being used
     */
    public RotatorMode getRotatorMode()
    {
	return astroCalc.getRotatorMode();
    }


    /**
     * Set the tracking position angle for the rotator on this telescope.
     * @param newPositionAngle the desired tracking position angle
     */
    public void setPositionAngle( double newPositionAngle )
    {
	astroCalc.setPositionAngle( newPositionAngle );
    }


    /**
     * Get the tracking position angle for the rotator on this telescope.
     * @return the tracking position angle
     */
    public double getPositionAngle()
    {
	return astroCalc.getPositionAngle();
    }


    /**
     * Return the currently active VirtualTelescope
     * @return the active VirtualTelescope
     */
    public VirtualTelescope getActiveVirtualTelescope()
    {
	VirtualTelescope vt;

	synchronized( activeVTLock )
	    {
		vt = activeVT;
	    }
	return vt;
    }


    /**
     * Set the currently active VirtualTelescope to the one in the
     * VirtualTelescope Hashtable represented by the specified name.
     * @param newActiveVTName
     * @return boolean describing whether the method was successful, i.e. whethger a valid VT name was given
     */
    public boolean setActiveVirtualTelescope( String newActiveVTName )
    {
	VirtualTelescope vt = (VirtualTelescope)
	    virtualTelescopeHash.get( newActiveVTName );

	if( vt == null )
	    return false;

	synchronized( activeVTLock )
	    {
		activeVT = vt;
		return true;
	    }
    }


    /**
     * Get the name of this Telescope.
     * @return this Telescope's name
     */
    public String getName()
    {
	return name;
    }


    /**
     * Return the Mount used by this Telescope.
     * @return this Telescope's Mount
     */
    public Mount getMount()
    {
	return mount;
    }


    /**
     * Return this Telescope's Timer object
     */
    public ngat.ngtcs.subsystem.Timer getTimer()
    {
	return timer;
    }


    /**
     * Return the AstrometryCalculator-implementing object to be used for all
     * astrometric transformations for this Telescope.
     * @return this Telescope's AstrometryCalculator
     */
    public AstrometryCalculator getAstrometryCalculator()
    {
	//	return ( AstrometryCalculator )astroCalc;
	return astroCalc;
    }


    /**
     * Return the focal length of this Telescope, in metres.
     * @return the focal length of this Telescope
     */
    public double getFocalLength()
    {
	return focalLength;
    }


    /**
     * Returns a boolean describing whether a SHUTDOWN has been initiated.
     * @return the state of the shutdown progress
     */
    public boolean shutdownInProgress()
    {
	boolean temp;
	synchronized( shutdownLock )
	    {
		temp = shutdownInProgress;
	    }
	return temp;
    }


    /**
     * Sets the state defining that a shutdown is in progress.
     */
    public void shutdown()
    {
	synchronized( shutdownLock )
	    {
		if( shutdownInProgress ) return;

		shutdownInProgress = true;
	    }

	communicator.shutdown();

	System.err.println( "communicator stopped" );
	System.err.println( "sending the SAFE command to all sub-systems" );

	makeSafe();

	for( int i = 0; i < conSubsystemList.size(); i++ )
	    {
		String subsysName = (String)( conSubsystemList.get( i ) );
		ControllableSubSystem subsystem = 
		    (ControllableSubSystem)
		    ( conSubsystemHash.get( subsysName ) );

		System.err.println( "making-safe "+subsysName+" [ "+
				    subsystem+" ]" );

		if( subsystem.shutdown() )
		    {
			System.err.println( subsysName+" shutdown" );
		    }
		else
		    {
			System.err.println( "problem shutting down "+
					    subsysName );
		    }
	    }

	System.err.println( "all sub-systems attempted SAFE and SHUTDOWN,"+
			    " terminating NGTCS"+
			    " (waiting for communication threads to die)" );

	return;
    }


    /**
     * Place this Telescope and all sub-systems into a SAFE state in
     * preparation for a shutdown or "brown-out" situation.
     */
    public void makeSafe()
    {
	for( int i = 0; i < conSubsystemList.size(); i++ )
	    {
		String subsysName = (String)( conSubsystemList.get( i ) );
		ControllableSubSystem subsystem = 
		    (ControllableSubSystem)
		    ( conSubsystemHash.get( subsysName ) );

		System.err.println( "making-safe "+subsysName+" [ "+
				    subsystem+" ]" );

		if( subsystem.makeSafe() )
		    {
			System.err.println( subsysName+" safe" );
		    }
		else
		    {
			System.err.println( "problem making "+subsysName+
					    " safe!" );
		    }
	    }
	softwareState = SoftwareState.SAFE;
	logger.close();
    }


    /**
     * Assign the current state of this Telescope.
     * @param newState the new state of the Telescope 
     */
    public void setSoftwareState( SoftwareState newSoftwareState )
    {
	/*
	  This needs to be synchronized
	*/
	softwareState = newSoftwareState;
    }


    /**
     * Return the current state of this Telescope.
     * @return the state of this Telescope 
     */
    public SoftwareState getSoftwareState()
    {
	/*
	  This needs to be synchronized
	*/
	return softwareState;
    }


    /**
     * Assign the current state of this Telescope.
     * @param newState the new state of the Telescope 
     */
    public void setTelescopeState( TelescopeState newTelescopeState )
    {
	/*
	  This needs to be synchronized
	*/
	telescopeState = newTelescopeState;
    }


    /**
     * Return the current state of this Telescope.
     * @return the state of this Telescope 
     */
    public TelescopeState getTelescopeState()
    {
	/*
	  This needs to be synchronized
	*/
	return telescopeState;
    }


    /**
     * Return the current Status of this Telescope.
     * @return the status of this Telescope
     */
    public Status getStatus()
    {
	MountStatus ms = (MountStatus)( mount.getStatus() );
	ReportedTarget rt = activeVT.calcObservedTarget
	    ( ms.getTimestamp(), ms.getPosition() );
	return new TelescopeStatus
	    ( name, softwareState, telescopeState, currentTarget, rt, ms );
    }


    /**
     * Return the List of Loggers for this Telescope.
     * @return the list of loggers
     */
    public List getLoggerList()
    {
	return loggerList;
    }


    /**
     * Return the Logger that is named as specified.
     * @param loggerName
     * @return the logger specified
     */
    public Logger getLogger( String loggerName )
    {
	return (Logger)( loggerHash.get( loggerName ) );
    }


    /**
     * Return the List of PluggableSubSystems for this Telescope.
     * @return the list of subsystems
     */
    public List getPluggableSubSystemList()
    {
	return subsystemList;
    }


    /**
     * Return the PluggableSubSystem object reference that is named as
     * specified.
     * @param subsystemName
     * @return the subsystem specified
     */
    public PluggableSubSystem getPluggableSubSystem( String subsystemName )
    {
	return (PluggableSubSystem)( subsystemHash.get( subsystemName ) );
    }


    /**
     * Return the List of ControllableSubSystems for this Telescope.
     * @return the list of subsystems
     */
    public List getControllableSubSystemList()
    {
	return conSubsystemList;
    }


    /**
     * Return the ControllableSubSystem object reference that is named as
     * specified.
     * @param subsystemName
     * @return the subsystem specified
     */
    public ControllableSubSystem getControllableSubSystem
	( String subsystemName )
    {
	return (ControllableSubSystem)( conSubsystemHash.get
					( subsystemName ) );
    }
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: Telescope.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/Telescope.java,v $
 *      $Id: Telescope.java,v 1.1 2003-07-01 10:11:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
