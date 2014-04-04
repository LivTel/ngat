// ArcomESSFormatException.java
// $Header: /space/home/eng/cjm/cvs/ngat/serial/arcomess/ArcomESSFormatException.java,v 1.1 2009-02-04 11:26:41 cjm Exp $
package ngat.serial.arcomess;

/**
 * This class extends java.lang.IllegalArgumentException. Objects of this class are thrown when an illegal
 * format argument is passed into various parse routines in ArcomESS.
 * @author Chris Mottram
 * @version $Revision$
 */
public class ArcomESSFormatException extends IllegalArgumentException
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
	public ArcomESSFormatException(String fromClassName,String methodName,String illegalParameter)
	{
		super("ArcomESSFormatException:"+fromClassName+":"+methodName+":Illegal Parameter:"+
		      illegalParameter);
	}
}

//
// $Log: not supported by cvs2svn $
//
