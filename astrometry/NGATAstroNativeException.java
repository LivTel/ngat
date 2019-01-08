// NGATAstroNativeException.java
// $Header$
package ngat.astrometry;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in NGATAstro produces an
 * error. The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision$
 */
public class NGATAstroNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public NGATAstroNativeException(String errorString)
	{
		super(errorString);
	}
}

