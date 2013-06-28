package ngat.util.logging;

/** A template for creating LogCollators.
 */
public class LogGenerator {

    /** The system which generated the log message.*/
    private String system;

    /** The sub-system/module which generated the log message.*/
    private String subSystem;

    /** The class/type of the component which generated the log message.*/
    private String srcCompClass;

     /** The ID of the component which generated the log message.*/
    private String srcCompId;

    /** A Logger to bind to.*/
    private Logger logger;

    /** Create LogGenerator bound to a specifiec Logger.*/
    public LogGenerator(Logger logger) {
	this.logger = logger;
    }

    /** Set the ELR system field.
     * @param system the value of the system field.
     * @return This LogGenerator.
     */
    public LogGenerator system(String system) {
	this.system = system;
	return this;
    }
    
    /** Set the ELR sub system field.
     * @param subSystem the value of the sub-system field.
     * @return This LogGenerator.
     */
    public LogGenerator subSystem(String subSystem) {
	this.subSystem = subSystem;
	return this;
    }
    
    /** Set the ELR source component class field.
     * @param srcCompClass the value of the source component class field.
     * @return This LogGenerator.
     */
    public LogGenerator srcCompClass(String srcCompClass) {
	this.srcCompClass = srcCompClass;
	return this;
    } 
    
    /** Set the ELR source component ID field.
     * @param srcCompId the value of the source component ID field.
     * @return This LogGenerator.
     */
    public LogGenerator srcCompId(String srcCompId) {
	this.srcCompId = srcCompId;
	return this;
    }

    /** Create a new LogCollator instance bound to the generator's logger and setup with the appropriate ELR fields.*/
    public LogCollator create() {
	return (new LogCollator(logger)).system(system).subSystem(subSystem).srcCompClass(srcCompClass).srcCompId(srcCompId);
    }


}
