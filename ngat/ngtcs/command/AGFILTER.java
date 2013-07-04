package ngat.ngtcs.command;

/**
 * This command sets the position of the filter for the autoguider.  The
 * positions are either in or out of the autoguider beam.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class AGFILTER extends ngat.ngtcs.command.Command
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
    new String( "$Id: AGFILTER.java,v 1.3 2013-07-04 10:06:08 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * boolean Describing whether the autoguider filter is in the beam.
   */
  protected boolean deploy = false;

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
   * Constructor. The boolean argument describes whether the autoguide filter
   * should be used.
   * @param s the String ID of this command
   * @param b boolean describing whether the autoguide filter should be used 
   */
  public AGFILTER( String s, boolean b )
  {
    super( s );
    deploy = b;
  }


  /**
   * Return a boolean describing whether the autoguide filter should be in
   * the autoguider optical beam.
   * @return deploy
   * @see #deploy
   */
  public boolean deploy()
  {
    return deploy;
  }
}
/*
 *    $Date: 2013-07-04 10:06:08 $
 * $RCSfile: AGFILTER.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGFILTER.java,v $
 *      $Id: AGFILTER.java,v 1.3 2013-07-04 10:06:08 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:06:18  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
