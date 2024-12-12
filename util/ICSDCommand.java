// ICSDCommand.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/ICSDCommand.java,v 1.2 2004-08-02 10:45:06 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;
import java.net.*;

import ngat.net.TelnetConnection;
import ngat.util.logging.*;

/**
 * This class implements a generic method to send a command to an instance of <b>icsd_inet</b>. This is a daemon,
 * written in C, that sits on an INET socket waiting for connections. A telnet connection is made to
 * the daemon running on the instrument control computer. The a command string 
 * is then sent over the connection, and the connection closed. 
 * @version $Revision$
 * @author Chris Mottram
 * @see ngat.net.TelnetConnection
 */
public class ICSDCommand implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Constant defining the log level to send for error messages generated by instances of this class.
	 */
	public final static int LOG_LEVEL_ERROR = 1;
	/**
	 * The default port number the <b>icsd_inet</b> daemon sits on.
	 */
	protected static int DEFAULT_PORT_NUMBER = 7368; /* ID */
	/**
	 * The command string to send over the opened connection.
	 */
	protected String commandString = null;
	/**
	 * The instance of TelnetConnection used to send the command to the icsd_inet daemon.
	 */
	protected TelnetConnection telnetConnection = null;
	/**
	 * The logger to report errors to.
	 */
	protected Logger logger = null;

	/**
	 * Constructor. The address is the current machine, the port number the default.
	 * @exception UnknownHostException Thrown if the current host is not known.
	 * @see #ICSDCommand(InetAddress,int)
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDCommand() throws UnknownHostException
	{
		this(InetAddress.getLocalHost(),DEFAULT_PORT_NUMBER);
	}

	/**
	 * Constructor.
	 * @param a The address of the host to send the command to.
	 * @see #ICSDCommand(InetAddress,int)
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDCommand(InetAddress a)
	{
		this(a,DEFAULT_PORT_NUMBER);
	}

	/**
	 * Constructor.
	 * @param addressString A String representing the address of the host to send the command to.
	 * @exception UnknownHostException Thrown if the host address cannot be determined.
	 * @see #ICSDCommand(String,int)
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDCommand(String addressString) throws UnknownHostException
	{
		this(addressString,DEFAULT_PORT_NUMBER);
	}

	/**
	 * Constructor. A logger instance and a telnet connection instance are constructed.
	 * @param addressString A string representing the address of the host to send the command to.
	 * 	e.g. "java.sun.com" or "206.26.48.100".
	 * @param p The portnumber of the <b>icsd_inet</b> daemon on the host.
	 * @see #ICSDCommand(InetAddress,int)
	 * @see #telnetConnection
	 * @see #logger
	 * @exception UnknownHostException Thrown by InetAddress.getByName, 
	 * 	if the host address cannot be determined.
	 */
	public ICSDCommand(String addressString,int p) throws UnknownHostException
	{
		this(InetAddress.getByName(addressString),p);
	}

	/**
	 * Constructor. A logger instance and a telnet connection instance are constructed.
	 * @param a The address of the host to send the command to.
	 * @param p The portnumber of the <b>icsd_inet</b> daemon on the host.
	 * @see #telnetConnection
	 * @see #logger
	 */
	public ICSDCommand(InetAddress a,int p)
	{
		super();
		telnetConnection = new TelnetConnection(a,p);
		logger = LogManager.getLogger(this);
	}

	/**
	 * The run method, called to actually send the command to the daemon.
	 * This calls the <i>send</i> method to actually send the command to the icsd_inet.
	 * Any exception are caught and logged to the default logger.
	 * @see #send
	 */
	public void run()
	{
		try
		{
			send();
		}
		catch(Exception e)
		{
			if(logger != null)
			{
				logger.log(LOG_LEVEL_ERROR,"run failed",e);
				logger.dumpStack(LOG_LEVEL_ERROR,e);
			}
		}
	}

	/**
	 * This method sends <i>commandString</i> over the telnet conenction <i>telnetConnection</i>.
	 * This is done by calling the following:
	 * <ul>
	 * <li>telnetConnection.open()
	 * <li>telnetConnection.sendLine(commandString)
	 * <li>telnetConnection.close()
	 * </ul>
	 * @exception IOException Thrown if open/close fails.
	 * @exception NullPointerException Thrown if open/close/sendLine fails, or the commandString is null.
	 * @see #telnetConnection
	 * @see #commandString
	 */
	public void send() throws IOException,NullPointerException
	{
		if(commandString == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":send:The command string was null.");
		}
		telnetConnection.open();
		telnetConnection.sendLine(commandString);
		telnetConnection.close();
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2001/05/14 19:02:35  cjm
// Initial revision
//
//