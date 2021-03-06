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
 * @author $Author$
 * @version $Revision$
 */
public class SHOW_ASTROMETRY_Done extends CommandDone
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
  private double wavelength;

  /**
   *
   */
  private double temperature;

  /**
   *
   */
  private double pressure;

  /**
   *
   */
  private double humidity;

  /**
   *
   */
  private double ut1_utc;

  /**
   *
   */
  private double poleX;

  /**
   *
   */
  private double poleY;

  /**
   *
   */
  private double tdt_utc;

  /**
   *
   */
  private double airmass;

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
  public SHOW_ASTROMETRY_Done( SHOW s,
			       double wav,
			       double temp,
			       double pres,
			       double hum,
			       double ut1_utc,
			       double polx,
			       double poly,
			       double tdt_utc,
			       double air,
			       double agw )
  {
    super( (Command)s );

    wavelength = wav;
    temperature = temp;
    pressure = pres;
    humidity = hum;
    ut1_utc = ut1_utc;
    poleX = polx;
    poleY = poly;
    tdt_utc = tdt_utc;
    airmass = air;
    autoguiderWavelength = agw;
  }


  /**
   *
   */
  public double getWavelength()
  {
    return( wavelength );
  }


  /**
   *
   */
  public double getTemperature()
  {
    return( temperature );
  }


  /**
   *
   */
  public double getWavelength()
  {
    return( pressure );
  }


  /**
   *
   */
  public double getPressure()
  {
    return( humidity );
  }


  /**
   *
   */
  public double getHumidity()
  {
    return( ut1_utc );
  }


  /**
   *
   */
  public double getUT1_UTC()
  {
    return( poleX );
  }


  /**
   *
   */
  public double getPole()
  {
    return( poleY );
  }


  /**
   *
   */
  public double getTDT_UTC()
  {
    return( tdt_utc );
  }


  /**
   *
   */
  public double getAirmass()
  {
    return( airmass );
  }


  /**
   *
   */
  public double getAutoguiderWavelength()
  {
    return( autoguiderWavelength );
  }
}
/*
 *    $Date: 2013-07-04 10:07:35 $
 * $RCSfile: SHOW_ASTORMETRY_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_ASTORMETRY_Done.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
