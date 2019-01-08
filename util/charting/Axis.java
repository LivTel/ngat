package ngat.util.charting;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Axis {

    public static Color DEFAULT_GRID_COLOR = Color.white;

    /** True if major ticks should be displayed. (Default is True).*/
    protected boolean showMajorTicks;

    /** True if minor ticks should be displayed. (Default is False).*/
    protected boolean showMinorTicks;
    
    /** True if major grid lines should be displayed. (Default is False).*/
    protected boolean showMajorGrid;
    
    /** True if minor grid lines should be displayed. (Default is False).*/
    protected boolean showMinorGrid;

    /** True if the axis labels should be locked to the majorTickInterval.*/
    protected boolean lockAxisLabels;
    
    /** Interval between major ticks (user units).*/
    protected double majorTickInterval;
    
    /** Interval between minor ticks (user units).*/
    protected double minorTickInterval;

    /** Color of the grid lines and ticks.*/
    protected Color gridColor;

    double minimum;

    double maximum;

    /** Formats the axis labels.*/
    protected Labeller labeller;

    /** Axis title.*/
    protected Title title;


    public Axis() {
	showMajorTicks = true;
	showMinorTicks = false;
	showMajorGrid  = false;
	showMinorGrid  = false;
	gridColor = DEFAULT_GRID_COLOR;
	minimum = 0.0;
	maximum = 0.0;
	minorTickInterval = 0.0;
	majorTickInterval = 0.0;
	lockAxisLabels = false;
    }

    public boolean lockAxisLabels() { return lockAxisLabels; }

    public void setLockAxisLabels(boolean lock) { this.lockAxisLabels = lock; }

    public boolean showMajorTicks() { return showMajorTicks; }

    public void setShowMajorTicks(boolean show) { this.showMajorTicks = show; }

    public boolean showMinorTicks() { return showMinorTicks; }

    public void setShowMinorTicks(boolean show) { this.showMinorTicks = show; }
    
    public boolean showMajorGrid()  { return showMajorGrid; }
 
    public void setShowMajorGrid(boolean show) { this.showMajorGrid = show; }
    
    public boolean showMinorGrid()  { return showMinorGrid; }
 
    public void setShowMinorGrid(boolean show) { this.showMinorGrid = show; }
    
    public double getMajorTickInterval() { return majorTickInterval; }
    
    public void setMajorTickInterval(double interval) { this.majorTickInterval = interval; }

    public double getMinorTickInterval() { return minorTickInterval; }

    public void setMinorTickInterval(double interval) { this.minorTickInterval = interval; }

    public Color getGridColor() { return gridColor; }

    public void setGridColor(Color color) { this.gridColor = color; }

    public Labeller getLabeller() { return labeller; }

    public void setLabeller(Labeller labeller) { this.labeller = labeller; }

    public Title getTitle() { return title; }

    public void setTitle(Title title) { this.title = title; }

    public double getMinimum() { return minimum; }

    public void setMinimum(double min) { this.minimum = min; }

    public double getMaximum() { return maximum; }

    public void setMaximum(double max) { this.maximum = max; }

    public double getExtent() { return maximum - minimum; }

    public void setExtent(double min, double max) {
	this.minimum = min;
	this.maximum = max;
    }

    /** Nudge the axis along by value.*/
    public void advance(double value) {
	minimum += value;
	maximum += value;
    }

}
