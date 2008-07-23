package ngat.net;

import java.io.IOException;

/**
 * Generic interface for classes which wish to act as
 * protocol implementors.
 * <br>
 * $Id: ProtocolImpl.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public interface ProtocolImpl {
    
    /** Override to provide the implementation of the protocol from 
     *the client or server perspective.*/
    public void implement();

    /** Cancel the implementation of the protocol immediately - if possible.*/
    public void cancel();
    
} 

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/05/14 15:35:43  snf
/** Added cancel method.
/**
/** Revision 1.3  2000/11/28 11:18:14  snf
/** Removed exception from implement() method.
/**
/** Revision 1.2  2000/11/23 10:56:22  snf
/** Changed exception thrown by implement to generic IO.
/**
/** Revision 1.1  2000/11/23 10:37:56  snf
/** Initial revision
/** */
