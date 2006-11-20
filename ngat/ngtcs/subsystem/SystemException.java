package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: cjm $
 * @version $Revision: 1.1 $
 */
public class SystemException extends java.lang.Exception
{
    /*=========================================================================*/
    /*                                                                         */
    /* CLASS FIELDS.                                                           */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
      new String( "$Id:" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/


    /*=========================================================================*/
    /*                                                                         */
    /* CLASS METHODS.                                                          */
    /*                                                                         */
    /*=========================================================================*/


    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT METHODS.                                                         */
    /*                                                                         */
    /*=========================================================================*/

    public SystemException( String s )
    {
	super( s );
    }

    public SystemException()
    {
	super();
    }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: SystemException.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/SystemException.java,v $
 *      $Id: SystemException.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
