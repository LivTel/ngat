package ngat.util.logging;

/** Interface which is implemented by classes which wish to save 
 * their state (or other information) in a from which can be put
 * into a LogRecord as parameters.*/
public interface Loggable {

    /** Implementors should return an array of wrappers or other
     * objects to indicate their current state.
     * @return The current state variables, wrapped if neccessary.*/
    public Object[] grabState();
    
}
