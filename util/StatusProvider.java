package ngat.util;

/** 
*
* <dl>	
* <dt><b>RCS:</b>
* <dd>$Id$
* <dt><b>Source:</b>
* <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StatusProvider.java,v $
* </dl>
* @author $Author$
* @version $Revision$
*/

public interface StatusProvider {

    /** Implementors should return an instance of a concrete implemention 
     * of StatusCategory.
     */
    public StatusCategory getStatus();

}

/** $Log: not supported by cvs2svn $ */
