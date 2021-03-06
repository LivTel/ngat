// ICSDRebootCommand.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/ICSDRebootCommand.java,v 1.4 2004-08-02 10:45:10 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;
import java.net.*;
import java.text.SimpleDateFormat;

import ngat.net.TelnetConnection;
import ngat.util.logging.*;

/**
 * This class is used to reboot an instrument control system. A telnet conenction is made to
 * an <b>icsd_inet</b> daemon running on the instrument control computer. The command "reboot"
 * is then sent over the connection, and the connection closed. 
 * An example call sequence is presented below:
 * <pre>
 * ICSDRebootCommand command = new ICSDRebootCommand("ltccd1",7368);
 * command.send();
 * </pre>
 * @version $Revision$
 * @author Chris Mottram
 * @see ngat.net.TelnetConnection
 */
public class ICSDRebootCommand extends ICSDCommand implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * String containing the command string to send over the connection.
	 */
	public final static String REBOOT = "reboot";

	/**
	 * Constructor. The address is the current machine, the port number the default.
	 * @exception UnknownHostException Thrown if the current host is not known.
	 * @see ICSDCommand#ICSDCommand()
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDRebootCommand() throws UnknownHostException
	{
		super();
		commandString = REBOOT;
	}

	/**
	 * Constructor.
	 * @param a The address of the host to send the command to.
	 * @see ICSDCommand#ICSDCommand(InetAddress,int)
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDRebootCommand(InetAddress a)
	{
		super(a,DEFAULT_PORT_NUMBER);
		commandString = REBOOT;
	}

	/**
	 * Constructor.
	 * @param addressString A String representing the address of the host to send the command to.
	 * @exception UnknownHostException Thrown if the host address cannot be determined.
	 * @see ICSDCommand#ICSDCommand(String,int)
	 * @see #DEFAULT_PORT_NUMBER
	 */
	public ICSDRebootCommand(String addressString) throws UnknownHostException
	{
		super(addressString,DEFAULT_PORT_NUMBER);
		commandString = REBOOT;
	}

	/**
	 * Constructor. A logger instance and a telnet connection instance are constructed.
	 * @param addressString A string representing the address of the host to send the command to.
	 * 	e.g. "java.sun.com" or "206.26.48.100".
	 * @param p The portnumber of the <b>icsd_inet</b> daemon on the host.
	 * @see ICSDCommand#ICSDCommand(String,int)
	 * @see #telnetConnection
	 * @see #logger
	 * @exception UnknownHostException Thrown by InetAddress.getByName, 
	 * 	if the host address cannot be determined.
	 */
	public ICSDRebootCommand(String addressString,int p) throws UnknownHostException
	{
		super(addressString,p);
		commandString = REBOOT;
	}

	/**
	 * Constructor. A logger instance and a telnet connection instance are constructed.
	 * @param a The address of the host to send the command to.
	 * @param p The portnumber if the <b>icsd_inet</b> daemon on the host.
	 * @see ICSDCommand#ICSDCommand(InetAddress,int)
	 * @see #telnetConnection
	 * @see #logger
	 */
	public ICSDRebootCommand(InetAddress a,int p)
	{
		super(a,p);
		commandString = REBOOT;
	}

	/**
	 * Main program.
	 * @param args Argument list. Should have 2 arguments as follows:
	 * 	<pre>
	 * 	java ICSDRebootCommand &lt;address&gt; &lt;port number&gt;
	 * 	</pre>
	 */
	public static void main(String args[])
	{
		ICSDRebootCommand command = null;
		Logger logger = null;
		LogFormatter logFormatter = null;
		LogHandler logHandler = null;
		String addressString = null;
		int portNumber = 0;

		if((args.length > 2))
		{
			System.err.println("Usage:java ICSDRebootCommand [<address> <port number>]");
			System.err.println("The default port number is:"+DEFAULT_PORT_NUMBER);

			System.exit(1);
		}
		if(args.length > 0)
		{
			addressString = args[0];
		}
		if(args.length == 2)
		{
			try
			{
				portNumber = Integer.parseInt(args[1]);
			}
			catch(NumberFormatException e)
			{
				System.err.println("Failed to parse port number:"+args[1]+":"+e);
				System.exit(2);
			}
		}
		else
			portNumber = DEFAULT_PORT_NUMBER;
		// create (error) logger
		logger = LogManager.getLogger("ngat.util.ICSDRebootCommand");
		logFormatter = new BogstanLogFormatter();
		logFormatter.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS z"));
		logHandler = new ConsoleLogHandler(logFormatter);
		logHandler.setLogLevel(Logging.ALL);
		logger.addHandler(logHandler);
		logger.setLogLevel(Logging.ALL);
		// do command		
		try
		{
			if(args.length > 0)
				command = new ICSDRebootCommand(addressString,portNumber);
			else
				command = new ICSDRebootCommand();
			command.send();
		}
		catch(Exception e)
		{
			System.err.println("Failed:"+e);
			e.printStackTrace();
			System.exit(3);
		}
		System.exit(0);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.3  2001/05/15 12:20:03  cjm
// Changed comment.
//
// Revision 1.2  2001/05/15 09:08:29  cjm
// Fixed comment.
//
// Revision 1.1  2001/05/14 19:02:08  cjm
// Initial revision
//
//
