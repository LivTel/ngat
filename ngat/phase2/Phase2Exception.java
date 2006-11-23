package ngat.phase2;

public class Phase2Exception extends Exception {

    /** A code to indicate the reason for the exception.*/
    private int code;

    /** Create a Phase2Exception with no message and the given code.
     * @param code The error code.
     */
    public Phase2Exception(int code) {
	super();
	this.code = code;
    }

    /** Create a Phase2Exception with the given message and  code.
     * @param code The error code.
     * @param message The error message.
     */
    public Phase2Exception(int code, String message) {
	super(message);
	this.code = code;
    }

    /** Returns the error code.*/
    public int getCode() { return code; }

    /** Returns a readable description of the exception
     * in the form: Phase2Exception: <code> : <message>
     */
    public String toString() {
	return ": "+code+" : "+getMessage();
    }

}
