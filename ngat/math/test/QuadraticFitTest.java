// QuadraticFitTest.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/math/test/QuadraticFitTest.java,v 0.1 2000-08-18 17:31:56 cjm Exp $

import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

import ngat.OSS.utility.*;

/**
 * Simple test class for QuadraticFit.
 * Puts command line arguments as y values in data list (i,args[i]).
 * Then does a quadratic fit and prints the result out.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class QuadraticFitTest implements ChiSquaredFitUpdateListener
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: QuadraticFitTest.java,v 0.1 2000-08-18 17:31:56 cjm Exp $");
	private QuadraticFit quadraticFit = null;
	private GraphPlot graphPlot = null;
	private GraphFrame graphFrame = null;
	private DecimalFormat decimalFormat = null;
	private int minX,maxX,minY,maxY,dataCount;

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
		minX = 0;
		maxX = 10;
		minY = 0;
		maxY = 10;
		dataCount = args.length;
		for(int i = 0;i < args.length;i++)
		{
			try
			{
				double d = Double.parseDouble(args[i]);
				quadraticFit.addPoint((double)(i+1),d);
				if(maxX <= (i+1))
					maxX = (i+2);
				if(minY >= (int)d)
					minY = ((int)d)-1;
				if(maxY <= (int)d)
					maxY = ((int)d)+1;
			}
			catch (NumberFormatException e)
			{
				System.err.println("Test failed for parameter i="+i+" = "+args[i]+":"+e);
			}
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

		graphPlot.SetJoinPoints(0,true);
		graphPlot.SetJoinPoints(1,true);

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

	private void run()
	{
		quadraticFit.quadraticFit();
//		quadraticFit.quadraticFit(new ChiSquaredFitOneParameterUpdate("a",1,dataCount-1),
//			new ChiSquaredFitOneParameterUpdate("b",1,dataCount-1),
//			new ChiSquaredFitOneParameterUpdate("c",0,dataCount));
		System.out.println("A = "+decimalFormat.format(quadraticFit.getA()));
		System.out.println("B = "+decimalFormat.format(quadraticFit.getB()));
		System.out.println("C = "+decimalFormat.format(quadraticFit.getC()));
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
//		a = csf.getCurrentParameter("a");
//		b = csf.getCurrentParameter("b");
//		c = csf.getCurrentParameter("c");
		bestChiSquared = csf.getBestChiSquared();
		graphPlot.clear();
		graphPlot.clearAnnotation();
		annotationY = 1;
		graphPlot.annotate("type = "+ChiSquaredFitUpdateListener.UPDATE_TYPE_STRING[type],2,
			maxY-annotationY);
		annotationY++;
		graphPlot.annotate("A = "+decimalFormat.format(a),2,maxY-annotationY);
		annotationY++;
		graphPlot.annotate("B = "+decimalFormat.format(b),2,maxY-annotationY);
		annotationY++;
		graphPlot.annotate("C = "+decimalFormat.format(c),2,maxY-annotationY);
		annotationY++;
		graphPlot.annotate("\u03c7\u00b2 = "+decimalFormat.format(bestChiSquared),2,maxY-annotationY);
		annotationY++;
		graphPlot.annotate("loop = "+csf.getLoopCount(),2,maxY-annotationY);
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
// $Log: not supported by cvs2svn $
//
