// SizedGridLayout.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/SizedGridLayout.java,v 0.1 1999-12-09 16:38:42 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;

/**
 * This class extends GridLayout, but tries to keep the container a specified size.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class SizedGridLayout extends GridLayout
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SizedGridLayout.java,v 0.1 1999-12-09 16:38:42 cjm Exp $");
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
//
