package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class Util
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Util.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


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
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Util.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Util.java,v $
 *     $Log: not supported by cvs2svn $
 */
