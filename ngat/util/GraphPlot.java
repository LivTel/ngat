package ngat.util;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Enumeration;

/**
 * A generic panel for drawing graphical output in 2D. Multiple channels can be defined
 * and symbol-type, size and color set for each channel. Subclasses should redefine the
 * paint() and putPoint() methods to carry out any additional processing.
 *
 * $Id: GraphPlot.java,v 1.1 2000-11-21 16:24:33 snf Exp $
 *
 */ 
public class GraphPlot extends JPanel {
   
    /** Low limit of X axis in user units.*/
    protected float xLo;

    /** High limit of X axis in user units*/
    protected float xHi;

    /** Low limit of Y axis in user units*/
    protected float yLo;

    /** High limit of Y axis in user units*/
    protected float yHi; 

    /** Range of X axis in user units.*/
    protected float deltaX; 

    /** Range of Y axis in user units.*/
    protected float deltaY;

    /** Records the points to plot.*/
    protected CircularList pts[];

    /** Number of points recorded in each channel.*/
    protected int nPts[];

    /** Mark set per channel.*/
    protected int mark[];

    /** Mark size set per channel.*/
    protected int markSize[];

    /** Color set per channel.*/
    protected Color markColor[];

    /** Stores annotation text.*/
    protected String annotateText[];

    /** X position of ith annotation text.*/
    protected float annotateX[];

    /** Y position of ith annotation text.*/
    protected float annotateY[];

    /** True if channel i points are to be joined.*/
    protected boolean joinPoints[];

    /** Records current channel.*/   
    protected int level;

    /** Number of annotations.*/
    protected int nAnn;

    /** Standard number of points to plot if unspecified.*/
    public static final int MAX_PTS = 100;

    /** Indicates a diamond shape should be drawn.*/
    public static final int DIAMOND = 1;

    /**  Indicates a spot shape should be drawn.*/
    public static final int SPOT = 2;

    /**  Indicates a square shape should be drawn.*/
    public static final int SQUARE = 3;

    /**  Indicates a cross shape should be drawn.*/
    public static final int CROSS = 4;

    /** Default Graph with MAX_PTS number of plot points per channel. */
    public GraphPlot(float x1, float x2, float y1, float y2) {
	this(MAX_PTS, x1, x2, y1, y2);
    }

    /** Constructs a Graph panel with maxPoints plotted points per channel and with
     * axes ranging from X: (x1 to x2) and Y: (y1 to y2) in user units. */
    public GraphPlot(int maxPoints, float x1, float x2, float y1, float y2) {
	super(true);
	xLo = x1;
	xHi = x2;
	yLo = y1;
	yHi = y2;
	
	deltaX = 20.0F;
	deltaY = 20.0F;
	
	level = 1;

	nPts = new int[5];
	pts = new CircularList[5];
	mark = new int[5];
	markSize = new int[5];
	markColor = new Color[5];
	joinPoints = new boolean[5];

	nAnn = 0;
	annotateText = new String[10];
	annotateX = new float[10];
	annotateY = new float[10];

	// Initialise symbols.

	for (int i = 0; i < 5; i++) {
	    pts[i] = new CircularList(maxPoints);
	    mark[i] = CROSS;
	    markSize[i] = 3;
	    joinPoints[i] = false;
	}

	markColor[0] = Color.blue;
	markColor[1] = Color.red;
	markColor[2] = Color.magenta;
	markColor[3] = Color.black;
	markColor[4] = Color.green;

	setBackground(Color.white);
	
    }

    /** Plot a point at user coordinates (x, y) on channel 1). */
    public void putPoint(float x , float y) {
	putPoint(x, y, 1);
    }
    
    /** Plot a point at user coordinates (x, y) on specified channel. 
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param chan The channel number.
     */
    public void putPoint(float x, float y, int chan) {
	pts[level].add(x,y);
	repaint();
    }

    /** Add a piece of text annotation at specified position.
     * @param text the message to be placed.
     * @param x the x coordinate (user coords).
     * @param y the y coordinate (user coords).
     */
    public void annotate(String text, float x, float y) {
	annotateText[nAnn] = new String(text);
	annotateX[nAnn] = x;
	annotateY[nAnn] = y;
	nAnn++;
	repaint();
    }

    /** Clear all channels.*/
    public void clear() {
	for (int k = 0; k < 5; k++) {
	    nPts[k]=0;
	}
    }

    /** Clear out specified channel.*/
    public void clear(int chan) {
	nPts[chan] = 0;
    }

    /** Clears the annotation.*/
    public void clearAnnotation() {
	nAnn = 0;
    }

    /** Set the symbol type for the specified channel.
     * @param chan The channel.
     * @param type The symbol to use. */
    public void setMark(int chan, int type) { mark[chan] = type;}

    /** Set the size of symbol to use on the specified channel.
     * @param chan The specified channel.
     * @param size The size (in screen pixels) of the symbol. */
    public void setMarkSize(int chan, int size) { markSize[chan] = size;}

    /** Set the Color of the symbols on the specified channel.
     * @param chan The channel.
     * @param color The Color to use for this channel. */
    public void setMarkColor(int chan, Color color) { markColor[chan] = color;}

    /** Toggle joined-up points on this channel.
     * @param chan The channel.
     * @param join If true, points are joined together. */
    public void SetJoinPoints(int chan, boolean joinup) { joinPoints[chan] = joinup;}

    /** Reset the value of xLo. */
    public void setXLo(float x) { xLo = x;}

    /** Reset the value of xHi. */
    public void setXHi(float x) { xHi = x;}

    /** Reset the value of yLo. */
    public void setYLo(float y) { yLo = y;}

    /** Reset the value of yHi. */
    public void setYHi(float y) { yHi = y;}

    /** Translate from user to screen coordinates. */
    protected float getScreenX(float x) {
	float scale = (getSize().width - 2*deltaX)/(xHi - xLo);
	float xOffset = deltaX - xLo*scale;
	return x*scale + xOffset;
    }
    
    /** Translate from user to screen coordinates. */
    protected float getScreenY(float y) {
	float scale = (getSize().height - 2*deltaY)/(yHi - yLo);
	float yOffset = yHi*scale +deltaY;
	return yOffset - y*scale;
    }
    
    /** Calculate and draw the X axis tick marks. */
    protected void drawXTicks(Graphics g) {
      int nTicks = 0;
      int m1 = 0;
      int m2 = 0;
      int xTickPwr = (int)(Math.log((double)(xHi - xLo))/Math.log(10));
      float xTickInt = (float)Math.pow(10, xTickPwr);
      
      while (nTicks < 6) {
	  xTickInt /= 2;
	  m1 = (int)((float)(xLo/xTickInt) + 1);
	  m2 = (int)((float)(xHi/xTickInt));
	  nTicks = m2 - m1 + 1;
      }

      // Calculate vertical grid end points.

      int yMin = (int)getScreenY(yLo);
      int yMax = (int)getScreenY(yHi);

      for (int i = m1; i <= m2; i++) {
	int xLabel = (int)(i * xTickInt);
	int xScreen = (int)getScreenX((float)xLabel);
	int yScreen = (int)getScreenY(yLo);
	g.drawLine(xScreen, yScreen, xScreen, yScreen + 5); // Tick.
	g.drawString("" + xLabel, xScreen - 5, yScreen + 15); // Label	
	g.drawLine(xScreen, yMin, xScreen, yMax); // Vert. grid
      }

      // Minor tick marks.

      xTickInt /= 5;
      m1 = (int)((float)(xLo/xTickInt) + 1);
      m2 = (int)((float)(xHi/xTickInt));
      nTicks = m2 - m1 + 1;
      for (int i = m1; i <= m2; i++) {
	int xLabel = (int)(i * xTickInt);
	int xScreen = (int)getScreenX((float)xLabel);
	int yScreen = (int)getScreenY(yLo);
	g.drawLine(xScreen, yScreen, xScreen, yScreen + 2);
      }


  }
    /** Calculate and draw the Y axis tick marks. */
    protected void drawYTicks(Graphics g) {
	int nTicks = 0;
	int m1 = 0;
	int m2 = 0;
	int yTickPwr = (int)(Math.log((double)(yHi - yLo))/Math.log(10));
	float yTickInt = (float)Math.pow(10, yTickPwr);
	
	while (nTicks < 6) {
	    yTickInt /=2;
	    m1 = (int)(yLo/yTickInt + 1);
	    m2 = (int)(yHi/yTickInt);
	    nTicks = m2 - m1 + 1;
	}

	// Calculate Horizontal grid endpoints.

	int xMin = (int)getScreenX(xLo);
	int xMax = (int)getScreenX(xHi);

	for (int i = m1; i <= m2; i++) {
	    int yLabel = (int)(i * yTickInt);
	    int yScreen = (int)getScreenY((float)yLabel);
	    int xScreen = (int)getScreenX(xLo);
	    g.drawLine(xScreen, yScreen, xScreen - 5, yScreen);
	    g.drawString("" + yLabel, xScreen - 15, yScreen);
	    g.drawLine(xMin, yScreen, xMax, yScreen);
	} 

	// Minor tick marks.

	yTickInt /= 5;
	m1 = (int)((float)(yLo/yTickInt) + 1);
	m2 = (int)((float)(yHi/yTickInt));
	nTicks = m2 - m1 + 1;
	for (int i = m1; i <= m2; i++) {
	    int yLabel = (int)(i * yTickInt);
	    int yScreen = (int)getScreenY((float)yLabel);
	    int xScreen = (int)getScreenX(xLo);
	    g.drawLine(xScreen, yScreen, xScreen - 2, yScreen);
	}
	
    }
    


    /** Draw the axis bounding box. */
    protected void drawAxes(Graphics g) {
	int  x1 = (int)getScreenX(xLo);
	int  x2 = (int)getScreenX(xHi);
	int  y1 = (int)getScreenY(yLo);
	int  y2 = (int)getScreenY(yHi);
	
	g.drawLine(x1, y1, x2, y1);
	g.drawLine(x2, y1, x2, y2);
	g.drawLine(x1, y1, x1, y2);
	g.drawLine(x1, y2, x2, y2);
    }


    /** Draw the annotation text. */    
    protected void drawAnnotation(Graphics g) {
	if (nAnn < 1) return;
	for (int i = 0; i < nAnn; i++) {
	    int x = (int)getScreenX(annotateX[i]);
	    int y = (int)getScreenY(annotateY[i]);
	    g.drawString(annotateText[i], x, y);
	}
    }

    /** Draw the graph points. */
    public void drawPoints(Graphics g){
	UPoint pt = null;

	for (int k = 0; k < 5; k++) {

	    g.setColor(markColor[k]);
	   
	    Enumeration e = pts[k].enumerate();
	    if (!e.hasMoreElements()) continue;

	    pt = (UPoint)e.nextElement();

	    int size = markSize[k];
	    int x1 = (int)getScreenX(pt.x);
	    int y1 = (int)getScreenY(pt.y);

	    while (e.hasMoreElements()) {

		pt = (UPoint)e.nextElement();
				
		int x2 = (int)getScreenX(pt.x);
		int y2 = (int)getScreenY(pt.y);
		
		switch (mark[k]) {
		case (SPOT):
		    g.fillOval(x1-size, y1-size,size*2,size*2);
		    break;
		case (DIAMOND):
		    g.fillOval(x1-size,y1-size,size,size*2);
		    break;
		case (SQUARE):
		    g.fillRect(x1-size,y1-size,size*2,size*2);
		    break;
		case (CROSS):
		    g.drawLine(x1-size,y1,x1+size,y1);
		    g.drawLine(x1,y1-size,x1,y1+size);
		    break;
		default:
		    g.drawLine(x1-size,y1-size,x1+size,y1+size);
		    g.drawLine(x1-size,y1+size,x1+size,y1-size);
		    break;
		}
		g.drawLine(x1-2, y1, x1+2, y1);
		g.drawLine(x1, y1-2, x1, y1+2);	
		
		if (joinPoints[k]) {
		    g.drawLine(x1,y1, x2,y2);
		}

		x1 = x2;
		y1 = y2;
	    }
	}
	g.setColor(Color.black);
    }

    /** Plot the points on the graph. Subclasses may need to override. */
    public void paint(Graphics g) {
	super.paint(g);

	drawAxes(g);
	drawXTicks(g);
	drawYTicks(g);
	drawAnnotation(g);
	drawPoints(g);
	
    }
    
    /** Just calls paint() - helps to avoid flicker. */
    public void update(Graphics g) {
	paint(g);
    }

    /** Used to store sets of points. When the list reaches the specified size
     * the first element becomes overwritten and the axes are moved along.*/
    class CircularList {

	private int nn;

	private UPoint[] array;

	private int start;

	private int next;

	CircularList(int nn) {
	    this.nn = nn;
	    array = new UPoint[nn];
	    for (int  i = 0; i < nn; i++) {
		array[i] = new UPoint();
	    }
	    start = nn-1;
	    next  = 0;
	}
    
	public int getSize() { return nn;}

	public void add(float x, float y) {
	    array[next].x = x;
	    array[next].y = y; 
	    System.out.println("addpoint  next"+next+" start"+start);
	    next = (next + 1)%nn;
	    System.out.println("addpoint +next"+next+" start"+start);
	    if (start == next) {
		
		start = (start + 1)%nn;
		xLo += 1.0f;
		xHi += 1.0f;
		System.out.println("OK SHIFTING AXIS BY 1.0f");
	    }
	}
	
	public Enumeration enumerate() { return new Enumerator();}

	private class Enumerator implements Enumeration {
	    
	    private int count;

	    private int temp;

	    Enumerator() {count = start;}

	    public boolean hasMoreElements() {
		if (count == next) return false;
		return true;
	    }

	    public Object nextElement() {
		temp = count;
		count = (count+1)%nn;
		return array[temp];
	    }

	}
	    
    }

    class UPoint {

	public float x;
	public float y;

	UPoint(float x, float y) {
	    this.x = x;
	    this.y = y;
	}

	UPoint() {this(0.0f, 0.0f);}
    }
    
}

/** $Log: not supported by cvs2svn $ */
