package ngat.ngtcs.common;
 
/**
 * 
 * 
 * @version $Revision: 1.2 $
 * @author $Author: cjm $
 */
public class MeteorologicalData extends Data
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
        new String( "$Id: MeteorologicalData.java,v 1.2 2013-07-04 10:37:57 cjm Exp $" );

    /**
     *
     */
    protected double temperature = 273.0;

    /**
     *
     */
    protected double pressure = 775.0;

    /**
     *
     */
    protected double humidity = 0.5;

    /**
     *
     */
    protected double troposphericLapseRate = 0.0065;


    /**
     * Empty constructor for MeteorologicalData objects.
     */
    public MeteorologicalData()
    {

    }


    /**
     * Empty constructor for MeteorologicalData objects.
     */
    public MeteorologicalData( double newTemp, double newPres, double newHum )
    {
	temperature = newTemp;
	pressure = newPres;
	humidity = newHum;
    }


    /**
     * This method sets temperature in Kelvins.
     * @param newTemperatureKelvin
     * @see #temperature
     */
    public void setTemperatureKelvin( double newTemperatureKelvin )
    {
	/*
	if( !isValid( newTemperatureKelvin, 0.0, 350.0 )
	{
	    throw new IllegalArgumentException
	    ( "value ["+newTemeperatureKelvin+"] out of range for temp : "+
	    "0.0 .. 350.0" );
	}
	*/

        temperature = newTemperatureKelvin;
    }


    /**
     * This method sets temperature in degrees Celsius.
     * @param newTemperatureCelsius
     * @see #temperature
     */
    public void setTemperatureCelsius( double newTemperatureCelsius )
    {
        temperature = newTemperatureCelsius + 273.15;
    }


    /**
     * This method returns temperature in Kelvins.
     * @return temperature
     * @see #temperature
     */
    public double getTemperatureKelvin()
    {
        return temperature;
    }


    /**
     * This method returns temperature in degrees Celsius.
     * @return temperature
     * @see #temperature
     */
    public double getTemperatureCelsius()
    {
        return temperature - 273.15;
    }


    /**
     * This method sets pressure.
     * @param newPressure
     * @see #pressure
     */
    public void setPressure( double newPressure )
    {
	/*
	if( !isValid( newPressure, 0.0, 1100.0 )
	{
	    throw new IllegalArgumentException
		( "value ["+newPressure+"] out of range for pressure : "+
		  "0.0 .. 1100.0" );
	}
	*/

        pressure = newPressure;
    }


    /**
     * This method returns pressure.
     * @return pressure
     * @see #pressure
     */
    public double getPressure()
    {
        return pressure;
    }


    /**
     * This method sets humidity.
     * @param newHumidity
     * @see #humidity
     */
    public void setHumidity( double newHumidity )
    {
	/*
	if( !isValid( newHumidity, 0.0, 1.0 )
	{
	    throw new IllegalArgumentException
		( "value ["+newHumidity+"] out of range for humidity : "+
		  "0.0 .. 1.0" );
	}
	*/

        humidity = newHumidity;
    }


    /**
     * This method returns humidity.
     * @return humidity
     * @see #humidity
     */
    public double getHumidity()
    {
        return humidity;
    }


    /**
     * This method sets troposphericLapseRate.
     * @param newTroposphericLapseRate
     * @see #troposphericLapseRate
     */
    public void setTroposphericLapseRate( double newTroposphericLapseRate )
    {
	/*
	if( !isValid( newTroposphericLapseRate, 0.0, 1.0 )
	{
	    throw new IllegalArgumentException
	    ( "value ["+newTroposphericLapseRate+"] out of range for TLR : "+
	    "0.0 .. 1.0" );
	}
	*/

        troposphericLapseRate = newTroposphericLapseRate;
    }


    /**
     * This method returns troposphericLapseRate.
     * @return troposphericLapseRate
     * @see #troposphericLapseRate
     */
    public double getTroposphericLapseRate()
    {
        return troposphericLapseRate;
    }


}
/*
 *    $Date: 2013-07-04 10:37:57 $
 * $RCSfile: MeteorologicalData.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/MeteorologicalData.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
*/
