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
// TestWCSToolsPixelToWCS.java
// $Header: /space/home/eng/cjm/cvs/ngat/astrometry/test/TestWCSToolsPixelToWCS.java,v 1.1 2007-08-10 10:37:23 cjm Exp $
package ngat.astrometry.test;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

import ngat.astrometry.*;

/**
 * Test Pixel to WCS (RA/Dec) coordinate conversion routines in WCSTools.
 * @author Chris Mottram
 * @version $Revision$
 * @see ngat.astrometry.WCSTools
 */
public class TestWCSToolsPixelToWCS
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

	public static void main(String args[])
	{
		WCSToolsWorldCoorHandle handle = null;
		WCSToolsCoordinate position = null;
		String fitsFilename = null;
		String raString = null;
		String decString = null;
		double xPixel = 0.0;
		double yPixel = 0.0;

		if(args.length != 3)
		{
			System.err.println("java ngat.astrometry.test.TestWCSToolsPixelToWCS <FITS filename> <X Pixel> <Y Pixel>");
			System.exit(1);
		}
		fitsFilename = args[0];
		try
		{
			xPixel = Double.parseDouble(args[1]);
		}
		catch(Exception e)
		{
			System.err.println("Parsing X Pixel "+args[1]+" failed:"+e);
			System.exit(2);
		}
		try
		{
			yPixel = Double.parseDouble(args[2]);
		}
		catch(Exception e)
		{
			System.err.println("Parsing Y Pixel "+args[2]+" failed:"+e);
			System.exit(3);
		}
		try
		{
			// Open FITS filename and get WCSTools handle
			handle = WCSTools.getWCSFITS(fitsFilename,3);
			// convert x,y pixel to RA/Dec position
			position = WCSTools.pixelToWCS(handle,xPixel,yPixel);
			// Close WCSTools handle
			WCSTools.wcsFree(handle);
			// Convert position (in decimal degrees) to RA/Dec string
			raString = Position.toHMSString(position.getXCoordinate()*Math.PI/180.0);
			decString = Position.toDMSString(position.getYCoordinate()*Math.PI/180.0);
			System.out.println("Filename : "+fitsFilename);
			System.out.println("Pixel : "+xPixel+","+yPixel);
			System.out.println("Position (J2000) : "+raString+","+decString);
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

