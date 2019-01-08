package ngat.rcs.comms;

import ngat.rcs.*;

import ngat.rcs.tmm.*;
import ngat.rcs.tmm.executive.*;
import ngat.rcs.tmm.manager.*;

import ngat.rcs.emm.*;

import ngat.rcs.scm.*;
import ngat.rcs.scm.collation.*;
import ngat.rcs.scm.detection.*;

import ngat.rcs.control.*;
import ngat.rcs.statemodel.*;

import ngat.rcs.iss.*;

import ngat.rcs.pos.*;
import ngat.rcs.tocs.*;
import ngat.rcs.science.*;
import ngat.rcs.calib.*;


import ngat.net.*;
import ngat.message.base.*;
import ngat.message.RCS_TCS.*;

/** Factory for generating handlers for TCS commands received from
 * the Robotic Control System. This implementation creates
 * subclasses of CIL_ProxyHandler which act as relays to pass the
 * command, via CIL to the TCS. <br>
 * This class can only be used via the singleton pattern, by calling
 * the static method getInstance(). A typical use might be as follows:-
 * <pre>
 *    ..
 *    ..
 *    RequestHandlerFactory factory = CIL_ProxyCommandImplFactory.getInstance();
 *    RequestHandler handler = factory.createHandler(someProtocolImpl, someCommand);
 *    ..
 *    ..
 * </pre>
 * <br><br>
 * $Id$
 */
public class CIL_ProxyHandlerFactory implements RequestHandlerFactory {

    private static CIL_ProxyHandlerFactory instance = null;
    
    public static CIL_ProxyHandlerFactory getInstance() {
	if (instance == null)
	    instance = new CIL_ProxyHandlerFactory();
	return instance;
    }
    
    /** Selects the appropriate handler for the specified command. 
     * May return <i>null</i> if the ProtocolImpl is not defined or not an
     * instance of JMSMA_ProtocolServerImpl or the request is not
     * defined or not an instance of RCS_TO_TCS. */
    public RequestHandler createHandler(ProtocolImpl serverImpl,
					Object request) {
	//System.out.println("CIL_HandlerFactory: request to create ProxyHandler: using SImpl:"+
	//serverImpl+" req:"+request+" isa "+request.getClass().getName());
	// Deal with undefined and illegal args.
	if ( (serverImpl == null) ||
	     ! (serverImpl instanceof JMSMA_ProtocolServerImpl) ) return null;
	if ( (request == null)    || 
	     ! (request instanceof RCS_TO_TCS) ) return null;
	
	// Cast to correct subclass.
	RCS_TO_TCS command = (RCS_TO_TCS) request;
	CIL_ProxyHandler handler = new CIL_ProxyHandler
	    ((JMSMA_ProtocolServerImpl)serverImpl, (RCS_TO_TCS) request);

	if (System.getProperty("TCS_MODE").equals("lt_sim")) {
	    handler.setTranslatorFactory(LT_Sim_CommandTranslatorFactory.getInstance());
	}  else {
	    handler.setTranslatorFactory(LT_RGO_TCS_CommandTranslatorFactory.getInstance());
	}
	return handler;
    }

    /** Private contructor for singleton instance.*/
    private CIL_ProxyHandlerFactory() {}

}    

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/04/27 17:14:32  snf
/** backup
/**
/** Revision 1.2  2000/12/22 14:40:37  snf
/** Backup.
/**
/** Revision 1.1  2000/12/12 18:50:00  snf
/** Initial revision
/** */
