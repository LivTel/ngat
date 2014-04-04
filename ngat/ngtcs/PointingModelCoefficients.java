package ngat.ngtcs;

/**
 * This class provides (limited) top-level functionality and the generic
 * class-cast to enable generic handling of coefficients.
 * <p>
 * As this class implements Cloneable (by inheritance) the
 * <code>CloneNotSupportedException</code> should <b>never</b> occur. However,
 * should the sub-class involve complicated Objects instead of basic
 * primitives (<code>int</code>s, <code>double</code>s etc.), then the
 * <code>Object.clone</code> method should be over-ridden to perform a deep
 * copy to ensure that it is values and not references that are cloned.
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class PointingModelCoefficients
  implements Cloneable, java.io.Serializable
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
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


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
   *
   */
  public PointingModelCoefficients()
  {
  }

  /**
   * Return a copy of this set of coefficients.
   */
  public final PointingModelCoefficients getCopy()
  {
    PointingModelCoefficients pmc = null;
    try
    {
      pmc = (PointingModelCoefficients)( this.clone() );
    }
    catch( CloneNotSupportedException cnse )
    {

    }
    return( pmc );
  }

  /**
   * This method must be implemented to provide the ability to combine all the
   * coefficients in <code>coefficientList</code>, as all sub-classes will
   * probably use different coefficient sets.
   */
  protected abstract void plus( PointingModelCoefficients pmc );

  /**
   * This method must be implemented to set all values in the pointing model to
   * zero.  This method is used to initialise the <code>combined</code>
   * PointingModelCoefficient before summing all coefficient sets used in the
   * pointing model.
   */
  protected abstract void nullValues();
}
/*
 *    $Date: 2013-07-02 15:25:33 $
 * $RCSfile: PointingModelCoefficients.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/PointingModelCoefficients.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
