// ThreadMonitorFrame.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/ThreadMonitorFrame.java,v 1.2 1999-05-20 16:38:35 dev Exp $
package ngat.util;

import java.awt.*;
import java.awt.event.*;

/**
 * This class uses the ThreadMonitor thread to display the currently executing threads in a box
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class ThreadMonitorFrame extends Frame implements ActionListener
{
	/**
	 * The ThreadMonitor object used to display threads.
	 */
	private ThreadMonitor thread = null;
	/**
	 * The List to display threads in.
	 */
	private List list = null;
	/**
	 * The button to update threads with.
	 */
	private Button updateButton = null;

	/**
	 * Constructor for ThreadMonitorFrame.
	 */
	public ThreadMonitorFrame(String s)
	{
		super(s+" : Thread Monitor ");
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(gridBag);

        //Set up the menu bar.
		MenuBar mb = new MenuBar();
		Menu m = new Menu("File");
		MenuItem menuItem = new MenuItem("Quit");
		menuItem.addActionListener(this);
		m.add(menuItem);

		mb.add(m);
		setMenuBar(mb);

		list = new List(10,false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.8;
		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		c.gridheight = GridBagConstraints.RELATIVE;
		gridBag.setConstraints(list,c);
		add(list);

		updateButton = new Button("Update");
		updateButton.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.2;
		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		c.gridheight = GridBagConstraints.REMAINDER;
		gridBag.setConstraints(updateButton,c);
		add(updateButton);

		thread = new ThreadMonitor(list);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				thread.stop();
				dispose();
			}
		});
		thread.start();
	}

	/**
	 * Called when an action is performed e.g. The Quit button is clicked.
	 */
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Update"))
		{
			thread.update();
		}
		if(event.getActionCommand().equals("Quit"))
		{
			thread.stop();
			dispose();
		}
	}

	/**
	 * Get the Thread Monitor we are using to display the threads.
	 * @return the ThreadMonitor object.
	 */
	public ThreadMonitor getThreadMonitor()
	{
		return thread;
	}
}
// $Log: not supported by cvs2svn $
// Revision 1.1  1999/03/25 14:00:34  dev
// Backup
//
