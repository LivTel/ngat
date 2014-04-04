// ScienceFoldMove.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/test/ScienceFoldMove.java,v 1.1 2013-06-28 10:53:06 cjm Exp $

import java.lang.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class stows and deploys the science fold, when controlled from the A&G PLC.
 * Based on talking to ltlampplc, using the following controls:
 * <ul>
 * <li>N55:1/0 set to 1 will STOW
 * <li>N55:1/0 set to 0 will DEPLOY
 * <li>N55:2/0 signal indicating STOWED
 * <li>N55:2/1 signal indicated DEPLOYED
 * </ul>
 * @author Chris Mottram
 * @version $Revision$
 */
public class ScienceFoldMove
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Which type of device to try to connect to.
	 * @see ngat.eip.EIPPLC#PLC_TYPE_MICROLOGIX1100
	 */
	protected int plcType = EIPPLC.PLC_TYPE_MICROLOGIX1100;
	/**
	 * The hostname of the device to connect to (ltlampplc).
	 */
	protected String hostname = "ltlampplc";
	/**
	 * The backplane containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int backplane = 1;
	/**
	 * The slot containing the PLC. Part of the Ethernet/IP addressing.
	 */
	protected int slot = 0;
	/**
	 * The logger.
	 */
	protected Logger logger = null;
	/**
	 * The log level.
	 */
	protected int logLevel = Logging.VERBOSITY_VERY_VERBOSE;
	/**
	 * Control address for setting whether to stow/deploy.
	 */
	protected String plcControlAddress = "N55:1/0";
	/**
	 * Control address for setting whether to stow/deploy.
	 */
	protected String plcDeployStatusAddress = "N55:2/1";
	/**
	 * Control address for setting whether to stow/deploy.
	 */
	protected String plcStowStatusAddress = "N55:2/0";
	/**
	 * How many seconds to wait for a stow/deploy to complete, befire timing out.
	 */
	protected int timeoutCount = 60;
	/**
	 * PLC library instance.
	 * @see ngat.eip.EIPPLC
	 */
	protected EIPPLC plc = null;
	/**
	 * Do a stow operation. Command line argument flag.
	 */
	protected boolean doStow = false;
	/**
	 * Do a deploy operation. Command line argument flag.
	 */
	protected boolean doDeploy = false;

	/**
	 * Constructor. Create PLC instance. Create logger instance.
	 * @see #plc
	 * @see #logger
	 * @see ngat.eip.EIPPLC
	 */
	public ScienceFoldMove()
	{
		super();
		plc = new EIPPLC();
		logger = LogManager.getLogger(this);
	}

	/**
	 * Set log level (of the PLC).
	 * @param level The level.
	 * @see #logger
	 * @see #plc
	 */
	public void setLogLevel(int level)
	{
		logger.setLogLevel(logLevel);
		plc.setLogFilterLevel(logLevel);
	}

	/**
	 * Deploy the science fold.
	 * <ul>
	 * <li>We create a handle to the PLC.
	 * <li>We open the handle to the PLC.
	 * <li>We call setBoolean to address plcControlAddress to set the control bit to deploy the science fold.
	 * <li>We enter a loop until done:
	 *     <ul>
	 *     <li>We call getBoolean to retrieve the plcDeployStatusAddress value.
	 *     <li>If the status value is true, we exit successfully from the loop.
	 *     <li>Otherwise we increment a timeout.
	 *     <li>If the timeout is greater than timeoutCount, throw a timed out exception.
	 *     <li>Otherwise sleep a second.
	 *     </ul>
	 * <li>Finally, calls close and destroyHandle to close the connection to the EIPPLC.
	 * </ul>
	 * @exception EIPNativeException Thrown if an error occurs initialising,opening or 
	 *            closing the EIPPLC interface.
	 * @exception Exception Thrown if the deploy times out.
	 * @see #logger
	 * @see #plc
	 * @see #hostname
	 * @see #plcType
	 * @see #backplane
	 * @see #slot
	 * @see #logLevel
	 * @see #plcControlAddress
	 * @see #plcDeployStatusAddress
	 * @see #timeoutCount
	 * @see ngat.eip.EIPPLC#setLogFilterLevel
	 * @see ngat.eip.EIPPLC#createHandle
	 * @see ngat.eip.EIPPLC#destroyHandle
	 * @see ngat.eip.EIPPLC#setBoolean
	 * @see ngat.eip.EIPPLC#getBoolean
	 * @see ngat.eip.EIPHandle
	 */
	public void deploy() throws EIPNativeException, Exception
	{
		EIPHandle handle = null;
		boolean done,statusValue;
		int timeoutIndex = 0;

		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":deploy:Started.");
		logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+":deploy:Creating handle.");
		handle = plc.createHandle(hostname,backplane,slot,plcType);
		logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+":deploy:Opening handle.");
		plc.open(handle);
		try
		{
			// set control demand
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":deploy:Setting demand:Set "+plcControlAddress+" to false.");
			plc.setBoolean(handle,plcControlAddress,false);
			// wait until science fold moved, or timeout
			timeoutIndex = 0;
			done = false;
			while(done == false)
			{
				logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
					   ":deploy:Getting status:Get "+plcDeployStatusAddress+".");
				statusValue = plc.getBoolean(handle,plcDeployStatusAddress);
				logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
					   ":deploy:"+plcDeployStatusAddress+" returned "+statusValue+".");
				if(statusValue)
				{
					// fold is in position
					logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
						   ":deploy:Science fold deployed.");
					done = true;
				}
				else
				{
					// check timeout and wait a bit
					timeoutIndex++;
					if(timeoutIndex > timeoutCount)
					{
						logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
							   ":deploy:Science fold deploy timed out.");
						throw new Exception(this.getClass().getName()+
								    ":deploy:Deploy failed:timeout after "+
								    timeoutIndex+" seconds.");
					}
					Thread.sleep(1000);
				}
			}// end while
		}
		finally
		{
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":deploy:Closing handle.");
			plc.close(handle);
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":deploy:Destroying handle.");
			plc.destroyHandle(handle);
		}
		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":deploy:Finished.");
	}

	/**
	 * Stow the science fold.
	 * <ul>
	 * <li>We create a handle to the PLC.
	 * <li>We open the handle to the PLC.
	 * <li>We call setBoolean to address plcControlAddress to set the control bit to stow the science fold.
	 * <li>We enter a loop until done:
	 *     <ul>
	 *     <li>We call getBoolean to retrieve the plcStowStatusAddress value.
	 *     <li>If the status value is true, we exit successfully from the loop.
	 *     <li>Otherwise we increment a timeout.
	 *     <li>If the timeout is greater than timeoutCount, throw a timed out exception.
	 *     <li>Otherwise sleep a second.
	 *     </ul>
	 * <li>Finally, calls close and destroyHandle to close the connection to the EIPPLC.
	 * </ul>
	 * @exception EIPNativeException Thrown if an error occurs initialising,opening or 
	 *            closing the EIPPLC interface.
	 * @exception Exception Thrown if the stow times out.
	 * @see #logger
	 * @see #plc
	 * @see #hostname
	 * @see #plcType
	 * @see #backplane
	 * @see #slot
	 * @see #logLevel
	 * @see #plcControlAddress
	 * @see #plcStowStatusAddress
	 * @see #timeoutCount
	 * @see ngat.eip.EIPPLC#setLogFilterLevel
	 * @see ngat.eip.EIPPLC#createHandle
	 * @see ngat.eip.EIPPLC#destroyHandle
	 * @see ngat.eip.EIPPLC#setBoolean
	 * @see ngat.eip.EIPPLC#getBoolean
	 * @see ngat.eip.EIPHandle
	 */
	public void stow() throws EIPNativeException, Exception
	{
		EIPHandle handle = null;
		boolean done,statusValue;
		int timeoutIndex = 0;

		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":stow:Started.");
		logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+":stow:Creating handle.");
		handle = plc.createHandle(hostname,backplane,slot,plcType);
		logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+":stow:Opening handle.");
		plc.open(handle);
		try
		{
			// set control demand
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":stow:Setting demand:Set "+plcControlAddress+" to true.");
			plc.setBoolean(handle,plcControlAddress,true);
			// wait until science fold moved, or timeout
			timeoutIndex = 0;
			done = false;
			while(done == false)
			{
				logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
					   ":stow:Getting status:Get "+plcStowStatusAddress+".");
				statusValue = plc.getBoolean(handle,plcStowStatusAddress);
				logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
					   ":stow:"+plcStowStatusAddress+" returned "+statusValue+".");
				if(statusValue)
				{
					// fold is in position
					logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
						   ":stow:Science fold stowed.");
					done = true;
				}
				else
				{
					// check timeout and wait a bit
					timeoutIndex++;
					if(timeoutIndex > timeoutCount)
					{
						logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
							   ":stow:Science fold stow timed out.");
						throw new Exception(this.getClass().getName()+
								    ":stow:Stow failed:timeout after "+timeoutIndex+
								    " seconds.");
					}
					Thread.sleep(1000);
				}
			}// end while
		}
		finally
		{
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":stow:Closing handle.");
			plc.close(handle);
			logger.log(Logging.VERBOSITY_VERY_VERBOSE,this.getClass().getName()+
				   ":stow:Destroying handle.");
			plc.destroyHandle(handle);
		}
		logger.log(Logging.VERBOSITY_INTERMEDIATE,this.getClass().getName()+":stow:Finished.");
	}

	/**
	 * Method to initialise the logger.
	 * @see #logger
	 */
	protected void initLoggers()
	{
		Logger l = null;
		LogHandler handler = null;
		BogstanLogFormatter blf = null;
		String loggerList[] = {"ScienceFoldMove","ngat.eip.EIPPLC"};

		// setup log formatter
		blf = new BogstanLogFormatter();
		blf.setDateFormat(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.SSS z"));
		// setup log handler
		handler = new ConsoleLogHandler(blf);
		handler.setLogLevel(Logging.ALL);
		// Apply handler and filter to each logger in the list
		for(int i=0;i < loggerList.length;i++)
		{
			System.out.println(this.getClass().getName()+":initLoggers:Setting up logger:"+loggerList[i]);
			l = LogManager.getLogger(loggerList[i]);
			l.addHandler(handler);
			l.setLogLevel(Logging.ALL);
		}
	}

	/**
	 * Parse command line arguments.
	 * @param args The command line argument list.
	 * @see #help
	 * @see #logLevel
	 * @see #doDeploy
	 * @see #doStow
	 */
	private void parseArgs(String[] args)
	{
		for(int i = 0; i < args.length;i++)
		{

			if(args[i].equals("-d")||args[i].equals("-deploy"))
			{
				doDeploy = true;
			}
			else if(args[i].equals("-h")||args[i].equals("-help"))
			{
				help();
				System.exit(0);
			}
			else if(args[i].equals("-log_level")||args[i].equals("-l"))
			{
				if((i+1)< args.length)
				{
					logLevel = Integer.parseInt(args[i+1]);
					i++;
				}
				else
				{
					System.err.println("-log_level should have an numeric argument.");
					System.exit(1);
				}
			}
			else if(args[i].equals("-s")||args[i].equals("-stow"))
			{
				doStow = true;
			}
			else
			{
				System.out.println(this.getClass().getName()+":Option not supported:"+args[i]);
				System.exit(1);
			}
		}
	}

	/**
	 * Help message routine.
	 */
	private void help()
	{
		System.out.println(this.getClass().getName()+" Help:");
		System.out.println("Options are:");
		System.out.println("\t-help");
		System.out.println("\t-stow|-deploy");
		System.out.println("\t-log_level|-l <number>");
	}

	/**
	 * Main method - entry point for the test program.
	 * Example run: 
	 * <pre>java ScienceFoldMove -stow|-deploy</pre>
	 * <ul>
	 * <li>Calls parseArgs to parse the command line arguments.
	 * <li>Calls initLoggers to setup logging.
	 * <li>Calls run method to get the value from the PLC.
	 * </ul>
	 * @param args Command line arguments.
	 * @see #parseArgs
	 * @see #initLoggers
	 * @see #deploy
	 * @see #stow
	 * @see #doDeploy
	 * @see #doStow
	 */
	public static void main(String[] args)
	{
		ScienceFoldMove sfm = new ScienceFoldMove();

		sfm.parseArgs(args);
		sfm.initLoggers();
		try
		{
			if(sfm.doDeploy)
				sfm.deploy();
			if(sfm.doStow)
				sfm.stow();
		}
		catch(Exception e)
		{
			System.err.println("ScienceFoldMove stow/deploy failed:"+e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
//
// $Log: not supported by cvs2svn $
//
