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
// TestWCSToolsWCSToPixel.java
// $Header: /space/home/eng/cjm/cvs/ngat/astrometry/test/TestWCSToolsWCSToPixel.java,v 1.1 2007-08-10 10:37:26 cjm Exp $
package ngat.astrometry.test;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

import ngat.astrometry.*;

/**
 * Test WCS (RA/Dec) to Pixel coordinate conversion routines in WCSTools.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 * @see ngat.astrometry.WCSTools
 */
public class TestWCSToolsWCSToPixel
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: TestWCSToolsWCSToPixel.java,v 1.1 2007-08-10 10:37:26 cjm Exp $");

	public static void main(String args[])
	{
		WCSToolsWorldCoorHandle handle = null;
		WCSToolsCoordinate position = null;
		String fitsFilename = null;
		double xPos = 0.0;
		double yPos = 0.0;

		if(args.length != 3)
		{
			System.err.println("java ngat.astrometry.test.TestWCSToolsWCSToPixel <FITS filename> <HH:MM:SS> <[+|-]DD:MM:SS>");
			System.exit(1);
		}
		fitsFilename = args[0];
		try
		{
			// parse RA -> decimal radians
			xPos = Position.parseHMS(args[1]);
		}
		catch(Exception e)
		{
			System.err.println("Parsing RA "+args[1]+" failed:"+e);
			System.exit(2);
		}
		try
		{
			// parse Dec -> decimal radians
			yPos = Position.parseDMS(args[2]);
		}
		catch(Exception e)
		{
			System.err.println("Parsing Declination "+args[2]+" failed:"+e);
			System.exit(3);
		}
		try
		{
			// Open FITS filename and get WCSTools handle
			handle = WCSTools.getWCSFITS(fitsFilename,3);
			// convert RA/Dec position (decimal degrees) to X,Y pixel coordinates
			position = WCSTools.wcsToPixel(handle,xPos*180.0/Math.PI,yPos*180.0/Math.PI);
			// Close WCSTools handle
			WCSTools.wcsFree(handle);
			System.out.println("Filename : "+fitsFilename);
			System.out.println("Position (J2000) : "+args[1]+","+args[2]+
					   ": Decimal Degrees (Radians) : "+xPos+","+yPos);
			System.out.println("Pixel : "+position.getXCoordinate()+","+position.getYCoordinate());
		}
		catch(Exception e)
		{
			System.err.println("Conversion failed:"+e);
			System.exit(3);
		}
		System.exit(0);
	}
}
//
// $Log: not supported by cvs2svn $
//

