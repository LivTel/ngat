/**
 * A first in-first out queue that will block on both the <code>remove</code>
 * and <code>peek</code> methods until they can succeed.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
/* $Date: 2002-09-25 10:07:12 $
 * $RCSfile: BlockingQueue.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/util/BlockingQueue.java,v $
 * $Log: not supported by cvs2svn $
 */

package ngat.util;

import java.util.*;

public class BlockingQueue
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: BlockingQueue.java,v 1.1 2002-09-25 10:07:12 je Exp $" );

    /**
     * The Queue.
     */
    private Vector queue;

    /**
     * The size of the queue.
     */
    private int size;


    /**
     * Create a queue of pre-determined size.
     */
    public BlockingQueue( int capacity )
    {
	queue = new Vector( capacity );
	size = 0;
    }


    /**
     * Create a queue.
     */
    public BlockingQueue()
    {
	queue = new Vector();
	size = 0;
    }


    /**
     * Pushes an Object to tail of queue.
     */
    public synchronized void push( Object o ) throws InterruptedException
    {
	queue.add( o );
	size++;
	notifyAll();
    }

    /**
     * Removes and returns head object - blocks until non-empty.
     */
    public synchronized Object remove() throws InterruptedException
    {
	while ( size == 0 )
	    {
		wait();
	    }
	Object o = queue.remove( 0 );
	size--;
	notifyAll();
	return o;
    }

    /**
     * Peeks at head object - blocks until non-empty.
     */
    public synchronized Object peek() throws InterruptedException
    {
	while( size == 0 )
	    {
		wait();
	    }
	Object o = queue.get( 0 );
	notifyAll();
	return o;
    }
}
