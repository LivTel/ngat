/* EXPOSE.java */
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
 * This command is the superset of all exposure commands
 *
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>boolean</i> pipelineProcess</b> Whether to pass the generated images through the Data Pipeline.
 * <li><b><i>boolean</i> standard</b>Whether this exposure is a standard frame (of a standard star).
 * </ul>
 * <h2>Done Parameters:</h2>
 * <ul>
 * <li><b><i>String</i> filename</b> The FITS filename of the data.
 * <li><b><i>String</i> float</b> seeing</b> (in arcsec) FWHM of the brightest object in the frame.
 * <li><b><i>float</i> counts</b> (0 .. 65535) For the brightest object in the frame.
 * <li><b><i>float</i> xpix</b> (pixels) For the brightest object in the frame.
 * <li><b><i>float</i> ypix</b> (pixels) For the brightest object in the frame.
 * <li><b><i>float</i> photometricity</b> (mags of extinction) Only for standards.
 * <li><b><i>float</i> skyBrightness</b> (mags/arcsec&#178;) Estimate of sky brightness.
 * <li><b><i>boolean</i> saturation</b> true if the frame was saturated.
 * </ul>
 * @see ISS_TO_INST
 * @see EXPOSE_DONE
 */

public class EXPOSE extends ISS_TO_INST implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
	/**
	 * Attribute pipelineProcess of type boolean.
	 */
	private boolean pipelineProcess;
	/**
	 * Attribute standard of type boolean.
	 */
	private boolean standard;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set pipelineProcess.
	 * @param in The value to set, of type boolean.
	 */
	public void setPipelineProcess(boolean in)
	{
		this.pipelineProcess=in;
	}

	/**
	 * Method to get pipelineProcess.
	 * @return The method returns the attribute pipelineProcess.
	 */
	public boolean getPipelineProcess()
	{
		return this.pipelineProcess;
	}

	/**
	 * Method to set standard.
	 * @param in The value to set, of type boolean.
	 */
	public void setStandard(boolean in)
	{
		this.standard=in;
	}

	/**
	 * Method to get standard.
	 * @return The method returns the attribute standard.
	 */
	public boolean getStandard()
	{
		return this.standard;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for EXPOSE.
	 * @param id The message id.
	 */
	public EXPOSE(String id)
	{
		super(id);
	}
}