package ngat.ngtcs;

import java.io.*;
import java.util.*;
import java.text.*;

import ngat.util.*;
import ngat.util.logging.*;

/**
 * This purpose of this class is to initialise <code>LogHandlers</code> and
 * their respective <code>LogFormatter</code> as specified in the logging
 * configuration file.
 * <br>
 * Defaults for non-existence of the configuration file or keywords in the file
 * are implemented.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class LoggingInitialiser
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
	new String( "$Id: LoggingInitialiser.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

    /**
     * Default port for NGTCS multicast logging.
     */
    public final static int NGTCS_MULTICAST_PORT = 35535;

    /**
     * Default group name for NGTCS multicast logging.
     */
    public final static String NGTCS_MULTICAST_GROUP_NAME = "NGTCS logging";

    /**
     * Default directory for NGTCS file logging.
     */
    public final static String NGTCS_LOGGING_DIRECTORY = "logs";

    /**
     * Default maximum log level for NGTCS logging.
     */
    public final static int NGTCS_MAX_LOG_LEVEL = 5;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The NGATProperties used to configure logging.
     */
    protected NGATProperties np;

    /**
     * The name of the Telescope for which logging will be configured.
     */
    protected String telescopeName;

    /**
     * The directory
     */
    protected String loggingDir;

    /**
     * The group name used in initialising multicast logs, set to the default
     * of "NGTCS".
     */
    protected String groupName;

    /**
     * The list of <code>LogHandlers</code> initialised.
     */
    protected List handlerList;

    /**
     * <code>boolean</code> Descibing whether a logging configuration file
     * exists.
     */
    protected boolean noConfigFile = false;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Create a new initialiser for the telescope with the name specified.
     * <br>
     * This method creates the NGATProperties from the filename
     * <code>s+"-logging.cfg"</code>.  If no such file exists default options
     * are used.
     * @param s the String name of the Telescope to initialise logging for.
     */
    public LoggingInitialiser( String s )
	throws InitialisationException
    {
	if( s == null )
	    throw new InitialisationException
		( "[null] Name given for Telescope" );

	telescopeName = s;
	np = new NGATProperties();
	try
	    {
		np.load( telescopeName+"-logging.cfg" );
	    }
	catch( Exception e )
	    {
		noConfigFile = true;
	    }
    }


    /**
     * Perform the logging initialisation.
     * This method will setup the default logging option (a
     * <code> FileLogHandler</code> using the
     * <code>ngat.util.logging.BasicLogFormatter</code>) if there is no
     * configuration file, or if the keywords used on the NGATProperties search
     * return <code>null</code>. Also, the default values for
     * <code>loggingDir</code> (./logs or .\logs - depending on system) and
     * <code>groupName</code> (NGTCS) will be used if the keywords do not
     * exist.
     */
    public void initialise()
	throws InitialisationException
    {
	handlerList = new Vector();

	if( noConfigFile )
	    {
		if( handlerList.size() == 0 )
		    {
			handlerList.add
			    ( getHandler( null, getFormatter( null ) ) );
		    }
		return;
	    }

	loggingDir = np.getProperty( "logDirectory" );
	if( loggingDir == null )
	    loggingDir = NGTCS_LOGGING_DIRECTORY;

	groupName = np.getProperty( "groupName" );
	if( groupName == null )
	    groupName = NGTCS_MULTICAST_GROUP_NAME;

	LogFormatter formatter;
	LogHandler handler;
	boolean moreHandlers = true;

	while( moreHandlers )
	    {
		formatter = null;
		handler = null;

		String handlerClassName = np.getProperty
		    ( "logHandler"+handlerList.size() );
		String formatterClassName = np.getProperty
		    ( "logFormatter"+handlerList.size() );

		if( ( handlerClassName == null )&&
		    ( handlerList.size() > 0 ) )
		    {
			moreHandlers = false;
		    }
		else
		    {
			formatter = getFormatter( formatterClassName );
			handler = getHandler( handlerClassName, formatter );

			handlerList.add( handler );
		    }
	    }
    }


    /**
     * Return an instance of the LogFormatter specified.
     * <br>
     * If the specified name is <code>null</code> a
     * <code>ngat.util.logging.BasicLogFormatter</code> is returned.
     * @param formatterClassName the name of the <code>LogFormatter</code> to instantiate
     * @return an instance of <code>LogFormatter</code>
     */
    protected LogFormatter getFormatter( String formatterClassName )
	throws InitialisationException
    {
	LogFormatter formatter;
	
	if( formatterClassName == null )
	    formatterClassName = new String
		( "ngat.util.logging.BasicLogFormatter" );;

	try
	    {
		formatter = (LogFormatter)
		    ( Class.forName( formatterClassName ).newInstance() );
	    }
	catch( Exception e )
	    {
		throw new InitialisationException( e );
	    }

	return formatter;
    }


    /**
     * Return an instance of the LogHandler specified.
     * <br>
     * If the specified name is <code>null</code> a
     * <code>ngat.util.logging.FileLogHandler</code> is returned.
     * @param handlerClassName the name of the <code>LogHandler</code> to instantiate
     * @param formatter the LogFormatter to use with this instantiated handler
     * @return an instance of <code>LogHandler</code>
     */
    protected LogHandler getHandler( String handlerClassName,
				     LogFormatter formatter )
	throws InitialisationException
    {
	LogHandler handler;

	if( handlerClassName == null )
	    {
		System.err.println( "null LogHandler class name given - "+
				    "using ngat.util.logging.FileLogHandler" );
		handlerClassName = new String
		    ( "ngat.util.logging.FileLogHandler" );
	    }

	if( handlerClassName.equals
	    ( "ngat.util.logging.FileLogHandler" ) )
	    {
		String logFilename =
		    ( loggingDir+( File.separator )+"NGTCS-"+
		      telescopeName+
		      ( new SimpleDateFormat( "-dd-MM-yyyy" ).format
			  ( new Date( System.currentTimeMillis() ) ) ) );
		try
		    {
			handler = new FileLogHandler( logFilename, formatter );
		    }
		catch( FileNotFoundException fnfe )
		    {
			throw new InitialisationException( fnfe );
		    }
	    }
	else if( handlerClassName.equals
		 ( "ngat.util.logging.ConsoleLogHandler" ) )
	    {
		handler = new ConsoleLogHandler( formatter );
	    }
	else if( handlerClassName.equals
		 ( "ngat.util.logging.MulticastLogHandler" ) )
	    {
		int portNumber = np.getInt( "multicastPort",
					    NGTCS_MULTICAST_PORT );
		try
		    {
			handler = new MulticastLogHandler
			    ( groupName, portNumber, formatter );
		    }
		catch( IOException ioe )
		    {
			throw new InitialisationException( ioe );
		    }
	    }
	else if( handlerClassName.equals
		 ("ngat.util.logging.MulticastLogRelay" ) )
	    {
		int portNumber = np.getInt( "multicastPort",
					    NGTCS_MULTICAST_PORT );

		try
		    {
			handler = new MulticastLogRelay
			    ( groupName, portNumber );
		    }
		catch( IOException ioe )
		    {
			throw new InitialisationException( ioe );
		    }
	    }
	else
	    {
		throw new InitialisationException
		    ( "LogHandler ["+handlerClassName+
		      "] not supported by"+this.getClass().getName() );
	    }
	handler.setLogLevel( NGTCS_MAX_LOG_LEVEL );

	return handler;
    }


    /**
     * Return the list of initialised <code>LogHandlers</code>.
     */
    public List getHandlers()
    {
	return handlerList;
    }
}
/*
 * $Date: 2003-07-01 10:11:30 $
 * $RCSfile: LoggingInitialiser.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/LoggingInitialiser.java,v $
 * $Log: not supported by cvs2svn $
 */
