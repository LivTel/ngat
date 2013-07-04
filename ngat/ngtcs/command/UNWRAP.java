package ngat.ngtcs.command;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class UNWRAP extends ngat.ngtcs.command.Command
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
	new String( "$Id: UNWRAP.java,v 1.3 2013-07-04 10:08:34 cjm Exp $" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * Mechanism to unwrap.
     */
    protected TrackingMechanism mechanism;

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
     * Create an UNWRAP command with the specified ID, to unwrap the specified 
     * mechanism.  The mechanism argument must either be
     * <code>TrackingMechanmism.AZIMUTH</code> or
     * <code>TrackingMechanmism.ROTATOR</code> or an IllegalArgumentException
     * will be thrown.
     * @param s the String ID of this command
     * @param t the TrackingMechanism to unwrap
     */
    public UNWRAP( String s, TrackingMechanism t )
    {
	super( s );

	if( ( t != TrackingMechanism.AZIMUTH )&&
	    ( t != TrackingMechanism.ROTATOR ) )
	    throw new IllegalArgumentException
		( "Tarcking Mechanims to unwrap must be "+
		  TrackingMechanism.AZIMUTH.getName()+" or "+
		  TrackingMechanism.ROTATOR.getName() );

	mechanism = t;
    }
}
/*
 *    $Date: 2013-07-04 10:08:34 $
 * $RCSfile: UNWRAP.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/UNWRAP.java,v $
 *      $Id: UNWRAP.java,v 1.3 2013-07-04 10:08:34 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
