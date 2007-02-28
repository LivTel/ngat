package ngat.util.logging;


import java.io.*;
import java.net.*;
import java.util.*;

/** Classes which wish to act as Listeners to receive updates when a multicast
 * Serializable Object is received should implement this interface.
 */
public interface MulticastUpdateListener {

    /** Notification of received Serializable Object.*/
    public void objectReceived(Serializable object);

}
