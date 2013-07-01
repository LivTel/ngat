package ngat.message.SMS;

import ngat.message.base.*;

public class NETWORK_TEST_DONE extends COMMAND_DONE {

    /** Create a NETWORK_TEST_DONE with specified id.
     * @param id The unique id of this NETWORK_TEST_DONE.
     */
    public NETWORK_TEST_DONE (String id) { super(id); }
    
    /** Returns a String representation of the object.*/
    public String toString() {
	return "["+this.getClass().getName()+":"+
	    getId()+"]";
    }
    // Hand generated code.
    
} // class def. [NETWORK_TEST_DONE].
