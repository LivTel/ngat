/* BEAM_STEER.java */
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
 * On receiving this command the BSS will move it's mechanisms
 * according to the configuration contained therein.
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>ngat.phase2.XBeamSteeringConfig</i> beamConfig</b> The configuration to attain.
 * </ul>
 * <h2>Done Parameters:</h2>
 * Inherited from RCS_TO_BSS_DONE.
 * @see RCS_TO_BSS
 */

public class BEAM_STEER extends RCS_TO_BSS implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: BEAM_STEER.java,v 1.1 2013-07-01 09:26:43 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute beamConfig of type ngat.phase2.XBeamSteeringConfig.
	 */
	private ngat.phase2.XBeamSteeringConfig beamConfig;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set beamConfig.
	 * @param in The value to set, of type ngat.phase2.XBeamSteeringConfig.
	 */
	public void setBeamConfig(ngat.phase2.XBeamSteeringConfig in)
	{
		this.beamConfig=in;
	}

	/**
	 * Method to get beamConfig.
	 * @return The method returns the attribute beamConfig.
	 */
	public ngat.phase2.XBeamSteeringConfig getBeamConfig()
	{
		return this.beamConfig;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for BEAM_STEER.
	 * @param id The message id.
	 */
	public BEAM_STEER(String id)
	{
		super(id);
	}
}
