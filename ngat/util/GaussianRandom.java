package ngat.util;

import java.util.*;

/** Generates random (doubles) with a Gaussian distribution.
 * <br><br>
 * $Id: GaussianRandom.java,v 1.5 2007-11-20 11:13:44 snf Exp $
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

    /** Stores the interval.*/
    protected double delta;

    /** Stores the cumulative frequency distribution.*/
    protected double ff[];

    /** Random number generator.*/
    protected Random randomBastard;

    /** Create a GaussianRandom generator. The random numbers are
     * picked from the inverse of the cumulative distribution by linear
     * interpolation. For increased accuracy a large granularity can 
     * be chosen. A later implementation will vary the interpolation
     * granularity around the mean for increased efficiency.
     * @param mean The mean of the distribution.
     * @param sdev The standard deviation.
     * @param n The granularity. The larger this is the more
     * accurately the distribution will be generated but the longer
     * the calculations will take. The default value
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
	//this(mean, sdev, DEFAULT_N);
	this.mean = mean;
	this.sdev = sdev;
	randomBastard = new Random(System.currentTimeMillis());
    }

    /** New implementation using java.util.Random .*/
    public double random() {
	return randomBastard.nextGaussian()*sdev + mean;	
    }
    
    
 //    /** @return A random number with the specified Gaussian distribution.*/
//     public double random() {
// 	double r = Math.random();
// 	for (int  i = 0; i < (n-1); i++) {
// 	    if ( (ff[i] < r) && (r < ff[i+1]) ) {
// 		double ip = i + (r-ff[i])/(ff[i+1]-ff[i]);
// 		return ip;
// 	    }
// 	}
// 	return -1.0;
//     }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2007/11/20 10:52:41  snf
/** fixed
/**
/** Revision 1.3  2007/11/20 10:52:17  snf
/** changed to use java.util.Random
/**
/** Revision 1.2  2001/02/23 18:49:14  snf
/** Added linger and awaken.
/**
/** Revision 1.1  2000/12/11 14:35:56  snf
/** Initial revision
/** */
