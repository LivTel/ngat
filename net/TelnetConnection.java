// TelnetConnection.java
// $Header: /space/home/eng/cjm/cvs/ngat/net/TelnetConnection.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.io.*;
import java.lang.*;
import java.net.*;

/**
 * The TelnetConnection Class opens and closes a telnet type connection (TCP/IP connection sending ASCII text)
 * and allows the user to send and receive strings over it.
 * The class includes a main method for testing, and a startable thread to deal with out from the server
 * being connected to. It implments Runnable.
 * @author Chris Mottram
 * @version $Revision$
 */
public class TelnetConnection implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The address the connetion is made to.
	 */
	protected InetAddress address = null;
	/**
	 * The port number of the connection.
	 */
	protected int portNumber = 0;
	/**
	 * The socket of the connection (after it has been opened).
	 */
	protected Socket socket = null;
	/**
	 * A stream, on top of the socket connection, to which all output is written.
	 * @see #socket
	 */
	protected PrintWriter outputWriter = null;
	/**
	 * A stream, on top of the socket connection, to receive all input.
	 * @see #socket
	 */
	protected BufferedReader inputReader = null;
	/**
	 * Listener interface, whose lineRead method is called for each line of input received by a TelnetConnection
	 * instance being run as a thread.
	 * @see #run
	 */
	protected TelnetConnectionListener listener = null;

	/**
	 * Default constructor.
	 */
	public TelnetConnection()
	{
		super();
	}

	/**
	 * Constructor.
	 * @param a The address of the connection.
	 * @param p The port number of the connection.
	 * @see #setAddress
	 * @see #setPortNumber
	 */
	public TelnetConnection(InetAddress a,int p)
	{
		super();
		setAddress(a);
		setPortNumber(p);
	}

	/**
	 * Constructor.
	 * @param a A String representing the address of the connection.
	 * @param p The port number of the connection.
	 * @exception UnknownHostException Thrown by setAddress, 
	 * 	if the host address cannot be determined from it's name.
	 * @see #setAddress
	 * @see #setPortNumber
	 */
	public TelnetConnection(String addString,int p)  throws UnknownHostException
	{
		super();
		setAddress(addString);
		setPortNumber(p);
	}

	/**
	 * Method to set the address of the connection.
	 * @param a The address.
	 * @see #address
	 */
	public void setAddress(InetAddress a)
	{
		address = a;
	}

	/**
	 * Method to set the address of the connection. The address is parsed by
	 * InetAddress.
	 * @param addressName The address of the connection as a string, 
	 * 	e.g. "java.sun.com" or "206.26.48.100".
	 * @exception UnknownHostException Thrown by InetAddress.getByName, 
	 * 	if the host address cannot be determined.
	 * @see #address
	 */
	public void setAddress(String addressName) throws UnknownHostException
	{
		address = InetAddress.getByName(addressName);
	}

	/**
	 * Method to set the port number of the connection.
	 * @param a The port number.
	 * @see #portNumber
	 */
	public void setPortNumber(int p)
	{
		portNumber = p;
	}

	/**
	 * Method to open a connection, using the address and port number given previously.
	 * @exception IOException Thrown if an IO error occurs when creating the socket.
	 * @exception NullPointerException Thrown if the address is null.
	 * @see #address
	 * @see #portNumber
	 * @see #socket
	 * @see #inputReader
	 * @see #outputWriter
	 */
	public void open() throws IOException,NullPointerException
	{
		if(address == null)
		{
			throw new NullPointerException(this.getClass().getName()+":open:Address was null:port number="+
				portNumber);
		}
		socket = new Socket(address,portNumber);
		outputWriter = new PrintWriter(socket.getOutputStream(),true);
		inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * This method closes an open connection. The connection should have been previously opened
	 * with open.
	 * @exception IOException Thrown if the output stream flush or close fails.
	 * @exception NullPointerException Thrown if outputWriter or inputReader is null.
	 * @see #open
	 * @see #outputWriter
	 * @see #inputReader
	 * @see #socket
	 */
	public void close() throws IOException,NullPointerException
	{
		if(outputWriter == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":close:Output Stream was null:socket="+
				socket+":address="+address+":port number="+portNumber);
		}
		outputWriter.flush();
		outputWriter.close();
		outputWriter = null;
		if(inputReader == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":close:Input Stream was null:socket="+
				socket+":address="+address+":port number="+portNumber);
		}
		inputReader.close();
		inputReader = null;
		socket.close();
		socket = null;
	}

	/**
	 * Method to read a line from the input.
	 * @return A string of the next line of input from the telnet connection. The line-termination is <b>NOT</b>
	 * 	included. If the end of the stream is found <i>null</i> is returned.
	 * @exception IOException Thrown if reading the line failed.
	 * @exception NullPointerException Thrown if inputReader is null. 
	 * 	Ensure <i>open</i> has been called before this method.
	 * @see #inputReader
	 */
	public String readLine() throws IOException, NullPointerException 
	{
		String replyString = null;

		if(inputReader == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":readLine:input reader was null:address="+
				address+":port number="+portNumber);
		}
		replyString = inputReader.readLine();
		return replyString;
	}

	/**
	 * Method to send a string over the telnet connection. A new-line is <b>NOT</b> sent at the end
	 * of the line.
	 * @param s The string to send.
	 * @exception NullPointerException Thrown if outputWriter is null. 
	 * 	Ensure <i>open</i> has been called before this method.
	 */
	public void send(String s) throws NullPointerException
	{
		if(outputWriter == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":send:output writer was null:address="+
				address+":port number="+portNumber);
		}
		outputWriter.print(s);
	}

	/**
	 * Method to send a string over the telnet connection. A new-line is sent at the end
	 * of the line.
	 * @param s The string to send.
	 * @exception NullPointerException Thrown if outputWriter is null. 
	 * 	Ensure <i>open</i> has been called before this method.
	 */
	public void sendLine(String s) throws NullPointerException
	{
		if(outputWriter == null)
		{
			throw new NullPointerException(this.getClass().getName()+
				":sendLine:output writer was null:address="+
				address+":port number="+portNumber);
		}
		outputWriter.println(s);
	}

	/**
	 * Method to set the listener for this connection. This listener is called each time the run
	 * method receives a line of input (assuming this connection instance is being run in a thread).
	 * @param l The listener. This can be null (input is received but discarded).
	 * @see #listener
	 */
	public void setListener(TelnetConnectionListener l)
	{
		listener = l;
	}

	/**
	 * Method that implements the Runnable interface.
	 * This enters a while loop, calling <i>readLine</i> until it returns null 
	 * (the end of the stream has been reached).
	 * Each time <i>readLine</i> returns non-null, if the <i>listener</i> is non-null, this is send the line.
	 * @see #readLine
	 * @see #listener
	 */
	public void run()
	{
		String currentLine = null;
		boolean done = false;

		done = false;
		try
		{
			while(done == false)
			{
				currentLine = readLine();
				if(currentLine != null)
				{
					if(listener != null)
						listener.lineRead(currentLine);
				}
				else // end of stream reached.
					done = true;
			}// end while !done
		}
		catch(Exception e)
		{
			System.err.println(this.getClass().getName()+":run:"+e);
		}
	}

	/**
	 * Main method, for executing as a program.
	 * @param args The argument list. This should be as follows:
	 * 	<pre>
	 * 	java TelnetConnection &lt;address&gt; &lt;port number&gt; &lt;Command string&gt;
	 * 	</pre>
	 */
	public static void main(String args[])
	{
		TelnetConnection tc = null;
		Thread thread = null;
		int portNumber = 0;

		if(args.length != 3)
		{
			System.err.println("Usage:java TelnetConnection <address> <port number> <Command string>");
			System.exit(1);
		}
		try
		{
			portNumber = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e)
		{
			System.err.println("Failed to parse port number:"+args[1]+":"+e);
			System.err.println("Usage:java TelnetConnection <address> <port number> <Command string>");
			System.exit(2);
		}
		try
		{
			tc = new TelnetConnection(args[0],portNumber);
			tc.setListener(tc. new DefaultTelnetConnectionListener(new PrintWriter(System.out,true)));
			tc.open();
			thread = new Thread(tc,"Reader thread");
			thread.start();
			tc.sendLine(args[2]);
			thread.join();
			tc.close();
		}
		catch(Exception e)
		{
			System.err.println("Failed:"+e);
			System.exit(3);
		}
		System.exit(0);
	}

	/**
	 * Inner class. This implements TelnetConnectionListener, and prints all
	 * input received from a connection to the specified output writer.
	 */
	public class DefaultTelnetConnectionListener implements TelnetConnectionListener
	{
		/**
		 * The print writer to output all input to.
		 */
		protected PrintWriter outputWriter = null;

		/**
	 	 * Constructor.
		 * @param pw The print writer, where all input received from a connection will be sent.
		 */
		public DefaultTelnetConnectionListener(PrintWriter pw)
		{
			super();
			outputWriter = pw;
		}

		/**
	 	 * Method implementing the interface. If <i>outputWriter</i> is non-null,
		 * <i>line</i> is written to outputWriter. Otherwise it is writtn to System.out.
		 * @param line The line of input received over the telnet connection.
		 */ 
		public void lineRead(String line)
		{
			if(outputWriter != null)
				outputWriter.println(line);
			else
				System.out.println(line);
		}
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.4  2004/07/30 15:04:47  cjm
// Removed fundamental.
//
// Revision 1.3  2001/05/15 09:10:09  cjm
// Fixed comment.
//
// Revision 1.2  2001/05/14 17:41:56  cjm
// Swapped NullPointerException's for Exceptions when testing whether fields were null.
//
// Revision 1.1  2001/05/14 16:42:36  cjm
// Initial revision
//
//
