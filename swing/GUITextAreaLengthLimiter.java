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
// GUITextAreaLengthLimiter.java
// $Header: /space/home/eng/cjm/cvs/ngat/swing/GUITextAreaLengthLimiter.java,v 1.2 2006-05-16 18:15:28 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The GUITextAreaLengthLimiter checks the number of lines in the JTextArea, 
 * and if there are more lines in the TextArea than the
 * specified limit it removes lines of text at the start of the area. 
 * We need to do this otherwise the TextArea consumes more and more memory until the JVM runs out.
 * The GUITextAreaLengthLimiter is Runnable. It is used as an argument to SwingUtilities.invokeLater.
 * This is needed as updating <b>must</b> be done in the Swing thread.
 * @author Chris Mottram
 * @version $Revision$
 * @see javax.swing.SwingUtilities#invokeLater
 */
public class GUITextAreaLengthLimiter implements Runnable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The Swing JTextArea component to examine.
	 */
	private JTextArea textArea = null;
	/**
	 * The number of lines of text to limit the TextArea to.
	 */
	private int textLengthLimit = 0;
	/**
	 * Whether we should try to scroll the text area to the end of the text area.
	 */
	private boolean scrollToEnd = false;

	/**
	 * Constructor.
	 * @param ta The Text area to check the line length of.
	 * @param lineNumber The maximum number of lines.
	 * @param scroll Whether to scroll the text area to the end of text after deletion of lines.
	 */
	public GUITextAreaLengthLimiter(JTextArea ta,int lineNumber,boolean scroll)
	{
		textArea = ta;
		textLengthLimit = lineNumber;
		scrollToEnd = scroll;
	}

	/**
	 * Run method, hopefully called from the Swing thread. 
	 * It calls textArea.getLineCount() to see how many lines are in the text area. 
	 * If there are more lines than textLengthLimit (specified in the constructor), the character offset
	 * to the end of the number of lines to remove is calculated, 
	 * and remove called to remove that many lines of text.
	 * If we wish to scroll to the end, it then calculates a offset to the end of the
	 * line before, and sets the caret position to this offset. This should scroll the text area to the 
	 * end of the text.
	 * @see #textArea
	 * @see #textLengthLimit
	 * @see #scrollToEnd
	 */
	public void run()
	{
		int lineNumber = 0;
		int removeLineCount = 0;
		int offset = 0;

		lineNumber = textArea.getLineCount();
		if(lineNumber > textLengthLimit)
		{
			removeLineCount = lineNumber-textLengthLimit;
			try
			{
				offset = textArea.getLineEndOffset(removeLineCount);
				textArea.getDocument().remove(0,offset);
				if(scrollToEnd)
				{
					lineNumber = textArea.getLineCount();// recalculate - it's changed!!
					offset = textArea.getLineEndOffset(lineNumber-1);
					textArea.setCaretPosition(offset);
				}// end if scrollToEnd
			}
			catch(BadLocationException e)
			{
			}
		}// end if limit exceeded
	}// end run method
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2003/08/21 13:04:16  cjm
// Initial revision
//
// Revision 0.1  2000/06/26 18:07:08  cjm
// initial revision.
//
//
