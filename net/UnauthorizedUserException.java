package ngat.net;

/** Exception thrown by CryptoSource and Authenticator when illegal
 * or unauthenticated messages are received.
 *
 * $Id$
 */
public class UnauthorizedUserException extends AuthenticationException {

    /** Create an UnauthorizedUserException with no message.*/
    public UnauthorizedUserException() { super(); }

    /** Create an UnauthorizedUserException with specified message.
     * @param message A message to include.*/
    public UnauthorizedUserException(String message) { super(message); }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/02/19 13:47:24  snf
/** Initial revision
/**
/** Revision 1.1  2001/02/19 12:49:57  snf
/** Initial revision
/** */
