package ngat.util.charting;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Graph implements ChartConstants {


    public static final Color DEFAULT_POINT_COLOR = Color.blue;

    /** Title, used in legend.*/
    protected String legend;

    /** The color of the data symbols and lines.*/
    protected Color pointColor;

    /** Data point symbol type (enum).*/
    protected int pointSymbol;

    /** Data point symbol size (pixels).*/
    protected int pointSize;

    /** Data point symbol fill (enum).*/
    protected int pointFillStyle;

    /** Data point joining line style (enum).*/
    protected int lineStyle;

    protected Vector points;

    protected boolean showPointSymbols;

    protected boolean showErrorBars;

    protected boolean showJoiningLine;

    /** True if this graph is attached to the primary Y axis, false if
     * it is attached to the secondary.
     */
    boolean attachedPrimary;
    
    public Graph() {
	points = new Vector();
	pointColor     = DEFAULT_POINT_COLOR;
	pointSize      = 2;
	pointSymbol    = SQUARE_SYMBOL;
	pointFillStyle = FILL_STYLE_FILLED;
	showPointSymbols = true;
	showErrorBars = false;
	showJoiningLine = false;
	
    }

    public String getLegend() { return legend; }

    public void setLegend(String legend) { this.legend = legend; }
   
    public Color getPointColor() { return pointColor; };

    public void setPointColor(Color color) { this.pointColor = color; }

    public int getPointSymbol() { return pointSymbol; }

    public void setPointSymbol(int symbol) { this.pointSymbol = symbol; }
 
    public int getPointSize() { return pointSize; }
    
    public void setPointSize(int size) { this.pointSize = size; }

    public int getPointFillStyle() { return pointFillStyle; }

    public void setPointFillStyle(int style) { this.pointFillStyle = style; }
  
    public int getLineStyle() { return lineStyle; }

    public void setLineStyle(int style) {this.lineStyle = style; }

    public boolean isAttachedPrimary() { return attachedPrimary; }

    public boolean isAttachedSecondary() { return !attachedPrimary; }

    public void setAttachedPrimary(boolean attach) { attachedPrimary = attach; }
 
    public boolean showPointSymbols() { return showPointSymbols; }

    public void setShowPointSymbols(boolean show) { this.showPointSymbols = show; }

    public boolean showErrorBars() { return showErrorBars; }

    public void setShowErrorBars(boolean show) { this.showErrorBars = show; }
    
    public boolean showJoiningLine() { return showJoiningLine; }

    public void setShowJoiningLine(boolean show) { this.showJoiningLine = show; }

    /** Add a point with (x,y) coordinates and no error bars.
     * If the chart has its scrolling flag set, then if the x
     * value exceeds the current xHi field the chart's advance()
     * method is called to advance the xLo, xHi fields by the
     * amount specified by axisAdvance field.
     */
    public DataPoint addPoint(double x, double y) {
	return addPoint(x, y, 0.0, 0.0);	
    }
    
    /**
     * @param yErrorPlus The error amount to <i>add</i> to y.
     * @param yErrorMinus The error amount to <i>subtract</i> from y.
     */
    public DataPoint addPoint(double x, double y,
                              double yErrorPlus, double yErrorMinus) {
        DataPoint point = new DataPoint(x, y, yErrorPlus, yErrorMinus);

	// update chart axes ??????? if scrolling ?????
	synchronized (points) {
	    points.add(point);
	}
        return point;
    }
    
    /** Draw the Graph in whatever way it gets drawn within
     * the area of the Graphics context specified..
     * @param graphics The graphics context to draw into.
     * @param bounds The (graph) screen area in which to draw.
     * @param units Start and extent of the user units scales.
     */
    public void draw(Graphics g, Rectangle bounds, DoubleRectangle units) {
	
	if (pointColor != null)
	    g.setColor(pointColor);

	// Iterate over the datapoints.
	synchronized (points) {
	Iterator it = points.iterator();
	boolean first = true;
	int lastX = 0;
	int lastY = 0;
	while (it.hasNext()) {
	    
	    DataPoint point = (DataPoint)it.next();

	    //point position is .
	    int x    = getScreenX(point.getX(), bounds, units);
	    int y    = getScreenY(point.getY(), bounds, units);
	    if (x >= bounds.x && x <= (bounds.x + bounds.width) && 
		y >= bounds.y && y <= (bounds.y + bounds.height)) { 
		
		int size = pointSize;
		
		// Draw the point symbol. (if wanted).
		if (showPointSymbols) {
		    drawSymbol(g, x, y);
		}
		
		// Deal with errorbars. (if wanted).
		if (showErrorBars) {
		    int errorPlus  = getScreenY(point.getY() + point.getErrorPlus(),  bounds, units);
		    int errorMinus = getScreenY(point.getY() - point.getErrorMinus(), bounds, units);
		    
		    g.drawLine(x,   errorPlus,  x,   errorMinus);
		    g.drawLine(x-2, errorPlus,  x+2, errorPlus);
		    g.drawLine(x-2, errorMinus, x+2, errorMinus);
		}
	    }

	    // Deal with joining lines.	  
	    if (showJoiningLine) {
		if (first) {
		    first = false;
		} else {
		    double intX = 0.0;
		    double intY = 0.0;
		    double maxX = bounds.x + bounds.width;
		    double minX = bounds.x;
		    double maxY = bounds.y + bounds.height;
		    double minY = bounds.y;
		   
		    boolean last_IN = (lastX >= minX && lastX <= maxX &&
				       lastY >= minY && lastY <= maxY);
		    
		    boolean curr_IN = (x >= minX && x <= maxX && 
				       y >= minY && y <= maxY);
		   
				    
		    if 
			(last_IN && curr_IN) { 
			// a. Last and current are both IN.
			g.drawLine(lastX, lastY, x, y);
		    } else if
			(last_IN && !curr_IN) {
			// b. Last is IN, current is OUT.
			double m1 = (maxY - lastY) / (maxX - lastX); // always +ve.
			double m2 = (minY - lastY) / (maxX - lastX); // always -ve.
			double m  = (y - lastY) / (x - lastX);
			if 
			    (m > m1) {
			    intX = (intY - lastY)/m + lastX;
			    intY = maxY;
			} else if 
			    (m < m2) {
			    intX = (intY - lastY)/m + lastX;
			    intY = minY;
			} else {
			    intX = maxX;
			    intY = m*(intX - lastX) + lastY;
			}
			g.drawLine(lastX, lastY, (int)intX, (int)intY);
		    } else if
			(!last_IN && curr_IN) {
			// c. Last is OUT, current is IN.
			double m3 = (y - maxY) / (x - minX); // always -ve.
			double m4 = (y - minY) / (x - minX); // always +ve.
			double m  = (y - lastY) / (x - lastX);
			if
			    (m < m3) {
			    intX = (intY - y)/m + x;
			    intY = maxY;
			} else if
			    ( m > m4) {
			    intX = (intY - y)/m + x;
			    intY = minY;
			} else {
			    intX = minX;
			    intY = m*(intX - x) + y;
			}
			g.drawLine((int)intX, (int)intY, x, y);
		    } else {
			// d. Last and current are both OUT.
			// Ignore them.
		    }		   
		}
		lastX = x;
		lastY = y;
	    }

	}
	}
    }

    public void drawSymbol(Graphics g, int x, int y) {
	int size = pointSize;

	switch (pointFillStyle) {
	case FILL_STYLE_FILLED:
	    switch (pointSymbol) {
	    case SQUARE_SYMBOL:
		g.fillRect(x-size, y-size, 2*size, 2*size);
		break;
	    case CIRCLE_SYMBOL:
		g.fillOval(x-size, y-size, 2*size, 2*size);
		break;
	    case TRIANGLE_UP_SYMBOL:
		Polygon p1 = new Polygon();
		p1.addPoint(x-size, y-size);
		p1.addPoint(x+size, y-size);
		p1.addPoint(x, y+size);
		g.fillPolygon(p1);
		break;
	    case TRIANGLE_DOWN_SYMBOL:
		Polygon p2 = new Polygon();
		p2.addPoint(x-size, y+size);
		p2.addPoint(x+size, y+size);
		p2.addPoint(x, y-size);
		g.fillPolygon(p2);
		break; 
	    default:
		g.drawLine(x,y,x,y);
	    }
	    break;
	case FILL_STYLE_OPEN:
	    switch (pointSymbol) {
	    case SQUARE_SYMBOL:
		g.drawRect(x-size, y-size, 2*size, 2*size);
		break;
	    case CIRCLE_SYMBOL:
		g.drawOval(x-size, y-size, 2*size, 2*size);
		break;
	    case TRIANGLE_UP_SYMBOL:
		Polygon p3 = new Polygon();
		p3.addPoint(x-size, y-size);
		p3.addPoint(x+size, y-size);
		p3.addPoint(x, y+size);
		g.drawPolygon(p3);
		break;
	    case TRIANGLE_DOWN_SYMBOL:
		Polygon p4 = new Polygon();
		p4.addPoint(x-size, y+size);
		p4.addPoint(x+size, y+size);
		p4.addPoint(x, y-size);
		g.drawPolygon(p4);
		break;
	    default:
		g.drawLine(x,y,x,y);
	    }
	default:
	    g.drawLine(x,y,x,y);
	    break;
	}
    }


    /** Transform the user coordinate into a screen coordinate.
     * @param x The x ordinate (user units).
     * @param area The screen area to draw into (screen pixel units).
     * @param units The range of user units in x and y axes.
     * @return The screen X-ordinate.
     */
    protected int getScreenX(double x, Rectangle area, DoubleRectangle units) {
        double xLo = units.x;
        double xHi = units.x + units.width;
        int value =  area.x + (int)((x - xLo)*((double)(area.width))/(xHi-xLo));
        return value;                 
    }
    
    /** Transform the user coordinate into a screen coordinate.
     * @param y The y ordinate (user units).
     * @param area The screen area to draw into (screen pixel units).
     * @param units The range of user units in x and y axes.
     * @return The screen Y-ordinate.
     */
    protected int getScreenY(double y, Rectangle area, DoubleRectangle units) {
	double yLo = units.y;
        double yHi = units.y + units.height;
        int value =  area.y + area.height - ((int)((y - yLo)*((double)(area.height))/(yHi-yLo)));
        return value;     
    }   

}
