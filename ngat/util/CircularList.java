// CircularList.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/util/CircularList.java,v 0.1 1999-12-10 14:52:14 cjm Exp $
package ngat.util;

import java.lang.*;

/**
 * This class implements a circular linked list.<br>
 * This class was taken from <b>Java Threads</b>, an O'Reilly book by Scott Oaks and Henry Wong, Chapter 5, P90.
 * @version $Revision: 0.1 $
 */
public class CircularList
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: CircularList.java,v 0.1 1999-12-10 14:52:14 cjm Exp $");
	/**
	 * The current element in the list.
	 */
	private CircularListNode current;

	/**
	 * Insert an element in the list.
	 * @param o The element to insert.
	 */
	public synchronized void insert(Object o)
	{
		CircularListNode tn = new CircularListNode();
		tn.o = o;
		if(current == null)
		{
			tn.next = tn.prev = tn;
			current = tn;
		}
		else // add before current node
		{
			tn.next = current;
			tn.prev = current.prev;
			current.prev.next = tn;
			current.prev = tn;
		}
	}

	/**
	 * Delete the element from the list.
	 * @param o The element to delete.
	 */
	public synchronized void delete(Object o)
	{
		CircularListNode p = find(o);
		CircularListNode next = p.next;
		CircularListNode prev = p.prev;
		if(p == p.next) // last object on the list
		{
			current = null;
			return;
		}
		prev.next = next;
		next.prev = prev;
		if(current == p)
			current = next;
	}

	/**
	 * Internal method to find an element in the list.
	 * @param o The element to find.
	 * @return A reference to the node which contains object o.
	 */
	private CircularListNode find(Object o)
	{
		CircularListNode p = current;
		if(p == null)
			throw new IllegalArgumentException();
		do
		{
			if(p.o == o)
				return p;
			p = p.next;
		} while(p != current);
		throw new IllegalArgumentException();
	}

	/**
	 * Method to find an element in the list. The equals method is used to test for equivalence.
	 * @param o The element to find.
	 * @return The found element is returned.
	 */
	public synchronized Object locate(Object o)
	{
		CircularListNode p = current;
		do
		{
			if(p.o.equals(o))
				return p.o;
			p = p.next;
		} while(p != current);
		throw new IllegalArgumentException();
	}

	/**
	 * Get the next element in the list.
	 * @return The next element.
	 */
	public synchronized Object getNext()
	{
		if(current == null)
			return null;
		current = current.next;
		return current.o;
	}
}
//
// $Log: not supported by cvs2svn $
//
