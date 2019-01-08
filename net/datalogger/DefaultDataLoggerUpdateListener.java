package ngat.net.datalogger;

/** Interface whcih must be implemented by any classes which are to act
 * as handlers for DataLogging.
 */
public class DefaultDataLoggerUpdateListener implements DataLoggerUpdateListener{

    /** Name for this handler.*/
    protected String name;

    /** Create a DefaultDataLoggerUpdateListener with name.*/
    public DefaultDataLoggerUpdateListener(String name) {
	this.name = name;
    }
    
    /** Dump data to stderr.*/ 
    public void dataUpdate(Object data) {
	System.err.println("DataHandler:"+name+" Recieved update: "+(data != null ? data.getClass().getName() : "NULL")+" : "+data);
    }
    
}
