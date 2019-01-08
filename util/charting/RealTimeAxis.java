package ngat.util.charting;

/** An Axis which is acapable of scrolling in real-time. Used for
 * time-series data where the time axis must update.
 */
public class RealTimeAxis extends Axis {

    /** The time at which the Axis starts (millis).*/
    protected long startTime;
    
    /** Length of the Axis window (millis).*/
    protected long windowSize;
    
    /** Length of the increment and the update time (millis).*/
    protected long incrementSize;

    /** The Chart to which it is attached - needs to update.*/
    protected Chart chart;

    /** Carries out the scrolling action.*/
    protected Thread scroller;

    public RealTimeAxis(long startTime, long windowSize, long incrementSize) {
	super();
	this.startTime     = startTime;
	this.windowSize    = windowSize;
	this.incrementSize = incrementSize;
	lockAxisLabels = false;
	maximum = (double)startTime;
	minimum = maximum - (double)windowSize;
    }
    
    public void startScroller() {
	if (scroller != null) {
	    if (scroller.isAlive())
		return;	    
	}
	scroller = new Scroller();
	scroller.start();
    }
    
    public void setChart(Chart chart) {
	this.chart = chart;
    }
    
    private class Scroller extends Thread {
	public void run() {
	    while (true) {
		try {sleep(incrementSize);} catch(InterruptedException e){}
		advance( (double)incrementSize );		
		chart.redraw();						   
	    }
	}
    }
    
}
