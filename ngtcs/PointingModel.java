package ngat.ngtcs;

import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * The top-level pointing model class.  This abstract class contains generic
 * fields and methods and provides the generic class-cast.
 * <p>
 * Every PointingModel <i>must</i> create the <code>permanent</code> Object
 * to be used as the main set of coefficients used to calculate pointing model
 * adjustments.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class PointingModel
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

  /**
   * List of coefficient objects to be used when applying/removing
   * corrections.
   */
  private List coefficientList;

  /**
   * The main set of coefficients that will be used in all transformations.
   */
  protected PointingModelCoefficients permanent = null;

  /**
   * Used to store the last set of coefficients to enable reverting.
   * @see #revertValues
   */
  private PointingModelCoefficients previous = null;

  /**
   * The set of coefficients that includes all extra sets of coefficients, such
   * as the autoguiding set.
   * @see 'combineCoefficients
   */
  protected PointingModelCoefficients combined = null;

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
   * The blank constructor to ensure <code>newCoefficientSet</code> doesn't
   * fail.
   */
  public PointingModel()
  {
  }


  /**
   *
   */
  public abstract void initialise( Telescope t )
    throws InitialisationException;

  /**
   * This method is imlemented to return the specific sub-class of coefficients
   * required by the PointingModel sub-class.
   */
  public abstract PointingModelCoefficients newCoefficientSet();


  /**
   * Add a set of coefficients to the pointing model.  The added set will be
   * used in the transformations, incorporated during the
   * <code>combineCoefficients</code> method.
   * @param p the new set of coefficients to add
   */
  public void addCoefficients( PointingModelCoefficients p )
  {
    coefficientList.add( p );
  }


  /**
   * Remove a set of coefficients from the pointing model.
   * @param p the reference for the set to remove
   */
  public void removeCoefficients( PointingModelCoefficients p )
  {
    coefficientList.remove( p );
  }


  /**
   * Apply the attitude correction to the specified "perfect" mount position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_applyAttitudeCorrection</code>.
   * @param m the mount position to transform
   * @return the transformed position
   */
  public final XYZMatrix applyAttitudeCorrection( XYZMatrix m )
  {
    combineCoefficients();
    return( _applyAttitudeCorrection( m ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "perfect" mount attitude to the "observed" mount attitude in the
   * pointing-model specific manner.
   * @param m the mount position to transform
   * @return the transformed position
   */
  protected abstract XYZMatrix _applyAttitudeCorrection( XYZMatrix m );


  /**
   * Apply the position correction to the specified "perfect" mount position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_applyPositionCorrection</code>.
   * @param m the mount position to transform
   * @return the transformed position
   */
  public final XYZMatrix applyPositionCorrection( XYZMatrix m )
  {
    combineCoefficients();
    return( _applyPositionCorrection( m ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "perfect" mount position to the "observed" mount position in the
   * pointing-model specific manner.
   * @param m the mount position to transform
   * @return the transformed position
   */
  protected abstract XYZMatrix _applyPositionCorrection( XYZMatrix m );


  /**
   * Apply the correction to the specified "perfect" rotator position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_applyRotatorCorrection</code>.
   * @param m the rotator position to transform
   * @return the transformed position
   */
  public final double applyRotatorCorrection( double d )
  {
    combineCoefficients();
    return( _applyRotatorCorrection( d ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "perfect" rotator position to the "observed" rotator position in the
   * pointing-model specific manner.
   * @param m the rotator position to transform
   * @return the transformed position
   */
  protected abstract double _applyRotatorCorrection( double d );


  /**
   * Remove the attitude correction to the specified "observed" mount position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_removeAttitudeCorrection</code>.
   * @param m the mount position to transform
   * @return the transformed position
   */
  public final XYZMatrix removeAttitudeCorrection( XYZMatrix m )
  {
    combineCoefficients();
    return( _removeAttitudeCorrection( m ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "observed" mount attitude to the "perfect" mount attitude in the
   * pointing-model specific manner.
   * @param m the mount position to transform
   * @return the transformed position
   */
  protected abstract XYZMatrix _removeAttitudeCorrection( XYZMatrix m );


  /**
   * Remove the position correction to the specified "observed" mount position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_removePositionCorrection</code>.
   * @param m the mount position to transform
   * @return the transformed position
   */
  public final XYZMatrix removePositionCorrection( XYZMatrix m )
  {
    combineCoefficients();
    return( _removePositionCorrection( m ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "observed" mount position to the "perfect" position in the
   * pointing-model specific manner.
   * @param m the mount position to transform
   * @return the transformed position
   */
  protected abstract XYZMatrix _removePositionCorrection( XYZMatrix m );


  /**
   * Remove the rotator correction to the specified "observed" position.
   * <p>
   * This method sums the pointing model coefficient sets using
   * <code>combineCoefficients</code>and uses the result in the Pointing
   * Model-specific subclass implementation,
   * <code>_removeRotatorCorrection</code>.
   * @param m the rotator position to transform
   * @return the transformed position
   */
  public final double removeRotatorCorrection( double d )
  {
    combineCoefficients();
    return( _removeRotatorCorrection( d ) );
  }

  /**
   * This method must be implemented by the concrete sub-class to transform
   * the "observed" rotator position to the "perfect" position in the
   * pointing-model specific manner.
   * @param m the rotator position to transform
   * @return the transformed position
   */
  protected abstract double _removeRotatorCorrection( double d );


  /**
   * This method calls <code>updateCoefficients( PointingModelCoefficients pmc,
   * XYZMatrix desired,XYZMatrix achieved )</code> with the
   * <code>permanent</code> coefficient set as the <code>pmc</code>
   * reference.
   * @param desired the desired mount position as an XYZMatrix
   * @param achieved the achieved mount position as an XYZMatrix
   */
  public final void updateCoefficients( XYZMatrix desired,
					XYZMatrix achieved )
  {
    if( permanent != null )
      previous = permanent.getCopy();

    calculateCoefficients( permanent, desired, achieved );
  }


  /**
   * Calculate the PointingModelCoefficients that will align the desired
   * position with the achieved position.  this method calls the
   * <code>PointingModel</code>-specific <code>calculateCoefficients</code>.
   * @param desired the desired mount position as an XYZMatrix
   * @param achieved the achieved mount position as an XYZMatrix
   * @see #calculateCoefficients
   */
  public final void updateCoefficients( PointingModelCoefficients pmc,
					XYZMatrix desired,
					XYZMatrix achieved )
  {
    calculateCoefficients( pmc, desired, achieved );
  }


  /**
   * Revert the main pointing model coefficient set values to the ones used
   * previously.  If no previous set exists nothing will happen.
   */
  public final void revertValues()
  {
    if( previous == null )
    {
      return;
    }

    permanent = (PointingModelCoefficients)( previous.getCopy() );
    previous = null;
  }


  /**
   * The concrete sub-class implementation of this method will perform the
   * <code>PointingModel</code>-specific calculations that will align the
   * desired vector with the achieved vector by calculating the required
   * <code>PointingModelCoefficients</code> and storing them in
   * <code>pmc</code>
   * @param desired the desired mount position as an XYZMatrix
   * @param achieved the achieved mount position as an XYZMatrix
   */
  protected abstract void calculateCoefficients( PointingModelCoefficients pmc,
						 XYZMatrix desired,
						 XYZMatrix achieved );


  /**
   * Method to add ALL coefficients in the coefficient list and create one
   * PointingModelCoefficient with the results.
   */
  public void combineCoefficients()
  {
    combined.nullValues();
    combined.plus( permanent );

    for( int i = 0; i < coefficientList.size(); i++ )
    {
      combined.plus( (PointingModelCoefficients)( coefficientList.get( i ) ) );
    }
  }
}
/*
 *    $Date: 2013-07-02 15:25:56 $
 * $RCSfile: PointingModel.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/PointingModel.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
