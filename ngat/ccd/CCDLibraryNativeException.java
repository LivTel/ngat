// CCDLibraryNativeException.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibraryNativeException.java,v 1.2 1999-09-23 10:45:49 cjm Exp $
package ngat.ccd;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in CCDLibrary produces an
 * error. The individual parts of the error generated are stored in the exception as well as the complete message.
 * The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class CCDLibraryNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: CCDLibraryNativeException.java,v 1.2 1999-09-23 10:45:49 cjm Exp $");
	/**
	 * A type of error that can cause this exception to be created. This type is when the error type
	 * is unknown.
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_NATIVE
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_JNI
	 */
	public final static int CCD_NATIVE_EXCEPTION_TYPE_NONE = 0;
	/**
	 * A type of error that can cause this exception to be created. This type is for errors created
	 * in the native C library code.
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_JNI
	 */
	public final static int CCD_NATIVE_EXCEPTION_TYPE_NATIVE = 1;
	/**
	 * A type of error that can cause this exception to be created. This type is for errors created
	 * by the JNI Java Native Interface functions.
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_NATIVE
	 */
	public final static int CCD_NATIVE_EXCEPTION_TYPE_JNI = 2;
	/**
	 * String representation of the possible error types.
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_NONE
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_NATIVE
	 * @see #CCD_NATIVE_EXCEPTION_TYPE_JNI
	 */
	private String errorTypeString[] = {"None","Native","JNI"};
	/**
	 * The error string supplied to the exception.
	 */
	private String errorString = null;
	/**
	 * The type of error that caused this exception to be created. This is one of
	 * CCD_NATIVE_EXCEPTION_TYPE_NATIVE or CCD_NATIVE_EXCEPTION_TYPE_JNI, depending whether the error
	 * occured in the native code or the JNI interface code.
	 */
	private int errorType = CCD_NATIVE_EXCEPTION_TYPE_NONE;

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public CCDLibraryNativeException(String errorString)
	{
		super(errorString);
		this.errorString = new String(errorString);
		this.errorType = CCD_NATIVE_EXCEPTION_TYPE_NONE;
	}

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 * @param errorType The type of error that caused the exception to be raised. One of
	 * CCD_NATIVE_EXCEPTION_TYPE_NATIVE or CCD_NATIVE_EXCEPTION_TYPE_JNI.
	 */
	public CCDLibraryNativeException(String errorString,int errorType)
	{
		super(errorString);
		this.errorString = new String(errorString);
		if(isErrorType(errorType))
			this.errorType = errorType;
		else
			this.errorType = CCD_NATIVE_EXCEPTION_TYPE_NONE;
	}

	/**
	 * Class routine to create the exception from the actual values in the CCDLibrary. Is uses the 
	 * CCDLibrary routine CCDErrorString to create the error string.
	 * @param libccd The instance of the CCDLibrary class we are getting the error string from.
	 * @return Returns the created exception.
	 * @see CCDLibrary#CCDErrorString
	 */
	public static CCDLibraryNativeException createNativeException(CCDLibrary libccd)
	{
		return new CCDLibraryNativeException(libccd.CCDErrorString(),
			CCDLibraryNativeException.CCD_NATIVE_EXCEPTION_TYPE_NATIVE);
	}

	/**
	 * Class routine to create a JNI related exception.
	 * @param s The error string.
	 * @return Returns the created exception.
	 */
	public static CCDLibraryNativeException createJNIException(String s)
	{
		return new CCDLibraryNativeException(s,CCDLibraryNativeException.CCD_NATIVE_EXCEPTION_TYPE_JNI);
	}

	/**
	 * Retrieve routine for the error string supplied to the exception.
	 * @return Returns a copy of the errorString for this exception.
	 * @see #errorString
	 */
	public String getErrorString()
	{
		return new String(errorString);
	}

	/**
	 * Retrieve routine for the error type supplied to the exception.
	 * @return Returns the error type number supplied for this exception.
	 * @see #errorType
	 */
	public int getErrorType()
	{
		return errorType;
	}

	/**
	 * Routine to get a string representation of the error type supplied to the exception.
	 * @return Returns a string, describing the error type of this exception.
	 * @see #errorType
	 * @see #errorTypeString
	 */
	public String getErrorTypeString()
	{
		return new String(errorTypeString[errorType]);
	}

	/**
	 * Private check routine for the error type supplied to the exception.
	 * @param errorType An integer to be checked to see it if is a legal error type.
	 * @return Returns a boolean, true if the supplied number is a legal error type and false otherwise.
	 * @see #errorType
	 */
	private boolean isErrorType(int errorType)
	{
		return ((errorType == CCD_NATIVE_EXCEPTION_TYPE_NONE)||
			(errorType == CCD_NATIVE_EXCEPTION_TYPE_NATIVE)||
			(errorType == CCD_NATIVE_EXCEPTION_TYPE_JNI));
	}
}

//
// $Log: not supported by cvs2svn $
// Revision 1.1  1999/09/20 14:40:08  cjm
// Initial revision
//
//