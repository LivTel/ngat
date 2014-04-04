// PersistentUniqueInteger.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/PersistentUniqueInteger.java,v 1.2 2001-07-31 10:56:49 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * This class allows us to control a persistent unique integer. This is
 * <ul>
 * <li><b>Persistent</b> The value of the number is always stored on disc. If the program
 * 	is quit or crashes, the number will be re-loaded from disc when it is re-started.
 * 	As it is on disc, more than one process/JVM can see the number.
 * <li><b>Unique</b> This particular class was designed to supply numbers that never occur more than
 * 	once. e.g. two calls to <i>increment()</i> on the same filename should never return the same number.
 * 	The <i>set()</i> call actually could be used to invalidate this property. e.g. Only call <i>set()</i> to
 * 	initialise a new PersistentUniqueInteger.
 * </ul>
 * The underlying file used is a <i>LockFile</i>. This class does not extend LockFile, or allow access to the
 * LockFile instance, so that access is denied to LockFile.unLock(), which would allow deletion of this
 * persistent integer.
 * @see LockFile
 * @see LockFile#unLock
 * @version $Revision$
 * @author Chris Mottram
 */
public class PersistentUniqueInteger
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Default name used for the lock file name paramater.
	 * This is embeddded into the symbolic link. 
	 */
	public final static String DEFAULT_NAME = "Integer";
	/**
	 * The lock file used to hold the unique integer.
	 * <b>Do not call lockfile.unLock() with this reference.</b> This would
	 * delete the persistent integer.
	 * @see LockFile#unLock
	 */
	protected LockFile lockFile = null;

	/**
	 * Default constructor. This constructs the lock file, sets the filename and name.
	 * @param lockFileName The filename of the lock file that holds the unique integer.
	 * @see #lockFile
	 * @see #DEFAULT_NAME
	 */
	public PersistentUniqueInteger(String lockFileName)
	{
		super();
		lockFile = new LockFile();
		lockFile.setFilename(lockFileName);
		lockFile.setName(DEFAULT_NAME);
	}

	/**
	 * <b>This routine should not normally be used.</b>
	 * This method allows us to set the current value of the persistent unique integer
	 * to any value. This could potentially allow us to reset the integer to a number
	 * that has been previously used (losing the unique property of the integer).
	 * This method exists to allow the initialisation of a persistent unique integer to a value
	 * other than zero. It should be called after the constructor, but before the
	 * first call to get() or increment(), the first time the  persistent unique integer
	 * is to be used.
	 * @param n The value to set the persistent unique integer to.
	 * @see LockFile#setData
	 * @exception FileUtilitiesNativeException Thrown in LockFile.setData() fails.
	 * @exception Exception Thrown if LockFile.setData() fails.
	 */
	public synchronized void set(int n) throws FileUtilitiesNativeException, Exception
	{
		lockFile.setData(""+n);
	}

	/**
	 * Method to increment and return the persistent unique integer.
	 * The current value is retrieved using get (which initialises the number to zero if the
	 * underlying file does not exist). It is incremented, and the lock file's data then set.
	 * The new integer is then returned.
	 * @return The incremented persistent unique integer value.
	 * @see #get
	 * @see LockFile#setData
	 * @exception FileUtilitiesNativeException Thrown in LockFile.setData/get fails.
	 * @exception NumberFormatException Thrown if get() fails.
	 * @exception Exception Thrown if LockFile.setData()/get() fails.
	 */
	public synchronized int increment() throws FileUtilitiesNativeException, NumberFormatException, Exception
	{
		int currentNumber;

		currentNumber = get();
		currentNumber++;
		lockFile.setData(""+currentNumber);
		return currentNumber;
	}

	/**
	 * Method to get the current value of the number.
	 * If the lock file does not exist it is created with data '0'.
	 * The lock file's data is then read, and parsed into an integer, which is returned.
	 * @return The current persistent unique integer value.
	 * @exception FileUtilitiesNativeException Thrown in LockFile.setData()/LockFile.getData() fails.
	 * @exception NumberFormatException Thrown if Integer.parseInt() fails.
	 * @exception Exception Thrown if LockFile.setData()/LockFile.getData() fails.
	 * @see #lockFile
	 * @see LockFile#isLocked
	 * @see LockFile#setData
	 * @see LockFile#getData
	 * @see java.lang.Integer#parseInt
	 */
	public synchronized int get() throws FileUtilitiesNativeException, NumberFormatException, Exception
	{
		String currentString;
		int currentNumber;

	// lazy initialisation to prevent NullPointerException
		if(lockFile.isLocked() == false)
			lockFile.setData("0");
	// get data
		currentString = lockFile.getData();
	// parse data to number
		currentNumber = Integer.parseInt(currentString);
		return currentNumber;
	}

	/**
	 * Test main program. Call as follows:
	 * <pre>
	 * java ngat.util.PersistentUniqueInteger &lt;filename&gt; &lt;increment|get|set &lt;n&gt;&gt;
	 * </pre>
	 * @param args The argument list.
	 */
	public static void main(String args[])
	{
		PersistentUniqueInteger pui = null;
		String filename = null;
		String operation = null;

		if(args.length < 2)
		{
			System.err.println("java ngat.util.PersistentUniqueInteger <filename> "+
						"<increment|get|set <n>>");
			System.exit(1);
		}
		filename = args[0];
		operation = args[1];
		pui = new PersistentUniqueInteger(filename);
		try
		{
			if(operation.equals("increment"))
			{
				int currentNumber = -1;

				currentNumber = pui.increment();
				System.out.println("Current Number Now:"+currentNumber);
			}
			else if(operation.equals("get"))
			{
				int currentNumber = -1;

				currentNumber = pui.get();
				System.out.println("Current Number:"+currentNumber);
			}
			else if(operation.equals("set"))
			{
				int number;

				if(args.length != 3)
				{
					System.err.println("set requires a number.");
					System.exit(1);
				}
				number = Integer.parseInt(args[2]);
				pui.set(number);
				System.out.println("Number set to:"+number);
			}
			else
			{
				System.err.println("java ngat.util.PersistentUniqueInteger <filename> "+
						"<increment|get|set <n>>");
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
