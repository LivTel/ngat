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
// GUILabelSetter.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUILabelSetter.java,v 0.4 2006-05-16 18:15:25 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * The GUILabelSetter is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It sets a label's text. This is needed as updating <b>must</b> be done
 * in the Swing thread.
 * @author Chris Mottram
 * @version $Revision$
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUILabelSetter implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The Swing JLabel to append to.
	 */
	private JLabel label = null;
	/**
	 * The text to set.
	 */
	private String string = null;

	/**
	 * Constructor.
	 * @param l The label to append text to.
	 * @param s The string to set the label to.
	 */
	public GUILabelSetter(JLabel l,String s)
	{
		label = l;
		string = s;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls label.setText(string)
	 * to append the text to the text area.
	 * @see #label
	 * @see #string
	 */
	public void run()
	{
		label.setText(string);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.3  1999/11/29 11:40:01  cjm
// Added package statement.
//
// Revision 0.2  1999/11/24 12:50:41  cjm
// Changed @see clause.
//
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//
