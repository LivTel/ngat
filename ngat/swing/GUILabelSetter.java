// GUILabelSetter.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUILabelSetter.java,v 0.2 1999-11-24 12:50:41 cjm Exp $

import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * The GUILabelSetter is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It sets a label's text. This is needed as updating <b>must</b> be done
 * in the Swing thread.
 * @author Chris Mottram
 * @version $Revision: 0.2 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUILabelSetter implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUILabelSetter.java,v 0.2 1999-11-24 12:50:41 cjm Exp $");
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
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//
