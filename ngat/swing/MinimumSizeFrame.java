// MinimumSizeFrame.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/MinimumSizeFrame.java,v 0.4 1999-12-09 15:11:32 cjm Exp $
package ngat.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 * This class extends JFrame, but tries to set a minimum size for the frame.
 * It does this by monitoring Component Window re-sizeing events, and checking the current size	
 * against the minimum size and re-sizing if appropriate. This is not a very good mechanism
 * for doing this.
 * @author Chris Mottram
 * @version $Revision: 0.4 $
 */
public class MinimumSizeFrame extends JFrame
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: MinimumSizeFrame.java,v 0.4 1999-12-09 15:11:32 cjm Exp $");
	/**
	 * Dimension to hold the minimum size.
 	 */
	private Dimension min = null;

	/**
	 * A Constructor.
	 * @param m The minimum size of the frame.
	 */
	public MinimumSizeFrame(Dimension m)
	{
		super();
		min = new Dimension(m);
		addComponentListener(new MinimumSizeFrameComponentListener());
	}

	/**
	 * A Constructor.
	 * @param title The title.
	 * @param m The minimum size of the frame.
	 */
	public MinimumSizeFrame(String title,Dimension m)
	{
		super(title);
		min = new Dimension(m);
		addComponentListener(new MinimumSizeFrameComponentListener());
	}

	/**
	 * Method to set the minimum size.
	 * @param m The minimum size of the frame.
	 */
	public void setMinimumSize(Dimension m)
	{
		min = new Dimension(m);
	}

	/**
	 * Routine that returns the minimum size.
	 */
	public Dimension getMinimumSize()
	{
		System.err.println(this.getClass().getName()+":getMinimumSize:"+min);
		if(min != null)
			return new Dimension(min);
		else
			return super.getMinimumSize();
	}

	/**
	 * Inner class that listens for resize events on MinimumSizeFrame.
	 */
	class MinimumSizeFrameComponentListener extends ComponentAdapter implements ComponentListener
	{
		/**
		 * Re-size event method.
	 	 */
		public void componentResized(ComponentEvent e)
		{
			Dimension d = null;
			boolean changed = false;

			if(min == null)
				return;
			d = getSize();
			if(d.width < min.width)
			{
				d.width = min.width;
				changed = true;
			}
			if(d.height < min.height)
			{
				d.height = min.height;
				changed = true;
			}
			if(changed)
			{
				setSize(d);
			}
		}
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.3  1999/12/09 15:09:17  cjm
// Added RCSID.
//
// Revision 0.2  1999/11/29 11:51:35  cjm
// Changed package to ngat.swing.
//
// Revision 0.1  1999/11/22 09:53:49  cjm
// initial revision.
//
//