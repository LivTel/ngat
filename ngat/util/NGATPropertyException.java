// NGATPropertyException.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/NGATPropertyException.java,v 0.1 2000-09-06 13:13:07 cjm Exp $
package ngat.util;

public class NGATPropertyException extends Exception
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: NGATPropertyException.java,v 0.1 2000-09-06 13:13:07 cjm Exp $");

    public NGATPropertyException()
    {
	super();
    }

    public NGATPropertyException( Exception e, String a, String b )
    {
	super( "NGATProperty : "+e+" on key ("+a+") value - "+b );
    }
}
//
// $Log: not supported by cvs2svn $
//
