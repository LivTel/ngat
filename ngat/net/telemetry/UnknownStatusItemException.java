package ngat.net.telemetry;

public class UnknownStatusItemException extends Exception {

    public  UnknownStatusItemException() {
	super();
    }

    public  UnknownStatusItemException(String message) {
        super(message);
    }

    public  UnknownStatusItemException(Exception e) {
        super(e);
    }

}