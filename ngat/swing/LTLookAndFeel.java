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
// LTLookAndFeel.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/LTLookAndFeel.java,v 0.3 2006-05-16 18:15:30 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * This class is an LT specific look and feel.
 * @author Chris Mottram
 * @version $Revision: 0.3 $
 */
public class LTLookAndFeel extends MetalLookAndFeel
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: LTLookAndFeel.java,v 0.3 2006-05-16 18:15:30 cjm Exp $");

	/**
	 * Return a string that identifies this look and feel. 
	 * @return Returns 'LT'.
	 */
	public String getID()
	{
		return "LT";
	}

	/**
	 * Return a short string that identifies this look and feel. 
	 * @return Returns 'Liverpool Telescope'.
	 */
	public String getName()
	{
		return "Liverpool Telescope";
	}

	/**
	 * Return a one line description of this look and feel implementation.
	 * @return Returns 'Liverpool Telescope Look and Feel'.
	 */
	public String getDescription()
	{
		return "Liverpool Telescope Look and Feel";
	}

	/**
	 * If the underlying platform has a "native" look and feel, and this is an implementation of it, return true.
	 * @return This method returns false.
	 */
	public boolean isNativeLookAndFeel()
	{
		return false;
	}

	/**
	 * Return true if the underlying platform supports and or permits this look and feel.
	 * @return This method returns true.
	 */
	public boolean isSupportedLookAndFeel()
	{
		return true;
	}

	/**
	 * UIManager.setLookAndFeel calls this method before the first call (and typically the only call) 
	 * to getDefaults(). 
	 */
	public void initialize()
	{
//diddly		UIDefaults uiDefaults = null;

		super.initialize();
//		uiDefaults = super.getDefaults();
//		for(Enumeration e = uiDefaults.keys(); e.hasMoreElements() ;)
//		{
//			String keyString = (String)e.nextElement();
//			System.out.println("key:"+keyString+":\t\tvalue:"+uiDefaults.get(keyString));
//		}
	}

	/**
	 * UIManager.setLookAndFeel calls this method just before we're replaced by a new default look and feel. 
	 * This just calls the super-classes uninitialise.
	 */
	public void uninitialize()
	{
		super.uninitialize();
	}

	public UIDefaults getDefaults()
	{
		UIDefaults uiDefaults = null;
		ColorUIResource ltBackgroundColourBlue = new ColorUIResource(200,200,255);
		ColorUIResource ltBackgroundColourLightBlue = new ColorUIResource(225,225,255);
		ColorUIResource ltForegroundColourBlue = new ColorUIResource(0,0,200);
		ColorUIResource ltDisabledForegroundColourBlue = new ColorUIResource(153,153,200);
		ColorUIResource ltBackgroundColourYellow = new ColorUIResource(255,255,225);
		ColorUIResource ltSelectionForegroundColour = new ColorUIResource(255,0,0);
		ColorUIResource ltSelectionBackgroundColour = new ColorUIResource(255,200,200);
		FontUIResource ltDefaultFont = new FontUIResource("Dialog",FontUIResource.PLAIN,10);
		FontUIResource ltBoldFont = new FontUIResource("Dialog",FontUIResource.BOLD,12);
		InsetsUIResource ltDefaultInsets = new InsetsUIResource(1,1,1,1);

		uiDefaults = super.getDefaults();
	// Button
		uiDefaults.put("Button.background",ltBackgroundColourBlue);
		uiDefaults.put("Button.foreground",ltForegroundColourBlue);
		uiDefaults.put("Button.focus",new ColorUIResource(0,255,255));
		uiDefaults.put("Button.select",ltSelectionBackgroundColour);
		uiDefaults.put("Button.margin",new InsetsUIResource(2,14,2,14));
		uiDefaults.put("Button.disabledText",ltDisabledForegroundColourBlue);
		uiDefaults.put("Button.font",ltDefaultFont);
//		uiDefaults.put("Button.textShiftOffset",0);
	// CheckBox
		uiDefaults.put("CheckBox.background",ltBackgroundColourBlue);
		uiDefaults.put("CheckBox.foreground",ltForegroundColourBlue);
		uiDefaults.put("CheckBox.focus",new ColorUIResource(0,255,255));
		uiDefaults.put("CheckBox.disabledText",ltDisabledForegroundColourBlue);
		uiDefaults.put("CheckBox.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("CheckBox.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("CheckBox.font",ltDefaultFont);
		uiDefaults.put("CheckBox.margin",ltDefaultInsets);
//		uiDefaults.put("CheckBox.textShiftOffset",0);
	// CheckBoxMenuItem
		uiDefaults.put("CheckBoxMenuItem.background",ltBackgroundColourBlue);
		uiDefaults.put("CheckBoxMenuItem.foreground",ltForegroundColourBlue);
		uiDefaults.put("CheckBoxMenuItem.acceleratorForeground",ltForegroundColourBlue);
		uiDefaults.put("CheckBoxMenuItem.acceleratorSelectionForeground",ltForegroundColourBlue);
		uiDefaults.put("CheckBoxMenuItem.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("CheckBoxMenuItem.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("CheckBoxMenuItem.disabledForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("CheckBoxMenuItem.margin",ltDefaultInsets);
		uiDefaults.put("CheckBoxMenuItem.font",ltDefaultFont);
	// Label
		uiDefaults.put("Label.background",ltBackgroundColourBlue);
		uiDefaults.put("Label.foreground",ltForegroundColourBlue);
		uiDefaults.put("Label.disabledForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("Label.disabledShadow",new ColorUIResource(153,153,153));
		uiDefaults.put("Label.font",ltDefaultFont);
	// List
		uiDefaults.put("List.background",ltBackgroundColourYellow);
		uiDefaults.put("List.foreground",ltForegroundColourBlue);
		uiDefaults.put("List.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("List.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("List.font",ltDefaultFont);
	// MenuBar
		uiDefaults.put("MenuBar.background",ltBackgroundColourBlue);
		uiDefaults.put("MenuBar.foreground",ltForegroundColourBlue);
		uiDefaults.put("MenuBar.font",ltDefaultFont);
	// Menu
		uiDefaults.put("Menu.background",ltBackgroundColourBlue);
		uiDefaults.put("Menu.foreground",ltForegroundColourBlue);
		uiDefaults.put("Menu.acceleratorForeground",ltForegroundColourBlue);
		uiDefaults.put("Menu.acceleratorSelectionForeground",new ColorUIResource(255,0,255));
		uiDefaults.put("Menu.disabledForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("Menu.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("Menu.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("Menu.font",ltDefaultFont);
		uiDefaults.put("Menu.acceleratorFont",ltDefaultFont);
		uiDefaults.put("Menu.margin",ltDefaultInsets);
	// MenuItem
		uiDefaults.put("MenuItem.background",ltBackgroundColourBlue);
		uiDefaults.put("MenuItem.foreground",ltForegroundColourBlue);
		uiDefaults.put("MenuItem.acceleratorForeground",ltForegroundColourBlue);
		uiDefaults.put("MenuItem.acceleratorSelectionForeground",new ColorUIResource(255,0,255));
		uiDefaults.put("MenuItem.disabledForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("MenuItem.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("MenuItem.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("MenuItem.font",ltDefaultFont);
		uiDefaults.put("Menu.margin",ltDefaultInsets);
	// Panel
		uiDefaults.put("Panel.background",ltBackgroundColourBlue);
		uiDefaults.put("Panel.foreground",ltForegroundColourBlue);
		uiDefaults.put("Panel.font",ltDefaultFont);
	// OptionPane
		uiDefaults.put("OptionPane.foreground",ltForegroundColourBlue);
		uiDefaults.put("OptionPane.background",ltBackgroundColourBlue);
		uiDefaults.put("OptionPane.messageForeground",ltForegroundColourBlue);
//		uiDefaults.put("OptionPane.errorIcon",new IconUIResource(""));
//		uiDefaults.put("OptionPane.informationIcon",);
//		uiDefaults.put("OptionPane.questionIcon",);
//		uiDefaults.put("OptionPane.warningIcon",);
		uiDefaults.put("OptionPane.font",ltDefaultFont);
	// ScrollBar
		uiDefaults.put("ScrollBar.foreground",ltForegroundColourBlue);
		uiDefaults.put("ScrollBar.background",ltBackgroundColourBlue);
//		uiDefaults.put("ScrollBar.darkShadow",ltBackgroundColourBlue);
//		uiDefaults.put("ScrollBar.shadow",ltBackgroundColourBlue);
		uiDefaults.put("ScrollBar.highlight",ltSelectionBackgroundColour);
		uiDefaults.put("ScrollBar.track",ltBackgroundColourBlue);
//		uiDefaults.put("ScrollBar.trackHighlight",ltBackgroundColourBlue);
		uiDefaults.put("ScrollBar.thumb",ltBackgroundColourBlue);
		uiDefaults.put("ScrollBar.thumbHighlight",ltSelectionBackgroundColour);
//		uiDefaults.put("ScrollBar.thumbShadow",ltBackgroundColourBlue);
//		uiDefaults.put("ScrollBar.thumbLightShadow",ltBackgroundColourBlue);
//		uiDefaults.put("ScrollBar.thumbDarkShadow",ltBackgroundColourBlue);
	// TextArea
		uiDefaults.put("TextArea.background",ltBackgroundColourYellow);
		uiDefaults.put("TextArea.foreground",ltForegroundColourBlue);
		uiDefaults.put("TextArea.caretForeground",ltSelectionForegroundColour);
		uiDefaults.put("TextArea.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("TextArea.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("TextArea.inactiveForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("TextArea.font",ltDefaultFont);
		uiDefaults.put("TextArea.margin",ltDefaultInsets);
	// TextField
		uiDefaults.put("TextField.background",ltBackgroundColourYellow);
		uiDefaults.put("TextField.foreground",ltForegroundColourBlue);
		uiDefaults.put("TextField.caretForeground",ltSelectionForegroundColour);
		uiDefaults.put("TextField.selectionForeground",ltSelectionForegroundColour);
		uiDefaults.put("TextField.selectionBackground",ltSelectionBackgroundColour);
		uiDefaults.put("TextField.inactiveForeground",ltDisabledForegroundColourBlue);
		uiDefaults.put("TextField.font",ltDefaultFont);
		uiDefaults.put("TextField.margin",ltDefaultInsets);
	// TitledBorder
		uiDefaults.put("TitledBorder.titleColor",ltForegroundColourBlue);
		uiDefaults.put("TitledBorder.font",ltBoldFont);

	// return results
		return uiDefaults;
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 0.2  1999/12/10 14:54:27  cjm
// backup.
//
// Revision 0.1  1999/11/30 14:05:33  cjm
// initial revision.
//
//
