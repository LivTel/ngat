package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class PluggableSubSystemFactory
    implements PluggableSubSystemCreator
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: PluggableSubSystemFactory.java,v 1.1 2003-07-01 10:13:46 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    public PluggableSubSystem getInstance()
	throws InstantiationException
    {
	String s1 = this.getClass().getName();
	String s2 = ngat.util.StringUtilities.getLeaf( s1, '.' );
	String s3 = s2.substring( 0, s2.lastIndexOf( "Factory" ) );

	try
	    {
		return (PluggableSubSystem)
		    ( Class.forName( s3 ).newInstance() );
	    }
	catch( Exception e )
	    {
		throw new InstantiationException( e.toString() );
	    }
    }
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: PluggableSubSystemFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/PluggableSubSystemFactory.java,v $
 *      $Id: PluggableSubSystemFactory.java,v 1.1 2003-07-01 10:13:46 je Exp $
 *     $Log: not supported by cvs2svn $
 */
