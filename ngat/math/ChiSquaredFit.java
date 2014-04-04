// ChiSquaredFit.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/ChiSquaredFit.java,v 0.1 2000-08-18 17:24:43 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * Class to do a chi squared fit, which returns chi squared for a model verses an actual data set.
 * Chi squared is a measure of how well the model fits the data.
 * @author Chris Mottram
 * @version $Revision$
 */
public class ChiSquaredFit
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * This value is the default for how much to shrink a parameter range during a parameter constrain operation.
	 * It should be greater than one.
	 * For instance, 2.0 will halve the range, 3.0 takes a third etc.
	 */
	public final static double DEFAULT_RANGE_SHRINK_VALUE	= 2.0;
	/**
	 * This value is the default used to set the step size of a parameter during a parameter constrain operation.
	 * It gives the effective number of parameter interations that will take place during 
	 * the next run i.e. stepSize = (maxValue-minValue)/DEFAULT_STEP_COUNT.
	 * 10 is a good value for three one degree of freedom quadratic fits,
	 * 50 is a good value for one three degree of freedom quadratic fit.
	 */
	public final static double DEFAULT_STEP_COUNT		= 10.0;
	/**
	 * The start index for i, when running through i for each chi squared fit.
	 */
	private int startIndex = 0;
	/**
	 * The end index for i, when running through i for each chi squared fit.
	 */
	private int endIndex = 0;
	/**
	 * The data to run through, for compare against values retrieved from the model.
	 */
	private List dataList = null;
	/**
	 * The model to use when doing the chi squared fit.
	 */
	private ChiSquaredFitModeller dataModeller = null;
	/**
	 * The class which has the getValue function that calculates the data value.
	 */
	private ChiSquaredFitDataValuer dataValuer = null;
	/**
	 * An object to send an update message to after the best chi squared is found for
	 * the current parameter setup (before they are constrained).
	 */
	private ChiSquaredFitUpdateListener updateListener = null;
	/**
	 * The list of data parameters to vary to find the values of parameters with the smallest
	 * chi squared fit.
	 */
	private List parameterList = null;
	/**
	 * The lowest chi squared produced.
	 */
	private double bestChiSquared = 0.0;
	/**
	 * The loop of parameter constraint we are currently working on.
	 */
	private int loopCount = 0;
	/**
	 * This value is how much to shrink a parameter range during a parameter constrain operation.
	 * It should be greater than one.
	 * For instance, 2.0 will halve the range.
	 * @see #DEFAULT_RANGE_SHRINK_VALUE
	 */
	private double parameterRangeShrinkValue = DEFAULT_RANGE_SHRINK_VALUE;
	/**
	 * This value is used to set the step size of a parameter during a parameter constrain operation.
	 * It gives the effective number of parameter interations that will take place during 
	 * the next run i.e. stepSize = (maxValue-minValue)/parameterStepCount.
	 * 10 is a good value for three one degree of freedom quadratic fits,
	 * 50 is a good value for one three degree of freedom quadratic fit.
	 */
	private double parameterStepCount = DEFAULT_STEP_COUNT;

	/**
	 * Constructor. Initialises parameterList.
	 * @see #parameterList
	 */
	public ChiSquaredFit()
	{
		parameterList = new Vector();
	}

	/**
	 * Method to set the start index for i, when running through i for each chi squared fit.
	 * @param index The index into dataList to use.
	 * @see #dataList
	 * @see #startIndex
	 */
	public void setStart(int index)
	{
		startIndex = index;
	}
	
	/**
	 * Method to set the end index for i, when running through i for each chi squared fit.
	 * @param index The index into dataList to use.
	 * @see #dataList
	 * @see #endIndex
	 */
	public void setEnd(int index)
	{
		endIndex = index;
	}

	/**
	 * Set the data to use.
	 * @param dl A list of data.
	 * @see #dataList
	 */
	public void setData(List dl)
	{
		dataList = dl;
	}

	/**
	 * Set the modeller which returns model values for each x in the list.
	 * @param m The modeller.
	 * @see #dataModeller
	 */
	public void setModeller(ChiSquaredFitModeller m)
	{
		dataModeller = m;
	}

	/**
	 * Get the modeller which returns model values for each index in the list.
	 * Don't modify the modeller's parameter's outside the ChiSquaredFit class,
	 * you could really confuse the ChiSquaredFit.
	 * @return The modeller.
	 * @see #dataModeller
	 */
	public ChiSquaredFitModeller getModeller()
	{
		return dataModeller;
	}

	/**
	 * Set the data valuer which returns data values for each x in the list.
	 * @param m The data valuer.
	 * @see #dataValuer
	 */
	public void setActual(ChiSquaredFitDataValuer v)
	{
		dataValuer = v;
	}

	/**
	 * Return the data valuer which returns data values for each index in the list.
	 * @return The data valuer.
	 * @see #dataValuer
	 */
	public ChiSquaredFitDataValuer getActual()
	{
		return dataValuer;
	}

	/**
	 * Method to add a parameter to the list to vary, to see the effects on chi squared.
	 * @param name The name of the parameter.
	 * @param minValue The minimum value of the parameter.
	 * @param maxValue The maximum value of the parameter.
	 * @param stepSize The step size for the chi squared loop increments.
	 */
	public void addParameter(String name, double minValue,double maxValue,double stepSize)
	{
		ChiSquaredFitParameter csfp = null;

		csfp = new ChiSquaredFitParameter();
		csfp.setName(name);
		csfp.setMinValue(minValue);
		csfp.setMaxValue(maxValue);
		csfp.setStepSize(stepSize);
		parameterList.add(csfp);
	}

	/**
	 * Set an update listener. This is an interface which is called after every parameterLoop.
	 * @param ul The update listener.
	 * @see #updateListener
	 * @see #parameterLoop
	 * @see #chiSquaredFit
	 */
	public void setUpdateListener(ChiSquaredFitUpdateListener ul)
	{
		updateListener = ul;
	}

	/**
	 * Method to set a parameter constraint constant.
	 * @see #parameterRangeShrinkValue
	 */
	public void setParameterRangeShrinkValue(double prsv)
	{
		parameterRangeShrinkValue = prsv;
	}

	/**
	 * Method to set a parameter constraint constant.
	 * @see #parameterStepCount
	 */
	public void setParameterStepCount(double psc)
	{
		parameterStepCount = psc;
	}

	/**
	 * Do a chi squared fit. A loop is entered, until maxLoopCount loops has occured or the bestChiSquared
	 * Is better than targetChiSquared. The loop does the following:
	 * <ul>
	 * <li>bestChiSquared is initialised to the largest double.
	 * <li>parameterLoop is called with index zero to start interating over
	 * 	parameter combinations.  This generates the bestChiSquared and saves the associated parameters used.
	 * <li>The update listener is called if it is non-null.
	 * <li>Loop exit conditions are checked.
	 * <li>If we arn't exiting the loop, call parameterConstrain to get better parameter ranges for the next loop.
	 * </ul>
	 * @param  maxLoopCount The maximum number of times to change the parameter range to close in
	 * 	on the targetChiSquared
	 * @param targetChiSquared If the bestChiSquared is better than the targetChiSquared then exit the loop
	 * @see #bestChiSquared
	 * @see #parameterLoop
	 * @see #updateListener
	 * @see #parameterConstrain
	 */
	public void chiSquaredFit(int maxLoopCount,double targetChiSquared)
	{
		boolean done = false;

		done = false;
		loopCount = 0;
		bestChiSquared = Double.MAX_VALUE;
		while(!done)
		{
			parameterLoop(0);
			if(updateListener != null)
			{
				updateListener.chiSquaredUpdate(
					ChiSquaredFitUpdateListener.UPDATE_TYPE_PARAMETER_LOOP,this);
			}
			loopCount++;
			done = ((loopCount >= maxLoopCount)||(bestChiSquared < targetChiSquared));
			if(!done)
			{
				parameterConstrain();
			}
		}
		if(updateListener != null)
		{
			updateListener.chiSquaredUpdate(
				ChiSquaredFitUpdateListener.UPDATE_TYPE_FINISHED,this);
		}
	}

	/**
	 * Loop over a parameter, for each of it's possible values. Set it's current value in the loop.
	 * If this is not the last parameter, call this method recursively for the next parameter.
	 * Otherwise call the dataLoop, to get a chiSquared for this particular combination of parameters.
	 * @param parameterIndex The current parameter index for this loop
	 * @see #dataLoop
	 */
	public void parameterLoop(int parameterIndex)
	{
		ChiSquaredFitParameter parameter = null;
		double parameterValue;
		int parameterCount = 0;

		parameter = (ChiSquaredFitParameter)(parameterList.get(parameterIndex));
		parameterCount = parameter.getCount();

		for(int index = 0; index < parameterCount;index++)
		{
		// find current value for this parameter
			parameterValue = parameter.getMinValue()+(index*parameter.getStepSize());
		// set parameter's current value
			parameter.setCurrentValue(parameterValue);
			dataModeller.setParameter(parameter.getName(),parameterValue);
		// do next parameter/data loop
			if(parameterIndex < (parameterList.size()-1))
				parameterLoop(parameterIndex+1);
			else
				dataLoop();
		}
	}

	/**
	 * Method that does the loop over each data point comparing model and actual data.
	 * Called from the last parameterLoop call.
	 * If the chi squared generated is better than the current best, bestChiSquared is updated and
	 * the best value for each parameter is stored.
	 * @see #getChiSquared
	 * @see #bestChiSquared
	 */
	public void dataLoop()
	{
		ChiSquaredFitParameter parameter = null;
		double chiSquared = 0.0;
		int parameterListCount = 0;

		chiSquared = getChiSquared();
		if(updateListener != null)
		{
			updateListener.chiSquaredUpdate(
				ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED,this);
		}
		if(chiSquared < bestChiSquared)
		{
			bestChiSquared = chiSquared;
			parameterListCount = parameterList.size();
			for(int parameterIndex = 0; parameterIndex < parameterListCount; parameterIndex++)
			{
				parameter = (ChiSquaredFitParameter)(parameterList.get(parameterIndex));
				parameter.setBestValue();
			}
			if(updateListener != null)
			{
				updateListener.chiSquaredUpdate(
					ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED,this);
			}
		}
	}

	/**
	 * Gets the parameter value stored against this parameter when the best chi squared was returned.
	 * @param name The name of the parameter to query.
	 * @exception IllegalArgumentException Thrown if a parameter of name 'name' not found in the list.
	 * @see #parameterList
	 */
	public double getBestParameter(String name)
	{
		ChiSquaredFitParameter parameter = null;
		double bestValue = 0.0;
		int parameterListCount,parameterIndex;
		boolean done;

		parameterListCount = parameterList.size();
		parameterIndex = 0;
		done = false;
		while((parameterIndex < parameterListCount)&&(!done))
		{
			parameter = (ChiSquaredFitParameter)(parameterList.get(parameterIndex));
			if(parameter.getName().equals(name))
			{
				done = true;
				bestValue = parameter.getBestValue();
			}
			parameterIndex++;
		}
		if(!done)
		{
			throw new IllegalArgumentException(this.getClass().getName()+":getBestParameter:name="+
				name+" not found.");
		}
		return bestValue;
	}

	/**
	 * Gets the current parameter value stored against this parameter.
	 * @param name The name of the parameter to query.
	 * @exception IllegalArgumentException Thrown if a parameter of name 'name' not found in the list.
	 * @see #parameterList
	 */
	public double getCurrentParameter(String name)
	{
		ChiSquaredFitParameter parameter = null;
		double value = 0.0;
		int parameterListCount,parameterIndex;
		boolean done;

		parameterListCount = parameterList.size();
		parameterIndex = 0;
		done = false;
		while((parameterIndex < parameterListCount)&&(!done))
		{
			parameter = (ChiSquaredFitParameter)(parameterList.get(parameterIndex));
			if(parameter.getName().equals(name))
			{
				done = true;
				value = parameter.getCurrentValue();
			}
			parameterIndex++;
		}
		if(!done)
		{
			throw new IllegalArgumentException(this.getClass().getName()+":getCurrentParameter:name="+
				name+" not found.");
		}
		return value;
	}

	/**
	 * Get the best chi squared done by this iteration.
	 */
	public double getBestChiSquared()
	{
		return bestChiSquared;
	}

	/**
	 * Get the current parameter constraint loop for this object.
	 */
	public int getLoopCount()
	{
		return loopCount;
	}

	/**
	 * Internal method to do one data sum loop and return a chi squared value.
	 * Note dataModeller.setParameter should have been called for each parameter before this is called.
	 * @return ChiSquared for this loop through the data set.
	 */
	private double getChiSquared()
	{
		double chiSquared = 0.0,chiSquaredIncrement;
		double modelValue,dataValue;
		int parameterListCount;

		parameterListCount = parameterList.size();
		for(int i=startIndex;i< endIndex;i++)
		{
			modelValue = dataModeller.getValue(dataList,i);
			dataValue = dataValuer.getValue(dataList,i);
			chiSquaredIncrement = ((modelValue-dataValue)*(modelValue-dataValue))/
				((endIndex-startIndex)-parameterListCount);
			chiSquared += chiSquaredIncrement;
		}
		return chiSquared;
	}

	/**
	 * This method is called after the best chi squared for the current parameter run has been found.
	 * It refines the parameter ranges so they are more detailed around the best parameter value.
	 * If the best chi squared value was obtained for a parameter on the edge of it's current constraint,
	 * the current contraint is moved to be centred over the previous edge rather than constrained .
	 * Otherwise the parameter range is centred on the best parameter, the range is halved.
	 */
	private void parameterConstrain()
	{
		ChiSquaredFitParameter parameter = null;
		int parameterListCount;
		double bestValue,maxValue,range;

		parameterListCount = parameterList.size();
		for(int parameterIndex = 0; parameterIndex < parameterListCount; parameterIndex++)
		{
			parameter = (ChiSquaredFitParameter)(parameterList.get(parameterIndex));

			bestValue = parameter.getBestValue();
			range = (parameter.getMaxValue()-parameter.getMinValue());
		// Note parameterLoop may never have used parameter.getMaxValue() is stepSize is
		// exactley divisible, so test against maxValue used as well...
			maxValue = parameter.getMinValue()+(parameter.getCount()*parameter.getStepSize());
			if((bestValue > parameter.getMinValue()) && (bestValue < parameter.getMaxValue())
				&& (bestValue < parameter.getMaxValue()))
			{
		// constrain parameter
				parameter.setMinValue(bestValue-(range/(2.0+parameterRangeShrinkValue)));
				parameter.setMaxValue(bestValue+(range/(2.0+parameterRangeShrinkValue)));
				parameter.setStepSize((parameter.getMaxValue()-parameter.getMinValue())/
					parameterStepCount);
			}
			else
			{
		// move range to centre of the required edge
				parameter.setMinValue(bestValue - (range/2.0));
				parameter.setMaxValue(bestValue + (range/2.0));
				// step size is the same
			}
		}
	}

	/**
	 * Inner class representing a parameter to get a chi squared fit for.
	 */
	private class ChiSquaredFitParameter
	{
		/**
	 	 * The name of the parameter.
		 */
		private String name = null;
		/**
		 * The minimum value for the parameter.
		 */
		private double minValue = 0.0;
		/**
		 * The maximum value for the parameter.
		 */
		private double maxValue = 0.0;
		/**
		 * The step size for the parameter.
		 */
		private double stepSize = 0.0;
		/**
	 	 * The current value of this parameter.
		 */
		private double currentValue = 0.0;
		/**
	 	 * The value of this parameter which produced the lowest chi squared so far.
		 */
		private double bestValue = 0.0;

		/**
		 * Method to set a parameter's name.
		 * @see #name
		 */
		public void setName(String n)
		{
			name = n;
		}

		/**
		 * Method to get a parameter's name.
		 * @see #name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * Method to set a parameter's minimum value.
		 * @see #minValue
		 */
		public void setMinValue(double mv)
		{
			minValue = mv;
		}

		/**
		 * Method to get a parameter's minValue.
		 * @see #minValue
		 */
		public double getMinValue()
		{
			return minValue;
		}
		
		/**
		 * Method to set a parameter's maximum value.
		 * @see #maxValue
		 */
		public void setMaxValue(double mv)
		{
			maxValue = mv;
		}

		/**
		 * Method to get a parameter's maxValue.
		 * @see #maxValue
		 */
		public double getMaxValue()
		{
			return maxValue;
		}

		/**
		 * Method to set a parameter's step size.
		 * @see #stepSize
		 */
		public void setStepSize(double ss)
		{
			stepSize = ss;
		}

		/**
		 * Method to get a parameter's stepSize.
		 * @see #stepSize
		 */
		public double getStepSize()
		{
			return stepSize;
		}

		/**
		 * This method sets the current value.
		 * @see #currentValue
		 */
		public void setCurrentValue(double cv)
		{
			currentValue = cv;
		}

		/**
		 * This method gets the current value.
		 * @see #currentValue
		 */
		public double getCurrentValue()
		{
			return currentValue;
		}

		/**
		 * This method sets the best value to the specified value.
		 * @see #bestValue
		 */
		public void setBestValue(double bv)
		{
			bestValue = bv;
		}

		/**
		 * This method sets the bestValue to the currentValue.
		 * @see #bestValue
		 * @see #currentValue
		 */
		public void setBestValue()
		{
			bestValue = currentValue;
		}

		/**
		 * This method returns the best value.
		 * @see #bestValue
		 */
		public double getBestValue()
		{
			return bestValue;
		}

		/**
		 * Method to get how many loop interations needed for this parameter.
		 * @return This is (maxValue-minValue)/stepSize.
		 * @see #minValue
		 * @see #maxValue
		 * @see #stepSize
		 */
		public int getCount()
		{
			return (int)((maxValue-minValue)/stepSize);
		}
	}// end class
}
//
// $Log: not supported by cvs2svn $
//
