package ngat.util;

/**
 *
 * $Id$
 *
 */
public class SynchFlag {

    protected int count;
    protected Thread flag;

    public SynchFlag() {
	count = 0;
	flag = null;
    }

    public synchronized void getSynchFlag() {

	while (tryGetSynchFlag() == false) {
	    try {
		wait();
	    }catch (Exception e) {}
	}
    }

    public synchronized boolean tryGetSynchFlag() {
	if (flag == null) {
	    flag = Thread.currentThread();
	    count = 1;
	    return true;
	}
	if (flag == Thread.currentThread()) {
	    count++;
	    return true;
	}
	return false;
    }

    public synchronized void freeSynchFlag() {
	if (getSynchFlagOwner() == Thread.currentThread()) {
	    count--;
	    if (count == 0) {
		flag = null;
		notify();
	    }
	}
    }

    public synchronized Thread getSynchFlagOwner() {
	return flag;
    }

}

/** $Log: not supported by cvs2svn $ */
