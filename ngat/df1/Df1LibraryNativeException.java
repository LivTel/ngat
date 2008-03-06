// Df1LibraryNativeException.java
// $Header: /space/home/eng/cjm/cvs/ngat/df1/Df1LibraryNativeException.java,v 1.1 2008-03-06 10:46:40 cjm Exp $
package ngat.df1;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in Df1Library produces an
 * error. The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class Df1LibraryNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: Df1LibraryNativeException.java,v 1.1 2008-03-06 10:46:40 cjm Exp $");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public Df1LibraryNativeException(String errorString)
	{
		super(errorString);
	}

	/**
	 * Constructor for the exception. Used from C JNI interface.
	 * @param errorString The error string.
	 * @param libdf1 The Df1Library instance that caused this excecption.
	 */
	public Df1LibraryNativeException(String errorString,Df1Library libdf1)
	{
		super(errorString);
	}
}

//
// $Log: not supported by cvs2svn $
//
