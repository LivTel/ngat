package ngat.net.transaction;

import ngat.net.*;

/** Classes which wish to handle the request(s) from a 
 * Distrubuted Transaction Protocol (DTP) session must implement 
 * the method in this interface.
 */
public interface DTPServer {
    
    /** Overwrite to handle the request message received from a DTP implementor.
     * @param request The DistributedTransactionMessage received.
     * @return A suitable ResponseTransactionMessage.
     */
    public ResponseTransactionMessage handleRequest(DistributedTransactionMessage request);
    
}
