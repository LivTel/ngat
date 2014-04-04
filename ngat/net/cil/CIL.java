package ngat.net.cil;

import java.io.*;

/** Provides a standard interface to the TTL CIL library
 * for internode communications between the telescope 
 * subsystems. Further method signatures will be added
 * as required. Any class may implement this interface by
 * providing a mechanism for encapsulation of the CIL's UDP
 * transport layer. A more generic version of this
 * will include methods to pass a full eCilMsg_t like struct
 * into the doSend() method.
 * In particular it is suggested that singleton classes,
 * conforming to the standard Singleton pattern,
 * could provide static accessors - typically send()
 * and receive() which grab the singleton instance and
 * call its doSend() and doReceive() methods. e.g.<br>
 * <pre>
 *
 * // Implementation of CIL.doSend().
 * public void doSend(int seq, String msg) {
 *       
 *        // 1. Pack the data into a byte array
 *        //    in network byte order.
 *        // 2. Open a UDP stream.
 *        // 3. Send the datagram.
 *        
 * // Convenience method via singleton.
 * // Grab the singleton and dispatch message.
 * public static void send(int seq, String msg) {
 *        getInstance().doSend(seq, msg);
 * }
 *
 *
 *
 * <br><br><img src = "doc-files/snft.gif"><br><br>
 * // Singleton grabber method.
 * public static Singleton getInstance() {
 *        if (theInstance == null) {
 *              theInstance = new Singleton();
 *        return theInstance;
 * }
 *
 * .. other methods.
 *  
 * }
 * </pre>
 * <br><br>
 * $Id$
 */
public interface CIL {

    /** Implementation of CIL function eCilSetup(). 
     * Classes which wish to implement this method should perform
     * any initialization of sockets etc here. This method should 
     * generally be called in the constructor of some handler
     * in the target system and should only be called once.
     * @param sendPort The local port to bind to.
     * @exception IOException If any problem occurs during setup.
     */
    public void doSetup(int sendPort) throws IOException;


    /** Implementation of CIL function eCilSend().
     * Classes which wish to implement this method should perform
     * the following actions:-<br>
     * <ol>
     * <li>Grab any neccessary information regarding
     *     Rx and Tx IDs and Class / Service categories
     *     (as specified in CIL Header Specification).
     * <li>Pack the ids and other data into a byte array conforming 
     *     to the <b>eCilMsg_t</b> structure.
     * <li>Push the byte array into a datagram.
     * <li>Open a UDP socket to the required destination/port
     *     - if not already open.
     * <li>Send the datagram via UDP.
     * </ol>
     * @param txId The CIL id of the transmitter.
     * @param rxId The CIL id of the reciver.
     * @param mclass The CIL class id of the message.
     * @param sclass The CIL class id of the service.
     * @param seqno The application generated sequence number.
     * @param message The command/request/response string to send.
     * @exception IOException If any problem occurs while sending.
     */
    public void doSend(int txId, int rxId, int mclass, int sclass, int seqno, String message) throws IOException;

    /** Implementation of CIL function eCilReceive().
     * Classes which wish to implement this method should perform
     * the following actions:-<br>
     * <ol>
     * <li>Open a UDP socket on a specified port
     *     - if not already open.
     * <li>Listen for a UDP datagram from the server.
     * <li>Unpack the bytes into an equivalent of the
     *     <b>eCilMsg_t</b> structure (CIL_Message).
     * <li>Return the structure.
     * </ol>
     * @exception IOException If any problem occurs while reading.
     */
    public CIL_Message doReceive() throws IOException;

}

/** $Log: not supported by cvs2svn $ */
