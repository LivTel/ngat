/* INST_TO_ISS_DONE.java */
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
 * Instrumnet to ISS commands base class
 */ 

public class INST_TO_ISS_DONE extends COMMAND_DONE implements Serializable
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
	 * Default constructor for INST_TO_ISS_DONE.
	 * @param id The message id.
	 */
	public INST_TO_ISS_DONE(String id)
	{
		super(id);
	}
}
