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
// WCSTools.java
// $Header: /space/home/eng/cjm/cvs/ngat/astrometry/WCSTools.java,v 1.1 2013-06-28 10:11:49 cjm Exp $
package ngat.astrometry;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * This class wraps Doug Mink wcstools library, such that we can extract coordinate positions from a WCS fitted
 * FITS image.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class WCSTools
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: WCSTools.java,v 1.1 2013-06-28 10:11:49 cjm Exp $");

	/**
	 * Static code to load libngatastrometrywcstools, the shared C library that implements an interface to the
	 * WCSTools routines.
	 */
	static
	{
		System.loadLibrary("ngatastrometrywcstools");
	}

	/**
	 * Native wrapper for the WCSTools GetWCSFITS routine.
	 * @param filename The filename.
	 * @param verbosity The amount of logging to stdout printed by the library.
	 * @return An instance of WCSToolsWorldCoorHandle.
	 */
	protected static native WCSToolsWorldCoorHandle GetWCSFITS(String filename,int verbosity) throws Exception;
	/**
	 * Native wrapper for the WCSTools wcsfree routine.
	 * @param handle The Java handle.
	 */
	protected static native void wcsfree(WCSToolsWorldCoorHandle handle) throws Exception;
	/**
	 * Native wrapper for the WCSTools pix2wcs routine.
	 * @param handle The Java handle.
	 * @param xpix The X pixel position.
	 * @param ypix The Y pixel position.
	 * @return An instance of WCSToolsCoordinate containing the RA in decimal degrees in one param
	 *         and the Declination in decimal degrees in the other.
	 */
	protected static native WCSToolsCoordinate pix2wcs(WCSToolsWorldCoorHandle handle,double xpix,double ypix) throws Exception;
	/**
	 * Native wrapper for the WCSTools wcs2pix routine.
	 * @param handle The Java handle.
	 * @param xpos The X position, world coordinates in decimal degrees.
	 * @param ypos The Y position, world coordinates in decimal degrees.
	 * @return An instance of WCSToolsCoordinate containing the X pixel position in one param
	 *         and the Y pixel positionin the other.
	 */
	protected static native WCSToolsCoordinate wcs2pix(WCSToolsWorldCoorHandle handle,double xpos,double ypos) throws Exception;

	/**
	 * Method to open the specified FITS filename and retrieve WCS information from it.
	 * @param filename A string giving the filename of a FITS image with WCS information in it.
	 * @param verbosity An integer, the more positive the number the more verbosity.
	 * @return A new instance of WCSToolsWorldCoorHandle is returned, which maps to a 
	 *         wcstools 'struct WorldCoor'.
	 * @see #GetWCSFITS
	 */
	public static WCSToolsWorldCoorHandle getWCSFITS(String filename,int verbosity) throws Exception
	{
		return GetWCSFITS(filename,verbosity);
	}

	/**
	 * Method to free an opened handle.
	 * @param handle The Java object representing the handle.
	 * @see #wcsfree
	 */
	public static void wcsFree(WCSToolsWorldCoorHandle handle) throws Exception
	{
		wcsfree(handle);
	}

	/**
	 * Method to take a pixel position from the opened handle, and return a coordinate
	 * containing the WCS Position (RA/Dec) for that pixel position.
	 * @param handle The WCSTools handle, previously created from getWCSFITS.
	 * @param xpix The X pixel position.
	 * @param ypix The Y pixel position.
	 * @return A new instance of WCSToolsCoordinate is returned, the X coordinate containing the
	 *         RA in decimal degrees, and the Y coordinate containing the Declination in decimal degrees.
	 * @see #getWCSFITS
	 * @see #pix2wcs
	 */
	public static WCSToolsCoordinate pixelToWCS(WCSToolsWorldCoorHandle handle,double xpix,double ypix) 
		throws Exception
	{
		return pix2wcs(handle,xpix,ypix);
	}

	/**
	 * Method to take an RA and Dec (in decimal degrees), and useing the transform in the opened handle
	 * return an instance of WCSToolsCoordinate containing that position's X and Y pixel location of the
	 * opened FITS image.
	 * @param handle The WCSTools handle, previously created from getWCSFITS.
	 * @param xpos The RA position, in decimal degrees.
	 * @param ypos The Declination position, in decimal degrees.
	 * @return A new instance of WCSToolsCoordinate is returned, the X coordinate containing the
	 *         X pixel position, and the Y coordinate containing the Y pixel position.
	 * @see #getWCSFITS
	 * @see #wcs2pix
	 */
	public static WCSToolsCoordinate wcsToPixel(WCSToolsWorldCoorHandle handle,double xpos,double ypos) 
		throws Exception
	{
		return wcs2pix(handle,xpos,ypos);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2007/09/27 15:35:59  cjm
// Initial revision
//
//
