// ArcomESSNativeException.java
// $Header: /space/home/eng/cjm/cvs/ngat/serial/arcomess/ArcomESSNativeException.java,v 1.1 2009-02-04 11:26:41 cjm Exp $
package ngat.serial.arcomess;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in ArcomESS produces an
 * error. The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision$
 */
public class ArcomESSNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public ArcomESSNativeException(String errorString)
	{
		super(errorString);
	}

	/**
	 * Constructor for the exception. Used from C JNI interface.
	 * @param errorString The error string.
	 * @param libarcom_ess The ArcomESS instance that caused this excecption.
	 */
	public ArcomESSNativeException(String errorString,ArcomESS libarcom_ess)
	{
		super(errorString);
	}
}

//
// $Log: not supported by cvs2svn $
//
