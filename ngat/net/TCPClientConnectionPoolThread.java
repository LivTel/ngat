// TCPClientConnectionPoolThread.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPClientConnectionPoolThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;

import ngat.message.base.*;

/**
 * The TCPClientConnectionPoolThread extends thread. It implements the generic ISS instrument command protocol.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TCPClientConnectionPoolThread extends Thread
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TCPClientConnectionPoolThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $");
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
	 * Whether to finish the thread or not.
	 */
	protected boolean finished = false;

	/**
	 * Constructor of the thread. 
	 * @param address The address of the socket to communicate to.
	 * @param portNumber The port number to communicate to.
	 */
	public TCPClientConnectionPoolThread(InetAddress address,int portNumber)
	{
		super();
		command = null;
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
	 * Routine to set the command the thread is to send. The command is set and then
	 * notify is called to get the run method out of it's wait state and start to process the command.
	 * @see #command
	 * @see #run
	 */
	public synchronized void setCommand(COMMAND c)
	{
		command = c;
		notify();
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
	public synchronized void run()
	{
		while(!finished)
		{
			while(command == null)
			{
				try
				{           
					wait();
				}
				catch (InterruptedException e)
				{
					synchronized(errorStream)
					{
						errorStream.println(name+":wait:"+e);
					}
				}
			}
			if(finished)
				return;
			try
			{
			// test command and rename thread
				name = new String(this.getClass().getName()+
					":command:"+command.getClass().getName());
				setName(name);
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
				processAcknowledge();
			// wait for the server to finish
				getDoneFromServer();
			// do something with the done command
				processDone();
			// close the streams etc.
				close();
			}
			catch(IOException e)
			{
				command = null;
				synchronized(errorStream)
				{
					errorStream.println(name+":run:"+e);
				}
			}
			catch(ClassNotFoundException e)
			{
				command = null;
				synchronized(errorStream)
				{
					errorStream.println(name+":run:"+e);
				}
			}
		}// end while
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
	 * @exception IOException If readObject fails an IOException occurs.
	 * @exception ClassNotFoundException If readObject cannot find the read object an ClassNotFoundException 
	 * 	occurs.
	 * @see #acknowledge
	 * @see #run
	 */
	protected final void getAcknowledgeFromServer() throws IOException,ClassNotFoundException
	{
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
	 * to inform the client the command has been completed. 
	 * @exception IOException If readObject fails an IOException occurs.
	 * @exception ClassNotFoundException If readObject cannot find the read object an ClassNotFoundException 
	 * @see #run
	 */
	protected final void getDoneFromServer() throws IOException,ClassNotFoundException
	{
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
	 * @see #run
	 * @exception IOException If close fails an IOException occurs.
	 */
	protected final void close() throws IOException
	{
		inputStream.close();
		outputStream.flush();
		outputStream.close();
		inputStream = null;
		outputStream = null;
		socket.close();
		command = null;
	}

	/**
	 * Routine to return the command the thread is trying to send to the server.
	 * @return The command.
	 */
	public synchronized COMMAND getCommand()
	{
		return command;
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
// $log$
