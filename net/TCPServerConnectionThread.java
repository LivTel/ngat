package ngat.net;

import java.lang.*;
import java.io.*;
import java.net.*;
import ngat.message.base.*;
import ngat.util.logging.*;

/**
 * The TCPServerConnectionThread extends thread. It implements the generic ISS
 * instrument command protocol, but should be overridden to provide the
 * implementation of a set of commands the ISS or instrument wants to
 * implement.
 *
 * @author $Author$
 * @version $Revision$
 */
public class TCPServerConnectionThread extends Thread
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
   * Constant defining the log level to send for error messages generated by instances of this class.
   */
  public final static int LOG_LEVEL_ERROR = 1;
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
   * The stream to report errors on.
   */
  protected PrintWriter errorStream = null;
  /**
   * The logger to report errors to.
   */
  protected Logger logger = null;
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
   * Constructor of the thread. 
   * @param connectionSocket The socket the thread is to communicate with.
   * @see #socket
   * @see #name
   * @see #logger
   */
  public TCPServerConnectionThread(Socket connectionSocket)
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
    logger = LogManager.getLogger(this);
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
   * Routine to set this threads error stream to a stream passed in.
   * @param stream The stream error messages will be sent to. 
   */
  public void setErrorStream(PrintWriter stream)
  {
    errorStream = stream;
  }

  /**
   * The run method, which implements the ISS Generic Instrument Command Protocol. This consists of:
   * <dl>
   * <dt><a href="#getCommandFromClient">getCommandFromClient</a></dt>
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
   * @see #getCommandFromClient
   * @see #calculateAcknowledgeTime
   * @see #sendAcknowledge
   * @see #processCommand
   * @see #sendDone
   * @see #close
   */
  public void run()
  {
    ACK acknowledge = null;

    // if so socket stop
    if(socket == null)
      return;
    try
    {
      // attempt to get object input/output streams.
      getInputStream();
      getOutputStream();
      // try to get command from client
      getCommandFromClient();
      // initialise response to command
      try
      {
	init();
      }
      catch(Exception e)
      {
	processError(name+":run:init:command:"+command+":done:"+done,e);
      }
      // calculate time to complete command and send acknowledge.
      try
      {
	acknowledge = calculateAcknowledgeTime();
      }
      catch(Exception e)
      {
	processError(name+":run:calculateAcknowledgeTime:"+
		     "command:"+command+":done:"+done,e);
      }
      sendAcknowledge(acknowledge);
      // do the operation the command requests.
      try
      {
	processCommand();
      }
      catch(Exception e)
      {
	processError(name+":run:processCommand:command:"+command+":done:"+done,e);
      }
      // tell the client we have finished and whether we have succeeded or not
      sendDone();
      // close the streams etc.
      close();
    }
    catch(IOException e)
    {
      socket = null;
      processError(name+":run:command:"+command+":done:"+done,e);
    }
    catch(ClassNotFoundException e)
    {
      socket = null;
      processError(name+":run:command:"+command+":done:"+done,e);
    }
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
  protected void getCommandFromClient() throws ClassNotFoundException,IOException
  {
    String commandClassName = null;

    if(inputStream == null)
    {
      command = null;
      return;
    }
    socket.setSoTimeout(TCPConstants.getCommandTimeOut());
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
   * This routine examines the <a href="#command">command</a> the client has sent. It is called as soon
   * as the command object is read from the client. This allows the server to perform any initialisation
   * before the acknowledge time is calculated.
   */
  protected void init()
  {
  }

  /**
   * This routine examines the <a href="#command">command</a> the client has sent and estimates
   * how long it will take to perform operations relevant to this command. It returns an 
   * instance of ngat.message.base.ACK containing the time in
   * milliseconds to complete this command. . This method should be overridden in subclasses to 
   * calculate this time accurately. This method just returns an ACK with 1000 milliseconds.
   * @return A instance of (a subclass of) ngat.message.base.ACK, containing the time taken to 
   * complete the command in milliseconds.
   * @see #command
   * @see #run
   * @see ngat.message.base.ACK
   */
  protected ACK calculateAcknowledgeTime()
  {
    ACK acknowledge = null;

    acknowledge = new ACK(command.getId());
    acknowledge.setTimeToComplete(1000);
    return acknowledge;
  }

  /**
   * This routine sends an acknowledge back to the client to tell it the command has been received.
   * @param acknowledge The acknowledge object to send back to the client.
   * @exception NullPointerException If the acknowledge object is null this exception is thrown.
   * @exception IOException If the acknowledge object fails to be sent an IOException results.
   * @see #run
   */
  public final void sendAcknowledge(ACK acknowledge) throws IOException
  {
    if(acknowledge == null)
    {
      throw new NullPointerException(this.getClass().getName()+
				     "sendAcknowledge:acknowledge was NULL.");
    }
    outputStream.writeObject(acknowledge);
    outputStream.flush();
  }

  /**
   * This method writes the <a href="#done">done</a> object to the <a href="#outputStream">outputStream</a>
   * to inform the client the command has been completed. If the <a href="#done">done</a> object is null
   * it is created as a error message.
   * @exception IOException If the done object fails to be sent an IOException results.
   * @see #run
   */
  public final void sendDone() throws IOException
  {
    if(done == null)
    {
      done = new COMMAND_DONE(command.getId());
      done.setErrorNum(1);
      done.setErrorString(name+":sendDone:done was null");
      done.setSuccessful(false);
    }
    outputStream.writeObject(done);
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
   * Default method for processing an error message generated during the running of this thread.
   * This writes the errorString to the errorStream. If the logger has been created,
   * it logs the error with level LOG_LEVEL_ERROR.
   * This method can be over-written by sub-classes to send the errorString to an alternative 
   * location.
   * @param errorString The error string generated.
   * @see #LOG_LEVEL_ERROR
   * @see #errorStream
   * @see #logger
   */
  protected void processError(String errorString)
  {
    if(errorStream != null)
    {
      errorStream.println(errorString);
    }
    if(logger != null)
      logger.log(LOG_LEVEL_ERROR,errorString);
  }

  /**
   * Method for processing an error message generated during the running of this thread.
   * This writes the errorString to the errorStream, if the errorStream is non-null.
   * If the logger has been created, it logs the error with level LOG_LEVEL_ERROR.
   * The exception parameter has it's stack trace logged as well.
   * This method can be over-written by sub-classes to send the errorString to an alternative 
   * location.
   * This method sets the error field to true, to indicate an error has occured.
   * @param errorString The error string generated.
   * @param exception The exception that was thrown to cause this error.
   * @see #LOG_LEVEL_ERROR
   * @see #errorStream
   * @see #logger
   */
  protected void processError(String errorString,Exception exception)
  {
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
   * This routine closes the streams down to complete the operation of the thread.
   * @exception IOException If a stream or socket fails to close an IOException results.
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
    socket = null;
  }

  /**
   * Routine to get the command being executed by the thread
   * This will be null if the thread has not read the command from the input stream.
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
};
// $Log: not supported by cvs2svn $
// Revision 1.18  2001/11/13 17:09:20  je
// removed `final' qualifier from getCommandFromClient()
//
// Revision 1.17  2001/07/11 10:49:53  cjm
// Error handling now uses a logger.
//
// Revision 1.16  2001/03/27 10:48:16  cjm
// Added error logger.
//
// Revision 1.15  1999/12/03 11:45:06  cjm
// Added processError method to improve error handling.
//
// Revision 1.14  1999/11/19 16:22:41  cjm
// Catching all exceptions around init,calcualateAcknowledgeTime and processCommand.
//
// Revision 1.13  1999/11/01 14:40:02  cjm
// Changed calculateAcknowledgeTime so that it returns an ACK message rather than an int.
// Changed sendAcknowledge so that acceps an ACK message rather than an int.
// These changes made so that some responses can use sub-classes of ACK to return extra information.
//
// Revision 1.12  1999/10/25 09:47:13  cjm
// sendAcknowledge method sends ACK even if command is null, to ensure client receives an ACK before a DONE.
// Added init method to initialise a threads response to a command.
//
// Revision 1.11  1999/06/10 08:47:11  dev
// errorStream: from PrintStream to PrintWriter (deprecation).
//
// Revision 1.10  1999/06/09 16:53:32  dev
// initial implementation of a thread abort procedure
// error stream set routine
//
// Revision 1.9  1999/06/02 15:19:04  dev
// "Backup"
//
// Revision 1.8  1999/05/20 16:38:34  dev
// "Backup"
//
// Revision 1.7  1999/05/17 09:04:29  dev
// Current Version without Socket Timeouts.
//
// Revision 1.6  1999/05/10 15:41:50  dev
// "Backup"
//
