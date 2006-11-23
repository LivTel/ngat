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
import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.base.*;
import ngat.message.RCS_TCS.*;

/**
 * Implements the RCS Communications Proxy Layer conversation handler.
 * This acts as the conversation handler for the Proxy layer for a given
 * command sent from the RCS to the TCS. It handles the result sent back
 * from the TCS converting to an appropriate ACK and COMMAND_DONE. The 
 * response is sent back to the client using the JMSMA_ProtocolServerImpl's
 * sendDone() method via its connection. 
 * <br><br>
 * $Id: CIL_ProxyHandler.java,v 1.1 2006-11-23 10:37:57 snf Exp $
 */
public class CIL_ProxyHandler implements RequestHandler { 

    public static double FUDGE = 1.1;
    
    public static long DEFAULT_TIMEOUT = 10000L;

    /** ERROR_BASE for this Utility type.*/
    public static final int ERROR_BASE = 6100;

    public static int CIL_TRANSMIT_ERROR = 606101;

    /** Reference to the ServerImplementation for this handler.*/
    protected JMSMA_ProtocolServerImpl serverImpl;

    /** The factory for translating commands into strings.*/
    protected CommandTranslatorFactory translatorFactory;
    
    /** The RCS_TCS command to be sent to the TCS.*/
    protected RCS_TO_TCS command;

    /** Object to synch on.*/
    protected Object synch;

    protected static Logger commandLog = LogManager.getLogger("COMMAND");

    protected static int logLevel = 0;
    
    /** Indicates that the command has completed or failed. i.e. the TCS
     * has sent either a <i>completed</i> or an <i>error</i> message.*/
    protected volatile boolean completed;

    /** Stores the sequence number obtained from the Registry for this handler.*/
    protected int seqNo;
    
    /** A period at which the handler should timeout and tell the server
     * to send an ACK(timeout) to its client to keep it alive. The timeout 
     * should be set by the handler based on the actual command received
     * and any relevant telescope/TCS/RCS parameters which should allow the
     * handler to <i>estimate</i> the time the command will actually take.
     * <br><pre>
     * e.g. Given that a SLEW command is being sent. The following data
     *      will give an estimate of the time required.
     *      - current telescope alt/az/rot. (from mechanism sensors.)
     *      - requested source position.    (from mechanism sensors.)
     *      - time to limits info?.         (from limit sensors.)
     *      - some float to allow for latency in network/other systems.
     * </pre>
     * This information should have been calculated by a higher level
     * task e.g. the <b>ObservationTask</b> which will have originated the
     * SLEW_Task that is acting as client for this command. It should 
     * already have set its own (and the SLEW_Task's) timeout based
     * on this info.
     */
    protected long handlingTime;

    protected static ConfigurationProperties config;

    /** Reference to the SCT which created and is running this.*/
    protected Thread runner;

    /** Create a Proxyhandler for the specified COMMAND and server.
     * Calls calculateTimeout() and sets handling Time to this value.
     * Subclasses should override calculateTimeout() if they want to
     * set it here. It is still possible to reset it later if desired.
     * @param serverImpl The ProtocolImpl which has invoked this handler.
     * @param command The RCS_TCS COMMAND which is to be sent and
     * for which the response is to be handled. .*/
    public CIL_ProxyHandler(JMSMA_ProtocolServerImpl serverImpl, RCS_TO_TCS command) {
	this.serverImpl = serverImpl;
	this.command = command;
	completed    = false;
	runner       = Thread.currentThread();
	synch        = new Object();	
	setHandlingTime(calculateTimeout());
    }
   
    /** Configures the CIL_ProxyHandler from a file.*/
    public static void configure(File file) throws IOException, IllegalArgumentException {
	config = new ConfigurationProperties();
	config.load(new FileInputStream(file));	
	int rcsid = config.getIntValue("rcs.id", 18);
	CIL_Message.RCS_ID = rcsid;
	int tcsid = config.getIntValue("tcs.id", 17);
	CIL_Message.TCS_ID = tcsid;

    }

    /** Causes the handler to contact the TCS, passing on the command and
     * waiting for ACK and DONE messages.
     * This method simulates the JMS(MA) protocol by sending ACKs
     * to the ServerImpl at the nominated timeout interval. The 
     * ProxyReader will send the initial (real) ACK when/if the TCS
     * sends it a CIL 'acknowledge' message. When the CIL 'completed'
     * or CIL 'response' or CIL 'error' message is received the 
     * ProxyReader will send it via proxyDone() or proxyError() which
     * causes the execution thread to be interrupted. The thread then pushes
     * the DONE to the server to force it to forward it to its client.*/   
    public void handleRequest() { 

	//log("Handling request: "+command.getId());

	// Register for (and get) a CIL sequence number.
	seqNo = CIL_ProxyRegistry.registerSequenceNo(this);
	
	//log("Got sequence number: "+seqNo);

	String commandString = (String)translatorFactory.translateCommand(command);

	// Send the command. If it fails via an IOError tell
	// the server via ExceptionCallback mechanism and die.
	try {
	    JCIL.send(CIL_Message.RCS_ID,
		      CIL_Message.TCS_ID,
		      CIL_Message.COMMAND_CLASS, 
		      CIL_Message.SERVICE_TYPE, 
		      seqNo,
		      commandString);
	    //log("Sent command via CIL to TCS: "+command.getClass().getName());
	   
	} catch (IOException e) {
	    COMMAND_DONE error = new COMMAND_DONE(command.getId());
	    error.setSuccessful(false);
	    error.setErrorNum(CIL_TRANSMIT_ERROR); // This is temporary.
	    error.setErrorString("CIL_ProxyHandler:"+command.getId()+
				 ": sending command: IOException:"+e);
	    serverImpl.sendDone(error);
	    return;
	}
		
    }
    

    /** Calculates the timeout period. This should be overridden by
     * subclasses and may involve reference to external information.
     * @return The timeout period for this command in msecs.*/
    protected long calculateTimeout() { 	
	return config.getLongValue(command.getClass().getName(), DEFAULT_TIMEOUT);	
    }

    /** Sets (or resets) the timeout period for this handler. The method
     * obtains a synch lock before accessing the timeout field as this 
     * Thread may want to test it while it is being set by 
     * e.g. a ServerConnectionThread.
     * @param handling Time The value to set for timeout.*/
    public void setHandlingTime(long handlingTime) { 
	synchronized (synch) {
	    // System.err.println("Setting HTime for: "+command.getClass().getName()+
	    // " -> "+handlingTime);
	    this.handlingTime = handlingTime; 
	}
    }

    /** Returns the handling Time period for this handler. The method
     * obtains a synch lock before accessing the handling Time field
     * as other Threads may want to reset it while this Thread 
     * is reading it.*/
    public long getHandlingTime() {
	synchronized (synch) {   
	    return handlingTime;
	}
    }

    /** Passes a TCS-Acknowledge message back from the 
     * TCS to be parsed for information. The RGO-TCS does not pass
     * any timing information back so the ProxyHandler has to decide
     * this by itsself.*/
    public void proxyAck(String strAck) {
	//log("Proxy ack: "+strAck);
	ACK ack = new ACK(command.getId());
	//ACK ack = (ACK)translatorFactory.translateAck(command, strAck);
	ack.setTimeToComplete((int)(FUDGE*(double)getHandlingTime()));
	//System.err.println("Relaying ACK with ttc: "+ack.getTimeToComplete());
	if (serverImpl != null)
	    serverImpl.sendAck(ack);
    }
    
    /** Passes a TCS-Completed message back from the 
     * TCS to be parsed for DONE information. Interrupts the
     * current execution thread to break out of the wait/sleep.*/
    public void proxyDone(String strDone) {
	COMMAND_DONE done = (COMMAND_DONE)translatorFactory.translateResponse(command, strDone);
	if (serverImpl != null)
	    serverImpl.sendDone(done);
	completed = true;
	//runner.interrupt();
    }
    
    /** Passes a TCS-Error message back from the 
     * TCS to be parsed for DONE information. Interrupts the
     * current execution thread to break out of the wait/sleep.*/
    public void proxyError(String strError) {
	//log("Proxy error: "+strError);
	COMMAND_DONE error = (COMMAND_DONE)translatorFactory.translateResponse(command, strError);
	if (serverImpl != null)
	    serverImpl.sendDone(error);
	completed = true;
	//runner.interrupt();
    }
    
    /** Handles any exceptions thrown by the server !
     * Not really bothered for now.*/
    public void exceptionOccurred(Object source, Exception e) {
	log("Exception handler: src: "+source+" ex: "+e);
    }

    /** @return The command translator factory.
     * @return The currently set translator factory.*/
    protected CommandTranslatorFactory getTranslatorFactory() { return translatorFactory; }
    
    /** Set the factory for translating commands into Strings.
     * @param translatorFactory The factory to set.*/
    protected void setTranslatorFactory(CommandTranslatorFactory translatorFactory) {
	this.translatorFactory = translatorFactory; 
    } 

    /** Returns the id of the command being sent.*/
    public String getCommandId() { 
	if (command != null)
	    return command.getId();
	else
	    return "no-command-set";
    }

    /** Called to allow the handler to clear up resources etc.
     * Deregisters from the Registry. We dont know why it did this -either because it 
     * has finished or timed-out or failed.*/
    public void dispose() {
	CIL_ProxyRegistry.deregister(seqNo);
	serverImpl = null;
	command    = null; 
	translatorFactory = null;
    }

    private void log(String message) {
	System.err.println("CIL_ProxyHandler: "+message);
    }

    public String toString() {
	return (command != null ? "CILH::"+command.getClass().getName()+"("+command.getId()+"):"+seqNo : "unknown");

	// e.g. CILH::ngat.message.RCS_TCS.SLEW(OBS12/SLEW):56556
    
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2002/09/16 09:38:28  snf
/** *** empty log message ***
/**
/** Revision 1.3  2001/06/08 16:27:27  snf
/** Added GRB_ALERT.
/**
/** Revision 1.2  2001/02/16 17:44:27  snf
/** *** empty log message ***
/**
/** Revision 1.1  2000/12/12 18:50:00  snf
/** Initial revision
/**
/** Revision 1.4  2000/11/30 15:36:54  snf
/** Updated handling of IOError on send.
/**
/** Revision 1.3  2000/11/29 15:50:32  snf
/** *** empty log message ***
/**
/** Revision 1.2  2000/11/29 15:40:32  snf
/** Now implements CIL_Proxy. Handling Time is now accessed via synch block.
/**
/** Revision 1.1  2000/11/28 18:15:16  snf
/** Initial revision
/** */





