package ngat.util.charting;

public class DataPoint {

    protected double x;

    protected double y;

    /** The error amount (above y value - always positive).*/
    protected double errorPlus;

    /** The error amount (below y value - always positive).*/
    protected double errorMinus;

    /** Create a DataPoint at (user units) (x, y) and no errors.*/
    public DataPoint(double x, double y) {
        this(x, y, 0.0, 0.0);
    }

    /** Create a DataPoint at (user units) (x, y) with errors.*/
    public DataPoint(double x, double y, double dyPlus, double dyMinus) {
	this.x = x;
        this.y = y;
        errorPlus  = dyPlus;
        errorMinus = dyMinus;
    }

    public double getX() { return x;}

    public double getY() { return y;}

    public double getErrorPlus() { return errorPlus;}

    public double getErrorMinus() { return errorMinus;}


    /** Sets the errors above and below the y value. (Absoute errors - both positive).*/
    public void setErrorAbsolute(double dyPlus, double dyMinus) {
        errorPlus  = dyPlus;
        errorMinus = dyMinus;
    }
    /** Sets the errors above and below the y value. (Fractional errors - both positive).*/
    public void setErrorFraction(double fyPlus, double fyMinus) {
        errorPlus  = y * fyPlus;
        errorMinus = y * fyMinus;
    }

}
