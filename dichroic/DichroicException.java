// DichroicException.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/DichroicException.java,v 1.1 2011-10-12 10:16:23 cjm Exp $
package ngat.dichroic;

/**
 * This class extends Exception. It is used to throw Dichroic level errors.
 * @author Chris Mottram
 * @version $Revision$
 */
public class DichroicException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public DichroicException(String errorString)
	{
		super(errorString);
	}

}

//
// $Log: not supported by cvs2svn $
//
