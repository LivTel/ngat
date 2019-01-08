package ngat.util;

public interface ControlableThread {

    
    /** Tell the Thread to die. Other Threads may call this. */
    public void terminate();

}
