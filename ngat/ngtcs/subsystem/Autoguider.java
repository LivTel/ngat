package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class Autoguider extends BasicMechanism
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Autoguider.java,v 1.1 2003-07-01 10:13:46 je Exp $" );


    /**
     *
     */
    public Autoguider()
    {
	super();
    }


    /**
     *
     */
    public void initialise( Telescope t ) throws InitialisationException
    {
	super.initialise( t );
    }
    
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: Autoguider.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Autoguider.java,v $
 *     $Log: not supported by cvs2svn $
 */
