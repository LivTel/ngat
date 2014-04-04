// EIPFormatException.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/EIPFormatException.java,v 1.1 2008-10-09 14:14:21 cjm Exp $
package ngat.eip;

/**
 * This class extends java.lang.IllegalArgumentException. Objects of this class are thrown when an illegal
 * format argument is passed into various parse routines in EIPPLC.
 * @author Chris Mottram
 * @version $Revision$
 */
public class EIPFormatException extends IllegalArgumentException
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param fromClassName The name of the class the exception occured in.
	 * @param methodName The name of the method the exception occured in.
	 * @param illegalParameter The illegal string that could not be parsed by the method.
	 */
	public EIPFormatException(String fromClassName,String methodName,String illegalParameter)
	{
		super("EIPFormatException:"+fromClassName+":"+methodName+":Illegal Parameter:"+
		      illegalParameter);
	}
}

//
// $Log: not supported by cvs2svn $
//
