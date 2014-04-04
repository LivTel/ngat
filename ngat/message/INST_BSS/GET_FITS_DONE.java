/* GET_FITS_DONE.java */
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
package ngat.message.INST_BSS;

import ngat.message.base.*;

import java.util.*;
import java.io.*;
/** 
 * On receiving this command the BSS will return a set of FITS headers reflecting
 * the current status of elements within the Beam Steering System.
 * <h2>Command Parameters:</h2>
 * </ul>
 * <h2>Done Parameters:</h2>
 * <ul>
 * <h2>Done Parameters:</h2>
 * <ul>
 * <li><b><i>java.util.Vector</i> fitsHeader</b> A vector containing the FITS headers.
 * </ul>
 * @see INST_TO_BSS
 */ 

public class GET_FITS_DONE extends INST_TO_BSS_DONE implements Serializable
{

/* DONE CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* DONE ATTRIBUTES */
	/**
	 * Attribute fitsHeader of type java.util.Vector.
	 */
	private java.util.Vector fitsHeader;

/* DATTRIBUTE ACCESSORS */
	/**
	 * Method to set fitsHeader.
	 * @param in The value to set, of type java.util.Vector.
	 */
	public void setFitsHeader(java.util.Vector in)
	{
		this.fitsHeader=in;
	}

	/**
	 * Method to get fitsHeader.
	 * @return The method returns the attribute fitsHeader.
	 */
	public java.util.Vector getFitsHeader()
	{
		return this.fitsHeader;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for GET_FITS_DONE.
	 * @param id The message id.
	 */
	public GET_FITS_DONE(String id)
	{
		super(id);
	}
}
