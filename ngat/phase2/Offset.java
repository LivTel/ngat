package ngat.phase2;

import java.io.*;

public class Offset implements Serializable {

    private double dx;

    private double dy;

    public Offset() {
	this(0.0,0.0);
    }

    public Offset(double dx, double dy) {
	this.dx = dx;
	this.dy = dy;
    }

    public void setXOffset(double dx) { this.dx = dx; }
    public double getXOffset() { return dx; }

    public void setYOffset(double dy) { this.dy = dy; }
    public double getYOffset() { return dy; }

    public String toString() { return "("+dx+","+dy+")"; }

}
