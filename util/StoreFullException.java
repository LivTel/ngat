package ngat.util;

/**
 * Exception thrown when an attempt is made to write to a full SharedStore.
 *
 * $Id$
 *
 */
public class StoreFullException extends Exception {

    public StoreFullException() { super();}

    public StoreFullException(String msg) { super(msg);}

}

/** $Log: not supported by cvs2svn $ */
