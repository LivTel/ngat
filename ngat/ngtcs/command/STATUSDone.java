package ngat.ngtcs.command;

import java.util.List;
import java.util.Vector;

import ngat.ngtcs.common.*;

/**
 * This class is used to return the status of the telescope and/or any 
 * specified mechanisms on that Telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class STATUSDone extends CommandDone
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
	new String( "$Id: STATUSDone.java,v 1.1 2003-07-01 10:12:39 je Exp $" );


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Telescope status
     */
    private TelescopeStatus telescopeStatus = null;

    /**
     * Vector containing Status objects.
     */
    private List statusList = null;

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
     * Constructor.
     */
    public STATUSDone( STATUS status )
    {
	super( status );
	statusList = new Vector();
    }


    /**
     * Add the given TelescopeStatus to the List which are to be returned.
     * @param ts the TelescopeStatus to be added
     */
    public void addTelescopeStatus( TelescopeStatus s )
    {
	telescopeStatus = s;
    }


    /**
     * Return the TelescopeStatus object
     * @return telescopeStatus
     */
    public TelescopeStatus getTelescopeStatus()
    {
	return telescopeStatus;
    }


    /**
     * Add the specified Status object to the List which are to be returned.
     * @param s the Status object to be added
     */
    public void addStatus( Status s )
    {
	statusList.add( s );
    }


    /**
     * Return the List of Status added to this object.
     * @return status list
     */
    public List getStatus()
    {
	return statusList;
    }
}
/*
 * $Date: 2003-07-01 10:12:39 $
 * $RCSfile: STATUSDone.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/STATUSDone.java,v $
 * $Log: not supported by cvs2svn $
 */
