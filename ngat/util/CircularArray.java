package ngat.util;

import java.util.Enumeration;
/**
 * Implementation of a fixed length array for storing
 * Objects. The array grows as objects are added up
 * until the specified number of elements is inserted
 * thereafter as additional elements are added, the 
 * first (i.e. oldest) elements are overwritten.
 *
 * This implementation does not provide a mechanism for 
 * removing elements.
 *
 * $Id: CircularArray.java,v 1.1 2000-11-21 17:37:40 snf Exp $
 *
 */
public class CircularArray {
    
    /** Size of the array.*/
    private int nn;
    
    /** Underlying container for the data.*/
    private Object[] array;
    
    /** Records the first element position in array.*/
    private int start;
    
    /** Records the next element position in array.*/
    private int next;

    /** Records the position of the last element.*/
    private int last;
    
    /** Create a CircularArray of length NN-1 elements.
     * @param nn The wrap length of the Array plus 1.*/
    public CircularArray(int nn) {
	this.nn = nn;
	array = new Object[nn];
	for (int  i = 0; i < nn; i++) {
	    array[i] = new Object();
	}
	start = 0;
	next  = 0;
    }
    
    /** Returns the size of the underlying array which stores
     * the objects. This is also the wrap length. */
    public int getSize() { return nn+1;}

    /** Returns the actual number of set elements.
     * NOTE: This may not work correctly - NEEDS TESTING ! */
    public int getCount() { return (next + nn - start)%nn; }
    
    /** Insert the specified object (at the end of the array). */
    public void insert(Object stuff) {
	last = next;
	array[next] = stuff;
	// Always increment <next>.
	next = (next + 1)%nn;	
	// Increment <start> if <next> collides with it.
	if (start == next) {	    
	    start = (start + 1)%nn;
	}	
    }
    
    /** Returns the 'first' element in the list.*/
    public Object first() { return array[start]; }

    /** Returns the 'last' element in the list.*/
    public Object last() { return array[last]; }
	
    /** Returns an enumeration of the elements from start to last.*/
    public Enumeration enumerate() { return new Enumerator();}
    
    private class Enumerator implements Enumeration {
	
	private int count;
	
	private int temp;
	
	Enumerator() {count = start;}
	
	public boolean hasMoreElements() {
	    if (count == next) return false;
	    return true;
	}
	
	public Object nextElement() {
	    temp = count;
	    count = (count+1)%nn;
	    return array[temp];
	}
	
    }
    
}

/** $Log: not supported by cvs2svn $ */
