package ngat.net;

/** Exception thrown by CryptoSource and Authenticator when illegal
 * or unauthenticated messages are received.
 *
 * $Id$
 */
public class InvalidUserException extends AuthenticationException {

    /** Create an InvalidUserException with no message.*/
    public InvalidUserException() { super(); }

    /** Create an InvalidUserException with specified message.
     * @param message A message to include.*/
    public InvalidUserException(String message) { super(message); }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/02/19 13:46:25  snf
/** Initial revision
/**
/** Revision 1.1  2001/02/19 12:49:57  snf
/** Initial revision
/** */
