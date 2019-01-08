// EIPNativeException.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/EIPNativeException.java,v 1.1 2008-10-09 14:14:21 cjm Exp $
package ngat.eip;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in EIPPLC produces an
 * error. The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision$
 */
public class EIPNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public EIPNativeException(String errorString)
	{
		super(errorString);
	}

	/**
	 * Constructor for the exception. Used from C JNI interface.
	 * @param errorString The error string.
	 * @param plc The EIPPLC instance that caused this excecption.
	 */
	public EIPNativeException(String errorString,EIPPLC plc)
	{
		super(errorString);
	}
}

//
// $Log: not supported by cvs2svn $
//
