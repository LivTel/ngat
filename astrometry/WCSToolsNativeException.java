// WCSToolsNativeException.java
// // $Header: /space/home/eng/cjm/cvs/ngat/astrometry/WCSToolsNativeException.java,v 1.1 2013-06-28 10:11:49 cjm Exp $
package ngat.astrometry;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in WCSTools produces an
 * error. The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision$
 */
public class WCSToolsNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public WCSToolsNativeException(String errorString)
	{
		super(errorString);
	}
}

//
// $Log: not supported by cvs2svn $
// Revision 1.1  2007/09/27 15:35:59  cjm
// Initial revision
//
//
