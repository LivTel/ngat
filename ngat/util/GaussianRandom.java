package ngat.net;

/** Generates random (doubles) with a Gaussian distribution.
 * <br><br>
 * $Id: GaussianRandom.java,v 1.1 2000-12-11 14:35:56 snf Exp $
 */
public class GaussianRandom {

    /** The standard granularity setting.*/
    public static final int DEFAULT_N = 21;

    /** The mean of the distribution.*/
    protected double mean;

    /** The standard deviation of the distribution.*/
    protected double sdev;
  
    /** The granularity.*/
    protected double n;

    /** Create a GaussianRandom generator. The random numbers are
     * picked from the inverse of the cumulative distribution by linear
     * interpolation. For increased accuracy a large granularity can 
     * be chosen. A later implementation will vary the interpolation
     * granularity around the mean for increased efficiency.
     * @param mean The mean of the distribution.
     * @param sdev The standard deviation.
     * @param n The granularity. The larger this is the more
     * accurately the distribution will be generated. The default value
     * of 21 gives a reasonable result generally.
     */
    public GaussianRandom(double mean, double sdev, int n) {
	this.mean = mean;
	this.sdev = sdev;
	int nn = n;
	if (nn % 2 == 0) nn = nn+1;
	this.n = nn;
	delta = 10.0*sdev/(nn-1);
	ff = new double[nn];
	double f = 0.0;
	double xc = 0.0;
	double x0 = mean - 5.0*sdev;
	for (int  i = 0; i < nn; i++) {
	    xc = x0 + i * delta;
	    f = f + Math.exp(-1.0*( (xc-mean)*(xc-mean)/(sdev*sdev) ) );
	    ff[i] = f;
	}

	for (int  i = 0; i < nn; i++) {
	    ff[i] = ff[i]/f;
	}
    }

    /** Create a GaussianRandom generator using the default
     * granularity setting of 21.
     * @param mean The mean of the distribution.
     * @param sdev The standard deviation.
     */
    public GaussianRandom(double mean, double sdev) {
	this(mean, sdev, DEFAULT_N);
    }


    /** @return A random number with the specified Gaussian distribution.*/
    public double random() {
	double r = Math.random();
	for (int  i = 0; i < nn-1; i++) {
	    if ( (ff[i] < r) && (r < ff[i+1]) ) {
		double ip = i + (r-ff[i])/(ff[i+1]-ff[i]);
		return ip;
	    }
	}
	return -1.0;
    }

}

/** $Log: not supported by cvs2svn $ */
