// QuadraticFit.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/QuadraticFit.java,v 0.3 2000-09-01 15:30:37 cjm Exp $
package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * This class does a quadratic fit for a series of (x,y) data points.
 * It works out values (a,b,c) for a quadratic fit such that y = a(x*x)+bx+c.
 * @author Chris Mottram
 * @version $Revision: 0.3 $
 */
public class QuadraticFit
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: QuadraticFit.java,v 0.3 2000-09-01 15:30:37 cjm Exp $");
	/**
	 * The number of parameter fit for a quadratic. This is three (a,b,c).
	 */
	public final static int PARAMETER_COUNT = 3;
	/**
	 * The index in various arrays of the 'a' parameter.
	 */
	public final static int PARAMETER_A = 0;
	/**
	 * The index in various arrays of the 'b' parameter.
	 */
	public final static int PARAMETER_B = 1;
	/**
	 * The index in various arrays of the 'c' parameter.
	 */
	public final static int PARAMETER_C = 2;
	/**
	 * The name of each parameter we are fitting for. They are ordered in the
	 * list the same as their indexs in various arrays of parameter values.
	 * @see #PARAMETER_A
	 * @see #PARAMETER_B
	 * @see #PARAMETER_C
	 */
	public final static String PARAMETER_NAME_LIST[] = {"a","b","c"};
	/**
	 * Default parameter minimum start values.
	 * @see #parameterStartMinValue
	 */
	public final static double DEFAULT_PARAMETER_START_MIN_VALUE[] = {-10.0,-100.0,-1000.0};
	/**
	 * Default parameter maximum start values.
	 * @see #parameterStartMaxValue
	 */
	public final static double DEFAULT_PARAMETER_START_MAX_VALUE[] = {10.0,100.0,1000.0};
	/**
	 * Default parameter step size start values.
	 * @see #parameterStartStepSize
	 */
	public final static double DEFAULT_PARAMETER_START_STEP_SIZE[] = {0.2,10.0,100.0};
	/**
	 * Default parameter step count.
	 * @see #parameterStepCount
	 */
	public final static double DEFAULT_PARAMETER_STEP_COUNT = 50.0;
	/**
	 * List holding the data points for the fit.
	 */
	protected List dataList = null;
	/**
	 * The value of the <b>a</b> constant computed by the quadratic fit.
	 */
	protected double a = 0.0;
	/**
	 * The value of the <b>b</b> constant computed by the quadratic fit.
	 */
	protected double b = 0.0;
	/**
	 * The value of the <b>c</b> constant computed by the quadratic fit.
	 */
	protected double c = 0.0;
	/**
	 * The value of the <b>chi squared</b> computed for the quadratic fit given by a,b, and c.
	 * Note this value only set for one three degree of freedom ChiSquaredFit.
	 * @see #quadraticFit
	 */
	protected double chiSquared = 0.0;
	/**
	 * The value of the <b>chi squared</b> computed for the quadratic fit of a.
	 * Note this value only set for three one degree of freedom ChiSquaredFit.
	 * @see #quadraticFit
	 */
	protected double aChiSquared;
	/**
	 * The value of the <b>chi squared</b> computed for the quadratic fit of b.
	 * Note this value only set for three one degree of freedom ChiSquaredFit.
	 * @see #quadraticFit
	 */
	protected double bChiSquared;
	/**
	 * The value of the <b>chi squared</b> computed for the quadratic fit of c.
	 * Note this value only set for three one degree of freedom ChiSquaredFit.
	 * @see #quadraticFit
	 */
	protected double cChiSquared;
	/**
	 * Update listener passed through to chi squared fit.
	 */
	protected ChiSquaredFitUpdateListener updateListener = null;
	/**
	 * Array of doubles holding the start minimum value for the a,b and c parameters.
	 */
	protected double parameterStartMinValue[];
	/**
	 * Array of doubles holding the start maximum value for the a,b and c parameters.
	 */
	protected double parameterStartMaxValue[];
	/**
	 * Array of doubles holding the start step size for the a,b and c parameters.
	 */
	protected double parameterStartStepSize[];
	/**
	 * A value passed through to the Chi Squared fit. Used to determine the step size when
	 * constraining the parameter(s), such that step count steps are performed in the next parameter loop.
	 */
	protected double parameterStepCount = DEFAULT_PARAMETER_STEP_COUNT;

	/**
	 * Constructor.
	 * Constructs the dataList using a Vector.
	 * Initialise a,b, and c parameters.
	 * Initialses parameterStartMinValue parameterStartMaxValue parameterStartStepSize
	 * and fills them with values from DEFAULT_PARAMETER_START_MIN_VALUE, DEFAULT_PARAMETER_START_MAX_VALUE
	 * and DEFAULT_PARAMETER_START_STEP_SIZE
	 * @see #dataList
	 * @see #parameterStartMinValue
	 * @see #parameterStartMaxValue
	 * @see #parameterStartStepSize
	 * @see #DEFAULT_PARAMETER_START_MIN_VALUE
	 * @see #DEFAULT_PARAMETER_START_MAX_VALUE
	 * @see #DEFAULT_PARAMETER_START_STEP_SIZE
	 */
	public QuadraticFit()
	{
		dataList = new Vector();
		parameterStartMinValue = new double[PARAMETER_COUNT];
		for(int i = 0;i < PARAMETER_COUNT;i++)
			parameterStartMinValue[i] = DEFAULT_PARAMETER_START_MIN_VALUE[i];
		parameterStartMaxValue = new double[PARAMETER_COUNT];
		for(int i = 0;i < PARAMETER_COUNT;i++)
			parameterStartMaxValue[i] = DEFAULT_PARAMETER_START_MAX_VALUE[i];
		parameterStartStepSize = new double[PARAMETER_COUNT];
		for(int i = 0;i < PARAMETER_COUNT;i++)
			parameterStartStepSize[i] = DEFAULT_PARAMETER_START_STEP_SIZE[i];
		a = 0.0;
		b = 0.0;
		c = 0.0;
	}

	/**
	 * Method to add a point to the list of points used for the fit. The QuadraticFitPoint is
	 * added to the dataList.
	 * @param p The point to add.
	 * @see #dataList
	 * @see QuadraticFitPoint
	 */
	public void addPoint(QuadraticFitPoint p)
	{
		dataList.add(p);
	}

	/**
	 * Method to add a point to the list of points used for the fit. A QuadraticFitPoint is added to
	 * the dataList.
	 * @param x The x value of the point.
	 * @param y The y value of the point.
	 * @see #dataList
	 */
	public void addPoint(double x,double y)
	{
		QuadraticFitPoint p = null;

		p = new QuadraticFitPoint(x,y);
		dataList.add(p);
	}

	/**
	 * Method to add a point to the list of points used for the fit. A QuadraticFitPoint is added to
	 * the dataList.
	 * @param x The x value of the point.
	 * @param y The y value of the point.
	 * @see #dataList
	 */
	public void addPoint(float x,float y)
	{
		QuadraticFitPoint p = null;

		p = new QuadraticFitPoint(x,y);
		dataList.add(p);
	}

	/**
	 * Method to set an update listener.
	 * This method should be called after the QuadraticFit is constructed, but before the
	 * quadraticFit method is called to do the fit.
	 * @see #updateListener
	 */
	public void setUpdateListener(ChiSquaredFitUpdateListener ul)
	{
		updateListener = ul;
	}

	/**
	 * This method sets the minimum,maximum and step size values for one of the parameters
	 * the quadratic fit is searching for. A good choice for these values will improve the
	 * chance of QuadraticFit fining a good fit.
	 * This method should be called after the QuadraticFit is constructed, but before the
	 * quadraticFit method is called to do the fit.
	 * @param parameterIndex Which parameter we are setting the initial start values for. This one of
	 * 	PARAMETER_A, PARAMETER_B or PARAMETER_C.
	 * @param min The minimum start value of the specified parameter. The default values for this
	 * 	(overridden by this method) are specified in DEFAULT_PARAMETER_START_MIN_VALUE.
	 * @param max The maximum start value of the specified parameter. The default values for this
	 * 	(overridden by this method) are specified in DEFAULT_PARAMETER_START_MAX_VALUE.
	 * @param stepSize The initial step size of the specified parameter. Subsequent loops
	 * 	(after the first one) use parameterStepCount and the new parameter range to re-calculate the
	 * 	step size. The default values for the step size
	 * 	(overridden by this method) are specified in DEFAULT_PARAMETER_START_STEP_SIZE.
	 * @see #parameterStartMinValue
	 * @see #parameterStartMaxValue
	 * @see #parameterStartStepSize
	 * @see #PARAMETER_A
	 * @see #PARAMETER_B
	 * @see #PARAMETER_C
	 * @see #DEFAULT_PARAMETER_START_MIN_VALUE
	 * @see #DEFAULT_PARAMETER_START_MAX_VALUE
	 * @see #DEFAULT_PARAMETER_START_STEP_SIZE
	 */
	public void setParameterStartValues(int parameterIndex,double min,double max,double stepSize)
	{
		parameterStartMinValue[parameterIndex] = min;
		parameterStartMaxValue[parameterIndex] = max;
		parameterStartStepSize[parameterIndex] = stepSize;
	}

	/**
	 * Method to set the parameter step count. This is used by ChiSquaredFit to determine the step size
	 * when constraining the parameters. 100.0 is a good value for the three one degree of freedom technique.
	 * 50.0 is a good value for the three degree of freedom technoque.
	 * This method should be called after the QuadraticFit is constructed, but before the
	 * quadraticFit method is called to do the fit.
	 */
	public void setParameterStepCount(double v)
	{
		parameterStepCount = v;
	}

	/**
	 * Method that actually performs the quadratic fit, using the data previously added 
	 * to the dataList. This uses three degrees of freedom (i.e. finds parameters a,b, and c
	 * simultaneously.
	 * It calls <b>quadraticFit(10,0.01)</b>.
	 * @see #quadraticFit(int,double)
	 */
	public void quadraticFit()
	{
		quadraticFit(10,0.01);
	}

	/**
	 * Method that actually performs the quadratic fit, using the data previously added 
	 * to the dataList. This uses three degrees of freedom (i.e. finds parameters a,b, and c
	 * simultaneously.
	 * @param loopCount The number of times to refine the parameter ranges whilst calculating the
	 * 	quadratic fit. 10 is a good value for this number.
	 * @param targetChiSquared A target chi squared, if the computed exceeds this target the loops are 
	 *  	exited early. Determines how accurate the fit is. 0.01 is a good value.
	 * @see #dataList
	 */
/* three degrees of freedom model */
	public void quadraticFit(int loopCount,double targetChiSquared)
	{
		ChiSquaredFit csf = null;

		csf = new ChiSquaredFit();
		csf.setStart(0);
		csf.setEnd(dataList.size());
		csf.setData(dataList);
		csf.setModeller(new QuadraticChiSquaredFitModel());
		csf.setActual(new QuadraticChiSquaredFitActual());
		csf.setUpdateListener(updateListener);
		csf.addParameter("a",parameterStartMinValue[PARAMETER_A],parameterStartMaxValue[PARAMETER_A],
			parameterStartStepSize[PARAMETER_A]);
		csf.addParameter("b",parameterStartMinValue[PARAMETER_B],parameterStartMaxValue[PARAMETER_B],
			parameterStartStepSize[PARAMETER_B]);
		csf.addParameter("c",parameterStartMinValue[PARAMETER_C],parameterStartMaxValue[PARAMETER_C],
			parameterStartStepSize[PARAMETER_C]);
		csf.setParameterStepCount(parameterStepCount);//50.0
		csf.chiSquaredFit(loopCount,targetChiSquared);
		chiSquared = csf.getBestChiSquared();
		a = csf.getBestParameter("a");
		b = csf.getBestParameter("b");
		c = csf.getBestParameter("c");
	}

	/**
	 * Method that actually performs the quadratic fit, using the data previously added 
	 * to the dataList. This uses three one degrees of freedom chi sqaured fits 
	 * i.e. finds parameters a first using the second derivative, b second using the first derivative,
	 * and then finds c.
	 * @param loopCount The number of times to refine the parameter ranges whilst calculating the
	 * 	quadratic fit. 100 is a good value for this number.
	 * @param targetChiSquared A target chi squared, if the computed exceeds this target the loops are 
	 *  	exited early. Determines how accurate the fit is. 0.01 is a good value.
	 * @param aul The update listener to use for the <b>a</b> chi squared fit.
	 * @param bul The update listener to use for the <b>b</b> chi squared fit.
	 * @param cul The update listener to use for the <b>c</b> chi squared fit.
	 * @see #dataList
	 */
/* three one degree of freedom technique */
	public void quadraticFit(int loopCount,double targetChiSquared,
		ChiSquaredFitUpdateListener aul,ChiSquaredFitUpdateListener bul,ChiSquaredFitUpdateListener cul)
	{
		ChiSquaredFit csf = null;

	// do one degree of freedom fit on rate of change of gradient (a).
		csf = new ChiSquaredFit();
		csf.setStart(1);
		csf.setEnd(dataList.size()-2);
		csf.setData(dataList);
		csf.setModeller(new QuadraticChiSquaredFitGradientRateModel());
		csf.setActual(new QuadraticChiSquaredFitGradientRateActual());
		csf.setUpdateListener(aul);
		csf.addParameter("a",parameterStartMinValue[PARAMETER_A],parameterStartMaxValue[PARAMETER_A],
			parameterStartStepSize[PARAMETER_A]);
		csf.setParameterStepCount(parameterStepCount);//100.0
		csf.chiSquaredFit(loopCount,targetChiSquared);
		aChiSquared = csf.getBestChiSquared();
		a = csf.getBestParameter("a");
	// do one degree of freedom fit on gradient (b) using a.
		csf = new ChiSquaredFit();
		csf.setStart(1);
		csf.setEnd(dataList.size()-2);
		csf.setData(dataList);
		csf.setModeller(new QuadraticChiSquaredFitGradientModel(a));
		csf.setActual(new QuadraticChiSquaredFitGradientActual());
		csf.setUpdateListener(bul);
		csf.addParameter("b",parameterStartMinValue[PARAMETER_B],parameterStartMaxValue[PARAMETER_B],
			parameterStartStepSize[PARAMETER_B]);
		csf.setParameterStepCount(parameterStepCount);//100.0
		csf.chiSquaredFit(loopCount,targetChiSquared);
		bChiSquared = csf.getBestChiSquared();
		b = csf.getBestParameter("b");
	// do one degree of freedom fit on y boost (c) using a and b.
		csf = new ChiSquaredFit();
		csf.setStart(0);
		csf.setEnd(dataList.size());
		csf.setData(dataList);
		csf.setModeller(new QuadraticChiSquaredFitModel(a,b));
		csf.setActual(new QuadraticChiSquaredFitActual());
		csf.setUpdateListener(cul);
		csf.addParameter("c",parameterStartMinValue[PARAMETER_C],parameterStartMaxValue[PARAMETER_C],
			parameterStartStepSize[PARAMETER_C]);
		csf.setParameterStepCount(parameterStepCount);//100.0
		csf.chiSquaredFit(loopCount,targetChiSquared);
		cChiSquared = csf.getBestChiSquared();
		c = csf.getBestParameter("c");
	}

	/**
	 * Method to get the value of the <b>a</b> constant, in the quadratic y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * @see #a
	 * @see #quadraticFit
	 */
	public double getA()
	{
		return a;
	}

	/**
	 * Method to get the value of the <b>b</b> constant, in the quadratic y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * @see #b
	 * @see #quadraticFit
	 */
	public double getB()
	{
		return b;
	}

	/**
	 * Method to get the value of the <b>c</b> constant, in the quadratic y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * @see #c
	 * @see #quadraticFit
	 */
	public double getC()
	{
		return c;
	}

	/**
	 * Method to get the value of the <b>chiSquared</b> constant, computed for the quadratic 
	 * y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * This value is only set for the three degree of freedom method.
	 * @see #chiSquared
	 * @see #quadraticFit
	 */
	public double getChiSquared()
	{
		return chiSquared;
	}

	/**
	 * Method to get the value of the <b>aChiSquared</b> constant, computed for the quadratic 
	 * y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * This value is only set for the three chi squared fit's of one degree of freedom method.
	 * @see #aChiSquared
	 * @see #quadraticFit
	 */
	public double getAChiSquared()
	{
		return aChiSquared;
	}

	/**
	 * Method to get the value of the <b>bChiSquared</b> constant, computed for the quadratic 
	 * y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * This value is only set for the three chi squared fit's of one degree of freedom method.
	 * @see #bChiSquared
	 * @see #quadraticFit
	 */
	public double getBChiSquared()
	{
		return bChiSquared;
	}

	/**
	 * Method to get the value of the <b>cChiSquared</b> constant, computed for the quadratic 
	 * y = (a*(x*x)) + (b*x) + c.
	 * This will be zero until the quadraticFit method has been called to compute it.
	 * This value is only set for the three chi squared fit's of one degree of freedom method.
	 * @see #cChiSquared
	 * @see #quadraticFit
	 */
	public double getCChiSquared()
	{
		return cChiSquared;
	}

	/**
	 * Gets the data list used.
	 * @return The list if data.
	 */
	public List getDataList()
	{
		return dataList;
	}

// internal classes
// These two model and actual for second derivative of a quadratic
	/**
	 * Private inner class to model the second derivative of y= a(x^2) + bx + c. i.e.
	 * value = 2a. A is the only acceptable parameter.
	 * @see ChiSquaredFitModeller
	 */
	protected class QuadraticChiSquaredFitGradientRateModel implements ChiSquaredFitModeller
	{
		/**
		 * The parameter that is varied to find a chi squared fit for it.
		 */
		protected double a;

		/**
		 * Method called to set a constant parameter to compute a chi squared fit for.
		 * This class only allows the parameter name "a".
		 */
		public void setParameter(String name,double value)
		{
			if(name.equals("a"))
				a = value;
			else
				throw new IllegalArgumentException(this.getClass().getName()+":setParameter:name="+
					name+" to "+value+":Parameter not found.");
		}

		/**
		 * Method called to get a model data value to compare against a real value.
		 * This one always returns "2*a" where a is a parameter.
	 	 */
		public double getValue(List dataList,int index)
		{
			return 2*a;
		}
	}

	/**
	 * Private inner class to get the the second derivative of y= a(x^2) + bx + c, from the
	 * actual data. The getValue method returns the rate of change of gradient.
	 * @see ChiSquaredFitDataValuer
	 */
	protected class QuadraticChiSquaredFitGradientRateActual implements ChiSquaredFitDataValuer
	{
		/**
		 * Method called to get a actual data value.
		 * This one returns getRateOfChangeOfGradient for the middle of three QuadraticFitPoints.
		 * @see QuadraticFitPoint#getRateOfChangeOfGradient
	 	 */
		public double getValue(List dataList,int index)
		{
			QuadraticFitPoint point1,point2,point3;
			double retval;

			if((index < 1)||(index > dataList.size()-2))
			{
				throw new IllegalArgumentException(this.getClass().getName()+":getValue:index="+
					index+" illegal for size "+dataList.size()+".");
			}
			point1 = (QuadraticFitPoint)dataList.get(index-1);
			point2 = (QuadraticFitPoint)dataList.get(index);
			point3 = (QuadraticFitPoint)dataList.get(index+1);
			retval =  point1.getRateOfChangeOfGradient(point2,point3);
			return retval;
		}
	}

// These two model and actual for first derivative of a quadratic
	/**
	 * Private inner class to model the first derivative of y= a(x^2) + bx + c. i.e.
	 * value = 2ax + b. A and B are parameters, A can be set in the constructor so a 1 degree freedom
	 * of movement can be used once A has been determined.
	 * @see ChiSquaredFitModeller
	 */
	protected class QuadraticChiSquaredFitGradientModel implements ChiSquaredFitModeller
	{
		/**
		 * This parameter may be varied to find a chi squared fit for it,
	 	 * or it may be constant (set in the constructor) so that chi squared is over
		 * one degree of freedom.
		 */
		protected double a;
		/**
		 * The parameter that is varied to find a chi squared fit for it.
		 */
		protected double b;

		/**
		 * Default Constructor. 
		 */
		public QuadraticChiSquaredFitGradientModel()
		{
		}

		/**
	 	 * Constructor. If we are only doing a chi squared fit of 'b', we can set the models
		 * 'a' value to a previously determined value with this constructor.
	 	 * @param aValue The constant value to use for 'a'.
		 */
		public QuadraticChiSquaredFitGradientModel(double aValue)
		{
			super();
			a = aValue;
		}

		/**
		 * Method called to set a constant parameter to compute a chi squared fit for.
		 * This class only allows the parameter name "a".
		 */
		public void setParameter(String name,double value)
		{
			if(name.equals("a"))
				a = value;
			else if(name.equals("b"))
				b = value;
			else
				throw new IllegalArgumentException(this.getClass().getName()+":setParameter:name="+
					name+" to "+value+":Parameter not found.");
		}

		/**
		 * Method called to get a model data value to compare against a real value.
		 * This one always returns "2ax + b" where a is a parameter.
	 	 */
		public double getValue(List dataList,int index)
		{
			QuadraticFitPoint point;

			point = (QuadraticFitPoint)dataList.get(index);
			return (2*a*point.getX()) + b;
		}
	}

	/**
	 * Private inner class to get the the first derivative of y= a(x^2) + bx + c, from the
	 * actual data. The getValue method returns the rate of change of gradient.
	 * @see ChiSquaredFitDataValuer
	 */
	protected class QuadraticChiSquaredFitGradientActual implements ChiSquaredFitDataValuer
	{
		/**
		 * Method called to get a real data value.
		 * This one returns getGradient for the QuadraticFitPoints at index and index+1.
		 * @see QuadraticFitPoint#getGradient
	 	 */
/* This is the old version of the getValue method, that only uses two data points to get a gradient,
** rather than getting a gradient each side of the point and averaging it.
** This version gives less accurate results.
**		public double getValue(List dataList,int index)
**		{
**			QuadraticFitPoint point1,point2;
**
**			if((index < 0)||(index > dataList.size()-2))
**			{
**				throw new IllegalArgumentException(this.getClass().getName()+":getValue:index="+
**					index+" illegal for size "+dataList.size()+".");
**			}
**			point1 = (QuadraticFitPoint)dataList.get(index);
**			point2 = (QuadraticFitPoint)dataList.get(index+1);
**			return point1.getGradient(point2);
**		}
*/
		/**
		 * Method called to get a actual data value.
		 * This one return the average gradient of: 
	 	 * dataList[index-1].getGradient(dataList[index])
	 	 * dataList[index].getGradient(dataList[index+1]) of three QuadraticFitPoints.
		 * @see QuadraticFitPoint#getGradient
	 	 */
		public double getValue(List dataList,int index)
		{
			QuadraticFitPoint point1,point2,point3;

			if((index < 1)||(index > dataList.size()-2))
			{
				throw new IllegalArgumentException(this.getClass().getName()+":getValue:index="+
					index+" illegal for size "+dataList.size()+".");
			}
			point1 = (QuadraticFitPoint)dataList.get(index-1);
			point2 = (QuadraticFitPoint)dataList.get(index);
			point3 = (QuadraticFitPoint)dataList.get(index+1);
			return (point1.getGradient(point2)+point2.getGradient(point3))/2.0;
		}
	}

// These two model and actual for three degrees of freedom chi squared fit of a quadratic
	/**
	 * Private inner class to model y= a(x^2) + bx + c. i.e.
	 * a,b and c are the parameters.
	 * @see ChiSquaredFitModeller
	 */
	protected class QuadraticChiSquaredFitModel implements ChiSquaredFitModeller
	{
		/**
		 * A parameter that is varied to find a chi squared fit for it.
		 */
		protected double a;
		/**
		 * A parameter that is varied to find a chi squared fit for it.
		 */
		protected double b;
		/**
		 * A parameter that is varied to find a chi squared fit for it.
		 */
		protected double c;

		/**
		 * Default Constructor. 
		 */
		public QuadraticChiSquaredFitModel()
		{
		}

		/**
		 * Constructor. This can be used to initialise the models 'a' and 'b' values
		 * to constants, so that we can use this model to do a one degree fit on 'c',
		 * rather than a three degree of freedom fit and 'a','b' and 'c'. 
		 */
		public QuadraticChiSquaredFitModel(double aValue,double bValue)
		{
			a = aValue;
			b = bValue;
		}

		/**
		 * Method called to set a constant parameter to compute a chi squared fit for.
		 * This class only allows parameters name "a","b" and "c".
		 */
		public void setParameter(String name,double value)
		{
			if(name.equals("a"))
				a = value;
			else if(name.equals("b"))
				b = value;
			else if(name.equals("c"))
				c = value;
			else
				throw new IllegalArgumentException(this.getClass().getName()+":setParameter:name="+
					name+" to "+value+":Parameter not found.");
		}

		/**
		 * Method called to get a model data value to compare against a real value.
		 * This one always returns a(x^2) +bx + c where a,b,c are the parameters.
	 	 */
		public double getValue(List dataList,int index)
		{
			QuadraticFitPoint point;

			point = (QuadraticFitPoint)dataList.get(index);
			return (a*(point.getX()*point.getX())) + (b * point.getX()) + c;
		}
	}

	/**
	 * Private inner class to get the actual value of y from the
	 * data. The getValue method returns y.
	 * @see ChiSquaredFitDataValuer
	 */
	protected class QuadraticChiSquaredFitActual implements ChiSquaredFitDataValuer
	{
		/**
		 * Method called to get a model data value to compare against a real value.
		 * This one returns y for the x at index in the dataList.
	 	 */
		public double getValue(List dataList,int index)
		{
			QuadraticFitPoint point;

			point = (QuadraticFitPoint)dataList.get(index);
			return point.getY();
		}
	}
}
//
// $Log: not supported by cvs2svn $
// Revision 0.2  2000/08/29 10:12:57  cjm
// Added setParameterStartValues method.
//
// Revision 0.1  2000/08/18 17:24:43  cjm
// initial revision.
//
//

