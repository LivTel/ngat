// FileUtilitesNativeException.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/FileUtilitesNativeException.java,v 1.1 2001-06-20 15:40:12 cjm Exp $
package ngat.util;

/**
 * This class extends Exception. Objects of this class are thrown when the underlying C code in libngatutil 
 * produces an error. 
 * The JNI interface itself can also generate these exceptions.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class FileUtilitesNativeException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: FileUtilitesNativeException.java,v 1.1 2001-06-20 15:40:12 cjm Exp $");
	/**
	 * The function name supplied to the exception.
	 */
	private String functionName = null;
	/**
	 * The error string supplied to the exception.
	 */
	private String errorString = null;
	/**
	 * The error number supplied to the exception.
	 */
	private int errorNumber = 0;

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public FileUtilitesNativeException(String errorString)
	{
		super(errorString);
		this.functionName = null;
		this.errorString = errorString;
		this.errorNumber = 0;
	}

	/**
	 * Constructor for the exception. 
	 * @param functionName The JNI function in which the error occured.
	 * @param errorString The error string.
	 * @param errorNumber The error number.
	 * @see #functionName
	 * @see #errorString
	 * @see #errorNumber
	 */
	public FileUtilitesNativeException(String functionName,int errorNumber,String errorString)
	{
		super(errorString);
		this.functionName = functionName;
		this.errorString = errorString;
		this.errorNumber = errorNumber;
	}

	/**
	 * Retrieve routine for the function name string supplied to the exception.
	 * @return Returns the function name for this exception.
	 * @see #functionName
	 */
	public String getFunctionName()
	{
		return functionName;
	}

	/**
	 * Retrieve routine for the error string supplied to the exception.
	 * @return Returns the errorString for this exception.
	 * @see #errorString
	 */
	public String getErrorString()
	{
		return errorString;
	}

	/**
	 * Retrieve routine for the error number supplied to the exception.
	 * @return Returns the error number supplied for this exception.
	 * @see #errorNumber
	 */
	public int getErrorNumber()
	{
		return errorNumber;
	}

	/**
	 * Method to format the exception into a string to print.
	 * @return A formatted error string e.g.
	 *	<pre>
	 * 	functionName+" failed: ("+errorNumber+") "+errorString
	 *	</pre>
	 */
	public String toString()
	{
		return new String(functionName+" failed: ("+errorNumber+") "+errorString);
	}
}

//
// $Log: not supported by cvs2svn $
//