// Dichroic.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/Dichroic.java,v 1.3 2012-01-18 15:14:41 cjm Exp $
package ngat.dichroic;

import java.io.*;
import java.lang.*;
import java.net.*;
import ngat.net.*;
import ngat.util.logging.*;

/**
 * This class provides an interface to drive the IO:O Dichroic. This is an Arduino Ethernet + POE board,
 * which provides a telnet-like interface to move the 3 position dichroic (0|1|2|left|middle|right), and get
 * status and error information. 
 * @author Chris Mottram
 * @version $Revision$
 */
public class Dichroic
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Basic log level.
	 * @see ngat.util.logging.Logging#VERBOSITY_INTERMEDIATE
	 */
	public final static int LOG_LEVEL_DICHROIC_BASIC = Logging.VERBOSITY_INTERMEDIATE;
	/**
	 * Constant specifying an UNKNOWN slide position.
	 */
	protected final static int SLIDE_POSITION_UNKNOWN = -1;

// per instance variables
	/**
	 * The socket connection to the Arduino Ethernet + POE.
	 * @see ngat.net.TelnetConnection
	 */
	protected TelnetConnection connection = null;
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

// constructor
	/**
	 * Constructor. Constructs the logger, and (telnet) connection.
	 * @see #logger
	 * @see #connection
	 */
	public Dichroic()
	{
		super();
		logger = LogManager.getLogger(this);
		connection = new TelnetConnection();
	}

	/**
	 * Constructor.
	 * @param a The address of the telnet connection.
	 * @param p The port number of the telnet connection.
	 */
	public Dichroic(InetAddress a,int p)
	{
		super();
		logger = LogManager.getLogger(this);
		connection = new TelnetConnection(a,p);
	}

	/**
	 * Set the IP address of the Arduino.
	 * @param a The IP address, as a InetAddress.
	 * @see #connection
	 * @see ngat.net.TelnetConnection#setAddress
	 * @see #logger
	 * @see #LOG_LEVEL_DICHROIC_BASIC
	 */
	public void setAddress(InetAddress a)
	{
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":setAddress:"+a);
		connection.setAddress(a);
	}

	/**
	 * Set the IP address of the Arduino.
	 * @param addressName The IP address, as a String.
	 * @see #connection
	 * @see ngat.net.TelnetConnection#setAddress
	 * @see #logger
	 * @see #LOG_LEVEL_DICHROIC_BASIC
	 */
	public void setAddress(String addressName) throws UnknownHostException
	{
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":setAddress:"+addressName);
		connection.setAddress(addressName);
	}

	/**
	 * Set the port number of the server on the Arduino.
	 * @param p The port number.
	 * @see #connection
	 * @see ngat.net.TelnetConnection#setPortNumber
	 * @see #logger
	 * @see #LOG_LEVEL_DICHROIC_BASIC
	 */
	public void setPortNumber(int p)
	{
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":setPortNumber:"+p);
		connection.setPortNumber(p);
	}

	/**
	 * Move the dichroic to a specified position.
	 * <ul>
	 * <li>Check the position argument is valid.
	 * <li>We open the telnet connection.
	 * <li>We construct a move command string.
	 * <li>We send the command string to the open connection using sendLine.
	 * <li>We receive a reply from the connection using readLine.
	 * <li>If the reply was non-null, we parse the reply (which should be a valid integer) to get the
	 *     error code returned by the arduino. If it is non-zero, we throw an exception.
	 * <li>If the reply is null, we failed to get a reply from the arduino, and throw an exception.
	 * <li>Finally, we always close the connection.
	 * </ul>
	 * Note the method is synchronized on the object instance, so two thread cannot access the move
	 * and getPosition simultaneously.
	 * @param position An integer resresenting a position, one of : 0|1|2.
	 * @exception IOException Thrown if opening/closing/reading from the connection fails.
	 * @exception NullPointerException Thrown if opening/closing/reading from/writing to the connection fails.
	 * @exception DichroicMoveException Thrown if the move returns a non-zero error code from the arduino,
	 *            or nothing is returned. Also thrown if the position is not legal,
	 * @see #connection
	 * @see #isPosition
	 * @see ngat.net.TelnetConnection#open
	 * @see ngat.net.TelnetConnection#sendLine
	 * @see ngat.net.TelnetConnection#readLine
	 * @see ngat.net.TelnetConnection#close
	 */
	public synchronized void move(int position) throws IOException,NullPointerException,DichroicMoveException
	{
		int errorCode;
		String commandString = null;
		String replyString = null;

		// check parameters
		if(!isPosition(position,false))
		{
			throw new DichroicMoveException(this.getClass().getName()+":move("+position+
							"):Illegal position argument.");
		}
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+") started.");
		// open connection
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
			   "):Opening connection.");
		connection.open();
		try
		{
			// send move command
			commandString = new String("move "+position);
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
				   "):Sending command:"+commandString);
			connection.sendLine(commandString);
			// read reply
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
				   "):Reading reply.");
			replyString = connection.readLine();
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
				   "):move returned:"+replyString);
			// check reply returned
			if(replyString != null)
			{
				// if reply returned parse error code
				logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
					   "):Parsing error code.");
				errorCode = Integer.parseInt(replyString);
				logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
					   "):Error code:"+errorCode);
				if(errorCode != 0)
					throw new DichroicMoveException(errorCode);
			}
			else // end of stream reached / no string returned.
				throw new DichroicMoveException(this.getClass().getName()+":move("+position+
					   "):reply was null.");
		}
		// always close connection even if move failed.
		finally
		{
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+
				   "):Closing connection.");
			connection.close();
		}
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":move("+position+"):Finished.");
	}

	/**
	 * Method to get the current position of the dichroic.
	 * <ul>
	 * <li>We open the telnet connection.
	 * <li>We send the get position command string to the open connection using sendLine.
	 * <li>We receive a reply from the connection using readLine.
	 * <li>If the reply was non-null, we parse the reply (which should be 0|1|2|-1) to get the
	 *     position, and return this as an integer. If the reply cannot be parsed, we throw an exception.
	 * <li>If the reply is null, we failed to get a reply from the arduino, and throw an exception.
	 * <li>Finally, we always close the connection.
	 * </ul>
	 * Note the method is synchronized on the object instance, so two thread cannot access the move
	 * and getPosition simultaneously.
	 * @return The dichroic's current position, one of: 0|1|2|-1.
	 * @exception IOException Thrown if opening/closing/reading from the connection fails.
	 * @exception NullPointerException Thrown if opening/closing/reading from/writing to the connection fails.
	 * @exception DichroicException Thrown if the get position command does not return a string, 
	 *            or returns a string that is not known.
	 * @exception NumberFormatException Thrown if parsing the reply as an integer fails.
	 */
	public synchronized int getPosition() throws IOException, NullPointerException, DichroicException,
						     NumberFormatException
	{
		String replyString = null;
		int position;

		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Started.");
		// open conenction
		logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Opening connection.");
		connection.open();
		try
		{
			// send command
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Sending command.");
			connection.sendLine("get position");
			// get reply
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Reading reply.");
			replyString = connection.readLine();
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Read reply:"+
				   replyString);
			if(replyString != null)
			{
				logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+
					   ":getPosition:Parsing reply:"+replyString);
				// if reply returned parse string
				position = Integer.parseInt(replyString);
				if(!isPosition(position,true))
				{
					throw new DichroicException(this.getClass().getName()+
								    ":getPosition:Failed to parse reply:"+replyString);
				}
				return position;
			}
			else // end of stream reached / no string returned.
				throw new DichroicException(this.getClass().getName()+
							    ":getPosition:reply was null.");
		}
		// always close connection even if move failed.
		finally
		{
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+
				   ":getPosition:Closing connection.");
			connection.close();
			logger.log(LOG_LEVEL_DICHROIC_BASIC,this.getClass().getName()+":getPosition:Finished.");
		}
	}

	/**
	 * Return whether the specified position integer is a valid dichroic position or not.
	 * @param position The position integer to test.
	 * @param allowUnknown A boolean, if true allow -1 to be valid, otherwise only allow 0|1|2.
	 * @return True if the specified position integer is a valid dichroic position, false if it is not.
	 */
	protected boolean isPosition(int position,boolean allowUnknown)
	{
		if((position == 0)||(position == 1)||(position == 2))
			return true;
		if(allowUnknown && (position == -1))
			return true;
		return false;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.2  2012/01/17 14:56:01  cjm
// SLIDE_POSITION_ constants now protected.
// Eventually to be deprecated, as the contents of the slide move around.
//
// Revision 1.1  2011/10/12 10:16:10  cjm
// Initial revision
//
//
