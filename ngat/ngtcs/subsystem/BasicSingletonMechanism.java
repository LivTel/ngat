package ngat.ngtcs.subsystem;

import ngat.util.logging.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * This class is a singleton and represents the TTL status Database used for
 * storing all information about the whole telescope.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class BasicSingletonMechanism
    extends ngat.ngtcs.subsystem.BasicMechanism
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
	new String( "$Id: BasicSingletonMechanism.java,v 1.2 2003-07-02 14:10:37 je Exp $" );

    /**
     * The <b>ONLY</b> instance of this class.
     */
    protected static BasicSingletonMechanism instance = null;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     *
     */
    protected Telescope telescope;

    /**
     *
     */
    protected Logger logger;

    /**
     *
     */
    protected String logName;

    /**
     * The Software state of this object.
     */
    protected SoftwareState state;

    /**
     * Boolean describing the initialisation state of this object.
     */
    protected boolean initialised = false;

    /**
     *
     */
    protected String errMsg = "Error!";

    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

  /**
   * Return the ONLY instance of this class.  If the instance reference is
   * NULL, then construct the instance first.
   * @return the ONLY instnce of this class
   */
  public static PluggableSubSystem getInstance()
  {
    if( instance == null )
    {
      instance = new BasicSingletonMechanism();
    }

    return instance;
  }

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The non-public Constructor required by singleton classes.
     */
    protected BasicSingletonMechanism()
    {

    }
}
/*
 *    $Date: 2003-07-02 14:10:37 $
 * $RCSfile: BasicSingletonMechanism.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/BasicSingletonMechanism.java,v $
 *      $Id: BasicSingletonMechanism.java,v 1.2 2003-07-02 14:10:37 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
