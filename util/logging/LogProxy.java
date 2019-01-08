package ngat.util.logging;

/** A proxy to use instead of a logger soas to be able to setup various fixed values
 * and then call methods in a single line to output a message.
 * e.g. proxy.method("dostuff").cat("info").log(1, message)
 *
 */
public class LogProxy {

    private Logger logger;

    private String clazz = "";

    private String method = "";

    private String source = "";

    private String cat =  "";

    public LogProxy(String clazz, String source, Logger logger) {
	this.clazz = clazz;
	this.source = source;
	this.logger = logger;
    }

    public LogProxy method(String method) {
	this.method = method;
	return this;
    }

    public LogProxy cat(String cat) {
	this.cat = cat;
	return this;
    }

    public void log(int level, String message) {
	logger.log(cat, level, clazz, source, method, message);
    }
    
    public void setLogLevel(int level) {
	logger.setLogLevel(level);
    }

}
