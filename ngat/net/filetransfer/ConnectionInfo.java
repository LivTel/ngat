package ngat.net.filetransfer;

public class ConnectionInfo {

    public static final int IDLE      = 1;
    public static final int INCOMING  = 2;
    public static final int OUTGOING  = 3;
    public static final int COMPLETED = 4;

    public static final int CLIENT   = 1;
    public static final int RELAY    = 2;
    public static final int ENDPOINT = 2;

    public Route route;

    public int   state;

    public long  totalBytes;

    public long  transferredBytes;

    public String toString() {
	return "Connection: "+route+
	    ", state="+toStateString(state)+
	    ", transferred "+transferredBytes+"/"+totalBytes+" bytes"+
	    " ("+(int)(100.0*(transferredBytes/totalBytes))+")";
    }
    
    public String toStateString(int state) {
	switch (state) {
	case IDLE:
	    return "IDLE";
	case INCOMMING:
	    return "INCOMING";
	case OUTGOING:
	    return "OUTGOING";
	case COMPLETED:
	    return "COMPLETED";
	default:
	    return "UNKNOWN";
	}
    }

}
