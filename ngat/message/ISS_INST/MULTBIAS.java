/* MULTBIAS.java */
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
 * This command should take a number of zero exposures (bias frames).
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> numberExposures</b> The number of exposures to take.
 * </ul>
 * Some parameters are inherited from CALIBRATE.
 * <h2>Done Parameters:</h2>
 * These are inherited from CALIBRATE_DONE.
 * </pre>
 */

public class MULTBIAS extends CALIBRATE implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: MULTBIAS.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute numberExposures of type int.
	 */
	private int numberExposures;

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


/* CONSTRUCTOR */
	/**
	 * Default constructor for MULTBIAS.
	 * @param id The message id.
	 */
	public MULTBIAS(String id)
	{
		super(id);
	}
}
