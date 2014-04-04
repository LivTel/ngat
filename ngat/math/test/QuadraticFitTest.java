package ngat.math.test;

import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

import ngat.math.*;
import ngat.util.*;

/**
 * Simple test class for QuadraticFit.
 * Puts command line arguments as x,y values in data list.
 * Then does a quadratic fit and prints the result out.
 * @author Chris Mottram
 * @version $Revision$
 */
public class QuadraticFitTest implements ChiSquaredFitUpdateListener
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Degrees of freedom value.
	 */
	public final static int ONE_DEGREE_OF_FREEDOM = 1;
	/**
	 * Degrees of freedom value.
	 */
	public final static int THREE_DEGREES_OF_FREEDOM = 3;
	private QuadraticFit quadraticFit = null;
	private GraphPlot graphPlot = null;
	private GraphFrame graphFrame = null;
	private DecimalFormat decimalFormat = null;
	private int minX,maxX,minY,maxY,dataCount;
	private int loopCount = 10;
	private int degreesOfFreedom = THREE_DEGREES_OF_FREEDOM;
	private double targetChiSquared = 0.01;

	public static void main(String args[])
	{
		QuadraticFitTest qft = new QuadraticFitTest();
		
		qft.initData(args);
		qft.initGraph();
		qft.run();
	}
	
	private void initData(String args[])
	{
		quadraticFit = new QuadraticFit();
		minX = 999;
		maxX = -999;
		minY = 999;
		maxY = -999;
		dataCount = 0;
		if(args.length == 0)
		{
			help();
			System.exit(0);
		}
		for(int i = 0;i < args.length;i++)
		{
				if(args[i].equals("-degrees_of_freedom"))
				{
					if((i+1) >= args.length)
					{
						System.err.println("Degrees of Freedom needs parameter.");
						System.exit(1);
					}
					try
					{
						degreesOfFreedom = Integer.parseInt(args[i+1]);
						i++;
						if((degreesOfFreedom != ONE_DEGREE_OF_FREEDOM)&&
						   (degreesOfFreedom != THREE_DEGREES_OF_FREEDOM))
						{
							System.err.println("Degrees of Freedom should be 1 or 3.");
							System.exit(1);
						}
					}
					catch (NumberFormatException e)
					{
						System.err.println("Failed to parse degrees of freedom "+
								   args[i+1]+":"+e);
						System.exit(1);
					}
				}
				else if(args[i].equals("-help"))
				{
					help();
					System.exit(0);
				}
				else if(args[i].equals("-loop_count"))
				{
					if((i+1) >= args.length)
					{
						System.err.println("Loop count needs parameter.");
						System.exit(1);
					}
					try
					{
						loopCount = Integer.parseInt(args[i+1]);
						i++;
					}
					catch (NumberFormatException e)
					{
						System.err.println("Failed to parse loop count "+args[i+1]+":"+e);
						System.exit(1);
					}
				}
				else if(args[i].equals("-parameter_start_values"))
				{
					if((i+4) >= args.length)
					{
						System.err.println("Parameter start values needs 4 parameters: "+
								   "paramater index,min,max, and step size.");
						System.exit(1);
					}
					try
					{
						int parameterIndex;
						double min,max,stepSize;

						if(args[i+1].equals("a"))
							parameterIndex = QuadraticFit.PARAMETER_A;
						else if(args[i+1].equals("b"))
							parameterIndex = QuadraticFit.PARAMETER_B;
						else if(args[i+1].equals("c"))
							parameterIndex = QuadraticFit.PARAMETER_C;
						else
							parameterIndex = Integer.parseInt(args[i+1]);
						i++;
						min = Double.parseDouble(args[i+1]);
						i++;
						max = Double.parseDouble(args[i+1]);
						i++;
						stepSize = Double.parseDouble(args[i+1]);
						i++;
						System.err.println("Setting parameter "+parameterIndex+" to min "+
								   min+" , max "+max+" , step size "+stepSize);
						quadraticFit.setParameterStartValues(parameterIndex,min,max,stepSize);
					}
					catch (NumberFormatException e)
					{
						System.err.println("Failed to parse parameter start value "+
								   args[i+1]+":"+e);
						System.exit(1);
					}
				}
				else if(args[i].equals("-parameter_step_count"))
				{
					if((i+1) >= args.length)
					{
						System.err.println("Parameter step count needs parameter.");
						System.exit(1);
					}
					try
					{
						double v;
						v = Double.parseDouble(args[i+1]);
						quadraticFit.setParameterStepCount(v);
						i++;
					}
					catch (NumberFormatException e)
					{
						System.err.println("Failed to parse parameter step count "+
								   args[i+1]+":"+e);
						System.exit(1);
					}
				}
				else
				{
					if((i+1) >= args.length)
					{
						System.err.println("Odd number of parameters detected at "+i+".");
						System.err.println("Supply pairs of x/y positions to quadratic fit to.");
						System.exit(1);
					}
					try
					{
						double xd = Double.parseDouble(args[i]);
						i++;
						double yd = Double.parseDouble(args[i]);
						quadraticFit.addPoint(xd,yd);
						System.err.println("Added point:x = "+xd+" :y = "+yd);
						dataCount++;
						if(minX >= (int)xd)
							minX = ((int)xd)-1;
						if(maxX <= (int)xd)
							maxX = ((int)xd)+1;
						if(minY >= (int)yd)
							minY = ((int)yd)-1;
						if(maxY <= (int)yd)
							maxY = ((int)yd)+1;
					}
					catch (NumberFormatException e)
					{
						System.err.println("Test failed for parameter i="+i+" = "+
								   args[i]+","+args[i+1]+":"+e);
						System.exit(1);
					}
				}// end if
		}// end for on args
		quadraticFit.setUpdateListener(this);
	}
	
	public void initGraph()
	{
		graphPlot = new GraphPlot(minX,maxX,minY,maxY);
		
		graphPlot.setMark(0,GraphPlot.SPOT);
		graphPlot.setMark(1,GraphPlot.SPOT);
		
		graphPlot.setMarkSize(0,2);
		graphPlot.setMarkSize(1,2);
		
		graphPlot.setJoinPoints(0,true);
		graphPlot.setJoinPoints(1,true);
		
		graphFrame = new GraphFrame(this.getClass().getName(),graphPlot);
		graphFrame.setVisible(true);
		graphFrame.addWindowListener(new WindowAdapter() 
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
		graphFrame.setBounds(0,0,400,400);
		
		decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(5);
		try
		{
			Thread.sleep(10);
		}
		catch(InterruptedException e)
		{
		}
	}

	/**
	 * Run the quadratic fit.
	 * @see #loopCount
	 * @see #targetChiSquared
	 * @see #degreesOfFreedom
	 */
	private void run()
	{
		if(degreesOfFreedom == ONE_DEGREE_OF_FREEDOM)
		{
			quadraticFit.quadraticFit(loopCount,targetChiSquared,
						  new ChiSquaredFitOneParameterUpdate("a",1,dataCount-1),
						  new ChiSquaredFitOneParameterUpdate("b",1,dataCount-1),
						  new ChiSquaredFitOneParameterUpdate("c",0,dataCount));
		}
		else if (degreesOfFreedom == THREE_DEGREES_OF_FREEDOM)
		{
			quadraticFit.quadraticFit(loopCount,targetChiSquared);
		}
		else
		{
			System.out.println(this.getClass().getName()+"Illegal degrees of freedom:"+
					   degreesOfFreedom+".");
			System.exit(1);
		}	
 		System.out.println("A = "+decimalFormat.format(quadraticFit.getA()));
		System.out.println("B = "+decimalFormat.format(quadraticFit.getB()));
		System.out.println("C = "+decimalFormat.format(quadraticFit.getC()));
		System.out.println("\u03c7\u00b2 = "+decimalFormat.format(quadraticFit.getChiSquared()));
	}
	
	public void chiSquaredUpdate(int type,ChiSquaredFit csf)
	{
		float annotateXPos;
		double a;
		double b;
		double c;
		double bestChiSquared;
		float annotationY;
		List dataList;

		if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED)
			return;
		if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED)
			return;
		dataList = quadraticFit.getDataList();
		if((type == ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED) || 
		   (type == ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED))
		{
			a = csf.getCurrentParameter("a");
			b = csf.getCurrentParameter("b");
			c = csf.getCurrentParameter("c");
		}
		else
		{
			a = csf.getBestParameter("a");
			b = csf.getBestParameter("b");
			c = csf.getBestParameter("c");
		}
		bestChiSquared = csf.getBestChiSquared();
		graphPlot.clear();
		graphPlot.clearAnnotation();
		annotateXPos = minX+((maxX-minX)/10);
		annotationY = 1;
		graphPlot.annotate("type = "+ChiSquaredFitUpdateListener.UPDATE_TYPE_STRING[type],annotateXPos,
				   maxY-annotationY);
		System.out.println("type = "+ChiSquaredFitUpdateListener.UPDATE_TYPE_STRING[type]);
		annotationY++;
		graphPlot.annotate("A = "+decimalFormat.format(a),annotateXPos,maxY-annotationY);
		System.out.println("A = "+decimalFormat.format(a));
		annotationY++;
		graphPlot.annotate("B = "+decimalFormat.format(b),annotateXPos,maxY-annotationY);
		System.out.println("B = "+decimalFormat.format(b));
		annotationY++;
		graphPlot.annotate("C = "+decimalFormat.format(c),annotateXPos,maxY-annotationY);
		System.out.println("C = "+decimalFormat.format(c));
		annotationY++;
		graphPlot.annotate("\u03c7\u00b2 = "+decimalFormat.format(bestChiSquared),annotateXPos,maxY-annotationY);
		System.out.println("\u03c7\u00b2 = "+decimalFormat.format(bestChiSquared));
		annotationY++;
		graphPlot.annotate("loop = "+csf.getLoopCount(),annotateXPos,maxY-annotationY);
		for(int i = 0;i < dataList.size();i++)
		{
			QuadraticFitPoint qfp = (QuadraticFitPoint)(dataList.get(i));
			
			graphPlot.putPoint((float)(qfp.getX()),(float)(qfp.getY()),0);
			graphPlot.putPoint((float)(qfp.getX()),(float)(quadraticY(qfp.getX(),a,b,c)),1);
		}
		try
		{
			Thread.sleep(1);
		}
		catch(InterruptedException e)
		{
		}
	}
	
	public double quadraticY(double x,double a,double b,double c)
	{
		return (a*(x*x))+(b*x)+c;
	}
	
	/**
	 * Method to print out a help message.
	 */
	private void help()
	{
		System.out.println("java ngat.math.test.QuadraticFitTest [-degrees_of_freedom <1|3>|-loop_count <n>\n"+
				   "\t|-parameter_step_count <n>|\n"+
				   "\t-parameter_start_values <a|b|c> <min> <max> <step size>|<x> <y>]...");
		System.out.println("Supply pairs of x/y positions to quadratic fit to.");
		System.out.println("-degrees_of_freedom specifies whether to do 3 1 DOF fits, or 1 3 DOF fit.");
		System.out.println("-loop_count <n> specifies the number of times through the quadratic fit loop "+
				   "(default 10).");
		System.out.println("-parameter_step_count <n> specifies that step count steps are performed in the "+
				   "next parameter loop.");
		System.out.println("-parameter_start_values <a|b|c> <min> <max> <step size> configures one of the "+
				   "parameter ranges. The step size is used the first time only.");
	}
	
	/**
	 * Class that provides an update listener for a three one degree of freedom fit.
	 */
	private class ChiSquaredFitOneParameterUpdate implements ChiSquaredFitUpdateListener
	{
		/**
		 * The parameter name. Specify the parameter name exactley as specified in the QuadraticFit 
		 * (a,b,c).
		 */
		private String parameterName = null;
		/**
		 * The start index in the data List to start drawing the graph. This is needed, as
		 * some derivative data evaluation functions need points around the index passed to them.
		 */
		private int sIndex;
		/**
		 * The end index in the data List to start drawing the graph. This is needed, as
		 * some derivative data evaluation functions need points around the index passed to them.
		 */
		private int eIndex;
		/**
		 * Constructor.
		 * @param s The parameter name. Specify the parameter name exactley as specified in the QuadraticFit 
		 * 	(a,b,c).
		 * @param si The start index in the data List to start drawing the graph. This is needed, as
		 * 	some derivative data evaluation functions need points around the index passed to them.
		 * @param ei The end index in the data List to start drawing the graph. This is needed, as
		 * 	some derivative data evaluation functions need points around the index passed to them.
		 * @see #parameterName
		 * @see #sIndex
		 * @see #eIndex
		 */
		public ChiSquaredFitOneParameterUpdate(String s,int si,int ei)
		{
			parameterName = s;
			sIndex = si;
			eIndex = ei;
		}
		
		/**
		 * The update listener. Note it doesn't modify the retrieved model's
		 * parameter values, as it assumes ChiSquaredFit calls the updateListener when
		 * it has found a new best ChiSquaredFit.
		 */
		public void chiSquaredUpdate(int type,ChiSquaredFit csf)
		{
			ChiSquaredFitModeller modeller = null;
			ChiSquaredFitDataValuer dataValuer = null;
			double bestValue;
			List dataList = null;
			
			if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED)
				return;
			if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED)
				return;
			dataList = quadraticFit.getDataList();
			modeller = csf.getModeller();
			dataValuer = csf.getActual();
			bestValue = csf.getBestParameter(parameterName);
			
			graphPlot.clear();
			graphPlot.clearAnnotation();
			graphPlot.annotate(parameterName+" = "+decimalFormat.format(bestValue),2,maxY-1);
			graphPlot.annotate("\u03c7\u00b2 = "+decimalFormat.format(csf.getBestChiSquared()),2,maxY-2);
			graphPlot.annotate("loop = "+csf.getLoopCount(),2,maxY-3);
			for(int i = sIndex;i < eIndex;i++)
			{
				QuadraticFitPoint qfp = (QuadraticFitPoint)(dataList.get(i));
				
				graphPlot.putPoint((float)(qfp.getX()),(float)(dataValuer.getValue(dataList,i)),0);
				graphPlot.putPoint((float)(qfp.getX()),(float)(modeller.getValue(dataList,i)),1);
			}
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
			}
		}
	}//end class ChiSquaredFitAUpdate
}
//
// $Header: /space/home/eng/cjm/cvs/ngat/math/test/QuadraticFitTest.java,v 0.6 2004-07-28 11:09:40 cjm Exp $
//
// $Log: not supported by cvs2svn $
// Revision 0.5  2004/02/12 17:30:15  cjm
// More parameters.
//
// Revision 0.4  2001/08/13 13:09:29  cjm
// Upgraded to use ngat.util. package.
//
// Revision 0.3  2001/08/13 12:59:48  cjm
// Added x corrdinate input to argument list parser.
//
// Revision 0.2  2000/08/18 17:56:20  cjm
// Added ngat.math import.
//
// Revision 0.1  2000/08/18 17:31:56  cjm
// initial revision.
//
//
