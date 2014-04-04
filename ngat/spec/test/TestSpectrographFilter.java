// TestSpectrographFilter.java
// $Header: /space/home/eng/cjm/cvs/ngat/spec/test/TestSpectrographFilter.java,v 0.4 2013-06-28 10:13:30 cjm Exp $
package ngat.spec.test;

import java.lang.*;

import ngat.spec.*;

/**
 * Simple test of ngat.spec library.
 * @author Chris Mottram
 * @version $Revision$
 */
public class TestSpectrographFilter
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	/**
	 * Run method. 
	 * <ul>
	 * <li>Opens spectrograph library.
	 * <li>Sets arc lamp.
	 * <li>Switches arc lamp on.
	 * <li>Auto configures filter positions.
	 * <li>Prints position zero.
	 * <li>Drives filter to position zero.
	 * <li>Switches arc lamp off.
	 * <li>Closes spectrograph library.
	 * </ul>
	 */
	public void run()
	{
		int ncols,nrows,hbin,vbin,startx,starty;

		try
		{
			SpecLibrary.selectIODevice(SpecLibrary.DAIO_INTERFACE_DEVICE_TEXT);
			SpecLibrary.open(SpecLibrary.SPEC_OPEN_ALL_BIT);//SPEC_OPEN_DAIO_BIT
			SpecLibrary.arcLampSetLamp(0);
			SpecLibrary.arcLampSet(true);

			SpecLibrary.filterAutoConfigurePosition();
			System.out.println("A/D count for filter position 0:"+SpecLibrary.filterGetPositionADCount(0));
			SpecLibrary.filterSetPosition(0);

			SpecLibrary.cameraSetupStartup(false,false);
			startx = SpecLibrary.cameraSetupGetStartColumn();
			starty = SpecLibrary.cameraSetupGetStartRow();
			ncols = SpecLibrary.cameraSetupGetNumberCols();
			nrows = SpecLibrary.cameraSetupGetNumberRows();
			hbin = SpecLibrary.cameraSetupGetHorizontalBinning();
			vbin = SpecLibrary.cameraSetupGetVerticalBinning();
			SpecLibrary.cameraSetupDimensions(startx,starty,ncols,nrows,hbin,vbin);

			SpecLibrary.cameraTemperatureSet(-20.0);
			SpecLibrary.cameraExpose(10000,"test.fits");

			SpecLibrary.arcLampSet(false);
			SpecLibrary.close();
		}
		catch(SpecNativeException e)
		{
			System.err.println(this.getClass().getName()+":failed:"+e);
			return;
		}
	}

	/**
	 * Main program. Creates an instance of this class, and calls it's run method.
	 * @see #run
	 */
	public static void main(String args[])
	{
		TestSpectrographFilter tsf = new TestSpectrographFilter();
		tsf.run();
		System.exit(0);
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 0.3  2002/02/14 18:05:46  cjm
// Added extra calls for startx/starty.
//
// Revision 0.2  2000/10/19 13:48:27  cjm
// initial test program.
//
// Revision 0.1  2000/10/16 17:39:24  cjm
// initial revision.
//
//
