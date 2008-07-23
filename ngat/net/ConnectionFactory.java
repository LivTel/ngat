package ngat.net;


/** ConnectionFactory is implemented by any class which needs to 
 * create IConnections to nominated susbsystems. The connection is
 * typically based on some concept of designated resources which can
 * be queried in order to decide how/what type the connection should be.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: ConnectionFactory.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/ConnectionFactory.java,v $
 * </dl>
 * @author $Author: eng $
 * @version $Revision: 1.1 $
 */
public interface ConnectionFactory {
     
    /** Create an IConnection of unspecified type to the named resource. The 
     * implementation may choose how it uses the resource name to locate a
     * connection. The type of connection returned is also completely upto
     * any implementor. E.g. a table of resouce-name against host and port nos
     * could be maintained etc.
     * @param name The name/id of the resource to connect to. 
     * @exception UnknownResourceException If the named resource is not known.*/
    public IConnection createConnection(String name) throws UnknownResourceException;
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/07/03 14:57:41  snf
/** Fixed javadoc error.
/**
/** Revision 1.2  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/02/02 08:52:15  snf
/** Initial revision
/** */
