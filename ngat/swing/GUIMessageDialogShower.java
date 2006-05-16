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
// GUIMessageDialogShower.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIMessageDialogShower.java,v 1.2 2006-05-16 18:15:26 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import java.awt.*;
import javax.swing.*;

/**
 * The GUIMessageDialogShower is Runnable. It is used as an argument to SwingUtilities.invokeLater or
 * SwingUtilities.invokeAndWait.
 * It shows a message dialog. This is needed as updating <b>must</b> be done
 * in the Swing thread.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 * @see javax.swing.JOptionPane#showMessageDialog
 */
public class GUIMessageDialogShower implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUIMessageDialogShower.java,v 1.2 2006-05-16 18:15:26 cjm Exp $");
	/**
	 * Parent component to pass to the message dialog.
	 */
	private Component parentComponent = null;
	/**
	 * Message to pass to the message dialog.
	 */
	private Object message = null;
	/**
	 * Dialog title to pass to the message dialog.
	 */
	private String title = null;
	/**
	 * Type of message to pass to the message dialog.
	 */
	private int messageType = 0;

	/**
	 * Constructor.
	 * @param p The parent component. Determines the Frame in which the dialog is displayed. 
	 * 	If null, or if the parentComponent has no Frame, a default Frame is used.
	 * @param m The message. The Object to display
	 * @param t The title. The title string for the dialog.
	 * @param mt The message type. The type of message to be displayed: ERROR_MESSAGE, INFORMATION_MESSAGE, 
	 * WARNING_MESSAGE, QUESTION_MESSAGE, or PLAIN_MESSAGE (from JOptionPane).
	 */
	public GUIMessageDialogShower(Component p,Object m,String t,int mt)
	{
		parentComponent = p;
		message = m;
		title = t;
		messageType = mt;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls the static showMessageDialog
	 * to show the dialog.
	 * @see javax.swing.JOptionPane#showMessageDialog
	 */
	public void run()
	{
		JOptionPane.showMessageDialog(parentComponent,message,title,messageType);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  1999/12/02 17:48:40  cjm
// Initial revision
//
//
