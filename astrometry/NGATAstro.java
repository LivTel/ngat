// NGATAstro.java
// $HeadURL$
package ngat.astrometry;

import java.util.Date;

import ngat.util.logging.*;

/**
 * This class wraps the ngatastro library, which provides an MJD calculation method not using Slalib code.
 * @author Chris Mottram
 * @version $Revision$
 */
public class NGATAstro
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Static code to load libngatastrometrywcstools, the shared C library that implements an interface to the
	 * WCSTools routines.
	 */
	static
	{
		System.loadLibrary("ngatastrojni");
	}

//internal C layer initialisation
	/**
	 * Native method that allows the JNI layer to store a reference to this Class's logger.
	 * @param logger The logger for this class.
	 */
	private native void initialiseLoggerReference(Logger logger);
	/**
	 * Native method that allows the JNI layer to release the global reference to this Class's logger.
	 */
	private native void finaliseLoggerReference();
	/**
	 * Get the Modified Julian Date for the specified timestamp.
	 * @param timeMillis The time in milliseconds since the epoch (1st Jan 1970).
	 * @return The Modified Julian Date in decimal days.
	 * @exception Exception Thrown if an error occurs.
	 */
	protected static native double GetMJD(long timeMillis) throws Exception;

// per instance variables
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

	/**
	 * Constructor for NGATAstro. Constructs the logger, and sets the C layers reference to it.
	 * @see #logger
	 * @see #initialiseLoggerReference
	 */
	public NGATAstro()
	{
		super();
		logger = LogManager.getLogger(this);
		initialiseLoggerReference(logger);
	}

	/**
	 * Finalize method for this class, delete JNI global references.
	 * @see #finaliseLoggerReference
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		finaliseLoggerReference();
	}

	/**
	 * Get the Modified Julian Date for now. System.currentTimeMillis() is used to get the current number of
	 * milliseonds since the epoch, which is passed to GetMJD.
	 * @return The Modified Julian Date in decimal days.
	 * @exception Exception Thrown if an error occurs.
	 * @see #GetMJD
	 */
	public static  double getMJD() throws Exception
	{
		return GetMJD(System.currentTimeMillis());
	}

	/**
	 * Get the Modified Julian Date for the specified date. date.getTime() is used to convert the date
	 * into milliseconds since the epoch.
	 * @param date The timestamp to get the MJD for.
	 * @return The Modified Julian Date in decimal days.
	 * @exception Exception Thrown if an error occurs.
	 * @see #GetMJD
	 */
	public static  double getMJD(Date date) throws Exception
	{
		return GetMJD(date.getTime());
	}

	/**
	 * Get the Modified Julian Date for the specified timestamp.
	 * @param timeMillis The time in milliseconds since the epoch (1st Jan 1970).
	 * @return The Modified Julian Date in decimal days.
	 * @exception Exception Thrown if an error occurs.
	 * @see #GetMJD
	 */
	public static  double getMJD(long timeMillis) throws Exception
	{
		return GetMJD(timeMillis);
	}
}
