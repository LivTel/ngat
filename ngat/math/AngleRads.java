package ngat.math;

public class AngleRad implements Angle {

    protected double angleRads;

    public AngleRad(double angleRads) {
	this.angleRads = angleRads;
    }

    
    /** Returns the angle in radians.*/
    public double getAngleRadians() {
	return angleRads;
    }

}
