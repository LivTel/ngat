package ngat.net.transaction;

import ngat.net.*;

/** Classes which wish to handle the response(s) from a 
 * Distrubuted Transaction Protocol (DTP) session must implement 
 * the 2 methods in this interface.
 */
public interface DTPClient {

    /** Overwrite to handle any i/o errors detected by DTP implementor..
     * @param e An exception which was thrown by the DTP implementor.
     */
    public void failed(Exception e);
    
    /** Overwrite to handle the response message received from a DTP implementor.
     * @param response The ResponseTransactionMessage received.
     */
    public void handleResponse(ResponseTransactionMessage response);
    
}
