/* GET_FITS.java */
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

public class GET_FITS extends INST_TO_BSS implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GET_FITS.java,v 1.1 2013-07-01 08:57:31 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute instrumentName of type java.lang.String.
	 */
	private java.lang.String instrumentName;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set instrumentName.
	 * @param in The value to set, of type java.lang.String.
	 */
	public void setInstrumentName(java.lang.String in)
	{
		this.instrumentName=in;
	}

	/**
	 * Method to get instrumentName.
	 * @return The method returns the attribute instrumentName.
	 */
	public java.lang.String getInstrumentName()
	{
		return this.instrumentName;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for GET_FITS.
	 * @param id The message id.
	 */
	public GET_FITS(String id)
	{
		super(id);
	}
}
