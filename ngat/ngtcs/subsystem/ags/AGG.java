package ngat.ngtcs.subsystem.ags;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;


import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.net.cil.*;

/**
 * This class is a singleton and represents the TTL Autoguider System.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGG extends TTL_System
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
	new String( "$Id: AGG.java,v 1.1 2003-09-19 16:08:38 je Exp $" );

    /**
     *
     */
    protected static AGG instance = null;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    protected TTL_CIL cil = null;

    /**
     *
     */
    protected AGS ags;

    /**
     *
     */
    protected AGG_GuidePacketReader gpr;

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * 
     */
    public static AGG getInstance()
    {
	if( instance == null )
		instance = new AGG();

	return instance;
    }


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The non-public Constructor required by singleton classes.
     */
    protected AGG()
    {
	super( TTL_Package.AGG );
    }


    /**
     *
     */
    public void initialise( Telescope t )
	throws InitialisationException
    {
	super.initialise( t );

	NGATProperties np = getProperties();

	cil = new TTL_CIL();
	initialiseCIL( np, "", cil );

	gpr = new AGG_GuidePacketReader();

	//vt = new VirtualTelescope( telescope );

	initialised = true;
    }


    /**
     *
     */
    public AGG_GuidePacket getGuidePacket()
      throws TTL_SystemException
    {
	return( gpr.getGuidePacket() );
    }
}
/*
 *    $Date: 2003-09-19 16:08:38 $
 * $RCSfile: AGG.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG.java,v $
 *      $Id: AGG.java,v 1.1 2003-09-19 16:08:38 je Exp $
 *     $Log: not supported by cvs2svn $
 */
