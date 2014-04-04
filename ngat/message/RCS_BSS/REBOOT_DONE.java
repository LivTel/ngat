/* REBOOT_DONE.java */
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
 * This command caused the instrument to ``reboot'', restarting
 * software tasks and leaving the instrument hardware in
 * a defined state.
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> level</b> The level of reboot, one of:
 *     <ul>
 *     <li>1  = REDATUM
 *     <li>2  = SOFTWARE
 *     <li>3  = HARDWARE
 *     <li>4  = POWER_OFF
 *     </ul>
 * </ul>
 * <h2>Done Parameters:</h2>
 * This command may not return a DONE reply, depending on how the reboot is implemented.
 */ 

public class REBOOT_DONE extends RCS_TO_BSS_DONE implements Serializable
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
	 * Default constructor for REBOOT_DONE.
	 * @param id The message id.
	 */
	public REBOOT_DONE(String id)
	{
		super(id);
	}
}
