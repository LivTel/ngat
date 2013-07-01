/* FILENAME_ACK.java */
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
 * During execution of commands that produce multiple FITS files an ACK of a sub-class of this class will
 * be returned to the client after each frame has been saved to disk, for real time GUIs to display the frame.
 * It will be returned to the calling process using the Multiple Acknowledge protocol.
 * <h2>Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> timeToComplete</b> (in ms) (inherited from ACK)
 * <li><b><i>String</i> filename</b> (of raw data before Data Pipeline)
 * </ul>
 */

public class FILENAME_ACK extends ACK implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FILENAME_ACK.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute filename of type String.
	 */
	private String filename;

/* ATTRIBUTE ACCESSORS */
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
	 * Default constructor for FILENAME_ACK.
	 * @param id The message id.
	 */
	public FILENAME_ACK(String id)
	{
		super(id);
	}
}
