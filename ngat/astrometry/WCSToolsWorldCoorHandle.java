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
// WCSToolsWorldCoorHandle.java
// $Header: /space/home/eng/cjm/cvs/ngat/astrometry/WCSToolsWorldCoorHandle.java,v 1.1 2013-06-28 10:11:49 cjm Exp $
package ngat.astrometry;

import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Instances of this class are used as Handles when calling WCSTools routines. The class itself extends Object
 * but has no new fields or methods! When instansiated, the objects reference is used in the JNI code to
 * map to a 'struct WorldCoor*' instance, which is the C layer handle used for accessing WCSTools routines.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class WCSToolsWorldCoorHandle extends Object
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: WCSToolsWorldCoorHandle.java,v 1.1 2013-06-28 10:11:49 cjm Exp $");

	/**
	 * Default null constructor.
	 */
	public WCSToolsWorldCoorHandle()
	{
		super();
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2007/09/27 15:35:59  cjm
// Initial revision
//
//


