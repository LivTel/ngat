// TelnetConnectionListener.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TelnetConnectionListener.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;

/**
 * The TelnetConnectionListener is used with a TelnetConnection instance, that is being run as a thread.
 * The TelnetConnection thread receives input from the connection, and for each line calls
 * the <i>lineRead</i> method in this interface.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public interface TelnetConnectionListener
{
	/**
	 * Method called by TelnetConnection's run method, when it receives a line of input.
	 * @param s The line of input read from the telnet connection.
	 * @see TelnetConnection#run
	 */
	public void lineRead(String line);
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2001/05/14 16:42:24  cjm
// Initial revision
//
//
