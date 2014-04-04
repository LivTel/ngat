package ngat.util;

/**
 * Exception thrown when an attempt is made to read from an empty SharedStore.
 *
 * $Id$
 *
 */
public class StoreEmptyException extends Exception {

    public StoreEmptyException() { super();}

    public StoreEmptyException(String msg) { super(msg);}

}

/** $Log: not supported by cvs2svn $ */
