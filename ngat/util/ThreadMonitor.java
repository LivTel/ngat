// ThreadMonitor.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/ThreadMonitor.java,v 1.2 1999-03-25 14:00:34 dev Exp $
package ngat.util;

import java.awt.*;

/**
 * This class extends Thread, and tries to monitor the threads currently executing in this JVM. A list is passed in
 * to display the threads in. It monitors all the the active threads in the group and sub-groups of this threads'
 * group.
 */
public class ThreadMonitor extends Thread
{
	List list;
	int updateTime = 2000; // unit is ms
	ThreadGroup threadGroup = null;
	Thread threadArray[] = null;

	/**
	 * Constructor for the thread monitor.
	 * @param list The list to display threads in.
	 */
	public ThreadMonitor(List list)
	{
		this(list,2000);
	}

	/**
	 * Constructor for the thread monitor.
	 * @param list The list to display threads in.
	 * @param The updateTime between updating the threads in the list, in milliseconds.
	 */
	public ThreadMonitor(List list,int updateTime)
	{
		setName("Thread Monitor");
		this.list = list;
		this.updateTime = updateTime;
	}

	/**
	 * The run method. This goes into an infinite loop, deleting the list, 
	 * getting a list of active threads in this group, and setting the list.
	 */
	public void run()
	{
		getTopThreadGroup();
		while(true)
		{
			update();
			try
			{
				sleep(updateTime);
			}
			catch (InterruptedException exc)
			{
			}
		}
	}

	/**
	 * Routine that updates the list. Gets all threads in the current threadGroup (and sub groups)
	 * and puts their name into the list.
	 */
	public synchronized void update()
	{
		int count;
		String s;

		if(threadGroup == null)
			getTopThreadGroup();
		list.removeAll();
		count = threadGroup.activeCount();
		threadArray = new Thread[count];
		count=threadGroup.enumerate(threadArray);
		for(int i = 1;i < count;i++)
		{
			s = new String(threadArray[i].getName());
			list.addItem(s);
		}
	}

	/**
	 * Set the update time for the monitor thread.
	 * @param milliseconds The time in milliseconds.
	 */
	public void setUpdateTime(int milliseconds)
	{
		updateTime = milliseconds;
	}

	private void getTopThreadGroup()
	{
		threadGroup = this.getThreadGroup();
		while(threadGroup.getParent() != null)
			threadGroup = threadGroup.getParent();
	}
}

// $Log: not supported by cvs2svn $
// Revision 1.1  1999/03/19 16:22:07  dev
// Backup
//
