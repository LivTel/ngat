// FileUtilities.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/FileUtilities.java,v 1.2 2001-07-31 10:56:24 cjm Exp $
package ngat.util;

import java.io.*;
import java.lang.*;

/**
 * Some useful file routines.
 * There are methods to create/read/test existence of symbolic links.
 * @version $Revision: 1.2 $
 * @author Chris Mottram
 */
public class FileUtilities
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: FileUtilities.java,v 1.2 2001-07-31 10:56:24 cjm Exp $");
	/**
	 * Default size of a buffer to read the contents of symbolic link into.
	 */
	protected final static int DEFAULT_READ_LINK_BUFFER_SIZE = 256;

	/**
	 * Static code to load libngatutil, the shared C library that implements an interface to the
	 * native routines this class uses.
	 */
	static
	{
		System.loadLibrary("ngatutil");
	}

	/**
	 * Internal native method. The Java JNI interface to the C code that
	 * actually calls the C symlink routine.
	 * @param linkContents A string which contains the characters to put into the
	 * 	link. Normally this is another pathname to a file, but a <i>dangling link</i>
	 * 	can also be created, where the link just contains data.
	 * @param linkFilename A string representing a valid filename.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs whilst creating the symbolic link.
	 */
	private static native void symlink(String linkContents,String linkFilename) 
		throws FileUtilitiesNativeException;
	/**
	 * Internal native method. The Java JNI interface to the C code that
	 * actually calls the C readlink routine.
	 * @param linkFilename A string representing a valid filename.
	 * @param bufferLength The length of buffer to allocate in the C routine, which must be long enough
	 * 	to receive the contents of the link (what pathname/data it points to).
	 * @return A String is returned. It contains the current contents of the link.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs whilst the link is read.
	 */
	private static native String readlink(String linkFilename,int bufferLength) 
		throws FileUtilitiesNativeException;
	/**
	 * Internal native method. The Java JNI interface to the C code that
	 * actually calls the C readlink routine.
	 * @param linkFilename A string representing a valid filename.
	 * @return A boolean is returned, true if the filename exists as a link, false otherwise. 
	 */
	private static native boolean symlink_lstat_exists(String linkFilename);
	/**
	 * This method creates a symbolic link. The link has a filename linkFilename,
	 * and has contents (points to) linkContents.
	 * @param linkContents A string which contains the characters to put into the
	 * 	link. Normally this is another pathname to a file, but a <i>dangling link</i>
	 * 	can also be created, where the link just contains data.
	 * @param linkFilename A string representing a valid filename.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs in symlink.
	 * @see #symlink
	 */
	public static void createSymbolicLink(String linkContents,String linkFilename) 
		throws FileUtilitiesNativeException
	{
		symlink(linkContents,linkFilename);
	}

	/**
	 * This method creates a symbolic link. The link has a filename link,
	 * and has contents (points to) linkContents.
	 * @param linkContents A string which contains the characters to put into the
	 * 	link. Normally this is another pathname to a file, but a <i>dangling link</i>
	 * 	can also be created, where the link just contains data.
	 * @param linkFilename An instance of File representing a valid filename.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs in symlink.
	 * @see #symlink
	 */
	public static void createSymbolicLink(String linkContents,File link) throws FileUtilitiesNativeException
	{
		symlink(linkContents,link.getAbsolutePath());
	}

	/**
	 * Method to read the contents of a symbolic link.
	 * @param linkFilename A string representing a valid filename.
	 * @return The current contents of the link are returned.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs in readlink.
	 * @see #readlink
	 * @see #DEFAULT_READ_LINK_BUFFER_SIZE
	 */
	public static String readSymbolicLink(String linkFilename) throws FileUtilitiesNativeException
	{
		String retval = null;

		retval = readlink(linkFilename,DEFAULT_READ_LINK_BUFFER_SIZE);
		return retval;
	}

	/**
	 * Method to read the contents of a symbolic link.
	 * @param link An instance of File representing a valid filename.
	 * @return The current contents of the link are returned.
	 * @exception FileUtilitiesNativeException Thrown if an error occurs in readlink.
	 * @see #readlink
	 * @see #DEFAULT_READ_LINK_BUFFER_SIZE
	 */
	public static String readSymbolicLink(File link) throws FileUtilitiesNativeException
	{
		String retval = null;

		retval = readlink(link.getAbsolutePath(),DEFAULT_READ_LINK_BUFFER_SIZE);
		return retval;
	}

	/**
	 * Method to see if a symbolic link exists. We cannot use java.io.File.exists() for this,
	 * as this follows the link to see if that exists.
	 * @param linkFilename A String representing a valid filename.
	 * @return A boolean. This is true if the link exists, false if it does not or an error occured.
	 * @see #symlink_lstat_exists
	 */
	public static boolean symbolicLinkExists(String linkFilename)
	{
		return symlink_lstat_exists(linkFilename);
	}

	/**
	 * Method to see if a symbolic link exists. We cannot use java.io.File.exists() for this,
	 * as this follows the link to see if that exists.
	 * @param link An instance of File representing a valid link filename.
	 * @return A boolean. This is true if the link exists, false if it does not or an error occured.
	 * @see #symlink_lstat_exists
	 */
	public static boolean symbolicLinkExists(File link)
	{
		return symlink_lstat_exists(link.getAbsolutePath());
	}

	/**
	 * Test main program. Call as follows:
	 * <pre>
	 * java ngat.util.FileUtilities &lt;file&gt; &lt;createsymlink &lt;to&gt;|readsymlink&gt;
	 * </pre>
	 * @param args The argument list.
	 */
	public static void main(String args[])
	{
		String filename = null;
		String operation = null;

		if(args.length < 2)
		{
			System.err.println("java ngat.util.FileUtilities <file> "+
				"<createsymlink <to>|readsymlink|symlinkexists>");
			System.exit(1);
		}
		filename = args[0];
		operation = args[1];
		try
		{
			if(operation.equals("createsymlink"))
			{
				if(args.length == 3)
					createSymbolicLink(args[2],filename);
				else
					System.err.println("createsymlink operation needs a to filename.");
			}
			else if(operation.equals("readsymlink"))
			{
				String s = null;

				s = readSymbolicLink(filename);
				System.out.println("Symbolic Link "+filename+" points to "+s);
			}
			else if(operation.equals("symlinkexists"))
			{
				boolean exists;

				exists = symbolicLinkExists(filename);
				System.out.println("Symbolic Link "+filename+" exists: "+exists);
			}
			else
			{
				System.err.println("java ngat.util.FileUtilities <file> "+
						"<createsymlink <to>|readsymlink|symlinkexists>");
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
// Revision 1.1  2001/07/31 09:47:10  cjm
// Initial revision
//
// Revision 1.1  2001/06/20 15:40:12  cjm
// Initial revision
//
//
