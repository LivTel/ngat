package ngat.util.logging;

/** Collects information neccessary to generate ELRs. LogCollators are created by a
 * LogGenerator by calling LogGenerator.create(). The setter methods in LogCollator
 * all return the instance on which they were called soas to allow chaining while a
 * record is being built up.
 * Usage example
 *
 * <code>
 *  generator.create().system("X").subSystem("Y").srcCompClass("Z").warning().msg("A test").
 *    context("K1", "V1").
 *    context("K2", "V2").
 *    context("K3", "V3").
 *     send();
 * </code>
 */
public class LogCollator {

    /** A Logger to send the generated ELR via.*/
    private Logger logger;
    
    /** An ELR to build.*/
    private ExtendedLogRecord elr;

    /** Create a LogCollator bound to the specified Logger.
     * @param logger  A logger to send the collated ELR log via.
     */
    public LogCollator(Logger logger) {
	this.logger = logger;
	elr = new ExtendedLogRecord();	
	// Set some default values incase they are not set.
	elr.setLevel(1);
	elr.setSeverity(Logging.SEVERITY_INFORMATION);
    }
    
    /** Set the ELR system field.
     * @param system the value of the system field.
     * @return This LogCollator.
     */
    public LogCollator system(String system) {
	elr.setSystem(system);
	return this;
    }
    
    /** Set the ELR sub system field.
     * @param subSystem the value of the sub-system field.
     * @return This LogCollator.
     */
    public LogCollator subSystem(String subSystem) {
	elr.setSubSystem(subSystem);
	return this;
    }
    
    /** Set the ELR source component class field.
     * @param srcCompClass the value of the source component class field.
     * @return This LogCollator.
     */
    public LogCollator srcCompClass(String srcCompClass) {
	elr.setSrcCompClass(srcCompClass);
	return this;
    } 
    
    /** Set the ELR source component ID field.
     * @param srcCompId the value of the source component ID field.
     * @return This LogCollator.
     */
    public LogCollator srcCompId(String srcCompId) {
	elr.setSrcCompId(srcCompId);
	return this;
    }

    /** Set the ELR source block ID field.
     * @param block the value of the source block ID field.
     * @return This LogCollator.
     */
    public LogCollator block(String block) {
	elr.setBlock(block);
	return this;
    }
    
    /** Set the ELR severity field.
     * @param severity the value of the severity field.
     * @return This LogCollator.
     */
    public LogCollator severity(int severity) {
	elr.setSeverity(severity);
	return this;
    }
    
    /** Set the ELR level field.
     * @param level the value of the level field.
     * @return This LogCollator.
     */
    public LogCollator level(int level) {
	elr.setLevel(level);
	return this;
    }
    
    /** Set the ELR theme field.
     * @param theme the value of the theme field.
     * @return This LogCollator.
     */
    public LogCollator theme(String theme) {
	elr.setTheme(theme);
	return this;
    }
    
    /** Set the ELR category field.
     * @param category the value of the category field.
     * @return This LogCollator.
     */
    public LogCollator category(String category) {
	elr.setCategory(category);
	return this;
    }
    
    /** Set the ELR message field.
     * @param message the value of the message field.
     * @return This LogCollator.
     */
    public LogCollator msg(String message) {
	elr.setMessage(message);
	return this;
    }
    
    /** Add a context entry (key, value) pair.
     * @param key the context variable name.
     * @param value the context variable value.
     * @return This LogCollator.
     */
    public LogCollator context(String key, String value) {
	elr.addContextEntry(key, value);
	return this;
    }

    /** Convenience method. Sets the ELR severity level to FATAL.
     * @return This LogCollator.
     */
    public LogCollator fatal() {
	elr.setSeverity(Logging.SEVERITY_FATAL);
	return this;
    }
    
    /** Convenience method. Sets the ELR severity level to CRITICAL.
     * @return This LogCollator.
     */
    public LogCollator critical() {
	elr.setSeverity(Logging.SEVERITY_CRITICAL);
	return this;
    }
    
    /** Convenience method. Sets the ELR severity level to ERROR.
     * @return This LogCollator.
     */
    public LogCollator error() {
	elr.setSeverity(Logging.SEVERITY_ERROR);
	return this;
    }
    
    /** Convenience method. Sets the ELR severity level to WARNING.
     * @return This LogCollator.
     */
    public LogCollator warn() {
	elr.setSeverity(Logging.SEVERITY_WARNING);
	return this;
    }
    
    
    /** Convenience method. Sets the ELR severity level to INFORMATION.
     * @return This LogCollator.
     */
    public LogCollator info() {
	elr.setSeverity(Logging.SEVERITY_INFORMATION);
	return this;
    }

      
    /** Publish the log record (ELR) to the attached logger.*/
    public void send() {
	elr.setTime(System.currentTimeMillis());
	logger.xlog(elr);
    }

    /** Sets various fields based on call info extracted from stack frames - very expensive.*/
    public LogCollator extractCallInfo() {
	Throwable t = new Throwable();
        StackTraceElement[] stack = t.getStackTrace();

	//System.err.println("Stack [0]= "+stack[0]);

	if (stack.length > 1) {
	    elr.setSrcCompClass(stack[1].getClassName());
	    elr.setBlock(stack[1].getMethodName()+":"+stack[1].getLineNumber());
	    //System.err.println("Called by: "+stack[1]);
	    elr.setThread(Thread.currentThread().getName());
	}
	return this;
    }

    public String toString() {
	return "LogCollator: Bound to: "+logger.getName()+", ELR=["+elr+"]";
    }

}
