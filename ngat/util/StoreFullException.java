package ngat.util;

/**
 * Exception thrown when an attempt is made to write to a full SharedStore.
 *
 * $Id: StoreFullException.java,v 1.1 2000-11-21 16:59:08 snf Exp $
 *
 */
public class StoreFullException extends Exception {

    public StoreFullException() { super();}

    public StoreFullException(String msg) { super(msg);}

}

/** $Log: not supported by cvs2svn $ */
