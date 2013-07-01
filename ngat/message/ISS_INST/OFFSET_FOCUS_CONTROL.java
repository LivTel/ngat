/* OFFSET_FOCUS_CONTROL.java */
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
 * This is a subclass of OFFSET_FOCUS. It allows an instrument to offset the telescope focus.
 * In this case the telescope focus is controlled by a FOCUS_CONTROL(instrument), and so
 * OFFSET_FOCUS_CONTROL allows the specification of the instruments name, which determines
 * whether the ISS actually applies the specified focus offset, or ignores it.
 * <h2>Command Parameters:</h2>
 * Inherited from OFFSET_FOCUS.
 * <h2>Done Parameters:</h2>
 * Inherited from OFFSET_FOCUS.
 */

public class OFFSET_FOCUS_CONTROL extends OFFSET_FOCUS implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: OFFSET_FOCUS_CONTROL.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

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
	 * Default constructor for OFFSET_FOCUS_CONTROL.
	 * @param id The message id.
	 */
	public OFFSET_FOCUS_CONTROL(String id)
	{
		super(id);
	}
}
