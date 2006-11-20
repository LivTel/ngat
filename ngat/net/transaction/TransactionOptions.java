package ngat.net.transaction;

import java.io.*;

/** Contains various codes representing message/transaction control options and states.*/
public interface TransactionOptions {
 
    /** Status category - indicates that transaction is not known of.*/
    public static final int UNKNOWN_STATUS   = 790001;
    
    /** Status category - indicates that transaction has been received.*/
    public static final int RECVD_STATUS     = 790002;
    
    /** Status category - indicates that transaction has been received and forwarded succesfully.*/
    public static final int FWD_STATUS       = 790003;
    
    /** Status category - indicates that transaction has been executed by destination.*/
    public static final int DONE_STATUS      = 790004;

    /** Status category - indicates that a CANCEL request has been made by the originator
     * but not yet confirmed by downstream managers.*/
    public static final int CAN_RECVD_STATUS = 790005;
    
    /** Status category - indicates that downstream managers have confirmed cancellation 
     * of the executable .*/
    public static final int CAN_STATUS       = 790006;

    /** Status category - indicates that a DELETE request has been made by the originator
     * but not yet confirmed by downstream managers.*/
    public static final int DEL_RECVD_STATUS = 790007;

    /** Status category - indicates that downstream managers have confirmed deletion
     * of the executable .*/
    public static final int DEL_STATUS       = 790008;

    /** Status category - indicates that downstream managers could not validate the originating client.*/
    public static final int INVALID_STATUS   = 790010;
    
    /** Option class code indicating option on timeout expiry.*/
    public final int TIMEOUT_OPTION = 790001;

    /** Option class code indicating timeout period.*/
    public final int TIME_TO_LIVE = 790002;

    /** Option value code indicating to discard on timeout.*/
    public final int DISCARD_ON_TIMEOUT = 790003;
    
    /** Option response code indicating a message has timedout.*/
    public final int TIMED_OUT = 790004;

    /** Option response code indicating that the client was not validated.*/
    public final int INVALID_CLIENT = 790005;

   

}
