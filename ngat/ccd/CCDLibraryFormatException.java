// CCDLibraryFormatException.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibraryFormatException.java,v 0.4 1999-09-13 13:56:34 cjm Exp $
package ngat.ccd;

/**
 * This class extends java.lang.IllegalArgumentException. Objects of this class are thrown when an illegal
 * format argument is passed into various parse routines in CCDLibrary.
 * @author Chris Mottram
 * @version $Revision: 0.4 $
 */
public class CCDLibraryFormatException extends IllegalArgumentException
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: CCDLibraryFormatException.java,v 0.4 1999-09-13 13:56:34 cjm Exp $");

	/**
	 * Constructor for the exception.
	 * @param fromClassName The name of the class the exception occured in.
	 * @param methodName The name of the method the exception occured in.
	 * @param illegalParameter The illegal string that could not be parsed by the method.
	 */
	public CCDLibraryFormatException(String fromClassName,String methodName,String illegalParameter)
	{
		super("CCDLibraryFormatException:"+fromClassName+":"+methodName+":Illegal Parameter:"+
			illegalParameter);
	}
}

//
// $Log: not supported by cvs2svn $
// Revision 0.3  1999/09/10 15:33:29  cjm
// Changed package to ngat.ccd.
//
// Revision 0.2  1999/06/07 16:56:41  dev
// String to Number parse routines
//
// Revision 0.1  1999/06/07 10:01:11  dev
// initial revision
//
//