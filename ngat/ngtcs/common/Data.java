package ngat.ngtcs.common;

import java.lang.String;

/**
 * This class is a subset of the Telescope Data used in the astrometric
 * transformations performed by the NGTCS.
 *
 * Some method overloading has been used to re-direct references to make access
 * from the Java Native Interface easier.
 * 
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public abstract class Data implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
        new String( "$Id: Data.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Data Constructor.
     * @param str the name of this site.
     */
    public Data()
    {

    }


    /**
     * Check the input values
     * @param value
     * @param minRange
     * @param maxRange
     * @return boolean describing whether this argument is valid
     */
    protected boolean isValid( double value, double minRange, double maxRange )
    {
	if( ( value >= minRange )&&( value <= maxRange ) )
	    {
		return true;
	    }
	else return false;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Data.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Data.java,v $
 *     $Log: not supported by cvs2svn $
 */
