package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_SystemException extends java.lang.Exception
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
	new String( "$Id: TTL_SystemException.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

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

    public TTL_SystemException( String s )
    {
	super( s );
    }

    public TTL_SystemException()
    {
	super();
    }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_SystemException.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SystemException.java,v $
 *      $Id: TTL_SystemException.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
