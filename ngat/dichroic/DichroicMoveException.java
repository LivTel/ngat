// DichroicMoveException.java
// $Header: /space/home/eng/cjm/cvs/ngat/dichroic/DichroicMoveException.java,v 1.1 2011-10-12 10:16:01 cjm Exp $
package ngat.dichroic;
/**
 * This class extends DichroicException. It is used to throw Dichroic move errors.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class DichroicMoveException extends DichroicException
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: DichroicMoveException.java,v 1.1 2011-10-12 10:16:01 cjm Exp $");
	/**
	 * The error code returned by the move command.
	 */
	int errorCode;

	/**
	 * Constructor for the exception.
	 * @param errorString The error string.
	 */
	public DichroicMoveException(String errorString)
	{
		super(errorString);
	}

	/**
	 * Constructor for the exception. makeErrorString is called to create a sensible error string 
	 * associated with the error code.
	 * @param errorCode An integer error number returned by the underlying Arduino.
	 * @see #errorCode
	 * @see #makeErrorString
	 */
	public DichroicMoveException(int errorCode)
	{
		super(makeErrorString(errorCode));
		this.errorCode = errorCode;
	}

	/**
	 * Return the error code associated with this exception.
	 * @return An integer error code.
	 * @see #errorCode
	 */
	public int getErrorCode()
	{
		return errorCode;
	}

	/**
	 * Class method called during superclass construction to generate a sensible error message based on
	 * the error code. These error messages are based on the Arduino sketch in: 
	 * ~dev/src/arduino/sketchbook/Dichroic/Dichroic.pde
	 * @param errorCode The error code to generate an error message for.
	 * @return A string describing the error message.
	 */
	protected static String makeErrorString(int errorCode)
	{
		switch(errorCode)
		{
			case 1:
				return new String ("midstopStow:ERROR 1:Dichroic in RIGHT position.");
			case 2:
				return new String ("midstopStow:ERROR 2:Dichroic NOT in LEFT position.");
			case 3:
				return new String ("midstopStow:ERROR 3:Dichroic in MID position.");
			case 4:
				return new String ("midstopDeploy:ERROR 4:Failed to deploy.");
			case 5:
				return new String ("midstopStow:ERROR 5:Failed to stow.");
			case 6:
				return new String ("moveLeft:ERROR 6:Mid Stop no longer stowed.");
			case 7:
				return new String ("moveLeft:ERROR 7:Move timeout.");
			case 8:
				return new String ("moveLeft:ERROR 8:Dichroic NOT in LEFT position.");
			case 9:
				return new String ("moveLeft:ERROR 9:Dichroic in RIGHT position.");
			case 10:
				return new String ("moveLeft:ERROR 10:Dichroic in MID position.");
			case 11:
				return new String ("moveMiddle:ERROR 11:Mid Stop no longer deployed.");
			case 12:
				return new String ("moveMiddle:ERROR 12:Move timeout.");
			case 13:
				return new String ("moveMiddle:ERROR 13:Dichroic in LEFT position.");
			case 14:
				return new String ("moveMiddle:ERROR 14:Dichroic in RIGHT position.");
			case 15:
				return new String ("moveMiddle:ERROR 15:Dichroic not in MID position.");
			case 16:
				return new String ("moveRight:ERROR 16:Mid stop no longer stowed.");
			case 17:
				return new String ("moveRight:ERROR 17:Move timeout.");
			case 18:
				return new String ("moveRight:ERROR 18:Dichroic in LEFT position.");
			case 19:
				return new String ("moveRight:ERROR 19:Dichroic not in RIGHT position.");
			case 20:
				return new String ("moveRight:ERROR 20:Dichroic in MID position.");
			case 21:
				return new String ("messageReady:ERROR 21:Unknown get sensor command.");
			case 22:
				return new String ("messageReady:ERROR 22:Unknown get command.");
			case 23:
				return new String ("messageReady:ERROR 23:Unknown midstop command.");
			case 24:
				return new String ("messageReady:ERROR 24:Unknown move command.");
			case 25:
				return new String ("messageReady:ERROR 25:Unknown slidedirection command.");
			case 26:
				return new String ("messageReady:ERROR 26:Unknown command.");
			default:
				return new String ("DichroicMoveException: move returned error code:"+errorCode);
		}
	}
}

//
// $Log: not supported by cvs2svn $
//
