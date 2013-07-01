package ngat.message.SMS;

import ngat.message.base.*;
import ngat.phase2.*;
import java.util.*;

public class EXECUTION_UPDATE extends COMMAND {
 
    public static final int VETO_LEVEL_NONE      = 0;
    public static final int VETO_LEVEL_LOW       = 1;
    public static final int VETO_LEVEL_MEDIUM    = 2;
    public static final int VETO_LEVEL_HIGH      = 3;
    public static final int VETO_LEVEL_PERMANENT = 4;

    private long groupId;
    
    private long historyId;
    
    private boolean success;

    private long time;

    private IExecutionFailureContext efc;

    private int vetoLevel;

    public EXECUTION_UPDATE(String name) {
	 super(name);
    }


    public void setGroupId(long gid) {this.groupId = gid;}

    public long getGroupId(){ return groupId;}

    public void setHistoryId(long hid) {this.historyId = hid;}

    public long getHistoryId(){ return historyId;}

    public void setSuccess(boolean s) {this.success = s;}

    public boolean getSuccess() { return success;}

    public void setTime(long time) {this.time = time;}

    public long getTime() { return time;}

    public void setExecutionFailureContext(IExecutionFailureContext efc) {this.efc = efc;}

    public IExecutionFailureContext getExecutionFailureContext() { return efc;}

    public int getVetoLevel() { return vetoLevel; }

    public void setVetoLevel(int vetoLevel) {this.vetoLevel = vetoLevel;}


    public String toString() {
	return "EXECUTION_UPDATE: "+
	    " GID="+groupId+
	    " XID="+historyId+
	    " Time="+(new Date(time))+
	    " Success="+success+
	    (efc != null ? " EFC="+efc:"")+
	    " VL = "+vetoLevel;
	
	// e.g. EXECUTION_UPDATE: GID=2134 XID=40567 Time=2008-07-21 T 21:33:34.4 Success=false EFC=650302, Failed due: AG_NOT_ACQUIRE, VL = 0	

    }
    
}