/* MOVE_FOLD.java */
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
 * Move the fold mirror in/out of the beam and/or to a given cardinal
 * point.  An instrument should issue this command to ensure
 * that the fold mirror is in the correct position for the
 * exposure.
 * <pre>
 * Command Parameters:  int Position 
 *
 * where Position = 0 - 4 defined as
 *
 * 0 = OUT_OF_BEAM
 * 1 = PORT_1
 * 2 = PORT_2
 * 3 = PORT_3
 * 4 = PORT_4
 *
 * Done Parameters:  None
 * </pre>
 */

public class MOVE_FOLD extends INST_TO_ISS implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute mirror_position of type int.
	 */
	private int mirror_position;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set mirror_position.
	 * @param in The value to set, of type int.
	 */
	public void setMirror_position(int in)
	{
		this.mirror_position=in;
	}

	/**
	 * Method to get mirror_position.
	 * @return The method returns the attribute mirror_position.
	 */
	public int getMirror_position()
	{
		return this.mirror_position;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for MOVE_FOLD.
	 * @param id The message id.
	 */
	public MOVE_FOLD(String id)
	{
		super(id);
	}
}
