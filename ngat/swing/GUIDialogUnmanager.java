// GUIDialogUnmanager.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIDialogUnmanager.java,v 0.2 1999-11-29 11:46:40 cjm Exp $
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
 * @version $Revision: 0.2 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUIDialogUnmanager implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUIDialogUnmanager.java,v 0.2 1999-11-29 11:46:40 cjm Exp $");
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
// Revision 0.1  1999/11/29 11:44:21  cjm
// initial revision.
//
//
