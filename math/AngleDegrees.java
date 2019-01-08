package ngat.math;

public class AngleDegrees implements Angle {

    protected double angleDegrees;

    public AngleDegrees(double angleDegrees) {
	this.angleDegrees = angleDegrees;
    }

    
    /** Returns the angle in radians.*/
    public double getAngleRadians() {
	return Math.toRadians(angleDegrees);
    }

  /** Returns the default style of formatting for the type of Angle.*/
    public String getDefaultFormatString() {
	return ""+angleDegrees;
    }
}
