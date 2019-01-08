package ngat.math;

public class AngleHMS implements Angle {

    protected int h,m;
    protected double s;

    public AngleHMS(int h, int m, double s) {
	this.h = h;
	this.m = m;
	this.s = s;
    }
    
    /** Returns the angle in radians.*/
    public double getAngleRadians() {
	double degs = (3600.0*h + 60.0*m + s)/240.0;
	return Math.toRadians(degs);
    }

    /** Return a readable description of the angle.*/
    public String getDefaultFormatString() {
	StringBuffer result = new StringBuffer();
	result.append(h);
	result.append(":");
	result.append(m);
	result.append(":");	
	result.append(s);
	return result.toString();
    }

}
