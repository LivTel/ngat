// CPUScheduler.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/CPUScheduler.java,v 0.1 1999-12-10 14:53:08 cjm Exp $
package ngat.util;

import java.lang.*;

/**
 * This class implements a scheduler thread. This is a high priority thread that decides which, of
 * a set of lower priority threads, should be allowed to run. It implements a round-robin scheduling system.
 * It should be used on CPU intensive tasks. <br>
 * This class was taken from <b>Java Threads</b>, an O'Reilly book by Scott Oaks and Henry Wong, Chapter 6, P149.
 * @version $Revision$
 */
public class CPUScheduler extends Thread
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Thread priority to run the scheduler at.
	 */
	public static int THREAD_PRIORITY_SCHEDULER = 6;
	/**
	 * Thread priority to run the currently running thread at.
	 */
	public static int THREAD_PRIORITY_RUNNING = 4;
	/**
	 * Thread priority for all the threads not running.
	 */
	public static int THREAD_PRIORITY_NOT_RUNNING = 2;
	/**
	 * List of threads to be scheduled.
	 */
	private CircularList threads;
	/**
	 * The currently running thread.
	 */
	private Thread current;
	/**
	 * Time to allow each thread to run.
	 */
	private int timeslice;
	/**
	 * Whether the scheduler is already running.
	 */
	private static boolean initialized = false;
	/**
	 * Whether there is any threads to schedule.
	 */
	private boolean needThreads;

	/**
	 * Method to check if an instance of this class is already running or not.
	 * @return true if an instance is already running, false if it is not.
	 */
	private static synchronized boolean isInitialized()
	{
		if(initialized)
			return true;
		initialized = true;
		return false;
	}

	/**
	 * Constructor.
	 * @param t The timeslice for each thread.
	 */
	public CPUScheduler(int t)
	{
		if(isInitialized())
			throw new SecurityException("Already Initialised.");
		threads = new CircularList();
		timeslice = t;
		setDaemon(true);
	}

	/**
	 * Mehtod to add a thread to be scheduled.
	 * @param t The thread to be scheduled.
	 */
	public synchronized void addThread(Thread t)
	{
		t.setPriority(THREAD_PRIORITY_NOT_RUNNING);
		threads.insert(t);
		if(needThreads)
		{
			needThreads = false;
			notify();
		}
	}

	/**
	 * Method to remove a thread.
	 * @param t The thread to remove.
	 */
	public void removeThread(Thread t)
	{
		threads.delete(t);
		synchronized(this)
		{
			if(t == current)
				current = null;
		}
	}

	/**
	 * The run method for the scheduler.
	 */
	public synchronized void run()
	{
		setPriority(THREAD_PRIORITY_SCHEDULER);
		while(true)
		{
			current = (Thread)threads.getNext();
			while(current == null)
			{
				needThreads = true;
				try
				{
					wait();
				}
				catch(Exception e)
				{
				}
				current = (Thread)threads.getNext();
			}
			try
			{
				current.setPriority(THREAD_PRIORITY_RUNNING);
			}
			catch(Exception e)
			{
				removeThread(current);
				continue;
			}
			try
			{
				sleep(timeslice);
			}
			catch(InterruptedException ie)
			{
			}
			if(current != null)
			{
				try
				{
					current.setPriority(THREAD_PRIORITY_NOT_RUNNING);
				}
				catch(Exception e)
				{
					removeThread(current);
				}
			}
		}// end while true
	}
}
//
// $Log: not supported by cvs2svn $
//
