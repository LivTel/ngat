package ngat.ngtcs.subsystem;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public abstract class PluggableSubSystemFactory
  implements PluggableSubSystemCreator
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: PluggableSubSystemFactory.java,v 1.3 2013-07-04 10:54:37 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * This method is a general purpose factory method to instantiate a class
   * with <emph>exactly</emph> the same name as the inheriting class,
   * without the Factory suffix.  e.g.
   * an inheriting class <code>org.example.MechanismFactory</code> will
   * instantiate the <code>org.example.Mechanism</code> class automatically
   * using this method by extending this class.
   * @return an instance of this factorys' PluggableSubSystem
   */
  public PluggableSubSystem getInstance()
    throws InstantiationException
  {
    String s1 = this.getClass().getName();
    String s2 = s1.substring( 0, s1.lastIndexOf( "Factory" ) );

    try
    {
      return (PluggableSubSystem)
	( Class.forName( s2 ).newInstance() );
    }
    catch( Exception e )
    {
      throw new InstantiationException( e.toString() );
    }
  }
}
/*
 *    $Date: 2013-07-04 10:54:37 $
 * $RCSfile: PluggableSubSystemFactory.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/PluggableSubSystemFactory.java,v $
 *      $Id: PluggableSubSystemFactory.java,v 1.3 2013-07-04 10:54:37 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:01:09  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
