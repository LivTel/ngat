// LTMetalTheme.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/LTMetalTheme.java,v 0.1 1999-12-02 17:19:54 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.metal.MetalTheme;

/**
 * This class is an LT specific theme.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class LTMetalTheme extends MetalTheme
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: LTMetalTheme.java,v 0.1 1999-12-02 17:19:54 cjm Exp $");
	/**
	 * Selected foreground colour.
	 */
	private ColorUIResource ltSelectionForegroundColour = null;
	/**
	 * Selected background colour.
	 */
	private ColorUIResource ltSelectionBackgroundColour = null;
	/**
	 * Default font.
	 */
	private	FontUIResource ltDefaultFont = null;

	/**
	 * Constructor. Calls super, and creates the common resources.
	 * @see #ltSelectionBackgroundColour
	 * @see #ltSelectionForegroundColour
	 * @see #ltDefaultFont
	 */
	public LTMetalTheme()
	{
		super();
		ltSelectionBackgroundColour = new ColorUIResource(255,200,200);
		ltSelectionForegroundColour = new ColorUIResource(255,0,0);
		ltDefaultFont =  new FontUIResource("Dialog",FontUIResource.PLAIN,12);
	}

	public String getName()
	{
		return "Liverpool Telescope";
	}

	protected ColorUIResource getPrimary1()
	{
		return new ColorUIResource(50,50,150);
	}

	protected ColorUIResource getPrimary2()
	{
		return new ColorUIResource(125,125,200);
	}

	protected ColorUIResource getPrimary3()
	{
		return new ColorUIResource(150,150,200);
	}

	protected ColorUIResource getSecondary1()
	{
		return new ColorUIResource(150,150,255);
	}

	protected ColorUIResource getSecondary2()
	{
		return new ColorUIResource(175,175,255);
	}

	protected ColorUIResource getSecondary3()
	{
		return new ColorUIResource(200,200,255);
	}

	protected ColorUIResource getBlack()
	{
		return new ColorUIResource(0,0,128);
	}

	protected ColorUIResource getWhite()
	{
		return new ColorUIResource(255,255,200);
	}

	public ColorUIResource getMenuSelectedForeground()
	{
		return new ColorUIResource(255,0,0);
	}

	public ColorUIResource getMenuSelectedBackground()
	{
		return ltSelectionBackgroundColour;
	}

	public ColorUIResource getTextHighlightColor()
	{
		return ltSelectionBackgroundColour;
	}

	public FontUIResource getControlTextFont()
	{
		return ltDefaultFont;
	}

	public FontUIResource getSystemTextFont()
	{
		return ltDefaultFont;
	}

	public FontUIResource getUserTextFont()
	{
		return ltDefaultFont;
	}

	public FontUIResource getMenuTextFont()
	{
		return ltDefaultFont;
	}

	public FontUIResource getWindowTitleFont()
	{
		return ltDefaultFont;
	}

	public FontUIResource getSubTextFont()
	{
		return new FontUIResource("Dialog",FontUIResource.PLAIN,10);
	}

	public void addCustomEntriesToTable(UIDefaults table)
	{
		super.addCustomEntriesToTable(table);
		table.put("Button.select",ltSelectionBackgroundColour);
		table.put("TextArea.caretForeground",ltSelectionForegroundColour);
		table.put("TextArea.selectionForeground",ltSelectionForegroundColour);
		table.put("TextArea.selectionBackground",ltSelectionBackgroundColour);
		table.put("TextField.caretForeground",ltSelectionForegroundColour);
		table.put("TextField.selectionForeground",ltSelectionForegroundColour);
		table.put("TextField.selectionBackground",ltSelectionBackgroundColour);
	}
}
//
// $Log: not supported by cvs2svn $
//
