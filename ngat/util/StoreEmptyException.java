package ngat.util;

/**
 * Exception thrown when an attempt is made to read from an empty SharedStore.
 *
 * $Id: StoreEmptyException.java,v 1.1 2000-11-21 16:59:35 snf Exp $
 *
 */
public class StoreEmptyException extends Exception {

    public StoreEmptyException() { super();}

    public StoreEmptyException(String msg) { super(msg);}

}

/** $Log: not supported by cvs2svn $ */
