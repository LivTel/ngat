/* EXPOSE_DP_ACK.java */
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
 * During execution of a command which produces multiple FITS images with sources on them an ACK of this class will
 * be returned to the client after each frame has been pipeline processed (using EXPOSE_REDUCE), 
 * for real time GUIs to display the processed frame.
 * <h2>Parameters:</h2>
 * <ul>
 * <li><b><i>int</i> timeToComplete</b> (in ms) Inherited from ACK.
 * <li><b><i>String</i> filename</b> Filename of pipeline processed data (inherited from FILENAME_ACK).
 * <li><b><i>float</i> seeing</b> (in arcsec) For the brightest object in the frame.
 * <li><b><i>float</i> counts</b> (0 .. 65535) For the brightest object in the frame.
 * <li><b><i>float</i> xpix</b> (pixels) For the brightest object in the frame.
 * <li><b><i>float</i> ypix</b> (pixels) For the brightest object in the frame.
 * <li><b><i>float</i> photometricity</b> (mags of extinction) Only for standards.
 * <li><b><i>float</i> skyBrightness</b> (mags/arcsec&#178;) Estimate of sky brightness.
 * <li><b><i>boolean</i> saturation</b> true if the frame was saturated.
 * </ul>
 * @see FILENAME_ACK
 * @see EXPOSE_REDUCE
 */

public class EXPOSE_DP_ACK extends FILENAME_ACK implements Serializable
{

/* CONSTANTS */
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");

/* ATTRIBUTES */
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

/* ATTRIBUTE ACCESSORS */
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
	 * Default constructor for EXPOSE_DP_ACK.
	 * @param id The message id.
	 */
	public EXPOSE_DP_ACK(String id)
	{
		super(id);
	}
}
