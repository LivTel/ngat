// CircularListNode.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/CircularListNode.java,v 0.1 1999-12-10 14:52:34 cjm Exp $
package ngat.util;

import java.lang.*;

/**
 * This class holds all the information needed for each object in a circular list.<br>
 * This class was taken from <b>Java Threads</b>, an O'Reilly book by Scott Oaks and Henry Wong, Chapter 5, P90.
 * @version $Revision$
 */
public class CircularListNode
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * The object in the list.
	 */
	Object o;
	/**
	 * A reference to the next node in the list.
	 */
	CircularListNode next;
	/**
	 * A reference to the previous node in the list.
	 */
	CircularListNode prev;
}
//
// $Log: not supported by cvs2svn $
//
