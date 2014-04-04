// SpecNativeException.java -*- mode: Fundamental;-*-
// Exception thrown from the JNI layer of libspec.
// $Header: /space/home/eng/cjm/cvs/ngat/spec/SpecNativeException.java,v 0.1 2000-10-19 10:42:11 cjm Exp $
package ngat.spec;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in <b>libspec</b> 
 * produces an error. 
 * The individual parts of the error generated are stored in the exception as well as the complete message.
 * The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision$
 */
public class SpecNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Error number returned from the libspec shared C library. 0 is no error.
	 */
	protected int specErrorNumber = 0;
	/**
	 * Error string returned from the libspec shared C library. null is no error string.
	 */
	protected String specErrorString = null;
	/**
	 * Error number returned from the libdaio shared C library. 0 is no error.
	 */
	protected int daioErrorNumber = 0;
	/**
	 * Error string returned from the libdaio shared C library. null is no error string.
	 */
	protected String daioErrorString = null;

	/**
	 * Null parameter constructor for the exception.
	 */
	public SpecNativeException()
	{
		super();
	}

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public SpecNativeException(String errorString)
	{
		super(errorString);
	}

	/**
	 * Constructor for the exception. Sets the internal copies of the current libspec and libdaio
	 * error numbers/strings.
	 * @param sen The libspec error number.
	 * @param ses The libspec error string.
	 * @param den The libdaio error number.
	 * @param des The libdaio error string.
	 * @see #specErrorNumber
	 * @see #specErrorString
	 * @see #daioErrorNumber
	 * @see #daioErrorString
	 */
	public SpecNativeException(int sen,String ses,int den,String des)
	{
		super();
		specErrorNumber = sen;
		specErrorString = ses;
		daioErrorNumber = den;
		daioErrorString = des;		
	}

	/**
	 * Returns the error message string. This consists of the following components:
	 * <ul>
	 * <li>The results of super.getMessage().
	 * <li>The libspec error number.
	 * <li>The libspec error string.
	 * <li>The libdaio error number.
	 * <li>The libdaio error string.
	 * </ul>
	 * Note this string is used as part of the inherited <i>toString</i> method.
	 * @see #specErrorNumber
	 * @see #specErrorString
	 * @see #daioErrorNumber
	 * @see #daioErrorString
	 */
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer();
		String sm = null;

		sm = super.getMessage();
		if(sm != null)
			sb.append(sm);
		if(specErrorNumber != 0)
			sb.append("\nSPEC Error("+specErrorNumber+"):"+specErrorString);
		if(daioErrorNumber != 0)
			sb.append("\nDAIO Error("+daioErrorNumber+"):"+daioErrorString);
		return sb.toString();
	}

	/**
	 * Method to get the error number retrieved from the libspec shared C library.
	 * @return An error number. Zero means the number was not set/no error occured in this library.
	 */
	public int getSpecErrorNumber()
	{
		return specErrorNumber;
	}

	/**
	 * Method to get the error string retrieved from the libspec shared C library.
	 * @return An error string. Null means the string was not set/no error occured in this library.
	 */
	public String getSpecErrorString()
	{
		return specErrorString;
	}

	/**
	 * Method to get the error number retrieved from the libdaio shared C library.
	 * @return An error number. Zero means the number was not set/no error occured in this library.
	 */
	public int getDaioErrorNumber()
	{
		return daioErrorNumber;
	}

	/**
	 * Method to get the error string retrieved from the libdaio shared C library.
	 * @return An error string. Null means the string was not set/no error occured in this library.
	 */
	public String getDaioErrorString()
	{
		return daioErrorString;
	}

}
//
// $Log: not supported by cvs2svn $
//
