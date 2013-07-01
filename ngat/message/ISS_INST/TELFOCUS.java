/* TELFOCUS.java */
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
 * This command requires the instrument to issue telescope SET_FOCUS commands
 * in order to attempt to focus the telescope. A frame is taken at several focus positions,
 * and the real time pipeline is run in the frames to measure the f.w.h.m. (seeing) of each frame.
 * A chi-squared fit is applied to the resulting data to find the optimum focus, and best seeing.
 * <h2>Parameters:</h2>
 * <ul>
 * <li><b><i>float</i> startFocus</b> (in mm)
 * <li><b><i>float</i> endFocus</b> (in mm)
 * <li><b><i>float</i> step</b> (in mm)
 * <li><b><i>int</i> exposureTime</b> (in milliseconds)
 * </ul>
 * <h2>Done Parameters:</h2>
 * <ul>
 * <li><b><i>float</i> seeing</b> (fwhm in arcsec)
 * <li><b><i>float</i> currentFocus</b> (in mm)
 * <li><b><i>double</i> a</b> (<b>a</b> term in the equation <i>ax<sup>2</sup>+bx+c</i> generated by chi-squared fit)
 * <li><b><i>double</i> b</b> (<b>b</b> term in the equation <i>ax<sup>2</sup>+bx+c</i> generated by chi-squared fit)
 * <li><b><i>double</i> c</b> (<b>c</b> term in the equation <i>ax<sup>2</sup>+bx+c</i> generated by chi-squared fit)
 * <li><b><i>double</i> chiSquared</b> (The best chi-squared value that resulted in the parameter set.)
 * </ul>
 */

public class TELFOCUS extends SETUP implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: TELFOCUS.java,v 1.1 2013-07-01 09:14:14 cjm Exp $");

/* ATTRIBUTES */
	/**
	 * Attribute startFocus of type float.
	 */
	private float startFocus;
	/**
	 * Attribute endFocus of type float.
	 */
	private float endFocus;
	/**
	 * Attribute step of type float.
	 */
	private float step;
	/**
	 * Attribute exposureTime of type int.
	 */
	private int exposureTime;

/* ATTRIBUTE ACCESSORS */
	/**
	 * Method to set startFocus.
	 * @param in The value to set, of type float.
	 */
	public void setStartFocus(float in)
	{
		this.startFocus=in;
	}

	/**
	 * Method to get startFocus.
	 * @return The method returns the attribute startFocus.
	 */
	public float getStartFocus()
	{
		return this.startFocus;
	}

	/**
	 * Method to set endFocus.
	 * @param in The value to set, of type float.
	 */
	public void setEndFocus(float in)
	{
		this.endFocus=in;
	}

	/**
	 * Method to get endFocus.
	 * @return The method returns the attribute endFocus.
	 */
	public float getEndFocus()
	{
		return this.endFocus;
	}

	/**
	 * Method to set step.
	 * @param in The value to set, of type float.
	 */
	public void setStep(float in)
	{
		this.step=in;
	}

	/**
	 * Method to get step.
	 * @return The method returns the attribute step.
	 */
	public float getStep()
	{
		return this.step;
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
	 * Default constructor for TELFOCUS.
	 * @param id The message id.
	 */
	public TELFOCUS(String id)
	{
		super(id);
	}
}
