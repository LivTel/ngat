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
public class SHOW_METEOROLOGY_Done extends CommandDone
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
  private String wmsStatus;

  /**
   *
   */
  private boolean rainAlarm;

  /**
   *
   */
  private double trussTemperature;

  /**
   *
   */
  private double oilTemperature;

  /**
   *
   */
  private double primaryMirrorTemperature;

  /**
   *
   */
  private double secondaryMirrorTemperature;

  /**
   *
   */
  private double airTemperature;

  /**
   *
   */
  private double windSpeed;

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
  private double windDirection;

  /**
   *
   */
  private double moistureLevel;

  /**
   *
   */
  private double dewPointTemperature;

  /**
   *
   */
  private double aNgBoxTemp

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
  public SHOW_METEOROLOGY_Done( SHOW s,
				String wmsStat,
				boolean rain,
				double trussTemp,
				double oilTemp,
				double pmTemp,
				double smTemp,
				double airTemp,
				double windSpd,
				double windDir,
				double press,
				double hum,
				double moist,
				double dewPnt,
				double agTemp )
  {
    super( (Command)s );

    wmsStatus = wmsStat;
    rainAlarm = rain;
    trussTemperature = trussTemp;
    oilTemperature = oilTemp;
    primaryMirrorTemperature = pmTemp;
    secondaryMirrorTemperature = smTemp;
    airTemperature = airTemp;
    windSpeed = windSpd;
    pressure = press;
    humidity = hum;
    windDirection = windDir;
    moistureLevel = moist;
    dewPointTemperature = dewPnt;
    aNgBoxTemp = agTemp;
  }


  /**
   *
   */
  public String getWMSStatus()
  {
    return( wmsStatus );
  }


  /**
   *
   */
  public boolean getRainAlarm()
  {
    return( rainAlarm );
  }


  /**
   *
   */
  public double getTrussTemperature()
  {
    return( trussTemperature );
  }


  /**
   *
   */
  public double getOilTemperature()
  {
    return( oilTemperature );
  }


  /**
   *
   */
  public double getPrimaryMirrorTemperature()
  {
    return( primaryMirrorTemperature );
  }


  /**
   *
   */
  public double getSecondaryMirrorTemperature()
  {
    return( secondaryMirrorTemperature );
  }


  /**
   *
   */
  public double getAirTemperature()
  {
    return( airTemperature );
  }


  /**
   *
   */
  public double getWindSpeed()
  {
    return( windSpeed );
  }


  /**
   *
   */
  public double getPressure()
  {
    return( pressure );
  }


  /**
   *
   */
  public double getHumidity()
  {
    return( humidity );
  }


  /**
   *
   */
  public double getWindDirection()
  {
    return( windDirection );
  }


  /**
   *
   */
  public double getMoistureLevel()
  {
    return( moistureLevel );
  }


  /**
   *
   */
  public double getDewPointTemperature()
  {
    return( dewPointTemperature );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_METEOROLOGY_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_METEOROLOGY_Done.java,v $
 *      $Id: SHOW_METEOROLOGY_Done.java,v 1.1 2006-11-20 14:47:33 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
