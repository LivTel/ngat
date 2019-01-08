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
 * $Id$
 *
 */
public class CircularArray {
    
    /** Size of the array.*/
    private int nn;
    
    /** Stores the current size of the array.*/
    private int count;

    /** Underlying container for the data.*/
    private Object[] array;
    
    /** Records the first element position in array.*/
    private int start;
    
    /** Create a CircularArray of length NN-1 elements.
     * @param nn The wrap length of the Array plus 1.*/
    public CircularArray(int nn) {
	this.nn = nn;
	array = new Object[nn];
	for (int  i = 0; i < nn; i++) {
	    array[i] = new Integer(555);
	}
	start = 0;
	count = 0;
    }
    
    /** Returns the size of the underlying array which stores
     * the objects. This is also the wrap length. */
    public int getSize() { return nn;}

    /** Returns the actual number of set elements.
     * NOTE: This may not work correctly - NEEDS TESTING ! */
    public int getCount() { 
	return count;
	// ## return (next + nn - start)%nn;
    }
    
    /** Insert the specified object (at the end of the array). */
    public void insert(Object stuff) {
	// Increment count unless full.
	if (count < nn) {
	    count++;
	} else {
	    start = (start + 1) % nn;
	}
	array[(start + count - 1) % nn ] = stuff;	
    }
    
    /** Returns the 'first' element in the list.*/
    public Object first() { 
	if (count == 0 )
	    return null;return array[start]; 
    }
    
    /** Returns the 'last' element in the list.*/
    public Object last() { 
	if (count == 0 )
	    return null;
	return array[(start + count - 1) % nn]; 
    }
    
    /** Returns an enumeration of the elements from start to last.*/
    public Enumeration enumerate() { return new Enumerator(); }
    
    /** Returns an enumeration of the last n elements.*/
    public Enumeration reverse(int n) { return new Reverser(n); }

    /** Inner class to represent an Enumeration of the array's content.*/
    private class Enumerator implements Enumeration {
	
	private int ct;
	
	Enumerator() { ct = 0; }
	
	public boolean hasMoreElements() {
	    if (ct == count) return false;
	    return true;
	}
	
	public Object nextElement() {	  
	    ct++;
	    return array[ (start + ct - 1) % nn ];  
	}
	
    }
    
    /** Inner class to represent a reverse Enumeration of the array's
     * contents over, upto a set number of elements.*/
    private class Reverser implements Enumeration {
     
	private int ct;

	private int n;
	
	Reverser(int no) { 
	    n = no;
	    if (no > count)
		n = count;	    
	    ct = count;
	}
	
	public boolean hasMoreElements() {
	    if (ct == 0) return false;
	    return true;
	}
	
	public Object nextElement() {
	    int at = ct;
	    ct--;
	    return array[ (start + at - 1) % nn ];	    
	}
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/02/23 18:49:14  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/01/04 11:10:29  snf
/** Modified counter.
/**
/** Revision 1.1  2000/11/21 17:37:40  snf
/** Initial revision
/** */
