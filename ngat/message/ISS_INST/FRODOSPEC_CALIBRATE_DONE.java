/* FRODOSPEC_CALIBRATE_DONE.java */
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
 * This command is the superset of all calibrate commands for FrodoSpec.
 * These are commands that take calibration frames.
 *
 * <pre>
 * Command Parameters:  int arm (whether the calibration is to be done on the RED or BLUE arm)   
 *
 * Done Parameters:  (Inherited from CALIBRATE)
 *                   String filename (generated filename)
 *                   float peakCounts
 *                   float meanCounts  
 * </pre>
 */ 

public class FRODOSPEC_CALIBRATE_DONE extends CALIBRATE_DONE implements Serializable
{

/* DONE CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* DONE ATTRIBUTES */

/* DATTRIBUTE ACCESSORS */

/* CONSTRUCTOR */
	/**
	 * Default constructor for FRODOSPEC_CALIBRATE_DONE.
	 * @param id The message id.
	 */
	public FRODOSPEC_CALIBRATE_DONE(String id)
	{
		super(id);
	}
}
