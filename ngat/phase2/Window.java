package ngat.phase2;

import ngat.phase2.nonpersist.*;
import java.io.*;
import com.odi.*;

/** Represents a CCD detector window. A Subset of pixels on the CCD,
 * One or more instances of this class reside within each 
 * instance of Instrument (in the windowList field).
 * <br>
 * $Id: Window.java,v 1.1 2000-11-23 11:50:35 snf Exp $ 
 */
public class Window implements Serializable {

    /** The X axis position of the top/left corner of the window.*/
    protected int xs;

    /** The Y axis position of the top/left corner of the window.*/
    protected int ys;

    /** The X axis position of the bottom/right corner of the window.*/
    protected int xe;
    
    /** The Y axis position of the bottom/right corner of the window.*/
    protected int ye;
  
    /** Indicates whether this Window has been activated.*/
    protected boolean active;
    
    /** Create a window of size 0x0 at coordinates (0,0).*/
    public Window() {this(0,0,0,0);}
  
    /** Create a window of size:- <br>
     * width:  (xe-xs) <br>
     * height: (ye-ys) <br>
     * at coordinates: (xs, ys).*/
    public Window(int xs, int ys, int xe, int ye) {
	this.xs = xs;
	this.ys = ys;
	this.xe = xe;
	this.ye = ye;
	active = false;
    }
  
    /** Translate a NonPersistent Window into a Window.*/
    public Window(NPWindow npWindow) {
	super();
	setXs(npWindow.getXs());
	setYs(npWindow.getYs());
	setXe(npWindow.getXe());
	setYe(npWindow.getYe());
	setActive(npWindow.isActive());
    }
    
    /** Translate a Window to Nonpersistent form.*/
    public NPWindow makeNP() {
	NPWindow npWindow = new NPWindow();
	npWindow.setXs(xs);
	npWindow.setYs(ys);
	npWindow.setXe(xe);
	npWindow.setYe(ye);
	npWindow.setActive(active);
	return npWindow;
    }
    
    /** Return the X axis position of the top/left corner of the window.*/
    public int getXe() { return xe;}
    
    /** Set the X axis position of the top/left corner of the window.*/
    public void setXe(int xe) {this.xe = xe;} 
    
    /** Return the Y axis position of the top/left corner of the window.*/
    public int getYe() { return ye;}
      
    /** Set the Y axis position of the top/left corner of the window.*/
    public void setYe(int ye) {this.ye = ye;}
    
    /** Return the X axis position of the bottom/right corner of the window.*/
    public int getXs() { return xs;}
    
    /** Set the X axis position of the bottom/right corner of the window.*/
    public void setXs(int xs) {this.xs = xs;}    
    
    /** Return the Y axis position of the bottom/right corner of the window.*/
    public int getYs() { return ys;}
    
    /** Set the Y axis position of the bottom/right corner of the window.*/
    public void setYs(int ys) {this.ys = ys;}
    
    /** Return the width of the window.*/
    public int getWidth() { return xe-xs; }
    
    /** Return the height of the window.*/
    public int getHeight() { return ye-ys; }  
    
    /** True if the window is active - i.e. in use.*/
    public boolean isActive() { return active;}
  
    /** Set the active state of the window.*/
    public void setActive(boolean active) { this.active = active; }

}

/** $Log: not supported by cvs2svn $ */
