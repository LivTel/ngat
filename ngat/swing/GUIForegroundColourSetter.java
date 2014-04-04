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
// GUIForegroundColourSetter.java 
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIForegroundColourSetter.java,v 1.1 2008-04-29 14:56:24 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import java.awt.Color;
import javax.swing.*;

/**
 * The GUIForegroundColourSetter is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It sets a component's foreground colour. This is needed as updating <b>must</b> be done in the Swing thread.
 * @author Chris Mottram
 * @version $Revision$
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUIForegroundColourSetter implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The Swing JComponent to append to.
	 */
	private JComponent component = null;
	/**
	 * The colour to set the component's foreground to.
	 */
	private Color colour = null;

	/**
	 * Constructor.
	 * @param j The component to change the foreground colour of.
	 * @param c The colour to set the component's foreground to.
	 */
	public GUIForegroundColourSetter(JComponent j,Color c)
	{
		component = j;
		colour = c;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls component.setForeground(colour)
	 * to set the component's foreground colour.
	 * @see #component
	 * @see #colour
	 */
	public void run()
	{
		component.setForeground(colour);
	}
}
//
// $Log: not supported by cvs2svn $
//
