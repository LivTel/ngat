package ngat.fits;

import java.lang.*;
import ngat.util.logging.*;

/**
 * This class loads, flips image data and writes it back to disk.
 * @author Chris Mottram
 * @version $Revision: 721 $
 */
public class FitsFlip
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Static code to load libngatfits, the shared C library that implements an interface to the
	 * CFITSIO FITS file routines.
	 */
	static
	{
		System.loadLibrary("ngatfits");
	}

//internal C layer initialisation
	/**
	 * Native method that allows the JNI layer to store a reference to this Class's logger.
	 * @param logger The logger for this class.
	 */
	private native void initialiseLoggerReference(Logger logger);
	/**
	 * Native method that allows the JNI layer to release the global reference to this Class's logger.
	 */
	private native void finaliseLoggerReference();
	/**
	 * Native wrapper to a libngatfits routine to return the error number.
	 * @return The error number.
	 */
	private native int Fits_Flip_Get_Error_Number();
	/**
	 * Native wrapper to a libccsfits routine to return the error string.
	 * @return A String of the error.
	 */
	private native String Fits_Flip_Get_Error_String();
	/**
	 * Native wrapper to a libngatfits routine to open the filename.
	 * @param filename The filename to open.
	 * @exception FitsFlipException Thrown if the operation fails.
	 */
	private native void Fits_Flip_Open(String filename) throws FitsFlipException;
	/**
	 * Native wrapper to a libngatfits routine to close the file.
	 * @exception FitsFlipException Thrown if the operation fails.
	 */
	private native void Fits_Flip_Close() throws FitsFlipException;
	/**
	 * Native wrapper to a libngatfits routine to flip the image.
	 * @param flipX A boolean, if true flip the image in x.
	 * @param flipY A boolean, if true flip the image in y.
	 * @exception FitsFlipException Thrown if the operation fails.
	 */
	private native void Fits_Flip_Flip(boolean flipX,boolean flipY) throws FitsFlipException;

// per instance variables
	/**
	 * The logger to log messages to.
	 */
	protected Logger logger = null;

	/**
	 * Constructor for FitsFlip. Constructs the logger, and sets the C layers reference to it.
	 * @see #logger
	 * @see #initialiseLoggerReference
	 */
	public FitsFlip()
	{
		super();
		logger = LogManager.getLogger(this);
		initialiseLoggerReference(logger);
	}

	/**
	 * Finalize method for this class, delete JNI global references.
	 * @see #finaliseLoggerReference
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		finaliseLoggerReference();
	}

	/**
	 * Method to flip the image.
	 * @param filename A string representing the FITS filename to flip.
	 * @param flipX A boolean, if true flip the image in x.
	 * @param flipY A boolean, if true flip the image in y.
	 * @exception FitsFlipException Thrown if the operation fails.
	 */
	public void flip(String filename,boolean flipX,boolean flipY) throws FitsFlipException
	{
		logger.log(Logging.VERBOSITY_VERBOSE,this.getClass().getName()+":flip:"+filename+
			   ":Opening FITS file.");
		try
		{
			Fits_Flip_Open(filename);
			logger.log(Logging.VERBOSITY_VERBOSE,this.getClass().getName()+":flip:"+filename+
				   ":Opened.");
			logger.log(Logging.VERBOSITY_VERBOSE,this.getClass().getName()+":flip:"+filename+
				   ":Flipping image:x:"+flipX+" y:"+flipY+".");
			Fits_Flip_Flip(flipX,flipY);
		}
		finally
		{
		// close the fits file.
			logger.log(Logging.VERBOSITY_VERBOSE,this.getClass().getName()+":flip:"+filename+
				   ":Closing.");
			Fits_Flip_Close();
		}
		logger.log(Logging.VERBOSITY_VERBOSE,this.getClass().getName()+":flip:"+filename+":Finished.");
	}
}
