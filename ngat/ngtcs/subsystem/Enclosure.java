package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public abstract class Enclosure extends BasicMechanism
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Enclosure.java,v 1.1 2003-07-01 10:13:46 je Exp $" );


    /**
     *
     */
    public Enclosure()
    {
	super();
    }


    /**
     * Send the OPEN request to the enclosure.
     * @return boolean describing the success of the method
     */
    public boolean open()
    {
	

	return true;
    }


    /**
     * Send the CLOSE request to the enclosure.
     * @return boolean describing the success of the method
     */
    public boolean close()
    {
	return true;
    }

}
/*
 * $Date: 2003-07-01 10:13:46 $
 * $RCSfile: Enclosure.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Enclosure.java,v $
 * $Log: not supported by cvs2svn $
 */
