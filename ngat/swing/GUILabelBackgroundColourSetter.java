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
// GUILabelBackgroundColourSetter.java 
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUILabelBackgroundColourSetter.java,v 1.1 2008-04-28 17:23:56 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import java.awt.Color;
import javax.swing.*;

/**
 * The GUILabelBackgroundColourSetter is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It sets a label's background colour. This is needed as updating <b>must</b> be done in the Swing thread.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUILabelBackgroundColourSetter implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUILabelBackgroundColourSetter.java,v 1.1 2008-04-28 17:23:56 cjm Exp $");
	/**
	 * The Swing JLabel to append to.
	 */
	private JLabel label = null;
	/**
	 * The colour to set the label's background to.
	 */
	private Color colour = null;

	/**
	 * Constructor.
	 * @param l The label to append text to.
	 * @param c The colour to set the label's background to.
	 */
	public GUILabelBackgroundColourSetter(JLabel l,Color c)
	{
		label = l;
		colour = c;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls label.setBackground(colour)
	 * to set the label's background colour.
	 * @see #label
	 * @see #colour
	 */
	public void run()
	{
		label.setBackground(colour);
	}
}
//
// $Log: not supported by cvs2svn $
//
