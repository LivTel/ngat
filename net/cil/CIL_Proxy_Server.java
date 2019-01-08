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


import java.io.*;
import ngat.net.*;

/**
 * <br><br>
 * $Id$
 */
public class CIL_Proxy_Server extends SlotServer {

    /** The singleton instance of the CIL_Proxy_Server.*/
    public static CIL_Proxy_Server instance = null;

    /** Create the singleton instance of the CIL_Proxy_Server.*/
    private CIL_Proxy_Server() {
	super(JMSMA_ProtocolImplFactory.getInstance(),
	      CIL_ProxyHandlerFactory.getInstance());
    }
    
    /** @return The singleton instance of CIL_Proxy_Server.*/
    public static CIL_Proxy_Server getInstance() {
	if (instance == null)
	    instance = new CIL_Proxy_Server();
	return instance;
    }


}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/12/12 18:50:00  snf
/** Initial revision
/** */
