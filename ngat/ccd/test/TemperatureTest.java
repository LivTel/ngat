package ngat.ccd.test;
// TemperatureTest.java
// java TemperatureTest <temperature>
// $Header: /space/home/eng/cjm/cvs/ngat/ccd/test/TemperatureTest.java,v 1.1 2002-12-16 19:51:57 cjm Exp $

import java.lang.*;
import ngat.ccd.*;

import ArrayTemperature;

/**
 * Little test program that tests CCDLibrary's CCDTemperatureGet and CCDTemperatureSet against
 * Voodoo's ArrayTemperature's calculate_temperature and calculate_adu.
 * Run as follows:
 * java TemperatureTest <temperature>
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class TemperatureTest
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id: TemperatureTest.java,v 1.1 2002-12-16 19:51:57 cjm Exp $");
	/**
	 * The temperature we are testing.
	 */
	public double temperature = 0.0;

	/**
	 * Method to parse arguments.
	 * It expects one argument: e.g. TemperatureTest <temperature>.
	 * @param args The argument list.
	 * @exception IllegalArgumentException Thrown if there is not exactley one argument.
	 * @exception NumberFormatException Thrown if the argument is not a valid double.
	 */
	protected void parseArgs(String args[]) throws IllegalArgumentException, NumberFormatException
	{
		if(args.length != 1)
		{
			throw new IllegalArgumentException(this.getClass().getName()+
				" <temperature (degrees C)>");
		}
		temperature = Double.parseDouble(args[0]);
	}

	/**
	 * Test the CCDLibrary.
	 * @exception CCDLibraryNativeException Thrown if one of the routines fails.
	 */
	protected void testCCDLibrary() throws CCDLibraryNativeException
	{
		CCDLibrary libccd = null;
		CCDLibraryDouble ccdTemperature = null;

	// initialise
		libccd = new CCDLibrary();
		libccd.CCDInitialise(CCDLibrary.CCD_INTERFACE_DEVICE_TEXT);
		libccd.CCDTextSetPrintLevel(CCDLibrary.CCD_TEXT_PRINT_LEVEL_ALL);
		libccd.CCDInterfaceOpen();
	// set
		libccd.CCDTemperatureSet(temperature);
	// get
//		ccdTemperature = new CCDLibraryDouble();
//		libccd.CCDTemperatureGet(ccdTemperature);
	// close
		libccd.CCDInterfaceClose();
//		System.out.println(this.getClass().getName()+":CCDTemperatureGet = "+ccdTemperature.getValue());
	}

	/**
	 * Test Voodoo's ArrayTemperature class.
	 */
	protected void testVoodoo()
	{
		ArrayTemperature at = new ArrayTemperature();
		int adu;
		double calc_temperature;

//diddly		ArrayTemperature.set_algorithm("nonlinear");
		adu = at.calculate_adu(temperature);
		System.out.println(this.getClass().getName()+":Voodoo adu = "+adu);
//		adu = 0xc60;// 0xc60 is what ccd_text returns.
//		calc_temperature = at.calculate_temperature(adu); 
//		System.out.println(this.getClass().getName()+":Voodoo:adu = "+adu+", calculated temperature = "+
//			calc_temperature);

	}

	/**
	 * Main program.
	 */
	public static void main(String args[])
	{
		TemperatureTest tt = new TemperatureTest();

		try
		{
			tt.parseArgs(args);
			tt.testCCDLibrary();
			tt.testVoodoo();
		}
		catch (Exception e)
		{
			System.err.println(tt.getClass().getName()+ " failed:"+e);
			System.exit(1);
		}
		System.exit(0);
	}
}
//
// $Log: not supported by cvs2svn $
//
