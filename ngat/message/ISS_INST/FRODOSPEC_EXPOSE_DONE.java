/* FRODOSPEC_EXPOSE_DONE.java */
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
 * This command is the superset of all exposure commands for FrodoSpec.
 *
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> arm</b>Which arm to do the exposure on (RED_ARM or BLUE_ARM, see ngat.phase2.FrodoSpecConfig).
 * </ul>
 * Inherited from EXPOSE:
 * <ul>
 * <li><b><i>boolean</i> pipelineProcess</b> Whether to pass the generated images through the Data Pipeline.
 * <li><b><i>boolean</i> standard</b>Whether this exposure is a standard frame (of a standard star).
 * </ul>
 * <h2>Done Parameters:</h2>
 * Inherited from EXPOSE:
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
 * @see EXPOSE
 * @see EXPOSE_DONE
 */ 

public class FRODOSPEC_EXPOSE_DONE extends EXPOSE_DONE implements Serializable
{

/* DONE CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FRODOSPEC_EXPOSE_DONE.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* DONE ATTRIBUTES */

/* DATTRIBUTE ACCESSORS */

/* CONSTRUCTOR */
	/**
	 * Default constructor for FRODOSPEC_EXPOSE_DONE.
	 * @param id The message id.
	 */
	public FRODOSPEC_EXPOSE_DONE(String id)
	{
		super(id);
	}
}
