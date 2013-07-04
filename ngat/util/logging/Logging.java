package ngat.util.logging;

public interface Logging {

    /** Log level indicating Logging is switched OFF.
     * This should be used as a Logger or Handler's LogLevel and NOT
     * as the level field in a message.*/
    public final static int OFF = 0;

    /** Log level indicating to log ALL messages.
     * This should be used as a Logger or Handler's LogLevel and NOT
     * as the level field in a message.*/
    public final static int ALL = -1;
    
    /** Log level 1.*/
    public final static int LEVEL_1 = 1;
    
    /** Log level 2.*/
    public final static int LEVEL_2 = 2;
    
    /** Log level 3.*/
    public final static int LEVEL_3 = 3;

    /** Log level 4.*/
    public final static int LEVEL_4 = 4;
    
    /** Log level 5.*/
    public final static int LEVEL_5 = 5;
    
    /** Log level 6.*/
    public final static int LEVEL_6 = 6;
    
    /** Log level 7.*/
    public final static int LEVEL_7 = 7;

    /** Log level 8.*/
    public final static int LEVEL_8 = 8;
    
    /** Log level 9.*/
    public final static int LEVEL_9 = 9;

    // Compatibility with C-logging.
    public final static int VERBOSITY_VERY_TERSE = 1;
    public final static int VERBOSITY_TERSE = 2;
    public final static int VERBOSITY_INTERMEDIATE = 3;
    public final static int VERBOSITY_VERBOSE = 4;
    public final static int VERBOSITY_VERY_VERBOSE = 5;

    // Default level names.
    public final static int LEVEL_SUMMARY = 1;
    public final static int LEVEL_DETAIL = 2;
    public final static int LEVEL_FINE = 3;
    public final static int LEVEL_DEBUG = 4;
    public final static int LEVEL_TRACE = 5;

    // Alternative level names.
    public final static int LOG_LEVEL_PITHY = 1;
    public final static int LOG_LEVEL_STINGY = 2;
    public final static int LOG_LEVEL_THOROUGH = 3;
    public final static int LOG_LEVEL_CHATTY = 4;
    public final static int LOG_LEVEL_PRATTLING = 5;

    /** Constant to indicate Logging category: WARNING.*/
    public static final String WARNING = "WARNING";
   
    /** Constant to indicate Logging category: ERROR.*/
    public static final String ERROR = "ERROR";

    /** Constant to indicate Logging category: TRACE.*/
    public static final String TRACE = "TRACE";
    
    /** Constant to indicate Logging category: FATAL.*/
    public static final String FATAL = "FATAL";
    
    /** Constant to indicate Logging category: ENTER.*/
    public static final String ENTER = "ENTER";
    
    /** Constant to indicate Logging category: EXIT.*/
    public static final String EXIT = "EXIT";

    /** Constant to indicate Logging category: CALL.*/
    public static final String CALL = "CALL";
    
    /** Constant to indicate Logging category: RETURN.*/
    public static final String RETURN = "RETURN";
    
    /** Constant to indicate Logging category: CHECK.*/
    public static final String CHECK = "CHECK";
    
    /** Constant to indicate Logging category: DUMP.*/
    public static final String DUMP = "DUMP";
    
    /** Constant to indicate Logging category: INFO.*/
    public static final String INFO = "INFO";
    
    /** Constant to indicate Logging category: EVENT.*/
    public static final String EVENT = "EVENT";
    
    /** Constant to indicate Logging category: ACTION.*/
    public static final String ACTION = "ACTION";

    /** Constant to indicate Logging category: EXCEPTION.*/
    public static final String EXCEPTION = "EXCEPTION";

    /** Constant to indicate Logging category: COLOR=BLACK.*/
    public static final String BLACK_TEXT = "BLACK_TEXT";

    /** Constant to indicate Logging category: COLOR=RED.*/
    public static final String RED_TEXT = "RED_TEXT";

    /** Constant to indicate Logging category: COLOR=GREEN.*/
    public static final String GREEN_TEXT = "GREEN_TEXT";
    
    /** Constant to indicate Logging category: COLOR=BROWN.*/
    public static final String BROWN_TEXT = "BROWN_TEXT";
    
    /** Constant to indicate Logging category: COLOR=BLUE.*/
    public static final String BLUE_TEXT = "BLUE_TEXT";
    
    /** Constant to indicate Logging category: COLOR=MAGENTA.*/
    public static final String MAGENTA_TEXT = "MAGENTA_TEXT";
    
    /** Constant to indicate Logging category: COLOR=CYAN.*/
    public static final String CYAN_TEXT = "CYAN_TEXT";

    /** Constant to indicate Logging category: COLOR=GREY.*/
    public static final String GREY_TEXT = "GREY_TEXT";

    /** Severity level: FATAL.*/
    public static final int SEVERITY_FATAL= 5;

    /** Severity level: CRITICAL.*/
    public static final int SEVERITY_CRITICAL = 4;
    
    /** Severity level: ERROR.*/
    public static final int SEVERITY_ERROR = 3;
    
    /** Severity level: WARNING.*/
    public static final int SEVERITY_WARNING = 2;
    
    // /** Severity level: HARMLESS.*/
    //    public static final int SEVERITY_HARMLESS = 2;
    
    /** Severity level:INFORMATION. */
    public static final int SEVERITY_INFORMATION = 1 ;





}
