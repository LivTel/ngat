package ngat.message.OSS;

import java.io.*;

/**
 * Contains serializable information about a Client, can be sent across a
 * socket connection and used as an entry in a key/value table.
 *
 * $Id$
 *
 */

public class ClientDescriptor implements Serializable {

    /** Client type constant: PEST client.*/
    public static final int PEST_CLIENT  = 1;

    /** Client type constant: Robotic Control client.*/
    public static final int RCS_CLIENT   = 2;

    /** Client type constant: Admin Tool client.*/
    public static final int ADMIN_CLIENT = 3;

    /** Client type constant: Offline Control client.*/
    public static final int OCS_CLIENT   = 4;
    
    /** Client type constant: Planetarium client.*/
    public static final int POS_CLIENT   = 5;
    
    /** Client type constant: Target of Opportunity client.*/
    public static final int TOCS_CLIENT  = 6;

    /** Client type constant: Network client.*/
    public static final int NET_CLIENT   = 7;
    
    /** Client priority constant: General (lowest) priority.*/
    public static final int GENERAL_PRIORITY  = 1;

    /** Client priority constant: Admin priority.*/
    public static final int ADMIN_PRIORITY    = 2;

    /** Client priority constant: RCS priority.*/
    public static final int RCS_PRIORITY      = 3;
    
    /** Client priority constant: PI User priority.*/
    public static final int PI_USER_PRIORITY  = 4;

    /** Client priority constant: CoI User priority.*/
    public static final int COI_USER_PRIORITY = 5;

    /** Client priority constant: User priority.*/
    public static final int USER_PRIORITY     = 6;

    /** Client priority constant: User priority.*/
    public static final int TOCS_PRIORITY     = 7;

  
    /** The user id (OSS user-name) for this client.*/
    protected String id;

    /** The type of this client - used by OSS to determine whether to allow connection.*/
    protected int type;

    /** The user's password - not used.*/
    protected String password;

    /** The priority level of the user - decides which queue to place a request into.*/
    protected int priority;

    /** The registration id of an individual client application - not used by OSS.*/
    protected long regId;

    public ClientDescriptor(String id, int type, int priority, String password, long regId){
	this.id       = id;
	this.type     = type;
	this.priority = priority;
	this.password = password; 
	this.regId    = regId;
    }

    public ClientDescriptor(String id, int type, int priority){
	this(id, type, priority, null, 0L);
    }
    
    public ClientDescriptor(String id, int type, int priority, String password){
	this(id, type, priority, password, 0L);
    }

    public String getId() { return id;}
    
    public int getType() { return type;}
    
    public int getPriority() { return priority;}
    
    public String getPassword() { return password;}
    
    public long getRegId() { return regId;}

    /** Returns a description of the client type.*/
    public static final String toClientTypeString(int type) {
	switch (type) {
	case PEST_CLIENT:
	    return "PEST client";
	case RCS_CLIENT:
	    return "RCS client";
	case ADMIN_CLIENT:
	    return "Admin client";
	case OCS_CLIENT:
	    return "OCS client";
	case POS_CLIENT:
	    return "Planetarium client";
	case TOCS_CLIENT:
	    return "Target of Opportunity client";
	case NET_CLIENT:
	    return "Telescope Network client";
	default:
	    return "Unknown client ("+type+")";
	}
    }

    /** Returns a description of the client priority.*/
    public static final String toClientPriorityString(int priority) {
	switch (priority) {
	case GENERAL_PRIORITY:
	    return "General (lowest) priority";
	case ADMIN_PRIORITY:
	    return "Admin priority";
	case RCS_PRIORITY:
	    return "RCS priority";
	case PI_USER_PRIORITY:
	    return "PI User priority";
	case COI_USER_PRIORITY:
	    return "CoI User priority";	  
	case USER_PRIORITY:
	    return "General user priority";
	case TOCS_PRIORITY:
	    return "Target of Opportunity Agent priority";
	default:
	    return "Unknown priority ("+priority+")";
	} 
    }
    
    /** Returns a description of the client priority for authorization checking.*/
    public static final String toClientAuthorizationString(int priority) {
	switch (priority) {
	case GENERAL_PRIORITY:
	    return "GENERAL";
	case ADMIN_PRIORITY:
	    return "ADMIN";
	case RCS_PRIORITY:
	    return "RCS";
	case PI_USER_PRIORITY:
	    return "PI_USER";
	case COI_USER_PRIORITY:
	    return "COI_USER";	  
	case USER_PRIORITY:
	    return "USER";
	case TOCS_PRIORITY:
	    return "TOCS";
	default:
	    return "UKG("+priority+")";
	} 
    }


    /** Returns readable String representation of client decsriptor.*/
    public String toString() {
	return "[ClientDescriptor: "+id+
	    ", Type="+toClientTypeString(type)+
	    ", Priority="+toClientPriorityString(priority)+
	    ", Password="+password+
	    ", RegID="+regId+"]";
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/12/18 11:50:57  snf
/** Initial revision
/**
/** Revision 1.1  2006/12/05 12:41:18  snf
/** Initial revision
/**
/** Revision 1.2  2001/02/23 18:48:26  snf
/** *** empty log message ***
/**
/** Revision 1.1  2000/12/01 18:13:03  snf
/** Initial revision
/**
/** Revision 1.1  2000/09/12 10:52:42  snf
/** Initial revision
/** */
