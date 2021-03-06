/* REDUCE_DONE.java */
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
 * This command is the superset of all reduction commands. A reduction always takes in a source FITS file,
 * processes it and returns the processed file.
 * <pre>
 * Command Parameters:  String filename (filename of FITS file to process).
 *
 * Done Parameters:  String filename (generated filename)
 * </pre>
 */ 

public class REDUCE_DONE extends INST_TO_DP_DONE implements Serializable
{

/* DONE CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* DONE ATTRIBUTES */
	/**
	 * Attribute filename of type String.
	 */
	private String filename;

/* DATTRIBUTE ACCESSORS */
	/**
	 * Method to set filename.
	 * @param in The value to set, of type String.
	 */
	public void setFilename(String in)
	{
		this.filename=in;
	}

	/**
	 * Method to get filename.
	 * @return The method returns the attribute filename.
	 */
	public String getFilename()
	{
		return this.filename;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for REDUCE_DONE.
	 * @param id The message id.
	 */
	public REDUCE_DONE(String id)
	{
		super(id);
	}
}
