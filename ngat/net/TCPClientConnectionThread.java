// TCPClientConnectionThread.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPClientConnectionThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;

import ngat.message.base.*;

/**
 * The TCPClientConnectionThread extends thread. It implements the generic ISS instrument command protocol.
 * @author Chris Mottram
 * @version $Revision$
 */
public class TCPClientConnectionThread extends Thread
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Default buffer size for input and output buffers.
	 */
	public final static int DEFAULT_BUFFER_SIZE = 2048;
	/**
	 * The address the thread is communicating to.
	 */
	protected InetAddress address = null;
	/**
	 * The port number the thread is communicating to.
	 */
	protected int portNumber = 0;
	/**
	 * The socket the thread is communicating over.
	 */
	protected Socket socket = null;
	/**
	 * The name of the thread.
	 */
	protected String name = null;
	/**
	 * The object input stream to get reply objects from.
	 */
	protected ObjectInputStream inputStream = null;
	/**
	 * The object output stream to send commands to.
	 */
	protected ObjectOutputStream outputStream = null;
	/**
	 * The size of buffer to pass into BufferedInput/OutputStream for buffering.
	 */
	protected int inputOutputBufferSize = DEFAULT_BUFFER_SIZE;
	/**
	 * The stream to report errors on (The System err stream by default).
	 */
	 protected PrintWriter errorStream = new PrintWriter(System.err,true);
	/**
	 * The Command message sent from the client.
	 */
	protected COMMAND command = null;
	/**
	 * The acknowledge message returned to the client.
	 */
	protected ACK acknowledge = null;
	/**
	 * The done message returned to the client.
	 */
	protected COMMAND_DONE done = null;

	/**
	 * Constructor of the thread. 
	 * @param address The address of the socket to communicate to.
	 * @param portNumber The port number to communicate to.
	 * @param c The command to communicate.
	 */
	public TCPClientConnectionThread(InetAddress address,int portNumber,COMMAND c)
	{
		super();
		command = c;
		this.address = address;
		this.portNumber = portNumber;
		name = new String(this.getClass().getName());
		setName(name);
	}

	/**
	 * Routine to set this threads buffering for input/output operations over the socket connection.
	 * The default buffer size is DEFAULT_BUFFER_SIZE. This method should be called before the
	 * thread is started, where the buffer is allocated.
	 * @param size The size to set the BufferedInput/OutputStream buffer size to. This should
	 * 	be a positive integer.
	 * @see #inputOutputBufferSize
	 * @see #DEFAULT_BUFFER_SIZE
	 */
	public void setIOBufferSize(int size)
	{
		if(size > -1)
			inputOutputBufferSize = size;
	}

	/**
	 * The run method, which implements the ISS Generic Instrument Command Protocol. This consists of:
	 * <dl>
	 * <dt><a href="#sendCommand">sendCommand</a></dt>
	 *	<dd>Send the COMMAND object from the client via the socket.</dd>
	 * <dt><a href="#getAcknowledgeFromServer">getAcknowledgeFromServer</a></dt> 
	 * 	<dd>Get the acknowledge time back to the client.</dd>
	 * <dt><a href="#processAcknowledge">processAcknowledge</a></dt> 
	 * 	<dd>Do anything relating to getting the acknowledge.</dd>
	 * <dt><a href="#getDoneFromServer">getDoneFromServer</a></dt> 
	 * 	<dd>Get the message to inform the client we have finished processing that command.</dd>
	 * <dt><a href="#processDone">processDone</a></dt> 
	 * 	<dd>Do anything relating to getting the done message.</dd>
	 * <dt><a href="#close">close</a></dt> 
	 * 	<dd>Close the clients socket.</dd>
	 * </dl>
	 * The following methods should be overridden in subclasses:
	 * <ul>
	 * <li><a href="#processAcknowledge">processAcknowledge</a></li>
	 * <li><a href="#processDone">processDone</a></li>
	 * </ul>
	 * @see #sendCommand
	 * @see #getAcknowledgeFromServer
	 * @see #processAcknowledge
	 * @see #getDoneFromServer
	 * @see #processDone
	 * @see #close
	 */
	public void run()
	{
	// test command and rename thread
		if(command == null)
		{
			synchronized(errorStream)
			{
				errorStream.println(name+":run:command was null");
			}
			return;
		}
		name = new String(this.getClass().getName()+
			":command:"+command.getClass().getName());
		setName(name);
		try
		{
		// try opening socket
			socket = new Socket(address,portNumber);
		// rename thread
			name = new String(this.getClass().getName()+
				":command:"+command.getClass().getName()+
				":remote:"+socket.getInetAddress().toString()+":"+socket.getPort()+
				":local:"+socket.getLocalAddress().toString()+":"+socket.getLocalPort());
			setName(name);
		// attempt to get object input/output streams.
			getOutputStream();
			getInputStream();
		// try to send command to client
			sendCommand();
		// get time to complete command.
			getAcknowledgeFromServer();
		// do something with the acknowledge command.
			try
			{
				processAcknowledge();
			}
			catch(Exception e)
			{
				errorStream.println(name+":run:processAcknowledge:"+e);
			}
		// wait for the server to finish
			getDoneFromServer();
		// do something with the done command
			try
			{
				processDone();
			}
			catch(Exception e)
			{
				errorStream.println(name+":run:processDone:"+e);
			}
		// close the streams etc.
			close();
		}
		catch(IOException e)
		{
			errorStream.println(name+":run:"+e);
		}
		catch(ClassNotFoundException e)
		{
			errorStream.println(name+":run:"+e);
		}
	}

	/**
	 * Opens up the Object Output stream <a href="#outputStream">outputStream</a> from Socket 
	 * <a href="#socket">socket</a> so that we can send 
	 * objects to the server. If the socket is null or an IOException occurs the outputStream
	 * is null. This method should not be overridden.
	 * @exception IOException If the ObjectOutputStream constructor fails an IOException occurs.
	 * @see #run
	 */
	protected final void getOutputStream() throws IOException
	{
		outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream(),
			inputOutputBufferSize));
		outputStream.flush();
	}

	/**
	 * Opens up the Object Input stream <a href="#inputStream">inputStream</a> from Socket 
	 * <a href="#socket">socket</a> so that we can receive 
	 * objects from the server. If the socket is null or an IOException occurs the inputStream
	 * is null. This method should not be overridden.
	 * @exception IOException If the ObjectInputStream constructor fails an IOException occurs.
	 * @see #run
	 */
	protected final void getInputStream() throws IOException
	{
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream(),
			inputOutputBufferSize));
	};

	/**
	 * This routine tries to send the <a href="#command">command</a> to the server.
	 * If the <a href="#outputStream">outputStream</a> is null or an exception occurs the routine fails.
	 * @exception IOException If writeObject fails an IOException occurs.
	 * @see #command
	 * @see #run
	 */
	protected final void sendCommand() throws IOException
	{
		outputStream.writeObject(command);
		outputStream.flush();
	}

	/**
	 * This routine gets the acknowledge back from the server to tell the client the command has been received.
	 * The socket timeout is set to that if no acknowledge is received a timout occurs.
	 * @exception IOException If readObject fails an IOException occurs.
	 * @exception ClassNotFoundException If readObject cannot find the read object an ClassNotFoundException 
	 * 	occurs.
	 * @see #acknowledge
	 * @see #run
	 * @see TCPConstants#getAckTimeOut
	 */
	protected final void getAcknowledgeFromServer() throws IOException,ClassNotFoundException
	{
		socket.setSoTimeout(TCPConstants.getAckTimeOut());
		acknowledge = (ACK)(inputStream.readObject());
	}

	/**
	 * This routine processes the acknowledge object returned by the server. 
	 * This method should be overridden in subclasses.
	 * @see #acknowledge
	 * @see #run
	 */
	protected void processAcknowledge()
	{
		if(acknowledge == null)
			return;
	}

	/**
	 * This method gets the <a href="#done">done</a> object from the <a href="#inputStream">inputStream</a>
	 * to inform the client the command has been completed. The socket timeout is set to 
	 * the acknowledge's timeToComplete, so the operation will timeout if nothing is received.
	 * @exception IOException If readObject fails an IOException occurs.
	 * @exception ClassNotFoundException If readObject cannot find the read object an ClassNotFoundException 
	 * @see #run
	 */
	protected final void getDoneFromServer() throws IOException,ClassNotFoundException
	{
		if(acknowledge != null)
			socket.setSoTimeout(acknowledge.getTimeToComplete());
		done = (COMMAND_DONE)(inputStream.readObject());
	}

	/**
	 * This routine processes the done object returned by the server. 
	 * This method should be overridden in subclasses.
	 * @see #done
	 * @see #run
	 */
	protected void processDone()
	{
		if(done == null)
			return;
	}

	/**
	 * This routine closes the streams down to complete the operation of the thread.
	 * @exception IOException If close fails an IOException occurs.
	 * @see #run
	 */
	protected final void close() throws IOException
	{
		inputStream.close();
		outputStream.flush();
		outputStream.close();
		inputStream = null;
		outputStream = null;
		socket.close();
	}

	/**
	 * Routine to return the command the thread is trying to send to the server.
	 * @return The command.
	 */
	public COMMAND getCommand()
	{
		return command;
	}

	/**
	 * Routine to return the acknowledge message the server sent in reply to the command, or null
	 * if the server hasn't returned an acknowledge yet.
	 * @return The done message.
	 */
	public ACK getAcknowledge()
	{
		return acknowledge;
	}

	/**
	 * Routine to return the done message the server sent in reply to the command, or null
	 * if the server hasn't returned  a message yet.
	 * @return The done message.
	 */
	public COMMAND_DONE getDone()
	{
		return done;
	}

	/**
	 * Routine to set the error stream to something other than it's default value.
	 * @param errStream The stream to set the error stream to.
	 */
	public void setErrorStream(PrintWriter errStream)
	{
		PrintWriter oldStream = null;

		oldStream = errorStream;
		synchronized(oldStream)
		{
			errorStream = errStream;
		}
	}
};
// $Log: not supported by cvs2svn $
// Revision 1.10  1999/11/19 16:28:40  cjm
// Changed so that processAcknowledge and processDone catch Exceptions inside of them-
// they can never fail and stop the thread.
//
// Revision 1.9  1999/06/10 08:47:11  dev
// errorStream: from PrintStream to PrintWriter (deprecation).
//
// Revision 1.8  1999/06/02 15:16:49  dev
// "Backup"
//
// Revision 1.7  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 1.6  1999/05/17 09:04:29  dev
// Current Version without Socket Timeouts.
//
// Revision 1.5  1999/05/10 15:41:50  dev
// "Backup"
//
