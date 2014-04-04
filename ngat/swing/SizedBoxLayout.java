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
// SizedBoxLayout.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/SizedBoxLayout.java,v 0.2 2006-05-16 18:15:32 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;

/**
 * This class extends BoxLayout, but tries to keep the container a specified size.
 * @author Chris Mottram
 * @version $Revision$
 */
public class SizedBoxLayout extends BoxLayout
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The size we want the layout be to.
	 */
	private Dimension size = null;

	/**
	 * Constructor. Calls super(target,axis) and stores s for later.
	 * @see BoxLayout#BoxLayout
	 */
	public SizedBoxLayout(Container target, int axis,Dimension s)
	{
		super(target,axis);
		size = s;
	}

	/**
	 * Method to get preffered size. Over-ridden to return our preffered size.
	 * @see #size
	 */
	public Dimension preferredLayoutSize(Container parent)
	{
		return size;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  1999/12/09 16:38:02  cjm
// initial revision.
//
//
