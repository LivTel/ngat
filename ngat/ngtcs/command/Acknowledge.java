package ngat.ngtcs.command;

import java.util.*;

/**
 * The Acknowledge message is returned from the NGTCS to the specified
 * <code><b>Communicator</b></code> object being used on the Telescope system.
 * An Acknowledge is returned after command receipt with a timeout.  Further
 * Acknowledges may be sent to increase this timeout.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class Acknowledge extends Message
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
	new String( "$Id: Acknowledge.java,v 1.1 2003-07-01 10:12:39 je Exp $" );

    /**
     * Total number of acknowledges sent.
     */
    private static long totalAcknowledgeNumber = 0;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * This acknowledges number.
     */
    private long acknowledgeNumber = 0;

    /**
     * Time to complete Command, in seconds.
     */
    private int timeToComplete;

    /**
     * Command for which this is an Acknowledge for.
     */
    private Command command;


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Return the total number of acknowledge sent.
     * @return <code>totalAcknowledgeNumber</code>
     * @see #totalAcknowledgeNumber
     */
    public static long getTotalAcknowledgeNumber()
    {
	return totalAcknowledgeNumber;
    }


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Constructor.
     * <br>
     * Sets the ID of this Acknowledge, and increments the acknowledge counters
     * accordingly.
     * @param s the ID string of this acknowledge.
     * @param c the <code>Command</code> for which this is the <code>Ack</code>
     */
    public Acknowledge( String s, Command c )
    {
	super( s );
	command = c;
	acknowledgeNumber = ++totalAcknowledgeNumber;

    }


    /**
     * Set the completion time for the <code>Command</code> specified in the
     * constructor.
     */
    public void setTimeToComplete( int i )
    {
	timeToComplete = i;
    }


    /**
     * Get the completion time for the <code>Command</code> specified in the
     * constructor.
     */
    public int getTimeToComplete()
    {
	return timeToComplete;
    }


    /**
     * Return the number of this acknowledge.
     * @return <code>acknowledgeNumber</code>
     * @see #acknowledgeNumber
     */
    public long getAcknowledgeNumber()
    {
	return acknowledgeNumber;
    }


    /**
     * Return the <code>Command</code> for which this is an Acknowledgement.
     * @return command
     * @see #command
     */
    public Command getCommand()
    {
	return command;
    }
}
/*
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: Acknowledge.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/Acknowledge.java,v $
 *      $Id: Acknowledge.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */
