// GUITextAppender.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUITextAppender.java,v 0.2 1999-11-24 12:50:31 cjm Exp $

import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * The GUITextAppender is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It appends some passed in text to the JTextArea. This is needed as updating <b>must</b> be done
 * in the Swing thread.
 * @author Chris Mottram
 * @version $Revision: 0.2 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUITextAppender implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUITextAppender.java,v 0.2 1999-11-24 12:50:31 cjm Exp $");
	/**
	 * The Swing JTextAreacomponent to append to.
	 */
	private JTextArea textArea = null;
	/**
	 * The text to append.
	 */
	private String appendString = null;

	/**
	 * Constructor.
	 * @param j The Text area to append text to.
	 * @param s The string to append to the text area.
	 */
	public GUITextAppender(JTextArea j,String s)
	{
		textArea = j;
		appendString = s;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls textArea.append(appendString)
	 * to append the text to the text area.
	 * @see #textArea
	 * @see #appendString
	 */
	public void run()
	{
		textArea.append(appendString);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//
