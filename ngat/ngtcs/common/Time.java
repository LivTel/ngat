package ngat.ngtcs.common;

/**
 * An object to hold a Timestamp as LST and the following calendars:
 * <ul>
 * <li>Modified Julian Date</li>
 * <li>Julian Epoch </li>
 * <li>Besselian Epoch </li>
 * </ul><br>
 * in the following timescales:
 * <ul>
 * <li>UT1</li>
 * <li>TDB</li>
 * <li>TDT</li>
 * <li>TAI</li>
 * </ul>
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class Time implements java.io.Serializable
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
	new String( "$Id: Time.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The timestamp from which the other values were pcaluclated.
     */
    protected Timestamp timestamp;

    /**
     * The Local Sidereal Time, in radians.
     */
    protected double lst;

    /**
     * The timestamp in the UT1 timescale as a Modified Julian Date.
     */
    protected double ut1MJD;

    /**
     * The timestamp in the UT1 timescale as a Julian epoch.
     */
    protected double ut1Julian;

    /**
     * The timestamp in the UT1 timescale as a Besselian epoch.
     */
    protected double ut1Besselian;

    /**
     * The timestamp in the Terrestrial Dynamical Barycentric timescale as a
     * Modified Julian Date.
     */
    protected double tdbMJD;

    /**
     * The timestamp in the Terrestrial Dynamical Barycentric timescale as a
     * Julian epoch.
     */
    protected double tdbJulian;

    /**
     * The timestamp in the Terrestrial Dynamical Barycentric timescale as a
     * Besselian epoch.
     */
    protected double tdbBesselian;

    /**
     * The timestamp in the Terrestrial Dynamical Time timescale as a
     * Modified Julian Date.
     */
    protected double tdtMJD;

    /**
     * The timestamp in the Terrestrial Dynamical Time timescale as a
     * Julian epoch.
     */
    protected double tdtJulian;

    /**
     * The timestamp in the Terrestrial Dynamical Time timescale as a
     * Besselian epoch.
     */
    protected double tdtBesselian;

    /**
     * The timestamp in the International Atomic Time timescale as a
     * Modified Julian Date.
     */
    protected double taiMJD;

    /**
     * The timestamp in the International Atomic Time timescale as a
     * Julian epoch.
     */
    protected double taiJulian;

    /**
     * The timestamp in the International Atomic Time timescale as a
     * Besselian epoch.
     */
    protected double taiBesselian;

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
     * The empty constructor.
     */
    public Time()
    {
    }


    /**
     * Create a Time object with all the values set.
     * @param t the timestamp for which the other values have been calcualted
     * @param d1 the Local Sidereal Time in radians
     * @param d2 the MJD in the UT1 timescale
     * @param d3 the Julian epoch in the UT1 timescale
     * @param d4 the Besselian epoch in the UT1 timescale
     * @param d5 the MJD in the TDB timescale
     * @param d6 the Julian epoch in the TDB timescale
     * @param d7 the Besselian epoch in the TDB timescale
     * @param d8 the MJD in the TDT timescale
     * @param d9 the Julian epoch in the TDT timescale
     * @param d10 the Besselian epoch in the TDT timescale
     * @param d11 the MJD in the TAI timescale
     * @param d12 the Julian epoch in the TAI timescale
     * @param d13 the Besselian epoch in the TAI timescale
     */
    public Time( Timestamp t, double d1, double d2, double d3, double d4,
		 double d5, double d6, double d7, double d8, double d9,
		 double d10, double d11, double d12, double d13 )
    {
      timestamp = t;
      lst = d1;
      ut1MJD = d2;
      ut1Julian = d3;
      ut1Besselian = d4;
      tdbMJD = d5;
      tdbJulian = d6;
      tdbBesselian = d7;
      tdtMJD = d8;
      tdtJulian = d9;
      tdtBesselian = d10;
      taiMJD = d11;
      taiJulian = d12;
      taiBesselian = d13;
    }


    /**
     * Set the Timestamp.
     * @param t the Timestamp
     * @see #timestamp
     */
    public void setTimestamp( Timestamp t )
    {
        timestamp = t;
    }


    /**
     * Retrieve the Timestamp.
     * @return timestamp
     * @see #timestamp
     */
    public Timestamp getTimestamp()
    {
      return timestamp;
    }


    /**
     * Set the LST in radians.
     * @param d the LST
     * @see #lst
     */
    public void setLST( double d )
    {
      lst = d;
    }


    /**
     * Retrieve the LST in radians.
     * @return lst
     * @see #lst
     */
    public double getLST()
    {
      return lst;
    }


    /**
     * Set the MJD date in the UT1 timescale.
     * @param d the date
     * @see #ut1MJD
     */
    public void setMJDUT1( double d )
    {
        ut1MJD = d;
    }


    /**
     * Retrieve the MJD date in the UT1 timescale.
     * @return ut1MJD
     * @see #ut1MJD
     */
    public double getMJDUT1()
    {
        return ut1MJD;
    }


    /**
     * Set the Julian date in the UT1 timescale.
     * @param d the date
     * @see #ut1Julian
     */
    public void setJulianUT1( double d )
    {
        ut1Julian = d;
    }


    /**
     * Retrieve the Julian date in the UT1 timescale.
     * @return ut1Julian
     * @see #ut1Julian
     */
    public double getJulianUT1()
    {
        return ut1Julian;
    }


    /**
     * Set the Besselian date in the UT1 timescale.
     * @param d the date
     * @see #ut1Besselian
     */
    public void setBesselianUT1( double d )
    {
        ut1Besselian = d;
    }


    /**
     * Retrieve the Besselian date in the UT1 timescale.
     * @return ut1Besselian
     * @see #ut1Besselian
     */
    public double getBesselianUT1()
    {
        return ut1Besselian;
    }


    /**
     * Set the MJD date in the TDB timescale.
     * @param d the date
     * @see #tdbMJD
     */
    public void setMJDTDB( double d )
    {
        tdbMJD = d;
    }


    /**
     * Retrieve the MJD date in the TDB timescale.
     * @return tdbMJD
     * @see #tdbMJD
     */
    public double getMJDTDB()
    {
        return tdbMJD;
    }


    /**
     * Set the Julian date in the TDB timescale.
     * @param d the date
     * @see #tdbJulian
     */
    public void setJulianTDB( double d )
    {
        tdbJulian = d;
    }


    /**
     * Retrieve the Julian date in the TDB timescale.
     * @return tdbJulian
     * @see #tdbJulian
     */
    public double getJulianTDB()
    {
        return tdbJulian;
    }


    /**
     * Set the Besselian date in the TDB timescale.
     * @param d the date
     * @see #tdbBesselian
     */
    public void setBesselianTDB( double d )
    {
        tdbBesselian = d;
    }


    /**
     * Retrieve the Besselian date in the TDB timescale.
     * @return tdbBesselian
     * @see #tdbBesselian
     */
    public double getBesselianTDB()
    {
        return tdbBesselian;
    }


    /**
     * Set the MJD date in the TDT timescale.
     * @param d the date
     * @see #tdtMJD
     */
    public void setMJDTDT( double d )
    {
        tdtMJD = d;
    }


    /**
     * Retrieve the MJD date in the TDT timescale.
     * @return tdtMJD
     * @see #tdtMJD
     */
    public double getMJDTDT()
    {
        return tdtMJD;
    }


    /**
     * Set the Julian date in the TDT timescale.
     * @param d the date
     * @see #tdtJulian
     */
    public void setJulianTDT( double d )
    {
        tdtJulian = d;
    }


    /**
     * Retrieve the Julian date in the TDT timescale.
     * @return tdtJulian
     * @see #tdtJulian
     */
    public double getJulianTDT()
    {
        return tdtJulian;
    }


    /**
     * Set the Besselian date in the TDT timescale.
     * @param d the date
     * @see #tdtBesselian
     */
    public void setBesselianTDT( double d )
    {
        tdtBesselian = d;
    }


    /**
     * Retrieve the Besselian date in the TDT timescale.
     * @return tdtBesselian
     * @see #tdtBesselian
     */
    public double getBesselianTDT()
    {
        return tdtBesselian;
    }


    /**
     * Set the MJD date in the TAI timescale.
     * @param d the date
     * @see #taiMJD
     */
    public void setMJDTAI( double d )
    {
        taiMJD = d;
    }


    /**
     * Retrieve the MJD date in the TAI timescale.
     * @return taiMJD
     * @see #taiMJD
     */
    public double getMJDTAI()
    {
        return taiMJD;
    }


    /**
     * Set the Julian date in the TAI timescale.
     * @param d the date
     * @see #taiJulian
     */
    public void setJulianTAI( double d )
    {
        taiJulian = d;
    }


    /**
     * Retrieve the Julian date in the TAI timescale.
     * @return taiJulian
     * @see #taiJulian
     */
    public double getJulianTAI()
    {
        return taiJulian;
    }


    /**
     * Set the Besselian date in the TAI timescale.
     * @param d the date
     * @see #taiBesselian
     */
    public void setBesselianTAI( double d )
    {
        taiBesselian = d;
    }


    /**
     * Retrieve the Besselian date in the TAI timescale.
     * @return taiBesselian
     * @see #taiBesselian
     */
    public double getBesselianTAI()
    {
        return taiBesselian;
    }


}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Time.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Time.java,v $
 *      $Id: Time.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
