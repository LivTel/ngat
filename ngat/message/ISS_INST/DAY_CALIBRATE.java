/* DAY_CALIBRATE.java */
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
 * This command takes a series of calibration frames, without opening the shutter.
 * These calibrations are usually performed during the day.
 * They consist of a specified set of BIAS and DARK frames, using a specified set of instrument configurations
 * (binning values) and exposure length's (for DARK frames).
 * The command will terminate when it has no more time to complete more BIAS/DARKS, or all of them have been 
 * completed.
 */

public class DAY_CALIBRATE extends CALIBRATE implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: DAY_CALIBRATE.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute timeToComplete of type long.
	 */
	private long timeToComplete;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set timeToComplete.
	 * @param in The value to set, of type long.
	 */
	public void setTimeToComplete(long in)
	{
		this.timeToComplete=in;
	}

	/**
	 * Method to get timeToComplete.
	 * @return The method returns the attribute timeToComplete.
	 */
	public long getTimeToComplete()
	{
		return this.timeToComplete;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for DAY_CALIBRATE.
	 * @param id The message id.
	 */
	public DAY_CALIBRATE(String id)
	{
		super(id);
	}
}
