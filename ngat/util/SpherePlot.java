package ngat.util;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel containing a projection of the surface of a 3D sphere, from a
 * given viewing direction.
 *
 *
 * $Id$
 *
 * */
public class SpherePlot extends GraphPlot {

    /** The altitude of the viewpoint. (polar coord). */
    double th0;
    /** The azimuth of the viewpoint. (polar coord). */
    double phi0;
    /** Holds the number of points to paint in a highlight color.*/
    int nReds;

    double boxth;
    double boxphi;
    double boxdth;
    double boxdph;
    boolean dobox;

    float[][] xPts;

    float[][] yPts;

    int[] nPts;

    static final double PI = Math.PI;

    /** Construct a SphereView observed from the chosen direction.
     * @param th0 the altitude coord of the viewpoint.
     * @param phi0 the azimuth coord of the viewpoint. */
    public SpherePlot(double th0, double phi0) {
	super(10000, -1.0f, 1.0f, -1.0f, 1.0f);
	this.th0 = th0;
	this.phi0 = phi0;
	this.nReds = 1;
	nPts = new int[5];
	xPts = new float[1000][5];
	yPts = new float[1000][5];	
    }
    
    /** Translate from (th,phi) to projected X coordinate. */
    public double getSphereX(double th, double phi) {
	return Math.cos(th)*Math.cos(phi - phi0 - PI/2);
    }

    /** Translate from (th,phi) to projected Y coordinate. */
    public double getSphereY(double th, double phi) {
	return Math.sin(th)*Math.cos(th0) - Math.cos(th)*Math.sin(th0)*Math.cos(phi-phi0);
    }

    /** Draw the latitude and longitude projections. */
    public void drawGrid(Graphics g) {

	// draw the longitude lines.

	int ll = 0;
	double phi = 0.0;

	while (phi < 2*PI) {

	    // draw longitude phi.
	   
	    double th = -PI/2;

	    int xold = (int)getScreenX((float)getSphereX(th,phi));
	    int yold = (int)getScreenY((float)getSphereY(th,phi));

	    while (th < PI/2) {

		double xu = getSphereX(th, phi);
		double yu = getSphereY(th, phi);

		if (th == 0.0) {
		    g.setColor(Color.red);
		} else g.setColor(Color.black);
		if (phi > phi0+PI/2 && phi < phi0+3*PI/2) g.setColor(Color.magenta);

		int xs = (int)getScreenX((float)xu);
		int ys = (int)getScreenY((float)yu);
		g.drawLine(xold,yold,xs,ys);

		xold = xs;
		yold = ys;
		    
		th += PI/300;
	    }

	    phi += PI/6;
	}

	// draw equator. and latitudes in PI/8 increments.

	drawLatitude(g, 0.0);
	drawLatitude(g, PI/8);
	drawLatitude(g, PI/4);
	drawLatitude(g, 3*PI/8);
	drawLatitude(g, -PI/8);
	drawLatitude(g, -PI/4);
	drawLatitude(g, -3*PI/8);

	g.setColor(Color.orange);
	int xCen = (int)getScreenX((float)getSphereX(0.0,0.0));
	int yCen = (int)getScreenY((float)getSphereY(0.0,0.0));
	g.fillOval(xCen,yCen,8,8);
	g.setColor(Color.black);
    }

    /** Draws a box of latitude lines.*/
    public void putBox(double th, double phi, double dth, double dph) {
	boxth  = th;
	boxphi = phi;
	boxdth = dth;
	boxdph = dph;
	dobox  = true;
	repaint();
    }

    /** Draws a single latitude line. 
     * @param th the latitude in rads. */
    protected void drawLatitude(Graphics g, double th) {
	drawLatitude(g, th, 0.0, 2*PI);
    }

    /** Draws a latitude arc.*/
    protected void drawLatitude(Graphics g, double th, double ph0, double ph1) {

	double phi = ph0;

	int xold = (int)getScreenX((float)getSphereX(th, phi));
	int yold = (int)getScreenY((float)getSphereY(th, phi));
	
	while (phi < ph1) {

	    double xu = getSphereX(th, phi);
	    double yu = getSphereY(th, phi);
	    
	    g.setColor(Color.black);
	    if (phi > phi0+PI/2 && phi < phi0+3*PI/2) g.setColor(Color.magenta);

	    int xs = (int)getScreenX((float)xu);
	    int ys = (int)getScreenY((float)yu);
	    g.drawLine(xold,yold,xs,ys);
	    
	    xold = xs;
	    yold = ys;
	    
	    phi += PI/400;
	    
	}
    }
    
    
    /** Overridden method to paint the panel. */
    public void paint(Graphics g) {

	// Clear the screen between repaints (KGP does NOT!).
	// this is just the default update method being replaced.
	g.setColor(getBackground());
	g.fillRect(0,0,getSize().width,getSize().height);
	g.setColor(getForeground());

	drawGrid(g);

	// Draw the bounding circle.

	g.setColor(Color.blue);
	int oX = (int)deltaX;
	int oY = (int)deltaY;
	int dX = getSize().width-2*oX;
	int dY = getSize().height-2*oY;
	g.drawOval(oX, oY, dX, dY);
	g.setColor(Color.black);

	for (int k = 0; k < 5; k++) {

	    // paint the points. xPts, yPts hold the (th,phi) for the point!	   

	    for (int i = 1; i < nPts[k]; i++) {				
		int x1 = (int)getScreenX((float)getSphereX(xPts[i][k],yPts[i][k]));
		int y1 = (int)getScreenY((float)getSphereY(xPts[i][k],yPts[i][k]));
		
		int size = 3;
		g.setColor(Color.black);

		// Check whether the point is on the 'darkside' of the sphere.

		double csa = Math.cos(xPts[i][k])*Math.cos(th0)*Math.cos(yPts[i][k]-phi0)+Math.sin(xPts[i][k])*Math.sin(th0);
		if (csa <= 0.0) {
		    g.setColor(Color.magenta);
		    size = 2;
		}

		// Make the last nReds entered points red.
		if (i >= nPts[k]-nReds) g.setColor(Color.red); 

		g.fillOval(x1-size,y1-size,2*size,2*size);	
		
	    }
	}
	
	// draw any box.
	if (dobox) {
	    g.setColor(Color.blue);
	    double th = boxth - boxdth;
	    while (th < boxth + boxdth) {
		drawLatitude(g, th, boxphi-boxdph, boxphi+boxdph);
		th = th + (boxdth/10);
	    }
	    g.setColor(Color.red);
	    g.drawLine((int)getScreenX((float)getSphereX(th0, phi0)), 
		       (int)getScreenY((float)getSphereY(th0, phi0)),
		       (int)getScreenX((float)getSphereX(boxth, boxphi)),
		       (int)getScreenY((float)getSphereY(boxth,boxphi)));
	}
	
	g.setColor(Color.black);

    } // paint

    /** Place a point at (th, phi) polar coord. 
     * @param th the altitude of the point in rads.
     * @param phi the azimuth of the point in rads. */
    public void putPoint(float th, float phi) {
	nPts[0]++;
	xPts[nPts[0]][0] = th;
	yPts[nPts[0]][0] = phi;
	repaint();
    }

    public void setReds(int nReds) {
	this.nReds = nReds;
    }
	
    public void update(Graphics g) {
	g.setColor(getBackground());
	g.fillRect(0,0,getSize().width,getSize().height);
	g.setColor(getForeground());
	paint(g);
    }

    /** Increment the viewpoint altitude.
     *@param d the increment in rads. */
    public void incTh0(double d) {	
	th0 = th0 + d % (Math.PI*2) ;
	repaint();
    }

    /** Decrement the viewpoint altitude.
     * @param d the decrement in rads. */
    public void decTh0(double d) {
	th0 = th0 - d % (Math.PI*2);
	repaint();
    }

    /** Increment the viewpoint azimuth.
     * @param d the increment in rads. */
    public void incPhi0(double d) {
	phi0 = phi0 + d % (Math.PI*2);
	repaint();
    }

    /** Decrement the viewpoint azimuth.
     * @param d the decrement in rads. */
    public void decPhi0(double d) {
	phi0 = phi0 - d % (Math.PI*2);
	repaint();
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/21 16:44:51  snf
/** Initial revision
/** */






