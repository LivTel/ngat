package ngat.ngtcs.subsystem.sdb;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SDB_Factory
    implements ngat.ngtcs.subsystem.PluggableSubSystemCreator
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
	new String( "$Id$" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    protected SDB reference = null;

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

    /**
     * The PluggableSubSyustemCreator getInstance method - returning the ONLY
     * instance of a SDB.
     * @return the ONLY SDB reference
     */
    public ngat.ngtcs.subsystem.PluggableSubSystem getInstance()
	throws InstantiationException
    {
	 return SDB.getInstance();
    }
}
/*
 *    $Date: 2013-07-04 12:59:32 $
 * $RCSfile: SDB_Factory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_Factory.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:40  je
 *     Initial revision
 *
 */
