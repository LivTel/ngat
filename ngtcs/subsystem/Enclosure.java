package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 */
public abstract class Enclosure extends BasicMechanism
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );


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
  public abstract void open() throws SystemException;


  /**
   * Send the CLOSE request to the enclosure.
   * @return boolean describing the success of the method
   */
  public abstract void close() throws SystemException;


  /**
   * Check if the enclosure is open.
   * @return boolean describing whether the enclosure is open
   */
  public abstract boolean isOpen();

}
/*
 * $Date: 2013-07-04 10:54:11 $
 * $RCSfile: Enclosure.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Enclosure.java,v $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2003/07/01 10:13:46  je
 * Initial revision
 *
 */
