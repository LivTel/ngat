package ngat.ngtcs.common;

import java.lang.Double;
import java.lang.String;

/**
 * Definitions of Equinoxes
 * 
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public final class Equinox implements java.io.Serializable
{
    /**
     * Name of Equinox
     */
    private String name = "J2000";

    /**
     * Constant integer representation of the standard Equinoxes, 
     * set to 0 (zero) for non-standard values (B1950, J2000, ICRF, APPARENT ).
     * Primarily implemented for JNI interaction
     */
    private int eqInt = 2032;

    /**
     * Epoch of this Equinox for non ICRF/APPARENT Equinoxes.
     */
    private Epoch epoch = new Epoch( 2000.0 );

    /**
     * Equinox B1950
     */
    public final static Equinox B1950 = 
	new Equinox( "B1950", 
		     new Epoch( CalendarType.BESSELIAN,
				1950.0,
				TimescaleType.TDB ),
		     2031 );

    /**
     * Equinox FK4
     */
    public final static Equinox FK4 = B1950;

    /**
     * Equinox J2000
     */
    public final static Equinox J2000 = 
	new Equinox( "J2000", 
		     new Epoch( CalendarType.JULIAN,
				2000.0,
				TimescaleType.TDB ),
		     2032 );

    /**
     * Equinox FK5
     */
    public final static Equinox FK5 = J2000;

    /**
     * ICRF
     */
    public final static Equinox ICRF = 
	new Equinox( "ICRF", 2033 );

    /**
     * APPARENT Equinox
     */
    public final static Equinox APPARENT = 
	new Equinox( "APPARENT", 2034 );


    /**
     * Private constructor used in this class to implement the standard
     * equinoxes.
     * @param newName name of this standard Equinox
     * @param newEpoch epoch of this standard Equinox
     * @param newInt integer representation of this standard Equinox
     */
    private Equinox( String newName, Epoch newEpoch, int newInt )
    {
	name  = newName;
	epoch = newEpoch;
	eqInt = newInt;
    }


    /**
     * Private constructor used in this class to implement the standard
     * equinoxes.
     * @param newName name of this standard Equinox
     * @param newInt integer representation of this standard Equinox
     */
    private Equinox( String newName, int newInt )
    {
	name  = newName;
	eqInt = newInt;
    }


    /**
     * Public constructor for a standard equinox of the specified name.
     * Non-standard names will throw the IllegalArgumentException.  
     * The test is case insensitve.
     * @param s the name of the Equinox (B1950, FK4, FK5, J2000, ICRF, APPARENT );
     */
    public Equinox( String s ) throws java.lang.IllegalArgumentException
    {
	if( ( s.equalsIgnoreCase( Equinox.B1950.getName() ) ||
	      ( s.equalsIgnoreCase( "fk4" ) ) ) )
	    {
		epoch = B1950.getEpoch();
		eqInt = B1950.getInt();
	    }
	else if( ( s.equalsIgnoreCase( Equinox.J2000.getName() ) ) ||
		 ( s.equalsIgnoreCase( "fk5" ) ) )
	    {
		epoch = J2000.getEpoch();
		eqInt = J2000.getInt();
	    }
	else if( s.equalsIgnoreCase( Equinox.ICRF.getName() ) )
	    {
		eqInt = ICRF.getInt();
	    }
	else if( s.equalsIgnoreCase( Equinox.APPARENT.getName() ) )
	    {
		eqInt = APPARENT.getInt();
	    }
	else
	    {
		throw new java.lang.IllegalArgumentException
		    ( "unrecognised standard equinox name ["+s+"] " );
	    }
    }


    /**
     * Public constructor for an equinox of the specified epoch.
     * Standard epochs (B1950, J2000) are recognised.
     * @param epoch the epoch of this Equinox
     * @see Epoch
     */
    public Equinox( Epoch e )
    {
	if( e.equals( B1950.getEpoch() ) )
	    {
		name  = B1950.getName();
		eqInt = B1950.getInt();
	    }
	else if( e.equals( Equinox.J2000.getEpoch() ) )
	    {
		name  = B1950.getName();
		eqInt = B1950.getInt();
	    }
	else 
	    {
		/* Non-standard Epochs */
	    }
	epoch = e;
    }


    /**
     * Return the name of this Equinox
     * @return name
     * @see #name
     */
    public String getName()
    {
	return name;
    }


    /**
     * Return the epoch of this Equinox
     * @return epoch
     * @see #epoch
     */
    public Epoch getEpoch()

    {
	return epoch;
    }


    /**
     * Return the integer representation of this epoch.
     * @return eqInt
     * @see #eqInt
     */
    public int getInt()
    {
	return eqInt;
    }


    /**
     * Return a String representation of this Equinox in the form:
     * <code>calendar date timescale</code>.
     * @return string representation of this Equinox
     */
    public String toString()
    {
	return( epoch.getCalendarType().getName()+" (int:"+
		epoch.getCalendarType().getInt()+") "+
		epoch.getDate()+" "+
		epoch.getTimescaleType().getName()+" (int:"+
		epoch.getTimescaleType().getInt()+")" );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Equinox.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Equinox.java,v $
 *     $Log: not supported by cvs2svn $
 */
