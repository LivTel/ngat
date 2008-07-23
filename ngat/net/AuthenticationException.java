package ngat.net;

import java.io.*;
/** Exception thrown by CryptoSource and Authenticator when illegal
 * or unauthenticated messages are received.
 *
 * $Id: AuthenticationException.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class AuthenticationException extends StreamCorruptedException {

    /** Create an AuthenticationException with no message.*/
    public AuthenticationException() { super(); }

    /** Create an AuthenticationException with specified message.
     * @param message A message to include.*/
    public AuthenticationException(String message) { super(message); }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/02/19 12:49:57  snf
/** Initial revision
/** */
