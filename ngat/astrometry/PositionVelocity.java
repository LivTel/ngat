package ngat.astrometry;

public class PositionVelocity {

    public double x;

    public double y;

    public double z;
    
    public double vx;
    
    public double vy;

    public double vz;

    public PositionVelocity( double x, double y, double z, double vx, double vy, double vz) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.vx = vx;
	this.vy = vy;
	this.vz = vz;
    }

}
