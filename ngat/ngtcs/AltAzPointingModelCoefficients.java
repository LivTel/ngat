package ngat.ngtcs;

import java.io.*;

import ngat.util.*;
import ngat.ngtcs.common.*;

/**
 * This class contains the coefficients used in a standard AltAzPointingModel.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AltAzPointingModelCoefficients extends PointingModelCoefficients
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
	new String( "$Id: AltAzPointingModelCoefficients.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

    /**
     * Multiplicative conversion from arcseconds to radians.
     * Equal to <code>Math.PI / 648000.0</code>
     */
    public static final double ARCSECS2RADIANS = Math.PI / 648000.0;

    /**
     * Multiplicative conversion from radians to arcseconds.
     * Equal to <code>648000.0 / Math.PI</code>
     */
    public static final double RADIANS2ARCSECS = 648000.0 / Math.PI;

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The Azimuth index (zero-point) error in arcseconds.
     */
    protected double ia = 0.0;

    /**
     * The Altitude index (zero-point) error in arcseconds.
     */
    protected double ie = 0.0;

    /**
     * The tube-flexure coefficient.
     */
    protected double tf = 0.0;

    /**
     * The Collimation error in arcseconds.
     */
    protected double ca = 0.0;

    /**
     * The Alt-Az axis Non-perpendicularity in arcseconds.
     */
    protected double npae = 0.0;

    /**
     * The Azimuth axis North tilt in arcseconds.
     */
    protected double an = 0.0;

    /**
     * The Azimuth axis West tilt in arcseconds.
     */
    protected double aw = 0.0;


    /**
     *
     */
    public AltAzPointingModelCoefficients()
    {

    }


    /**
     * This method sets ia.  <b>Note</b> newIA should be in arcseconds.
     * @param newIA
     * @see #ia
     */
    public void setIA( double newIA )
    {
        ia = newIA * ARCSECS2RADIANS;
    }


    /**
     * This method returns ia in arcseconds.
     * @return ia
     * @see #ia
     */
    public double getIA()
    {
        return ia * RADIANS2ARCSECS;
    }


    /**
     * This method sets ie.  <b>Note</b> newIE should be in arcseconds
     * @param newIE
     * @see #ie
     */
    public void setIE( double newIE )
    {
        ie = newIE * ARCSECS2RADIANS;
    }


    /**
     * This method returns ie in arcseconds.
     * @return ie
     * @see #ie
     */
    public double getIE()
    {
        return ie * RADIANS2ARCSECS;
    }


    /**
     * This method sets tf.
     * @param newTF
     * @see #tf
     */
    public void setTF( double newTF )
    {
        tf = newTF;
    }


    /**
     * This method returns tf.
     * @return tf
     * @see #tf
     */
    public double getTF()
    {
      return tf;
    }


    /**
     * This method sets ca.  <b>Note</b> newCA should be in arcseconds
     * @param newCA
     * @see #ca
     */
    public void setCA( double newCA )
    {
        ca = newCA * ARCSECS2RADIANS;
    }


    /**
     * This method returns ca in arcseconds.
     * @return ca
     * @see #ca
     */
    public double getCA()
    {
        return ca * RADIANS2ARCSECS;
    }


    /**
     * This method sets npae.  <b>Note</b> newNPAE should be in arcseconds
     * @param newNPAE
     * @see #npae
     */
    public void setNPAE( double newNPAE )
    {
        npae = newNPAE * ARCSECS2RADIANS;
    }


    /**
     * This method returns npae in arcseconds.
     * @return npae
     * @see #npae
     */
    public double getNPAE()
    {
        return npae * RADIANS2ARCSECS;
    }


    /**
     * This method sets an.  <b>Note</b> newAN should be in arcseconds
     * @param newAN
     * @see #an
     */
    public void setAN( double newAN )
    {
        an = newAN * ARCSECS2RADIANS;
    }


    /**
     * This method returns an in arcseconds.
     * @return an
     * @see #an
     */
    public double getAN()
    {
        return an * RADIANS2ARCSECS;
    }


    /**
     * This method sets aw.  <b>Note</b> newAW should be in arcseconds
     * @param newAW
     * @see #aw
     */
    public void setAW( double newAW )
    {
        aw = newAW * ARCSECS2RADIANS;
    }


    /**
     * This method returns aw in arcseconds.
     * @return aw
     * @see #aw
     */
    public double getAW()
    {
        return aw * RADIANS2ARCSECS;
    }
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: AltAzPointingModelCoefficients.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/AltAzPointingModelCoefficients.java,v $
 *      $Id: AltAzPointingModelCoefficients.java,v 1.1 2003-07-01 10:11:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
