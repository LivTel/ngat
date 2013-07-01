/* EXPOSE_REDUCE_DONE.java */
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
package ngat.message.INST_DP;

import ngat.message.base.*;

import java.util.*;
import java.io.*;
/** 
 * This command is the reduce commands for all exposure FITS files.
 *
 * <h2>Command Parameters:</h2>
 * <ul>
 * <li><b><i>String</i> filename</b> (inherited from REDUCE).
 * <li><b><i>boolean</i> wcsFit</b> Whether to try to WCS fit the reduced image (used for acquisition).
 * </ul>
 * <h2>Done Parameters:</h2>
 * <ul>
 * <li><b><i>String</i> filename</b> (inherited from REDUCE).
 * <li><b><i>float</i> seeing</b> (in arcsec) (for the brightest object in the frame).
 * <li><b><i>float</i> counts</b> (0 .. 65535) (for the brightest object in the frame).
 * <li><b><i>float</i> xpix</b> (pixels) (for the brightest object in the frame).
 * <li><b><i>float</i> ypix</b> (pixels) (for the brightest object in the frame).
 * <li><b><i>float</i> photometricity</b> (mags of extinction) (only for standards).
 * <li><b><i>float</i> skyBrightness</b> (mags/arcsec&#178;) (estimate of sky brightness).
 * <li><b><i>boolean</i> saturation</b> (true if the frame was saturated).
 * </ul>
 * @see EXPOSE_REDUCE_DONE
 */ 

public class EXPOSE_REDUCE_DONE extends REDUCE_DONE implements Serializable
{

/* DONE CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: EXPOSE_REDUCE_DONE.java,v 1.1 2013-07-01 08:59:00 cjm Exp $");

/* DONE ATTRIBUTES */
	/**
	 * Attribute seeing of type float.
	 */
	private float seeing;
	/**
	 * Attribute counts of type float.
	 */
	private float counts;
	/**
	 * Attribute xpix of type float.
	 */
	private float xpix;
	/**
	 * Attribute ypix of type float.
	 */
	private float ypix;
	/**
	 * Attribute photometricity of type float.
	 */
	private float photometricity;
	/**
	 * Attribute skyBrightness of type float.
	 */
	private float skyBrightness;
	/**
	 * Attribute saturation of type boolean.
	 */
	private boolean saturation;

/* DATTRIBUTE ACCESSORS */
	/**
	 * Method to set seeing.
	 * @param in The value to set, of type float.
	 */
	public void setSeeing(float in)
	{
		this.seeing=in;
	}

	/**
	 * Method to get seeing.
	 * @return The method returns the attribute seeing.
	 */
	public float getSeeing()
	{
		return this.seeing;
	}

	/**
	 * Method to set counts.
	 * @param in The value to set, of type float.
	 */
	public void setCounts(float in)
	{
		this.counts=in;
	}

	/**
	 * Method to get counts.
	 * @return The method returns the attribute counts.
	 */
	public float getCounts()
	{
		return this.counts;
	}

	/**
	 * Method to set xpix.
	 * @param in The value to set, of type float.
	 */
	public void setXpix(float in)
	{
		this.xpix=in;
	}

	/**
	 * Method to get xpix.
	 * @return The method returns the attribute xpix.
	 */
	public float getXpix()
	{
		return this.xpix;
	}

	/**
	 * Method to set ypix.
	 * @param in The value to set, of type float.
	 */
	public void setYpix(float in)
	{
		this.ypix=in;
	}

	/**
	 * Method to get ypix.
	 * @return The method returns the attribute ypix.
	 */
	public float getYpix()
	{
		return this.ypix;
	}

	/**
	 * Method to set photometricity.
	 * @param in The value to set, of type float.
	 */
	public void setPhotometricity(float in)
	{
		this.photometricity=in;
	}

	/**
	 * Method to get photometricity.
	 * @return The method returns the attribute photometricity.
	 */
	public float getPhotometricity()
	{
		return this.photometricity;
	}

	/**
	 * Method to set skyBrightness.
	 * @param in The value to set, of type float.
	 */
	public void setSkyBrightness(float in)
	{
		this.skyBrightness=in;
	}

	/**
	 * Method to get skyBrightness.
	 * @return The method returns the attribute skyBrightness.
	 */
	public float getSkyBrightness()
	{
		return this.skyBrightness;
	}

	/**
	 * Method to set saturation.
	 * @param in The value to set, of type boolean.
	 */
	public void setSaturation(boolean in)
	{
		this.saturation=in;
	}

	/**
	 * Method to get saturation.
	 * @return The method returns the attribute saturation.
	 */
	public boolean getSaturation()
	{
		return this.saturation;
	}


/* CONSTRUCTOR */
	/**
	 * Default constructor for EXPOSE_REDUCE_DONE.
	 * @param id The message id.
	 */
	public EXPOSE_REDUCE_DONE(String id)
	{
		super(id);
	}
}