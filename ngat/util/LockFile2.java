// LockFile2.java
// $Header: /space/home/eng/cjm/cvs/ngat/util/LockFile2.java,v 1.1 2010-02-02 14:39:15 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * This class implements a LockFile. This is a file that resides on the computer's filesystem, and can be
 * atomically created and deleted. This allows two processes competing for a limited resource to
 * synchronise on a lock file by obtaining the lock file, doing the work and then releasing it again.
 * In this case we are trying to synchronise against a cron job that is looking at the file(s) we are creating,
 * and signalling to that software when it is safe to access our created files. So we want a multi-process
 * multi-language locking implementation.
 * There are a variety of ways of doing this in Java:
 * <ul>
 * <li>java.io.File createNewFile allows you to atomically create new files (and is the method we use here).
 *     Read the documentation which explicity states "this method should not be used for file-locking,".
 *     Then read @link http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4676183 which explains the reason
 *     behind that documentation.
 * <li>java.nio.channels.FileLock is recomended Java method for file-locking. However, it uses the underlying
 *     OS locking mechanism, which under Unix is liable to be advisory only! See also fcntl and flock for
 *     the C documentation. And this locks an open file only - not useful. 
 * <li>ngat.util.LockFile is our own lock file implementation. Looks OK, but is overly complicated using
 *     two files, both of which are symbolic links. There is no equivalent C API at the moment.
 * <ul>
 * @see java.nio.channels.FileLock
 * @see ngat.util.LockFile
 * @see java.io.File
 * @version $Revision: 1.1 $
 * @author Chris Mottram
 */
public class LockFile2
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: LockFile2.java,v 1.1 2010-02-02 14:39:15 cjm Exp $");
	/**
	 * Default extension of the lock file.
	 */
	public final static String DEFAULT_LOCK_EXTENSION = "lock";
	/**
	 * The filename of the lock file.
	 */
	protected File lockFile = null;

	/**
	 * Constructor.
	 * @see #DEFAULT_LOCK_EXTENSION
	 * @see #lockFile
	 * @see #LockFile2(java.io.File)
	 */
	public LockFile2(String filename)
	{
		this(new File(filename));
	}

	/**
	 * Constructor. Creates the lockFile object instance, by removing any extension from the leaf
	 * and adding the DEFAULT_LOCK_EXTENSION extension.
	 * @see #DEFAULT_LOCK_EXTENSION
	 * @see #lockFile
	 */
	public LockFile2(File file)
	{
		String parentName = null;
		String leafName = null;
		String leafMinusExtension = null;
		int dotPosition;

		// get parent
		parentName = file.getParent();
		if(parentName == null)
			parentName = new String(".");
		// get leaf filename
		leafName = file.getName();
		// remove entension from leaf filename
		dotPosition = leafName.lastIndexOf('.');
		if(dotPosition > -1)
			leafMinusExtension = leafName.substring(0,dotPosition);
		else
			leafMinusExtension = leafName;
		// create lockfile which is input filename - extension + .lock
		lockFile = new File(parentName+File.separator+
				    leafMinusExtension+"."+DEFAULT_LOCK_EXTENSION);
	}

	/**
	 * Lock the lock file (i.e. create it).
	 * @see #lockFile
	 * @exception IOException Thrown if an IO problem occurs whilst creating the lock file.
	 * @exception Exception Thrown if the lock file could not be created.
	 */
	public synchronized void lock() throws Exception,IOException
	{
		if(lockFile.createNewFile() == false)
			throw new Exception(this.getClass().getName()+":lock:Failed to create lock file:"+lockFile);
	}

	/**
	 * Unlock the lock file (i.e. remove it).
	 * @see #lockFile
	 * @exception Exception Thrown if the lock file could not be deleted.
	 */
	public synchronized void unLock() throws Exception
	{
		if(lockFile.delete() == false)
			throw new Exception(this.getClass().getName()+":unLock:Failed to delete lock file:"+lockFile);
	}

	/**
	 * Return whether the file is locked (i.e. whether the lock file exists).
	 * @return true if the file is locked and false otherwise.
	 * @see #lockFile
	 * @exception Exception Thrown if the lock file could not be deleted.
	 */
	public synchronized boolean isLocked()
	{
		return lockFile.exists();
	}

	/**
	 * Return the details of the lock file.
	 * @return The lock file as a File.
	 * @see #lockFile
	 */
	public File getLockFile()
	{
		return lockFile;
	}

	/**
	 * Return a string description of this object instance.
	 * @return A string.
	 * @see #lockFile
	 */
	public String toString()
	{
		return new String(this.getClass().getName()+":"+lockFile);
	}

	/**
	 * Test main program. Call as follows:
	 * <pre>
	 * java ngat.util.LockFile2 &lt;filename&gt; &lt;lock|unlock|islocked&gt;
	 * </pre>
	 * @param args The argument list.
	 */
	public static void main(String args[])
	{
		LockFile2 lf = null;
		String filename = null;
		String operation = null;

		if(args.length < 2)
		{
			System.err.println("java ngat.util.LockFile <filename> <lock|unlock|islocked|>");
			System.exit(1);
		}
		filename = args[0];
		operation = args[1];
		lf = new LockFile2(filename);
		try
		{
			if(operation.equals("lock"))
			{
				lf.lock();
			}
			else if(operation.equals("unlock"))
			{
				lf.unLock();
			}
			else if(operation.equals("islocked"))
			{
				if(lf.isLocked())
					System.out.println("Is currently locked.");
				else
					System.out.println("Is currently UNlocked.");
			}
			else
			{
				System.err.println("java ngat.util.LockFile <filename> <lock|unlock|islocked>");
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			System.err.println("Operation failed:"+e);
			System.exit(1);
		}
		System.exit(0);
	}
}
//
// $Log: not supported by cvs2svn $
//
