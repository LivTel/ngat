/* MULTRUN.java */
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
 * This command does a series of exposures, each of the same length.
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> numberExposures</b> The number of exposures to take.
 * <li><b><i>int</i> exposureTime</b> (ms) The length of each exposure, in milliseconds.
 * </ul>
 * Some parameters are inherited from EXPOSE.
 * <h2>Done Parameters:</h2>
 * These are inherited from EXPOSE_DONE.
 * @see EXPOSE
 * @see EXPOSE_DONE
 */

public class MULTRUN extends EXPOSE implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute numberExposures of type int.
	 */
	private int numberExposures;
	/**
	 * Attribute exposureTime of type int.
	 */
	private int exposureTime;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set numberExposures.
	 * @param in The value to set, of type int.
	 */
	public void setNumberExposures(int in)
	{
		this.numberExposures=in;
	}

	/**
	 * Method to get numberExposures.
	 * @return The method returns the attribute numberExposures.
	 */
	public int getNumberExposures()
	{
		return this.numberExposures;
	}

	/**
	 * Method to set exposureTime.
	 * @param in The value to set, of type int.
	 */
	public void setExposureTime(int in)
	{
		this.exposureTime=in;
	}

	/**
	 * Method to get exposureTime.
	 * @return The method returns the attribute exposureTime.
	 */
	public int getExposureTime()
	{
		return this.exposureTime;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for MULTRUN.
	 * @param id The message id.
	 */
	public MULTRUN(String id)
	{
		super(id);
	}
}
