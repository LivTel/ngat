package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * Simple class to contain the relevent data to define an autoguiding centroid.
 * This data is the double precision value of centroid position on the X and Y
 * axis (in pixels) as well as the LATEST timestamp for the two data values.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_AutoguiderCentroid
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
    new String( "$Id: TTL_AutoguiderCentroid.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * The X pixel position.
   */
  protected double x;

  /**
   * The Y pixel position.
   */
  protected double y;

  /**
   * The timestamp of the LATEST pixel value
   */
  protected Timestamp timestamp;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Simple constructor, specifying the X and Y pixel values, and the
   * Timestamp.
   * @param _x the X pixel value
   * @param _y the Y pixel value
   * @param _t the Timestamp of the most recent data
   */
  public TTL_AutoguiderCentroid( double _x, double _y, Timestamp _t )
  {
    x = _x;
    y = _y;
    timestamp = _t;
  }


  /**
   * Method to read the X pixel value, as a double.
   * @return X
   * @see #x
   */
  public double getXPixel()
  {
    return x;
  }


  /**
   * Method to read the Y pixel value, as a double.
   * @return Y
   * @see #y
   */
  public double getYPixel()
  {
    return y;
  }


  /**
   * Method to read the timestamp for the most recent pixel data.
   * @return timestamp
   * @see #timestamp
   */
  public Timestamp getTimestamp()
  {
    return timestamp;
  }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_AutoguiderCentroid.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_AutoguiderCentroid.java,v $
 *      $Id: TTL_AutoguiderCentroid.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
