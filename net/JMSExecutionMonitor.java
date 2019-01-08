package ngat.net;

import ngat.util.*;
import ngat.message.base.*;

public class JMSExecutionMonitor {

    private volatile boolean completed;

    private COMMAND_DONE reply;

    private long timeleft;

    BooleanLock lock;

    public JMSExecutionMonitor() {
	lock = new BooleanLock(false);
    }

    public void setCompleted(boolean c) { this.completed = c;}
    public boolean isCompleted() { return completed;}

    public void setReply(COMMAND_DONE r) { 
	setCompleted(true);
	this.reply = r;
	lock.setValue(true);
    }

    public COMMAND_DONE getReply() { return reply;}

    public void setTimeToCompletion(long t) { this.timeleft = t;}
    public long getTimeToCompletion() { return timeleft;}

    public void waitUntil(long timeout) throws InterruptedException {
	lock.waitUntilTrue(timeout);
    }


}
