package ngat.math;

public class AngleDegrees implements Angle {

    protected double angleDegrees;

    public AngleDegree(double angleDegrees) {
	this.angleDegrees = angleDegrees;
    }

    
    /** Returns the angle in radians.*/
    public double getAngleRadians() {
	return Math.toRadians(angleDegrees);
    }

}
