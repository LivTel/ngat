// QuadraticFitTest2.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/test/QuadraticFitTest2.java,v 0.3 2001-08-13 13:09:21 cjm Exp $

import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

import ngat.math.*;
import ngat.util.*;

/**
 * Simple test class for QuadraticFit.
 * Command line arguments are a,b,c,perturbation factor,number of points.
 * Then does a quadratic fit and prints the result out.
 * @author Chris Mottram
 * @version $Revision: 0.3 $
 */
public class QuadraticFitTest2 implements ChiSquaredFitUpdateListener
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: QuadraticFitTest2.java,v 0.3 2001-08-13 13:09:21 cjm Exp $");
	private QuadraticFit quadraticFit = null;
	private GraphPlot graphPlot = null;
	private GraphFrame graphFrame = null;
	private DecimalFormat decimalFormat = null;
	private int minX,maxX,minY,maxY,dataCount;
	private double enteredParameterList[] = new double[3];
	private double perturbation = 0.0;

	public static void main(String args[])
	{
		QuadraticFitTest2 qft = new QuadraticFitTest2();

		qft.initData(args);
		qft.initGraph();
		qft.run();
	}

	private void initData(String args[])
	{
		double xInc = 1.0;
		int i = 0;

		quadraticFit = new QuadraticFit();
		
		if(args.length != 6)
		{
			System.out.println("QuadraticFitTest2 <a> <b> <c> <perturbation> <number of points> <x inc>");
			System.exit(1);
		}
		try
		{
			for(i = 0;i < 3;i++)
				enteredParameterList[i] = Double.parseDouble(args[i]);
			i = 3;
			perturbation = Double.parseDouble(args[i]);
			i = 4;
			dataCount = Integer.parseInt(args[i]);
			i = 5;
			xInc = Double.parseDouble(args[i]);
		}
		catch (NumberFormatException e)
		{
			System.err.println("Getting number from paramater "+i+" = "+args[i]+": failed:"+e);
		}
		minX = 0;
		maxX = 10;
		minY = 0;
		maxY = 10;
		for(i = 0;i < dataCount;i++)
		{
			double x,y;

			x = (double)(i*xInc);
			y = quadraticY(x,enteredParameterList[0],enteredParameterList[1],enteredParameterList[2]);
			y += perturbation*(Math.random()-0.5);
			quadraticFit.addPoint(x,y);
			if(minX >= (int)x)
				minX = ((int)x)-1;
			if(maxX <= (int)x)
				maxX = ((int)x)+1;
			if(minY >= (int)y)
				minY = ((int)y)-1;
			if(maxY <= (int)y)
				maxY = ((int)y)+1;
		}
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
			Thread.sleep(100);
		}
		catch(InterruptedException e)
		{
		}
	}

	private void run()
	{
		double a,b,c,chiSquared,aChiSquared,bChiSquared,cChiSquared;

		quadraticFit.quadraticFit();
//		quadraticFit.quadraticFit(new ChiSquaredFitOneParameterUpdate("a",1,dataCount-1),
//			new ChiSquaredFitOneParameterUpdate("b",1,dataCount-1),
//			new ChiSquaredFitOneParameterUpdate("c",0,dataCount));
		a = quadraticFit.getA();
		b = quadraticFit.getB();
		c = quadraticFit.getC();
		chiSquared = quadraticFit.getChiSquared();
		aChiSquared = quadraticFit.getAChiSquared();
		bChiSquared = quadraticFit.getBChiSquared();
		cChiSquared = quadraticFit.getCChiSquared();
		System.out.println("A = "+decimalFormat.format(a));
		System.out.println("B = "+decimalFormat.format(b));
		System.out.println("C = "+decimalFormat.format(c));
		System.out.println("\u03c7\u00b2 = "+decimalFormat.format(chiSquared)+
			": a\u03c7\u00b2 = "+decimalFormat.format(aChiSquared)+
			": b\u03c7\u00b2 = "+decimalFormat.format(bChiSquared)+
			": c\u03c7\u00b2 = "+decimalFormat.format(cChiSquared));
		System.out.println("Seeing = "+decimalFormat.format(c));
		System.out.println("bestFocus = "+decimalFormat.format(getFocus(a,b)));

	}

	public void chiSquaredUpdate(int type,ChiSquaredFit csf)
	{
		double a;
		double b;
		double c;
		double bestChiSquared;
		int annotationY;
		List dataList;

		if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED)
			return;
		if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED)
			return;
		dataList = quadraticFit.getDataList();
		a = csf.getBestParameter("a");
		b = csf.getBestParameter("b");
		c = csf.getBestParameter("c");
		bestChiSquared = csf.getBestChiSquared();
		graphPlot.clear();
		graphPlot.clearAnnotation();
		annotationY = 1;
		graphPlot.annotate("type = "+ChiSquaredFitUpdateListener.UPDATE_TYPE_STRING[type],2,
			maxY-annotationY);
		annotationY+= Math.max(1,maxY/10);
		graphPlot.annotate("A = "+decimalFormat.format(a),2,maxY-annotationY);
		annotationY+= Math.max(1,maxY/10);
		graphPlot.annotate("B = "+decimalFormat.format(b),2,maxY-annotationY);
		annotationY+= Math.max(1,maxY/10);
		graphPlot.annotate("C = "+decimalFormat.format(c),2,maxY-annotationY);
		annotationY+= Math.max(1,maxY/10);
		graphPlot.annotate("\u03c7\u00b2 = "+decimalFormat.format(bestChiSquared),2,maxY-annotationY);
		annotationY+= Math.max(1,maxY/10);
		graphPlot.annotate("loop = "+csf.getLoopCount(),2,maxY-annotationY);
		for(int i = 0;i < dataList.size();i++)
		{
			QuadraticFitPoint qfp = (QuadraticFitPoint)(dataList.get(i));

			graphPlot.putPoint((float)(qfp.getX()),(float)(qfp.getY()),0);
			graphPlot.putPoint((float)(qfp.getX()),(float)(quadraticY(qfp.getX(),a,b,c)),1);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
		}
	}

	/**
	 * Method to return y = a*(x*x) + (b*x) +c.
	 */
	public double quadraticY(double x,double a,double b,double c)
	{
		return (a*(x*x))+(b*x)+c;
	}

	/**
	 * Method to return the best focus position for the telescope.
	 * This is done using computed values of <b>a</b> and <b>b</b> in the equation:
	 * y = a(x*x) + bx +c. This is differentiated to give the rate of change of y: 
	 * 2ax + b, which is zero at the best focus. Therefore this method returns:
	 * (-b)/(2a).
	 * @param a The computed <b>a</b> coefficient in the quadratic.
	 * @param b The computed <b>b</b> coefficient in the quadratic.
	 * @return The focus.
	 */
	public double getFocus(double a,double b)
	{
		return (-b)/(2*a);
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
			ChiSquaredFitModeller modeller;
			ChiSquaredFitDataValuer dataValuer;
			double bestValue;
			List dataList;
			int annotationY;

			if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_CHI_SQUARED)
				return;
			if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_BEST_CHI_SQUARED)
				return;
			dataList = quadraticFit.getDataList();
			modeller = csf.getModeller();
			dataValuer = csf.getActual();
			bestValue = csf.getBestParameter(parameterName);
			modeller.setParameter(parameterName,bestValue);
			graphPlot.clear();
			graphPlot.clearAnnotation();
			annotationY = 1;
			graphPlot.annotate("type = "+ChiSquaredFitUpdateListener.UPDATE_TYPE_STRING[type],2,
				maxY-annotationY);
			annotationY+= Math.max(1,maxY/10);
			graphPlot.annotate(parameterName+" = "+decimalFormat.format(bestValue),2,maxY-annotationY);
			annotationY+= Math.max(1,maxY/10);
			graphPlot.annotate("\u03c7\u00b2 = "+decimalFormat.format(csf.getBestChiSquared()),2,
				maxY-annotationY);
			annotationY+= Math.max(1,maxY/10);
			graphPlot.annotate("loop = "+csf.getLoopCount(),2,maxY-annotationY);
			for(int i = sIndex;i < eIndex;i++)
			{
				QuadraticFitPoint qfp = (QuadraticFitPoint)(dataList.get(i));
				float x,yd,ym;

				x = (float)(qfp.getX());
				yd = (float)(dataValuer.getValue(dataList,i));
				graphPlot.putPoint(x,yd,0);
				ym = (float)(modeller.getValue(dataList,i));
				graphPlot.putPoint(x,ym,1);
			}
			try
			{
				if(type == ChiSquaredFitUpdateListener.UPDATE_TYPE_PARAMETER_LOOP)
					Thread.sleep(50);
				else
					Thread.sleep(5000);
			}
			catch(InterruptedException e)
			{
			}
		}
	}//end class ChiSquaredFitAUpdate
}
//
// $Log: not supported by cvs2svn $
// Revision 0.2  2000/08/18 17:56:04  cjm
// Added ngat.math import.
//
// Revision 0.1  2000/08/18 17:31:56  cjm
// initial revision.
//
//
