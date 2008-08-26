package ngat.util.logging;

import java.io.*;

/** Stores a mesage and associated information from a Logging call.*/
public class LogRecord implements Serializable {
    
   
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -8299743037667027751L;
    
  /** The message placed in the original log call. This may contain
   * information in a format which can be parsed by a LogFormatter.*/
  protected String message;
    
  /** The logger which originated this LogRecord.*/
  protected String loggerName;
    
  /** The class of object which contained the log call.*/
  protected String clazz;
    
  /** The identity of the object which contained the log call.*/
  protected String source;
    
  /** The method in the orignating object where the call was made.*/
  protected String method;
    
  /** The thread which made the log call.*/
  protected String thread;
    
  /** Category identity of theis record.*/
  String cat;

  /** The time the log casll was made.*/
  protected long time;
    
  /** The unique (to the calling Logger) sequence no of this message.*/
  protected int seqno;
    
  /** The level (granularity) of the message.*/
  protected int level;

  /** An array to hold parameters for logging - their significance is entirely
   * upto the application calling the Logger.*/
  protected Object[] params;

  /** An exception to log.*/
  protected Exception exception;

  /** Create a LogRecord with the specified level and message.
   * @param level The level of this message.
   * @param message The message to include.*/
  public LogRecord(int level, String message) {
    this.level = level;
    this.message = message;
  }

  /** Set the name of the Logger which generated this record.
   * @param loggerName The name of the Logger which generated this record.*/
  public void setLoggerName(String loggerName) { this.loggerName = loggerName; }
    
  /** @return The name of the Logger which generated this record.*/
  public String getLoggerName() { return loggerName; }
    
  /** Set the name of the class  which generated this record.
   * @param clazz The name of the class  which generated this record.*/
  public void setClazz(String clazz) { this.clazz = clazz; }
    
  /** @return The name of the class  which generated this record.*/
  public String getClazz() { return clazz; }
    
  /** Set the name/id of the object which generated this record.*/
  public void setSource(String source) { this.source = source; }
    
  /** @return The name/id of the object which generated this record.*/
  public String getSource() { return source; }
    
  /** Set the name of the method which invoked log().
   * @param method The name of the method. */
  public void setMethod(String method) { this.method = method; }
    
  /** @return The method which invoked log().*/
  public String getMethod() { return method; }
    
  /** Set the id of the Thread which invoked the original log()
   * call which generated this record.*/
  public void setThread(String thread) { this.thread = thread; }
       
  /** @return The thread which was running and invoked the original log()
   * call which generated this record.*/
  public String getThread() {return thread; }
    
  /** Set the creation time of this record.
   * @param time The time (millis from 1970) when this record was
   * generated at the originating Logger.*/
  public void setTime(long time) { this.time = time; }
    
  /** @return The time (millis from 1970) when this record was
   * generated at the originating Logger.*/
  public long getTime() {return time; }
    
  /** Set the sequence number of this record.
   * @param seqno The (unique to the originating Logger) sequence
   * no. for this record.*/
  public void setSeqno(int seqno) { this.seqno = seqno; } 
    
  /** @return The sequence no. (unique to the originating Logger)
   * of this record.*/ 
  public int getSeqno() { return seqno; }
    
  /** Set parameters for this record.
   * @param params The set of parameters for this record.*/
  public void setParams(Object[] params) { this.params = params; }

  /** @return Any parameters stored with this record.*/
  public Object[] getParams() { return params; }

  /** Set the category ident of theis record.
   * @param cat The category for this record.*/
  public void setCat(String cat) { this.cat = cat; }

  /** @return The category ident of this record.*/
  public String getCat() { return cat; }

  /** Set an Exception for this record.
   * @param exception The Exception stored in this record.*/
  public void setException(Exception exception) { this.exception = exception; }

  /** @return The Exception for this record.*/
  public Exception getException() { return exception; }

  /** @return The message from this record.*/
  public String getMessage() { return message; }
 
  /** @return The logging level of this record.*/
  public int getLevel() {  return level; }

  public String toString() {
    return 
      "ngat.util.logging.LogRecord {"+
      "\nLoggerName: "+loggerName+
      "\nClazz: "+clazz+
      "\nSource: "+source+
      "\nMethod: "+method+
      "\nThread: "+thread+
      "\nTime: "+time+
      "\nSeq: "   +seqno+
      "\nCat: "+cat+
      "\nEnclosed exception: "+exception+
      "\nMessage: "+message;
  }

}
