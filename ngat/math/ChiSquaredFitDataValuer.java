// ChiSquaredFitDataValuer.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/ChiSquaredFitDataValuer.java,v 0.1 2000-08-18 17:24:43 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * Interface describing a model to call when calculating a Chi Squared Fit.
 * This interface allows the Chi squared fit to get an actual data value for a given i in the
 * list of data objects.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public interface ChiSquaredFitDataValuer
{
	/**
	 * Method to return the current value for the data at a given index.
	 * @param data An data object retrieved from ChiSquaredFit's dataList for a value i, that the
	 * 	modeller uses to get the value of the model.
	 * @return The current value this model returns.
	 */
	public double getValue(List dataList,int index);
}
//
// $Log: not supported by cvs2svn $
//
