package ngat.net.transaction;

public class SubmissionException extends Exception {

    /** Create a SubmissionException with default message.*/
    public SubmissionException() { super(); }

    /** Create a SubmissionException with specified  message.
     * @param message A message to disply.
     */
    public SubmissionException(String message) { super(message); }

}
