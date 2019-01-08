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
// SizedGridLayout.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/SizedGridLayout.java,v 0.2 2006-05-16 18:15:33 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;

/**
 * This class extends GridLayout, but tries to keep the container a specified size.
 * @author Chris Mottram
 * @version $Revision$
 */
public class SizedGridLayout extends GridLayout
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
	 * @param s The fixed size we want this container to be.
	 * @see GridLayout#GridLayout
	 */
	public SizedGridLayout(int rows, int cols,Dimension s)
	{
		super(rows,cols);
		size = s;
	}

	/**
	 * Method called when minimum size wanted. Over-ridden to return our fixed size.
	 */
	public Dimension minimumLayoutSize(Container parent)
	{
		return size;
	}

	/**
	 * Method called when preffered size wanted. Over-ridden to return our fixed size.
	 */
	public Dimension preferredLayoutSize(Container parent)
	{
		return size;
	}

	/**
	 * Method called when maximum size wanted. Over-ridden to return our fixed size.
	 */
	public Dimension maximumLayoutSize(Container parent)
	{
		return size;
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.1  1999/12/09 16:38:42  cjm
// initial revision.
//
//
