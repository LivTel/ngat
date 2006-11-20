package ngat.ngtcs.command;

import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * The response to the SHOW command, containing the information requested by
 * the SHOW command specified in the constructor.  For information requested
 * from this object that was not specified by the original SHOW command, or not
 * applicabel due to the Telescope's current mode of operation, the returned
 * values will be zero or null, depending upon the return type.  This
 * <b>MUST</b> be checked for in the control process that requested the data
 * with the SHOW command.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class SHOW_LIMITS_Done extends CommandDone
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
    new String( "$Id:" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  private double positiveAzimuthLimit;

  /**
   *
   */
  private double negativeAzimuthLimit;

  /**
   *
   */
  private double positiveAltitudeLimit;

  /**
   *
   */
  private double negativeAltitudeLimit;

  /**
   *
   */
  private double positiveRotatorLimit;

  /**
   *
   */
  private double negativeRotatorLimit;

  /**
   *
   */
  private double timetoAzimuthLimit;

  /**
   *
   */
  private double timeToAltitudeLimit;

  /**
   *
   */
  private double timeToRotatorLimit;

  /**
   *
   */
  private double azimuthLimitSense;

  /**
   *
   */
  private double altitudeLimitSense;

  /**
   *
   */
  private double rotatorLimitSense;

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
   * Initialise the reply to the specified SHOW command containing the
   * <code><b>true</b></code> successful flag and requested data or a
   * <code><b>false</b></code> successful flag.
   * @param s the SHOW command to which this is a reply
   */
  public SHOW_LIMITS_Done( SHOW s,
			   double posAzLim,
			   double negAzLim,
			   double posElLim,
			   double negElLim,
			   double posRotLim,
			   double negRotLim,
			   double tAzLim,
			   double azLim,
			   double tElLim,
			   double elLim,
			   double tRotLim,
			   double rotLim )
  {
    super( s );

    positiveAzimuthLimit = posAzLim;
    negativeAzimuthLimit = negAzLim;
    positiveAltitudeLimit = posElLim;
    negativeAltitudeLimit = negElLim;
    positiveRotatorLimit = posRotLim;
    negativeRotatorLimit = negRotLim;
    timetoAzimuthLimit = tAzLim;
    timeToAltitudeLimit = azLim;
    timeToRotatorLimit = tElLim;
    azimuthLimitSense = elLim;
    altitudeLimitSense = tRotLim;
    rotatorLimitSense = rotLim;
  }


  /**
   *
   */
  public double getPositiveAzimuthLimit()
  {
    return( positiveAzimuthLimit );
  }


  /**
   *
   */
  public double getNegativeAzimuthLimit()
  {
    return( negativeAzimuthLimit );
  }


  /**
   *
   */
  public double getPositiveAltitudeLimit()
  {
    return( positiveAltitudeLimit );
  }


  /**
   *
   */
  public double getNegativeAltitudeLimit()
  {
    return( negativeAltitudeLimit );
  }


  /**
   *
   */
  public double getPositiveRotatorLimit()
  {
    return( positiveRotatorLimit );
  }


  /**
   *
   */
  public double getNegativeRotatorLimit()
  {
    return( negativeRotatorLimit );
  }


  /**
   *
   */
  public double getTimetoAzimuthLimit()
  {
    return( timetoAzimuthLimit );
  }


  /**
   *
   */
  public double getTimeToAltitudeLimit()
  {
    return( timeToAltitudeLimit );
  }


  /**
   *
   */
  public double getTimeToRotatorLimit()
  {
    return( timeToRotatorLimit );
  }


  /**
   *
   */
  public double getAzimuthLimitSense()
  {
    return( azimuthLimitSense );
  }


  /**
   *
   */
  public double getAltitudeLimitSense()
  {
    return( altitudeLimitSense );
  }


  /**
   *
   */
  public double getRotatorLimitSense()
  {
    return( rotatorLimitSense );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_LIMITS_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_LIMITS_Done.java,v $
 *      $Id: SHOW_LIMITS_Done.java,v 1.1 2006-11-20 14:47:33 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
