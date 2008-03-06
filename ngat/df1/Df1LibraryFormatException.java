// Df1LibraryFormatException.java
// $Header: /space/home/eng/cjm/cvs/ngat/df1/Df1LibraryFormatException.java,v 1.1 2008-03-06 10:46:40 cjm Exp $
package ngat.df1;

/**
 * This class extends java.lang.IllegalArgumentException. Objects of this class are thrown when an illegal
 * format argument is passed into various parse routines in Df1Library.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class Df1LibraryFormatException extends IllegalArgumentException
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: Df1LibraryFormatException.java,v 1.1 2008-03-06 10:46:40 cjm Exp $");

	/**
	 * Constructor for the exception.
	 * @param fromClassName The name of the class the exception occured in.
	 * @param methodName The name of the method the exception occured in.
	 * @param illegalParameter The illegal string that could not be parsed by the method.
	 */
	public Df1LibraryFormatException(String fromClassName,String methodName,String illegalParameter)
	{
		super("Df1LibraryFormatException:"+fromClassName+":"+methodName+":Illegal Parameter:"+
		      illegalParameter);
	}
}

//
// $Log: not supported by cvs2svn $
//
