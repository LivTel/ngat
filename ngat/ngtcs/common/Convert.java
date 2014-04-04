package ngat.ngtcs.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class Convert
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     * Format used to align argument values.
     */
    protected static DecimalFormat df = new DecimalFormat();

    /**
     *
     */
    protected static SimpleDateFormat sdf = new SimpleDateFormat
	( "EEE MMM dd HH:mm:ss yyyy zzz" );

    /**
     * Tokenizer used to break up strings.
     */
    protected static StringTokenizer st;

    /**
     * Return string.
     */
    protected static String ret = null;

    /**
     * Character length of format.
     */
    protected static int stringLength = 0;

    /**
     * Constant used in calculating format
     */
    protected static final double ln10 = Math.log( 10.0 );

    /**
     *
     *
     */
    public static void setCharacterLength( int length )
    {
	stringLength = length;
    }


    /**
     * Convert Radians to Hours, Minutes, Seconds from 0 - 23:59:59.999
     * @param angleIn the angle in radians.
     * @return string format of input angle.
     */
    public static String radiansTo24HMSString( double angleIn )
    {
	return formatAngle2( 'a', 3, angleIn );
    }


    /**
     * Convert Radians to Hours, Minutes, Seconds from -11:59:59.999 to
     * +12:00:00.000
     * @param angleIn the angle in radians.
     * @return string format of input angle.
     */
    public static String radiansTo12HMSString( double angleIn )
    {
	return formatAngle2( 'b', 3, angleIn );
    }


    /**
     * Convert Radians to Hours, Minutes from 0 - 23:59.999
     * @param angleIn the angle in radians.
     * @return string format of input angle.
     */
    public static String radiansTo24HMDecimalString( double angleIn )
    {
	while( angleIn < 0.0 )
	    {
		angleIn += 2.0 * Math.PI;
	    }
	while( angleIn >= (2.0 * Math.PI) )
	    {
		angleIn -= 2.0 * Math.PI;
	    }

	return formatAngle( "00.0000000", Math.toDegrees( angleIn ) / 15.0 );
    }


    /**
     * Convert angle in radians to Time String of the specified format in 24 hr
     * fashion, i.e. without sign.
     * @param angleIn the angle to convert
     * @param formatString the DecimalFormat style string format
     * @return the formatted string
     */
    public static String radiansTo24HFormattedTime( String formatString,
						    double angleIn )
    {
	if( formatString == null ) return( "" );

	while( angleIn < 0.0 )
	    {
		angleIn += ( 2.0 * Math.PI );
	    }
	while( angleIn >= (2.0 * Math.PI) )
	    {
		angleIn -= ( 2.0 * Math.PI );
	    }
	double angle = Math.toDegrees( angleIn ) / 15.0;

	return formatAngle( formatString, angle );
    }


    /**
     * Convert Radians to Degrees, Minutes, Seconds from 0.0 to 359.999
     * @param angleIn the angle
     * @return string format of input angle
     */
    public static String radiansTo360DMSString( double angleIn )
    {
	return formatAngle2( 'c', 2, angleIn );
    }


    /**
     * Convert Radians to Degrees, Minutes, Seconds from -179.9990 to +180.000
     * @param angleIn the angle
     * @return string format of input angle
     */
    public static String radiansTo180DMSString( double angleIn )
    {
	return formatAngle2( 'd', 2, angleIn );
    }


    /**
     *
     *
     *
     *
     */
    public static String radiansTo360DDecimalString( double angleIn )
    {
	while( angleIn < 0.0 )
	    {
		angleIn += 2.0 * Math.PI;
	    }
	while( angleIn >= (2.0 * Math.PI) )
	    {
		angleIn -= 2.0 * Math.PI;
	    }

	return formatAngle( "000.000000", Math.toDegrees( angleIn ) );
    }


    /**
     *
     *
     *
     *
     */
    public static String radiansTo180DDecimalString( double angleIn )
    {
	while( angleIn < Math.PI )
	    {
		angleIn += ( 2.0 * Math.PI );
	    }
	while( angleIn >= Math.PI )
	    {
		angleIn -= ( 2.0 * Math.PI );
	    }

	return formatAngle( "000.000000", Math.toDegrees( angleIn ) );
    }


    /**
     * Convert angle in radians to Angle String of the specified format in 
     * 360 degres fashion, i.e. without sign.
     * @param angleIn the angle to convert
     * @param formatString the DecimalFormat style string format
     * @return the formatted string
     */
    public static String radiansTo360DMSFormattedAngle( String formatString,
							double angleIn )
    {
	if( formatString == null ) return( "" );

	while( angleIn <= 0.0 )
	    {
		angleIn += ( 2.0 * Math.PI );
	    }
	while( angleIn > (2.0 * Math.PI) )
	    {
		angleIn -= ( 2.0 * Math.PI );
	    }

	double angle = Math.toDegrees( angleIn );

	return formatAngle( formatString, angle );
    }


    /**
     * Convert angle in radians to Angle String of the specified format in 
     * 180 degres fashion, i.e. with sign.
     * @param angleIn the angle to convert
     * @param formatString the DecimalFormat style string format
     * @return the formatted string
     */
    public static String radiansTo180DMSFormattedAngle( String formatString,
							double angleIn )
    {
	if( formatString == null ) return( "" );

	while( angleIn < Math.PI )
	    {
		angleIn += ( 2.0 * Math.PI );
	    }
	while( angleIn >= Math.PI )
	    {
		angleIn -= ( 2.0 * Math.PI );
	    }

	double angle = Math.toDegrees( angleIn );

	return formatAngle( "+"+formatString, angle );
    }


  /**
   *
   */
  public static String xyzMatrixToRADec( XYZMatrix mat )
  {
    String result = null;

    result = radiansTo24HMSString
      ( Math.atan2( mat.getY(), mat.getX() ) );

    return result+" "+radiansTo180DMSString( Math.asin( mat.getZ() ) );
  }


  /**
   *
   */
  public static String xyzMatrixToHADec( XYZMatrix mat )
  {
    String result = null;

    result = radiansTo12HMSString
      ( Math.atan2( -mat.getY(), mat.getX() ) );

    return result+" "+radiansTo180DMSString( Math.asin( mat.getZ() ) );
  }


  protected static double getMod( double arg1, double arg2 )
  {
    double argInt;

    if( arg1 < 0.0 )
      {
	argInt = Math.ceil( arg1/arg2 );
      }
    else
      {
	argInt = Math.floor( arg1/arg2 );
      }

    return( arg1 - ( argInt * arg2 ) );
  }


  /**
   *
   */
  public static String xyzMatrixToAltAz( XYZMatrix mat )
  {
    String result = null;

    result = radiansTo360DDecimalString
	( Math.atan2( -( mat.getY() ), mat.getX() ) );

    return result+" "+radiansTo180DDecimalString( Math.asin( mat.getZ() ) );
  }


    public static String signedFormat( String format, double value )
    {
	df.applyPattern( format );
	String returnString = df.format( value );
	if( value < 0.0 )
	    {
		return ( returnString );
	    }
	else
	    {
		return ( "+"+returnString );
	    }
    }


    public static String format( String format, double value )
    {
	df.applyPattern( format );
	return df.format( value );
    }


    /**
     *
     */
    public static String formatTime( long seconds )
    {
	return sdf.format( new java.util.Date( seconds * 1000 ) );
    }


    /**
     *
     *
     *
     */
    protected static String formatAngle( String formatString, double angleIn )
    {
	String units=null, minutes=null, seconds=null;
	double remainder;

	st = new StringTokenizer( formatString, " " );
	units=st.nextToken();
	if( st.hasMoreTokens() )
	    {
		minutes = st.nextToken();
	    }
	if( st.hasMoreTokens() )
	    {
		seconds = st.nextToken();
	    }

	df.applyPattern( units );

	if( minutes == null )
	    {
		return( df.format( angleIn ) );
	    }
	else
	    {
		ret = df.format( Math.floor( angleIn ) )+" ";
	    }

	df.applyPattern( minutes );
	remainder = ( angleIn - Math.floor( angleIn ) ) * 60.0;

	if( seconds == null )
	    {
		return( ret+df.format( remainder ) );
	    }
	else
	    {
		ret = ret+df.format( Math.floor( remainder ) )+" ";
		remainder = ( remainder - Math.floor( remainder ) ) * 60.0;
		df.applyPattern( seconds );
		return( ret+df.format( remainder ) );
	    }

	/*
	remainder = angleIn - Math.floor( angleIn );
	if( st.hasMoreTokens() )
	    {
		minutes = st.nextToken();
		df.applyPattern( minutes );
		ret = ( ret+" "+df.format
			( Math.floor( remainder*60.0 ) ) );
	    }
	if( st.hasMoreTokens() )
	    {
		seconds = st.nextToken();
		df.applyPattern( seconds );
		ret = ( ret+" "+df.format
			( getMod( ( angleIn*3600.0 ), 60.0 ) ) );
	    }
	return ret;
	*/
    }





    /**
     * This method formats an input angle into the specified format with
     * the specified number of decimal places.
     * The types of angle which can be formatted are:
     * a)   00 00 00.000000000  -   23 59 59.999999999 : unsigned H M S
     * b)  -11 59 59.999999999  -  +12 00 00.000000000 : signed H M S
     * c)  000 00 00.000000000  -  359 59 59.999999999 : unsigned D M S
     * d) -179 59 59.999999999  - +180 00 00.000000000 : signed D M S
     *
     * @param type the tpye of angle to be formatted into
     * @param decimalPlaces the number of decimal places for the seconds
     * @param angleIn the angle to be formatted in radians
     * @return the formatted angle as a String object
     */
  public static String formatAngle2( char type, int decimalPlaces, 
				     double angleIn )
    {
	double units = 0.0;
	long unitsLong = 0;
	double minutes = 0.0;
	long minutesLong = 0;
	long allMinutes = 0;
	double seconds = 0.0;
	long secondsLong = 0;
	long allSeconds = 0;
	double fractional = 0.0;
	long fractionalLong = 0;
	long allDecimalLong = 0;
	long absDecimalLong = 0;
	double allDecimal = 0.0;
	long decimals = 0;
	long    sign = 0;
	long oneEightyDegrees = 0;
	long twelveHours = 0;
	String returnString = "";
	String formatString = "";
	char signChar = ' ';

	if( ( decimalPlaces < 0 ) || ( decimalPlaces > 9 ) )
	    {
		return "decimal places out of range 0..9\n";
	    }

	for( int i = 0; i < decimalPlaces; i++ )
	  {
	    formatString = ( formatString+"0" );
	  }

	if( angleIn < 0.0 )
	    {
		sign = -1;
	    }

	units = Math.toDegrees( angleIn );

	if( ( type == 'a' ) || ( type == 'b' ) )
	    {
		units /= 15.0;
	    }

	decimals = (long)Math.pow( 10.0, (double)decimalPlaces );
	allDecimal = units * 3600.0 * (double)decimals;
	allDecimalLong = (long)Math.floor( allDecimal );
	if( Math.abs( allDecimal - (double)allDecimalLong ) >= 0.5 )
	    {
		allDecimalLong++;
	    }


	twelveHours = 12 * 3600 * decimals;
	oneEightyDegrees = 180 * 3600 * decimals;

	switch( type )
	    {
	    case 'a':
		while( allDecimalLong < 0 )
		    {
			allDecimalLong += ( 2 * twelveHours );
		    }
		while( allDecimalLong >= ( 2 * twelveHours ) )
		    {
			allDecimalLong -= ( 2 * twelveHours );
		    }
		break;

	    case 'b':
		while( allDecimalLong <= -twelveHours )
		    {
			allDecimalLong += ( 2 * twelveHours );
		    }
		while( allDecimalLong > twelveHours )
		    {
			allDecimalLong -= ( 2 * twelveHours );
		    }
		break;

	    case 'c':
		while( allDecimalLong < 0 )
		    {
			allDecimalLong += ( 2 * oneEightyDegrees );
		    }
		while( allDecimalLong >= ( 2 * oneEightyDegrees ) )
		    {
			allDecimalLong -= ( 2 * oneEightyDegrees );
		    }
		break;

	    case 'd':
		while( allDecimalLong <= -oneEightyDegrees )
		    {
			allDecimalLong += ( 2 * oneEightyDegrees );
		    }
		while( allDecimalLong > oneEightyDegrees )
		    {
			allDecimalLong -= ( 2 * oneEightyDegrees );
		    }
		break;

	    default:
		return( "No such type ["+type+"] formatting!" );
	    }

	absDecimalLong = (long)Math.abs( allDecimalLong );
	fractionalLong = ( absDecimalLong ) % decimals;
	allSeconds = ( absDecimalLong - fractionalLong ) / decimals;
	secondsLong = allSeconds % 60;
	allMinutes = ( allSeconds - secondsLong ) / 60;
	minutesLong = allMinutes % 60;
	unitsLong = ( allMinutes - minutesLong ) / 60;
	df.applyPattern( "00" );


	switch( type )
	    {
	    case 'a':
	    case 'b':
		returnString = ( df.format( unitsLong )+" "+
				 df.format( minutesLong )+" "+
				 df.format( secondsLong )+"." );
		break;

	    case 'c':
	    case 'd':
		returnString = ( df.format( unitsLong )+" "+
				 df.format( minutesLong )+" "+
				 df.format( secondsLong )+"." );
		break;
	    }


	df.applyPattern( formatString );

	if( ( type == 'b' ) || ( type == 'd' ) )
	  {
	    if( sign < 0 )
	      {
		signChar = '-';
	      }
	    else
	      {
		signChar = '+';
	      }
	  }

	return ( signChar+returnString+df.format( fractionalLong ) );
    }

}
/*
 *    $Date: 2013-07-04 10:35:38 $
 * $RCSfile: Convert.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Convert.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
