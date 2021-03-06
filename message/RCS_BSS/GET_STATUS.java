/* GET_STATUS.java */
/* FILE GENERATED BY makeamess - DO NOT EDIT */

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
package ngat.message.RCS_BSS;

import ngat.message.base.*;

import java.util.*;
import java.io.*;
/**
 * Command to get some status information from the Beam Steering System.
 * <h2>Command Parameters:</h2>
 * Inherited from RCS_TO_BSS.
 * <h2>Done Parameters:</h2>
 * <ul>
 * <li><b><i>java.util.Hashtable</i> statusData</b> A hashtable containing keyword/values describing the status
 * of the Beam Steering System and mechanisms under it's control.
 * </ul>
 */

public class GET_STATUS extends RCS_TO_BSS implements Serializable
{

/* CONSTANTS */

/* ATTRIBUTES */

/* ATTRIBUTE ACCESSORS */

/* CONSTRUCTOR */
	/**
	 * Default constructor for GET_STATUS.
	 * @param id The message id.
	 */
	public GET_STATUS(String id)
	{
		super(id);
	}
}
