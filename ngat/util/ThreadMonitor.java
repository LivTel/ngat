// ThreadMonitor.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/ThreadMonitor.java,v 1.1 1999-03-19 16:22:07 dev Exp $
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
	boolean allThreads = true;
	int updateTime = 2000; // unit is ms
	Thread threadArray[] = null;

	/**
	 * Constructor for the thread monitor.
	 * @param list The list to display threads in.
	 */
	public ThreadMonitor(List list)
	{
		this(list,true,2000);
	}

	/**
	 * Constructor for the thread monitor.
	 * @param list The list to display threads in.
	 * @param allThreads Whether to display all running threads or just those running Socket connections.
	 */
	public ThreadMonitor(List list, boolean allThreads)
	{
		this(list,allThreads,2000);
	}

	/**
	 * Constructor for the thread monitor.
	 * @param list The list to display threads in.
	 * @param allThreads Whether to display all running threads or just those running Socket connections.
	 * @param The updateTime between updating the threads in the list, in milliseconds.
	 */
	public ThreadMonitor(List list,boolean allThreads,int updateTime)
	{
		this.list = list;
		this.allThreads = allThreads;
		this.updateTime = updateTime;
	}

	/**
	 * The run method. This goes into an infinite loop, deleting the list, 
	 * getting a list of active threads in this group, and setting the list.
	 */
	public void run()
	{
		int count;
		String s;
		int i;

		Thread.currentThread().setName("Thread Monitor");
		while(true)
		{
			list.removeAll();
			synchronized (this)
			{
				count = Thread.activeCount();
				threadArray = new Thread[count];
				count=Thread.enumerate(threadArray);
				for (i=1;i<count;i++)
				{
					s = new String(threadArray[i].getName());
					if (allThreads == true)
					{
						list.addItem(s);
					}
					else
					{
						if(s.startsWith("Socket"))
							list.addItem(s);
					}
				}
			}
			try
			{
				sleep(updateTime);
			}
			catch (InterruptedException exc)
			{
			}
		}
	}
}

// $Log: not supported by cvs2svn $
