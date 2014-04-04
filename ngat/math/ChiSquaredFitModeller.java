// ChiSquaredFitModeller.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/ChiSquaredFitModeller.java,v 0.1 2000-08-18 17:24:43 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * Interface describing a model to call when calculating a Chi Squared Fit.
 * The model can have various parameters which are set to a value for one run through
 * the chi squared fit.
 * @author Chris Mottram
 * @version $Revision$
 */
public interface ChiSquaredFitModeller extends ChiSquaredFitDataValuer
{
	/**
	 * Method to set a parameter used to calculate the function this modeller is implementing.
	 * @param name The string name of the parameter.
	 * @param value The value this parameter will take for the next call to getValue.
	 * @see #getValue
	 */
	public void setParameter(String name,double value);
}
//
// $Log: not supported by cvs2svn $
//
