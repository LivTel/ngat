package ngat.message.OSS;

/** This class represents events in the lifecyle of a project.
 * ### TBD - Pad this out with some detail - ie. what events
 * ### TBD and how do we indicate them - 
 * ### TBD  1. int codes + internal component for details
 * ### TBD  2. subclasses to hold detail and distinguish
 */
public class ProjectStatus {

    public String event;

    public ProjectStatus(String event) {
	this.event = event;
    }

    public String toString() { return "ProjectStatus: "+event; }

}
