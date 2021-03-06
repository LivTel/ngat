package ngat.message.RCS_TCS;

import ngat.message.base.*;

/** Base class for all RCS to TCS commands.
 * <br>
 * $Id$
 */
public class RCS_TO_TCS_DONE extends COMMAND_DONE {

    /** Create a RCS_TO_TCS_DONE command response with specified id.
     * @param id The unique id of the command.*/
    public RCS_TO_TCS_DONE(String id) {
	super(id);
    }

}

/** $Log: not supported by cvs2svn $ */
