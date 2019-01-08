// LockFile.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/LockFile.java,v 1.2 2001-07-31 10:56:42 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * This class implements a LockFile. This is a file that resides on the computer's filesystem, and can be
 * atomically created and deleted. This allows two processes competing for a limited resource to
 * synchronise on a lock file by obtaining the lock file, doing the work and then releasing it again.
 * Note Java already has synchronisation internally to a JVM using the synchronize keyword, this Class
 * allows external synchronisation (to say C programs) as well.<br>
 * The lock file is implemented as a symbolic link, that is <i>dangling</i> (does not point to a file that exists).
 * This allows us to to store some data atomically when the symbolic link is created (in the link string).
 * This allows us to use this as a mechanism to store small bits of persistent data. There are several
 * methods (getData and setData) that allow us to change this data. Note the setData method has to create
 * new link, and them move it over the last one. This makes the LockFile class more complicated, as there
 * are now potentially two lock files on the filesystem.
 * @see FileUtilities
 * @version $Revision$
 * @author Chris Mottram
 */
public class LockFile
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Default name of the lock file.
	 */
	public final static String DEFAULT_NAME = "lock";
	/**
	 * Default string to append to the lock file, to create a temporary copy file.
	 */
	public final static String DEFAULT_OTHER_FILE_EXTENSION = ".tmp";
	/**
	 * The filename of the lock file.
	 */
	protected File file = null;
	/**
	 * The filename of the other lock file, a temporary file used during changing the filenames data.
	 */
	protected File otherFile = null;
	/**
	 * The name of the lock file, used as the first part of the data string.
	 */
	protected String name = DEFAULT_NAME;

	/**
	 * Default constructor.
	 */
	public LockFile()
	{
		super();
	}

	/**
	 * Set the filename to use as the lock file.
	 * @see #setFilename(java.io.File)
	 */
	public synchronized void setFilename(String filename)
	{
		setFilename(new File(filename));
	}

	/**
	 * Set the filename to use as the lock file. otherFile is set to the same name plus the
	 * DEFAULT_OTHER_FILE_EXTENSION.
	 * @param f The filename of the lock file.
	 * @see #file
	 * @see #otherFile
	 * @see #DEFAULT_OTHER_FILE_EXTENSION
	 */
	public synchronized void setFilename(File f)
	{
		file = f;
		otherFile = new File(file.getAbsolutePath()+DEFAULT_OTHER_FILE_EXTENSION);
	}

	/**
	 * Set the name of the lock file.
	 */
	public synchronized void setName(String n)
	{
		name = n;
	}

	/**
	 * lock the file. No data is encoded into the lock.
	 * @see #lock(java.lang.String)
	 * @exception FileUtilitiesNativeException Thrown if creating the symbolic link fails.
	 * @exception Exception Thrown if the lock operation fails. This can occur if the lock file, or the
	 * 	temporary lock file, exist.
	 */
	public synchronized void lock() throws FileUtilitiesNativeException, Exception
	{
		lock(null);
	}

	/**
	 * Lock the file. If the file or the temporary file exist the operation fails as the file is already
	 * locked. Otherwise a lock file is created using createSymbolicLink.
	 * @param data A string that is encoded into the lock file so that it can retrieved at a later date.
	 * @exception FileUtilitiesNativeException Thrown if creating the symbolic link fails.
	 * @exception Exception Thrown if the lock operation fails. This can occur if the lock file, or the
	 * 	temporary lock file, exist.
	 * @see FileUtilities#createSymbolicLink
	 */
	public synchronized void lock(String data) throws FileUtilitiesNativeException, Exception
	{
		if(FileUtilities.symbolicLinkExists(file))
			throw new Exception("File "+file+" already exists.");
		if(FileUtilities.symbolicLinkExists(otherFile))
			throw new Exception("File "+otherFile+" already exists.");
		FileUtilities.createSymbolicLink(name+":"+data,file);
	}

	/**
	 * Method to unlock a lock file. If the lock file, or the temporary lock file, exists, it is 
	 * deleted and the operation is successful. Otherwise an exception is thrown.
	 * @exception Exception Thrown if the unLock operation fails. If neither the lock file 
	 * 	or the temporary lock file exist then the lock file is already unlocked. If a lock file
	 * 	delete operation fails this is also thrown.
	 */
	public synchronized void unLock() throws Exception
	{
		boolean fileDeleted = false;

		if(FileUtilities.symbolicLinkExists(file))
		{
			if(file.delete() == false)
				throw new Exception("Failed to unlock:Failed to delete:"+file);
			fileDeleted = true;
		}
		if(FileUtilities.symbolicLinkExists(otherFile))
		{
			if(otherFile.delete() == false)
				throw new Exception("Failed to unlock:Failed to delete:"+otherFile);
			fileDeleted = true;
		}
		if(fileDeleted == false)
		{
			throw new Exception("Failed to unlock:Lock already unlocked.");
		}
	}

	/**
	 * Return whether the lock file is currently locked.
	 * @return If the lock file or the temporary lock file exist, true is returned.
	 * 	Otherwise false is returned.
	 * @see #file
	 * @see #otherFile
	 */
	public synchronized boolean isLocked()
	{
		return (FileUtilities.symbolicLinkExists(file)||FileUtilities.symbolicLinkExists(otherFile));
	}

	/**
	 * Try to change the data set in the sybolic link used for the lock file.
	 * @param data The new data to set.
	 * @exception FileUtilitiesNativeException Thrown if readSymbolicLink fails.
	 * @exception Exception Thrown if a file delete or rename operation fails.
	 */
	public synchronized void setData(String data) throws FileUtilitiesNativeException, Exception
	{
		if(FileUtilities.symbolicLinkExists(file))
		{
		// Note we don't delete otherFile before renaming file to otherFile.
			if(file.renameTo(otherFile) == false)
				throw new Exception ("setData:Rename "+file+" to "+otherFile+" failed.");
		}
		FileUtilities.createSymbolicLink(name+":"+data,file);
		if(FileUtilities.symbolicLinkExists(otherFile))
		{
			if(otherFile.delete() == false)
				throw new Exception ("setData:Deleting "+otherFile+" failed.");
		}
	}

	/**
	 * Method to get the data stored in this lock file. This is done by reading the contents of a symbolic link,
	 * using readSymbolicLink. This should be the lock file, or it can be the temporary lock file if
	 * the lock file does not exist. getDataFromLinkContents is called to get the data from the
	 * link contents.
	 * @return The data string in the lock file.
	 * @exception Exception Thrown if getDataFromLinkContents fails.
	 * @exception FileUtilitiesNativeException Thrown if readSymbolicLink fails.
	 * @see FileUtilities#readSymbolicLink
	 * @see #getDataFromLinkContents
	 */
	public synchronized String getData() throws FileUtilitiesNativeException, Exception
	{
		String linkContents = null;

		if(FileUtilities.symbolicLinkExists(file))
			linkContents = FileUtilities.readSymbolicLink(file);
		else if(FileUtilities.symbolicLinkExists(otherFile))
			linkContents = FileUtilities.readSymbolicLink(otherFile);
		return getDataFromLinkContents(linkContents);
	}

	/**
	 * This internal method gets the data embedded in the lock files' symbolic links' content string.
	 * @param linkContents The contents of the symbolic link used as a lock file, obtained by
	 * 	using readSymbolicLink.
	 * @return The data string. This is the linkContents with the name of the lock removed.
	 * @exception Exception Thrown if the start of the linkContents is not the name of the lock file.
	 */
	private String getDataFromLinkContents(String linkContents) throws Exception
	{
		if(linkContents.startsWith(name+":") == false)
		{
			throw new Exception("Symbolic link has contents:"+linkContents+
					": Should start with:"+name+":");
		}
		return linkContents.substring(name.length()+1);// +1 for ":"
	}

	/**
	 * Test main program. Call as follows:
	 * <pre>
	 * java ngat.util.LockFile &lt;lock file&gt;  &lt;name&gt; 
	 * 	&lt;lock [data]|unlock|getdata|islocked|setdata &lt;data&gt;&gt;
	 * </pre>
	 * @param args The argument list.
	 */
	public static void main(String args[])
	{
		LockFile lf = null;
		String lockFile = null;
		String lockName = null;
		String operation = null;

		if(args.length < 3)
		{
			System.err.println("java ngat.util.LockFile <lock file> <name> "+
						"<lock [data]|unlock|islocked|getdata|setdata <data>>");
			System.exit(1);
		}
		lockFile = args[0];
		lockName = args[1];
		operation = args[2];
		lf = new LockFile();
		lf.setFilename(lockFile);
		lf.setName(lockName);
		try
		{
			if(operation.equals("lock"))
			{
				if(args.length == 4)
					lf.lock(args[3]);
				else
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
			else if(operation.equals("getdata"))
			{
				String currentData = null;

				currentData = lf.getData();
				System.out.println("Current Data:"+currentData);
			}
			else if(operation.equals("setdata"))
			{
				if(args.length == 4)
					lf.setData(args[3]);
				else
					System.err.println("setdata operation needs data.");
			}
			else
			{
				System.err.println("java ngat.util.LockFile <lock file> <name> "+
							"<lock [data]|unlock|islocked|getdata|setdata <data>>");
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
// Revision 1.1  2001/06/20 15:40:12  cjm
// Initial revision
//
//
