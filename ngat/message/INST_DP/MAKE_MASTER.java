/* MAKE_MASTER.java */
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
package ngat.message.INST_DP;

import ngat.message.base.*;

import java.util.*;
import java.io.*;
/**
 * This command is the super-class of all commands that create master versions of data, from a directory
 * of reduced data frames.
 * <pre>
 * Command Parameters:  String dirname (directory of FITS files to process).
 *
 * Done Parameters:  None
 * </pre>
 */

public class MAKE_MASTER extends INST_TO_DP implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute dirname of type String.
	 */
	private String dirname;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set dirname.
	 * @param in The value to set, of type String.
	 */
	public void setDirname(String in)
	{
		this.dirname=in;
	}

	/**
	 * Method to get dirname.
	 * @return The method returns the attribute dirname.
	 */
	public String getDirname()
	{
		return this.dirname;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for MAKE_MASTER.
	 * @param id The message id.
	 */
	public MAKE_MASTER(String id)
	{
		super(id);
	}
}
