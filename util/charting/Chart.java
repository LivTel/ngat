package ngat.util.charting;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/** Represnts a chart of possibly several graphs.*/
public class Chart extends JPanel {

    public static final int LEGEND_X_OFFSET = 50;
    
    public static final int LEGEND_PADDING  = 20;

    public static final Color DEFAULT_CHART_BG_COLOR = Color.gray;

    public static final Color DEFAULT_GRAPH_BG_COLOR = Color.cyan;
    
    public static final Color DEFAULT_BOUNDARY_COLOR = Color.black;

    public static final Color DEFAULT_GRID_COLOR     = Color.white;

    protected Axis xAxis;

    protected Axis yAxisPrimary;

    protected Axis yAxisSecondary;

    protected Map graphs;

    protected Title title;

    protected Color chartBgColor;

    protected ImageIcon chartBgImage;

    protected Color graphBgColor;

    protected Color boundaryColor;

    /** Insets to graph area - (screen pixel units).*/
    protected Insets graphInsets;

    /** The standard amount to advance the x-axis by when scrolling.*/
    protected double axisAdvance;

    protected boolean scrolling;
    
    protected boolean showLegend;

    protected Vector markers;

    /** Instantaneously holds the area of the screen in which the Graphs are drawn (screen pixel units).*/
    Rectangle graphArea;
    
    /** Instantaneously holds the drawable area of the whole screen (screen pixel units).*/
    Rectangle screen;

    public Chart() {
        super(true);
        graphs = new HashMap();
        chartBgColor  = DEFAULT_CHART_BG_COLOR;
        graphBgColor  = DEFAULT_GRAPH_BG_COLOR;
	boundaryColor = DEFAULT_BOUNDARY_COLOR;
	graphInsets   = new Insets(0, 0, 0, 0);
	// Setup.
	xAxis          = new Axis();
	// Setup.
	yAxisPrimary   = new Axis();
	// Setup.
	yAxisSecondary = new Axis();

	scrolling  = false;
	showLegend = false;

	markers = new Vector();
    }

    public Axis getXAxis() { return xAxis; }

    public void setXAxis(Axis axis) { this.xAxis = axis; }

    public Axis getYAxisPrimary() { return yAxisPrimary; }

    public Axis getYAxisSecondary() { return yAxisSecondary; }


    public Title getTitle() { return title; }

    public void setTitle(Title title) { this.title = title; }


    public Color getChartBgColor() { return chartBgColor; }

    public void setChartBgColor(Color color) { this.chartBgColor = color; }

 
    public ImageIcon getChartBgImage() { return chartBgImage; }

    public void setChartBgImage(ImageIcon image) { this.chartBgImage = image; }
    
    
    public Color getGraphBgColor() { return graphBgColor; }
    
    public void setGraphBgColor(Color color) { this.graphBgColor = color; }
    

    public Color getBoundaryColor() { return boundaryColor; }
    
    public void setBoundaryColor(Color color) { this.boundaryColor = color; }
    

    public Insets getGraphInsets() { return graphInsets; }
    
    public void setGraphInsets(Insets insets) { graphInsets = (Insets)insets.clone(); }
 
    public void setGraphInsets(int top, int left, int bottom, int right) {
	graphInsets = new Insets(top, left, bottom, right);
    }

    public double getAxisAdvance() { return axisAdvance; }
    
    public void setAxisAdvance(double advance) { this.axisAdvance = advance; }
    
    
    public boolean isScrolling() { return scrolling; }
    
    public void setScrolling(boolean scrolling) { this.scrolling = scrolling; }
    
    public boolean showLegend() {  return showLegend; }

    public void setShowLegend(boolean show) { this.showLegend = show; }


    /** Add the specified Graph to this Chart.*/
    public void addGraph(String title, Graph graph) {       
        graphs.put(title, graph);
    }

    public Graph getGraph(String title) {
	return (Graph)graphs.get(title);
    }

    /** Add a marker at the specified Y axis level and using the specified style.*/
    public void addMarker(double mark, int style) {
	markers.add(new Double(mark));
    }

    /** Called externally to repaint this Chart.*/
    public void redraw() {
        repaint();
    }

    /** paint the display.*/
    public void paint(Graphics g) {

        super.paint(g);
 
	int scrWidth  = getSize().width;
        int scrHeight = getSize().height;
	screen = new Rectangle(0, 0, scrWidth, scrHeight);
      	
	// Screen units of the graphing area.
	graphArea = new Rectangle(graphInsets.left, 
				  graphInsets.top, 
				  scrWidth  - graphInsets.left - graphInsets.right,
				  scrHeight - graphInsets.top  - graphInsets.bottom);

        // Background image/color.
        if (chartBgColor != null) {
            g.setColor(chartBgColor);
            g.fillRect(0, 0, screen.width, screen.height);
        }

        // Graph area background.
        if (graphBgColor != null) {
	    g.setColor(graphBgColor);	  
//  	    Graphics2D g2d = (Graphics2D)g;
//  	    g2d.setPaint(new GradientPaint(graphArea.x, 
//  					   graphArea.y, 
//  					   new Color(50,150,240),
//  					   graphArea.x+graphArea.width,
//  					   graphArea.y+graphArea.height,
//  					   new Color(240,220,200)));
	    g.fillRect(graphArea.x,
		       graphArea.y,
		       graphArea.width, 
		       graphArea.height);
	    
	    
        }

        // Graph area boundary.
	if (boundaryColor != null) {
	    g.setColor(boundaryColor);
	    g.drawRect(graphArea.x,
		       graphArea.y,
		       graphArea.width, 
		       graphArea.height);
	}

        // Title. (put it in center of GraphArea at top.
        if (title != null) {
            title.draw(g, graphArea);
        }

        // X-axis.
        if (xAxis != null) {
            paintXAxis(g);
        }
	
        // Y-axis (primary).
        if (yAxisPrimary != null) {
            paintYAxisPrimary(g);
        }

        // Y-axis (secondary).
        if (yAxisSecondary != null) {
            paintYAxisSecondary(g);
        }

        // Each graph.	
        Iterator it = graphs.values().iterator();

	int count = 0; 
	FontMetrics fm = g.getFontMetrics();
	int maxstrlen = 0;
	Graph graph = null;
        while (it.hasNext()) {	    
            graph = (Graph)it.next();
	    // Define the limits of the axes (user units).	
	    DoubleRectangle units = null;
	    if (graph.isAttachedPrimary()) {		
		units = new DoubleRectangle(xAxis.getMinimum(), 
					    yAxisPrimary.getMinimum(),
					    xAxis.getExtent(),
					    yAxisPrimary.getExtent());
	    } else {
		units = new DoubleRectangle(xAxis.getMinimum(), 
					    yAxisSecondary.getMinimum(),
					    xAxis.getExtent(),
					    yAxisSecondary.getExtent());		
	    }
	    graph.draw(g, graphArea, units);	
	   
	    // ################################################################################
	    // ##This needs some fiddling with because of drawString using bottom, left corner.
	    // ################################################################################
	    if (showLegend()) {
		// We allow 10 pixels from the legend box left.
		graph.drawSymbol(g, 
				 graphArea.x + graphArea.width + LEGEND_X_OFFSET + LEGEND_PADDING,
				 graphArea.y + (count+1)*(fm.getMaxAscent() + fm.getMaxDescent()));
		// We allow 10 pixels for the symbol
		g.drawString(graph.getLegend(),
			   graphArea.x + graphArea.width + LEGEND_X_OFFSET + 2*LEGEND_PADDING,
			   graphArea.y + (count+1)*(fm.getMaxAscent() + fm.getMaxDescent()));
		// See how long the text is.
		int strlen = fm.stringWidth(graph.getLegend());
		if (strlen > maxstrlen)
		    maxstrlen = strlen;		
	    }
	    count++;
        }
	
	// Draw the legend box.
	g.setColor(Color.blue);
	g.drawRect(graphArea.x + graphArea.width + LEGEND_X_OFFSET,
		   graphArea.y,
		   maxstrlen + 3*LEGEND_PADDING,
		   (count+1)*(fm.getMaxAscent() + fm.getMaxDescent()));
		   
	
    }
    
    
    /** Paints the X-axis into the graphics context.*/
    protected void paintXAxis(Graphics g) {
	
	double xLo = xAxis.getMinimum();
	double xHi = xAxis.getMaximum();
	
	int graphBase  = screen.height - graphInsets.bottom;
	// also = graphArea.y + graphArea.height;

	// Major ticks.	
	if (xAxis.showMajorTicks()) {
	    
	    g.setColor(xAxis.getGridColor());	    
	 
	    double tic = ((int)(xLo/xAxis.getMajorTickInterval()) + 1)* xAxis.getMajorTickInterval();
	    
	    while (tic < xHi) {
		
		int iTic = getScreenX(tic);
		
		g.drawLine(iTic, graphBase,
			   iTic, graphBase + 10);
		
		if (xAxis.showMajorGrid()) {
		    g.drawLine(iTic, graphBase, iTic, graphArea.y);
		    // USE THE CORRECT STYLE
		}		
		tic += xAxis.getMajorTickInterval();
	    }	    
	}   
	
	// Minor ticks.	
	if (xAxis.showMinorTicks()) {
	    
	    g.setColor(xAxis.getGridColor());	    
	    double tic = xLo;

	    while (tic < xHi) {		

		int iTic = getScreenX(tic);
		
		g.drawLine(iTic, graphBase,
			   iTic, graphBase + 5);
		
		if (xAxis.showMinorGrid()) { 
		    g.drawLine(iTic, graphBase, iTic, graphArea.y );
		    // USE THE CORRECT STYLE
		}		
		tic += xAxis.getMinorTickInterval();
	    }
	}   
		
	// Labels. We get the X-axis labeller to return a lable for (labPos)
	// then call its label method at (x,y).	
	double lab = ((int)(xLo/xAxis.getMajorTickInterval()) + 1)* xAxis.getMajorTickInterval();


	while (lab < xHi) {	    
	    int iLab = getScreenX(lab);
	    
	    Labeller labeller = xAxis.getLabeller(); 
	    labeller.drawLabel(g, iLab, graphBase + 25, lab);
	    
	    lab += xAxis.getMajorTickInterval();	    
	}
	
    }
    
    /** Paints the Y-axis into the graphics context.*/
    protected void paintYAxisPrimary(Graphics g) {
	
	double yLo = yAxisPrimary.getMinimum();
	double yHi = yAxisPrimary.getMaximum();
	
	int graphRight = screen.width - graphInsets.right;
	// also = graphArea.x + graphArea.width;

	// Major ticks.	
	if (yAxisPrimary.showMajorTicks()) {
	    
	    g.setColor(yAxisPrimary.getGridColor());	    
	    double tic = yLo;
	    
	    while (tic < yHi) {
		
		int iTic = getScreenYPrimary(tic);
		
		g.drawLine(graphArea.x, iTic,
			   graphArea.x - 10, iTic);
		
		if (yAxisPrimary.showMajorGrid()) {
		    g.drawLine(graphArea.x, iTic, graphRight, iTic);
		    // USE THE CORRECT STYLE
		}		
		tic += yAxisPrimary.getMajorTickInterval();
	    }	    
	}   
	
	// Minor ticks.	
	if (yAxisPrimary.showMinorTicks()) {
	    
	    g.setColor(yAxisPrimary.getGridColor());	    
	    double tic = yLo;

	    while (tic < yHi) {		

		int iTic = getScreenYPrimary(tic);
		
		g.drawLine(graphArea.x, iTic,
			   graphArea.x - 5, iTic);
		
		if (yAxisPrimary.showMinorGrid()) { 
		    g.drawLine(graphArea.x, iTic, graphRight, iTic);
		    // USE THE CORRECT STYLE
		}		
		tic += yAxisPrimary.getMinorTickInterval();
	    }
	}   
	
	// Labels. We get the X-axis labeller to return a lable for (labPos)
	// then call its label method at (x,y).	
	double lab = yLo;
	
	while (lab < yHi) {	    
	    int iLab = getScreenYPrimary(lab);
	    
	    Labeller labeller = yAxisPrimary.getLabeller(); 
	    labeller.drawLabel(g, graphArea.x - 35, iLab, lab);
	    
	    lab += yAxisPrimary.getMajorTickInterval();	    
	}
	
	// Add any marker lines...........####TEMP#####
	Iterator it = markers.iterator(); 
	g.setColor(Color.red);
	while (it.hasNext()) {	    
	    int iYMark = getScreenYPrimary(((Double)it.next()).doubleValue());	   
	    g.drawLine(graphArea.x, iYMark, graphRight, iYMark );
	}
	
    }
    
    /** Paints the Y-axis into the graphics context.*/
    protected void paintYAxisSecondary(Graphics g) {
	
	double yLo = yAxisSecondary.getMinimum();
	double yHi = yAxisSecondary.getMaximum();
	
	int graphRight = screen.width - graphInsets.right;
	// also = graphArea.x + graphArea.width;

	// Major ticks.	
	if (yAxisSecondary.showMajorTicks()) {
	    
	    g.setColor(yAxisSecondary.getGridColor());	    
	    double tic = yLo;
	    
	    while (tic < yHi) {
		
		int iTic = getScreenYSecondary(tic);
		
		g.drawLine(graphRight, iTic,
			   graphRight + 10, iTic);
		
		if (yAxisSecondary.showMajorGrid()) {
		    g.drawLine(graphArea.x, iTic, graphRight, iTic);
		    // USE THE CORRECT STYLE
		}		
		tic += yAxisSecondary.getMajorTickInterval();
	    }	    
	}   
	
	// Minor ticks.	
	if (yAxisSecondary.showMinorTicks()) {
	    
	    g.setColor(yAxisSecondary.getGridColor());	    
	    double tic = yLo;

	    while (tic < yHi) {		

		int iTic = getScreenYSecondary(tic);
		
		g.drawLine(graphRight, iTic,
			   graphRight + 5, iTic);
		
		if (yAxisSecondary.showMinorGrid()) { 
		    g.drawLine(graphArea.x, iTic, graphRight, iTic);
		    // USE THE CORRECT STYLE
		}		
		tic += yAxisSecondary.getMinorTickInterval();
	    }
	}   
	
	// Labels. We get the X-axis labeller to return a lable for (labPos)
	// then call its label method at (x,y).	
	double lab = yLo;
	
	while (lab < yHi) {	    
	    int iLab = getScreenYSecondary(lab);
	    
	    Labeller labeller = yAxisSecondary.getLabeller(); 
	    labeller.drawLabel(g, graphRight + 15, iLab, lab);
	    
	    lab += yAxisSecondary.getMajorTickInterval();	    
	}
	
    }

    protected int getScreenX(double x) {
        double xHi = xAxis.getMaximum();
        double xLo = xAxis.getMinimum();
        int value =  graphArea.x +
            (int)((x - xLo)*((double)(graphArea.width))/(xHi-xLo));
        return value;                 
    }

    protected int getScreenYPrimary(double y) {
	double yHi = yAxisPrimary.getMaximum();
        double yLo = yAxisPrimary.getMinimum();
        int value =  graphArea.y + graphArea.height - ((int)((y - yLo)*((double)(graphArea.height))/(yHi-yLo)));
        return value;     
    } 

    protected int getScreenYSecondary(double y) {
	double yHi = yAxisSecondary.getMaximum();
        double yLo = yAxisSecondary.getMinimum();
        int value =  graphArea.y + graphArea.height - ((int)((y - yLo)*((double)(graphArea.height))/(yHi-yLo)));
        return value;     
    }

    /** Scroll the X-axis.*/
    public void scroll() {
        xAxis.advance(axisAdvance);
    }


}










