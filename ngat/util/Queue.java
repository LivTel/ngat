package ngat.util;


/** Interface to define a queue data structure.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: Queue.java,v 1.1 2001-07-11 10:24:23 snf Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/Queue.java,v $
 * </dl>
 * @author $Author: snf $
 * @version $Revision: 1.1 $
 */
public interface Queue {

    /** Override to return the size (no. of elements) in the queue at present.*/
    public int size();

    /** Override to return True if there are no (zero) elements in the queue.*/
    public boolean isEmpty();

    /** Override to return the object at the head of the queue but without removing it.
     * @exception QueueEmptyException If the queue is empty*/
    public Object peek() throws QueueEmptyException ;

    /** Override to return (and remove) the object at the head of the queue.
     * @exception QueueEmptyException If the queue is empty.*/
    public Object dequeue() throws QueueEmptyException;

    /** Override to place an object at the tail of the queue.
     * @param data The object to place in the queue.*/
    public void enqueue(Object data);

    /** Override to return the object at specified position in the queue.
     * Returns null if no element at that position.
     * @param pos The position to get the element from.*/
    public Object get(int pos);

}

/** $Log: not supported by cvs2svn $ */
