package ngat.net.camp;

import ngat.net.*;
import ngat.message.base.*;

/** Classes which wish to handle the response(s) from a 
 * Control and Monitor Protocol (CAMP) session must implement 
 * the 2 methods in this interface.
 */
public interface CAMPResponseHandler {

    /** Overwrite to handle any i/o errors detected by CAMP implementor..
     * @param e An exception which was thrown by the CAMP implementor.
     */
    public void failed(Exception e, IConnection connection);
    
    /** Overwrite to handle the response message received from a CAMP implementor.
     * @param update The UPDATE received.
     */
    public void handleUpdate(COMMAND_DONE update, IConnection connection);
    
}
