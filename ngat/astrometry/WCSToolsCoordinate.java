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
// WCSToolsCoordinate.java
// $Header: /space/home/eng/cjm/cvs/ngat/astrometry/WCSToolsCoordinate.java,v 1.1 2013-06-28 10:11:49 cjm Exp $
package ngat.astrometry;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * This class wraps two doubles, allowing a coordinate return object to be generated from WCSTools methods.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class WCSToolsCoordinate
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: WCSToolsCoordinate.java,v 1.1 2013-06-28 10:11:49 cjm Exp $");
	/**
	 * The 'x' coordinate value.
	 */
	protected double xCoordinate;
	/**
	 * The 'y' coordinate value.
	 */
	protected double yCoordinate;

	/**
	 * Default null constructor.
	 */
	public WCSToolsCoordinate()
	{
		super();
	}

	/**
	 * Constructor.
	 * @param x The x coordinate value.
	 * @param y The y coordinate value.
	 * @see #xCoordinate
	 * @see #yCoordinate
	 */
	public WCSToolsCoordinate(double x, double y)
	{
		super();
		xCoordinate = x;
		yCoordinate = y;
	}

	/**
	 * Set the X coordinate.
	 * @param x The x coordinate value.
	 * @see #xCoordinate
	 */
	public void setXCoordinate(double x)
	{
		xCoordinate = x;
	}

	/**
	 * Get the X coordinate's value.
	 * @return The x coordinate value.
	 * @see #xCoordinate
	 */
	public double getXCoordinate()
	{
		return xCoordinate;
	}

	/**
	 * Set the Y coordinate.
	 * @param y The y coordinate value.
	 * @see #yCoordinate
	 */
	public void setYCoordinate(double y)
	{
		yCoordinate = y;
	}

	/**
	 * Get the Y coordinate's value.
	 * @return The y coordinate value.
	 * @see #yCoordinate
	 */
	public double getYCoordinate()
	{
		return yCoordinate;
	}

	/**
	 * Return a string describing the coordinates.
	 * @return A string of the form "x = <value>,y = <value>".
	 * @see #toString(java.lang.String)
	 */
	public String toString()
	{
		return toString("");
	}

	/**
	 * Return a string describing the coordinates.
	 * @return A string of the form prefix+"x = <value>,y = <value>".
	 * @see #xCoordinate
	 * @see #yCoordinate
	 */
	public String toString(String prefix)
	{
		return new String(prefix+"x = "+xCoordinate+", y = "+yCoordinate);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2007/09/27 15:35:59  cjm
// Initial revision
//
//

