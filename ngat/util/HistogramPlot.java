package ngat.util;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * A JPanel for plotting Histograms. The HistogramPlot updates its display each time a
 * sample is presented. Binning is determined by supplied parameters and there is the option
 * to automatically resize the Y (count) axis if the highest count starts to reach the upper
 * limit of the axis.
 * ## this will be changed to extend SimpleGraphPlot as only ONE channel is needed 
 *
 * $Id: HistogramPlot.java,v 1.1 2000-11-21 16:43:47 snf Exp $
 * 
 **/ 
public class HistogramPlot extends GraphPlot {
   
    protected float xMin, xMax, xSize;
    protected int counts[];
    protected int maxCount;
    protected int nBins;
    protected boolean autoresize;

    /** Constructs a Graph panel with maxPoints plotted points per channel and with
     * axes ranging from X: (x1 to x2) and Y: (0 to initMax). The bins are of width
     * (x2 - x1) / nBins  */
    public HistogramPlot(int bins, float ax1, float ax2, int initMax) {
	// use default of 200 for yHi.
	super(bins, ax1, ax2, 0.0f, (float)initMax);
	xMin = ax1;
	xMax = ax2;
	xSize = xMax - xMin;
	nBins = bins;
	maxCount = 0;
	autoresize = true;
	counts = new int[nBins];
	for (int i = 0; i < nBins; i++){
	    counts[i] = 0;
	}
    }

    /** Count and bin a sample with the given value.*/
    public void sample(float x) {
	int bin = (int)((float)(nBins*(x-xMin)/(xSize)));
	counts[bin]++;
	if (counts[bin] > maxCount) maxCount = counts[bin];
	if (autoresize) {
	    if ((float)maxCount > yHi*0.95f) setYHi(yHi*1.5f);
	}
	repaint();
    }

    /** Set the value in one of the bins. This is used to allow a channel
     * monitor type display to be used. */
    public void putChannel(int channel, float val) {
	counts[channel] = (int)val;
	repaint();
    }
   
    /** Draw the histogram columns. */
    public void drawPoints(Graphics g) {
	g.setColor(Color.blue);
	for (int i = 0; i < nBins; i++) {
	    float xc = (i+0.5f)*xSize/nBins;
	    float wc = xSize*0.45f/nBins;
	    int xa = (int)getScreenX(xc-wc);
	    int xb = (int)getScreenX(xc+wc);
	    int ys = (int)getScreenY((float)counts[i]);
	    int y0 = (int)getScreenY(0.0f);
	    System.out.println("count: "+counts[i]+" ys: "+ys+ " y0: "+y0);
	    g.fillRect(xa, ys, xb-xa, y0-ys);
	}
    }

    /** Just calls paint() - helps to avoid flicker. */
    public void update(Graphics g) {
	paint(g);
    }
    
}


/** $Log: not supported by cvs2svn $ */







