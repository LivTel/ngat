package ngat.math;

public class Range {

    private double min;

    private double max;

    public Range(double min, double max) {
	this.min = min;
	this.max = max;
    }

    public double getMinimum() { return min;}

    public double getMaximum() { return max;}

    public String toString() {
	return "["+min+","+max+"]";
    }

}