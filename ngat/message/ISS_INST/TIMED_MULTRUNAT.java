/* TIMED_MULTRUNAT.java */
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
 * This command does a series of exposures, each of the same length, starting at a specified time.
 * Exposures are taken until a given total duration has elapsed.
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>Date</i> startTime</b> A date representing the time to start taking exposures.
 * <li><b><i>int</i> totalDuration</b> The total length of time to do exposures for, in milliseconds.
 * <li><b><i>int</i> exposureTime</b> (ms) The length of each exposure, in milliseconds.
 * </ul>
 * Some parameters are inherited from EXPOSE.
 * <h2>Done Parameters:</h2>
 * These are inherited from EXPOSE_DONE.
 * @see EXPOSE
 * @see EXPOSE_DONE
 */

public class TIMED_MULTRUNAT extends EXPOSE implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute startTime of type java.util.Date.
	 */
	private java.util.Date startTime;
	/**
	 * Attribute totalDuration of type int.
	 */
	private int totalDuration;
	/**
	 * Attribute exposureTime of type int.
	 */
	private int exposureTime;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set startTime.
	 * @param in The value to set, of type java.util.Date.
	 */
	public void setStartTime(java.util.Date in)
	{
		this.startTime=in;
	}

	/**
	 * Method to get startTime.
	 * @return The method returns the attribute startTime.
	 */
	public java.util.Date getStartTime()
	{
		return this.startTime;
	}

	/**
	 * Method to set totalDuration.
	 * @param in The value to set, of type int.
	 */
	public void setTotalDuration(int in)
	{
		this.totalDuration=in;
	}

	/**
	 * Method to get totalDuration.
	 * @return The method returns the attribute totalDuration.
	 */
	public int getTotalDuration()
	{
		return this.totalDuration;
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
	 * Default constructor for TIMED_MULTRUNAT.
	 * @param id The message id.
	 */
	public TIMED_MULTRUNAT(String id)
	{
		super(id);
	}
}
