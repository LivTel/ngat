package ngat.net.transaction;

import java.io.*;

/** Contains various methods for the DTMS package.*/
public class TransactionUtilities implements TransactionOptions {
 
    /** Returns a readable representation of the specified state code.
     * @param code The state code.
     */
    public static String getStateCodeString(int code) {
	switch (code) {
	case UNKNOWN_STATUS:
	    return "UNKNOWN_STATUS";
	case RECVD_STATUS:
	    return "RECVD_STATUS";
	case FWD_STATUS:
	    return "FWD_STATUS";
	case DONE_STATUS:
	    return "DONE_STATUS";
	case CAN_RECVD_STATUS:
	    return "CAN_RECVD_STATUS";
	case CAN_STATUS:
	    return "CAN_STATUS";
	    
	case DEL_RECVD_STATUS:
	    return "DEL_RECVD_STATUS";
	    
	case DEL_STATUS:
	    return "DEL_STATUS";
	default:
	    return "UNKNOWN_STATUS";
	}
    }

    /** Returns a readable representation of the specified option code.
     * @param code The option code.
     */
    public static String getOptionCodeString(int code) {
	switch (code) {
	case DISCARD_ON_TIMEOUT:
	    return "DISCARD_ON_TIMEOUT";
	default:
	    return "UNKNOWN_OPTION";
	} 
    }

    /** Returns a readable representation of the specified error code.
     * @param code The error code.
     */
    public static String getErrorCodeString(int code) {
	switch (code) {
	case TIMED_OUT:
	    return "TIMED_OUT";
	default:
	    return "UNKNOWN_ERROR";
	} 
    }

    
}
