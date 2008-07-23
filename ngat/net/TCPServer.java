// $Header: /space/home/eng/cjm/cvs/ngat/net/TCPServer.java,v 1.1 2008-07-23 12:41:17 eng Exp $
package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;

import ngat.util.logging.*;

/**
 * This class implements a TCP server. This server extends the Thread class. 
 * When run it creates a SocketServer, that sits on a port waiting connections.
 * When a connection is accpeted, the <a href="#startConnectionThread">
 * startConnectionThread</a> is called to start a new thread to deal with the 
 * connection.  This class also has a redirectable error stream.  The 
 * <a href="#startConnectionThread">startConnectionThread</a> method spawns a
 * <a href="ngat.net.TCPServerConnectionThread.html">TCPServerConnectionThread
 * </a> by default to deal with the connection.
 *
 * This class should be subclassed  and the <a href="#startConnectionThread">
 * startConnectionThread</a> overwritten to spawn the thread of your choice.
 * @author Chris Mottram Jason Etherton
 * @version $Revision: 1.1 $
 */
public class TCPServer extends Thread
{
  /**
   * Revision Control System id string, showing the version of the Class
   */
  public final static String RCSID = new String("$Id: TCPServer.java,v 1.1 2008-07-23 12:41:17 eng Exp $");

  /**
   * Constant defining the log level to send for error messages generated
   * by instances of this class.
   */
  public final static int LOG_LEVEL_ERROR = 1;

  /**
   * The name of the class.
   */
  protected String name = null;

  /**
   * The Socket Server the thread will accept connections with.
   */
  protected ServerSocket serverSocket = null;

  /**
   * Length of server socket Timeout in milliseconds.
   */
  protected int socketTimeout = 0;

  /**
   * The port number the socket server will listen on.
   */
  protected int portNumber = 0;
    
  /**
   * The stream to report errors on.
   */
    
  protected PrintWriter errorStream = null;
  /**
   * The logger to report errors to.
   */
    
  protected Logger logger = null;
  /**
   * Whether to stop trying to receive new connections.
   */
    
  protected boolean quit = false;
  /**
   * Whether an error has occured during the operation of this thread.
   */
    
  protected boolean error = false;
    
  /**
   * Constructor of the thread. Sets the thread name and the port for the 
   * server to run on.
   * Initialises quit and error booleans. Creates the logger.
   * @param name A name to give the thread.
   * @param portNumber The port to open the SocketServer on.
   * @see #name
   * @see #portNumber
   * @see #logger
   * @see #quit
   * @see #error
   */
  public TCPServer(String name,int portNumber)
  {
    super();
    if(name != null)
    {
      this.name = new String(name);
      setName(this.getClass().getName()+":"+this.name+" on port "+
	      portNumber);
    }
    else
    {
      this.name = new String("Unnamed");
      setName(this.getClass().getName()+":"+this.name+" on port "+
	      portNumber);
    }
    this.portNumber = portNumber;
    logger = LogManager.getLogger(this);
    quit = false;
    error = false;
  }

  /**
   * Constructor of the thread. Sets the thread name and the port for the 
   * server to run on.
   * Initialises quit and error booleans. Creates the logger.
   * @param name A name to give the thread.
   * @param portNumber The port to open the SocketServer on.
   * @param soTimeout server socket timeout in milliseconds.
   * @see #name
   * @see #portNumber
   * @see #logger
   * @see #quit
   * @see #error
   */
  public TCPServer(String name, int portNumber, int soTimeout)
  {
    super();
    if(name != null)
    {
      this.name = new String(name);
      setName(this.getClass().getName()+":"+this.name+" on port "+
	      portNumber);
    }
    else
    {
      this.name = new String("Unnamed");
      setName(this.getClass().getName()+":"+this.name+" on port "+
	      portNumber);
    }
    this.portNumber = portNumber;
    socketTimeout = soTimeout;
    logger = LogManager.getLogger(this);
    quit = false;
    error = false;
  }

  /**
   * The main run method of the thread. Creates a Socket Server and then 
   * enters an infinite loop, accepting new connections and calling 
   * <a href="#startConnectionThread">startConnectionThread</a> to deal with 
   * them. Calls processError if an error occurs and we are not quitting.
   * We would expect an IOException to be thrown when we close the server 
   * socket whilst accepting a connection (as we do during quitting).
   * @see #startConnectionThread
   * @see #processError
   */
  public final void run()
  {
    try
    {
      serverSocket = new ServerSocket(portNumber);
    }
    catch (IOException e)
    {
      processError(this.getClass().getName()+":"+name+
		   ":Could not listen on port: "+
		   portNumber+", ",e);
      serverSocket = null;
    }
    if(serverSocket == null) return;

    // if socketTimeout was not specified in the constructor
    // timeout is infinite.
    try
    {
      serverSocket.setSoTimeout( socketTimeout );
    }
    catch( SocketException se )
    {
      processError(this.getClass().getName()+":"+name+
		   " setSoTimeout failed: "+portNumber+", ",se);
    }
    while(quit == false)
    {
      Socket connectionSocket = null;
      try
      {
	connectionSocket = serverSocket.accept();
      }
      catch(InterruptedIOException iie) //catch timeout exception
      {
	if( quit == true ) return;
      }
      catch(IOException e)
      {
	// if quit is true we expect serverSocket.accept 
	// to throw an exception
	if(quit == false)
	{
	  processError(this.getClass().getName()+":"+
		       name+" Accept failed: "+
		       portNumber+", ",e);
	}
	connectionSocket = null;
      }
      if(connectionSocket != null)
	startConnectionThread(connectionSocket);
    }
  }

  /**
   * This routine is called from the <a href="#run">run</a> method, when a 
   * thread is needed to handle a new connection. This method should be 
   * overridden to start a thread Class of your choice.
   * The default is to start a 
   * <a href="ngat.net.TCPServerConnectionThread.html">
   * TCPServerConnectionThread</a> thread.
   * @param connectionSocket The Socket the connection is connected to, 
   * that the connection thread needs to to talk over.
   * @see TCPServerConnectionThread
   */
  public void startConnectionThread(Socket connectionSocket)
  {
    new TCPServerConnectionThread(connectionSocket).start();
  }

  /**
   * Routine to close the server socket. Should be called prior to the stop 
   * method (inherited from Thread).  Sets the quit field to true, to 
   * indicate we have tried to stop this thread.
   * Calls processError if an error occurs.
   * @see #processError
   * @see #quit
   */
  public void close()
  {
    if(serverSocket == null)
      return;
    quit = true;
    try
    {
      serverSocket.close();
    }
    catch(IOException e)
    {
      processError(this.getClass().getName()+":"+name+
		   "Could not close server socket,",e);
    }
    serverSocket = null;
  };

  /**
   * Routine to set the error stream to something other than it's default 
   * value.
   * @param errStream The stream to set the error stream to.
   */
  public void setErrorStream(PrintWriter errStream)
  {
    errorStream = errStream;
  }

  /**
   * Method for processing an error message generated during the running of 
   * this thread.  This writes the errorString to the errorStream, if the 
   * errorStream is non-null.
   * If the logger has been created, it logs the error with level 
   * LOG_LEVEL_ERROR.
   * This method can be over-written by sub-classes to send the errorString 
   * to an alternative location.
   * This method sets the error field to true, to indicate an error has 
   * occured.
   * @param errorString The error string generated.
   * @see #LOG_LEVEL_ERROR
   * @see #errorStream
   * @see #error
   * @see #logger
   */
  protected void processError(String errorString)
  {
    error = true;
    if(errorStream != null)
    {
      errorStream.println(errorString);
    }
    if(logger != null)
      logger.log(LOG_LEVEL_ERROR,errorString);
  }

  /**
   * Method for processing an error message generated during the running of 
   * this thread.  This writes the errorString to the errorStream, if the 
   * errorStream is non-null.  If the logger has been created, it logs the 
   * error with level LOG_LEVEL_ERROR.
   * The exception parameter has it's stack trace logged as well.
   * This method can be over-written by sub-classes to send the errorString 
   * to an alternative location.
   * This method sets the error field to true, to indicate an error has 
   * occured.
   * @param errorString The error string generated.
   * @param exception The exception that was thrown to cause this error.
   * @see #LOG_LEVEL_ERROR
   * @see #errorStream
   * @see #error
   * @see #logger
   */
  protected void processError(String errorString,Exception exception)
  {
    error = true;
    if(errorStream != null)
    {
      errorStream.println(errorString);
      exception.printStackTrace(errorStream);
    }
    if(logger != null)
    {
      logger.log(LOG_LEVEL_ERROR,errorString,exception);
      logger.dumpStack(LOG_LEVEL_ERROR,exception);
    }
  }

  /**
   * Method to get whether the TCPServer thread was quit (using the close 
   * method).
   * @return A boolean, true if the close method was called, false if it 
   * was not.
   * @see #close
   * @see #quit
   */
  public final boolean getQuit()
  {
    return quit;
  }
    
  /**
   * Method to get whether the TCPServer thread had an error occur during 
   * it's execution.
   * @return A boolean, true if an error occured, false if no error occured.
   * @see #processError
   * @see #error
   */
  public final boolean getError()
  {
    return error;
  }

};

// $Log: not supported by cvs2svn $
// Revision 1.16  2001/11/09 14:08:24  je
// Changed timeout to deal with close method (quit = true).
//
// Revision 1.15  2001/11/01 14:55:55  je
// Corrections from previous 1.14
//
// Revision 1.14  2001/09/24 13:41:02  je
// Over-ridden the constructor to allow for timeouts on the server sockets.
//
// Revision 1.13  2001/07/11 10:49:53  cjm
// Error handling now uses a logger.
//
// Revision 1.12  2001/03/27 10:48:20  cjm
// Added error logger.
//
// Revision 1.11  2000/08/01 13:23:32  cjm
// Added error detection mechanism, quit and error retrieve function, more documentation.
//
// Revision 1.10  1999/12/03 11:45:00  cjm
// Added processError method to improve error handling.
//
// Revision 1.9  1999/11/29 10:28:02  cjm
// Fixed printing out of error messages.
//
// Revision 1.8  1999/11/29 10:14:59  cjm
// Fixed quit implementation.
//
// Revision 1.7  1999/11/29 10:00:48  cjm
// Added quit field so that close() method causes termination of the while loop.
//
// Revision 1.6  1999/06/10 08:47:11  dev
// errorStream: from PrintStream to PrintWriter (deprecation).
//
// Revision 1.5  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 1.4  1999/05/17 09:04:29  dev
// Current Version without Socket Timeouts.
//
// Revision 1.3  1999/04/27 11:26:22  dev
// Backup
//
// Revision 1.2  1999/03/25 14:00:34  dev
// Backup
//
// Revision 1.1  1999/03/16 17:05:28  dev
// Backup
//



