// ChiSquaredFitUpdateListener.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/ChiSquaredFitUpdateListener.java,v 0.1 2000-08-18 17:24:43 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * Interface used to call an update function after each new chi squared interation, before
 * new parameter limits are computed.
 * @author Chris Mottram
 * @version $Revision$
 */
public interface ChiSquaredFitUpdateListener
{
	/**
	 * The update was called because the a new chi squared has been computed.
	 */
	public final static int UPDATE_TYPE_CHI_SQUARED 	= 0;
	/**
	 * The update was called because the chi squared has found a new best value.
	 */
	public final static int UPDATE_TYPE_BEST_CHI_SQUARED 	= 1;
	/**
	 * The update was called because current parameter set has been computed.
	 */
	public final static int UPDATE_TYPE_PARAMETER_LOOP 	= 2;
	/**
	 * The update was called because the chi squared fit has finished.
	 */
	public final static int UPDATE_TYPE_FINISHED 		= 3;
	/**
	 * String name for each type.
	 */
	public final static String UPDATE_TYPE_STRING[] = {"Chi Squared","Best Chi Squared",
								"parameter loop","finished"};
	/**
	 * Method to inform a waiting object that a chi squared interation has taken place.
	 * @param type The Type of update sent to the listening object instance.
	 * @param csf The ChiSquaredFit instance that generated this update.
	 */
	public void chiSquaredUpdate(int type,ChiSquaredFit csf);
}
//
// $Log: not supported by cvs2svn $
//
