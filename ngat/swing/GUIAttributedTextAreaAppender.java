// GUIAttributedTextAreaAppender.java
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUIAttributedTextAreaAppender.java,v 1.1 2003-08-21 13:04:16 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The GUIAttributedTextAreaAppender is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * It appends some passed in text to the AttributedTextArea. This is needed as updating <b>must</b> be done
 * in the Swing thread.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUIAttributedTextAreaAppender implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: GUIAttributedTextAreaAppender.java,v 1.1 2003-08-21 13:04:16 cjm Exp $");
	/**
	 * The Swing AttributedTextArea component to append to.
	 */
	private AttributedTextArea textArea = null;
	/**
	 * The text to append.
	 */
	private String appendString = null;
	/**
	 * The colour of text to append.
	 */
	private Color appendColour = null;
	/**
	 * Whether we should try to scroll the text area to the text just appeneded.
	 */
	private boolean scrollToEnd = false;

	/**
	 * Constructor.
	 * @param j The Text area to append text to.
	 * @param s The string to append to the text area.
	 * @param c The colour of the string to append to the text area.
	 * @param b Whether to scroll the text area to the text just appended.
	 */
	public GUIAttributedTextAreaAppender(AttributedTextArea j,String s,Color c,boolean b)
	{
		textArea = j;
		appendString = s;
		appendColour = c;
		scrollToEnd = b;
	}

	/**
	 * Constructor. No colour is specified, so appendColour is set to nill, which will cause
	 * the run method to append the text in the default colour.
	 * @param j The Text area to append text to.
	 * @param s The string to append to the text area.
	 * @param b Whether to scroll the text area to the text just appended.
	 */
	public GUIAttributedTextAreaAppender(AttributedTextArea j,String s,boolean b)
	{
		textArea = j;
		appendString = s;
		appendColour = null;
		scrollToEnd = b;
	}

	/**
	 * Run method, hopefully called from the Swing thread. Just calls textArea.append(appendString)
	 * to append the text to the text area. 
	 * If we wish to scroll to the end, it then gets the number of lines, calculates a offset to the end of the
	 * line before, and sets the caret position to this offset. This should scroll the text area to the appended
	 * text.
	 * @see #textArea
	 * @see #appendString
	 * @see #appendColour
	 * @see #scrollToEnd
	 */
	public void run()
	{
		int endOffset = 0;

		try
		{
			if(appendColour != null)
				textArea.append(appendString,appendColour);
			else
				textArea.append(appendString);
			if(scrollToEnd)
			{
				endOffset = textArea.getText().length();
				textArea.setCaretPosition(endOffset);
			}
		}
		catch(BadLocationException e)
		{
		}
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.5  2003/08/14 16:40:56  cjm
// Removed fundamental.
//
// Revision 0.4  1999/12/14 15:16:33  cjm
// Added scroll to end technology.
//
// Revision 0.3  1999/11/29 11:46:03  cjm
// Changed package to ngat.swing.
//
// Revision 0.2  1999/11/24 12:50:31  cjm
// Changed @see clause.
//
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//
