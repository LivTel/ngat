package ngat.util.logging;

import ngat.util.*;

import java.io.*;

/** An interface which should be implemented by classes which wish to handle the logging
 * of StatusCategory data.
 */
public interface StatusLogger {

    /** Prepare the logger.*/
    public void open() throws Exception;
   
    /** Publish the supplied StatusCategory.*/
    public void publish(StatusCategory cat);

    /** Close this logger.*/
    public void close() throws Exception;

    /** Set the formatter.*/
    public void setFormatter(StatusLogFormatter formatter);

}
