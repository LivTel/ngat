/* SET_LOGGING.java */
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
package ngat.message.ISS_INST;

import ngat.message.base.*;

import java.util.*;
import java.io.*;
/**
 * Set logging level (logs to kept locally on the instrument
 * computer)
 *
 * <pre>
 *
 * Command Parameters:   int level where level is defined something like
 *
 * 1 = COMMANDS
 * 2 = SOFTWARE
 * 4 = HARDWARE
 *
 * and these can be combined with an OR statement so 7 = EVERYTHING
 *
 * Done Parameters:  None
 *
 * </pre>
 */

public class SET_LOGGING extends SETUP implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SET_LOGGING.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute level of type int.
	 */
	private int level;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set level.
	 * @param in The value to set, of type int.
	 */
	public void setLevel(int in)
	{
		this.level=in;
	}

	/**
	 * Method to get level.
	 * @return The method returns the attribute level.
	 */
	public int getLevel()
	{
		return this.level;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for SET_LOGGING.
	 * @param id The message id.
	 */
	public SET_LOGGING(String id)
	{
		super(id);
	}
}
