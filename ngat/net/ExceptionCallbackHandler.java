package ngat.net;

/**
 * Interface to provide a mechanism for passing exceptions back
 * from one thread to another (possibly controlling) thread.
 * Concrete implementations can use <i>source</i> to determine which 
 * object gave rise to the exception. It is up to the implementor
 * of the class which calls exceptionOccurred() to pass itself as
 * the source argument. 
 *
 * e.g. The following fragment illustrates a (Thread) which wishes 
 * to notify another thread that it has thrown an exception to 
 * allow it to respond. The controller thread must implement the
 * ExceptionCallbackHandler interface. There is no constraint on
 * the controlled thread - other than that is is a Thread. This
 * is not essential, however there is no point using this mechanism
 * if this is not the case.
 * <br>
 * <pre>
 * ... some controlled Thread class...
 * public void run() {
 *      try {
 *          .
 *          .
 *          // Some code which might throw an exception.
 *          .
 *          .
 *      } catch (Exception e) {
 *          ((ExceptionCallbackHandler)controller).exceptionOccurred(this, e);
 *
 *      }
 * }
 * </pre>
 * <br>
 * When using this mechanism you should be aware of liveness issues - the
 * callback method will block the controlled thread's execution until
 * the callback has returned.
 * <br>
 * 
 * $Id$
 *
 */
public interface ExceptionCallbackHandler {

    public void exceptionOccurred(Object source, Exception e);

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/11/23 10:55:21  snf
/** Changed javadoc tags.
/**
/** Revision 1.1  2000/11/23 10:19:53  snf
/** Initial revision
/** */
