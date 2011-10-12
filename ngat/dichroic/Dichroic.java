// Dichroic.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/Dichroic.java,v 1.1 2011-10-12 10:16:10 cjm Exp $
package ngat.dichroic;

import java.io.*;
import java.lang.*;
import java.net.*;
import ngat.net.*;
import ngat.util.logging.*;

/**
 * This class provides an interface to drive the IO:O Dichroic. This is an Arduino Ethernet + POE board,
 * which provides a telnet-like interface to move the 3 position dichroic (red|blue|mirror), and get
 * status and error information. 
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class Dichroic
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: Dichroic.java,v 1.1 2011-10-12 10:16:10 cjm Exp $");
	/**
	 * Basic log level.
	 * @see ngat.util.logging.Logging#VERBOSITY_INTERMEDIATE
	 */
	public final static int LOG_LEVEL_DICHROIC_BASIC = Logging.VERBOSITY_INTERMEDIATE;
	/**
	 * Constant specifying the RED slide position.
	 */
	public final static int SLIDE_POSITION_RED = 0;
	/**
	 * Constant specifying the BLUE slide position.
	 */
	public final static int SLIDE_POSITION_BLUE = 2;
	/**
	 * Constant specifying the MIRROR slide position.
	 */
	public final static int SLIDE_POSITION_MIRROR = 1;
	/**
	 * Constant specifying an UNKNOWN slide position.
	 */
	public final static int SLIDE_POSITION_UNKNOWN = -1;
	/**
	 * Array of 3 strings represting the slide positions. Should match the constants:
	 * SLIDE_POSITION_RED , SLIDE_POSITION_BLUE , SLIDE_POSITION_MIRROR.
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 */
	public final static String SLIDE_POSITION_STRING_LIST[] = {"red","mirror","blue"};

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
	 * <li>We construct a move command string using SLIDE_POSITION_STRING_LIST.
	 * <li>We send the command string to the open connection using sendLine.
	 * <li>We receive a reply from the connection using readLine.
	 * <li>If the reply was non-null, we parse the reply (which should be a valid integer) to get the
	 *     error code returned by the arduino. If it is non-zero, we throw an exception.
	 * <li>If the reply is null, we failed to get a reply from the arduino, and throw an exception.
	 * <li>Finally, we always close the connection.
	 * </ul>
	 * Note the method is synchronized on the object instance, so two thread cannot access the move
	 * and getPosition simultaneously.
	 * @param position An integer resresenting a position, one of: SLIDE_POSITION_RED, SLIDE_POSITION_BLUE, 
	 *        SLIDE_POSITION_MIRROR.
	 * @exception IOException Thrown if opening/closing/reading from the connection fails.
	 * @exception NullPointerException Thrown if opening/closing/reading from/writing to the connection fails.
	 * @exception DichroicMoveException Thrown if the move returns a non-zero error code from the arduino,
	 *            or nothing is returned. Also thrown if the position is not legal,
	 * @see #connection
	 * @see #isPosition
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 * @see #SLIDE_POSITION_STRING_LIST
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
			commandString = new String("move "+SLIDE_POSITION_STRING_LIST[position]);
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
	 * <li>If the reply was non-null, we parse the reply (which should be red|blue|mirror|unknown) to get the
	 *     position, and return this as an integer. If the reply cannot be parsed, we throw an exception.
	 * <li>If the reply is null, we failed to get a reply from the arduino, and throw an exception.
	 * <li>Finally, we always close the connection.
	 * </ul>
	 * Note the method is synchronized on the object instance, so two thread cannot access the move
	 * and getPosition simultaneously.
	 * @return The dichroic's current position, one of: SLIDE_POSITION_RED , SLIDE_POSITION_BLUE , 
	 *         SLIDE_POSITION_MIRROR, SLIDE_POSITION_UNKNOWN.
	 * @exception IOException Thrown if opening/closing/reading from the connection fails.
	 * @exception NullPointerException Thrown if opening/closing/reading from/writing to the connection fails.
	 * @exception DichroicException Thrown if the get position command does not return a string, 
	 *            or returns a string that is not known.
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 * @see #SLIDE_POSITION_UNKNOWN
	 */
	public synchronized int getPosition() throws IOException, NullPointerException, DichroicException
	{
		String replyString = null;

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
				if(replyString.equals("red"))
					return SLIDE_POSITION_RED;
				else if(replyString.equals("mirror"))
					return SLIDE_POSITION_MIRROR;
				else if(replyString.equals("blue"))
					return SLIDE_POSITION_BLUE;
				else if(replyString.equals("unknown"))
					return SLIDE_POSITION_UNKNOWN;
				else
					throw new DichroicException(this.getClass().getName()+
								    ":getPosition:Failed to parse reply:"+replyString);
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
	 * Translate a string into a valid position integer.
	 * @param s The string to translate, should be one of: 'red','blue','mirror','unknown'.
	 * @return A position integer, one of: SLIDE_POSITION_RED, SLIDE_POSITION_BLUE, SLIDE_POSITION_MIRROR,
	 *         SLIDE_POSITION_UNKNOWN.
	 * @exception DichroicException Thrown if the string is not valid.
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 * @see #SLIDE_POSITION_UNKNOWN
	 */
	public static int positionFromString(String s) throws DichroicException
	{
		if(s.equals("red"))
			return SLIDE_POSITION_RED;
		else if(s.equals("blue"))
			return SLIDE_POSITION_BLUE;
		else if(s.equals("mirror"))
			return SLIDE_POSITION_MIRROR;
		else if(s.equals("unknown"))
			return SLIDE_POSITION_UNKNOWN;
		else
			throw new DichroicException("Dichroic:positionFromString:Failed to parse string:"+s);
	}

	/**
	 * Translate a position into a string description.
	 * @param position A position integer, one of: SLIDE_POSITION_RED, SLIDE_POSITION_BLUE, SLIDE_POSITION_MIRROR,
	 *         SLIDE_POSITION_UNKNOWN.
	 * @return The string description , should be one of: 'red','blue','mirror','unknown'.
	 * @exception DichroicException Thrown if the position integer is not valid.
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 * @see #SLIDE_POSITION_UNKNOWN
	 */
	public static String stringFromPosition(int position) throws DichroicException
	{
		if(position == SLIDE_POSITION_RED)
			return "red";
		else if(position == SLIDE_POSITION_BLUE)
			return "blue";
		else if(position == SLIDE_POSITION_MIRROR)
			return "mirror";
		else if(position == SLIDE_POSITION_UNKNOWN)
			return "unknown";
		else
			throw new DichroicException("Dichroic:stringFromPosition:Illegal position:"+position);
	}

	/**
	 * Return whether the specified position integer is a valid dichroic position or not.
	 * @param position The position integer to test.
	 * @param allowUnknown A boolean, if true allow SLIDE_POSITION_UNKNOWN to be valid, otherwise only
	 *        allow SLIDE_POSITION_RED / SLIDE_POSITION_BLUE / SLIDE_POSITION_MIRROR.
	 * @return True if the specified position integer is a valid dichroic position, false if it is not.
	 * @see #SLIDE_POSITION_RED
	 * @see #SLIDE_POSITION_BLUE
	 * @see #SLIDE_POSITION_MIRROR
	 * @see #SLIDE_POSITION_UNKNOWN
	 */
	protected boolean isPosition(int position,boolean allowUnknown)
	{
		if((position == SLIDE_POSITION_RED)||(position == SLIDE_POSITION_BLUE)||
		   (position == SLIDE_POSITION_MIRROR))
			return true;
		if(allowUnknown && (position == SLIDE_POSITION_UNKNOWN))
			return true;
		return false;
	}
}
//
// $Log: not supported by cvs2svn $
//
