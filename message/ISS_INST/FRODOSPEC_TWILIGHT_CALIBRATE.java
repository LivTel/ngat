/* FRODOSPEC_TWILIGHT_CALIBRATE.java */
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
 * This command should be issued when pointing at blank sky.
 * It will take a series of sky flats using a specified set of configurations.  It should calculate the
 * exposure time from the current local time, taking into account the
 * date, filter etc. and the supplied current telescope position.
 * The command will terminate when it has no more time to complete more skyflats.
 */

public class FRODOSPEC_TWILIGHT_CALIBRATE extends FRODOSPEC_CALIBRATE implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute telescopeAltitude of type float.
	 */
	private float telescopeAltitude;
	/**
	 * Attribute telescopeAzimuth of type float.
	 */
	private float telescopeAzimuth;
	/**
	 * Attribute timeToComplete of type long.
	 */
	private long timeToComplete;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set telescopeAltitude.
	 * @param in The value to set, of type float.
	 */
	public void setTelescopeAltitude(float in)
	{
		this.telescopeAltitude=in;
	}

	/**
	 * Method to get telescopeAltitude.
	 * @return The method returns the attribute telescopeAltitude.
	 */
	public float getTelescopeAltitude()
	{
		return this.telescopeAltitude;
	}

	/**
	 * Method to set telescopeAzimuth.
	 * @param in The value to set, of type float.
	 */
	public void setTelescopeAzimuth(float in)
	{
		this.telescopeAzimuth=in;
	}

	/**
	 * Method to get telescopeAzimuth.
	 * @return The method returns the attribute telescopeAzimuth.
	 */
	public float getTelescopeAzimuth()
	{
		return this.telescopeAzimuth;
	}

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
	 * Default constructor for FRODOSPEC_TWILIGHT_CALIBRATE.
	 * @param id The message id.
	 */
	public FRODOSPEC_TWILIGHT_CALIBRATE(String id)
	{
		super(id);
	}
}