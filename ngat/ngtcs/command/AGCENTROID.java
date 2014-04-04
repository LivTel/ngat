package ngat.ngtcs.command;

/**
 * This command centroids on the current guide source, then returns (x,y)
 * pixel, full-width half-maximum and magnitude of the source.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGCENTROID extends ngat.ngtcs.command.Command
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
   * Basic constructor, taking the command id as the sole argument.
   * @param s the String ID of this command.
   */
  public AGCENTROID( String s )
  {
    super( s );
  }
}
/*
 *    $Date: 2013-07-04 10:06:03 $
 * $RCSfile: AGCENTROID.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGCENTROID.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:05:22  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
