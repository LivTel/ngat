// CCDLibraryFormatException.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/CCDLibraryFormatException.java,v 0.1 1999-06-07 10:01:11 dev Exp $
/**
 * This class extends java.lang.IllegalArgumentException. Objects of this class are thrown when an illegal
 * format argument is passed into various parse routines in CCDLibrary.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
class CCDLibraryFormatException extends IllegalArgumentException
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: CCDLibraryFormatException.java,v 0.1 1999-06-07 10:01:11 dev Exp $");

	public CCDLibraryFormatException(String fromClassName,String methodName,String illegalParameter)
	{
		super(this.getClass().getName()+":"+fromClassName+":"+methodName+":Illegal Parameter:"+
			illegalParameter);
	}
}

//
// $Log: not supported by cvs2svn $
//