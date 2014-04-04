package ngat.util;


/** Linked list implementation of the Queue interface.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/LinkedQueue.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public class LinkedQueue implements Queue {

    /** The head of the queue.*/
    protected Node head;

    /** The end of the queue.*/
    protected Node tail;

    /** Size of the queue.*/
    protected int size;

    /** Create a Linked Queue data structure.*/
    public LinkedQueue() {
	head = null;
	tail = null;
	size = 0;
    }

    /** Returns the size (no. of elements) in the queue at present.*/
    public int size() { return size; }

    /** Returns True if there are no (zero) elements in the queue.*/
    public boolean isEmpty() { return size == 0; }

    /** Returns the object at the head of the queue but without removing it.
     * @exception QueueEmptyException If the queue is empty*/
    public Object peek() throws QueueEmptyException {
	Object obj;
	if (size == 0)
	    throw new QueueEmptyException("Queue is empty.");
	obj = head.getElement();
	return obj;
    }
    /** Returns (and remove) the object at the head of the queue.
     * @exception QueueEmptyException If the queue is empty.*/
    public Object dequeue() throws QueueEmptyException {
	Object obj;
	if (size == 0)
	    throw new QueueEmptyException("Queue is empty.");
	obj = head.getElement();
	head = head.getNext();
	size--;
	if (size == 0)
	    tail = null;
	return obj;
    }

    /** Places an object at the tail of the queue.
     * @param data The object to place in the queue.*/
    public void enqueue(Object data) {
	Node n = new Node();
	n.setElement(data);
	n.setNext(null);
	if (size == 0)
	    head = n;
	else 
	    tail.setNext(n);
	tail = n;
	size++;
    }

    /** Returns the object at specified position in the queue.
     * Returns null if no element at that position.
     * @param pos The position to get the element from.*/
    public Object get(int pos) {
	if (pos < 0 || pos >= size) return null;
        Node n = head;
	for (int i = 0; i < pos; i++) {
	    n = n.getNext();
	}
	return n.getElement();
    }

    /** Holds a queue data element and a pointer to the next element in the queue.*/
    class Node {
	
	/** The element associated with this node.*/
	private Object element;

	/** The next node in sequence.*/
	private Node next;

	/** Create a Node with the specified parameters.
	 * @param element The element to set.
	 * @param next The next node in the queue.
	 */
	Node(Object element, Node next) {
	    this.element = element;
	    this.next = next;
	}
	
	/** Create an initially detached Node with no element.*/
	Node() {
	    this(null, null);
	}

	/** Set the element for this node.
	 * @param element The element to set.
	 */
	public void setElement(Object element) { this.element = element; }

	/** Returns the element for this node.*/
	public Object getElement() { return element; }

	/** Set the next node in the queue.
	 * @param next The next node in the queue.
	 */
	public void setNext(Node next) { this.next = next; }

	/** Returns the next node in the queue.*/
	public Node getNext() { return next; }
    }

}

/** $Log: not supported by cvs2svn $ */
