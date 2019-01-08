package ngat.ngtcs.command;

import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGCENTROIDDone extends CommandDone
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
   *
   */
  protected double xPixel;

  /**
   *
   */
  protected double yPixel;

  /**
   *
   */
  protected double fwhm;

  /**
   *
   */
  protected double mag;

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
   *
   */
  public AGCENTROIDDone( AGCENTROID a )
  {
    super( (Command)a );
  }


  /**
   * Set the X pixel retrieved from the Status Database.
   * @param d the latest X pixel
   */
  public void setXPixel( double d )
  {
    xPixel = d;
  }


  /**
   * Retrieve the X pixel of the most recent centroid.
   * @return xPixel
   * @see #xPixel
   */
  public double getXPixel()
  {
    return xPixel;
  }

  /**
   * Set the Y pixel  retrieved from the Status Database.
   * @param d the latest Y pixel
   */
  public void setYPixel( double d )
  {
    yPixel = d;
  }


  /**
   * Retrieve the Y pixel of the most recent centroid.
   * @returnyPixel
   * @see #yPixel
   */
  public double getYPixel()
  {
    return yPixel;
  }

  /**
   * Set the FWHM retrieved from the Status Database.
   * @param d the latest 
   */
  public void setFWHM( double d )
  {
    fwhm = d;
  }


  /**
   * Retrieve the FWHM of the most recent centroid.
   * @return fwhm
   * @see #fwhm
   */
  public double getFWHM()
  {
    return fwhm;
  }

  /**
   * Set the magnitude retrieved from the Status Database.
   * @param d the latest magnitude
   */
  public void setMagnitude( double d )
  {
    mag = d;
  }


  /**
   * Retrieve the magnitude of the most recent centroid.
   * @return mag
   * @see #mag
   */
  public double getMagnitude()
  {
    return mag;
  }

}
/*
 *    $Date: 2013-07-04 10:05:47 $
 * $RCSfile: AGCENTROIDDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGCENTROIDDone.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:05:54  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
