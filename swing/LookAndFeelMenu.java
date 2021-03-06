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
// LookAndFeelMenu.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/LookAndFeelMenu.java,v 0.2 2006-05-16 18:15:29 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Look And Feel selection menu bar.
 * @author Chris Mottram
 * @version $Revision$
 */
public class LookAndFeelMenu extends JMenu implements ActionListener
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Top Level frame to update.
	 */
	private JFrame topLevelFrame = null;
	/**
	 * List on installed look and feels.
	 */
	private UIManager.LookAndFeelInfo lookAndFeelArray[];

	/**
	 * Constructor. Calls the JMenu constructor.
	 * Gets a list of installed look and feels.
	 * Adds a menu item for each look and feel.
	 * Sets the action listener back to this class.
	 * @param tlf The top-level frame to update.
	 * @see UIManager#getInstalledLookAndFeels
	 */
	public LookAndFeelMenu(JFrame tlf)
	{
		super("Look and Feel");
		getAccessibleContext().setAccessibleDescription("The menu for selecting a look and feel.");
		topLevelFrame = tlf;
		lookAndFeelArray = UIManager.getInstalledLookAndFeels();
		for(int i = 0;i < lookAndFeelArray.length;i++)
		{
			JMenuItem menuItem = null;

			menuItem = new JMenuItem(lookAndFeelArray[i].getName());
			menuItem.addActionListener(this);
			add(menuItem);
		}
	}

	/**
	 * Routine that is called when a menu item is pressed.
	 * We try to find the menu item in the lookAndFeelArray[].getName().
	 * We change the Look and feel based on the selected menu item.
	 * We tell the topLevelFrame to update it's user interface and re-pack.
	 * @see #lookAndFeelArray
	 * @see #topLevelFrame
	 */
	public void actionPerformed(ActionEvent event)
	{
		String commandString = event.getActionCommand();
		int index;

		index = 0;
		while((index < lookAndFeelArray.length)&&
			(commandString.equals(lookAndFeelArray[index].getName())==false))
			index++;
		if(index < lookAndFeelArray.length)
		{
			try
			{
				Class cl = Class.forName(lookAndFeelArray[index].getClassName());
				LookAndFeel laf = (LookAndFeel)(cl.newInstance());
			// Note - we dont't use setLookAndFeel(String) here - as this fails for
			// the LT look and feel - it can't find it for some reason.
				UIManager.setLookAndFeel(laf);
				SwingUtilities.updateComponentTreeUI(topLevelFrame);
			// frame.pack() seems to reset the size to prefferredSize
			// this is silly if the user has re-sized
			// therefore get and reset size - this looks a little silly though...
				Dimension d = topLevelFrame.getSize();
				topLevelFrame.pack();
				topLevelFrame.setSize(d);
			}
			catch(Exception e)
			{
				System.err.println("Setting look and feel `"+lookAndFeelArray[index].getClassName()+
					"' failed:"+e);
				JOptionPane.showMessageDialog((Component)null,
					(Object)("Setting look and feel `"+lookAndFeelArray[index].getClassName()+
					"' failed."),
					" Set Look and Feel Error ",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog((Component)null,
				(Object)("Failed to find look and feel `"+commandString+"'."),
				" Set Look and Feel Error ",JOptionPane.ERROR_MESSAGE);
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  1999/11/30 15:47:54  cjm
// initial revision.
//
//
