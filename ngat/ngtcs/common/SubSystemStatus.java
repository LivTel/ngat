package ngat.ngtcs.common; 

/**
 * The generic SubSystemStatus class - used only for testing
 *
 * @author $Author$ 
 * @version $Revision$
 */
public class SubSystemStatus extends Status
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
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * PluggableSubSystem for which this is a Status.
   */
  protected String systemName = null;

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
   * Constructor.
   */
  public SubSystemStatus( String s )
  {
    super();
    systemName = new String( s );
  }
}
/*
 *    $Date: 2013-07-04 10:46:31 $
 * $RCSfile: SubSystemStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SubSystemStatus.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
