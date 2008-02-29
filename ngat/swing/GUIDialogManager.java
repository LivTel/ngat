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
// GUIDialogManager.java
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIDialogManager.java,v 1.2 2008-02-29 14:47:26 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import ngat.util.logging.*;

/**
 * The GUIDialogManager is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It manages a Window (JDialog and JFrame are subclasses thereof). 
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUIDialogManager implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUIDialogManager.java,v 1.2 2008-02-29 14:47:26 cjm Exp $");
	/**
	 * The awt Window to manage.
	 */
	private Window window = null;
	/**
	 * The logger to log to.
	 */
	private Logger logger = null;

	/**
	 * Constructor.
	 * @param w The window to pack/manage.
	 */
	public GUIDialogManager(Window w)
	{
		window = w;
		logger = LogManager.getLogger(this);
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls window.pack() and window.setVisible(true)
	 * to manage the dialog.
	 * @see #window
	 * @see #logger
	 * @see java.awt.Window#pack
	 * @see java.awt.Window#setVisible
	 */
	public void run()
	{
		logger.log(1,this.getClass().getName()+":run:Window pack.");
		window.pack();
		logger.log(1,this.getClass().getName()+":run:Window setVisible.");
		window.setVisible(true);
		logger.log(1,this.getClass().getName()+":run:Finished.");
	}
}
//
// $Log: not supported by cvs2svn $
//
