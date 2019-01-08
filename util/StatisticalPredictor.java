package ngat.util;

import java.util.*;

/** Basic Predictor model - uses an array to store a probabilistic 
 * distribution of model samples.
 * Predictions are made by calculating the expectation value of the
 * recorded samples. 
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StatisticalPredictor.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 * $Id$
 */
public class StatisticalPredictor implements Predictor {

    /** A name to identify this Predictor instance in logs.*/
    protected String id;
    
    /** Holds the statictically binned counts.*/
    protected double[] count;

    /** The number of bins to use between low and hi limits.*/
    protected int nbin;

    /**  The anticipated lower bound of the received samples.*/
    protected double low;

    /** The anticipated upper bound of the received samples.*/
    protected double hi;
    
    /** Holds the total samples received.*/
    protected int total;
  
    /** Create a StatisticalPredictor using the supplied settings. 
     * @param id A name to identify this Predictor instance in logs.
     * @param low The anticipated lower bound of the received samples.
     * @param hi The anticipated upper bound of the received samples.
     * @param nbin The number of bins to use.
     */
    public StatisticalPredictor(String id, double low, double hi, int nbin) {
	this.id   = id;
	count     = new double[nbin + 2];
	this.nbin = nbin;
	this.low  = low;
	this.hi   = hi;
	total     = 0;
    }
    
    
    /** Provides a mechanism for updating the model
     * using the supplied reading at the specified time.
     * @param time The time the update is for.
     * @param value The value for the update.
     */
    public void   update(long time, double value) {
	int ibin = 0;
	if 
	    (value < low) {
	    ibin = 0;
	} 
	else if
	    (value > hi) {
	    ibin = nbin + 1;
	} 
	else {		
	    ibin = (int)((value - low) * (double)nbin / (hi - low)) + 1;
	}	
	//System.err.println("Update Count "+ibin+" is "+count[ibin]);
	count[ibin]++;
	total++;
    }
    
    /** Provides a mechanism for updating the model
     * using the set of values at the specified times. The times should be
     * sequentially ordered - what if they are not?.
     * @param times The set of times the updates are for.
     * @param values The set of values for the updates.
     */
    public void   update(long[] times, double[] values) {
	int size = Math.min(times.length, values.length);
	for (int  i =0; i < size; i++) {
	    update(times[i], values[i]);
	}
    }

    /** Returns a prediction of the value of the model
     * variable at the specified time.
     * @param time The time for which a prediction is required.
     */
    public double predict(long time) {
	double sum = 0;
	for (int i = 1; i <= nbin; i++) {
	    sum += (double)count[i] * ( (hi - low) * ((double)i - 0.5) / (double)nbin );
	    //System.err.println("Predict Sumat "+i+" is "+sum+" total is "+total);
	}
	return sum / (double)total;
    }
    
    /** Returns an estimate of the probability (range [0.0 to 1.0] ) of the 
     * model variable lying between <b>x1</b> and <b>x2</b> over the time
     * interval <b>[t1, t2]</b>.
     * @param time1 The start of the interval for which the prediction is required.
     * @param time2 The end of the interval for which the prediction is required.
     * @param x1 The lower limit for the prediction range.
     * @param x2 The upper limit for the prediction range.
     */
    public double predict(long time1, long time2, double x1, double x2) {
	int sum = 0;
	int ibin1 = (int)((x1 - low)/(double)nbin) + 1;
	int ibin2 = (int)((x2 - low)/(double)nbin) + 1;

	for (int i = ibin1; i <= ibin2; i++) {
	    sum += count[i];
	}
	return (double)sum / (double)total;
    }
    
    /** Returns the name of this Predictor.*/
    public String getId() { return id; }
    
    /** Sets the name for this Predictor.*/
    public void setId(String id) { this.id = id; }

}

/** $Log: not supported by cvs2svn $ */
