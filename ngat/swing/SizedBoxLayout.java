// SizedBoxLayout.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/SizedBoxLayout.java,v 0.1 1999-12-09 16:38:02 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;

/**
 * This class extends BoxLayout, but tries to keep the container a specified size.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class SizedBoxLayout extends BoxLayout
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: SizedBoxLayout.java,v 0.1 1999-12-09 16:38:02 cjm Exp $");
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
//
