// TCPServerConnectionPoolThread.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPServerConnectionPoolThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;
import ngat.message.base.*;

/**
 * The TCPServerConnectionPoolThread extends thread. It implements the generic ISS instrument command protocol, but
 * should be overridden to provide the implementation of a set of commands the ISS or instrument wants to implement.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TCPServerConnectionPoolThread extends Thread
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TCPServerConnectionPoolThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $");
	/**
	 * Default buffer size for input and output buffers.
	 */
	public final static int DEFAULT_BUFFER_SIZE = 2048;
	/**
	 * The socket the thread is communicating over.
	 */
	protected Socket socket = null;
	/**
	 * The name of the thread.
	 */
	protected String name = null;
	/**
	 * The object input stream to get command objects from.
	 */
	protected ObjectInputStream inputStream = null;
	/**
	 * The object output stream to send replies to.
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
	 * The Command message received from the client.
	 */
	protected COMMAND command = null;
	/**
	 * A lock object when accessing the command field from different threads. Synchronize on this
	 * object before accessing the command field from another thread.
	 */
	protected Object commandLock = new Object();
	/**
	 * The done message to return to the client.
	 */
	protected COMMAND_DONE done = null;
	/**
	 * Whether to finish the thread or not.
	 */
	protected boolean finished = false;

	/**
	 * Constructor of the thread.
	 */
	public TCPServerConnectionPoolThread()
	{
		this(null);
	}

	/**
	 * Constructor of the thread. 
	 * @param connectionSocket The socket the thread is to communicate with.
	 */
	public TCPServerConnectionPoolThread(Socket connectionSocket)
	{
		super();
		socket = connectionSocket;
		if(socket != null)
		{
			name = new String(this.getClass().getName()+
				":remote:"+socket.getInetAddress().toString()+":"+socket.getPort()+
				":local:"+socket.getLocalAddress().toString()+":"+socket.getLocalPort());
		}
		else
		{
			name = new String(this.getClass().getName());
		}
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
	 * Routine to set the socket to communicate on. This is a synchronized method so that
	 * it can call notify, to get the threads run method out of it's wait state (waiting for the socket
	 * to be set).
	 * @param s The socket the thread is to communicate on.
	 */
	synchronized public void setSocket(Socket s)
	{
		this.socket = s;
		notify();
	}

	/**
	 * Routine to get the socket the thread is communicating on. This can also be used to determine
	 * whether the thread is currently processing a command, if the thread is null it is not.
	 * @return The current value of the socket field.
	 */
	synchronized public Socket getSocket()
	{
		return socket;
	}

	/**
	 * Routine to set the the finished flag to true, to stop this thread after it has completed
	 * it's current operation. The run method is notifyed so that it can read the flag and terminate.
	 * @see #finished
	 * @see #run
	 */
	synchronized public void setFinished()
	{
		this.finished = true;
		notify();
	}

	/**
	 * The run method, which implements the ISS Generic Instrument Command Protocol. This consists of:
	 * <dl>
	 * <dt><a href="#getCommandFromServer">getCommandFromServer</a></dt>
	 *	<dd>Get a COMMAND object from the client via the socket.</dd>
	 * <dt><a href="#calculateAcknowledgeTime">calculateAcknowledgeTime</a></dt>
	 *	<dd>Calculate how long the command takes to execute.</dd>
	 * <dt><a href="#sendAcknowledge">sendAcknowledge</a></dt> 
	 * 	<dd>Send the acknowledge time back to the client.</dd>
	 * <dt><a href="#processCommand">processCommand</a></dt> 
	 * 	<dd>Do the operation the command requests.</dd>
	 * <dt><a href="#sendDone">sendDone</a></dt> 
	 * 	<dd>Send the message to inform the client we have finished processing that command.</dd>
	 * <dt><a href="#close">close</a></dt> 
	 * 	<dd>Close the clients socket.</dd>
	 * </dl>
	 * The following methods should be overridden in subclasses:
	 * <ul>
	 * <li><a href="#calculateAcknowledgeTime">calculateAcknowledgeTime</a></li>
	 * <li><a href="#processCommand">processCommand</a></li>
	 * </ul>
	 * @see #getCommandFromServer
	 * @see #calculateAcknowledgeTime
	 * @see #sendAcknowledge
	 * @see #processCommand
	 * @see #sendDone
	 * @see #close
	 */
	public synchronized void run()
	{
		int milliseconds;

		while(!finished)
		{
			while(socket == null)
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
			// attempt to get object input/output streams.
				getInputStream();
				getOutputStream();
			// try to get command from client
				getCommandFromServer();
			// calculate time to complete command and send acknowledge.
				milliseconds = calculateAcknowledgeTime();
				sendAcknowledge(milliseconds);
			// do the operation the command requests.
				processCommand();
			// tell the client we have finished and whether we have succeeded or not
				sendDone();
			// close the streams etc.
				close();
			}
			catch(IOException e)
			{
				socket = null;
				synchronized(errorStream)
				{
					errorStream.println(name+":run:"+e);
				}
			}
			catch(ClassNotFoundException e)
			{
				socket = null;
				synchronized(errorStream)
				{
					errorStream.println(name+":run:"+e);
				}
			}
		}// end while
	}

	/**
	 * Opens up the Object Input stream <a href="#inputStream">inputStream</a> from Socket 
	 * <a href="#socket">socket</a> so that we can receive 
	 * objects from the client. If the socket is null or an IOException occurs the inputStream
	 * is null. This method should not be overridden.
	 * @exception IOException If the ObjectInputStream fails to be constructed an IOException results.
	 * @see #run
	 */
	protected final void getInputStream() throws IOException
	{
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream(),
				inputOutputBufferSize));
	};

	/**
	 * Opens up the Object Output stream <a href="#outputStream">outputStream</a> from Socket 
	 * <a href="#socket">socket</a> so that we can send 
	 * objects to the client. If the socket is null or an IOException occurs the outputStream
	 * is null. This method should not be overridden.
	 * @exception IOException If the ObjectOutputStream fails to be constructed an IOException results.
	 * @see #run
	 */
	protected final void getOutputStream() throws IOException
	{
		if(socket == null)
			return;
		outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream(),
				inputOutputBufferSize));
		outputStream.flush();
	}

	/**
	 * This routine tries to get the <a href="#command">command</a> the client has sent by reading an object 
	 * from the socket. 
	 * If the <a href="#inputStream">inputStream</a> is null or an exception occurs the 
	 * <a href="#command">command</a> is null.
	 * @exception IOException If the readObject fails to be constructed an IOException results.
	 * @exception ClassNotFoundException If the readObject reads an unknown class a ClassNotFoundException results.
	 * @see #command
	 * @see #run
	 */
	protected final void getCommandFromServer() throws ClassNotFoundException,IOException
	{
		String commandClassName = null;

		if(inputStream == null)
		{
			command = null;
			return;
		}
		synchronized(commandLock)
		{
			command = (COMMAND)(inputStream.readObject());
		}
		if(command == null)
			commandClassName = new String("null command");
		else
			commandClassName = command.getClass().getName();
	// set name to include command we are processing
		name = new String(this.getClass().getName()+
			":command:"+commandClassName+
			":remote:"+socket.getInetAddress().toString()+":"+socket.getPort()+
			":local:"+socket.getLocalAddress().toString()+":"+socket.getLocalPort());
		setName(name);
	}

	/**
	 * This routine examines the <a href="#command">command</a> the client has sent and estimates
	 * how long it will take to perform operations relevant to this command. It returns the time in
	 * milliseconds. This method should be overridden in subclasses to calculate this time
	 * accurately. This method just returns 0 milliseconds.
	 * @return The time taken to complete the command in milliseconds.
	 * @see #command
	 * @see #run
	 */
	protected int calculateAcknowledgeTime()
	{
		return 0;
	}

	/**
	 * This routine sends an acknowledge back to the client to tell it the command has been received.
	 * @param milliseconds The time taken to complete the command.
	 * @exception IOException If the acknowledge object fails to be sent an IOException results.
	 * @see #run
	 */
	protected final void sendAcknowledge(int milliseconds) throws IOException
	{
		ACK acknowledge = null;

		if(command == null)
			return;
		acknowledge = new ACK(command.getId());
		acknowledge.setTimeToComplete(milliseconds);
		outputStream.writeObject(acknowledge);
		outputStream.flush();
	}

	/**
	 * This routine examines the <a href="#command">command</a> the client has sent and 
	 * executes the  operations relevant to this command. This method should be overridden in subclasses 
	 * to call the routines to perform the execution.
	 * @see #run
	 */
	protected void processCommand()
	{
		if(command == null)
			return;
	// setup return object.
		done = new COMMAND_DONE(command.getId());
		done.setErrorNum(0);
		done.setErrorString("");
		done.setSuccessful(true);
	}

	/**
	 * This method writes the <a href="#done">done</a> object to the <a href="#outputStream">outputStream</a>
	 * to inform the client the command has been completed. If the <a href="#done">done</a> object is null
	 * it is created as a error message.
	 * @exception IOException If the done object fails to be sent an IOException results.
	 * @see #run
	 */
	protected final void sendDone() throws IOException
	{
		if(done == null)
		{
			done = new COMMAND_DONE(command.getId());
			done.setErrorNum(100);
			done.setErrorString(name+":sendDone:done was null");
			done.setSuccessful(false);
		}
		outputStream.writeObject(done);
		outputStream.flush();
	}

	/**
	 * This routine closes the streams down to complete the operation of the thread.
	 * @exception IOException If a stream or socket fails to close an IOException results.
	 * @see #run
	 */
	protected final void close() throws IOException
	{
		inputStream.close();
		outputStream.close();
		outputStream.close();
		inputStream = null;
		outputStream = null;
		socket.close();
		socket = null;
	}

	/**
	 * Routine to get the command being executed by the thread
	 * This will be null if the thread has not raed the command from the input stream.
	 * @see #command
	 */
	public final COMMAND getCommand()
	{
		COMMAND c = null;

		synchronized(commandLock)
		{
			c = command;
		}
		return c;
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
// Revision 0.9  2001/07/03 14:40:55  cjm
// Changed Error code.
//
// Revision 0.8  1999/06/10 08:47:11  dev
// errorStream: from PrintStream to PrintWriter (deprecation).
//
// Revision 0.7  1999/06/09 16:53:32  dev
// initial implementation of a thread abort procedure
// error stream set routine
//
// Revision 0.6  1999/06/02 15:19:04  dev
// "Backup"
//
// Revision 0.5  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 0.4  1999/05/17 09:04:29  dev
// Current Version without Socket Timeouts.
//
// Revision 0.3  1999/05/10 15:41:50  dev
// "Backup"
//
// Revision 0.2  1999/05/07 16:44:42  dev
// Backup
//
// Revision 0.1  1999/05/07 13:10:00  dev
// initial revision
//