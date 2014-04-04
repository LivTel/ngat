package ngat.util;

/**
 * This Exception is thrown when parsing of an <code>NGATProperties</code>
 * propertyfails, either through the value for the property key does not exist
 * (value = <code>null</code>), or the type expected cannot be parsed from the
 * <code>String</code> value e.g.
 * <br>
 * <pre>
 * int i = Integer.parseInt( "not a String representation of an int" );
 * </pre>
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 */
public class NGATPropertyException extends Exception
{
    /**
     * Revision Control System id string, showing the version of the Class.
     */
    public final static String RCSID = new String("$Id$");

    /**
     * The basic Constructor.
     */
    public NGATPropertyException()
    {
	super();
    }

    /**
     * The NGATPropertyException-specific Constructor, detailing the reason
     * parsing failed (<code>Exception <b>e</b></code>), the key used to
     * search the properties (<b>a</b>) and the returned value from the
     * key-search (<b>b</b>).
     */
    public NGATPropertyException( Exception e, String a, String b )
    {
	super( e+" on key ["+a+"] and value ["+b+"]" );
    }
}
/*
 * $Date: 2002-10-23 09:57:27 $
 * $RCSfile: NGATPropertyException.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/util/NGATPropertyException.java,v $
 * $Log: not supported by cvs2svn $
 * Revision 0.2  2002/09/27 12:59:56  je
 * Documentation added.
 *
 * Revision 0.1  2000/09/06 13:13:07  cjm
 * initial revision.
 *
 */
