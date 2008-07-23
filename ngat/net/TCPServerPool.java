// TCPServerPool.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPServerPool.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class implements a TCP server. This server extends the Thread class. When run it creates a SocketServer, that
 * sits on a port waiting connections. It also creates a series of worker threads, to handle requests as they arrive.
 * When a connection is accpeted, the 
 * <a href="#startConnectionThread">startConnectionThread</a> is called to notify a worker thread to deal with the 
 * connection.
 * This class also has a redirectable error stream.
 * The <a href="#startConnectionThread">startConnectionThread</a> notifys a created
 * <a href="ngat.net.TCPServerConnectionPoolThread.html">TCPServerConnectionPoolThread</a> by default to deal with the 
 * connection.
 * This class should be subclassed  and the <a href="#startConnectionThread">startConnectionThread</a> overwritten to
 * notify the thread of your choice.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TCPServerPool extends Thread
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: TCPServerPool.java,v 1.1 2008-07-23 12:41:17 eng Exp $");
	/**
	 * Default size of Thread pool for server.
	 */
	public final static int DEFAULTSTARTTHREADCOUNT = 3;
	/**
	 * The name of the class.
	 */
	protected String name = null;
	/**
 	 * The Socket Server the thread will accept connections with.
	 */
	protected ServerSocket serverSocket = null;
	/**
	 * The port number the socket server will listen on.
	 */
	protected int portNumber = 0;
	/**
	 * The stream to report errors on (The System err stream by default).
	 */
	 protected PrintWriter errorStream = new PrintWriter(System.err,true);
	/**
	 * The nunber of worker threads to start initially.
	 */
	private int startThreadCount = DEFAULTSTARTTHREADCOUNT;
	/**
	 * The list of worker threads to start.
	 */
	private Vector threadList = null;

	/**
	 * Constructor of the thread. 
	 * @param name A name to give the thread.
	 * @param portNumber The port to open the SocketServer on.
	 */
	public TCPServerPool(String name,int portNumber)
	{
		super();
		if(name != null)
		{
			this.name = new String(name);
			setName(this.getClass().getName()+":"+this.name+" on port "+portNumber);
		}
		else
		{
			this.name = new String("Unnamed");
			setName(this.getClass().getName()+":"+this.name+" on port "+portNumber);
		}
		this.portNumber = portNumber;
	}

	/**
	 * Constructor of the thread. 
	 * @param name A name to give the thread.
	 * @param portNumber The port to open the SocketServer on.
	 * @param threadCount The number of worker threads to start initially.
	 */
	public TCPServerPool(String name,int portNumber,int threadCount)
	{
		this(name,portNumber);
		startThreadCount = threadCount;
	}

	/**
	 * The main run method of the thread. Creates a Socket Server to accept new connections from,
	 * and starts some worker threads to deal with connections.
	 * It then enters an infinite loop,
	 * accepting new connections and calling <a href="#startConnectionThread">startConnectionThread</a> to
	 * deal with them.
	 * @see #createConnectionThread
	 * @see #startConnectionThread
	 */
	public final void run()
	{
		TCPServerConnectionPoolThread thread = null;
		int i;
		boolean done = false;

		try
		{
			serverSocket = new ServerSocket(portNumber);
		}
		catch (IOException e)
		{
			synchronized(errorStream)
			{
				errorStream.println(this.getClass().getName()+":"+name+":Could not listen on port: "+
					portNumber+", "+e);
			}
			serverSocket = null;
		}
		if(serverSocket == null)
			return;
		// start some threads to deal with requests
		threadList = new Vector();
		for(i=0;i<startThreadCount;i++)
		{
			thread = createConnectionThread();
			threadList.addElement(thread);
		}
		// go into infinite loop accepting connections from the socket and setting a worker thread onto them.
		while(true)
		{
			Socket connectionSocket = null;
			try
			{
				connectionSocket = serverSocket.accept();
			}
			catch (IOException e)
			{
				synchronized(errorStream)
				{
					errorStream.println(this.getClass().getName()+":"+name+" Accept failed: "+
						portNumber+", "+e);
				}
				connectionSocket = null;
			}
			if(connectionSocket != null)
			{
				i = 0;
				done = false;
				while((!done)&&(i < threadList.size()))
				{
					thread = (TCPServerConnectionPoolThread)(threadList.elementAt(i));
					if(thread.getSocket() == null)
						done = true;
					i++;
				}
				if(!done)
				{
					thread = createConnectionThread();
					threadList.addElement(thread);
				}
				startConnectionThread(thread,connectionSocket);
			}
		}
	}

	/**
	 * This routine is called from the <a href="#run">run</a> method, to start a new connection thread. 
	 * This method should be overridden to start a thread Class of your choice.
	 * The default is to start a 
	 * <a href="ngat.net.TCPServerConnectionPoolThread.html">TCPServerConnectionPoolThread</a> 
	 * thread. The thread should start and go into a wait state until it's setSocket method is called.
	 * @param connectionSocket The Socket the connection is connected to, that the connection thread needs to
	 * to talk over.
	 * @see TCPServerConnectionPoolThread
	 */
	public TCPServerConnectionPoolThread createConnectionThread()
	{
		TCPServerConnectionPoolThread thread = null;

		thread = new TCPServerConnectionPoolThread();
		thread.start();
		return thread;
	}

	/**
	 * This routine is called from the <a href="#run">run</a> method, when a thread is needed
	 * to handle a new connection. This method should be overridden to start a thread Class of your choice.
	 * The threads setSocket routine is called to sets the threads socket to communicate over,
	 * this should allow the thread to begin execution of it's run method.
	 * @param connectionSocket The Socket the connection is connected to, that the connection thread needs to
	 * to talk over.
	 * @see TCPServerConnectionPoolThread
	 */
	public void startConnectionThread(TCPServerConnectionPoolThread thread,Socket connectionSocket)
	{
		thread.setSocket(connectionSocket);
	}

	/**
	 * Routine to close the server socket. Should be called prior to the stop method (inherited from Thread).
	 */
	public void close()
	{
		TCPServerConnectionPoolThread thread = null;
		int i;

		if(serverSocket != null)
		{
			try
			{
				serverSocket.close();
			}
			catch(IOException e)
			{
					synchronized(errorStream)
				{
					errorStream.println(this.getClass().getName()+":"+name+
						"Could not close server socket,"+e);
				}
			}
			serverSocket = null;
		}
		if(threadList != null)
		{
			for(i=0;i<threadList.size();i++)
			{
				thread = (TCPServerConnectionPoolThread)(threadList.elementAt(0));
				thread.setFinished();
				threadList.removeElementAt(0);
			}
		}
	};

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
// Revision 0.6  1999/06/24 12:45:59  dev
// "Backup"
//
// Revision 0.5  1999/06/10 08:47:11  dev
// errorStream: from PrintStream to PrintWriter (deprecation).
//
// Revision 0.4  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 0.3  1999/05/17 09:04:29  dev
// Current Version without Socket Timeouts.
//
// Revision 0.2  1999/05/10 09:50:23  dev
// Backup
//
// Revision 0.1  1999/05/07 13:08:57  dev
// initial revision
//



