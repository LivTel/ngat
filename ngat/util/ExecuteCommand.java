// ExecuteCommand.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/ExecuteCommand.java,v 0.1 2000-02-15 13:39:28 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * This is a simple wrapper class for executing a system command and returning the results as a string. It
 * uses Runtime.exec to execute the command, and waits for the commands termination. The output and error streams are
 * piped to Strings which can be returned.
 * This class will not execute system commands that require typed input.
 * @version $Revision: 0.1 $
 */
public class ExecuteCommand implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: ExecuteCommand.java,v 0.1 2000-02-15 13:39:28 cjm Exp $");
	/**
	 * The command an instance of this command executes when the run method is called.
	 */
	protected String commandString = null;
	/**
	 * Object reference to keep the exception instance that terminated execution of the run method.
	 */
	protected Exception exception = null;
	/**
	 * The Process instance used to execute the command.
	 */
	protected Process process = null;
	/**
	 * The string generated from the output stream (stdout) of the command execution.
	 */
	protected String outputString = null;
	/**
	 * The string generated from the error stream (stderr) of the command execution.
	 */
	protected String errorString = null;

	/**
	 * Constructor.
	 * @param cs The command to execute when the run method is called.
	 */
	public ExecuteCommand(String cs)
	{
		super();
		commandString = new String(cs);
	}

	/**
	 * Method to set the command string to execute. This string will be executed when the run
	 * method is next called.
	 * @param cs The command to execute when the run method is called.
	 */
	public void setCommandString(String cs)
	{
		commandString = new String(cs);
	}

	/**
	 * The run method, that actually executes the command. The method does not return until
	 * command execution is complete. If an exception occurs, the exception field is set and the
	 * run method terminates. The output and error streams of the run command are captured.
	 * @see #exception
	 * @see #commandString
	 * @see #outputString
	 * @see #errorString
	 */
	public void run()
	{
		Runtime runtime = null;
		BufferedReader inputReader = null;
		BufferedReader errorReader = null;
		StringBuffer outputStringBuffer = null;
		StringBuffer errorStringBuffer = null;
		String s = null;
		boolean inputDone,errorDone;

		runtime = Runtime.getRuntime();
		exception = null;
		process = null;
		outputString = null;
		errorString = null;
		try
		{
		// start process
			process = runtime.exec(commandString);
		// setup streams
			outputStringBuffer = new StringBuffer();
			errorStringBuffer = new StringBuffer();
			inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			inputDone = false;
			errorDone = false;
			while((inputDone == false)||(errorDone == false))
			{
				if(!inputDone)
				{
					s = inputReader.readLine();
					if(s != null)
						outputStringBuffer.append(s);
					else
						inputDone = true;
				}
				if(!errorDone)
				{
					s = errorReader.readLine();
					if(s != null)
						errorStringBuffer.append(s);
					else
						errorDone = true;
				}
			}
			outputString = outputStringBuffer.toString();
			errorString = errorStringBuffer.toString();
		// wait for process termination
			process.waitFor();
		}
		catch(IOException e)
		{
			exception = e;
			return;
		}
		catch(InterruptedException e)
		{
			exception = e;
			return;
		}
	}

	/**
	 * Method to get an exception that caused the run method to terminate.
	 * null is returned if no exception occured.
	 * @return The contents of the exception field.
	 * @see #exception
	 */
	public Exception getException()
	{
		return exception;
	}

	/**
	 * Method that gets the exit value of the process that executed the command.
	 * @return The exit value of the command, usually zero for success and non-zero for failure.
	 * @exception IllegalThreadStateException Thrown if the process has not terminated or been started.
	 * @see #process
	 */
	public int getExitValue() throws IllegalThreadStateException
	{
		int exitValue;

		if(process == null)
		{
			exitValue = 1;
			throw new IllegalThreadStateException(this.getClass().getName()+
				":getExitValue:process is null.");
		}
		else
			exitValue = process.exitValue();
		return exitValue;
	}

	/**
	 * Method that returns the output string, generated from the output stream (stdout) of the
	 * command that was executed.
	 * @return The output string, which is null if the execution failed/has not been run yet.
	 * @see #outputString
	 */
	public String getOutputString()
	{
		return outputString;
	}

	/**
	 * Method that returns the error string, generated from the error stream (stderr) of the
	 * command that was executed.
	 * @return The error string, which is null if the execution failed/has not been run yet.
	 * @see #errorString
	 */
	public String getErrorString()
	{
		return errorString;
	}
}
//
// $Log: not supported by cvs2svn $
//
