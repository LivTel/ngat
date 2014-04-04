package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class Util
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );


    /**
     * Return true if the argument A is outside the range define by arguments
     * B and C, else return false.
     * @param value the argument to be tested
     * @param minRange the minimum allowable value
     * @param maxRange the maximum allowable value
     * @return boolean describing if minRange < value < maxRange
     */
    public static boolean outOfRange( double value, double minRange, 
				      double maxRange )
    {
        if( ( value >= minRange )&&( value <= maxRange ) )
            {
                return false;
            }
        else return true;
    }


    /**
     * Return true if the argument A is outside the range define by arguments
     * B and C, else return false.
     * @param value the argument to be tested
     * @param minRange the minimum allowable value
     * @param maxRange the maximum allowable value
     * @return boolean describing if minRange < value < maxRange
     */
    public static boolean outOfRange( int value, int minRange, 
				      int maxRange )
    {
        if( ( value >= minRange )&&( value <= maxRange ) )
            {
                return false;
            }
        else return true;
    }
}
/*
 *    $Date: 2013-07-04 10:47:41 $
 * $RCSfile: Util.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Util.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
