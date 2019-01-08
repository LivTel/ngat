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
// GUIDialogUnmanager.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIDialogUnmanager.java,v 0.3 2006-05-16 18:15:25 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * The GUIDialogUnmanager is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It unmanages a JDialog. This is needed when the dialog is unmanaged from a menu item, as otherwise
 * the menu-bar the item is on complains about events from unmanaged components.
 * @author Chris Mottram
 * @version $Revision$
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUIDialogUnmanager implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The Swing JDialog to unmanage.
	 */
	private JDialog dialog = null;

	/**
	 * Constructor.
	 * @param d The dialog to unmanage.
	 */
	public GUIDialogUnmanager(JDialog d)
	{
		dialog = d;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls dialog.setVisible(false)
	 * to unmanage the dialog.
	 * @see #dialog
	 */
	public void run()
	{
		dialog.setVisible(false);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.2  1999/11/29 11:46:40  cjm
// Changed package to ngat.swing.
//
// Revision 0.1  1999/11/29 11:44:21  cjm
// initial revision.
//
//
