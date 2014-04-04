/* OFFSET_TT.java */
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
 * This class is the superclass of tiptilt offset commands from the RCS to BSS.
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>String </i> instrumentName</b> The name of the instrument to do the offset for.
 * <li><b><i>int </i> tiptiltId</b> Which tiptilt to offset.
 * </ul>
 * <h2>Done Parameters:</h2>
 * None
 */

public class OFFSET_TT extends RCS_TO_BSS implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Constant TIPTILT_ID_TOP of type int with value 1.
	 */
	public final static int TIPTILT_ID_TOP = 1;
	/**
	 * Constant TIPTILT_ID_BOTTOM of type int with value 2.
	 */
	public final static int TIPTILT_ID_BOTTOM = 2;

/* ATTRIBUTES */
	/**
	 * Attribute instrumentName of type java.lang.String.
	 */
	private java.lang.String instrumentName;
	/**
	 * Attribute tiptiltId of type int.
	 */
	private int tiptiltId;

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

	/**
	 * Method to set tiptiltId.
	 * @param in The value to set, of type int.
	 */
	public void setTiptiltId(int in)
	{
		this.tiptiltId=in;
	}

	/**
	 * Method to get tiptiltId.
	 * @return The method returns the attribute tiptiltId.
	 */
	public int getTiptiltId()
	{
		return this.tiptiltId;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for OFFSET_TT.
	 * @param id The message id.
	 */
	public OFFSET_TT(String id)
	{
		super(id);
	}
}
