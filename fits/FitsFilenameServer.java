/*   
    Copyright 2006, Astrophysics Research Institute, Liverpool John Moores University.

    This file is part of NGAT.

    NGAT is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    NGAT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NGAT; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
// FitsFilenameServer.java
// $Header$
package ngat.fits;

import java.io.*;
import java.lang.*;
import java.net.*;
import java.text.*;
import java.util.*;

import org.json.*;

import ngat.flask.EndPoint;
import ngat.util.logging.*;

/**
 * This class allows a Java program to make client-side calls to the filename-server
 * (https://gitlab.services.newrobotictelescope.org/nrt-crew/ljmu/filename-server).
 * This is a HTTP API end-point (developed in NODEjs) that supports the LT FITS filename standard,
 * and acts as a web service to generate LT style FITS filenames.
 * We use the ngat.flask library to provide the end-point interaction code.
 * @author Chris Mottram
 * @version $Revision$
 */
public class FitsFilenameServer implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Multrun flag - signifying the filename to be produced should be the first one in a new MULTRUN.
	 */
	public final static String MULTRUN_FLAG_START 	= "start";
	/**
	 * Multrun flag - signifying the filename to be produced should be the next one in an already started MULTRUN.
	 */
	public final static String MULTRUN_FLAG_NEXT 	= "next";
	/**
	 * Flask end-point instance.
	 */
	protected EndPoint endPoint = null;
	/**
	 * Instrument code describing the instrument the exposure was taken with.
	 * @see FitsFilename#INSTRUMENT_CODE_FRODOSPEC_BLUE
	 * @see FitsFilename#INSTRUMENT_CODE_CCD_CAMERA
	 * @see FitsFilename#INSTRUMENT_CODE_O
	 * @see FitsFilename#INSTRUMENT_CODE_LIRIC
	 * @see FitsFilename#INSTRUMENT_CODE_LOCI
	 * @see FitsFilename#INSTRUMENT_CODE_RISE
	 * @see FitsFilename#INSTRUMENT_CODE_FRODOSPEC_RED
	 * @see FitsFilename#INSTRUMENT_CODE_SPRAT
	 * @see FitsFilename#INSTRUMENT_CODE_MOPTOP_3
	 * @see FitsFilename#INSTRUMENT_CODE_MOPTOP_4
	 */
	protected char instrumentCode;
	/**
	 * Exposure code saying what type of exposure was taken.
	 * @see FitsFilename#EXPOSURE_CODE_ARC
	 * @see FitsFilename#EXPOSURE_CODE_BIAS
	 * @see FitsFilename#EXPOSURE_CODE_DARK
	 * @see FitsFilename#EXPOSURE_CODE_EXPOSURE
	 * @see FitsFilename#EXPOSURE_CODE_SKY_FLAT
	 * @see FitsFilename#EXPOSURE_CODE_ACQUIRE
	 * @see FitsFilename#EXPOSURE_CODE_STANDARD
	 * @see FitsFilename#EXPOSURE_CODE_LAMP_FLAT
	 */
	protected char exposureCode;
	/**
	 * Flag passed to filename serber to determine whether it is to return a 
	 * filename representing the start os a new multrun, or a filename representing the next exposure in an
	 * already started multrun.
	 * @see #MULTRUN_FLAG_START
	 * @see #MULTRUN_FLAG_NEXT
	 */
	protected String multrunFlag = MULTRUN_FLAG_START;
	/**
	 * The extension to concatenate onto the end of the generated filename.
	 * Currently defaults to 'fits'.
	 */
	protected String fileExtension = new String("fits");
	/**
	 * The logger to report errors to.
	 */
	protected Logger logger = null;

	/**
	 * Default constructor. Construct the logger. Construct the Flask end-point. 
	 * We set the end-point to do post requests, and the end-point name to 'filename'.
	 * @see #logger
	 * @see #endPoint
	 */
	public FitsFilenameServer()
	{
		super();
		logger = LogManager.getLogger(this);
		endPoint = new EndPoint();
		endPoint.setDoPost();
		endPoint.setFlaskEndPointName("filename");
	}

	/**
	 * Constructor. Construct the logger. Construct the Flask end-point.
	 * We set the end-point to do post requests, and the end-point name to 'filename'.
	 * @param address A string representing the address of the end-point, i.e. "loci1",
	 *     "localhost".
	 * @param portNumber An integer representing the port number the end-point is listening on.
	 * @see #logger
	 * @see #endPoint
	 */
	public FitsFilenameServer(String address,int portNumber)
	{
		super();
		logger = LogManager.getLogger(this);
		endPoint = new EndPoint();
		endPoint.setDoPost();
		endPoint.setFlaskEndPointName("filename");
		endPoint.setIPAddress(address);
		endPoint.setPortNumber(portNumber);
	}

	/**
	 * Set the address.
	 * @param address A string representing the address of the end-point, i.e. "loci", "localhost".
	 * @exception UnknownHostException Thrown if the address in unknown.
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#setIPAddress
	 */
	public void setAddress(String address) throws UnknownHostException
	{
		endPoint.setIPAddress(address);
	}

	/**
	 * Set the port number.
	 * @param portNumber An integer representing the port number the end-point is listening on.
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#setPortNumber
	 */
	public void setPortNumber(int portNumber)
	{
		endPoint.setPortNumber(portNumber);
	}

	/**
	 * Set routine for the instrument code. The end-point's "instrument" parameter is set accordingly.
	 * @param code The code to set to.
	 * @see #instrumentCode
	 * @see #endPoint
	 * @exception IllegalArgumentException Thrown if code is not a valid instrument code.
	 * @see FitsFilename#INSTRUMENT_CODE_FRODOSPEC_BLUE
	 * @see FitsFilename#INSTRUMENT_CODE_RISE
	 * @see FitsFilename#INSTRUMENT_CODE_FRODOSPEC_RED
	 * @see FitsFilename#INSTRUMENT_CODE_O
	 * @see FitsFilename#INSTRUMENT_CODE_SPRAT
	 * @see FitsFilename#INSTRUMENT_CODE_LIRIC
	 * @see FitsFilename#INSTRUMENT_CODE_LOCI
	 * @see FitsFilename#INSTRUMENT_CODE_MOPTOP_3
	 * @see FitsFilename#INSTRUMENT_CODE_MOPTOP_4
	 */
	public void setInstrumentCode(char code) throws IllegalArgumentException
	{
		if((code != FitsFilename.INSTRUMENT_CODE_FRODOSPEC_BLUE) &&
		   (code != FitsFilename.INSTRUMENT_CODE_RISE) && 
		   (code != FitsFilename.INSTRUMENT_CODE_FRODOSPEC_RED) &&
		   (code != FitsFilename.INSTRUMENT_CODE_O)&&
		   (code != FitsFilename.INSTRUMENT_CODE_SPRAT) && 
		   (code != FitsFilename.INSTRUMENT_CODE_LIRIC) &&
		   (code != FitsFilename.INSTRUMENT_CODE_LOCI) &&
		   (code != FitsFilename.INSTRUMENT_CODE_MOPTOP_3) &&
		   (code != FitsFilename.INSTRUMENT_CODE_MOPTOP_4))
		{
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":setInstrumentCode:Illegal instrument code "+code+".");
		}
		instrumentCode = code;
		endPoint.addParameter("instrument",instrumentCode);
	}

	/**
	 * Set routine for the exposure code. The end-point's "exposure" parameter is set accordingly.
	 * @param code The code to set to.
	 * @exception IllegalArgumentException Thrown if code is not a valid exposure code.
	 * @see #exposureCode
	 * @see #endPoint
	 * @see FitsFilename#EXPOSURE_CODE_ARC
	 * @see FitsFilename#EXPOSURE_CODE_BIAS
	 * @see FitsFilename#EXPOSURE_CODE_DARK
	 * @see FitsFilename#EXPOSURE_CODE_EXPOSURE
	 * @see FitsFilename#EXPOSURE_CODE_ACQUIRE
	 * @see FitsFilename#EXPOSURE_CODE_SKY_FLAT
	 * @see FitsFilename#EXPOSURE_CODE_STANDARD
	 * @see FitsFilename#EXPOSURE_CODE_LAMP_FLAT
	 */
	public void setExposureCode(char code) throws IllegalArgumentException
	{
		if ((code != FitsFilename.EXPOSURE_CODE_ARC) &&
		    (code != FitsFilename.EXPOSURE_CODE_BIAS) &&
		    (code != FitsFilename.EXPOSURE_CODE_DARK) && 
		    (code != FitsFilename.EXPOSURE_CODE_EXPOSURE) &&
		    (code != FitsFilename.EXPOSURE_CODE_SKY_FLAT) && 
		    (code != FitsFilename.EXPOSURE_CODE_ACQUIRE) &&
		    (code != FitsFilename.EXPOSURE_CODE_STANDARD) && 
		    (code != FitsFilename.EXPOSURE_CODE_LAMP_FLAT))
		{
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":setExposureCode:Illegal exposure code "+code+".");
		}
		exposureCode = code;
		endPoint.addParameter("exposure",exposureCode);
	}

	/**
	 * Set routine for the multrun flag. The end-point's "multrun" parameter is set accordingly.
	 * @param flag The multrun flag: one of MULTRUN_FLAG_START|MULTRUN_FLAG_NEXT.
	 * @exception IllegalArgumentException Thrown if flag is not a valid multrun flag.
	 * @see #multrunFlag
	 * @see #endPoint
	 * @see #MULTRUN_FLAG_START
	 * @see #MULTRUN_FLAG_NEXT
	 */
	public void setMultrunFlag(String flag) throws IllegalArgumentException
	{
		if((flag != MULTRUN_FLAG_START) &&
		   (flag != MULTRUN_FLAG_NEXT))
		{
			throw new IllegalArgumentException(this.getClass().getName()+
							   ":setMultrunFlag:Illegal multrun flag "+flag+".");
		}
		multrunFlag = flag;
		endPoint.addParameter("multrun",multrunFlag);
	}
	
	/**
	 * Set routine for the file extension string.
	 * @param s The string to set the file extension to, withour the '.'.
	 * @see #fileExtension
	 */
	public void setFileExtension(String s)
	{
		if(s != null)
			fileExtension = new String(s);
	}

	/**
	 * Run thread. Just invokes the end-point's run method.
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#run
	 */
	public void run()
	{
		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":run:started.");
		endPoint.run();
		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":run:finished.");
	}

	/**
	 * Return whether the command return status returned by the Flask end-point was "OK".
	 * @return A boolean, true if the reply status returned by the Flask end-point 
	 *         (with the JSON key 'status') was "OK", false otherwise.
	 * @see #endPoint
	 */	
	public boolean isReturnStatusOK() throws org.json.JSONException
	{
		String returnStatusString;
		boolean success;
		
		try
		{
			returnStatusString = getReturnStatus();
			success = returnStatusString.equals("OK");
		}
		catch(Exception e)
		{
			success = false;
		}
		return success;
	}
	
	/**
	 * Return a copy of the Http Response Code generated by the server when responding to the end-point request.
	 * This is normally 200 (HTTP_OK) if the call succeeded.
	 * @return The Http Response Code.
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#getHttpResponseCode
	 */
	public int getHttpResponseCode()
	{
		return endPoint.getHttpResponseCode();
	}
	
	/**
	 * Return an exception generated when an end-point call is invoked via a Thread (run method).
	 * @return An exception if one was generated during an end-point call, or null otherwise.
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#getRunException
	 */
	public Exception getRunException()
	{
		return endPoint.getRunException();
	}
	
	/**
	 * Return the command return status returned by the Flask end-point.
	 * @return A string, the reply status returned by the Flask end-point 
	 *         (with the JSON key 'status').
	 * @see #endPoint
	 * @exception JSONException Thrown if the key is not found or if the value is not a string.
	 */
	public String getReturnStatus() throws org.json.JSONException
	{
		return endPoint.getReturnValueString("status");
	}

	/**
	 * Return the message string returned by the Flask end-point.
	 * @return The message as a string returned by the Flask end-point 
	 *         (with the JSON key 'message').
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#getReturnValueString(java.lang.String)
	 * @exception JSONException Thrown if the key is not found or if the value is not a string.
	 */
	public String getReturnMessage() throws org.json.JSONException
	{
		return endPoint.getReturnValueString("message");
	}

	/**
	 * Get the LT FITS filename returned by the Flask end-point.
	 * @return The LT FITS filename string returned by the Flask end-point 
	 *         (with the JSON key 'filename').
	 * @see #endPoint
	 * @see ngat.flask.EndPoint#getReturnValueString(java.lang.String)
	 * @exception JSONException Thrown if the key is not found or if the value is not a string.
	 */
	public String getReturnFilename() throws org.json.JSONException
	{
		return endPoint.getReturnValueString("filename");
	}

	/**
	 * Main test program.
	 * @param args The argument list.
	 */
	public static void main(String args[])
	{
		FitsFilenameServer fitsFilenameServer = null;
		String hostname = null;
		String filename = null;
		String instrumentCodeString = new String("k");
		String exposureTypeString = new String("e");
		String multrunFlagString = new String("start");
		String fitsEntensionString = new String("fits");
		char instrumentCode;
		char exposureType;
		int portNumber = 3000;

		if(args.length < 6)
		{
			System.out.println("java ngat.fits.FitsFilenameServer <hostname> <port number> <instrument code> <exposure type> <multrun:start|next> <fits extension>");
			System.exit(1);
		}
		try
		{
			hostname = args[0];
			portNumber = Integer.parseInt(args[1]);
			instrumentCodeString = args[2];
			exposureTypeString = args[3];
			multrunFlagString = args[4];
			fitsEntensionString = args[5];
			// parse instrument code string into a character
			if(instrumentCodeString.length() != 1)
			{
				throw new Exception(FitsFilenameServer.class.getName()+":Illegal instrument code "+
						    instrumentCodeString+" with length "+
						    instrumentCodeString.length()+".");
			}
			instrumentCode = instrumentCodeString.charAt(0);
			// parse exposure type string into a character
			if(exposureTypeString.length() != 1)
			{
				throw new Exception(FitsFilenameServer.class.getName()+":Illegal exposure code "+
						    exposureTypeString+" with length "+
						    exposureTypeString.length()+".");
			}
			exposureType = exposureTypeString.charAt(0);
			// construct instance to send command to filename server
			fitsFilenameServer = new FitsFilenameServer();
			fitsFilenameServer.initialiseLogging();
			fitsFilenameServer.setAddress(hostname);
			fitsFilenameServer.setPortNumber(portNumber);
			fitsFilenameServer.setInstrumentCode(instrumentCode);
			fitsFilenameServer.setExposureCode(exposureType);
			fitsFilenameServer.setMultrunFlag(multrunFlagString);
			fitsFilenameServer.setFileExtension(fitsEntensionString);
			fitsFilenameServer.run();
			if(fitsFilenameServer.getRunException() != null)
			{
				System.err.println("FitsFilenameServer: Command failed.");
				fitsFilenameServer.getRunException().printStackTrace(System.err);
				System.exit(1);
			}
			System.out.println("Http Response Code (200 on success):"+fitsFilenameServer.getHttpResponseCode());
			System.out.println("Return Status:"+fitsFilenameServer.getReturnStatus());
			System.out.println("Is Return Status OK:"+fitsFilenameServer.isReturnStatusOK());
			System.out.println("Filename:"+fitsFilenameServer.getReturnFilename());
			System.out.println("Message:"+fitsFilenameServer.getReturnMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		System.exit(0);
	}
	
	/**
	 * A simple class method to setup console logging for testing the FitsFilenameServer
	 * from the command line.
	 */
	public static void initialiseLogging()
	{
		Logger l = null;
		LogHandler handler = null;
		BogstanLogFormatter blf = null;
		String loggerNameStringArray[] = {"ngat.flask.EndPoint",
						  "ngat.fits.FitsFilenameServer"};
		
		blf = new BogstanLogFormatter();
		blf.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS z"));
		handler = new ConsoleLogHandler(blf);
		handler.setLogLevel(Logging.ALL);
		for(int index = 0; index < loggerNameStringArray.length; index ++)
		{
			l = LogManager.getLogger(loggerNameStringArray[index]);
			l.setLogLevel(Logging.ALL);
			l.addHandler(handler);
		}
	}
}
