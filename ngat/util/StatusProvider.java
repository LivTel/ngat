package ngat.util;

/** 
*
* <dl>	
* <dt><b>RCS:</b>
* <dd>$Id: StatusProvider.java,v 1.1 2006-11-20 14:35:17 cjm Exp $
* <dt><b>Source:</b>
* <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StatusProvider.java,v $
* </dl>
* @author $Author: cjm $
* @version $Revision: 1.1 $
*/

public interface StatusProvider {

    /** Implementors should return an instance of a concrete implemention 
     * of StatusCategory.
     */
    public StatusCategory getStatus();

}

/** $Log: not supported by cvs2svn $ */
