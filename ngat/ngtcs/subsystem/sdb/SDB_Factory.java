package ngat.ngtcs.subsystem.sdb;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SDB_Factory
    implements ngat.ngtcs.subsystem.PluggableSubSystemCreator
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
	new String( "$Id: SDB_Factory.java,v 1.1 2003-09-19 16:09:40 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    protected SDB reference = null;

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
 *    $Date: 2003-09-19 16:09:40 $
 * $RCSfile: SDB_Factory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_Factory.java,v $
 *      $Id: SDB_Factory.java,v 1.1 2003-09-19 16:09:40 je Exp $
 *     $Log: not supported by cvs2svn $
 */
