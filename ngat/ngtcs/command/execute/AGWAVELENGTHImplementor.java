package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.ags.*;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGWAVELENGTHImplementor
    extends CommandImplementor
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
	new String( "$Id: AGWAVELENGTHImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
    public AGWAVELENGTHImplementor
	( ExecutionThread eT, Telescope t, Command c )
    {
        super( eT, t, c );
    }


    /**
     *
     */
    public void execute()
    {
	TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();

	ag.setWavelength( ( (AGWAVELENGTH)command ).getWavelength() );
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: AGWAVELENGTHImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGWAVELENGTHImplementor.java,v $
 *      $Id: AGWAVELENGTHImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
