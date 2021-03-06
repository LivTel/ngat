package ngat.ngtcs.net.cil;

import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_CIL_SendCommand
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

    /**
     *
     */
    protected TTL_CIL cil = null;

    /**
     *
     */
    protected TTL_CIL in_CIL = null;

    /**
     *
     */
    protected TTL_CIL out_CIL = null;

    /**
     *
     */
    protected boolean two_CIL = false;

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
     *
     */
    public TTL_CIL_SendCommand( TTL_CIL c )
    {
	cil = c;
	two_CIL = false;
    }


    /**
     *
     */
    public TTL_CIL_SendCommand( TTL_CIL c1,  TTL_CIL c2 )
    {
	in_CIL = c1;
	out_CIL = c2;
	two_CIL = true;
    }


    /**
     *
     */
    public void sendCommand( TTL_DataType command, int value1, int value2 )
    {

    }
}
/*
 *    $Date: 2013-07-04 10:48:53 $
 * $RCSfile: TTL_CIL_SendCommand.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_SendCommand.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 */
