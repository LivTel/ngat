// AttributedTextArea.java
// $Header: /space/home/eng/cjm/cvs/ngat/swing/AttributedTextArea.java,v 1.1 2003-08-21 13:04:16 cjm Exp $
package ngat.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/** 
 * Subclass of JTextPane allowing colour attributes.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public class AttributedTextArea extends JTextPane
{
	/**
	 * Document model, used to add coloured text.
	 */
	protected DefaultStyledDocument defaultStyledDocument = null;
	/**
	 * The default colour for text.
	 */
	protected Color defaultForegroundColour = null;

	/**
	 * Constructor. Set the document to the default styled document.
	 * @see #defaultStyledDocument
	 */
	public AttributedTextArea()
	{
		super();
	}

	/**
	 * Constructor. Set the document to the default styled document.
	 * @see #defaultStyledDocument
	 */
	public AttributedTextArea(String s)
	{
		super();
		setText(s);
	}

	/**
	 * Append text. Initialises defaultStyledDocument if neccessary, using initialiseDocument.
	 * @param string The string to append.
	 * @param color The colour of the text.
	 * @exception BadLocationException Thrown if an error occurs.
	 * @see #initialiseDocument
	 */
	public void append(String string,Color color) throws BadLocationException,ClassCastException
	{
		SimpleAttributeSet attr = null;

		initialiseDocument();
		attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr,color);
		defaultStyledDocument.insertString(defaultStyledDocument.getLength(),string,attr);
	}

	/**
	 * Append text in default colour.
	 * @param string The string to append.
	 * @exception BadLocationException Thrown if an error occurs.
	 * @see #defaultForegroundColour
	 */
	public void append(String string) throws BadLocationException
	{
		if(defaultForegroundColour != null)
			append(string,defaultForegroundColour);
		else
			append(string,Color.black);
	}

	/**
	 * Sets the line-wrapping policy of the text area. 
	 * If set to true the lines will be wrapped if they are too long to fit within the allocated width. 
	 * If set to false, the lines will always be unwrapped. 
	 * A PropertyChange event ("lineWrap") is fired when the policy is changed.
	 * @param wrap indicates if lines should be wrapped.
	 */
	public void setLineWrap(boolean wrap)
	{
		//diddly
	}

	/**
	 * Set the style of wrapping used if the text area is wrapping lines. 
	 * If set to true the lines will be wrapped at word boundries (ie whitespace) if they are too long to fit 
	 * within the allocated width.
	 * If set to false, the lines will be wrapped at character boundries.
	 * @param word indicates if word boundries should be used for line wrapping.
	 */
	public void setWrapStyleWord(boolean word)
	{
		//diddly
	}

	/**
	 * Return the number of lines in the text in the text area.
	 * @return The number of lines.
	 * @see #getText
	 */
	public int getLineCount()
	{
		String text = null;
		int lineCount,textLength;
		char ch;

		text = getText();
		if(text != null)
			textLength = text.length();
		else
			textLength = 0;
		lineCount = 0;
		for(int i = 0; i < textLength; i++)
		{
			ch = text.charAt(i);
			if(ch == '\n')
				lineCount++;
		}
		return lineCount;
	}

	/**
	 * Determines the offset of the end of the given line.
	 * @param line The number of the line. Should be >= 0.
	 * @return The character offset of the last character on that line.
	 * @exception BadLocationException Thrown if line is not in the document.
	 */
	public int getLineEndOffset(int line) throws BadLocationException
	{
		String text = null;
		int offset,currentLine,textLength;
		boolean done;
		char ch;

		if(line < 0)
			throw new BadLocationException("Line "+line+" out of range [0.."+getLineCount()+"].",line);
		offset = 0;
		currentLine = 0;
		done = false;
		text = getText();
		if(text != null)
			textLength = text.length();
		else
			textLength = 0;
		while(done == false)
		{
			if(offset >= textLength)
			{
				throw new BadLocationException("Line "+line+" out of range [0.."+getLineCount()+
							       "] (offset "+offset+" > "+textLength+".",line);
			}
			ch = text.charAt(offset);
			if(ch == '\n')
			{
				if(line == currentLine)
				{
					done = true;
					// don't increment offset, should be character before here?
				}
				else
				{
					currentLine++;
					offset++;
				}
			}
			else
			{
				offset++;
			}
		}// end while
		return offset;
	}

	/**
	 * Method to initialise data about this document.
	 * The default styled document is retrieved.
	 * @see #defaultStyledDocument
	 * @see #defaultForegroundColour
	 */
	protected void initialiseDocument()
	{
		Document document = null;
		Style style = null;

		// get defaultStyledDocument
		if(defaultStyledDocument == null)
		{
			document = getDocument();
			defaultStyledDocument = (DefaultStyledDocument)document;
		}
		// get default foreground colour
		if(defaultForegroundColour == null)
		{
			// get default style
			style = defaultStyledDocument.getLogicalStyle(0);
			if(style != null)
			{
				defaultForegroundColour = defaultStyledDocument.getForeground(style);
			}
		}
	}

}
/*
** $Log: not supported by cvs2svn $
*/
