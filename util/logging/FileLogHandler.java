package ngat.util.logging;

import java.io.*;
import java.util.*;
import java.text.*;

public class FileLogHandler extends LogHandler implements ExtendedLogHandler {

    /** Delimiter for rotating files.*/
    protected final static String ROTATING_FILE_DELIMITER = "_";

    /** Constant, Indicates that rotation occurs hourly.*/
    public final static int HOURLY_ROTATION = 1;

    /** Constant, Indicates that rotation occurs daily.*/
    public final static int DAILY_ROTATION  = 2;
    
    /** Constant, Indicates that rotation occurs weekly.*/
    public final static int WEEKLY_ROTATION = 3;

    /** Date formatter1.*/
    protected SimpleDateFormat sdf1 = new SimpleDateFormat();
    
    /** Date formatter2.*/
    protected SimpleDateFormat sdf2 = new SimpleDateFormat();

    protected SimpleTimeZone UTC = new SimpleTimeZone(0,"UTC");

    /** An output stream to send output to.*/
    protected PrintStream out;

    /** The base filename.*/
    protected String filename;

    /** The file extension.*/
    protected String fileExtension;

    /** Indicates whether to rotate files when a record count limit is exceeded.*/
    boolean rotate;

    /** The record count limit. - When exceeded the file is closed and a new file
     * opened with rotationIndex incremented by one.*/
    int recordLimit;

    /** The maximum number of files to be opened.*/
    int fileLimit;

    /** The file number to start with - default 1.*/
    int fileStart;

    /** Records the current file suffix.*/
    int fileCount;

    /** The record count.*/
    int recordCount;

    /** Indicates whether the files are to rotate when a time period is exceeded.*/
    boolean timed;

    /** Indicates the time period for timed rotation. One of {HOURLY, DAILY, WEEKLY}.*/
    int period;
    
    /** Time at which file should rotate.*/
    long timeLimit;

    /** True if the File is to be opened for append.*/
    boolean append;

    /** Create a FileLogHandler on the specified file and using
     * the specified formatter. The file(s) will be of the form:- <br>
     * &lt;filename&gt;.&lt;rotate-count&gt;.&lt;fileExtension&gt; <br>
     * E.g. Task.3.html or Error.6.xml or Info.txt etc*/
    public FileLogHandler(String filename, LogFormatter formatter) 
	throws FileNotFoundException {	
	super(formatter);
	this.filename = filename;
	recordCount = 0;	
	fileExtension = formatter.getExtensionName();
	out = new PrintStream(new FileOutputStream(filename+"."+fileExtension), true);
	out.println(formatter.getHead());
	rotate = false;
    }

    /** Create a FileLogHandler on the specified file and using
     * the specified formatter with the supplied parameters.
     * @param recordLimit The number of records after which the file is rotated. If
     * this is 0 (zero) then there is no limit and no rotation.
     * @param fileStart The numeric suffix of the first file in the rotation.
     * @param fileLimit The numeric suffix of the last file in the rotation.
     * @param append True if the file should be opened for appending.
     */
    public FileLogHandler(String filename, LogFormatter formatter, int recordLimit, 
			  int fileStart, int fileLimit, boolean append) 
	throws FileNotFoundException {
	super(formatter);
	this.filename = filename;
	this.fileLimit = fileLimit;
	this.fileStart = fileStart;
	this.recordLimit = recordLimit;	
	this.append      = append;
	recordCount = 0;	
	fileCount =fileStart;
	fileExtension = formatter.getExtensionName();
	out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
						   fileCount+"."+
						   fileExtension, append));
	out.println(formatter.getHead());
	rotate = true;
    }

    /** Create a FileLogHandler on the specified file and using
     * the specified formatter with the supplied parameters. The file is created
     * rather than being appended to.
     * @param recordLimit The number of records after which the file is rotated. If
     * this is 0 (zero) then there is no limit and no rotation.
     * @param fileStart The numeric suffix of the first file in the rotation.
     * @param fileLimit The numeric suffix of the last file in the rotation.
     */
    public FileLogHandler(String filename, LogFormatter formatter, int recordLimit, 
			  int fileStart, int fileLimit) 
	throws FileNotFoundException {
	this(filename, formatter, recordLimit, fileStart, fileLimit, false);
    }
    /** Create a FileLogHandler on the specified file and using 
     * the specified formatter with the supplied parameters. The file is appended
     * to if it exists otherwise created.
     * @param period The rotation period code - one of {HOURLY, DAILY, WEEKLY}.
     */
    public FileLogHandler(String filename, LogFormatter formatter, int period)
	throws FileNotFoundException {
	super(formatter);
	append = true; 
	timed  = true;
	this.filename = filename;	
	fileExtension = formatter.getExtensionName(); 
	this.period = period;
	Calendar cal = Calendar.getInstance();
	cal.setTimeZone(LogFormatter.UTC);
	String genCount = "";
	String timCount = "";
	switch (period) {
	case HOURLY_ROTATION:
	    sdf1 = new SimpleDateFormat("DDD");    // Day of year.
	    sdf2 = new SimpleDateFormat("'H'HH");  // Hour of day (0-23).	
	 
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.roll(Calendar.HOUR_OF_DAY, true);
	    break;
	case DAILY_ROTATION:
	    sdf1 = new SimpleDateFormat("yyyy");   // Year.
	    sdf2 = new SimpleDateFormat("'D'DDD"); // Day of year. 
	   
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.roll(Calendar.DAY_OF_YEAR, true);
	    break;
	case WEEKLY_ROTATION:
	    sdf1 = new SimpleDateFormat("yyyy");   // Year.
	    sdf2 = new SimpleDateFormat("'W'ww");  // Week in year. 	 
	    int dow = cal.get(Calendar.DAY_OF_WEEK);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.roll(Calendar.DATE, 8-dow);
	    break; 
	default:
	    // Default to Hourly logs with datestamp.
	    sdf1 = new SimpleDateFormat("yyyyMMdd");
	    sdf2 = new SimpleDateFormat("HH");
	    period = HOURLY_ROTATION; 
	  
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.roll(Calendar.HOUR_OF_DAY, true);
	} 
	timeLimit = cal.getTime().getTime();
	sdf1.setTimeZone(LogFormatter.UTC);
	sdf2.setTimeZone(LogFormatter.UTC);
	genCount = sdf1.format(new Date());
	timCount = sdf2.format(new Date());
	out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
						   genCount+ROTATING_FILE_DELIMITER+
						   timCount+"."+
						   fileExtension, append));
	SimpleDateFormat adf  =  new SimpleDateFormat("yyyyMMdd HH:mm z");
	adf.setTimeZone(LogFormatter.UTC);
	//System.err.println("Will roll at: "+adf.format(new Date(timeLimit)));
	// E.g. HOURLY: error_015_H17.txt  for Hour17 of day 015.
	// or   DAILY:  ops_2001_D234.html for Day234 of 2001.
	// or   WEEKLY: task_2002_W16.csv  for Week16 of 2002.

	out.println(formatter.getHead());		
    }

    /** Sets the append flag.*/
    public void setAppend(boolean append) { this.append = append;}


    /** Publish a LogRecord to the output file.*/
    public void publish(LogRecord record) {
	recordCount++;
	if (rotate) {
	    if (recordCount > recordLimit) {
		// Close the current stream.
		close();
		// Rotate.
		if (fileCount < fileLimit) 
		    fileCount++;
		else
		    fileCount = fileStart;
		// Record count back to 0 whatever.
		recordCount = 0;
		try {
		    out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
							       fileCount+"."+
							       fileExtension, append));
		    out.println(formatter.getHead());
		} catch (IOException e) {}
	    }
	} else if (timed) {
	    if (System.currentTimeMillis() > timeLimit) {
		// Close the current stream.
		close();
		long time = System.currentTimeMillis();
	
		Calendar cal = Calendar.getInstance();	
		cal.setTimeZone(LogFormatter.UTC);
		

		String genCount = "";
		String timCount = "";
		switch (period) {
		case HOURLY_ROTATION:
		    time += 3600*1000L;
		    cal.setTime(new Date(time));
		    //sdf1.applyPattern("DDD");    // Day of year.
		    //sdf2.applyPattern("'H'HH");  // Hour of day (0-23).			  
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    //cal.roll(Calendar.HOUR_OF_DAY, true);
		    break;
		case DAILY_ROTATION:
		    time += 24*3600*1000L;
		    //sdf1.applyPattern("yyyy");   // Year.
		    //sdf2.applyPattern("'D'DDD"); // Day of year. 
		    cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    //cal.roll(Calendar.DAY_OF_YEAR, true);
		    break;
		case WEEKLY_ROTATION:
		    time += 7*24*3600*1000L;
		    cal.setTime(new Date(time));
		    //sdf1.applyPattern("yyyy");   // Year.
		    //sdf2.applyPattern("'W'ww");  // Week in year. 
		    int dow = cal.get(Calendar.DAY_OF_WEEK);		  
		    cal.set(Calendar.HOUR_OF_DAY, 0);
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    // cal.roll(Calendar.DATE, 8-dow);
		    break; 
		default:
		    // Default to Hourly logs with datestamp.
		    time += 3600*1000L;
		    cal.setTime(new Date(time));
		    //sdf1.applyPattern("yyyyMMdd");
		    // sdf2.applyPattern("HH");
		    period = HOURLY_ROTATION;   
		    cal.set(Calendar.MINUTE, 0);
		    cal.set(Calendar.SECOND, 0);
		    // cal.roll(Calendar.HOUR_OF_DAY, true); 		  
		}  
		timeLimit = cal.getTime().getTime();
		//cal.setTime(new Date(time));
		//sdf1.setTimeZone(LogFormatter.UTC);
		//sdf2.setTimeZone(LogFormatter.UTC);
		genCount = sdf1.format(new Date());
		timCount = sdf2.format(new Date());
		try {
		    out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
							       genCount+ROTATING_FILE_DELIMITER+
							       timCount+"."+
							       fileExtension, append)); 
		    out.println(formatter.getHead());
		} catch (IOException e) {}		
	    }
	}
	out.println(formatter.format(record));
    }
    
    public void publish(ExtendedLogRecord record) {
	recordCount++;
        if (rotate) {
            if (recordCount > recordLimit) {
                // Close the current stream.
                close();
                // Rotate.
                if (fileCount < fileLimit)
                    fileCount++;
                else
                    fileCount = fileStart;
                // Record count back to 0 whatever.
                recordCount = 0;
                try {
                    out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
                                                               fileCount+"."+
                                                               fileExtension, append));
                    out.println(formatter.getHead());
                } catch (IOException e) {}
            }
        } else if (timed) {

	    if (System.currentTimeMillis() > timeLimit) {
                // Close the current stream.
                close();
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(LogFormatter.UTC);
                String genCount = "";
                String timCount = "";
                switch (period) {
                case HOURLY_ROTATION:
                    sdf1 = new SimpleDateFormat("DDD");    // Day of year.
                    sdf2 = new SimpleDateFormat("'H'HH");  // Hour of day (0-23).
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.roll(Calendar.HOUR_OF_DAY, true);
                    break;
                case DAILY_ROTATION:
		    sdf1 = new SimpleDateFormat("yyyy");   // Year.
                    sdf2 = new SimpleDateFormat("'D'DDD"); // Day of year.
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.roll(Calendar.DAY_OF_YEAR, true);
                    break;
                case WEEKLY_ROTATION:
                    sdf1 = new SimpleDateFormat("yyyy");   // Year.
                    sdf2 = new SimpleDateFormat("'W'ww");  // Week in year.
                    int dow = cal.get(Calendar.DAY_OF_WEEK);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.roll(Calendar.DATE, 8-dow);
                    break;
                default:
                    // Default to Hourly logs with datestamp.
                    sdf1 = new SimpleDateFormat("yyyyMMdd");
                    sdf2 = new SimpleDateFormat("HH");
                    period = HOURLY_ROTATION;
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.roll(Calendar.HOUR_OF_DAY, true);
                }
		timeLimit = cal.getTime().getTime();
                sdf1.setTimeZone(LogFormatter.UTC);
                sdf2.setTimeZone(LogFormatter.UTC);
                genCount = sdf1.format(new Date());
                timCount = sdf2.format(new Date());
                try {
                    out = new PrintStream(new FileOutputStream(filename+ROTATING_FILE_DELIMITER+
                                                               genCount+ROTATING_FILE_DELIMITER+
                                                               timCount+"."+
                                                               fileExtension, append));
                    out.println();
                } catch (IOException e) {}
            }
        }
        out.println(record.toString());
    }

    public boolean isLoggable(ExtendedLogRecord record) {
        return (record.getLevel() <= logLevel);
    }

    
    /** Write the tail and close the file.*/
    public void close() {
	out.println(formatter.getTail());
	out.close();
    }

}
