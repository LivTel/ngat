package ngat.ngtcs;

import java.io.*;

import ngat.util.*;
import ngat.ngtcs.common.*;

/**
 * This class contains the coefficients used in a standard AltAzPointingModel.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AltAzPointingModelCoefficients extends PointingModelCoefficients
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
    new String( "$Id: AltAzPointingModelCoefficients.java,v 1.2 2013-07-02 13:26:23 cjm Exp $" );

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

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

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

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  public static void main( String agrs[] )
  {
    PointingModelCoefficients pmc1 = null, pmc2 = null;
    pmc1 = new AltAzPointingModelCoefficients();
    ( (AltAzPointingModelCoefficients)pmc1 ).setIA( 1.0 );
    ( (AltAzPointingModelCoefficients)pmc1 ).setAN( 2.0 );
    ( (AltAzPointingModelCoefficients)pmc1 ).setAW( 3.0 );
    pmc2 = pmc1.getCopy();
    System.err.println( "pmc2.ia = "+
			( (AltAzPointingModelCoefficients)pmc2 ).getIA()+
			", pmc2.an = "+
			( (AltAzPointingModelCoefficients)pmc2 ).getAN()+
			", pmc2.aw = "+
			( (AltAzPointingModelCoefficients)pmc2 ).getAW() );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create a Coefficient set for the an AltAzPointingModel.
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


  /**
   * Method to add two coefficient sets together, <code>this</code> and the
   * specified one.
   * @param pmc the coefficient set to add to this.
   */
  public void plus( PointingModelCoefficients pmc )
  {
    AltAzPointingModelCoefficients aapmc
      = (AltAzPointingModelCoefficients)pmc;

    ia   += aapmc.getIA();
    ie   += aapmc.getIE();
    tf   += aapmc.getTF();
    ca   += aapmc.getCA();
    npae += aapmc.getNPAE();
    an   += aapmc.getAN();
    aw   += aapmc.getAW();
  }


  /**
   * Set all values to zero.
   */
  protected void nullValues()
  {
    ia   = 0.0;
    ie   = 0.0;
    tf   = 0.0;
    ca   = 0.0;
    npae = 0.0;
    an   = 0.0;
    aw   = 0.0;
  }
}
/*
 *    $Date: 2013-07-02 13:26:23 $
 * $RCSfile: AltAzPointingModelCoefficients.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/AltAzPointingModelCoefficients.java,v $
 *      $Id: AltAzPointingModelCoefficients.java,v 1.2 2013-07-02 13:26:23 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
