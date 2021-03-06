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


import ngat.util.*;
import ngat.util.logging.*;

import java.io.*;
import java.util.*;

/** Reads data from UDP socket connection to the TCS and distributes
 * responses to the associated ProxyHandlers. 
 * <br><br>
 * <b>## TBD - Maybe the ProxyHandler handoff needs to be asynch? ##</b>
 * <br><br>
 * $Id$
 */
public class CIL_ProxyReader {

    /** Default Polling interval for despatcher thread. Msec)*/
    public static final long DESPATCHER_POLLING_INTERVAL = 200;
    
    /** Message queue.*/
    List messages;
    
    /** Reference to the Reader thread.*/
    ControlThread reader;
    
    /** Reference to the Despatch thread.*/
    ControlThread despatcher;

    /** Polling interval for despatcher thread. Msec)*/
    private static long despatcherPollingInterval = DESPATCHER_POLLING_INTERVAL;

    Logger cilLogger;

    /** ######TEMP ######DEBUGGING ONLY*/
    volatile int qq = 0;
  
    /** Create a ProxyReader which loops until terminated.*/
    public CIL_ProxyReader() {
	messages   = new Vector();
	reader     = new Reader();
	despatcher = new Despatcher();
	cilLogger = LogManager.getLogger("CIL");	    	    
    }

    /** Start the Reader and Despatcher threads.*/
    public void start() {
	reader.start();
	despatcher.start();
    }

    /** Return a reference to the Despatcher thread.*/
    public ControlThread getDespatchThread() {  return despatcher; }
    
    /** Return a reference to the Reader thread.*/
    public ControlThread getReaderThread() { return reader; }

    /** Set the polling interval for despatcher thread. Msec)*/
    public static void setDespatcherPollingInterval(long dpi) {
	despatcherPollingInterval = dpi;
    }

    /** Reads from the CIL port.*/
    class Reader extends ControlThread {

	private CIL_Message message;

	/** Create a Reader.*/
	Reader() {
	    super("PROXY_READER", true);
	}
	
	/** Setup CIL_ProxyReader ReaderThread.*/
	public void initialise() {}


	/** Read messages from TCS and push into Queue.*/      
	public void mainTask() {
	    	   
	    // Wait on the UDP port for a TCS message.
	    // If the message fails ignore it.
	    try {
		//System.err.println("CIL Reader:: Waiting for next message");
		message = JCIL.receive();
		cilLogger.log(1, "CIL_Reader", "-", "-",
			   "CIL_Reader:: Read UDP Message: Class: "+message.getMessageClass()+
			      ", Seq: "+message.getSequenceNo());
		// log("Read response: "+ message);
	    } catch (Exception e) {
		if (JCIL.isBound()) {
		    cilLogger.log(2, "CIL_Reader", "-", "-",
				  "CIL_Reader:: No response received from JCIL via receive(): "+e);
		}
		// We dont log if unbound as we expect to fail....

		return;
	    }
	    
	    messages.add(message);
	    qq++;
	    cilLogger.log(3, "CIL_Reader", "-", "-",
			  "CIL_Reader:: Pushed 1 message, Q: "+qq);
	    
	}
	
	public void shutdown() {}

    }
    
    /** Despatches responses to handlers.*/
    class Despatcher extends ControlThread {	
	
	private CIL_ProxyHandler handler;
	
	private String data;

	private int    mClass;
	
	private CIL_Message message;
	
	/** Create a Despatcher.*/
	Despatcher() {
	    super("PROXY_DESPATCHER", true);	   
	}
	
	/** Setup CIL_ProxyReader DespatchThread.*/
	public void initialise() {}
	
	/**Read message from queue and pass on to the relevant handlers.
	 * If a message is errored we cant do much about it as we dont
	 * know who it was for or what it is. Its originator 
	 * (a CIL_ProxyHandler) should notice it hasn't arrived 
	 * at some point and get upset.
	 */
	public void mainTask() {

	    try {
		Thread.sleep(despatcherPollingInterval);
	    } catch (InterruptedException ix) {
		cilLogger.log(1, "CIL_Reader::Despatcher", "-", "-",
			      "CIL_Despatcher:: Interrupted polling message queue");
	    }

	    // Read from the Queue.
	    if (messages.size() == 0) {
		return;
	    } else {
		message = (CIL_Message)messages.remove(0);
		qq--;
	    }
	    			    
	    // Unpack the content.
	    int    seqNo   = message.getSequenceNo();
	    int    txId    = message.getTxId();	
	    int    rxId    = message.getRxId();	
	    int    mClass  = message.getMessageClass();
	    int    sClass  = message.getServiceClass();
	    String data    = message.getData();	 
	  
	    cilLogger.log(3, "CIL_Reader::Despatcher", "-", "-",
		       "CIL_Despatcher:: Pulled message: Class: "+mClass+", SN: "+seqNo+", Q: "+qq);
	    
	    // Request the handler for this sequence no.
	    handler = CIL_ProxyRegistry.lookup(seqNo);
	    
	    if (handler == null) {		    		
		cilLogger.log(1, "CIL_Reader::Despatcher", "-", "-",
			   "CIL_Despatcher:: No handler found for message: SN:"+seqNo+" - ignoring");
		return;
	    }

	    cilLogger.log(1, "CIL_Reader::Despatcher", "-", "-",
		       "CIL_Despatcher:: Received message: SN:"+seqNo+
			  ", Class: "+JCIL.toMClassString(mClass)+
			  ", For Handler: "+handler);
	    
	    try {
		switch (mClass) {
		case CIL_Message.ACK_CLASS:
		    handler.proxyAck(data);
		break;
		case CIL_Message.DONE_CLASS:
		    handler.proxyDone(data);
		    break;
		case CIL_Message.ERROR_CLASS:
		    handler.proxyError(data);
		    break;
		case CIL_Message.ACTION_CLASS:
		    handler.proxyAck(data);
		break;
		case CIL_Message.RESPONSE_CLASS:
		    handler.proxyDone(data);
		    break;
		default:
		}
	    
		cilLogger.log(1, "CIL_Reader::Despatcher", "-", "-",
			      "CIL_Despatcher:: Completed processing of message: SN:"+seqNo+
			      ", Class: "+JCIL.toMClassString(mClass));
	    } catch (Exception e) {
		cilLogger.log(1, "CIL_Reader::Despatcher", "-", "-",
			      "CIL_Despatcher:: Error during handling of message: SN:"+seqNo+" : "+e+" - (stacktrace on stderr)");
		e.printStackTrace();
	    }

	} 

	public void shutdown() {}
	
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/06/08 16:27:27  snf
/** Added GRB_ALERT.
/**
/** Revision 1.2  2001/04/27 17:14:32  snf
/** backup
/**
/** Revision 1.1  2000/12/12 18:50:00  snf
/** Initial revision
/**
/** Revision 1.1  2000/12/07 13:56:16  snf
/** Initial revision
/**
/** Revision 1.3  2000/11/30 15:36:17  snf
/** Updated CIL_Message calls.
/**
/** Revision 1.2  2000/11/29 15:46:05  snf
/** Changed init method.
/**
/** Revision 1.1  2000/11/28 18:15:24  snf
/** Initial revision
/** */
