package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public final class MountType
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: MountType.java,v 1.1 2003-07-01 10:13:46 je Exp $" );

    public final static MountType ALTAZ      = new MountType( "ALTAZ" );

    public final static MountType ALTALT     = new MountType( "ALTALT" );

    public final static MountType EQUATORIAL = new MountType( "EQUATORIAL" );


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String name of this MountType.
     */
    private String name;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Private constructor required for type-safe enumerations.
     * Sets the name of this MountType to that specified.
     * @param newNmae the name of this MountType
     */
    private MountType( String newName )
    {
	name = newName;
    }


    /**
     * Get the <code>String</code> name of this <code>MountType</code>.
     * @return the name of this <code>MountType</code>
     * @see #name
     */
    public String getName()
    {
	return name;
    }
}
/*
 * $Date: 2003-07-01 10:13:46 $
 * $RCSfile: MountType.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/MountType.java,v $
 * $Log: not supported by cvs2svn $
 */
