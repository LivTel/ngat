package ngat.ngtcs.common;

/**
 * Type-safe enumerations.
 * =======================
 *
 * These are the only instances of this class that can be used, defining 
 * possible States for any NGTCS-pluggable modules.
 *
 * This class should be sub-classed for all PluggableSubSystem-specific states 
 * that need to be defined.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class State extends ngat.util.TypeSafeEnumeration
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: State.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Constructor.  Private to ensure that the only instances of this class 
     * are those declared in this class.
     * @param s the name of the state
     * @param i the int representation of the state
     */
    protected State( String s, int i )
    {
	super( s, i );
    }


    /**
     * Constructor.  Private to ensure that the only instances of this class 
     * are those declared in this class.
     * @param s the name of the state
     */
    protected State( String s )
    {
	super( s );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/State.java,v $
 *      $Id: State.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
