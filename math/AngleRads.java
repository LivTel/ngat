package ngat.math;

public class AngleRads implements Angle {

    protected double angleRads;

    public AngleRads(double angleRads) {
	this.angleRads = angleRads;
    }

    
    /** Returns the angle in radians.*/
    public double getAngleRadians() {
	return angleRads;
    }

    /** Returns the default style of formatting for the type of Angle.*/
    public String getDefaultFormatString() {
	return ""+angleRads;
    }
}
