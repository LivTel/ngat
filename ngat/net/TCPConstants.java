// TCPConstants.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPConstants.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

/**
 * The TCPConstants Class keeps constant values for the TCP Connection classes.
 */
public class TCPConstants
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: TCPConstants.java,v 1.1 2008-07-23 12:41:17 eng Exp $");
	/**
	 * The initial value of the commandTimeOut value.
	 * @see #commandTimeOut
	 */
	public final static int DEFAULT_COMMAND_TIMEOUT = 60*1000;
	/**
	 * The initial value of the ackTimeOut value.
	 * @see #ackTimeOut
	 */
	public final static int DEFAULT_ACK_TIMEOUT = 60*1000;
	/**
	 * The Command message timeout value, used to set the socket timeout
	 * when waiting for a command object.
	 */
	public static int commandTimeOut = DEFAULT_COMMAND_TIMEOUT;
	/**
	 * The Acknowledge message timeout value, used to set the socket timeout
	 * when waiting for an acknowledge object.
	 */
	public static int ackTimeOut = DEFAULT_ACK_TIMEOUT;

	/**
	 * Routine to set the timeout used when setting the socket timeout for the acknowledge message.
	 * @param t The time, in milliseconds, to set the timeout to.
	 */
	public static void setAckTimeOut(int t)
	{
		ackTimeOut = t;
	}

	/**
	 * Routine to return the acknowledge timeout, used to set the socket timeout, when awaiting an
	 * acknowledge object over the socket.
	 * @return The time in milliseconds.
	 */
	public static int getAckTimeOut()
	{
		return ackTimeOut;
	}

	/**
	 * Routine to set the timeout used when setting the socket timeout for the command message.
	 * @param t The time, in milliseconds, to set the timeout to.
	 */
	public static void setCommandTimeOut(int t)
	{
		commandTimeOut = t;
	}

	/**
	 * Routine to return the command timeout, used to set the socket timeout, when awaiting an
	 * command object over the socket.
	 * @return The time in milliseconds.
	 */
	public static int getCommandTimeOut()
	{
		return commandTimeOut;
	}
}
// $Log: not supported by cvs2svn $
// Revision 0.2  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 0.1  1999/05/17 12:32:55  dev
// initial revision
//
